/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.badge;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
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