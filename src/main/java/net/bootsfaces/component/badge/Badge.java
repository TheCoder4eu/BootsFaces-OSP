/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.badge;

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
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:badge /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(Badge.COMPONENT_TYPE)
public class Badge extends BadgeCore implements net.bootsfaces.render.IHasTooltip, net.bootsfaces.render.IResponsive {


	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".badge.Badge";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.badge.Badge";

	public Badge() {
		AddResourcesListener.addThemedCSSResource("core.css");
		//!bs-less//AddResourcesListener.addThemedCSSResource("badges.css");
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
