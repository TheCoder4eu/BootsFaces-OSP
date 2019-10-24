/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.touchSpin;

import javax.faces.component.FacesComponent;

import net.bootsfaces.component.inputText.InputText;

/** This class holds the attributes of &lt;b:spinner /&gt;. */
@FacesComponent("net.bootsfaces.component.touchSpin.TouchSpinCore")
public abstract class TouchSpinCore extends InputText implements net.bootsfaces.render.IHasTooltip {

	protected enum PropertyKeys {
		ajax, autoUpdate, binding, boostat, booster, buttonDownClass, buttonUpClass, colLg, colMd, colSm, colXs, converterMessage, decimals, delay, display, fieldId, fieldSize, forceStepDivisibility, hidden, initval, inline, labelColLg, labelColMd, labelColSm, labelColXs, labelLargeScreen, labelMediumScreen, labelSmallScreen, labelStyle, labelStyleClass, labelTinyScreen, largeScreen, max, maxBoostedStep, mediumScreen, min, mousewheel, name, offset, offsetLg, offsetMd, offsetSm, offsetXs, onchange, onclick, oncomplete, onerror, onstartdownspin, onstartspin, onstartupspin, onstopdownspin, onstopspin, onstopupspin, onsuccess, postfix, postfixExtraClass, prefix, prefixExtraClass, process, renderLabel, required, requiredMessage, smallScreen, span, step, stepInterval, stepIntervalDelay, style, styleClass, tags, tinyScreen, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, type, update, value, verticalButtons, verticalDownClass, verticalUpClass, visible;
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
	 * Whether the Button submits the form with AJAX. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isAjax() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.ajax, false);
	}

	/**
	 * Whether the Button submits the form with AJAX. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAjax(boolean _ajax) {
		getStateHelper().put(PropertyKeys.ajax, _ajax);
	}

	/**
	 * Setting this flag updates the widget on every AJAX request. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isAutoUpdate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.autoUpdate, false);
	}

	/**
	 * Setting this flag updates the widget on every AJAX request. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAutoUpdate(boolean _autoUpdate) {
		getStateHelper().put(PropertyKeys.autoUpdate, _autoUpdate);
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
	 * Boost at every nth step. <P>
	 * @return Returns the value of the attribute, or 10, if it hasn't been set by the JSF file.
	 */
	public int getBoostat() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.boostat, 10);
	}

	/**
	 * Boost at every nth step. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBoostat(int _boostat) {
		getStateHelper().put(PropertyKeys.boostat, _boostat);
	}

	/**
	 * If enabled, the the spinner is continually becoming faster as holding the button. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isBooster() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.booster, true);
	}

	/**
	 * If enabled, the the spinner is continually becoming faster as holding the button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBooster(boolean _booster) {
		getStateHelper().put(PropertyKeys.booster, _booster);
	}

	/**
	 * Class(es) of down button. <P>
	 * @return Returns the value of the attribute, or "btn btn-default", if it hasn't been set by the JSF file.
	 */
	public String getButtonDownClass() {
		return (String) getStateHelper().eval(PropertyKeys.buttonDownClass, "btn btn-default");
	}

	/**
	 * Class(es) of down button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonDownClass(String _buttonDownClass) {
		getStateHelper().put(PropertyKeys.buttonDownClass, _buttonDownClass);
	}

	/**
	 * Class(es) of up button. <P>
	 * @return Returns the value of the attribute, or "btn btn-default", if it hasn't been set by the JSF file.
	 */
	public String getButtonUpClass() {
		return (String) getStateHelper().eval(PropertyKeys.buttonUpClass, "btn btn-default");
	}

	/**
	 * Class(es) of up button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonUpClass(String _buttonUpClass) {
		getStateHelper().put(PropertyKeys.buttonUpClass, _buttonUpClass);
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColLg() {
		return (String) getStateHelper().eval(PropertyKeys.colLg, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColLg(String _colLg) {
		getStateHelper().put(PropertyKeys.colLg, _colLg);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColMd() {
		return (String) getStateHelper().eval(PropertyKeys.colMd, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColMd(String _colMd) {
		getStateHelper().put(PropertyKeys.colMd, _colMd);
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColSm() {
		return (String) getStateHelper().eval(PropertyKeys.colSm, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColSm(String _colSm) {
		getStateHelper().put(PropertyKeys.colSm, _colSm);
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColXs() {
		return (String) getStateHelper().eval(PropertyKeys.colXs, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColXs(String _colXs) {
		getStateHelper().put(PropertyKeys.colXs, _colXs);
	}

	/**
	 * Message to display when conversion fails. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getConverterMessage() {
		return (String) getStateHelper().eval(PropertyKeys.converterMessage);
	}

	/**
	 * Message to display when conversion fails. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setConverterMessage(String _converterMessage) {
		getStateHelper().put(PropertyKeys.converterMessage, _converterMessage);
	}

	/**
	 * Number of decimal points. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getDecimals() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.decimals, 0);
	}

	/**
	 * Number of decimal points. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDecimals(int _decimals) {
		getStateHelper().put(PropertyKeys.decimals, _decimals);
	}

	/**
	 * Delays the AJAX request. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDelay() {
		return (String) getStateHelper().eval(PropertyKeys.delay);
	}

	/**
	 * Delays the AJAX request. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDelay(String _delay) {
		getStateHelper().put(PropertyKeys.delay, _delay);
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * @return Returns the value of the attribute, or "block", if it hasn't been set by the JSF file.
	 */
	public String getDisplay() {
		return (String) getStateHelper().eval(PropertyKeys.display, "block");
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisplay(String _display) {
		getStateHelper().put(PropertyKeys.display, _display);
	}

	/**
	 * Unique id of the input field itself (as opposed to the id, which is belongs to the entire component, including the div surrounding the input field).  Useful for frameworks like JAAS, which require you to use a specific field id. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFieldId() {
		return (String) getStateHelper().eval(PropertyKeys.fieldId);
	}

	/**
	 * Unique id of the input field itself (as opposed to the id, which is belongs to the entire component, including the div surrounding the input field).  Useful for frameworks like JAAS, which require you to use a specific field id. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFieldId(String _fieldId) {
		getStateHelper().put(PropertyKeys.fieldId, _fieldId);
	}

	/**
	 * The size of the input. Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFieldSize() {
		return (String) getStateHelper().eval(PropertyKeys.fieldSize);
	}

	/**
	 * The size of the input. Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFieldSize(String _fieldSize) {
		getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
	}

	/**
	 * How to force the value to be divisible by step value: 'none' | 'round' | 'floor' | 'ceil' <P>
	 * @return Returns the value of the attribute, or "round", if it hasn't been set by the JSF file.
	 */
	public String getForceStepDivisibility() {
		return (String) getStateHelper().eval(PropertyKeys.forceStepDivisibility, "round");
	}

	/**
	 * How to force the value to be divisible by step value: 'none' | 'round' | 'floor' | 'ceil' <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setForceStepDivisibility(String _forceStepDivisibility) {
		getStateHelper().put(PropertyKeys.forceStepDivisibility, _forceStepDivisibility);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHidden() {
		return (String) getStateHelper().eval(PropertyKeys.hidden);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHidden(String _hidden) {
		getStateHelper().put(PropertyKeys.hidden, _hidden);
	}

	/**
	 * Applied when no explicit value is set on the input with the value attribute. Empty string means that the value remains empty on initialization. <P>
	 * @return Returns the value of the attribute, or "", if it hasn't been set by the JSF file.
	 */
	public String getInitval() {
		return (String) getStateHelper().eval(PropertyKeys.initval, "");
	}

	/**
	 * Applied when no explicit value is set on the input with the value attribute. Empty string means that the value remains empty on initialization. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInitval(String _initval) {
		getStateHelper().put(PropertyKeys.initval, _initval);
	}

	/**
	 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isInline() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.inline, false);
	}

	/**
	 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInline(boolean _inline) {
		getStateHelper().put(PropertyKeys.inline, _inline);
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLabelColLg() {
		return (String) getStateHelper().eval(PropertyKeys.labelColLg, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelColLg(String _labelColLg) {
		getStateHelper().put(PropertyKeys.labelColLg, _labelColLg);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLabelColMd() {
		return (String) getStateHelper().eval(PropertyKeys.labelColMd, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelColMd(String _labelColMd) {
		getStateHelper().put(PropertyKeys.labelColMd, _labelColMd);
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLabelColSm() {
		return (String) getStateHelper().eval(PropertyKeys.labelColSm, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelColSm(String _labelColSm) {
		getStateHelper().put(PropertyKeys.labelColSm, _labelColSm);
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLabelColXs() {
		return (String) getStateHelper().eval(PropertyKeys.labelColXs, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelColXs(String _labelColXs) {
		getStateHelper().put(PropertyKeys.labelColXs, _labelColXs);
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLabelLargeScreen() {
		return (String) getStateHelper().eval(PropertyKeys.labelLargeScreen, "-1");
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelLargeScreen(String _labelLargeScreen) {
		getStateHelper().put(PropertyKeys.labelLargeScreen, _labelLargeScreen);
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLabelMediumScreen() {
		return (String) getStateHelper().eval(PropertyKeys.labelMediumScreen, "-1");
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelMediumScreen(String _labelMediumScreen) {
		getStateHelper().put(PropertyKeys.labelMediumScreen, _labelMediumScreen);
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLabelSmallScreen() {
		return (String) getStateHelper().eval(PropertyKeys.labelSmallScreen, "-1");
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelSmallScreen(String _labelSmallScreen) {
		getStateHelper().put(PropertyKeys.labelSmallScreen, _labelSmallScreen);
	}

	/**
	 * The CSS inline style of the label. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabelStyle() {
		return (String) getStateHelper().eval(PropertyKeys.labelStyle);
	}

	/**
	 * The CSS inline style of the label. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelStyle(String _labelStyle) {
		getStateHelper().put(PropertyKeys.labelStyle, _labelStyle);
	}

	/**
	 * The CSS class of the label. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabelStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.labelStyleClass);
	}

	/**
	 * The CSS class of the label. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelStyleClass(String _labelStyleClass) {
		getStateHelper().put(PropertyKeys.labelStyleClass, _labelStyleClass);
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLabelTinyScreen() {
		return (String) getStateHelper().eval(PropertyKeys.labelTinyScreen, "-1");
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelTinyScreen(String _labelTinyScreen) {
		getStateHelper().put(PropertyKeys.labelTinyScreen, _labelTinyScreen);
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLargeScreen() {
		return (String) getStateHelper().eval(PropertyKeys.largeScreen, "-1");
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLargeScreen(String _largeScreen) {
		getStateHelper().put(PropertyKeys.largeScreen, _largeScreen);
	}

	/**
	 * Maximum value. <P>
	 * @return Returns the value of the attribute, or 100, if it hasn't been set by the JSF file.
	 */
	public double getMax() {
		return (double) (Double) getStateHelper().eval(PropertyKeys.max, 100);
	}

	/**
	 * Maximum value. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMax(double _max) {
		getStateHelper().put(PropertyKeys.max, _max);
	}

	/**
	 * Maximum step when boosted. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getMaxBoostedStep() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.maxBoostedStep, 0);
	}

	/**
	 * Maximum step when boosted. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxBoostedStep(int _maxBoostedStep) {
		getStateHelper().put(PropertyKeys.maxBoostedStep, _maxBoostedStep);
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getMediumScreen() {
		return (String) getStateHelper().eval(PropertyKeys.mediumScreen, "-1");
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMediumScreen(String _mediumScreen) {
		getStateHelper().put(PropertyKeys.mediumScreen, _mediumScreen);
	}

	/**
	 * Minimum value. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public double getMin() {
		return (double) (Double) getStateHelper().eval(PropertyKeys.min, 0);
	}

	/**
	 * Minimum value. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMin(double _min) {
		getStateHelper().put(PropertyKeys.min, _min);
	}

	/**
	 * Enables the mouse wheel to change the value of the input. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isMousewheel() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.mousewheel, true);
	}

	/**
	 * Enables the mouse wheel to change the value of the input. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMousewheel(boolean _mousewheel) {
		getStateHelper().put(PropertyKeys.mousewheel, _mousewheel);
	}

	/**
	 * The name of the field in the HMTL form and the HTTP request. Useful for frameworks like JAAS, which require you to use a specific field name. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getName() {
		return (String) getStateHelper().eval(PropertyKeys.name);
	}

	/**
	 * The name of the field in the HMTL form and the HTTP request. Useful for frameworks like JAAS, which require you to use a specific field name. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setName(String _name) {
		getStateHelper().put(PropertyKeys.name, _name);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffset() {
		return (String) getStateHelper().eval(PropertyKeys.offset);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffset(String _offset) {
		getStateHelper().put(PropertyKeys.offset, _offset);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetLg() {
		return (String) getStateHelper().eval(PropertyKeys.offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetLg(String _offsetLg) {
		getStateHelper().put(PropertyKeys.offsetLg, _offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetMd() {
		return (String) getStateHelper().eval(PropertyKeys.offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetMd(String _offsetMd) {
		getStateHelper().put(PropertyKeys.offsetMd, _offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetSm() {
		return (String) getStateHelper().eval(PropertyKeys.offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetSm(String _offsetSm) {
		getStateHelper().put(PropertyKeys.offsetSm, _offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetXs() {
		return (String) getStateHelper().eval(PropertyKeys.offsetXs);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetXs(String _offsetXs) {
		getStateHelper().put(PropertyKeys.offsetXs, _offsetXs);
	}

	/**
	 * Client side callback to execute when the value is changed with one of the buttons (but not triggered when the spinner hits the limit set by min or max. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnchange() {
		return (String) getStateHelper().eval(PropertyKeys.onchange);
	}

	/**
	 * Client side callback to execute when the value is changed with one of the buttons (but not triggered when the spinner hits the limit set by min or max. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnchange(String _onchange) {
		getStateHelper().put(PropertyKeys.onchange, _onchange);
	}

	/**
	 * The onclick attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnclick() {
		return (String) getStateHelper().eval(PropertyKeys.onclick);
	}

	/**
	 * The onclick attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
		getStateHelper().put(PropertyKeys.onclick, _onclick);
	}

	/**
	 * JavaScript to be executed when ajax completes. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOncomplete() {
		return (String) getStateHelper().eval(PropertyKeys.oncomplete);
	}

	/**
	 * JavaScript to be executed when ajax completes. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncomplete(String _oncomplete) {
		getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
	}

	/**
	 * JavaScript to be executed when ajax results on an error (including both network errors and Java exceptions). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnerror() {
		return (String) getStateHelper().eval(PropertyKeys.onerror);
	}

	/**
	 * JavaScript to be executed when ajax results on an error (including both network errors and Java exceptions). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnerror(String _onerror) {
		getStateHelper().put(PropertyKeys.onerror, _onerror);
	}

	/**
	 * Client side callback to execute when the spinner starts spinning downwards. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnstartdownspin() {
		return (String) getStateHelper().eval(PropertyKeys.onstartdownspin);
	}

	/**
	 * Client side callback to execute when the spinner starts spinning downwards. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnstartdownspin(String _onstartdownspin) {
		getStateHelper().put(PropertyKeys.onstartdownspin, _onstartdownspin);
	}

	/**
	 * Client side callback to execute when the spinner starts spinning upwards or downwards. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnstartspin() {
		return (String) getStateHelper().eval(PropertyKeys.onstartspin);
	}

	/**
	 * Client side callback to execute when the spinner starts spinning upwards or downwards. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnstartspin(String _onstartspin) {
		getStateHelper().put(PropertyKeys.onstartspin, _onstartspin);
	}

	/**
	 * Client side callback to execute when the spinner starts spinning upwards. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnstartupspin() {
		return (String) getStateHelper().eval(PropertyKeys.onstartupspin);
	}

	/**
	 * Client side callback to execute when the spinner starts spinning upwards. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnstartupspin(String _onstartupspin) {
		getStateHelper().put(PropertyKeys.onstartupspin, _onstartupspin);
	}

	/**
	 * Client side callback to execute when the spinner stops downspinning. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnstopdownspin() {
		return (String) getStateHelper().eval(PropertyKeys.onstopdownspin);
	}

	/**
	 * Client side callback to execute when the spinner stops downspinning. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnstopdownspin(String _onstopdownspin) {
		getStateHelper().put(PropertyKeys.onstopdownspin, _onstopdownspin);
	}

	/**
	 * Client side callback to execute when the spinner stops spinning. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnstopspin() {
		return (String) getStateHelper().eval(PropertyKeys.onstopspin);
	}

	/**
	 * Client side callback to execute when the spinner stops spinning. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnstopspin(String _onstopspin) {
		getStateHelper().put(PropertyKeys.onstopspin, _onstopspin);
	}

	/**
	 * Client side callback to execute when the spinner stops upspinning. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnstopupspin() {
		return (String) getStateHelper().eval(PropertyKeys.onstopupspin);
	}

	/**
	 * Client side callback to execute when the spinner stops upspinning. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnstopupspin(String _onstopupspin) {
		getStateHelper().put(PropertyKeys.onstopupspin, _onstopupspin);
	}

	/**
	 * JavaScript to be executed when ajax completes with success (i.e. there's neither a network error nor a Java exception). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnsuccess() {
		return (String) getStateHelper().eval(PropertyKeys.onsuccess);
	}

	/**
	 * JavaScript to be executed when ajax completes with success (i.e. there's neither a network error nor a Java exception). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnsuccess(String _onsuccess) {
		getStateHelper().put(PropertyKeys.onsuccess, _onsuccess);
	}

	/**
	 * Extra class(es) for prefix. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPostfix() {
		return (String) getStateHelper().eval(PropertyKeys.postfix);
	}

	/**
	 * Extra class(es) for prefix. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPostfix(String _postfix) {
		getStateHelper().put(PropertyKeys.postfix, _postfix);
	}

	/**
	 * Extra class(es) for postfix. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPostfixExtraClass() {
		return (String) getStateHelper().eval(PropertyKeys.postfixExtraClass);
	}

	/**
	 * Extra class(es) for postfix. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPostfixExtraClass(String _postfixExtraClass) {
		getStateHelper().put(PropertyKeys.postfixExtraClass, _postfixExtraClass);
	}

	/**
	 * Text before the input. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPrefix() {
		return (String) getStateHelper().eval(PropertyKeys.prefix);
	}

	/**
	 * Text before the input. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPrefix(String _prefix) {
		getStateHelper().put(PropertyKeys.prefix, _prefix);
	}

	/**
	 * Text after the input. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPrefixExtraClass() {
		return (String) getStateHelper().eval(PropertyKeys.prefixExtraClass);
	}

	/**
	 * Text after the input. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPrefixExtraClass(String _prefixExtraClass) {
		getStateHelper().put(PropertyKeys.prefixExtraClass, _prefixExtraClass);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getProcess() {
		return (String) getStateHelper().eval(PropertyKeys.process);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setProcess(String _process) {
		getStateHelper().put(PropertyKeys.process, _process);
	}

	/**
	 * Allows you to suppress automatic rendering of labels. Used internally by AngularFaces, too. <P>
	 * @return Returns the value of the attribute, or net.bootsfaces.component.ComponentUtils.isRenderLabelDefault(), if it hasn't been set by the JSF file.
	 */
	public boolean isRenderLabel() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.renderLabel,
				net.bootsfaces.component.ComponentUtils.isRenderLabelDefault());
	}

	/**
	 * Allows you to suppress automatic rendering of labels. Used internally by AngularFaces, too. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRenderLabel(boolean _renderLabel) {
		getStateHelper().put(PropertyKeys.renderLabel, _renderLabel);
	}

	/**
	 * Boolean value Require input in the component when the form is submitted. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isRequired() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.required, false);
	}

	/**
	 * Boolean value Require input in the component when the form is submitted. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequired(boolean _required) {
		getStateHelper().put(PropertyKeys.required, _required);
	}

	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRequiredMessage() {
		return (String) getStateHelper().eval(PropertyKeys.requiredMessage);
	}

	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequiredMessage(String _requiredMessage) {
		getStateHelper().put(PropertyKeys.requiredMessage, _requiredMessage);
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getSmallScreen() {
		return (String) getStateHelper().eval(PropertyKeys.smallScreen, "-1");
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSmallScreen(String _smallScreen) {
		getStateHelper().put(PropertyKeys.smallScreen, _smallScreen);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSpan() {
		return (String) getStateHelper().eval(PropertyKeys.span);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(String _span) {
		getStateHelper().put(PropertyKeys.span, _span);
	}

	/**
	 * Incremental/decremental step on up/down change. <P>
	 * @return Returns the value of the attribute, or 1, if it hasn't been set by the JSF file.
	 */
	public double getStep() {
		return (double) (Double) getStateHelper().eval(PropertyKeys.step, 1);
	}

	/**
	 * Incremental/decremental step on up/down change. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStep(double _step) {
		getStateHelper().put(PropertyKeys.step, _step);
	}

	/**
	 * Refresh rate of the spinner in milliseconds. <P>
	 * @return Returns the value of the attribute, or 100, if it hasn't been set by the JSF file.
	 */
	public int getStepInterval() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.stepInterval, 100);
	}

	/**
	 * Refresh rate of the spinner in milliseconds. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStepInterval(int _stepInterval) {
		getStateHelper().put(PropertyKeys.stepInterval, _stepInterval);
	}

	/**
	 * Time in milliseconds before the spinner starts to spin. <P>
	 * @return Returns the value of the attribute, or 500, if it hasn't been set by the JSF file.
	 */
	public int getStepIntervalDelay() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.stepIntervalDelay, 500);
	}

	/**
	 * Time in milliseconds before the spinner starts to spin. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStepIntervalDelay(int _stepIntervalDelay) {
		getStateHelper().put(PropertyKeys.stepIntervalDelay, _stepIntervalDelay);
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
	 * Show the words of the input text as tags (similar to price tags in the supermarket). You can select one or more tags. The list is sent to the backend bean as a comma-separated list. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isTags() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.tags, false);
	}

	/**
	 * Show the words of the input text as tags (similar to price tags in the supermarket). You can select one or more tags. The list is sent to the backend bean as a comma-separated list. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTags(boolean _tags) {
		getStateHelper().put(PropertyKeys.tags, _tags);
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getTinyScreen() {
		return (String) getStateHelper().eval(PropertyKeys.tinyScreen, "-1");
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTinyScreen(String _tinyScreen) {
		getStateHelper().put(PropertyKeys.tinyScreen, _tinyScreen);
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
	 * @return Returns the value of the attribute, or "body", if it hasn't been set by the JSF file.
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
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
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
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
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
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
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

	/**
	 * Type of the input. The default is text. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getType() {
		return (String) getStateHelper().eval(PropertyKeys.type);
	}

	/**
	 * Type of the input. The default is text. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setType(String _type) {
		getStateHelper().put(PropertyKeys.type, _type);
	}

	/**
	 * Component(s) to be updated with ajax. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getUpdate() {
		return (String) getStateHelper().eval(PropertyKeys.update);
	}

	/**
	 * Component(s) to be updated with ajax. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	/**
	 * Value. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getValue() {
		return (String) getStateHelper().eval(PropertyKeys.value);
	}

	/**
	 * Value. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setValue(String _value) {
		getStateHelper().put(PropertyKeys.value, _value);
	}

	/**
	 * Enables the traditional up/down buttons. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isVerticalButtons() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.verticalButtons, false);
	}

	/**
	 * Enables the traditional up/down buttons. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVerticalButtons(boolean _verticalButtons) {
		getStateHelper().put(PropertyKeys.verticalButtons, _verticalButtons);
	}

	/**
	 * Class of the down button with vertical buttons mode enabled. <P>
	 * @return Returns the value of the attribute, or "glyphicon glyphicon-chevron-down", if it hasn't been set by the JSF file.
	 */
	public String getVerticalDownClass() {
		return (String) getStateHelper().eval(PropertyKeys.verticalDownClass, "glyphicon glyphicon-chevron-down");
	}

	/**
	 * Class of the down button with vertical buttons mode enabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVerticalDownClass(String _verticalDownClass) {
		getStateHelper().put(PropertyKeys.verticalDownClass, _verticalDownClass);
	}

	/**
	 * Class of the up button with vertical buttons mode enabled. <P>
	 * @return Returns the value of the attribute, or "glyphicon glyphicon-chevron-up", if it hasn't been set by the JSF file.
	 */
	public String getVerticalUpClass() {
		return (String) getStateHelper().eval(PropertyKeys.verticalUpClass, "glyphicon glyphicon-chevron-up");
	}

	/**
	 * Class of the up button with vertical buttons mode enabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVerticalUpClass(String _verticalUpClass) {
		getStateHelper().put(PropertyKeys.verticalUpClass, _verticalUpClass);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getVisible() {
		return (String) getStateHelper().eval(PropertyKeys.visible);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVisible(String _visible) {
		getStateHelper().put(PropertyKeys.visible, _visible);
	}

}
