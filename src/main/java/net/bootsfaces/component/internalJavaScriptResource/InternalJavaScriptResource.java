/**
 *  Copyright 2014-17 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.internalJavaScriptResource;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.*;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:internalJavaScriptResource /&gt;. */
@FacesComponent("net.bootsfaces.component.internalJavaScriptResource.InternalJavaScriptResource")
public class InternalJavaScriptResource extends InternalJavaScriptResourceCore {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.internalJavaScriptResource.InternalJavaScriptResource";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.internalJavaScriptResource.InternalJavaScriptResource";

	public InternalJavaScriptResource() {
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
