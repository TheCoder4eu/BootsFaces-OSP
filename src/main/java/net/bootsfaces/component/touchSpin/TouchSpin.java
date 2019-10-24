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

package net.bootsfaces.component.touchSpin;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:spinner /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(TouchSpin.COMPONENT_TYPE)
public class TouchSpin extends TouchSpinCore
implements IAJAXComponent, IAJAXComponent2, ClientBehaviorHolder, IHasTooltip, IResponsive {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".touchSpin.TouchSpin";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.touchSpin.TouchSpin";

	public TouchSpin() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		//AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addExtCSSResource("bootstrap-touchspin.min.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/bootstrap-touchspin.min.js");
		setRendererType(DEFAULT_RENDERER);
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
