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

package net.bootsfaces.component.inputTextarea;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.inputSecret.InputSecret;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

@FacesRenderer(componentFamily = C.BSFCOMPONENT, rendererType = "net.bootsfaces.component.InputTextareaRenderer")
public class InputTextareaRenderer extends CoreRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		InputTextarea inputText = (InputTextarea) component;

		if (inputText.isDisabled() || inputText.isReadonly()) {
			return;
		}

		decodeBehaviors(context, inputText);

		String clientId = inputText.getClientId(context);
		String name = "input_" + clientId;
		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(name);

		if (submittedValue != null) {
			inputText.setSubmittedValue(submittedValue);
		}
		new AJAXRenderer().decode(context, component, name);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		InputTextarea inputText = (InputTextarea) component;

		ResponseWriter rw = context.getResponseWriter();
		String responsiveStyleClass = Responsive.getResponsiveStyleClass(inputText, false).trim();
		if (responsiveStyleClass.length() > 0) {
			rw.startElement("div", component);
			rw.writeAttribute("class", responsiveStyleClass, "class");
		}

		String clientId = inputText.getClientId();

		// "Prepend" facet
		UIComponent prep = inputText.getFacet("prepend");
		// "Append" facet
		UIComponent app = inputText.getFacet("append");
		boolean prepend = (prep != null);
		boolean append = (app != null);

		String label = inputText.getLabel();
		{
			if (!inputText.isRenderLabel()) {
				label = null;
			}
		}

		// Define TYPE ( if null set default = text )
		// support for b:inputSecret
		String t;
		if (component instanceof InputSecret) {
			t = "password";
		} else { // ordinary input fields
			t = inputText.getType();
			if (t == null)
				t = "text";
		}

		rw.startElement("div", component);
		if (null != inputText.getDir()) {
			rw.writeAttribute("dir", inputText.getDir(), "dir");
		}

		Tooltip.generateTooltip(context, inputText, rw);
		rw.writeAttribute("id", clientId, "id");
		if (inputText.isInline()) {
			rw.writeAttribute("class", "form-inline", "class");

		} else {
			rw.writeAttribute("class", "form-group", "class");
		}

		if (label != null) {
			rw.startElement("label", component);
			rw.writeAttribute("for", "input_" + clientId, "for");
			generateErrorAndRequiredClass(inputText, rw, clientId, inputText.getLabelStyleClass());
			writeAttribute(rw, "style", inputText.getLabelStyle());

			rw.writeText(label, null);
			rw.endElement("label");
		}

		if (append || prepend) {
			rw.startElement("div", component);
			rw.writeAttribute("class", "input-group", "class");
		}


		if (prepend) {
			R.decorateFacetComponent(inputText, prep, context, rw);
		}

		rw.startElement("textarea", inputText);
		rw.writeAttribute("id", "input_" + clientId, null);
		rw.writeAttribute("name", "input_" + clientId, null);
		rw.writeAttribute("type", t, null);
		rw.writeAttribute("rows", inputText.getRows(), "rows");


		generateStyleClass(inputText, rw);

		String ph = inputText.getPlaceholder();
		if (ph != null) {
			rw.writeAttribute("placeholder", ph, null);
		}

		if (inputText.isDisabled()) {
			rw.writeAttribute("disabled", "disabled", null);
		}
		if (inputText.isReadonly()) {
			rw.writeAttribute("readonly", "readonly", null);
		}

		// Encode attributes (HTML 4 pass-through + DHTML)
		renderPassThruAttributes(context, component, H.INPUT_TEXT);

		String autocomplete = inputText.getAutocomplete();
		if ((autocomplete != null) && (autocomplete.equals("off"))) {
			rw.writeAttribute("autocomplete", "off", null);
		}

		// Render Ajax Capabilities
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), inputText, rw);

		String v = getValue2Render(context, component);
		if (null == v)
			v="";
		rw.writeText(v, null);
		rw.endElement("textarea");

		if (append) {
			R.decorateFacetComponent(inputText, app, context, rw);
		}

		if (append || prepend) {
			rw.endElement("div");
		} // input-group
		rw.endElement("div"); // form-group

		if (responsiveStyleClass.length() > 0) {
			rw.endElement("div");
		}

		Tooltip.activateTooltips(context, inputText);
	}

	private void generateStyleClass(InputTextarea inputText, ResponseWriter rw) throws IOException {
		StringBuilder sb;
		String s;
		sb = new StringBuilder(20); // optimize int
		sb.append("form-control");

		String fsize = inputText.getFieldSize();

		if (fsize != null) {
			sb.append(" input-").append(fsize);
		}

		// styleClass and class support
		String sclass = inputText.getStyleClass();
		if (sclass != null) {
			sb.append(" ").append(sclass);
		}

		sb.append(" ").append(getErrorAndRequiredClass(inputText, inputText.getClientId()));
		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute("class", s, "class");
		}
	}
}
