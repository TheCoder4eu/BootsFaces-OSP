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

package net.bootsfaces.component.shake;

import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:shake /&gt;. */
@FacesComponent("net.bootsfaces.component.shake.Shake")
public class Shake extends UICommand implements ClientBehaviorHolder, IAJAXComponent {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.shake.Shake";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.shake.Shake";

	public Shake() {
		AddResourcesListener.addResourceToHeadButAfterJQuery("bsf", "jq/mobile/shake.js");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public Map<String, String> getJQueryEvents() {
		return null;
	}

	@Override
	@Deprecated
	public String getOnclick() {
		throw new IllegalAccessError("The onclick attribute hasn't been implemented. Please use onshake instead.");
	}

	/**
	 * Manage EL-expression for snake-case attributes
	 */
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	protected enum PropertyKeys {
		ajax, disabled, interval, oncomplete, onshake, process, threshold, update, widgetVar;
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
	 * Whether the Button submits the form with AJAX.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isAjax() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.ajax, false);
	}

	/**
	 * Whether the Button submits the form with AJAX.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAjax(boolean _ajax) {
		getStateHelper().put(PropertyKeys.ajax, _ajax);
	}

	/**
	 * Disables the input element, default is false.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
	}

	/**
	 * Disables the input element, default is false.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * Specifies the delay (in milliseconds) between each slide.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getInterval() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.interval, 500);
	}

	/**
	 * Specifies the delay (in milliseconds) between each slide.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInterval(int _interval) {
		getStateHelper().put(PropertyKeys.interval, _interval);
	}

	/**
	 * JavaScript to be executed when ajax completes with success.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOncomplete() {
		return (String) getStateHelper().eval(PropertyKeys.oncomplete);
	}

	/**
	 * JavaScript to be executed when ajax completes with success.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncomplete(String _oncomplete) {
		getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
	}

	/**
	 * A method binding expression referring to a method for handling a
	 * valuechangeevent.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnshake() {
		return (String) getStateHelper().eval(PropertyKeys.onshake);
	}

	/**
	 * A method binding expression referring to a method for handling a
	 * valuechangeevent.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnshake(String _onshake) {
		getStateHelper().put(PropertyKeys.onshake, _onshake);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which
	 * values are to be sent to the server.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getProcess() {
		return (String) getStateHelper().eval(PropertyKeys.process);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which
	 * values are to be sent to the server.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setProcess(String _process) {
		getStateHelper().put(PropertyKeys.process, _process);
	}

	/**
	 * Specifies the sensitivity of the skae detector.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getThreshold() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.threshold, 15);
	}

	/**
	 * Specifies the sensitivity of the skae detector.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setThreshold(int _threshold) {
		getStateHelper().put(PropertyKeys.threshold, _threshold);
	}

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getUpdate() {
		return (String) getStateHelper().eval(PropertyKeys.update);
	}

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	/**
	 * optional widget variable to access the datatable widget in JavaScript
	 * code.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar, "bfShakeDetector");
	}

	/**
	 * optional widget variable to access the datatable widget in JavaScript
	 * code.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidgetVar(String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}

}
