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

package net.bootsfaces.render;

import net.bootsfaces.C;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import net.bootsfaces.component.GenContainerDiv;

/**
 * Rendering functions for the core or common to more than one component
 * 
 * @author thecoder4.eu
 */
public final class R {
    public static final String BADGE="badge";
    public static final String LABEL="label";
    
    public static final String INVERSE="inverse";
    public static final String WHITE="white";
    public static final String DFLT="-default";
    
    public static final String ADDON="input-group-addon";
    public static final String CARET="caret";
    public static final String DROPDOWN="dropdown";
    // Removing unneded icon-white used in Bootstrap 2
    // TBS3 uses font color of parent element
    //!//public static final String ICON="icon";
    //!//public static final String ICONWH=ICON.concat(C.HYP).concat(WHITE);
    //Glyph icons / Font Awesome icons
    public static final String GLYPHICON="glyphicon glyphicon-";
    public static final String FAICON="fa fa-";
    
    public static final String ROW="row";
    // span => col-md-* as of TBS3
    public static final String COLMD="col-md-"; //Default as of TBS3
    // span => col-md-offset-* as of TBS3
    public static final String OFFSET="col-md-offset-"; //Default as of TBS3
    //public static final String ="";
    

    /**
     * Renders an Icon
     * @param rw ResponseWriter
     * @param c  UIComponent
     * @param icon Icon Name
     * @param fa Icon set: false for Bootstrap Glyphicons, true for Font Awesome
     * @param size Icon Size lg, 2x, 3x, 4x, 5x
     * @param rotate Can be L,R
     * @param flip Can be H,V
     * @param spin true or false
     * @param addon If true it is an addon for inputs.
     * @throws IOException 
     */
    public static final void encodeIcon(ResponseWriter rw, UIComponent c, String icon, boolean fa, String size, String rotate, String flip, boolean spin, boolean addon) throws IOException {
        /*
        Markup:
        <span><i class="icon-search"></i></span>
        */
        rw.startElement(H.SPAN, c);
        if(addon) {
            rw.writeAttribute(H.ID, c.getClientId()+C.USCORE+ADDON, null);
            rw.writeAttribute(H.CLASS, ADDON, H.CLASS);
        }
        rw.startElement(H.I, c);
        rw.writeAttribute(H.ID, c.getClientId()+"_icon", null);
		Tooltip.generateTooltip(FacesContext.getCurrentInstance(), c.getAttributes(), rw);

        
        StringBuilder sb = new StringBuilder(100); //optimize int
        //if(set!=null && set.equalsIgnoreCase("FA")) {
        if(fa) {
            sb.append(FAICON+icon);
        } else {
            sb.append(GLYPHICON+icon);
        }
        if(size!=null) { sb.append(" fa-"+size); }
        if(rotate!=null) {
            if(rotate.equalsIgnoreCase("R")) { sb.append(" fa-rotate-90"); }
            if(rotate.equalsIgnoreCase("L")) { sb.append(" fa-rotate-270"); }
        }
        if(flip!=null) {
            if(flip.equalsIgnoreCase("H")) { sb.append(" fa-flip-horizontal"); }
            if(flip.equalsIgnoreCase("V")) { sb.append(" fa-flip-vertical"); }
        }
        if(spin) { sb.append(" fa-spin"); }
        rw.writeAttribute(H.CLASS, sb.toString(), H.CLASS);
        //!//rw.writeAttribute(H.CLASS, GLYPHICON+icon, H.CLASS);
        rw.endElement(H.I);
        
        rw.endElement(H.SPAN);
    }
    /*public static final void encodeIcon(ResponseWriter rw, UIComponent c, String icon, boolean white) throws IOException {
        rw.startElement(H.I, c);
        rw.writeAttribute(H.ID, c.getClientId()+C.USCORE+ICON, null);
        rw.writeAttribute(H.CLASS, GLYPHICON+icon+(white ? C.SP+ICONWH : C.EMPTY), H.CLASS);
        rw.endElement(H.I);
    }*/
    
