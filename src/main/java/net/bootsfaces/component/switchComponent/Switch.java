/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.switchComponent;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.render.Tooltip; 

/** This class holds the attributes of &lt;b:switch /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target="head"),
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/bootstrap-switch.css"),
	@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
	@ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head"),
	@ResourceDependency(library = "bsf", name = "js/bootstrap-switch.js", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")})
@FacesComponent("net.bootsfaces.component.switch.Switch")
public class Switch extends net.bootsfaces.component.selectBooleanCheckbox.SelectBooleanCheckbox
		implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.switch.Switch";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.switch.Switch";

	public Switch() {

		Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}
}

