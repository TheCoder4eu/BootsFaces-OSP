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

import javax.faces.application.Resource;
import javax.faces.application.ResourceWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author thecoder4.eu
 */
public class BsfResWrapper extends ResourceWrapper {
    
    Resource resource;
    
    public BsfResWrapper(Resource r) {
        this.resource=r;
    }
    
    @Override
            public String getRequestPath() {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String mapping = externalContext.getRequestServletPath();

                if (externalContext.getRequestPathInfo() == null) {
                    mapping = mapping.substring(mapping.lastIndexOf('.'));
                }

                String path = super.getRequestPath();

                if (mapping.charAt(0) == '/') {
                    return path.replaceFirst(mapping, "");
                }
                else if (path.contains("?")) {
                    return path.replace(mapping + "?", "?");
                }
                else {
                    return path.substring(0, path.length() - mapping.length());
                }
            }

            @Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
            public String getResourceName() {
                return resource.getResourceName();
            }

            @Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
            public String getLibraryName() {
                return resource.getLibraryName();
            }

            @Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
            public String getContentType() {
                return resource.getContentType();
            }

            @Override
            public Resource getWrapped() {
                return resource;
            }
        
}
