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

package net.bootsfaces.component.form;

import java.io.IOException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import net.bootsfaces.C;

import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:form /&gt;. */
@FacesComponent(Form.COMPONENT_TYPE)
public class Form extends UIForm {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.form.Form";

	public static final String COMPONENT_FAMILY = "javax.faces.Form";

	public static final String DEFAULT_RENDERER = "javax.faces.Form";

	public Form() {
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

	protected enum PropertyKeys {
		horizontal, inline, style, styleClass;
		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	/**
	 * Use this flag to create a horizontal form (labels are on the same line as their input fields) <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isHorizontal() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.horizontal, false);
	}

	/**
	 * Use this flag to create a horizontal form (labels are on the same line as their input fields) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHorizontal(boolean _horizontal) {
		getStateHelper().put(PropertyKeys.horizontal, _horizontal);
	}

	/**
	 * Use this flag to create a inline form (labels are on the same line as their input fields) <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isInline() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.inline, false);
	}

	/**
	 * Use this flag to create a inline form (labels are on the same line as their input fields) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInline(boolean _inline) {
		getStateHelper().put(PropertyKeys.inline, _inline);
	}

	/**
	 * Inline style of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline style of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of this element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of this element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

}
