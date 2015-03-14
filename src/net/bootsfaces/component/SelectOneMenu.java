/**
 *  Copyright 2014 Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.SelectItem;

import net.bootsfaces.C;
import net.bootsfaces.render.A;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;

/**
 * This class represents and renders a combobox (a drop-down menu).
 * 
 * @author 2014 Stephan Rauh (http://www.beyondjava.net).
 */

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head") })
@FacesComponent(C.SELECT_ONE_MENU_COMPONENT_TYPE)
public class SelectOneMenu extends HtmlInputText {

	/** Bootstrap CSS class for AddOns (i.e. components rendered seamlessly in front of or behind the input field). */
	public static final String ADDON = "input-group-addon";

	/**
	 * The component family for this component.
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	/**
	 * The standard component type for this component.
	 */
	public static final String COMPONENT_TYPE = C.SELECT_ONE_MENU_COMPONENT_TYPE;

	public SelectOneMenu() {
		setRendererType(null); // this component renders itself
	}

	/** Receives the value from the client and sends it to the JSF bean. */
	@Override
	public void decode(FacesContext context) {
		if (isDisabled() || isReadonly()) {
			return;
		}
		String subVal = (String) context.getExternalContext().getRequestParameterMap().get(getClientId(context));

		this.setSubmittedValue(subVal);
		this.setValid(true);
	}

	/** Generates the HTML code for this component. */
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
		Map<String, Object> attrs = getAttributes();
		ResponseWriter rw = context.getResponseWriter();
		String clientId = getClientId(context);
		rw.startElement(H.DIV, this);
		rw.writeAttribute(H.CLASS, "form-group", H.CLASS);

		addLabel(attrs, rw, clientId);

		// "Prepend" facet
		UIComponent prependingAddOnFacet = getFacet(C.PREPEND);
		if ((prependingAddOnFacet != null)) {
			R.addClass2FacetComponent(prependingAddOnFacet, "OutputText", ADDON);
		}

		// "Append" facet
		UIComponent appendingAddOnFacet = getFacet(C.APPEND);
		if ((appendingAddOnFacet != null)) {
			R.addClass2FacetComponent(appendingAddOnFacet, "OutputText", ADDON);
		}
		final boolean hasAddon = startInputGroupForAddOn(rw, (prependingAddOnFacet != null), (appendingAddOnFacet != null));

		int span = startColSpanDiv(attrs, rw);

		addPrependingAddOnToInputGroup(context, rw, prependingAddOnFacet, (prependingAddOnFacet != null));
		renderSelectTag(context, attrs, rw, clientId);
		addAppendingAddOnToInputGroup(context, rw, appendingAddOnFacet, (appendingAddOnFacet != null));

		closeInputGroupForAddOn(rw, hasAddon);
		closeColSpanDiv(rw, span);
		rw.endElement("div"); // form-group
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
	protected void addAppendingAddOnToInputGroup(FacesContext context, ResponseWriter rw, UIComponent appendingAddOnFacet,
			boolean hasAppendingAddOn) throws IOException {
		if (hasAppendingAddOn) {
			if (appendingAddOnFacet.getClass().getName().endsWith("Button")
					|| (appendingAddOnFacet.getChildCount() > 0 && appendingAddOnFacet.getChildren().get(0).getClass().getName()
							.endsWith("Button"))) {
				rw.startElement(H.DIV, this);
				rw.writeAttribute(H.CLASS, "input-group-btn", H.CLASS);
				appendingAddOnFacet.encodeAll(context);
				rw.endElement(H.DIV);
			} else {
				appendingAddOnFacet.encodeAll(context);
			}
		}
	}

