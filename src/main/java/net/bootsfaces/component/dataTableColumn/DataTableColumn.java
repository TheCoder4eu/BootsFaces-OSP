/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.dataTableColumn;

import jakarta.el.ValueExpression;
import jakarta.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:dataTableColumn /&gt;. */
@FacesComponent(DataTableColumn.COMPONENT_TYPE)
public class DataTableColumn extends DataTableColumnCore {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".dataTableColumn.DataTableColumn";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dataTableColumn.DataTableColumn";

	public DataTableColumn() {
		AddResourcesListener.addThemedCSSResource("core.css");
		//AddResourcesListener.addThemedCSSResource("bsf.css");
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
