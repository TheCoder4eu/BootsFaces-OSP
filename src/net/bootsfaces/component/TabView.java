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

import static net.bootsfaces.C.BSFCOMPONENT;
import static net.bootsfaces.C.TAB_VIEW_COMPONENT_TYPE;
import static net.bootsfaces.render.A.ACTIVE;
import static net.bootsfaces.render.A.TAB_ATTRS;
import static net.bootsfaces.render.A.TAB_VIEW_ATTRS;
import static net.bootsfaces.render.H.A;
import static net.bootsfaces.render.H.CLASS;
import static net.bootsfaces.render.H.DIV;
import static net.bootsfaces.render.H.HREF;
import static net.bootsfaces.render.H.ID;
import static net.bootsfaces.render.H.LI;
import static net.bootsfaces.render.H.ROLE;
import static net.bootsfaces.render.H.STYLECLASS;
import static net.bootsfaces.render.H.UL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.R;

/**
 * This class represents and renders a tab strip.
 * 
 * @author 2014 Stephan Rauh (http://www.beyondjava.net).
 */

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
        @ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
        @ResourceDependency(library = "bsf", name = "js/tab.js", target = "body") })
@FacesComponent(TAB_VIEW_COMPONENT_TYPE)
public class TabView extends UIOutput {

    /**
     * The component family for this component.
     */
    public static final String COMPONENT_FAMILY = BSFCOMPONENT;

    /**
     * The standard component type for this component.
     */
    public static final String COMPONENT_TYPE = TAB_VIEW_COMPONENT_TYPE;

    private int currentlyActiveIndex = -1;

    public TabView() {
        setRendererType(null); // this component renders itself
        AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");

    }

    /**
     * Decode be be used to implement an AJAX version of TabView.
     * 
     * @param context
     *            the current FacesContext
     */
    @Override
    public void decode(FacesContext context) {
        super.decode(context);

        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String activeIndexValue = params.get(getClientId(context) + "_activeIndex");

        if (null != activeIndexValue && activeIndexValue.length() > 0) {
            try {
                currentlyActiveIndex = Integer.valueOf(activeIndexValue);
            } catch (NumberFormatException e) {

            }

        }

        // String subVal = (String) context.getExternalContext().getRequestParameterMap().get(getClientId(context));
        // this.setSubmittedValue("on".equals(subVal));
        // this.setValid(true);
    }

    /**
     * Generates the HTML code for the entire TabStrip.
     * 
     * @param context
     *            the current FacesContext
     * @throws IOException
     *             only thrown if something's wrong with the response writer
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
        ResponseWriter writer = context.getResponseWriter();

        Map<String, Object> attributes = getAttributes();
        String clientId = getClientId(context);
        writer.startElement("input", this);
        writer.writeAttribute("type", "hidden", null);
        final String hiddenInputFieldID = clientId + "_activeIndex";
        writer.writeAttribute("name", hiddenInputFieldID, "name");
        writer.writeAttribute("id", hiddenInputFieldID, "id");
        writer.writeAttribute("value", determineActiveIndex(attributes, currentlyActiveIndex), "value");
        writer.endElement("input");

        writer.startElement(UL, this);
        writer.writeAttribute(ID, clientId, ID);
        String classes = "nav nav-tabs";
        if (attributes.containsKey(STYLECLASS)) {
            classes += " ";
            classes += attributes.get(STYLECLASS);
        }
        writer.writeAttribute(CLASS, classes, CLASS);
        String role = "tablist";
        R.encodeHTML4DHTMLAttrs(writer, attributes, TAB_VIEW_ATTRS);
        if (attributes.containsKey(ROLE)) {
            role = (String) attributes.get(ROLE);
        }

        writer.writeAttribute(ROLE, role, ROLE);

        final List<UIComponent> tabs = getTabs();
        encodeTabs(context, writer, tabs, attributes, currentlyActiveIndex, hiddenInputFieldID);
        writer.endElement("ul");
        encodeTabContentPanes(context, writer, this, attributes, currentlyActiveIndex, tabs);
    }

    /**
     * Essentially, getTabs() does the same as getChildren(), but it filters everything that's not a tab. In particular, comments are
     * ignored.<br />
     * See issue 77 (https://github.com/TheCoder4eu/BootsFaces-OSP/issues/77).
     * 
     * @return
     */
    private List<UIComponent> getTabs() {
        List<UIComponent> children = getChildren();
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
            Map<String, Object> attributes, int currentlyActiveIndex, List<UIComponent> tabs) throws IOException {
        writer.startElement(DIV, tabView);
        String classes = "tab-content";
        if (attributes.containsKey("contentClass")) {
            classes += " ";
            classes += net.bootsfaces.render.A.asString(attributes.get("contentClass"));
        }
        writer.writeAttribute(CLASS, classes, CLASS);

        if (attributes.containsKey("contentStyle")) {
            String inlineCSS = net.bootsfaces.render.A.asString(attributes.get("contentStyle"));
            writer.writeAttribute("style", inlineCSS, "style");
        }

        String role = "tablist";
        if (attributes.containsKey(ROLE)) {
            role = (String) attributes.get(ROLE);
        }
        writer.writeAttribute(ROLE, role, ROLE);
        int activeIndex = determineActiveIndex(attributes, currentlyActiveIndex);

        if (null != tabs) {
            for (int index = 0; index < tabs.size(); index++) {
                if (tabs.get(index).isRendered()) {
                    encodeTabPane(context, writer, tabs.get(index), index == activeIndex);
                }
            }
        }
        writer.endElement(DIV);
    }

