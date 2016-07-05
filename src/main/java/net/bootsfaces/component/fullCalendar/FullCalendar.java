package net.bootsfaces.component.fullCalendar;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIData;

/**
 * 
 * @author jottyfan
 *
 */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/moment.min.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/fullcalendar.min.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/fullcalendar.min.css", target = "head") })
@FacesComponent("net.bootsfaces.component.fullCalendar.FullCalendar")
public class FullCalendar extends UIData {

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
