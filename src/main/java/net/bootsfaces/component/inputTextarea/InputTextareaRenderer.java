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

package net.bootsfaces.component.inputTextarea;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.inputSecret.InputSecret;
import net.bootsfaces.component.inputText.InputTextRenderer;
import net.bootsfaces.render.CoreInputRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

@FacesRenderer(componentFamily = C.BSFCOMPONENT, rendererType = "net.bootsfaces.component.InputTextareaRenderer")
public class InputTextareaRenderer extends CoreInputRenderer {
	private static final Logger LOGGER = Logger.getLogger(InputTextRenderer.class.getName());

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
		int numberOfDivs=0;
		
		ResponseWriter rw = context.getResponseWriter();
		String clientId = inputText.getClientId();
		boolean clientIdHasBeenRendered=false;

		String responsiveLabelClass = null;
		String label = inputText.getLabel();
		{
			if (!inputText.isRenderLabel()) {
				label = null;
			}
		}
		if (null != label) {
			responsiveLabelClass = Responsive.getResponsiveLabelClass(inputText);
		}

		String responsiveStyleClass = Responsive.getResponsiveStyleClass(inputText, false).trim();
		if (responsiveStyleClass.length() > 0 && responsiveLabelClass == null && (!isHorizontalForm(component))) {
			rw.startElement("div", component);
			rw.writeAttribute("class", responsiveStyleClass, "class");
			rw.writeAttribute("id", clientId, null);
			Tooltip.generateTooltip(context, inputText, rw);
			clientIdHasBeenRendered=true;
			numberOfDivs++;
		}


		// "Prepend" facet
		UIComponent prep = inputText.getFacet("prepend");
		// "Append" facet
		UIComponent app = inputText.getFacet("append");
		boolean prepend = (prep != null);
		boolean append = (app != null);

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
		numberOfDivs++;
		if (null != inputText.getDir()) {
			rw.writeAttribute("dir", inputText.getDir(), "dir");
		}
		if (!clientIdHasBeenRendered) {
			rw.writeAttribute("id", clientId, "id");
			Tooltip.generateTooltip(context, inputText, rw);
			clientIdHasBeenRendered=true;
		}
                
                rw.writeAttribute("class", getWithFeedback(getInputMode(inputText.isInline()), component), "class");

		if (label != null) {
			rw.startElement("label", component);
			rw.writeAttribute("for", "input_" + clientId, "for");
			generateErrorAndRequiredClass(inputText, rw, clientId, inputText.getLabelStyleClass(), responsiveLabelClass,
					"control-label");
			writeAttribute(rw, "style", inputText.getLabelStyle());

			rw.writeText(label, null);
			rw.endElement("label");
		}

		if (responsiveStyleClass.length() > 0 && isHorizontalForm(component)) {
			rw.startElement("div", component);
			rw.writeAttribute("class", responsiveStyleClass, "class");
			numberOfDivs++;
		}

		if (append || prepend) {
			rw.startElement("div", component);
			rw.writeAttribute("class", "input-group", "class");
			numberOfDivs++;
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
		renderPassThruAttributes(context, component, new String[] { "accesskey", "alt", "dir", "lang", "maxlength", "size", "style",
				"tabindex", "title" });

		String autocomplete = inputText.getAutocomplete();
		if ((autocomplete != null) && (autocomplete.equals("off"))) {
			rw.writeAttribute("autocomplete", "off", null);
		}

		// Render Ajax Capabilities
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), inputText, rw, false);

		String v = getValue2Render(context, component);
		if (null == v)
			v="";
		rw.writeText(v, null);
		rw.endElement("textarea");

		if (append) {
			R.decorateFacetComponent(inputText, app, context, rw);
		}

		while (numberOfDivs>0) {
			rw.endElement("div"); 
			numberOfDivs--;
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
