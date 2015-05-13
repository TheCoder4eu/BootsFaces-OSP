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

import static net.bootsfaces.render.H.STYLECLASS;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author thecoder4.eu
 */
public enum RModal {
    modal;
    
    public static final String MODAL="modal";
    public static final String MODALHEAD="modal-header";
    public static final String MODALBODY="modal-body";
    public static final String MODALFOOT="modal-footer";
    
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
         * <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
         *   <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="myModalLabel">Modal header</h3>
                </div>
                <div class="modal-body">
                <p>One fine body…</p>
                </div>
                <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                <button class="btn btn-primary">Save changes</button>
                </div>
             </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
           </div><!-- /.modal -->
         * 
         */
        
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = c.getAttributes();
        
        String title = attrs.get(A.TITLE).toString();
        boolean closable= A.toBool(attrs.get(A.CLOSABLE));
        rw.startElement(H.DIV, c); //modal
        rw.writeAttribute(H.ID,c.getClientId(fc),H.ID);
        
        String styleClasses = MODAL+" fade";
        if (attrs.containsKey(STYLECLASS)) {
            styleClasses = attrs.get(STYLECLASS) + " " + styleClasses;
        }
        rw.writeAttribute(H.CLASS, styleClasses,H.CLASS);
        rw.writeAttribute(H.ROLE, "dialog",null);
        rw.writeAttribute("tabindex", "-1",null);
        rw.writeAttribute(H.ARIALBLBY, c.getClientId(fc)+"_Label",null);
        rw.writeAttribute("aria-hidden", "true",null);
        
        rw.startElement(H.DIV, c); //modal-dialog
        rw.writeAttribute(H.CLASS, MODAL+"-dialog",H.CLASS);
        rw.startElement(H.DIV, c); //modal-content
        rw.writeAttribute(H.CLASS, MODAL+"-content",H.CLASS);
        
        rw.startElement(H.DIV, c); //modal-header
        rw.writeAttribute(H.CLASS, MODALHEAD,H.CLASS);
        
        rw.startElement(H.BUTTON, c);
        rw.writeAttribute(H.TYPE, H.BUTTON,H.TYPE);
        rw.writeAttribute(H.CLASS, "close",H.CLASS);
        rw.writeAttribute(H.DISMISS, MODAL,H.DISMISS);
        rw.write("&".concat("times").concat(";"));
        rw.endElement(H.BUTTON);
        
        if(title!=null) {
            rw.startElement(H.H4, c);
            rw.writeAttribute(H.ID,c.getClientId(fc)+"_Label",H.ID);
            rw.writeText(title, null);
            rw.endElement(H.H4);
            rw.startElement(H.BR, c);
            rw.endElement(H.BR);
        }
        rw.endElement(H.DIV); //modal-header
        
        rw.startElement(H.DIV, c); //modal-body
        rw.writeAttribute(H.CLASS, MODALBODY,H.CLASS);
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
        Map<String, Object> attrs = c.getAttributes();
        
        
        rw.endElement(H.DIV); //modal-body
        
        
        UIComponent foot = c.getFacet("footer");
        if (foot != null) {
            rw.startElement(H.DIV, c); //modal-footer
            rw.writeAttribute(H.CLASS, MODALFOOT,H.CLASS);
            foot.encodeAll(fc);
            
            rw.endElement(H.DIV); //modal-footer
        }
        rw.endElement(H.DIV); //modal-content
        rw.endElement(H.DIV); //modal-dialog
        rw.endElement(H.DIV); //modal
        JQ.initModal(rw, c.getClientId(fc));
    }
}
