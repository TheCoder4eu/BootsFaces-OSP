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

package net.bootsfaces.component.gyroscope;

import java.io.IOException;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.utils.FacesMessages;

/** This class generates the HTML code of &lt;b:gyroscope /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.gyroscope.Gyroscope")
public class GyroscopeRenderer extends CoreRenderer {


	/**
	 * This methods receives and processes input made by the user. More specifically, it ckecks whether the
	 * user has interacted with the current b:gyroscope. The default implementation simply stores
	 * the input value in the list of submitted values. If the validation checks are passed,
	 * the values in the <code>submittedValues</code> list are store in the backend bean.
	 * @param context the FacesContext.
	 * @param component the current b:gyroscope.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		Gyroscope gyroscope = (Gyroscope) component;

		if (gyroscope.isDisabled()) {
			return;
		}

		decodeBehaviors(context, gyroscope);

		String clientId = gyroscope.getClientId(context);
//		String submittedAlpha = (String) context.getExternalContext().getRequestParameterMap().get(clientId+".alpha");
//		String submittedBeta = (String) context.getExternalContext().getRequestParameterMap().get(clientId+".beta");
//		String submittedGamma = (String) context.getExternalContext().getRequestParameterMap().get(clientId+".gamma");

		new AJAXRenderer().decode(context, component, clientId);
	}

	public void processUpdates(FacesContext context, UIComponent component) {
		if (context == null) {
			throw new NullPointerException();
		}
		Gyroscope gyro = (Gyroscope)component;
		if (!gyro.isRendered()) {
			return;
		}
		if (gyro.isDisabled()) {
			return;
		}

		String clientId = component.getClientId(context);
		String submittedAlpha = (String) context.getExternalContext().getRequestParameterMap().get(clientId + ".alpha");
		String submittedBeta = (String) context.getExternalContext().getRequestParameterMap().get(clientId + ".beta");
		String submittedGamma = (String) context.getExternalContext().getRequestParameterMap().get(clientId + ".gamma");
//		System.out.println(submittedAlpha + ", " + submittedBeta + ", " + submittedGamma);
		if (inputIsValid(submittedAlpha) && inputIsValid(submittedBeta) && inputIsValid(submittedGamma)) {
			ValueExpression ve = component.getValueExpression("alpha");
			if (ve != null) {
				try {
					ve.setValue(context.getELContext(), submittedAlpha);
				} catch (ELException e) {
					FacesMessages.error("Couldn't populate the alpha value");
				} catch (Exception e) {
					FacesMessages.error("Couldn't populate the alpha value");
				}
			}
			ve = component.getValueExpression("beta");
			if (ve != null) {
				try {
					ve.setValue(context.getELContext(), submittedBeta);
				} catch (ELException e) {
					FacesMessages.error("Couldn't populate the beta value");
				} catch (Exception e) {
					FacesMessages.error("Couldn't populate the beta value");
				}
			}
			ve = component.getValueExpression("gamma");
			if (ve != null) {
				try {
					ve.setValue(context.getELContext(), submittedGamma);
				} catch (ELException e) {
					FacesMessages.error("Couldn't populate the gamma value");
				} catch (Exception e) {
					FacesMessages.error("Couldn't populate the gamma value");
				}
			}
		}

	}

	private boolean inputIsValid(String submittedBeta) {
		if (submittedBeta.length()>4)
			return false;
		for (int i = 0; i < submittedBeta.length(); i++) {
			if (!Character.isDigit(submittedBeta.charAt(i))) {
				if (submittedBeta.charAt(i)!='-')
					return false;
			}
		}
		return true;
	}

	/**
	 * This methods generates the HTML code of the current b:gyroscope.
	 * @param context the FacesContext.
	 * @param component the current b:gyroscope.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Gyroscope gyroscope = (Gyroscope) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = gyroscope.getClientId();

		rw.startElement("input", component);
		rw.writeAttribute("id", clientId + ".alpha", null);
		rw.writeAttribute("name", clientId + ".alpha", null);
		rw.writeAttribute("type", "hidden", null);
		rw.endElement("input");
		rw.startElement("input", component);
		rw.writeAttribute("id", clientId + ".beta", null);
		rw.writeAttribute("name", clientId + ".beta", null);
		rw.writeAttribute("type", "hidden", null);
		rw.endElement("input");
		rw.startElement("input", component);
		rw.writeAttribute("id", clientId + ".gamma", null);
		rw.writeAttribute("name", clientId + ".gamma", null);
		rw.writeAttribute("type", "hidden", null);
		rw.endElement("input");
		rw.startElement("input", component);
		rw.writeAttribute("id", clientId + ".timer", null);
		rw.writeAttribute("name", clientId + ".timer", null);
		rw.writeAttribute("value", "0", null);
		rw.writeAttribute("type", "hidden", null);
		rw.endElement("input");
		rw.startElement("script", component);
		rw.writeAttribute("id", clientId, null);
		StringBuilder jsCode = new StringBuilder();
		// Render Ajax Capabilities
		AJAXRenderer.generateAJAXCallForASingleEvent(
				FacesContext.getCurrentInstance(), gyroscope, rw,
				null, null, false, "rotation", jsCode);
		String js = jsCode.toString().replace("callAjax(this,", "callAjax(document.getElementById('" + clientId + ".alpha'),");
		rw.write("window.addEventListener('deviceorientation', function(event) {\n");
		rw.write("  var oldAlpha = document.getElementById('" + clientId + ".alpha').value;");
		rw.write("  var alpha = Math.round(event.alpha);");
		rw.write("  var oldBeta = document.getElementById('" + clientId + ".beta').value;");
		rw.write("  var beta = Math.round(event.beta);");
		rw.write("  var oldGamma = document.getElementById('" + clientId + ".gamma').value;");
		rw.write("  var gamma = Math.round(event.gamma);");
		rw.write("  if (alpha==oldAlpha && beta == oldBeta && gamma == oldGamma) return;");
		rw.write("   if (new Date().getTime() < document.getElementById('" + clientId + ".timer').value) return;");

		rw.write("  document.getElementById('" + clientId + ".alpha').value = alpha;");
		rw.write("  document.getElementById('" + clientId + ".beta').value = beta;");
		rw.write("  document.getElementById('" + clientId + ".gamma').value = gamma;");
		rw.write("  document.getElementById('" + clientId + ".timer').value = new Date().getTime()+100;");
		rw.write(js);
		rw.write("}, true);\n");
//		rw.write("window.addEventListener('compassneedscalibration', function(event) {\n");
//		rw.write("  alert('Your compass needs calibrating! Wave your device in a figure-eight motion');");
//		rw.write("  event.preventDefault();");
//		rw.write("}, true);\n");
		rw.endElement("script");
	}

}
