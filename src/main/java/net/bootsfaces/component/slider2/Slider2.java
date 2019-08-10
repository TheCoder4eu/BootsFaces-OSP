/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.slider2;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:slider2 /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(Slider2.COMPONENT_TYPE)
public class Slider2 extends Slider2Core {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".slider2.Slider2";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.slider2.Slider2";

	public Slider2() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addExtCSSResource("bootstrap-slider.min.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/bootstrap-slider.min.js");
		setRendererType(DEFAULT_RENDERER);
	}
	
	/**
	 * Convert object to number. To be backwards compatible with bound integers to properties where we now also want
	 * to accept floats now.
	 *
	 * @param object Object to convert to number. Should be either a number or a string.
	 *
	 * @return Object converted to number.
	 *
	 * @throws IllegalArgumentException When an incorrect type is provided.
	 */
	private static Number toNumber(Object object) {
		if (object instanceof Number) {
			return (Number) object;
		}
		if (object instanceof String) {
			return Float.valueOf((String) object);
		}
		throw new IllegalArgumentException("Use number or string");
	}


	/**
	 * <p>
	 * Return <code>true</code> if the new value is different from the previous
	 * value. First compare the two values by passing <em>value</em> to the
	 * <code>equals</code> method on argument <em>previous</em>. If that method
	 * returns <code>true</code>, return <code>true</code>. If that method returns
	 * <code>false</code>, and both arguments implement
	 * <code>java.lang.Comparable</code>, compare the two values by passing
	 * <em>value</em> to the <code>compareTo</code> method on argument
	 * <em>previous</em>. Return <code>true</code> if this method returns
	 * <code>0</code>, <code>false</code> otherwise.
	 * </p>
	 *
	 * @param previous
	 *            old value of this component (if any)
	 * @param value
	 *            new value of this component (if any)
	 */
	protected boolean compareValues(Object previous, Object value) {
		if (previous instanceof String && value instanceof Double) {
			previous = Double.valueOf((String) previous);
		}
		return super.compareValues(previous, value);
	}

		public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
 	 		super.processEvent(event);
 	 	}
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

	/**
	 * The maximum value of the slider. (default 100) <P>
	 * @return Returns the value of the attribute, or 100, if it hasn't been set by the JSF file.
	 */
	public Object getMax() {
		return getStateHelper().eval(PropertyKeys.max, 100);
	}

	/**
	 * The maximum value of the slider. (default 0) <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public Object getMin() {
		return getStateHelper().eval(PropertyKeys.min, 0);
	}

	/**
	 * Increment step <P>
	 * @return Returns the value of the attribute, or 1, if it hasn't been set by the JSF file.
	 */
	public Object getStep() {
		return getStateHelper().eval(PropertyKeys.step, 1);
	}

	/**
	 * The maximum value of the slider. (default 100) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMax(Object _max) {
		getStateHelper().put(PropertyKeys.max, toNumber(_max));
	}

	/**
	 * The maximum value of the slider. (default 0) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMin(Object _min) {
		getStateHelper().put(PropertyKeys.min, toNumber(_min));
	}

	/**
	 * Increment step <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStep(Object _step) {
		getStateHelper().put(PropertyKeys.step, toNumber(_step));
	}
}
