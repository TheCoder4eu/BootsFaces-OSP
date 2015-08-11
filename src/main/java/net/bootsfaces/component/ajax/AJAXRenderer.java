package net.bootsfaces.component.ajax;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.component.commandButton.CommandButton;
import net.bootsfaces.expressions.ExpressionResolver;

public class AJAXRenderer {
	public static void generateJavaScriptHandlers(FacesContext context, CommandButton component, ResponseWriter rw,
			String CID, String type) throws IOException {
		// Render Ajax Capabilities and on<Event>-Handlers

		generateMojarraAjax(context, component, rw);

		StringBuilder cJS = null;
		String complete = component.getOncomplete();
		if (component.isAjax()) {
			cJS = generateAJAXCall(context, component, complete);
		} else {
			cJS = new StringBuilder(encodeClick(component));// Fix
			// Chrome//+"document.forms['"+formId+"'].submit();");
		}

		if (null != cJS && cJS.toString().length() > 1) {// Fix Chrome
			rw.writeAttribute("onclick", cJS.toString(), null);
		}
	}

	public static void generateMojarraAjax(FacesContext context, ClientBehaviorHolder component, ResponseWriter rw)
			throws IOException {
		Map<String, List<ClientBehavior>> clientBehaviors = component.getClientBehaviors();
		Set<String> keysClientBehavior = clientBehaviors.keySet();
		for (String keyClientBehavior : keysClientBehavior) {
			List<ClientBehavior> behaviors = clientBehaviors.get(keyClientBehavior);
			for (ClientBehavior cb : behaviors) {
				ClientBehaviorContext behaviorContext = ClientBehaviorContext.createClientBehaviorContext(context,
						(UIComponent)component, keyClientBehavior, null, null);
				rw.writeAttribute("on" + keyClientBehavior, cb.getScript(behaviorContext) + ";return false;", null);
			}

		}
	}

	private static StringBuilder generateAJAXCall(FacesContext context, CommandButton component, String complete) {
		StringBuilder cJS = new StringBuilder(150);
		String update = ExpressionResolver.getComponentIDs(context, component, component.getUpdate());
		cJS.append(encodeClick(component)).append("return BsF.ajax.cb(this, event")
				.append(update == null ? "" : (",'" + update + "'"));
		if (complete != null) {
			cJS.append(",function(){" + complete + "}");
		}
		cJS.append(");");
		return cJS;
	}

	private static String encodeClick(CommandButton component) {
		String js;
		String oc = (String) component.getOnclick();
		if (oc != null) {
			js = oc.endsWith(";") ? oc : oc + ";";
		} else {
			js = "";
		}

		return js;
	}
}
