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

import java.util.Arrays;

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

	public Float[] getFloatValues() {
		String value = this.getValue(); 
		return BsfUtils.getSliderValues(value);
	}

	public void setFloatValues(Float ... values) {
		String value = null;
		if(values != null) {
			if(values.length > 1) {
				value = Arrays.toString(values);
			} else value = values[0].toString();
		}
		this.setValue(value);
	}
	
	/**
	 * Initial value float mode. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setValue(Float[] _values) {
		setFloatValues(_values);
	}
	
	public void setValue(Float _value) {
		setFloatValues(_value);
	}
	
	public void setValue(Integer _value) {
		setFloatValues(_value.floatValue());
	}
	
	/**
	 * Override the GetValue to manage multiple type of inputs
	 */
	public String getValue() {
		Object val = getStateHelper().eval(PropertyKeys.value, "5");
		if(val instanceof Float) 
			return val.toString();
		else if (val instanceof Float[]) 
			return Arrays.toString((Float[])val);
		else if (val instanceof Integer)
			return val.toString();
		return (String) getStateHelper().eval(PropertyKeys.value, "5");
	}
}
