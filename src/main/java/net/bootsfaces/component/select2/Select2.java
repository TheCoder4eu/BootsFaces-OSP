/**
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

package net.bootsfaces.component.select2;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:select2 /&gt;. */
@FacesComponent("net.bootsfaces.component.select2.Select2")
public class Select2 extends Select2Core implements net.bootsfaces.render.IHasTooltip, net.bootsfaces.render.IResponsive {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.select2.Select2";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.select2.Select2";

	public Select2() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
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
