/**
 * Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
 *
 * This file is part of BootsFaces.
 *
 * BootsFaces is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * BootsFaces is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */
package net.bootsfaces.component.inputTextarea;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.C;
import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
		@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
@FacesComponent("net.bootsfaces.component.inputTextarea.InputTextarea")
public class InputTextarea extends HtmlInputText implements IHasTooltip, IAJAXComponent {
	private String renderLabel = null;

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.INPUTTEXT_COMPONENT_TYPE;
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			Arrays.asList("blur", "change", "valueChange", "click", "dblclick", "focus", "keydown", "keypress", "keyup",
					"mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));
	private Map<String, Object> attributes;

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).
	 * 
	 * @return
	 */
	public Map<String, String> getJQueryEvents() {
		return null;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "valueChange";
	}

	protected enum PropertyKeys {

		ajax, placeholder, fieldSize, inline, type, oncomplete, renderLabel, span, tooltip, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, process, update, tooltipContainer, rows;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		@Override
		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	public InputTextarea() {
		setRendererType("net.bootsfaces.component.InputTextareaRenderer");
		Tooltip.addResourceFile();
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value
	 * that can be used to fix rendering error in special cases. Also see
	 * data-container in the documentation of Bootstrap. The default value is
	 * body.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltipContainer() {
		String value = (String) getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
		return value;
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value
	 * that can be used to fix rendering error in special cases. Also see
	 * data-container in the documentation of Bootstrap. The default value is
	 * body.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
		getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which
	 * values are to be sent to the server.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getProcess() {
		String value = (String) getStateHelper().eval(PropertyKeys.process);
		return value;
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which
	 * values are to be sent to the server.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setProcess(String _process) {
		getStateHelper().put(PropertyKeys.process, _process);
	}

	/**
	 * Activates AJAX. The default value is false (no AJAX).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isAjax() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.ajax, false);
		return (boolean) value;
	}

	/**
	 * Activates AJAX. The default value is false (no AJAX).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAjax(boolean _ajax) {
		getStateHelper().put(PropertyKeys.ajax, _ajax);
	}

	public java.lang.String getPlaceholder() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.placeholder, null);
	}

	public void setPlaceholder(java.lang.String _placeholder) {
		getStateHelper().put(PropertyKeys.placeholder, _placeholder);
	}

	/**
	 * Inline forms are more compact and put the label to the left hand side of
	 * the input field instead of putting it above the input field. Inline
	 * applies only to screens that are at least 768 pixels wide.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInline(boolean _inline) {
		getStateHelper().put(PropertyKeys.inline, _inline);
	}

	public java.lang.String getFieldSize() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.fieldSize, null);
	}

	public void setFieldSize(java.lang.String _fieldSize) {
		getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
	}

	/**
	 * Inline forms are more compact and put the label to the left hand side of
	 * the input field instead of putting it above the input field. Inline
	 * applies only to screens that are at least 768 pixels wide.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isInline() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.inline, false);
		return (boolean) value;
	}

	/**
	 * Allows you to suppress automatic rendering of labels. Used by
	 * AngularFaces, too.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isRenderLabel() {
		if (null != renderLabel) {
			boolean defaultValue = Boolean.valueOf(renderLabel);
			Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.renderLabel, defaultValue);
			return (boolean) value;
		}
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.renderLabel, false);
		return (boolean) value;
	}

	/**
	 * Allows you to suppress automatic rendering of labels. Used by
	 * AngularFaces, too.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRenderLabel(boolean _renderLabel) {
		getStateHelper().put(PropertyKeys.renderLabel, _renderLabel);
	}

	/**
	 * The size of the input specified as number of grid columns.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getSpan() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.span, 0);
		return (int) value;
	}

	/**
	 * The size of the input specified as number of grid columns.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(int _span) {
		getStateHelper().put(PropertyKeys.span, _span);
	}

	/**
	 * The text of the tooltip.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String) getStateHelper().eval(PropertyKeys.tooltip);
		return value;
	}

	/**
	 * The text of the tooltip.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, _tooltip);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
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
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
		getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
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
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
		getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
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
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
		getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom".
	 * <P>
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
	 * "auto left". Default to "bottom".
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
		getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
	}

	public java.lang.String getType() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.type, "text");
	}

	public void setType(java.lang.String _type) {
		getStateHelper().put(PropertyKeys.type, _type);
	}

	public String getOncomplete() {
		String value = (String) getStateHelper().eval(PropertyKeys.oncomplete);
		return value;
	}

	/**
	 * Javascript to be executed when ajax completes with success.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncomplete(String _oncomplete) {
		getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
	}

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getUpdate() {
		String value = (String) getStateHelper().eval(PropertyKeys.update);
		return value;
	}

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	/**
	 * Number of rows
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getRows() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.rows, 5);
		return (int) value;
	}

	/**
	 * Number of rows
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRows(int _rows) {
		getStateHelper().put(PropertyKeys.rows, _rows);
	}

}
