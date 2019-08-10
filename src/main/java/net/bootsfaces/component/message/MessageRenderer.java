/**
 * Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
 * <p>
 * This file is part of BootsFaces.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bootsfaces.component.message;

import java.io.IOException;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;

/** This class generates the HTML code of &lt;b:message /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.message.Message")
public class MessageRenderer extends CoreRenderer {
	private static boolean escapeWarningHasBeenShown = false;

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
		try {
		forValue = ExpressionResolver.getComponentIDs(context, message, forValue);
		} catch (FacesException ex) {
			if (null == message.getFor() || message.getFor().length() == 0) {
				throw new FacesException("<b:message> couldn't find its predecessor automatically. Please add the 'for' attribute to indicate which JSF element the message belongs to. id of the <b:message>: " + message.getClientId());
			}
		}

		List<FacesMessage> messageList = context.getMessageList(forValue);

		ResponseWriter rw = context.getResponseWriter();
		int numberOfDivs = 0;
		boolean idHasBeenRendered = false;

		String responsiveStyleClass = Responsive.getResponsiveStyleClass(message, false).trim();
		String clientId = message.getClientId(context);
		numberOfDivs++;
		rw.startElement("div", component);
		if (!responsiveStyleClass.isEmpty()) {
			writeAttribute(rw, "class", responsiveStyleClass);
		}
		writeAttribute(rw, "id", clientId);
		idHasBeenRendered = true;

		if (!messageList.isEmpty()) {
			numberOfDivs++;
			rw.startElement("div", message);
			if (!idHasBeenRendered) {
				writeAttribute(rw, "id", clientId);
			}
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

			writeAttribute(rw, "class", styleClass.trim());
			String style = message.getStyle();
			if (null == style)
				style = "";
			else if (!style.endsWith(";"))
				style += ";";
			String severityStyle = findHighestSeverityStyle(messageList, message);
			if (null == severityStyle)
				severityStyle = "";
			else if (!severityStyle.endsWith(";"))
				severityStyle += ";";

			writeAttribute(rw, "style", style + severityStyle);
			writeAttribute(rw, "role", "alert");

			boolean onlyMostSevere = message.isOnlyMostSevere();
			FacesMessage mostSevere = null;
			if (onlyMostSevere) {
				for (FacesMessage msg : messageList) {
					if (null == mostSevere || msg.getSeverity().getOrdinal() > mostSevere.getSeverity().getOrdinal()) {
						mostSevere = msg;
					}
				}
			}

			boolean firstMessage = true;

			for (FacesMessage msg : messageList) {
				if (onlyMostSevere && msg != mostSevere) {
					continue;
				}
				if (!firstMessage) {
					if (message.isLineBreak()) {
						rw.append(message.getLineBreakTag());
					}
				}
				firstMessage = false;

				if (message.isShowIcon()) {
					rw.startElement("span", component);
					writeAttribute(rw, "class", findHighestSeverityIcon(messageList, message) + " bf-message-icon");
					writeAttribute(rw, "aria-hidden", "true");
					rw.endElement("span");
				}

				if (message.isShowSummary() && msg.getSummary() != null && !msg.getSummary().equals(msg.getDetail())) {
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
				msg.rendered();
			}
		}
		for (int i = numberOfDivs; i > 0; i--) {
			rw.endElement("div");
		}
	}

	public static void warnOnFirstUse() {
		if (!escapeWarningHasBeenShown) {
			System.out.println(
					"One of your application's component deactivates HTML and JavaScript escaping. This is discouraged because it might be a security issue if not used carefully. Use at own risk.");
			escapeWarningHasBeenShown = true;
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
			return ("alert-danger " + (message.getFatalClass() == null ? "" : message.getFatalClass()));
		if (hasError)
			return ("alert-danger " + (message.getErrorClass() == null ? "" : message.getErrorClass()));
		if (hasWarning)
			return ("alert-warning " + (message.getWarnClass() == null ? "" : message.getWarnClass()));

		return ("alert-info " + (message.getInfoClass() == null ? "" : message.getInfoClass()));
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
			return "bficon bficon-error-circle-o";// "fa fa-exclamation-circle";
		if (hasError)
			return "bficon bficon-error-circle-o";// "fa fa-exclamation-circle";
		if (hasWarning)
			return "bficon bficon-warning-triangle-o";// "fa
		// fa-exclamation-triangle";
		return "bficon bficon-info";// "fa fa-info-circle";
	}
}
