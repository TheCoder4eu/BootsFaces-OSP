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

package net.bootsfaces.component.navBar;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostAddToViewEvent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:navBar /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/collapse.js", target = "body"), })
@ListenerFor(systemEventClass = PostAddToViewEvent.class)
@FacesComponent("net.bootsfaces.component.navBar.NavBar")
public class NavBar extends UIComponentBase implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.navBar.NavBar";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.navBar.NavBar";

	public NavBar() {
		// AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY,
		// "jq/jquery.js");
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		//!bs-less//AddResourcesListener.addThemedCSSResource("navbar.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		setRendererType(DEFAULT_RENDERER);
	}

	/**
	 * Dario D'Urzo <br>
	 * Dynamically add custom css to manage non-sticky footer. In this way, only
	 * if fixed attribute is "non-sticky" the system load the correct css that
	 * manages all style aspect of this functionlity.
	 *
	 * This is also cross-theme.
	 */
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (event instanceof PostAddToViewEvent) {
			if ("non-sticky".equals(getFixed()) || ("bottom".equals(getPosition()) && (!isSticky()))) {
				UIOutput resource = new UIOutput();
				resource.getAttributes().put("name", "css/sticky-footer-navbar.css");
				resource.getAttributes().put("library", C.BSF_LIBRARY);
				resource.getAttributes().put("target", "head");
				resource.setRendererType("javax.faces.resource.Stylesheet");
				FacesContext.getCurrentInstance().getViewRoot().addComponentResource(FacesContext.getCurrentInstance(),
						resource);
			}
		}
		super.processEvent(event);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		alt,
		binding,
		brand,
		brandAlign,
		brandHref,
		brandImg,
		brandImgStyle,
		brandImgStyleClass,
		brandStyle,
		brandStyleClass,
		fixed,
		fluid,
		inverse,
		onclick,
		position,
		sticky,
		style,
		styleClass,
		tooltip,
		tooltipContainer,
		tooltipDelay,
		tooltipDelayHide,
		tooltipDelayShow,
		tooltipPosition;
		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	/**
	 * alternative text <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAlt() {
		return (String) getStateHelper().eval(PropertyKeys.alt, "Brand");
	}

	/**
	 * alternative text <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAlt(String _alt) {
		getStateHelper().put(PropertyKeys.alt, _alt);
	}

	/**
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		return (javax.faces.component.UIComponent) getStateHelper().eval(PropertyKeys.binding);
	}

	/**
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
		getStateHelper().put(PropertyKeys.binding, _binding);
	}

	/**
	 * Brand for the Navbar. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBrand() {
		return (String) getStateHelper().eval(PropertyKeys.brand);
	}

	/**
	 * Brand for the Navbar. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrand(String _brand) {
		getStateHelper().put(PropertyKeys.brand, _brand);
	}

	/**
	 * Brand alignment, can be right or left. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBrandAlign() {
		return (String) getStateHelper().eval(PropertyKeys.brandAlign);
	}

	/**
	 * Brand alignment, can be right or left. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandAlign(String _brandAlign) {
		getStateHelper().put(PropertyKeys.brandAlign, _brandAlign);
	}

	/**
	 * Link URL for the Navbar Brand. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBrandHref() {
		return (String) getStateHelper().eval(PropertyKeys.brandHref);
	}

	/**
	 * Link URL for the Navbar Brand. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandHref(String _brandHref) {
		getStateHelper().put(PropertyKeys.brandHref, _brandHref);
	}

	/**
	 * Optional brand image. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBrandImg() {
		return (String) getStateHelper().eval(PropertyKeys.brandImg);
	}

	/**
	 * Optional brand image. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandImg(String _brandImg) {
		getStateHelper().put(PropertyKeys.brandImg, _brandImg);
	}

	/**
	 * CSS inline style of the brand image. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBrandImgStyle() {
		return (String) getStateHelper().eval(PropertyKeys.brandImgStyle);
	}

	/**
	 * CSS inline style of the brand image. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandImgStyle(String _brandImgStyle) {
		getStateHelper().put(PropertyKeys.brandImgStyle, _brandImgStyle);
	}

	/**
	 * CSS style class of the brand image. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBrandImgStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.brandImgStyleClass);
	}

	/**
	 * CSS style class of the brand image. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandImgStyleClass(String _brandImgStyleClass) {
		getStateHelper().put(PropertyKeys.brandImgStyleClass, _brandImgStyleClass);
	}

	/**
	 * Inline style of the optional navBar brand. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBrandStyle() {
		return (String) getStateHelper().eval(PropertyKeys.brandStyle);
	}

	/**
	 * Inline style of the optional navBar brand. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandStyle(String _brandStyle) {
		getStateHelper().put(PropertyKeys.brandStyle, _brandStyle);
	}

	/**
	 * CSS style class of the optional navBar brand. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBrandStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.brandStyleClass);
	}

	/**
	 * CSS style class of the optional navBar brand. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandStyleClass(String _brandStyleClass) {
		getStateHelper().put(PropertyKeys.brandStyleClass, _brandStyleClass);
	}

	/**
	 * Deprecated (use position and sticky instead). If specified, the Fixed Bar will be rendered on top or bottom of the page. Can be "bottom", "top" or "non-sticky". The latter is a footer that scrolls with the page. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFixed() {
		return (String) getStateHelper().eval(PropertyKeys.fixed);
	}

	/**
	 * Deprecated (use position and sticky instead). If specified, the Fixed Bar will be rendered on top or bottom of the page. Can be "bottom", "top" or "non-sticky". The latter is a footer that scrolls with the page. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFixed(String _fixed) {
		getStateHelper().put(PropertyKeys.fixed, _fixed);
	}

	/**
	 * Boolean value default is false; when set to true the navbar container will be "fluid": a full width container, spanning the entire width of the viewport. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isFluid() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.fluid, false);
	}

	/**
	 * Boolean value default is false; when set to true the navbar container will be "fluid": a full width container, spanning the entire width of the viewport. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFluid(boolean _fluid) {
		getStateHelper().put(PropertyKeys.fluid, _fluid);
	}

	/**
	 * Boolean value to specify if Navbar should use inverse color scheme. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isInverse() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.inverse, false);
	}

	/**
	 * Boolean value to specify if Navbar should use inverse color scheme. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInverse(boolean _inverse) {
		getStateHelper().put(PropertyKeys.inverse, _inverse);
	}

	/**
	 * JavaScript function which is called when the widget is clicked. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnclick() {
		return (String) getStateHelper().eval(PropertyKeys.onclick);
	}

	/**
	 * JavaScript function which is called when the widget is clicked. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
		getStateHelper().put(PropertyKeys.onclick, _onclick);
	}

	/**
	 * Position of the navBar. Legal values: "top", "bottom" or "inline". The default value is "top". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPosition() {
		return (String) getStateHelper().eval(PropertyKeys.position);
	}

	/**
	 * Position of the navBar. Legal values: "top", "bottom" or "inline". The default value is "top". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPosition(String _position) {
		getStateHelper().put(PropertyKeys.position, _position);
	}

	/**
	 * Deprecated (use position and sticky instead). If true, a full-width navbar that scrolls away with the page will be rendered. Can be true or false, default false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isStatic() {
		return (boolean) (Boolean) getStateHelper().eval("static", false);
	}

	/**
	 * Deprecated (use position and sticky instead). If true, a full-width navbar that scrolls away with the page will be rendered. Can be true or false, default false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStatic(boolean _static) {
		getStateHelper().put("static", _static);
	}

	/**
	 * Determines whether the navBar is pinned at its default position (i.e. sticky="true"), or if it scrolls with the page (sticky="false"). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isSticky() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.sticky, true);
	}

	/**
	 * Determines whether the navBar is pinned at its default position (i.e. sticky="true"), or if it scrolls with the page (sticky="false"). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSticky(boolean _sticky) {
		getStateHelper().put(PropertyKeys.sticky, _sticky);
	}

	/**
	 * Inline style of the navBar element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline style of the navBar element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of the navBar element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of the navBar element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * The text of the tooltip. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		return (String) getStateHelper().eval(PropertyKeys.tooltip);
	}

	/**
	 * The text of the tooltip. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, _tooltip);
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering errors in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipContainer() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering errors in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
		getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelay() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
		getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
		getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
		getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipPosition() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipPosition);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
		getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
	}

}
