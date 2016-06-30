/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.dataTableColumn;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:dataTableColumn /&gt;. */
@FacesComponent("net.bootsfaces.component.dataTableColumn.DataTableColumn")
public class DataTableColumn extends DataTableColumnCore {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.dataTableColumn.DataTableColumn";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dataTableColumn.DataTableColumn";

	public DataTableColumn() {
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Manage EL-expression for snake-case attributes
	 */
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}
}
