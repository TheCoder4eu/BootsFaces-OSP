/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.iconAwesome;

import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.component.icon.Icon;
import net.bootsfaces.render.Tooltip;

/** This class holds the attributes of &lt;b:iconAwesome /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/font-awesome.css", target = "head"),
		                @ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head"),
		        		@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
		        		@ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head")
})
@FacesComponent("net.bootsfaces.component.iconAwesome.IconAwesome")
public class IconAwesome extends Icon {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.iconAwesome.IconAwesome";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.iconAwesome.IconAwesome";

	private Map<String, Object> attributes;

	public IconAwesome() {
		Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}
}
