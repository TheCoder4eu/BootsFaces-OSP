/**
 *  Copyright 2015-2016 Stephan Rauh, http://www.beyondjava.net.
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
