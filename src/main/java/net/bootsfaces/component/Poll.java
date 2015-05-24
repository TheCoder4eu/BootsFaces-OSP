/**
 *  Copyright 2015 Stephan Rauh (http://www.beyondjava.net)
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */

package net.bootsfaces.component;

import java.io.IOException;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.PreRenderComponentEvent;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/**
 * The poll component refreshes a portion of the JSF view periodically via AJAX.
 * @author Stephan Rauh
 */

@ResourceDependencies({ @ResourceDependency(library = "javax.faces", name = "jsf.js", target = "body"), })
@FacesComponent(C.POLL_COMPONENT_TYPE)
public class Poll extends HtmlCommandButton {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.COMMANDBUTTON_COMPONENT_TYPE;
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public Poll() {
		setRendererType(null); // this component renders itself
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
//		super.encodeBegin(context);
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		Map<String, Object> attrs = getAttributes();
		ResponseWriter rw = context.getResponseWriter();


        context.getApplication().publishEvent(context,
                                              PreRenderComponentEvent.class,
                                              this);

		String id = getClientId();
		String update = (String) attrs.get("update");
		if (null == update) update="@form";
		String intervalAsString = (String) attrs.get("interval");
		if (null == intervalAsString) intervalAsString="1000";
		String intervalInMillseconds = intervalAsString;
		String execute = (String) attrs.get("execute");
		if (null == execute) execute="@none";
		String once = (String) attrs.get("once");
		boolean isOnce = "true".equalsIgnoreCase(once);

		rw.append("<script id='" + id + "' type='text/javascript'>");
		rw.append("\r\n");
		rw.append("new function() {");
		rw.append("\r\n");
		rw.append("   var pollId;");
		rw.append("\r\n");
		rw.append("  var handleError = function(){clearInterval(pollId);console.log('error with b:poll " + id + "');};");
		rw.append("\r\n");
		rw.append("  pollId = setInterval(function() {");
		rw.append("\r\n");
		if (isOnce) {
			rw.append("    clearInterval(pollId);");
			rw.append("\r\n");
		}
		rw.append("    jsf.ajax.request('" + id + "',null, {" + id + ":" + id + ", execute:'" + execute+"', render:'" + update + "', onerror:handleError	});");
		rw.append("\r\n");
		rw.append("  }, " + intervalInMillseconds + ");");
		rw.append("\r\n");
		rw.append("}();");
		rw.append("\r\n");

		
		rw.append("</script>");
//		popComponentFromEL(context);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
