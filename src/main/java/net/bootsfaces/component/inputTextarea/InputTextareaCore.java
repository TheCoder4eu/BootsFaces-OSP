/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.inputTextarea;

import javax.faces.component.html.HtmlInputText;

/** This class holds the attributes of &lt;b:inputTextarea /&gt;. */
public abstract class InputTextareaCore extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {

	protected enum PropertyKeys {
		accesskey, ajax, alt, autoUpdate, autocomplete, binding, colLg, colMd, colSm, colXs, converterMessage, delay, dir, disabled, display, fieldSize, hidden, immediate, inline, label, labelColLg, labelColMd, labelColSm, labelColXs, labelLargeScreen, labelMediumScreen, labelSmallScreen, labelStyle, labelStyleClass, labelTinyScreen, lang, largeScreen, mediumScreen, offset, offsetLg, offsetMd, offsetSm, offsetXs, onblur, onchange, onclick, oncomplete, ondblclick, onerror, onfocus, onkeydown, onkeypress, onkeyup, onmousedown, onmousemove, onmouseout, onmouseover, onmouseup, onselect, onsuccess, placeholder, process, readonly, renderLabel, required, requiredMessage, rows, smallScreen, span, style, styleClass, tabindex, tinyScreen, title, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, type, update, visible;
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
	 * Controls browser autocomplete behavior. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAutocomplete() {
		return (String) getStateHelper().eval(PropertyKeys.autocomplete);
	}

	/**
	 * Controls browser autocomplete behavior. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAutocomplete(String _autocomplete) {
		getStateHelper().put(PropertyKeys.autocomplete, _autocomplete);
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
	 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
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
	 * An optional label of the field. The label is only shown if you also set render-attribute='true'. The label is also used for error messages. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		return (String) getStateHelper().eval(PropertyKeys.label);
	}

	/**
	 * An optional label of the field. The label is only shown if you also set render-attribute='true'. The label is also used for error messages. <P>
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
	 * Client side callback to execute when input element loses focus. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnblur() {
		return (String) getStateHelper().eval(PropertyKeys.onblur);
	}

	/**
	 * Client side callback to execute when input element loses focus. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnblur(String _onblur) {
		getStateHelper().put(PropertyKeys.onblur, _onblur);
	}

	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnchange() {
		return (String) getStateHelper().eval(PropertyKeys.onchange);
	}

	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. <P>
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
	 * Client side callback to execute when input element is double clicked. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndblclick() {
		return (String) getStateHelper().eval(PropertyKeys.ondblclick);
	}

	/**
	 * Client side callback to execute when input element is double clicked. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndblclick(String _ondblclick) {
		getStateHelper().put(PropertyKeys.ondblclick, _ondblclick);
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
	 * Client side callback to execute when input element receives focus. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnfocus() {
		return (String) getStateHelper().eval(PropertyKeys.onfocus);
	}

	/**
	 * Client side callback to execute when input element receives focus. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnfocus(String _onfocus) {
		getStateHelper().put(PropertyKeys.onfocus, _onfocus);
	}

	/**
	 * Client side callback to execute when a key is pressed down over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnkeydown() {
		return (String) getStateHelper().eval(PropertyKeys.onkeydown);
	}

	/**
	 * Client side callback to execute when a key is pressed down over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeydown(String _onkeydown) {
		getStateHelper().put(PropertyKeys.onkeydown, _onkeydown);
	}

	/**
	 * Client side callback to execute when a key is pressed and released over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnkeypress() {
		return (String) getStateHelper().eval(PropertyKeys.onkeypress);
	}

	/**
	 * Client side callback to execute when a key is pressed and released over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeypress(String _onkeypress) {
		getStateHelper().put(PropertyKeys.onkeypress, _onkeypress);
	}

	/**
	 * Client side callback to execute when a key is released over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnkeyup() {
		return (String) getStateHelper().eval(PropertyKeys.onkeyup);
	}

	/**
	 * Client side callback to execute when a key is released over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeyup(String _onkeyup) {
		getStateHelper().put(PropertyKeys.onkeyup, _onkeyup);
	}

	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousedown() {
		return (String) getStateHelper().eval(PropertyKeys.onmousedown);
	}

	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousedown(String _onmousedown) {
		getStateHelper().put(PropertyKeys.onmousedown, _onmousedown);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved within input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousemove() {
		return (String) getStateHelper().eval(PropertyKeys.onmousemove);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved within input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousemove(String _onmousemove) {
		getStateHelper().put(PropertyKeys.onmousemove, _onmousemove);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved away from input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseout() {
		return (String) getStateHelper().eval(PropertyKeys.onmouseout);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved away from input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseout(String _onmouseout) {
		getStateHelper().put(PropertyKeys.onmouseout, _onmouseout);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved onto input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseover() {
		return (String) getStateHelper().eval(PropertyKeys.onmouseover);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved onto input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseover(String _onmouseover) {
		getStateHelper().put(PropertyKeys.onmouseover, _onmouseover);
	}

	/**
	 * Client side callback to execute when a pointer input element is released over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseup() {
		return (String) getStateHelper().eval(PropertyKeys.onmouseup);
	}

	/**
	 * Client side callback to execute when a pointer input element is released over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseup(String _onmouseup) {
		getStateHelper().put(PropertyKeys.onmouseup, _onmouseup);
	}

	/**
	 * Client side callback to execute when text within input element is selected by user. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnselect() {
		return (String) getStateHelper().eval(PropertyKeys.onselect);
	}

	/**
	 * Client side callback to execute when text within input element is selected by user. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnselect(String _onselect) {
		getStateHelper().put(PropertyKeys.onselect, _onselect);
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
	 * Number of rows <P>
	 * @return Returns the value of the attribute, or 5, if it hasn't been set by the JSF file.
	 */
	public int getRows() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.rows, 5);
	}

	/**
	 * Number of rows <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRows(int _rows) {
		getStateHelper().put(PropertyKeys.rows, _rows);
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
