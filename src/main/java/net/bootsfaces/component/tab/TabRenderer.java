/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.tab;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;


/** This class generates the HTML code of &lt;b:tab /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.tab.Tab")
public class TabRenderer extends CoreRenderer {
	
	@Override
	public void decode(FacesContext context, UIComponent component) {
		// The AJAXRenderer generates the id of the child element, but the AJAX event is processed by the parent instead
		component.getParent().decode(context);
	}
	
	/**
	 * This methods generates the HTML code of the current b:tab.
	 * @param context the FacesContext.
	 * @param component the current b:tab.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */  
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		// Nothing to do: this component is rendered by b:tabView
	}

	/**
	 * This methods generates the HTML code of the current b:tab.
	 * @param context the FacesContext.
	 * @param component the current b:tab.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */  
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		// Nothing to do: this component is rendered by b:tabView
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// TODO Auto-generated method stub
		super.encodeChildren(context, component);
	}
	
}
