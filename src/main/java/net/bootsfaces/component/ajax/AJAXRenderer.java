package net.bootsfaces.component.ajax;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.component.ActionSource;
import javax.faces.component.ActionSource2;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;

import net.bootsfaces.component.commandButton.CommandButton;
import net.bootsfaces.component.tabView.TabView;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;

public class AJAXRenderer extends CoreRenderer {
	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.component.ajax.AJAXRenderer");

	// local constants
	public static final String BSF_EVENT_PREFIX = "BsFEvent=";
	public static final String AJAX_EVENT_PREFIX = "ajax:";

	public void decode(FacesContext context, UIComponent component) {
		String id = component.getClientId(context);
		decode(context, component, id);
	}

	public void decode(FacesContext context, UIComponent component, String componentId) {
		if (componentIsDisabledOrReadonly(component)) {
			return;
		}
		String source = (String) context.getExternalContext().getRequestParameterMap().get("javax.faces.source");
		if (component instanceof TabView && source != null) {
			for (UIComponent tab : component.getChildren()) {
				String tabId = tab.getClientId().replace(":", "_") + "_tab";
				if (source.equals(tabId)) {
					component = tab;
					componentId = tabId;
					break;
				}
			}
		}

		if (source == null) {
			// check for non-ajax call
			if (context.getExternalContext().getRequestParameterMap().containsKey(componentId)) {
				source = componentId;
			}
		}
		if (source != null && source.equals(componentId)) {
			String event = context.getExternalContext().getRequestParameterMap().get("javax.faces.partial.event");
			String realEvent = (String) context.getExternalContext().getRequestParameterMap().get("params");
			if (null != realEvent && realEvent.startsWith(BSF_EVENT_PREFIX)) {
				realEvent = realEvent.substring(BSF_EVENT_PREFIX.length());
				if (!realEvent.equals(event)) {
					// System.out.println("Difference between event and
					// realEvent:" + event + " vs. " + realEvent
					// + " Component: " + component.getClass().getSimpleName());
					event = realEvent;
				}
			}
			String nameOfGetter = "getOn" + event;
			try {
				Method[] methods = component.getClass().getMethods();
				for (Method m : methods) {
					if (m.getParameterTypes().length == 0) {
						if (m.getReturnType() == String.class) {
							if (m.getName().equalsIgnoreCase(nameOfGetter)) {
								String jsCallback = (String) m.invoke(component);
								if (jsCallback != null && jsCallback.contains(AJAX_EVENT_PREFIX)) {
									if (component instanceof CommandButton && "action".equals(event)) {
										component.queueEvent(new ActionEvent(component));
									} else {
										FacesEvent ajaxEvent = new BootsFacesAJAXEvent(
												new AJAXBroadcastComponent(component), event, jsCallback);
										ajaxEvent.setPhaseId(PhaseId.INVOKE_APPLICATION);
										if (component instanceof ActionSource) {
											if (((ActionSource) component).isImmediate())
												ajaxEvent.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
										}
										component.queueEvent(ajaxEvent);
									}
								}
								break;
							}

						}
					}
				}
			} catch (Exception ex) {
				LOGGER.log(Level.WARNING, "Couldn't invoke method " + nameOfGetter);
			}

			if (null != event) {
				UIComponentBase bb = (UIComponentBase) component;
				Map<String, List<ClientBehavior>> clientBehaviors = bb.getClientBehaviors();
				for (Entry<String, List<ClientBehavior>> entry : clientBehaviors.entrySet()) {
					if (event.equals(entry.getKey())) {
						List<ClientBehavior> value = entry.getValue();
						for (ClientBehavior bh : value) {
							if (bh instanceof AjaxBehavior) {
								// String delay = ((AjaxBehavior)
								// bh).getDelay();
								bh.decode(context, component);
							}
						}
					}
				}
			}

			boolean addEventToQueue = false;
			if (component instanceof ActionSource) {
				ActionSource b = (ActionSource) component;
				ActionListener[] actionListeners = b.getActionListeners();
				if (null != actionListeners && actionListeners.length > 0) {
					addEventToQueue = true;
				}
			}
			if (component instanceof ActionSource2) {
				MethodExpression actionExpression = ((ActionSource2) component).getActionExpression();
				if (null != actionExpression) {
					addEventToQueue = true;
				}
			}
			if (addEventToQueue) {
				ActionEvent ae = new ActionEvent(component);
				if (component instanceof ActionSource) {
		            if (((ActionSource) component).isImmediate()) {
		                ae.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
		            } else {
		                ae.setPhaseId(PhaseId.INVOKE_APPLICATION);
		            }
				}
				component.queueEvent(ae );
			}
		}
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
		generateBootsFacesAJAXAndJavaScript(context, component, rw, null, null, false);
	}

