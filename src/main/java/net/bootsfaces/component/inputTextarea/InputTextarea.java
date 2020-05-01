/**
 * Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu)
 *
 * This file is part of BootsFaces.
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
package net.bootsfaces.component.inputTextarea;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.IResponsiveLabel;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author thecoder4.eu
 */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(InputTextarea.COMPONENT_TYPE)
public class InputTextarea extends InputTextareaCore implements IHasTooltip, IAJAXComponent, IAJAXComponent2, IResponsive, IResponsiveLabel {
	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".inputTextarea.InputTextarea";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			Arrays.asList("blur", "change", "click", "dblclick", "focus", "keydown", "keypress", "keyup",
					"mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

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

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "change";
	}

	@Override
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

	public InputTextarea() {
		setRendererType("net.bootsfaces.component.InputTextareaRenderer");
		AddResourcesListener.addThemedCSSResource("core.css");
		Tooltip.addResourceFiles();
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}
}
