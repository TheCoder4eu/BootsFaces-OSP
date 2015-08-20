package net.bootsfaces.component.ajax;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.BehaviorBase;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

public class BootsFacesAJAXEvent extends AjaxBehaviorEvent {
	private static final long serialVersionUID = 1L;
	
	private String jsCallback;
	
	private String event;
	

	public BootsFacesAJAXEvent(UIComponent source, String event, String jsCallback) {
		super(source, new BehaviorBase());
		this.jsCallback=jsCallback;
		this.event=event;
		System.out.println("Created AJAXBroadCastComponent for " + event + " " + source.getClass().getSimpleName());
	}

	@Override
	public boolean isAppropriateListener(FacesListener faceslistener) {
		return (faceslistener instanceof BootsFacesAJAXListener);
	}

	@Override
	public void processListener(FacesListener faceslistener) {
		((BootsFacesAJAXListener) faceslistener).processAjaxBehavior(getComponent());
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