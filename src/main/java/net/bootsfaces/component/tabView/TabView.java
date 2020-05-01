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

package net.bootsfaces.component.tabView;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:tabView /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(TabView.COMPONENT_TYPE)
public class TabView extends UIOutput implements net.bootsfaces.render.IHasTooltip, ClientBehaviorHolder,
		IAJAXComponent, IAJAXComponent2, IContentDisabled {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".tabView.TabView";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.tabView.TabView";

	public TabView() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addExtCSSResource("bootstrap-treeview.min.css");
		//AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/tab.js");
		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			Arrays.asList("blur", "change", "valueChange", "click", "dblclick", "focus", "keydown", "keypress", "keyup",
					"mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).Ø
	 *
	 * @return
	 */
	public Map<String, String> getJQueryEvents() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("show", "shown.bs.tab");
		result.put("shown", "shown.bs.tab");
		result.put("hide", "hide.bs.tab");
		result.put("hidden", "hidden.bs.tab");
		return result;
	}

	/**
	 * Returns the parameter list of jQuery and other non-standard JS callbacks.
	 * If there's no parameter list for a certain event, the default is simply "event".
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterLists() {
		return null;
	}

	/**
	 * Returns the subset of the parameter list of jQuery and other non-standard JS callbacks which is sent to the server via AJAX.
	 * If there's no parameter list for a certain event, the default is simply null.
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterListsForAjax() {
		return null;
	}

	@Override
	public boolean getRendersChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "valueChange";
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public void processDecodes(FacesContext context) {
		super.processDecodes(context);
	}

	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
			super.processEvent(event);
		}
	}

	protected enum PropertyKeys {
		activeIndex, ajax, autoUpdate, contentClass, contentDisabled, contentStyle, delay, dir, disabled, immediate, onclick, oncomplete, onerror, onhidden, onhide, onshow, onshown, onsuccess, pills, process, role, styleClass, tabPosition, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, update;
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
	 * Optional attribute to define which tab is active initially. <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getActiveIndex() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.activeIndex, 0);
	}

	/**
	 * Optional attribute to define which tab is active initially. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setActiveIndex(int _activeIndex) {
		getStateHelper().put(PropertyKeys.activeIndex, _activeIndex);
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
	 * Style class of the div surrounding the panes. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentClass() {
		return (String) getStateHelper().eval(PropertyKeys.contentClass);
	}

	/**
	 * Style class of the div surrounding the panes. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentClass(String _contentClass) {
		getStateHelper().put(PropertyKeys.contentClass, _contentClass);
	}

	/**
	 * Enables or disables every child element of this container. By default, child elements are enabled. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isContentDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.contentDisabled, false);
	}

	/**
	 * Enables or disables every child element of this container. By default, child elements are enabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentDisabled(boolean _contentDisabled) {
		getStateHelper().put(PropertyKeys.contentDisabled, _contentDisabled);
	}

	/**
	 * Inline CSS style of the div surrounding the panes. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentStyle() {
		return (String) getStateHelper().eval(PropertyKeys.contentStyle);
	}

	/**
	 * Inline CSS style of the div surrounding the panes. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentStyle(String _contentStyle) {
		getStateHelper().put(PropertyKeys.contentStyle, _contentStyle);
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
	 * JavaScript and/or AJAX code to be executed just after pushing a tab to the back <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnhidden() {
		return (String) getStateHelper().eval(PropertyKeys.onhidden);
	}

	/**
	 * JavaScript and/or AJAX code to be executed just after pushing a tab to the back <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnhidden(String _onhidden) {
		getStateHelper().put(PropertyKeys.onhidden, _onhidden);
	}

	/**
	 * JavaScript and/or AJAX code to be executed just before pushing a tab to the back <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnhide() {
		return (String) getStateHelper().eval(PropertyKeys.onhide);
	}

	/**
	 * JavaScript and/or AJAX code to be executed just before pushing a tab to the back <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnhide(String _onhide) {
		getStateHelper().put(PropertyKeys.onhide, _onhide);
	}

	/**
	 * JavaScript and/or AJAX code to be executed just before bringing a tab to the front <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnshow() {
		return (String) getStateHelper().eval(PropertyKeys.onshow);
	}

	/**
	 * JavaScript and/or AJAX code to be executed just before bringing a tab to the front <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnshow(String _onshow) {
		getStateHelper().put(PropertyKeys.onshow, _onshow);
	}

	/**
	 * JavaScript and/or AJAX code to be executed just after bringing a tab to the front <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnshown() {
		return (String) getStateHelper().eval(PropertyKeys.onshown);
	}

	/**
	 * JavaScript and/or AJAX code to be executed just after bringing a tab to the front <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnshown(String _onshown) {
		getStateHelper().put(PropertyKeys.onshown, _onshown);
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
	 * Change the rendering of tab to pills mode. Default false <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isPills() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.pills, false);
	}

	/**
	 * Change the rendering of tab to pills mode. Default false <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPills(boolean _pills) {
		getStateHelper().put(PropertyKeys.pills, _pills);
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
	 * The role can be used to provide information to screenreaders. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRole() {
		return (String) getStateHelper().eval(PropertyKeys.role);
	}

	/**
	 * The role can be used to provide information to screenreaders. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRole(String _role) {
		getStateHelper().put(PropertyKeys.role, _role);
	}

	/**
	 * Style class of the div surrounding this element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of the div surrounding this element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Position of tabs. Legal values are: left, right, top, bottom. Default is top <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTabPosition() {
		return (String) getStateHelper().eval(PropertyKeys.tabPosition);
	}

	/**
	 * Position of tabs. Legal values are: left, right, top, bottom. Default is top <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTabPosition(String _tabPosition) {
		getStateHelper().put(PropertyKeys.tabPosition, _tabPosition);
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

}
