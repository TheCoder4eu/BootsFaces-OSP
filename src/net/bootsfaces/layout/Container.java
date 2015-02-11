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
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import net.bootsfaces.C;
import net.bootsfaces.render.A;
import net.bootsfaces.render.H;

/**
 *
 * @author thecoder4.eu
 */
@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css"),
        @ResourceDependency(library="bsf", name="jq/jquery.js")
})
@FacesComponent(C.CONTAINER_COMPONENT_TYPE)
public class Container extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.CONTAINER_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFLAYOUT;
    

    public Container() {
        setRendererType(null); // this component renders itself
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        /*
         * OLD Only Fluid: as of BS3
         * Now BS3 has again container-fluid
         * <div class="container">...</div>
         * or
         * <div class="container-fluid">...</div>
         */
        
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = getAttributes();
        boolean fluid=A.toBool(attrs.get("fluid"), false);
        
        rw.startElement(H.DIV, this);
        rw.writeAttribute(H.ID,getClientId(fc),H.ID);
        rw.writeAttribute(H.CLASS, (fluid ? "container-fluid" : "container"),H.CLASS);
    }
    
    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        fc.getResponseWriter().endElement(H.DIV);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
