/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.scrollSpy;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import net.bootsfaces.component.scrollSpy.event.ScrollSpyEventListener;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:scrollSpy /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/scrollspy.js", target = "body"), })
@FacesComponent("net.bootsfaces.component.scrollSpy.ScrollSpy")
public class ScrollSpy extends UIComponentBase {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.scrollSpy.ScrollSpy";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.scrollSpy.ScrollSpy";

	public ScrollSpy() {
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
		container,
		offset,
		selectionListener,
		smooth,
		smoothSpeed,
		target,
		update;
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
	 * Id of the element that is the scrollable container. Search expressions are allowed if they yield only one result. If not specified, body is set by default. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContainer() {
		return (String) getStateHelper().eval(PropertyKeys.container);
	}

	/**
	 * Id of the element that is the scrollable container. Search expressions are allowed if they yield only one result. If not specified, body is set by default. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContainer(String _container) {
		getStateHelper().put(PropertyKeys.container, _container);
	}

	/**
	 * Scroll offset <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getOffset() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.offset, 0);
	}

	/**
	 * Scroll offset <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffset(int _offset) {
		getStateHelper().put(PropertyKeys.offset, _offset);
	}

	/**
	 * Selection listener called on selection changed. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public ScrollSpyEventListener getSelectionListener() {
		return (ScrollSpyEventListener) getStateHelper().eval(PropertyKeys.selectionListener);
	}

	/**
	 * Selection listener called on selection changed. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectionListener(ScrollSpyEventListener _selectionListener) {
		getStateHelper().put(PropertyKeys.selectionListener, _selectionListener);
	}

	/**
	 * Enable smooth scroll <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isSmooth() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.smooth, false);
	}

	/**
	 * Enable smooth scroll <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSmooth(boolean _smooth) {
		getStateHelper().put(PropertyKeys.smooth, _smooth);
	}

	/**
	 * Speed of the smooth scroll (default 800) <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getSmoothSpeed() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.smoothSpeed, 800);
	}

	/**
	 * Speed of the smooth scroll (default 800) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSmoothSpeed(int _smoothSpeed) {
		getStateHelper().put(PropertyKeys.smoothSpeed, _smoothSpeed);
	}

	/**
	 * Id of the navigation target. Usually the id of the NavBar component. Search expressions are allowed if they yield only one result. If not specified, component with .navbar class is set. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTarget() {
		return (String) getStateHelper().eval(PropertyKeys.target);
	}

	/**
	 * Id of the navigation target. Usually the id of the NavBar component. Search expressions are allowed if they yield only one result. If not specified, component with .navbar class is set. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTarget(String _target) {
		getStateHelper().put(PropertyKeys.target, _target);
	}

	/**
	 * Component(s) to be updated with ajax. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getUpdate() {
		return (String) getStateHelper().eval(PropertyKeys.update);
	}

	/**
	 * Component(s) to be updated with ajax. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

}
