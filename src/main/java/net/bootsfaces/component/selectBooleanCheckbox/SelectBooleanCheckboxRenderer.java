/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.selectBooleanCheckbox;

import javax.faces.component.*;
import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.A;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:selectBooleanCheckbox /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.selectBooleanCheckbox.SelectBooleanCheckbox")
public class SelectBooleanCheckboxRenderer extends CoreRenderer {

	/**
	 * Bootstrap CSS class for AddOns (i.e. components rendered seamlessly in
	 * front of or behind the input field).
	 */
	public static final String ADDON = "input-group-addon";

	/**
	 * This methods receives and processes input made by the user. More
	 * specifically, it ckecks whether the user has interacted with the current
	 * b:selectBooleanCheckbox. The default implementation simply stores the
	 * input value in the list of submitted values. If the validation checks are
	 * passed, the values in the <code>submittedValues</code> list are store in
	 * the backend bean.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:selectBooleanCheckbox.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		SelectBooleanCheckbox selectBooleanCheckbox = (SelectBooleanCheckbox) component;

		if (selectBooleanCheckbox.isDisabled() || selectBooleanCheckbox.isReadonly()) {
			return;
		}

		decodeBehaviors(context, selectBooleanCheckbox);

		String clientId = selectBooleanCheckbox.getClientId(context);
		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(clientId);

		if (submittedValue != null) {
			selectBooleanCheckbox.setSubmittedValue("on".equals(submittedValue));
		}
	}

	/**
	 * This methods generates the HTML code of the current
	 * b:selectBooleanCheckbox.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:selectBooleanCheckbox.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		SelectBooleanCheckbox selectBooleanCheckbox = (SelectBooleanCheckbox) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = selectBooleanCheckbox.getClientId();

		Tooltip.generateTooltip(context, selectBooleanCheckbox, rw);

		addLabel(rw, clientId, selectBooleanCheckbox);

		// "Prepend" facet
		UIComponent prependingAddOnFacet = selectBooleanCheckbox.getFacet(C.PREPEND);
		if ((prependingAddOnFacet != null)) {
			R.addClass2FacetComponent(prependingAddOnFacet, "OutputText", ADDON);
		}

		// "Append" facet
		UIComponent appendingAddOnFacet = selectBooleanCheckbox.getFacet(C.APPEND);
		if ((appendingAddOnFacet != null)) {
			R.addClass2FacetComponent(appendingAddOnFacet, "OutputText", ADDON);
		}
		final boolean hasAddon = startInputGroupForAddOn(rw, (prependingAddOnFacet != null),
				(appendingAddOnFacet != null), selectBooleanCheckbox);

		int span = startColSpanDiv(rw, selectBooleanCheckbox);

		addPrependingAddOnToInputGroup(context, rw, prependingAddOnFacet, (prependingAddOnFacet != null),
				selectBooleanCheckbox);
		renderInputTag(context, rw, clientId, selectBooleanCheckbox);
		addAppendingAddOnToInputGroup(context, rw, appendingAddOnFacet, (appendingAddOnFacet != null),
				selectBooleanCheckbox);

		closeInputGroupForAddOn(rw, hasAddon);
		closeColSpanDiv(rw, span);

		Tooltip.activateTooltips(context, selectBooleanCheckbox);

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
	 * @param selectBooleanCheckbox the component to render

	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void addAppendingAddOnToInputGroup(FacesContext context, ResponseWriter rw,
			UIComponent appendingAddOnFacet, boolean hasAppendingAddOn, SelectBooleanCheckbox selectBooleanCheckbox)
					throws IOException {
		if (hasAppendingAddOn) {
			if (appendingAddOnFacet.getClass().getName().endsWith("Button") || (appendingAddOnFacet.getChildCount() > 0
					&& appendingAddOnFacet.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement("div", selectBooleanCheckbox);
				rw.writeAttribute("class", "input-group-btn", "class");
				appendingAddOnFacet.encodeAll(context);
				rw.endElement("div");
			} else {
				appendingAddOnFacet.encodeAll(context);
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
	 *            the id used by the label to reference the input field
	 * @param selectBooleanCheckbox the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void addLabel(ResponseWriter rw, String clientId, SelectBooleanCheckbox selectBooleanCheckbox)
			throws IOException {
		if (selectBooleanCheckbox.isRenderLabel()) {
			String label = selectBooleanCheckbox.getLabel();
			if (label != null) {
				rw.startElement("label", selectBooleanCheckbox);
				rw.writeAttribute("for", clientId, "for");
				rw.writeText(label, null);
				rw.endElement("label");
			}
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
	 * 
	 * @param hasPrependingAddOn
	 * @param selectBooleanCheckbox the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void addPrependingAddOnToInputGroup(FacesContext context, ResponseWriter rw,
			UIComponent prependingAddOnFacet, boolean hasPrependingAddOn, SelectBooleanCheckbox selectBooleanCheckbox)
					throws IOException {
		if (hasPrependingAddOn) {
			if (prependingAddOnFacet.getClass().getName().endsWith("Button")
					|| (prependingAddOnFacet.getChildCount() > 0
							&& prependingAddOnFacet.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement("div", selectBooleanCheckbox);
				rw.writeAttribute("class", "input-group-btn", "class");
				prependingAddOnFacet.encodeAll(context);
				rw.endElement("div");
			} else {
				prependingAddOnFacet.encodeAll(context);
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
	protected void closeColSpanDiv(ResponseWriter rw, int span) throws IOException {
		if (span > 0) {
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
	 * Renders the input tag.
	 * 
	 * @param context
	 *            the FacesContext
	 * @param rw
	 * @param clientId
	 * @param selectBooleanCheckbox the component to render
	 * @throws java.io.IOException
	 */
	protected void renderInputTag(FacesContext context, ResponseWriter rw, String clientId,
			SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		renderInputTag(rw, context, selectBooleanCheckbox);
		renderInputTagAttributes(rw, clientId, selectBooleanCheckbox);
		// Render Ajax Capabilities
		AJAXRenderer.generateMojarraAjax(FacesContext.getCurrentInstance(), selectBooleanCheckbox, rw);

		renderInputTagValue(context, rw, selectBooleanCheckbox);
		renderInputTagEnd(rw, selectBooleanCheckbox);
	}

