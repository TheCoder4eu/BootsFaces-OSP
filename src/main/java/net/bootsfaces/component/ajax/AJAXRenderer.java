package net.bootsfaces.component.ajax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.PropertyNotFoundException;
import javax.faces.component.ActionSource;
import javax.faces.component.ActionSource2;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIViewRoot;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorHint;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;

import net.bootsfaces.component.commandButton.CommandButton;
import net.bootsfaces.component.inputText.InputText;
import net.bootsfaces.component.tabView.TabView;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;

public class AJAXRenderer extends CoreRenderer {

	public void decode(FacesContext context, UIComponent component) {
		if (componentIsDisabledOrReadonly(component)) {
			return;
		}
		String source = (String) context.getExternalContext().getRequestParameterMap().get("javax.faces.source");

		String id = component.getClientId(context);
		if (component instanceof InputText) {
			id = "input_" + id; // Todo remove this hack
		}
		// else if (component instanceof Icon) {
		// id += "_icon";
		// }

		if (component instanceof TabView && source != null) {
			for (UIComponent tab : component.getChildren()) {
				String tabId = tab.getClientId().replace(":", "_") + "_tab";
				if (source.equals(tabId)) {
					component = tab;
					id = tabId;
					break;
				}
			}
		}
		if (source == null) {
			// check for non-ajax call
			if (context.getExternalContext().getRequestParameterMap().containsKey(id)) {
				source = id;
			}
		}

		if (source != null && source.equals(id)) {
			String event = context.getExternalContext().getRequestParameterMap().get("javax.faces.partial.event");
			String nameOfGetter = "getOn" + event;
			try {
				Method[] methods = component.getClass().getMethods();
				for (Method m : methods) {
					if (m.getParameterTypes().length == 0) {
						if (m.getReturnType() == String.class) {
							if (m.getName().equalsIgnoreCase(nameOfGetter)) {
								String jsCallback = (String) m.invoke(component);
								if (jsCallback != null && jsCallback.contains("ajax:")) {
									if (component instanceof CommandButton && "action".equals(event)) {
										component.queueEvent(new ActionEvent(component));
									} else {
										FacesEvent ajaxEvent = new BootsFacesAJAXEvent(
												new AJAXBroadcastComponent(component), event, jsCallback);
										ajaxEvent.setPhaseId(PhaseId.INVOKE_APPLICATION);
										component.queueEvent(ajaxEvent);
									}
								}
								break;
							}

						}
					}
				}
			} catch (ReflectiveOperationException ex) {
				System.err.println("Couldn't invoke method " + nameOfGetter);
			}

			UIComponentBase bb = (UIComponentBase) component;
			Map<String, List<ClientBehavior>> clientBehaviors = bb.getClientBehaviors();
			for (Entry<String, List<ClientBehavior>> entry : clientBehaviors.entrySet()) {
				System.out.println(entry.getKey());
				List<ClientBehavior> value = entry.getValue();
				for (ClientBehavior bh : value) {
					ClientBehaviorContext behaviorContext = ClientBehaviorContext.createClientBehaviorContext(context,
							(UIComponent) component, entry.getKey(), null, null);
					String script = bh.getScript(behaviorContext);
					System.out.println(bh.getClass().getName() + " Client: " + script);
					if (bh instanceof AjaxBehavior) {
						Collection<String> execute = ((AjaxBehavior) bh).getExecute();
						Collection<String> render = ((AjaxBehavior) bh).getRender();
						String delay = ((AjaxBehavior) bh).getDelay();
						String onerror = ((AjaxBehavior) bh).getOnerror();
						String onevent = ((AjaxBehavior) bh).getOnevent();
						Set<ClientBehaviorHint> hints = bh.getHints();
						boolean disabled = ((AjaxBehavior) bh).isDisabled();
						boolean immediate = ((AjaxBehavior) bh).isImmediate();
						boolean resetValues = ((AjaxBehavior) bh).isResetValues();
						bh.decode(context, component);
					}
				}

			}

			if (component instanceof ActionSource) {
				ActionSource b = (ActionSource) component;
				ActionListener[] actionListeners = b.getActionListeners();
				if (null != actionListeners && actionListeners.length > 0) {
					component.queueEvent(new ActionEvent(component));
				}
				// for (ActionListener l: actionListeners) {
				//// l.processAction(new ActionEvent(component));
				// FacesEvent ajaxEvent = new BootsFacesAJAXActionListenerEvent(
				// component, event, l);
				// ajaxEvent.setPhaseId(PhaseId.INVOKE_APPLICATION);
				// component.queueEvent(ajaxEvent);
				// }

			}
			if (component instanceof ActionSource2) {
				MethodExpression actionExpression = ((ActionSource2) component).getActionExpression();
				if (null != actionExpression) {
					String expressionString = actionExpression.getExpressionString();
					System.out.println(expressionString);
					component.queueEvent(new ActionEvent(component));
					// actionExpression.invoke(context, params)
				}
			}

			// if (component instanceof CommandButton && (event == null ||
			// event.equals("click"))) {
			//// component.queueEvent(new ActionEvent(component));
			// FacesEvent ajaxEvent = new BootsFacesAJAXEvent(
			// new AJAXBroadcastComponent(component), event, null);
			// ajaxEvent.setPhaseId(PhaseId.INVOKE_APPLICATION);
			// component.queueEvent(ajaxEvent);
			// }
		}
	}

