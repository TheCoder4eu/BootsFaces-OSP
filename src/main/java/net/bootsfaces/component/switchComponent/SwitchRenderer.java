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

package net.bootsfaces.component.switchComponent;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.selectBooleanCheckbox.SelectBooleanCheckboxRenderer;
import net.bootsfaces.render.Tooltip;


/** This class generates the HTML code of &lt;b:switchWidget /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.switch.Switch")
public class SwitchRenderer extends SelectBooleanCheckboxRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter rw = context.getResponseWriter();
		String clientId = component.getClientId();
		super.encodeBegin(context, component);
		clientId=clientId.replace(":","\\\\:");
		rw.append("<script>");
		rw.append("$('#input_" + clientId + "').bootstrapSwitch();");
		rw.append("</script>");
	}
}
