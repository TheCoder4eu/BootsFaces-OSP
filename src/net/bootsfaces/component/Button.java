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
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlOutcomeTargetButton;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;

/**
 * This components represents and renders a button without AJAX functionality.
 * 
 * @author thecoder4.eu
 */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "jq/jquery.js", target = "head") })
@FacesComponent(C.BUTTON_COMPONENT_TYPE)
public class Button extends HtmlOutcomeTargetButton {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.BUTTON_COMPONENT_TYPE;
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public Button() {
		setRendererType(null); // this component renders itself
	}

	/** 
	 * Renders the button. <br>
	 * General layout of the generated HTML code:<br>
	 * &lt;button class="btn btn-large" href="#">&lt;i class="icon-star">&lt;/i> Star&lt;/button>
	 * 
	 * @param context the current FacesContext
	 * @throws IOException thrown if something's wrong with the ResponseWriter
	 */
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		encodeHTML(context, getAttributes());
	}

	/** 
	 * Encode the HTML code of the button.
	 * 
	 * @param context the current FacesContext
	 * @param attrs the attribute list
	 * @throws IOException thrown if something's wrong with the ResponseWriter
	 */
	public void encodeHTML(FacesContext context, Map<String, Object> attrs) throws IOException {
		ResponseWriter rw = context.getResponseWriter();

		Object value = attrs.get(A.VALUE);

		rw.startElement(H.BUTTON, this);
		rw.writeAttribute(H.ID, getClientId(context), H.ID);
		rw.writeAttribute(H.NAME, getClientId(context), H.NAME);
		rw.writeAttribute(H.TYPE, H.BUTTON, null);
		rw.writeAttribute(H.CLASS, getStyleClasses(attrs), H.CLASS);

		rw.writeAttribute(A.CLICK, encodeClick(context, attrs), null);
		String d = A.asString(attrs.get(A.DISMISS));
		if (d != null) {
			rw.writeAttribute(A.DATA_DISMISS, d, null);
		}
		boolean disabled = (A.toBool(attrs.get(A.DISABLED)));
		if (disabled) {
			rw.writeAttribute(A.DISABLED, A.DISABLED, null);
		}

		// Encode attributes (HTML 4 pass-through + DHTML)
		R.encodeHTML4DHTMLAttrs(rw, attrs, A.ALLBUTTON_ATTRS);

		String icon = A.asString(attrs.get(A.ICON));
		if (icon != null) {
			Object ialign = attrs.get(A.ICON_ALIGN); // Default Left
			boolean white=null!=attrs.get(A.LOOK);
			if (ialign != null && ialign.equals(A.RIGHT)) {
				rw.writeText(value + C.SP, null);
				R.encodeIcon(rw, this, icon, white);
			} else {
				R.encodeIcon(rw, this, icon, white);
				rw.writeText(C.SP + value, null);
			}

		} else {
			rw.writeText(value, null);
		}

		rw.endElement(H.BUTTON);
	}

	/**
	 * Renders the Javascript code dealing with the click event.
	 * If the developer provides their own onclick handler, is precedes the generated Javascript code.
	 * @param context The current FacesContext.
	 * @param attrs the attribute list
	 * @return some Javascript code, such as "window.location.href='/targetView.jsf';"
	 */
	private String encodeClick(FacesContext context, Map<String, Object> attrs) {
		String js;
		String userClick = getOnclick();
		if (userClick != null) {
			js = userClick;
		} // +C.COLON; }
		else {
			js = "";
		}
		String outcome = getOutcome();
		outcome = (outcome == null) ? context.getViewRoot().getViewId() : outcome;

		ConfigurableNavigationHandler cnh = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
		NavigationCase navCase = cnh.getNavigationCase(context, null, outcome);
		/*
		 * Param Name: javax.faces.PROJECT_STAGE Default Value: The default value is ProjectStage#Production but IDE can set it differently
		 * in web.xml Expected Values: Development, Production, SystemTest, UnitTest Since: 2.0
		 * 
		 * If we cannot get an outcome we use an Alert to give a feedback to the Developer if this build is in the Development Stage
		 */
		if (navCase == null) {
			if (FacesContext.getCurrentInstance().getApplication().getProjectStage().equals(ProjectStage.Development)) {
				return "alert('WARNING! " + C.W_NONAVCASE_BUTTON + "');";
			} else {
				return "";
			}
		} // throw new FacesException("The outcome '"+outcome+"' cannot be resolved."); }
		String vId = navCase.getToViewId(context);

		Map<String, List<String>> params = getParams(navCase, this);
		String url;
		url = context.getApplication().getViewHandler()
				.getBookmarkableURL(context, vId, params, isIncludeViewParams() || navCase.isIncludeViewParams());

		if (url != null) {
			// fragment
			String frag = A.asString(attrs.get(A.FRAGMENT));
			if (frag != null) {
				url += "#" + frag;
			}
			js += "window.location.href='" + url + "';";
		}

		return js;
	}

	/**
	 * Find all parameters to include by looking at nested uiparams and params of navigation case
	 */
	protected static Map<String, List<String>> getParams(NavigationCase navCase, Button button) {
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

	/**
	 * Collects the CSS classes of the button.
	 * @param attrs the attribute list.
	 * @return the CSS classes (separated by a space).
	 */
	private static String getStyleClasses(Map<String, Object> attrs) {
		StringBuilder sb;
		sb = new StringBuilder(40); // optimize int
		sb.append("btn");
		String size = A.asString(attrs.get(A.SIZE));
		if (size != null) {
			sb.append(" btn-").append(size);
		}

		String look = A.asString(attrs.get(A.LOOK));
		if (look != null) {
			sb.append(" btn-").append(look);
		} else {
			sb.append(" btn-default");
		}

		if (A.toBool(attrs.get(A.DISABLED))) {
			sb.append(C.SP + A.DISABLED);
		}
		// TODO add styleClass and class support
		String sclass = A.asString(attrs.get(H.STYLECLASS));
		if (sclass != null) {
			sb.append(" ").append(sclass);
		}

		return sb.toString().trim();
	}

    /**
     * <p>Return the identifier of the component family to which this
     * component belongs.  This identifier, in conjunction with the value
     * of the <code>rendererType</code> property, may be used to select
     * the appropriate {@link Renderer} for this component instance.</p>
     */
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
