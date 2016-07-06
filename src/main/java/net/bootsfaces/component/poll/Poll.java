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

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;

import net.bootsfaces.C;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 * The poll component refreshes a portion of the JSF view periodically via AJAX.
 * 
 * @author Stephan Rauh
 */
@FacesComponent("net.bootsfaces.component.poll.Poll")
public class Poll extends HtmlCommandButton {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.poll.Poll";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public Poll() {
		setRendererType(null); // this component renders itself
		Tooltip.addResourceFiles();
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
	public void encodeBegin(FacesContext context) throws IOException {
		// super.encodeBegin(context);
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		Map<String, Object> attrs = getAttributes();
		ResponseWriter rw = context.getResponseWriter();

		/*
		 * context.getApplication().publishEvent(context,
		 * PreRenderComponentEvent.class, this);
		 */

		String id = getClientId();
		String update = (String) attrs.get("update");
		if (null == update)
			update = "@form";
		String intervalAsString = (String) attrs.get("interval");
		if (null == intervalAsString)
			intervalAsString = "1000";
		String intervalInMillseconds = intervalAsString;
		String execute = (String) attrs.get("execute");
		if (null == execute)
			execute = "@none";
		String once = (String) attrs.get("once");
		boolean isOnce = "true".equalsIgnoreCase(once);

		rw.append("<script id='" + id + "' type='text/javascript'>");
		rw.append("\r\n");
		rw.append("new function() {");
		rw.append("\r\n");
		rw.append("   var pollId;");
		rw.append("\r\n");
		rw.append(
				"  var handleError = function(){clearInterval(pollId);console.log('error with b:poll " + id + "');};");
		rw.append("\r\n");
		rw.append("  pollId = setInterval(function() {");
		rw.append("\r\n");
		if (isOnce) {
			rw.append("    clearInterval(pollId);");
			rw.append("\r\n");
		}
		rw.append("    jsf.ajax.request('" + id + "',null, {'" + id + "':'" + id + "', execute:'" + execute
				+ "', render:'" + update + "', onerror:handleError	});");
		rw.append("\r\n");
		rw.append("  }, " + intervalInMillseconds + ");");
		rw.append("\r\n");
		rw.append("}();");
		rw.append("\r\n");

		rw.append("</script>");
		// popComponentFromEL(context);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
}
