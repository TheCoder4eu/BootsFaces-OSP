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
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.JQ;
import net.bootsfaces.render.R;

/**
 *
 * @author thecoder4.eu
 */

@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css", target="head"),
        @ResourceDependency(library="bsf", name="css/badges.css", target="head"),
        @ResourceDependency(library="bsf", name="css/jq.ui.core.css", target="head"),
        @ResourceDependency(library="bsf", name="css/jq.ui.theme.css", target="head"),
        @ResourceDependency(library="bsf", name="css/jq.ui.slider.css", target="head"),
        @ResourceDependency(library="bsf", name="css/bsf.css", target="head"),
        @ResourceDependency(library="bsf", name="jq/ui/core.js", target="body"),
        @ResourceDependency(library="bsf", name="jq/ui/widget.js", target="body"),
        @ResourceDependency(library="bsf", name="jq/ui/mouse.js", target="body"),
        @ResourceDependency(library="bsf", name="jq/ui/slider.js", target="body")
})
@FacesComponent(C.SLIDER_COMPONENT_TYPE)
public class Slider extends HtmlInputText {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.SLIDER_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    private int min;
    private int max;
    
    private static final String SHANDLE="ui-slider-handle-";
    public static final String SLIDER="slider";
    public static final String SLIDERV=SLIDER+C.HYP+C.V;

    public Slider() {
        setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
    }
    
    /** Method added to prevent AngularFaces from setting the type */
    public String getType() {
        String mode=A.asString(getAttributes().get(A.MODE),"badge");
        return mode.equals("edit") ? H.TEXT : H.HIDDEN;
    }
    
    /** Method added to prevent AngularFaces from setting the type 
     * @param type this parameter is ignored
     */
    public void setType(String type) {
    	// ignore the type - it's defined by the mode attribute
    	
    }
    
    @Override
    public void decode(FacesContext context) {
        String subVal = context.getExternalContext().getRequestParameterMap().get(getClientId(context));

        if(subVal != null) {
            this.setSubmittedValue(subVal); this.setValid(true);
        }
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
         * <div id="slider"></div>
         */
        
        ResponseWriter rw = context.getResponseWriter();
        encodeHTML(context, rw);
        
    }
    
    private void encodeHTML(FacesContext context, ResponseWriter rw) throws IOException {
        Map<String, Object> attrs = getAttributes();
        String clientId = getClientId(context);
        
        String mode=A.asString(attrs.get(A.MODE),"badge");
        String label=A.asString(attrs.get(A.LABEL));
        
        min=A.toInt(attrs.get(JQ.MIN), 0);
        max=A.toInt(attrs.get(JQ.MAX), 100);
        Object v=getSubmittedValue();
        if(v==null) { v=this.getValue();}
        if(v==null) { v=A.asString(attrs.get(A.VALUE));this.setValue(v);}
        if(v==null) { v=max/2;this.setValue(v);}
        int val=A.toInt(v);
        if(val>max) { val=max; }
        if(val<min) { val=min; }
        String o;
        if(attrs.get(JQ.ORIENTATION)!=null) { o=A.asString(attrs.get(JQ.ORIENTATION)); }
        else { o=C.H; }
        boolean vo=o.startsWith(C.V); boolean bottom=o.endsWith(C.BOTTOM);
        
        rw.startElement(H.DIV, null);//form-group
        rw.writeAttribute(H.CLASS, "form-group",H.CLASS);
        R.encodeRow(rw, null, null,(vo ? SLIDERV : SLIDER)); //rw.write("<-- Slider START -->\n");//Slider Widget Row
        //-------------------------------------------------------------->
        //<<-- Vertical -->>
        if (vo) {
            if (label != null && !bottom) {
                R.encodeRow(rw, null, null, null);
                encodeVLabel(rw, label);
                rw.endElement(H.DIV);/* Row */
            }
            R.encodeRow(rw, null, null, null); 
            if (bottom) {
                encodeSliderDiv(rw, vo, clientId);
                rw.endElement(H.DIV);/* Row */
                R.encodeRow(rw, null, null, null);
            }
            encodeInput(rw, mode, context, val, clientId, vo, min, max);
            if (!bottom) {
                rw.endElement(H.DIV); /* Row */
                
                R.encodeRow(rw, null, null, null);
                encodeSliderDiv(rw, vo, clientId);
            }
            rw.endElement(H.DIV); /* Row */
            if (label != null && bottom) {
                R.encodeRow(rw, null, null, null);
                encodeVLabel(rw, label);
                rw.endElement(H.DIV); /* Row */
            }

        } else {
        //<<-- Horizontal -->>
        
            if (label != null) {
                R.encodeRow(rw, null, null, null);
                
                R.encodeColumn(rw, null, 6, 6, 6, 6, 0, 0, 0, 0, null, null);
                rw.startElement(H.LABEL, this);
                rw.writeAttribute(A.FOR, clientId, null);
                rw.write(label);
                rw.endElement(H.LABEL); //Label
                
                rw.endElement(H.DIV);//Column
                
                rw.endElement(H.DIV);/* Row */
            }
            R.encodeRow(rw, null, null, null); 
            
            encodeInput(rw, mode, context, val, clientId, vo, min, max);
            
            encodeSliderDiv(rw, vo, clientId); 
            rw.endElement(H.DIV);/* Row */

        } //if vo
        
         //<<---------------------------------------
         rw.endElement(H.DIV); //rw.write("<!-- Slider Widget Row -->\n");//Slider Widget Row
         
        rw.endElement(H.DIV); //rw.write("<!-- form-group -->\n");//form-group
        
        encodeJS(rw, clientId);
    }

