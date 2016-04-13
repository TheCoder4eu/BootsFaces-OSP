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

import javax.faces.FacesException;
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
		if (!component.isRendered())
			return;

		ProgressBar progressBar = (ProgressBar) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = progressBar.getClientId();

		rw.startElement("div", progressBar); // outer div
		rw.writeAttribute("class", "progress", "class");
		Tooltip.generateTooltip(context, progressBar, rw);

		rw.startElement("div", progressBar); // inner div, responsible for the actual bar
		rw.writeAttribute("id", clientId, "id");

		int max = progressBar.getMax();
		int min = progressBar.getMin();

		if (max == min)
			throw new FacesException("ProgressBar: max and min values must not match.");

		double value = Double.parseDouble(progressBar.getValue());
		double width = (value - min) / (max - min) * 100;

		String style = "width: " + width + "%;";
		style += progressBar.getStyle();

		rw.writeAttribute("style", style, null);

		rw.writeAttribute("role", "progressbar", "role");
		rw.writeAttribute("aria-valuemax", max, "aria-valuemax");
		rw.writeAttribute("aria-valuemin", min, "aria-valuemin");
		rw.writeAttribute("aria-valuenow", progressBar.getValue(), "aria-valuenow");

		String caption = progressBar.getCaption();

		if (caption != null && !caption.isEmpty())
			rw.writeAttribute("aria-valuetext", caption, "aria-valuetext");

		writeStyleClass(progressBar, rw);

		String labelText = caption != null ? caption : progressBar.getValue() + "%";
		writeCaption(progressBar, rw, labelText);

		rw.endElement("div");
		rw.endElement("div");
		Tooltip.activateTooltips(context, progressBar);
	}

	private void writeStyleClass(ProgressBar progressBar, ResponseWriter rw) throws IOException {
		String classes = "progress-bar";
		if (progressBar.getLook() != null)
			classes += " progress-bar-" + progressBar.getLook();

		if (progressBar.isAnimated())
			classes += " active";
		if (progressBar.isStriped() || progressBar.isAnimated())
			classes += " progress-bar-striped";

		classes += " " + progressBar.getStyleClass();
		rw.writeAttribute("class", classes, "class");
	}

	private void writeCaption(ProgressBar progressBar, ResponseWriter rw, String labelText) throws IOException {
		// check if a caption was set explicitly and use that, if not show the percentage
		rw.startElement("span", progressBar);
		// if the caption shouldn't be shown, we set the sr-only styleclass, which hides the text
		if (!progressBar.isRenderCaption())
			rw.writeAttribute("class", "sr-only", "class");
		rw.writeText(labelText, null);
		rw.endElement("span");
	}
}
