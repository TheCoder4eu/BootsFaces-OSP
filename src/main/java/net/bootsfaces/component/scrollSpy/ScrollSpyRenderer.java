/**
 *  Copyright 2014-2016 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.scrollSpy;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.scrollSpy.event.ScrollSpyEventListener;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.utils.BsfUtils;


/** This class generates the HTML code of &lt;b:scrollSpy /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.scrollSpy.ScrollSpy")
public class ScrollSpyRenderer extends CoreRenderer {

	/**
	 * Decode ajax behaviours specific to the components
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		ScrollSpy ss = (ScrollSpy) component;
		super.decode(context, ss);

		final ScrollSpyEventListener selectionListener = ss.getSelectionListener();
		String params = context.getExternalContext().getRequestParameterMap().get("params");
		if (params != null && selectionListener != null) {
			if (params != null) {
				params = params.replace("BsFEvent=", "");
				String[] pair = params.split(":", 2);
				String key = pair[0];
				String value = null;
				if (pair.length == 2) {
					value = pair[1];
				}
				if (value != null) {
					if ("itemSelected".equals(key)) {
						selectionListener.itemSelected(value);
					} else {
						throw new FacesException("Unexpected event when trying to decode the scrollspy event: " + key);
					}
				}
			}
		}
	}

	/**
	 * This methods generates the HTML code of the current b:scrollSpy.
	 * @param context the FacesContext.
	 * @param component the current b:scrollSpy.
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
		ScrollSpy scrollSpy = (ScrollSpy) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = scrollSpy.getClientId();

		// Get attributes
		String container = scrollSpy.getContainer();
		if(!BsfUtils.isStringValued(container)) {
			container = "body";
		} else container = "#" + decodeAndEscapeSelectors(context, component, container);

		String target = scrollSpy.getTarget();
		if(!BsfUtils.isStringValued(target)) {
			target = ".navbar";
		} else target = "#" + decodeAndEscapeSelectors(context, component, target);

		int offset = scrollSpy.getOffset();
		if(!BsfUtils.isStringValued(target)) {
			offset = 20;
		}
		boolean smooth = scrollSpy.isSmooth();
		int smoothSpeed = scrollSpy.getSmoothSpeed();
		boolean hasListeners = (scrollSpy.getSelectionListener() != null);
		// String updateItems = BsfUtils.GetOrDefault("'" + scrollSpy.getUpdate() + "'", "null");
		String updateItems = scrollSpy.getUpdate();
		if(updateItems != null)
			updateItems = ExpressionResolver.getComponentIDs(context, component, updateItems);
		if(hasListeners) {
			// check is inside form (MyFaces requires to be)
			final UIForm form = BsfUtils.getClosestForm(scrollSpy);
			if(form == null) {
				throw new FacesException("The scrollspy component must be inside a form if an actionlistener is defined", null);
			}
		}

		rw.startElement("script", component);
		rw.writeText("" +
					"$(document).ready(function() { " +
					"	$('" + container + "').scrollspy({ target: '" + target + "', offset: " + offset + "});", null);
		if(smooth) {
			rw.writeText("" +
					"$('" + target + " a').on('click', function(event) { " +
					"   console.log('click called'); " + 
					"	var hash = this.hash; " +
					"	$('" + ("body".equals(container) ? "html, body": container) + "').animate({ " +
					"		scrollTop: $(hash).parent().scrollTop() + $(hash).offset().top - $(hash).parent().offset().top " +
					"	}, { " +
					"       duration: " + smoothSpeed + ", " +
					"		specialEasing: { " +
			        "        	width: 'linear', " +
			        "        	height: 'easeOutBounce' " +
			        "	    } "+
					"	}); " +
			        "   event.preventDefault(); " +
					"}); ", null);
		}

		if(hasListeners) {
			rw.writeText("" +
					"$('" + target + "').on('activate.bs.scrollspy', function() { " +
					"	var x = $('" + target + " li.active > a').text(); " +
					"   BsF.ajax.callAjax(this, 'action', '" + updateItems + "', '" + clientId + "', null,  null, null, 'itemSelected:' + x); " +
		    		"}); ", null);
		}

		rw.writeText("});", null);
		rw.endElement("script");
	}

	/**
	 * Decode and escape selectors if necessary
	 * @param context
	 * @param component
	 * @param selector
	 * @return
	 */
	private String decodeAndEscapeSelectors(FacesContext context, UIComponent component, String selector) {
		selector = ExpressionResolver.getComponentIDs(context, component, selector);
		selector = BsfUtils.escapeJQuerySpecialCharsInSelector(selector);

		return selector;
	}
}
