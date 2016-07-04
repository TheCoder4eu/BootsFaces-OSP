package net.bootsfaces.component.fullCalendar;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;

/**
 * @author jottyfan
 */
/** This class generates the HTML code of &lt;b:fullcalendar /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.fullcalendar.Fullcalendar")
public class FullCalendarRenderer extends CoreRenderer {

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
		
		rw.endElement("div");
		
		// activate the jquery plugin on that div
		rw.startElement("script", component);
		rw.writeText("$(document).ready(function() {", null);
		rw.writeText("  $(['id=" + clientId + "'].fullCalendar({", null);
		// TODO: add options and callbacks here
		rw.writeText("  })", null);
		rw.writeText("});", null);
		rw.endElement("script");
	}
}
