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

package net.bootsfaces.component.navLink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.ProjectStage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.component.navBarLinks.NavBarLinks;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:navLink /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.navLink.NavLink")
public class NavLinkRenderer extends CoreRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		if (componentIsDisabledOrReadonly(component)) {
			return;
		}

		String param = component.getClientId(context);
		if (context.getExternalContext().getRequestParameterMap().containsKey(param)) {
			new AJAXRenderer().decode(context, component);
		}
	}

	/**
	 * This methods generates the HTML code of the current b:navLink.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:navLink.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		AbstractNavLink navlink = (AbstractNavLink) component;
		if (!navlink.isRendered()) {
			return;
		}

		// If there is the header attribute, we only render a Header
		String head = navlink.getHeader();
		if (head != null) {
			encodeHeader(context, head, (UIComponent) navlink);
		} else {
			// if there is no href, no outcome, no child and no value we render
			// a divider
			if ((navlink.getValue() == null) && (((UIComponent)navlink).getChildCount() == 0)) {
				encodeDivider(context, navlink);
			} else {
				encodeHTML(context, (UIComponent) navlink);
			}
		} // if header
		Tooltip.activateTooltips(context, component);
	}

	public void encodeHeader(FacesContext context, String h, UIComponent navlink) throws IOException {
		ResponseWriter rw = context.getResponseWriter();

		rw.startElement("li", navlink);
		writeAttribute(rw, "id", navlink.getClientId(context), "id");
		String styleClass = ((AbstractNavLink)navlink).getStyleClass();
		if (null == styleClass)
			writeAttribute(rw, "class", "dropdown-header", "class");
		else
			writeAttribute(rw, "class", "dropdown-header " + styleClass, "class");
		writeAttribute(rw, "style", ((AbstractNavLink)navlink).getStyle(), "style");
		writeAttribute(rw, "role", "presentation", null);
		rw.writeText(h, null);
		rw.endElement("li");
	}

	public void encodeDivider(FacesContext context, AbstractNavLink navlink) throws IOException {
		ResponseWriter rw = context.getResponseWriter();
		rw.startElement("li", (UIComponent) navlink);
		Tooltip.generateTooltip(context, (UIComponent) navlink, rw);
		String styleClass = navlink.getStyleClass();
		if (null == styleClass)
			styleClass = "";
		else
			styleClass += " ";
		if (((UIComponent)navlink).getParent().getClass().equals(NavBarLinks.class)) {
			writeAttribute(rw, "class", styleClass + "divider-vertical", "class");
		} else {
			writeAttribute(rw, "class", styleClass + "divider", "class");
		}
		writeAttribute(rw, "style", navlink.getStyle(), "style");
		writeAttribute(rw, "role", "presentation", null);

		rw.endElement("li");
	}

	public void encodeHTML(FacesContext context, UIComponent navlink) throws IOException {
		ResponseWriter rw = context.getResponseWriter();

		String value = (String) ((AbstractNavLink)navlink).getValue();
		rw.startElement("li", navlink);
		writeAttribute(rw, "id", navlink.getClientId(context), "id");
		Tooltip.generateTooltip(context, navlink, rw);
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, (ClientBehaviorHolder)navlink, rw);

		R.encodeHTML4DHTMLAttrs(rw, navlink.getAttributes(), H.ALLBUTTON);

		writeAttribute(rw, "class", getStyleClasses(((AbstractNavLink)navlink)));
		writeAttribute(rw, "style", ((AbstractNavLink)navlink).getStyle());

		rw.startElement("a", navlink);
		writeAttribute(rw, "style", ((AbstractNavLink)navlink).getContentStyle(), "style");
		writeAttribute(rw, "class", ((AbstractNavLink)navlink).getContentClass(), "class");
		boolean hasActionExpression=false;
		if (navlink instanceof NavCommandLink)
			if (((NavCommandLink) navlink).getActionExpression() != null)
				hasActionExpression=true;
		if (((AbstractNavLink)navlink).getUpdate() == null && (!((AbstractNavLink)navlink).isAjax()) && (!hasActionExpression)) {
			String url = encodeHref(context, ((AbstractNavLink)navlink));
			if (url == null) {
				/*
				 * If we cannot get an outcome we use the Bootstrap Framework to
				 * give a feedback to the developer if this build is in the
				 * Development Stage
				 */
				if (FacesContext.getCurrentInstance().getApplication().getProjectStage()
						.equals(ProjectStage.Development)) {
					writeAttribute(rw, "data-toggle", "tooltip", null);
					writeAttribute(rw, "title", FacesContext.getCurrentInstance().getApplication().getProjectStage()
							+ "WARNING! " + "This link is disabled because a navigation case could not be matched.",
							null);
				}
				url = "#";

			}
			writeAttribute(rw, "href", url, null);
		}
		writeAttribute(rw, "role", "menuitem", null);
		writeAttribute(rw, "tabindex", "-1", null);

		String icon = ((AbstractNavLink)navlink).getIcon();
		String faicon = ((AbstractNavLink)navlink).getIconAwesome();
		boolean fa = false; // flag to indicate wether the selected icon set is
							// Font Awesome or not.
		if (faicon != null) {
			icon = faicon;
			fa = true;
		}
		if (icon != null) {
			Object ialign = ((AbstractNavLink)navlink).getIconAlign(); // Default Left
			if (ialign != null && ialign.equals("right")) {
				if (value != null)
					rw.writeText(value + " ", null);
				if (navlink.getChildCount() > 0) {
					for (UIComponent c : navlink.getChildren()) {
						c.encodeAll(context);
					}
				}
				IconRenderer.encodeIcon(rw, navlink, icon, fa);
			} else {
				IconRenderer.encodeIcon(rw, navlink, icon, fa);
				if (navlink.getChildCount() > 0) {
					for (UIComponent c : navlink.getChildren()) {
						c.encodeAll(context);
					}
				}
				if (value != null)
					rw.writeText(" " + value, null);
			}

		} else {
			if (value != null)
				rw.writeText(value, null);
			if (navlink.getChildCount() > 0) {
				for (UIComponent c : navlink.getChildren()) {
					c.encodeAll(context);
				}
			}
		}
		rw.endElement("a");
		rw.endElement("li");
	}

	private String getStyleClasses(AbstractNavLink navlink) {
		String c = "";
		boolean active = navlink.isActive();
		if (active) {
			c += "active";
		}

		String styleClass = navlink.getStyleClass();
		if (null != styleClass)
			c += " " + styleClass;

		return c;
	}

	private String encodeHref(FacesContext context, AbstractNavLink navlink) {
		String href = navlink.getHref();

		String url;

		if (href != null) {
			url = getResourceURL(context, href);
			return url;
		} else {
			String outcome = navlink.getOutcome();
			outcome = (outcome == null) ? context.getViewRoot().getViewId() : outcome;

			ConfigurableNavigationHandler cnh = (ConfigurableNavigationHandler) context.getApplication()
					.getNavigationHandler();
			NavigationCase navCase = cnh.getNavigationCase(context, null, outcome);
			if (navCase == null) {
				return null;
			}
			String vId = navCase.getToViewId(context);

			Map<String, List<String>> params = getParams(navCase, navlink);

			url = context.getApplication().getViewHandler().getBookmarkableURL(context, vId, params,
					navlink.isIncludeViewParams() || navCase.isIncludeViewParams());

			if (url != null) {
				String frag = navlink.getFragment();
				if (frag != null) {
					url += "#" + frag;
				}
				return url;
			} else {
				return "#";
			}
		}

	}

	protected String getResourceURL(FacesContext fc, String value) {
		return fc.getExternalContext().encodeResourceURL(value);
	}

	/**
	 * Find all parameters to include by looking at nested uiparams and params
	 * of navigation case
	 */
	protected Map<String, List<String>> getParams(NavigationCase navCase, AbstractNavLink button) {
		Map<String, List<String>> params = new LinkedHashMap<String, List<String>>();

		// UIParams
		for (UIComponent child : ((UIComponent)button).getChildren()) {
			if (child.isRendered() && (child instanceof UIParameter)) {
				UIParameter uiParam = (UIParameter) child;

				if (!uiParam.isDisable()) {
					List<String> paramValues = params.get(uiParam.getName());
					if (paramValues == null) {
						paramValues = new ArrayList<String>();
						params.put(uiParam.getName(), paramValues);
					}

					paramValues.add(String.valueOf(uiParam.getValue()));
				}
			}
		}

		// NavCase Params
		Map<String, List<String>> navCaseParams = navCase.getParameters();
		if (navCaseParams != null && !navCaseParams.isEmpty()) {
			for (Map.Entry<String, List<String>> entry : navCaseParams.entrySet()) {
				String key = entry.getKey();

				// UIParams take precedence
				if (!params.containsKey(key)) {
					params.put(key, entry.getValue());
				}
			}
		}

		return params;
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// this component rendered it's children itself
	}
}
