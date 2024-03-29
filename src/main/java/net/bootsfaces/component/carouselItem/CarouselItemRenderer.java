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

package net.bootsfaces.component.carouselItem;

import java.io.IOException;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.carouselCaption.CarouselCaptionRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;


/** This class generates the HTML code of &lt;b:carouselItem /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.carouselItem.CarouselItem")
public class CarouselItemRenderer extends CoreRenderer {
	/**
	 * This methods receives and processes input made by the user. More specifically, it ckecks whether the
	 * user has interacted with the current b:carouselItem. The default implementation simply stores
	 * the input value in the list of submitted values. If the validation checks are passed,
	 * the values in the <code>submittedValues</code> list are store in the backend bean.
	 * @param context the FacesContext.
	 * @param component the current b:carouselItem.
	 */  
	@Override
	public void decode(FacesContext context, UIComponent component) {
	    // CarouselItem carouselItem = (CarouselItem) component;
	
		new AJAXRenderer().decode(context, component);	
	}
	
	/**
	 * This methods generates the HTML code of the current b:carouselItem.
	* <code>encodeBegin</code> generates the start of the component. After the, the JSF framework calls <code>encodeChildren()</code>
	* to generate the HTML code between the beginning and the end of the component. For instance, in the case of a panel component
	* the content of the panel is generated by <code>encodeChildren()</code>. After that, <code>encodeEnd()</code> is called
	* to generate the rest of the HTML code.
	 * @param context the FacesContext.
	 * @param component the current b:carouselItem.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */  
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		CarouselItem carouselItem = (CarouselItem) component;
		ResponseWriter rw = context.getResponseWriter();
		// String clientId = carouselItem.getClientId();
		
		// put custom code here
		// Simple demo widget that simply renders every attribute value
		rw.startElement("div", carouselItem);
		Tooltip.generateTooltip(context, carouselItem, rw);

		rw.writeAttribute("style", carouselItem.getStyle(), "style");
	    String styleClass = carouselItem.getStyleClass();
	    if (null == styleClass)
	    	styleClass="item";
	    else
	    	styleClass = "item " + styleClass;
	    if (carouselItem.isActive()) {
	    	styleClass += " active";
	    }
		rw.writeAttribute("class", styleClass, "class");
		
	    rw.writeAttribute("id", carouselItem.getId(), "id");
	    Tooltip.activateTooltips(context, carouselItem);
	    AJAXRenderer.generateBootsFacesAJAXAndJavaScript(context, carouselItem, rw, false);
	}
	
	/**
	 * This methods generates the HTML code of the current b:carouselItem.
	* <code>encodeBegin</code> generates the start of the component. After the, the JSF framework calls <code>encodeChildren()</code>
	* to generate the HTML code between the beginning and the end of the component. For instance, in the case of a panel component
	* the content of the panel is generated by <code>encodeChildren()</code>. After that, <code>encodeEnd()</code> is called
	* to generate the rest of the HTML code.
	 * @param context the FacesContext.
	 * @param component the current b:carouselItem.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */  
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		CarouselItem carouselItem = (CarouselItem) component;
		ResponseWriter rw = context.getResponseWriter();
		
		if (carouselItem.getCaption()!=null) {
			new CarouselCaptionRenderer().encodeDefaultCaption(context, component, carouselItem.getCaption());
		}

		rw.endElement("div");
		Tooltip.activateTooltips(context, carouselItem);
	
	}
	
}