	/**
	 * Public API for the command button.
	 * 
	 * @param context
	 * @param component
	 * @param rw
	 * @throws IOException
	 */
	public static void generateBootsFacesAJAXAndJavaScriptForCommandButtons(FacesContext context,
			CommandButton component, ResponseWriter rw) throws IOException {
		// Render Ajax Capabilities and on<Event>-Handlers

		generateBootsFacesAJAXAndJavaScript(context, component, rw);

		// StringBuilder cJS = null;
		//
		// if (component.isAjax()) {
		// cJS = generateAJAXCall(context, component);
		// } else {
		// cJS = new StringBuilder(encodeClick(component));// Fix
		// // Chrome//+"document.forms['"+formId+"'].submit();");
		// }
		//
		// if (null != cJS && cJS.toString().length() > 1) {// Fix Chrome
		// rw.writeAttribute("onclick", cJS.toString(), null);
		// }
	}

	/**
	 * Public API for every input component (effectively everything except the
	 * command button).
	 * 
	 * @param context
	 * @param component
	 * @param rw
	 * @throws IOException
	 */
	public static void generateBootsFacesAJAXAndJavaScript(FacesContext context, ClientBehaviorHolder component,
			ResponseWriter rw) throws IOException {
		generateBootsFacesAJAXAndJavaScript(context, component, rw, null, null);

	}

