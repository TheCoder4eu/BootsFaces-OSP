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

package net.bootsfaces.component.canvas;

import javax.el.MethodExpression;
import javax.faces.component.*;
import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
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
	    writeAttribute(rw, "class", canvas.getStyleClass(), "class");
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
