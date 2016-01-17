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
import javax.faces.component.UIMessages;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;

/**
 *
 * @author duncan
 */
@FacesRenderer(componentFamily="javax.faces.Messages", rendererType="net.bootsfaces.component.MessagesRenderer")
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

        writer.writeAttribute("id", clientId, "id");

        for (String severity : messages.keySet()){
            List<FacesMessage> severityMessages = messages.get(severity);
            if (severityMessages.size() > 0){
                encodeSeverityMessages(facesContext, uiMessages, severity, severityMessages);
            }
        }
            
        writer.endElement("div");
    }

    private void encodeSeverityMessages(FacesContext facesContext, UIMessages uiMessages, String severity, List<FacesMessage> messages) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
        String styleClassPrefix = "";
        if ("warn".equals(severity)){
        	styleClassPrefix="alert-warning";
        }
        else {
            if ("fatal".equals(severity)){
                styleClassPrefix = "alert-danger";
            } else if ("error".equals(severity)) {
            	styleClassPrefix="alert-danger";
            } else {
                styleClassPrefix = "alert-"+severity;
            }
        }
        
        writer.startElement("div", null);
//        if (messages.size() > 1) styleClassPrefix = styleClassPrefix + " alert-block";
        
        writer.writeAttribute("class", "alert fadein "+styleClassPrefix, null);
        writer.writeAttribute("style", "padding:15px;margin-top:10px", null);
        
        writer.startElement("a", null);
        writer.writeAttribute("class", "close", null);
        writer.writeAttribute("data-dismiss","alert", null);
        writer.writeAttribute("href", "#", null);
        writer.write("&times;");
        writer.endElement("a");
        
        for (FacesMessage msg : messages){
            String summary = msg.getSummary() != null ? msg.getSummary() : "";
            String detail = msg.getDetail() != null ? msg.getDetail() : summary;
            
            writer.startElement("div", null);

            if (uiMessages.isShowSummary()){
                writer.startElement("strong", null);
                writer.writeText(summary, null);
                writer.endElement("strong");
            }
            
            if (uiMessages.isShowDetail()){
                writer.writeText(" "+detail, null);
            }
            
            writer.endElement("div");
            msg.rendered();
        }
        writer.endElement("div");
    }
    
    
}