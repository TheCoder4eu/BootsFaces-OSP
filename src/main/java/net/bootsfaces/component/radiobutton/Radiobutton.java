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

package net.bootsfaces.component.radiobutton;

import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:radiobutton /&gt;. */
@FacesComponent(Radiobutton.COMPONENT_TYPE)
public class Radiobutton extends RadiobuttonCore implements net.bootsfaces.render.IHasTooltip, IResponsive, IAJAXComponent {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".radiobutton.Radiobutton";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.radiobutton.Radiobutton";

	public Radiobutton() {
		Tooltip.addResourceFiles();
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

	@Override
	public Map<String, String> getJQueryEvents() {
		// this is not an jQuery component
		return null;
	}
	
	public String getDefaultEventName() {
		return "click";
	}

	public String getName() {
		String propertyName = getValueExpression("value").getExpressionString();
		if (propertyName.startsWith("#{") && propertyName.endsWith("}")) {
			propertyName=propertyName.substring(2, propertyName.length()-1);
			return "input_"+propertyName.trim();
		} else {
			throw new FacesException("The value attribute of a radiobutton must be an EL expression.");
		}

	}

	@Override
	public String getType() {
		return "hidden";
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
