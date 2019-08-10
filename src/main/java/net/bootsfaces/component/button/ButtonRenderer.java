/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component.button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.ProjectStage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;


/** This class generates the HTML code of &lt;b:button /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.button.Button")
public class ButtonRenderer extends CoreRenderer {
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
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
	        return;
	    }
		Button button = (Button) component;
		encodeHTML(context, button);
	}

	/**
	 * Encode the HTML code of the button.
	 *
	 * @param context
	 *            the current FacesContext
	 * @throws IOException
	 *             thrown if something's wrong with the ResponseWriter
	 */
	public void encodeHTML(FacesContext context, Button button)
	throws IOException {
		boolean idHasBeenRendered=false;
		if (button.getHref()!=null&&button.getOutcome()!=null) 
			throw new FacesException("Please define the href attribute or the outcome attribute, but not both");
		ResponseWriter rw = context.getResponseWriter();
		String clientId = button.getClientId();
		
		// add responsive style
		String clazz = Responsive.getResponsiveStyleClass(button, false).trim();
		boolean isResponsive = clazz.length() > 0;
		if (isResponsive) {
			rw.startElement("div", button);
			rw.writeAttribute("class", clazz, null);
			rw.writeAttribute("id", clientId, "id");
			idHasBeenRendered = true;
		}


		Object value = (button.getValue() != null ? button.getValue() : "");
		String style = button.getStyle();

		String tag = "button";
		if ((button.getHref() != null) || (button.getTarget() != null)) {
			tag="a";
		}
		rw.startElement(tag, button);
		if (!idHasBeenRendered) {
			rw.writeAttribute("id", clientId, "id");
		}
		rw.writeAttribute("name", clientId, "name");
		if ("button".equals(tag)) {
			rw.writeAttribute("type", "button", null);
		}
		if(BsfUtils.isStringValued(button.getDir())) {
			rw.writeAttribute("dir", button.getDir(), "dir");
		}
		if (style != null) {
			rw.writeAttribute("style", style, "style");
		}
		if (button.getHref() != null)
			rw.writeAttribute("href", button.getHref(), "href");
		if (button.getTarget() != null)
			rw.writeAttribute("target", button.getTarget(), "target");
		rw.writeAttribute("class", getStyleClasses(button, isResponsive), "class");

		Tooltip.generateTooltip(context, button, rw);

		final String clickHandler = encodeClick(context, button);
		if (null != clickHandler && clickHandler.length() > 0) {
			rw.writeAttribute("onclick", clickHandler, null);
		}
		if (BsfUtils.isStringValued(button.getDismiss())) {
			rw.writeAttribute("data-dismiss", button.getDismiss(), null);
		}
		if (button.isDisabled()) {
			rw.writeAttribute("disabled", "disabled", null);
		}

		// Encode attributes (HTML 4 pass-through + DHTML)
		renderPassThruAttributes(context, button, new String[] { "accesskey", "dir", "lang", "style", "tabindex", "title" });
		
		writeAttribute(rw, "onblur", button.getOnblur());
		writeAttribute(rw, "onchange", button.getOnchange());
		writeAttribute(rw, "onclick", button.getOnclick());
		writeAttribute(rw, "ondblclick", button.getOndblclick());
		writeAttribute(rw, "onfocus", button.getOnfocus());
		writeAttribute(rw, "onkeydown", button.getOnkeydown());
		writeAttribute(rw, "onkeypress", button.getOnkeypress());
		writeAttribute(rw, "onkeyup", button.getOnkeyup());
		writeAttribute(rw, "onmousedown", button.getOnmousedown());
		writeAttribute(rw, "onmousemove", button.getOnmousemove());
		writeAttribute(rw, "onmouseout", button.getOnmouseout());
		writeAttribute(rw, "onmouseover", button.getOnmouseover());
		writeAttribute(rw, "onmouseup", button.getOnmouseup());
		
		renderPassThruAttributes(context, button, button.getEventNames().toArray(new String[0]));


		String icon = button.getIcon();
		String faicon = button.getIconAwesome();
		boolean fa = false; // flag to indicate whether the selected icon set is
							// Font Awesome or not.
		if (faicon != null) {
			icon = faicon;
			fa = true;
		}
		if (icon != null) {
			Object ialign = button.getIconAlign(); // Default Left
			if (ialign != null && ialign.equals("right")) {
				rw.writeText(value + " ", null);
				IconRenderer.encodeIcon(rw, button, icon, fa, button.getIconSize(), button.getIconRotate(), button.getIconFlip(), button.isIconSpin(), null, null, false, false, false, false,
						button.isIconBrand(), button.isIconInverse(), button.isIconLight(), button.isIconPulse(), button.isIconRegular(), button.isIconSolid());
			} else {
				IconRenderer.encodeIcon(rw, button, icon, fa, button.getIconSize(), button.getIconRotate(), button.getIconFlip(), button.isIconSpin(), null, null, false, false, false, false,
						button.isIconBrand(), button.isIconInverse(), button.isIconLight(), button.isIconPulse(), button.isIconRegular(), button.isIconSolid());
				rw.writeText(" " + value, null);
			}

		} else {
			rw.writeText(value, null);
		}

		Tooltip.activateTooltips(context, button);
		rw.endElement(tag);
		if (isResponsive) {
			rw.endElement("div");
		}
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
	private String encodeClick(FacesContext context, Button button) {
		String js;
		String userClick = button.getOnclick();
		if (userClick != null) {
			js = userClick;
		} // +COLON; }
		else {
			js = "";
		}

		String fragment = button.getFragment();
		String outcome = button.getOutcome();
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
				js += "window.open('" + fragment + "', '";
				if (button.getTarget() != null)
					js += button.getTarget();
				else
					js += "_self";
				js += "');";
				return js;
			}
		}

		if (outcome == null || outcome.equals("") || outcome.equals("@none"))
			return js;

		if (canOutcomeBeRendered(button, fragment, outcome)) {
			String url = determineTargetURL(context, button, outcome);

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
					js += "window.open('" + url + "', '";
					if (button.getTarget() != null)
						js += button.getTarget();
					else
						js += "_self";
					js += "');";
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
	private boolean canOutcomeBeRendered(Button button, String fragment, String outcome) {
		boolean renderOutcome = true;
		if (null == outcome && button.getAttributes() != null && button.getAttributes().containsKey("ng-click")) {
			String ngClick = (String)button.getAttributes().get("ng-click");
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
	private String determineTargetURL(FacesContext context, Button button, String outcome) {
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
				return "alert('WARNING! " + C.W_NONAVCASE_BUTTON + "');";
			} else {
				return "";
			}
		} // throw new FacesException("The outcome '"+outcome+"' cannot be
			// resolved."); }
		String vId = navCase.getToViewId(context);

		Map<String, List<String>> params = getParams(navCase, button);
		String url;
		url = context.getApplication().getViewHandler().getBookmarkableURL(context, vId, params,
				button.isIncludeViewParams() || navCase.isIncludeViewParams());
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
	 * @return the CSS classes (separated by a space).
	 */
	private static String getStyleClasses(Button button, boolean isResponsive) {
		StringBuilder sb;
		sb = new StringBuilder(40); // optimize int
		sb.append("btn");
		String size = button.getSize();
		if (size != null) {
			sb.append(" btn-").append(size);
		}

		String look = button.getLook();
		if (look != null) {
			sb.append(" btn-").append(look);
		} else {
			sb.append(" btn-default");
		}

		if (button.isDisabled()) {
			sb.append(" disabled");
		}
		if (isResponsive) {
			sb.append(" btn-block");
		}
		String sclass = button.getStyleClass();
		if (sclass != null) {
			sb.append(" ").append(sclass);
		}


		return sb.toString().trim();
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
