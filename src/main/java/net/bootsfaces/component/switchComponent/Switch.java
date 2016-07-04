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

package net.bootsfaces.component.switchComponent;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:switch /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/bootstrap-switch.js", target = "head") })
@FacesComponent("net.bootsfaces.component.switch.Switch")
public class Switch extends net.bootsfaces.component.selectBooleanCheckbox.SelectBooleanCheckbox
		implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.switch.Switch";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.switch.Switch";

	public Switch() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("bootstrap-switch.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("core.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		onText,
		offText,
		onColor,
		offColor,
		indeterminate,
		inverse,
		switchsize,
		animate,
		disabled,
		readonly,
		labelText,
		handleWidth,
		labelWidth;

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
	 * Optional label of the active switch. The default value is 'on'.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnText() {
		String value = (String) getStateHelper().eval(PropertyKeys.onText);
		return value;
	}

	/**
	 * Optional label of the active switch. The default value is 'on'.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnText(String _onText) {
		getStateHelper().put(PropertyKeys.onText, _onText);
	}

	/**
	 * Optional label of the inactive switch. The default value is 'off'.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOffText() {
		String value = (String) getStateHelper().eval(PropertyKeys.offText);
		return value;
	}

	/**
	 * Optional label of the inactive switch. The default value is 'off'.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffText(String _offText) {
		getStateHelper().put(PropertyKeys.offText, _offText);
	}

	/**
	 * Color of the left hand side of the switch. Legal values: 'primary',
	 * 'info', 'success', 'warning', 'danger', 'default'. Default value:
	 * 'primary'.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnColor() {
		String value = (String) getStateHelper().eval(PropertyKeys.onColor);
		return value;
	}

	/**
	 * Color of the left hand side of the switch. Legal values: 'primary',
	 * 'info', 'success', 'warning', 'danger', 'default'. Default value:
	 * 'primary'.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnColor(String _onColor) {
		getStateHelper().put(PropertyKeys.onColor, _onColor);
	}

	/**
	 * Color of the right hand side of the switch. Legal values: 'primary',
	 * 'info', 'success', 'warning', 'danger', 'default'. Default value:
	 * 'primary'.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOffColor() {
		String value = (String) getStateHelper().eval(PropertyKeys.offColor);
		return value;
	}

	/**
	 * Color of the right hand side of the switch. Legal values: 'primary',
	 * 'info', 'success', 'warning', 'danger', 'default'. Default value:
	 * 'primary'.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffColor(String _offColor) {
		getStateHelper().put(PropertyKeys.offColor, _offColor);
	}

	/**
	 * Indeterminate state. Turned off by default.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isIndeterminate() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.indeterminate, false);
		return (boolean) value;
	}

	/**
	 * Indeterminate state. Turned off by default.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIndeterminate(boolean _indeterminate) {
		getStateHelper().put(PropertyKeys.indeterminate, _indeterminate);
	}

	/**
	 * Inverse switch direction. Default value: false
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
	 * Inverse switch direction. Default value: false
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInverse(boolean _inverse) {
		getStateHelper().put(PropertyKeys.inverse, _inverse);
	}

	/**
	 * The checkbox size. null, 'mini', 'small', 'normal', 'large'. Default
	 * value: 'null'.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getSwitchsize() {
		String value = (String) getStateHelper().eval(PropertyKeys.switchsize);
		return value;
	}

	/**
	 * The checkbox size. null, 'mini', 'small', 'normal', 'large'. Default
	 * value: 'null'.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSwitchsize(String _switchsize) {
		getStateHelper().put(PropertyKeys.switchsize, _switchsize);
	}

	/**
	 * Animate the switch. Default value: true.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isAnimate() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.animate, false);
		return (boolean) value;
	}

	/**
	 * Animate the switch. Default value: true.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAnimate(boolean _animate) {
		getStateHelper().put(PropertyKeys.animate, _animate);
	}

	/**
	 * Is the switch disabled? Default value: false.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isDisabled() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
		return (boolean) value;
	}

	/**
	 * Is the switch disabled? Default value: false.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * Is the switch readonly? Default value: false.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isReadonly() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.readonly, false);
		return (boolean) value;
	}

	/**
	 * Is the switch readonly? Default value: false.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setReadonly(boolean _readonly) {
		getStateHelper().put(PropertyKeys.readonly, _readonly);
	}

	/**
	 * Text of the center handle of the switch. Default value: a space (nbsp).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getLabelText() {
		String value = (String) getStateHelper().eval(PropertyKeys.labelText);
		return value;
	}

	/**
	 * Text of the center handle of the switch. Default value: a space (nbsp).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelText(String _labelText) {
		getStateHelper().put(PropertyKeys.labelText, _labelText);
	}

	/**
	 * Width of the left and right sides in pixels.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getHandleWidth() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.handleWidth, 0);
		return (int) value;
	}

	/**
	 * Width of the left and right sides in pixels.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHandleWidth(int _handleWidth) {
		getStateHelper().put(PropertyKeys.handleWidth, _handleWidth);
	}

	/**
	 * Width of the center handle in pixels.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getLabelWidth() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.labelWidth, 0);
		return (int) value;
	}

	/**
	 * Width of the center handle in pixels.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelWidth(int _labelWidth) {
		getStateHelper().put(PropertyKeys.labelWidth, _labelWidth);
	}

}
