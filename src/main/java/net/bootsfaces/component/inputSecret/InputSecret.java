/**
 *  Copyright 2014-2017 Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.inputSecret;

import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.component.inputText.InputText;

/**
 *
 * @author Stephan Rauh, http://www.beyondjava.net
 */

@FacesComponent(InputSecret.COMPONENT_TYPE)
public class InputSecret extends InputText {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".inputSecret.InputSecret";

	protected enum PropertyKeys {
		renderValue;
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
	 * By default, the value of the password field is never sent to the client.
	 * However, if you need to send the value to the client for some reason, you can
	 * set this flag to true. Please make sure that this is not a security hole. The
	 * password may be unreadable on the screen, but hackers can read it easily.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or false, if it hasn't been set
	 *         by the JSF file.
	 */
	public boolean isRenderValue() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.renderValue, false);
	}

	/**
	 * By default, the value of the password field is never sent to the client.
	 * However, if you need to send the value to the client for some reason, you can
	 * set this flag to true. Please make sure that this is not a security hole. The
	 * password may be unreadable on the screen, but hackers can read it easily.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRenderValue(boolean _renderValue) {
		getStateHelper().put(PropertyKeys.renderValue, _renderValue);
	}

}
