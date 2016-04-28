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

package net.bootsfaces.component.icon;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:icon /&gt;. */
@FacesComponent("net.bootsfaces.component.icon.Icon")
public class Icon extends UICommand implements net.bootsfaces.render.IHasTooltip, IAJAXComponent, ClientBehaviorHolder {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.icon.Icon";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.icon.Icon";

	public Icon() {
		Tooltip.addResourceFile();
		// AddResourcesListener.addBasicJSResource("javax.faces", "jsf.js");
		// AddResourcesListener.addBasicJSResource("bsf", "js/bsf.js");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("tooltip.css");
		AddResourcesListener.addThemedCSSResource("icons.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			Arrays.asList("click", "dblclick", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup"));

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
		return "click";
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		addon,
		ajax,
		binding,
		disabled,
		flip,
		name,
		onclick,
		oncomplete,
		ondblclick,
		onmousedown,
		onmousemove,
		onmouseout,
		onmouseover,
		onmouseup,
		process,
		readonly,
		rotate,
		size,
		spin,
		style,
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
	 * Boolean value: if true the element is used as Addon. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isAddon() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.addon, false);
	}
	
	/**
	 * Boolean value: if true the element is used as Addon. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAddon(boolean _addon) {
	    getStateHelper().put(PropertyKeys.addon, _addon);
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
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		return  (javax.faces.component.UIComponent)getStateHelper().eval(PropertyKeys.binding);
	}
	
	/**
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
	    getStateHelper().put(PropertyKeys.binding, _binding);
    }
	

	/**
	 * Boolean value to specify if the button is disabled. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.disabled, false);
	}
	
	/**
	 * Boolean value to specify if the button is disabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
	    getStateHelper().put(PropertyKeys.disabled, _disabled);
    }
	

	/**
	 * Flip the icon: can be H (horizontal) or V (vertical). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFlip() {
		return  (String)getStateHelper().eval(PropertyKeys.flip);
	}
	
	/**
	 * Flip the icon: can be H (horizontal) or V (vertical). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFlip(String _flip) {
	    getStateHelper().put(PropertyKeys.flip, _flip);
    }
	

	/**
	 * Icon name, mandatory. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getName() {
		return  (String)getStateHelper().eval(PropertyKeys.name);
	}
	
	/**
	 * Icon name, mandatory. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setName(String _name) {
	    getStateHelper().put(PropertyKeys.name, _name);
    }
	

	/**
	 * The onclick attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnclick() {
		return  (String)getStateHelper().eval(PropertyKeys.onclick);
	}
	
	/**
	 * The onclick attribute. <P>
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
	 * Client side callback to execute when input element is double clicked. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndblclick() {
		return  (String)getStateHelper().eval(PropertyKeys.ondblclick);
	}
	
	/**
	 * Client side callback to execute when input element is double clicked. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndblclick(String _ondblclick) {
	    getStateHelper().put(PropertyKeys.ondblclick, _ondblclick);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousedown() {
		return  (String)getStateHelper().eval(PropertyKeys.onmousedown);
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
		return  (String)getStateHelper().eval(PropertyKeys.onmousemove);
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
		return  (String)getStateHelper().eval(PropertyKeys.onmouseout);
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
		return  (String)getStateHelper().eval(PropertyKeys.onmouseover);
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
		return  (String)getStateHelper().eval(PropertyKeys.onmouseup);
	}
	
	/**
	 * Client side callback to execute when a pointer input element is released over input element. <P>
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
	 * Flag indicating that this input element will prevent changes by the user. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isReadonly() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.readonly, false);
	}
	
	/**
	 * Flag indicating that this input element will prevent changes by the user. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setReadonly(boolean _readonly) {
	    getStateHelper().put(PropertyKeys.readonly, _readonly);
    }
	

	/**
	 * Rotate 90 degrees the icon: Can be L,R. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRotate() {
		return  (String)getStateHelper().eval(PropertyKeys.rotate);
	}
	
	/**
	 * Rotate 90 degrees the icon: Can be L,R. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRotate(String _rotate) {
	    getStateHelper().put(PropertyKeys.rotate, _rotate);
    }
	

	/**
	 * Icon Size: legal values are lg, 2x, 3x, 4x, 5x. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSize() {
		return  (String)getStateHelper().eval(PropertyKeys.size);
	}
	
	/**
	 * Icon Size: legal values are lg, 2x, 3x, 4x, 5x. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSize(String _size) {
	    getStateHelper().put(PropertyKeys.size, _size);
    }
	

	/**
	 * Boolean value: if true the icon will spin. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isSpin() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.spin, false);
	}
	
	/**
	 * Boolean value: if true the icon will spin. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpin(boolean _spin) {
	    getStateHelper().put(PropertyKeys.spin, _spin);
    }
	

	/**
	 * Inline style <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return  (String)getStateHelper().eval(PropertyKeys.style);
	}
	
	/**
	 * Inline style <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
	    getStateHelper().put(PropertyKeys.style, _style);
    }
	

	/**
	 * CSS style class <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return  (String)getStateHelper().eval(PropertyKeys.styleClass);
	}
	
	/**
	 * CSS style class <P>
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

