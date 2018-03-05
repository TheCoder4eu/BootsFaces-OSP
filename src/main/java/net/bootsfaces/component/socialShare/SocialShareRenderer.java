/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.socialShare;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.utils.BsfUtils;

/** This class generates the HTML code of &lt;b:socialShare /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.socialShare.SocialShare")
public class SocialShareRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:socialShare.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:socialShare.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		SocialShare socialShare = (SocialShare) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = socialShare.getClientId();

		if (!BsfUtils.isStringValued(socialShare.getShares())) {
			return;
		}

		beginResponsiveWrapper(component, rw);

		rw.startElement("div", socialShare);
		rw.writeAttribute("id", clientId + "_wrapper", "id");

		// Create the div container
		rw.startElement("div", socialShare);
		rw.writeAttribute("id", clientId, "id");
		if (BsfUtils.isStringValued(socialShare.getStyle()))
			rw.writeAttribute("style", socialShare.getStyle(), "style");
		rw.writeAttribute("class", socialShare.getStyleClass(), "class");
		rw.endElement("div");

		rw.endElement("div");

		// decode shares
		String shares = "";
		String[] _share = socialShare.getShares().split(",");
		for (int i = 0; i < _share.length; i++) {
			_share[i] = "'" + _share[i] + "'";
			shares += _share[i];
			if (i < _share.length - 1)
				shares += ",";
		}

		String showCount = socialShare.getShowCount();
		if (!"false".equalsIgnoreCase(showCount) && !"true".equalsIgnoreCase(showCount)
				&& BsfUtils.isStringValued(showCount))
			showCount = "'" + showCount + "'";

		String scriptId = "#" + BsfUtils.escapeJQuerySpecialCharsInSelector(clientId);
		rw.startElement("script", socialShare);
		rw.writeText("$(function () { " +
		// social share section
				"$('" + scriptId + "').jsSocials({  "
				+ (BsfUtils.isStringValued(socialShare.getUrl()) ? "url: '" + socialShare.getUrl() + "', " : "")
				+ (BsfUtils.isStringValued(socialShare.getText()) ? "text: '" + socialShare.getText() + "', " : "")
				+ (BsfUtils.isStringValued(socialShare.getShareIn()) ? "shareIn: '" + socialShare.getShareIn() + "', "	: "")
				+ (BsfUtils.isStringValued(showCount) ? "showCount: " + showCount + ", " : "")
				+ (socialShare.isShowLabel() ? "showLabel: true, " : "showLabel: false,") + "shares: [" + shares + "] "
				+ "});" 
				+ (socialShare.isDisableBlock() ?
				// no block
						"" :
						// overlay
						"$('" + scriptId + "_wrapper').block({" 
						      + "	message: '" + socialShare.getBlockMessage() 
						      + "',"
						      + "	css: { " + "		border: 'none', " 
						      + "     padding: '8px', "
						      + "		backgroundColor: '#000', " 
						      + "		'-webkit-border-radius': '10px', "
						      + "		'-moz-border-radius': '10px', " 
						      + " 	'border-radius': '10px', "
						      + "		opacity: 1, " 
						      + "		color: '#fff', " + "		fontSize: '12px', "
						      + "		cursor: 'default', " 
						      + "		top: '40%', " 
						      + "		left: '35%' " 
						      + "	}, "
						      + " overlayCSS: { "
						      + "     backgroundColor: '#000', " 
						      + "     opacity: 0.6, "
						      + "     cursor: 'default' " 
						      + " } " 
						      + "}); " 
						      + "$('" + scriptId
						      + "_wrapper').click(function() { $('" + scriptId + "_wrapper').unblock(); }); ")
				              + "});", null);

		rw.endElement("script");
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		endResponsiveWrapper(component, context.getResponseWriter());
	}
}
