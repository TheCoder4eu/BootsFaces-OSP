/**
 *  Copyright 2014-2016 Dario D'Urzo
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
package net.bootsfaces.component.growl;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.utils.BsfUtils;

/**
 * TODO: Customizable animations. Line break support. Customization of styles
 * @author durzod
 *
 */
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
        
        // get the for value
        String forValue = uiGrowl.getFor();
       
        Iterator<FacesMessage> allMessages = null;
        if ( uiGrowl.isGlobalOnly() ) {
        	allMessages = facesContext.getMessages(null); 
        } else if(forValue != null && forValue.length() > 0) {
        	forValue = ExpressionResolver.getComponentIDs(facesContext, uiGrowl, forValue);
        	allMessages = facesContext.getMessages(forValue);
        } else {
        	allMessages = facesContext.getMessages();
        }
        
        writer.startElement("script", uiGrowl);
        writer.writeAttribute("id", clientId, "id");
        writer.writeText("$(function() { ", null);
        
        while (allMessages.hasNext()) {
            FacesMessage message = allMessages.next();
            if (message.isRendered() && !uiGrowl.isRedisplay()){
                continue;
            }
            
            encodeSeverityMessage(facesContext, uiGrowl, message);
        }
        writer.writeText("});", null);
        writer.endElement("script");
    }
    
    /**
     * Encode single faces message as growl
     * @param facesContext
     * @param uiGrowl
     * @param msg
     * @throws IOException
     */
    private void encodeSeverityMessage(FacesContext facesContext, Growl uiGrowl, FacesMessage msg) 
    throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();

        String summary = msg.getSummary() != null ? msg.getSummary() : "";
        String detail = msg.getDetail() != null ? msg.getDetail() : summary;
        if(uiGrowl.isEscape()) {
        	summary = BsfUtils.escapeHtml(summary);
        	detail = BsfUtils.escapeHtml(detail);
        }
        summary = summary.replace("'", "\\\'");
        detail = detail.replace("'", "\\\'");
        
        // get message type
        String messageType = getMessageType(msg);
        
        // get icon for message
        String icon = uiGrowl.getIcon() != null ? "fa fa-" + uiGrowl.getIcon() : getSeverityIcon(msg);

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
				"			from: '" + uiGrowl.getPlacementFrom() + "'," + 
				"			align: '" + uiGrowl.getPlacementAlign() + "'" + 
				" 		}, " + 
				"		animate: { " + 
				"			enter: '" + uiGrowl.getAnimationEnter() + "', " +
				"			exit: '" + uiGrowl.getAnimationExit() + "' " +
				"		} " + 
				"   }); " +
				"", null);
    }

    /**
     * Get severity related icons.
     * We use FA icons because future version of Bootstrap
     * does not support glyphicons anymore
     * @param message
     * @return
     */
	private String getSeverityIcon(FacesMessage message) {
		if (message.getSeverity().equals(FacesMessage.SEVERITY_WARN))
			return "fa fa-exclamation-triangle";
		else if (message.getSeverity().equals(FacesMessage.SEVERITY_ERROR))
			return "fa fa-times-circle";
		else if (message.getSeverity().equals(FacesMessage.SEVERITY_FATAL))
			return "fa fa-ban";
		return "fa fa-info-circle";
	}
	
	/**
	 * Translate severity type to growl style class
	 * @param message
	 * @return
	 */
	private String getMessageType(FacesMessage message) {
		if (message.getSeverity().equals(FacesMessage.SEVERITY_WARN))
			return "warning";
		else if (message.getSeverity().equals(FacesMessage.SEVERITY_ERROR))
			return "danger";
		else if (message.getSeverity().equals(FacesMessage.SEVERITY_FATAL))
			return "danger";
		else if (message.getSeverity().equals(FacesMessage.SEVERITY_INFO))
			return "info";
		return "success";
	}
}
