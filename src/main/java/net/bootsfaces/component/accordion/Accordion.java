/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu), Stephan Rauh (http://www.beyondjava.net) and Dario D'Urzo.
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
package net.bootsfaces.component.accordion;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:accordion /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/transition.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "js/collapse.js", target = "body"), })
@FacesComponent("net.bootsfaces.component.accordion.Accordion")
public class Accordion extends AccordionCore implements IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.accordion.Accordion";
	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.accordion.Accordion";

	public Accordion() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("panels.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}
}
