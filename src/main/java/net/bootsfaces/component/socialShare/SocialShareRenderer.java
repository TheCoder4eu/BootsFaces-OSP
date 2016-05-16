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

package net.bootsfaces.component.socialShare;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.utils.BsfUtils;

/** This class generates the HTML code of &lt;b:socialShare /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.socialShare.SocialShare")
public class SocialShareRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:socialShare.
	 * @param context the FacesContext.
	 * @param component the current b:socialShare.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		SocialShare socialShare = (SocialShare) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = socialShare.getClientId();

		if(!BsfUtils.isStringValued(socialShare.getShares())) return;
		
		rw.startElement("div", socialShare);
		rw.writeAttribute("id", clientId + "_wrapper", "id");
		rw.writeAttribute("class", Responsive.getResponsiveStyleClass(socialShare, false), "class");
		
		// Create the div container
		rw.startElement("div", socialShare);
		rw.writeAttribute("id", clientId, "id");
		if(BsfUtils.isStringValued(socialShare.getStyle())) rw.writeAttribute("style", socialShare.getStyle(), "style");
		rw.writeAttribute("class", socialShare.getStyleClass(), "class");
		rw.endElement("div");
		
		rw.endElement("div");
		
		
		// decode shares
		String shares = ""; 
		String[] _share = socialShare.getShares().split(",");
		for(int i = 0; i < _share.length; i++) {
			_share[i] = "'" + _share[i] + "'";
			shares += _share[i];
			if(i < _share.length -1) shares += ",";
		}
		
		String showCount = socialShare.getShowCount();
		if(!"false".equalsIgnoreCase(showCount) && !"true".equalsIgnoreCase(showCount) && BsfUtils.isStringValued(showCount)) 
			showCount = "'" + showCount + "'";

		String scriptId = "#" + BsfUtils.escapeJQuerySpecialCharsInSelector(clientId);
		rw.startElement("script", socialShare);
		rw.writeText("$(function () { " +
						  // social share section
					      "$('" + scriptId + "').jsSocials({  " +
						      (BsfUtils.isStringValued(socialShare.getUrl()) ? 			"url: '" + socialShare.getUrl() + "', " : "") +
						      (BsfUtils.isStringValued(socialShare.getText()) ? 		"text: '" + socialShare.getText() + "', " : "") +
						      (BsfUtils.isStringValued(socialShare.getShareIn()) ? 		"shareIn: '" + socialShare.getShareIn() + "', " : "") +
						      (BsfUtils.isStringValued(showCount) ? 					"showCount: " + showCount + ", " : "") +
						      (socialShare.isShowLabel() ? 								"showLabel: true, ": "showLabel: false,") +
						      "shares: [" + shares + "] " +
						  "});" +
						  // overlay
						  "$('" + scriptId + "_wrapper').block({ message: 'Click to enable sharing links', css: {color: '#fff', fontSize: '12px', backgroundColor: 'transparent', border: '2px solid gray', left: '40%'} }); " + 
						  "$('" + scriptId + "_wrapper').click(function() { $('" + scriptId + "_wrapper').unblock(); }); " +
					  "});", null);
		
		rw.endElement("script");
	}
	
	/**
	 * Get the style class
	 * @param sb
	 * @return
	 *
	private String getStyleClass(SocialShare sb) {
		String sClass = sb.getStyleClass();
		if(sClass == null) sClass = "";
		
		sClass += Responsive.getResponsiveStyleClass(sb, false);
		
		return sClass;
	}
	*/
}
