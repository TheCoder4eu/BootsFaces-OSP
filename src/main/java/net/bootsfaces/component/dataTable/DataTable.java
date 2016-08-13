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

package net.bootsfaces.component.dataTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIData;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.dataTableColumn.DataTableColumn;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:dataTable /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/datatables.min.js", target = "body"),
		// @ResourceDependency(library = "bsf", name =
		// "js/datatables-bf-extensions.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "css/datatables.min.css", target = "head") })
@FacesComponent("net.bootsfaces.component.dataTable.DataTable")
public class DataTable extends DataTableCore
		implements IAJAXComponent, ClientBehaviorHolder, net.bootsfaces.render.IHasTooltip, IResponsive {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.dataTable.DataTable";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dataTable.DataTable";

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click",
			"dblclick", "dragstart", "dragover", "drop", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup"));

	/**
	 * This map is used to store the column sort information gathered during
	 * rendering.
	 */
	private Map<Integer, String> columnSortOrder;

	/**
	 * This array ist used to store the column information bits that are used to
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
	 * This map contains all of the default sorting for each column. This map is
	 * used to store the column sort information gathered during rendering.
	 *
	 * @return The map containing the column / sort type pairs
	 */
	public Map<Integer, String> getColumnSortOrderMap() {
		return columnSortOrder;
	}

	/**
	 * Called in order to lazily initialize the map. This map is used to store
	 * the column sort information gathered during rendering.
	 */
	public void initColumnSortOrderMap() {
		this.columnSortOrder = new HashMap<Integer, String>();
	}

	/**
	 * This array ist used to store the column information bits that are used to
	 * initialize the columns using the columns attribute of datatables.net
	 */
	public List<String> getColumnInfo() {
		return columnInfo;
	}

	/**
	 * This array ist used to store the column information bits that are used to
	 * initialize the columns using the columns attribute of datatables.net
	 */
	public void setColumnInfo(List<String> columnInfo) {
		this.columnInfo = columnInfo;
	}

	/**
	 * <p>
	 * Flag indicating whether or not this UIData instance is nested within
	 * another UIData instance
	 * </p>
	 *
	 * <p>
	 * This is not part of the component state.
	 * </p>
	 */
	private Boolean isNested = null;

	enum PKFromUIData {
		/**
		 * <p>
		 * This map contains <code>SavedState</code> instances for each
		 * descendant component, keyed by the client identifier of the
		 * descendant. Because descendant client identifiers will contain the
		 * <code>rowIndex</code> value of the parent, per-row state information
		 * is actually preserved.
		 * </p>
		 */
		saved
	}

	/**
	 * <p>
	 * Override the default {@link UIComponentBase#processDecodes} processing to
	 * perform the following steps.
	 * </p>
	 * <ul>
	 * <li>If the <code>rendered</code> property of this {@link UIComponent} is
	 * <code>false</code>, skip further processing.</li>
	 * <li>Set the current <code>rowIndex</code> to -1.</li>
	 * <li>Call the <code>processDecodes()</code> method of all facets of this
	 * {@link UIData}, in the order determined by a call to
	 * <code>getFacets().keySet().iterator()</code>.</li>
	 * <li>Call the <code>processDecodes()</code> method of all facets of the
	 * {@link UIColumn} children of this {@link UIData}.</li>
	 * <li>Iterate over the set of rows that were included when this component
	 * was rendered (i.e. those defined by the <code>first</code> and
	 * <code>rows</code> properties), performing the following processing for
	 * each row:
	 * <ul>
	 * <li>Set the current <code>rowIndex</code> to the appropriate value for
	 * this row.</li>
	 * <li>If <code>isRowAvailable()</code> returns <code>true</code>, iterate
	 * over the children components of each {@link UIColumn} child of this
	 * {@link UIData} component, calling the <code>processDecodes()</code>
	 * method for each such child.</li>
	 * </ul>
	 * </li>
	 * <li>Set the current <code>rowIndex</code> to -1.</li>
	 * <li>Call the <code>decode()</code> method of this component.</li>
	 * <li>If a <code>RuntimeException</code> is thrown during decode
	 * processing, call {@link FacesContext#renderResponse} and re-throw the
	 * exception.</li>
	 * </ul>
	 *
	 * @param context
	 *            {@link FacesContext} for the current request
	 *
	 * @throws NullPointerException
	 *             if <code>context</code> is <code>null</code>
	 */
	public void processDecodes(FacesContext context) {

		if (context == null) {
			throw new NullPointerException();
		}
		if (!isRendered()) {
			return;
		}

		pushComponentToEL(context, this);
		preDecode(context);
		iterate(context, PhaseId.APPLY_REQUEST_VALUES);
		decode(context);
		popComponentFromEL(context);
	}

	// Perform pre-decode initialization work. Note that this
	// initialization may be performed either during a normal decode
	// (ie. processDecodes()) or during a tree visit (ie. visitTree()).
	private void preDecode(FacesContext context) {
		setDataModel(null); // Re-evaluate even with server-side state saving
		/**
		 * Properties that are tracked by state saving.
		 */

		Object saved = getStateHelper().get(PKFromUIData.saved);
		if (null == saved || !keepSaved(context)) {
			// noinspection CollectionWithoutInitialCapacity
			getStateHelper().remove(PKFromUIData.saved);
		}
	}

	/**
	 * <p>
	 * Perform the appropriate phase-specific processing and per-row iteration
	 * for the specified phase, as follows:
	 * <ul>
	 * <li>Set the <code>rowIndex</code> property to -1, and process the facets
	 * of this {@link UIData} component exactly once.</li>
	 * <li>Set the <code>rowIndex</code> property to -1, and process the facets
	 * of the {@link UIColumn} children of this {@link UIData} component exactly
	 * once.</li>
	 * <li>Iterate over the relevant rows, based on the <code>first</code> and
	 * <code>row</code> properties, and process the children of the
	 * {@link UIColumn} children of this {@link UIData} component once per
	 * row.</li>
	 * </ul>
	 *
	 * @param context
	 *            {@link FacesContext} for the current request
	 * @param phaseId
	 *            {@link PhaseId} of the phase we are currently running
	 */
	private void iterate(FacesContext context, PhaseId phaseId) {

		// Process each facet of this component exactly once
		setRowIndex(-1);
		if (getFacetCount() > 0) {
			for (UIComponent facet : getFacets().values()) {
				if (phaseId == PhaseId.APPLY_REQUEST_VALUES) {
					facet.processDecodes(context);
				} else if (phaseId == PhaseId.PROCESS_VALIDATIONS) {
					facet.processValidators(context);
				} else if (phaseId == PhaseId.UPDATE_MODEL_VALUES) {
					facet.processUpdates(context);
				} else {
					throw new IllegalArgumentException();
				}
			}
		}

		// collect rendered columns once
		List<UIComponent> renderedColumns = new ArrayList<UIComponent>(getChildCount());
		if (getChildCount() > 0) {
			for (UIComponent child : getChildren()) {
				if (child instanceof UIColumn && child.isRendered()) {
					renderedColumns.add(child);
				} else if (child instanceof DataTableColumn && child.isRendered()) {
					renderedColumns.add(child);
				}
			}
		}

		// Process each facet of our child UIColumn components exactly once
		setRowIndex(-1);
		for (UIComponent column : renderedColumns) {
			if (column.getFacetCount() > 0) {
				
				for (UIComponent columnFacet : column.getFacets().values()) {
					resetClientIdCacheRecursively(columnFacet);					
					if (phaseId == PhaseId.APPLY_REQUEST_VALUES) {
						columnFacet.processDecodes(context);
					} else if (phaseId == PhaseId.PROCESS_VALIDATIONS) {
						columnFacet.processValidators(context);
					} else if (phaseId == PhaseId.UPDATE_MODEL_VALUES) {
						columnFacet.processUpdates(context);
					} else {
						throw new IllegalArgumentException();
					}
				}
			}
		}

		// Iterate over our UIColumn children, once per row
		int processed = 0;
		int rowIndex = getFirst() - 1;
		int rows = getRows();

		while (true) {

			// Have we processed the requested number of rows?
			if ((rows > 0) && (++processed > rows)) {
				break;
			}

			// Expose the current row in the specified request attribute
			setRowIndex(++rowIndex);
			if (!isRowAvailable()) {
				break; // Scrolled past the last row
			}

			// Perform phase-specific processing as required
			// on the *children* of the UIColumn (facets have
			// been done a single time with rowIndex=-1 already)
			for (UIComponent kid : renderedColumns) {
				if (kid.getChildCount() > 0) {
					for (UIComponent grandkid : kid.getChildren()) {
						if (!grandkid.isRendered()) {
							continue;
						}
						resetClientIdCacheRecursively(grandkid);
						if (phaseId == PhaseId.APPLY_REQUEST_VALUES) {
							grandkid.processDecodes(context);
						} else if (phaseId == PhaseId.PROCESS_VALIDATIONS) {
							grandkid.processValidators(context);
						} else if (phaseId == PhaseId.UPDATE_MODEL_VALUES) {
							grandkid.processUpdates(context);
						} else {
							throw new IllegalArgumentException();
						}
					}
				}
			}

		}

		// Clean up after ourselves
		setRowIndex(-1);

	}

	/**
	 * <p>
	 * Return <code>true</code> if we need to keep the saved per-child state
	 * information. This will be the case if any of the following are true:
	 * </p>
	 *
	 * <ul>
	 *
	 * <li>there are messages queued with severity ERROR or FATAL.</li>
	 *
	 * <li>this <code>UIData</code> instance is nested inside of another
	 * <code>UIData</code> instance</li>
	 *
	 * </ul>
	 *
	 * @param context
	 *            {@link FacesContext} for the current request
	 */
	private boolean keepSaved(FacesContext context) {

		return (contextHasErrorMessages(context) || isNestedWithinIterator());

	}

	private Boolean isNestedWithinIterator() {
		if (isNested == null) {
			UIComponent parent = this;
			while (null != (parent = parent.getParent())) {
				if (parent instanceof UIData || parent.getClass().getName().endsWith("UIRepeat")) {
					isNested = Boolean.TRUE;
					break;
				}
			}
			if (isNested == null) {
				isNested = Boolean.FALSE;
			}
			return isNested;
		} else {
			return isNested;
		}
	}

	private boolean contextHasErrorMessages(FacesContext context) {

		FacesMessage.Severity sev = context.getMaximumSeverity();
		return (sev != null && (FacesMessage.SEVERITY_ERROR.compareTo(sev) >= 0));

	}
	
	private void resetClientIdCacheRecursively(UIComponent c) {
		String id=c.getId();
		if (null != id) {
			c.setId(id); // this strange operation clears the cache of the clientId
		}
		Iterator<UIComponent> children = c.getFacetsAndChildren();
		if (children != null) {
			while (children.hasNext()) {
				UIComponent kid = children.next();
				resetClientIdCacheRecursively(kid);
			}
		}
	}


}
