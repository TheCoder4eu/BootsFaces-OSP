/**
 *  Copyright 2014-2019 Dario D'Urzo
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

import net.bootsfaces.C;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreMessageRenderer;
import net.bootsfaces.utils.BsfUtils;

/**
 * TODO: Customizable animations. Line break support. Customization of styles
 * @author durzod
 *
 */
@FacesRenderer(componentFamily=C.BSFCOMPONENT, rendererType="net.bootsfaces.component.GrowlRenderer")
public class GrowlRenderer extends CoreMessageRenderer {
	
	static final String TEMPLATE = 
			"<div data-notify='container' class='col-xs-11 col-sm-3 alert alert-{0} {9}' role='alert'>\n" + 
		    "<div {8}>" +
			"	<button type='button' aria-hidden='true' class='close' data-notify='dismiss'>×</button>\n" + 
			"	<span data-notify='icon'></span>\n" + 
			"	<span data-notify='title'>{1}</span>\n" + 
			"	<span data-notify='message'>{2}</span>\n" + 
			"	<div class='progress' data-notify='progressbar'>\n" + 
			"		<div class='progress-bar progress-bar-{0}' role='progressbar' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100' style='width: 0%;'></div>\n" + 
			"	</div>\n" + 
			"	<a href='{3}' target='{4}' data-notify='url'></a>\n" + 
			"</div>"+
			"</div>";
	
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
            if (!shouldBeRendered(message, uiGrowl)){
                continue;
            }
            
            encodeSeverityMessage(facesContext, uiGrowl, message);
            message.rendered();
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
        
        String template = null;
        if (uiGrowl.getStyle()!= null) {
        		template = TEMPLATE.replace("{8}", "style='" + uiGrowl.getStyle() + "'");
        }
        if (uiGrowl.getStyleClass() != null) {
	        	if (null == template) {
	        		template = TEMPLATE;
	        }
	        	template = template.replace("{9}", uiGrowl.getStyleClass());
        }
        if (null == template) {
         	template="";
        }	else {
        		template = ", template: \"" + template.replace("{8}", "").replace("{9}", "").replace("\n", "") + "\"";
        		System.out.println(template);
        }

        writer.writeText("" +
				"$.notify({" + 
				"	title: '" + (uiGrowl.isShowSummary() ? summary : "") + "', " +
				"	message: '" + (uiGrowl.isShowDetail() ? detail : "") + "', " +
				"	icon: '" +  icon + "'" +
				"}, {" + 
				"	position: null, " + 
				"	type: '" + messageType + "', " +
				"	allow_dismiss: " + uiGrowl.isAllowDismiss() + ", " +
				"	newest_on_top: " + uiGrowl.isNewestOnTop() + ", " + 
				"     delay: " + uiGrowl.getDelay() + ", " + 
				"     timer: " + uiGrowl.getTimer() + ", " + 
				"	placement: { " + 
				"		from: '" + uiGrowl.getPlacementFrom() + "'," + 
				"		align: '" + uiGrowl.getPlacementAlign() + "'" + 
				"	}, " + 
				"	animate: { " + 
				"		enter: '" + uiGrowl.getAnimationEnter() + "', " +
				"		exit: '" + uiGrowl.getAnimationExit() + "' " +
				"	} " + 
				"     " + template +
				" }); " +
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
