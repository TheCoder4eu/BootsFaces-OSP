/**
 *  Copyright 2014-2019 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

/** This class holds the attributes of &lt;b:form /&gt;. */
public abstract class FormCore extends javax.faces.component.html.HtmlForm {

	protected enum PropertyKeys {
		autoUpdate, horizontal, inline;
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
	 * Setting this flag updates the widget on every AJAX request. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isAutoUpdate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.autoUpdate, false);
	}

	/**
	 * Setting this flag updates the widget on every AJAX request. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAutoUpdate(boolean _autoUpdate) {
		getStateHelper().put(PropertyKeys.autoUpdate, _autoUpdate);
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

}
