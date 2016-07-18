package net.bootsfaces.component.radiobutton;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RadioButtonInternalStateBean {
	private Map<String, Boolean> inputHasAlreadyBeenRendered = new HashMap<String, Boolean>();

	public boolean inputHasAlreadyBeenRendered(String key) {
		boolean result = inputHasAlreadyBeenRendered.containsKey(key);
		inputHasAlreadyBeenRendered.put(key, true);
		return result;
	}

}
