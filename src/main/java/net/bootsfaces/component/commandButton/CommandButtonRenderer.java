/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu)
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
package net.bootsfaces.component.commandButton;

import java.io.IOException;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:commandButton /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = CommandButton.DEFAULT_RENDERER)
public class CommandButtonRenderer extends CoreRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		if (componentIsDisabledOrReadonly(component)) {
			return;
		}
		
		boolean found=false;

		String clientId = component.getClientId(context);
		String param = clientId;

		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		if (params.containsKey(param)) {
			found=true;
		} else {
			String source = params.get("javax.faces.source");
	        if (clientId.equals(source)) {
	        	found = true;
	        }
		}
		if (found) {
			new AJAXRenderer().decode(context, component);
		}
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		boolean idHasBeenRendered=false;
		CommandButton commandButton = (CommandButton) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = component.getClientId(context);

		// add responsive style
		String clazz = Responsive.getResponsiveStyleClass(commandButton, false).trim();
		boolean isResponsive = clazz.length() > 0;
		if (isResponsive) {
			rw.startElement("div", commandButton);
			rw.writeAttribute("class", clazz, null);
			rw.writeAttribute("id", clientId, "id");
			idHasBeenRendered = true;
		}

		String type = commandButton.getType();
		if (null == type)
			type = "submit";

		String style = commandButton.getStyle();

		rw.startElement("button", component);
		rw.writeAttribute("type", type, null);
		if (!idHasBeenRendered) {
			rw.writeAttribute("id", clientId, "id");
		}
		rw.writeAttribute("name", clientId, "name");
		if (null != commandButton.getDir()) {
			rw.writeAttribute("dir", commandButton.getDir(), "dir");
		}


		Tooltip.generateTooltip(context, commandButton, rw);

		writeAttribute(rw, "style", style, "style");

		rw.writeAttribute("class", getStyleClasses(commandButton, isResponsive), "class");

		if (commandButton.isDisabled()) {
			rw.writeAttribute("disabled", "disabled", "disabled");
		}

		if (!type.equals("reset") && !type.equals("button")) {
			// Check if it is in a Form
			String formId = R.findComponentFormId(context, component);
			if (formId == null) {
				throw new FacesException("CommandButton : '" + clientId + "' must be inside a form element");
			}
		}

		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, commandButton, rw, false);

		R.encodeHTML4DHTMLAttrs(rw, commandButton.getAttributes(), new String[] { "accesskey", "dir", "lang", "style", "tabindex", "title" });
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter rw = context.getResponseWriter();

		CommandButton commandButton = (CommandButton) component;

		Object value = commandButton.getValue();
		String icon = commandButton.getIcon();
		String faicon = commandButton.getIconAwesome();

		boolean fa = false; // flag to indicate whether the selected icon set is
		// Font Awesome or not.
		if (faicon != null) {
			icon = faicon;
			fa = true;
		}

		if (icon != null) {

			Object ialign = commandButton.getIconAlign(); // Default Left

			if (ialign != null && ialign.equals("right")) {
				value = value != null ? value + " " : null;
				writeText(rw, value, null);
				IconRenderer.encodeIcon(rw, component, icon, fa, commandButton.getIconSize(), commandButton.getIconRotate(), commandButton.getIconFlip(), commandButton.isIconSpin(), null, null, false, false, false, false,
						commandButton.isIconBrand(), commandButton.isIconInverse(), commandButton.isIconLight(), commandButton.isIconPulse(), commandButton.isIconRegular(), commandButton.isIconSolid());
			} else {
				IconRenderer.encodeIcon(rw, component, icon, fa, commandButton.getIconSize(), commandButton.getIconRotate(), commandButton.getIconFlip(), commandButton.isIconSpin(), null, null, false, false, false, false,
				commandButton.isIconBrand(), commandButton.isIconInverse(), commandButton.isIconLight(), commandButton.isIconPulse(), commandButton.isIconRegular(), commandButton.isIconSolid());
				value = value != null ? " " + value : null;
				writeText(rw, value, null);
			}

		} else {
			if (component.getChildCount() > 0) {
				value = value != null ? " " + value : null;
				writeText(rw, value, null);
			} else {
				writeText(rw, value, null);
			}
		}

		Tooltip.activateTooltips(context, component);
		rw.endElement("button");
		String clazz = Responsive.getResponsiveStyleClass(commandButton, false).trim();
		boolean isResponsive = clazz.length() > 0;
		if (isResponsive) {
			rw.endElement("div");
		}
	}

	private String getStyleClasses(CommandButton component, boolean isResponsive) {
		StringBuilder sb = new StringBuilder(40); // optimize int

		sb.append("btn");
		String size = component.getSize();
		if (size != null) {
			sb.append(" btn-").append(size);
		}
		// TBS3 Si usa look, non severity
		String look = component.getLook();
		if (look != null) {
			sb.append(" btn-").append(look);
		} else {
			sb.append(" btn-default");
		}

		if (component.isDisabled()) {
			sb.append(" " + "disabled");
		}
		if (isResponsive) {
			sb.append(" btn-block");
		}
		String sclass = component.getStyleClass();
		if (sclass != null) {
			sb.append(" ").append(sclass);
		}

		return sb.toString().trim();

	}


}
