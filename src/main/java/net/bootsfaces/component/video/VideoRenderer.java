/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.video;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:video /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.video.Video")
public class VideoRenderer extends CoreRenderer {
	
	@Override
	public void decode(FacesContext context, UIComponent component) {
		Video inputText = (Video) component;

		decodeBehaviors(context, inputText);

		String clientId = inputText.getClientId(context);
		String submittedId = (String) context.getExternalContext().getRequestParameterMap().get("javax.faces.source");
		if (clientId.equals(submittedId)) {
			new AJAXRenderer().decode(context, component, clientId);
		}
		else if ((clientId+"_video").equals(submittedId)) {
			new AJAXRenderer().decode(context, component, clientId+"_video");
		}
	}


	/**
	 * This methods generates the HTML code of the current b:video.
	 * @param context the FacesContext.
	 * @param component the current b:video.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		Video video = (Video) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = video.getClientId();
		boolean idHasBeenRendered=false;
		rw.startElement("div", video);
		String responsiveStyle = Responsive.getResponsiveStyleClass(video, false);
		if (null != responsiveStyle && responsiveStyle.trim().length()>0) {
			rw.writeAttribute("class", responsiveStyle, null);
			rw.writeAttribute("id", clientId, null);
			idHasBeenRendered=true;
		}
	
		rw.startElement("video", video);
		Tooltip.generateTooltip(context, video, rw);
		rw.writeAttribute("src", video.getSrc(), null);
		rw.writeAttribute("autoplay", video.isAutoplay(), null);
		rw.writeAttribute("controls", video.isControls(), null);
		rw.writeAttribute("loop", video.isLoop(), null);
		String videoId = clientId;
		if (idHasBeenRendered) {
			videoId = clientId + "_video";
		}
		rw.writeAttribute("id", videoId, null);
		String style = video.getStyle();
		if (null != style) {
			rw.writeAttribute("style", style, null);
		}
		String styleClass = video.getStyleClass();
		if (null == styleClass)
			styleClass = "";
		else
			styleClass = " " + styleClass;
		
		Tooltip.generateTooltip(context, video, rw);

		rw.writeAttribute("class", styleClass, "class");
		
		// Render Ajax Capabilities
		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), video, rw, false);
	
		rw.endElement("video");
		if (null != responsiveStyle && responsiveStyle.trim().length()>0) {
			rw.endElement("div");
		}		
		Tooltip.activateTooltips(context, video, videoId);	
	}

}
