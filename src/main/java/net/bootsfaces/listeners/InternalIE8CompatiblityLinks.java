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
import javax.faces.FacesException;

import javax.faces.application.Resource;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ExternalContext;
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
	 * <p>
	 * The standard component type for this component.</p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.internalIE8CompatibilityLink.InternalIE8CompatibilityLink";
	/**
	 * <p>
	 * The component family for this component.</p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public InternalIE8CompatiblityLinks() {
		setRendererType(null); // this component renders itself
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		encodeJS(context, C.BSF_LIBRARY, "js/html5shiv.js");
		encodeJS(context, C.BSF_LIBRARY, "js/respond.js");
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	private void encodeJS(FacesContext context, String library, String script) throws IOException {
		ResponseWriter responseWriter = context.getResponseWriter();
		ExternalContext externalContext = context.getExternalContext();
		Resource resource = context.getApplication().getResourceHandler().createResource(script, library, "text/javascript");

		if (resource == null) {
			throw new FacesException("Error loading JavaScript, cannot find \"" + script + "\" resource of \"" + library + "\" library");
		} else {
			responseWriter.write("<!--[if lt IE 9]>");
			responseWriter.startElement("script", null);
			responseWriter.writeAttribute("type", "text/javascript", null);
			responseWriter.writeAttribute("src", externalContext.encodeResourceURL(resource.getRequestPath()), null);
			responseWriter.endElement("script");
			responseWriter.write("<![endif]-->");
		}
	}

}
