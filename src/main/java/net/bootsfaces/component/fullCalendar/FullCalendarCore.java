package net.bootsfaces.component.fullCalendar;

import javax.faces.component.UIData;

/**
 * 
 * @author jottyfan
 *
 */
public class FullCalendarCore extends UIData implements net.bootsfaces.render.IHasTooltip {

	protected enum PropertyKeys {
		allDaySlot, businessHours, calendarHeader, colLg, colMd, colSm, colXs, data, dayClick, defaultDate, defaultView, display, editable, eventClick, events, height, hidden, lang, largeScreen, mediumScreen, offset, offsetLg, offsetMd, offsetSm, offsetXs, scrollTime, slotDuration, smallScreen, span, style, styleClass, tinyScreen, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, visible, weekends, weekMode, weekNumbers;
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
	
	public String getDefaultDate() {
		return (String) getStateHelper().eval(PropertyKeys.defaultDate, null); 
	}
	
	public void setDefaultDate(String defaultDate) {
		getStateHelper().put(PropertyKeys.defaultDate, defaultDate);
	}
	
	public String getDayClick() {
		return (String) getStateHelper().eval(PropertyKeys.dayClick, "function(calEvent, jsEvent, view){ $(this).css('background', 'yellow'); }");
	}

	public void setDayClick(String dayClick) {
		getStateHelper().put(PropertyKeys.dayClick, dayClick);
	}
	
	public String getEventClick() {
		return (String) getStateHelper().eval(PropertyKeys.eventClick, "function(calEvent, jsEvent, view){ $(this).css('background', 'yellow'); }");
	}

	public void setEventClick(String eventClick) {
		getStateHelper().put(PropertyKeys.eventClick, eventClick);
	}
	
	public String getScrollTime() {
		return (String) getStateHelper().eval(PropertyKeys.scrollTime, "06:00:00");
	}
	
	public void setScrollTime(String scrollTime) {
		getStateHelper().put(PropertyKeys.scrollTime, scrollTime);
	}
	
	public Boolean getAllDaySlot() {
		return (Boolean) getStateHelper().eval(PropertyKeys.allDaySlot, true);
	}
	
	public void setAllDaySlot(Boolean allDaySlot) {
		getStateHelper().put(PropertyKeys.allDaySlot, allDaySlot);
	}
	
	public String getSlotDuration() {
		return (String) getStateHelper().eval(PropertyKeys.slotDuration, "00:30:00");
	}
	
	public void setSlotDuration(String slotDuration) {
		getStateHelper().put(PropertyKeys.slotDuration, slotDuration);
	}
	
	public String getDefaultView() {
		return (String) getStateHelper().eval(PropertyKeys.defaultView, "month");
	}

	public void setDefaultView(String defaultView) {
		getStateHelper().put(PropertyKeys.defaultView, defaultView);
	}

	public String getBusinessHours() {
		return (String) getStateHelper().eval(PropertyKeys.businessHours,
				"{start: '8:00', end: '18:00', dow: [1, 2, 3, 4, 5]}");
	}

	public void setBusinessHours(String businessHours) {
		getStateHelper().put(PropertyKeys.businessHours, businessHours);
	}

	public String getCalendarHeader() {
		return (String) getStateHelper().eval(PropertyKeys.calendarHeader,
				"{left: 'prev,next today', center: 'title', right: 'month,agendaWeek,agendaDay'}");
	}

	public void setCalendarHeader(String header) {
		getStateHelper().put(PropertyKeys.calendarHeader, header);
	}

	public Integer getHeight() {
		return (Integer) getStateHelper().eval(PropertyKeys.height, 480);
	}

	public void setHeight(Integer height) {
		getStateHelper().put(PropertyKeys.height, height);
	}

	public Boolean getWeekNumbers() {
		return (Boolean) getStateHelper().eval(PropertyKeys.weekNumbers, "false");
	}

	public void setWeekNumbers(Boolean weekNumbers) {
		getStateHelper().put(PropertyKeys.weekNumbers, weekNumbers);
	}

	public String getLang() {
		return (String) getStateHelper().eval(PropertyKeys.lang, "en");
	}

	public void setLang(String lang) {
		getStateHelper().put(PropertyKeys.lang, lang);
	}

	public String getEvents() {
		return (String) getStateHelper().eval(PropertyKeys.events, "[]");
	}

	public void setEvents(String events) {
		getStateHelper().put(PropertyKeys.events, events);
	}

