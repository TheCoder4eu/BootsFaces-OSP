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

package net.bootsfaces.component.socialButton;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class generates the HTML code of &lt;b:socialButton /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.socialButton.SocialButton")
public class SocialButtonRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:socialButton.
	 * @param context the FacesContext.
	 * @param component the current b:socialButton.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		SocialButton socialButton = (SocialButton) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = socialButton.getClientId();
	
		rw.startElement("a", socialButton);
		rw.writeAttribute("target", "_blank", "target");
		rw.writeAttribute("id", clientId, "id");
		if(BsfUtils.isStringValued(socialButton.getHref())) rw.writeAttribute("href", socialButton.getHref(), "href");
		if(BsfUtils.isStringValued(socialButton.getStyle())) rw.writeAttribute("style", socialButton.getStyle(), "style");
		rw.writeAttribute("class", getStyleClass(socialButton), "class");
		Tooltip.generateTooltip(context, socialButton, rw);
		
		IconRenderer.encodeIcon(rw, socialButton, socialButton.getSocial(), true);
		if(!socialButton.isOnlyIcon() && BsfUtils.isStringValued(socialButton.getValue())) {
			rw.writeText(socialButton.getValue(), null);
		}
		
		rw.endElement("a");
		
		Tooltip.activateTooltips(context, socialButton);
	}
	
	/**
	 * Get the style class
	 * @param sb
	 * @return
	 */
	private String getStyleClass(SocialButton sb) {
		String sClass = sb.getStyleClass();
		if(sClass == null) sClass = "";
		
		sClass += "btn btn-social";
		if(sb.isOnlyIcon()) sClass += "-icon";
		
		sClass += (" btn-" + sb.getSocial() );
		
		sClass += Responsive.getResponsiveStyleClass(sb, false);
		
		return sClass;
	}

}