    private void encodeVLabel(ResponseWriter rw, String label) throws IOException {
        R.encodeColumn(rw, null, 12, 12, 12, 12, 0, 0, 0, 0, null, null);
        rw.startElement(H.P, this);
        rw.write(label);
        rw.endElement(H.P); //Label
        rw.endElement(H.DIV); //Column
    }

    private void encodeInput(ResponseWriter rw, String mode, FacesContext context, int val, String clientId, boolean vo, int min, int max) throws IOException {
        int cols=( vo ? 12 : 1);
        if(!mode.equals("basic")) {
        /* int span, int offset, int cxs, int csm, int clg, int oxs, int osm, int olg */
        R.encodeColumn(rw, null, cols, cols, cols, cols, 0, 0, 0, 0, null, null);
        if(mode.equals("badge")) { 
        	R.encodeBadge(context, this, "_badge", Integer.toString(val) ); }
        }
        // remove wrong type information that may have been added by AngularFaces
        if (getPassThroughAttributes().containsKey("type")) {
        	getPassThroughAttributes().remove("type");
        }
        //Input
        rw.startElement(H.INPUT, this);
        rw.writeAttribute(H.ID, clientId, null);
        rw.writeAttribute(H.NAME, clientId, null);
        rw.writeAttribute(H.TYPE, (mode.equals("edit") ? H.TEXT : H.HIDDEN), null);
        rw.writeAttribute(H.SIZE, String.valueOf(max).length()-1, null);
        rw.writeAttribute(H.MIN, min, null);
        rw.writeAttribute(H.MAX, max, null);
        rw.writeAttribute(H.MAXLEN, String.valueOf(max).length(), null);
        
        rw.writeAttribute(H.CLASS, "form-control input-sm"+( vo ? " text-center" : C.EMPTY),H.CLASS);
        
        rw.writeAttribute(H.VALUE, val, null);
        
        //if (rdonly) { rw.writeAttribute(H.READONLY, H.READONLY, null); }
        rw.endElement(H.INPUT);
         
        if(!mode.equals("basic")) { rw.endElement(H.DIV); }//Column
    }

    private void encodeSliderDiv(ResponseWriter rw, boolean vo, String clientId) throws IOException {
        /* int span, int offset, int cxs, int csm, int clg, int oxs, int osm, int olg */
        R.encodeColumn(rw, null, (vo ? 12 : 4), (vo ? 12 : 4), (vo ? 12 : 4), (vo ? 12 : 4), 0, 0, 0, 0, null, null);
        //Slider <div>
        rw.startElement(H.DIV, null);
        rw.writeAttribute(H.ID, clientId+C.USCORE+JQ.SLIDER, null);//concat controproducente
        rw.endElement(H.DIV);
        rw.endElement(H.DIV); //Column
    }
    
    private void encodeJS(ResponseWriter rw, String cId) throws IOException {
        Map<String, Object> attrs = getAttributes();
        StringBuilder sb = new StringBuilder(100);
        sb.append(A.VALUE).append(C.COLON).append(this.getValue()).append(C.COMMA);
        if(attrs.get(JQ.MAX)!=null) {
            sb.append(JQ.MAX).append(C.COLON).append(A.toInt(attrs.get(JQ.MAX))).append(C.COMMA);
        }
        if(attrs.get(JQ.MIN)!=null) {
            sb.append(JQ.MIN).append(C.COLON).append(A.toInt(attrs.get(JQ.MIN))).append(C.COMMA);
        }
        if(attrs.get(JQ.ORIENTATION)!=null) {
            String o=A.asString(attrs.get(JQ.ORIENTATION));
            if(o.endsWith(C.BOTTOM)) { o=C.V;}
            sb.append(JQ.ORIENTATION).append(C.COLON).append(C.QUOTE.concat(o).concat(C.QUOTE)).append(C.COMMA);
        }
        if(attrs.get(JQ.STEP)!=null) {
            sb.append(JQ.STEP).append(C.COLON).append(A.toInt(attrs.get(JQ.STEP))).append(C.COMMA);
        }
        sb.append(JQ.RANGE).append(C.COLON).append("\"min\"").append(C.COMMA);
        
        String hsize=A.asString(attrs.get(A.HANDLE_SIZE));
        String hshape=A.asString(attrs.get(A.HANDLE_SHAPE));
        boolean hround=( (hshape!=null) && (hshape.equals("round")) );
        
        JQ.simpleSlider(rw,cId, sb.toString(),A.asString(attrs.get(A.MODE),"badge").equals("badge"),hsize,hround);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
    }
    
    @Override
    public void encodeChildren(FacesContext context) throws IOException {
    }
    
}