	public Boolean getEditable() {
		return (Boolean) getStateHelper().eval(PropertyKeys.editable, "false");
	}

	public void setEditable(Boolean editable) {
		getStateHelper().put(PropertyKeys.editable, editable);
	}

	public String getWeekMode() {
		return (String) getStateHelper().eval(PropertyKeys.weekMode, "liquid");
	}

	public void setWeekMode(String weekMode) {
		if ("liquid".equals(weekMode) || "fixed".equals(weekMode) || "variable".equals(weekMode)) {
			getStateHelper().put(PropertyKeys.weekMode, weekMode);
		} else {
			getStateHelper().put(PropertyKeys.weekMode, "liquid");
		}
	}

	public Boolean getWeekends() {
		return (Boolean) getStateHelper().eval(PropertyKeys.weekends, "true");
	}

	public void setWeekends(Boolean weekends) {
		getStateHelper().put(PropertyKeys.weekends, weekends);
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200
	 * pixels wide). The number may optionally be followed by "column" or
	 * "columns". Alternative legal values: half, one-third, two-thirds,
	 * one-fourth, three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getColLg() {
		return (String) getStateHelper().eval(PropertyKeys.colLg, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200
	 * pixels wide). The number may optionally be followed by "column" or
	 * "columns". Alternative legal values: half, one-third, two-thirds,
	 * one-fourth, three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColLg(String _colLg) {
		getStateHelper().put(PropertyKeys.colLg, _colLg);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992
	 * pixels wide). The number may optionally be followed by "column" or
	 * "columns". Alternative legal values: half, one-third, two-thirds,
	 * one-fourth, three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getColMd() {
		return (String) getStateHelper().eval(PropertyKeys.colMd, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992
	 * pixels wide). The number may optionally be followed by "column" or
	 * "columns". Alternative legal values: half, one-third, two-thirds,
	 * one-fourth, three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColMd(String _colMd) {
		getStateHelper().put(PropertyKeys.colMd, _colMd);
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p
	 * pixels wide). The number may optionally be followed by "column" or
	 * "columns". Alternative legal values: half, one-third, two-thirds,
	 * one-fourth, three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getColSm() {
		return (String) getStateHelper().eval(PropertyKeys.colSm, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p
	 * pixels wide). The number may optionally be followed by "column" or
	 * "columns". Alternative legal values: half, one-third, two-thirds,
	 * one-fourth, three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColSm(String _colSm) {
		getStateHelper().put(PropertyKeys.colSm, _colSm);
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767
	 * pixels wide). The number may optionally be followed by "column" or
	 * "columns". Alternative legal values: half, one-third, two-thirds,
	 * one-fourth, three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getColXs() {
		return (String) getStateHelper().eval(PropertyKeys.colXs, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767
	 * pixels wide). The number may optionally be followed by "column" or
	 * "columns". Alternative legal values: half, one-third, two-thirds,
	 * one-fourth, three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColXs(String _colXs) {
		getStateHelper().put(PropertyKeys.colXs, _colXs);
	}

	/**
	 * Some data
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getData() {
		return (String) getStateHelper().eval(PropertyKeys.data);
	}

	/**
	 * Some data
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setData(String _data) {
		getStateHelper().put(PropertyKeys.data, _data);
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added.
	 * Legal values: block, inline, inline-block. Default: block.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getDisplay() {
		return (String) getStateHelper().eval(PropertyKeys.display, "block");
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added.
	 * Legal values: block, inline, inline-block. Default: block.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisplay(String _display) {
		getStateHelper().put(PropertyKeys.display, _display);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values:
	 * lg, md, sm, xs.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getHidden() {
		return (String) getStateHelper().eval(PropertyKeys.hidden);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values:
	 * lg, md, sm, xs.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHidden(String _hidden) {
		getStateHelper().put(PropertyKeys.hidden, _hidden);
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns
	 * to span on large screens (≥1200 pixels wide). The number may optionally
	 * be followed by "column" or "columns". Alternative legal values: half,
	 * one-third, two-thirds, one-fourth, three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getLargeScreen() {
		return (String) getStateHelper().eval(PropertyKeys.largeScreen, "-1");
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns
	 * to span on large screens (≥1200 pixels wide). The number may optionally
	 * be followed by "column" or "columns". Alternative legal values: half,
	 * one-third, two-thirds, one-fourth, three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLargeScreen(String _largeScreen) {
		getStateHelper().put(PropertyKeys.largeScreen, _largeScreen);
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns
	 * to span on medium screens (≥992 pixels wide). The number may optionally
	 * be followed by "column" or "columns". Alternative legal values: half,
	 * one-third, two-thirds, one-fourth, three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getMediumScreen() {
		return (String) getStateHelper().eval(PropertyKeys.mediumScreen, "-1");
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns
	 * to span on medium screens (≥992 pixels wide). The number may optionally
	 * be followed by "column" or "columns". Alternative legal values: half,
	 * one-third, two-thirds, one-fourth, three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMediumScreen(String _mediumScreen) {
		getStateHelper().put(PropertyKeys.mediumScreen, _mediumScreen);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOffset() {
		return (String) getStateHelper().eval(PropertyKeys.offset);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffset(String _offset) {
		getStateHelper().put(PropertyKeys.offset, _offset);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOffsetLg() {
		return (String) getStateHelper().eval(PropertyKeys.offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetLg(String _offsetLg) {
		getStateHelper().put(PropertyKeys.offsetLg, _offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOffsetMd() {
		return (String) getStateHelper().eval(PropertyKeys.offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetMd(String _offsetMd) {
		getStateHelper().put(PropertyKeys.offsetMd, _offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOffsetSm() {
		return (String) getStateHelper().eval(PropertyKeys.offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetSm(String _offsetSm) {
		getStateHelper().put(PropertyKeys.offsetSm, _offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOffsetXs() {
		return (String) getStateHelper().eval(PropertyKeys.offsetXs);
	}

	/**
	 * Integer value to specify how many columns to offset.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetXs(String _offsetXs) {
		getStateHelper().put(PropertyKeys.offsetXs, _offsetXs);
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns
	 * to span on small screens (≥768p pixels wide). The number may optionally
	 * be followed by "column" or "columns". Alternative legal values: half,
	 * one-third, two-thirds, one-fourth, three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getSmallScreen() {
		return (String) getStateHelper().eval(PropertyKeys.smallScreen, "-1");
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns
	 * to span on small screens (≥768p pixels wide). The number may optionally
	 * be followed by "column" or "columns". Alternative legal values: half,
	 * one-third, two-thirds, one-fourth, three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSmallScreen(String _smallScreen) {
		getStateHelper().put(PropertyKeys.smallScreen, _smallScreen);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992
	 * pixels). The number may optionally be followed by "column" or "columns".
	 * Alternative legal values: half, one-third, two-thirds, one-fourth,
	 * three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getSpan() {
		return (String) getStateHelper().eval(PropertyKeys.span);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992
	 * pixels). The number may optionally be followed by "column" or "columns".
	 * Alternative legal values: half, one-third, two-thirds, one-fourth,
	 * three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(String _span) {
		getStateHelper().put(PropertyKeys.span, _span);
	}

	/**
	 * Inline style of the input element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline style of the input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of this element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of this element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns
	 * to span on tiny screens (≤ 767 pixels wide). The number may optionally be
	 * followed by "column" or "columns". Alternative legal values: half,
	 * one-third, two-thirds, one-fourth, three-fourths.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTinyScreen() {
		return (String) getStateHelper().eval(PropertyKeys.tinyScreen, "-1");
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns
	 * to span on tiny screens (≤ 767 pixels wide). The number may optionally be
	 * followed by "column" or "columns". Alternative legal values: half,
	 * one-third, two-thirds, one-fourth, three-fourths.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTinyScreen(String _tinyScreen) {
		getStateHelper().put(PropertyKeys.tinyScreen, _tinyScreen);
	}

	/**
	 * The text of the tooltip.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltip() {
		return (String) getStateHelper().eval(PropertyKeys.tooltip);
	}

	/**
	 * The text of the tooltip.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, _tooltip);
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value
	 * that can be used to fix rendering errors in special cases. Also see
	 * data-container in the documentation of Bootstrap. The default value is
	 * body.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltipContainer() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value
	 * that can be used to fix rendering errors in special cases. Also see
	 * data-container in the documentation of Bootstrap. The default value is
	 * body.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
		getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelay() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
		getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
		getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
		getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom".
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltipPosition() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipPosition);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom".
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
		getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values:
	 * lg, md, sm, xs.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getVisible() {
		return (String) getStateHelper().eval(PropertyKeys.visible);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values:
	 * lg, md, sm, xs.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVisible(String _visible) {
		getStateHelper().put(PropertyKeys.visible, _visible);
	}
}
