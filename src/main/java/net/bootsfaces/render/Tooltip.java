/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.render;

import java.io.IOException;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;

/**
 * Renders a tooltip.
 *
 * @author Stephan Rauh
 */
public class Tooltip {

	public static void generateTooltip(FacesContext context, UIComponent component, ResponseWriter rw)
			throws IOException {
		Map<String, Object> attrs = component.getAttributes();
		String tooltip = (String) attrs.get("tooltip");
		if (null != tooltip) {
			//set default values first, if not present
			String position = (String) attrs.get("tooltipPosition");
			if (null == position) // compatibility for the HTML-style using "-" characters instead of camelcase
				position = (String) attrs.get("tooltip-position");
			if (null == position)
				position = "auto";
			String container = (String) attrs.get("tooltipContainer");
			if (null == container) // compatibility for the HTML-style using "-" characters instead of camelcase
				container = (String) attrs.get("tooltip-container");
			if (null == container || container.length() == 0)
				container = "body";
			verifyAndWriteTooltip(context, rw, tooltip, position, container);
		}
	}

	private static void verifyAndWriteTooltip(FacesContext context, ResponseWriter rw, String tooltip,
			String position, String container) throws IOException {
		if (null == position)
			position="bottom";
		boolean ok = "top".equals(position);
		ok |= "bottom".equals(position);
		ok |= "right".equals(position);
		ok |= "left".equals(position);
		ok |= "auto".equals(position);
		ok |= "auto top".equals(position);
		ok |= "auto bottom".equals(position);
		ok |= "auto right".equals(position);
		ok |= "auto left".equals(position);
		if (!ok) {
			position = "bottom";
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong JSF markup",
					"Tooltip position must either be 'auto', 'top', 'bottom', 'left' or 'right'."));
		}
		rw.writeAttribute("data-toggle", "tooltip", null);
		rw.writeAttribute("data-placement", position, "data-placement");
		rw.writeAttribute("data-container", container, "data-container");
		rw.writeAttribute("title", tooltip, null);
	}

	private static String generateDelayAttributes(Map<String, Object> attrs) throws IOException {
		String json = "";
		String delayShow = getAndCheckDelayAttribute("tooltip-delay-show", attrs, "show");
		if (null == delayShow) {
			delayShow = getAndCheckDelayAttribute("tooltip-delay", attrs, "show");
		}
		if (null != delayShow)
			json += delayShow + ",";

		String delayHide = getAndCheckDelayAttribute("tooltip-delay-hide", attrs, "hide");
		if (null == delayHide) {
			delayHide = getAndCheckDelayAttribute("tooltip-delay", attrs, "hide");
		}
		if (null != delayHide)
			json += delayHide + ",";
		if (!json.isEmpty()) {
			return "{" + json.substring(0, json.length() - 1) + "}";
		}
		return null;
	}

	private static String getAndCheckDelayAttribute(String attributeName, Map<String, Object> attrs,
			String htmlAttributeName) throws FacesException {
		Object value = attrs.get(attributeName);
		if (null != value) {
			if ((value instanceof String) && ((String)value).length() > 0) {
				try {
					Integer.parseInt((String)value);
					return htmlAttributeName + ":" + value;
				} catch (NumberFormatException ex) {
					//throw exception later
				}
			}
			else if (value instanceof Integer) {
				return htmlAttributeName + ":" + value;
			}

			//if we reach this point, the value wasn't accepted as Integer
			throw new FacesException("The attribute " + attributeName + " has to be numeric. The value '"
					+ value + "' is invalid.");
		}

		return null;
	}

	/**
	 * Adds the required resource files for tooltips to the required resources list.
	 */
	public static void addResourceFiles() {
		// if (null != getAttributes().get("tooltip")) {
		//!bs-less//AddResourcesListener.addThemedCSSResource("tooltip.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/tooltip.js");
		// }
	}

	public static void activateTooltips(FacesContext context, UIComponent component)
			throws IOException {
		String id = component.getClientId();
		activateTooltips(context, component, id);
	}

	public static void activateTooltips(FacesContext context, UIComponent component, String id) throws IOException {
		Map<String, Object> attributes = component.getAttributes();
		if (attributes.get("tooltip") != null) {
			id = id.replace(":", "\\\\:"); // we need to escape the id for
											// jQuery
			String delayOptions = generateDelayAttributes(attributes);
			String options = "";
			if (null != delayOptions)
				options = "'delay':" + delayOptions + ",";
			if (options.length() > 0)
				options = "{" + options.substring(0, options.length() - 1) + "}";

			String js = "$(function () {\n" + "$('#" + id + "').tooltip(" + options + ")\n" + "});\n";
			//destroy existing tooltips to prevent ajax bugs in some browsers and prevent memory leaks (see #323 and #220)
			js+="$('.tooltip').tooltip('destroy'); ";
			context.getResponseWriter().write("<script type='text/javascript'>" + js + "</script>");
		}
	}
}
