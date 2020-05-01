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

package net.bootsfaces.component.column;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:column /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.column.Column")
public class ColumnRenderer extends CoreRenderer {
	/**
	 * Column sizes
	 * @author durzod
	 *
	 */
	public enum ColSizes {
		xs, sm, md, lg
	}

	/**
	 * This methods generates the HTML code of the current b:column.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:column.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		Column column = (Column) component;

		if (column.isRendered()) {
			ResponseWriter rw = context.getResponseWriter();

			String style = column.getStyle();
			String sclass = column.getStyleClass();

			rw.startElement("div", column);
			if (null != column.getDir()) {
				rw.writeAttribute("dir", column.getDir(), "dir");
			}

			if (this != null) {
				rw.writeAttribute("id", column.getClientId(), "id");
				Tooltip.generateTooltip(FacesContext.getCurrentInstance(), column, rw);
			}

			if (sclass == null) {
				sclass = Responsive.getResponsiveStyleClass(column).trim();
				if (sclass.length()==0) sclass=null;
			} else {
				sclass += Responsive.getResponsiveStyleClass(column);
			}
			rw.writeAttribute("class", sclass, "class");
			if (style != null) {
				rw.writeAttribute("style", style, "style");
			}
			beginDisabledFieldset(column, rw);
		}
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		Column column = (Column) component;

		if (column.isRendered()) {
			super.encodeChildren(context, component);
		}
	}

	public void encodeEnd(FacesContext fc, UIComponent component) throws IOException {
		Column column = (Column) component;

		if (column.isRendered()) {
			ResponseWriter rw = fc.getResponseWriter();
			endDisabledFieldset(column, rw);
	        rw.endElement("div");
	        Tooltip.activateTooltips(FacesContext.getCurrentInstance(), column);
		}
    }
}
