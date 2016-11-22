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

package net.bootsfaces.component.dateTimePicker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;
import net.bootsfaces.utils.LocaleUtils;

/** This class generates the HTML code of &lt;b:dateTimePicker /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.dateTimePicker.DateTimePicker")
public class DateTimePickerRenderer extends CoreRenderer {
	private static final String DTP_CONTAINER_ID = "dtp_container_";

	@Override
	public void decode(FacesContext context, UIComponent component) {
		DateTimePicker dtp = (DateTimePicker) component;
		String clientId = dtp.getClientId();
		String subVal = context.getExternalContext().getRequestParameterMap().get(dtp.getClientId());

		if (dtp.isDisabled() || dtp.isReadonly()) {
			return;
		}

		// System.out.println("Submitted value = " + subVal);
		if (subVal != null) {
			dtp.setSubmittedValue(subVal);
			dtp.setValid(true);
		}
		new AJAXRenderer().decode(context, dtp, clientId + "_Input");
		new AJAXRenderer().decode(context, dtp, clientId);
	}

	/**
	 * Get value displayable
	 * @param ctx
	 * @param dtp
	 * @return
	 */
	public static String getValueAsString(Object value, FacesContext ctx, DateTimePicker dtp) {
		// Else we use our own converter
		if(value == null) {
			return null;
		}
		Locale sloc = BsfUtils.selectLocale(ctx.getViewRoot().getLocale(), dtp.getLocale(), dtp);
		String sdf = BsfUtils.selectDateFormat(sloc, dtp.getFormat());
		// assume that the format is always specified as moment.js format
		sdf = LocaleUtils.momentToJavaFormat(sdf);

		return getDateAsString(ctx, dtp, value, sdf, sloc);
	}

	/**
	 * Get date in string format
	 * @param value
	 * @param format
	 * @param locale
	 * @return
	 */

	public static String getDateAsString(FacesContext fc, DateTimePicker dtp, Object value, String format, Locale locale) {
		if (value == null) {
			return null;
		}

		Converter converter = dtp.getConverter();
		return  converter == null ?
				getInternalDateAsString(value, format, locale)
				:
				converter.getAsString(fc, dtp, value);

	}


	public static String getInternalDateAsString(Object dt, String format, Locale locale) {
		if (dt == null) {
			return null;
		}

		if (dt instanceof String) {
			return (String) dt;
		} else if (dt instanceof Date) {
			SimpleDateFormat dtFormat = new SimpleDateFormat(format, locale);
			dtFormat.setTimeZone(java.util.TimeZone.getDefault());

			return dtFormat.format((Date) dt);
		} else {
			throw new IllegalArgumentException(
					"Value could be either String or java.util.Date, you may want to use a custom converter.");
		}
	}

	/**
	 * This methods generates the HTML code of the current b:dateTimePicker.
	 * @param context the FacesContext.
	 * @param component the current b:dateTimePicker.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		DateTimePicker dtp = (DateTimePicker) component;
		ResponseWriter rw = context.getResponseWriter();

		String divPrefix = encodeHTML(context, rw, dtp);
		encodeJS(context, rw, dtp, divPrefix);
	}

	/**
	 * Encodes the HTML for this context
	 *
	 * @param fc
	 * @throws IOException
	 */
	private String encodeHTML(FacesContext fc, ResponseWriter rw, DateTimePicker dtp)
	throws IOException {
		String clientId = dtp.getClientId();
		String mode = dtp.getMode();
		String styleClass = dtp.getStyleClass();
		if(styleClass == null) styleClass = "";
		styleClass = styleClass.trim();

		String responsiveStyleClass = Responsive.getResponsiveStyleClass(dtp, false);
		String label = dtp.getLabel();
		if (!dtp.isRenderLabel()) {
			label = null;
		}

		String divPrefix="";
		if (null != responsiveStyleClass && responsiveStyleClass.trim().length()>0) {
			rw.startElement("div", dtp);
			if (!isHorizontalForm(dtp)) {
				rw.writeAttribute("class", responsiveStyleClass + " form-group", "class");
			} else {
				rw.writeAttribute("class", "form-group", "class");
			}
			rw.writeAttribute("id", clientId, null);
			divPrefix=DTP_CONTAINER_ID;
		} else if (label != null) {
			rw.startElement("div", dtp);
			rw.writeAttribute("id", clientId, null);
			divPrefix=DTP_CONTAINER_ID;
		}
		
		

		if (label != null) {
			rw.startElement("label", dtp);
			rw.writeAttribute("for", clientId + "_Input", "for"); // "input_" + clientId
			generateErrorAndRequiredClassForLabels(dtp, rw, clientId, dtp.getLabelStyleClass());
			writeAttribute(rw, "style", dtp.getLabelStyle());

			rw.writeText(label, null);
			rw.endElement("label");
		}

		if (isHorizontalForm(dtp) && null != responsiveStyleClass && responsiveStyleClass.trim().length()>0) {
			rw.startElement("div", dtp);
			rw.writeAttribute("class", responsiveStyleClass, "class");
		}
		

		Object v = dtp.getSubmittedValue();
		if (v == null) {
			v = dtp.getValue();
		}

		// Icon
		String icon = dtp.getIcon();
		String faicon = dtp.getIconAwesome();
		boolean fa = false;
		if (BsfUtils.isStringValued(faicon)) {
			icon = faicon;
			fa = true;
		}
		if(!BsfUtils.isStringValued(icon)) {
			icon = "calendar";
			fa = false;
		}

		if ("plain".equals(mode)) {
			// simple wrapper
			rw.startElement("div", dtp);
			if (styleClass.length() > 0) {
				rw.writeAttribute("class", styleClass, "class");
			}
			rw.writeAttribute("id", divPrefix + clientId, null);

			rw.writeAttribute("class", "input-group date " + styleClass, "class");
			if(dtp.getStyle() != null) rw.writeAttribute("style", dtp.getStyle(), "style");
			// input
			rw.startElement("input", dtp);
			rw.writeAttribute("type", "text", null);
			rw.writeAttribute("id", clientId + "_Input", null);
			rw.writeAttribute("name", clientId, null);
			//rw.writeAttribute("class", "form-control " + getErrorAndRequiredClass(dtp, clientId), "class");
			generateStyleClass(dtp, rw);
			if(BsfUtils.isStringValued(dtp.getPlaceholder())) rw.writeAttribute("placeholder", dtp.getPlaceholder(), null);
			if (v != null) {
				rw.writeAttribute("value", getValueAsString(v, fc, dtp), null);
			}
			Tooltip.generateTooltip(fc, dtp, rw);
			// Render Ajax Capabilities
			AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), dtp, rw, false);
			rw.endElement("input");
			// span
			rw.startElement("span", dtp);
			rw.writeAttribute("class", "input-group-addon", "class");
			IconRenderer.encodeIcon(rw, dtp, icon, fa, null, null, null, false, null, null, dtp.isDisabled(), true, true, true);
			rw.endElement("span");

			rw.endElement("div");
		}
		else if ("inline".equals(mode)) {
			// div
			rw.startElement("div", dtp);
			rw.writeAttribute("class", "input-group date " + styleClass, "class");
			if(dtp.getStyle() != null) rw.writeAttribute("style", (dtp.isDisabled() ? "opacity: 0.65; pointer-events: none;" : "") + dtp.getStyle(), "style");
			else if(dtp.isDisabled()) rw.writeAttribute("style", "opacity: 0.65; pointer-events: none;", null);
			rw.writeAttribute("id", divPrefix + clientId, null);
			rw.endElement("div");

			// write the input item
			rw.startElement("input", dtp);
			rw.writeAttribute("id", clientId + "_Input", null);
			rw.writeAttribute("name", clientId, null);
			rw.writeAttribute("type", "hidden", "type");
			if (v != null) {
				rw.writeAttribute("value", getValueAsString(v, fc, dtp), null);
			}
			Tooltip.generateTooltip(fc, dtp, rw);
			rw.endElement("input");
		}
		else { // "popup"
			// div
			rw.startElement("div", dtp);
			rw.writeAttribute("class", "input-group date " + styleClass, "class");
			if(dtp.getStyle() != null) rw.writeAttribute("style", dtp.getStyle(), "style");
			rw.writeAttribute("id", divPrefix + clientId, null);

			// input
			rw.startElement("input", dtp);
			rw.writeAttribute("type", "text", null);
			rw.writeAttribute("id", clientId + "_Input", null);
			rw.writeAttribute("name", clientId, null);
			// rw.writeAttribute("class", "form-control " + getErrorAndRequiredClass(dtp, clientId), "class");
			generateStyleClass(dtp, rw);
			
			if(BsfUtils.isStringValued(dtp.getPlaceholder())) rw.writeAttribute("placeholder", dtp.getPlaceholder(), null);
			if (v != null) {
				rw.writeAttribute("value", getValueAsString(v, fc, dtp), null);
			}
			Tooltip.generateTooltip(fc, dtp, rw);
			// Render Ajax Capabilities
			AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), dtp, rw, false);
			rw.endElement("input");

			// span
			rw.startElement("span", dtp);
			rw.writeAttribute("class", "input-group-addon", "class");
			IconRenderer.encodeIcon(rw, dtp, icon, fa, null, null, null, false, null, null, dtp.isDisabled(), true, true, true);
			rw.endElement("span");

			// end
			rw.endElement("div");
		}

		if (isHorizontalForm(dtp) && null != responsiveStyleClass && responsiveStyleClass.trim().length()>0) {
			rw.endElement("div");
		}
		if (null != responsiveStyleClass && responsiveStyleClass.trim().length()>0) {
			rw.endElement("div");
		} else if (label != null) {
			rw.endElement("div");
		}

		return divPrefix;
	}

	private void generateStyleClass(DateTimePicker dtp, ResponseWriter rw)
	throws IOException {
		StringBuilder sb;
		String s;
		sb = new StringBuilder(20); // optimize int
		sb.append("form-control");

		String fsize = dtp.getFieldSize();

		if (fsize != null) {
			sb.append(" input-").append(fsize);
		}

		// styleClass and class support
		String sclass = dtp.getStyleClass();
		if (sclass != null) {
			sb.append(" ").append(sclass);
		}

		sb.append(" ").append(getErrorAndRequiredClass(dtp, dtp.getClientId()));

		s = sb.toString().trim();
		if (s != null && s.length() > 0) {
			rw.writeAttribute("class", s, "class");
		}
	}

	/**
	 * Encode the javascript code
	 * @throws IOException
	 */
	private void encodeJS(FacesContext fc, ResponseWriter rw, DateTimePicker dtp, String divPrefix)
	throws IOException {
		String clientId = dtp.getClientId();
		String mode = dtp.getMode();

		Object v = dtp.getSubmittedValue();
		if (v == null) {
			v = dtp.getValue();
		}

		// show all buttons
		if(dtp.isShowButtonPanel()) {
			dtp.setShowClearButton(true);
			dtp.setShowCloseButton(true);
			dtp.setShowTodayButton(true);
		}

		Locale sloc = BsfUtils.selectLocale(fc.getViewRoot().getLocale(), dtp.getLocale(), dtp);
		String format = BsfUtils.selectDateTimeFormat(sloc, dtp.getFormat(), dtp.isShowDate(), dtp.isShowTime());
		String displayFormat = "'" + (dtp.getFormat() == null ? LocaleUtils.javaToMomentFormat(format) : format) + "'";
		String inlineDisplayDate = "'" + (dtp.getFormat() == null ?
				getDateAsString(fc, dtp, v, format, sloc)
				:
				getDateAsString(fc, dtp, v, LocaleUtils.momentToJavaFormat(format), sloc)) + "'";

		boolean openOnClick= !"plain".equals(mode) && !"inline".equals(mode);
		String fullSelector =  "#" + BsfUtils.escapeJQuerySpecialCharsInSelector(divPrefix + clientId);

		String defaultDate = BsfUtils.isStringValued(dtp.getInitialDate()) ?
			dtp.getInitialDate().contains("moment") ? dtp.getInitialDate() : "'" + dtp.getInitialDate() + "'" : "";
		String minDate = BsfUtils.isStringValued(dtp.getMinDate()) ?
							dtp.getMinDate().contains("moment") ? dtp.getMinDate() : "'" + dtp.getMinDate() + "'" :
							"";
		String maxDate = BsfUtils.isStringValued(dtp.getMaxDate()) ?
							dtp.getMinDate().contains("moment") ? dtp.getMaxDate() : "'" + dtp.getMaxDate() + "'" :
							"";

		rw.startElement("script", dtp);
		rw.writeText("$(function () { " +
					      "$('" + fullSelector + "').datetimepicker({  " +
					        (dtp.isAllowInputToggle() || openOnClick  ?         		"allowInputToggle: true, ": "") +
					      	(dtp.isCollapse() ? 										"collapse: " + dtp.isCollapse() + ", ": "") +
					      	(BsfUtils.isStringValued(dtp.getDayViewHeaderFormat()) ? 	"dayViewHeaderFormat: '" + dtp.getDayViewHeaderFormat() + "', " : "") +
					      	(BsfUtils.isStringValued(dtp.getDisabledDates()) ?			"disabledDates: [" + dtp.getDisabledDates() + "], " : "") +
					      	(BsfUtils.isStringValued(dtp.getDisableTimeInterval()) ?	"disabledTimeIntervals: [" + dtp.getDisableTimeInterval() + "], " : "") +
					      	(BsfUtils.isStringValued(dtp.getEnabledDates()) ?			"enabledDates: [" + dtp.getDisableTimeInterval() + "], " : "") +
					      	(dtp.isFocusOnShow() ? 										"focusOnShow: " + dtp.isFocusOnShow() + ", ": "") +
					      	(BsfUtils.isStringValued(dtp.getInitialDate()) ?			"defaultDate: " + defaultDate + ", " : "") +
					      	(dtp.isKeepInvalid() ? 										"keepInvalid: " + dtp.isKeepInvalid() + ", ": "") +
					      	(dtp.isKeepOpen() ? 										"keepOpen: " + dtp.isKeepOpen() + ", ": "") +
					      	(BsfUtils.isStringValued(minDate) ?							"minDate: " + minDate + ", " : "") +
					      	(BsfUtils.isStringValued(maxDate) ?							"maxDate: " + maxDate + ", " : "") +
					      	(dtp.isShowWeek() ? 										"calendarWeeks: " + dtp.isShowWeek() + ", ": "") +
					      	(dtp.isShowClearButton() ? 									"showClear: " + dtp.isShowClearButton() + ", ": "") +
					      	(dtp.isShowCloseButton() ? 									"showClose: " + dtp.isShowCloseButton() + ", ": "") +
					      	(dtp.isShowTodayButton() ? 									"showTodayButton: " + dtp.isShowTodayButton() + ", ": "") +
					      	(dtp.isSideBySide() || "inline".equals(mode) ? 				"sideBySide: true, ": "") +
					      	(dtp.getTimeStepping() > 0 ?								"stepping: " + dtp.getTimeStepping() + ", ": "") +
					      	(BsfUtils.isStringValued(dtp.getToolbarPlacement()) ?		"toolbarPlacement: '" + dtp.getToolbarPlacement() + "', " : "") +
					      	(BsfUtils.isStringValued(dtp.getViewMode()) ?				"viewMode: '" + dtp.getViewMode() + "', " : "") +
					      	(dtp.isUseCurrent() ? 										"useCurrent: " + dtp.isUseCurrent() + ", ": "") +
					      	(dtp.isUseStrict() ? 										"useStrict: " + dtp.isUseStrict() + ", ": "") +
					      	(BsfUtils.isStringValued(dtp.getWidgetParent()) ?           "widgetParent: '" + BsfUtils.resolveSearchExpressions(dtp.getWidgetParent()) + "', " : "" ) +
					      	("inline".equals(mode) ? 									"inline: true," : "" ) +
					      	"date: moment(" + inlineDisplayDate + ", " + displayFormat + "), " +
					      	"locale: '" + sloc.getLanguage() + "', " +
					      	"format: " + displayFormat +
						  "});" +
					      // ("inline".equals(type) ? "$('" + fullSelector + "').date(" + inlineDisplayDate + ")" : "") +
					  "});", null);

		if("inline".equals(mode)) {
			rw.writeText("$('" + fullSelector + "').on('dp.change', function(e) { " +
						 "   $('#" + BsfUtils.escapeJQuerySpecialCharsInSelector(clientId + "_Input") + "').val( e.date.format(" + displayFormat + ") ); " +
						 "});", null);
		}
		if(dtp.isDisabled() && !"inline".equals(mode)) {
			rw.writeText("$('" + fullSelector + "').data(\"DateTimePicker\").disable(); ", null);
		}
		rw.endElement("script");
		new AJAXRenderer().generateBootsFacesAJAXAndJavaScriptForJQuery(fc, dtp, rw, fullSelector, null);
	}
}
