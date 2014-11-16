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
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author droidcoder
 */
public enum RPanel {
    panel,
    look,
    title,
    heading,
    body,
    footer,
    titleClass;
    
    private static final String PP=panel+" "+panel+"-"; //"panel panel-"
    private static final String PPD=PP+"default"; //"panel panel-default"
    private static final String PH=panel+"-"+heading; //"panel-heading"
    private static final String PB=panel+"-"+body; //"panel-body"
    private static final String PF=panel+"-"+footer; //"panel-footer"

    /**
     * Begin Encoding
     * @param c
     * @param fc
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        
        /*
         * <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Panel title</h3>
            </div>
            <div class="panel-body">
              Panel content
            </div>
            <div class="panel-footer">Panel footer</div>
           </div>
         */
        ResponseWriter rw = fc.getResponseWriter();
        Map<String, Object> attrs = c.getAttributes();

        String Look = A.asString(attrs,look);
        String Title = A.asString(attrs,title);
        String TitleClass = A.asString(attrs,titleClass);
        
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.ID, c.getClientId(fc), H.ID);

        if (Look != null) {
            rw.writeAttribute(H.CLASS, PP + Look, H.CLASS); //"panel panel-" + Look
        } else {
            rw.writeAttribute(H.CLASS, PPD, H.CLASS); //"panel panel-default"
        }
        
        UIComponent head = c.getFacet(heading.name());
        if (head != null || Title != null) {
            rw.startElement(H.DIV, c);
            rw.writeAttribute(H.CLASS, PH, H.CLASS); //"panel-heading"
            if (Title != null) {
                rw.startElement(H.H4, c);
                if (TitleClass != null){
                	rw.writeAttribute(H.CLASS, TitleClass, H.CLASS);
                } else {
                	rw.writeAttribute(H.CLASS, "panel-title", H.CLASS);
                }
                rw.writeText(Title, null);
                rw.endElement(H.H4);
            } else {
                head.encodeAll(fc);
            }
            rw.endElement(H.DIV);
        }

        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.CLASS, PB, H.CLASS); //"panel-body"
    }
    
    /**
     * End encoding
     * @param c
     * @param fc
     * @throws IOException
     */
    public static final void encEnd(UIComponent c, FacesContext fc) throws IOException {
        ResponseWriter rw = fc.getResponseWriter();
        rw.endElement(H.DIV); //panel-body
        UIComponent foot = c.getFacet(footer.name());
        if (foot != null) {
            rw.startElement(H.DIV, c); //modal-footer
            rw.writeAttribute(H.CLASS, PF, H.CLASS); //"panel-footer"
            foot.encodeAll(fc);
            
            rw.endElement(H.DIV); //panel-footer
        }
        
        rw.endElement(H.DIV);
    }
}
