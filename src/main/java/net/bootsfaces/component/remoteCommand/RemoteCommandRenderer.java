/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.remoteCommand;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.CoreRenderer;

/** This class generates the HTML code of &lt;b:remoteCommand /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.remoteCommand.RemoteCommand")
public class RemoteCommandRenderer extends CoreRenderer {
	/**
	 * This methods receives and processes input made by the user. More specifically, it ckecks whether the
	 * user has interacted with the current b:remoteCommand. The default implementation simply stores
	 * the input value in the list of submitted values. If the validation checks are passed,
	 * the values in the <code>submittedValues</code> list are store in the backend bean.
	 * @param context the FacesContext.
	 * @param component the current b:remoteCommand.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
//		RemoteCommand remoteCommand = (RemoteCommand) component;

//		if (component.isDisabled() ) {
//			return;
//		}

		decodeBehaviors(context, component);

		String clientId = component.getClientId(context);
		new AJAXRenderer().decode(context, component, clientId);
	}

	/**
	 * This methods generates the HTML code of the current b:remoteCommand.
	 * @param context the FacesContext.
	 * @param component the current b:remoteCommand.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		RemoteCommand remoteCommand = (RemoteCommand) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = remoteCommand.getClientId();

		// put custom code here
		// Simple demo widget that simply renders every attribute value
		StringBuilder call = AJAXRenderer.generateAJAXCall(context, remoteCommand, null);
		String name = remoteCommand.getName();
		if (null == name) {
			throw new FacesException("b:remoteCommand: Please define the name of the JavaScript function calling the Java backend.");
		}
			
		rw.startElement("script", component);
		rw.writeAttribute("id", clientId, null);
		String c = call.toString().replace("callAjax(this,", "callAjax({x:{id:'" + clientId + "'}},");
		rw.append("function " + name + "(){" + c + "}");
		rw.endElement("script");

	}

}
