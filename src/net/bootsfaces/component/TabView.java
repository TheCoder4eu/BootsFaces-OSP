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
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;

/**
 * This class represents and renders a checkbox.
 * 
 * @author 2014 Stephan Rauh (http://www.beyondjava.net).
 */

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/tab.js", target = "head") })
@FacesComponent(C.TAB_VIEW_COMPONENT_TYPE)
public class TabView extends HtmlInputText {

	/**
	 * The component family for this component.
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	/**
	 * The standard component type for this component.
	 */
	public static final String COMPONENT_TYPE = C.TAB_VIEW_COMPONENT_TYPE;

	public TabView() {
		setRendererType(null); // this component renders itself
	}

	/** Decode be be used to implement an AJAX version of TabView. */
	@Override
	public void decode(FacesContext context) {
		super.decode(context);
		// String subVal = (String) context.getExternalContext().getRequestParameterMap().get(getClientId(context));
		// this.setSubmittedValue("on".equals(subVal));
		// this.setValid(true);
	}

	/**
	 * Generates the HTML code for the entire TabStrip.
	 * 
	 * @param content
	 *            the current FacesContext
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = getClientId(context);
		Map<String, Object> attributes = getAttributes();

		// writer.startElement("div", this);
		// writer.writeAttribute("class", "row", "class");
		// writer.startElement("div", this);
		// writer.writeAttribute("class", "col", "col-md-12");
		writer.startElement("ul", this);
		writer.writeAttribute("id", clientId, "id");
		String classes = "nav nav-tabs";
		if (attributes.containsKey("styleClass")) {
			classes += " ";
			classes += attributes.get("styleClass");
		}
		writer.writeAttribute("class", classes, "class");
		String role = "tablist";
		if (attributes.containsKey("role")) {
			role = (String) attributes.get("role");
		}

		writer.writeAttribute("role", role, "role");

		encodeTabs(context, writer, getChildren(), attributes);
		writer.endElement("ul");
		// writer.endElement("div");
		// writer.endElement("div");
		encodeTabContentPanes(context, writer, this, attributes);
	}

	/**
	 * Generates the HTML of the tab panes.
	 * 
	 * @param content
	 *            the current FacesContext
	 * @param writer
	 *            the response writer
	 * @param children
	 *            the tabs
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */
	private static void encodeTabContentPanes(FacesContext context, ResponseWriter writer, TabView tabView, Map<String, Object> attributes)
			throws IOException {
		writer.startElement("div", tabView);
		String classes = "tab-content";
		if (attributes.containsKey("contentClass")) {
			classes += " ";
			classes += attributes.get("contentClass");
		}
		writer.writeAttribute("class", classes, "class");
		String role = "tablist";
		if (attributes.containsKey("role")) {
			role = (String) attributes.get("role");
		}
		writer.writeAttribute("role", role, "role");
		int activeIndex = determineActiveIndex(attributes);

		List<UIComponent> children = tabView.getChildren();
		if (null != children) {
			for (int index = 0; index < children.size(); index++) {
				encodeTabPane(context, writer, children.get(index), index == activeIndex);
			}
		}
		writer.endElement("div");
	}

	private static int determineActiveIndex(Map<String, Object> attributes) {
		int activeIndex = 0;
		if (attributes.containsKey("activeIndex")) {
			String ai = (String) attributes.get("activeIndex");
			try {
				activeIndex = Integer.valueOf(ai);
			} catch (Exception e) {
				throw new FacesException("Illegal value of TabView.activeIndex", e);
			}
		}
		return activeIndex;
	}

	/**
	 * Generate an individual tab pane. Basically, that's &lt;div role="tabpanel" class="tab-pane active" id="home"&lt; {{childContent}}
	 * &gt;/div&gt;
	 *
	 * @param content
	 *            the current FacesContext
	 * @param writer
	 *            the response writer
	 * @param tab
	 *            the tab to be rendered.
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */

	private static void encodeTabPane(FacesContext context, ResponseWriter writer, UIComponent tab, boolean isActive) throws IOException {
		writer.startElement("div", tab);
		writer.writeAttribute("id", tab.getClientId().replace(":", "_"), "id");
		String classes = "tab-pane";
		if (isActive) {
			classes += " active";
		}
		Map<String, Object> tabAttributes = tab.getAttributes();
		if (tabAttributes.containsKey("styleClass")) {
			classes += " ";
			classes += tabAttributes.get("styleClass");
		}
		writer.writeAttribute("class", classes, "class");
		tab.encodeChildren(context);
		writer.endElement("div");
	}

	/**
	 * Generates the HTML of the tabs.
	 * 
	 * @param content
	 *            the current FacesContext
	 * @param writer
	 *            the response writer
	 * @param children
	 *            the tabs
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */
	private static void encodeTabs(FacesContext context, ResponseWriter writer, List<UIComponent> children, Map<String, Object> attributes)
			throws IOException {
		if (null != children) {
			int activeIndex = determineActiveIndex(attributes);
			for (int index = 0; index < children.size(); index++) {
				encodeTab(context, writer, children.get(index), index == activeIndex);
			}
		}
	}

	/**
	 * Generate an individual tab. Basically, that's &lt;li role="presentation" class="active"&gt&lt;a href="#{clientID}" role="tab"
	 * data-toggle="tab"&lt; {{title}} &gt;/a&gt;
	 *
	 * @param content
	 *            the current FacesContext
	 * @param writer
	 *            the response writer
	 * @param tab
	 *            the tab to be rendered.
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */

	private static void encodeTab(FacesContext context, ResponseWriter writer, UIComponent tab, boolean isActive) throws IOException {
		writer.startElement("li", tab);
		writer.writeAttribute("role", "presentation", "role");
		Map<String, Object> tabAttributes = tab.getAttributes();
		String classes = isActive ? "active" : "";
		if (tabAttributes.containsKey("styleClass")) {
			classes += " ";
			classes += tabAttributes.get("styleClass");
		}
		writer.writeAttribute("class", classes, "class");
		writer.startElement("a", tab);
		writer.writeAttribute("role", "tab", "role");
		writer.writeAttribute("data-toggle", "tab", "data-toggle");
		writer.writeAttribute("href", "#" + tab.getClientId().replace(":", "_"), "href");
		writer.writeText(tabAttributes.get("title"), null);
		writer.endElement("a");
		writer.endElement("li");
	}
}
