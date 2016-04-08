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

package net.bootsfaces.component.progressBar;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;


/** This class holds the attributes of &lt;b:progressBar /&gt;. */
@FacesComponent("net.bootsfaces.component.progressBar.ProgressBar")
public class ProgressBar extends UIOutput  implements net.bootsfaces.render.IHasTooltip  {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.progressBar.ProgressBar";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.progressBar.ProgressBar";

	public ProgressBar() {
	Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Manage EL-expression for snake-case attributes
	 */
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

    protected enum PropertyKeys {
		label,
		look,
		value,
		max,
		min,
		striped,
		animated,
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
	 * Optional label, which is shown above the progress bar. Can be used for indicating the current value. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		String value = (String)getStateHelper().eval(PropertyKeys.label);
		return  value;
	}

	/**
	 * Optional label, which is shown above the progress bar. Can be used for indicating the current value. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
	    getStateHelper().put(PropertyKeys.label, _label);
    }


	/**
	 * Look of the ProgressBar, can be info, success, warning, or danger. If not specified, a blue bar will be rendered. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLook() {
		String value = (String)getStateHelper().eval(PropertyKeys.look);
		return  value;
	}

	/**
	 * Look of the ProgressBar, can be info, success, warning, or danger. If not specified, a blue bar will be rendered. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLook(String _look) {
	    getStateHelper().put(PropertyKeys.look, _look);
    }


	/**
	 * The value the ProgressBar should indicate. See the label attribute, if the exact value should be shown in text form. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getValue() {
		String value = (String)getStateHelper().eval(PropertyKeys.value);
		return  value;
	}

	/**
	 * The value the ProgressBar should indicate. See the label attribute, if the exact value should be shown in text form. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setValue(String _value) {
	    getStateHelper().put(PropertyKeys.value, _value);
    }


	/**
	 * The maximum value of the ProgressBar. (default 100) <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getMax() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.max, 100);
		return value;
	}

	/**
	 * The maximum value of the ProgressBar. (default 100) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMax(int _max) {
	    getStateHelper().put(PropertyKeys.max, _max);
    }


	/**
	 * The minimum value of the ProgressBar. (default 0) <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getMin() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.min, 0);
		return value;
	}

	/**
	 * The minimum value of the ProgressBar. (default 0) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMin(int _min) {
	    getStateHelper().put(PropertyKeys.min, _min);
    }


	/**
	 * If true, stripes will be added to the ProgressBar's look. Default value: false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isStriped() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.striped, false);
		return value;
	}

	/**
	 * If true, stripes will be added to the ProgressBar's look. Default value: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStriped(boolean _striped) {
	    getStateHelper().put(PropertyKeys.striped, _striped);
    }


	/**
	 * If true, stripes will be added and animated to move from right to left to indicate running actions. Default value: false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isAnimated() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.animated, false);
		return value;
	}

	/**
	 * If true, stripes will be added and animated to move from right to left to indicate running actions. Default value: false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAnimated(boolean _animated) {
	    getStateHelper().put(PropertyKeys.animated, _animated);
    }


	/**
	 * The text of the tooltip. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltip);
		return  value;
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
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
		return  value;
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
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
		return value;
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
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
		return value;
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
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
		return value;
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
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
		return  value;
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
	    getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
    }

}

