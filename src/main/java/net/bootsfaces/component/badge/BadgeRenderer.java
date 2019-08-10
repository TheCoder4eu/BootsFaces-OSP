/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.badge;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreInputRenderer;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:badge /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.badge.Badge")
public class BadgeRenderer extends CoreInputRenderer {

	/**
	 * This methods generates the HTML code of the current b:badge.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:badge.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Badge badge = (Badge) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = badge.getClientId();

		if (!component.isRendered()) {
			return;
		}
		String styleClass = badge.getStyleClass();
		String style=badge.getStyle();
		String val = getValue2Render(context, badge);

		generateBadge(context, badge, rw, clientId, styleClass, style, val, null);
	}

	protected void generateBadge(FacesContext context, UIComponent component, ResponseWriter rw,
			String clientId, String styleClass, String style, String val, String suffix) throws IOException {

		if (null != suffix) {
			clientId = clientId + suffix;
		}
		boolean idHasBeenRendered=false;
		String clazz = "";
		if (component instanceof IResponsive) {
			clazz = Responsive.getResponsiveStyleClass((IResponsive)component, false).trim();
		}
		boolean isResponsive = clazz.length() > 0;
		if (isResponsive) {
			rw.startElement("div", component);
			rw.writeAttribute("class", clazz, null);
			rw.writeAttribute("id", clientId, "id");
			idHasBeenRendered = true;
		}
		rw.startElement("span", component);
		if (!idHasBeenRendered) {
			rw.writeAttribute("id", clientId, "id");
		}
		if (styleClass == null)
			styleClass = "badge";
		else
			styleClass += " badge";
		Tooltip.generateTooltip(context, component, rw);
		rw.writeAttribute("class", styleClass, "class");
		if (isResponsive) {
			if (null == style) { 
				style="display:block";
			}
			else {
				style += ";display:block";
			}
		}
		if (null != style)
			rw.writeAttribute("style", style, "style");
		if (val!=null) {
			rw.writeText(val, null);
		}
		rw.endElement("span");
		if (isResponsive) {
			rw.endElement("div");
		}

		Tooltip.activateTooltips(context, component);
	}
}