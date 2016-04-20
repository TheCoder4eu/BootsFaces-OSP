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

package net.bootsfaces.component.tabView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:tabView /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/tab.js", target = "head"), })
@FacesComponent("net.bootsfaces.component.tabView.TabView")
public class TabView extends UIOutput
		implements net.bootsfaces.render.IHasTooltip, ClientBehaviorHolder, IAJAXComponent {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.tabView.TabView";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.tabView.TabView";

	public TabView() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("bootstrap-treeview.min.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("core.css");
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
		result.put("click", "click");
		return result;
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

	protected enum PropertyKeys {
		activeIndex,
		ajax,
		contentClass,
		contentStyle,
		dir,
		tabPosition,
		pills,
		disabled,
		immediate,
		onclick,
		oncomplete,
		process,
		role,
		styleClass,
		tooltip,
		tooltipContainer,
		tooltipDelay,
		tooltipDelayHide,
		tooltipDelayShow,
		tooltipPosition,
		update
;
        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {}

        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }
	

	/**
	 * Optional attribute to define which tab is active initially. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getActiveIndex() {
		return (int) (Integer)getStateHelper().eval(PropertyKeys.activeIndex, 0);
	}
	
	/**
	 * Optional attribute to define which tab is active initially. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setActiveIndex(int _activeIndex) {
	    getStateHelper().put(PropertyKeys.activeIndex, _activeIndex);
    }
	

	/**
	 * Activates AJAX. The default value is false (no AJAX). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isAjax() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.ajax, false);
	}
	
	/**
	 * Activates AJAX. The default value is false (no AJAX). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAjax(boolean _ajax) {
	    getStateHelper().put(PropertyKeys.ajax, _ajax);
    }
	

	/**
	 * Style class of the div surrounding the panes. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentClass() {
		return  (String)getStateHelper().eval(PropertyKeys.contentClass);
	}
	
	/**
	 * Style class of the div surrounding the panes. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentClass(String _contentClass) {
	    getStateHelper().put(PropertyKeys.contentClass, _contentClass);
    }
	

	/**
	 * Inline CSS style of the div surrounding the panes. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentStyle() {
		return  (String)getStateHelper().eval(PropertyKeys.contentStyle);
	}
	
	/**
	 * Inline CSS style of the div surrounding the panes. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentStyle(String _contentStyle) {
	    getStateHelper().put(PropertyKeys.contentStyle, _contentStyle);
    }
	

	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphabet, based on the page content). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDir() {
		return  (String)getStateHelper().eval(PropertyKeys.dir);
	}
	
	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphabet, based on the page content). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
	    getStateHelper().put(PropertyKeys.dir, _dir);
    }
	

	/**
	 * Position of tabs. Legal values are: left, right, top, bottom. Default is top <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTabPosition() {
		return  (String)getStateHelper().eval(PropertyKeys.tabPosition);
	}
	
	/**
	 * Position of tabs. Legal values are: left, right, top, bottom. Default is top <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTabPosition(String _tabPosition) {
	    getStateHelper().put(PropertyKeys.tabPosition, _tabPosition);
    }
	

	/**
	 * Change the rendering of tab to pills mode. Default false <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isPills() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.pills, false);
	}
	
	/**
	 * Change the rendering of tab to pills mode. Default false <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPills(boolean _pills) {
	    getStateHelper().put(PropertyKeys.pills, _pills);
    }
	

	/**
	 * Disables the input element, default is false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.disabled, false);
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
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isImmediate() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.immediate, false);
	}
	
	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
	    getStateHelper().put(PropertyKeys.immediate, _immediate);
    }
	

	/**
	 * OnClick DHTML event . <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnclick() {
		return  (String)getStateHelper().eval(PropertyKeys.onclick);
	}
	
	/**
	 * OnClick DHTML event . <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
	    getStateHelper().put(PropertyKeys.onclick, _onclick);
    }
	

	/**
	 * JavaScript to be executed when ajax completes with success. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOncomplete() {
		return  (String)getStateHelper().eval(PropertyKeys.oncomplete);
	}
	
	/**
	 * JavaScript to be executed when ajax completes with success. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncomplete(String _oncomplete) {
	    getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
    }
	

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getProcess() {
		return  (String)getStateHelper().eval(PropertyKeys.process);
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
		return  (String)getStateHelper().eval(PropertyKeys.role);
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
		return  (String)getStateHelper().eval(PropertyKeys.styleClass);
	}
	
	/**
	 * Style class of the div surrounding this element. <P>
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
		return  (String)getStateHelper().eval(PropertyKeys.tooltip);
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
		return  (String)getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
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
		return (int) (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
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
		return (int) (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
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
		return (int) (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
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
		return  (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
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
		return  (String)getStateHelper().eval(PropertyKeys.update);
	}
	
	/**
	 * Component(s) to be updated with ajax. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
	    getStateHelper().put(PropertyKeys.update, _update);
    }
	
}

