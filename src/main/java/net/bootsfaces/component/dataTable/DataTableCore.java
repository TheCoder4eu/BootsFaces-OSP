/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

import javax.faces.component.UIData;

/** This class holds the attributes of &lt;b:dataTable /&gt;. */
public abstract class DataTableCore extends UIData implements net.bootsfaces.render.IHasTooltip {

	protected enum PropertyKeys {
		ajax, autoUpdate, border, caption, colLg, colMd, colSm, colXs, columnVisibility, contentDisabled, copy, csv,
		customLangUrl, customOptions, delay, deselectOnBackdropClick, disabled, display, excel, fixedHeader, hidden,
		immediate, info, lang, largeScreen, lengthChange, markSearchResults, mediumScreen, multiColumnSearch,
		multiColumnSearchPosition, offset, offsetLg, offsetMd, offsetSm, offsetXs, onclick, oncomplete, ondblclick,
		ondeselect, onerror, onmousedown, onmousemove, onmouseout, onmouseover, onmouseup, onorder, onpage, onsearch,
		onselect, onsuccess, pageLength, pageLengthMenu, paginated, pdf, print, process, responsive, rowGroup,
		rowHighlight, rowStyleClass, saveState, scrollCollapse, scrollHorizontally, scrollSize, scrollX, searching,
		select, selectedColumn, selectedItems, selectedRow, selectionInfo, selectionMode, smallScreen, span, striped,
		style, styleClass, tinyScreen, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow,
		tooltipPosition, update, visible, widgetVar;
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
	 * Whether the Button submits the form with AJAX. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isAjax() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.ajax, false);
	}

	/**
	 * Whether the Button submits the form with AJAX. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAjax(boolean _ajax) {
		getStateHelper().put(PropertyKeys.ajax, _ajax);
	}

	/**
	 * Setting this flag updates the widget on every AJAX request. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isAutoUpdate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.autoUpdate, false);
	}

	/**
	 * Setting this flag updates the widget on every AJAX request. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAutoUpdate(boolean _autoUpdate) {
		getStateHelper().put(PropertyKeys.autoUpdate, _autoUpdate);
	}

	/**
	 * If set, this will surround the table by a border. Defaults to true. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isBorder() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.border, true);
	}

	/**
	 * If set, this will surround the table by a border. Defaults to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBorder(boolean _border) {
		getStateHelper().put(PropertyKeys.border, _border);
	}

	/**
	 * Optional attribute describing what the datatable is for. Usually, this attribute is used by screen readers to help the user to quickly grasp the intent of the table. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getCaption() {
		return (String) getStateHelper().eval(PropertyKeys.caption);
	}

	/**
	 * Optional attribute describing what the datatable is for. Usually, this attribute is used by screen readers to help the user to quickly grasp the intent of the table. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCaption(String _caption) {
		getStateHelper().put(PropertyKeys.caption, _caption);
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColLg() {
		return (String) getStateHelper().eval(PropertyKeys.colLg, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColLg(String _colLg) {
		getStateHelper().put(PropertyKeys.colLg, _colLg);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColMd() {
		return (String) getStateHelper().eval(PropertyKeys.colMd, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColMd(String _colMd) {
		getStateHelper().put(PropertyKeys.colMd, _colMd);
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColSm() {
		return (String) getStateHelper().eval(PropertyKeys.colSm, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColSm(String _colSm) {
		getStateHelper().put(PropertyKeys.colSm, _colSm);
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColXs() {
		return (String) getStateHelper().eval(PropertyKeys.colXs, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColXs(String _colXs) {
		getStateHelper().put(PropertyKeys.colXs, _colXs);
	}

	/**
	 * Adds a button allowing the user to show and hide of the columns. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isColumnVisibility() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.columnVisibility, false);
	}

	/**
	 * Adds a button allowing the user to show and hide of the columns. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColumnVisibility(boolean _columnVisibility) {
		getStateHelper().put(PropertyKeys.columnVisibility, _columnVisibility);
	}

	/**
	 * Enables or disables every child element of this container. By default, child elements are enabled. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isContentDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.contentDisabled, false);
	}

	/**
	 * Enables or disables every child element of this container. By default, child elements are enabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentDisabled(boolean _contentDisabled) {
		getStateHelper().put(PropertyKeys.contentDisabled, _contentDisabled);
	}

	/**
	 * Adds a 'copy to clipboard' button. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isCopy() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.copy, false);
	}

	/**
	 * Adds a 'copy to clipboard' button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCopy(boolean _copy) {
		getStateHelper().put(PropertyKeys.copy, _copy);
	}

	/**
	 * Adds a 'export CSV file' button. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isCsv() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.csv, false);
	}

	/**
	 * Adds a 'export CSV file' button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCsv(boolean _csv) {
		getStateHelper().put(PropertyKeys.csv, _csv);
	}

	/**
	 * Defines a custom lang file url for languages BootsFaces doesn't support out-of-the-box. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getCustomLangUrl() {
		return (String) getStateHelper().eval(PropertyKeys.customLangUrl);
	}

	/**
	 * Defines a custom lang file url for languages BootsFaces doesn't support out-of-the-box. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCustomLangUrl(String _customLangUrl) {
		getStateHelper().put(PropertyKeys.customLangUrl, _customLangUrl);
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
	 * Delays the AJAX request. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDelay() {
		return (String) getStateHelper().eval(PropertyKeys.delay);
	}

	/**
	 * Delays the AJAX request. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDelay(String _delay) {
		getStateHelper().put(PropertyKeys.delay, _delay);
	}

	/**
	 * If you set this option to true, the user can de-select rows by simply clicking outside the table. Defaults to false. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isDeselectOnBackdropClick() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.deselectOnBackdropClick, false);
	}

	/**
	 * If you set this option to true, the user can de-select rows by simply clicking outside the table. Defaults to false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDeselectOnBackdropClick(boolean _deselectOnBackdropClick) {
		getStateHelper().put(PropertyKeys.deselectOnBackdropClick, _deselectOnBackdropClick);
	}

	/**
	 * Boolean value to specify if the button is disabled. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
	}

	/**
	 * Boolean value to specify if the button is disabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * @return Returns the value of the attribute, or "block", if it hasn't been set by the JSF file.
	 */
	public String getDisplay() {
		return (String) getStateHelper().eval(PropertyKeys.display, "block");
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisplay(String _display) {
		getStateHelper().put(PropertyKeys.display, _display);
	}

	/**
	 * Adds a 'export Excel file' button. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isExcel() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.excel, false);
	}

	/**
	 * Adds a 'export Excel file' button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setExcel(boolean _excel) {
		getStateHelper().put(PropertyKeys.excel, _excel);
	}

	/**
	 * Activates the fixed header plugin of the dataTable. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isFixedHeader() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.fixedHeader, false);
	}

	/**
	 * Activates the fixed header plugin of the dataTable. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFixedHeader(boolean _fixedHeader) {
		getStateHelper().put(PropertyKeys.fixedHeader, _fixedHeader);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHidden() {
		return (String) getStateHelper().eval(PropertyKeys.hidden);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHidden(String _hidden) {
		getStateHelper().put(PropertyKeys.hidden, _hidden);
	}

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isImmediate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.immediate, false);
	}

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
		getStateHelper().put(PropertyKeys.immediate, _immediate);
	}

	/**
	 * If set, this will enable the information about record count. Defaults to true. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isInfo() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.info, true);
	}

	/**
	 * If set, this will enable the information about record count. Defaults to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInfo(boolean _info) {
		getStateHelper().put(PropertyKeys.info, _info);
	}

	/**
	 * Configured lang for the dataTable. If no default language is configured, the language configured in the browser is used. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLang() {
		return (String) getStateHelper().eval(PropertyKeys.lang);
	}

	/**
	 * Configured lang for the dataTable. If no default language is configured, the language configured in the browser is used. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLang(String _lang) {
		getStateHelper().put(PropertyKeys.lang, _lang);
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLargeScreen() {
		return (String) getStateHelper().eval(PropertyKeys.largeScreen, "-1");
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLargeScreen(String _largeScreen) {
		getStateHelper().put(PropertyKeys.largeScreen, _largeScreen);
	}

	/**
	 * Allows the user to disable the pageLength menu. Defaults to true. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isLengthChange() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.lengthChange, true);
	}

	/**
	 * Allows the user to disable the pageLength menu. Defaults to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLengthChange(boolean _lengthChange) {
		getStateHelper().put(PropertyKeys.lengthChange, _lengthChange);
	}

	/**
	 * If true, search results are marked yellow as you type. Based on mark.js (see https://datatables.net/blog/2017-01-19). <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isMarkSearchResults() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.markSearchResults, false);
	}

	/**
	 * If true, search results are marked yellow as you type. Based on mark.js (see https://datatables.net/blog/2017-01-19). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMarkSearchResults(boolean _markSearchResults) {
		getStateHelper().put(PropertyKeys.markSearchResults, _markSearchResults);
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getMediumScreen() {
		return (String) getStateHelper().eval(PropertyKeys.mediumScreen, "-1");
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMediumScreen(String _mediumScreen) {
		getStateHelper().put(PropertyKeys.mediumScreen, _mediumScreen);
	}

	/**
	 * If true, &lt;b:inputText /&gt; fields will be generated at the bottom of each column which allow you to perform per-column filtering. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isMultiColumnSearch() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.multiColumnSearch, false);
	}

	/**
	 * If true, &lt;b:inputText /&gt; fields will be generated at the bottom of each column which allow you to perform per-column filtering. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMultiColumnSearch(boolean _multiColumnSearch) {
		getStateHelper().put(PropertyKeys.multiColumnSearch, _multiColumnSearch);
	}

	/**
	 * Should the multi-column-search attributes be at the bottom or the top of the table? Legal values: 'top','botton', and 'both'. Default to 'bottom'. <P>
	 * @return Returns the value of the attribute, or "bottom", if it hasn't been set by the JSF file.
	 */
	public String getMultiColumnSearchPosition() {
		return (String) getStateHelper().eval(PropertyKeys.multiColumnSearchPosition, "bottom");
	}

	/**
	 * Should the multi-column-search attributes be at the bottom or the top of the table? Legal values: 'top','botton', and 'both'. Default to 'bottom'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMultiColumnSearchPosition(String _multiColumnSearchPosition) {
		getStateHelper().put(PropertyKeys.multiColumnSearchPosition, _multiColumnSearchPosition);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffset() {
		return (String) getStateHelper().eval(PropertyKeys.offset);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffset(String _offset) {
		getStateHelper().put(PropertyKeys.offset, _offset);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetLg() {
		return (String) getStateHelper().eval(PropertyKeys.offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetLg(String _offsetLg) {
		getStateHelper().put(PropertyKeys.offsetLg, _offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetMd() {
		return (String) getStateHelper().eval(PropertyKeys.offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetMd(String _offsetMd) {
		getStateHelper().put(PropertyKeys.offsetMd, _offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetSm() {
		return (String) getStateHelper().eval(PropertyKeys.offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetSm(String _offsetSm) {
		getStateHelper().put(PropertyKeys.offsetSm, _offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetXs() {
		return (String) getStateHelper().eval(PropertyKeys.offsetXs);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetXs(String _offsetXs) {
		getStateHelper().put(PropertyKeys.offsetXs, _offsetXs);
	}

	/**
	 * The onclick attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnclick() {
		return (String) getStateHelper().eval(PropertyKeys.onclick);
	}

	/**
	 * The onclick attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
		getStateHelper().put(PropertyKeys.onclick, _onclick);
	}

	/**
	 * JavaScript to be executed when ajax completes. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOncomplete() {
		return (String) getStateHelper().eval(PropertyKeys.oncomplete);
	}

	/**
	 * JavaScript to be executed when ajax completes. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncomplete(String _oncomplete) {
		getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
	}

	/**
	 * Client side callback to execute when input element is double clicked. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndblclick() {
		return (String) getStateHelper().eval(PropertyKeys.ondblclick);
	}

	/**
	 * Client side callback to execute when input element is double clicked. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndblclick(String _ondblclick) {
		getStateHelper().put(PropertyKeys.ondblclick, _ondblclick);
	}

	/**
	 * Client side and/or AJAX callback to execute when a row is deselected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndeselect() {
		return (String) getStateHelper().eval(PropertyKeys.ondeselect);
	}

	/**
	 * Client side and/or AJAX callback to execute when a row is deselected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndeselect(String _ondeselect) {
		getStateHelper().put(PropertyKeys.ondeselect, _ondeselect);
	}

	/**
	 * JavaScript to be executed when ajax results on an error (including both network errors and Java exceptions). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnerror() {
		return (String) getStateHelper().eval(PropertyKeys.onerror);
	}

	/**
	 * JavaScript to be executed when ajax results on an error (including both network errors and Java exceptions). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnerror(String _onerror) {
		getStateHelper().put(PropertyKeys.onerror, _onerror);
	}

	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousedown() {
		return (String) getStateHelper().eval(PropertyKeys.onmousedown);
	}

	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousedown(String _onmousedown) {
		getStateHelper().put(PropertyKeys.onmousedown, _onmousedown);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved within input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousemove() {
		return (String) getStateHelper().eval(PropertyKeys.onmousemove);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved within input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousemove(String _onmousemove) {
		getStateHelper().put(PropertyKeys.onmousemove, _onmousemove);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved away from input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseout() {
		return (String) getStateHelper().eval(PropertyKeys.onmouseout);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved away from input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseout(String _onmouseout) {
		getStateHelper().put(PropertyKeys.onmouseout, _onmouseout);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved onto input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseover() {
		return (String) getStateHelper().eval(PropertyKeys.onmouseover);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved onto input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseover(String _onmouseover) {
		getStateHelper().put(PropertyKeys.onmouseover, _onmouseover);
	}

	/**
	 * Client side callback to execute when a pointer input element is released over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseup() {
		return (String) getStateHelper().eval(PropertyKeys.onmouseup);
	}

	/**
	 * Client side callback to execute when a pointer input element is released over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseup(String _onmouseup) {
		getStateHelper().put(PropertyKeys.onmouseup, _onmouseup);
	}

	/**
	 * Client side and/or AJAX callback to execute when the table is sorted. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnorder() {
		return (String) getStateHelper().eval(PropertyKeys.onorder);
	}

	/**
	 * Client side and/or AJAX callback to execute when the table is sorted. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnorder(String _onorder) {
		getStateHelper().put(PropertyKeys.onorder, _onorder);
	}

	/**
	 * Client side and/or AJAX callback to execute when the current table page changes. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnpage() {
		return (String) getStateHelper().eval(PropertyKeys.onpage);
	}

	/**
	 * Client side and/or AJAX callback to execute when the current table page changes. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnpage(String _onpage) {
		getStateHelper().put(PropertyKeys.onpage, _onpage);
	}

	/**
	 * Client side and/or AJAX callback to execute when the user starts a search. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnsearch() {
		return (String) getStateHelper().eval(PropertyKeys.onsearch);
	}

	/**
	 * Client side and/or AJAX callback to execute when the user starts a search. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnsearch(String _onsearch) {
		getStateHelper().put(PropertyKeys.onsearch, _onsearch);
	}

	/**
	 * Client side and/or AJAX callback to execute when a row is selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnselect() {
		return (String) getStateHelper().eval(PropertyKeys.onselect);
	}

	/**
	 * Client side and/or AJAX callback to execute when a row is selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnselect(String _onselect) {
		getStateHelper().put(PropertyKeys.onselect, _onselect);
	}

	/**
	 * JavaScript to be executed when ajax completes with success (i.e. there's neither a network error nor a Java exception). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnsuccess() {
		return (String) getStateHelper().eval(PropertyKeys.onsuccess);
	}

	/**
	 * JavaScript to be executed when ajax completes with success (i.e. there's neither a network error nor a Java exception). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnsuccess(String _onsuccess) {
		getStateHelper().put(PropertyKeys.onsuccess, _onsuccess);
	}

	/**
	 * Sets the default page length for paginated dataTable. The default value is 10. <P>
	 * @return Returns the value of the attribute, or 10, if it hasn't been set by the JSF file.
	 */
	public int getPageLength() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.pageLength, 10);
	}

	/**
	 * Sets the default page length for paginated dataTable. The default value is 10. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPageLength(int _pageLength) {
		getStateHelper().put(PropertyKeys.pageLength, _pageLength);
	}

	/**
	 * Sets the default page length for paginated dataTable. The default value is [10, 25, 50, 100]. The brackets are optional. Read https://www.datatables.net/examples/advanced_init/length_menu.html for details. <P>
	 * @return Returns the value of the attribute, or "[ 10, 25, 50, 100 ]", if it hasn't been set by the JSF file.
	 */
	public String getPageLengthMenu() {
		return (String) getStateHelper().eval(PropertyKeys.pageLengthMenu, "[ 10, 25, 50, 100 ]");
	}

	/**
	 * Sets the default page length for paginated dataTable. The default value is [10, 25, 50, 100]. The brackets are optional. Read https://www.datatables.net/examples/advanced_init/length_menu.html for details. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPageLengthMenu(String _pageLengthMenu) {
		getStateHelper().put(PropertyKeys.pageLengthMenu, _pageLengthMenu);
	}

	/**
	 * Activates the pagination of the dataTable. Default value is 'true'. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isPaginated() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.paginated, true);
	}

	/**
	 * Activates the pagination of the dataTable. Default value is 'true'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPaginated(boolean _paginated) {
		getStateHelper().put(PropertyKeys.paginated, _paginated);
	}

	/**
	 * Adds a 'export PDF file' button. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isPdf() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.pdf, false);
	}

	/**
	 * Adds a 'export PDF file' button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPdf(boolean _pdf) {
		getStateHelper().put(PropertyKeys.pdf, _pdf);
	}

	/**
	 * Adds a 'print' button. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isPrint() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.print, false);
	}

	/**
	 * Adds a 'print' button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPrint(boolean _print) {
		getStateHelper().put(PropertyKeys.print, _print);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getProcess() {
		return (String) getStateHelper().eval(PropertyKeys.process);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setProcess(String _process) {
		getStateHelper().put(PropertyKeys.process, _process);
	}

	/**
	 * Activates the responsive plugin of the dataTable <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isResponsive() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.responsive, false);
	}

	/**
	 * Activates the responsive plugin of the dataTable <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setResponsive(boolean _responsive) {
		getStateHelper().put(PropertyKeys.responsive, _responsive);
	}

	/**
	 * Group the rows by a common column value. Can be a number or a Json-object, as documented at https://datatables.net/reference/option/#rowgroup. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRowGroup() {
		return (String) getStateHelper().eval(PropertyKeys.rowGroup);
	}

	/**
	 * Group the rows by a common column value. Can be a number or a Json-object, as documented at https://datatables.net/reference/option/#rowgroup. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRowGroup(String _rowGroup) {
		getStateHelper().put(PropertyKeys.rowGroup, _rowGroup);
	}

	/**
	 * Enable the row highlight css. Default: true. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isRowHighlight() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.rowHighlight, true);
	}

	/**
	 * Enable the row highlight css. Default: true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRowHighlight(boolean _rowHighlight) {
		getStateHelper().put(PropertyKeys.rowHighlight, _rowHighlight);
	}

	/**
	 * Optional CSS class for each row. If it's an EL expression, it's evaluated for each row. You can also provide a comma-separated list. In this case, the CSS classes are assigned cyclically to the row. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRowStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.rowStyleClass);
	}

	/**
	 * Optional CSS class for each row. If it's an EL expression, it's evaluated for each row. You can also provide a comma-separated list. In this case, the CSS classes are assigned cyclically to the row. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRowStyleClass(String _rowStyleClass) {
		getStateHelper().put(PropertyKeys.rowStyleClass, _rowStyleClass);
	}

	/**
	 * Stores the state of the datatable on the client, so that after a page reload the same filters are active, the same page is shown etc. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isSaveState() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.saveState, true);
	}

	/**
	 * Stores the state of the datatable on the client, so that after a page reload the same filters are active, the same page is shown etc. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSaveState(boolean _saveState) {
		getStateHelper().put(PropertyKeys.saveState, _saveState);
	}

	/**
	 * If set, this will have the container match the height of the rows shown in the table if that height is smaller than that given height by the scroll-size. Default: true. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isScrollCollapse() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.scrollCollapse, true);
	}

	/**
	 * If set, this will have the container match the height of the rows shown in the table if that height is smaller than that given height by the scroll-size. Default: true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setScrollCollapse(boolean _scrollCollapse) {
		getStateHelper().put(PropertyKeys.scrollCollapse, _scrollCollapse);
	}

	/**
	 * Adds a horizontal scroll bar on small screens. Similar to scroll-x, but wraps the entire table within the scroll area. Defaults to false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isScrollHorizontally() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.scrollHorizontally, false);
	}

	/**
	 * Adds a horizontal scroll bar on small screens. Similar to scroll-x, but wraps the entire table within the scroll area. Defaults to false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setScrollHorizontally(boolean _scrollHorizontally) {
		getStateHelper().put(PropertyKeys.scrollHorizontally, _scrollHorizontally);
	}

	/**
	 * If set, force the height of table to the size specified. You can optionally to add the unit (e.g. scroll-size="200px"). By default, it's px. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getScrollSize() {
		return (String) getStateHelper().eval(PropertyKeys.scrollSize);
	}

	/**
	 * If set, force the height of table to the size specified. You can optionally to add the unit (e.g. scroll-size="200px"). By default, it's px. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setScrollSize(String _scrollSize) {
		getStateHelper().put(PropertyKeys.scrollSize, _scrollSize);
	}

	/**
	 * If set, the table can scroll horizontally. Similar to scroll-horizontally, but uses a different approach, so the page selector and the search are not scrolled with the table. Defaults to false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isScrollX() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.scrollX, false);
	}

	/**
	 * If set, the table can scroll horizontally. Similar to scroll-horizontally, but uses a different approach, so the page selector and the search are not scrolled with the table. Defaults to false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setScrollX(boolean _scrollX) {
		getStateHelper().put(PropertyKeys.scrollX, _scrollX);
	}

	/**
	 * If set to false, this feature completely disables the search functionality of the datatable (i.e. both the UI and the JavaScript API). <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isSearching() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.searching, true);
	}

	/**
	 * If set to false, this feature completely disables the search functionality of the datatable (i.e. both the UI and the JavaScript API). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSearching(boolean _searching) {
		getStateHelper().put(PropertyKeys.searching, _searching);
	}

	/**
	 * Allows the user to select rows. Defaults to false. Is automatically set to true if selected-column, selected-row or selection-mode is set. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isSelect() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.select, false);
	}

	/**
	 * Allows the user to select rows. Defaults to false. Is automatically set to true if selected-column, selected-row or selection-mode is set. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelect(boolean _select) {
		getStateHelper().put(PropertyKeys.select, _select);
	}

	/**
	 * Optional parameter defining which columns are selected when the datatable is initially rendered. If this attribute is an integer, it's the column index. If it's a string, it's a jQuery expression. Automatically sets selection='true' and selected-items='column'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public java.lang.Object getSelectedColumn() {
		return (java.lang.Object) getStateHelper().eval(PropertyKeys.selectedColumn);
	}

	/**
	 * Optional parameter defining which columns are selected when the datatable is initially rendered. If this attribute is an integer, it's the column index. If it's a string, it's a jQuery expression. Automatically sets selection='true' and selected-items='column'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectedColumn(java.lang.Object _selectedColumn) {
		getStateHelper().put(PropertyKeys.selectedColumn, _selectedColumn);
	}

	/**
	 * Determines whether rows, columns or individual cells are selected. Legal values are 'row', 'column', and 'cell'. The default value is 'row'. Automatically sets select='true'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSelectedItems() {
		return (String) getStateHelper().eval(PropertyKeys.selectedItems);
	}

	/**
	 * Determines whether rows, columns or individual cells are selected. Legal values are 'row', 'column', and 'cell'. The default value is 'row'. Automatically sets select='true'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectedItems(String _selectedItems) {
		getStateHelper().put(PropertyKeys.selectedItems, _selectedItems);
	}

	/**
	 * Optional parameter defining which rows are selected when the datatable is initially rendered. If this attribute is an integer, it's the row index. If it's a string, it's a jQuery expression. If it's another object, it's compared to the loop var.  Automatically sets selection='true' and selected-items='row'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public java.lang.Object getSelectedRow() {
		return (java.lang.Object) getStateHelper().eval(PropertyKeys.selectedRow);
	}

	/**
	 * Optional parameter defining which rows are selected when the datatable is initially rendered. If this attribute is an integer, it's the row index. If it's a string, it's a jQuery expression. If it's another object, it's compared to the loop var.  Automatically sets selection='true' and selected-items='row'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectedRow(java.lang.Object _selectedRow) {
		getStateHelper().put(PropertyKeys.selectedRow, _selectedRow);
	}

	/**
	 * The datatable shows below the table how many rows are selected. Setting this option to false deactivates this feature. Defaults to true. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isSelectionInfo() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.selectionInfo, true);
	}

	/**
	 * The datatable shows below the table how many rows are selected. Setting this option to false deactivates this feature. Defaults to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectionInfo(boolean _selectionInfo) {
		getStateHelper().put(PropertyKeys.selectionInfo, _selectionInfo);
	}

	/**
	 * Set this property to "single" if you want to prevent multiple selections. Default is "multiple". <P>
	 * @return Returns the value of the attribute, or "multiple", if it hasn't been set by the JSF file.
	 */
	public String getSelectionMode() {
		return (String) getStateHelper().eval(PropertyKeys.selectionMode, "multiple");
	}

	/**
	 * Set this property to "single" if you want to prevent multiple selections. Default is "multiple". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectionMode(String _selectionMode) {
		getStateHelper().put(PropertyKeys.selectionMode, _selectionMode);
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getSmallScreen() {
		return (String) getStateHelper().eval(PropertyKeys.smallScreen, "-1");
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSmallScreen(String _smallScreen) {
		getStateHelper().put(PropertyKeys.smallScreen, _smallScreen);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSpan() {
		return (String) getStateHelper().eval(PropertyKeys.span);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(String _span) {
		getStateHelper().put(PropertyKeys.span, _span);
	}

	/**
	 * If set, this will show the row in alternating background colors (typically shades of gray). Defaults to true. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isStriped() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.striped, true);
	}

	/**
	 * If set, this will show the row in alternating background colors (typically shades of gray). Defaults to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStriped(boolean _striped) {
		getStateHelper().put(PropertyKeys.striped, _striped);
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
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getTinyScreen() {
		return (String) getStateHelper().eval(PropertyKeys.tinyScreen, "-1");
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTinyScreen(String _tinyScreen) {
		getStateHelper().put(PropertyKeys.tinyScreen, _tinyScreen);
	}

	/**
	 * The text of the tooltip. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		return (String) getStateHelper().eval(PropertyKeys.tooltip);
	}

	/**
	 * The text of the tooltip. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, _tooltip);
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering errors in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * @return Returns the value of the attribute, or "body", if it hasn't been set by the JSF file.
	 */
	public String getTooltipContainer() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering errors in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
		getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelay() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
		getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
		getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
		getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipPosition() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipPosition);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
		getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
	}

	/**
	 * Component(s) to be updated with ajax. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getUpdate() {
		return (String) getStateHelper().eval(PropertyKeys.update);
	}

	/**
	 * Component(s) to be updated with ajax. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getVisible() {
		return (String) getStateHelper().eval(PropertyKeys.visible);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVisible(String _visible) {
		getStateHelper().put(PropertyKeys.visible, _visible);
	}

	/**
	 * optional widget variable to access the datatable widget in JavaScript code. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar);
	}

	/**
	 * optional widget variable to access the datatable widget in JavaScript code. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidgetVar(String _widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
	}

}
