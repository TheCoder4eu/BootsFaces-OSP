/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.slider2;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:slider2 /&gt;. */
@FacesComponent("net.bootsfaces.component.slider2.Slider2")
public class Slider2 extends Slider2Core {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.slider2.Slider2";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.slider2.Slider2";

	public Slider2() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("bootstrap-slider.min.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/bootstrap-slider.min.js");
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
	
	@Override
	public String getValue() {
		Object val = getStateHelper().eval(PropertyKeys.value, "5");
		if(val instanceof Double) 
			return val.toString();
		return (String) val;
	}

	@Override
	public Double getMax() {
		Object max = getStateHelper().eval(PropertyKeys.max, 100);
		if(max instanceof Integer) return ((Integer) max).doubleValue();
		else if(max instanceof String) return Double.parseDouble((String)max);
		return (Double) max;
	}

	@Override
	public Double getMin() {
		Object min = getStateHelper().eval(PropertyKeys.min, 0);
		if(min instanceof Integer) return ((Integer) min).doubleValue();
		else if(min instanceof String) return Double.parseDouble((String)min);
		return (Double) min;
	}

	@Override
	public Double getStep() {
		Object step = getStateHelper().eval(PropertyKeys.step, 1);
		if(step instanceof Integer) return ((Integer) step).doubleValue();
		else if(step instanceof String) return Double.parseDouble((String)step);
		return (Double) step;
	}
}
