/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.jumbotron;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;


/** This class generates the HTML code of &lt;b:jumbotron /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.jumbotron.Jumbotron")
public class JumbotronRenderer extends CoreRenderer {
	
	/**
	 * This methods generates the HTML code of the current b:jumbotron.
	* <code>encodeBegin</code> generates the start of the component. After the, the JSF framework calls <code>encodeChildren()</code>
	* to generate the HTML code between the beginning and the end of the component. For instance, in the case of a panel component
	* the content of the panel is generated by <code>encodeChildren()</code>. After that, <code>encodeEnd()</code> is called
	* to generate the rest of the HTML code.
	 * @param context the FacesContext.
	 * @param component the current b:jumbotron.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */  
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		Jumbotron jumbotron = (Jumbotron) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = jumbotron.getClientId();
		
		rw.startElement("div", jumbotron);
		rw.writeAttribute("id",clientId,"id");
		
		if(BsfUtils.isStringValued(jumbotron.getStyle()))
			rw.writeAttribute("style", jumbotron.getStyle(), "style");
		
		Tooltip.generateTooltip(context, jumbotron, rw);
		
		String styleClass = "jumbotron";
		if(BsfUtils.isStringValued(jumbotron.getStyleClass()))
			styleClass = styleClass + " " + jumbotron.getStyleClass();
		rw.writeAttribute("class", styleClass, "class");
		beginDisabledFieldset(jumbotron, rw);
	}
	
	/**
	 * This methods generates the HTML code of the current b:jumbotron.
	* <code>encodeBegin</code> generates the start of the component. After the, the JSF framework calls <code>encodeChildren()</code>
	* to generate the HTML code between the beginning and the end of the component. For instance, in the case of a panel component
	* the content of the panel is generated by <code>encodeChildren()</code>. After that, <code>encodeEnd()</code> is called
	* to generate the rest of the HTML code.
	 * @param context the FacesContext.
	 * @param component the current b:jumbotron.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */  
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		Jumbotron jumbotron = (Jumbotron) component;
		ResponseWriter rw = context.getResponseWriter();
		endDisabledFieldset(jumbotron, rw);
		rw.endElement("div");
		Tooltip.activateTooltips(context, jumbotron);
	}
}
