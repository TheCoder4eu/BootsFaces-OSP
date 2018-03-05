/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component.fetchBeanInfos;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.utils.BsfUtils;

/**
 * &lt;b:fetchBeanInfos&gt; brings information about the outcome of the last validation to the client.
 * So you can ask from JavaScript whether the validation has failed, and which classes of messages
 * have been generated..
 *
 * @author Stephan Rauh
 */

@FacesComponent(FetchBeanInfos.COMPONENT_TYPE)
public class FetchBeanInfos extends FetchBeanInfosCore {

        public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".fetchBeanInfos.FetchBeanInfos";
    
	/**
	 * The component family for this component.
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public FetchBeanInfos() {
		setRendererType("net.bootsfaces.component.fetchBeanInfos.FetchBeanInfos");
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Provide support to snake-case attribute in EL-expression items
	 */
	@Override
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		super.encodeBegin(context);
	}
}
