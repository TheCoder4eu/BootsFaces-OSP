/**
 *  Copyright 2015-2016 Stephan Rauh (http://www.beyondjava.net)
 *
 *  This file is part of BootsFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bootsfaces.component.poll;

import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.event.ListenerFor;
import jakarta.faces.event.ListenersFor;
import jakarta.faces.event.PostAddToViewEvent;

import java.io.IOException;

import jakarta.el.ValueExpression;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.html.HtmlCommandButton;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.event.ActionEvent;

import net.bootsfaces.C;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.utils.BsfUtils;

/**
 * The poll component refreshes a portion of the JSF view periodically via AJAX.
 *
 * @author Stephan Rauh
 * @author Jasper de Vries &lt;jepsar@gmail.com&gt;
 */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(Poll.COMPONENT_TYPE)
public class Poll extends HtmlCommandButton {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".poll.Poll";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	/**
	 * Property keys.
	 */
	enum PropertyKeys {
		autoUpdate, execute, interval, once, update, stop, widgetVar
	}

	public Poll() {
		setRendererType(null); // this component renders itself
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	public void decode(FacesContext context) {
		if (this.isDisabled()) {
			return;
		}

		String param = this.getClientId(context);
		if (context.getExternalContext().getRequestParameterMap().containsKey(param)) {
			queueEvent(new ActionEvent(this));
		}
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		String id = getClientId();
		String widgetVarName = getWidgetVar() == null ? BsfUtils.widgetVarName(id) : getWidgetVar();
		String intervalId = "window." + BsfUtils.javaScriptVarName(id);
		String update = ExpressionResolver.getComponentIDs(context, this, getUpdate());
		String execute = ExpressionResolver.getComponentIDs(context, this, getExecute());

		ResponseWriter rw = context.getResponseWriter();
		rw.append("<script id='" + id + "' type='text/javascript'>\r\n");
		if (isStop()) {
			rw.append("clearInterval(" + intervalId + ");\r\n");
		} else {
			rw.append(widgetVarName + " = new function(){\r\n");
			rw.append("var o = this;\r\n");
			rw.append("var handleError = function(){ o.stop(); console.log('error with b:poll " + id + "');};\r\n");
			rw.append("this.start = function(){ o.stop(); " + intervalId + " = setInterval(function(){ ");
			rw.append("faces.ajax.request('" + id + "', null, {'" + id + "':'" + id + "', execute:'" + execute
					+ "', render:'" + update + "', onerror:handleError }); }, " + getInterval() + "); };\r\n");
			rw.append("this.stop = function(){ clearInterval(" + intervalId + "); };\r\n");
			rw.append("this.start();\r\n");
			rw.append("}();\r\n");
		}
		rw.append("</script>");
	}

	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
			super.processEvent(event);
		}
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Setting this flag updates the widget on every AJAX request.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or , false, if it hasn't been set
	 *         by the JSF file.
	 */
	public boolean isAutoUpdate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.autoUpdate, false);
	}

	/**
	 * Setting this flag updates the widget on every AJAX request.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAutoUpdate(boolean _autoUpdate) {
		getStateHelper().put(PropertyKeys.autoUpdate, _autoUpdate);
	}

	/**
	 * Returns which input fields are to be sent? Default value: @none.
	 *
	 * @return Which input fields are to be sent? Default value: @none.
	 */
	public String getExecute() {
		return (String) getStateHelper().eval(PropertyKeys.execute, "@none");
	}

	/**
	 * Sets which input fields are to be sent?
	 *
	 * @param execute
	 *            Which input fields are to be sent?
	 */
	public void setExecute(String execute) {
		getStateHelper().put(PropertyKeys.execute, execute);
	}

	/**
	 * Returns number of milliseconds between polls. Default value is 1000.
	 *
	 * @return Number of milliseconds between polls. Default value is 1000.
	 */
	public Integer getInterval() {
		return (Integer) getStateHelper().eval(PropertyKeys.interval, 1000);
	}

	/**
	 * Sets number of milliseconds between polls.
	 *
	 * @param interval
	 *            Number of milliseconds between polls.
	 */
	public void setInterval(Integer interval) {
		getStateHelper().put(PropertyKeys.interval, interval);
	}

	/**
	 * Returns if the poll should called only once. Default value is false.
	 *
	 * @return If the poll should called only once. Default value is false.
	 */
	@Deprecated
	public boolean isOnce() {
		return (Boolean) getStateHelper().eval(PropertyKeys.once, false);
	}

	/**
	 * Sets if the poll should be called only once.
	 *
	 * @param once
	 *            Indicate if the poll should called only once.
	 */
	@Deprecated
	public void setOnce(boolean once) {
		getStateHelper().put(PropertyKeys.once, once);
	}

	/**
	 * Returns which region of the screen is to be updated? Default value: @form.
	 *
	 * @return Which region of the screen is to be updated? Default value: @form.
	 */
	public String getUpdate() {
		return (String) getStateHelper().eval(PropertyKeys.update, "@form");
	}

	/**
	 * Sets which region of the screen is to be updated?
	 *
	 * @param update
	 *            Which region of the screen is to be updated?
	 */
	public void setUpdate(String update) {
		getStateHelper().put(PropertyKeys.update, update);
	}

	/**
	 * Returns if the poll needs to stop. Default value: false.
	 *
	 * @return {@code true}, if the poll needs to stop. Default value: false.
	 */
	public boolean isStop() {
		return (Boolean) getStateHelper().eval(PropertyKeys.stop, false);
	}

	/**
	 * Sets if the poll needs to stop.
	 *
	 * @param stop
	 *            Indicate if the poll needs to stop.
	 */
	public void setStop(boolean stop) {
		getStateHelper().put(PropertyKeys.stop, stop);
	}

	/**
	 * Returns optional widget variable to access the poll widget in JavaScript
	 * code.
	 *
	 * @return Optional widget variable to access the poll widget in JavaScript
	 *         code.
	 */
	public String getWidgetVar() {
		return (String) getStateHelper().eval(PropertyKeys.widgetVar);
	}

	/**
	 * Sets optional widget variable to access the poll widget in JavaScript
	 * code.
	 *
	 * @param widgetVar
	 *            widget variable name.
	 */
	public void setWidgetVar(String widgetVar) {
		getStateHelper().put(PropertyKeys.widgetVar, widgetVar);
	}

}
