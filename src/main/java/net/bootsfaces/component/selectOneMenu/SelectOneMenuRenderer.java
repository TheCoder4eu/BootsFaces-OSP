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

package net.bootsfaces.component.selectOneMenu;

import java.io.IOException;
import java.util.List;

import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.SelectItemUtils;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.icon.Icon;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:SelectOneMenu /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.selectOneMenu.SelectOneMenu")
public class SelectOneMenuRenderer extends CoreRenderer {

    /** Receives the value from the client and sends it to the JSF bean. */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        SelectOneMenu menu = (SelectOneMenu) component;
        if (menu.isDisabled() || menu.isReadonly()) {
            return;
        }
        String outerClientId = menu.getClientId(context);
        String clientId = outerClientId+"Inner";
        String submittedOptionValue = (String) context.getExternalContext().getRequestParameterMap().get(clientId);

        List<SelectItem> items = SelectItemUtils.collectOptions(context, menu);

        if (null != submittedOptionValue) {
            for (int index = 0; index < items.size(); index++) {
                Object currentOption = items.get(index);
                String currentOptionValueAsString;
                Object currentOptionValue = null;
                if (currentOption instanceof SelectItem) {
                    if (!((SelectItem) currentOption).isDisabled()) {
                        currentOptionValue = ((SelectItem) currentOption).getValue();
                        if (null == currentOptionValue) // use the label as
                                                        // fall-back
                            currentOptionValue = ((SelectItem) currentOption).getLabel();
                    }
                }
                if (currentOptionValue instanceof String) {
                    currentOptionValueAsString = (String) currentOptionValue;
                } else
                    currentOptionValueAsString = String.valueOf(index);
                if (submittedOptionValue.equals(currentOptionValueAsString)) {
                    menu.setSubmittedValue(currentOptionValue);
                    menu.setValid(true);
                    menu.validateValue(context, submittedOptionValue);
                    new AJAXRenderer().decode(context, component, clientId);
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
        String clientId = outerClientId+"Inner";
        String span = startColSpanDiv(rw, menu);
        rw.startElement("div", menu);
        Tooltip.generateTooltip(context, menu, rw);

        if (menu.isInline()) {
            rw.writeAttribute("class", "form-inline", "class");
        } else {
            rw.writeAttribute("class", "form-group", "class");
        }
        rw.writeAttribute("id", outerClientId, "id");
        writeAttribute(rw, "dir", menu.getDir(), "dir");

        addLabel(rw, clientId, menu);

        UIComponent prependingAddOnFacet = menu.getFacet("prepend");
        UIComponent appendingAddOnFacet = menu.getFacet("append");
        final boolean hasAddon = startInputGroupForAddOn(rw, (prependingAddOnFacet != null),
                (appendingAddOnFacet != null), menu);

        addPrependingAddOnToInputGroup(context, rw, prependingAddOnFacet, (prependingAddOnFacet != null), menu);
        renderSelectTag(context, rw, clientId, menu);
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
            if (appendingAddOnFacet.getClass().getName().endsWith("Button") || (appendingAddOnFacet.getChildCount() > 0
                    && appendingAddOnFacet.getChildren().get(0).getClass().getName().endsWith("Button"))) {
                rw.startElement("div", menu);
                rw.writeAttribute("class", "input-group-btn", "class");
                appendingAddOnFacet.encodeAll(context);
                rw.endElement("div");
            } else {
                if (appendingAddOnFacet instanceof Icon)
                    ((Icon) appendingAddOnFacet).setAddon(true); // modifies the id of the icon
                rw.startElement("span", menu);
                rw.writeAttribute("class", "y input-group-addon", "class");
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
    protected void addLabel(ResponseWriter rw, String clientId, SelectOneMenu menu) throws IOException {
        String label = menu.getLabel();
        {
            if (!menu.isRenderLabel()) {
                label = null;
            }
        }
        if (label != null) {
            rw.startElement("label", menu);
            rw.writeAttribute("for", clientId, "for");
            generateErrorAndRequiredClass(menu, rw, clientId);
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
            if (prependingAddOnFacet.getClass().getName().endsWith("Button")
                    || (prependingAddOnFacet.getChildCount() > 0
                            && prependingAddOnFacet.getChildren().get(0).getClass().getName().endsWith("Button"))) {
                rw.startElement("div", menu);
                rw.writeAttribute("class", "input-group-btn", "class");
                prependingAddOnFacet.encodeAll(context);
                rw.endElement("div");
            } else {
                if (prependingAddOnFacet instanceof Icon)
                    ((Icon) prependingAddOnFacet).setAddon(true); // modifies the id of the icon
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
        if (span != null && span.trim().length()>0) {
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

    /** Renders the select tag. */
    protected void renderSelectTag(FacesContext context, ResponseWriter rw, String clientId, SelectOneMenu menu)
            throws IOException {
        renderSelectTag(rw, menu);
        renderSelectTagAttributes(rw, clientId, menu);
        renderOptions(context, rw, menu);

        renderInputTagEnd(rw);
    }


    /**
     * Parts of this class are an adapted version of
     * InputRenderer#getSelectItems() of PrimeFaces 5.1.
     *
     * @param rw
     * @throws IOException
     */
    protected void renderOptions(FacesContext context, ResponseWriter rw, SelectOneMenu menu)
            throws IOException {
        List<SelectItem> items = SelectItemUtils.collectOptions(context, menu);

        for (int index = 0; index < items.size(); index++) {
            Object option = items.get(index);
            renderOption(context,menu, rw, (SelectItem) option, index);
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
     * @throws IOException
     *             thrown if something's wrong with the response writer
     */
    protected void renderOption(FacesContext context, SelectOneMenu menu, ResponseWriter rw, SelectItem selectItem, int index)
            throws IOException {

        String itemLabel = selectItem.getLabel();
        final String description = selectItem.getDescription();
        final Object itemValue = selectItem.getValue();

        renderOption(context, menu, rw, index, itemLabel, description, itemValue, selectItem.isDisabled());
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

    private String getOptionAsString(FacesContext context, SelectOneMenu menu, Object value, Converter converter) throws ConverterException {

        if (converter == null) {
            if (value == null) {
                return "";
            } else if (value instanceof String) {
                return (String) value;
            } else {
                Converter implicitConverter = findImplicitConverter(context, menu);

                return implicitConverter == null ? value.toString() : implicitConverter.getAsString(context, menu, value);
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

    private boolean isSelected(FacesContext context, SelectOneMenu menu, Object value, Object itemValue, Converter converter) {
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
            final String description, final Object itemValue, boolean isDisabled) throws IOException {
        Object submittedValue = menu.getSubmittedValue();
        Object selectedOption;
        Object optionValue;
        Converter converter = menu.getConverter();
        String itemValueAsString = getOptionAsString(context, menu, itemValue, converter);
        if (submittedValue != null) {
            selectedOption = submittedValue;
            optionValue = itemValueAsString;
        } else {
            selectedOption = menu.getValue();
            optionValue = itemValue;
        }

        boolean isItemLabelBlank = itemLabel == null || itemLabel.trim().isEmpty();
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
            } else
                value = String.valueOf(index);
            rw.writeAttribute("value", value, "value");
            if (isSelected(context, menu, selectedOption, optionValue, converter)) {
                rw.writeAttribute("selected", "true", "selected");
            }
        } else if (itemLabel.equals(selectedOption)) {
            rw.writeAttribute("selected", "true", "selected");
        }
        if (isDisabled)
            rw.writeAttribute("disabled", "disabled", "disabled");

        rw.write(itemLabel);

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
    protected void renderSelectTagAttributes(ResponseWriter rw, String clientId, SelectOneMenu menu)
            throws IOException {
        rw.writeAttribute("id", clientId, null);
        //Tooltip.generateTooltip(FacesContext.getCurrentInstance(), menu, rw);
        rw.writeAttribute("name", clientId, null);

        StringBuilder sb;
        String s;
        sb = new StringBuilder(20); // optimize int
        sb.append("form-control");
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

        AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), menu, rw);

        // Encode attributes (HTML 4 pass-through + DHTML)
        R.encodeHTML4DHTMLAttrs(rw, menu.getAttributes(), H.SELECT_ONE_MENU);
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
    protected String startColSpanDiv(ResponseWriter rw, SelectOneMenu menu) throws IOException {
        String clazz = Responsive.getResponsiveStyleClass(menu, false);
        if (clazz!= null && clazz.trim().length()>0) {
            rw.startElement("div", menu);
            rw.writeAttribute("class", clazz, "class");
        }
        return clazz;
    }

    /**
     * Starts the input field group (if needed to display a component seamlessly
     * in front of or behind the input field). This method is protected in order
     * to allow third-party frameworks to derive from it.
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
