/**
 *  Copyright 2014 - 17 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.internalJavaScriptResource;

import javax.faces.component.*;
import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.internalCssScriptResource.InternalCssScriptResource;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:internalJavaScriptResource /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.internalJavaScriptResource.InternalJavaScriptResource")
public class InternalJavaScriptResourceRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:internalJavaScriptResource.
	 * @param context the FacesContext.
	 * @param component the current b:internalJavaScriptResource.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		InternalJavaScriptResource internalJavaScriptResource = (InternalJavaScriptResource) component;
		ResponseWriter rw = context.getResponseWriter();

		rw.startElement("script", internalJavaScriptResource);
		rw.writeAttribute("src", internalJavaScriptResource.getUrl(), null);

		rw.endElement("script");

	}

}