	/**
	 * Renders the optional label. This method is protected in order to allow third-party frameworks to derive from it.
	 * 
	 * @param attrs
	 *            the input field's attribute list
	 * @param rw
	 *            the response writer
	 * @param clientId
	 *            the id used by the label to refernce the input field
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void addLabel(Map<String, Object> attrs, ResponseWriter rw, String clientId) throws IOException {
		String label = A.asString(attrs.get(A.LABEL));
		{
			Object rl = attrs.get(A.RENDERLABEL);
			if (null != rl) {
				if (!A.toBool(attrs.get(A.RENDERLABEL))) {
					label = null;
				}
			}
		}
		if (label != null) {
			rw.startElement(H.LABEL, this);
			rw.writeAttribute(A.FOR, clientId, A.FOR);
			rw.writeText(label, null);
			rw.endElement(H.LABEL);
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
	protected void addPrependingAddOnToInputGroup(FacesContext context, ResponseWriter rw, UIComponent prependingAddOnFacet,
			boolean hasPrependingAddOn) throws IOException {
		if (hasPrependingAddOn) {
			if (prependingAddOnFacet.getClass().getName().endsWith("Button")
					|| (prependingAddOnFacet.getChildCount() > 0 && prependingAddOnFacet.getChildren().get(0).getClass().getName()
							.endsWith("Button"))) {
				rw.startElement(H.DIV, this);
				rw.writeAttribute(H.CLASS, "input-group-btn", H.CLASS);
				prependingAddOnFacet.encodeAll(context);
				rw.endElement(H.DIV);
			} else {
				prependingAddOnFacet.encodeAll(context);
			}
		}
	}

	/**
	 * Terminate the column span div (if there's one). This method is protected in order to allow third-party frameworks to derive from it.
	 * 
	 * @param rw
	 *            the response writer
	 * @param span
	 *            the width of the components (in BS columns).
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void closeColSpanDiv(ResponseWriter rw, int span) throws IOException {
		if (span > 0) {
			rw.endElement(H.DIV);
		}
	}

	/**
	 * Terminates the input field group (if there's one). This method is protected in order to allow third-party frameworks to derive from
	 * it.
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
			rw.endElement(H.DIV);
		}
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/** Renders the select tag. */
	protected void renderSelectTag(FacesContext context, Map<String, Object> attrs, ResponseWriter rw, String clientId) throws IOException {
		renderSelectTag(rw);
		renderSelectTagAttributes(attrs, rw, clientId);
		String selectedOption = R.getValue2Render(context, this);
		renderOptions(context, rw, selectedOption);

		renderInputTagEnd(attrs, rw);
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
	protected SelectItem createSelectItem(FacesContext context, UISelectItems uiSelectItems, Object value, Object label) {
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
	 * Parts of this class are an adapted version of InputRenderer#getSelectItems() of PrimeFaces 5.1.
	 * 
	 * @param rw
	 * @param selectedOption
	 * @throws IOException
	 */
	protected void renderOptions(FacesContext context, ResponseWriter rw, String selectedOption) throws IOException {
		List<UIComponent> selectItems = getChildren();
		for (UIComponent kid : selectItems) {
			if (kid instanceof UISelectItem) {
				UISelectItem option = (UISelectItem) kid;
				renderOption(rw, option, selectedOption);
			} else if (kid instanceof UISelectItems) {

				UISelectItems uiSelectItems = ((UISelectItems) kid);
				Object value = uiSelectItems.getValue();

				if (value != null) {
					if (value instanceof SelectItem) {
						renderOption(rw, (SelectItem) value, selectedOption);
					} else {
						if (value.getClass().isArray()) {
							for (int i = 0; i < Array.getLength(value); i++) {
								Object item = Array.get(value, i);

								if (item instanceof SelectItem)
									renderOption(rw, (SelectItem) item, selectedOption);
								else
									renderOption(rw, createSelectItem(context, uiSelectItems, item, null), selectedOption);
							}
						} else if (value instanceof Map) {
							Map map = (Map) value;

							for (Iterator it = map.keySet().iterator(); it.hasNext();) {
								Object key = it.next();

								renderOption(rw, createSelectItem(context, uiSelectItems, map.get(key), String.valueOf(key)),
										selectedOption);
							}
						} else if (value instanceof Collection) {
							Collection collection = (Collection) value;

							for (Iterator it = collection.iterator(); it.hasNext();) {
								Object item = it.next();
								if (item instanceof SelectItem)
									renderOption(rw, (SelectItem) item, selectedOption);
								else
									renderOption(rw, createSelectItem(context, uiSelectItems, item, null), selectedOption);
							}
						}
					}
				}

			}
		}
	}

	/**
	 * Renders a single &lt;option&gt; tag. For some reason, <code>SelectItem</code> and <code>UISelectItem</code> don't share a common
	 * interface, so this method is repeated twice.
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
	protected void renderOption(ResponseWriter rw, SelectItem selectItem, String selectedOption) throws IOException {

		String itemLabel = selectItem.getLabel();
		boolean isItemLabelBlank = itemLabel == null || itemLabel.trim().length() == 0;
		itemLabel = isItemLabelBlank ? "&nbsp;" : itemLabel;

		rw.startElement("option", null);
		rw.writeAttribute("data-label", itemLabel, null);
		if (selectItem.getDescription() != null) {
			rw.writeAttribute("title", selectItem.getDescription(), null);
		}
		if (selectItem.getValue() != null) {
			String value = (String) selectItem.getValue();
			rw.writeAttribute("value", value, "value");
			if (value.equals(selectedOption)) {
				rw.writeAttribute("selected", "true", "selected");
			}
		} else if (itemLabel.equals(selectedOption)) {
			rw.writeAttribute("selected", "true", "selected");
		}

		if (itemLabel.equals("&nbsp;"))
			rw.write(itemLabel);
		else {
			rw.write(itemLabel);
		}

		rw.endElement("option");
	}

	/**
	 * Renders a single &lt;option&gt; tag. For some reason, <code>SelectItem</code> and <code>UISelectItem</code> don't share a common
	 * interface, so this method is repeated twice.
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
	protected void renderOption(ResponseWriter rw, UISelectItem selectItem, String selectedOption) throws IOException {

		String itemLabel = selectItem.getItemLabel();
		boolean isItemLabelBlank = itemLabel == null || itemLabel.trim().length() == 0;
		itemLabel = isItemLabelBlank ? "&nbsp;" : itemLabel;

		rw.startElement("option", null);
		rw.writeAttribute("data-label", itemLabel, null);
		if (selectItem.getItemDescription() != null) {
			rw.writeAttribute("title", selectItem.getItemDescription(), null);
		}
		if (selectItem.getItemValue() != null) {
			String value = (String) selectItem.getItemValue();
			rw.writeAttribute("value", value, "value");
			if (value.equals(selectedOption)) {
				rw.writeAttribute("selected", "true", "selected");
			}
		} else if (itemLabel.equals(selectedOption)) {
			rw.writeAttribute("selected", "true", "selected");
		}

		if (itemLabel.equals("&nbsp;"))
			rw.write(itemLabel);
		else {
			rw.write(itemLabel);
		}

		rw.endElement("option");
	}

	/**
	 * Renders the start of the input tag. This method is protected in order to allow third-party frameworks to derive from it.
	 * 
	 * @param rw
	 *            the response writer
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderSelectTag(ResponseWriter rw) throws IOException {
		rw.startElement(H.SELECT, this);
	}

	/**
	 * Renders the attributes of the input tag. This method is protected in order to allow third-party frameworks to derive from it.
	 * 
	 * @param attrs
	 *            the component's attribute list
	 * @param rw
	 *            the response writer
	 * @param clientId
	 *            the client id (used both as id and name)
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderSelectTagAttributes(Map<String, Object> attrs, ResponseWriter rw, String clientId) throws IOException {
		rw.writeAttribute(H.ID, clientId, null);
		rw.writeAttribute(H.NAME, clientId, null);
		rw.writeAttribute(H.TYPE, "checkbox", null);

		StringBuilder sb;
		String s;
		sb = new StringBuilder(20); // optimize int
		sb.append("form-control");
		String fsize = A.asString(attrs.get(A.FIELDSIZE));

		if (fsize != null) {
			sb.append(" input-").append(fsize);
		}
		String cssClass = A.asString(attrs.get(H.STYLECLASS));
		if (cssClass != null) {
			sb.append(" ").append(cssClass);
		}
		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute(H.CLASS, s, H.CLASS);
		}

		if (A.toBool(attrs.get(A.DISABLED))) {
			rw.writeAttribute(A.DISABLED, A.DISABLED, null);
		}
		if (A.toBool(attrs.get(A.READONLY))) {
			rw.writeAttribute(A.READONLY, A.READONLY, null);
		}

		// Encode attributes (HTML 4 pass-through + DHTML)
		R.encodeHTML4DHTMLAttrs(rw, attrs, A.CHECKBOX_ATTRS);
	}

	/**
	 * Closes the input tag. This method is protected in order to allow third-party frameworks to derive from it.
	 * 
	 * @param rw
	 *            the response writer
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTagEnd(Map<String, Object> attrs, ResponseWriter rw) throws IOException {
		rw.endElement(H.SELECT);
	}

	/**
	 * Start the column span div (if there's one). This method is protected in order to allow third-party frameworks to derive from it.
	 * 
	 * @param attrs
	 *            the current attribute list
	 * @param rw
	 *            the response writer
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected int startColSpanDiv(Map<String, Object> attrs, ResponseWriter rw) throws IOException {
		int span = A.toInt(attrs.get(A.SPAN));
		if (span > 0) {
			rw.startElement(H.DIV, this);
			rw.writeAttribute(H.CLASS, "col-md-" + span, H.CLASS);
		}
		return span;
	}

	/**
	 * Starts the input field group (if needed to display a component seamlessly in front of or behind the input field). This method is
	 * protected in order to allow third-party frameworks to derive from it.
	 * 
	 * @param rw
	 *            the response writer
	 * @param hasPrependingAddOn
	 * @param hasAppendingAddOn
	 * @return true if there is an add-on in front of or behind the input field
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected boolean startInputGroupForAddOn(ResponseWriter rw, boolean hasPrependingAddOn, boolean hasAppendingAddOn) throws IOException {
		final boolean hasAddon = hasAppendingAddOn || hasPrependingAddOn;
		if (hasAddon) {
			rw.startElement(H.DIV, this);
			rw.writeAttribute(H.CLASS, "input-group", H.CLASS);
		}
		return hasAddon;
	}
}
