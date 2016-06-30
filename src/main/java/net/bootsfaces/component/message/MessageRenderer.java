/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
	private static boolean escapeWarningHasBeenShown=false;

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

		rw.startElement("div", message);
		writeAttribute(rw, "id", clientId);
		if (null != messageList && (!messageList.isEmpty())) {
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
			writeAttribute(rw, "style", findHighestSeverityStyle(messageList, message));
			writeAttribute(rw, "role", "alert");

			boolean onlyMostSevere = message.isOnlyMostSevere();
			FacesMessage mostSevere = null;
			if (onlyMostSevere) {
				for (FacesMessage msg : messageList) {
					if (null==mostSevere) {
						mostSevere=msg;
					} else if (msg.getSeverity().getOrdinal()>mostSevere.getSeverity().getOrdinal()) {
						mostSevere=msg;
					}
				}
			}

			boolean firstMessage=true;

			for (FacesMessage msg : messageList) {
				if (onlyMostSevere && msg!=mostSevere) {
					continue;
				}
				if (!firstMessage) {
					if (message.isLineBreak()) {
						rw.append(message.getLineBreakTag());
					}
				}
				firstMessage=false;

				if (message.isShowIcon()) {
					rw.startElement("span", component);
					writeAttribute(rw, "class", findHighestSeverityIcon(messageList, message) + " bf-message-icon");
					writeAttribute(rw, "aria-hidden", "true");
					rw.endElement("span");
				}

				if (message.isShowSummary() && msg.getSummary() != null &&
						!msg.getSummary().equals(msg.getDetail()) ) {
					rw.startElement("span", component);
					writeAttribute(rw, "class", "bf-message-summary");
					if (message.isEscape()) {
						rw.writeText(msg.getSummary(), null);
					} else {
						warnOnFirstUse();
						rw.write(msg.getSummary());
					}
					rw.endElement("span");
				}

				if (message.isShowDetail() && msg.getDetail() != null) {
					rw.startElement("span", component);
					writeAttribute(rw, "class", "bf-message-detail");
					if (message.isEscape()) {
						rw.writeText(msg.getDetail(), null);
					} else {
						warnOnFirstUse();
						rw.write(msg.getDetail());
					}

					rw.endElement("span");
				}
			}
		}
		rw.endElement("div");
	}

	public static void warnOnFirstUse() {
		if (!escapeWarningHasBeenShown) {
			System.out.println("One of your application's component deactivates HTML and JavaScript escaping. This is discouraged because it might be a security issue if not used carefully. Use at own risk.");
			escapeWarningHasBeenShown=true;
		}
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
			return ("alert-danger " + message.getFatalClass());
		if (hasError)
			return ("alert-danger " + message.getErrorClass());
		if (hasWarning)
			return ("alert-warning " + message.getWarnClass());

		return ("alert-info " + message.getInfoClass());
	}

	private String findHighestSeverityStyle(List<FacesMessage> messageList, Message message) {
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
			return message.getFatalStyle();
		if (hasError)
			return message.getErrorStyle();
		if (hasWarning)
			return message.getWarnStyle();
		return message.getInfoStyle();
	}

	private String findHighestSeverityIcon(List<FacesMessage> messageList, Message message) {
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
			return "fa fa-exclamation-circle";
		if (hasError)
			return "fa fa-exclamation-circle";
		if (hasWarning)
			return "fa fa-exclamation-triangle";
		return "fa fa-info-circle";
	}
}
