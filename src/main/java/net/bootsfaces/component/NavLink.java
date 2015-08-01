/**
 *  Copyright 2014 Riccardo Massera (TheCoder4.Eu)
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.ProjectStage;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.application.ResourceHandler;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
//import javax.faces.component.html.HtmlOutcomeTargetButton;
import javax.faces.component.html.HtmlOutcomeTargetLink;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.render.H;
import net.bootsfaces.render.JSEventHandlerRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head")

})
@FacesComponent(C.NAVLINK_COMPONENT_TYPE)
public class NavLink extends HtmlOutcomeTargetLink {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.NAVLINK_COMPONENT_TYPE;
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DIVIDER = "divider";
	public static final String DIVIDERH = DIVIDER; // divider-horizontal
	public static final String DIVIDERV = DIVIDER.concat(C.HYP).concat(C.V); // divider-vertical
	public static final String DROPDOWN = "dropdown";

	public NavLink() {
		setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		Tooltip.addResourceFile();
	}

	Map<String, Object> attrs;
	// !//boolean white=false;

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		/*
		 * <li><a href="#"><i class="icon-star"></i> Star</a></li> type=divider
		 * => <li class="divider-vertical"></li>
		 */
		attrs = getAttributes();

		// If there is the header attribute, we only render a Header
		String head = A.asString(attrs.get(A.HEADER));
		if (head != null) {
			encodeHeader(context, head);
		} else {
			// if there is no href, no outcome and no value attributes
			// we render a divider
			if (attrs.get(A.VALUE) == null && attrs.get(A.VALUE) == null && attrs.get(A.VALUE) == null) {
				encodeDivider(context);
			} else {
				encodeHTML(context);
			}
		} // if header
		Tooltip.activateTooltips(context, attrs, this);
	}

	public void encodeHeader(FacesContext context, String h) throws IOException {
		ResponseWriter rw = context.getResponseWriter();

		rw.startElement(H.LI, this);
		rw.writeAttribute(H.ID, getClientId(context), H.ID);
		String styleClass = A.asString(attrs.get("styleClass"));
		if (null == styleClass)
			rw.writeAttribute(H.CLASS, DROPDOWN + "-header", H.CLASS);
		else
			rw.writeAttribute(H.CLASS, DROPDOWN + "-header " + styleClass, H.CLASS);
		String style = A.asString(attrs.get("style"));
		if (null != style) {
			rw.writeAttribute("style", style, "style");
		}
		rw.writeAttribute(H.ROLE, "presentation", null);
		rw.writeText(h, null);
		rw.endElement(H.LI);
	}

	public void encodeDivider(FacesContext context) throws IOException {
		ResponseWriter rw = context.getResponseWriter();
		rw.startElement(H.LI, this);
		Tooltip.generateTooltip(context, attrs, rw);
		// rw.writeAttribute("data-class",
		// this.getParent().getClass().getSimpleName()+"-"+this.getParent().getClass().getName(),
		// null);
		String styleClass = A.asString(attrs.get("styleClass"));
		if (null == styleClass)
			styleClass = "";
		else
			styleClass += " ";
		if (this.getParent().getClass().getName().equals(NavBarLinks.COMPONENT_TYPE)) {
			rw.writeAttribute(H.CLASS, styleClass + DIVIDERV, H.CLASS);
		} else {
			rw.writeAttribute(H.CLASS, styleClass + DIVIDERH, H.CLASS);
		}
		String style = A.asString(attrs.get("style"));
		if (null != style) {
			rw.writeAttribute("style", style, "style");
		}
		rw.writeAttribute(H.ROLE, "presentation", null);

		rw.endElement(H.LI);
	}

	public void encodeHTML(FacesContext context) throws IOException {
		ResponseWriter rw = context.getResponseWriter();

		String value = A.asString(attrs.get(A.VALUE));
		String url = encodeHref(context);
		// else {

		rw.startElement(H.LI, this);
		rw.writeAttribute(H.ID, getClientId(context), H.ID);
		Tooltip.generateTooltip(context, attrs, rw);
		// rw.writeAttribute(H.TYPE, H.BUTTON, null);
		rw.writeAttribute(H.CLASS, getStyleClasses(), H.CLASS);
		String style = A.asString(attrs.get("style"));
		if (null != style) {
			rw.writeAttribute("style", style, "style");
		}
		rw.startElement(H.A, this);
		style = A.asString(attrs.get("contentStyle"));
		if (null != style) {
			rw.writeAttribute("style", style, "style");
		}
		String contentClass = A.asString(attrs.get("contentClass"));
		if (null != style) {
			rw.writeAttribute("class", contentClass, "class");
		}
		if (url == null) {
			/*
			 * Param Name: javax.faces.PROJECT_STAGE Default Value: The default
			 * value is ProjectStage#Production but IDE can set it differently
			 * in web.xml Expected Values: Development, Production, SystemTest,
			 * UnitTest Since: 2.0
			 * 
			 * If we cannot get an outcome we use the Bootstrap Framework to
			 * give a feedback to the Developer if this build is in the
			 * Development Stage
			 */
			// R.encodeLabel(rw, this, "important", C.W_NONAVCASE_LINK);
			if (FacesContext.getCurrentInstance().getApplication().getProjectStage().equals(ProjectStage.Development)) {
				rw.writeAttribute(H.TOGGLE, H.TOOLTIP, null);
				rw.writeAttribute(H.TITLE, FacesContext.getCurrentInstance().getApplication().getProjectStage()
						+ "WARNING! " + C.W_NONAVCASE_LINK, null);
			}
			url = C.HASH;

		}
		rw.writeAttribute(H.HREF, url, null);
		rw.writeAttribute(H.ROLE, "menuitem", null);
		rw.writeAttribute("tabindex", "-1", null);

		JSEventHandlerRenderer.generateJSEventHandlers(rw, this);

		String icon = A.asString(attrs.get(A.ICON));
		String faicon = A.asString(attrs.get(A.ICONAWESOME));
		boolean fa = false; // flag to indicate wether the selected icon set is
							// Font Awesome or not.
		if (faicon != null) {
			icon = faicon;
			fa = true;
		}
		if (icon != null) {
			Object ialign = attrs.get(A.ICON_ALIGN); // Default Left
			if (ialign != null && ialign.equals(A.RIGHT)) {
				rw.writeText(value + C.SP, null);
				R.encodeIcon(rw, this, icon, fa);
				// !//R.encodeIcon(rw, this, icon, white);
			} else {
				R.encodeIcon(rw, this, icon, fa);
				// !//R.encodeIcon(rw, this, icon, white);
				rw.writeText(C.SP + value, null);
			}

		} else {
			rw.writeText(value, null);
		}
		rw.endElement(H.A);
		rw.endElement(H.LI);
	}

	private String getStyleClasses() {
		String c = "";
		boolean active = A.toBool(attrs.get(A.ACTIVE));
		if (active) {
			c += "active";
		}

		String styleClass = A.asString(attrs.get("styleClass"));
		if (null != styleClass)
			c += " " + styleClass;

		return c;
	}

	private String encodeHref(FacesContext context) {
		String href = A.asString(attrs.get(A.HREF));

		String url;

		if (href != null) {
			url = getResourceURL(context, href);
			return url;
		} else {
			String outcome = getOutcome();
			outcome = (outcome == null) ? context.getViewRoot().getViewId() : outcome;

			ConfigurableNavigationHandler cnh = (ConfigurableNavigationHandler) context.getApplication()
					.getNavigationHandler();
			NavigationCase navCase = cnh.getNavigationCase(context, null, outcome);
			if (navCase == null) {
				return null;
			} // throw new FacesException("The outcome '"+outcome+"' cannot be
				// resolved."); }
			String vId = navCase.getToViewId(context);

			Map<String, List<String>> params = getParams(navCase, this);

			url = context.getApplication().getViewHandler().getBookmarkableURL(context, vId, params,
					isIncludeViewParams() || navCase.isIncludeViewParams());

			if (url != null) {
				// fragment
				String frag = A.asString(attrs.get(A.FRAGMENT));
				if (frag != null) {
					url += "#" + frag;
				}
				return url;
			} // return url; }
			else {
				return H.HASH;
			} // return #
		}

	}

	protected String getResourceURL(FacesContext fc, String value) {
		if (value.contains(ResourceHandler.RESOURCE_IDENTIFIER)) {
			return value;
		} else {
			String url = fc.getApplication().getViewHandler().getResourceURL(fc, value);

			return fc.getExternalContext().encodeResourceURL(url);
		}
	}

	/**
	 * Find all parameters to include by looking at nested uiparams and params
	 * of navigation case
	 */
	protected Map<String, List<String>> getParams(NavigationCase navCase, NavLink button) {
		Map<String, List<String>> params = new LinkedHashMap<String, List<String>>();

		// UIParams
		for (UIComponent child : button.getChildren()) {
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
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
