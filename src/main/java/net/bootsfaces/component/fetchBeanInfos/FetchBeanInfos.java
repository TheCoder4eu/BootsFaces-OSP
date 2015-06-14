/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component.fetchBeanInfos;

import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.H;
import net.bootsfaces.render.RAlert;
import net.bootsfaces.render.Tooltip;

/**
 * The &lt;alert&gt; tag generates a colored box that can be used to display error messages, warnings, informations or simply success messages.
 * 
 * @author thecoder4eu
 */

@FacesComponent(C.FETCH_BEAN_INFOS_COMPONENT_TYPE)
public class FetchBeanInfos extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.FETCH_BEAN_INFOS_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public FetchBeanInfos() {
        setRendererType(null); // this component renders itself
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        ResponseWriter rw = fc.getResponseWriter();
        rw.startElement("script", this);
        if (fc.isValidationFailed()) {
        	rw.writeText("validationFailed=true;", null);
        } else  {
        	rw.writeText("validationFailed=false;", null);
        }
        rw.endElement("script");
        
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
