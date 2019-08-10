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

package net.bootsfaces.component.selectOneMenu;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.SelectItemAndComponent;
import net.bootsfaces.component.SelectItemUtils;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.inputText.InputTextRenderer;
import net.bootsfaces.render.CoreInputRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:SelectOneMenu /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.selectOneMenu.SelectOneMenu")
public class SelectOneMenuRenderer extends CoreInputRenderer {
	private static final Logger LOGGER = Logger.getLogger(InputTextRenderer.class.getName());

	/** Receives the value from the client and sends it to the JSF bean. */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		SelectOneMenu menu = (SelectOneMenu) component;
		if (menu.isDisabled() || menu.isReadonly()) {
			return;
		}
		String outerClientId = menu.getClientId(context);
		String clientId = outerClientId + "Inner";
		String submittedOptionValue = (String) context.getExternalContext().getRequestParameterMap().get(clientId);

		Converter converter = menu.getConverter();
		if (null == converter) {
			converter = findImplicitConverter(context, component);
		}
		List<SelectItemAndComponent> items = SelectItemUtils.collectOptions(context, menu, converter);

		if (null != submittedOptionValue) {
			for (int index = 0; index < items.size(); index++) {
				Object currentOption = items.get(index).getSelectItem();
				String currentOptionValueAsString;
				Object currentOptionValue = null;
				if (currentOption instanceof SelectItem) {
					if (!((SelectItem) currentOption).isDisabled()) {
						currentOptionValue = ((SelectItem) currentOption).getValue();
					}
				}
				if (currentOptionValue instanceof String) {
					currentOptionValueAsString = (String) currentOptionValue;
				} else if (null != converter) {
					currentOptionValueAsString = converter.getAsString(context, component, currentOptionValue);
				} else if (currentOptionValue != null) {
					currentOptionValueAsString = String.valueOf(index);
				} else {
					currentOptionValueAsString = ""; // null values are submitted as empty strings
				}
				if (submittedOptionValue.equals(currentOptionValueAsString)) {
					Object submittedValue = null;
					if (currentOptionValue == null) {
						submittedValue = null;
					} else {
						submittedValue = null != converter ? currentOptionValueAsString : currentOptionValue;
					}
					menu.setSubmittedValue(submittedValue);
					menu.setValid(true);
					
					menu.validateValue(context, submittedValue);
					new AJAXRenderer().decode(context, component, clientId);
					if (menu.isValid()) {
						if (currentOptionValue == null)  {
							menu.setLocalValueSet(true);
						}
					}
					return;
				}
			}
			menu.validateValue(context, null);
			menu.setSubmittedValue(null);
			menu.setValid(false);
			return;
		}

