package net.bootsfaces.component.fullCalendar;

import javax.faces.component.UIData;

/**
 * 
 * @author jottyfan
 *
 */
public class FullCalendarCore extends UIData implements net.bootsfaces.render.IHasTooltip {

	protected enum PropertyKeys {
		allDaySlot, autoUpdate, businessHours, calendarHeader, colLg, colMd, colSm, colXs, dayClick, defaultDate, defaultView, display, editable, eventClick, events, height, hidden, lang, largeScreen, mediumScreen, offset, offsetLg, offsetMd, offsetSm, offsetXs, scrollTime, slotDuration, smallScreen, span, style, styleClass, tinyScreen, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, visible, weekMode, weekNumbers, weekends;
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
	 * String value to determine if the 'all-day' slot is displayed at the top of the calendar in agenda views <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isAllDaySlot() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.allDaySlot, true);
	}

	/**
	 * String value to determine if the 'all-day' slot is displayed at the top of the calendar in agenda views <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllDaySlot(boolean _allDaySlot) {
		getStateHelper().put(PropertyKeys.allDaySlot, _allDaySlot);
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
	 * String value that contains the business hours and the days of week, for example: {start: '8:00', end: '18:00', dow: \[1, 2, 3, 4, 5\]} <P>
	 * @return Returns the value of the attribute, or "{start: '8:00', end: '18:00', dow: [1, 2, 3, 4, 5]}", if it hasn't been set by the JSF file.
	 */
	public String getBusinessHours() {
		return (String) getStateHelper().eval(PropertyKeys.businessHours,
				"{start: '8:00', end: '18:00', dow: [1, 2, 3, 4, 5]}");
	}

	/**
	 * String value that contains the business hours and the days of week, for example: {start: '8:00', end: '18:00', dow: \[1, 2, 3, 4, 5\]} <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBusinessHours(String _businessHours) {
		getStateHelper().put(PropertyKeys.businessHours, _businessHours);
	}

	/**
	 * String value that contains the calendar header, for example: {left: 'prev,next today', center: 'title', right: 'month,agendaWeek,agendaDay'} <P>
	 * @return Returns the value of the attribute, or "{left: 'prev,next today', center: 'title', right: 'month,agendaWeek,agendaDay'}", if it hasn't been set by the JSF file.
	 */
	public String getCalendarHeader() {
		return (String) getStateHelper().eval(PropertyKeys.calendarHeader,
				"{left: 'prev,next today', center: 'title', right: 'month,agendaWeek,agendaDay'}");
	}

	/**
	 * String value that contains the calendar header, for example: {left: 'prev,next today', center: 'title', right: 'month,agendaWeek,agendaDay'} <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCalendarHeader(String _calendarHeader) {
		getStateHelper().put(PropertyKeys.calendarHeader, _calendarHeader);
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
	 * String value to bind a javascript function to the on click event of a day; javascript signature is function(calEvent, jsEvent, view){ ... }; calEvent contains a day when clicked on the month's calendar, a day time of a slot otherwise <P>
	 * @return Returns the value of the attribute, or "function(calEvent, jsEvent, view){ $(this).css('background', 'yellow'); }", if it hasn't been set by the JSF file.
	 */
	public String getDayClick() {
		return (String) getStateHelper().eval(PropertyKeys.dayClick,
				"function(calEvent, jsEvent, view){ $(this).css('background', 'yellow'); }");
	}

	/**
	 * String value to bind a javascript function to the on click event of a day; javascript signature is function(calEvent, jsEvent, view){ ... }; calEvent contains a day when clicked on the month's calendar, a day time of a slot otherwise <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDayClick(String _dayClick) {
		getStateHelper().put(PropertyKeys.dayClick, _dayClick);
	}

	/**
	 * String value of default date in iso 8601 format; this is the date where the calendar position is set on loading <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDefaultDate() {
		return (String) getStateHelper().eval(PropertyKeys.defaultDate);
	}

	/**
	 * String value of default date in iso 8601 format; this is the date where the calendar position is set on loading <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDefaultDate(String _defaultDate) {
		getStateHelper().put(PropertyKeys.defaultDate, _defaultDate);
	}

	/**
	 * String value to determine which view to display on page load; use one of month, basicDay, basicWeek, agendaDay, agendaWeek <P>
	 * @return Returns the value of the attribute, or "month", if it hasn't been set by the JSF file.
	 */
	public String getDefaultView() {
		return (String) getStateHelper().eval(PropertyKeys.defaultView, "month");
	}

	/**
	 * String value to determine which view to display on page load; use one of month, basicDay, basicWeek, agendaDay, agendaWeek <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDefaultView(String _defaultView) {
		getStateHelper().put(PropertyKeys.defaultView, _defaultView);
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
	 * Boolean value to specify if this calendar is editable <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isEditable() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.editable, false);
	}

	/**
	 * Boolean value to specify if this calendar is editable <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEditable(boolean _editable) {
		getStateHelper().put(PropertyKeys.editable, _editable);
	}

	/**
	 * String value to bind a javascript function to the on click event on a date; javascript signature is function(calEvent, jsEvent, view){ ... } <P>
	 * @return Returns the value of the attribute, or "function(calEvent, jsEvent, view){ $(this).css('background', 'yellow'); }", if it hasn't been set by the JSF file.
	 */
	public String getEventClick() {
		return (String) getStateHelper().eval(PropertyKeys.eventClick,
				"function(calEvent, jsEvent, view){ $(this).css('background', 'yellow'); }");
	}

	/**
	 * String value to bind a javascript function to the on click event on a date; javascript signature is function(calEvent, jsEvent, view){ ... } <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEventClick(String _eventClick) {
		getStateHelper().put(PropertyKeys.eventClick, _eventClick);
	}

	/**
	 * String value that contains events of this calendar in json notation; see http:\/\/fullcalendar.io/docs/event_data/Event_Object/ for details; also, use net.bootsfaces.component.fullCalendar.FullCalendarEventList to generate a json string from java beans <P>
	 * @return Returns the value of the attribute, or "[]", if it hasn't been set by the JSF file.
	 */
	public String getEvents() {
		return (String) getStateHelper().eval(PropertyKeys.events, "[]");
	}

	/**
	 * String value that contains events of this calendar in json notation; see http:\/\/fullcalendar.io/docs/event_data/Event_Object/ for details; also, use net.bootsfaces.component.fullCalendar.FullCalendarEventList to generate a json string from java beans <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEvents(String _events) {
		getStateHelper().put(PropertyKeys.events, _events);
	}

	/**
	 * Integer value to specify the height of the calender <P>
	 * @return Returns the value of the attribute, or 480, if it hasn't been set by the JSF file.
	 */
	public int getHeight() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.height, 480);
	}

	/**
	 * Integer value to specify the height of the calender <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHeight(int _height) {
		getStateHelper().put(PropertyKeys.height, _height);
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
	 * String value to specify the calendar language <P>
	 * @return Returns the value of the attribute, or "en", if it hasn't been set by the JSF file.
	 */
	public String getLang() {
		return (String) getStateHelper().eval(PropertyKeys.lang, "en");
	}

	/**
	 * String value to specify the calendar language <P>
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
	 * String value that contains the hour where weekly and daily calendars start, for example: 06:00:00 (default), 12:00:00, 09:00:00 <P>
	 * @return Returns the value of the attribute, or "06:00:00", if it hasn't been set by the JSF file.
	 */
	public String getScrollTime() {
		return (String) getStateHelper().eval(PropertyKeys.scrollTime, "06:00:00");
	}

	/**
	 * String value that contains the hour where weekly and daily calendars start, for example: 06:00:00 (default), 12:00:00, 09:00:00 <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setScrollTime(String _scrollTime) {
		getStateHelper().put(PropertyKeys.scrollTime, _scrollTime);
	}

	/**
	 * String value to define the duration of a slot; default is 00:30:00 for 30 minutes <P>
	 * @return Returns the value of the attribute, or "00:30:00", if it hasn't been set by the JSF file.
	 */
	public String getSlotDuration() {
		return (String) getStateHelper().eval(PropertyKeys.slotDuration, "00:30:00");
	}

	/**
	 * String value to define the duration of a slot; default is 00:30:00 for 30 minutes <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSlotDuration(String _slotDuration) {
		getStateHelper().put(PropertyKeys.slotDuration, _slotDuration);
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
	 * String value to specify calendar size, one of liquid, fixed or variable <P>
	 * @return Returns the value of the attribute, or "liquid", if it hasn't been set by the JSF file.
	 */
	public String getWeekMode() {
		return (String) getStateHelper().eval(PropertyKeys.weekMode, "liquid");
	}

	/**
	 * String value to specify calendar size, one of liquid, fixed or variable <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWeekMode(String _weekMode) {
		getStateHelper().put(PropertyKeys.weekMode, _weekMode);
	}

	/**
	 * Boolean value to specify if this calendar should display week numbers <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isWeekNumbers() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.weekNumbers, false);
	}

	/**
	 * Boolean value to specify if this calendar should display week numbers <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWeekNumbers(boolean _weekNumbers) {
		getStateHelper().put(PropertyKeys.weekNumbers, _weekNumbers);
	}

	/**
	 * Boolean value to specify if this calendar should display weekends <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isWeekends() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.weekends, true);
	}

	/**
	 * Boolean value to specify if this calendar should display weekends <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWeekends(boolean _weekends) {
		getStateHelper().put(PropertyKeys.weekends, _weekends);
	}

}
