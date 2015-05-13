/**
 *  Copyright 2015 Stephan Rauh, http://www.beyondjava.net
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
package net.bootsfaces.listeners;

import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.render.RIcon;

/**
 *
 * @author Stephan Rauh, http://www.beyondjava.net
 */
@FacesComponent(C.INTERNAL_FA_LINK)
public class InternalFALink extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.ICON_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    public InternalFALink() {
        setRendererType(null); // this component renders itself
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
    	ResponseWriter responseWriter = fc.getResponseWriter();
        
        responseWriter.append("<link type=\"text/css\" rel=\"stylesheet\" href=\"" + C.FONTAWESOME_CDN_URL + "\" />");
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
}
