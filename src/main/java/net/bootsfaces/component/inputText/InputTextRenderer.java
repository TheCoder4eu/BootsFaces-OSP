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

package net.bootsfaces.component.inputText;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.inputSecret.InputSecret;
import net.bootsfaces.render.A;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

@FacesRenderer(componentFamily = C.BSFCOMPONENT, rendererType = "net.bootsfaces.component.InputTextRenderer")
public class InputTextRenderer extends CoreRenderer {

	@Override
	public void decode(FacesContext context, UIComponent component) {
		InputText inputText = (InputText) component;

		if (inputText.isDisabled() || inputText.isReadonly()) {
			return;
		}

		decodeBehaviors(context, inputText);

		String clientId = inputText.getClientId(context);
		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(clientId);

		if (submittedValue != null) {
			inputText.setSubmittedValue(submittedValue);

		}
	}
        
        @Override
	public Object getConvertedValue(FacesContext fc, UIComponent c, Object sval) throws ConverterException {
            Converter cnv = resolveConverter(fc, c);

            if (cnv != null) {
                return cnv.getAsObject(fc, c, (String) sval);
            } else {
                return sval;
            }
        }
        
    
        protected Converter resolveConverter(FacesContext context, UIComponent c) {
            if(!(c instanceof ValueHolder)) { return null; }

            Converter cnv = ((ValueHolder) c).getConverter();

            if(cnv != null) { return cnv; } 
            else {
                ValueExpression ve = c.getValueExpression("value");

                if(ve != null) {
                    Class<?> valType = ve.getType(context.getELContext());

                    if(valType != null) { return context.getApplication().createConverter(valType); }
                }

                return null;
            }
        }

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
		InputText inputText = (InputText) component;
		Map<String, Object> attrs = inputText.getAttributes();

		ResponseWriter rw = context.getResponseWriter();
		String clientId = inputText.getClientId();

		// Map<String, Object> attrs = getAttributes();
		// "Prepend" facet
		UIComponent prep = inputText.getFacet(C.PREPEND);
		// "Append" facet
		UIComponent app = inputText.getFacet(C.APPEND);
		boolean prepend = (prep != null);
		boolean append = (app != null);

		// If the facet contains only one component, getChildCount()=0 and the Facet is the UIComponent
		if (prepend) {
			R.addClass2FacetComponent(prep, "OutputText", InputText.ADDON);
                        R.setFacetComponentAttribute(prep, "Icon", "addon", "true");
                        R.setFacetComponentAttribute(prep, "IconAwesome", "addon", "true");
		}
		if (append) {
			R.addClass2FacetComponent(app, "OutputText", InputText.ADDON);
                        R.setFacetComponentAttribute(app, "Icon", "addon", "true");
                        R.setFacetComponentAttribute(app, "IconAwesome", "addon", "true");
		}

		String label = A.asString(attrs.get("label"));
		{
			Object rl = attrs.get(A.RENDERLABEL);
			if (null != rl) {
				if (!A.toBool(attrs.get(A.RENDERLABEL))) {
					label = null;
				}
			}
		}

		// Define TYPE ( if null set default = text )
		// support for b:inputSecret
		String t;
		if (component instanceof InputSecret) {
			t = H.PASSWORD;
		} else { // ordinary input fields
			t = A.asString(attrs.get("type"));
			if (t == null)
				t = "text";
		}

		rw.startElement("div", component);
		Tooltip.generateTooltip(context, attrs, rw);
		rw.writeAttribute("class", "form-group", "class");
		if (label != null) {
			rw.startElement("label", component);
			rw.writeAttribute("for", clientId, "for");
			rw.writeText(label, null);
			rw.endElement("label");
		}
		if (append || prepend) {
			rw.startElement("div", component);
			rw.writeAttribute("class", "input-group", "class");
		}
		int span = A.toInt(attrs.get(A.SPAN));
		if (span > 0) {
			rw.startElement("div", component);
			rw.writeAttribute("class", "col-md-" + span, "class");
		}

		if (prepend) {
			if (prep.getClass().getName().endsWith("Button")
					|| (prep.getChildCount() > 0 && prep.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement("div", inputText);
				rw.writeAttribute("class", "input-group-btn", "class");
				prep.encodeAll(context);
				rw.endElement("div");
			} else {
				prep.encodeAll(context);
			}
		}
		// Input
		rw.startElement("input", inputText);
		rw.writeAttribute("id", clientId, null);
		rw.writeAttribute("name", clientId, null);
		rw.writeAttribute("type", t, null);

		StringBuilder sb;
		String s;
		sb = new StringBuilder(20); // optimize int
		sb.append("form-control");
		String fsize = A.asString(attrs.get(A.FIELDSIZE));

		if (fsize != null) {
			sb.append(" input-").append(fsize);
		}
		// styleClass and class support
		String sclass = A.asString(attrs.get(H.STYLECLASS));
		if (sclass != null) {
			sb.append(" ").append(sclass);
		}
		if (inputText.isRequired()) {
			sb.append(" ").append("bf-required");
		}
		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute("class", s, H.STYLECLASS);
		}

		String ph = A.asString(attrs.get(A.PHOLDER));
		if (ph != null) {
			rw.writeAttribute(H.PHOLDER, ph, null);
		}

		if (A.toBool(attrs.get(A.DISABLED))) {
			rw.writeAttribute(A.DISABLED, A.DISABLED, null);
		}
		if (A.toBool(attrs.get(A.READONLY))) {
			rw.writeAttribute(A.READONLY, A.READONLY, null);
		}

		// Encode attributes (HTML 4 pass-through + DHTML)
		// R.encodeHTML4DHTMLAttrs(rw, attrs, A.INPUT_TEXT_ATTRS);
		renderPassThruAttributes(context, component, A.INPUT_TEXT_ATTRS);

		if ((A.asString(attrs.get("autocomplete")) != null) && (A.asString(attrs.get("autocomplete")).equals("off"))) {
			rw.writeAttribute("autocomplete", "off", null);
		}
		// Render Value
		String v = R.getValue2Render(context, component);
		rw.writeAttribute("value", v, null);

		// Render Ajax Capabilities
		AJAXRenderer.generateMojarraAjax(FacesContext.getCurrentInstance(), inputText, rw);

		rw.endElement("input");
		if (append) {
			if (app.getClass().getName().endsWith("Button")
					|| (app.getChildCount() > 0 && app.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement("div", inputText);
				rw.writeAttribute("class", "input-group-btn", "class");
				app.encodeAll(context);
				rw.endElement("div");
			} else {
				app.encodeAll(context);
			}
		}

		if (append || prepend) {
			rw.endElement("div");
		} // input-group
		rw.endElement("div"); // form-group
		if (span > 0) {
			rw.endElement("div"); // span
			// rw.endElement(H.DIV); //row NO
		}
		Tooltip.activateTooltips(context, attrs, inputText);
	}
}
