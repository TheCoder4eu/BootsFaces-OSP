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

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author thecoder4.eu
 */
public enum RnavBar {
    navbar;
    
    public static final String NAVBAR="navbar";
    public static final String NAVBARDEFAULT=NAVBAR+" navbar-default";
    public static final String NAVBARHEAD=NAVBAR+"-header";
    public static final String NAVBARBRAND=NAVBAR+"-brand";
    public static final String NAVBARINNER=NAVBAR+"-inner";
    public static final String NAVBARINVERSE=NAVBAR+" navbar-inverse";
    public static final String NAVBARFIXBOTTOM=" navbar-fixed-bottom";
    public static final String NAVBARFIXTOP=" navbar-fixed-top";
    public static final String NAVBARSTATICTOP=" navbar-static-top";
    
    /**
     * Begin Encoding
     * @param c the UIComponent to encode
     * @param fc the FacesContext
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        if (!c.isRendered()) {
            return;
        }
        /*
         * Fixed:
           <div class="navbar">
            <div class="navbar-inner">
             <div class="container">
             <a class="brand" href="#">Title</a>
         *   <ul class="nav">
         *      <li class="active"><a href="#">Home</a></li>
         *      <li><a href="#">Link</a></li>...
         *   </ul>
             </div>
            </div>
           </div>
           * 
           * 
         * <div class="navbar navbar-default" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Brand</a>
        </div>
         * 
         */
        
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = c.getAttributes();
        
        String fixed = A.asString(attrs.get(A.FIXED));
        boolean sttc = A.toBool(attrs.get(A.STATIC), false);//A.asString(attrs.get(A.STATIC));
        boolean inverse = A.toBool(attrs.get(A.INVERSE));
        boolean fluid = A.toBool(attrs.get("fluid"),false);
        String ns=null;
        
        if (inverse) { ns = NAVBARINVERSE; } else { ns = NAVBARDEFAULT; }
        if (fixed != null) {
            if (fixed.equals(A.TOP)) { ns += NAVBARFIXTOP; }
            if (fixed.equals(A.BOTTOM)) { ns += NAVBARFIXBOTTOM; }
        }
        if (sttc) { ns += NAVBARSTATICTOP; }

        /* 
         * The <nav> tag defines a set of navigation links. The <nav> element is intended only for major block of navigation links.
         * The <nav> tag is supported in Internet Explorer 9, Firefox, Opera, Chrome, and Safari.
         * See http://www.w3schools.com/tags/tag_nav.asp
         * Note: Internet Explorer 8 and earlier versions, do not support the <nav> tag.
         * When IE8 will be dropped there will be HTML5 <nav> tag instead of <div> */
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.ID,c.getClientId(fc),H.ID);
        Tooltip.generateTooltip(fc, attrs, rw);
        rw.writeAttribute(H.CLASS, ns,H.CLASS);
        rw.writeAttribute(H.ROLE, "navigation", null);
        
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.CLASS, fluid ? "container-fluid" : "container",H.CLASS); //x Layout Centrato. TODO : layout full width
        
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.CLASS, NAVBARHEAD,H.CLASS); //navbar-header
        rw.startElement(H.BUTTON, c);
        rw.writeAttribute(A.TYPE, A.BUTTON, A.TYPE);
        rw.writeAttribute(H.CLASS, NAVBAR+"-toggle",H.CLASS); //navbar-toggle
        rw.writeAttribute(A.DATA_TOGGLE, "collapse", A.TYPE);
        rw.writeAttribute(A.DATA_TARGET, ".navbar-ex1-collapse", A.TYPE);
        
        rw.startElement(H.SPAN, c);rw.writeAttribute(H.CLASS, "sr-only",H.CLASS);rw.writeText("Toggle navigation", null);rw.endElement(H.SPAN);
        rw.startElement(H.SPAN, c);rw.writeAttribute(H.CLASS, "icon-bar",H.CLASS);rw.endElement(H.SPAN);
        rw.startElement(H.SPAN, c);rw.writeAttribute(H.CLASS, "icon-bar",H.CLASS);rw.endElement(H.SPAN);
        rw.startElement(H.SPAN, c);rw.writeAttribute(H.CLASS, "icon-bar",H.CLASS);rw.endElement(H.SPAN);
        rw.endElement(H.BUTTON);
        String brand = A.asString(attrs.get(A.BRAND));
        String brandImg = A.asString(attrs.get(A.BRAND_IMG));
        //<a class="navbar-brand" href="#">Brand</a>
        if(brand!=null || brandImg!=null) {
        	rw.startElement(H.A, c);
        	rw.writeAttribute(H.CLASS, NAVBARBRAND,H.CLASS); //navbar-brand
        	String href = A.asString(attrs.get(A.BRAND_HREF));
        	if(href==null) { rw.writeAttribute(H.HREF, H.HASH,H.HREF); }
        	else           { rw.writeAttribute(H.HREF, href,H.HREF); }
                rw.startElement(H.SPAN, c);
        	if(brandImg!=null) {
        		String altText = A.asString(attrs.get(A.ALT));
        		if(altText==null) altText = "Brand"; // default
        		rw.startElement(H.IMG , c);
        		rw.writeAttribute(H.ALT, altText, H.ALT);
        		rw.writeAttribute(H.SRC, brandImg, H.SRC);
        		rw.endElement(H.IMG);
        	}
        	if(brand!=null) rw.writeText(brand, null);
                rw.endElement(H.SPAN);
        	rw.endElement(H.A);
        }
        rw.endElement(H.DIV); //navbar-header


        /*
         * <!-- Collect the nav links, forms, and other content for toggling -->
         * <div class="collapse navbar-collapse navbar-ex1-collapse">
         */
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.CLASS, "collapse navbar-collapse navbar-ex1-collapse",H.CLASS);
        
        /* Brand now goes in navbar-header div */
    }
    
    /**
    * End encoding
    * @param c the UIComponent to encode
    * @param fc the FacesContext
    * @throws IOException
    */
    public static final void encEnd(UIComponent c, FacesContext fc) throws IOException {
        if (!c.isRendered()) {
            return;
        }
        ResponseWriter rw = fc.getResponseWriter();
        rw.endElement(H.DIV); //collapse
        rw.endElement(H.DIV); //container
        rw.endElement(H.DIV); //navbar
        Tooltip.activateTooltips(fc, c.getAttributes(), c);
    }
}
