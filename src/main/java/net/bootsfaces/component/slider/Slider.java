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

package net.bootsfaces.component.slider;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:slider /&gt;. */
@FacesComponent("net.bootsfaces.component.slider.Slider")
public class Slider extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.slider.Slider";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.slider.Slider";

	public Slider() {
		//!bs-less//AddResourcesListener.addThemedCSSResource("badges.css");
		AddResourcesListener.addThemedCSSResource("jq.ui.core.css");
		AddResourcesListener.addThemedCSSResource("jq.ui.theme.css");
		AddResourcesListener.addThemedCSSResource("jq.ui.slider.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/core.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/widget.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/mouse.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/slider.js");
		Tooltip.addResourceFiles();
		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/** Method added to prevent AngularFaces from setting the type */
	public String getType() {
		String mode = A.asString(getAttributes().get("mode"), "badge");
		return mode.equals("edit") ? "text" : "hidden";
	}

	/**
	 * Method added to prevent AngularFaces from setting the type
	 *
	 * @param type
	 *            this parameter is ignored
	 */
	public void setType(String type) {
		// ignore the type - it's defined by the mode attribute

	}

	protected enum PropertyKeys {
		binding,
		disabled,
		handleShape,
		handleSize,
		inline,
		label,
		labelStyle,
		labelStyleClass,
		max,
		min,
		mode,
		orientation,
		span,
		step,
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
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
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
	 * The default handle is squared. Specifing the "round" value for this attribute will turn the handle shape to a circle. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHandleShape() {
		return (String) getStateHelper().eval(PropertyKeys.handleShape);
	}

	/**
	 * The default handle is squared. Specifing the "round" value for this attribute will turn the handle shape to a circle. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHandleShape(String _handleShape) {
		getStateHelper().put(PropertyKeys.handleShape, _handleShape);
	}

	/**
	 * In some situations (eg. mobile phones) the slider handle may be difficult to operate because of its size. Specifing the "md" or "lg" value for this attribute will change the handle size accordingly. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHandleSize() {
		return (String) getStateHelper().eval(PropertyKeys.handleSize);
	}

	/**
	 * In some situations (eg. mobile phones) the slider handle may be difficult to operate because of its size. Specifing the "md" or "lg" value for this attribute will change the handle size accordingly. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHandleSize(String _handleSize) {
		getStateHelper().put(PropertyKeys.handleSize, _handleSize);
	}

	/**
	 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
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
	 * The maximum value of the slider. (default 100) <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getMax() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.max, 100);
	}

	/**
	 * The maximum value of the slider. (default 100) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMax(int _max) {
		getStateHelper().put(PropertyKeys.max, _max);
	}

	/**
	 * The minimum value of the slider. (default 0) <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getMin() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.min, 0);
	}

	/**
	 * The minimum value of the slider. (default 0) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMin(int _min) {
		getStateHelper().put(PropertyKeys.min, _min);
	}

	/**
	 * Mode of the Slider Widget. There are three modes available : badge, edit and basic. In basic mode, only the slider and the label(if present) will be shown and the slider value will be hidden. In badge mode, the default, the slider value will be shown in a badge. In edit mode, an editable input field showing the slider value will be shown; in this mode you can change the value by sliding or editing the value in the field. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
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
	 * Determines whether the slider handles move horizontally (min on left, max on right) or vertically (min on bottom, max on top). Possible values: "horizontal"(default), "vertical", "vertical-bottom". If vertical is specified, the Label is rendered on top, then the value and the slider on the bottom. If vertical-bottom is specified, the slider is rendered on top, then the value and the Label on the bottom. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOrientation() {
		return (String) getStateHelper().eval(PropertyKeys.orientation);
	}

	/**
	 * Determines whether the slider handles move horizontally (min on left, max on right) or vertically (min on bottom, max on top). Possible values: "horizontal"(default), "vertical", "vertical-bottom". If vertical is specified, the Label is rendered on top, then the value and the slider on the bottom. If vertical-bottom is specified, the slider is rendered on top, then the value and the Label on the bottom. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOrientation(String _orientation) {
		getStateHelper().put(PropertyKeys.orientation, _orientation);
	}

	/**
	 * The column span of the slider. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
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
	 * The step of the slider. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getStep() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.step, 0);
	}

	/**
	 * The step of the slider. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStep(int _step) {
		getStateHelper().put(PropertyKeys.step, _step);
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
	 * Style class of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of the input element. <P>
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
