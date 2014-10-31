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
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
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
    @ResourceDependency(library="bsf", name="css/bsf.css", target="head")
})
@FacesComponent(C.INPUTTEXT_COMPONENT_TYPE)
public class InputText extends HtmlInputText {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.INPUTTEXT_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    public static final String ADDON="input-group-addon";
    
    public InputText() {
        setRendererType(null); // this component renders itself
    }
    
    @Override
    public void decode(FacesContext context) {
        String subVal = (String) context.getExternalContext().getRequestParameterMap().get(getClientId(context));

        if(subVal != null) {
            this.setSubmittedValue(subVal);this.setValid(true);
        }
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        /*
         * 
         */
        
        Map<String, Object> attrs = getAttributes();
        ResponseWriter rw = context.getResponseWriter();
        String clientId = getClientId(context);
        
        //Map<String, Object> attrs = getAttributes();
        
        //"Prepend" facet
        UIComponent prep = getFacet(C.PREPEND);
        //"Append" facet
        UIComponent app = getFacet(C.APPEND);
        boolean prepend = (prep!=null);
        boolean append = (app!=null);
        
        // If the facet contains only one component, getChildCount()=0 and the Facet is the UIComponent
        if(prepend) { R.addClass2FacetComponent(prep, "OutputText", ADDON); }//(prep.getChildren(), "OutputText", S.ADDON); }
        if(append) { R.addClass2FacetComponent(app, "OutputText", ADDON); }
        
        
        String l = A.asString(attrs.get(A.LABEL));
        
        rw.startElement(H.DIV, this);
        rw.writeAttribute(H.CLASS, "form-group", H.CLASS);
        if(l!=null) {
        rw.startElement(H.LABEL, this);
        rw.writeAttribute(A.FOR, clientId, A.FOR);
        rw.writeText(l, null);
        rw.endElement(H.LABEL);
        }
        if(append||prepend) {
        rw.startElement(H.DIV, this);
        rw.writeAttribute(H.CLASS, "input-group", H.CLASS);
        }
        int span = A.toInt(attrs.get(A.SPAN));
        if(span>0) {
            rw.startElement(H.DIV, this);
            rw.writeAttribute(H.CLASS, "col-md-"+span, H.CLASS);
        }
        
        if(prepend) {
            if(prep.getClass().getName().endsWith("Button")||(prep.getChildCount()>0 && prep.getChildren().get(0).getClass().getName().endsWith("Button")) ){
                rw.startElement(H.DIV, this);
                rw.writeAttribute(H.CLASS, "input-group-btn", H.CLASS);
                prep.encodeAll(context);
                rw.endElement(H.DIV);
            } else { prep.encodeAll(context); }
        }
        //Input
        rw.startElement(H.INPUT, this);
        rw.writeAttribute(H.ID, clientId, null);
        rw.writeAttribute(H.NAME, clientId, null);
        rw.writeAttribute(H.TYPE, H.TEXT, null);
        
        StringBuilder sb; String s;
        sb = new StringBuilder(20); //optimize int
        sb.append("form-control");
        String fsize=A.asString(attrs.get(A.FIELDSIZE));
        
        if(fsize!=null) {sb.append(" input-").append(fsize); }
        //styleClass and class support
        String sclass=A.asString(attrs.get(H.STYLECLASS));
        if(sclass!=null) {sb.append(" ").append(sclass); }
        s=sb.toString().trim();
        if(s!=null && s.length()>0) { rw.writeAttribute(H.CLASS, s, H.CLASS); }
        
        String ph=A.asString(attrs.get(A.PHOLDER));
        if(ph!=null) { rw.writeAttribute(H.PHOLDER, ph, null); }
        
        if(A.toBool(attrs.get(A.DISABLED))) { rw.writeAttribute(A.DISABLED, A.DISABLED, null); }
        if(A.toBool(attrs.get(A.READONLY))) { rw.writeAttribute(A.READONLY, A.READONLY, null); }
        
        //Encode attributes (HTML 4 pass-through + DHTML)
        R.encodeHTML4DHTMLAttrs(rw, attrs, A.INPUT_TEXT_ATTRS);
        
        if( (A.asString(attrs.get("autocomplete"))!=null) && (A.asString(attrs.get("autocomplete")).equals("off")) )
           { rw.writeAttribute("autocomplete", "off", null); }
        //Render Value
        String v=R.getValue2Render(context, this);
        rw.writeAttribute(H.VALUE, v, null);
        rw.endElement(H.INPUT);
        if(append) {
            if(app.getClass().getName().endsWith("Button")||(app.getChildCount()>0 && app.getChildren().get(0).getClass().getName().endsWith("Button"))){
                rw.startElement(H.DIV, this);
                rw.writeAttribute(H.CLASS, "input-group-btn", H.CLASS);
                app.encodeAll(context);
                rw.endElement(H.DIV);
            } else { app.encodeAll(context); }
        }
        
        if(append||prepend) {rw.endElement(H.DIV);} //input-group
        rw.endElement(H.DIV); //form-group
        if(span>0) {
            rw.endElement(H.DIV); //span
            //rw.endElement(H.DIV); //row NO
        }
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }


}
