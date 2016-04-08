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

package net.bootsfaces.component.progressBar;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;


/** This class generates the HTML code of &lt;b:progressBar /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.progressBar.ProgressBar")
public class ProgressBarRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:progressBar.
	 * @param context the FacesContext.
	 * @param component the current b:progressBar.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		ProgressBar progressBar = (ProgressBar) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = progressBar.getClientId();

		rw.startElement("div", progressBar);
		rw.writeAttribute("class", "progress", "class");
		Tooltip.generateTooltip(context, progressBar, rw);

	    rw.startElement("div", progressBar);
		rw.writeAttribute("id", clientId, "id");
		rw.writeAttribute("class", "progress-bar", "class");

//		String style = progressBar.getStyle();
//		if (null != style) {
//			rw.writeAttribute("style", style, null);
//		}
//
//		String styleClass = progressBar.getStyleClass();
//		if (null == styleClass)
//			styleClass = "";
//		else
//			styleClass = " " + styleClass;

		rw.writeAttribute("aria-valuemax", progressBar.getMax() != 0 ? progressBar.getMax() : 100, "aria-valuemax");
		rw.writeAttribute("aria-valuemin", progressBar.getMin(), "aria-valuemin");
		rw.writeAttribute("aria-valuenow", progressBar.getValue(), "aria-valuenow");
		rw.writeAttribute("role", "progressbar", "role");

	    rw.writeAttribute("label", progressBar.getLabel(), "label");
	    rw.writeAttribute("look", progressBar.getLook(), "look");

	    rw.writeAttribute("striped", String.valueOf(progressBar.isStriped()), "striped");
	    rw.writeAttribute("animated", String.valueOf(progressBar.isAnimated()), "animated");

	    /*rw.writeAttribute("tooltip", progressBar.getTooltip(), "tooltip");
	    rw.writeAttribute("tooltip-container", progressBar.getTooltipContainer(), "tooltip-container");
	    rw.writeAttribute("tooltip-delay", progressBar.getTooltipDelay(), "tooltip-delay");
	    rw.writeAttribute("tooltip-delay-hide", progressBar.getTooltipDelayHide(), "tooltip-delay-hide");
	    rw.writeAttribute("tooltip-delay-show", progressBar.getTooltipDelayShow(), "tooltip-delay-show");
	    rw.writeAttribute("tooltip-position", progressBar.getTooltipPosition(), "tooltip-position");*/

		rw.writeText(progressBar.getLabel(), null);
		rw.endElement("div");
		rw.endElement("div");
		Tooltip.activateTooltips(context, progressBar);

	}


}
