/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.DataModel;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.tab.Tab;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:tabView /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.tabView.TabView")
public class TabViewRenderer extends CoreRenderer {

	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.component.tabView.TabViewRenderer");

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// TODO Auto-generated method stub
		// super.encodeChildren(context, component);
	}

	/**
	 * Decode to be used to implement an AJAX version of TabView. This methods
	 * receives and processes input made by the user. More specifically, it ckecks
	 * whether the user has interacted with the current b:tabView. The default
	 * implementation simply stores the input value in the list of submitted values.
	 * If the validation checks are passed, the values in the
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
		if (!tabView.isRendered()) {
			return;
		}
		if (tabView.isDisabled()) {
			return;
		}

		decodeBehaviors(context, tabView);

		String clientId = tabView.getClientId(context);
		String activeIndexId = clientId.replace(":", "_") + "_activeIndex";
		String newIndexValue = (String) context.getExternalContext().getRequestParameterMap().get(activeIndexId);

		new AJAXRenderer().decode(context, component);
		if (null != newIndexValue && newIndexValue.length() > 0) {
			try {
				if (Integer.valueOf(newIndexValue) != tabView.getActiveIndex()) {
					int newIndex = Integer.valueOf(newIndexValue);
					if (newIndex < tabView.getChildCount()) {
						UIComponent maybeTab = tabView.getChildren().get(newIndex);
						if (maybeTab instanceof Tab) {
							if (!(((Tab) maybeTab).isDisabled())) {
								ValueExpression ve = component.getValueExpression("activeIndex");
								if (ve != null) {
									try {
										ve.setValue(context.getELContext(), newIndex);
									} catch (ELException e) {
										tabView.setActiveIndex(newIndex);
									} catch (Exception e) {
										tabView.setActiveIndex(newIndex);
									}
								} else {
									tabView.setActiveIndex(newIndex);
								}
	
							}
						}
					}
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
		assertComponentIsInsideForm(component,
				"The initially opened tab is opened after an post-back request, regardless which tab has been previously activated by the user");
		if (!component.isRendered()) {
			return;
		}
		TabView tabView = (TabView) component;
		ResponseWriter writer = context.getResponseWriter();
		String clientId = tabView.getClientId();
		if (!tabView.isRendered()) {
			return;
		}

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
		if ("bottom".equalsIgnoreCase(tabPosition))
			wrapperClass += " tabs-below";

		writer.startElement("div", tabView);
		writer.writeAttribute("class", wrapperClass, "class");
		writer.writeAttribute("role", "tabpanel", "class");
		writer.writeAttribute("dir", tabView.getDir(), "dir");

		final List<UIComponent> tabs = getTabs(tabView);

		int activeIndex = forceActiveTabToBeEnabled(tabView, tabs);

		if ("bottom".equalsIgnoreCase(tabPosition)) {
			encodeTabContentPanes(context, writer, tabView, activeIndex, tabs);
			encodeTabLinks(context, writer, tabView, activeIndex, tabs, clientId, hiddenInputFieldID);
		} else if ("left".equalsIgnoreCase(tabPosition)) {
			writer.startElement("div", component);
			writer.writeAttribute("class", "col-md-2", "class");
			encodeTabLinks(context, writer, tabView, activeIndex, tabs, clientId, hiddenInputFieldID);
			writer.endElement("div");
			writer.startElement("div", component);
			writer.writeAttribute("class", "col-md-10", "class");
			encodeTabContentPanes(context, writer, tabView, activeIndex, tabs);
			writer.endElement("div");
			drawClearDiv(writer, tabView);
		} else if ("right".equalsIgnoreCase(tabPosition)) {
			writer.startElement("div", component);
			writer.writeAttribute("class", "col-md-10", "class");
			encodeTabContentPanes(context, writer, tabView, activeIndex, tabs);
			writer.endElement("div");
			writer.startElement("div", component);
			writer.writeAttribute("class", "col-md-2", "class");
			encodeTabLinks(context, writer, tabView, activeIndex, tabs, clientId, hiddenInputFieldID);
			writer.endElement("div");
			drawClearDiv(writer, tabView);
		} else {
			encodeTabLinks(context, writer, tabView, activeIndex, tabs, clientId, hiddenInputFieldID);
			encodeTabContentPanes(context, writer, tabView, activeIndex, tabs);
		}

		writer.endElement("div");
		Map<String, String> eventHandlers = new HashMap<String, String>();
		eventHandlers.put("shown","if(typeof PrimeFaces != 'undefined'){PrimeFaces.invokeDeferredRenders('" + clientId + "_content');}");
		new AJAXRenderer().generateBootsFacesAJAXAndJavaScriptForJQuery(context, component, writer,
				"#" + clientId + " > li > a[data-toggle=\"tab\"]", eventHandlers);
		Tooltip.activateTooltips(context, tabView);
	}

	/**
	 * returns the currently active tab. If the attribute getActiveIndex() points to
	 * an inactive tab, the next active tag is returned. Important: this method must
	 * not call setActiveIndex(), because this means the you can't control the tabs
	 * from a Java bean. Setting an attributes overrides EL expressions defined by
	 * the JSF page.
	 * 
	 * @param tabView
	 * @param tabs
	 * @return Integer.MAX_VALUE if there's no active tab.
	 */
	private int forceActiveTabToBeEnabled(TabView tabView, final List<UIComponent> tabs) {
		List<Integer> tabNumberToArrayIndex = new ArrayList<Integer>();
		
		int maxIndex = 0;
		for (UIComponent t: tabs) {
			DataModel<?> dm = ((Tab)t).getDataModel();
			int count = dm.getRowCount();
			if (count > 0) {
				for (int i = 0; i < count; i++) {
					tabNumberToArrayIndex.add(maxIndex);
					
				}
				maxIndex++;
			} else if (count == 0) {
				tabNumberToArrayIndex.add(maxIndex);
				maxIndex++;
			} else {
				// unknown number of tabs
				return tabView.getActiveIndex();
			}
		}
		
		int activeTabNumber = tabView.getActiveIndex();
		if (tabView.isDisabled()) {
			activeTabNumber = Integer.MAX_VALUE;
		}
		if (activeTabNumber < 0) {
			activeTabNumber = tabNumberToArrayIndex.size() + activeTabNumber; // -2 is the second tab
														// from the right hand
														// side
		}
		if (activeTabNumber >= 0 && activeTabNumber < Integer.MAX_VALUE) {

			if (activeTabNumber >= tabNumberToArrayIndex.size()) {
				activeTabNumber = tabNumberToArrayIndex.size() - 1;
			}
			int newActiveTabNumber = activeTabNumber;
			while (((Tab) tabs.get(tabNumberToArrayIndex.get(newActiveTabNumber))).isDisabled()) {
				newActiveTabNumber++;
				if (newActiveTabNumber >= tabNumberToArrayIndex.size()) {
					newActiveTabNumber = 0;
				}
				if (newActiveTabNumber == activeTabNumber) {
					// this happens if every tab is inactive
					newActiveTabNumber = Integer.MAX_VALUE;
					break;
				}
			}
			if (activeTabNumber != newActiveTabNumber) {
				ValueExpression ve = tabView.getValueExpression("activeIndex");
				if (ve != null) {
					try {
						ve.setValue(FacesContext.getCurrentInstance().getELContext(), newActiveTabNumber);
					} catch (ELException e) {
					} catch (Exception e) {
					}
				}
			}
			activeTabNumber = newActiveTabNumber;
		}
		return activeTabNumber;
	}

	/**
	 * Draw a clear div
	 * 
	 * @param writer
	 * @param tabView
	 * @throws IOException
	 */
	private static void drawClearDiv(ResponseWriter writer, UIComponent tabView) throws IOException {
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
		if ("left".equalsIgnoreCase(tabView.getTabPosition()) || "right".equalsIgnoreCase(tabView.getTabPosition())) {
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
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, tabView, writer, false);
		R.encodeHTML4DHTMLAttrs(writer, tabView.getAttributes(), new String[] { "style" });
		if (tabView.getRole() != null) {
			role = tabView.getRole();
		}

		writer.writeAttribute("role", role, "role");

		encodeTabs(context, writer, tabs, currentlyActiveIndex, hiddenInputFieldID, tabView.isDisabled());
		writer.endElement("ul");
	}

	/**
	 * Essentially, getTabs() does the same as getChildren(), but it filters
	 * everything that's not a tab. In particular, comments are ignored. See issue
	 * 77 (https://github.com/TheCoder4eu/BootsFaces-OSP/issues/77).
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
	private static void encodeTabContentPanes(final FacesContext context, final ResponseWriter writer,
			final TabView tabView, final int currentlyActiveIndex, final List<UIComponent> tabs) throws IOException {
		writer.startElement("div", tabView);		
		writer.writeAttribute("id", tabView.getClientId() + "_content", "id");		
		String classes = "tab-content ui-hidden-container";		
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

  if (null != tabs) {
   int numberOfTabsRendered = 0;
   int offset = 0;
   for (int index = 0; index < tabs.size(); index++) {
    final Tab tab = (Tab) tabs.get(index);
    if (tab.isRendered()) {
     final int currentIndex = numberOfTabsRendered;
     final int currentOffset = offset;
     Runnable r = new Runnable() {
      public void run() {
       try {
        encodeTabPane(context, writer, tab,
          (currentIndex+currentOffset == currentlyActiveIndex) && (!tabView.isDisabled()));
       } catch (IOException ex) {
        // exotic case, suffice it to log it
        LOGGER.log(Level.SEVERE, "An exception occurred while rendering a tab.", ex);
       }
      }
     };

     if (tab.getValue() == null) {
      r.run();
      numberOfTabsRendered ++;
     } else {
      numberOfTabsRendered += ((Tab)tabs.get(currentIndex)).encodeTabs(context, r);
     }
    } else {
      offset++;  
    }
   }
  }
  writer.endElement("div");
 }

	/**
	 * Generate an individual tab pane. Basically, that's &lt;div role="tabpanel"
	 * class="tab-pane active" id="home"&lt; {{childContent}} &gt;/div&gt;
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

	private static void encodeTabPane(final FacesContext context, final ResponseWriter writer, final UIComponent child,
			final boolean isActive) throws IOException {

		Tab tab = (Tab) child;
		writer.startElement("div", tab);
		writer.writeAttribute("id", tab.getClientId().replace(":", "_") + "_pane", "id");
		if (tab.getDir() != null)
			writer.writeAttribute("dir", tab.getDir(), "dir");
		String classes = "tab-pane";
		if (!tab.isDisabled()) {
			if (isActive) {
				classes += " active";
			}
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
	private static void encodeTabs(final FacesContext context, final ResponseWriter writer, final List<UIComponent> children,
			final int currentlyActiveIndex, final String hiddenInputFieldID, final boolean disabled) throws IOException {
		if (null != children) {
			int tabIndex = 0;

			for (int index = 0; index < children.size(); index++) {	
				// todo what's the meaning of those countless parameters?
				final int loopIndex = index;
				final int currentIndex = tabIndex;
				final Tab tab = (Tab)children.get(loopIndex);
				Runnable r = new Runnable() {
					int offset = 0;
					public void run() {
						try {
							encodeTab(context, writer, tab, currentIndex + offset == currentlyActiveIndex, hiddenInputFieldID,
									currentIndex + offset, disabled);
							offset++;
						} catch (IOException ex) {
							// exotic case, suffice it to log it
							LOGGER.log(Level.SEVERE, "An exception occurred while rendering a tab.", ex);
						}
					}
				};

				if (((Tab) children.get(index)).getValue() == null) {
					r.run();
					tabIndex ++;
				} else {
					tabIndex += tab.encodeTabs(context, r);
				}
			}
		}
	}

	/**
	 * Generate an individual tab. Basically, that's &lt;li role="presentation"
	 * class="active"&gt&lt;a href="#{clientID}" role="tab" data-toggle="tab"&lt;
	 * {{title}} &gt;/a&gt;
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
			String hiddenInputFieldID, int tabIndex, boolean disabled) throws IOException {
		Tab tab = (Tab) child;
		if (!tab.isRendered())
			return;
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
		if (tab.isDisabled() || disabled) {
			classes += " disabled";
		}
		if (classes.length() > 0) {
			writer.writeAttribute("class", classes.trim(), "class");
		}
		if (tab.isDisabled() || disabled) {
			writer.writeAttribute("onclick", "event.preventDefault(); return false;", null);
		}

		if (tab.getStyle() != null) {
			writer.writeAttribute("style", tab.getStyle(), "style");
		}

		encodeTabAnchorTag(context, writer, tab, hiddenInputFieldID, tabIndex, disabled);
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
			String hiddenInputFieldID, int tabindex, boolean disabled) throws IOException {
		writer.startElement("a", tab);
		writer.writeAttribute("id", tab.getClientId().replace(":", "_") + "_tab", "id");
		writer.writeAttribute("role", "tab", "role");
		if (tab.isDisabled() || disabled) {
			writer.writeAttribute("onclick", "event.preventDefault(); return false;", null);
		} else {
			writer.writeAttribute("data-toggle", "tab", "data-toggle");
			writer.writeAttribute("href", "#" + tab.getClientId().replace(":", "_") + "_pane", "href");
			String onclick = "document.getElementById('" + hiddenInputFieldID + "').value='" + String.valueOf(tabindex)
					+ "';";
			AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, tab, writer, "click", onclick, false, true);
		}
		R.encodeHTML4DHTMLAttrs(writer, tab.getAttributes(), new String[] { "style", "tabindex" });

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
