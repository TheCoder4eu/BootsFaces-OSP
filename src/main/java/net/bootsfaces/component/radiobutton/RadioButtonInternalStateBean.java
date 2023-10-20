package net.bootsfaces.component.radiobutton;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RadioButtonInternalStateBean {

	private Map<String, Boolean> inputHasAlreadyBeenRendered = new HashMap<String, Boolean>();

	public boolean inputHasAlreadyBeenRendered(String key) {
		boolean result = inputHasAlreadyBeenRendered.containsKey(key);
		inputHasAlreadyBeenRendered.put(key, true);
		return result;
	}

}
