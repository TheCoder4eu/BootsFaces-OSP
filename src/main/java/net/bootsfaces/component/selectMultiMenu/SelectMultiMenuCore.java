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

package net.bootsfaces.component.selectMultiMenu;

import javax.faces.component.html.HtmlInputText;

/** This class holds the attributes of &lt;b:selectMultiMenu /&gt;. */
public abstract class SelectMultiMenuCore extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {

	protected enum PropertyKeys {
		accesskey, ajax, allSelectedText, alt, autoUpdate, binding, buttonContainer, buttonWidth, buttonClass, colLg, colMd, colSm, colXs, delay, dir, disableIfEmpty, disabled, display, dropRight, enableCaseInsensitiveFiltering, enableFiltering, fieldSize, filterPlaceholder, hidden, immediate, includeSelectAllOption, inline, label, labelColLg, labelColMd, labelColSm, labelColXs, labelLargeScreen, labelMediumScreen, labelSmallScreen, labelStyle, labelStyleClass, labelTinyScreen, lang, largeScreen, maxHeight, mediumScreen, nSelectedText, nonSelectedText, numberDisplayed, offset, offsetLg, offsetMd, offsetSm, offsetXs, onchange, onclick, oncomplete, ondeselectall, ondropdownhidden, ondropdownhide, ondropdownshow, ondropdownshown, onerror, oninitialized, onselectall, onsuccess, process, radiobuttons, readonly, renderLabel, required, requiredMessage, selectAllText, size, smallScreen, span, style, styleClass, tabindex, tinyScreen, title, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, update, visible;
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
	 * Access key to transfer focus to the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAccesskey() {
		return (String) getStateHelper().eval(PropertyKeys.accesskey);
	}

