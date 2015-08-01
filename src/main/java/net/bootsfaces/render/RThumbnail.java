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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.bootsfaces.render;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author droidcoder
 */
public enum RThumbnail {
    thumbnail,
    caption;
    

    /**
     * Begin Encoding
     * @param c
     * @param fc
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        if (!c.isRendered()) {
            return;
        }
        
        
        //Map<String, Object> attrs = getAttributes();
        
        //String val = A.asString(attrs.get(A.VALUE), getClientId(context));
        ResponseWriter rw = fc.getResponseWriter();
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.ID, c.getClientId(fc), H.ID);
        Tooltip.generateTooltip(fc, c.getAttributes(), rw);
        rw.writeAttribute(H.CLASS, thumbnail, H.CLASS);
        /*UIComponent capt;
        capt = c.getFacet(caption.name());
        if (capt != null ) {
            rw.startElement(H.DIV, c);
            rw.writeAttribute(H.CLASS, caption, H.CLASS);
            capt.encodeAll(fc);
            rw.endElement(H.DIV);
        }*/
    }
    
    /**
     * End encoding
     * @param c
     * @param fc
     * @throws IOException
     */
    public static final void encEnd(UIComponent c, FacesContext fc) throws IOException {
        if (!c.isRendered()) {
            return;
        }
        
        
        //Map<String, Object> attrs = getAttributes();
        
        //String val = A.asString(attrs.get(A.VALUE), getClientId(context));
        ResponseWriter rw = fc.getResponseWriter();
        UIComponent capt;
        capt = c.getFacet(caption.name());
        if (capt != null ) {
            rw.startElement(H.DIV, c);
            rw.writeAttribute(H.CLASS, caption, H.CLASS);
            capt.encodeAll(fc);
            rw.endElement(H.DIV);
        }
        rw.endElement(H.DIV);
        Tooltip.activateTooltips(fc, c.getAttributes(), c);
    }
}