	public static void generateBootsFacesAJAXAndJavaScript(FacesContext context, ClientBehaviorHolder component,
			ResponseWriter rw, String specialEvent, String specialEventHandler) throws IOException {
		boolean generatedAJAXCall = false;
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
								if (keyClientBehavior.equals(specialEvent)) {
									if (null == jsCallback || jsCallback.length() == 0)
										jsCallback = specialEventHandler;
									else
										jsCallback = jsCallback + ";javascript:" + specialEventHandler;
								}
								jsCallback = convertAJAXToJavascript(context, jsCallback, component);
								break;
							}
						}
					}
				}
			} catch (ReflectiveOperationException ex) {
				System.err.println("Couldn't invoke method " + nameOfGetter);
			}

			// TODO behaviors don't render correctly?
			String script = "";
			List<ClientBehavior> behaviors = clientBehaviors.get(keyClientBehavior);
			if (null != behaviors) {
				for (ClientBehavior cb : behaviors) {
					if (cb.getClass().getSimpleName().equals("AjaxBehavior")) {
						// ClientBehaviorContext behaviorContext =
						// ClientBehaviorContext.createClientBehaviorContext(context,
						// (UIComponent) component, keyClientBehavior, null,
						// null);
						StringBuilder s = generateAJAXCall(context, (IAJAXComponent) component, (AjaxBehavior) cb);
						script += s.toString() + ";";
					}
				}
			}
			if (jsCallback.contains("BsF.ajax.cb(") || script.contains("BsF.ajax.cb(")) {
				generatedAJAXCall = true;

			}
			// TODO end
			if (jsCallback.length() > 0 || script.length() > 0) {
				if (component instanceof CommandButton)
					if (jsCallback.length() > 0 && "click".equals(keyClientBehavior))
						script += ";return false;";
				rw.writeAttribute("on" + keyClientBehavior, jsCallback + script, null);
			}

		}
		if (!generatedAJAXCall) {
			// should we generate AJAX nonetheless?
			boolean ajax = ((IAJAXComponent) component).isAjax();
			ajax |= null != ((IAJAXComponent) component).getUpdate();

			if (ajax) {
				StringBuilder s = generateAJAXCall(context, (IAJAXComponent) component, null);
				String script = s.toString() + ";";
				String defaultEvent = ((IAJAXComponent) component).getDefaultEventName();
				rw.writeAttribute("on" + defaultEvent, script, null);
			}
		}
	}

	private static String convertAJAXToJavascript(FacesContext context, String jsCallback,
			ClientBehaviorHolder component) {
		if (jsCallback == null)
			jsCallback = "";
		else {
			if (jsCallback.contains("ajax:")) {
				int pos = jsCallback.indexOf("ajax:");
				String rest = "";
				int end = jsCallback.indexOf(";javascript:", pos);
				if (end >= 0) {
					rest = jsCallback.substring(end + ";javascript:".length());
					jsCallback = jsCallback.substring(0, end);
				}

				StringBuilder ajax = generateAJAXCall(context, (IAJAXComponent) component);

				jsCallback = jsCallback.substring(0, pos) + ";" + ajax + rest;
			}

			if (!jsCallback.endsWith(";"))
				jsCallback += ";";
		}
		return jsCallback;
	}

	/**
	 * Evaluates an EL expression into an object.
	 *
	 * @param p_expression
	 *            the expression
	 * @throws PropertyNotFoundException
	 *             if the attribute doesn't exist at all (as opposed to being
	 *             null)
	 * @return the object
	 */
	public static MethodExpression evalAsMethodExpression(String p_expression) throws PropertyNotFoundException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		MethodExpression vex = expressionFactory.createMethodExpression(elContext, p_expression, String.class,
				new Class[0]);
		return vex;
	}

	private static StringBuilder generateAJAXCall(FacesContext context, IAJAXComponent component) {
		String complete = component.getOncomplete();
		StringBuilder cJS = new StringBuilder(150);
		String update = ExpressionResolver.getComponentIDs(context, (UIComponent) component, component.getUpdate());
		cJS.append("BsF.ajax.cb(this, event").append(update == null ? "" : (",'" + update + "'"));
		if (complete != null) {
			cJS.append(",function(){" + complete + "}");
		}
		cJS.append(");");
		return cJS;
	}

	private static StringBuilder generateAJAXCall(FacesContext context, IAJAXComponent component,
			ClientBehavior ajaxBehavior) {
		StringBuilder cJS = new StringBuilder(150);
		// find default values
		String update = component.getUpdate();
		String oncomplete = component.getOncomplete();
		if (ajaxBehavior != null) {
			// the default values can be overridden by the AJAX behavior
			if (ajaxBehavior instanceof AjaxBehavior) {
				boolean disabled = ((AjaxBehavior) ajaxBehavior).isDisabled();
				if (!disabled) {
					String onerror = ((AjaxBehavior) ajaxBehavior).getOnerror(); // todo
					String onevent = ((AjaxBehavior) ajaxBehavior).getOnevent(); // todo
					Collection<String> execute = ((AjaxBehavior) ajaxBehavior).getExecute();
					if (null != execute && (!execute.isEmpty())) {
						update = "";
						for (String u : execute) {
							update += u + " ";
						}
					}

					Collection<String> render = ((AjaxBehavior) ajaxBehavior).getRender();
					oncomplete = component.getOncomplete();
				}
			}
		}

		update = ExpressionResolver.getComponentIDs(context, (UIComponent) component, update);
		cJS.append(encodeClick(component)).append("return BsF.ajax.cb(this, event")
				.append(update == null ? "" : (",'" + update + "'"));
		if (oncomplete != null) {
			cJS.append(",function(){" + oncomplete + "}");
		}
		cJS.append(");");

		return cJS;
	}

	private static String encodeClick(IAJAXComponent component) {
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

		return generateAJAXCall(behaviorContext.getFacesContext(), namespaceParameters, component, eventName,
				ajaxCommand, execute, render, onevent, onerror, sourceId, delay, resetValues, params);
	}

	private static String generateAJAXCall(FacesContext context, boolean namespaceParameters, UIComponent component,
			String eventName, StringBuilder ajaxCommand, Collection<String> execute, Collection<String> render,
			String onevent, String onerror, String sourceId, String delay, Boolean resetValues,
			Collection<ClientBehaviorContext.Parameter> params) {
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
	// Appends an name/value property pair to a JSON object. Assumes
	// object has already been opened by the caller.
	public static void appendProperty(StringBuilder builder, String name, Object value, boolean quoteValue) {

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
	private static void appendQuotedValue(StringBuilder builder, String script) {

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
