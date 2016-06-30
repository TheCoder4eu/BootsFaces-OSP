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

package net.bootsfaces.component.panel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:panel /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.panel.Panel")
public class PanelRenderer extends CoreRenderer {
	/**
	 * This methods receives and processes input made by the user. More
	 * specifically, it ckecks whether the user has interacted with the current
	 * b:panel. The default implementation simply stores the input value in the
	 * list of submitted values. If the validation checks are passed, the values
	 * in the <code>submittedValues</code> list are store in the backend bean.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:panel.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		Panel panel = (Panel) component;

		String clientId = panel.getClientId(context);
		String jQueryClientID = clientId.replace(":", "_");
		// the panel uses two ids to send requests to the server!
		new AJAXRenderer().decode(context, component, panel.getClientId(context));
		new AJAXRenderer().decode(context, component, jQueryClientID+"content");

		String collapseStateId = clientId.replace(":", "_") + "_collapsed";

		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(collapseStateId);

		if (submittedValue != null) {
			if (Boolean.valueOf(submittedValue) != panel.isCollapsed())
				panel.setCollapsed(Boolean.valueOf(submittedValue));
		}
	}

	/**
	 * This methods generates the HTML code of the current b:panel.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:panel.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Panel panel = (Panel) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = panel.getClientId();

		String jQueryClientID = clientId.replace(":", "_");

		boolean isCollapsible = panel.isCollapsible();
		String accordionParent = panel.getAccordionParent();

		if (isCollapsible && null == accordionParent) {
			rw.startElement("div", panel);
			rw.writeAttribute("class", "panel-group" + Responsive.getResponsiveStyleClass(panel), null);
		}

		String _look = panel.getLook();
		String _title = panel.getTitle();
		String _titleClass = panel.getTitleClass();
		String _styleClass = panel.getStyleClass();
		if (null == _styleClass) {
			_styleClass = "";
		} else {
			_styleClass += " ";
		}

		rw.startElement("div", panel);
		rw.writeAttribute("id", clientId, "id");
		writeAttribute(rw, "dir", panel.getDir(), "dir");

		// render all data-* attributes
		renderPassThruAttributes(context, component, null, true);

		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, panel, rw);
		Tooltip.generateTooltip(context, panel, rw);
		String _style = panel.getStyle();
		if (null != _style && _style.length() > 0) {
			rw.writeAttribute("style", _style, "style");
		}

		if (_look != null) {
			rw.writeAttribute("class", _styleClass + "panel panel-" + _look, "class");
		} else {
			rw.writeAttribute("class", _styleClass + "panel panel-default", "class");
		}

		UIComponent head = panel.getFacet("heading");
		if (head != null || _title != null) {
			rw.startElement("div", panel);
			rw.writeAttribute("class", "panel-heading", "class");
			String _titleStyle = panel.getTitleStyle();
			if (null != _titleStyle) {
				rw.writeAttribute("style", _titleStyle, "style");
			}
			if (_title != null) {
				rw.startElement("h4", panel);
				if (_titleClass != null) {
					rw.writeAttribute("class", _titleClass, "class");
				} else {
					rw.writeAttribute("class", "panel-title", "class");
				}
				if (isCollapsible) {
					writeTitleLink(panel, rw, jQueryClientID, accordionParent);
				}

				rw.writeText(_title, null);
				if (isCollapsible) {
					rw.endElement("a");
				}
				rw.endElement("h4");
			} else {
				if (isCollapsible) {
					writeTitleLink(panel, rw, jQueryClientID, accordionParent);
				}
				head.encodeAll(context);
				if (isCollapsible) {
					rw.endElement("a");
				}
			}
			rw.endElement("div");
		}

		rw.startElement("div", panel);
		rw.writeAttribute("id", jQueryClientID + "content", null);
		writeAttribute(rw, "dir", panel.getDir(), "dir");

		String _contentClass = panel.getContentClass();
		if (null == _contentClass)
			_contentClass = "";
		if (isCollapsible) {
			_contentClass += " panel-collapse collapse"; // in
			if (!panel.isCollapsed())
				_contentClass += " in";
		}
		_contentClass = _contentClass.trim();
		if (_contentClass.length() > 0)
			rw.writeAttribute("class", _contentClass, "class");
		String _contentStyle = panel.getContentStyle();
		if (null != _contentStyle && _contentStyle.length() > 0) {
			rw.writeAttribute("style", _contentStyle, "style");
		}
		// create the body
		rw.startElement("div", panel);
		rw.writeAttribute("class", "panel-body", "class");
	}

	private void writeTitleLink(Panel panel, ResponseWriter rw, String jQueryClientID, String accordionParent)
			throws IOException {
		String sclass = "panel-title-link ";
		rw.startElement("a", panel);
		rw.writeAttribute("data-toggle", "collapse", "null");
		rw.writeAttribute("data-target", "#" + jQueryClientID + "content", "null");
		rw.writeAttribute("style", "display: block;", "style"); //let the anchor
		rw.writeAttribute("href", "javascript:;", "null");
		if (panel.isCollapsed()) {
			sclass += " collapsed";
		}
		rw.writeAttribute("class", sclass, null);
		if(null != accordionParent) {
			rw.writeAttribute("data-parent", "#" + accordionParent, null);
		}
	}

	/**
	 * This methods generates the HTML code of the current b:panel.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:panel.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Panel panel = (Panel) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = panel.getClientId();
		rw.endElement("div"); // panel-body
		UIComponent foot = panel.getFacet("footer");
		if (foot != null) {
			rw.startElement("div", panel); // modal-footer
			rw.writeAttribute("class", "panel-footer", "class");
			foot.encodeAll(context);

			rw.endElement("div"); // panel-footer
		}

		rw.endElement("div");
		rw.endElement("div"); // panel-body
		boolean isCollapsible = panel.isCollapsible();
		String accordionParent = panel.getAccordionParent();

		if (isCollapsible && null == accordionParent) {
			String jQueryClientID = clientId.replace(":", "_");
			rw.endElement("div");
			rw.startElement("input", panel);
			rw.writeAttribute("type", "hidden", null);
			String hiddenInputFieldID = jQueryClientID + "_collapsed";
			rw.writeAttribute("name", hiddenInputFieldID, "name");
			rw.writeAttribute("id", hiddenInputFieldID, "id");
			rw.writeAttribute("value", String.valueOf(panel.isCollapsed()), "value");
			rw.endElement("input");
			Map<String, String> eventHandlers = new HashMap<String, String>();
			eventHandlers.put("expand", "document.getElementById('" + hiddenInputFieldID
					+ "').value='false';");
			eventHandlers.put("collapse", "document.getElementById('" + hiddenInputFieldID
					+ "').value='true';");
			new AJAXRenderer().generateBootsFacesAJAXAndJavaScriptForJQuery(context, component, rw, "#"+jQueryClientID+"content", eventHandlers);
		}
		Tooltip.activateTooltips(context, panel);
	}
	//  $('#j_idt40_j_idt43content').on('show.bs.collapse', function(){ document.getElementById('j_idt40_j_idt43_collapsed').value='false'; });
}
