package net.bootsfaces.component.ajax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIViewRoot;
import javax.faces.component.behavior.AjaxBehavior;
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
		Collection<String> eventNames = component.getEventNames();
		for (String keyClientBehavior : eventNames) {

			String jsCallback = "";
			String nameOfGetter = "getOn" + keyClientBehavior;
			try {
				Method[] methods = component.getClass().getMethods();
				for (Method m : methods) {
					if (m.getParameterTypes().length == 0) {
						if (m.getReturnType() == String.class) {
							if (m.getName().equalsIgnoreCase(nameOfGetter)) {
								jsCallback = (String) m.invoke(component);
								if (jsCallback == null)
									jsCallback = "";
								else if (!jsCallback.endsWith(";"))
									jsCallback += ";";
								break;
							}

						}
					}
				}
			} catch (ReflectiveOperationException ex) {
				System.err.println("Couldn't invoke method " + nameOfGetter);
			}

			String script = "";
			List<ClientBehavior> behaviors = clientBehaviors.get(keyClientBehavior);
			if (null != behaviors) {
				for (ClientBehavior cb : behaviors) {
					ClientBehaviorContext behaviorContext = ClientBehaviorContext.createClientBehaviorContext(context,
							(UIComponent) component, keyClientBehavior, null, null);
					String s = buildAjaxCommand(behaviorContext, (AjaxBehavior)cb, false);
					script += cb.getScript(behaviorContext) + ";";
				}
			}
			if (jsCallback.length() > 0 || script.length() > 0) {
				if (script.length() > 0 && "click".equals(keyClientBehavior))
					script += ";return false;";
				rw.writeAttribute("on" + keyClientBehavior, jsCallback + script, null);
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

	// ToDo - copied from Mojarra, and has to be adapted to BootsFaces AJAX
	private static String buildAjaxCommand(ClientBehaviorContext behaviorContext, AjaxBehavior ajaxBehavior,
			boolean namespaceParameters) {

		// First things first - if AjaxBehavior is disabled, we are done.
		if (ajaxBehavior.isDisabled()) {
			return null;
		}

		UIComponent component = behaviorContext.getComponent();
		String eventName = behaviorContext.getEventName();

		StringBuilder ajaxCommand = new StringBuilder(256);
		Collection<String> execute = ajaxBehavior.getExecute();
		Collection<String> render = ajaxBehavior.getRender();
		String onevent = ajaxBehavior.getOnevent();
		String onerror = ajaxBehavior.getOnerror();
		String sourceId = behaviorContext.getSourceId();
		String delay = ajaxBehavior.getDelay();
		Boolean resetValues = null;
		if (ajaxBehavior.isResetValuesSet()) {
			resetValues = ajaxBehavior.isResetValues();
		}
		Collection<ClientBehaviorContext.Parameter> params = behaviorContext.getParameters();

		// Needed workaround for SelectManyCheckbox - if execute doesn't have
		// sourceId,
		// we need to add it - otherwise, we use the default, which is
		// sourceId:child, which
		// won't work.
		ClientBehaviorContext.Parameter foundparam = null;
		for (ClientBehaviorContext.Parameter param : params) {
			if (param.getName().equals("incExec") && (Boolean) param.getValue()) {
				foundparam = param;
			}
		}
		if (foundparam != null && !execute.contains(sourceId)) {
			execute = new LinkedList<String>(execute);
			execute.add(component.getClientId());
		}
		if (foundparam != null) {
			try {
				// And since this is a hack, we now try to remove the param
				params.remove(foundparam);
			} catch (UnsupportedOperationException uoe) {
					System.err.println("Unsupported operation" + uoe);
			}
		}

		ajaxCommand.append("mojarra.ab(");

		if (sourceId == null) {
			ajaxCommand.append("this");
		} else {
			ajaxCommand.append("'");
			ajaxCommand.append(sourceId);
			ajaxCommand.append("'");
		}

		ajaxCommand.append(",event,'");
		ajaxCommand.append(eventName);
		ajaxCommand.append("',");

		appendIds(component, ajaxCommand, execute);
		ajaxCommand.append(",");
		appendIds(component, ajaxCommand, render);

		String namingContainerId = null;
		if (namespaceParameters) {
			FacesContext context = behaviorContext.getFacesContext();
			UIViewRoot viewRoot = context.getViewRoot();
			if (viewRoot instanceof NamingContainer) {
				namingContainerId = viewRoot.getContainerClientId(context);
			}
		}

		if ((namingContainerId != null) || (onevent != null) || (onerror != null) || (delay != null)
				|| (resetValues != null) || !params.isEmpty()) {

			ajaxCommand.append(",{");

			if (namingContainerId != null) {
				// the literal string must exactly match the corresponding value
				// in jsf.js.
				appendProperty(ajaxCommand, "com.sun.faces.namingContainerId", namingContainerId, true);
			}

			if (onevent != null) {
				appendProperty(ajaxCommand, "onevent", onevent, false);
			}

			if (onerror != null) {
				appendProperty(ajaxCommand, "onerror", onerror, false);
			}

			if (delay != null) {
				appendProperty(ajaxCommand, "delay", delay, true);
			}

			if (resetValues != null) {
				appendProperty(ajaxCommand, "resetValues", resetValues, false);
			}

			if (!params.isEmpty()) {
				for (ClientBehaviorContext.Parameter param : params) {
					appendProperty(ajaxCommand, param.getName(), param.getValue(), true);
				}
			}

			ajaxCommand.append("}");
		}

		ajaxCommand.append(")");

		return ajaxCommand.toString();
	}

	// ToDo - copied from Mojarra, and has to be adapted to BootsFaces AJAX
	// Appends an ids argument to the ajax command
	private static void appendIds(UIComponent component, StringBuilder builder, Collection<String> ids) {

		if ((null == ids) || ids.isEmpty()) {
			builder.append('0');
			return;
		}

		builder.append("'");

		boolean first = true;

		for (String id : ids) {
			if (id.trim().length() == 0) {
				continue;
			}
			if (!first) {
				builder.append(' ');
			} else {
				first = false;
			}

			if (id.equals("@all") || id.equals("@none") || id.equals("@form") || id.equals("@this")) {
				builder.append(id);
			} else {
				builder.append(getResolvedId(component, id));
			}
		}

		builder.append("'");
	}

	// ToDo - copied from Mojarra, and has to be adapted to BootsFaces AJAX
	// Returns the resolved (client id) for a particular id.
	private static String getResolvedId(UIComponent component, String id) {

		UIComponent resolvedComponent = component.findComponent(id);
		if (resolvedComponent == null) {
			if (id.charAt(0) == UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())) {
				return id.substring(1);
			}
			return id;
		}

		return resolvedComponent.getClientId();
	}
	
	// ToDo - copied from Mojarra, and has to be adapted to BootsFaces AJAX
	// Appends an name/value property pair to a JSON object.  Assumes
    // object has already been opened by the caller.
    public static void appendProperty(StringBuilder builder, 
                                      String name,
                                      Object value,
                                      boolean quoteValue) {

        if ((null == name) || (name.length() == 0))
            throw new IllegalArgumentException();


        char lastChar = builder.charAt(builder.length() - 1);
        if ((lastChar != ',') && (lastChar != '{'))
            builder.append(',');

        appendQuotedValue(builder, name);
        builder.append(":");

        if (value == null) {
            builder.append("''");
        } else if (quoteValue) {
            appendQuotedValue(builder, value.toString());
        } else {
            builder.append(value.toString());
        }
    }

	// ToDo - copied from Mojarra, and has to be adapted to BootsFaces AJAX
    // Append a script to the chain, escaping any single quotes, since
    // our script content is itself nested within single quotes.
    private static void appendQuotedValue(StringBuilder builder, 
                                          String script) {

        builder.append("'");

        int length = script.length();

        for (int i = 0; i < length; i++) {
            char c = script.charAt(i);

            if (c == '\'' || c == '\\') {
                builder.append('\\');
            } 

            builder.append(c);
        }

        builder.append("'");
    }


}
