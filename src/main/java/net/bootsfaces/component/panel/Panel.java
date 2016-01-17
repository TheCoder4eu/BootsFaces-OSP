/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.panel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.render.Tooltip;

/** This class holds the attributes of &lt;b:panel /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/panels.css"),
		@ResourceDependency(library = "bsf", name = "css/bsf.css"),
		@ResourceDependency(library = "bsf", name = "js/collapse.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
@FacesComponent("net.bootsfaces.component.panel.Panel")
public class Panel extends UIComponentBase
		implements net.bootsfaces.render.IHasTooltip, IAJAXComponent, ClientBehaviorHolder {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.panel.Panel";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.panel.Panel";

	private Map<String, Object> attributes;

	public Panel() {

		Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList(
			"click", "dblclick", "mousedown",
			"mousemove", "mouseout", "mouseover", "mouseup", "expand", "expanded", "collapse", "collapsed"));

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).Ø
	 * 
	 * @return
	 */
	public Map<String, String> getJQueryEvents() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("expand", "show.bs.collapse");
		result.put("expanded", "shown.bs.collapse");
		result.put("collapse", "hide.bs.collapse");
		result.put("collapsed", "hidden.bs.collapse");
		return result;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}

	protected enum PropertyKeys {
		ajax, binding, collapsed, collapsible, contentClass, contentStyle, dir, disabled, immediate, look, onclick, oncomplete, oncollapse, ondblclick, oncollapsed, onexpand, onexpanded, onmousedown, onmousemove, onmouseout, onmouseover, onmouseup, process, style, styleClass, title, titleClass, titleStyle, tooltip, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, update, tooltipContainer;

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

	/**
	 * An el expression referring to a server side UIComponent instance in a
	 * backing bean.
	 * <P>
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
	 * backing bean.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
		getStateHelper().put(PropertyKeys.binding, _binding);
	}

	/**
	 * Is the panel collapsed?
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isCollapsed() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.collapsed, false);
		return (boolean) value;
	}

	/**
	 * Is the panel collapsed?
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCollapsed(boolean _collapsed) {
		getStateHelper().put(PropertyKeys.collapsed, _collapsed);
	}

	/**
	 * By default, panels can be folded by clicking the title bar. Seting
	 * collapsible to false will suppress the collapse/expand feature.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isCollapsible() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.collapsible, true);
		return (boolean) value;
	}

	/**
	 * By default, panels can be folded by clicking the title bar. Seting
	 * collapsible to false will suppress the collapse/expand feature.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCollapsible(boolean _collapsible) {
		getStateHelper().put(PropertyKeys.collapsible, _collapsible);
	}

	/**
	 * contentClass is optional: if specified, the content will be displayed
	 * with this specific class
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getContentClass() {
		String value = (String) getStateHelper().eval(PropertyKeys.contentClass);
		return value;
	}

	/**
	 * contentClass is optional: if specified, the content will be displayed
	 * with this specific class
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentClass(String _contentClass) {
		getStateHelper().put(PropertyKeys.contentClass, _contentClass);
	}

	/**
	 * Inline style of the content area.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getContentStyle() {
		String value = (String) getStateHelper().eval(PropertyKeys.contentStyle);
		return value;
	}

	/**
	 * Inline style of the content area.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentStyle(String _contentStyle) {
		getStateHelper().put(PropertyKeys.contentStyle, _contentStyle);
	}

	/**
	 * Boolean value to specify if the button is disabled.
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
	 * Boolean value to specify if the button is disabled.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * Flag indicating that, if this component is activated by the user,
	 * notifications should be delivered to interested listeners and actions
	 * immediately (that is, during Apply Request Values phase) rather than
	 * waiting until Invoke Application phase. Default is false.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isImmediate() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.immediate, false);
		return (boolean) value;
	}

	/**
	 * Flag indicating that, if this component is activated by the user,
	 * notifications should be delivered to interested listeners and actions
	 * immediately (that is, during Apply Request Values phase) rather than
	 * waiting until Invoke Application phase. Default is false.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
		getStateHelper().put(PropertyKeys.immediate, _immediate);
	}

	/**
	 * Look of the Panel, can be primary, success, info, warning, danger.
	 * Default is warning.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getLook() {
		String value = (String) getStateHelper().eval(PropertyKeys.look);
		return value;
	}

	/**
	 * Look of the Panel, can be primary, success, info, warning, danger.
	 * Default is warning.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLook(String _look) {
		getStateHelper().put(PropertyKeys.look, _look);
	}

	/**
	 * The onclick attribute.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnclick() {
		String value = (String) getStateHelper().eval(PropertyKeys.onclick);
		return value;
	}

	/**
	 * The onclick attribute.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
		getStateHelper().put(PropertyKeys.onclick, _onclick);
	}

	/**
	 * Javascript to be executed when ajax completes with success.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
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
	 * JavaScript code or AJAX method to be executed when the panel starts to be
	 * collapsed.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOncollapse() {
		String value = (String) getStateHelper().eval(PropertyKeys.oncollapse);
		return value;
	}

	/**
	 * JavaScript code or AJAX method to be executed when the panel starts to be
	 * collapsed.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncollapse(String _oncollapse) {
		getStateHelper().put(PropertyKeys.oncollapse, _oncollapse);
	}

	/**
	 * Client side callback to execute when input element is double clicked.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOndblclick() {
		String value = (String) getStateHelper().eval(PropertyKeys.ondblclick);
		return value;
	}

	/**
	 * Client side callback to execute when input element is double clicked.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndblclick(String _ondblclick) {
		getStateHelper().put(PropertyKeys.ondblclick, _ondblclick);
	}

	/**
	 * JavaScript code or AJAX method to be executed when the panel has finished
	 * collapsing.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOncollapsed() {
		String value = (String) getStateHelper().eval(PropertyKeys.oncollapsed);
		return value;
	}

	/**
	 * JavaScript code or AJAX method to be executed when the panel has finished
	 * collapsing.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncollapsed(String _oncollapsed) {
		getStateHelper().put(PropertyKeys.oncollapsed, _oncollapsed);
	}

	/**
	 * JavaScript code or AJAX method to be executed when the panel starts to be
	 * expanded.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnexpand() {
		String value = (String) getStateHelper().eval(PropertyKeys.onexpand);
		return value;
	}

	/**
	 * JavaScript code or AJAX method to be executed when the panel starts to be
	 * expanded.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnexpand(String _onexpand) {
		getStateHelper().put(PropertyKeys.onexpand, _onexpand);
	}

	/**
	 * JavaScript code or AJAX method to be executed when the panel has finished
	 * expandeding.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnexpanded() {
		String value = (String) getStateHelper().eval(PropertyKeys.onexpanded);
		return value;
	}

	/**
	 * JavaScript code or AJAX method to be executed when the panel has finished
	 * expandeding.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnexpanded(String _onexpanded) {
		getStateHelper().put(PropertyKeys.onexpanded, _onexpanded);
	}

	/**
	 * Client side callback to execute when a pointer input element is pressed
	 * down over input element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmousedown() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmousedown);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is pressed
	 * down over input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousedown(String _onmousedown) {
		getStateHelper().put(PropertyKeys.onmousedown, _onmousedown);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * within input element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmousemove() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmousemove);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * within input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousemove(String _onmousemove) {
		getStateHelper().put(PropertyKeys.onmousemove, _onmousemove);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * away from input element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmouseout() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmouseout);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * away from input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseout(String _onmouseout) {
		getStateHelper().put(PropertyKeys.onmouseout, _onmouseout);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * onto input element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmouseover() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmouseover);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * onto input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseover(String _onmouseover) {
		getStateHelper().put(PropertyKeys.onmouseover, _onmouseover);
	}

	/**
	 * Client side callback to execute when a pointer input element is released
	 * over input element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmouseup() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmouseup);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is released
	 * over input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseup(String _onmouseup) {
		getStateHelper().put(PropertyKeys.onmouseup, _onmouseup);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getProcess() {
		String value = (String)getStateHelper().eval(PropertyKeys.process);
		return  value;
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
	 * Inline style of the entire panel area.
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
	 * Inline style of the entire panel area.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of the input element.
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
	 * Style class of the input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Title is optional: if specified, the heading facet will not be rendered
	 * and the panel will render with a heading with this Title.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTitle() {
		String value = (String) getStateHelper().eval(PropertyKeys.title);
		return value;
	}

	/**
	 * Title is optional: if specified, the heading facet will not be rendered
	 * and the panel will render with a heading with this Title.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitle(String _title) {
		getStateHelper().put(PropertyKeys.title, _title);
	}

	/**
	 * TitleClass is optional: if specified, the title will be displayed with
	 * this specific class
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTitleClass() {
		String value = (String) getStateHelper().eval(PropertyKeys.titleClass);
		return value;
	}

	/**
	 * TitleClass is optional: if specified, the title will be displayed with
	 * this specific class
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitleClass(String _titleClass) {
		getStateHelper().put(PropertyKeys.titleClass, _titleClass);
	}

	/**
	 * Inline style of the title area.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTitleStyle() {
		String value = (String) getStateHelper().eval(PropertyKeys.titleStyle);
		return value;
	}

	/**
	 * Inline style of the title area.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitleStyle(String _titleStyle) {
		getStateHelper().put(PropertyKeys.titleStyle, _titleStyle);
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

	/**
	 * Which region of the screen is to be updated? Default value: @form
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
	 * Which region of the screen is to be updated? Default value: @form
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	/**
	 * Direction indication for text that does not inherit directionality.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getDir() {
		String value = (String) getStateHelper().eval(PropertyKeys.dir);
		return value;
	}

	/**
	 * Direction indication for text that does not inherit directionality.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
		getStateHelper().put(PropertyKeys.dir, _dir);
	}

}
