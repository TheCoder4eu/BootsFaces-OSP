/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.slider;

import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.C;
import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.render.Tooltip;

/** This class holds the attributes of &lt;b:slider /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/badges.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/jq.ui.core.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/jq.ui.theme.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/jq.ui.slider.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
	@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
	@ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head"),
	/* moved to constructor @ResourceDependency(library = "bsf", name = "jq/ui/core.js", target = "body"), */
	/* moved to constructor @ResourceDependency(library = "bsf", name = "jq/ui/widget.js", target = "body"), */
	/* moved to constructor @ResourceDependency(library = "bsf", name = "jq/ui/mouse.js", target = "body"),*/ 
	/* moved to constructor @ResourceDependency(library = "bsf", name = "jq/ui/slider.js", target = "body") , */
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")})
@FacesComponent("net.bootsfaces.component.slider.Slider")
public class Slider extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.slider.Slider";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.slider.Slider";

	private Map<String, Object> attributes;

	public Slider() {
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
        AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/core.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/widget.js");
        AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/mouse.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/slider.js");
		Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(super.getAttributes());
		return attributes;
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
		binding, disabled, handleShape, handleSize, id, inline, label, max, min, mode, orientation, span, step, style, styleClass, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition;

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
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering error in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipContainer() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
		return  value;
	}
	
	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering error in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
	    getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
    }
	/**
	 * An el expression referring to a server side UIComponent instance in a
	 * backing bean. <P>
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
	 * backing bean. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
		getStateHelper().put(PropertyKeys.binding, _binding);
	}

	/**
	 * If true, you can't move the slider, nor can you edit the number. <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isDisabled() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
		return (boolean) value;
	}

	/**
	 * If true, you can't move the slider, nor can you edit the number. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * The default handle is squared. Specifing the "round" value for this
	 * attribute will turn the handle shape to a circle. <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getHandleShape() {
		String value = (String) getStateHelper().eval(PropertyKeys.handleShape);
		return value;
	}

	/**
	 * The default handle is squared. Specifing the "round" value for this
	 * attribute will turn the handle shape to a circle. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHandleShape(String _handleShape) {
		getStateHelper().put(PropertyKeys.handleShape, _handleShape);
	}

	/**
	 * In some situations (eg. mobile phones) the slider handle may be difficult
	 * to operate because of its size. Specifing the "md" or "lg" value for this
	 * attribute will change the handle size accordingly. <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getHandleSize() {
		String value = (String) getStateHelper().eval(PropertyKeys.handleSize);
		return value;
	}

	/**
	 * In some situations (eg. mobile phones) the slider handle may be difficult
	 * to operate because of its size. Specifing the "md" or "lg" value for this
	 * attribute will change the handle size accordingly. <P>
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
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.inline, false);
		return (boolean) value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getLabel() {
		String value = (String) getStateHelper().eval(PropertyKeys.label);
		return value;
	}

	/**
	 * Label for the widget field. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
		getStateHelper().put(PropertyKeys.label, _label);
	}

	/**
	 * The maximum value of the slider. (default 100) <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getMax() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.max, 100);
		return (int) value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getMin() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.min, 0);
		return (int) value;
	}

	/**
	 * The minimum value of the slider. (default 0) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMin(int _min) {
		getStateHelper().put(PropertyKeys.min, _min);
	}

	/**
	 * Mode of the Slider Widget.<br>
	 * There are three modes available : badge, edit and basic.<br>
	 * In <b>basic mode</b>, only the slider and the label(if present) will be
	 * shown and the slider value will be hidden.<br>
	 * In <b>badge mode</b>, the default, the slider value will be shown in a
	 * badge.<br>
	 * In <b>edit mode</b>, an editable input field showing the slider value
	 * will be shown; in this mode you can change the value by sliding or
	 * editing the value in the field. <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getMode() {
		String value = (String) getStateHelper().eval(PropertyKeys.mode, "badge");
		return value;
	}

	/**
	 * Mode of the Slider Widget.<br>
	 * There are three modes available : badge, edit and basic.<br>
	 * In <b>basic mode</b>, only the slider and the label(if present) will be
	 * shown and the slider value will be hidden.<br>
	 * In <b>badge mode</b>, the default, the slider value will be shown in a
	 * badge.<br>
	 * In <b>edit mode</b>, an editable input field showing the slider value
	 * will be shown; in this mode you can change the value by sliding or
	 * editing the value in the field. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMode(String _mode) {
		getStateHelper().put(PropertyKeys.mode, _mode);
	}

	/**
	 * Determines whether the slider handles move horizontally (min on left, max
	 * on right) or vertically (min on bottom, max on top).<br>
	 * Possible values: "horizontal"(default), "vertical", "vertical-bottom".
	 * <br>
	 * If <b>vertical</b> is specified, the Label is rendered on top, then the
	 * value and the slider on the bottom.<br>
	 * If <b>vertical-bottom</b> is specified, the slider is rendered on top,
	 * then the value and the Label on the bottom. <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOrientation() {
		String value = (String) getStateHelper().eval(PropertyKeys.orientation);
		return value;
	}

	/**
	 * Determines whether the slider handles move horizontally (min on left, max
	 * on right) or vertically (min on bottom, max on top).<br>
	 * Possible values: "horizontal"(default), "vertical", "vertical-bottom".
	 * <br>
	 * If <b>vertical</b> is specified, the Label is rendered on top, then the
	 * value and the slider on the bottom.<br>
	 * If <b>vertical-bottom</b> is specified, the slider is rendered on top,
	 * then the value and the Label on the bottom. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOrientation(String _orientation) {
		getStateHelper().put(PropertyKeys.orientation, _orientation);
	}

	/**
	 * The column span of a Horizontal slider. <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getSpan() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.span, 12);
		return (int) value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getStep() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.step, 0);
		return (int) value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyle() {
		String value = (String) getStateHelper().eval(PropertyKeys.style);
		return value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String) getStateHelper().eval(PropertyKeys.styleClass);
		return value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String) getStateHelper().eval(PropertyKeys.tooltip);
		return value;
	}

	/**
	 * The text of the tooltip. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, _tooltip);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). <P>
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
	 * milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
		getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). <P>
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
	 * milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
		getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). <P>
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
	 * milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
		getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom". <P>
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
	 * "auto left". Default to "bottom". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
		getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
	}

}
