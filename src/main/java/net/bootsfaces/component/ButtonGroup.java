/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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

package net.bootsfaces.component;

import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.render.A;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */

@ResourceDependencies({ 
	    @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
@FacesComponent(C.BUTTONGROUP_COMPONENT_TYPE)
public class ButtonGroup extends GenContainerDiv {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.BUTTONGROUP_COMPONENT_TYPE;
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
	private Map<String, Object> attributes;

	public ButtonGroup() {
		setRendererType(null); // this component renders itself
		Tooltip.addResourceFile();
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}

	/*
	 * <div class="btn-group"> ... </div>
	 */
	@Override
	public String getContainerStyles() {
		StringBuilder sb = new StringBuilder();
		sb.append("btn-group");
		String o = A.asString(getAttributes().get("orientation"));
		String s = A.asString(getAttributes().get("size"));
		if (o != null && o.equals("vertical")) {
			sb.append("-vertical");
		}
		if (s != null) {
			sb.append(" btn-group-").append(s);
		}
		return sb.toString();
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
