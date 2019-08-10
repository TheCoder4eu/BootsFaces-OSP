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

package net.bootsfaces.component.selectMultiMenu;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.IResponsiveLabel;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:selectMultiMenu /&gt;. */
@ResourceDependencies({
	  @ResourceDependency(library = "bsf", name = "js/bootstrap-multiselect.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/dropdown.js", target = "body"), })

@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(SelectMultiMenu.COMPONENT_TYPE)
public class SelectMultiMenu extends SelectMultiMenuCore implements net.bootsfaces.render.IHasTooltip, IResponsive, IResponsiveLabel,
                                                                    net.bootsfaces.component.ajax.IAJAXComponent, IAJAXComponent2 {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".selectMultiMenu.SelectMultiMenu";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.selectMultiMenu.SelectMultiMenu";

	public SelectMultiMenu() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addExtCSSResource("bootstrap-multiselect.css");

		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
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


	@Override
	public Map<String, String> getJQueryEvents() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("selectall", "onSelectAll");
		result.put("dropdownshow", "onDropdownShow");
		result.put("dropdownhidden", "onDropdownHidden");
		result.put("selectall", "onSelectAll");
		result.put("change", "onChange");
		result.put("dropdownhide", "onDropdownHide");
		result.put("deselectall", "onDeselectAll");
		result.put("dropdownshown", "onDropdownShown");
		result.put("unitialized", "onInitialized");
		return result;
	}

	@Override
	public Map<String, String> getJQueryEventParameterLists() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("change", "option, checked, select");
		return result;
	}

	@Override
	public Map<String, String> getJQueryEventParameterListsForAjax() {
		return null;
	}
	
	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			new ArrayList<String>());

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "change";
	}
}
