/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu), Stephan Rauh (http://www.beyondjava.net) and Dario D'Urzo.
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
package net.bootsfaces.component.accordion;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.event.ListenerFor;
import jakarta.faces.event.ListenersFor;
import jakarta.faces.event.PostAddToViewEvent;

import jakarta.el.ValueExpression;
import jakarta.faces.application.ResourceDependencies;
import jakarta.faces.application.ResourceDependency;
import jakarta.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:accordion /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/transition.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "js/collapse.js", target = "body"), })
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(Accordion.COMPONENT_TYPE)
public class Accordion extends AccordionCore implements IHasTooltip, net.bootsfaces.render.IResponsive, IContentDisabled {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".accordion.Accordion";
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.accordion.Accordion";

	public Accordion() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		//AddResourcesListener.addThemedCSSResource("bsf.css");
		//!bs-less//AddResourcesListener.addThemedCSSResource("panels.css");
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

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}
}
