/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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

package net.bootsfaces.component.tabLinks;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;

import net.bootsfaces.component.linksContainer.LinksContainer;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author thecoder4.eu
 */

@FacesComponent("net.bootsfaces.component.tabLinks.TabLinks")
public class TabLinks extends LinksContainer {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.tabLinks.TabLinks";

	public TabLinks() {
		setRendererType(null); // this component renders itself
		AddResourcesListener.addThemedCSSResource("core.css");
		Tooltip.addResourceFiles();
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	/*
	 * <ul class="nav nav-tabs"> ... </ul>
	 */
	@Override
	protected String getContainerStyles() {
		return "nav nav-tabs";
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
}
