/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
import javax.faces.render.FacesRenderer;

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
		String subVal = context.getExternalContext().getRequestParameterMap().get(dtp.getClientId());

		// System.out.println("Submitted value = " + subVal);
		if (subVal != null) {
			dtp.setSubmittedValue(subVal);
			dtp.setValid(true);
		}
	}

	/**
	 * Get value displayable
	 * @param ctx
	 * @param dtp
	 * @return
	 */
	public String getValueAsString(Object value, FacesContext ctx, DateTimePicker dtp) {
		// Else we use our own converter
		Locale sloc = BsfUtils.selectLocale(ctx.getViewRoot().getLocale(), dtp.getLocale(), dtp);
		String sdf = BsfUtils.selectDateFormat(sloc, dtp.getFormat());
		// assume that the format is always specified as moment.js format
		sdf = LocaleUtils.momentToJavaFormat(sdf);

		return getDateAsString(value, sdf, sloc);
	}

	/**
	 * Get date in string format
	 * @param dt
	 * @param format
	 * @param locale
	 * @return
	 */
	public static String getDateAsString(Object dt, String format, Locale locale) {
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
		String divPrefix="";
		if (null != responsiveStyleClass && responsiveStyleClass.trim().length()>0) {
			rw.startElement("div", dtp);
			rw.writeAttribute("class", responsiveStyleClass, "class");
			rw.writeAttribute("id", clientId, null);
			divPrefix=DTP_CONTAINER_ID;
		}

		Object v = dtp.getSubmittedValue();
		if (v == null) {
			v = dtp.getValue();
		}

		if ("plain".equals(mode))
		{
			// simple wrapper
			rw.startElement("div", dtp);
			if (styleClass.length()>0) {
				rw.writeAttribute("class", styleClass, "class");
			}
			rw.writeAttribute("id", divPrefix + clientId, null);

			// input
			rw.startElement("input", dtp);
			rw.writeAttribute("type", "text", null);
			rw.writeAttribute("id", clientId, null);
			rw.writeAttribute("name", clientId, null);
			rw.writeAttribute("class", "form-control " + getErrorAndRequiredClass(dtp, clientId), "class");
			if(BsfUtils.isStringValued(dtp.getPlaceholder())) rw.writeAttribute("placeholder", dtp.getPlaceholder(), null);
			if (v != null) {
				rw.writeAttribute("value", getValueAsString(v, fc, dtp), null);
			}
			Tooltip.generateTooltip(fc, dtp, rw);
			rw.endElement("input");

			rw.endElement("div");
		}
		else if ("inline".equals(mode))
		{
			// div
			rw.startElement("div", dtp);
			rw.writeAttribute("class", "input-group date " + styleClass, "class");
			if(dtp.getStyle() != null) rw.writeAttribute("style", dtp.getStyle(), "style");
			rw.writeAttribute("id", divPrefix + clientId, null);
			rw.endElement("div");

			// write the input item
			rw.startElement("input", dtp);
			rw.writeAttribute("id", clientId+"Input", null);
			rw.writeAttribute("name", clientId, null);
			rw.writeAttribute("type", "hidden", "type");
			if (v != null) {
				rw.writeAttribute("value", getValueAsString(v, fc, dtp), null);
			}
			Tooltip.generateTooltip(fc, dtp, rw);
			rw.endElement("input");
		}
		else // "popup"
		{
			// div
			rw.startElement("div", dtp);
			rw.writeAttribute("class", "input-group date " + styleClass, "class");
			if(dtp.getStyle() != null) rw.writeAttribute("style", dtp.getStyle(), "style");
			rw.writeAttribute("id", divPrefix + clientId, null);

			// input
			rw.startElement("input", dtp);
			rw.writeAttribute("type", "text", null);
			rw.writeAttribute("id", clientId+"input", null);
			rw.writeAttribute("name", clientId, null);
			rw.writeAttribute("class", "form-control " + getErrorAndRequiredClass(dtp, clientId), "class");
			if(BsfUtils.isStringValued(dtp.getPlaceholder())) rw.writeAttribute("placeholder", dtp.getPlaceholder(), null);
			if (v != null) {
				rw.writeAttribute("value", getValueAsString(v, fc, dtp), null);
			}
			Tooltip.generateTooltip(fc, dtp, rw);
			rw.endElement("input");

			// span
			rw.startElement("span", dtp);
			rw.writeAttribute("class", "input-group-addon", "class");
			IconRenderer.encodeIcon(rw, dtp, "calendar", false, null, null, null, false, null, null, dtp.isDisabled(), true, true, true);
			rw.endElement("span");

			// end
			rw.endElement("div");
			if (null != responsiveStyleClass && responsiveStyleClass.trim().length()>0) {
				rw.endElement("div");
			}
		}
		return divPrefix;
	}

	/**
	 * Encode the javascript code
	 * @throws IOException
	 */
	private void encodeJS(FacesContext fc, ResponseWriter rw, DateTimePicker dtp, String divPrefix)
	throws IOException {
		String clientId = dtp.getClientId();
		String fullSelector = "#";
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
		String inlineDisplayDate = "'" + (dtp.getFormat() == null ? getDateAsString(v, format, sloc) : getDateAsString(v, LocaleUtils.momentToJavaFormat(format), sloc)) + "'";


		if("plain".equals(mode)) { fullSelector += BsfUtils.escapeJQuerySpecialCharsInSelector(clientId+"Input"); }
		else if("inline".equals(mode)) { fullSelector += BsfUtils.escapeJQuerySpecialCharsInSelector(divPrefix + clientId); }
		else { fullSelector += BsfUtils.escapeJQuerySpecialCharsInSelector(divPrefix + clientId); }

		rw.startElement("script", dtp);
		rw.writeText("$(function () { " +
					      "$('" + fullSelector + "').datetimepicker({  " +
					        (dtp.isAllowInputToggle() ? 								"collapse: " + dtp.isAllowInputToggle() + ", ": "") +
					      	(dtp.isCollapse() ? 										"allowInputToggle: " + dtp.isCollapse() + ", ": "") +
					      	(BsfUtils.isStringValued(dtp.getDayViewHeaderFormat()) ? 	"dayViewHeaderFormat: '" + dtp.getDayViewHeaderFormat() + "', " : "") +
					      	(BsfUtils.isStringValued(dtp.getDisabledDates()) ?			"disabledDates: [" + dtp.getDisabledDates() + "], " : "") +
					      	(BsfUtils.isStringValued(dtp.getDisableTimeInterval()) ?	"disabledTimeIntervals: [" + dtp.getDisableTimeInterval() + "], " : "") +
					      	(BsfUtils.isStringValued(dtp.getEnabledDates()) ?			"enabledDates: [" + dtp.getDisableTimeInterval() + "], " : "") +
					      	(dtp.isFocusOnShow() ? 										"focusOnShow: " + dtp.isFocusOnShow() + ", ": "") +
					      	(BsfUtils.isStringValued(dtp.getInitialDate()) ?			"defaultDate: [" + dtp.getInitialDate() + "], " : "") +
					      	(dtp.isKeepInvalid() ? 										"keepInvalid: " + dtp.isKeepInvalid() + ", ": "") +
					      	(dtp.isKeepOpen() ? 										"keepOpen: " + dtp.isKeepOpen() + ", ": "") +
					      	(BsfUtils.isStringValued(dtp.getLocale()) ?					"locale: [" + dtp.getLocale() + "], " : "") +
					      	(BsfUtils.isStringValued(dtp.getMinDate()) ?				"minDate: [" + dtp.getMinDate() + "], " : "") +
					      	(BsfUtils.isStringValued(dtp.getMaxDate()) ?				"maxDate: [" + dtp.getMaxDate() + "], " : "") +
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
					      	("inline".equals(mode) ? 									"inline: true," : "" ) +
					      	("inline".equals(mode) ? 									"date: moment(" + inlineDisplayDate + ", " + displayFormat + ")," : "" ) +
					      	"locale: '" + sloc.getLanguage() + "', " +
					      	"format: " + displayFormat +
						  "});" +
					      // ("inline".equals(type) ? "$('" + fullSelector + "').date(" + inlineDisplayDate + ")" : "") +
					  "});", null);

		if("inline".equals(mode)) {

			rw.writeText("$('" + fullSelector + "').on('dp.change', function(e) { " +
						 "   $('#" + BsfUtils.escapeJQuerySpecialCharsInSelector(clientId) + "').val( e.date.format(" + displayFormat + ") ); " +
						 "});", null);
		}
		rw.endElement("script");
	}
}
