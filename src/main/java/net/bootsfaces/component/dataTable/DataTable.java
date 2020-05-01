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

package net.bootsfaces.component.dataTable;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIViewRoot;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesEvent;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:dataTable /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(DataTable.COMPONENT_TYPE)
public class DataTable extends DataTableCore implements IAJAXComponent, IAJAXComponent2, ClientBehaviorHolder,
		net.bootsfaces.render.IHasTooltip, IResponsive, IContentDisabled {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".dataTable.DataTable";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dataTable.DataTable";

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click",
			"dblclick", "dragstart", "dragover", "drop", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup"));

	/**
	 * This map is used to store the column sort information gathered during
	 * rendering.
	 */
	private Map<Integer, String> columnSortOrder;

	/**
	 * This array is used to store the columnDef property used to
	 * initialize the columns using the columns attribute of datatables.net
	 */
	private List<String> columnDefinition;
	
	/**
	 * internal attribute used for b:dataTableColumn selectionMode="multiple"
	 */
	private String selectionMode2;
	
	/**
	 * This array is used to store the column information bits that are used to
	 * initialize the columns using the columns attribute of datatables.net
	 */
	private List<String> columnInfo;

	public enum DataTablePropertyType {
		pageLength, searchTerm, currentPage
	}

	public DataTable() {
		setRendererType(DEFAULT_RENDERER);
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		// DataTables with almost all extensions except Flash export and KeyTable - version of 10.11.2019
		AddResourcesListener.addDatatablesResourceIfNecessary("https://cdn.datatables.net/v/bs/jszip-2.5.0/dt-1.10.20/af-2.3.4/b-1.6.1/b-colvis-1.6.1/b-html5-1.6.1/b-print-1.6.1/cr-1.5.2/fc-3.3.0/fh-3.1.6/r-2.2.3/rg-1.1.1/rr-1.2.6/sc-2.0.1/sl-1.3.1/datatables.min.css", "css"); 
		AddResourcesListener.addDatatablesResourceIfNecessary("https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js", "css");
		AddResourcesListener.addDatatablesResourceIfNecessary("https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js", "css");
		AddResourcesListener.addDatatablesResourceIfNecessary("https://cdn.datatables.net/v/bs/jszip-2.5.0/dt-1.10.20/af-2.3.4/b-1.6.1/b-colvis-1.6.1/b-html5-1.6.1/b-print-1.6.1/cr-1.5.2/fc-3.3.0/fh-3.1.6/r-2.2.3/rg-1.1.1/rr-1.2.6/sc-2.0.1/sl-1.3.1/datatables.min.js", "js");
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
	 * returns the subset of AJAX requests that are implemented by jQuery callback
	 * or other non-standard means (such as the onclick event of b:tabView, which
	 * has to be implemented manually).
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

	/**
	 * Returns the parameter list of jQuery and other non-standard JS callbacks. If
	 * there's no parameter list for a certain event, the default is simply "event".
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterLists() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("select", "event, datatable, typeOfSelection, indexes");
		result.put("deselect", "event, datatable, typeOfSelection, indexes");
		return result;
	}

	/**
	 * Returns the subset of the parameter list of jQuery and other non-standard JS
	 * callbacks which is sent to the server via AJAX. If there's no parameter list
	 * for a certain event, the default is simply null.
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterListsForAjax() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("select", "'typeOfSelection':typeOfSelection,'indexes':indexes");
		result.put("deselect", "'typeOfSelection':typeOfSelection,'indexes':indexes");
		return result;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}

	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
 	 		super.processEvent(event);
 	 	}
		List<UIComponent> columns = getChildren();
		for (UIComponent column : columns) {
			if (column.getAttributes().get("selectionMode") != null) {
				String selectionMode = (String) column.getAttributes().get("selectionMode");
				if ("multiple".equals(selectionMode)) {
					importCheckboxColumnLib();
				}
			}
		}


	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * This map contains all of the default sorting for each column. This map is
	 * used to store the column sort information gathered during rendering.
	 *
	 * @return The map containing the column / sort type pairs
	 */
	public Map<Integer, String> getColumnSortOrderMap() {
		return columnSortOrder;
	}

	/**
	 * Called in order to lazily initialize the map. This map is used to store the
	 * column sort information gathered during rendering.
	 */
	public void initColumnSortOrderMap() {
		this.columnSortOrder = new HashMap<Integer, String>();
	}
	
	/**
	 * This array is used to store the columnDef attribute used to
	 * initialize the columns using the columns attribute of datatables.net
	 */
	public List<String> getColumnDefinition() {
		return columnDefinition;
	}

	/**
	 * This array is used to store the columnDef attribute used to
	 * initialize the columns using the columns attribute of datatables.net
	 */
	public void setColumnDefinition(List<String> columnDefinition) {
		this.columnDefinition = columnDefinition;
	}


	/**
	 * This array is used to store the column information bits that are used to
	 * initialize the columns using the columns attribute of datatables.net
	 */
	public List<String> getColumnInfo() {
		return columnInfo;
	}

	/**
	 * This array is used to store the column information bits that are used to
	 * initialize the columns using the columns attribute of datatables.net
	 */
	public void setColumnInfo(List<String> columnInfo) {
		this.columnInfo = columnInfo;
	}

	/**
	 * <p>
	 * Queue an event for broadcast at the end of the current request processing
	 * lifecycle phase. The default implementation in {@link UIComponentBase} must
	 * delegate this call to the <code>queueEvent()</code> method of the parent
	 * {@link UIComponent}.
	 * </p>
	 *
	 * @param event
	 *            {@link FacesEvent} to be queued
	 *
	 * @throws IllegalStateException
	 *             if this component is not a descendant of a {@link UIViewRoot}
	 * @throws NullPointerException
	 *             if <code>event</code> is <code>null</code>
	 */
	public void queueEvent(FacesEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		String indexes = (String) context.getExternalContext().getRequestParameterMap().get("indexes");
		context.getELContext().getELResolver().setValue(context.getELContext(), null, "indexes", indexes);
		String typeOfSelection = (String) context.getExternalContext().getRequestParameterMap().get("typeOfSelection");
		context.getELContext().getELResolver().setValue(context.getELContext(), null, "typeOfSelection",
				typeOfSelection);
		try {
			int oldIndex = getRowIndex();
			int index = Integer.valueOf(indexes);
			setRowIndex(index);
			super.queueEvent(event);
			setRowIndex(oldIndex);
		} catch (Exception multipleIndexes) {
			super.queueEvent(event);

		}
	}

	@Override
	public void setSelectedColumn(Object _selectedColumn) {
		super.setSelectedColumn(_selectedColumn);
		if (_selectedColumn != null) {
			setSelect(true);
			setSelectedItems("column");
		}
	}

	@Override
	public void setSelectedRow(Object _selectedRow) {
		super.setSelectedRow(_selectedRow);
		if (_selectedRow != null) {
			setSelect(true);
			setSelectedItems("row");
		}
	}

	@Override
	public void setSelectionMode(String _selectectionMode) {
		super.setSelectionMode(_selectectionMode);
		if (_selectectionMode != null) {
			setSelect(true);
		}
	}

	/**
	 * internal attribute used for b:dataTableColumn selectionMode="multiple"
	 */
	public String getSelectionMode2() {
		return selectionMode2;
	}

	/**
	 * internal attribute used for b:dataTableColumn selectionMode="multiple"
	 */
	public void setSelectionMode2(String selectionMode2) {
		this.selectionMode2 = selectionMode2;
	}

	@Override
	public void setSelectedItems(String _selectedItems) {
		super.setSelectedItems(_selectedItems);
		if (_selectedItems != null) {
			setSelect(true);
		}

	}
	
	/**
	 * If true, search results are marked yellow as you type. Based on mark.js (see https://datatables.net/blog/2017-01-19). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMarkSearchResults(boolean _markSearchResults) {
		if (_markSearchResults) {
			AddResourcesListener.addResourceIfNecessary("https://cdn.datatables.net/plug-ins/1.10.18/features/mark.js/datatables.mark.min.css");
			AddResourcesListener.addResourceIfNecessary("https://cdn.jsdelivr.net/g/mark.js(jquery.mark.min.js)");
			AddResourcesListener.addResourceIfNecessary("https://cdn.datatables.net/plug-ins/1.10.18/features/mark.js/datatables.mark.js");
		}
		super.setMarkSearchResults(_markSearchResults);
	}
	
	/**
	 * Group the rows by a common column value. Can be a number or a Json-object, as documented at https://datatables.net/reference/option/#rowgroup. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRowGroup(String _rowGroup) {
		AddResourcesListener.addResourceIfNecessary("https://cdn.datatables.net/rowgroup/1.1.0/js/dataTables.rowGroup.min.js");
		super.setRowGroup(_rowGroup);
	}
	
	void importCheckboxColumnLib() {
		AddResourcesListener.addBasicJSResource(C.BSF_LIBRARY, "js/dataTables.checkboxes.min.js");
		AddResourcesListener.addExtCSSResource("dataTables.checkboxes.css");
	}

}
