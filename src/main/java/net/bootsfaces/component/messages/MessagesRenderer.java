/**
 *  Copyright 2015-2016 Duncan Bloem and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.message.MessageRenderer;
import net.bootsfaces.render.CoreRenderer;

/**
 * @author duncan
 */
@FacesRenderer(componentFamily="javax.faces.Messages", rendererType="net.bootsfaces.component.messages.MessagesRenderer")
public class MessagesRenderer extends CoreRenderer {
    
    @Override
    public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException{
        if (!component.isRendered()){
            return;
        }
        
        Messages uiMessages = (Messages) component;
        ResponseWriter writer = facesContext.getResponseWriter();
        
        String clientId = uiMessages.getClientId(facesContext);
        Iterator<FacesMessage> allMessages = uiMessages.isGlobalOnly() ? facesContext.getMessages(null) : facesContext.getMessages();
        Map<String, List<FacesMessage>> messages = new HashMap<String, List<FacesMessage>>();
        messages.put("info", new ArrayList<FacesMessage>());  //Bootstrap info
        messages.put("warn", new ArrayList<FacesMessage>());  //Bootstrap warning
        messages.put("error", new ArrayList<FacesMessage>()); //Bootstrap Error
        messages.put("fatal", new ArrayList<FacesMessage>()); //Bootstrap Success
        
        while (allMessages.hasNext()){
            FacesMessage message = allMessages.next();
            Severity severity = message.getSeverity();
            if (message.isRendered() && !uiMessages.isRedisplay()){
                continue;
            }
            
            if(severity.equals(FacesMessage.SEVERITY_INFO)) messages.get("info").add(message);
            else if(severity.equals(FacesMessage.SEVERITY_WARN)) messages.get("warn").add(message);
            else if(severity.equals(FacesMessage.SEVERITY_ERROR)) messages.get("error").add(message);
            else if(severity.equals(FacesMessage.SEVERITY_FATAL)) messages.get("fatal").add(message);
        }
        
        writer.startElement("div", uiMessages);
		if (null != uiMessages.getDir()) {
			writer.writeAttribute("dir", uiMessages.getDir(), "dir");
		}
		writeAttribute(writer, "class", "bf-messages");
		writeAttribute(writer, "role", "alert");

        writer.writeAttribute("id", clientId, "id");

        for (String severity : messages.keySet()){
            List<FacesMessage> severityMessages = messages.get(severity);
            if (severityMessages.size() > 0){
                encodeSeverityMessages(facesContext, uiMessages, severity, severityMessages);
            }
        }
            
        writer.endElement("div");
    }

    private void encodeSeverityMessages(FacesContext facesContext, Messages uiMessages, String severity, List<FacesMessage> messages) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
        String styleClassPrefix = "";
        String stylePrefix = "";
        String iconStyleClass="";
        if ("warn".equals(severity)){
        	styleClassPrefix="alert-warning " + uiMessages.getWarnClass();
        	iconStyleClass="fa fa-exclamation-triangle";
        	stylePrefix=uiMessages.getWarnStyle();
        }
		else if ("fatal".equals(severity)) {
			styleClassPrefix = "alert-danger " + uiMessages.getFatalClass();
        	stylePrefix=uiMessages.getFatalStyle();
        	iconStyleClass="fa fa-exclamation-circle";
		} else if ("error".equals(severity)) {
			styleClassPrefix = "alert-danger " + uiMessages.getErrorClass();
        	stylePrefix=uiMessages.getErrorStyle();
        	iconStyleClass="fa fa-exclamation-circle";
		} else if ("info".equals(severity)) {
			styleClassPrefix = "alert-info " + uiMessages.getInfoClass();
        	stylePrefix=uiMessages.getInfoStyle();
        	iconStyleClass="fa fa-info-circle";
		}        
        
        writer.startElement("div", null);
        
        writer.writeAttribute("class", "alert fadein "+styleClassPrefix, null);
        writer.writeAttribute("style", "padding:15px;margin-top:10px;"+stylePrefix, null);
        
        writer.startElement("a", null);
        writer.writeAttribute("class", "close", null);
        writer.writeAttribute("data-dismiss","alert", null);
        writer.writeAttribute("href", "#", null);
        writer.write("&times;");
        writer.endElement("a");
        
        boolean firstMessage = true;
        for (FacesMessage msg : messages){
        	if (!firstMessage && uiMessages.isLineBreak())
				writer.append(uiMessages.getLineBreakTag());
        	firstMessage = false;
			
            writer.startElement("span", null);
            writer.writeAttribute("class", "bf-message", null);
            
			if (uiMessages.isShowIcon()) {
				writer.startElement("span", null);
				writeAttribute(writer, "class", iconStyleClass +" bf-message-icon");
				writeAttribute(writer, "aria-hidden", "true");
				writer.endElement("span");
			}
			
			/* only show the summary, if it is neither deactivated nor the same as detail 
			 * nor empty to prevent unwanted duplicated messages*/
            if (uiMessages.isShowSummary() && msg.getSummary() != null
            		&& !msg.getSummary().trim().isEmpty()
            		&& !msg.getSummary().equals(msg.getDetail())){
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
            
            if (uiMessages.isShowDetail() && msg.getDetail() != null){
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
}