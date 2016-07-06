/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

import javax.faces.component.UIComponentBase;

/** This class holds the attributes of &lt;b:fetchBeanInfos /&gt;. */
public abstract class FetchBeanInfosCore extends UIComponentBase {

	protected enum PropertyKeys {
		id;
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
	 * Unique identifier of the component in a namingContainer. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getId() {
		return (String) getStateHelper().eval(PropertyKeys.id);
	}

	/**
	 * Unique identifier of the component in a namingContainer. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setId(String _id) {
		getStateHelper().put(PropertyKeys.id, _id);
	}

}
