/**
 *  Copyright 2014 Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;

/**
 * This class represents and renders a checkbox.
 * 
 * @author 2014 Stephan Rauh (http://www.beyondjava.net).
 */

@FacesComponent(C.TAB_COMPONENT_TYPE)
public class Tab extends HtmlInputText {

	/**
	 * The component family for this component.
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	/**
	 * The standard component type for this component.
	 */
	public static final String COMPONENT_TYPE = C.TAB_COMPONENT_TYPE;

	public Tab() {
		setRendererType(null); // this component is rendered by its parent
	}

	/** Decode be be used to implement an AJAX version of TabView. */
	@Override
	public void decode(FacesContext context) {
		// nothing to do - this component is decoded by its parent!
	}
	
	@Override
	public void encodeAll(FacesContext context) throws IOException {
		// nothing to do - this component is rendered by its parent!
	}
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		// nothing to do - this component is rendered by its parent!
	}
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		// nothing to do - this component is rendered by its parent!
	}
}
