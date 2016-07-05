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

rw.startElement("a", fullCalendar);
rw.writeAttribute("href", "#", null);
rw.endElement("a");
		
		// create div to be used as calendar; the calendar itself is added in encodeEnd's jquery function
//		rw.startElement("div", fullCalendar);
//		rw.writeAttribute("id", clientId, "id");
//rw.writeText("here comes the calendar", null);
//		rw.endElement("div");
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
		
rw.startElement("div", null); // debugging only	
rw.writeText("now javascript definition follows", null);
rw.endElement("div");
		
		// activate the jquery plugin on that div
//		rw.startElement("script", null);
//		
//		rw.writeText("$(document).ready(function() {", null);
//		rw.writeText("  $(\"[id='" + clientId + "']\").fullCalendar({", null);
//		// TODO: add options and callbacks here
//		// TODO: fill by content from fullCalendar.getData()
//		// TODO: add event listeners to update fullCalendar.getData()
//		rw.writeText("  });", null);
//		rw.writeText("});", null);
//		
//		rw.endElement("script");
	}
}
