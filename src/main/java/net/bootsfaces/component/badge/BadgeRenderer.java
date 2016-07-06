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

package net.bootsfaces.component.badge;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:badge /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.badge.Badge")
public class BadgeRenderer extends CoreRenderer {

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

		rw.startElement("span", component);
		if (null != suffix) {
			clientId = clientId + suffix;
		}
		rw.writeAttribute("id", clientId, "id");
		if (styleClass == null)
			styleClass = "badge";
		else
			styleClass += " badge";
		Tooltip.generateTooltip(context, component, rw);
		if (component instanceof IResponsive) {
			styleClass += Responsive.getResponsiveStyleClass((IResponsive)component, false);
		}
		rw.writeAttribute("class", styleClass, "class");
		if (null != style)
			rw.writeAttribute("style", style, "style");
		if (val!=null) {
			rw.writeText(val, null);
		}
		rw.endElement("span");
		Tooltip.activateTooltips(context, component);
	}
}