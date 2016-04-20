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

package net.bootsfaces.component.pillLinks;

import javax.el.ValueExpression;

import net.bootsfaces.C;
import net.bootsfaces.component.linksContainer.LinksContainer;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author thecoder4eu
 */
public class PillLinks extends LinksContainer {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".PillLinks";

	private static final String STYLE = "nav nav-pills";

	public PillLinks() {
		setRendererType(null); // this component renders itself
		AddResourcesListener.addThemedCSSResource("core.css");
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	/*
	 * <ul class="nav nav-pills"> ... </ul>
	 */
	@Override
	protected String getContainerStyles() {
		return STYLE;
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
}
