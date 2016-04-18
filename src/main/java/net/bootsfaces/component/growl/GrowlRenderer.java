/**
 *  Copyright 2014-2016 Dario D'Urzo
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
package net.bootsfaces.component.growl;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

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
        
        Iterator<FacesMessage> allMessages = uiGrowl.isGlobalOnly() ? facesContext.getMessages(null) : facesContext.getMessages();
        
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
				"			enter: 'animated fadeInDown', " +
				"			exit: 'animated fadeOutUp' " +
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
