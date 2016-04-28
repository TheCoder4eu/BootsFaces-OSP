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

package net.bootsfaces.component.row;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:row /&gt;. */
@FacesComponent("net.bootsfaces.component.row.Row")
public class Row extends UIOutput implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.row.Row";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.row.Row";

	public Row() {
		Tooltip.addResourceFile();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("tooltip.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	protected enum PropertyKeys {
		dir,
		style,
		styleClass,
		tooltip,
		tooltipContainer,
		tooltipDelay,
		tooltipDelayHide,
		tooltipDelayShow,
		tooltipPosition
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
	 * Inline style of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return  (String)getStateHelper().eval(PropertyKeys.style);
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
		return  (String)getStateHelper().eval(PropertyKeys.styleClass);
	}
	
	/**
	 * Style class of this element. <P>
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
	
}

