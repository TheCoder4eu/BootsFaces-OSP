/**
 *  Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */

package net.bootsfaces.component;

import java.io.IOException;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;

/**
 *
 * @author thecoder4.eu
 */

@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css", target="head"),
        @ResourceDependency(library="javax.faces", name="jsf.js", target="body"),
        @ResourceDependency(library="bsf", name="js/bsf.js", target="head")
})
@FacesComponent(C.COMMANDBUTTON_COMPONENT_TYPE)
public class CommandButton extends HtmlCommandButton {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.COMMANDBUTTON_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    

    public CommandButton() {
        setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");

    }
    
    Map<String, Object> attrs;
    //!//boolean white=false;
    
    @Override
    public void decode(FacesContext context) {
    if(this.isDisabled()) {
        return;
    }

            String param = this.getClientId(context);
            if(context.getExternalContext().getRequestParameterMap().containsKey(param)) {
                    queueEvent(new ActionEvent(this));
            }
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
         * <button class="btn btn-large" href="#"><i class="icon-star"></i> Star</button>
         */
        attrs = getAttributes();
        encodeHTML(context);
        
    }
    
    public void encodeHTML(FacesContext context) throws IOException {
        ResponseWriter rw = context.getResponseWriter();
        String CID = getClientId(context);
        
        //1) get the value
        Object value = attrs.get(A.VALUE);
        //2) get the type (submit, button, reset ; default submit)
        String type = A.asString(attrs.get(A.TYPE), A.SUBMIT );
        //3) is it Ajax? (default= false)
        boolean ajax=A.toBool(attrs.get(A.AJAX), false);
        String style=A.asString(attrs.get(H.STYLE));
        
        rw.startElement(H.BUTTON, this);
        rw.writeAttribute(H.TYPE, type, null);
    	rw.writeAttribute(H.ID, CID, H.ID);
    	rw.writeAttribute(H.NAME, CID, H.NAME);
        //TODO rw.writeAttribute(H.TYPE, H.BUTTON, null);
        if(style!=null) { rw.writeAttribute(H.STYLE,style,H.STYLE); }
        
        rw.writeAttribute(H.CLASS, getStyleClasses(), H.CLASS);

        String title= A.asString(attrs.get(A.TITLE), null );
        if (title!=null && title.length()>0) {
            rw.writeAttribute(H.TITLE,title, H.TITLE);
        }
        
        
        
        StringBuilder cJS = new StringBuilder(150); //optimize int
        
        String render=A.asString(attrs.get(A.UPDATE));
        String complete=A.asString(attrs.get(A.ONCOMPLETE));
        //3) is it a Submit?
        if(!type.equals(A.RESET) && !type.equals(A.BUTTON)) {
            // Check if it is in a Form
            String formId = R.findComponentFormId(context, this);
            if(formId == null) {
                    throw new FacesException("CommandButton : '" + CID + "' must be inside a form element");
            }
            
            if(ajax) {
                cJS.append(encodeClick()).append("return BsF.ajax.cb(this, event")
                   .append(render==null ? "" : (",'"+ render+"'"));
                if(complete!=null) { cJS.append(",function(){"+complete+"}"); }
                cJS.append(");");
            } else {
                cJS = new StringBuilder(encodeClick());//Fix Chrome//+"document.forms['"+formId+"'].submit();");
            }
        }
        if(ajax && type.equals(A.RESET)) {
            cJS.append(encodeClick()).append("return BsF.ajax.cb(this, event")
                   .append(render==null ? "" : (",'"+ render+"'"));
                if(complete!=null) { cJS.append(",function(){"+complete+"}"); }
                cJS.append(");");
            //cJS.append("execute: this.id, ");
        }
        if(cJS.toString().length()>1) {//Fix Chrome
           rw.writeAttribute(A.CLICK, cJS.toString(), null);
        }
        
        //TODO : write DHTML attrs - onclick
        //Encode attributes (HTML 4 pass-through + DHTML)
        R.encodeHTML4DHTMLAttrs(rw, attrs, A.ALLBUTTON_ATTRS);
        
        String icon = A.asString(attrs.get(A.ICON));
        String faicon = A.asString(attrs.get(A.ICONAWESOME));
        boolean fa=false; //flag to indicate wether the selected icon set is Font Awesome or not.
        if(faicon != null) { icon=faicon; fa=true; }
        if(icon!=null) {
            Object ialign = attrs.get(A.ICON_ALIGN); //Default Left
            if(ialign!=null && ialign.equals(A.RIGHT)) {
                rw.writeText(value+C.SP, null);
                R.encodeIcon(rw, this, icon, fa);
                //!//R.encodeIcon(rw, this, icon, white);
            } else {
                R.encodeIcon(rw, this, icon, fa);
                //!//R.encodeIcon(rw, this, icon, white);
                rw.writeText(C.SP+value, null);
            }
            
        } else { rw.writeText(value, null); }
        
        rw.endElement(H.BUTTON);
    }
    
    
    private String encodeClick() {
        String js;
        String oc = getOnclick();
        if(oc != null) { js= oc.endsWith(C.SCOLON) ? oc : oc+C.SCOLON; }
        else { js=""; }
        
        
        return js;
    }
    
    private String getStyleClasses() {
        StringBuilder sb; //String s;
        sb = new StringBuilder(40); //optimize int
        sb.append("btn");
        String size = A.asString(attrs.get(A.SIZE));
        if(size!=null) { sb.append(" btn-").append(size); }
        //TBS3 Si usa look, non severity
        String look = A.asString(attrs.get(A.LOOK));
        if(look!=null) { sb.append(" btn-").append(look); }
        else { sb.append(" btn-default"); }
        
        if(A.toBool(attrs.get(A.DISABLED))) { sb.append(C.SP+A.DISABLED); }
        //TODO add styleClass and class support
        String sclass=A.asString(attrs.get(H.STYLECLASS));
        if(sclass!=null) {sb.append(C.SP).append(sclass); }
        
        return sb.toString().trim();
        
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

}
