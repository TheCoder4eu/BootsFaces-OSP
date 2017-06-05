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
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import net.bootsfaces.component.message.MessageRenderer;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreMessageRenderer;
import net.bootsfaces.render.Responsive;

/**
 * @author duncan
 */
@FacesRenderer(componentFamily = "javax.faces.Messages", rendererType = "net.bootsfaces.component.messages.MessagesRenderer")
public class MessagesRenderer extends CoreMessageRenderer {

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}

		Messages uiMessages = (Messages) component;
		ResponseWriter writer = facesContext.getResponseWriter();

		String clientId = uiMessages.getClientId(facesContext);

		List<FacesMessage> messagesToShow;

		if (uiMessages.getFor() != null) {
			if (uiMessages.isGlobalOnly()) {
				throw new FacesException("Error rendering b:messages: The attributes 'globalOnly' and 'for' are mutually exclusive.");
			}
			messagesToShow = new ArrayList<FacesMessage>();
			List<String> parentIds = resolveComponentIds(facesContext, uiMessages.getFor(), uiMessages);
			Iterator<String> clientIdsWithMessages = facesContext.getClientIdsWithMessages();
			while (clientIdsWithMessages.hasNext()) {
				String currentId = clientIdsWithMessages.next();
				boolean showIt = false;
				if (uiMessages.isRecursive()) {
					UIComponent c = facesContext.getViewRoot().findComponent(currentId);
					while (c != null && !showIt) {
						for (String parentId : parentIds) {
							if (c.getClientId().equals(parentId)) {
								showIt = true;
							}
						}
						c = c.getParent();
					}

				} else {
					for (String parentId : parentIds) {
						if (currentId.equals(parentId)) {
							showIt = true;
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
			messagesToShow = uiMessages.isGlobalOnly() ? facesContext.getMessageList(null)
					: facesContext.getMessageList();
		}

		Map<FacesMessage.Severity, List<FacesMessage>> messages = new LinkedHashMap<FacesMessage.Severity, List<FacesMessage>>();
		messages.put(FacesMessage.SEVERITY_FATAL, new ArrayList<FacesMessage>()); // Bootstrap fatal error
		messages.put(FacesMessage.SEVERITY_ERROR, new ArrayList<FacesMessage>()); // Bootstrap error
		messages.put(FacesMessage.SEVERITY_WARN, new ArrayList<FacesMessage>()); // Bootstrap warning
		messages.put(FacesMessage.SEVERITY_INFO, new ArrayList<FacesMessage>()); // Bootstrap info

		for (FacesMessage message : messagesToShow) {
			if (!shouldBeRendered(message, uiMessages)) {
				continue;
			}

			messages.get(message.getSeverity()).add(message);
		}

		int numberOfDivs = 0;
		boolean idHasBeenRendered = false;

		String responsiveStyleClass = Responsive.getResponsiveStyleClass(uiMessages, false).trim();
		if (!responsiveStyleClass.isEmpty()) {
			numberOfDivs++;
			writer.startElement("div", component);
			writeAttribute(writer, "class", responsiveStyleClass);
			writeAttribute(writer, "id", clientId);
			idHasBeenRendered = true;
		}

		numberOfDivs++;
		writer.startElement("div", uiMessages);
		if (null != uiMessages.getDir()) {
			writeAttribute(writer, "dir", uiMessages.getDir(), "dir");
		}
		writeAttribute(writer, "class", "bf-messages");
		writeAttribute(writer, "role", "alert");

		if (!idHasBeenRendered) {
			writeAttribute(writer, "id", clientId, "id");
		}

		for (Map.Entry<FacesMessage.Severity, List<FacesMessage>> entry : messages.entrySet()) {
			FacesMessage.Severity severity = entry.getKey();
			List<FacesMessage> severityMessages = entry.getValue();
			if (severityMessages.size() > 0) {
				encodeSeverityMessages(facesContext, uiMessages, severity, severityMessages);
				if (uiMessages.isOnlyMostSevere()) {
					break;
				}
			}
		}

		for (int i = numberOfDivs; i > 0; i--) {
			writer.endElement("div");
		}
	}

	private void encodeSeverityMessages(FacesContext facesContext, Messages uiMessages, FacesMessage.Severity severity,
			List<FacesMessage> messages) throws IOException {
		ResponseWriter writer = facesContext.getResponseWriter();
		String styleClassPrefix = "";
		if (null != uiMessages.getStyleClass()) {
			styleClassPrefix = uiMessages.getStyleClass() + " ";
		}
		String stylePrefix = uiMessages.getStyle();
		if (null == stylePrefix) {
			stylePrefix = "";
		} else if (!styleClassPrefix.endsWith(";")) {
			stylePrefix += ";";
		}
		String iconStyleClass = "";
		if (FacesMessage.SEVERITY_WARN.equals(severity)) {
			String warnClass = uiMessages.getWarnClass();
			if (null == warnClass) {
				styleClassPrefix += "alert-warning";
			} else {
				styleClassPrefix += "alert-warning " + warnClass;
			}
			iconStyleClass = "bficon bficon-warning-triangle-o";//"fa fa-exclamation-triangle";
			if (uiMessages.getWarnStyle() != null) {
				stylePrefix += uiMessages.getWarnStyle();
			}
		} else if (FacesMessage.SEVERITY_FATAL.equals(severity)) {
			String fatalClass = uiMessages.getFatalClass();
			if (null == fatalClass) {
				styleClassPrefix += "alert-danger";
			} else {
				styleClassPrefix += "alert-danger " + fatalClass;
			}
			if (uiMessages.getFatalStyle() != null) {
				stylePrefix += uiMessages.getFatalStyle();
			}
			iconStyleClass = "bficon bficon-error-circle-o";//"fa fa-exclamation-circle";
		} else if (FacesMessage.SEVERITY_ERROR.equals(severity)) {
			String errorClass = uiMessages.getErrorClass();
			if (null == errorClass) {
				styleClassPrefix += "alert-danger";
			} else {
				styleClassPrefix += "alert-danger " + errorClass;
			}
			if (uiMessages.getErrorStyle() != null) {
				stylePrefix += uiMessages.getErrorStyle();
			}
			iconStyleClass = "bficon bficon-error-circle-o";//"fa fa-exclamation-circle";
		} else if (FacesMessage.SEVERITY_INFO.equals(severity)) {
			String infoClass = uiMessages.getInfoClass();
			if (infoClass == null) {
				styleClassPrefix += "alert-info";
			} else {
				styleClassPrefix += "alert-info " + infoClass;
			}
			if (uiMessages.getInfoStyle() != null) {
				stylePrefix += uiMessages.getInfoStyle();
			}
			iconStyleClass = "bficon bficon-info";//"fa fa-info-circle";
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
			if (!firstMessage && uiMessages.isLineBreak()) {
				writer.append(uiMessages.getLineBreakTag());
			}
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
		} else {
			String csvListOfIds = ExpressionResolver.getComponentIDs(context, message, forComponent);
			if (null != csvListOfIds) {
				String[] ids = csvListOfIds.split(" ");
				Collections.addAll(idList, ids);
			}
		}
		return idList;
	}

}
