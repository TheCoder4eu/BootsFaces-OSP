package net.bootsfaces.component.fullCalendar;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 * @author jottyfan
 */
/** This class generates the HTML code of &lt;b:fullcalendar /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.fullCalendar.FullCalendar")
public class FullCalendarRenderer extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		FullCalendar fullCalendar = (FullCalendar) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = fullCalendar.getClientId();

		// create div to be used as calendar; the calendar itself is added in encodeEnd's jquery function
		rw.startElement("div", fullCalendar);
		rw.writeAttribute("id", clientId, "id");
		rw.endElement("div");
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		// this component has no children yet
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		FullCalendar fullCalendar = (FullCalendar) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = fullCalendar.getClientId();
		String lang = fullCalendar.getLang();
		if (lang == null) {
			lang = "en"; // ensure to have a value for lang; on loaded language files, lang must be specified
		}

		// activate the jquery plugin on that div
		rw.startElement("script", null);

		rw.writeText("$(document).ready(function() {", null);
		rw.writeText("  $(\"[id='" + clientId + "']\").fullCalendar({", null);
		rw.writeText("    lang: '" + lang + "',", null);
		if (fullCalendar.getScrollTime() != null) {
			rw.writeText("    scrollTime: '" + fullCalendar.getScrollTime() + "',", null);
		}
		rw.writeText("    allDaySlot: " + fullCalendar.isAllDaySlot() + ",", null);
		if (fullCalendar.getSlotDuration() != null) {
			rw.writeText("    slotDuration: '" +  fullCalendar.getSlotDuration() + "',", null);
		}
		if (fullCalendar.getDefaultView() != null) {
			rw.writeText("    defaultView: '" + fullCalendar.getDefaultView() + "',", null);
		}
		if (fullCalendar.getCalendarHeader() != null) {
			rw.writeText("    header: " + fullCalendar.getCalendarHeader() + ",", null);
		}
		if (fullCalendar.getBusinessHours() != null) {
			rw.writeText("    businessHours: " + fullCalendar.getBusinessHours() + ",", null);
		}
		rw.writeText("    height: " + fullCalendar.getHeight() + ",", null);
		rw.writeText("    weekNumbers: " + fullCalendar.isWeekNumbers() + ",", null);
		rw.writeText("    weekends: " + fullCalendar.isWeekends() + ",", null);
		rw.writeText("    editable: " + fullCalendar.isEditable() + ",", null);
		if (fullCalendar.getDefaultDate() != null) {
			rw.writeText("    defaultDate: '" + fullCalendar.getDefaultDate() + "',", null);
		}
		rw.writeText("    weekMode: '" + fullCalendar.getWeekMode() + "',", null);
		if (fullCalendar.getEventClick() != null) {
			rw.writeText("    eventClick: " + fullCalendar.getEventClick() + ",", null);
		}
		if (fullCalendar.getDayClick() != null) {
			rw.writeText("    dayClick: " + fullCalendar.getDayClick() + ",", null);
		}
		rw.writeText("    events: " + fullCalendar.getEvents(), null);
		// TODO: add onchange listener that updates a hidden input field with $([\"id='" + clientId + "'\"]).fullCalendar('getEventSources') that contains the events
		rw.writeText("  });", null);
		rw.writeText("});", null);
		rw.endElement("script");
	}
}
