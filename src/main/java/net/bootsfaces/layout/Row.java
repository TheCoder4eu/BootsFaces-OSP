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

package net.bootsfaces.layout;

import java.io.IOException;
import java.util.Map;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */
@ResourceDependency(library="bsf", name="css/core.css")
@FacesComponent(C.ROW_COMPONENT_TYPE)
public class Row extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.ROW_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFLAYOUT;

    public Row() {
        setRendererType(null); // this component renders itself
        Tooltip.addResourceFile();
    }
    
    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
         * <div class="row">  || <div class="row-fluid">
         * ...
         * </div>
         * BS3: Always fluid
         */
        
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = getAttributes();
       	R.encodeRow(rw, this, A.asString(attrs.get(H.STYLE)), A.asString(attrs.get(H.STYLECLASS)));
        
    }
    
    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        if (!isRendered()) {
            return;
        }
        fc.getResponseWriter().endElement("div");
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
