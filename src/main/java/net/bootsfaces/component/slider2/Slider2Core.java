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

package net.bootsfaces.component.slider2;

import javax.faces.component.html.HtmlInputText;

/** This class holds the attributes of &lt;b:slider2 /&gt;. */
public abstract class Slider2Core extends HtmlInputText {

	protected enum PropertyKeys {
		autoUpdate, badgeSpan, badgeStyle, badgeStyleClass, binding, disabled, focus, formatter, handleShape, inline,
		label, labelStyle, labelStyleClass, labelledBy, max, min, mode, natural_arrow_keys, orientation, precision,
		reversed, scale, selection, span, step, style, styleClass, tooltipSliderPosition, tooltipSplit,
		tooltipVisibility, widgetVar;
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
	 * The column span of the badge or input field (if it's shown). <P>
	 * @return Returns the value of the attribute, or 1, if it hasn't been set by the JSF file.
	 */
	public int getBadgeSpan() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.badgeSpan, 1);
	}

	/**
	 * The column span of the badge or input field (if it's shown). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBadgeSpan(int _badgeSpan) {
		getStateHelper().put(PropertyKeys.badgeSpan, _badgeSpan);
	}

	/**
	 * Inline style of the badge or input field (if it's shown). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBadgeStyle() {
		return (String) getStateHelper().eval(PropertyKeys.badgeStyle);
	}

	/**
	 * Inline style of the badge or input field (if it's shown). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBadgeStyle(String _badgeStyle) {
		getStateHelper().put(PropertyKeys.badgeStyle, _badgeStyle);
	}

	/**
	 * Style class of the badge or input field (if it's shown). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getBadgeStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.badgeStyleClass);
	}

	/**
	 * Style class of the badge or input field (if it's shown). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBadgeStyleClass(String _badgeStyleClass) {
		getStateHelper().put(PropertyKeys.badgeStyleClass, _badgeStyleClass);
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
	 * If true, you can't move the slider, nor can you edit the number. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
	}

	/**
	 * If true, you can't move the slider, nor can you edit the number. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * Focus the appropriate slider handle after a value change. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isFocus() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.focus, false);
	}

	/**
	 * Focus the appropriate slider handle after a value change. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFocus(boolean _focus) {
		getStateHelper().put(PropertyKeys.focus, _focus);
	}

	/**
	 * Javascript function that returns the plain value to be displayed in the tooltip <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFormatter() {
		return (String) getStateHelper().eval(PropertyKeys.formatter);
	}

	/**
	 * Javascript function that returns the plain value to be displayed in the tooltip <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFormatter(String _formatter) {
		getStateHelper().put(PropertyKeys.formatter, _formatter);
	}

	/**
	 * Handle shape. Accepts: 'round', 'square', 'triangle' or 'custom' <P>
	 * @return Returns the value of the attribute, or "round", if it hasn't been set by the JSF file.
	 */
	public String getHandleShape() {
		return (String) getStateHelper().eval(PropertyKeys.handleShape, "round");
	}

	/**
	 * Handle shape. Accepts: 'round', 'square', 'triangle' or 'custom' <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHandleShape(String _handleShape) {
		getStateHelper().put(PropertyKeys.handleShape, _handleShape);
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
	 * Label for the widget field. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		return (String) getStateHelper().eval(PropertyKeys.label);
	}

	/**
	 * Label for the widget field. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
		getStateHelper().put(PropertyKeys.label, _label);
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
	 * Labels for the slider handle's, Use array for multiple values in a range slider. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabelledBy() {
		return (String) getStateHelper().eval(PropertyKeys.labelledBy);
	}

	/**
	 * Labels for the slider handle's, Use array for multiple values in a range slider. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelledBy(String _labelledBy) {
		getStateHelper().put(PropertyKeys.labelledBy, _labelledBy);
	}

	/**
	 * The maximum value of the slider. (default 100) <P>
	 * @return Returns the value of the attribute, or 100, if it hasn't been set by the JSF file.
	 */
	public java.lang.Object getMax() {
		return (java.lang.Object) getStateHelper().eval(PropertyKeys.max, 100);
	}

	/**
	 * The maximum value of the slider. (default 100) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMax(java.lang.Object _max) {
		getStateHelper().put(PropertyKeys.max, _max);
	}

	/**
	 * The maximum value of the slider. (default 0) <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public java.lang.Object getMin() {
		return (java.lang.Object) getStateHelper().eval(PropertyKeys.min, 0);
	}

	/**
	 * The maximum value of the slider. (default 0) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMin(java.lang.Object _min) {
		getStateHelper().put(PropertyKeys.min, _min);
	}

	/**
	 * Mode of the Slider Widget. There are three modes available : badge, edit and basic. In basic mode, only the slider and the label(if present) will be shown and the slider value will be hidden. In badge mode, the default, the slider value will be shown in a badge. In edit mode, an editable input field showing the slider value will be shown; in this mode you can change the value by sliding or editing the value in the field. <P>
	 * @return Returns the value of the attribute, or "badge", if it hasn't been set by the JSF file.
	 */
	public String getMode() {
		return (String) getStateHelper().eval(PropertyKeys.mode, "badge");
	}

