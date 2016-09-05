/**
 *  Copyright 2015-2016 Duncan Bloem and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.message.MessageRenderer;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;

/**
 * @author duncan
 */
@FacesRenderer(componentFamily = "javax.faces.Messages", rendererType = "net.bootsfaces.component.messages.MessagesRenderer")
public class MessagesRenderer extends CoreRenderer {

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}

		Messages uiMessages = (Messages) component;
		ResponseWriter writer = facesContext.getResponseWriter();

		String clientId = uiMessages.getClientId(facesContext);

		List<FacesMessage> messagesToShow = new ArrayList<FacesMessage>();

		if (uiMessages.getFor()!=null) {
			if (uiMessages.isGlobalOnly()) {
				throw new FacesException("Error rendering b:messages: The attributes 'globalOnly' and 'for' are mutually exclusive.");
			}
			List<String> parentIds = resolveComponentIds(facesContext, uiMessages.getFor(), uiMessages);
			Iterator<String> clientIdsWithMessages = facesContext.getClientIdsWithMessages();
			while(clientIdsWithMessages.hasNext()) {
				String currentId = clientIdsWithMessages.next();
				boolean showIt=false;
				if (uiMessages.isRecursive()) {
					UIComponent c = facesContext.getViewRoot().findComponent(currentId);
					while (c != null && !showIt) {
						for (String parentId: parentIds) {
							if (c.getClientId().equals(parentId)) {
								showIt=true;
							}
						}
						c=c.getParent();
					}

				} else {
					for (String parentId: parentIds) {
						if (currentId.equals(parentId)) {
							showIt=true;
						}
					}
				}
				if (showIt) {
		            Iterator<FacesMessage> messagesIterator = facesContext.getMessages(currentId);
		            while (messagesIterator.hasNext()) {
		                FacesMessage next = messagesIterator.next();
		                if (!messagesToShow.contains(next)) {
		                	messagesToShow.add(next);
		                }
		            }
				}
			}
		} else {
			Iterator<FacesMessage> allMessages = uiMessages.isGlobalOnly() ? facesContext.getMessages(null)
					: facesContext.getMessages();
			while (allMessages.hasNext()) {
				messagesToShow.add(allMessages.next());
			}
		}

		Map<String, List<FacesMessage>> messages = new HashMap<String, List<FacesMessage>>();
		messages.put("info", new ArrayList<FacesMessage>()); // Bootstrap info
		messages.put("warn", new ArrayList<FacesMessage>()); // Bootstrap warning
		messages.put("error", new ArrayList<FacesMessage>()); // Bootstrap error
		messages.put("fatal", new ArrayList<FacesMessage>()); // Bootstrap fatal error

		for (FacesMessage message : messagesToShow) {
			Severity severity = message.getSeverity();
			if (message.isRendered() && !uiMessages.isRedisplay()) {
				continue;
			}

			if (severity.equals(FacesMessage.SEVERITY_INFO))
				messages.get("info").add(message);
			else if (severity.equals(FacesMessage.SEVERITY_WARN))
				messages.get("warn").add(message);
			else if (severity.equals(FacesMessage.SEVERITY_ERROR))
				messages.get("error").add(message);
			else if (severity.equals(FacesMessage.SEVERITY_FATAL))
				messages.get("fatal").add(message);
		}

		writer.startElement("div", uiMessages);
		if (null != uiMessages.getDir()) {
			writer.writeAttribute("dir", uiMessages.getDir(), "dir");
		}
		writeAttribute(writer, "class", "bf-messages" + Responsive.getResponsiveStyleClass(uiMessages, false));
		writeAttribute(writer, "role", "alert");

		writer.writeAttribute("id", clientId, "id");

		for (String severity : messages.keySet()) {
			List<FacesMessage> severityMessages = messages.get(severity);
			if (severityMessages.size() > 0) {
				encodeSeverityMessages(facesContext, uiMessages, severity, severityMessages);
			}
		}

		writer.endElement("div");
	}

	private void encodeSeverityMessages(FacesContext facesContext, Messages uiMessages, String severity,
			List<FacesMessage> messages) throws IOException {
		ResponseWriter writer = facesContext.getResponseWriter();
		String styleClassPrefix = "";
		String stylePrefix = uiMessages.getStyle();
		if (null == stylePrefix) {
			stylePrefix="";
		} else if (!styleClassPrefix.endsWith(";")) {
			stylePrefix += ";";
		}
		String iconStyleClass = "";
		if ("warn".equals(severity)) {
			String warnClass = uiMessages.getWarnClass();
			if (null == warnClass)
				styleClassPrefix = "alert-warning";
			else
				styleClassPrefix = "alert-warning " + warnClass;
			iconStyleClass = "fa fa-exclamation-triangle";
			stylePrefix += uiMessages.getWarnStyle();
		} else if ("fatal".equals(severity)) {
			String fatalClass = uiMessages.getFatalClass();
			if (null == fatalClass) {
				styleClassPrefix = "alert-danger";
			} else {
				styleClassPrefix = "alert-danger " + fatalClass;
			}
			stylePrefix += uiMessages.getFatalStyle();
			iconStyleClass = "fa fa-exclamation-circle";
		} else if ("error".equals(severity)) {
			String errorClass = uiMessages.getErrorClass();
			if (null == errorClass) {
				styleClassPrefix = "alert-danger";
			} else {
				styleClassPrefix = "alert-danger " + errorClass;
			}
			stylePrefix += uiMessages.getErrorStyle();
			iconStyleClass = "fa fa-exclamation-circle";
		} else if ("info".equals(severity)) {
			String infoClass = uiMessages.getInfoClass();
			if (infoClass==null) {
				styleClassPrefix = "alert-info";
			} else {
				styleClassPrefix = "alert-info " + infoClass;
			}
			stylePrefix += uiMessages.getInfoStyle();
			iconStyleClass = "fa fa-info-circle";
		}

		writer.startElement("div", null);

		writer.writeAttribute("class", "alert fadein " + styleClassPrefix, null);
		writer.writeAttribute("style", "padding:15px;margin-top:10px;" + stylePrefix, null);

		writer.startElement("a", null);
		writer.writeAttribute("class", "close", null);
		writer.writeAttribute("data-dismiss", "alert", null);
		writer.writeAttribute("href", "#", null);
		writer.write("&times;");
		writer.endElement("a");

		boolean firstMessage = true;
		for (FacesMessage msg : messages) {
			if (!firstMessage && uiMessages.isLineBreak())
				writer.append(uiMessages.getLineBreakTag());
			firstMessage = false;

			writer.startElement("span", null);
			writer.writeAttribute("class", "bf-message", null);

			if (uiMessages.isShowIcon()) {
				writer.startElement("span", null);
				writeAttribute(writer, "class", iconStyleClass + " bf-message-icon");
				writeAttribute(writer, "aria-hidden", "true");
				writer.endElement("span");
			}

			/*
			 * only show the summary, if it is neither deactivated nor the same
			 * as detail nor empty to prevent unwanted duplicated messages
			 */
			if (uiMessages.isShowSummary() && msg.getSummary() != null && !msg.getSummary().trim().isEmpty()
					&& !msg.getSummary().equals(msg.getDetail())) {
				writer.startElement("strong", null);
				writer.startElement("span", null);
				writeAttribute(writer, "class", "bf-message-summary");
				if (uiMessages.isEscape()) {
					writer.writeText(msg.getSummary(), null);
				} else {
					MessageRenderer.warnOnFirstUse();
					writer.write(msg.getSummary());
				}
				writer.endElement("span");
				writer.endElement("strong");
			}

			if (uiMessages.isShowDetail() && msg.getDetail() != null) {
				writer.startElement("span", null);
				writeAttribute(writer, "class", "bf-message-detail");
				if (uiMessages.isEscape()) {
					writer.writeText(msg.getDetail(), null);
				} else {
					MessageRenderer.warnOnFirstUse();
					writer.write(msg.getDetail());
				}
				writer.endElement("span");
			}

			writer.endElement("span");
			msg.rendered();
		}
		writer.endElement("div");
	}

	protected List<String> resolveComponentIds(FacesContext context, String forComponent, Messages message) {
		List<String> idList = new ArrayList<String>();
		if (null == forComponent || forComponent.length() == 0) {
			idList.add("");
		}
		else {
			String csvListOfIds = ExpressionResolver.getComponentIDs(context, message, forComponent);
			if (null != csvListOfIds) {
				String[] ids = csvListOfIds.split(" ");
				for (String id:ids) {
					idList.add(id);
				}
			}
		}
		return idList;
	}

}