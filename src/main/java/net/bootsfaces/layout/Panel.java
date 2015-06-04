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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.layout;

import java.io.IOException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import net.bootsfaces.C;
import net.bootsfaces.render.RPanel;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4eu
 */

@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css"),
        @ResourceDependency(library="bsf", name="css/panels.css"),
        @ResourceDependency(library="bsf", name="css/bsf.css"),
        @ResourceDependency(library="bsf", name="js/collapse.js", target="body")
})
@FacesComponent(C.PANEL_COMPONENT_TYPE)
public class Panel extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.PANEL_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public Panel() {
        setRendererType(null); // this component renders itself
        Tooltip.addResourceFile();
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
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
        
        RPanel.encBegin(this, context);
        /*
        ResponseWriter rw = context.getResponseWriter();

        Map<String, Object> attrs = getAttributes();

        String look = A.asString(attrs.get(A.LOOK));// attrs.get(A.LOOK).toString();
        String title = A.asString(attrs.get(A.TITLE));
        rw.startElement(H.DIV, this);
        rw.writeAttribute(H.ID, getClientId(context), H.ID);

        if (look != null) {
            rw.writeAttribute(H.CLASS, "panel panel-" + look, H.CLASS);
        } else {
            rw.writeAttribute(H.CLASS, "panel panel-default", H.CLASS);
        }
        
        UIComponent head = getFacet("heading");
        if (head != null || title != null) {
            rw.startElement(H.DIV, this);
            rw.writeAttribute(H.CLASS, "panel-heading", H.CLASS);
            if (title != null) {
                rw.startElement(H.H4, this);
                rw.writeText(title, null);
                rw.endElement(H.H4);
            } else {
                head.encodeAll(context);
            }
            rw.endElement(H.DIV);
        }

        rw.startElement(H.DIV, this);
        rw.writeAttribute(H.CLASS, "panel-body", H.CLASS);
        */
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        RPanel.encEnd(this, context);
        /*
        ResponseWriter rw = context.getResponseWriter();
        rw.endElement(H.DIV); //panel-body
        UIComponent foot = getFacet("footer");
        if (foot != null) {
            rw.startElement(H.DIV, this); //modal-footer
            rw.writeAttribute(H.CLASS, "panel-footer",H.CLASS);
            foot.encodeAll(context);
            
            rw.endElement(H.DIV); //panel-footer
        }
        
        rw.endElement(H.DIV);
        */
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