    /**
     * Renders an Icon - simple version without options
     * @param rw ResponseWriter
     * @param c  UIComponent
     * @param icon Icon Name
     * @param fa Icon set: false for Bootstrap Glyphicons, true for Font Awesome
     * @throws IOException 
     */
    public static final void encodeIcon(ResponseWriter rw, UIComponent c, String icon, boolean fa) throws IOException {
        encodeIcon(rw, c, icon, fa, null, null, null, false, false);
    }
    /**
     * Renders an addon Icon
     * Markup:
     * <span class="add-on"><i class="icon-envelope"></i></span>
     * 
     * @param rw ResponseWriter
     * @param c  UIComponent
     * @param icon Icon Name
     * @param fa Icon set: false for Bootstrap Glyphicons, true for Font Awesome
     * @throws java.io.IOException
     */
    public static final void addonIcon(ResponseWriter rw, UIComponent c, String icon, boolean fa) throws IOException {
        //rw.startElement(H.SPAN, c);
        //rw.writeAttribute(H.ID, c.getClientId()+C.USCORE+ADDON, null);
        //rw.writeAttribute(H.CLASS, ADDON, H.CLASS);
        encodeIcon(rw, c, icon, fa, null, null, null, false, true);
        //rw.endElement(H.SPAN);
    }
    
    /**
     * Encodes a Row
     * @param rw
     * @param c
     * @param style
     * @param sclass
     * @throws IOException 
     */
    public static final void encodeRow(ResponseWriter rw, UIComponent c, String style, String sclass) throws IOException {
        rw.startElement(H.DIV, c);
        if (null != c) {
        	Tooltip.generateTooltip(FacesContext.getCurrentInstance(), c.getAttributes(), rw);
        }
        String s = ROW;
        if(sclass!=null) { s+=C.SP+sclass; }        
        if(c!=null) { rw.writeAttribute(H.ID,c.getClientId(),H.ID); }
        if(style!=null) { rw.writeAttribute(H.STYLE,style,H.STYLE); }
        rw.writeAttribute(H.CLASS, s, H.CLASS);
        if (null != c) {
        	Tooltip.activateTooltips(FacesContext.getCurrentInstance(), c.getAttributes(), c);
        }
    }
    
    /**
     * Encodes a Column
     * @param rw
     * @param c
     * @param span
     * @param cxs
     * @param csm
     * @param clg
     * @param offset
     * @param oxs
     * @param osm
     * @param olg
     * @param style
     * @param sclass
     * @throws IOException 
     */
    public static final void encodeColumn(ResponseWriter rw, UIComponent c,
            int span, int cxs, int csm, int clg, int offset, int oxs, int osm, int olg, String style, String sclass) throws IOException {
        
        rw.startElement(H.DIV, c);
        Map<String, Object> componentAttrs = new HashMap<String, Object>();
        
        if(c != null) { 
            rw.writeAttribute(H.ID,c.getClientId(),H.ID); 
            Tooltip.generateTooltip(FacesContext.getCurrentInstance(),c.getAttributes(), rw);
            componentAttrs = c.getAttributes();
        }
        
        
        StringBuilder sb= new StringBuilder();
        if(span>0 || offset>0) {
            if(span>0) { sb.append(COLMD).append(span); }
            if(offset>0) {
                if(span>0) { sb.append(C.SP); }
                sb.append(OFFSET+offset);
            }
        }
        
        if(cxs>0) {sb.append(" col-xs-").append(cxs);}
        if ( componentAttrs.get("col-xs") != null && cxs==0){
            sb.append(" hidden-xs");
        }
        
        if(csm>0) {sb.append(" col-sm-").append(csm);}
        if ( componentAttrs.get("col-sm") != null && csm==0){
            sb.append(" hidden-sm");
        }
        
        
        if(clg>0) {sb.append(" col-lg-").append(clg);}
        if ( componentAttrs.get("col-lg") != null && clg==0){
            sb.append(" hidden-lg");
        }
        
        if(oxs>0) {sb.append(" col-xs-offset-").append(oxs);}
        if(osm>0) {sb.append(" col-sm-offset-").append(osm);}
        if(olg>0) {sb.append(" col-lg-offset-").append(olg);}
        
        if(sclass!=null) {sb.append(C.SP).append(sclass); }
        rw.writeAttribute(H.CLASS, sb.toString().trim(), H.CLASS);
        if(style!=null) { rw.writeAttribute(H.STYLE, style, H.STYLE); }
        
        if (null != c) {
        	Tooltip.activateTooltips(FacesContext.getCurrentInstance(), c.getAttributes(), c);
        }
    }
    
