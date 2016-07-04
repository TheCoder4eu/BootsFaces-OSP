/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
 *  
 *  This file is part of BootsFaces.
 *  
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
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
        rw.startElement("div", c);
        rw.writeAttribute("id", c.getClientId(fc), "id");
        Tooltip.generateTooltip(fc, c, rw);
        rw.writeAttribute("class", thumbnail, "class");
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
            rw.startElement("div", c);
            rw.writeAttribute("class", caption, "class");
            capt.encodeAll(fc);
            rw.endElement("div");
        }
        rw.endElement("div");
        Tooltip.activateTooltips(fc, c);
    }
}