	public static void generateBootsFacesAJAXAndJavaScript(FacesContext context, ClientBehaviorHolder component,
			ResponseWriter rw, String specialEvent, String specialEventHandler, boolean isJQueryCallback)
			throws IOException {
		boolean generatedAJAXCall = false;
		Collection<String> eventNames = component.getEventNames();
		if (null != eventNames) {
			for (String keyClientBehavior : eventNames) {
				if (null != ((IAJAXComponent) component).getJQueryEvents())
					if (((IAJAXComponent) component).getJQueryEvents().containsKey(keyClientBehavior))
						continue;
				generatedAJAXCall |= generateAJAXCallForASingleEvent(context, component, rw, specialEvent,
						specialEventHandler, isJQueryCallback, keyClientBehavior, null);
			}
		}
		if (!generatedAJAXCall) {
			// should we generate AJAX nonetheless?
			boolean ajax = ((IAJAXComponent) component).isAjax();
			ajax |= null != ((IAJAXComponent) component).getUpdate();

			if (ajax) {
				StringBuilder s = generateAJAXCallForClientBehavior(context, (IAJAXComponent) component,
						(ClientBehavior) null);
				String script = s.toString() + ";";
				String defaultEvent = ((IAJAXComponent) component).getDefaultEventName();
				if (component instanceof CommandButton)
					if (script.length() > 0 && "click".equals(defaultEvent))
						script += ";return false;";
				rw.writeAttribute("on" + defaultEvent, script, null);
			} else if (!(component instanceof CommandButton)) {
				// b:navCommandLink doesn't submit the form, so we need to use
				// AJAX
				boolean generateNonAJAXCommand = false;
				if (component instanceof ActionSource) {
					ActionSource b = (ActionSource) component;
					ActionListener[] actionListeners = b.getActionListeners();
					if (null != actionListeners && actionListeners.length > 0) {
						generateNonAJAXCommand = true;
					}
				}
				if (component instanceof ActionSource2) {
					MethodExpression actionExpression = ((ActionSource2) component).getActionExpression();
					if (null != actionExpression) {
						generateNonAJAXCommand = true;
					}
				}
				if (generateNonAJAXCommand && component instanceof IAJAXComponent) {
					// rw.writeAttribute("id", getClientId() + "_a", null);
					generateOnClickHandler(context, rw, (IAJAXComponent) component);
				}
			}
			// TODO: what about composite components?
		}
	}

	private static void generateOnClickHandler(FacesContext context, ResponseWriter rw, IAJAXComponent component)
			throws IOException {
		StringBuilder cJS = new StringBuilder(150); // optimize int
		cJS.append(encodeClick(component)).append("return BsF.ajax.cb(this, event);");

		rw.writeAttribute("onclick", cJS.toString(), null);
	}

