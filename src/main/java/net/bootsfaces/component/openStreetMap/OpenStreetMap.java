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

package net.bootsfaces.component.openStreetMap;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIViewRoot;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.event.FacesEvent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:openStreetMap /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(OpenStreetMap.COMPONENT_TYPE)
public class OpenStreetMap extends OpenStreetMapCore implements ClientBehaviorHolder {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".openStreetMap.OpenStreetMap";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.openStreetMap.OpenStreetMap";
        
        public static final String LEAFLET_VERSION = "1.3.4";

//	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click",
//			"dblclick", "dragstart", "dragover", "drop", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup"));

	public OpenStreetMap() {
		setRendererType(DEFAULT_RENDERER);
		Tooltip.addResourceFiles();
//		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener
				.addResourceIfNecessary("https://cdnjs.cloudflare.com/ajax/libs/leaflet/"+LEAFLET_VERSION+"/leaflet-src.js");
		AddResourcesListener
				.addResourceIfNecessary("https://cdnjs.cloudflare.com/ajax/libs/leaflet/"+LEAFLET_VERSION+"/leaflet-src.js.map");
		AddResourcesListener.addResourceIfNecessary("https://cdnjs.cloudflare.com/ajax/libs/leaflet/"+LEAFLET_VERSION+"/leaflet.css");
		AddResourcesListener.addResourceIfNecessary("https://cdnjs.cloudflare.com/ajax/libs/leaflet/"+LEAFLET_VERSION+"/leaflet.js");
		AddResourcesListener
				.addResourceIfNecessary("https://cdnjs.cloudflare.com/ajax/libs/leaflet/"+LEAFLET_VERSION+"/leaflet.js.map");
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

//	public Collection<String> getEventNames() {
//		return EVENT_NAMES;
//	}

//	public String getDefaultEventName() {
//		return "click";
//	}

	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
//		if (isAutoUpdate()) {
//			if (FacesContext.getCurrentInstance().isPostback()) {
//				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
//			}
//			super.processEvent(event);
//		}
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * <p>
	 * Queue an event for broadcast at the end of the current request processing
	 * lifecycle phase. The default implementation in {@link UIComponentBase} must
	 * delegate this call to the <code>queueEvent()</code> method of the parent
	 * {@link UIComponent}.
	 * </p>
	 *
	 * @param event {@link FacesEvent} to be queued
	 *
	 * @throws IllegalStateException if this component is not a descendant of a
	 *                               {@link UIViewRoot}
	 * @throws NullPointerException  if <code>event</code> is <code>null</code>
	 */
	public void queueEvent(FacesEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		String indexes = (String) context.getExternalContext().getRequestParameterMap().get("indexes");
		context.getELContext().getELResolver().setValue(context.getELContext(), null, "indexes", indexes);
		String typeOfSelection = (String) context.getExternalContext().getRequestParameterMap().get("typeOfSelection");
		context.getELContext().getELResolver().setValue(context.getELContext(), null, "typeOfSelection",
				typeOfSelection);
		try {
			int oldIndex = getRowIndex();
			int index = Integer.valueOf(indexes);
			setRowIndex(index);
			super.queueEvent(event);
			setRowIndex(oldIndex);
		} catch (Exception multipleIndexes) {
			super.queueEvent(event);

		}
	}

}
