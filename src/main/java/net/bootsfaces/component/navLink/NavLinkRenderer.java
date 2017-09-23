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
import net.bootsfaces.component.commandLink.CommandLink;
import net.bootsfaces.component.dropButton.DropButton;
import net.bootsfaces.component.dropMenu.DropMenu;
import net.bootsfaces.component.flyOutMenu.FlyOutMenu;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.component.kebab.Kebab;
import net.bootsfaces.component.link.Link;
import net.bootsfaces.component.listLinks.ListLinks;
import net.bootsfaces.component.navBar.NavBar;
import net.bootsfaces.component.navBarLinks.NavBarLinks;
import net.bootsfaces.component.navCommandLink.NavCommandLink;
import net.bootsfaces.component.pillLinks.PillLinks;
import net.bootsfaces.component.tabLinks.TabLinks;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Responsive;
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
			if ((navlink.getValue() == null) && (((UIComponent) navlink).getChildCount() == 0) && navlink.getIcon() == null && navlink.getIconAwesome()==null) {
				encodeDivider(context, navlink);
			} else {
				encodeHTML(context, (UIComponent) navlink);
			}
		} // if header
		Tooltip.activateTooltips(context, component);
	}

	public void encodeHeader(FacesContext context, String h, UIComponent navlink) throws IOException {
		ResponseWriter rw = context.getResponseWriter();

		String htmlTag = "span";
		UIComponent parent = navlink.getParent();
		if (parent != null) {
			if (parent.getClass().getSimpleName().equals("UIRepeat")) {
				parent = parent.getParent();
			}
			if (parent instanceof DropButton || parent instanceof NavBar || parent instanceof TabLinks
					|| parent instanceof PillLinks || parent instanceof ListLinks || parent instanceof NavBarLinks
					|| parent instanceof DropMenu || parent instanceof FlyOutMenu || parent instanceof Kebab) {
				htmlTag = "li";
			}
		}
		rw.startElement(htmlTag, navlink);
		writeAttribute(rw, "id", navlink.getClientId(context), "id");
		String styleClass = ((AbstractNavLink) navlink).getStyleClass();
		if (null == styleClass) {
			styleClass = "";
		}
		styleClass = "dropdown-header " + styleClass + " "
				+ Responsive.getResponsiveStyleClass((AbstractNavLink) navlink, false);

		writeAttribute(rw, "class", styleClass.trim(), "class");
		writeAttribute(rw, "style", ((AbstractNavLink) navlink).getStyle(), "style");
		writeAttribute(rw, "role", "presentation", null);
		rw.writeText(h, null);
		rw.endElement(htmlTag);
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
		if (((UIComponent) navlink).getParent().getClass().equals(NavBarLinks.class)) {
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

		Object _value = ((AbstractNavLink) navlink).getValue();
		String value = null;
		if (_value != null) {
			value = String.valueOf(_value);
		}
		Boolean useAjax = ((AbstractNavLink) navlink).isAjax();
		useAjax = useAjax == null ? true : useAjax; // by default, behave like before
		String htmlTag = "span";
		boolean idHasBeenRendered=false;
		if (!(navlink instanceof Link || navlink instanceof CommandLink)) {
			UIComponent parent = navlink.getParent();
			if (parent != null) {
				if (parent.getClass().getSimpleName().equals("UIRepeat")) {
					parent = parent.getParent();
				}
				if (parent instanceof DropButton || parent instanceof NavBar || parent instanceof TabLinks
						|| parent instanceof PillLinks || parent instanceof ListLinks || parent instanceof NavBarLinks
						|| parent instanceof DropMenu || parent instanceof FlyOutMenu) {
					htmlTag = "li";
				}
			}
			rw.startElement(htmlTag, navlink);
			writeAttribute(rw, "id", navlink.getClientId(context), "id");
			idHasBeenRendered=true;
			if (((AbstractNavLink) navlink).isDisabled()) {
				writeAttribute(rw, "class", "disabled");
			}

	
			String style = "cursor:pointer;";
			if (((AbstractNavLink) navlink).getStyle()!=null) {
				style += ((AbstractNavLink) navlink).getStyle();
			}
			writeAttribute(rw, "class", getStyleClasses(((AbstractNavLink) navlink)));
			writeAttribute(rw, "style", style);
		}

		rw.startElement("a", navlink);
		if (!idHasBeenRendered) {
			writeAttribute(rw, "id", navlink.getClientId(context), "id");
		}
		writeAttribute(rw, "style", ((AbstractNavLink) navlink).getContentStyle(), "style");

		
		
		Tooltip.generateTooltip(context, navlink, rw);
		
		if (!((AbstractNavLink)navlink).isDisabled()) {
			if (useAjax) {
				AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, (ClientBehaviorHolder) navlink, rw, false);
			} else {
				AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, (ClientBehaviorHolder) navlink, rw, true);
			}
		}

		R.encodeHTML4DHTMLAttrs(rw, navlink.getAttributes(), new String[] { "accesskey", "dir", "lang", "style", "tabindex", "title" });

		
		String styleClass = (navlink instanceof NavCommandLink ? "commandLink " : "");
		if (navlink instanceof Link || navlink instanceof CommandLink) {
			styleClass += getStyleClasses(((AbstractNavLink) navlink));
		}
		styleClass += (((AbstractNavLink) navlink).getContentClass() != null ? ((AbstractNavLink) navlink).getContentClass() : "");
		if (((AbstractNavLink) navlink).isDisabled()) {
			styleClass += " disabled";
		}
		writeAttribute(rw, "class", styleClass, "class");
		boolean hasActionExpression = false;
		if (!((AbstractNavLink) navlink).isDisabled()) {
			if (navlink instanceof NavCommandLink)
				if (((NavCommandLink) navlink).getActionExpression() != null)
					hasActionExpression = true;
			if (((AbstractNavLink) navlink).getUpdate() == null && (!useAjax)
					&& (!hasActionExpression)) {
				String url = encodeHref(context, ((AbstractNavLink) navlink));
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
				String target=((AbstractNavLink)navlink).getTarget();
				writeAttribute(rw, "target", target, null);
			}
		}
		if (!(navlink instanceof Link || navlink instanceof CommandLink)) {
			writeAttribute(rw, "role", "menuitem", null);
		} else {
			if (navlink instanceof Link) {
				if (((Link)navlink).getLook() != null) {
					writeAttribute(rw, "role", "button", null);
				}
			} else if (navlink instanceof CommandLink) {
				if (((CommandLink)navlink).getLook() != null) {
					writeAttribute(rw, "role", "button", null);
				}
			}

		}
		writeAttribute(rw, "tabindex", "-1", null);

		String icon = ((AbstractNavLink) navlink).getIcon();
		String faicon = ((AbstractNavLink) navlink).getIconAwesome();
		boolean fa = false; // flag to indicate whether the selected icon set is
							// Font Awesome or not.
		if (faicon != null) {
			icon = faicon;
			fa = true;
		}
		if (icon != null) {
			AbstractNavLink link = (AbstractNavLink) navlink;
			Object ialign = link.getIconAlign(); // Default
																		// Left
			if (ialign != null && ialign.equals("right")) {
				if (value != null)
					rw.writeText(value + " ", null);
				if (navlink.getChildCount() > 0) {
					for (UIComponent c : navlink.getChildren()) {
						c.encodeAll(context);
					}
				}
				IconRenderer.encodeIcon(rw, navlink, icon, fa, link.getIconSize(), link.getIconRotate(), link.getIconFlip(), link.isIconSpin(), null, null, false, false, false, false);
			} else {
				IconRenderer.encodeIcon(rw, navlink, icon, fa, link.getIconSize(), link.getIconRotate(), link.getIconFlip(), link.isIconSpin(), null, null, false, false, false, false);
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
		if (navlink instanceof NavLink || navlink instanceof NavCommandLink) {
			rw.endElement(htmlTag);
		}
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

		String responsiveStyleClass = Responsive.getResponsiveStyleClass((AbstractNavLink) navlink, false);
		c += " " + responsiveStyleClass;
		c += getStyleClasses(navlink, responsiveStyleClass.length()>1);
		return c.trim();
	}

	private String encodeHref(FacesContext context, AbstractNavLink navlink) {
		String href = navlink.getHref();

		String url;

		if (href != null) {
			url = getResourceURL(context, href);
			return url;
		} else {
			String outcome = navlink.getOutcome();
			if (outcome==null) {
				return null;
			}

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

	protected String getResourceURL(FacesContext fc, String url) {
		if (url.startsWith("http://")) {
			return url;
		}
		return fc.getExternalContext().encodeResourceURL(url);
	}

	/**
	 * Find all parameters to include by looking at nested uiparams and params
	 * of navigation case
	 */
	protected Map<String, List<String>> getParams(NavigationCase navCase, AbstractNavLink button) {
		Map<String, List<String>> params = new LinkedHashMap<String, List<String>>();

		// UIParams
		for (UIComponent child : ((UIComponent) button).getChildren()) {
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
	
	/**
	 * Collects the CSS classes of the link.
	 *
	 * @return the CSS classes (separated by a space).
	 */
	private static String getStyleClasses(AbstractNavLink link, boolean isResponsive) {
		StringBuilder sb;
		sb = new StringBuilder(20); // optimize int
		
		String look = null;
		if (link instanceof Link) {
			look = ((Link)link).getLook();
		} else if (link instanceof CommandLink) {
			look = ((CommandLink)link).getLook();
		}

		if (look != null) {
			sb.append("btn btn-").append(look);
			if (isResponsive) {
				sb.append(" btn-block");
			}
		}

		return sb.toString().trim();
	}
}
