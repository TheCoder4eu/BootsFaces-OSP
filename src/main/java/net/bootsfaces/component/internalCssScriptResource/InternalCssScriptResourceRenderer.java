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

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;

/** This class generates the HTML code of &lt;b:internalCssScriptResource /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.internalCssScriptResource.InternalCssScriptResource")
public class InternalCssScriptResourceRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:internalCssScriptResource.
	 * @param context the FacesContext.
	 * @param component the current b:internalCssScriptResource.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		InternalCssScriptResource internalCssScriptResource = (InternalCssScriptResource) component;
		ResponseWriter rw = context.getResponseWriter();

		rw.startElement("link", internalCssScriptResource);
		rw.writeAttribute("type", "text/css", null);
		rw.writeAttribute("rel", "stylesheet", null);
		rw.writeAttribute("href", internalCssScriptResource.getUrl(), null);

		rw.endElement("link");

	}
}
