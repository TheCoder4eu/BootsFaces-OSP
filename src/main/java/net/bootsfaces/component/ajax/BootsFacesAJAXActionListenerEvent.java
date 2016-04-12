package net.bootsfaces.component.ajax;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesListener;

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