	/**
	 * Access key to transfer focus to the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAccesskey(String _accesskey) {
		getStateHelper().put(PropertyKeys.accesskey, _accesskey);
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
	 * Text which is displayed if every option has been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAllSelectedText() {
		return (String) getStateHelper().eval(PropertyKeys.allSelectedText);
	}

	/**
	 * Text which is displayed if every option has been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllSelectedText(String _allSelectedText) {
		getStateHelper().put(PropertyKeys.allSelectedText, _allSelectedText);
	}

	/**
	 * Alternate textual description of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAlt() {
		return (String) getStateHelper().eval(PropertyKeys.alt);
	}

	/**
	 * Alternate textual description of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAlt(String _alt) {
		getStateHelper().put(PropertyKeys.alt, _alt);
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
	 * HTML snippet of the container holding both the button as well as the dropdown. Default: &lt;div class='btn-group' style='display:block' /&gt;. Note that the original definition of the widget doesn't use the style definition. We've added it to fix a rendering bug. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getButtonContainer() {
		return (String) getStateHelper().eval(PropertyKeys.buttonContainer);
	}

	/**
	 * HTML snippet of the container holding both the button as well as the dropdown. Default: &lt;div class='btn-group' style='display:block' /&gt;. Note that the original definition of the widget doesn't use the style definition. We've added it to fix a rendering bug. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonContainer(String _buttonContainer) {
		getStateHelper().put(PropertyKeys.buttonContainer, _buttonContainer);
	}

	/**
	 * The width of the multiselect button may be fixed using this option. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getButtonWidth() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.buttonWidth, 0);
	}

	/**
	 * The width of the multiselect button may be fixed using this option. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonWidth(int _buttonWidth) {
		getStateHelper().put(PropertyKeys.buttonWidth, _buttonWidth);
	}

	/**
	 * The CSS class of the button. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getButtonClass() {
		return (String) getStateHelper().eval(PropertyKeys.buttonClass);
	}

	/**
	 * The CSS class of the button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonClass(String _buttonClass) {
		getStateHelper().put(PropertyKeys.buttonClass, _buttonClass);
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
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphabet, based on the page content). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDir() {
		return (String) getStateHelper().eval(PropertyKeys.dir);
	}

	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphabet, based on the page content). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
		getStateHelper().put(PropertyKeys.dir, _dir);
	}

	/**
	 * If true, the button is disabled if no options are given. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isDisableIfEmpty() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disableIfEmpty, false);
	}

	/**
	 * If true, the button is disabled if no options are given. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisableIfEmpty(boolean _disableIfEmpty) {
		getStateHelper().put(PropertyKeys.disableIfEmpty, _disableIfEmpty);
	}

	/**
	 * Disables the input element, default is false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
	}

	/**
	 * Disables the input element, default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
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
	 * Moves the drop-down-area from the left hand side to the right hand side. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isDropRight() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.dropRight, false);
	}

	/**
	 * Moves the drop-down-area from the left hand side to the right hand side. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDropRight(boolean _dropRight) {
		getStateHelper().put(PropertyKeys.dropRight, _dropRight);
	}

	/**
	 * If set to true, the filter is case-insensitive. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableCaseInsensitiveFiltering() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.enableCaseInsensitiveFiltering, false);
	}

	/**
	 * If set to true, the filter is case-insensitive. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableCaseInsensitiveFiltering(boolean _enableCaseInsensitiveFiltering) {
		getStateHelper().put(PropertyKeys.enableCaseInsensitiveFiltering, _enableCaseInsensitiveFiltering);
	}

	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableFiltering() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.enableFiltering, false);
	}

	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableFiltering(boolean _enableFiltering) {
		getStateHelper().put(PropertyKeys.enableFiltering, _enableFiltering);
	}

	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFieldSize() {
		return (String) getStateHelper().eval(PropertyKeys.fieldSize);
	}

	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFieldSize(String _fieldSize) {
		getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
	}

	/**
	 * The placeholder used for the filter input. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFilterPlaceholder() {
		return (String) getStateHelper().eval(PropertyKeys.filterPlaceholder);
	}

	/**
	 * The placeholder used for the filter input. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFilterPlaceholder(String _filterPlaceholder) {
		getStateHelper().put(PropertyKeys.filterPlaceholder, _filterPlaceholder);
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
	 * If true, you can select every option with a single click. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isIncludeSelectAllOption() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.includeSelectAllOption, false);
	}

	/**
	 * If true, you can select every option with a single click. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIncludeSelectAllOption(boolean _includeSelectAllOption) {
		getStateHelper().put(PropertyKeys.includeSelectAllOption, _includeSelectAllOption);
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
	 * The Label of the field . <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		return (String) getStateHelper().eval(PropertyKeys.label);
	}

	/**
	 * The Label of the field . <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
		getStateHelper().put(PropertyKeys.label, _label);
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
	 * Code describing the language used in the generated markup for this component. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLang() {
		return (String) getStateHelper().eval(PropertyKeys.lang);
	}

	/**
	 * Code describing the language used in the generated markup for this component. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLang(String _lang) {
		getStateHelper().put(PropertyKeys.lang, _lang);
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
	 * Maximum height of the options panel. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getMaxHeight() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.maxHeight, 0);
	}

	/**
	 * Maximum height of the options panel. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxHeight(int _maxHeight) {
		getStateHelper().put(PropertyKeys.maxHeight, _maxHeight);
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
	 * Text which is displayed if more than numberDisplayed options have been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNSelectedText() {
		return (String) getStateHelper().eval(PropertyKeys.nSelectedText);
	}

	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNSelectedText(String _nSelectedText) {
		getStateHelper().put(PropertyKeys.nSelectedText, _nSelectedText);
	}

	/**
	 * Text which is displayed if no option has been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNonSelectedText() {
		return (String) getStateHelper().eval(PropertyKeys.nonSelectedText);
	}

	/**
	 * Text which is displayed if no option has been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNonSelectedText(String _nonSelectedText) {
		getStateHelper().put(PropertyKeys.nonSelectedText, _nonSelectedText);
	}

	/**
	 * Maximum number of options displayed in the button. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getNumberDisplayed() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.numberDisplayed, 0);
	}

	/**
	 * Maximum number of options displayed in the button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNumberDisplayed(int _numberDisplayed) {
		getStateHelper().put(PropertyKeys.numberDisplayed, _numberDisplayed);
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
	 * AJAX event: A function which is triggered on the change event of the options. Note that the event is not triggered when selecting or deselecting options using the select and deselect methods provided by the plugin. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnchange() {
		return (String) getStateHelper().eval(PropertyKeys.onchange);
	}

	/**
	 * AJAX event: A function which is triggered on the change event of the options. Note that the event is not triggered when selecting or deselecting options using the select and deselect methods provided by the plugin. <P>
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
	 * AJAX event: This function is triggered when the select all option is used to deselect all options. Note that this can also be triggered manually using the .multiselect('deselectAll') method. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndeselectall() {
		return (String) getStateHelper().eval(PropertyKeys.ondeselectall);
	}

	/**
	 * AJAX event: This function is triggered when the select all option is used to deselect all options. Note that this can also be triggered manually using the .multiselect('deselectAll') method. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndeselectall(String _ondeselectall) {
		getStateHelper().put(PropertyKeys.ondeselectall, _ondeselectall);
	}

	/**
	 * AJAX event: A callback called after the dropdown has been closed. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownhidden() {
		return (String) getStateHelper().eval(PropertyKeys.ondropdownhidden);
	}

	/**
	 * AJAX event: A callback called after the dropdown has been closed. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownhidden(String _ondropdownhidden) {
		getStateHelper().put(PropertyKeys.ondropdownhidden, _ondropdownhidden);
	}

	/**
	 * AJAX event: A callback called when the dropdown is closed. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownhide() {
		return (String) getStateHelper().eval(PropertyKeys.ondropdownhide);
	}

	/**
	 * AJAX event: A callback called when the dropdown is closed. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownhide(String _ondropdownhide) {
		getStateHelper().put(PropertyKeys.ondropdownhide, _ondropdownhide);
	}

	/**
	 * AJAX event: A callback called when the dropdown is shown. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownshow() {
		return (String) getStateHelper().eval(PropertyKeys.ondropdownshow);
	}

	/**
	 * AJAX event: A callback called when the dropdown is shown. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownshow(String _ondropdownshow) {
		getStateHelper().put(PropertyKeys.ondropdownshow, _ondropdownshow);
	}

	/**
	 * AJAX event: A callback called after the dropdown has been shown. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownshown() {
		return (String) getStateHelper().eval(PropertyKeys.ondropdownshown);
	}

	/**
	 * AJAX event: A callback called after the dropdown has been shown. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownshown(String _ondropdownshown) {
		getStateHelper().put(PropertyKeys.ondropdownshown, _ondropdownshown);
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
	 * AJAX event: A function which is triggered when the multiselect is finished initializing. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOninitialized() {
		return (String) getStateHelper().eval(PropertyKeys.oninitialized);
	}

	/**
	 * AJAX event: A function which is triggered when the multiselect is finished initializing. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOninitialized(String _oninitialized) {
		getStateHelper().put(PropertyKeys.oninitialized, _oninitialized);
	}

	/**
	 * AJAX event: This function is triggered when the select all option is used to select all options. Note that this can also be triggered manually using the .multiselect('selectAll') method. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnselectall() {
		return (String) getStateHelper().eval(PropertyKeys.onselectall);
	}

	/**
	 * AJAX event: This function is triggered when the select all option is used to select all options. Note that this can also be triggered manually using the .multiselect('selectAll') method. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnselectall(String _onselectall) {
		getStateHelper().put(PropertyKeys.onselectall, _onselectall);
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
	 * Set to true to display radiobuttons instead of checkboxes. Of course, in this case you can only select one option, so the widget's name is sort of a misnomer. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isRadiobuttons() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.radiobuttons, false);
	}

	/**
	 * Set to true to display radiobuttons instead of checkboxes. Of course, in this case you can only select one option, so the widget's name is sort of a misnomer. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRadiobuttons(boolean _radiobuttons) {
		getStateHelper().put(PropertyKeys.radiobuttons, _radiobuttons);
	}

	/**
	 * Flag indicating that this input element will prevent changes by the user. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isReadonly() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.readonly, false);
	}

	/**
	 * Flag indicating that this input element will prevent changes by the user. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setReadonly(boolean _readonly) {
		getStateHelper().put(PropertyKeys.readonly, _readonly);
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
	 * The text displayed for the select all option. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSelectAllText() {
		return (String) getStateHelper().eval(PropertyKeys.selectAllText);
	}

	/**
	 * The text displayed for the select all option. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectAllText(String _selectAllText) {
		getStateHelper().put(PropertyKeys.selectAllText, _selectAllText);
	}

	/**
	 * Number of characters used to determine the width of the input element. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getSize() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.size, 0);
	}

	/**
	 * Number of characters used to determine the width of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSize(int _size) {
		getStateHelper().put(PropertyKeys.size, _size);
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
	 * Style class of the input element. Is translated to the buttonContainer attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of the input element. Is translated to the buttonContainer attribute. <P>
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
	 * Advisory tooltip information. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTitle() {
		return (String) getStateHelper().eval(PropertyKeys.title);
	}

	/**
	 * Advisory tooltip information. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitle(String _title) {
		getStateHelper().put(PropertyKeys.title, _title);
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
