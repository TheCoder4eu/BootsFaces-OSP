/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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

package net.bootsfaces.component.button;

import static net.bootsfaces.C.BSFCOMPONENT;
import static net.bootsfaces.C.BUTTON_COMPONENT_TYPE;
import static net.bootsfaces.C.W_NONAVCASE_BUTTON;
import static net.bootsfaces.render.A.asString;
import static net.bootsfaces.render.A.toBool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.FacesException;
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

import net.bootsfaces.C;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.H;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 * This components represents and renders a button without AJAX functionality.
 * 
 * @author thecoder4.eu
 */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
@FacesComponent(BUTTON_COMPONENT_TYPE)
public class Button extends HtmlOutcomeTargetButton {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = BUTTON_COMPONENT_TYPE;
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = BSFCOMPONENT;

	public Button() {
		setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		Tooltip.addResourceFile();
	}
	
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}


	/**
	 * Renders the button. <br>
	 * General layout of the generated HTML code:<br>
	 * &lt;button class="btn btn-large" href="#"%gt;&lt;i
	 * class="icon-star"&gt;&lt;/i&gt; Star&lt;/button&gt;
	 * 
	 * @param context
	 *            the current FacesContext
	 * @throws IOException
	 *             thrown if something's wrong with the ResponseWriter
	 */
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}

		encodeHTML(context, getAttributes());
	}

	/**
	 * Encode the HTML code of the button.
	 * 
	 * @param context
	 *            the current FacesContext
	 * @param attrs
	 *            the attribute list
	 * @throws IOException
	 *             thrown if something's wrong with the ResponseWriter
	 */
	public void encodeHTML(FacesContext context, Map<String, Object> attrs) throws IOException {
		ResponseWriter rw = context.getResponseWriter();

		Object value = attrs.get("value");
		String style = asString(attrs.get("style"));

		rw.startElement("button", this);
		rw.writeAttribute("id", getClientId(context), "id");
		rw.writeAttribute("name", getClientId(context), "name");
		rw.writeAttribute("type", "button", null);
		if (null != attrs.get("dir")) {
			rw.writeAttribute("dir", attrs.get("dir"), "dir");
		}
		if (style != null) {
			rw.writeAttribute("style", style, "style");
		}
		rw.writeAttribute("class", getStyleClasses(attrs), "class");

		Tooltip.generateTooltip(context, attrs, rw);

		final String clickHandler = encodeClick(context, attrs);
		if (null != clickHandler && clickHandler.length() > 0) {
			rw.writeAttribute("onclick", clickHandler, null);
		}
		String d = asString(attrs.get("dismiss"));
		if (d != null) {
			rw.writeAttribute("data-dismiss", d, null);
		}
		boolean disabled = (toBool(attrs.get("disabled")));
		if (disabled) {
			rw.writeAttribute("disabled", "disabled", null);
		}

		// Encode attributes (HTML 4 pass-through + DHTML)
		renderPassThruAttributes(context, this, H.ALLBUTTON);

		String icon = asString(attrs.get("icon"));
		String faicon = asString(attrs.get("iconAwesome"));
		boolean fa = false; // flag to indicate wether the selected icon set is
							// Font Awesome or not.
		if (faicon != null) {
			icon = faicon;
			fa = true;
		}
		if (icon != null) {
			Object ialign = attrs.get("icon"+"Align"); // Default Left
			if (ialign != null && ialign.equals("right")) {
				rw.writeText(value + " ", null);
				IconRenderer.encodeIcon(rw, this, icon, fa);
			} else {
				IconRenderer.encodeIcon(rw, this, icon, fa);
				rw.writeText(" " + value, null);
			}

		} else {
			rw.writeText(value, null);
		}

		Tooltip.activateTooltips(context, getAttributes(), this);
		rw.endElement("button");
	}

	/**
	 * Renders the Javascript code dealing with the click event. If the
	 * developer provides their own onclick handler, is precedes the generated
	 * Javascript code.
	 * 
	 * @param context
	 *            The current FacesContext.
	 * @param attrs
	 *            the attribute list
	 * @return some Javascript code, such as
	 *         "window.location.href='/targetView.jsf';"
	 */
	private String encodeClick(FacesContext context, Map<String, Object> attrs) {
		String js;
		String userClick = getOnclick();
		if (userClick != null) {
			js = userClick;
		} // +COLON; }
		else {
			js = "";
		}

		String fragment = asString(attrs.get("fragment"));
		String outcome = getOutcome();
		if (null != outcome && outcome.contains("#")) {
			if (null != fragment && fragment.length()>0) {
				throw new FacesException("Please define the URL fragment either in the fragment attribute or in the outcome attribute, but not both");
			}
			int pos = outcome.indexOf("#");
			fragment = outcome.substring(pos);
			outcome = outcome.substring(0,  pos);
		}

		if (outcome == null || outcome.equals("")) {
			if (null != fragment && fragment.length()>0) {
				if (!fragment.startsWith("#")) {
					fragment = "#" + fragment;
				}
				js += "window.location.href='" + fragment + "';";
				return js;
			}
		}
		
		if (outcome == null || outcome.equals("") || outcome.equals("@none"))
			return js;

		if (canOutcomeBeRendered(attrs, fragment, outcome)) {
			outcome = (outcome == null) ? context.getViewRoot().getViewId() : outcome;

			String url = determineTargetURL(context, outcome);

			if (url != null) {
				if (url.startsWith("alert(")) {
					js=url;
				} else {
					if (fragment != null) {
						if (fragment.startsWith("#")) {
							url += fragment;
						} else {
							url += "#" + fragment;
						}
					}
					js += "window.location.href='" + url + "';";
				}
			}
		}

		return js;
	}

	/**
	 * Do we have to suppress the target URL?
	 * 
	 * @param attrs
	 *            the component's attribute list
	 * @param fragment
	 *            the fragment of the URL behind the hash (outcome#fragment)
	 * @param outcome
	 *            the value of the outcome attribute
	 * @return true if the outcome can be rendered.
	 */
	private boolean canOutcomeBeRendered(Map<String, Object> attrs, String fragment, String outcome) {
		boolean renderOutcome = true;
		if (null == outcome && attrs.containsKey("ng-click")) {
			String ngClick = asString(attrs.get("ng-click"));
			if (null != ngClick && (ngClick.length() > 0)) {
				if (fragment == null) {
					renderOutcome = false;
				}
			}
		}
		return renderOutcome;
	}

	/**
	 * Translate the outcome attribute value to the target URL.
	 * 
	 * @param context
	 *            the current FacesContext
	 * @param outcome
	 *            the value of the outcome attribute
	 * @return the target URL of the navigation rule (or the outcome if there's
	 *         not navigation rule)
	 */
	private String determineTargetURL(FacesContext context, String outcome) {
		ConfigurableNavigationHandler cnh = (ConfigurableNavigationHandler) context.getApplication()
				.getNavigationHandler();
		NavigationCase navCase = cnh.getNavigationCase(context, null, outcome);
		/*
		 * Param Name: javax.faces.PROJECT_STAGE Default Value: The default
		 * value is ProjectStage#Production but IDE can set it differently in
		 * web.xml Expected Values: Development, Production, SystemTest,
		 * UnitTest Since: 2.0
		 * 
		 * If we cannot get an outcome we use an Alert to give a feedback to the
		 * Developer if this build is in the Development Stage
		 */
		if (navCase == null) {
			if (FacesContext.getCurrentInstance().getApplication().getProjectStage().equals(ProjectStage.Development)) {
				return "alert('WARNING! " + W_NONAVCASE_BUTTON + "');";
			} else {
				return "";
			}
		} // throw new FacesException("The outcome '"+outcome+"' cannot be
			// resolved."); }
		String vId = navCase.getToViewId(context);

		Map<String, List<String>> params = getParams(navCase, this);
		String url;
		url = context.getApplication().getViewHandler().getBookmarkableURL(context, vId, params,
				isIncludeViewParams() || navCase.isIncludeViewParams());
		return url;
	}

	/**
	 * Find all parameters to include by looking at nested uiparams and params
	 * of navigation case
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
	 * 
	 * @param attrs
	 *            the attribute list.
	 * @return the CSS classes (separated by a space).
	 */
	private static String getStyleClasses(Map<String, Object> attrs) {
		StringBuilder sb;
		sb = new StringBuilder(40); // optimize int
		sb.append("btn");
		String size = asString(attrs.get("size"));
		if (size != null) {
			sb.append(" btn-").append(size);
		}

		String look = asString(attrs.get("look"));
		if (look != null) {
			sb.append(" btn-").append(look);
		} else {
			sb.append(" btn-default");
		}

		if (toBool(attrs.get("disabled"))) {
			sb.append(" " + "disabled");
		}
		// TODO add styleClass and class support
		String sclass = asString(attrs.get("styleClass"));
		if (sclass != null) {
			sb.append(" ").append(sclass);
		}

		return sb.toString().trim();
	}

	/**
	 * <p>
	 * Return the identifier of the component family to which this component
	 * belongs. This identifier, in conjunction with the value of the
	 * <code>rendererType</code> property, may be used to select the appropriate
	 * {@link Renderer} for this component instance.
	 * </p>
	 */
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Method temporarily copied from CoreRenderer. Should be replaced by a call
	 * of CoreRenderer in the long run.
	 * 
	 * @param context
	 *            the current FacesContext
	 * @param component
	 *            the current component
	 * @param attrs
	 *            the component's attribute list
	 * @throws IOException
	 *             thrown if something's wrong with the response writer.
	 */

	protected void renderPassThruAttributes(FacesContext context, UIComponent component, String[] attrs)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		// pre-defined attributes
		if (attrs != null && attrs.length > 0) {
			for (String attribute : attrs) {
				Object value = component.getAttributes().get(attribute);

				if (shouldRenderAttribute(value))
					writer.writeAttribute(attribute, value.toString(), attribute);
			}
		}
	}

	/**
	 * Detects whether an attribute is a default value or not. Method
	 * temporarily copied from CoreRenderer. Should be replaced by a call of
	 * CoreRenderer in the long run.
	 * 
	 * @param value
	 *            the value to be checked
	 * @return true if the value is not the default value
	 */
	protected boolean shouldRenderAttribute(Object value) {
		if (value == null)
			return false;

		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue();
		} else if (value instanceof Number) {
			Number number = (Number) value;

			if (value instanceof Integer)
				return number.intValue() != Integer.MIN_VALUE;
			else if (value instanceof Double)
				return number.doubleValue() != Double.MIN_VALUE;
			else if (value instanceof Long)
				return number.longValue() != Long.MIN_VALUE;
			else if (value instanceof Byte)
				return number.byteValue() != Byte.MIN_VALUE;
			else if (value instanceof Float)
				return number.floatValue() != Float.MIN_VALUE;
			else if (value instanceof Short)
				return number.shortValue() != Short.MIN_VALUE;
		}

		return true;
	}

}
