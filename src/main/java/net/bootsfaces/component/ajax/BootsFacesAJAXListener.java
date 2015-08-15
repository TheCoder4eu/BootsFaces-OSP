package net.bootsfaces.component.ajax;

import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

public class BootsFacesAJAXListener implements AjaxBehaviorListener {

	@Override
	public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
		System.out.println("Process 1");
		
	}

	public void processAjaxBehavior(UIComponent component) {
		System.out.println("Process 2");
	}

}