    /**
     * Which tab is active?
     * 
     * @param attributes
     *            the attribute map of the component to be rendered.
     * @return the index of the active tab. Default is 0.
     */
    private static int determineActiveIndex(Map<String, Object> attributes, int currentlyActiveIndex) {
        int activeIndex = 0;
        if (currentlyActiveIndex >= 0) {
            activeIndex = currentlyActiveIndex;
        } else if (attributes.containsKey("activeIndex")) {
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

    private static void encodeTabPane(FacesContext context, ResponseWriter writer, UIComponent tab, boolean isActive)
            throws IOException {
        writer.startElement(DIV, tab);
        writer.writeAttribute(ID, tab.getClientId().replace(":", "_"), ID);
        String classes = "tab-pane";
        if (isActive) {
            classes += " active";
        }
        Map<String, Object> tabAttributes = tab.getAttributes();
        if (tabAttributes.containsKey(STYLECLASS)) {
            classes += " ";
            classes += tabAttributes.get(STYLECLASS);
        }
        if (tabAttributes.get("contentStyle") != null) {
            writer.writeAttribute("style", tabAttributes.get("contentStyle"), "style");
        }

        writer.writeAttribute(CLASS, classes, CLASS);
        tab.encodeChildren(context);
        writer.endElement(DIV);
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
            Map<String, Object> attributes, int currentlyActiveIndex, String hiddenInputFieldID) throws IOException {
        if (null != children) {
            int activeIndex = determineActiveIndex(attributes, currentlyActiveIndex);
            for (int index = 0; index < children.size(); index++) {
                encodeTab(context, writer, children.get(index), index == activeIndex, hiddenInputFieldID, index);
            }
        }
    }

    /**
     * Generate an individual tab. Basically, that's &lt;li role="presentation" class="active"&gt&lt;a href="#{clientID}" role="tab"
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
    private static void encodeTab(FacesContext context, ResponseWriter writer, UIComponent tab, boolean isActive,
            String hiddenInputFieldID, int tabIndex) throws IOException {
        if (!tab.isRendered())
            return;
        writer.startElement(LI, tab);
        writer.writeAttribute(ROLE, "presentation", ROLE);
        Map<String, Object> tabAttributes = tab.getAttributes();
        String classes = isActive ? ACTIVE : "";
        if (tabAttributes.get(STYLECLASS) != null) {
            classes += " ";
            classes += tabAttributes.get(STYLECLASS);
        }
        if (classes.length() > 0)
            writer.writeAttribute(CLASS, classes, CLASS);
        if (tabAttributes.get("style") != null) {
            writer.writeAttribute("style", tabAttributes.get("style"), "style");
        }

        encodeTabAnchorTag(writer, tab, tabAttributes, hiddenInputFieldID, tabIndex);
        writer.endElement(LI);
    }

    /**
     * Generate the clickable entity of the tab.
     *
     * @param writer
     *            the response writer
     * @param tab
     *            the tab to be rendered.
     * @param tabAttributes
     *            the attribute list of the tab.
     * @throws IOException
     *             only thrown if something's wrong with the response writer
     */
    private static void encodeTabAnchorTag(ResponseWriter writer, UIComponent tab, Map<String, Object> tabAttributes,
            String hiddenInputFieldID, int tabindex) throws IOException {
        writer.startElement(A, tab);
        writer.writeAttribute(ROLE, "tab", ROLE);
        writer.writeAttribute("data-toggle", "tab", "data-toggle");
        writer.writeAttribute(HREF, "#" + tab.getClientId().replace(":", "_"), HREF);
        String onclick = "document.getElementById('" + hiddenInputFieldID + "').value='" + String.valueOf(tabindex)
                + "';";
        String userClick = (String) tabAttributes.get("onclick");
        if (null != userClick && userClick.trim().length() > 0) {
            onclick += userClick;
        }
        writer.writeAttribute("onclick", onclick, "onclick");
        R.encodeHTML4DHTMLAttrs(writer, tabAttributes, TAB_ATTRS);
        writer.writeText(tabAttributes.get("title"), null);
        writer.endElement(A);
    }
}
