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

package net.bootsfaces.component.dateTimePicker;

import javax.faces.component.html.HtmlInputText;

/** This class holds the attributes of &lt;b:dateTimePicker /&gt;. */
public abstract class DateTimePickerCore extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {

	protected enum PropertyKeys {
		ajax, allowInputToggle, autoUpdate, binding, colLg, colMd, colSm, colXs, collapse, dayViewHeaderFormat, delay,
		disableTimeInterval, disabledDates, display, enabledDates, fieldId, fieldSize, focusOnShow, format, hidden,
		icon, iconAwesome, iconBrand, iconFlip, iconInverse, iconLight, iconPosition, iconPulse, iconRegular,
		iconRotate, iconSize, iconSolid, iconSpin, immediate, initialDate, keepInvalid, keepOpen, labelColLg,
		labelColMd, labelColSm, labelColXs, labelLargeScreen, labelMediumScreen, labelSmallScreen, labelStyle,
		labelStyleClass, labelTinyScreen, largeScreen, locale, maxDate, mediumScreen, minDate, mode, offset, offsetLg,
		offsetMd, offsetSm, offsetXs, onclick, oncomplete, ondtchange, onerror, onsuccess, placeholder, process,
		renderLabel, required, requiredMessage, showButtonPanel, showClearButton, showCloseButton, showDate, showIcon,
		showTime, showTodayButton, showWeek, sideBySide, smallScreen, span, style, styleClass, tabindex, timeStepping,
		tinyScreen, toolbarPlacement, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow,
		tooltipPosition, update, useCurrent, useStrict, viewMode, visible, weekDaysDisabled, widgetParent;
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
	 * If true, the picker will show on textbox focus and icon click when used in a button group. Default: false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isAllowInputToggle() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.allowInputToggle, false);
	}

	/**
	 * If true, the picker will show on textbox focus and icon click when used in a button group. Default: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllowInputToggle(boolean _allowInputToggle) {
		getStateHelper().put(PropertyKeys.allowInputToggle, _allowInputToggle);
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
	 * If true, a Bootstrap collapse animation is used to switch between the date picker and the time picker. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isCollapse() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.collapse, true);
	}

	/**
	 * If true, a Bootstrap collapse animation is used to switch between the date picker and the time picker. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCollapse(boolean _collapse) {
		getStateHelper().put(PropertyKeys.collapse, _collapse);
	}

	/**
	 * Changes the heading of the datepicker when in 'days' view. Default: 'MMMM YYYY'. <P>
	 * @return Returns the value of the attribute, or "MMMM YYYY", if it hasn't been set by the JSF file.
	 */
	public String getDayViewHeaderFormat() {
		return (String) getStateHelper().eval(PropertyKeys.dayViewHeaderFormat, "MMMM YYYY");
	}

	/**
	 * Changes the heading of the datepicker when in 'days' view. Default: 'MMMM YYYY'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDayViewHeaderFormat(String _dayViewHeaderFormat) {
		getStateHelper().put(PropertyKeys.dayViewHeaderFormat, _dayViewHeaderFormat);
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
	 * Disables time selection between the given moments. Default: false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDisableTimeInterval() {
		return (String) getStateHelper().eval(PropertyKeys.disableTimeInterval);
	}

	/**
	 * Disables time selection between the given moments. Default: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisableTimeInterval(String _disableTimeInterval) {
		getStateHelper().put(PropertyKeys.disableTimeInterval, _disableTimeInterval);
	}

	/**
	 * Disables selection of dates in the array, e.g. holidays. Default: null. Accepts a comma separated list of Date, moment or JavaScript string. For example: '11/22/2013 00:53' (with single quotes). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDisabledDates() {
		return (String) getStateHelper().eval(PropertyKeys.disabledDates);
	}

	/**
	 * Disables selection of dates in the array, e.g. holidays. Default: null. Accepts a comma separated list of Date, moment or JavaScript string. For example: '11/22/2013 00:53' (with single quotes). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabledDates(String _disabledDates) {
		getStateHelper().put(PropertyKeys.disabledDates, _disabledDates);
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
	 * Disables selection of dates NOT in the array, e.g. holidays. Default: false. Accepts: array of [date, moment, JavaScript string]. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getEnabledDates() {
		return (String) getStateHelper().eval(PropertyKeys.enabledDates);
	}

	/**
	 * Disables selection of dates NOT in the array, e.g. holidays. Default: false. Accepts: array of [date, moment, JavaScript string]. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnabledDates(String _enabledDates) {
		getStateHelper().put(PropertyKeys.enabledDates, _enabledDates);
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
	 * If false, the textbox will not be given focus when the picker is shown. Default: true. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isFocusOnShow() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.focusOnShow, true);
	}

	/**
	 * If false, the textbox will not be given focus when the picker is shown. Default: true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFocusOnShow(boolean _focusOnShow) {
		getStateHelper().put(PropertyKeys.focusOnShow, _focusOnShow);
	}

	/**
	 * See momentjs' docs for valid formats. Format also dictates which components are shown, e.g. MM/dd/YYYY will not display the time picker. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFormat() {
		return (String) getStateHelper().eval(PropertyKeys.format);
	}

	/**
	 * See momentjs' docs for valid formats. Format also dictates which components are shown, e.g. MM/dd/YYYY will not display the time picker. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFormat(String _format) {
		getStateHelper().put(PropertyKeys.format, _format);
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
	 * Picker Icon, can be one of the Bootstrap icon names.  <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIcon() {
		return (String) getStateHelper().eval(PropertyKeys.icon);
	}

	/**
	 * Picker Icon, can be one of the Bootstrap icon names.  <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIcon(String _icon) {
		getStateHelper().put(PropertyKeys.icon, _icon);
	}

	/**
	 * Font Awesome Icon to show in this DateTimePicker, can be one of the Font Awesome icon names. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconAwesome() {
		return (String) getStateHelper().eval(PropertyKeys.iconAwesome);
	}

	/**
	 * Font Awesome Icon to show in this DateTimePicker, can be one of the Font Awesome icon names. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconAwesome(String _iconAwesome) {
		getStateHelper().put(PropertyKeys.iconAwesome, _iconAwesome);
	}

	/**
	 * Use the free brand font of FontAwesome 5. As a side effect, every FontAwesome icon on the same page is switched to FontAwesome 5.2.0. By default, the icon set is the older version 4.7.0. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isIconBrand() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.iconBrand, false);
	}

	/**
	 * Use the free brand font of FontAwesome 5. As a side effect, every FontAwesome icon on the same page is switched to FontAwesome 5.2.0. By default, the icon set is the older version 4.7.0. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconBrand(boolean _iconBrand) {
		getStateHelper().put(PropertyKeys.iconBrand, _iconBrand);
	}

	/**
	 * Flip the icon: can be H (horizontal) or V (vertical). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconFlip() {
		return (String) getStateHelper().eval(PropertyKeys.iconFlip);
	}

	/**
	 * Flip the icon: can be H (horizontal) or V (vertical). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconFlip(String _iconFlip) {
		getStateHelper().put(PropertyKeys.iconFlip, _iconFlip);
	}

	/**
	 * Switch the icon from black-on-white to white-on-black. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isIconInverse() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.iconInverse, false);
	}

	/**
	 * Switch the icon from black-on-white to white-on-black. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconInverse(boolean _iconInverse) {
		getStateHelper().put(PropertyKeys.iconInverse, _iconInverse);
	}

	/**
	 * Use the paid 'light' font of FontAwesome 5. As a side effect, every FontAwesome icon on the same page is switched to FontAwesome 5.2.0. By default, the icon set is the older version 4.7.0. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isIconLight() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.iconLight, false);
	}

	/**
	 * Use the paid 'light' font of FontAwesome 5. As a side effect, every FontAwesome icon on the same page is switched to FontAwesome 5.2.0. By default, the icon set is the older version 4.7.0. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconLight(boolean _iconLight) {
		getStateHelper().put(PropertyKeys.iconLight, _iconLight);
	}

	/**
	 * Put the icon to the left or to the right of the input field. Legal values: 'left' and 'right'. Default value: 'right'. <P>
	 * @return Returns the value of the attribute, or "right", if it hasn't been set by the JSF file.
	 */
	public String getIconPosition() {
		return (String) getStateHelper().eval(PropertyKeys.iconPosition, "right");
	}

	/**
	 * Put the icon to the left or to the right of the input field. Legal values: 'left' and 'right'. Default value: 'right'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconPosition(String _iconPosition) {
		getStateHelper().put(PropertyKeys.iconPosition, _iconPosition);
	}

	/**
	 * Boolean value: if true the icon will rotate with 8 discrete steps. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isIconPulse() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.iconPulse, false);
	}

	/**
	 * Boolean value: if true the icon will rotate with 8 discrete steps. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconPulse(boolean _iconPulse) {
		getStateHelper().put(PropertyKeys.iconPulse, _iconPulse);
	}

	/**
	 * Use the paid 'regular' font of FontAwesome 5. As a side effect, every FontAwesome icon on the same page is switched to FontAwesome 5.2.0. By default, the icon set is the older version 4.7.0. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isIconRegular() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.iconRegular, false);
	}

	/**
	 * Use the paid 'regular' font of FontAwesome 5. As a side effect, every FontAwesome icon on the same page is switched to FontAwesome 5.2.0. By default, the icon set is the older version 4.7.0. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconRegular(boolean _iconRegular) {
		getStateHelper().put(PropertyKeys.iconRegular, _iconRegular);
	}

	/**
	 * Rotate 90 degrees the icon: Can be L,R. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconRotate() {
		return (String) getStateHelper().eval(PropertyKeys.iconRotate);
	}

	/**
	 * Rotate 90 degrees the icon: Can be L,R. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconRotate(String _iconRotate) {
		getStateHelper().put(PropertyKeys.iconRotate, _iconRotate);
	}

	/**
	 * Icon Size: legal values are lg (=133%), 2x, 3x, 4x, 5x. If you're using Fontawesome 5, also 6x, 7x, 8x, 9, 10x, xs (=75%), and sm (=87.5%) are allowed. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconSize() {
		return (String) getStateHelper().eval(PropertyKeys.iconSize);
	}

	/**
	 * Icon Size: legal values are lg (=133%), 2x, 3x, 4x, 5x. If you're using Fontawesome 5, also 6x, 7x, 8x, 9, 10x, xs (=75%), and sm (=87.5%) are allowed. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconSize(String _iconSize) {
		getStateHelper().put(PropertyKeys.iconSize, _iconSize);
	}

	/**
	 * Use the free font of FontAwesome 5. As a side effect, every FontAwesome icon on the same page is switched to FontAwesome 5.2.0. By default, the icon set is the older version 4.7.0. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isIconSolid() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.iconSolid, false);
	}

	/**
	 * Use the free font of FontAwesome 5. As a side effect, every FontAwesome icon on the same page is switched to FontAwesome 5.2.0. By default, the icon set is the older version 4.7.0. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconSolid(boolean _iconSolid) {
		getStateHelper().put(PropertyKeys.iconSolid, _iconSolid);
	}

	/**
	 * Boolean value: if true the icon will spin. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isIconSpin() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.iconSpin, false);
	}

	/**
	 * Boolean value: if true the icon will spin. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconSpin(boolean _iconSpin) {
		getStateHelper().put(PropertyKeys.iconSpin, _iconSpin);
	}

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isImmediate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.immediate, false);
	}

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
		getStateHelper().put(PropertyKeys.immediate, _immediate);
	}

	/**
	 * Sets the picker default date/time. Overrides useCurrent. Default: false. Accepts: date, moment, string. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getInitialDate() {
		return (String) getStateHelper().eval(PropertyKeys.initialDate);
	}

	/**
	 * Sets the picker default date/time. Overrides useCurrent. Default: false. Accepts: date, moment, string. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInitialDate(String _initialDate) {
		getStateHelper().put(PropertyKeys.initialDate, _initialDate);
	}

	/**
	 * Will cause the date picker to not revert or overwrite invalid dates. Default: false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isKeepInvalid() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.keepInvalid, false);
	}

	/**
	 * Will cause the date picker to not revert or overwrite invalid dates. Default: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setKeepInvalid(boolean _keepInvalid) {
		getStateHelper().put(PropertyKeys.keepInvalid, _keepInvalid);
	}

	/**
	 * Will cause the date picker to stay open after selecting a date if no time components are being used. Deafult: false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isKeepOpen() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.keepOpen, false);
	}

	/**
	 * Will cause the date picker to stay open after selecting a date if no time components are being used. Deafult: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setKeepOpen(boolean _keepOpen) {
		getStateHelper().put(PropertyKeys.keepOpen, _keepOpen);
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
	 * The desired locale. It uses the moment.js locales. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLocale() {
		return (String) getStateHelper().eval(PropertyKeys.locale);
	}

	/**
	 * The desired locale. It uses the moment.js locales. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLocale(String _locale) {
		getStateHelper().put(PropertyKeys.locale, _locale);
	}

	/**
	 * Prevents date/time selections after this date. minDate will override defaultDate and useCurrent if either of these settings are the same day since both options are invalid according to the rules you've selected. Default: false, Accepts: date, moment, string. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getMaxDate() {
		return (String) getStateHelper().eval(PropertyKeys.maxDate);
	}

	/**
	 * Prevents date/time selections after this date. minDate will override defaultDate and useCurrent if either of these settings are the same day since both options are invalid according to the rules you've selected. Default: false, Accepts: date, moment, string. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxDate(String _maxDate) {
		getStateHelper().put(PropertyKeys.maxDate, _maxDate);
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
	 * Prevents date/time selections before this date. minDate will override defaultDate and useCurrent if either of these settings are the same day since both options are invalid according to the rules you've selected. Default: false, Accepts: date, moment, string. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getMinDate() {
		return (String) getStateHelper().eval(PropertyKeys.minDate);
	}

	/**
	 * Prevents date/time selections before this date. minDate will override defaultDate and useCurrent if either of these settings are the same day since both options are invalid according to the rules you've selected. Default: false, Accepts: date, moment, string. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMinDate(String _minDate) {
		getStateHelper().put(PropertyKeys.minDate, _minDate);
	}

	/**
	 * Determine the display mode of the component. Available types are: 'inline' (display the full calendar object), 'popup' (text with icon) and 'plain' (only text). Default: 'popup'. <P>
	 * @return Returns the value of the attribute, or "component", if it hasn't been set by the JSF file.
	 */
	public String getMode() {
		return (String) getStateHelper().eval(PropertyKeys.mode, "component");
	}

	/**
	 * Determine the display mode of the component. Available types are: 'inline' (display the full calendar object), 'popup' (text with icon) and 'plain' (only text). Default: 'popup'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMode(String _mode) {
		getStateHelper().put(PropertyKeys.mode, _mode);
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
	 * JavaScript event to manage date time change. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndtchange() {
		return (String) getStateHelper().eval(PropertyKeys.ondtchange);
	}

	/**
	 * JavaScript event to manage date time change. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndtchange(String _ondtchange) {
		getStateHelper().put(PropertyKeys.ondtchange, _ondtchange);
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
	 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPlaceholder() {
		return (String) getStateHelper().eval(PropertyKeys.placeholder);
	}

	/**
	 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlaceholder(String _placeholder) {
		getStateHelper().put(PropertyKeys.placeholder, _placeholder);
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
	 * Boolean value to specify if row Buttons to the bottom of calendar should be shown (all button at once). <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isShowButtonPanel() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showButtonPanel, false);
	}

	/**
	 * Boolean value to specify if row Buttons to the bottom of calendar should be shown (all button at once). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowButtonPanel(boolean _showButtonPanel) {
		getStateHelper().put(PropertyKeys.showButtonPanel, _showButtonPanel);
	}

	/**
	 * Show the 'Clear' button in the icon toolbar. Default: false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isShowClearButton() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showClearButton, false);
	}

	/**
	 * Show the 'Clear' button in the icon toolbar. Default: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowClearButton(boolean _showClearButton) {
		getStateHelper().put(PropertyKeys.showClearButton, _showClearButton);
	}

	/**
	 * Show the 'Close' button in the icon toolbar. Default: false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isShowCloseButton() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showCloseButton, false);
	}

	/**
	 * Show the 'Close' button in the icon toolbar. Default: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowCloseButton(boolean _showCloseButton) {
		getStateHelper().put(PropertyKeys.showCloseButton, _showCloseButton);
	}

	/**
	 * By setting this to false you can deactivate the date part of the dateTimePicker. Is ignored if the attribute 'format' is used.. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isShowDate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showDate, true);
	}

	/**
	 * By setting this to false you can deactivate the date part of the dateTimePicker. Is ignored if the attribute 'format' is used.. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowDate(boolean _showDate) {
		getStateHelper().put(PropertyKeys.showDate, _showDate);
	}

	/**
	 * Show or hide the calendar icon next to the input field. Legal values: 'true' and 'false'. Default value: 'true'. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isShowIcon() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showIcon, true);
	}

	/**
	 * Show or hide the calendar icon next to the input field. Legal values: 'true' and 'false'. Default value: 'true'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowIcon(boolean _showIcon) {
		getStateHelper().put(PropertyKeys.showIcon, _showIcon);
	}

	/**
	 * By setting this to false you can deactivate the time part of the dateTimePicker. Is ignored if the attribute 'format' is used. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isShowTime() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showTime, true);
	}

	/**
	 * By setting this to false you can deactivate the time part of the dateTimePicker. Is ignored if the attribute 'format' is used. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowTime(boolean _showTime) {
		getStateHelper().put(PropertyKeys.showTime, _showTime);
	}

	/**
	 * Show the 'Today' button in the icon toolbar. Default: false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isShowTodayButton() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showTodayButton, false);
	}

	/**
	 * Show the 'Today' button in the icon toolbar. Default: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowTodayButton(boolean _showTodayButton) {
		getStateHelper().put(PropertyKeys.showTodayButton, _showTodayButton);
	}

	/**
	 * Whether or not to show week numbers to the left of week rows. Default false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isShowWeek() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showWeek, false);
	}

	/**
	 * Whether or not to show week numbers to the left of week rows. Default false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowWeek(boolean _showWeek) {
		getStateHelper().put(PropertyKeys.showWeek, _showWeek);
	}

	/**
	 * Shows the picker side by side when using the time and date together. Default: false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isSideBySide() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.sideBySide, false);
	}

	/**
	 * Shows the picker side by side when using the time and date together. Default: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSideBySide(boolean _sideBySide) {
		getStateHelper().put(PropertyKeys.sideBySide, _sideBySide);
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
	 * Position of this element in the tabbing order for the current document.  This value must be an integer between 0 and 32767. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTabindex() {
		return (String) getStateHelper().eval(PropertyKeys.tabindex);
	}

	/**
	 * Position of this element in the tabbing order for the current document.  This value must be an integer between 0 and 32767. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTabindex(String _tabindex) {
		getStateHelper().put(PropertyKeys.tabindex, _tabindex);
	}

	/**
	 * Number of minutes the up/down arrow's will move the minutes value in the time picker. Default 1. <P>
	 * @return Returns the value of the attribute, or 1, if it hasn't been set by the JSF file.
	 */
	public int getTimeStepping() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.timeStepping, 1);
	}

	/**
	 * Number of minutes the up/down arrow's will move the minutes value in the time picker. Default 1. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTimeStepping(int _timeStepping) {
		getStateHelper().put(PropertyKeys.timeStepping, _timeStepping);
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
	 * Changes the placement of the icon toolbar. Default: 'default'. Accepts: 'default', 'top', 'bottom'. <P>
	 * @return Returns the value of the attribute, or "default", if it hasn't been set by the JSF file.
	 */
	public String getToolbarPlacement() {
		return (String) getStateHelper().eval(PropertyKeys.toolbarPlacement, "default");
	}

	/**
	 * Changes the placement of the icon toolbar. Default: 'default'. Accepts: 'default', 'top', 'bottom'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setToolbarPlacement(String _toolbarPlacement) {
		getStateHelper().put(PropertyKeys.toolbarPlacement, _toolbarPlacement);
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
	 * On show, will set the picker to the current date/time. Default: true. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isUseCurrent() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.useCurrent, true);
	}

	/**
	 * On show, will set the picker to the current date/time. Default: true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUseCurrent(boolean _useCurrent) {
		getStateHelper().put(PropertyKeys.useCurrent, _useCurrent);
	}

	/**
	 * Defines if moment should use strict date parsing when considering a date to be valid. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isUseStrict() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.useStrict, false);
	}

	/**
	 * Defines if moment should use strict date parsing when considering a date to be valid. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUseStrict(boolean _useStrict) {
		getStateHelper().put(PropertyKeys.useStrict, _useStrict);
	}

	/**
	 * The default view to display when the picker is shown. Note: To limit the picker to selecting, for instance the year and month, use format: MM/YYYY. Default: 'days'. Accepts: 'decades','years','months','days'. <P>
	 * @return Returns the value of the attribute, or "days", if it hasn't been set by the JSF file.
	 */
	public String getViewMode() {
		return (String) getStateHelper().eval(PropertyKeys.viewMode, "days");
	}

	/**
	 * The default view to display when the picker is shown. Note: To limit the picker to selecting, for instance the year and month, use format: MM/YYYY. Default: 'days'. Accepts: 'decades','years','months','days'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setViewMode(String _viewMode) {
		getStateHelper().put(PropertyKeys.viewMode, _viewMode);
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

	/**
	 * Days of the week that should be disabled. Values are 0 (Sunday) to 6 (Saturday). Multiple values should be comma-separated. Example: disable weekends: 0,6 or [0,6]. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWeekDaysDisabled() {
		return (String) getStateHelper().eval(PropertyKeys.weekDaysDisabled);
	}

	/**
	 * Days of the week that should be disabled. Values are 0 (Sunday) to 6 (Saturday). Multiple values should be comma-separated. Example: disable weekends: 0,6 or [0,6]. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWeekDaysDisabled(String _weekDaysDisabled) {
		getStateHelper().put(PropertyKeys.weekDaysDisabled, _weekDaysDisabled);
	}

	/**
	 * Parent widget of the popup to prevent overlap in case of DateTimePicker inside a small size container. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWidgetParent() {
		return (String) getStateHelper().eval(PropertyKeys.widgetParent);
	}

	/**
	 * Parent widget of the popup to prevent overlap in case of DateTimePicker inside a small size container. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidgetParent(String _widgetParent) {
		getStateHelper().put(PropertyKeys.widgetParent, _widgetParent);
	}

}
