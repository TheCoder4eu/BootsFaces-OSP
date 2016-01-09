/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;

/** This class generates the HTML code of &lt;b:message /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.message.Message")
public class MessageRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:message.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:message.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}

		Message message = (Message) component;
		String forValue = message.getFor();
		if (null == forValue || forValue.length() == 0)
			forValue = "@previous";
		forValue = ExpressionResolver.getComponentIDs(context, message, forValue);

		List<FacesMessage> messageList = new ArrayList<FacesMessage>();
		Iterator<FacesMessage> messageIterator = FacesContext.getCurrentInstance().getMessages(forValue);
		while (messageIterator.hasNext()) {
			FacesMessage fm = messageIterator.next();
			messageList.add(fm);
		}
		ResponseWriter rw = context.getResponseWriter();
		String clientId = message.getClientId();

		if (null != messageList && (!messageList.isEmpty())) {
			rw.startElement("div", message);
			writeAttribute(rw, "id", clientId);
			if (null != message.getDir()) {
				rw.writeAttribute("dir", message.getDir(), "dir");
			}

			String styleClass = message.getStyleClass();
			if (null != styleClass && styleClass.length() > 0)
				styleClass = styleClass + " ";
			else
				styleClass = "";

			String severityClass = findHighestSeverityClass(messageList, message);
			// alert-danger
			styleClass += "alert " + severityClass + " bf-message";
			writeAttribute(rw, "class", styleClass);
			writeAttribute(rw, "role", "alert");

			for (FacesMessage msg : messageList) {
				if (message.isShowIcon()) {
					rw.startElement("span", component);
					writeAttribute(rw, "class", "glyphicon glyphicon-exclamation-sign");
					writeAttribute(rw, "aria-hidden", "true");
					rw.endElement("span");
				}
				if (message.isShowSummary()) {
					if (msg.getSummary() != null && (!msg.getSummary().equals(msg.getDetail()))) {
						rw.startElement("span", component);
						writeAttribute(rw, "class", "bf-message-summary");
						rw.writeText(msg.getSummary(), null);
						rw.endElement("span");
					}
				}
				if (message.isShowDetail()) {
					rw.startElement("span", component);
					writeAttribute(rw, "class", "bf-message-detail");
					rw.writeText(msg.getDetail(), null);
					rw.endElement("span");
				}
			}
			rw.endElement("div");
		}

		// rw.startElement("message", message);
		//
		// rw.writeAttribute("dir", message.getDir(), "dir");
		// rw.writeAttribute("errorClass", message.getErrorClass(),
		// "errorClass");
		// rw.writeAttribute("errorStyle", message.getErrorStyle(),
		// "errorStyle");
		// rw.writeAttribute("fatalClass", message.getFatalClass(),
		// "fatalClass");
		// rw.writeAttribute("fatalStyle", message.getFatalStyle(),
		// "fatalStyle");
		// rw.writeAttribute("for", message.getFor(), "for");
		// rw.writeAttribute("id", message.getId(), "id");
		// rw.writeAttribute("infoClass", message.getInfoClass(), "infoClass");
		// rw.writeAttribute("infoStyle", message.getInfoStyle(), "infoStyle");
		// rw.writeAttribute("rendered", String.valueOf(message.isRendered()),
		// "rendered");
		// rw.writeAttribute("showDetail",
		// String.valueOf(message.isShowDetail()), "showDetail");
		// rw.writeAttribute("showSummary",
		// String.valueOf(message.isShowSummary()), "showSummary");
		// rw.writeAttribute("redisplay", String.valueOf(message.isRedisplay()),
		// "redisplay");
		// rw.writeAttribute("style", message.getStyle(), "style");
		// rw.writeAttribute("styleClass", message.getStyleClass(),
		// "styleClass");
		// rw.writeAttribute("warnClass", message.getWarnClass(), "warnClass");
		// rw.writeAttribute("warnStyle", message.getWarnStyle(), "warnStyle");
		// rw.writeText("Dummy content of b:message", null);
		// rw.endElement("message");
	}

	private String findHighestSeverityClass(List<FacesMessage> messageList, Message message) {
		boolean hasFatal = false;
		boolean hasError = false;
		boolean hasWarning = false;
		for (FacesMessage msg : messageList) {
			Severity severity = msg.getSeverity();
			if (msg.isRendered() && !message.isRedisplay()) {
				continue;
			}

			if (severity.equals(FacesMessage.SEVERITY_WARN))
				hasWarning = true;
			else if (severity.equals(FacesMessage.SEVERITY_ERROR))
				hasError = true;
			else if (severity.equals(FacesMessage.SEVERITY_FATAL))
				hasFatal = true;
		}
		if (hasFatal)
			return "alert-danger";
		if (hasError)
			return "alert-danger";
		if (hasWarning)
			return "alert-warning";

		return "alert-info";
	}
}
