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

package net.bootsfaces.component.dataTableColumn;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;

/** This class generates the HTML code of &lt;b:dataTableColumn /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.dataTableColumn.DataTableColumn")
public class DataTableColumnRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:dataTableColumn.
	 * @param context the FacesContext.
	 * @param component the current b:dataTableColumn.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		throw new FacesException("The b:dataTableColumn can only be used in a b:dataTable.");
	}

}
