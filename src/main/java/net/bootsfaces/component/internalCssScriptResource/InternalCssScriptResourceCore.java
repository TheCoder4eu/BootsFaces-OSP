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

package net.bootsfaces.component.internalCssScriptResource;

import javax.faces.component.UIOutput;

/** This class holds the attributes of &lt;b:internalCssScriptResource /&gt;. */
public abstract class InternalCssScriptResourceCore extends UIOutput {

	protected enum PropertyKeys {
		url;
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
	 * URL of the CSS file to include. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getUrl() {
		return (String) getStateHelper().eval(PropertyKeys.url);
	}

	/**
	 * URL of the CSS file to include. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUrl(String _url) {
		getStateHelper().put(PropertyKeys.url, _url);
	}

}
