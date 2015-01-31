package net.bootsfaces.component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.render.A;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;

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
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
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
			R.addClass2FacetComponent(prep, "OutputText", inputText.ADDON);
                        R.addClass2FacetComponent(prep, "Icon", inputText.ADDON);
                        R.addClass2FacetComponent(prep, "IconAwesome", inputText.ADDON);
		}// (prep.getChildren(), "OutputText", S.ADDON); }
		if (append) {
			R.addClass2FacetComponent(app, "OutputText", inputText.ADDON);
                        R.addClass2FacetComponent(app, "Icon", inputText.ADDON);
                        R.addClass2FacetComponent(app, "IconAwesome", inputText.ADDON);
		}

		String label = A.asString(attrs.get(A.LABEL));
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
			t = A.asString(attrs.get(A.TYPE));
			if (t == null)
				t = H.TEXT;
		}

		rw.startElement(H.DIV, component);
		rw.writeAttribute(H.CLASS, "form-group", H.CLASS);
		if (label != null) {
			rw.startElement(H.LABEL, component);
			rw.writeAttribute(A.FOR, clientId, A.FOR);
			rw.writeText(label, null);
			rw.endElement(H.LABEL);
		}
		if (append || prepend) {
			rw.startElement(H.DIV, component);
			rw.writeAttribute(H.CLASS, "input-group", H.CLASS);
		}
		int span = A.toInt(attrs.get(A.SPAN));
		if (span > 0) {
			rw.startElement(H.DIV, component);
			rw.writeAttribute(H.CLASS, "col-md-" + span, H.CLASS);
		}

		if (prepend) {
			if (prep.getClass().getName().endsWith("Button")
					|| (prep.getChildCount() > 0 && prep.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement(H.DIV, inputText);
				rw.writeAttribute(H.CLASS, "input-group-btn", H.CLASS);
				prep.encodeAll(context);
				rw.endElement(H.DIV);
			} else {
				prep.encodeAll(context);
			}
		}
		// Input
		rw.startElement(H.INPUT, inputText);
		rw.writeAttribute(H.ID, clientId, null);
		rw.writeAttribute(H.NAME, clientId, null);
		rw.writeAttribute(H.TYPE, t, null);

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
		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute(H.CLASS, s, H.STYLECLASS);
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
		rw.writeAttribute(H.VALUE, v, null);

		// Render Ajax Capabilities
		Map<String, List<ClientBehavior>> clientBehaviors = inputText.getClientBehaviors();
		Set<String> keysClientBehavior = clientBehaviors.keySet();
		for (String keyClientBehavior : keysClientBehavior) {
			List<ClientBehavior> behaviors = clientBehaviors.get(keyClientBehavior);
			for (ClientBehavior cb : behaviors) {
				ClientBehaviorContext behaviorContext = ClientBehaviorContext.createClientBehaviorContext(context, inputText,
						keyClientBehavior, null, null);
				rw.writeAttribute("on" + keyClientBehavior, cb.getScript(behaviorContext), null);
			}

		}

		rw.endElement(H.INPUT);
		if (append) {
			if (app.getClass().getName().endsWith("Button")
					|| (app.getChildCount() > 0 && app.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement(H.DIV, inputText);
				rw.writeAttribute(H.CLASS, "input-group-btn", H.CLASS);
				app.encodeAll(context);
				rw.endElement(H.DIV);
			} else {
				app.encodeAll(context);
			}
		}

		if (append || prepend) {
			rw.endElement(H.DIV);
		} // input-group
		rw.endElement(H.DIV); // form-group
		if (span > 0) {
			rw.endElement(H.DIV); // span
			// rw.endElement(H.DIV); //row NO
		}
	}
}