	/**
	 * Mode of the Slider Widget. There are three modes available : badge, edit and basic. In basic mode, only the slider and the label(if present) will be shown and the slider value will be hidden. In badge mode, the default, the slider value will be shown in a badge. In edit mode, an editable input field showing the slider value will be shown; in this mode you can change the value by sliding or editing the value in the field. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMode(String _mode) {
		getStateHelper().put(PropertyKeys.mode, _mode);
	}

	/**
	 * The natural order is used for the arrow keys. Arrow up select the upper slider value for vertical sliders, arrow right the righter slider value for a horizontal slider - no matter if the slider was reversed or not. By default the arrow keys are oriented by arrow up/right to the higher slider value, arrow down/left to the lower slider value. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isNatural_arrow_keys() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.natural_arrow_keys, false);
	}

	/**
	 * The natural order is used for the arrow keys. Arrow up select the upper slider value for vertical sliders, arrow right the righter slider value for a horizontal slider - no matter if the slider was reversed or not. By default the arrow keys are oriented by arrow up/right to the higher slider value, arrow down/left to the lower slider value. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNatural_arrow_keys(boolean _natural_arrow_keys) {
		getStateHelper().put(PropertyKeys.natural_arrow_keys, _natural_arrow_keys);
	}

	/**
	 * Set the orientation. Accepts 'vertical' or 'horizontal' <P>
	 * @return Returns the value of the attribute, or "horizontal", if it hasn't been set by the JSF file.
	 */
	public String getOrientation() {
		return (String) getStateHelper().eval(PropertyKeys.orientation, "horizontal");
	}

	/**
	 * Set the orientation. Accepts 'vertical' or 'horizontal' <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOrientation(String _orientation) {
		getStateHelper().put(PropertyKeys.orientation, _orientation);
	}

	/**
	 * The number of digits shown after the decimal. Defaults to the number of digits after the decimal of step value. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getPrecision() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.precision, 0);
	}

	/**
	 * The number of digits shown after the decimal. Defaults to the number of digits after the decimal of step value. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPrecision(int _precision) {
		getStateHelper().put(PropertyKeys.precision, _precision);
	}

	/**
	 * Whether or not the slider should be reversed <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isReversed() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.reversed, false);
	}

	/**
	 * Whether or not the slider should be reversed <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setReversed(boolean _reversed) {
		getStateHelper().put(PropertyKeys.reversed, _reversed);
	}

	/**
	 * Set to 'logarithmic' to use a logarithmic scale. <P>
	 * @return Returns the value of the attribute, or "linear", if it hasn't been set by the JSF file.
	 */
	public String getScale() {
		return (String) getStateHelper().eval(PropertyKeys.scale, "linear");
	}

	/**
	 * Set to 'logarithmic' to use a logarithmic scale. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setScale(String _scale) {
		getStateHelper().put(PropertyKeys.scale, _scale);
	}

	/**
	 * Selection placement. Accepts: 'before', 'after' or 'none'. In case of a range slider, the selection will be placed between the handles. <P>
	 * @return Returns the value of the attribute, or "before", if it hasn't been set by the JSF file.
	 */
	public String getSelection() {
		return (String) getStateHelper().eval(PropertyKeys.selection, "before");
	}

	/**
	 * Selection placement. Accepts: 'before', 'after' or 'none'. In case of a range slider, the selection will be placed between the handles. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelection(String _selection) {
		getStateHelper().put(PropertyKeys.selection, _selection);
	}

	/**
	 * The column span of the slider. <P>
	 * @return Returns the value of the attribute, or 12, if it hasn't been set by the JSF file.
	 */
	public int getSpan() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.span, 12);
	}

	/**
	 * The column span of the slider. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(int _span) {
		getStateHelper().put(PropertyKeys.span, _span);
	}

	/**
	 * Increment step <P>
	 * @return Returns the value of the attribute, or 1, if it hasn't been set by the JSF file.
	 */
	public java.lang.Object getStep() {
		return (java.lang.Object) getStateHelper().eval(PropertyKeys.step, 1);
	}

	/**
	 * Increment step <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStep(java.lang.Object _step) {
		getStateHelper().put(PropertyKeys.step, _step);
	}

	/**
	 * Inline style of the entire slider. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline style of the entire slider. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of the entire slider. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of the entire slider. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Position of tooltip, relative to slider. Accepts 'top'/'bottom' for horizontal sliders and 'left'/'right' for vertically orientated sliders. Default positions are 'top' for horizontal and 'right' for vertical slider. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipSliderPosition() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipSliderPosition);
	}

	/**
	 * Position of tooltip, relative to slider. Accepts 'top'/'bottom' for horizontal sliders and 'left'/'right' for vertically orientated sliders. Default positions are 'top' for horizontal and 'right' for vertical slider. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipSliderPosition(String _tooltipSliderPosition) {
		getStateHelper().put(PropertyKeys.tooltipSliderPosition, _tooltipSliderPosition);
	}

	/**
	 * If false show one tootip if true show two tooltips one for each handler. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isTooltipSplit() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.tooltipSplit, false);
	}

	/**
	 * If false show one tootip if true show two tooltips one for each handler. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipSplit(boolean _tooltipSplit) {
		getStateHelper().put(PropertyKeys.tooltipSplit, _tooltipSplit);
	}

	/**
	 * Whether to show the tooltip on drag, hide the tooltip, or always show the tooltip. Accepts: 'show', 'hide', or 'always'. <P>
	 * @return Returns the value of the attribute, or "show", if it hasn't been set by the JSF file.
	 */
	public String getTooltipVisibility() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipVisibility, "show");
	}

	/**
	 * Whether to show the tooltip on drag, hide the tooltip, or always show the tooltip. Accepts: 'show', 'hide', or 'always'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipVisibility(String _tooltipVisibility) {
		getStateHelper().put(PropertyKeys.tooltipVisibility, _tooltipVisibility);
	}

	/**
	 * Optional widget variable to access the slider2 widget in JavaScript code. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar);
	}

	/**
	 * Optional widget variable to access the slider2 widget in JavaScript code. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidgetVar(String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}

}
