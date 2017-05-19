/**
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
package net.bootsfaces.component;

import javax.faces.component.UIMessages;

/**
 *
 * @author Jasper de Vries &lt;jepsar@gmail.com&gt;
 */
public class UIMessagesBase extends UIMessages {

	/**
	 * All severity names in lower case, separated by comma.
	 */
	private static final String SEVERITIES_ALL = "info,warn,error,fatal";

	/**
	 * Property keys.
	 */
	enum PropertyKeys {
		severity
	}

	/**
	 * Returns comma separated list of severity names for which messages should be rendered.
	 *
	 * @return Comma separated list of severity names for which messages should be rendered.
	 */
	public String getSeverity() {
		return (String) getStateHelper().eval(PropertyKeys.severity, SEVERITIES_ALL);
	}

	/**
	 * Sets comma separated list of severity names for which messages should be rendered.
	 *
	 * @param severity Comma separated list of severity names.
	 */
	public void setSeverity(String severity) {
		getStateHelper().put(PropertyKeys.severity, severity);
	}

}
