/**
 *  Copyright 2014-2019 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
 *
 *  label file is part of BootsFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use label file except in compliance with the License.
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

package net.bootsfaces.component.label;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** label class generates the HTML code of &lt;b:label /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.label.Label")
public class LabelRenderer extends CoreRenderer {

	/**
	 * label methods generates the HTML code of the current b:label.
	 * @param context the FacesContext.
	 * @param component the current b:label.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		Label label = (Label) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = label.getClientId();
	
		boolean idHasBeenRendered = false;

		String sev = label.getSeverity();
		String txt = label.getValue();
		if (txt == null) {
			txt = label.getText();
		}

		// add responsive style
		String clazz = Responsive.getResponsiveStyleClass(label, false).trim();
		boolean isResponsive = clazz.length() > 0;
		if (isResponsive) {
			rw.startElement("div", label);
			rw.writeAttribute("class", clazz, null);
			rw.writeAttribute("id", clientId, "id");
			idHasBeenRendered = true;
		}

		rw.startElement("span", label);
		if (!idHasBeenRendered) {
			rw.writeAttribute("id", clientId, "id");
		}
		Tooltip.generateTooltip(context, label, rw);
		String sclass = "label" + " " + "label";
		if (sev != null) {
			sclass += "-" + sev;
		} else {
			sclass += "-default";
		}
		String styleClass = label.getStyleClass();
		sclass += styleClass != null ? " " + styleClass : "";
		rw.writeAttribute("class", sclass, "class");
		String style = label.getStyle();
		if (isResponsive) {
			if (null == style) {
				style = "display:block";
			} else {
				style += ";display:block";
			}
		}

		if (style != null)
			rw.writeAttribute("style", style, "style");

		rw.writeText(txt, null);
		rw.endElement("span");
		if (isResponsive) {
			rw.endElement("div");
		}

		Tooltip.activateTooltips(context, label);
	}

}
