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

package net.bootsfaces.component.selectMultiMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.SelectItemAndComponent;
import net.bootsfaces.component.SelectItemUtils;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.form.Form;
import net.bootsfaces.component.inputText.InputTextRenderer;
import net.bootsfaces.render.CoreInputRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.FacesMessages;

/** This class generates the HTML code of &lt;b:selectMultiMenu /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.selectMultiMenu.SelectMultiMenu")
public class SelectMultiMenuRenderer extends CoreInputRenderer {
	// http://davidstutz.github.io/bootstrap-multiselect/
	private static final Logger LOGGER = Logger.getLogger(InputTextRenderer.class.getName());

	/** Receives the value from the client and sends it to the JSF bean. */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		SelectMultiMenu menu = (SelectMultiMenu) component;
		if (menu.isDisabled() || menu.isReadonly()) {
			return;
		}
		List<String> submittedValues = new ArrayList<String>();
		String clientId = menu.getClientId().replace(":", "-");
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		for (String key : map.keySet()) {
			if (key.startsWith(clientId + ":")) {
				submittedValues.add(map.get(key));
			}
		}
		Converter converter = menu.getConverter();
		List<SelectItemAndComponent> items = SelectItemUtils.collectOptions(context, menu, converter);


		if (!submittedValues.isEmpty()) {
			// check for manipulated input
			String result = null;
			for (String submittedOptionValue : submittedValues) {
				boolean found = false;
				for (int index = 0; index < items.size(); index++) {
					Object currentOption = items.get(index).getSelectItem();
					String currentOptionValueAsString;
					Object currentOptionValue;
					if (currentOption instanceof SelectItem) {
						currentOptionValue = ((SelectItem) currentOption).getValue();
					} else {
						currentOptionValue = ((UISelectItem) currentOption).getItemValue();
					}
					if (currentOptionValue instanceof String) {
						currentOptionValueAsString = (String) currentOptionValue;
					} else if (currentOptionValue instanceof Number) {
						currentOptionValueAsString = currentOptionValue.toString();
					} else
						currentOptionValueAsString = String.valueOf(index);
					if ("".equals(currentOptionValueAsString) && submittedOptionValue.equalsIgnoreCase("on")) {
						// this is a special case which occurs when the value is
						// set to "".
						// In this case, the browser sends "on" instead of the
						// empty string.
						submittedOptionValue = "";
					}
					if (submittedOptionValue.equals(currentOptionValueAsString)) {
						if (null == result)
							result = currentOptionValueAsString;
						else
							result += "," + currentOptionValue;
						found = true;
						break;
					}
				}
				if (!found) {
					FacesMessages.error(clientId, "Invalid data",
							"Couldn't set the value of the b:selectMultiMenu because an option that wasn't defined by the JSF source code was passed.");
					menu.setSubmittedValue(result);
					menu.setValid(false);
					return;
				}
			}
			menu.setSubmittedValue(result);
			menu.setValid(true);
			new AJAXRenderer().decode(context, component, clientId);
			return;
		}

		menu.setSubmittedValue("");
		menu.setValid(true);
		new AJAXRenderer().decode(context, component, clientId);
	}

	/** Generates the HTML code for this component. */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		SelectMultiMenu menu = (SelectMultiMenu) component;

		if (!menu.isRendered()) {
			return;
		}
		ResponseWriter rw = context.getResponseWriter();
		String clientId = menu.getClientId(context).replace(":", "-");
		String span=null;
		boolean clientIdHasBeenRendered=false;
		if (!isHorizontalForm(component)) {
			span = startColSpanDiv(rw, menu, menu.getClientId(context), clientIdHasBeenRendered);
			if (null != span) {
				Tooltip.generateTooltip(context, menu, rw);
				clientIdHasBeenRendered=true;
			}
		}
		rw.startElement("div", menu);
		writeAttribute(rw, "dir", menu.getDir(), "dir");
		if (!clientIdHasBeenRendered) {
			writeAttribute(rw, "id", menu.getClientId(context));
			Tooltip.generateTooltip(context, menu, rw);
			clientIdHasBeenRendered=true;
		}

                rw.writeAttribute("class", getWithFeedback(getInputMode(menu.isInline()), component), "class");
                
		addLabel(rw, clientId + "Inner", menu);

		if (isHorizontalForm(component)) {
			span = startColSpanDiv(rw, menu, menu.getClientId(context), clientIdHasBeenRendered);
		}	
		UIComponent prependingAddOnFacet = menu.getFacet("prepend");
		UIComponent appendingAddOnFacet = menu.getFacet("append");
		final boolean hasAddon = startInputGroupForAddOn(rw, (prependingAddOnFacet != null),
				(appendingAddOnFacet != null), menu);

		addPrependingAddOnToInputGroup(context, rw, prependingAddOnFacet, (prependingAddOnFacet != null), menu);
		renderSelectTag(context, rw, clientId + "Inner", clientId, menu);
		addAppendingAddOnToInputGroup(context, rw, appendingAddOnFacet, (appendingAddOnFacet != null), menu);

		closeInputGroupForAddOn(rw, hasAddon);
		rw.endElement("div"); // form-group
		closeColSpanDiv(rw, span);
		Tooltip.activateTooltips(context, menu);

		String options = "";
		int maxHeight = menu.getMaxHeight();
		if (maxHeight > 0) {
			options += "," + "maxHeight:" + String.valueOf(maxHeight);
		}
		String nonSelectedText = menu.getNonSelectedText();
		if (nonSelectedText != null) {
			options += "," + "nonSelectedText:'" + nonSelectedText + "'";
		}
		String nSelectedText = menu.getNSelectedText();
		nSelectedText = (String) menu.getAttributes().get("nSelectedText"); // workaround
																			// -
																			// the
																			// regular
																			// getter
																			// always
																			// yields
																			// null
		if (nSelectedText != null) {
			options += "," + "nSelectedText:'" + nSelectedText + "'";
		}
		String allSelectedText = menu.getAllSelectedText();
		if (allSelectedText != null) {
			options += "," + "allSelectedText:'" + allSelectedText + "'";
		}
		int numberDisplayed = menu.getNumberDisplayed();
		if (numberDisplayed > 0) {
			options += "," + "numberDisplayed:" + String.valueOf(numberDisplayed);
		}

		if (menu.isIncludeSelectAllOption()) {
			options += "," + "includeSelectAllOption:" + "true";
		}

		String selectAllText = menu.getSelectAllText();
		if (selectAllText != null) {
			options += "," + "selectAllText:'" + selectAllText + "'";
		}

		if (menu.isEnableFiltering()) {
			options += "," + "enableFiltering:true";
		}

		String filterPlaceholder = menu.getFilterPlaceholder();
		if (filterPlaceholder != null) {
			options += "," + "filterPlaceholder:'" + filterPlaceholder + "'";
		}

		boolean enableCaseInsensitiveFiltering = menu.isEnableCaseInsensitiveFiltering();
		if (enableCaseInsensitiveFiltering) {
			options += "," + "enableCaseInsensitiveFiltering:" + "true";
		}

		boolean disableIfEmpty = menu.isDisableIfEmpty();
		if (disableIfEmpty) {
			options += "," + "disableIfEmpty:" + "true";
		}

		boolean dropRight = menu.isDropRight();
		if (dropRight) {
			options += "," + "dropRight:" + "true";
		}

		String buttonClass = menu.getButtonClass();
		if (buttonClass != null) {
			options += "," + "buttonClass:'" + buttonClass + "'";
		}

		String buttonContainer = menu.getButtonContainer();
		if (buttonContainer != null) {
			options += "," + "buttonContainer:'" + buttonClass + "'";
			if (menu.getStyleClass() != null)
				throw new FacesException(
						"b:selectMultiMenu can't process the styleClass attribute if the buttonContainer attribute is set.");
		} else {
			String styleClass = menu.getStyleClass();
			String blockStyle = "display:block";

			if (menu.isInline()) {
				blockStyle = "display:inline-block";
			} else {
				UIForm currentForm = AJAXRenderer.getSurroundingForm((UIComponent)component, true);
				if (null != currentForm && currentForm instanceof Form) {
					if (((Form) currentForm).isInline()) {
						blockStyle = "display:inline-block";
					}
				}
			}
			if (menu.getStyle()!=null) {
				blockStyle += ";" + menu.getStyle();
			}

			if (styleClass != null) {
				options += "," + "buttonContainer:'<div class=\"" + styleClass + "\"  style=\"" + blockStyle + "\"/>'";
			} else {
				options += "," + "buttonContainer:'<div class=\"btn-group\" style=\"" + blockStyle + "\" />'";
			}
		}

		int buttonWidth = menu.getButtonWidth();
		if (buttonWidth > 0) {
			options += "," + "buttonWidth:'" + buttonWidth + "px'";
		}
		
		for (String event: menu.getJQueryEvents().keySet()) {
			String optionalParameterList="event";
			if (menu.getJQueryEventParameterLists()!=null) {
				if (menu.getJQueryEventParameterLists().get(event) != null) {
					optionalParameterList = menu.getJQueryEventParameterLists().get(event);
				}
			}
			StringBuilder code = new StringBuilder();
			AJAXRenderer.generateAJAXCallForASingleEvent(context, menu,
					null, code, null, null, true, 
					event, null, optionalParameterList);
			if (code.length()>0) {
				String result = menu.getJQueryEvents().get(event);
				result += ":";
				String realCode = code.toString().replace(".callAjax(this", ".callAjax(document.getElementById('" + clientId + "Inner')" );
				if (!realCode.startsWith("function")) {
					result += "function(" + optionalParameterList + ")";
					result += "{";
					result += realCode;
					result += "}";
				} else {  // backward compatibility to BootsFaces 1.0.0 and earlier
					result += realCode;
				}
				options += "," + result;
			}
		}


		if (options.length() > 0) {
			options = "{" + options.substring(1, options.length()) + "}";
		}
		String js = "$(document).ready(function() {$('#" + clientId + "Inner').multiselect(" + options + ");});\n";
		context.getResponseWriter().write("<script type='text/javascript'>\r\n" + js + "\r\n</script>");
	}

	/**
	 * Renders components added seamlessly behind the input field.
	 *
	 * @param context
	 *            the FacesContext
	 * @param rw
	 *            the response writer
	 * @param appendingAddOnFacet
	 *            optional facet behind the field. Can be null.
	 * @param hasAppendingAddOn
	 *            optional facet in front of the field. Can be null.
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void addAppendingAddOnToInputGroup(FacesContext context, ResponseWriter rw,
			UIComponent appendingAddOnFacet, boolean hasAppendingAddOn, SelectMultiMenu menu) throws IOException {
		if (hasAppendingAddOn) {
			if (appendingAddOnFacet.getClass().getName().endsWith("Button") || (appendingAddOnFacet.getChildCount() > 0
					&& appendingAddOnFacet.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement("div", menu);
				rw.writeAttribute("class", "input-group-btn", "class");
				appendingAddOnFacet.encodeAll(context);
				rw.endElement("div");
			} else {
				rw.startElement("span", menu);
				rw.writeAttribute("class", "input-group-addon", "class");
				appendingAddOnFacet.encodeAll(context);
				rw.endElement("span");
			}
		}
	}

	/**
	 * Renders the optional label. This method is protected in order to allow
	 * third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param clientId
	 *            the id used by the label to refernce the input field
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void addLabel(ResponseWriter rw, String clientId, SelectMultiMenu menu) throws IOException {
		String label = menu.getLabel();
		if (!menu.isRenderLabel()) {
            label = null;
        }
		if (label != null) {
			rw.startElement("label", menu);
			rw.writeAttribute("for", clientId, "for");
                        
                        generateErrorAndRequiredClass(menu, rw, clientId, menu.getLabelStyleClass(), Responsive.getResponsiveLabelClass(menu), "control-label");
                        
			//generateErrorAndRequiredClassForLabels(menu, rw, clientId, "control-label " + menu.getLabelStyleClass());
			writeAttribute(rw, "style", menu.getLabelStyle());
			rw.writeText(label, null);
			rw.endElement("label");
		}
	}

	/**
	 * Renders components added seamlessly in front of the input field.
	 *
	 * @param context
	 *            the FacesContext
	 * @param rw
	 *            the response writer
	 * @param prependingAddOnFacet
	 * @param hasPrependingAddOn
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void addPrependingAddOnToInputGroup(FacesContext context, ResponseWriter rw,
			UIComponent prependingAddOnFacet, boolean hasPrependingAddOn, SelectMultiMenu menu) throws IOException {
		if (hasPrependingAddOn) {
			if (prependingAddOnFacet.getClass().getName().endsWith("Button")
					|| (prependingAddOnFacet.getChildCount() > 0
							&& prependingAddOnFacet.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement("div", menu);
				rw.writeAttribute("class", "input-group-btn", "class");
				prependingAddOnFacet.encodeAll(context);
				rw.endElement("div");
			} else if (prependingAddOnFacet instanceof HtmlOutputText) {
				HtmlOutputText out = (HtmlOutputText) prependingAddOnFacet;

				String sc = out.getStyleClass();
				if (sc == null)
					sc = "input-group-addon";
				else
					sc = sc + " " + "input-group-addon";
				out.setStyleClass(sc);
				prependingAddOnFacet.encodeAll(context);
			} else {
				rw.startElement("span", menu);
				rw.writeAttribute("class", "input-group-addon", "class");
				prependingAddOnFacet.encodeAll(context);
				rw.endElement("span");
			}
		}
	}

	/**
	 * Terminate the column span div (if there's one). This method is protected
	 * in order to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param span
	 *            the width of the components (in BS columns).
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void closeColSpanDiv(ResponseWriter rw, String span) throws IOException {
		if (span != null && span.trim().length() > 0) {
			rw.endElement("div");
		}
	}

	/**
	 * Terminates the input field group (if there's one). This method is
	 * protected in order to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param hasAddon
	 *            true if there is an add-on in front of or behind the input
	 *            field
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void closeInputGroupForAddOn(ResponseWriter rw, final boolean hasAddon) throws IOException {
		if (hasAddon) {
			rw.endElement("div");
		}
	}

	/**
	 * Algorithm works as follows; - If it's an input component, submitted value
	 * is checked first since it'd be the value to be used in case validation
	 * errors terminates jsf lifecycle - Finally the value of the component is
	 * retrieved from backing bean and if there's a converter, converted value
	 * is returned
	 *
	 * @param context
	 *            FacesContext instance
	 * @return End text
	 */
	public Object getValue2Render(FacesContext context, SelectMultiMenu menu) {
		Object sv = menu.getSubmittedValue();
		if (sv != null) {
			return sv;
		}

		Object val = menu.getValue();
		if (val != null) {
			Converter converter = menu.getConverter();

			if (converter != null)
				return converter.getAsString(context, menu, val);
			else
				return val;

		} else {
			// component is a value holder but has no value
			return null;
		}
	}

	/** Renders the select tag. */
	protected void renderSelectTag(FacesContext context, ResponseWriter rw, String clientId, String name,
			SelectMultiMenu menu) throws IOException {
		renderSelectTag(rw, menu);
		renderSelectTagAttributes(rw, clientId, name, menu);
        AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), menu, rw, false);
		Object selectedOption = getValue2Render(context, menu);
		String[] optionList;
		if (selectedOption == null) {
			optionList = new String[0];
		} else if (!(selectedOption instanceof String)) {
			throw new FacesException("SelectMultiMenu only works with Strings!");
		} else {
			optionList = ((String) selectedOption).split(",");
		}
		renderOptions(context, rw, optionList, menu);

		renderInputTagEnd(rw);
	}

	/**
	 * Copied from the InputRenderer class of PrimeFaces 5.1.
	 *
	 * @param context
	 * @param uiSelectItems
	 * @param value
	 * @param label
	 * @return
	 */
	protected SelectItem createSelectItem(FacesContext context, UISelectItems uiSelectItems, Object value,
			Object label) {
		String var = (String) uiSelectItems.getAttributes().get("var");
		Map<String, Object> attrs = uiSelectItems.getAttributes();
		Map<String, Object> requestMap = context.getExternalContext().getRequestMap();

		if (var != null) {
			requestMap.put(var, value);
		}

		Object itemLabelValue = attrs.get("itemLabel");
		Object itemValue = attrs.get("itemValue");
		String description = (String) attrs.get("itemDescription");
		Object itemDisabled = attrs.get("itemDisabled");
		Object itemEscaped = attrs.get("itemLabelEscaped");
		Object noSelection = attrs.get("noSelectionOption");

		if (itemValue == null) {
			itemValue = value;
		}

		if (itemLabelValue == null) {
			itemLabelValue = label;
		}

		String itemLabel = itemLabelValue == null ? String.valueOf(value) : String.valueOf(itemLabelValue);
		boolean disabled = itemDisabled == null ? false : Boolean.valueOf(itemDisabled.toString());
		boolean escaped = itemEscaped == null ? false : Boolean.valueOf(itemEscaped.toString());
		boolean noSelectionOption = noSelection == null ? false : Boolean.valueOf(noSelection.toString());

		if (var != null) {
			requestMap.remove(var);
		}

		return new SelectItem(itemValue, itemLabel, description, disabled, escaped, noSelectionOption);
	}

	/**
	 * Parts of this class are an adapted version of
	 * InputRenderer#getSelectItems() of PrimeFaces 5.1.
	 *
	 * @param rw
	 * @param selectedOption
	 * @throws IOException
	 */
	protected void renderOptions(FacesContext context, ResponseWriter rw, String[] selectedOption, SelectMultiMenu menu)
			throws IOException {
		Converter converter = menu.getConverter();
		List<SelectItemAndComponent> items = SelectItemUtils.collectOptions(context, menu, converter);

		for (int index = 0; index < items.size(); index++) {
			Object option = items.get(index).getSelectItem();
			if (option instanceof SelectItem) {
				renderOption(rw, (SelectItem) option, selectedOption, index);
			} else {
				renderOption(rw, (UISelectItem) option, selectedOption, index);
			}
		}
	}


	/**
	 * Renders a single &lt;option&gt; tag. For some reason,
	 * <code>SelectItem</code> and <code>UISelectItem</code> don't share a
	 * common interface, so this method is repeated twice.
	 *
	 * @param rw
	 *            The response writer
	 * @param selectItem
	 *            The current SelectItem
	 * @param selectedOption
	 *            the currently selected option
	 * @throws IOException
	 *             thrown if something's wrong with the response writer
	 */
	protected void renderOption(ResponseWriter rw, SelectItem selectItem, String[] selectedOption, int index)
			throws IOException {

		String itemLabel = selectItem.getLabel();
		final String description = selectItem.getDescription();
		final Object itemValue = selectItem.getValue();

		renderOption(rw, selectedOption, index, itemLabel, description, itemValue);
	}

	/**
	 * Renders a single &lt;option&gt; tag. For some reason,
	 * <code>SelectItem</code> and <code>UISelectItem</code> don't share a
	 * common interface, so this method is repeated twice.
	 *
	 * @param rw
	 *            The response writer
	 * @param selectItem
	 *            The current SelectItem
	 * @param selectedOption
	 *            the currently selected option
	 * @throws IOException
	 *             thrown if something's wrong with the response writer
	 */
	protected void renderOption(ResponseWriter rw, UISelectItem selectItem, String[] selectedOption, int index)
			throws IOException {

		String itemLabel = selectItem.getItemLabel();
		final String itemDescription = selectItem.getItemDescription();
		final Object itemValue = selectItem.getItemValue();

		boolean isItemLabelBlank = itemLabel == null || itemLabel.trim().length() == 0;
		itemLabel = isItemLabelBlank ? "&nbsp;" : itemLabel;

		renderOption(rw, selectedOption, index, itemLabel, itemDescription, itemValue);
	}

	private void renderOption(ResponseWriter rw, String[] selectedOption, int index, String itemLabel,
			final String description, final Object itemValue) throws IOException {
		boolean isItemLabelBlank = itemLabel == null || itemLabel.trim().length() == 0;
		itemLabel = isItemLabelBlank ? "&nbsp;" : itemLabel;

		rw.startElement("option", null);
		rw.writeAttribute("data-label", itemLabel, null);
		if (description != null) {
			rw.writeAttribute("title", description, null);
		}
		if (itemValue != null) {
			String value;
			if (itemValue instanceof String) {
				value = (String) itemValue;
			} else if (itemValue instanceof Number) {
				Integer intValue = ((Integer) itemValue);
				value = intValue == null ? null : intValue.toString();
			} else
				value = String.valueOf(index);
			rw.writeAttribute("value", value, "value");
			if (isInList(value, selectedOption)) {
				rw.writeAttribute("selected", "true", "selected");
			}
		} else if (isInList(itemLabel, selectedOption)) {
			rw.writeAttribute("selected", "true", "selected");
		}

		rw.write(itemLabel);

		rw.endElement("option");
	}

	private boolean isInList(String value, String[] options) {
		if (null == options)
			return false;
		for (String s : options) {
			if (s == null) {
				if (value == null)
					return true;
			} else if (s.equals(value))
				return true;
		}
		for (String s : options) {
			if (s != null && s.trim().equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Renders the start of the input tag. This method is protected in order to
	 * allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderSelectTag(ResponseWriter rw, SelectMultiMenu menu) throws IOException {
		rw.startElement("select", menu);

	}

	/**
	 * Renders the attributes of the input tag. This method is protected in
	 * order to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param clientId
	 *            the client id (used both as id and name)
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderSelectTagAttributes(ResponseWriter rw, String clientId, String name, SelectMultiMenu menu)
			throws IOException {
		rw.writeAttribute("id", clientId, null);
		rw.writeAttribute("name", name, null);

		StringBuilder sb;
		String s;
		sb = new StringBuilder(20); // optimize int
		sb.append("form-control");
		sb.append(" select-multi-menu");
		String fsize = menu.getFieldSize();

		if (fsize != null) {
			sb.append(" input-").append(fsize);
		}
		String cssClass = menu.getStyleClass();
		if (cssClass != null) {
			sb.append(" ").append(cssClass);
		}
		sb.append(" ").append(getErrorAndRequiredClass(menu, clientId));

		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute("class", s, "class");
		}
		
		if (menu.isDisabled()) {
			rw.writeAttribute("disabled", "disabled", null);
		}
		if (menu.isReadonly()) {
			rw.writeAttribute("readonly", "readonly", null);
		}

		if (!menu.isRadiobuttons()) {
			rw.writeAttribute("multiple", "multiple", null);
		}

		// Encode attributes (HTML 4 pass-through + DHTML)
		R.encodeHTML4DHTMLAttrs(rw, menu.getAttributes(), new String[] { "accesskey", "alt", "lang", "style", "tabindex", "title" });
	}

	/**
	 * Closes the input tag. This method is protected in order to allow
	 * third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTagEnd(ResponseWriter rw) throws IOException {
		rw.endElement("select");
	}

	/**
	 * Start the column span div (if there's one). This method is protected in
	 * order to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected String startColSpanDiv(ResponseWriter rw, SelectMultiMenu menu, String clientId, boolean clientIdHasBeenRendered) throws IOException {
		String clazz = Responsive.getResponsiveStyleClass(menu, false);
		if (clazz != null && clazz.trim().length() > 0) {
			clazz=clazz.trim();
			rw.startElement("div", menu);
			rw.writeAttribute("class", clazz, "class");
			if (!clientIdHasBeenRendered) {
				rw.writeAttribute("id", clientId, "id");
			}
			return clazz;
		}
		return null;
	}

	/**
	 * Starts the input field group (if needed to display a component seamlessly
	 * in front of or behind the input field). This method is protected in order
	 * to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param hasPrependingAddOn
	 * @param hasAppendingAddOn
	 * @return true if there is an add-on in front of or behind the input field
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected boolean startInputGroupForAddOn(ResponseWriter rw, boolean hasPrependingAddOn, boolean hasAppendingAddOn,
			SelectMultiMenu menu) throws IOException {
		final boolean hasAddon = hasAppendingAddOn || hasPrependingAddOn;
		if (hasAddon) {
			rw.startElement("div", menu);
			rw.writeAttribute("class", "input-group", "class");
		}
		return hasAddon;
	}

}
