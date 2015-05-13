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

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.RModal;

@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css", target="head"),
        @ResourceDependency(library="bsf", name="css/modals.css", target="head"),
        @ResourceDependency(library="bsf", name="js/modal.js", target="body")
})
@FacesComponent(C.MODAL_COMPONENT_TYPE)
public class Modal extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.MODAL_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    public static final String MODAL="modal";
    public static final String MODALHEAD="modal-header";
    public static final String MODALBODY="modal-body";
    public static final String MODALFOOT="modal-footer";
    
    public Modal() {
        setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");

    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
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
        
//        ResponseWriter rw = fc.getResponseWriter();
//        
//        Map<String, Object> attrs = getAttributes();
//        
//        String title = attrs.get(A.TITLE).toString();
//        boolean closable= A.toBool(attrs.get(A.CLOSABLE));
//        rw.startElement(H.DIV, this); //modal
//        rw.writeAttribute(H.ID,getClientId(fc),H.ID);
//        rw.writeAttribute(H.CLASS, MODAL+" fade",H.CLASS);
//        rw.writeAttribute(H.ROLE, "dialog",null);
//        rw.writeAttribute("tabindex", "-1",null);
//        rw.writeAttribute(H.ARIALBLBY, getClientId(fc)+"_Label",null);
//        rw.writeAttribute("aria-hidden", "true",null);
//        
//        rw.startElement(H.DIV, this); //modal-dialog
//        rw.writeAttribute(H.CLASS, MODAL+"-dialog",H.CLASS);
//        rw.startElement(H.DIV, this); //modal-content
//        rw.writeAttribute(H.CLASS, MODAL+"-content",H.CLASS);
//        
//        rw.startElement(H.DIV, this); //modal-header
//        rw.writeAttribute(H.CLASS, MODALHEAD,H.CLASS);
//        
//        rw.startElement(H.BUTTON, this);
//        rw.writeAttribute(H.TYPE, H.BUTTON,H.TYPE);
//        rw.writeAttribute(H.CLASS, "close",H.CLASS);
//        rw.writeAttribute(H.DISMISS, MODAL,H.DISMISS);
//        rw.write("&".concat("times").concat(";"));
//        rw.endElement(H.BUTTON);
//        
//        if(title!=null) {
//            rw.startElement(H.H4, this);
//            rw.writeAttribute(H.ID,getClientId(fc)+"_Label",H.ID);
//            rw.writeText(title, null);
//            rw.endElement(H.H4);
//            rw.startElement(H.BR, this);
//            rw.endElement(H.BR);
//        }
//        rw.endElement(H.DIV); //modal-header
//        
//        rw.startElement(H.DIV, this); //modal-body
//        rw.writeAttribute(H.CLASS, MODALBODY,H.CLASS);
        RModal.encBegin(this, fc);
    }

    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        RModal.encEnd(this, fc);
//        ResponseWriter rw = fc.getResponseWriter();
//        Map<String, Object> attrs = getAttributes();
//        
//        
//        rw.endElement(H.DIV); //modal-body
//        
//        
//        UIComponent foot = getFacet("footer");
//        if (foot != null) {
//            rw.startElement(H.DIV, this); //modal-footer
//            rw.writeAttribute(H.CLASS, MODALFOOT,H.CLASS);
//            foot.encodeAll(fc);
//            
//            rw.endElement(H.DIV); //modal-footer
//        }
//        rw.endElement(H.DIV); //modal-content
//        rw.endElement(H.DIV); //modal-dialog
//        rw.endElement(H.DIV); //modal
//        JQ.initModal(rw, getClientId(fc));
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
