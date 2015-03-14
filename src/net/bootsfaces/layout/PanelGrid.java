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

package net.bootsfaces.layout;

import java.io.IOException;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.render.A;

/**
 * This component puts child components in a grid, very much like &lt;h:panelGrid&gt;.
 *
 * <p>
 * Usage:
 * </p>
 * <p>
 * &lt;b:panelGrid id="grid-three-nine" colSpans="3,9" columnClasses="red,green" styleClass="gridStyle" rowClasses="light,dark"&gt;<br>
 * &nbsp;&nbsp;&nbsp;(child components)<br>
 * &lt;/b:panelGrid&gt;
 * </p>
 * <ul>
 * <li>
 * id: the id of the component. It's added to the surrounding &lt;div class="container" &gt;.</li>
 * <li>
 * styleClass: The CSS class assigned to the surrounding &lt;div class="container" &gt;.</li>
 * <li>
 * colSpans: comma-separated list of the Bootstrap columns each components spans. The number are translated to col-lg-%colspan%. In the
 * example, the first component is assigned col-lg-3, the second col-lg-9. The next row start with col-lg-3 again. The column spans must sum
 * up to 12.</li>
 * <li>
 * columnClasses: An optional attribute that allows to assign different CSS classes to each Bootstrap column. If "colSpans" defines more
 * columns than "columnClasses", the columnClasses are repeated cyclically.</li>
 * <li>
 * rowClasses: An optional attribute that allows to assign CSS classes to each Bootstrap row. If there are more rows than row classes, the
 * row classes are repeated cyclically.</li>
 * </ul>
 * 
 * @author Stephan Rauh, http://www.beyondjava.net
 * @since 22.11.2014
 */

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head") })
@FacesComponent(C.PANELGRID_COMPONENT_TYPE)
public class PanelGrid extends UIOutput {

	/**
	 * The standard component type for this component.
	 */
	public static final String COMPONENT_TYPE = C.PANELGRID_COMPONENT_TYPE;

	/**
	 * The component family for this component.
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public PanelGrid() {
		setRendererType(null); // no renderer needed
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		// suppress generation of children
	}

	/**
	 * This component is responsible for rendering its children.
	 */
	@Override
	public boolean getRendersChildren() {
		return true;
	}

	/** Renders the grid component and its children. */
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
		ResponseWriter writer = context.getResponseWriter();

		generateContainerStart(writer);

		int[] columns = getColSpanArray();
		String[] columnClasses = getColumnClasses(columns);
		String[] rowClasses = getRowClasses(this);

		int row = 0;
		int column = 0;
		List<UIComponent> children = this.getChildren();
		for (UIComponent child : children) {
			if (0 == column) {
				generateRowStart(writer, row, rowClasses);
			}
			generateColumnStart(child, columnClasses[column], writer);
			child.encodeAll(context);
			generateColumnEnd(writer, columns[column]);
			column++;
			if (column >= columns.length) {
				generateRowEnd(writer, row, rowClasses);
				column = 0;
				row++;
			}
		}

