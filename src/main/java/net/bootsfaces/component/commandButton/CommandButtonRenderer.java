/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
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

		String param = component.getClientId(context);
		if (context.getExternalContext().getRequestParameterMap().containsKey(param)) {
			new AJAXRenderer().decode(context, component);
		}
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		CommandButton commandButton = (CommandButton) component;
		ResponseWriter rw = context.getResponseWriter();
//		component.setId(component.getId());
		String CID = component.getClientId(context);

		// 2) get the type (submit, button, reset ; default submit)
		String type = commandButton.getType();
		if (null == type)
			type = "submit";
		// 3) is it Ajax? (default= false)
		String style = commandButton.getStyle();

		rw.startElement("button", component);
		rw.writeAttribute("type", type, null);
		rw.writeAttribute("id", CID, "id");
		rw.writeAttribute("name", CID, "name");
		if (null != commandButton.getDir()) {
			rw.writeAttribute("dir", commandButton.getDir(), "dir");
		}


		Tooltip.generateTooltip(context, commandButton, rw);

		writeAttribute(rw, "style", style, "style");

		rw.writeAttribute("class", getStyleClasses(commandButton), "class");

		if (commandButton.isDisabled()) {
			rw.writeAttribute("disabled", "disabled", "disabled");
		}

		if (!type.equals("reset") && !type.equals("button")) {
			// Check if it is in a Form
			String formId = R.findComponentFormId(context, component);
			if (formId == null) {
				throw new FacesException("CommandButton : '" + CID + "' must be inside a form element");
			}
		}

		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, commandButton, rw);

		// TODO : write DHTML attrs - onclick
		// Encode attributes (HTML 4 pass-through + DHTML)
		R.encodeHTML4DHTMLAttrs(rw, commandButton.getAttributes(), H.ALLBUTTON);
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter rw = context.getResponseWriter();
		// Map<String, Object> attrs = component.getAttributes();

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
				IconRenderer.encodeIcon(rw, component, icon, fa, commandButton.getIconSize(), commandButton.getIconRotate(), commandButton.getIconFlip(), commandButton.isIconSpin(), null, null, false, false, false, false);
			} else {
				IconRenderer.encodeIcon(rw, component, icon, fa, commandButton.getIconSize(), commandButton.getIconRotate(), commandButton.getIconFlip(), commandButton.isIconSpin(), null, null, false, false, false, false);
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

		rw.endElement("button");

		Tooltip.activateTooltips(context, component);
	}

	private String getStyleClasses(CommandButton component) {
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
		String sclass = component.getStyleClass();
		if (sclass != null) {
			sb.append(" ").append(sclass);
		}

		// add responsive style
		sb.append(Responsive.getResponsiveStyleClass(component, false));

		return sb.toString().trim();

	}


}
