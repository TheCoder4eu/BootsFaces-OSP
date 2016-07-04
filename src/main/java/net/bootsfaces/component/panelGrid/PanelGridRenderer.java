/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.panelGrid;

import java.io.IOException;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;


/** This class generates the HTML code of &lt;b:panelGrid /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.panelGrid.PanelGrid")
public class PanelGridRenderer extends CoreRenderer {

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
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
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        PanelGrid panelGrid = (PanelGrid) component;
		ResponseWriter writer = context.getResponseWriter();

		String responsiveStyle= Responsive.getResponsiveStyleClass(panelGrid, false);
		if (null != responsiveStyle) {
			writer.startElement("div", panelGrid);
			writer.writeAttribute("class", responsiveStyle.trim(), null);
		}
		generateContainerStart(writer, panelGrid);

		int[] columns = getColSpanArray(panelGrid);
		String[] columnClasses = getColumnClasses(panelGrid, columns);
		String[] rowClasses = getRowClasses(panelGrid);

		int row = 0;
		int column = 0;
		List<UIComponent> children = panelGrid.getChildren();
		for (UIComponent child : children) {
			if (0 == column) {
				generateRowStart(writer, row, rowClasses, panelGrid);
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
		if (null != responsiveStyle) {
			writer.endElement("div");
		}
        Tooltip.activateTooltips(context, panelGrid);
	}

	/**
	 * Extract the option row classes from the JSF file.
	 *
	 * @return null or a String array.
	 */
	protected String[] getRowClasses(PanelGrid grid) {
		String rowClasses = grid.getRowClasses();
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
	protected int[] getColSpanArray(PanelGrid panelGrid) {
		String columnsCSV = panelGrid.getColSpans();
		if (null == columnsCSV || columnsCSV.trim().length()==0)
		{
		    columnsCSV = panelGrid.getColumns();
		    if ("1".equals(columnsCSV)) {
		    	columnsCSV = "12";
		    } else if ("2".equals(columnsCSV)) {
		    	columnsCSV = "6,6";
		    } else if ("3".equals(columnsCSV)) {
		    	columnsCSV = "4,4,4";
		    } else if ("4".equals(columnsCSV)) {
		    	columnsCSV = "3,3,3,3";
		    } else if ("6".equals(columnsCSV)) {
		    	columnsCSV = "2,2,2,2,2,2";
		    } else if ("12".equals(columnsCSV)) {
		    	columnsCSV = "1,1,1,1,1,1,1,1,1,1,1,1";
		    } else {
		    	throw new FacesException("PanelGrid.columns attribute: Legal values are 1, 2, 3, 4, 6 and 12. If you need a different number of columns, please use the attribute 'col-spans'.");
		    }
		}
		if (null == columnsCSV || columnsCSV.trim().length()==0)
		{
		    throw new FacesException("PanelGrid.colSpans attribute: Please provide a comma-separated list of integer values");
		}
		String[] columnList = columnsCSV.replaceAll(" ", "").split(",");
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
	protected String[] getColumnClasses(PanelGrid panelGrid, int[] colSpans) {
		String columnsCSV = panelGrid.getColumnClasses();
		String[] columnClasses;

		if (null == columnsCSV || columnsCSV.trim().length()==0)
			columnClasses = null;
		else {
			columnClasses = columnsCSV.split(",");
			if (columnClasses.length > colSpans.length) {
				throw new FacesException("PanelGrid: You defined more columnClasses than columns or colSpans");
			}
		}

		String size = panelGrid.getSize();
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
	protected void generateRowStart(ResponseWriter writer, int row, String[] rowClasses, PanelGrid panelGrid) throws IOException {
		writer.startElement("div", panelGrid);
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
	protected void generateContainerStart(ResponseWriter writer, PanelGrid panelGrid) throws IOException {
		writer.startElement("div", panelGrid);

		String id = panelGrid.getId();
		if (null != id) {
			String clientId = panelGrid.getClientId();
			writer.writeAttribute("id", clientId, "id");
		}
		writeAttribute(writer, "dir", panelGrid.getDir(), "dir");

        Tooltip.generateTooltip(FacesContext.getCurrentInstance(), panelGrid, writer);


		String styleclass = panelGrid.getStyleClass();
		if (null != styleclass&& styleclass.trim().length()>0)
			writer.writeAttribute("class", styleclass, "class");
//		else
//			writer.writeAttribute("class", "container", "class");

		String style = panelGrid.getStyle();
		if (null != style && style.trim().length()>0)
			writer.writeAttribute("style", style, "style");
	}
}
