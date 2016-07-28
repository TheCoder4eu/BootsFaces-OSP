/**
 *  Copyright 2014-2016 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;

/** This class generates the HTML code of &lt;b:fetchBeanInfos /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.fetchBeanInfos.FetchBeanInfos")
public class FetchBeanInfosRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:fetchBeanInfos.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:fetchBeanInfos.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		FetchBeanInfos fetchBeanInfos = (FetchBeanInfos) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = fetchBeanInfos.getClientId();

		boolean validationFailed = context.isValidationFailed();
		Severity maximumSeverity = context.getMaximumSeverity();
		String maximum="null";
		if (maximumSeverity!=null) {
			maximum=String.valueOf(maximumSeverity.getOrdinal());
		}
		String variables = "bfMaximumMessageSeverity=" + maximum + ";";
		boolean hasFatalError = false;
		boolean hasError = false;
		boolean hasWarning = false;
		boolean hasInfo = false;
		List<FacesMessage> messageList = context.getMessageList();
		for (FacesMessage message : messageList) {
			if (FacesMessage.SEVERITY_FATAL.equals(message.getSeverity())) {
				hasFatalError = true;
			} else if (FacesMessage.SEVERITY_ERROR.equals(message.getSeverity())) {
				hasError = true;
			} else if (FacesMessage.SEVERITY_WARN.equals(message.getSeverity())) {
				hasWarning = true;
			} else if (FacesMessage.SEVERITY_INFO.equals(message.getSeverity())) {
				hasInfo = true;
			}
		}
		variables += "bfHasFatalError=" + hasFatalError + ";";
		variables += "bfHasError=" + hasError + ";";
		variables += "bfHasWarning=" + hasWarning + ";";
		variables += "bfHasInfo=" + hasInfo + ";";

		rw.startElement("script", fetchBeanInfos);
		rw.writeAttribute("id", clientId, null);
		rw.writeText("validationFailed=" + validationFailed + ";" + variables, null);
		rw.endElement("script");
	}

}