	/**
	 * Renders the start of the input tag. This method is protected in order to
	 * allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param selectBooleanCheckbox the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTag(ResponseWriter rw, FacesContext context, SelectBooleanCheckbox selectBooleanCheckbox)
			throws IOException {
		rw.startElement("div", selectBooleanCheckbox);
		Tooltip.generateTooltip(context, selectBooleanCheckbox, rw);

		rw.writeAttribute("class", "checkbox", "class");
		rw.startElement("label", selectBooleanCheckbox);

		rw.startElement("input", selectBooleanCheckbox);
	}

	/**
	 * Renders the attributes of the input tag. This method is protected in
	 * order to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param clientId
	 *            the client id (used both as id and name)
	 * @param selectBooleanCheckbox the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTagAttributes(ResponseWriter rw, String clientId,
			SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		rw.writeAttribute("id", clientId, null);
		rw.writeAttribute("name", clientId, null);
		rw.writeAttribute("type", "checkbox", null);

		StringBuilder sb;
		String s;
		sb = new StringBuilder(20); // optimize int
		String fsize = selectBooleanCheckbox.getFieldSize();

		if (fsize != null) {
			sb.append(" input-").append(fsize);
		}
		String cssClass = selectBooleanCheckbox.getStyleClass();
		if (cssClass != null) {
			sb.append(" ").append(cssClass);
		}
		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute("class", s, "class");
		}

		if (selectBooleanCheckbox.isDisabled()) {
			rw.writeAttribute("disabled", "disabled", null);
		}
		if (selectBooleanCheckbox.isReadonly()) {
			rw.writeAttribute("readonly", "readonly", null);
		}

		// Encode attributes (HTML 4 pass-through + DHTML)
		R.encodeHTML4DHTMLAttrs(rw, selectBooleanCheckbox.getAttributes(), A.CHECKBOX_ATTRS);
	}

	/**
	 * Closes the input tag. This method is protected in order to allow
	 * third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param selectBooleanCheckbox the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTagEnd(ResponseWriter rw, SelectBooleanCheckbox selectBooleanCheckbox)
			throws IOException {
		rw.endElement("input");
		String caption = selectBooleanCheckbox.getCaption();
		if (null != caption)
			rw.append(caption);
		rw.endElement("label");
		rw.endElement("div");
	}

	/**
	 * Renders the value of the input tag. This method is protected in order to
	 * allow third-party frameworks to derive from it.
	 *
	 * @param context
	 *            the FacesContext
	 * @param rw
	 *            the response writer
	 * @param selectBooleanCheckbox the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTagValue(FacesContext context, ResponseWriter rw,
			SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		String v = R.getValue2Render(context, selectBooleanCheckbox);
		if (v != null && "true".equals(v))
			rw.writeAttribute("checked", v, null);
	}

	/**
	 * Start the column span div (if there's one). This method is protected in
	 * order to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param selectBooleanCheckbox the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected int startColSpanDiv(ResponseWriter rw, SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		int span = selectBooleanCheckbox.getSpan();
		if (span > 0) {
			rw.startElement("div", selectBooleanCheckbox);
			rw.writeAttribute("class", "col-md-" + span, "class");
		}
		return span;
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
	 * @param selectBooleanCheckbox the component to render
	 * @return true if there is an add-on in front of or behind the input field
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected boolean startInputGroupForAddOn(ResponseWriter rw, boolean hasPrependingAddOn, boolean hasAppendingAddOn,
			SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		final boolean hasAddon = hasAppendingAddOn || hasPrependingAddOn;
		if (hasAddon) {
			rw.startElement("div", selectBooleanCheckbox);
			rw.writeAttribute("class", "input-group", "class");
		}
		return hasAddon;
	}

}
