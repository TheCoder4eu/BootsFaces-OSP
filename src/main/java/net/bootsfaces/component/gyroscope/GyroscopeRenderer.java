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

package net.bootsfaces.component.gyroscope;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.CoreRenderer;

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
		String submittedAlpha = (String) context.getExternalContext().getRequestParameterMap().get(clientId+".alpha");
		String submittedBeta = (String) context.getExternalContext().getRequestParameterMap().get(clientId+".beta");
		String submittedGamma = (String) context.getExternalContext().getRequestParameterMap().get(clientId+".gamma");

		if (submittedBeta != null) {
//			gyroscope.setSubmittedValue(submittedValue);
			System.out.println("Value: " + submittedAlpha + ", " + submittedBeta + ", " + submittedGamma);
		}
		new AJAXRenderer().decode(context, component, clientId);
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
		rw.endElement("input");
		rw.startElement("input", component);
		rw.writeAttribute("id", clientId + ".beta", null);
		rw.writeAttribute("name", clientId + ".beta", null);
		rw.endElement("input");
		rw.startElement("input", component);
		rw.writeAttribute("id", clientId + ".gamma", null);
		rw.writeAttribute("name", clientId + ".gamma", null);
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
		rw.write("  var beta = Math.round(event.alpha);");
		rw.write("  var oldGamma = document.getElementById('" + clientId + ".gamma').value;");
		rw.write("  var gamma = Math.round(event.gamma);");
		rw.write("  if (alpha==oldAlpha && beta == oldBeta && gamma == oldGamma) return false;");

		rw.write("  document.getElementById('" + clientId + ".alpha').value = alpha;");
		rw.write("  document.getElementById('" + clientId + ".beta').value = beta;");
		rw.write("  document.getElementById('" + clientId + ".gamma').value = gamma;");
		rw.write(js);
		rw.write("}, true);\n");
		rw.write("window.addEventListener('compassneedscalibration', function(event) {\n");
		rw.write("  alert('Your compass needs calibrating! Wave your device in a figure-eight motion');");
		rw.write("  event.preventDefault();");
		rw.write("}, true);\n");
		rw.endElement("script");
	}

}
