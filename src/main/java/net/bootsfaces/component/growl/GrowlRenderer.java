package net.bootsfaces.component.growl;

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

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.utils.BsfUtils;

@FacesRenderer(componentFamily="javax.faces.Messages", rendererType="net.bootsfaces.component.GrowlRenderer")
public class GrowlRenderer extends CoreRenderer {
    @Override
    public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException {
        if (!component.isRendered()){
            return;
        }
        
        Growl uiGrowl = (Growl) component;
        ResponseWriter writer = facesContext.getResponseWriter();
        
        String clientId = uiGrowl.getClientId(facesContext);
        Iterator<FacesMessage> allMessages = uiGrowl.isGlobalOnly() ? facesContext.getMessages(null) : facesContext.getMessages();
        Map<String, List<FacesMessage>> messages = new HashMap<String, List<FacesMessage>>();
        messages.put("info", new ArrayList<FacesMessage>());  //Bootstrap info
        messages.put("warn", new ArrayList<FacesMessage>());  //Bootstrap warning
        messages.put("error", new ArrayList<FacesMessage>()); //Bootstrap Error
        messages.put("fatal", new ArrayList<FacesMessage>()); //Bootstrap Success
        
        while (allMessages.hasNext()) {
        	
            FacesMessage message = allMessages.next();
            Severity severity = message.getSeverity();
            if (message.isRendered() && !uiGrowl.isRedisplay()){
                continue;
            }
            
            if(severity.equals(FacesMessage.SEVERITY_INFO)) messages.get("info").add(message);
            else if(severity.equals(FacesMessage.SEVERITY_WARN)) messages.get("warn").add(message);
            else if(severity.equals(FacesMessage.SEVERITY_ERROR)) messages.get("error").add(message);
            else if(severity.equals(FacesMessage.SEVERITY_FATAL)) messages.get("fatal").add(message);
        }
        
        writer.startElement("script", uiGrowl);
        writer.writeAttribute("id", clientId, "id");
        writer.writeText("$(function() { ", null);
        for (String severity : messages.keySet()) {
            List<FacesMessage> severityMessages = messages.get(severity);
            if (severityMessages.size() > 0){
                encodeSeverityMessages(facesContext, uiGrowl, severity, severityMessages);
            }
        }
        writer.writeText("});", null);
            
        writer.endElement("script");
    }

    private void encodeSeverityMessages(FacesContext facesContext, Growl uiGrowl, String severity, List<FacesMessage> messages) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
        
        for (FacesMessage msg : messages) {
            String summary = msg.getSummary() != null ? msg.getSummary() : "";
            String detail = msg.getDetail() != null ? msg.getDetail() : summary;
            if(uiGrowl.isEscape()) {
            	summary = BsfUtils.escapeHtml(summary);
            	detail = BsfUtils.escapeHtml(detail);
            }
            summary = summary.replace("'", "\\\'");
            detail = detail.replace("'", "\\\'");
            // Decode messageType to map bootstrap alert styles
            String messageType = "success";
            if("info".equals(severity)) {
            	messageType = "info";
            }
            else if("warn".equals(severity)) {
            	messageType = "warning";
            }
            else if("error".equals(severity)) {
            	messageType = "danger";
            }
            else if("fatal".equals(severity)) {
            	messageType = "danger";
            }
            
            String icon = uiGrowl.getIcon() != null ? "glyphicon glyphicon-" + uiGrowl.getIcon() : "glyphicon glyphicon-warning-sign";
            
            String from = BsfUtils.StringOrDefault(uiGrowl.getPlacementFrom(), "top");
            String align = BsfUtils.StringOrDefault(uiGrowl.getPlacementAlign(), "right");
            
	        writer.writeText("" +
					" 	$.notify({" + 
					" 		title: '" + (uiGrowl.isShowSummary() ? summary : "") + "', " +
					" 		message: '" + (uiGrowl.isShowDetail() ? detail : "") + "', " +
					" 		icon: '" +  icon + "'" +
					" 	}, {" + 
					" 		position: null, " + 
					" 		type: '" + messageType + "', " +
					"		allow_dismiss: " + uiGrowl.isAllowDismiss() + ", " +
					"		newest_on_top: " + uiGrowl.isNewestOnTop() + ", " + 
					"       delay: " + uiGrowl.getDelay() + ", " + 
					"       timer: " + uiGrowl.getTimer() + ", " + 
					" 		placement: { " + 
					"			from: '" + from + "'," + 
					"			align: '" + align + "'" + 
					" 		}, " + 
					"		animate: { " + 
					"			enter: 'animated fadeInDown', " +
					"			exit: 'animated fadeOutUp' " +
					"		} " + 
					"   }); " +
					"", null);
        }
    }
}
