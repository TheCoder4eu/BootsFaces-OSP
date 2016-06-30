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

package net.bootsfaces.component.dataTable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:dataTable /&gt;. */
@ResourceDependencies({
		@ResourceDependency(library = "bsf", name = "js/datatables.min.js", target = "body"),
//		@ResourceDependency(library = "bsf", name = "js/datatables-bf-extensions.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "css/datatables.min.css", target = "head") })
@FacesComponent("net.bootsfaces.component.dataTable.DataTable")
public class DataTable extends DataTableCore
		implements IAJAXComponent, ClientBehaviorHolder, net.bootsfaces.render.IHasTooltip, IResponsive {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.dataTable.DataTable";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dataTable.DataTable";

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click",
			"dblclick", "dragstart", "dragover", "drop", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup"));

	/** This map is used to store the column sort information gathered during rendering. */
	private Map<Integer, String> columnSortOrder;

	/** This array ist used to store the column information bits that are used to initialize the columns using the
	 * columns attribute of datatables.net */
	private List<String> columnInfo;

	public enum DataTablePropertyType {
		pageLength,
		searchTerm,
		currentPage
	}

	public DataTable() {
		setRendererType(DEFAULT_RENDERER);
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).
	 *
	 * @return
	 */
	public Map<String, String> getJQueryEvents() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("order", "order.dt");
		result.put("page", "page.dt");
		result.put("search", "search.dt");
		result.put("select", "select.dt");
		result.put("deselect", "deselect.dt");
		return result;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * This map contains all of the default sorting for each column.
	 * This map is used to store the column sort information gathered during rendering.
	 *
	 * @return The map containing the column / sort type pairs
	 */
	public Map<Integer, String> getColumnSortOrderMap() {
		return columnSortOrder;
	}

	/**
	 * Called in order to lazily initialize the map.
	 * This map is used to store the column sort information gathered during rendering.
	 */
	public void initColumnSortOrderMap() {
		this.columnSortOrder = new HashMap<Integer, String>();
	}

	/** This array ist used to store the column information bits that are used to initialize the columns using the
	 * columns attribute of datatables.net */
	public List<String> getColumnInfo() {
		return columnInfo;
	}

	/** This array ist used to store the column information bits that are used to initialize the columns using the
	 * columns attribute of datatables.net */
	public void setColumnInfo(List<String> columnInfo) {
		this.columnInfo = columnInfo;
	}
}
