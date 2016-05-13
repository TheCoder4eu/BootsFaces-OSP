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
		
		encodeHTML(context, rw, dtp);
		encodeJS(context, rw, dtp);
	}
	
	/**
	 * Encodes the HTML for this context
	 * 
	 * @param fc
	 * @throws IOException
	 */
	private void encodeHTML(FacesContext fc, ResponseWriter rw, DateTimePicker dtp) 
	throws IOException {
		String clientId = dtp.getClientId();
		String type = dtp.getType();
		String styleClass = dtp.getStyleClass();
		if(styleClass == null) styleClass = "";
		styleClass += " " + Responsive.getResponsiveStyleClass(dtp, false);
		
		Object v = dtp.getSubmittedValue();
		if (v == null) {
			v = dtp.getValue();
		}
		
		if ("plain".equals(type)) 
		{
			// simple wrapper
			rw.startElement("div", dtp);
			rw.writeAttribute("class", styleClass, "class");
			rw.writeAttribute("id", DTP_CONTAINER_ID + clientId, null);
			
			// input
			rw.startElement("input", dtp);
			rw.writeAttribute("type", "text", null);
			rw.writeAttribute("id", clientId, null);
			rw.writeAttribute("name", clientId, null);
			rw.writeAttribute("class", "form-control " + getErrorAndRequiredClass(dtp, clientId), "class");
			if (v != null) {
				rw.writeAttribute("value", getValueAsString(v, fc, dtp), null);
			}
			Tooltip.generateTooltip(fc, dtp, rw);
			rw.endElement("input");
			
			rw.endElement("div");
		}
		else if ("inline".equals(type)) 
		{
			// div
			rw.startElement("div", dtp);
			rw.writeAttribute("class", "input-group date " + styleClass, "class");
			if(dtp.getStyle() != null) rw.writeAttribute("style", dtp.getStyle(), "style");
			rw.writeAttribute("id", DTP_CONTAINER_ID + clientId, null);
			rw.endElement("div");
			
			// write the input item
			rw.startElement("input", dtp);
			rw.writeAttribute("id", clientId, null);
			rw.writeAttribute("name", clientId, null);
			rw.writeAttribute("type", "hidden", "type");
			if (v != null) {
				rw.writeAttribute("value", getValueAsString(v, fc, dtp), null);
			}
			Tooltip.generateTooltip(fc, dtp, rw);
			rw.endElement("input");
		}
		else // "component"
		{
			// div
			rw.startElement("div", dtp);
			rw.writeAttribute("class", "input-group date " + styleClass, "class");
			if(dtp.getStyle() != null) rw.writeAttribute("style", dtp.getStyle(), "style");
			rw.writeAttribute("id", DTP_CONTAINER_ID + clientId, null);
			
			// input
			rw.startElement("input", dtp);
			rw.writeAttribute("type", "text", null);
			rw.writeAttribute("id", clientId, null);
			rw.writeAttribute("name", clientId, null);
			rw.writeAttribute("class", "form-control " + getErrorAndRequiredClass(dtp, clientId), "class");
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
		}
	}
	
	/**
	 * Encode the javascript code
	 * @param fc
	 * @param rw
	 * @param dp
	 * @throws IOException
	 */
	private void encodeJS(FacesContext fc, ResponseWriter rw, DateTimePicker dtp) 
	throws IOException {
		String clientId = dtp.getClientId();
		String fullSelector = "#";
		String type = dtp.getType();
		
		Object v = dtp.getSubmittedValue();
		if (v == null) {
			v = dtp.getValue();
		}
		
		Locale sloc = BsfUtils.selectLocale(fc.getViewRoot().getLocale(), dtp.getLocale(), dtp);
		String format = BsfUtils.selectDateFormat(sloc, dtp.getFormat());
		String displayFormat = "'" + (dtp.getFormat() == null ? LocaleUtils.javaToMomentFormat(format) : format) + "'";
		String inlineDisplayDate = "'" + (dtp.getFormat() == null ? getDateAsString(v, format, sloc) : getDateAsString(v, LocaleUtils.momentToJavaFormat(format), sloc)) + "'";
		
		
		if("plain".equals(type)) { fullSelector += BsfUtils.escapeJQuerySpecialCharsInSelector(clientId); }
		else if("inline".equals(type)) { fullSelector += BsfUtils.escapeJQuerySpecialCharsInSelector(DTP_CONTAINER_ID + clientId); }
		else { fullSelector += BsfUtils.escapeJQuerySpecialCharsInSelector(DTP_CONTAINER_ID + clientId); }

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
					      	(dtp.isShowCalendarWeeks() ? 								"calendarWeeks: " + dtp.isShowCalendarWeeks() + ", ": "") +
					      	(dtp.isShowClearButton() ? 									"showClear: " + dtp.isShowClearButton() + ", ": "") +
					      	(dtp.isShowCloseButton() ? 									"showClose: " + dtp.isShowCloseButton() + ", ": "") +
					      	(dtp.isShowTodayButton() ? 									"showTodayButton: " + dtp.isShowTodayButton() + ", ": "") +
					      	(dtp.isSideBySide() || "inline".equals(type) ? 				"sideBySide: true, ": "") +
					      	(dtp.getTimeStepping() > 0 ?								"stepping: " + dtp.getTimeStepping() + ", ": "") +
					      	(BsfUtils.isStringValued(dtp.getToolbarPlacement()) ?		"toolbarPlacement: '" + dtp.getToolbarPlacement() + "', " : "") +
					      	(BsfUtils.isStringValued(dtp.getViewMode()) ?				"viewMode: '" + dtp.getViewMode() + "', " : "") +
					      	(dtp.isUseCurrent() ? 										"useCurrent: " + dtp.isUseCurrent() + ", ": "") +
					      	(dtp.isUseStrict() ? 										"useStrict: " + dtp.isUseStrict() + ", ": "") +
					      	("inline".equals(type) ? 									"inline: true," : "" ) +
					      	("inline".equals(type) ? 									"date: moment(" + inlineDisplayDate + ", " + displayFormat + ")," : "" ) +
					      	"locale: '" + sloc.getLanguage() + "', " +
					      	"format: " + displayFormat + 
						  "});" +
					      // ("inline".equals(type) ? "$('" + fullSelector + "').date(" + inlineDisplayDate + ")" : "") +
					  "});", null);
		
		if("inline".equals(type)) {
			
			rw.writeText("$('" + fullSelector + "').on('dp.change', function(e) { " +
						 "   $('#" + BsfUtils.escapeJQuerySpecialCharsInSelector(clientId) + "').val( e.date.format(" + displayFormat + ") ); " +
						 "});", null);
		}
		rw.endElement("script");
	}
}
