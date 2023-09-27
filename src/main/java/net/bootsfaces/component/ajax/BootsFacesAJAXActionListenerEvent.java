package net.bootsfaces.component.ajax;

import jakarta.faces.component.UIComponent;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;
import jakarta.faces.event.FacesListener;

public class BootsFacesAJAXActionListenerEvent extends ActionEvent {
	private static final long serialVersionUID = 1L;
	private ActionListener listener;

	public BootsFacesAJAXActionListenerEvent(UIComponent component, String event, ActionListener l) {
		super(component);
		listener = l;
	}

	@Override
	public boolean isAppropriateListener(FacesListener listener) {
		return true;
	}

	@Override
	public void processListener(FacesListener listener) {
		this.listener.processAction(this);
	}
}
