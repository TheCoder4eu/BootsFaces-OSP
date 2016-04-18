/**
 *  Copyright 2014-2016 Dario D'Urzo
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
 */package net.bootsfaces.component.growl;

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
		AddResourcesListener.addThemedCSSResource("alerts.css");
		AddResourcesListener.addThemedCSSResource("animate.css");
	}

	protected enum PropertyKeys {
		globalOnly, showDetail, showSummary, icon, placementFrom, placementAlign, escape, style, styleClass, delay, timer, newestOnTop, allowDismiss;

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
	 * Provide support to snake-case attribute in EL-expression items
	 */
	@Override
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}
	
	/**
	 * By default, error messages encode HTML and JavaScript code. Instead of
	 * being executed, the source code is displayed. This protects you against
	 * hacker attacks. By setting escape=false, you deactivate the protection,
	 * and allow HTML and JavaScript code to be rendered.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isEscape() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.escape, true);
		return (boolean) value;
	}

	/**
	 * By default, error messages encode HTML and JavaScript code. Instead of
	 * being execute, the source code is displayed. This protects you against
	 * hacker attacks. By setting escape=false, you deactivate the protection,
	 * and allow HTML and JavaScript code to be rendered.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEscape(boolean _escape) {
		getStateHelper().put(PropertyKeys.escape, _escape);
	}
	
	/**
	 * Specifies whether the detailed information from the message should be
	 * shown. Default to false.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isShowDetail() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.showDetail, true);
		return (boolean) value;
	}

	/**
	 * Specifies whether the detailed information from the message should be
	 * shown. Default to false.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowDetail(boolean _showDetail) {
		getStateHelper().put(PropertyKeys.showDetail, _showDetail);
	}

	/**
	 * Specifies whether the summary information from the message should be
	 * shown. Defaults to true.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isShowSummary() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.showSummary, true);
		return (boolean) value;
	}

	/**
	 * Specifies whether the summary information from the message should be
	 * shown. Defaults to true.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowSummary(boolean _showSummary) {
		getStateHelper().put(PropertyKeys.showSummary, _showSummary);
	}
	
	/**
	 * Specifies whether only messages (FacesMessage objects) not associated
	 * with a specific component should be displayed, ie whether messages should
	 * be ignored if they are attached to a particular component. Defaults to
	 * false.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isGlobalOnly() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.globalOnly, false);
		return (boolean) value;
	}

	/**
	 * Specifies whether only messages (FacesMessage objects) not associated
	 * with a specific component should be displayed, ie whether messages should
	 * be ignored if they are attached to a particular component. Defaults to
	 * false.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setGlobalOnly(boolean _globalOnly) {
		getStateHelper().put(PropertyKeys.globalOnly, _globalOnly);
	}
	
	/**
	 * Icon of the message
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getIcon() {
		String value = (String) getStateHelper().eval(PropertyKeys.icon, null);
		return value;
	}

	/**
	 * Icon of the message
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIcon(String _icon) {
		getStateHelper().put(PropertyKeys.icon, _icon);
	}
	
	/**
	 * The position of growl in vertical. Valid values are 'top' or 'bottom'
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getPlacementFrom() {
		String value = (String) getStateHelper().eval(PropertyKeys.placementFrom, "top");
		return value;
	}

	/**
	 * The position of growl in vertical. Valid values are 'top' or 'bottom'
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlacementFrom(String _placementFrom) {
		getStateHelper().put(PropertyKeys.placementFrom, _placementFrom);
	}

	/**
	 * The position of growl in horizontal. Valid values are 'left', 'center' or 'right'
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getPlacementAlign() {
		String value = (String) getStateHelper().eval(PropertyKeys.placementAlign, "right");
		return value;
	}

	/**
	 * The position of growl in horizontal. Valid values are 'left', 'center' or 'right'
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlacementAlign(String _placementAlign) {
		getStateHelper().put(PropertyKeys.placementAlign, _placementAlign);
	}
	
	/**
	 * HTML: CSS styling instructions.
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
	 * HTML: CSS styling instructions.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * The CSS class for this element. Corresponds to the HTML 'class'
	 * attribute.
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
	 * The CSS class for this element. Corresponds to the HTML 'class'
	 * attribute.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}
	
	/**
	 * The message is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getDelay() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.delay, 5000);
		return (int) value;
	}

	/**
	 * The message is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDelay(int _delay) {
		getStateHelper().put(PropertyKeys.delay, _delay);
	}
	
	/**
	 * This is the amount of milliseconds removed from the notify at every timer milliseconds.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTimer() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.timer, 1000);
		return (int) value;
	}

	/**
	 * This is the amount of milliseconds removed from the notify at every timer milliseconds.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTimer(int _timer) {
		getStateHelper().put(PropertyKeys.timer, _timer);
	}
	
	/**
	 * Specifies if newest messages must be displayed on top of the others.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isNewestOnTop() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.newestOnTop, false);
		return (boolean) value;
	}

	/**
	 * Specifies if newest messages must be displayed on top of the others.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNewestOnTop(boolean _newestOnTop) {
		getStateHelper().put(PropertyKeys.newestOnTop, _newestOnTop);
	}
	
	/**
	 * Specifies whether the message can be dismissed.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isAllowDismiss() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.allowDismiss, false);
		return (boolean) value;
	}

	/**
	 * Specifies whether the message can be dismissed.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllowDismiss(boolean _allowDismiss) {
		getStateHelper().put(PropertyKeys.allowDismiss, _allowDismiss);
	}
	
}
