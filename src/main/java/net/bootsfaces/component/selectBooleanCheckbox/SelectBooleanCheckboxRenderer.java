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

package net.bootsfaces.component.selectBooleanCheckbox;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.FacesMessages;

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
		
		decodeBehaviors(context, selectBooleanCheckbox); // moved to
																// AJAXRenderer
	
		String clientId = selectBooleanCheckbox.getClientId(context);
		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(clientId);

		if (submittedValue != null) {
			selectBooleanCheckbox.setSubmittedValue("on".equals(submittedValue));
		} else if (context.getExternalContext().getRequestParameterMap().containsKey(clientId + "_helper")) {
			selectBooleanCheckbox.setSubmittedValue(false);
		}
		if (Boolean.FALSE.equals(selectBooleanCheckbox.getSubmittedValue()) && selectBooleanCheckbox.isRequired()) {
			FacesMessages.error(component.getClientId(), "Validation error", "Please check this checkbox.");
		} else {

			String id = component.getClientId(context);
			new AJAXRenderer().decode(context, component, "input_" +id);
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

		int span = startColSpanDiv(rw, selectBooleanCheckbox);
		rw.startElement("div", component);
		writeAttribute(rw, "class", "form-group");
		addLabel(rw, clientId, selectBooleanCheckbox);

		renderInputTag(context, rw, clientId, selectBooleanCheckbox);

		rw.endElement("div");
		closeColSpanDiv(rw, span);

		Tooltip.activateTooltips(context, selectBooleanCheckbox);

	}

	/**
	 * Renders the optional label. This method is protected in order to allow
	 * third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param clientId
	 *            the id used by the label to reference the input field
	 * @param selectBooleanCheckbox
	 *            the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void addLabel(ResponseWriter rw, String clientId, SelectBooleanCheckbox selectBooleanCheckbox)
			throws IOException {
		if (selectBooleanCheckbox.isRenderLabel()) {
			String label = selectBooleanCheckbox.getLabel();
			if (label != null) {
				rw.startElement("label", selectBooleanCheckbox);
				writeAttribute(rw, "class", getErrorAndRequiredClass(selectBooleanCheckbox, clientId));
				if (null != selectBooleanCheckbox.getDir()) {
					rw.writeAttribute("dir", selectBooleanCheckbox.getDir(), "dir");
				}

				rw.writeAttribute("for", "input_" + clientId, "for");
				rw.writeText(label, null);
				rw.endElement("label");
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
	 * Renders the input tag.
	 * 
	 * @param context
	 *            the FacesContext
	 * @param rw
	 * @param clientId
	 * @param selectBooleanCheckbox
	 *            the component to render
	 * @throws java.io.IOException
	 */
	protected void renderInputTag(FacesContext context, ResponseWriter rw, String clientId,
			SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		renderInputTag(rw, context, selectBooleanCheckbox, clientId);
		renderInputTagAttributes(rw, clientId, selectBooleanCheckbox);
		// Render Ajax Capabilities
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), selectBooleanCheckbox, rw);

		renderInputTagValue(context, rw, selectBooleanCheckbox);
		renderInputTagEnd(rw, selectBooleanCheckbox);
		renderInputTagHelper(rw, context, selectBooleanCheckbox, clientId);
	}

	/**
	 * Renders the start of the input tag. This method is protected in order to
	 * allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param selectBooleanCheckbox
	 *            the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTagHelper(ResponseWriter rw, FacesContext context,
			SelectBooleanCheckbox selectBooleanCheckbox, String clientId) throws IOException {
		rw.startElement("input", selectBooleanCheckbox);
		rw.writeAttribute("name", clientId + "_helper", null);
		rw.writeAttribute("value", "on", "value");
		rw.writeAttribute("checked", "true", "checked");
		rw.writeAttribute("type", "hidden", "type");
		rw.writeAttribute("style", "display:none", "style");
		rw.endElement("input");
	}

	/**
	 * Renders the start of the input tag. This method is protected in order to
	 * allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param selectBooleanCheckbox
	 *            the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTag(ResponseWriter rw, FacesContext context, SelectBooleanCheckbox selectBooleanCheckbox,
			String clientId) throws IOException {
		rw.startElement("div", selectBooleanCheckbox);
		rw.writeAttribute("id", clientId, null);
		if (null != selectBooleanCheckbox.getDir()) {
			rw.writeAttribute("dir", selectBooleanCheckbox.getDir(), "dir");
		}

		Tooltip.generateTooltip(context, selectBooleanCheckbox, rw);

		rw.writeAttribute("class", "checkbox", "class");
		rw.startElement("label", selectBooleanCheckbox);
		writeAttribute(rw, "class", getErrorAndRequiredClass(selectBooleanCheckbox, clientId));

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
	 * @param selectBooleanCheckbox
	 *            the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTagAttributes(ResponseWriter rw, String clientId,
			SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		rw.writeAttribute("name", clientId, null);
		rw.writeAttribute("id", "input_" + clientId, null);
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
		addAttributesForSwitch(rw, selectBooleanCheckbox);

		// Encode attributes (HTML 4 pass-through + DHTML)
		R.encodeHTML4DHTMLAttrs(rw, selectBooleanCheckbox.getAttributes(), H.CHECKBOX);
	}

	/**
	 * The b:switch and the b:selectBooleanCheckbox share most of their code. This method allows to add extra attributes for the switch.
	 * @param rw
	 * @param selectBooleanCheckbox
	 * @throws IOException
	 */
	protected void addAttributesForSwitch(ResponseWriter rw, SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
	}

	/**
	 * Closes the input tag. This method is protected in order to allow
	 * third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param selectBooleanCheckbox
	 *            the component to render
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
	 * @param selectBooleanCheckbox
	 *            the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected void renderInputTagValue(FacesContext context, ResponseWriter rw,
			SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		String v = getValue2Render(context, selectBooleanCheckbox);
		if (v != null && "true".equals(v))
			rw.writeAttribute("checked", v, null);
	}

	/**
	 * Start the column span div (if there's one). This method is protected in
	 * order to allow third-party frameworks to derive from it.
	 *
	 * @param rw
	 *            the response writer
	 * @param selectBooleanCheckbox
	 *            the component to render
	 * @throws IOException
	 *             may be thrown by the response writer
	 */
	protected int startColSpanDiv(ResponseWriter rw, SelectBooleanCheckbox selectBooleanCheckbox) throws IOException {
		int span = selectBooleanCheckbox.getSpan();
		if (span > 0) {
			rw.startElement("div", selectBooleanCheckbox);
			rw.writeAttribute("class", "col-md-" + span, "class");
			if (null != selectBooleanCheckbox.getDir()) {
				rw.writeAttribute("dir", selectBooleanCheckbox.getDir(), "dir");
			}
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
	 * @param selectBooleanCheckbox
	 *            the component to render
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
			if (null != selectBooleanCheckbox.getDir()) {
				rw.writeAttribute("dir", selectBooleanCheckbox.getDir(), "dir");
			}
		}
		return hasAddon;
	}

}
