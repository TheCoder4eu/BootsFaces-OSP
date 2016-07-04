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

package net.bootsfaces.component.icon;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.iconAwesome.IconAwesome;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:icon /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.icon.Icon")
public class IconRenderer extends AJAXRenderer {

	/**
	 * This methods receives and processes input made by the user. More
	 * specifically, it ckecks whether the user has interacted with the current
	 * b:selectBooleanCheckbox. The default implementation simply stores the
	 * input value in the list of submitted values. If the validation checks are
	 * passed, the values in the <code>submittedValues</code> list are store in
	 * the backend bean.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:selectBooleanCheckbox.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		Icon icon = (Icon) component;

		if (icon.isDisabled() || icon.isReadonly()) {
			return;
		}

		decodeBehaviors(context, icon); // moved to AJAXRenderer
		new AJAXRenderer().decode(context, component);
	}

	/**
	 * This methods generates the HTML code of the current b:icon.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:icon.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Icon icon = (Icon) component;
		ResponseWriter writer = context.getResponseWriter();

		String nameOfIcon = icon.getName();
		String styleClass = icon.getStyleClass();
		String responsiveCSS= Responsive.getResponsiveStyleClass(icon, false).trim();
		if (responsiveCSS.length()>0) {
			writer.startElement("div", component);
			writer.writeAttribute("class", responsiveCSS, null);
		}
		String style = icon.getStyle();
		String size = icon.getSize();
		String rotate = icon.getRotate();
		String flip = icon.getFlip();
		boolean spin = icon.isSpin();

		encodeIcon(context.getResponseWriter(), icon, nameOfIcon, icon instanceof IconAwesome, size, rotate, flip, spin,
			 styleClass, style, icon.isDisabled(), icon.isAddon(), true, true);
		if (responsiveCSS.length()>0) {
			writer.endElement("div");
		}

		Tooltip.activateTooltips(context, icon);
	}

	/**
	 * Renders an Icon
	 *
	 * @param rw
	 *            ResponseWriter
	 * @param c
	 *            UIComponent
	 * @param icon
	 *            Icon Name
	 * @param isFontAwesome
	 *            Icon set: false for Bootstrap Glyphicons, true for Font
	 *            Awesome
	 * @param size
	 *            Icon Size lg, 2x, 3x, 4x, 5x
	 * @param rotate
	 *            Can be L,R
	 * @param flip
	 *            Can be H,V
	 * @param spin
	 *            true or false
	 * @throws IOException
	 */
	public static final void encodeIcon(ResponseWriter rw, UIComponent c, String icon, boolean isFontAwesome,
			String size, String rotate, String flip, boolean spin, String styleClass, String style,
			boolean isGrayedOut, boolean isAddon, boolean generateIdAndTooltip, boolean generateAJAXHandler) throws IOException {
		rw.startElement("span", c);
		rw.startElement("i", c);
		if (generateIdAndTooltip) {
			if (isAddon)
				rw.writeAttribute("id", c.getClientId() + "_icon", null);
			else
				rw.writeAttribute("id", c.getClientId(), null);
			Tooltip.generateTooltip(FacesContext.getCurrentInstance(), c, rw);
		}

		StringBuilder sb = new StringBuilder(100); // optimize int
		if (styleClass != null) {
			sb.append(styleClass).append(" ");
		}
		if (isFontAwesome) {
			if (icon.startsWith("fa-"))
				sb.append("fa " + icon);
			else
				sb.append("fa fa-" + icon);
		} else {
			if (icon.startsWith("glyphicon-"))
				sb.append("glyphicon " + icon);
			else
				sb.append("glyphicon glyphicon-" + icon);
		}
		if (size != null) {
			sb.append(" fa-" + size);
		}
		if (rotate != null) {
			if (rotate.equalsIgnoreCase("R")) {
				sb.append(" fa-rotate-90");
			}
			if (rotate.equalsIgnoreCase("L")) {
				sb.append(" fa-rotate-270");
			}
		}
		if (flip != null) {
			if (flip.equalsIgnoreCase("H")) {
				sb.append(" fa-flip-horizontal");
			}
			if (flip.equalsIgnoreCase("V")) {
				sb.append(" fa-flip-vertical");
			}
		}
		if (spin) {
			sb.append(" fa-spin");
		}
		rw.writeAttribute("class", sb.toString(), "class");
		if (isGrayedOut) {
			String filter = "filter: grayscale(100%);-webkit-filter: grayscale(100%);-moz-filter: grayscale(100%);-o-filter: grayscale(100%);-ms-filter: grayscale(100%);opacity:0.3";
			if (style == null)
				style = filter;
			else
				style += " " + filter;
		}
		if (style != null)
			rw.writeAttribute("style", style, "style");
		if (generateAJAXHandler) {
			if (c instanceof IAJAXComponent && c instanceof ClientBehaviorHolder)
				AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(),
						(ClientBehaviorHolder) c, rw);
		}
		rw.endElement("i");

		rw.endElement("span");
	}
}
