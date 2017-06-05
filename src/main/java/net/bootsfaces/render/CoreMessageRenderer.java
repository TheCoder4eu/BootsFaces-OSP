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
package net.bootsfaces.render;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import net.bootsfaces.component.UIMessagesBase;
import net.bootsfaces.component.growl.Growl;
import net.bootsfaces.component.messages.Messages;

/**
 * Core renderer for {@link Growl} and {@link Messages}.
 *
 * @author Jasper de Vries &lt;jepsar@gmail.com&gt;
 */
public class CoreMessageRenderer extends CoreRenderer {

	/**
	 * Returns {@code true} if the provided message should be rendered in the provided base message component.
	 *
	 * @param message Message to be checked for rendering.
	 * @param uiMessagesBase Base message component message needs to be rendered in.
	 * @return {@code true} if the provided message should be rendered in the provided base message component.
	 */
	protected boolean shouldBeRendered(FacesMessage message, UIMessagesBase uiMessagesBase) {
		return (!message.isRendered() || uiMessagesBase.isRedisplay())
			&& uiMessagesBase.getSeverity().contains(getSeverityName(message.getSeverity()));
	}

	/**
	 * Returns name of the given severity in lower case.
	 *
	 * @param severity Severity to get name of.
	 * @return Name of the given severity in lower case.
	 */
	public String getSeverityName(Severity severity) {
		if (severity.equals(FacesMessage.SEVERITY_INFO)) {
			return "info";
		} else if (severity.equals(FacesMessage.SEVERITY_WARN)) {
			return "warn";
		} else if (severity.equals(FacesMessage.SEVERITY_ERROR)) {
			return "error";
		} else if (severity.equals(FacesMessage.SEVERITY_FATAL)) {
			return "fatal";
		}
		throw new IllegalStateException();
	}

}