		generateContainerEnd(writer);

	}

	/**
	 * Extract the option row classes from the JSF file.
	 * 
	 * @return null or a String array.
	 */
	protected String[] getRowClasses(PanelGrid grid) {
		String rowClasses = A.asString(grid.getAttributes().get("rowClasses"));
		if (null == rowClasses || rowClasses.trim().length()==0)
			return null;
		String[] rows = rowClasses.split(",");
		return rows;
	}

	/**
	 * Read the colSpans attribute.
	 * 
	 * @return a integer array
	 * @throws FacesException
	 *             if the attribute is missing or invalid.
	 */
	protected int[] getColSpanArray() {
		String columnsCSV = A.asString(getAttributes().get("colSpans"));
		if (null == columnsCSV || columnsCSV.trim().length()==0)
		{
		    throw new FacesException("PanelGrid.colSpans attribute: Please provide a comma-separated list of integer values");
		}
		String[] columnList = columnsCSV.split(",");
		int[] columns = new int[columnList.length];
		int sum = 0;
		for (int i = 0; i < columnList.length; i++) {
			try {
				columns[i] = (int) Integer.valueOf(columnList[i]);
				sum += columns[i];
			} catch (NumberFormatException error) {
				throw new FacesException("PanelGrid.colSpans attribute: the list has to consists of integer values");
			}
		}
		if (sum != 12) {
			throw new FacesException("PanelGrid.colSpans attribute: The columns don't add up to 12");
		}
		return columns;
	}

	/**
	 * Merge the column span information and the optional columnClasses attribute.
	 * 
	 * @param colSpans
	 *            the integer array returned by getColSpans().
	 * @return null or an array of String consisting of the CSS classes.
	 */
	protected String[] getColumnClasses(int[] colSpans) {
		String columnsCSV = A.asString(getAttributes().get("columnClasses"));
		String[] columnClasses;

		if (null == columnsCSV || columnsCSV.trim().length()==0)
			columnClasses = null;
		else {
			columnClasses = columnsCSV.split(",");
			if (columnClasses.length > colSpans.length) {
				throw new FacesException("PanelGrid: You defined more columnClasses than columns or colSpans");
			}
		}

		String size = A.asString(getAttributes().get("size"));
		if (null == size || size.trim().equals(""))
			size = "lg";

		String[] result = new String[colSpans.length];

		for (int i = 0; i < colSpans.length; i++) {
			if (columnClasses == null) {
				result[i] = "col-" + size + "-" + colSpans[i];
			} else {
				String current = columnClasses[i % columnClasses.length];
				if (current.contains("col-")) {
					result[i] = current;
				} else {
					result[i] = "col-" + size + "-" + colSpans[i] + " " + current;
				}

			}
		}
		return result;
	}

	/**
	 * Generates the end of the Bootstrap column.
	 * 
	 * @param writer
	 *            the current response writer.
	 * @param span
	 *            the column span of the current column.
	 * @throws IOException
	 *             if something's wrong with the response writer.
	 */
	protected void generateColumnEnd(ResponseWriter writer, int span) throws IOException {
		writer.endElement("div");
	}

	/**
	 * Generates the end of the Bootstrap rows.
	 * 
	 * @param writer
	 *            the current response writer.
	 * @param row
	 *            the current row index
	 * @param rowClasses
	 *            the array of CSS classes
	 * @throws IOException
	 *             if something's wrong with the response writer.
	 */
	protected void generateRowEnd(ResponseWriter writer, int row, String[] rowClasses) throws IOException {
		writer.endElement("div");
	}

	/**
	 * Generated the end of the entire Bootstrap container.
	 * 
	 * @param writer
	 *            the current response writer.
	 * @throws IOException
	 *             if something's wrong with the response writer.
	 */
	protected void generateContainerEnd(ResponseWriter writer) throws IOException {
		writer.endElement("div");
	}

	/**
	 * Generates the start of each Bootstrap column.
	 * 
	 * @param child
	 *            the child component to be drawn within the column.
	 * @param colStyleClass
	 *            the current CSS style class
	 * @param writer
	 *            the current response writer.
	 * @throws IOException
	 *             if something's wrong with the response writer.
	 */
	protected void generateColumnStart(UIComponent child, String colStyleClass, ResponseWriter writer) throws IOException {
		writer.startElement("div", child);
		writer.writeAttribute("class", colStyleClass, "class");
	}

	/**
	 * Generates the start of each Bootstrap row.
	 * 
	 * @param writer
	 *            the current response writer.
	 * @param row
	 *            the current row index
	 * @param rowClasses
	 *            the array of row classes
	 * @throws IOException
	 *             if something's wrong with the response writer.
	 */
	protected void generateRowStart(ResponseWriter writer, int row, String[] rowClasses) throws IOException {
		writer.startElement("div", this);
		if (null == rowClasses)
			writer.writeAttribute("class", "row", "class");
		else
			writer.writeAttribute("class", "row " + rowClasses[row % rowClasses.length], "class");
	}

	/**
	 * Generates the start of the entire Bootstrap container.
	 * 
	 * @param writer
	 *            the current response writer.
	 * @throws IOException
	 *             if something's wrong with the response writer.
	 */
	protected void generateContainerStart(ResponseWriter writer) throws IOException {
		writer.startElement("div", this);

		String id = this.getId();
		if (null != id) {
			String clientId = this.getClientId();
			writer.writeAttribute("id", clientId, "id");
		}

		String styleclass = A.asString(this.getAttributes().get("styleClass"));
		if (null != styleclass&& styleclass.trim().length()>0)
			writer.writeAttribute("class", styleclass, "class");
//		else
//			writer.writeAttribute("class", "container", "class");

		String style = A.asString(this.getAttributes().get("style"));
		if (null != style && style.trim().length()>0)
			writer.writeAttribute("style", style, "style");
	}
}
