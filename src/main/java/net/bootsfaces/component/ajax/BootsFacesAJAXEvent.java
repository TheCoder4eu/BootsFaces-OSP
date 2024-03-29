package net.bootsfaces.component.ajax;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.behavior.BehaviorBase;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.event.FacesListener;

public class BootsFacesAJAXEvent extends AjaxBehaviorEvent {
	private static final long serialVersionUID = 1L;

	private String jsCallback;

	private String event;

	public BootsFacesAJAXEvent(UIComponent source, String event, String jsCallback) {
		super(source, new BehaviorBase());
		this.jsCallback = jsCallback;
		this.event = event;
	}

	@Override
	public boolean isAppropriateListener(FacesListener faceslistener) {
//		System.out.println("BootsFacesAJAXListener - isAppropriate - " + (faceslistener.getClass().getName()));
		return false;
	}

	@Override
	public void processListener(FacesListener faceslistener) {
//		System.out.println("BootsFacesAJAXListener - process");
		// ((BootsFacesAJAXListener)
		// faceslistener).processAjaxBehavior(getComponent());
	}

	public String getJsCallback() {
		return jsCallback;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}