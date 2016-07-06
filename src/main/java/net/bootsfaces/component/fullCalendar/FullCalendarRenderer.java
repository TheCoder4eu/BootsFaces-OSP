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
		
		// activate the jquery plugin on that div
		rw.startElement("script", null);
		
		rw.writeText("$(document).ready(function() {", null);
		rw.writeText("  $(\"[id='" + clientId + "']\").fullCalendar({", null);
		
		if (fullCalendar.getCalendarHeader() != null){
			rw.writeText("    header: " + fullCalendar.getCalendarHeader() + ",", null);
		}
		if (fullCalendar.getBusinessHours() != null){
			rw.writeText("    businessHours: " + fullCalendar.getBusinessHours() + ",", null);
		}
		if (fullCalendar.getHeight() != null){
			rw.writeText("    height: " + fullCalendar.getHeight() + ",", null);
		}
		if (fullCalendar.getLang() != null) {
			rw.writeText("    lang: '" + fullCalendar.getLang() + "',", null);
		}
		if (fullCalendar.getWeekNumbers() != null) {
			rw.writeText("    weekNumbers: " + fullCalendar.getWeekNumbers() + ",", null);
		}
		if (fullCalendar.getWeekends() != null) {
			rw.writeText("    weekends: " + fullCalendar.getWeekends() + ",", null);
		}
		if (fullCalendar.getEditable() != null) {
			rw.writeText("    editable: " + fullCalendar.getEditable() + ",", null);
		}
		rw.writeText("    weekMode: '" + fullCalendar.getWeekMode() + "',", null);
		rw.writeText("    events: " + fullCalendar.getEvents(), null);
		// TODO: add onchange listener that updates a hidden input field with $([\"id='" + clientId + "'\"]).fullCalendar('getEventSources') that contains the events
		rw.writeText("  });", null);
		rw.writeText("});", null);
		
		rw.endElement("script");
	}
}
