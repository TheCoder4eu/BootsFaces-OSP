/**
 *  Copyright 2014-17 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.link;

import javax.faces.component.FacesComponent;

import net.bootsfaces.component.navLink.NavLink;

/** This class holds the attributes of &lt;b:link /&gt;. */
@FacesComponent("net.bootsfaces.component.link.Link")
public class Link extends NavLink {

	public Link() {
		super();
	}

	protected enum PropertyKeys {
		look;
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
	 * Make the link look like a button. Can be primary, block, info, success,
	 * warning, important, danger, and default. If not specified, a standard HTML
	 * hyperlink is rendered.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getLook() {
		return (String) getStateHelper().eval(PropertyKeys.look);
	}

	/**
	 * Make the link look like a button. Can be primary, block, info, success,
	 * warning, important, danger, and default. If not specified, a standard HTML
	 * hyperlink is rendered.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLook(String _look) {
		getStateHelper().put(PropertyKeys.look, _look);
	}
}
