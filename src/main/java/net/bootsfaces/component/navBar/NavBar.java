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
@ResourceDependencies({ 
		@ResourceDependency(library = "bsf", name = "js/collapse.js", target = "body"),
		})
@ListenerFor(systemEventClass = PostAddToViewEvent.class)
@FacesComponent("net.bootsfaces.component.navBar.NavBar")
public class NavBar extends UIComponentBase implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.navBar.NavBar";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.navBar.NavBar";

	public NavBar() {
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		Tooltip.addResourceFile();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("tooltip.css");
		AddResourcesListener.addThemedCSSResource("navbar.css");
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
		alt, binding, brand, brandAlign, brandHref, brandImg, fixed, fluid, inverse, onclick, position, sticky, style, styleClass, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition;

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
	 * alternative text
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getAlt() {
		String value = (String) getStateHelper().eval(PropertyKeys.alt);
		return value;
	}

	/**
	 * alternative text
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAlt(String _alt) {
		getStateHelper().put(PropertyKeys.alt, _alt);
	}

	/**
	 * An el expression referring to a server side UIComponent instance in a
	 * backing bean.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		javax.faces.component.UIComponent value = (javax.faces.component.UIComponent) getStateHelper()
				.eval(PropertyKeys.binding);
		return value;
	}

	/**
	 * An el expression referring to a server side UIComponent instance in a
	 * backing bean.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
		getStateHelper().put(PropertyKeys.binding, _binding);
	}

	/**
	 * Brand for the Navbar.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getBrand() {
		String value = (String) getStateHelper().eval(PropertyKeys.brand);
		return value;
	}

	/**
	 * Brand for the Navbar.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrand(String _brand) {
		getStateHelper().put(PropertyKeys.brand, _brand);
	}

	/**
	 * Brand alignment, can be right or left.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getBrandAlign() {
		String value = (String) getStateHelper().eval(PropertyKeys.brandAlign);
		return value;
	}

	/**
	 * Brand alignment, can be right or left.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandAlign(String _brandAlign) {
		getStateHelper().put(PropertyKeys.brandAlign, _brandAlign);
	}

	/**
	 * Link URL for the Navbar Brand.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getBrandHref() {
		String value = (String) getStateHelper().eval(PropertyKeys.brandHref);
		return value;
	}

	/**
	 * Link URL for the Navbar Brand.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandHref(String _brandHref) {
		getStateHelper().put(PropertyKeys.brandHref, _brandHref);
	}

	/**
	 * Brand image.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getBrandImg() {
		String value = (String) getStateHelper().eval(PropertyKeys.brandImg);
		return value;
	}

	/**
	 * Brand image.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBrandImg(String _brandImg) {
		getStateHelper().put(PropertyKeys.brandImg, _brandImg);
	}

	/**
	 * Deprecated (use position and sticky instead). If specified, the Fixed Bar
	 * will be rendered on top or bottom of the page. Can be "bottom", "top" or
	 * "non-sticky". The latter is a footer that scrolls with the page.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getFixed() {
		String value = (String) getStateHelper().eval(PropertyKeys.fixed);
		return value;
	}

	/**
	 * Deprecated (use position and sticky instead). If specified, the Fixed Bar
	 * will be rendered on top or bottom of the page. Can be "bottom", "top" or
	 * "non-sticky". The latter is a footer that scrolls with the page.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFixed(String _fixed) {
		getStateHelper().put(PropertyKeys.fixed, _fixed);
	}

	/**
	 * Boolean value default is false; when set to true the navbar container
	 * will be "fluid": a full width container, spanning the entire width of the
	 * viewport.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isFluid() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.fluid, false);
		return (boolean) value;
	}

	/**
	 * Boolean value default is false; when set to true the navbar container
	 * will be "fluid": a full width container, spanning the entire width of the
	 * viewport.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFluid(boolean _fluid) {
		getStateHelper().put(PropertyKeys.fluid, _fluid);
	}

	/**
	 * Boolean value to specify if Navbar should use inverse color scheme.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isInverse() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.inverse, false);
		return (boolean) value;
	}

	/**
	 * Boolean value to specify if Navbar should use inverse color scheme.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInverse(boolean _inverse) {
		getStateHelper().put(PropertyKeys.inverse, _inverse);
	}

	/**
	 * JavaScript function which is called when the widget is clicked.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnclick() {
		String value = (String) getStateHelper().eval(PropertyKeys.onclick);
		return value;
	}

	/**
	 * JavaScript function which is called when the widget is clicked.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
		getStateHelper().put(PropertyKeys.onclick, _onclick);
	}

	/**
	 * Position of the navBar. Legal values: "top", "bottom" or "inline". The
	 * default value is "top".
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getPosition() {
		String value = (String) getStateHelper().eval(PropertyKeys.position);
		return value;
	}

	/**
	 * Position of the navBar. Legal values: "top", "bottom" or "inline". The
	 * default value is "top".
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPosition(String _position) {
		getStateHelper().put(PropertyKeys.position, _position);
	}

	/**
	 * Determines whether the navBar is pinned at its default position (i.e.
	 * sticky="true"), or if it scrolls with the page (sticky="false").
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isSticky() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.sticky, true);
		return (boolean) value;
	}

	/**
	 * Determines whether the navBar is pinned at its default position (i.e.
	 * sticky="true"), or if it scrolls with the page (sticky="false").
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSticky(boolean _sticky) {
		getStateHelper().put(PropertyKeys.sticky, _sticky);
	}

	/**
	 * Deprecated (use position and sticky instead). If true, a full-width
	 * navbar that scrolls away with the page will be rendered. Can be true or
	 * false, default false.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isStatic() {
		Boolean value = (Boolean) getStateHelper().eval("static", false);
		return (boolean) value;
	}

	/**
	 * Deprecated (use position and sticky instead). If true, a full-width
	 * navbar that scrolls away with the page will be rendered. Can be true or
	 * false, default false.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStatic(boolean _static) {
		getStateHelper().put("static", _static);
	}

	/**
	 * Inline style of the navBar element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyle() {
		String value = (String) getStateHelper().eval(PropertyKeys.style);
		return value;
	}

	/**
	 * Inline style of the navBar element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of the navBar element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String) getStateHelper().eval(PropertyKeys.styleClass);
		return value;
	}

	/**
	 * Style class of the navBar element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * The text of the tooltip.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String) getStateHelper().eval(PropertyKeys.tooltip);
		return value;
	}

	/**
	 * The text of the tooltip.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, _tooltip);
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value
	 * that can be used to fix rendering error in special cases. Also see
	 * data-container in the documentation of Bootstrap. The default value is
	 * body.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltipContainer() {
		String value = (String) getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
		return value;
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value
	 * that can be used to fix rendering error in special cases. Also see
	 * data-container in the documentation of Bootstrap. The default value is
	 * body.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
		getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelay() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
		return (int) value;
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
		getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
		return (int) value;
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
		getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
		return (int) value;
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
		getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom".
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltipPosition() {
		String value = (String) getStateHelper().eval(PropertyKeys.tooltipPosition);
		return value;
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom".
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
		getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
	}
}
