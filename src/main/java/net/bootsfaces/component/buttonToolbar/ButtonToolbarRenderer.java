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

package net.bootsfaces.component.buttonToolbar;

import java.io.IOException;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:buttonToolbar /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.buttonToolbar.ButtonToolbar")
public class ButtonToolbarRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:buttonToolbar.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param fc
	 *            the FacesContext.
	 * @param component
	 *            the current b:buttonToolbar.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext fc, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}

		ResponseWriter rw = fc.getResponseWriter();
		ButtonToolbar buttonToolbar = (ButtonToolbar) component;

		String pull = buttonToolbar.getPull();

		rw.startElement("div", buttonToolbar);
		rw.writeAttribute("id", component.getClientId(fc), "id");
		Tooltip.generateTooltip(fc, buttonToolbar, rw);
		String styleClasses = "btn-toolbar " + Responsive.getResponsiveStyleClass(buttonToolbar, false);
		if (null != buttonToolbar.getStyleClass()) {
			styleClasses += " " + buttonToolbar.getStyleClass();
		}
		if (pull != null && (pull.equals("right") || pull.equals("left"))) {
			rw.writeAttribute("class", styleClasses.concat(" ").concat("pull").concat("-").concat(pull),
					"class");
		} else {
			rw.writeAttribute("class", styleClasses, "class");
		}
		if (null != buttonToolbar.getStyle()) {
			rw.writeAttribute("style", buttonToolbar.getStyle(), "style");
		}
		beginDisabledFieldset(buttonToolbar, rw);
	}

	/**
	 * This methods generates the HTML code of the current b:buttonToolbar.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:buttonToolbar.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		ButtonToolbar buttonToolbar = (ButtonToolbar) component;
		ResponseWriter rw = context.getResponseWriter();
		endDisabledFieldset(buttonToolbar, rw);
		rw.endElement("div");
		Tooltip.activateTooltips(context, buttonToolbar);
	}
}
