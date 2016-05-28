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

package net.bootsfaces.component.gyroscope;

import javax.faces.component.UICommand;

/** This class holds the attributes of &lt;b:gyroscope /&gt;. */
public abstract class GyroscopeCore extends UICommand {

	protected enum PropertyKeys {
		ajax,
		alpha,
		beta,
		disabled,
		gamma,
		interval,
		oncomplete,
		onrotation,
		process,
		threshold,
		update,
		widgetVar;
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
	 * Whether the Button submits the form with AJAX. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isAjax() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.ajax, false);
	}

	/**
	 * Whether the Button submits the form with AJAX. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAjax(boolean _ajax) {
		getStateHelper().put(PropertyKeys.ajax, _ajax);
	}

	/**
	 * Variable to store the alpha value of the gyroscope's rotation <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.el.ValueExpression getAlpha() {
		return (javax.el.ValueExpression) getStateHelper().eval(PropertyKeys.alpha);
	}

	/**
	 * Variable to store the alpha value of the gyroscope's rotation <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAlpha(javax.el.ValueExpression _alpha) {
		getStateHelper().put(PropertyKeys.alpha, _alpha);
	}

	/**
	 * Variable to store the beta value of the gyroscope's rotation <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.el.ValueExpression getBeta() {
		return (javax.el.ValueExpression) getStateHelper().eval(PropertyKeys.beta);
	}

	/**
	 * Variable to store the beta value of the gyroscope's rotation <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBeta(javax.el.ValueExpression _beta) {
		getStateHelper().put(PropertyKeys.beta, _beta);
	}

	/**
	 * Disables the input element, default is false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
	}

	/**
	 * Disables the input element, default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * Variable to store the gamma value of the gyroscope's rotation <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.el.ValueExpression getGamma() {
		return (javax.el.ValueExpression) getStateHelper().eval(PropertyKeys.gamma);
	}

	/**
	 * Variable to store the gamma value of the gyroscope's rotation <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setGamma(javax.el.ValueExpression _gamma) {
		getStateHelper().put(PropertyKeys.gamma, _gamma);
	}

	/**
	 * Specifies the delay (in milliseconds) between each slide. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getInterval() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.interval, 500);
	}

	/**
	 * Specifies the delay (in milliseconds) between each slide. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInterval(int _interval) {
		getStateHelper().put(PropertyKeys.interval, _interval);
	}

	/**
	 * JavaScript to be executed when ajax completes with success. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOncomplete() {
		return (String) getStateHelper().eval(PropertyKeys.oncomplete);
	}

	/**
	 * JavaScript to be executed when ajax completes with success. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncomplete(String _oncomplete) {
		getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
	}

	/**
	 * A method binding expression referring to a method for handling a valuechangeevent. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnrotation() {
		return (String) getStateHelper().eval(PropertyKeys.onrotation);
	}

	/**
	 * A method binding expression referring to a method for handling a valuechangeevent. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnrotation(String _onrotation) {
		getStateHelper().put(PropertyKeys.onrotation, _onrotation);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getProcess() {
		return (String) getStateHelper().eval(PropertyKeys.process);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setProcess(String _process) {
		getStateHelper().put(PropertyKeys.process, _process);
	}

	/**
	 * Specifies the sensitivity of the skae detector. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getThreshold() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.threshold, 15);
	}

	/**
	 * Specifies the sensitivity of the skae detector. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setThreshold(int _threshold) {
		getStateHelper().put(PropertyKeys.threshold, _threshold);
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

	/**
	 * optional widget variable to access the datatable widget in JavaScript code. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar, "bfGyroscope");
	}

	/**
	 * optional widget variable to access the datatable widget in JavaScript code. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidgetVar(String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}

}
