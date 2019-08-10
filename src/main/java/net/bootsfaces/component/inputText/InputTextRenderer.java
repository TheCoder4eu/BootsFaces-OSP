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
package net.bootsfaces.component.inputText;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import net.bootsfaces.C;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.inputSecret.InputSecret;
import net.bootsfaces.render.CoreInputRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

@FacesRenderer(componentFamily = C.BSFCOMPONENT, rendererType = "net.bootsfaces.component.inputText.InputText")
public class InputTextRenderer extends CoreInputRenderer {
	private static final Logger LOGGER = Logger.getLogger(InputTextRenderer.class.getName());

	@Override
	public void decode(FacesContext context, UIComponent component) {
		decode(context, component, null, null);
	}

    /** 
     * This method is used by RadioButtons and SelectOneMenus to limit the list of legal values. If another value is
     * sent, the input field is considered empty. This comes in useful the the back-end attribute is a primitive
     * type like int, which doesn't support null values.
     * @param context
     * @param component
     * @param legalValues an optional list of legal values. May be null.
     * @param realEventSourceName The real attribute name of the request parameter. By default, BootsFaces guesses the attribute name
     * from the client ID or the name attribute of the input field. However, in some cases such as radio buttons,
     * this detection fails.
     */
	public void decode(FacesContext context, UIComponent component, List<String> legalValues, String realEventSourceName) {
		InputText inputText = (InputText) component;

		if (inputText.isDisabled() || inputText.isReadonly()) {
			return;
		}

		decodeBehaviors(context, inputText);

		String clientId = inputText.getClientId(context);
		String name = inputText.getName();
		if (realEventSourceName == null) {
			realEventSourceName = "input_" + clientId;
		}
		if (null != inputText.getFieldId()) {
			realEventSourceName = inputText.getFieldId();
		}
		
		if (null == name) {
			name = "input_" + clientId;
		}
		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(name);
		if (inputText instanceof InputSecret) {
			if (!((InputSecret)inputText).isRenderValue()) {
				if ("*******".equals(submittedValue)) {
					submittedValue = null;
				}
			}
		}
		
		if (null != legalValues && null != submittedValue) {
			boolean found = false;
			for (String option: legalValues) {
				found |= submittedValue.equals(option);
			}
			if (!found) {
				submittedValue = "";
			}
		}

		if (submittedValue != null) {
			inputText.setSubmittedValue(submittedValue);
		}
		new AJAXRenderer().decode(context, component, realEventSourceName);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		InputText inputText = (InputText) component;
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
			rw.writeAttribute("id", clientId, "id"); // clientId
			clientIdHasBeenRendered=true;
			Tooltip.generateTooltip(context, inputText, rw);
			numberOfDivs++;
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
		boolean visible =  !"hidden".equals(t);

		if (visible) {
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
		}

		String fieldId = inputText.getFieldId();
		if (null == fieldId) {
			fieldId = "input_" + clientId;
		}

		if (visible && label != null) {
			rw.startElement("label", component);
			rw.writeAttribute("for", fieldId, "for"); // "input_" +
																	// clientId
			generateErrorAndRequiredClass(inputText, rw, clientId, inputText.getLabelStyleClass(), responsiveLabelClass,
					"control-label");
			writeAttribute(rw, "style", inputText.getLabelStyle());

			rw.writeText(label, null);
			rw.endElement("label");
		}

		if (visible && responsiveStyleClass.length() > 0 && isHorizontalForm(component)) {
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

		// Input
		rw.startElement("input", inputText);
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
		renderPassThruAttributes(context, component, new String[] { "accesskey", "alt", "dir", "lang", "maxlength", "size", "style",
				"tabindex", "title" });

		String autocomplete = inputText.getAutocomplete();
		if ((autocomplete != null) && (autocomplete.equals("off"))) {
			rw.writeAttribute("autocomplete", "off", null);
		}

		String v = getValue2Render(context, component);
		if (inputText instanceof InputSecret) {
			if (!((InputSecret) inputText).isRenderValue()) {
				if (v != null && v.length() > 0) {
					v = "*******";
				}
			}
		}
		if (v != null && v.length()> 0) {
			rw.writeAttribute("value", v, null);
		}

		// Render Ajax Capabilities
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), inputText, rw, false);

		rw.endElement("input");
		if (append) {
			R.decorateFacetComponent(inputText, app, context, rw);
		}

		while (numberOfDivs>0) {
			rw.endElement("div"); 
			numberOfDivs--;
		}

