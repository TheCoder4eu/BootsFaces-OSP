/**
 *  Copyright 2015-2016 Stephan Rauh, http://www.beyondjava.net
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

package net.bootsfaces.listeners;

import java.io.IOException;

import javax.faces.application.Application;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;

/**
 *
 * @author Stephan Rauh, http://www.beyondjava.net
 */
@FacesComponent("net.bootsfaces.component.internalIE8CompatibilityLink.InternalIE8CompatibilityLink")
public class InternalIE8CompatiblityLinks extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE ="net.bootsfaces.component.internalIE8CompatibilityLink.InternalIE8CompatibilityLink";
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    public InternalIE8CompatiblityLinks() {
        setRendererType(null); // this component renders itself
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        Application app = context.getApplication();
        ResourceHandler rh = app.getResourceHandler();
    	ResponseWriter responseWriter = context.getResponseWriter();
		Resource h5s = rh.createResource("js/html5shiv.js", C.BSF_LIBRARY);
		Resource rjs = rh.createResource("js/respond.js", C.BSF_LIBRARY);

		responseWriter.write("<!--[if lt IE 9]>");
		responseWriter.startElement("script", null);
		responseWriter.writeAttribute("src", h5s.getRequestPath(), null);
		responseWriter.endElement("script");
		responseWriter.startElement("script", null);
		responseWriter.writeAttribute("src", rjs.getRequestPath(), null);
		responseWriter.endElement("script");
		responseWriter.write("<![endif]-->");
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
}
