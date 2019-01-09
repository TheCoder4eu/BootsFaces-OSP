/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

import javax.faces.component.UIColumn;

/** This class holds the attributes of &lt;b:dataTableColumn /&gt;. */
public abstract class DataTableColumnCore extends UIColumn {

	protected enum PropertyKeys {
		contentStyle, contentStyleClass, customOptions, dataOrder, dataSearch, dataType, footerStyle, footerStyleClass,
		headerStyle, headerStyleClass, label, labelStyle, labelStyleClass, order, orderBy, orderable, searchValue,
		searchable, selectionMode, style, styleClass, width;
		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	/**
	 * Inline style of the cells in the content area. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentStyle() {
		return (String) getStateHelper().eval(PropertyKeys.contentStyle);
	}

	/**
	 * Inline style of the cells in the content area. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentStyle(String _contentStyle) {
		getStateHelper().put(PropertyKeys.contentStyle, _contentStyle);
	}

	/**
	 * Style class of cells in the content area.. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.contentStyleClass);
	}

	/**
	 * Style class of cells in the content area.. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentStyleClass(String _contentStyleClass) {
		getStateHelper().put(PropertyKeys.contentStyleClass, _contentStyleClass);
	}

	/**
	 * Allows you to pass an arbitrary option to the datatable widget. Separate the options by a comma if you pass more than one. Note that this may cause incompatibilities when the next version of BootsFaces is released. Use at own risk. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getCustomOptions() {
		return (String) getStateHelper().eval(PropertyKeys.customOptions);
	}

	/**
	 * Allows you to pass an arbitrary option to the datatable widget. Separate the options by a comma if you pass more than one. Note that this may cause incompatibilities when the next version of BootsFaces is released. Use at own risk. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCustomOptions(String _customOptions) {
		getStateHelper().put(PropertyKeys.customOptions, _customOptions);
	}

	/**
	 * Allows you to specify a value for ordering. Useful i.E. for ordering formatted values. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDataOrder() {
		return (String) getStateHelper().eval(PropertyKeys.dataOrder);
	}

	/**
	 * Allows you to specify a value for ordering. Useful i.E. for ordering formatted values. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDataOrder(String _dataOrder) {
		getStateHelper().put(PropertyKeys.dataOrder, _dataOrder);
	}

	/**
	 * Allows you to specify a value for searching. The search doesn't consider the real content of this column. Instead, it considers the value of the data-search attribute. Useful i.E. for searching formatted values. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDataSearch() {
		return (String) getStateHelper().eval(PropertyKeys.dataSearch);
	}

	/**
	 * Allows you to specify a value for searching. The search doesn't consider the real content of this column. Instead, it considers the value of the data-search attribute. Useful i.E. for searching formatted values. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDataSearch(String _dataSearch) {
		getStateHelper().put(PropertyKeys.dataSearch, _dataSearch);
	}

	/**
	 * Specifies order-by more precisely. Is also used by the filtering methods. Legal values are 'string', 'date', 'numeric'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDataType() {
		return (String) getStateHelper().eval(PropertyKeys.dataType);
	}

	/**
	 * Specifies order-by more precisely. Is also used by the filtering methods. Legal values are 'string', 'date', 'numeric'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDataType(String _dataType) {
		getStateHelper().put(PropertyKeys.dataType, _dataType);
	}

	/**
	 * Inline style of the footer cell. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFooterStyle() {
		return (String) getStateHelper().eval(PropertyKeys.footerStyle);
	}

	/**
	 * Inline style of the footer cell. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFooterStyle(String _footerStyle) {
		getStateHelper().put(PropertyKeys.footerStyle, _footerStyle);
	}

	/**
	 * Style class of this footer cell. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFooterStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.footerStyleClass);
	}

	/**
	 * Style class of this footer cell. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFooterStyleClass(String _footerStyleClass) {
		getStateHelper().put(PropertyKeys.footerStyleClass, _footerStyleClass);
	}

	/**
	 * Inline style of the header cell. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHeaderStyle() {
		return (String) getStateHelper().eval(PropertyKeys.headerStyle);
	}

	/**
	 * Inline style of the header cell. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHeaderStyle(String _headerStyle) {
		getStateHelper().put(PropertyKeys.headerStyle, _headerStyle);
	}

	/**
	 * Style class of this header cell. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHeaderStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.headerStyleClass);
	}

	/**
	 * Style class of this header cell. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHeaderStyleClass(String _headerStyleClass) {
		getStateHelper().put(PropertyKeys.headerStyleClass, _headerStyleClass);
	}

	/**
	 * Label in the header of the colum. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		return (String) getStateHelper().eval(PropertyKeys.label);
	}

	/**
	 * Label in the header of the colum. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
		getStateHelper().put(PropertyKeys.label, _label);
	}

	/**
	 * The CSS inline style of the label. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabelStyle() {
		return (String) getStateHelper().eval(PropertyKeys.labelStyle);
	}

	/**
	 * The CSS inline style of the label. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelStyle(String _labelStyle) {
		getStateHelper().put(PropertyKeys.labelStyle, _labelStyle);
	}

	/**
	 * The CSS class of the label. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabelStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.labelStyleClass);
	}

	/**
	 * The CSS class of the label. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabelStyleClass(String _labelStyleClass) {
		getStateHelper().put(PropertyKeys.labelStyleClass, _labelStyleClass);
	}

	/**
	 * Is the table to be sorted by this column? Legal values are 'asc' and 'desc'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOrder() {
		return (String) getStateHelper().eval(PropertyKeys.order);
	}

	/**
	 * Is the table to be sorted by this column? Legal values are 'asc' and 'desc'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOrder(String _order) {
		getStateHelper().put(PropertyKeys.order, _order);
	}

	/**
	 * Allows you to sort input field. Legal values are dom-text, dom-text-numeric, dom-select and dom-checkbox. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOrderBy() {
		return (String) getStateHelper().eval(PropertyKeys.orderBy);
	}

	/**
	 * Allows you to sort input field. Legal values are dom-text, dom-text-numeric, dom-select and dom-checkbox. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOrderBy(String _orderBy) {
		getStateHelper().put(PropertyKeys.orderBy, _orderBy);
	}

	/**
	 * Disables or enables the sort button for this column. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isOrderable() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.orderable, true);
	}

	/**
	 * Disables or enables the sort button for this column. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOrderable(boolean _orderable) {
		getStateHelper().put(PropertyKeys.orderable, _orderable);
	}

	/**
	 * Initial content of the search filter field. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSearchValue() {
		return (String) getStateHelper().eval(PropertyKeys.searchValue);
	}

	/**
	 * Initial content of the search filter field. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSearchValue(String _searchValue) {
		getStateHelper().put(PropertyKeys.searchValue, _searchValue);
	}

	/**
	 * If set to false, this column is excluded from the multi-column-search feature of b:dataTable. Defaults to true. Note that this feature is active only if both searching='true' and multi-column-search='true' are set on the datatable. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isSearchable() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.searchable, true);
	}

	/**
	 * If set to false, this column is excluded from the multi-column-search feature of b:dataTable. Defaults to true. Note that this feature is active only if both searching='true' and multi-column-search='true' are set on the datatable. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSearchable(boolean _searchable) {
		getStateHelper().put(PropertyKeys.searchable, _searchable);
	}

	/**
	 * Add a checkbox column to select rows. Legal values are 'single' and 'multi'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSelectionMode() {
		return (String) getStateHelper().eval(PropertyKeys.selectionMode);
	}

	/**
	 * Add a checkbox column to select rows. Legal values are 'single' and 'multi'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectionMode(String _selectionMode) {
		getStateHelper().put(PropertyKeys.selectionMode, _selectionMode);
	}

	/**
	 * Inline style of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline style of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of this element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of this element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Width of the column. If you don't specify the unit, BootsFaces assumes you want to use 'px'. You can use the other units by adding them to the value, such as '50%', '5em', '16vw', etc. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWidth() {
		return (String) getStateHelper().eval(PropertyKeys.width);
	}

	/**
	 * Width of the column. If you don't specify the unit, BootsFaces assumes you want to use 'px'. You can use the other units by adding them to the value, such as '50%', '5em', '16vw', etc. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidth(String _width) {
		getStateHelper().put(PropertyKeys.width, _width);
	}

}