		// The following lines fix issue #1079 on basic tags (without typeahead).
		// They initialize tagsinput manually and empty duplicated 'name' attribute
		// on generated input (which holds the original HTML id).
		if (inputText.isTags() && (!inputText.isTypeahead())) {
			String id = fieldId; // input id
			id = id.replace(":", "\\\\:"); // escape the id for jQuery
			rw.startElement("script", null);
			String js = "$('#" + id + "').tagsinput();" + //
			            "$('#" + id + "').attr('name','');";
			rw.writeText(js, null);
			rw.endElement("script");
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

				// The following lines fix issue #1079 on tags with typeahead.
				// They empty duplicated 'name' attribute on generated input (which holds the original HTML id).
				String inputId = fieldId; // input id
				inputId = inputId.replace(":", "\\\\:"); // escape the id for jQuery
				js += "$('#" + inputId + "').attr('name','');";

				rw.writeText(js, null);

			} else {

				String options = "";
				options = addOption(options, "hint:" + inputText.isTypeaheadHint());
				options = addOption(options, "highlight:" + inputText.isTypeaheadHighlight());
				options = addOption(options, "minLength:" + inputText.getTypeaheadMinLength());
				String options2 = "";
				options2 = addOption(options2, "limit:" + inputText.getTypeaheadLimit());
				options2 = addOption(options2, "name:'" + typeaheadname + "'");
				options2 = addOption(options2,
						"source: BsF.substringMatcher(" + getTypeaheadValueArray(inputText) + ")");

				rw.writeText("$('." + id + "').typeahead({" + options + "},{" + options2 + "});", null);
			}
			rw.endElement("script");
		}
    encodeMask(context, inputText, fieldId, rw);
	}
  
  /**
   * Add script to enable the input mask. If the mask attribute starts with {@code {}} the value is expected to be a
   * JSON object (and can for example be used to set a regular expression: {@code {regex:'[0-9\u0600-\u06FF]*'}}).
   * 
   * See https://github.com/RobinHerbots/Inputmask.
   * 
   * @param context
   * @param inputText
   * @param fieldId
   * @param rw
   * 
   * @throws IOException
   */
  protected void encodeMask(FacesContext context,
                            InputText inputText,
                            String fieldId,
                            ResponseWriter rw) throws IOException {
    if (inputText.getMask() != null && !inputText.getMask().isEmpty()) {
      rw.startElement("script", inputText);
      rw.writeText("Inputmask(", null);
      if (inputText.getMask().trim().startsWith("{")) {
	rw.writeText(inputText.getMask().trim(), null);
      }
      else {
	rw.writeText(String.format("\"%s\"", inputText.getMask().replace("\"", "\\\"")), null);
      }
      rw.writeText(").mask(document.getElementById(\"", null);
      rw.writeText(fieldId, null);
      rw.writeText("\"));", null);
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
		Object values = inputText.getTypeaheadValues();
		if (null == values)
			return null;
		if (values instanceof String) {
			String s = (String) values;
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
		} else if (values instanceof Iterable) {
			StringBuilder b = new StringBuilder();
			for (Object p : (Iterable<?>)values) {
				if (b.length() > 0) {
					b.append(',');
				}
				b.append('\'');
				if (null != p) {
					b.append(p.toString().trim());
				}
				b.append('\'');
			}
			String s = b.toString();
			return "[" + s + "]";
		} else if (values instanceof Object[]) {
			StringBuilder b = new StringBuilder();
			for (Object p : (Object[])values) {
				if (b.length() > 0) {
					b.append(',');
				}
				b.append('\'');
				if (null != p) {
					b.append(p.toString().trim());
				}
				b.append('\'');
			}
			String s = b.toString();
			return "[" + s + "]";
		} else {
			throw new FacesException("Unexpected data type rendering the type ahead attribute of an inputText field");
		}
	}

	private String getTypeaheadObjectArray(InputText inputText) {
		Object values = inputText.getTypeaheadValues(); 
		if (null == values)
			return null;
		if (values instanceof String) {
			String s = (String) values;
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
		} else if (values instanceof Iterable) {
			StringBuilder b = new StringBuilder();
			for (Object p : (Iterable<?>)values) {
				if (b.length() > 0) {
					b.append(',');
				}
				b.append("{val:");
				b.append('\'');
				if (null != p) {
					b.append(p.toString().trim());
				}
				b.append('\'');
				b.append('}');
			}
			String s = b.toString();
			return "[" + s + "]";
		} else if (values instanceof Object[]) {
			StringBuilder b = new StringBuilder();
			for (Object p : (Object[])values) {
				if (b.length() > 0) {
					b.append(',');
				}
				b.append("{val:");
				b.append('\'');
				if (null != p) {
					b.append(p.toString().trim());
				}
				b.append('\'');
				b.append('}');
			}
			String s = b.toString();
			return "[" + s + "]";
		} else {
			throw new FacesException("Unexpected data type rendering the type ahead attribute of an inputText field");
		}
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
			sb.append(" ").append(inputText.getClientId().replace(":", "_"));
		}
		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute("class", s, "class");
		}
	}
}