		menu.setValid(true);
		menu.validateValue(context, submittedOptionValue);
		menu.setSubmittedValue(submittedOptionValue);
		new AJAXRenderer().decode(context, component, clientId);
	}

	/** Generates the HTML code for this component. */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		SelectOneMenu menu = (SelectOneMenu) component;

		if (!menu.isRendered()) {
			return;
		}
		ResponseWriter rw = context.getResponseWriter();
		String outerClientId = menu.getClientId(context);
		boolean clientIdHasBeenRendered = false;
		String clientId = outerClientId + "Inner";
		String span = null;
		if (!isHorizontalForm(component)) {
			span = startColSpanDiv(rw, menu, outerClientId);
			if (null != span) {
				Tooltip.generateTooltip(context, menu, rw);
				clientIdHasBeenRendered = true;
			}
		}
		rw.startElement("div", menu);

		rw.writeAttribute("class", getWithFeedback(getInputMode(menu.isInline()), component), "class");
		if (!clientIdHasBeenRendered) {
			rw.writeAttribute("id", outerClientId, "id");
			Tooltip.generateTooltip(context, menu, rw);
		}
		writeAttribute(rw, "dir", menu.getDir(), "dir");

		addLabel(rw, clientId, menu, outerClientId);
		if (isHorizontalForm(component)) {
			span = startColSpanDiv(rw, menu, null);
		}

		UIComponent prependingAddOnFacet = menu.getFacet("prepend");
		UIComponent appendingAddOnFacet = menu.getFacet("append");
		final boolean hasAddon = startInputGroupForAddOn(rw, (prependingAddOnFacet != null),
				(appendingAddOnFacet != null), menu);

		addPrependingAddOnToInputGroup(context, rw, prependingAddOnFacet, (prependingAddOnFacet != null), menu);
		renderSelectTag(context, rw, clientId, menu, outerClientId);
		addAppendingAddOnToInputGroup(context, rw, appendingAddOnFacet, (appendingAddOnFacet != null), menu);

		closeInputGroupForAddOn(rw, hasAddon);
		rw.endElement("div"); // form-group
		closeColSpanDiv(rw, span);
		Tooltip.activateTooltips(context, menu);
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
			UIComponent appendingAddOnFacet, boolean hasAppendingAddOn, SelectOneMenu menu) throws IOException {
		if (hasAppendingAddOn) {
			R.decorateFacetComponent(menu, appendingAddOnFacet, context, rw);
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
	protected void addLabel(ResponseWriter rw, String clientId, SelectOneMenu menu, String outerClientId)
			throws IOException {
		String label = menu.getLabel();
		{
			if (!menu.isRenderLabel()) {
				label = null;
			}
		}
		if (label != null) {
			rw.startElement("label", menu);
			rw.writeAttribute("for", clientId, "for");
			generateErrorAndRequiredClass(menu, rw, outerClientId, menu.getLabelStyleClass(),
					Responsive.getResponsiveLabelClass(menu), "control-label");
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
			UIComponent prependingAddOnFacet, boolean hasPrependingAddOn, SelectOneMenu menu) throws IOException {
		if (hasPrependingAddOn) {
			R.decorateFacetComponent(menu, prependingAddOnFacet, context, rw);
		}
	}

	/**
	 * Terminate the column span div (if there's one). This method is protected in
	 * order to allow third-party frameworks to derive from it.
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
	 * Terminates the input field group (if there's one). This method is protected
	 * in order to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param hasAddon
	 *            true if there is an add-on in front of or behind the input field
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void closeInputGroupForAddOn(ResponseWriter rw, final boolean hasAddon) throws IOException {
		if (hasAddon) {
			rw.endElement("div");
		}
	}

	/** Renders the select tag. */
	protected void renderSelectTag(FacesContext context, ResponseWriter rw, String clientId, SelectOneMenu menu,
			String outerClientId) throws IOException {
		renderSelectTag(rw, menu);
		renderSelectTagAttributes(rw, clientId, menu, outerClientId);
		renderOptions(context, rw, menu);

		renderInputTagEnd(rw);
		renderJQueryAfterComponent(rw, clientId, menu);
	}

	/**
	 * render a jquery javascript block after the component if necessary
	 * 
	 * @param rw
	 * @param clientId 
	 * @param menu
	 * @throws IOException 
	 */
	private void renderJQueryAfterComponent(ResponseWriter rw, String clientId, SelectOneMenu menu) throws IOException {
		Boolean select2 = menu.isSelect2();
		if (select2 != null && select2) {
			rw.startElement("script", menu);
			rw.writeAttribute("type", "text/javascript", "script");
			
			StringBuilder buf = new StringBuilder("$(document).ready(function(){");
			buf.append("\n");
			// jquery selector for the ID of the select component
			buf.append("  $(\"[id='").append(clientId).append("']\")");
			// select2 command to enable filtering
			buf.append(".select2();");
			buf.append("\n");
			buf.append("});");
			
			rw.writeText(buf.toString(), "script");
			rw.endElement("script");
		}
	}

	/**
	 * Compare current selection with items, if there is any element selected
	 * 
	 * @param context
	 * @param items
	 * @param converter
	 * @return
	 */
	private SelectItemAndComponent determineSelectedItem(FacesContext context, SelectOneMenu menu, List<SelectItemAndComponent> items, Converter converter) {
		Object submittedValue = menu.getSubmittedValue();
		Object selectedOption;
		if (submittedValue != null) {
			selectedOption = submittedValue;
		} else {
			selectedOption = menu.getValue();
		}

		for (int index = 0; index < items.size(); index++) {
			SelectItemAndComponent option = items.get(index);
			if (option.getSelectItem().isNoSelectionOption()) continue;
			
			Object itemValue = option.getSelectItem().getValue();
			String itemValueAsString = getOptionAsString(context, menu, itemValue, converter);

			Object optionValue;
			if (submittedValue != null) {
				optionValue = itemValueAsString;
			} else {
				optionValue = itemValue;
			}

			if (itemValue != null) {
				if (isSelected(context, menu, selectedOption, optionValue, converter)) {
					return option;
				}
			} 
		}
		return null;
	}
	
	/**
	 * Parts of this class are an adapted version of InputRenderer#getSelectItems()
	 * of PrimeFaces 5.1.
	 *
	 * @param rw
	 * @throws IOException
	 */
	protected void renderOptions(FacesContext context, ResponseWriter rw, SelectOneMenu menu) throws IOException {
		Converter converter = menu.getConverter();
		List<SelectItemAndComponent> items = SelectItemUtils.collectOptions(context, menu, converter);
		
		SelectItemAndComponent selection = determineSelectedItem(context, menu, items, converter);

		for (int index = 0; index < items.size(); index++) {
			SelectItemAndComponent option = items.get(index);

			if (option.getSelectItem().isNoSelectionOption() && 
					menu.isHideNoSelectionOption() && selection != null)
				continue;
			
			renderOption(context, menu, rw, (option.getSelectItem()), index, option.getComponent(), 
					option == selection || (selection == null && option.getSelectItem().isNoSelectionOption()));
		}
	}

	/**
	 * Renders a single &lt;option&gt; tag. For some reason, <code>SelectItem</code>
	 * and <code>UISelectItem</code> don't share a common interface, so this method
	 * is repeated twice.
	 *
	 * @param rw
	 *            The response writer
	 * @param selectItem
	 *            The current SelectItem
	 * @throws IOException
	 *             thrown if something's wrong with the response writer
	 */
	protected void renderOption(FacesContext context, SelectOneMenu menu, ResponseWriter rw, SelectItem selectItem,
			int index, UIComponent itemComponent, boolean isSelected) throws IOException {

		String itemLabel = selectItem.getLabel();
		final String description = selectItem.getDescription();
		final Object itemValue = selectItem.getValue();

		renderOption(context, menu, rw, index, itemLabel, description, itemValue, selectItem.isDisabled(),
				selectItem.isEscape(), itemComponent, isSelected);
	}

	private Converter findImplicitConverter(FacesContext context, UIComponent component) {
		ValueExpression ve = component.getValueExpression("value");

		if (ve != null) {
			Class<?> valueType = ve.getType(context.getELContext());

			if (valueType != null)
				return context.getApplication().createConverter(valueType);
		}

		return null;
	}

	private String getOptionAsString(FacesContext context, SelectOneMenu menu, Object value, Converter converter)
			throws ConverterException {

		if (converter == null) {
			if (value == null) {
				return "";
			} else if (value instanceof String) {
				return (String) value;
			} else {
				Converter implicitConverter = findImplicitConverter(context, menu);

				return implicitConverter == null ? value.toString()
						: implicitConverter.getAsString(context, menu, value);
			}
		} else {
			return converter.getAsString(context, menu, value);
		}
	}

	private Object coerceToModelType(FacesContext ctx, Object value, Class<? extends Object> itemValueType) {
		Object newValue;
		try {
			ExpressionFactory ef = ctx.getApplication().getExpressionFactory();
			newValue = ef.coerceToType(value, itemValueType);
		} catch (ELException ele) {
			newValue = value;
		} catch (IllegalArgumentException iae) {
			newValue = value;
		}

		return newValue;
	}

	private boolean isSelected(FacesContext context, SelectOneMenu menu, Object value, Object itemValue,
			Converter converter) {
		if (itemValue == null && value == null) {
			return true;
		}

		if (value != null) {
			Object compareValue;
			if (converter == null) {
				compareValue = coerceToModelType(context, itemValue, value.getClass());
			} else {
				compareValue = itemValue;

				if (compareValue instanceof String && !(value instanceof String)) {
					compareValue = converter.getAsObject(context, menu, (String) compareValue);
				}
			}

			if (value.equals(compareValue)) {
				return true;
			}

		}
		return false;
	}

	private void renderOption(FacesContext context, SelectOneMenu menu, ResponseWriter rw, int index, String itemLabel,
			final String description, final Object itemValue, boolean isDisabledOption, boolean isEscape,
			UIComponent itemComponent, boolean isSelected) throws IOException {

		Converter converter = menu.getConverter();
		if (converter == null && itemValue != null && (!(itemValue instanceof String))) {
			converter = findImplicitConverter(context, menu);
		}
		String itemValueAsString = getOptionAsString(context, menu, itemValue, converter);

		boolean isItemLabelBlank = itemLabel == null || itemLabel.trim().isEmpty();
		itemLabel = isItemLabelBlank ? itemValueAsString : itemLabel;

		rw.startElement("option", itemComponent);
		rw.writeAttribute("data-label", itemLabel, null);
		if (description != null) {
			rw.writeAttribute("title", description, null);
		}

		if (itemValue != null) {
			String value;
			if (null != converter) {
				value = converter.getAsString(context, menu, itemValue);
			}
			else if (itemValue instanceof String) {
				value = (String) itemValue;
			} else {
				value = String.valueOf(index); // this is used for objects
			}
			rw.writeAttribute("value", value, "value");
		}
		else {
			rw.writeAttribute("value", "", "value");
		}
		if (isSelected) {
			rw.writeAttribute("selected", "true", "selected");
		}

		if (isDisabledOption)
			rw.writeAttribute("disabled", "disabled", "disabled");

		if (isEscape && !isItemLabelBlank) {
			rw.writeText(itemLabel, null);
		} else {
			rw.write(itemLabel);
		}

		rw.endElement("option");
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
	protected void renderSelectTag(ResponseWriter rw, SelectOneMenu menu) throws IOException {
		rw.write("\n");
		rw.startElement("select", menu);
	}

	/**
	 * Renders the attributes of the input tag. This method is protected in order to
	 * allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param clientId
	 *            the client id (used both as id and name)
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderSelectTagAttributes(ResponseWriter rw, String clientId, SelectOneMenu menu,
			String outerClientId) throws IOException {
		rw.writeAttribute("id", clientId, null);
		// Tooltip.generateTooltip(FacesContext.getCurrentInstance(), menu, rw);
		rw.writeAttribute("name", clientId, null);

		StringBuilder sb;
		String s;
		sb = new StringBuilder(20); // optimize int
		sb.append("form-control");
		
		Boolean select2 = menu.isSelect2();

		if (select2 != null && select2) {
			sb.append(" select2style");
		}
		
		String fsize = menu.getFieldSize();

		if (fsize != null) {
			sb.append(" input-").append(fsize);
		}
		String cssClass = menu.getStyleClass();
		if (cssClass != null) {
			sb.append(" ").append(cssClass);
		}
		sb.append(" ").append(getErrorAndRequiredClass(menu, outerClientId));

		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute("class", s, "class");
		}

		if (menu.isDisabled() || menu.isReadonly()) {
			rw.writeAttribute("disabled", "disabled", null);
		}
		if (menu.isReadonly()) {
			rw.writeAttribute("readonly", "readonly", null);
		}

		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), menu, rw, false);

		// Encode attributes (HTML 4 pass-through + DHTML)
		R.encodeHTML4DHTMLAttrs(rw, menu.getAttributes(), new String[] { "accesskey", "alt", "lang", "style", "tabindex", "title" });
	}

	/**
	 * Closes the input tag. This method is protected in order to allow third-party
	 * frameworks to derive from it.
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
	 * Start the column span div (if there's one). This method is protected in order
	 * to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected String startColSpanDiv(ResponseWriter rw, SelectOneMenu menu, String clientId) throws IOException {
		String clazz = Responsive.getResponsiveStyleClass(menu, false);
		if (clazz != null && clazz.trim().length() > 0) {
			clazz = clazz.trim();
			rw.startElement("div", menu);
			rw.writeAttribute("class", clazz, "class");
			rw.writeAttribute("id", clientId, "id");
			return clazz;
		}

		return null;
	}

	/**
	 * Starts the input field group (if needed to display a component seamlessly in
	 * front of or behind the input field). This method is protected in order to
	 * allow third-party frameworks to derive from it.
	 *
	 * @param hasAppendingAddOn
	 *
	 * @param rw
	 *            the response writer
	 * @param hasPrependingAddOn
	 * @return true if there is an add-on in front of or behind the input field
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected boolean startInputGroupForAddOn(ResponseWriter rw, boolean hasPrependingAddOn, boolean hasAppendingAddOn,
			SelectOneMenu menu) throws IOException {
		final boolean hasAddon = hasAppendingAddOn || hasPrependingAddOn;
		if (hasAddon) {
			rw.startElement("div", menu);
			rw.writeAttribute("class", "input-group", "class");
		}
		return hasAddon;
	}

}
