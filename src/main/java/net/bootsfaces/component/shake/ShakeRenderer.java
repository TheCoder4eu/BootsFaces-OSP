/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.shake;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.tabView.TabView;
import net.bootsfaces.render.CoreRenderer;


/** This class generates the HTML code of &lt;b:shake /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.shake.Shake")
public class ShakeRenderer extends CoreRenderer {
	
	/**
	 * Decode to be used to implement an AJAX version of TabView. This methods
	 * receives and processes input made by the user. More specifically, it
	 * ckecks whether the user has interacted with the current b:tabView. The
	 * default implementation simply stores the input value in the list of
	 * submitted values. If the validation checks are passed, the values in the
	 * <code>submittedValues</code> list are store in the backend bean.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:tabView.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		TabView tabView = (TabView) component;

		decodeBehaviors(context, tabView);

		String clientId = tabView.getClientId(context);
		String activeIndexId = clientId.replace(":", "_") + "_activeIndex";
		String activeIndexValue = (String) context.getExternalContext().getRequestParameterMap().get(activeIndexId);

		new AJAXRenderer().decode(context, component);
		if (null != activeIndexValue && activeIndexValue.length() > 0) {
			try {
				if (Integer.valueOf(activeIndexValue) != tabView.getActiveIndex()) {
					tabView.setActiveIndex(Integer.valueOf(activeIndexValue));
				}
			} catch (NumberFormatException e) {

			}
		}
	}

	/**
	 * This methods generates the HTML code of the current b:shake.
	 * @param context the FacesContext.
	 * @param component the current b:shake.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */  
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		Shake shake = (Shake) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = shake.getClientId();
		
		rw.startElement("script", shake);
		rw.writeAttribute("id", clientId, null);
		rw.write("\nvar myShakeEvent = new Shake({\n");
		rw.write("  threshold: " + shake.getThreshold() + ",\n");
		rw.write("  timeout: " + shake.getInterval() + "\n");
		rw.write("});\n");
		
		if (!shake.isDisabled()) {
			// Start listening to device motion:
			rw.write("myShakeEvent.start();\n");
		}

		// Register a shake event listener on window with your callback:
		rw.write("window.addEventListener('shake', function(){\n");
		String code = new AJAXRenderer().generateBootsFacesAJAXAndJavaScriptForAnMobileEvent(context, shake, rw, clientId, "shake");
		rw.write(code + "\n");
		rw.write("}, false);\n");
		rw.endElement("script");
	}
}
