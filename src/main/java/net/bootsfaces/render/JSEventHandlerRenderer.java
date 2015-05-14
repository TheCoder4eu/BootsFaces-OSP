/**
 *  Copyright 2015 Stephan Rauh, http://www.beyondjava.net.
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
package net.bootsfaces.render;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

/** 
 * Generates the standard Javascript event handlers.
 * @author Stephan Rauh, http://www.beyondjava.net
 */
public class JSEventHandlerRenderer {
	/**
	 * Generates the standard Javascript event handlers.
	 * @param rw The response writer
	 * @param component the current component
	 * @throws IOException thrown if something goes wrong when writing the HTML code
	 */
	public static void generateJSEventHandlers(ResponseWriter rw, UIComponent component) throws IOException {
		Map<String, Object> attributes = component.getAttributes();
		String[] eventHandlers = {"onclick", "onblur", "onmouseover"};
        for (String event:eventHandlers) {
        	String handler = A.asString(attributes.get(event));
        	if (null != handler) {
        		rw.writeAttribute(event, handler, event);
        	}
        }
	}


}