	public static boolean generateAJAXCallForASingleEvent(FacesContext context, ClientBehaviorHolder component,
			ResponseWriter rw, String specialEvent, String specialEventHandler, boolean isJQueryCallback,
			String keyClientBehavior, StringBuilder generatedJSCode) throws IOException {
		boolean generatedAJAXCall = false;
		String jsCallback = "";
		String nameOfGetter = "getOn" + keyClientBehavior;
		try {
			Method[] methods = component.getClass().getMethods();
			for (Method m : methods) {
				if (m.getParameterTypes().length == 0) {
					if (m.getReturnType() == String.class) {
						if (m.getName().equalsIgnoreCase(nameOfGetter)) {
							jsCallback = (String) m.invoke(component);
							if (specialEventHandler != null && keyClientBehavior.equals(specialEvent)) {
								if (null == jsCallback || jsCallback.length() == 0)
									jsCallback = specialEventHandler;
								else
									jsCallback = jsCallback + ";javascript:" + specialEventHandler;
							}
							jsCallback = convertAJAXToJavascript(context, jsCallback, component, keyClientBehavior);
							if ("dragstart".equals(keyClientBehavior)) {
								rw.writeAttribute("draggable", "true", "draggable");
							}
							break;
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, "Couldn't invoke method " + nameOfGetter);
		}

		// TODO behaviors don't render correctly?
		// SR 19.09.2015: looks a bit odd, indeed. The method generateAJAXCall()
		// generates an onclick handler -
		// regardless of which event we currently deal with
		String script = "";
		Map<String, List<ClientBehavior>> clientBehaviors = component.getClientBehaviors();
		List<ClientBehavior> behaviors = clientBehaviors.get(keyClientBehavior);
		if (null != behaviors) {
			for (ClientBehavior cb : behaviors) {
				if (cb instanceof AjaxBehavior) {
					StringBuilder s = generateAJAXCallForClientBehavior(context, (IAJAXComponent) component,
							(AjaxBehavior) cb);
					script += s.toString() + ";";
				} else if (cb.getClass().getSimpleName().equals("AjaxBehavior")) {
					AjaxBehavior ab = new AjaxBehavior();
					Object disabled = readBeanAttribute(cb, "isDisabled");
					ab.setDisabled((Boolean) disabled);
					ab.setOnerror((String) readBeanAttribute(cb, "getOnerror"));
					ab.setRender((Collection<String>) readBeanAttributeAsCollection(cb, "getUpdate"));
					ab.setExecute((Collection<String>) readBeanAttributeAsCollection(cb, "getProcess"));
					ab.setOnevent(keyClientBehavior);
					StringBuilder s = generateAJAXCallForClientBehavior(context, (IAJAXComponent) component, ab);
					script += s.toString() + ";";
				}
			}
		}
		// TODO end
		if (jsCallback.contains("BsF.ajax.") || script.contains("BsF.ajax.")) {
			generatedAJAXCall = true;
		}
		if (!isJQueryCallback) {
			if (jsCallback.length() > 0 || script.length() > 0) {
				if (component instanceof CommandButton)
					if (generatedAJAXCall && "click".equals(keyClientBehavior))
						script += ";return false;";
				rw.writeAttribute("on" + keyClientBehavior, jsCallback + script, null);
			}
		}
		if (null != generatedJSCode) {
			generatedJSCode.setLength(0);
			if (jsCallback.length() > 0)
				generatedJSCode.append(jsCallback);
			if (script.length() > 0)
				generatedJSCode.append(script);
		}

		return generatedAJAXCall;
	}

	private static Object readBeanAttribute(Object bean, String getter) {
		try {
			Method method = bean.getClass().getMethod(getter);
			Object result = method.invoke(bean);
			return result;
		} catch (Exception e) {
			throw new FacesException("An error occured when reading the property " + getter + " from the bean "
					+ bean.getClass().getName(), e);
		}
	}

	private static Collection<String> readBeanAttributeAsCollection(Object bean, String getter) {
		Collection<String> result = null;
		try {
			Method method = bean.getClass().getMethod(getter);
			Object value = method.invoke(bean);
			if (null != value) {
				String[] partials = ((String) value).split(" ");
				result = new ArrayList<String>();
				for (String p : partials) {
					result.add(p);
				}
			}
			return result;
		} catch (Exception e) {
			throw new FacesException("An error occured when reading the property " + getter + " from the bean "
					+ bean.getClass().getName(), e);
		}
	}

	private static String convertAJAXToJavascript(FacesContext context, String jsCallback,
			ClientBehaviorHolder component, String event) {
		if (jsCallback == null)
			jsCallback = "";
		else {
			if (jsCallback.contains(AJAX_EVENT_PREFIX)) {
				int pos = jsCallback.indexOf(AJAX_EVENT_PREFIX);
				String rest = "";
				int end = jsCallback.indexOf(";javascript:", pos);
				if (end >= 0) {
					rest = jsCallback.substring(end + ";javascript:".length());
					jsCallback = jsCallback.substring(0, end);
				}

				StringBuilder ajax = generateAJAXCall(context, (IAJAXComponent) component, event);

				jsCallback = jsCallback.substring(0, pos) + ";" + ajax + rest;
			}

			if (!jsCallback.endsWith(";"))
				jsCallback += ";";
		}
		return jsCallback;
	}

	public static StringBuilder generateAJAXCall(FacesContext context, IAJAXComponent component, String event) {
		String complete = component.getOncomplete();
		String onError = null;
		String onSuccess = null;
		if (component instanceof IAJAXComponent2) {
			onError = ((IAJAXComponent2) component).getOnerror();
			onSuccess = ((IAJAXComponent2) component).getOnsuccess();
		}
		StringBuilder cJS = new StringBuilder(150);
		String update = component.getUpdate();
		if (null == update) {
			update = "@none";
		}
		update = ExpressionResolver.getComponentIDs(context, (UIComponent) component, update);
		String process = component.getProcess();
		if (null == process) {
			// see https://github.com/TheCoder4eu/BootsFaces-OSP/issues/371
			process = "@all";
		}

		if ("@all".equals(process) || "@none".equals(process)) {
			// these expressions are evaluated on the client side
		} else {
			process = ExpressionResolver.getComponentIDs(context, (UIComponent) component, process);
		}
		cJS.append("BsF.ajax.callAjax(this, event").append(",'" + update + "'").append(",'").append(process)
				.append("'");
		if (complete != null) {
			cJS.append(",function(){" + complete + "}");
		} else
			cJS.append(", null");
		if (onError != null) {
			cJS.append(",function(){" + onError + "}");
		} else
			cJS.append(", null");
		if (onSuccess != null) {
			cJS.append(",function(){" + onSuccess + "}");
		} else
			cJS.append(", null");
		if ((event != null) && (event.length() > 0)) {
			cJS.append(", '" + event + "'");
		}
		cJS.append(");");
		return cJS;
	}

	private static StringBuilder generateAJAXCallForClientBehavior(FacesContext context, IAJAXComponent component,
			ClientBehavior ajaxBehavior) {
		StringBuilder cJS = new StringBuilder(150);
		// find default values
		String update = component.getUpdate();
		String oncomplete = component.getOncomplete();
		String process = component.getProcess();
		String onevent = "";
		String onError = null;
		String onSuccess = null;
		if (component instanceof IAJAXComponent2) {
			onError = ((IAJAXComponent2) component).getOnerror();
			onSuccess = ((IAJAXComponent2) component).getOnsuccess();
		}
		if (ajaxBehavior != null) {
			// the default values can be overridden by the AJAX behavior
			if (ajaxBehavior instanceof AjaxBehavior) {
				boolean disabled = ((AjaxBehavior) ajaxBehavior).isDisabled();
				if (!disabled) {
					// String onerror = ((AjaxBehavior)
					// ajaxBehavior).getOnerror(); // todo
					onevent = ((AjaxBehavior) ajaxBehavior).getOnevent();
					if (onevent == null)
						onevent = "";
					Collection<String> execute = ((AjaxBehavior) ajaxBehavior).getExecute();
					if (null != execute && (!execute.isEmpty())) {
						for (String u : execute) {
							if (null == process)
								process = u;
							else
								process += " " + u;
						}
					} else {
						process = "@this";
					}

					Collection<String> render = ((AjaxBehavior) ajaxBehavior).getRender();
					if (null != render && (!render.isEmpty())) {
						update = "";
						for (String u : render) {
							update += u + " ";
						}
					}
					oncomplete = component.getOncomplete();
				}
			}
		}

		if ("@all".equals(process) || "@none".equals(process) ) {
			// these expressions are evaluated on the client side
		} else {
			process = ExpressionResolver.getComponentIDs(context, (UIComponent) component, process);
		}
		update = ExpressionResolver.getComponentIDs(context, (UIComponent) component, update);
		cJS.append(encodeClick(component)).append("BsF.ajax.callAjax(this, event")
				.append(update == null ? ",''" : (",'" + update + "'"))
				.append(process == null ? ",'@this'" : (",'" + process.trim() + "'"));
		if (oncomplete != null) {
			cJS.append(",function(){" + oncomplete + "}");
		} else
			cJS.append(", null");
		if (onError != null) {
			cJS.append(",function(){" + onError + "}");
		} else
			cJS.append(", null");
		if (onSuccess != null) {
			cJS.append(",function(){" + onSuccess + "}");
		} else
			cJS.append(", null");
		if ((onevent != null) && (onevent.length() > 0)) {
			cJS.append(", '" + onevent + "'");
			// cJS.append(", {'BsFEvent':'" + event+"'}'");
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

	/**
	 * Registers a callback with jQuery.
	 *
	 * @param context
	 * @param component
	 * @param rw
	 * @param jQueryExpressionOfTargetElement
	 * @param additionalEventHandlers
	 * @throws IOException
	 */
	public void generateBootsFacesAJAXAndJavaScriptForJQuery(FacesContext context, UIComponent component,
			ResponseWriter rw, String jQueryExpressionOfTargetElement, Map<String, String> additionalEventHandlers) throws IOException {
		if (jQueryExpressionOfTargetElement.contains(":")) {
			if (!jQueryExpressionOfTargetElement.contains("\\\\:")) { // avoid escaping twice
				jQueryExpressionOfTargetElement=jQueryExpressionOfTargetElement.replace(":", "\\\\:");
			}
		}
		IAJAXComponent ajaxComponent = (IAJAXComponent) component;
		Set<String> events = ajaxComponent.getJQueryEvents().keySet();
		for (String event : events) {
			StringBuilder code = new StringBuilder();
			String additionalEventHandler = null;
			if (null != additionalEventHandlers)
				additionalEventHandler = additionalEventHandlers.get(event);

			generateAJAXCallForASingleEvent(context, (ClientBehaviorHolder) component, rw, event,
					additionalEventHandler, true, event, code);
			if (code.length() > 0) {
				rw.startElement("script", component);
				String js = "$('" + jQueryExpressionOfTargetElement + "').on('" + ajaxComponent.getJQueryEvents().get(event)
						+ "', function(event){" + code.toString() + "});";
				rw.writeText(js, null);
				rw.endElement("script");
			}
		}
	}

	public String generateBootsFacesAJAXAndJavaScriptForAnMobileEvent(FacesContext context,
			ClientBehaviorHolder component, ResponseWriter rw, String clientId, String event) throws IOException {
		StringBuilder code = new StringBuilder();
		String additionalEventHandler = null;

		generateAJAXCallForASingleEvent(context, component, rw, event, additionalEventHandler, true, event, code);
		return code.toString();
	}
}
