/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component.scrollUp;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;

/** This class generates the HTML code of &lt;b:scrollUp /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.scrollUp.ScrollUp")
public class ScrollUpRenderer extends CoreRenderer {
	
	/**
	 * This methods generates the HTML code of the current b:scrollUp.
	 * @param context the FacesContext.
	 * @param component the current b:scrollUp.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */  
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		// do nothing
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
	        return;
	    }
		ScrollUp scrollUp = (ScrollUp) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = scrollUp.getClientId();
		
		// mixed params (can be boolean or string)
		String scrollTitle = scrollUp.getTitle();
		if(scrollTitle != null && !"false".equalsIgnoreCase(scrollTitle)) scrollTitle = "'" + scrollTitle + "'";
		String scrollTrigger = scrollUp.getTrigger();
		if(scrollTrigger != null && !"false".equalsIgnoreCase(scrollTrigger)) scrollTrigger = "'" + scrollTrigger + "'";
		String scrollTarget =  scrollUp.getTarget();
		if(scrollTarget != null && !"false".equalsIgnoreCase(scrollTarget)) scrollTarget = "'" + scrollTarget + "'";
		String scrollOverlay = scrollUp.getOverlay();
		if(scrollOverlay != null && !"false".equalsIgnoreCase(scrollOverlay)) scrollOverlay = "'" + scrollOverlay + "'";
		
		rw.startElement("script", scrollUp);
		rw.writeAttribute("id", clientId, "id");
		rw.writeText("" + 
					 "$(function() {" +
					 "	$.scrollUp({ " +
					 (scrollUp.getName() != null ? " scrollName: '" + scrollUp.getName() + "', ": "") +
					 (scrollUp.getDistance() != 0 ? " scrollDistance: " + scrollUp.getDistance() + ", ": "") +
					 (scrollUp.getFrom() != null ? " scrollFrom: '" + scrollUp.getFrom() + "', ": "") +
					 (scrollUp.getSpeed() != 0 ? " scrollSpeed: " + scrollUp.getSpeed() + ", ": "") +
					 (scrollUp.getEasing() != null ? " easingType: '" + scrollUp.getEasing() + "', ": "") +
					 (scrollUp.getAnimation() != null ? " animation: '" + scrollUp.getAnimation() + "', ": "") +
					 (scrollUp.getAnimationSpeed() != 0 ? " animationSpeed: " + scrollUp.getAnimationSpeed() + ", ": "") +
					 (scrollUp.getText() != null ? " scrollText: '" + scrollUp.getText() + "', ": "") +
					 (scrollTitle!= null ? " scrollTitle: " + scrollTitle + ", ": "") +
					 (scrollUp.getImage() != null ? " scrollImg: " + scrollUp.getImage() + ", ": "") +
					 (scrollTrigger != null ? " scrollTrigger: " + scrollTrigger + ", ": "") +
					 (scrollTarget != null ? " scrollTarget: " + scrollTarget + ", ": "") +
					 (scrollOverlay != null ? " activeOverlay: " + scrollOverlay + ", ": "") +
					 "		zIndex: 2147483647 " +	
					 "	});" +  
					 "});", null);
		rw.endElement("script");
	}
}
