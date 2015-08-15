package net.bootsfaces.component.ajax;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.BehaviorBase;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

public class BootsFacesAJAXEvent extends AjaxBehaviorEvent {
	private static final long serialVersionUID = 1L;
	

	public BootsFacesAJAXEvent(UIComponent component) {
		super(component, new BehaviorBase());
	}

	@Override
	public boolean isAppropriateListener(FacesListener faceslistener) {
		return (faceslistener instanceof BootsFacesAJAXListener);
	}

	@Override
	public void processListener(FacesListener faceslistener) {
		((BootsFacesAJAXListener) faceslistener).processAjaxBehavior(getComponent());
	}
	
}