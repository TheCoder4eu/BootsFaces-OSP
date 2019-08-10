/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.slider2;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.badge.BadgeRenderer;
import net.bootsfaces.render.A;
import static net.bootsfaces.render.CoreRenderer.getRequestParameter;
import static net.bootsfaces.render.CoreRenderer.getValueType;
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
		Number submittedValue = convert(component, Float.valueOf(getRequestParameter(context, clientId)));

		if (submittedValue != null) {
			slider.setSubmittedValue(submittedValue);
		}
		new AJAXRenderer().decode(context, component, clientId);
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

	/**
	 * Returns converted value of the provided float value (to the type that is used on the component's value
	 * attribute).
	 *
	 * @param component Component to convert value for.
	 * @param value     Float value to convert.
	 *
	 * @return Converted value of the provided float value (to the type that is used on the component's value
	 *         attribute).
	 */
	private Number convert(UIComponent component, Float value) {
		return convert((Class<? extends Number>) getValueType(component), value);
	}

	/**
	 * Returns converted value of the provided float value (to the provided type).
	 *
	 * @param type  Type to convert float to. Currently only {@link Float} and {@link Integer} are supported.
	 * @param value Float value to convert.
	 *
	 * @return Converted value of the provided float value (to the provided type).
	 *
	 * @throws IllegalArgumentException If an unsupported type is provided.
	 */
	private Number convert(Class<? extends Number> type, Float value) {
		if (int.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)) {
			return value.intValue();
		}
		if (float.class.isAssignableFrom(type) || Float.class.isAssignableFrom(type)) {
			return value;
		}
		throw new IllegalArgumentException("Use integer or float");
	}

	private void encodeHTML(Slider2 slider, FacesContext context, ResponseWriter rw) throws IOException {
		String clientId = slider.getClientId(context);

		String mode = slider.getMode();
		String label = slider.getLabel();
		Class<? extends Number> type = (Class<? extends Number>) getValueType(slider);
		if (type == null) {
			type = Integer.class;
		}

		float min = ((Number) slider.getMin()).floatValue();
		float max = ((Number) slider.getMax()).floatValue();
		Object v = slider.getSubmittedValue();
		if (v == null) {
			v = slider.getValue();
		}
		if (v == null) {
			v = min + (max - min) / 2;
		}
		Float val = A.toFloat(v);
		if (val > max) {
			val = max;
		}
		if (val < min) {
			val = min;
		}
		slider.setValue(convert(type, val));
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

		rw.writeAttribute("class", getWithFeedback(getInputMode(slider.isInline()), slider), "class");
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
				encodeSliderInput(slider, rw, isVertical, span, clientId, val);
				rw.endElement("div");/* Row */
				rw.startElement("div", null);
				rw.writeAttribute("class", "row", "class");
			}
			encodeInput(slider, rw, mode, context, convert(type, val), clientId, isVertical, convert(type, min), convert(type, max));
			if (!bottom) {
				rw.endElement("div"); /* Row */

				rw.startElement("div", null);
				rw.writeAttribute("class", "row " + getErrorAndRequiredClass(slider, clientId), "class");
				encodeSliderInput(slider, rw, isVertical, span, clientId, val);
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
				String styleClass="";
				if (!BsfUtils.isLegacyFeedbackClassesEnabled()) {
					styleClass = "control-label";
				}
				if (slider.getLabelStyleClass() != null) {
					styleClass += " " + slider.getLabelStyleClass();
				}
				writeAttribute(rw, "class", styleClass);
				writeAttribute(rw, "style", slider.getLabelStyle());
				rw.write(label);
				rw.endElement("label"); // Label

				rw.endElement("div");// Column

				rw.endElement("div");/* Row */
			}
			rw.startElement("div", null);
			rw.writeAttribute("class", "row", "class");

			encodeInput(slider, rw, mode, context, convert(type, val), clientId, isVertical, convert(type, min), convert(type, max));

			encodeSliderInput(slider, rw, isVertical, span, clientId, val);
			rw.endElement("div");/* Row */

		}

		// <<---------------------------------------
		rw.endElement("div"); // rw.write("<!-- Slider Widget Row
								// -->\n");//Slider Widget Row

		rw.endElement("div"); // rw.write("<!-- form-group -->\n");//form-group

		encodeJS(slider, rw, clientId, mode);
		Tooltip.activateTooltips(context, slider);
	}

	private void encodeVLabel(Slider2 slider, ResponseWriter rw, String label) throws IOException {
		R.encodeColumn(rw, null, 12, 12, 12, 12, 0, 0, 0, 0, null, null);
		rw.startElement("p", slider);
		if (!BsfUtils.isLegacyFeedbackClassesEnabled()) {
			rw.writeAttribute("class", "control-label", null);
		}
		rw.write(label);
		rw.endElement("p"); // Label
		rw.endElement("div"); // Column
	}

	private void encodeInput(Slider2 slider, ResponseWriter rw, String mode, FacesContext context, Number val,
			String clientId, boolean vo, Number min, Number max) throws IOException {
		int cols = (vo ? 12 : slider.getBadgeSpan());
		if (!mode.equals("basic")) {
			/*
			 * int span, int offset, int cxs, int csm, int clg, int oxs, int
			 * osm, int olg
			 */
			R.encodeColumn(rw, null, cols, cols, cols, cols, 0, 0, 0, 0, null, null);
			if (mode.equals("badge")) {
				generateBadge(context, slider, rw, clientId, slider.getBadgeStyleClass(), slider.getBadgeStyle(),
					val.toString(), "_badge");
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
		writeAttribute(rw, "style", slider.getBadgeStyle());

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

	private void encodeSliderInput(Slider2 slider, ResponseWriter rw, boolean vo, int span, String clientId, float val)
	throws IOException {
		int cols = span;
		if (!slider.getMode().equals("basic")) {
			cols -= slider.getBadgeSpan();
		}
		/*
		 * int span, int offset, int cxs, int csm, int clg, int oxs, int osm,
		 * int olg
		 */
		// For Horizontal, we keep one column for the input/badge
		//R.encodeColumn(rw, null, (vo ? 12 : cols), (vo ? 12 : cols), (vo ? 12 : cols), (vo ? 12 : cols), 0, 0, 0, 0, null, null); // Issue #172
		
		//<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12" align="center">
		if (vo) {
			rw.startElement("div", null);
			rw.writeAttribute("class", "col-md-12 col-xs-12 col-sm-12 col-lg-12", "class");
			rw.writeAttribute("align", "center", null);
		} else {
			R.encodeColumn(rw, null, cols, cols, cols, cols, 0, 0, 0, 0, null, null); // Issue #592
		}
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
		if(slider.isDisabled() || slider.isReadonly()) rw.writeAttribute("data-slider-enabled", "false", null);
		if(BsfUtils.isValued(slider.getPrecision())) rw.writeAttribute("data-slider-precision", slider.getPrecision(), null);
		if(BsfUtils.isValued(slider.getScale())) rw.writeAttribute("data-slider-ticks-scale", slider.getScale(), null);
		if(BsfUtils.isValued(slider.isFocus())) rw.writeAttribute("data-slider-focus", slider.isFocus(), null);
		if(BsfUtils.isValued(slider.getLabelledBy())) rw.writeAttribute("data-slider-labelledby", slider.getLabelledBy(), null);

		rw.endElement("input");

		rw.endElement("div"); // Column
	}

	private void encodeJS(Slider2 slider, ResponseWriter rw, String clientId, String mode) throws IOException {

		String fClientId = BsfUtils.escapeJQuerySpecialCharsInSelector(clientId);
		String varName = BsfUtils.isValued(slider.getWidgetVar())
			? slider.getWidgetVar()
			: clientId.replace(":", "_") + "_sv";

		rw.startElement("script", slider);
		//# Start enclosure
		rw.writeText("$(document).ready(function() {", null);

			rw.writeText(
					 // build slider structure
					 "window." + varName + " = new Slider('#" + fClientId + "_slider', { " +
					 	(BsfUtils.isStringValued(slider.getFormatter()) ? "formatter: " + slider.getFormatter() + "," : "") +
					 "}); ", null);
			rw.writeText(
					 "$('#" + fClientId + "_slider').on('slide', function(slideEvt) { " +
					 	 "$('#" + fClientId + "').val(slideEvt.value); "+
					 	 "$('#" + fClientId + "_badge').text(slideEvt.value); "+
					 "}); ", null);
                        String fchange="$('#" + fClientId + "').val($('#" + fClientId + "_slider').val()); ";
                        if (mode.equals("badge")) {
                            fchange+="$('#" + fClientId + "_badge').text($('#" + fClientId + "_slider').val()); ";
                        }
                        //Fix #699
                        rw.writeText(
					 "$('#" + fClientId + "_slider').on('change', function() { " +
					 	 fchange +
					 "}); ", null);
			rw.writeText(
					 "$('#" + fClientId + "').keyup(function(event) { " +
						 "   var val = this.value; " +
						 "   if(typeof val === 'string') val = Number(val); " +
					 	 "   " + varName + ".setValue(val, true, true); " +
					 "}); ", null);
                        
		rw.writeText("});", null);
		rw.endElement("script");

	}
}
