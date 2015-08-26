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
package net.bootsfaces.render;

import java.io.IOException;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.ResourceDependency;
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

	public static void generateTooltip(FacesContext context, IHasTooltip component, ResponseWriter rw)
			throws IOException {
		/**
		 * TODO quick and dirty solution - needs to be reworked to support EL
		 * expressions
		 */
		generateTooltip(context, ((UIComponent) component).getAttributes(), rw);
	}

	public static void generateTooltip(FacesContext context, Map<String, Object> attrs, ResponseWriter rw)
			throws IOException {
		String tooltip = (String) attrs.get("tooltip");
		if (null != tooltip) {
			String position = (String) attrs.get("tooltipPosition");
			if (null == position)
				position = (String) attrs.get("tooltip-position");
			if (null == position)
				position = "auto";
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
			rw.writeAttribute("data-container", "body", "data-container"); // quick fix for #166
			rw.writeAttribute("title", tooltip, null);
		}
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
		if (json.length() > 0) {
			return "{" + json.substring(0, json.length() - 1) + "}";
		}
		return null;
	}

	private static String getAndCheckDelayAttribute(String attributeName, Map<String, Object> attrs,
			String htmlAttributeName) throws FacesException {
		String value = (String) attrs.get(attributeName);
		if (null != value && value.length() > 0) {
			try {
				Integer.parseInt(value);
				return htmlAttributeName + ":" + value;
			} catch (NumberFormatException ex) {
				throw new FacesException("The attribute " + attributeName + "has to be either numeric. The value '"
						+ value + "' is invalid.");
			}
		}
		return null;

	}

	public static void addResourceFile() {
		// if (null != getAttributes().get("tooltip")) {
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/tooltip.js");
		// }
	}

	public static void activateTooltips(FacesContext context, Map<String, Object> attributes, UIComponent component)
			throws IOException {
		if (attributes.get("tooltip") != null) {
			String id = component.getClientId();
			id = id.replace(":", "\\\\:"); // we need to escape the id for
											// jQuery
			String delayOptions = generateDelayAttributes(attributes);
			String options = "";
			if (null != delayOptions)
				options = "'delay':" + delayOptions + ",";
			if (options.length() > 0)
				options = "{" + options.substring(0, options.length() - 1) + "}";

			String js = "$(function () {\n" + "$('#" + id + "').tooltip(" + options + ")\n" + "});\n";
			context.getResponseWriter().write("<script type='text/javascript'>" + js + "</script>");
		}
	}

	public static void activateTooltips(FacesContext context, IHasTooltip component) throws IOException {
		if (component.getTooltip() != null) {
			String id = ((UIComponent) component).getClientId();
			id = id.replace(":", "\\\\:"); // we need to escape the id for
											// jQuery
			String delayOptions = generateDelayAttributes(((UIComponent) component).getAttributes());
			String options = "";
			if (null != delayOptions)
				options = "'delay':" + delayOptions + ",";
			if (options.length() > 0)
				options = "{" + options.substring(0, options.length() - 1) + "}";

			String js = "$(function () {\n" + "$('#" + id + "').tooltip(" + options + ")\n" + "});\n";
			context.getResponseWriter().write("<script type='text/javascript'>" + js + "</script>");
		}

	}
}