    /**
     * 
     * @param c
     * @param fc
     * @throws IOException 
     */
    public static void genDivContainer(GenContainerDiv c, FacesContext fc) throws IOException {
        /*
        * <div class="?">
        * ...
        * </div>
        */
        
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = c.getAttributes();
        
        String pull = A.asString(attrs.get(A.PULL));
        
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.ID,c.getClientId(fc),H.ID);
		Tooltip.generateTooltip(fc, attrs, rw);
        if(pull!=null && (pull.equals(A.RIGHT) || pull.equals(A.LEFT)) ) {
            rw.writeAttribute(H.CLASS, c.getContainerStyles().concat(C.SP).concat(A.PULL).concat(C.HYP).concat(pull),H.CLASS);
        } else {
            rw.writeAttribute(H.CLASS, c.getContainerStyles(),H.CLASS);
        }
    }
    
    /**
     * Encodes a Label
     * @param fc
     * @param c
     * @param severity
     * @param text
     * @throws IOException 
     */
    public static void encodeLabel(FacesContext fc, UIComponent c, String severity, String text) throws IOException {
        ResponseWriter rw = fc.getResponseWriter();
        
        rw.startElement(H.SPAN, c);
        rw.writeAttribute(H.ID,c.getClientId(),H.ID);
        Tooltip.generateTooltip(fc, c.getAttributes(), rw);
        String sclass=LABEL+C.SP+LABEL;
        if(severity!=null) { sclass += C.HYP+severity; } else { sclass += DFLT; }
        rw.writeAttribute(H.CLASS, sclass,H.CLASS);
        rw.writeText(text, null);
        rw.endElement(H.SPAN);
        Tooltip.activateTooltips(fc, c.getAttributes(), c);
    }
    
    /**
     * Encodes a Badge
     * @param fc
     * @param c
     * @param suffix
     * @param val
     * @throws IOException 
     */
    public static void encodeBadge(FacesContext fc, UIComponent c, String suffix, String val) throws IOException {
        ResponseWriter rw = fc.getResponseWriter();
        
        rw.startElement(H.SPAN, c);
        String cid = c.getClientId(fc);
        if(suffix!=null) { cid=cid.concat(suffix); }
        rw.writeAttribute(H.ID, cid,H.ID);
		Tooltip.generateTooltip(fc, c.getAttributes(), rw);
        rw.writeAttribute(H.CLASS, BADGE,H.CLASS);
        rw.writeText(val, null);
        rw.endElement(H.SPAN);
		Tooltip.activateTooltips(fc, c.getAttributes(), c);
    }
    
    /**
     * Adds a CSS class to a component within a facet.
     * @param f the facet
     * @param cname the class name of the component to be manipulated.
     * @param aclass the CSS class to be added 
     */
    public static void addClass2FacetComponent(UIComponent f, String cname, String aclass) {
        // If the facet contains only one component, getChildCount()=0 and the Facet is the UIComponent
        if (f.getClass().getName().endsWith(cname)) {
            addClass2Component(f, aclass);
        } else {
            if (f.getChildCount() > 0) {
                for (UIComponent c : f.getChildren()) {
                    if (c.getClass().getName().endsWith(cname)) { addClass2Component(c, aclass); }
                }
            }
        }
    }
    
    /**
     * Adds a CSS class to a component in the view tree.
     * The class is appended to the styleClass value.
     * @param c the component
     * @param aclass the CSS class to be added
     */
    protected static void addClass2Component(UIComponent c, String aclass) {
        Map<String, Object> a = c.getAttributes();
        if(a.containsKey(H.STYLECLASS)) {a.put(H.STYLECLASS, a.get(H.STYLECLASS)+C.SP+aclass); }
        else                            {a.put(H.STYLECLASS, aclass); }
    }
    /**
     * Sets an attribute value to a component within a facet.
     * @param f the facet
     * @param cname the class name of the component to be manipulated.
     * @param attr
     * @param v 
     */
    public static void setFacetComponentAttribute(UIComponent f, String cname, String attr, String v) {
        // If the facet contains only one component, getChildCount()=0 and the Facet is the UIComponent
        if (f.getClass().getName().endsWith(cname)) {
            setComponentAttribute(f, attr, v);
        } else {
            if (f.getChildCount() > 0) {
                for (UIComponent c : f.getChildren()) {
                    if (c.getClass().getName().endsWith(cname)) { setComponentAttribute(f, attr, v); }
                }
            }
        }
    }
    /**
     * Sets a component attribute value.
     * @param c the component
     * @param attr the attribute name
     * @param v the value to set
     */
    protected static void setComponentAttribute(UIComponent c, String attr, String v) {
        if(attr!=null) { c.getAttributes().put(attr, v); }
    }
    /**
     * Encodes component attributes (HTML 4 + DHTML)
     * TODO: replace this method with CoreRenderer.renderPassThruAttributes()
     * @param rw ResponseWriter instance
     * @param attrs
     * @param alist
     * @throws IOException 
     */
    public static void encodeHTML4DHTMLAttrs(ResponseWriter rw, Map<String, Object> attrs, String [] alist) throws IOException {
        //Encode attributes (HTML 4 + DHTML)
        
        for (String a : alist) {
            if (attrs.get(a)!=null) {
                String val = A.asString(attrs.get(a));
                if(val!=null && val.length()>0) { rw.writeAttribute(a, val, a); }
            }
        }
    }
    
    /**
     * Finds the Form Id of a component inside a form.
     * @param fc FacesContext instance
     * @param c UIComponent instance
     * @return 
     */
    public static String findComponentFormId(FacesContext fc, UIComponent c) {
        UIComponent parent = c.getParent();

        while (parent != null) {
            if (parent instanceof UIForm) {
                return parent.getClientId(fc);
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    /**
     * Algorithm works as follows;
     * - If it's an input component, submitted value is checked first since it'd be the value to be used in case validation errors
     * terminates jsf lifecycle
     * - Finally the value of the component is retrieved from backing bean and if there's a converter, converted value is returned
     *
     * @param fc			FacesContext instance
     * @param c 			UIComponent instance whose value will be returned
     * @return					End text
     */
    public static String getValue2Render(FacesContext fc, UIComponent c) {
            if(c instanceof ValueHolder) {

                    if(c instanceof EditableValueHolder) {
                            Object sv = ((EditableValueHolder) c).getSubmittedValue();
                            if(sv != null) {
                                    return sv.toString();
                            }
                    }

                    ValueHolder vh = (ValueHolder) c;
                    Object val = vh.getValue();

        //format the value as string
        if(val != null) {
            Converter converter = getConverter(fc, vh);

            if(converter != null)
                return converter.getAsString(fc, c, val);
            else
                return val.toString();    //Use toString as a fallback if there is no explicit or implicit converter

        }
        else {
            //component is a value holder but has no value
            return null;
        }
            }

    //component it not a value holder
    return null;
    }
    
    /**
    * Finds the appropriate converter for a given value holder
    * 
    * @param fc	FacesContext instance
    * @param vh	ValueHolder instance to look converter for
    * @return		Converter
    */
    public static Converter getConverter(FacesContext fc, ValueHolder vh) {
        //explicit converter
        Converter converter = vh.getConverter();
                
        //try to find implicit converter
        if(converter == null) {
            ValueExpression expr = ((UIComponent) vh).getValueExpression("value");
            if(expr != null) {
                Class<?> valueType = expr.getType(fc.getELContext());
                if(valueType != null) {
                    converter = fc.getApplication().createConverter(valueType);
                }
            }
        }
        
        return converter;
    }
    
    /**
     * Renders the Childrens of a Component
     * @param fc
     * @param component
     * @throws IOException 
     */
    public static void renderChildren(FacesContext fc, UIComponent component) throws IOException {
        for (Iterator<UIComponent> iterator = component.getChildren().iterator(); iterator.hasNext();) {
            UIComponent child = (UIComponent) iterator.next();
            renderChild(fc, child);
        }
    }
    /**
     * Renders the Child of a Component
     * @param fc
     * @param child
     * @throws IOException 
     */
    public static void renderChild(FacesContext fc, UIComponent child) throws IOException {
        if (!child.isRendered()) {
            return;
        }

        child.encodeBegin(fc);

        if (child.getRendersChildren()) {
            child.encodeChildren(fc);
        } else {
            renderChildren(fc, child);
        }
        child.encodeEnd(fc);
    }
    
    // Suppress default constructor for noninstantiability
    private R() {
        throw new AssertionError();
    }
}
