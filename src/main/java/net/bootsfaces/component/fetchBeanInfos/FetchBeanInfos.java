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
package net.bootsfaces.component.fetchBeanInfos;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

import net.bootsfaces.C;
import net.bootsfaces.utils.BsfUtils;

/**
 * &lt;b:fetchBeanInfos&gt; brings information about the outcome of the last validation to the client.
 * So you can ask from JavaScript whether the validation has failed, and which classes of messages
 * have been generated..
 *
 * @author Stephan Rauh
 */

@FacesComponent("net.bootsfaces.component.fetchBeanInfos.FetchBeanInfos")
public class FetchBeanInfos extends FetchBeanInfosCore {

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
