/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.touchSpin;

import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:spinner /&gt;. */
@FacesComponent("net.bootsfaces.component.touchSpin.TouchSpin")
public class TouchSpin extends TouchSpinCore
implements IAJAXComponent, ClientBehaviorHolder, IHasTooltip, IResponsive {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.touchSpin.TouchSpin";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.touchSpin.TouchSpin";

	public TouchSpin() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		//AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addExtCSSResource("bootstrap-touchspin.min.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/bootstrap-touchspin.min.js");
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
	public Map<String, String> getJQueryEvents() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("startspin", "touchspin.on.startspin");
		result.put("startupspin", "touchspin.on.startupspin");
		result.put("startdownspin", "touchspin.on.startdownspin");
		result.put("stopspin", "touchspin.on.stopspin");
		result.put("stopupspin", "touchspin.on.stopupspin");
		result.put("stopdownspin", "touchspin.on.stopdownspin");
		result.put("change", "change");
		return result;
	}
	
	@Override
	public String getValue() {
		Object val = getStateHelper().eval(PropertyKeys.value);
		if(val instanceof Number) return val.toString();
		else return (String) val;
	}

	@Override
	public double getMax() {
		Object max = getStateHelper().eval(PropertyKeys.max, 100);
		if(max instanceof Integer) return ((Integer) max).doubleValue();
		else if(max instanceof String) return Double.parseDouble((String)max);
		return (Double) max;
	}

	@Override
	public double getMin() {
		Object min = getStateHelper().eval(PropertyKeys.min, 0);
		if(min instanceof Integer) return ((Integer) min).doubleValue();
		else if(min instanceof String) return Double.parseDouble((String)min);
		return (Double) min;
	}

	@Override
	public double getStep() {
		Object step = getStateHelper().eval(PropertyKeys.step, 1);
		if(step instanceof Integer) return ((Integer) step).doubleValue();
		else if(step instanceof String) return Double.parseDouble((String)step);
		return (Double) step;
	}
}
