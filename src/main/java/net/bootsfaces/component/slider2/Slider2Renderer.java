/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.slider2;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DoubleConverter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.badge.BadgeRenderer;
import net.bootsfaces.render.A;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class generates the HTML code of &lt;b:slider2 /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.slider2.Slider2")
public class Slider2Renderer extends BadgeRenderer {
	/**
	 * This methods receives and processes input made by the user. More specifically, it ckecks whether the
	 * user has interacted with the current b:slider2. The default implementation simply stores
	 * the input value in the list of submitted values. If the validation checks are passed,
	 * the values in the <code>submittedValues</code> list are store in the backend bean.
	 * @param context the FacesContext.
	 * @param component the current b:slider2.
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		Slider2 slider = (Slider2) component;

		if (slider.isDisabled() || slider.isReadonly()) {
			return;
		}

		decodeBehaviors(context, slider);

		String clientId = slider.getClientId(context);
		String submittedValue = (String) context.getExternalContext().getRequestParameterMap().get(clientId);
		
		if (submittedValue != null) {
			slider.setSubmittedValue(submittedValue);
		}
		new AJAXRenderer().decode(context, component, clientId);
	}
	
	@Override
	public Object getConvertedValue(FacesContext fc, UIComponent c, Object sval) throws ConverterException {
		Converter cnv = new DoubleConverter();
		return cnv.getAsObject(fc, c, (String) sval);
	}

	/**
	 * This methods generates the HTML code of the current b:slider2.
	 * @param context the FacesContext.
	 * @param component the current b:slider2.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		Slider2 slider = (Slider2) component;
		ResponseWriter rw = context.getResponseWriter();
		
		encodeHTML(slider, context, rw);
	}
	
	private void encodeHTML(Slider2 slider, FacesContext context, ResponseWriter rw) throws IOException {
		String clientId = slider.getClientId(context);

		String mode = slider.getMode();
		String label = slider.getLabel();

		double min = slider.getMin();
		double max = slider.getMax(); 
		Object v = slider.getSubmittedValue();
		if (v == null) {
			v = slider.getValue();
		}
		if (v == null) {
			v = slider.getValue();
			slider.setValue(v);
		}
		if (v == null) {
			v = max / 2;
			slider.setValue(v);
		}
		double val = A.toDouble(v);
		if (val > max) {
			val = max;
		}
		if (val < min) {
			val = min;
		}
		String valS = Double.toString(val);
		String o;
		if (slider.getOrientation() != null) {
			o = slider.getOrientation();
		} else {
			o = "horizontal";
		}
		boolean isVertical = o.startsWith("vertical");
		boolean bottom = o.endsWith("bottom");
		int span = slider.getSpan();

		rw.startElement("div", null);// form-group
		rw.writeAttribute("id", clientId, "id");
		Tooltip.generateTooltip(context, slider, rw);

		if (slider.isInline()) {
			rw.writeAttribute("class", getFormGroupWithFeedback("form-inline", clientId), "class");
		} else {
			rw.writeAttribute("class", getFormGroupWithFeedback("form-group", clientId), "class");
		}
		rw.startElement("div", null);
		String s = "row " + (isVertical ? "slider2-vertical" : "slider2");
		if (slider.getStyleClass()!=null) {
			s += " " + slider.getStyleClass();
		}
		rw.writeAttribute("class", s, "class");
		writeAttribute(rw, "style", slider.getStyle());
		// -------------------------------------------------------------->
		// <<-- Vertical -->>
		if (isVertical) {
			if (label != null && !bottom) {
				rw.startElement("div", null);
				String lsc = slider.getLabelStyleClass();
				if (lsc == null)
					lsc = "";
				else
					lsc = " " + lsc;
				rw.writeAttribute("class", "row " + getErrorAndRequiredClass(slider, clientId) + lsc, "class");
				writeAttribute(rw, "style", slider.getLabelStyle());
				encodeVLabel(slider, rw, label);
				rw.endElement("div");/* Row */
			}
			rw.startElement("div", null);
			rw.writeAttribute("class", "row", "class");
			if (bottom) {
				encodeSliderInput(slider, rw, isVertical, span, clientId, valS);
				rw.endElement("div");/* Row */
				rw.startElement("div", null);
				rw.writeAttribute("class", "row", "class");
			}
			encodeInput(slider, rw, mode, context, valS, clientId, isVertical, min, max);
			if (!bottom) {
				rw.endElement("div"); /* Row */

				rw.startElement("div", null);
				rw.writeAttribute("class", "row " + getErrorAndRequiredClass(slider, clientId), "class");
				encodeSliderInput(slider, rw, isVertical, span, clientId, valS);
			}
			rw.endElement("div"); /* Row */
			if (label != null && bottom) {
				rw.startElement("div", null);
				String lsc = slider.getLabelStyleClass();
				if (lsc == null)
					lsc = "";
				else
					lsc = " " + lsc;
				rw.writeAttribute("class", "row " + getErrorAndRequiredClass(slider, clientId) + lsc, "class");
				writeAttribute(rw, "style", slider.getLabelStyle());
				encodeVLabel(slider, rw, label);
				rw.endElement("div"); /* Row */
			}

		} else {
			// <<-- Horizontal -->>

			if (label != null) {
				rw.startElement("div", null);
				rw.writeAttribute("class", "row " + getErrorAndRequiredClass(slider, clientId), "class");

				R.encodeColumn(rw, null, 6, 6, 6, 6, 0, 0, 0, 0, null, null);
				rw.startElement("label", slider);
				rw.writeAttribute("for", clientId, null);
				writeAttribute(rw, "class", slider.getLabelStyleClass());
				writeAttribute(rw, "style", slider.getLabelStyle());
				rw.write(label);
				rw.endElement("label"); // Label

				rw.endElement("div");// Column

				rw.endElement("div");/* Row */
			}
			rw.startElement("div", null);
			rw.writeAttribute("class", "row", "class");

			encodeInput(slider, rw, mode, context, valS, clientId, isVertical, min, max);

			encodeSliderInput(slider, rw, isVertical, span, clientId, valS);
			rw.endElement("div");/* Row */

		}

		// <<---------------------------------------
		rw.endElement("div"); // rw.write("<!-- Slider Widget Row
								// -->\n");//Slider Widget Row

		rw.endElement("div"); // rw.write("<!-- form-group -->\n");//form-group

		encodeJS(slider, rw, clientId);
		Tooltip.activateTooltips(context, slider);
	}

	private void encodeVLabel(Slider2 slider, ResponseWriter rw, String label) throws IOException {
		R.encodeColumn(rw, null, 12, 12, 12, 12, 0, 0, 0, 0, null, null);
		rw.startElement("p", slider);
		rw.write(label);
		rw.endElement("p"); // Label
		rw.endElement("div"); // Column
	}
	
	private void encodeInput(Slider2 slider, ResponseWriter rw, String mode, FacesContext context, String val,
			String clientId, boolean vo, double min, double max) throws IOException {
		int cols = (vo ? 12 : 1);
		if (!mode.equals("basic")) {
			/*
			 * int span, int offset, int cxs, int csm, int clg, int oxs, int
			 * osm, int olg
			 */
			R.encodeColumn(rw, null, cols, cols, cols, cols, 0, 0, 0, 0, null, null);
			if (mode.equals("badge")) {
				generateBadge(context, slider, rw, clientId, slider.getBadgeStyleClass(), slider.getBadgeStyle(),
						val, "_badge");
			}
		}
		removeMisleadingType(slider);
		// Input
		rw.startElement("input", slider);
		rw.writeAttribute("id", clientId, null);
		rw.writeAttribute("name", clientId, null);
		rw.writeAttribute("type", (mode.equals("edit") ? "text" : "hidden"), null);
		rw.writeAttribute("size", String.valueOf(max).length() - 2, null);
		rw.writeAttribute("min", min, null);
		rw.writeAttribute("max", max, null);
		rw.writeAttribute("maxlength", String.valueOf(max).replace(".", "").length(), null);
		if (slider.isDisabled()) {
			rw.writeAttribute("disabled", "disabled", null);
		}
		if (slider.isReadonly()) {
			rw.writeAttribute("readonly", "readonly", null);
		}

		String styleClass = "form-control input-sm" + (vo ? " text-center" : "");
		if (slider.getBadgeStyleClass()!=null) {
			styleClass += " " + slider.getBadgeStyleClass();
		}
		rw.writeAttribute("class", styleClass, "class");
		writeAttribute(rw, "style", slider.getBadgeStyleClass());

		rw.writeAttribute("value", val, null);

		rw.endElement("input");

		if (!mode.equals("basic")) {
			rw.endElement("div");
		} // Column
	}


	/**
	 * remove wrong type information that may have been added by AngularFaces
	 */
	@SuppressWarnings("rawtypes")
	private void removeMisleadingType(Slider2 slider) {
		try {
			Method method = getClass().getMethod("getPassThroughAttributes", (Class[]) null);
			if (null != method) {
				Object map = method.invoke(this, (Object[]) null);
				if (null != map) {
					Map attributes = (Map) map;
					if (attributes.containsKey("type"))
						attributes.remove("type");
				}
			}
		} catch (Exception ignoreMe) {
			// we don't really have to care about this error
		}

	}

	private void encodeSliderInput(Slider2 slider, ResponseWriter rw, boolean vo, int span, String clientId, String val)
	throws IOException {
		int cols = span;
		if (!slider.getMode().equals("basic")) {
			cols--;
		}
		/*
		 * int span, int offset, int cxs, int csm, int clg, int oxs, int osm,
		 * int olg
		 */
		// For Horizontal, we keep one column for the input/badge
		R.encodeColumn(rw, null, (vo ? 12 : cols), (vo ? 12 : cols), (vo ? 12 : cols), (vo ? 12 : cols), 0, 0, 0, 0,
				null, null); // Issue #172
		// R.encodeColumn(rw, null, (vo ? 12 : 4), (vo ? 12 : 4), (vo ? 12 : 4),
		// (vo ? 12 : 4), 0, 0, 0, 0, null, null);
		// Slider <input>
		
		
		rw.startElement("input", null);
		rw.writeAttribute("id", clientId + "_slider", null); // concat
		
		rw.writeAttribute("data-slider-id", clientId + "_slider_id", null);
		
		if(BsfUtils.isValued(slider.getMin())) rw.writeAttribute("data-slider-min", slider.getMin(), null);
		if(BsfUtils.isValued(slider.getMax())) rw.writeAttribute("data-slider-max", slider.getMax(), null);
		if(BsfUtils.isValued(slider.getValue())) rw.writeAttribute("data-slider-value", val, null);
		if(BsfUtils.isValued(slider.getStep())) rw.writeAttribute("data-slider-step", slider.getStep(), null);
		if(BsfUtils.isValued(slider.getHandleShape())) rw.writeAttribute("data-slider-handle", slider.getHandleShape(), null);
		if(BsfUtils.isValued(slider.getTooltipVisibility())) rw.writeAttribute("data-slider-tooltip", slider.getTooltipVisibility(), null);
		if(BsfUtils.isValued(slider.isTooltipSplit())) rw.writeAttribute("data-slider-tooltip-split", slider.isTooltipSplit(), null);
		if(BsfUtils.isValued(slider.getTooltipSliderPosition())) rw.writeAttribute("data-slider-tooltip-position", slider.getTooltipSliderPosition(), null);
		if(BsfUtils.isValued(slider.getOrientation())) rw.writeAttribute("data-slider-orientation", slider.getOrientation().startsWith("vertical") ? "vertical" : "horizontal", null);
		if(BsfUtils.isValued(slider.isDisabled())) rw.writeAttribute("data-slider-enabled", !slider.isDisabled(), null);
		if(BsfUtils.isValued(slider.getPrecision())) rw.writeAttribute("data-slider-precision", slider.getPrecision(), null);
		if(BsfUtils.isValued(slider.getScale())) rw.writeAttribute("data-slider-ticks-scale", slider.getScale(), null);
		if(BsfUtils.isValued(slider.isFocus())) rw.writeAttribute("data-slider-focus", slider.isFocus(), null);
		if(BsfUtils.isValued(slider.getLabelledBy())) rw.writeAttribute("data-slider-labelledby", slider.getLabelledBy(), null);

		rw.endElement("input");
		
		rw.endElement("div"); // Column
	}

	private void encodeJS(Slider2 slider, ResponseWriter rw, String clientId) throws IOException {

		String fClientId = BsfUtils.escapeJQuerySpecialCharsInSelector(clientId);
		String VarName = clientId.replace(":", "_");
		
		rw.startElement("script", slider);
		//# Start enclosure
		rw.writeText("$(document).ready(function() {", null);
		
			rw.writeText(
					 // build slider structure
					 "var " + VarName + "_sv = new Slider('#" + fClientId + "_slider', { " +
					 	(BsfUtils.isStringValued(slider.getFormatter()) ? "formatter: " + slider.getFormatter() + "," : "") +
					 "}); ", null);	
			rw.writeText(
					 "$('#" + fClientId + "_slider').on('slide', function(slideEvt) { " +
					 	 "$('#" + fClientId + "').val(slideEvt.value); "+
					 	 "$('#" + fClientId + "_badge').text(slideEvt.value); "+
					 "}); ", null);	
			rw.writeText(
					 "$('#" + fClientId + "').keyup(function(event) { " +
						 "   var val = this.value; " +
						 "   if(typeof val === 'string') val = Number(val); " + 
					 	 "   " + VarName + "_sv.setValue(val, true, true); " +
					 "}); ", null);	

		rw.writeText("});", null);
		rw.endElement("script");

	}
}
