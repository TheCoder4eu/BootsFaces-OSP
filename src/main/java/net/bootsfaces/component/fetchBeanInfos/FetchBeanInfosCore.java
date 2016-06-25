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
