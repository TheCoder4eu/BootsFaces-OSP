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
import javax.faces.component.UIForm;
import javax.faces.component.UIParameter;
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
import net.bootsfaces.component.navCommandLink.NavCommandLink;
import net.bootsfaces.component.navLink.NavLink;
import net.bootsfaces.component.tabView.TabView;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;

public class AJAXRenderer extends CoreRenderer {
	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.component.ajax.AJAXRenderer");

	// local constants
	public static final String BSF_EVENT_PREFIX = "BsFEvent=";
	public static final String AJAX_EVENT_PREFIX = "ajax:";

	@Override
	public void decode(FacesContext context, UIComponent component) {
		String id = component.getClientId(context);
		decode(context, component, id);
	}

	public void decode(FacesContext context, UIComponent component, String componentId) {
		if (componentIsDisabledOrReadonly(component)) {
			return;
		}
		String source = context.getExternalContext().getRequestParameterMap().get("javax.faces.source");
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
		if (source != null && (source.equals(componentId) || (source.equals("input_" + componentId))
				|| (("input_" + source).equals(componentId)) || source.equals(componentId + "Inner")
				|| (source +  "_Input").equals(componentId))) {
			String event = context.getExternalContext().getRequestParameterMap().get("javax.faces.partial.event");
			String realEvent = context.getExternalContext().getRequestParameterMap().get("BsFEvent");
			if (null != realEvent) {
				if (!realEvent.equals(event)) {
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
										} else if (component instanceof IAJAXComponent) {
											if (((IAJAXComponent) component).isImmediate())
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
				component.queueEvent(ae);
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
	 * @param suppressAJAX
	 *            replaces the AJAX request by a BsF.submitForm(), but only if there
	 *            are parameters. Used by b:navCommandRenderer to implement an
	 *            action or an actionListener instead of rendering a simple link.
	 * @throws IOException
	 */
	public static void generateBootsFacesAJAXAndJavaScript(FacesContext context, ClientBehaviorHolder component,
			ResponseWriter rw, boolean suppressAJAX) throws IOException {
		generateBootsFacesAJAXAndJavaScript(context, component, rw, null, null, false, suppressAJAX);
	}

	public static void generateBootsFacesAJAXAndJavaScript(FacesContext context, ClientBehaviorHolder component,
			ResponseWriter rw, String specialEvent, String specialEventHandler, boolean isJQueryCallback,
			boolean suppressAJAX) throws IOException {
		boolean generatedAJAXCall = false;
		Collection<String> eventNames = component.getEventNames();
		Map<String, String> jQueryEvents = ((IAJAXComponent) component).getJQueryEvents();
		if (null != eventNames) {
			for (String keyClientBehavior : eventNames) {
				if (null != jQueryEvents)
					if (jQueryEvents.containsKey(keyClientBehavior))
						continue;
				generatedAJAXCall |= generateAJAXCallForASingleEvent(context, component, rw, null, specialEvent,
						specialEventHandler, isJQueryCallback, keyClientBehavior, null, null);
			}
		}
		if (generatedAJAXCall) {
			assertComponentIsInsideForm((UIComponent)component, "Please put every component triggering AJAX in a form tag.", true);
		}
		if (!generatedAJAXCall) {
			// should we generate AJAX nonetheless?
			boolean ajax = ((IAJAXComponent) component).isAjax();
			ajax |= null != ((IAJAXComponent) component).getUpdate();

			if (ajax) {
				// before generating an AJAX default handler, check if there's an jQuery handler
				// that's generated later
				if (null != jQueryEvents) {
					Set<String> events = jQueryEvents.keySet();
					for (String event : events) {
						String nameOfGetter = "getOn" + event;
						try {
							Method[] methods = component.getClass().getMethods();
							for (Method m : methods) {
								if (m.getParameterTypes().length == 0) {
									if (m.getReturnType() == String.class) {
										if (m.getName().equalsIgnoreCase(nameOfGetter)) {
											String jsCallback = (String) m.invoke(component);
											if (jsCallback != null && jsCallback.contains(AJAX_EVENT_PREFIX)) {
												ajax = false;
											}
											break;
										}

									}
								}
							}
						} catch (Exception e) {

						}
					}
				}
				if (ajax) {
					assertComponentIsInsideForm((UIComponent)component, "Please put every component triggering AJAX in a form tag.", true);
					StringBuilder s = generateAJAXCallForClientBehavior(context, (IAJAXComponent) component,
							(ClientBehavior) null);
					String script = s.toString() + ";";
					String defaultEvent = ((IAJAXComponent) component).getDefaultEventName();
					if (component instanceof CommandButton)
						if (script.length() > 0 && "click".equals(defaultEvent))
							script += ";return false;";
					rw.writeAttribute("on" + defaultEvent, script, null);
				}
			} else if (component instanceof CommandButton) {
				encodeFormSubmit((UIComponent) component, rw, false);
			} else {
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
					assertComponentIsInsideForm((UIComponent)component, "Please put every component triggering AJAX in a form tag.", true);
					generateOnClickHandler(rw, (IAJAXComponent) component, suppressAJAX);
				}
			}
			// TODO: what about composite components?
		}
	}

	private static void encodeFormSubmit(UIComponent component, ResponseWriter rw, boolean evenWithoutParameters)
			throws IOException {
		String parameterList = "";
		List<UIComponent> children = component.getChildren();
		for (UIComponent parameter : children) {
			if (parameter instanceof UIParameter) {
				String value = String.valueOf(((UIParameter) parameter).getValue());
				String name = ((UIParameter) parameter).getName();
				if (null != value) {
					parameterList += ",'" + name + "':'" + value + "'";
				}
			}
		}
		if (evenWithoutParameters || parameterList.length() > 0) {
			UIForm currentForm = getSurroundingForm((UIComponent) component, false);
			parameterList = "'" + currentForm.getClientId() + "',{'" + component.getClientId() + "':'"
					+ component.getClientId() + "'" + parameterList + "}";
			rw.writeAttribute("onclick",
					encodeClick((UIComponent) component) + "BsF.submitForm(" + parameterList + ");return false;", null);
		}
	}

	private static void generateOnClickHandler(ResponseWriter rw, IAJAXComponent component,
			boolean suppressAJAX) throws IOException {
		StringBuilder cJS = new StringBuilder(150); // optimize int
		if (suppressAJAX) {
			encodeFormSubmit((UIComponent) component, rw, true);
		} else {
			cJS.append(encodeClick((UIComponent) component)).append("return BsF.ajax.cb(this, event);");
		}

		rw.writeAttribute("onclick", cJS.toString(), null);
	}

	public static boolean generateAJAXCallForASingleEvent(FacesContext context, ClientBehaviorHolder component,
			ResponseWriter rw, StringBuilder code, String specialEvent, String specialEventHandler,
			boolean isJQueryCallback, String keyClientBehavior, StringBuilder generatedJSCode,
			String optionalParameterList) throws IOException {
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
							jsCallback = convertAJAXToJavascript(context, jsCallback, component, keyClientBehavior,
									optionalParameterList);
							if (null != code) {
								code.append(jsCallback);
							}
							if ("dragstart".equals(keyClientBehavior)) {
								if (null != rw) {
									rw.writeAttribute("draggable", "true", "draggable");
								}
							}
							break;
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, "Couldn't invoke method " + nameOfGetter + ". Additional information: ", ex);
		}

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
		if (jsCallback.contains("BsF.ajax.") || script.contains("BsF.ajax.")) {
			generatedAJAXCall = true;
		}
		if (!isJQueryCallback) {
			if (jsCallback.length() > 0 || script.length() > 0) {
				if (component instanceof CommandButton)
					if (generatedAJAXCall && "click".equals(keyClientBehavior))
						script += ";return false;";
				if (script.contains(jsCallback)) {
					// this happens when you combine onclick and f:ajax.
					// Both render the onclick attribute, but
					// in general it's hard to detect this situation because
					// different components have different default actions for
					// f:ajax. So let's simply use this hack.
					jsCallback = "";
				}
				if (null != rw) {
					rw.writeAttribute("on" + keyClientBehavior, jsCallback + script, null);
				}
				if (null != code) {
					code.append(jsCallback + script);
				}
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
			return method.invoke(bean);
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
			ClientBehaviorHolder component, String event, String optionalParameterList) {
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

				StringBuilder ajax = generateAJAXCall(context, (IAJAXComponent) component, event,
						optionalParameterList);

				jsCallback = jsCallback.substring(0, pos) + ";" + ajax + rest;
			}

			if (!jsCallback.endsWith(";"))
				jsCallback += ";";
		}
		return jsCallback;
	}

	public static StringBuilder generateAJAXCall(FacesContext context, IAJAXComponent component, String event,
			String optionalParameterList) {
		String complete = component.getOncomplete();
		String onError = null;
		String onSuccess = null;
		if (component instanceof IAJAXComponent2) {
			onError = ((IAJAXComponent2) component).getOnerror();
			onSuccess = ((IAJAXComponent2) component).getOnsuccess();
		}
		String delay = component.getDelay();
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
		} else
			cJS.append(", null");

		cJS.append(", " + delay);
		
		String parameterList = "";
		List<UIComponent> children = ((UIComponent) component).getChildren();
		for (UIComponent parameter : children) {
			if (parameter instanceof UIParameter) {
				String value = String.valueOf(((UIParameter) parameter).getValue());
				String name = ((UIParameter) parameter).getName();
				if (null != value) {
					parameterList += ",'" + name + "':'" + value + "'";
				}
			}
		}
		if (null != optionalParameterList) {
			parameterList += "," + optionalParameterList;
		}
		if (parameterList.length() > 0) {
			String json = ",{" + parameterList.substring(1) + "}";
			cJS.append(json);
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
		String delay = null;
		if (component instanceof IAJAXComponent2) {
			onError = ((IAJAXComponent2) component).getOnerror();
			onSuccess = ((IAJAXComponent2) component).getOnsuccess();
		}
		if (ajaxBehavior != null) {
			// the default values can be overridden by the AJAX behavior
			if (ajaxBehavior instanceof AjaxBehavior) {
				boolean disabled = ((AjaxBehavior) ajaxBehavior).isDisabled();
				if (!disabled) {
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
					delay = ((AjaxBehavior)ajaxBehavior).getDelay();
				}
			}
		}

		if ("@all".equals(process) || "@none".equals(process)) {
			// these expressions are evaluated on the client side
		} else {
			process = ExpressionResolver.getComponentIDs(context, (UIComponent) component, process);
		}
		if (update == null) {
			update = "";
		} else {
			update = ExpressionResolver.getComponentIDs(context, (UIComponent) component, update);
		}
		cJS.append(encodeClick((UIComponent) component)).append("BsF.ajax.callAjax(this, event")
				.append(update == null ? ",''" : (",'" + update + "'"))
				.append(process == null ? ",'@this'" : (",'" + process.trim() + "'"));
		if (oncomplete != null) {
			cJS.append(",function(){" + oncomplete + "}");
		} else
			cJS.append(",null");
		if (onError != null) {
			cJS.append(",function(){" + onError + "}");
		} else
			cJS.append(",null");
		if (onSuccess != null) {
			cJS.append(",function(){" + onSuccess + "}");
		} else
			cJS.append(",null");
		if ((onevent != null) && (onevent.length() > 0)) {
			cJS.append(", '" + onevent + "'");
		} else {
			cJS.append(",null");
		}
		if ((delay != null) && (delay.length() > 0)) {
			cJS.append(", '" + delay + "'");
		} else {
			cJS.append(",null");
		}
		String parameterList = "";
		List<UIComponent> children = ((UIComponent) component).getChildren();
		for (UIComponent parameter : children) {
			if (parameter instanceof UIParameter) {
				String value = String.valueOf(((UIParameter) parameter).getValue());
				String name = ((UIParameter) parameter).getName();
				if (null != value) {
					parameterList += ",'" + name + "':'" + value + "'";
				}
			}
		}
		if (parameterList.length() > 0) {
			String json = ",{" + parameterList.substring(1) + "}";
			cJS.append(json);
		}
		cJS.append(");");

		return cJS;
	}

	private static String encodeClick(UIComponent component) {
		String js;
		String oc = null;
		if (component instanceof IAJAXComponent) {
			oc = ((IAJAXComponent) component).getOnclick();
		}
		if (component instanceof NavLink) {
			oc = ((NavLink) component).getOnclick();
		}
		if (component instanceof NavCommandLink) {
			oc = ((NavCommandLink) component).getOnclick();
		}
		if (oc != null) {
			js = oc.endsWith(";") ? oc : oc + ";";
			if (js.contains("return ")) {
				js = "if (false===(function() {" + js + "})()) return false;";
			}
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
			ResponseWriter rw, String jQueryExpressionOfTargetElement, Map<String, String> additionalEventHandlers)
			throws IOException {
		generateBootsFacesAJAXAndJavaScriptForJQuery(context, component, rw, jQueryExpressionOfTargetElement,
				additionalEventHandlers, false);
	}

	/**
	 * Registers a callback with jQuery.
	 *
	 * @param context
	 * @param component
	 * @param rw
	 * @param jQueryExpressionOfTargetElement
	 * @param additionalEventHandlers
	 * @param attachOnReady
	 * @throws IOException
	 */
	public void generateBootsFacesAJAXAndJavaScriptForJQuery(FacesContext context, UIComponent component,
			ResponseWriter rw, String jQueryExpressionOfTargetElement, Map<String, String> additionalEventHandlers,
			boolean attachOnReady) throws IOException {
		if (jQueryExpressionOfTargetElement.contains(":")) {
			if (!jQueryExpressionOfTargetElement.contains("\\\\:")) { // avoid escaping twice
				jQueryExpressionOfTargetElement = jQueryExpressionOfTargetElement.replace(":", "\\\\:");
			}
		}
		IAJAXComponent ajaxComponent = (IAJAXComponent) component;
		Set<String> events = ajaxComponent.getJQueryEvents().keySet();
		for (String event : events) {
			StringBuilder code = new StringBuilder();
			String additionalEventHandler = null;
			if (null != additionalEventHandlers)
				additionalEventHandler = additionalEventHandlers.get(event);

			String parameterList = null;
			if (null != ajaxComponent.getJQueryEventParameterListsForAjax()) {
				if (null != ajaxComponent.getJQueryEventParameterListsForAjax().get(event))
					parameterList = ajaxComponent.getJQueryEventParameterListsForAjax().get(event);
			}
			generateAJAXCallForASingleEvent(context, (ClientBehaviorHolder) component, rw, null, event,
					additionalEventHandler, true, event, code, parameterList);
			if (code.length() > 0) {
				rw.startElement("script", component);
				parameterList = "event";
				if (null != ajaxComponent.getJQueryEventParameterLists()) {
					if (null != ajaxComponent.getJQueryEventParameterLists().get(event))
						parameterList = ajaxComponent.getJQueryEventParameterLists().get(event);
				}
				String js = "$('" + jQueryExpressionOfTargetElement + "').on('"
						+ ajaxComponent.getJQueryEvents().get(event) + "', function(" + parameterList + "){"
						+ code.toString() + "});";
				if (attachOnReady)
					js = "$(function() { " + js + " })";
				rw.writeText(js, null);
				rw.endElement("script");
			}
		}
	}

	public String generateBootsFacesAJAXAndJavaScriptForAnMobileEvent(FacesContext context,
			ClientBehaviorHolder component, ResponseWriter rw, String event) throws IOException {
		StringBuilder code = new StringBuilder();
		String additionalEventHandler = null;

		generateAJAXCallForASingleEvent(context, component, rw, null, event, additionalEventHandler, true, event, code,
				null);
		return code.toString();
	}
}
