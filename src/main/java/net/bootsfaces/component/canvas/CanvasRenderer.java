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

package net.bootsfaces.component.canvas;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:canvas /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.canvas.Canvas")
public class CanvasRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:canvas.
	 * @param context the FacesContext.
	 * @param component the current b:canvas.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		Canvas canvas = (Canvas) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = canvas.getClientId();

		rw.startElement("canvas", canvas);
		Tooltip.generateTooltip(context, canvas, rw);

//	    rw.writeAttribute("initial-drawing", canvas.getInitial-drawing(), "initial-drawing");
	    rw.writeAttribute("id", clientId, "id");
	    writeAttribute(rw, "style", canvas.getStyle(), "style");
	    String styleClass = canvas.getStyleClass();
	    if (null != styleClass)
	    	styleClass += Responsive.getResponsiveStyleClass(canvas, false);
	    else
	    	styleClass = Responsive.getResponsiveStyleClass(canvas, false);
		writeAttribute(rw, "class", styleClass, "class");
	    writeAttribute(rw, "width", canvas.getWidth(), "width");
	    writeAttribute(rw, "height", canvas.getHeight(), "height");
		rw.endElement("canvas");
		Tooltip.activateTooltips(context, canvas);
		Drawing drawing = canvas.getDrawing();
		if (null != drawing) {
			String script = ((Drawing) drawing).getJavaScript();
			rw.startElement("script", component);
			rw.write("\nnew function(){\n");
			rw.write("    var canvas=document.getElementById('" + clientId + "');\n");
			rw.write("    var ctx = canvas.getContext('2d');\n");

			rw.write(script);
			rw.write("\n}();\n");
			rw.endElement("script");
		}
	}
}
