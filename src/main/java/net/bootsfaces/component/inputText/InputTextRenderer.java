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
package net.bootsfaces.component.inputText;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.inputSecret.InputSecret;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

@FacesRenderer(componentFamily = C.BSFCOMPONENT, rendererType = "net.bootsfaces.component.inputText.InputText")
public class InputTextRenderer extends CoreRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		InputText inputText = (InputText) component;

		if (inputText.isDisabled() || inputText.isReadonly()) {
			return;
		}

		decodeBehaviors(context, inputText);

		String clientId = inputText.getClientId(context);
		String name = inputText.getName();
		if (null == name) {
			name = "input_" + clientId;
		}
		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(name);

		if (submittedValue != null) {
			inputText.setSubmittedValue(submittedValue);
		}
		new AJAXRenderer().decode(context, component, name);
	}

	/**
	 * This method is called by the JSF framework to get the type-safe value of the attribute. Do not delete this
	 * method.
	 */
	@Override
	public Object getConvertedValue(FacesContext fc, UIComponent c, Object sval) throws ConverterException {
		Converter cnv = resolveConverter(fc, c);

		if (cnv != null) {
			return cnv.getAsObject(fc, c, (String) sval);
		} else {
			return sval;
		}
	}

	protected Converter resolveConverter(FacesContext context, UIComponent c) {
		if (!(c instanceof ValueHolder)) {
			return null;
		}

		Converter cnv = ((ValueHolder) c).getConverter();

		if (cnv != null) {
			return cnv;
		} else {
			ValueExpression ve = c.getValueExpression("value");

			if (ve != null) {
				Class<?> valType = ve.getType(context.getELContext());

				if (valType != null) {
					return context.getApplication().createConverter(valType);
				}
			}

			return null;
		}
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		InputText inputText = (InputText) component;

		ResponseWriter rw = context.getResponseWriter();
		String clientId = inputText.getClientId();

		String responsiveLabelClass=null;
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
		if (responsiveStyleClass.length() > 0 && responsiveLabelClass == null) {
			rw.startElement("div", component);
			rw.writeAttribute("class", responsiveStyleClass, "class");
		}

		// "Prepend" facet
		UIComponent prep = inputText.getFacet("prepend");
		// "Append" facet
		UIComponent app = inputText.getFacet("append");
		boolean prepend = (prep != null);
		boolean append = (app != null);


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
		rw.writeAttribute("id", clientId, "id"); // clientId
		if (inputText.isInline()) {
			rw.writeAttribute("class", "form-inline", "class");

		} else {
			rw.writeAttribute("class", "form-group", "class");
		}

		if (label != null) {
			rw.startElement("label", component);
			rw.writeAttribute("for", "input_" + clientId, "for"); // "input_" + clientId
			generateErrorAndRequiredClass(inputText, rw, clientId, inputText.getLabelStyleClass(), responsiveLabelClass, "control-label");
			writeAttribute(rw, "style", inputText.getLabelStyle());

			rw.writeText(label, null);
			rw.endElement("label");
		}
		
		if (responsiveStyleClass.length() > 0 && responsiveLabelClass != null) {
			rw.startElement("div", component);
			rw.writeAttribute("class", responsiveStyleClass, "class");
		}

		if (append || prepend) {
			rw.startElement("div", component);
			rw.writeAttribute("class", "input-group", "class");
		}

		if (prepend) {
			R.decorateFacetComponent(inputText, prep, context, rw);
		}

		// Input
		rw.startElement("input", inputText);
		String fieldId = inputText.getFieldId();
		if (null == fieldId) {
			fieldId = "input_" + clientId;
		}
		rw.writeAttribute("id", fieldId, null); // "input_" + clientId
		String name = inputText.getName();
		// System.out.println(name);
		if (null == name) {
			name = "input_" + clientId;
		}
		rw.writeAttribute("name", name, null);
		rw.writeAttribute("type", t, null);

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
		if (inputText.isTags() && (!inputText.isTypeahead())) {
			rw.writeAttribute("data-role", "tagsinput", null);
		}

		String v = getValue2Render(context, component);
		rw.writeAttribute("value", v, null);

		// Render Ajax Capabilities
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), inputText, rw);

		rw.endElement("input");
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
		if (inputText.isTypeahead()) {
			String id = component.getClientId();
			id = id.replace(":", "_"); // we need to escape the id for jQuery
			rw.startElement("script", component);
			String typeaheadname = id + "_typeahead";
			if (inputText.isTags()) {
				String js = "var engine = new Bloodhound({" + //
						"name: '" + typeaheadname + "'," + //
						"local: " + getTypeaheadObjectArray(inputText) + "," + //
						"datumTokenizer: function(d) {" + //
						"  return Bloodhound.tokenizers.whitespace(d.val);" + //
						"}," + //
						"queryTokenizer: Bloodhound.tokenizers.whitespace" + //
						"});";
				js += "$('." + id + "').tagsinput({" + //
						"typeaheadjs: {" + //
						"  name: 'animals'," + //
						"  displayKey: 'val'," + //
						"  valueKey: 'val'," + //
						"  source: engine.ttAdapter()" + //
						"}" + //
						"});";//
				rw.writeText(js, null);

			} else {

				String options = "";
				options = addOption(options, "hint:" + inputText.isTypeaheadHint());
				options = addOption(options, "highlight:" + inputText.isTypeaheadHighlight());
				options = addOption(options, "minLength:" + inputText.getTypeaheadMinLength());
				String options2 = "";
				options2 = addOption(options2, "limit:" + inputText.getTypeaheadLimit());
				options2 = addOption(options2, "name:'" + typeaheadname + "'");
				options2 = addOption(options2, "source: BsF.substringMatcher(" + getTypeaheadValueArray(inputText) + ")");

				rw.writeText("$('." + id + "').typeahead({" + options + "},{" + options2 + "});", null);
			}
			rw.endElement("script");
		}
	}

	private String addOption(String options, String newOption) {
		if (options.length() > 0) {
			options += ",";
		}
		return options + newOption;
	}

	private String getTypeaheadValueArray(InputText inputText) {
		String s = inputText.getTypeaheadValues();
		if (null == s)
			return null;
		s = s.trim();
		if (!s.contains("\'")) {
			String[] parts = s.split(",");
			StringBuilder b = new StringBuilder(s.length() * 2);
			for (String p : parts) {
				if (b.length() > 0) {
					b.append(',');
				}
				b.append('\'');
				b.append(p.trim());
				b.append('\'');
			}
			s = b.toString();

		}
		return "[" + s + "]";
	}

	private String getTypeaheadObjectArray(InputText inputText) {
		String s = inputText.getTypeaheadValues();
		if (null == s)
			return null;
		s = s.trim();
		if (!s.contains("\'")) {
			String[] parts = s.split(",");
			StringBuilder b = new StringBuilder(s.length() * 2);
			for (String p : parts) {
				if (b.length() > 0) {
					b.append(',');
				}
				b.append("{val:");
				b.append('\'');
				b.append(p.trim());
				b.append('\'');
				b.append('}');
			}
			s = b.toString();

		}
		return "[" + s + "]";
	}


	private void generateStyleClass(InputText inputText, ResponseWriter rw) throws IOException {
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
		if (inputText.isTypeahead()) {
			sb.append(" ").append(inputText.getClientId().replace(":","_"));
		}
		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute("class", s, "class");
		}
	}
}
