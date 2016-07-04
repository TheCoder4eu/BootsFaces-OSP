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

package net.bootsfaces.component.tabView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.tab.Tab;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:tabView /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.tabView.TabView")
public class TabViewRenderer extends CoreRenderer {

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// TODO Auto-generated method stub
		//super.encodeChildren(context, component);
	}

	/**
	 * Decode to be used to implement an AJAX version of TabView. This methods
	 * receives and processes input made by the user. More specifically, it
	 * ckecks whether the user has interacted with the current b:tabView. The
	 * default implementation simply stores the input value in the list of
	 * submitted values. If the validation checks are passed, the values in the
	 * <code>submittedValues</code> list are store in the backend bean.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:tabView.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		TabView tabView = (TabView) component;

		decodeBehaviors(context, tabView);

		String clientId = tabView.getClientId(context);
		String activeIndexId = clientId.replace(":", "_") + "_activeIndex";
		String activeIndexValue = (String) context.getExternalContext().getRequestParameterMap().get(activeIndexId);

		new AJAXRenderer().decode(context, component);
		if (null != activeIndexValue && activeIndexValue.length() > 0) {
			try {
				if (Integer.valueOf(activeIndexValue) != tabView.getActiveIndex()) {
					tabView.setActiveIndex(Integer.valueOf(activeIndexValue));
				}
			} catch (NumberFormatException e) {

			}
		}
	}

	/**
	 * This methods generates the HTML code of the current b:tabView.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:tabView.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		assertComponentIsInsideForm(component, "The initially opened tab is opened after an post-back request, regardless which tab has been previously activated by the user");
		if (!component.isRendered()) {
			return;
		}
		TabView tabView = (TabView) component;
		ResponseWriter writer = context.getResponseWriter();
		String clientId = tabView.getClientId();

		writer.startElement("input", tabView);
		writer.writeAttribute("type", "hidden", null);
		final String hiddenInputFieldID = clientId.replace(":", "_") + "_activeIndex";
		writer.writeAttribute("name", hiddenInputFieldID, "name");
		writer.writeAttribute("id", hiddenInputFieldID, "id");
		writer.writeAttribute("value", "", "value");
		writer.endElement("input");

		// set tab directions
		final String tabPosition = tabView.getTabPosition();
		String wrapperClass = "tab-panel";
		if("bottom".equalsIgnoreCase(tabPosition)) wrapperClass += " tabs-below";

		writer.startElement("div", tabView);
		writer.writeAttribute("class", wrapperClass, "class");
		writer.writeAttribute("role", "tabpanel", "class");
		writer.writeAttribute("dir", tabView.getDir(), "dir");

		final List<UIComponent> tabs = getTabs(tabView);

		if("bottom".equalsIgnoreCase(tabPosition)) {
			encodeTabContentPanes(context, writer, tabView, tabView.getActiveIndex(), tabs);
			encodeTabLinks(context, writer, tabView, tabView.getActiveIndex(), tabs, clientId, hiddenInputFieldID);
		} else if("left".equalsIgnoreCase(tabPosition)) {
			writer.startElement("div", component);
			writer.writeAttribute("class", "col-md-2", "class");
			encodeTabLinks(context, writer, tabView, tabView.getActiveIndex(), tabs, clientId, hiddenInputFieldID);
			writer.endElement("div");
			writer.startElement("div", component);
			writer.writeAttribute("class", "col-md-10", "class");
			encodeTabContentPanes(context, writer, tabView, tabView.getActiveIndex(), tabs);
			writer.endElement("div");
			drawClearDiv(writer, tabView);
		} else if("right".equalsIgnoreCase(tabPosition)) {
			writer.startElement("div", component);
			writer.writeAttribute("class", "col-md-10", "class");
			encodeTabContentPanes(context, writer, tabView, tabView.getActiveIndex(), tabs);
			writer.endElement("div");
			writer.startElement("div", component);
			writer.writeAttribute("class", "col-md-2", "class");
			encodeTabLinks(context, writer, tabView, tabView.getActiveIndex(), tabs, clientId, hiddenInputFieldID);
			writer.endElement("div");
			drawClearDiv(writer, tabView);
		} else {
			encodeTabLinks(context, writer, tabView, tabView.getActiveIndex(), tabs, clientId, hiddenInputFieldID);
			encodeTabContentPanes(context, writer, tabView, tabView.getActiveIndex(), tabs);
		}

		writer.endElement("div");
		new AJAXRenderer().generateBootsFacesAJAXAndJavaScriptForJQuery(context, component, writer, "#" + clientId + " > li > a[data-toggle=\"tab\"]", null);
		Tooltip.activateTooltips(context, tabView);
	}

	/**
	 * Draw a clear div
	 * @param writer
	 * @param tabView
	 * @throws IOException
	 */
	private static void drawClearDiv(ResponseWriter writer, UIComponent tabView)
	throws IOException {
		writer.startElement("div", tabView);
		writer.writeAttribute("style", "clear:both;", "style");
		writer.endElement("div");
	}

	/**
	 * Encode the list of links that render the tabs
	 *
	 * @param context
	 * @param writer
	 * @param tabView
	 * @param currentlyActiveIndex
	 * @param tabs
	 * @param clientId
	 * @param hiddenInputFieldID
	 * @throws IOException
	 */
	private static void encodeTabLinks(FacesContext context, ResponseWriter writer, TabView tabView,
			int currentlyActiveIndex, List<UIComponent> tabs, String clientId, String hiddenInputFieldID)
	throws IOException {
		writer.startElement("ul", tabView);
		writer.writeAttribute("id", clientId, "id");
		Tooltip.generateTooltip(context, tabView, writer);
		String classes = "nav ";
		if("left".equalsIgnoreCase(tabView.getTabPosition()) || "right".equalsIgnoreCase(tabView.getTabPosition())) {
			classes += " nav-pills nav-stacked";
		} else {
			classes = classes + (tabView.isPills() ? " nav-pills" : " nav-tabs");
		}

		if (tabView.getStyleClass() != null) {
			classes += " ";
			classes += tabView.getStyleClass();
		}

		writer.writeAttribute("class", classes, "class");

		String role = "tablist";
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, tabView, writer);
		R.encodeHTML4DHTMLAttrs(writer, tabView.getAttributes(), H.TAB_VIEW);
		if (tabView.getRole() != null) {
			role = tabView.getRole();
		}

		writer.writeAttribute("role", role, "role");

		encodeTabs(context, writer, tabs, currentlyActiveIndex, hiddenInputFieldID);
		writer.endElement("ul");
	}

	/**
	 * Essentially, getTabs() does the same as getChildren(), but it filters
	 * everything that's not a tab. In particular, comments are ignored. See
	 * issue 77 (https://github.com/TheCoder4eu/BootsFaces-OSP/issues/77).
	 *
	 * @return
	 */
	private List<UIComponent> getTabs(TabView tabView) {
		List<UIComponent> children = tabView.getChildren();
		List<UIComponent> filtered = new ArrayList<UIComponent>(children.size());
		for (UIComponent c : children) {
			if (c instanceof Tab)
				filtered.add(c);
		}
		return filtered;
	}

	/**
	 * Generates the HTML of the tab panes.
	 *
	 * @param context
	 *            the current FacesContext
	 * @param writer
	 *            the response writer
	 * @param tabs
	 * @param children
	 *            the tabs
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */
	private static void encodeTabContentPanes(FacesContext context, ResponseWriter writer, TabView tabView,
			int currentlyActiveIndex, List<UIComponent> tabs) throws IOException {
		writer.startElement("div", tabView);
		String classes = "tab-content";
		if (tabView.getContentClass() != null) {
			classes += " ";
			classes += tabView.getContentClass();
		}
		writer.writeAttribute("class", classes, "class");

		if (tabView.getContentStyle() != null) {
			String inlineCSS = tabView.getContentStyle();
			writer.writeAttribute("style", inlineCSS, "style");
		}

		String role = "tablist";
		if (tabView.getRole() != null) {
			role = tabView.getRole();
		}
		writer.writeAttribute("role", role, "role");
		// int activeIndex = tabView.getActiveIndex();

		if (null != tabs) {
			for (int index = 0; index < tabs.size(); index++) {
				if (tabs.get(index).isRendered()) {
					encodeTabPane(context, writer, tabs.get(index), index == currentlyActiveIndex);
				}
			}
		}
		writer.endElement("div");
	}

	/**
	 * Generate an individual tab pane. Basically, that's &lt;div
	 * role="tabpanel" class="tab-pane active" id="home"&lt; {{childContent}}
	 * &gt;/div&gt;
	 *
	 * @param context
	 *            the current FacesContext
	 * @param writer
	 *            the response writer
	 * @param tab
	 *            the tab to be rendered.
	 * @param isActive
	 *            is the current tab active?
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */

	private static void encodeTabPane(FacesContext context, ResponseWriter writer, UIComponent child, boolean isActive)
			throws IOException {
		Tab tab = (Tab) child;
		writer.startElement("div", tab);
		writer.writeAttribute("id", tab.getClientId().replace(":", "_")+"_pane", "id");
		if (tab.getDir()!=null)
			writer.writeAttribute("dir", tab.getDir(), "dir");
		String classes = "tab-pane";
		if (isActive) {
			classes += " active";
		}
		if (tab.getStyleClass() != null) {
			classes += " ";
			classes += tab.getStyleClass();
		}
		if (tab.getContentStyle() != null) {
			writer.writeAttribute("style", tab.getContentStyle(), "style");
		}

		writer.writeAttribute("class", classes, "class");
		tab.encodeChildren(context);
		writer.endElement("div");
	}

	/**
	 * Generates the HTML of the tabs.
	 *
	 * @param context
	 *            the current FacesContext
	 * @param writer
	 *            the response writer
	 * @param children
	 *            the tabs
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */
	private static void encodeTabs(FacesContext context, ResponseWriter writer, List<UIComponent> children,
			int currentlyActiveIndex, String hiddenInputFieldID) throws IOException {
		if (null != children) {
			for (int index = 0; index < children.size(); index++) {
				encodeTab(context, writer, children.get(index), index == currentlyActiveIndex, hiddenInputFieldID, index);
			}
		}
	}

	/**
	 * Generate an individual tab. Basically, that's &lt;li role="presentation"
	 * class="active"&gt&lt;a href="#{clientID}" role="tab"
	 * data-toggle="tab"&lt; {{title}} &gt;/a&gt;
	 *
	 * @param context
	 *            the current FacesContext
	 * @param writer
	 *            the response writer
	 * @param tab
	 *            the tab to be rendered.
	 * @param isActive
	 *            is the current tab active?
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */
	private static void encodeTab(FacesContext context, ResponseWriter writer, UIComponent child, boolean isActive,
			String hiddenInputFieldID, int tabIndex) throws IOException {
		Tab tab = (Tab) child;
		if (!tab.isRendered())
			return;
		writer.append("\n<!-- tab #" + tabIndex + "-->\n");
		writer.startElement("li", tab);
		if (tab.getDir() != null)
			writer.writeAttribute("dir", tab.getDir(), "dir");
		writer.writeAttribute("id", tab.getClientId(), "id");
		writer.writeAttribute("role", "presentation", "role");

		Tooltip.generateTooltip(context, tab, writer);

		String classes = isActive ? "active" : "";
		if (tab.getStyleClass() != null) {
			classes += " ";
			classes += tab.getStyleClass();
		}
		if (classes.length() > 0) {
			writer.writeAttribute("class", classes, "class");
		}

		if (tab.getStyle() != null) {
			writer.writeAttribute("style", tab.getStyle(), "style");
		}

		encodeTabAnchorTag(context, writer, tab, hiddenInputFieldID, tabIndex);
		writer.endElement("li");
		Tooltip.activateTooltips(context, tab);
	}

	/**
	 * Generate the clickable entity of the tab.
	 *
	 * @param writer
	 *            the response writer
	 * @param tab
	 *            the tab to be rendered.
	 * @throws IOException
	 *             only thrown if something's wrong with the response writer
	 */
	private static void encodeTabAnchorTag(FacesContext context, ResponseWriter writer, Tab tab,
			String hiddenInputFieldID, int tabindex) throws IOException {
		writer.startElement("a", tab);
		writer.writeAttribute("id", tab.getClientId().replace(":", "_") + "_tab", "id");
		writer.writeAttribute("role", "tab", "role");
		writer.writeAttribute("data-toggle", "tab", "data-toggle");
		writer.writeAttribute("href", "#" + tab.getClientId().replace(":", "_")+"_pane", "href");
		String onclick = "document.getElementById('" + hiddenInputFieldID + "').value='" + String.valueOf(tabindex) + "';";
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, tab, writer, "click", onclick,false);
		R.encodeHTML4DHTMLAttrs(writer, tab.getAttributes(), H.TAB);

		UIComponent iconFacet = tab.getFacet("anchor");
		if (null != iconFacet) {
			iconFacet.encodeAll(FacesContext.getCurrentInstance());
			if (null != tab.getTitle()) {
				writer.writeText(" " + tab.getTitle(), null);
			}
		} else {
			writer.writeText(tab.getTitle(), null);
		}
		writer.endElement("a");
	}
}
