/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.thumbnail;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.event.ListenerFor;
import jakarta.faces.event.ListenersFor;
import jakarta.faces.event.PostAddToViewEvent;

import jakarta.el.ValueExpression;
import jakarta.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:thumbnail /&gt;. */

@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(Thumbnail.COMPONENT_TYPE)
public class Thumbnail extends ThumbnailCore implements net.bootsfaces.render.IHasTooltip, IResponsive {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".thumbnail.Thumbnail";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.thumbnail.Thumbnail";

	public Thumbnail() {

		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		//!bs-less//AddResourcesListener.addThemedCSSResource("thumbnails.css");
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
