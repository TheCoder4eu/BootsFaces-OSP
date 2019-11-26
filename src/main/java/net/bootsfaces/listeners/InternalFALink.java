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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;

/**
 * @author Stephan Rauh, http://www.beyondjava.net
 */
@FacesComponent("net.bootsfaces.component.internalFALink.InternalFALink")
public class InternalFALink extends UIComponentBase {

	private static final Logger LOGGER = Logger.getLogger(InternalFALink.class.getName());

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.internalFALink.InternalFALink";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public InternalFALink() {
		setRendererType(null); // this component renders itself
	}

	private String version = "4";

	private boolean needsVersion4 = false;

	@Override
	public void encodeBegin(FacesContext fc) throws IOException {
		final String FA_VERSION = "4.7.0";
		final String FONTAWESOME_CDN_URL = "//maxcdn.bootstrapcdn.com/font-awesome/" + FA_VERSION
				+ "/css/font-awesome.min.css";

		ResponseWriter responseWriter = fc.getResponseWriter();

		LOGGER.log(Level.FINER, "do encodeBegin - version is {0} / needsVersion4 is {1}",
				new Object[] { version, needsVersion4 });

		if (version.contains("5")) {
			responseWriter.append(
					"<link type=\"text/css\" rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.2.0/css/all.css\" crossorigin=\"anonymous\"/>");
			responseWriter.append(
					"<link type=\"text/css\" rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.2.0/css/solid.css\" crossorigin=\"anonymous\"/>");

		}
		if (version.contains("4")) {
			if (needsVersion4) {
				responseWriter.append("<link type=\"text/css\" rel=\"stylesheet\" href=\"" + FONTAWESOME_CDN_URL
						+ "\" crossorigin=\"anonymous\"/>");
			}
		}
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isNeedsVersion4() {
		return needsVersion4;
	}

	public void setNeedsVersion4(boolean needsVersion4) {
		this.needsVersion4 = needsVersion4;
	}
}
