/**
 *  Copyright 2014-2016 Dario D'Urzo
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
package net.bootsfaces.component.growl;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIMessages;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

@FacesComponent("net.bootsfaces.component.growl.Growl")
public class Growl extends UIMessages {

	public Growl() {
		super();
		setRendererType("net.bootsfaces.component.GrowlRenderer");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/bootstrap-notify.min.js");
		AddResourcesListener.addThemedCSSResource("core.css");
		//!bs-less//AddResourcesListener.addThemedCSSResource("alerts.css");
		AddResourcesListener.addThemedCSSResource("animate.css");
	}

	/**
	 * Provide support to snake-case attribute in EL-expression items
	 */
	@Override
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	protected enum PropertyKeys {
		allowDismiss,
		animationEnter,
		animationExit,
		delay,
		escape,
		globalOnly,
		icon,
		newestOnTop,
		placementAlign,
		placementFrom,
		showDetail,
		showSummary,
		style,
		styleClass,
		timer;
		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	/**
	 * Specifies whether the message can be dismissed. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isAllowDismiss() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.allowDismiss, false);
	}

	/**
	 * Specifies whether the message can be dismissed. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllowDismiss(boolean _allowDismiss) {
		getStateHelper().put(PropertyKeys.allowDismiss, _allowDismiss);
	}

	/**
	 * Animation of the message while entering <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAnimationEnter() {
		return (String) getStateHelper().eval(PropertyKeys.animationEnter, "animated fadeInDown");
	}

	/**
	 * Animation of the message while entering <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAnimationEnter(String _animationEnter) {
		getStateHelper().put(PropertyKeys.animationEnter, _animationEnter);
	}

	/**
	 * Animation of the message while exiting <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAnimationExit() {
		return (String) getStateHelper().eval(PropertyKeys.animationExit, "animated fadeOutUp");
	}

	/**
	 * Animation of the message while exiting <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAnimationExit(String _animationExit) {
		getStateHelper().put(PropertyKeys.animationExit, _animationExit);
	}

	/**
	 * The message is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getDelay() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.delay, 5000);
	}

	/**
	 * The message is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDelay(int _delay) {
		getStateHelper().put(PropertyKeys.delay, _delay);
	}

	/**
	 * By default, error messages encode HTML and JavaScript code. Instead of being executed, the source code is displayed. This protects you against hacker attacks. By setting escape=false, you deactivate the protection, and allow HTML and JavaScript code to be rendered. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEscape() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.escape, false);
	}

	/**
	 * By default, error messages encode HTML and JavaScript code. Instead of being executed, the source code is displayed. This protects you against hacker attacks. By setting escape=false, you deactivate the protection, and allow HTML and JavaScript code to be rendered. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEscape(boolean _escape) {
		getStateHelper().put(PropertyKeys.escape, _escape);
	}

	/**
	 * Specifies whether only messages (FacesMessage objects) not associated with a specific component should be displayed, ie whether messages should be ignored if they are attached to a particular component. Defaults to false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isGlobalOnly() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.globalOnly, false);
	}

	/**
	 * Specifies whether only messages (FacesMessage objects) not associated with a specific component should be displayed, ie whether messages should be ignored if they are attached to a particular component. Defaults to false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setGlobalOnly(boolean _globalOnly) {
		getStateHelper().put(PropertyKeys.globalOnly, _globalOnly);
	}

	/**
	 * The glyphicon to display on message <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIcon() {
		return (String) getStateHelper().eval(PropertyKeys.icon);
	}

	/**
	 * The glyphicon to display on message <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIcon(String _icon) {
		getStateHelper().put(PropertyKeys.icon, _icon);
	}

	/**
	 * Specifies if newest messages must be displayed on top of the others. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isNewestOnTop() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.newestOnTop, false);
	}

	/**
	 * Specifies if newest messages must be displayed on top of the others. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNewestOnTop(boolean _newestOnTop) {
		getStateHelper().put(PropertyKeys.newestOnTop, _newestOnTop);
	}

	/**
	 * Horizontal position of the growl message. Valid values are 'left', 'center' or 'right'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPlacementAlign() {
		return (String) getStateHelper().eval(PropertyKeys.placementAlign, "right");
	}

	/**
	 * Horizontal position of the growl message. Valid values are 'left', 'center' or 'right'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlacementAlign(String _placementAlign) {
		getStateHelper().put(PropertyKeys.placementAlign, _placementAlign);
	}

	/**
	 * Vertical position of the growl message. Valid values are 'top' or 'bottom'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPlacementFrom() {
		return (String) getStateHelper().eval(PropertyKeys.placementFrom, "top");
	}

	/**
	 * Vertical position of the growl message. Valid values are 'top' or 'bottom'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlacementFrom(String _placementFrom) {
		getStateHelper().put(PropertyKeys.placementFrom, _placementFrom);
	}

	/**
	 * Specifies whether the detailed information from the message should be shown. Default to false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowDetail() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showDetail, false);
	}

	/**
	 * Specifies whether the detailed information from the message should be shown. Default to false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowDetail(boolean _showDetail) {
		getStateHelper().put(PropertyKeys.showDetail, _showDetail);
	}

	/**
	 * Specifies whether the summary information from the message should be shown. Defaults to true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowSummary() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showSummary, true);
	}

	/**
	 * Specifies whether the summary information from the message should be shown. Defaults to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowSummary(boolean _showSummary) {
		getStateHelper().put(PropertyKeys.showSummary, _showSummary);
	}

	/**
	 * Inline style of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline style of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of this element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of this element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * This is the amount of milliseconds removed from the notify at every timer milliseconds. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTimer() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.timer, 1000);
	}

	/**
	 * This is the amount of milliseconds removed from the notify at every timer milliseconds. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTimer(int _timer) {
		getStateHelper().put(PropertyKeys.timer, _timer);
	}

}
