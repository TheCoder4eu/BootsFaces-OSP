/**
 *  Copyright 2014 - 17 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.touchSpin;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DoubleConverter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.inputText.InputTextRenderer;
import net.bootsfaces.utils.BsfUtils;

/** This class generates the HTML code of &lt;b:spinner /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.touchSpin.TouchSpin")
public class TouchSpinRenderer extends InputTextRenderer {
	/**
	 * This methods receives and processes input made by the user. More specifically, it ckecks whether the
	 * user has interacted with the current b:spinner. The default implementation simply stores
	 * the input value in the list of submitted values. If the validation checks are passed,
	 * the values in the <code>submittedValues</code> list are store in the backend bean.
	 * @param context the FacesContext.
	 * @param component the current b:spinner.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		TouchSpin spinner = (TouchSpin) component;

		if (spinner.isDisabled() || spinner.isReadonly()) {
			return;
		}

		decodeBehaviors(context, spinner);

		String clientId = spinner.getClientId(context);
		String name = spinner.getName();
		if (null == name) {
			name = "input_" + clientId;
		}
		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(name);

		if (submittedValue != null) {
			spinner.setSubmittedValue(submittedValue);
		}
		new AJAXRenderer().decode(context, component, name);
	}
	
	@Override
	public Object getConvertedValue(FacesContext fc, UIComponent c, Object sval) throws ConverterException {
		Converter cnv = new DoubleConverter();
		return cnv.getAsObject(fc, c, (String) sval);
	}

	/**
	 * This methods generates the HTML code of the current b:spinner.
	 * @param context the FacesContext.
	 * @param component the current b:spinner.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) 
	throws IOException {
		super.encodeBegin(context, component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) 
	throws IOException {
		super.encodeEnd(context, component);
		
		TouchSpin spin = (TouchSpin) component;
		
		ResponseWriter rw = context.getResponseWriter();
		String clientId = BsfUtils.escapeJQuerySpecialCharsInSelector(spin.getClientId());
		String fieldId = spin.getFieldId();
		if (null == fieldId) {
			fieldId = "input_" + clientId;
		}
		
		rw.startElement("script", spin);
		//# Start enclosure
		rw.writeText("$(document).ready(function() {", null);
		
		rw.writeText(
				 // build slider structure
				 "$('#" + fieldId + "').TouchSpin({ " +
						 
				 	(BsfUtils.isStringValued(spin.getInitval()) ? "initval: " + spin.getInitval() + ", " : "") +
				 	"min: " + spin.getMin() + ", " +
				 	"max: " + spin.getMax() + ", " +
				 	"step: " + spin.getStep() + ", " +
				 	(BsfUtils.isStringValued(spin.getForceStepDivisibility()) ? "forcestepdivisibility: '" + spin.getForceStepDivisibility() + "', " : "") +
				 	"decimals: " + spin.getDecimals() + ", " +
				 	"stepinterval: " + spin.getStepInterval() + ", " +
				 	"stepintervaldelay: " + spin.getStepIntervalDelay() + ", " +
				 	"verticalbuttons: " + spin.isVerticalButtons() + ", " +
				 	(BsfUtils.isStringValued(spin.getVerticalUpClass()) ? "verticalupclass: '" + spin.getVerticalUpClass() + "', " : "") +
				 	(BsfUtils.isStringValued(spin.getVerticalDownClass()) ? "verticaldownclass: '" + spin.getVerticalDownClass() + "', " : "") +
				 	(BsfUtils.isStringValued(spin.getPrefix()) ? "prefix: '" + spin.getPrefix() + "', " : "") +
				 	(BsfUtils.isStringValued(spin.getPrefixExtraClass()) ? "prefix_extraclass: '" + spin.getPrefixExtraClass() + "', " : "") +
				 	(BsfUtils.isStringValued(spin.getPostfix()) ? "postfix: '" + spin.getPostfix() + "', " : "") +
				 	(BsfUtils.isStringValued(spin.getPostfixExtraClass()) ? "postfix_extraclass: '" + spin.getPostfixExtraClass() + "', " : "") +
				 	"booster: " + spin.isBooster() + ", " +
				 	"boostat: " + spin.getBoostat() + ", " +
				 	"maxboostedstep: " + spin.getMaxBoostedStep() + "," +
				 	(BsfUtils.isStringValued(spin.getButtonDownClass()) ? "buttondown_class: '" + spin.getButtonDownClass() + "', " : "") +
				 	(BsfUtils.isStringValued(spin.getButtonUpClass()) ? "buttonup_class: '" + spin.getButtonUpClass() + "', " : "") +
				 	"mousewheel: " + spin.isMousewheel() + "" +
				 	
				 "}); ", null);	
		
		rw.writeText("});", null);
		//# End enclosure
		rw.endElement("script");
		
		new AJAXRenderer().generateBootsFacesAJAXAndJavaScriptForJQuery(context, component, rw, "#"+clientId, null);
	}
}
