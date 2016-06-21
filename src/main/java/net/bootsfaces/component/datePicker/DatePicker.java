/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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

package net.bootsfaces.component.datePicker;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import net.bootsfaces.C;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.JQ;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author thecoder4.eu
 */
@FacesComponent("net.bootsfaces.component.datepicker.Datepicker")
public class DatePicker extends HtmlInputText implements IResponsive {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.datepicker.Datepicker";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String ADDON = "input-group-addon";
	public static final String CALENDAR = "calendar";

	/**
	 * Selected Locale
	 */
	private Locale sloc;

	/**
	 * selected Date Format
	 */
	private String sdf;
	private String mode;

	public DatePicker() {
		setRendererType(null); // this component renders itself

		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("jq.ui.core.css");
		AddResourcesListener.addThemedCSSResource("jq.ui.theme.css");
		AddResourcesListener.addThemedCSSResource("jq.ui.datepicker.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");

		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/core.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/datepicker.js");
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		ResourceHandler rh = app.getResourceHandler();
		Resource rdp;
		Iterator<Locale> preferredLanguages = context.getExternalContext().getRequestLocales();
		while (preferredLanguages.hasNext()) {
			String language = preferredLanguages.next().getLanguage();
			if ("en".equals(language)) {
				break;
			}
			final String jsl = "jq/ui/i18n/datepicker-" + language + ".js";
			rdp = rh.createResource(jsl, C.BSF_LIBRARY);
			if (rdp != null) { // rdp is null if the language .js is not present
								// in jar
				AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, jsl);
				break;
			}

		}
		Tooltip.addResourceFiles();
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public void decode(FacesContext fc) {
		String subVal = fc.getExternalContext().getRequestParameterMap().get(getClientId(fc));

		if (subVal != null) {
			this.setSubmittedValue(subVal);
			this.setValid(true);
		}
	}

	@Override
	protected Object getConvertedValue(FacesContext fc, Object sval) throws ConverterException {
		if (sval == null) {
			return null;
		}

		String val = (String) sval;
		// If the Trimmed submitted value is empty, return null
		if (val.trim().length() == 0) {
			return null;
		}

		Converter converter = getConverter();

		// If the user supplied a converter, use it
		if (converter != null) {
			return converter.getAsObject(fc, this, val);
		}
		// Else we use our own converter
		sloc = selectLocale(fc.getViewRoot().getLocale(), A.asString(getAttributes().get(JQ.LOCALE)));
		sdf = selectDateFormat(sloc, A.asString(getAttributes().get(JQ.DTFORMAT)));

		Calendar cal = Calendar.getInstance(sloc);
		SimpleDateFormat format = new SimpleDateFormat(sdf, sloc);
		format.setTimeZone(cal.getTimeZone());

		try {
			cal.setTime(format.parse(val));
			cal.set(Calendar.HOUR_OF_DAY, 12);

			return cal.getTime();
		} catch (ParseException e) {
			this.setValid(false);
			throw new ConverterException(
					getMessage("javax.faces.converter.DateTimeConverter.DATE", val, sdf, getLabel(fc)));
		}
	}

	@Override
	public void encodeBegin(FacesContext fc) throws IOException {
		if (!isRendered()) {
			return;
		}
		/*
		 * Popup <input id="form:popCal" name="form:popCal" type="text" />
		 * <script id="form:popCal_js" type="text/javascript"> $(function(){
		 * $('form:popCal').datepicker({id:'form:popupCal',popup:true,locale:'
		 * en_US',dateFormat:'m/d/y'}); }); </script>
		 *
		 * Inline Adds a Div and Uses a Hidden Input
		 */

		encodeHTML(fc);
		encodeDefaultLanguageJS(fc);
		Tooltip.activateTooltips(fc, this);
	}

	/**
	 * Generates the default language for the date picker. Originally
	 * implemented in the HeadRenderer, this code has been moved here to provide
	 * better compatibility to PrimeFaces. If multiple date pickers are on the
	 * page, the script is generated redundantly, but this shouldn't do no harm.
	 *
	 * @param fc
	 *            The current FacesContext
	 * @throws IOException
	 */
	private void encodeDefaultLanguageJS(FacesContext fc) throws IOException {
		ResponseWriter rw = fc.getResponseWriter();
		rw.startElement("script", null);
		rw.write("$.datepicker.setDefaults($.datepicker.regional['" + fc.getViewRoot().getLocale().getLanguage()
				+ "']);");
		rw.endElement("script");
	}

	/**
	 * Encodes the HTML for this context
	 *
	 * @param fc
	 * @throws IOException
	 */
	private void encodeHTML(FacesContext fc) throws IOException {
		Map<String, Object> attrs = getAttributes();
		String clientId = getClientId(fc);
		ResponseWriter rw = fc.getResponseWriter();
		String responsiveStyleClass = Responsive.getResponsiveStyleClass(this, false).trim();
		if (responsiveStyleClass.length() > 0) {
			rw.startElement("div", this);
			rw.writeAttribute("class", responsiveStyleClass, "class");
		}
		// stz = selectTimeZone(attrs.get(A.TZ));

		sloc = selectLocale(fc.getViewRoot().getLocale(), A.asString(attrs.get(JQ.LOCALE)));
		sdf = selectDateFormat(sloc, A.asString(attrs.get(JQ.DTFORMAT)));

		// Debugging Locale and dateformat
		// rw.write("<span>DEBUG sloc='"+sloc+"', sdf='"+sdf+"' </span>");

		String dpId;

		Object v = getSubmittedValue();
		if (v == null) {
			v = this.getValue();
		}

		/*
		 * 6 modes: 1) inline 2) popup (no icons) 3) popup-icon 4) icon-popup 5)
		 * toggle-icon (Default) 6) icon-toggle
		 */
		boolean isDisabled = A.toBool(attrs.get("disabled"));
		mode = A.asString(attrs.get("mode"), "toggle-icon");
		boolean inline = mode.equals("inline");

		if (inline) { // inline => div with ID
			dpId = clientId + "_" + "div";
			rw.startElement("div", this);
			rw.writeAttribute("id", dpId, null);
			rw.endElement("div");
		} else { // popup
			dpId = clientId;

			if (!mode.equals("popup")) { // with icon => div with prepend/append
											// style
				rw.startElement("div", this);
				rw.writeAttribute("class", "input-group", "class");
				if (mode.equals("icon-popup") || mode.equals("icon-toggle")) {
					rw.startElement("span", this);
					rw.writeAttribute("id", clientId + "_" + ADDON, "id");
					rw.writeAttribute("class", ADDON, "class");
					IconRenderer.encodeIcon(rw, this, "calendar", false, null, null, null, false, null, null,
							isDisabled, true, true, true);
					rw.endElement("span");
				}
			}
		}
		String type = inline ? "hidden" : "text";

		rw.startElement("input", null);
		rw.writeAttribute("id", clientId, null);
		rw.writeAttribute("name", clientId, null);
		Tooltip.generateTooltip(fc, this, rw);
		rw.writeAttribute("type", type, null);
		String styleClass = new CoreRenderer().getErrorAndRequiredClass(this, clientId);
		rw.writeAttribute("class", "form-control " + styleClass, "class");
		if (v != null) {
			rw.writeAttribute("value", getDateAsString(v, sdf, sloc), null);
		}

		String ph = A.asString(attrs.get("placeholder"));
		if (ph != null) {
			rw.writeAttribute("placeholder", ph, null);
		}

		if (isDisabled) {
			rw.writeAttribute("disabled", "disabled", null);
		}
		if (A.toBool(attrs.get("readonly"))) {
			rw.writeAttribute("readonly", "readonly", null);
		}
		rw.endElement("input");

		encodeJS(fc, rw, clientId, dpId);
		if (mode.equals("popup-icon") || mode.equals("toggle-icon")) {
			rw.startElement("span", this);
			rw.writeAttribute("id", clientId + "_" + ADDON, "id");
			rw.writeAttribute("class", ADDON, "class");

			IconRenderer.encodeIcon(rw, this, "calendar", false, null, null, null, false, null, null, isDisabled, true,
					true, true);
			rw.endElement("span");
		}

		if (!inline && !mode.equals("popup")) {
			rw.endElement("div");
			JQ.datePickerToggler(rw, clientId, clientId + "_" + ADDON);

		} // Closes the popup prepend/append style div

		if (responsiveStyleClass.length() > 0) {
			rw.endElement("div");
		}
	}

	private void encodeJS(FacesContext fc, ResponseWriter rw, String cId, String dpId) throws IOException {
		Map<String, Object> attrs = getAttributes();

		StringBuilder sb = new StringBuilder(150);
		sb.append(JQ.DTFORMAT).append(":").append("'" + convertFormat(sdf) + "'").append(",");

		if (A.toInt(attrs.get(JQ.NUMOFMONTHS)) > 0) {
			sb.append(JQ.NUMOFMONTHS).append(":").append(attrs.get(JQ.NUMOFMONTHS)).append(",");
		}
		if (A.toInt(attrs.get(JQ.FIRSTDAY)) > 0) {
			sb.append(JQ.FIRSTDAY).append(":").append(attrs.get(JQ.FIRSTDAY)).append(",");
		}
		if (A.toBool(attrs.get(JQ.SHOWBUTS))) {
			sb.append(JQ.SHOWBUTS).append(":").append("true").append(",");
		}
		if (A.toBool(attrs.get(JQ.CHNGMONTH))) {
			sb.append(JQ.CHNGMONTH).append(":").append("true").append(",");
		}
		if (A.toBool(attrs.get(JQ.CHNGYEAR))) {
			sb.append(JQ.CHNGYEAR).append(":").append("true").append(",");
		}
		if (A.toBool(attrs.get(JQ.SHOWWK))) {
			sb.append(JQ.SHOWWK).append(":").append("true").append(",");
		}

		if (mode.equals("toggle-icon") || mode.equals("icon-toggle")) {
			sb.append(JQ.SHOWON).append(":").append("'" + "button" + "'").append(",");
		}
		if (A.toBool(attrs.get(JQ.DTDISABLED))) {
			sb.append(JQ.DTDISABLED).append(":").append("true").append(",");
		}

		/*
		 * Attributes that need decoding the Date
		 */
		if (attrs.get(JQ.MINDATE) != null) {
			sb.append(JQ.MINDATE + ":" + "'").append(getDateAsString(attrs.get(JQ.MINDATE), sdf, sloc)).append("'");
		}
		if (attrs.get(JQ.MAXDATE) != null) {
			sb.append(JQ.MAXDATE + ":" + "'").append(getDateAsString(attrs.get(JQ.MAXDATE), sdf, sloc)).append("'");
		}

		// If user specifies a specific language to use then we render the
		// datepicker using this language
		// else we use the selected locale language
		String l = A.asString(attrs.get(JQ.LANG));
		if (l == null) {
			l = sloc.getLanguage();
		}
		JQ.datePicker(rw, cId, dpId, sb.toString(), l);
	}

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

	// Pass the attrs timezone value
	/*
	 * public static TimeZone selectTimeZone(Object utz) { java.util.TimeZone
	 * selTimeZone; if (utz != null) { if (utz instanceof String) { selTimeZone
	 * = java.util.TimeZone.getTimeZone((String) utz); } else if (utz instanceof
	 * java.util.TimeZone) { selTimeZone = (java.util.TimeZone) utz; } else {
	 * throw new IllegalArgumentException(
	 * "TimeZone should be either String or java.util.TimeZone"); } } else {
	 * selTimeZone = java.util.TimeZone.getDefault(); } return selTimeZone; }
	 */

	// Pass facesContext.getViewRoot().getLocale() and attrs locale value
	public Locale selectLocale(Locale vrloc, Object loc) {
		java.util.Locale selLocale = vrloc;

		if (loc != null) {
			if (loc instanceof String) {
				selLocale = toLocale((String) loc);
			} else if (loc instanceof java.util.Locale) {
				selLocale = (java.util.Locale) loc;
			} else {
				throw new IllegalArgumentException(
						"Type:" + loc.getClass() + " is not a valid locale type for DatePicker:" + this.getClientId());
			}
		}

		return selLocale;
	}

	/**
	 * Selects the Date Pattern to use based on the given Locale if the input
	 * format is null
	 *
	 * @param locale
	 *            Locale (may be the result of a call to selectLocale)
	 * @param format
	 *            Input format String
	 * @return Date Pattern eg. dd/MM/yyyy
	 */
	public static String selectDateFormat(Locale locale, String format) {
		String selFormat;

		if (format == null) {
			selFormat = ((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale)).toPattern();
			// Since DateFormat.SHORT is silly, return a smart format
			if (selFormat.equals("M/d/yy")) {
				return "MM/dd/yyyy";
			}
			if (selFormat.equals("d/M/yy")) {
				return "dd/MM/yyyy";
			}
		} else {
			selFormat = format;
		}

		return selFormat;
	}

	/**
	 * Implementation from Apache Commons Lang
	 *
	 * @param str
	 * @return
	 */
	public static Locale toLocale(String str) {
		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len != 2 && len != 5 && len < 7) {
			throw new IllegalArgumentException("Invalid locale format: " + str);
		}
		char ch0 = str.charAt(0);
		char ch1 = str.charAt(1);
		if (ch0 < 'a' || ch0 > 'z' || ch1 < 'a' || ch1 > 'z') {
			throw new IllegalArgumentException("Invalid locale format: " + str);
		}
		if (len == 2) {
			return new Locale(str, "");
		} else {
			if (str.charAt(2) != '_') {
				throw new IllegalArgumentException("Invalid locale format: " + str);
			}
			char ch3 = str.charAt(3);
			if (ch3 == '_') {
				return new Locale(str.substring(0, 2), "", str.substring(4));
			}
			char ch4 = str.charAt(4);
			if (ch3 < 'A' || ch3 > 'Z' || ch4 < 'A' || ch4 > 'Z') {
				throw new IllegalArgumentException("Invalid locale format: " + str);
			}
			if (len == 5) {
				return new Locale(str.substring(0, 2), str.substring(3, 5));
			} else {
				if (str.charAt(5) != '_') {
					throw new IllegalArgumentException("Invalid locale format: " + str);
				}
				return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
			}
		}
	}

	/**
	 * Converts a java Date format to a jQuery date format
	 *
	 * @param format
	 *            Format to be converted
	 * @return converted format
	 */
	public static String convertFormat(String format) {
		if (format == null)
			return null;
		else {
			// day of week
			format = format.replaceAll("EEE", "D");
			// year
			format = format.replaceAll("yy", "y");

			// month
			if (format.indexOf("MMM") != -1) {
				format = format.replaceAll("MMM", "M");
			} else {
				format = format.replaceAll("M", "m");
			}
			return format;
		}
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * <p>
	 * Creates and returns a FacesMessage for the specified Locale.
	 * </p>
	 * Simplified and streamlined version of the implementation of Mojarra
	 * 2.2.8-b02 (see MessageFactory).
	 *
	 * @param messageId
	 *            - the key of the message in the resource bundle
	 * @param params
	 *            - substitution parameters
	 *
	 * @return a localized <code>FacesMessage</code> with the severity of
	 *         FacesMessage.SEVERITY_ERROR
	 */
	public static FacesMessage getMessage(String messageId, String... params) {
		String summary = null;
		String detail = null;
		ResourceBundle bundle;
		String bundleName;
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = context.getViewRoot().getLocale();

		// see if we have a user-provided bundle
		Application app = (FacesContext.getCurrentInstance().getApplication());
		if (null != (bundleName = app.getMessageBundle())) {
			if (null != (bundle = ResourceBundle.getBundle(bundleName, locale,
					Thread.currentThread().getContextClassLoader()))) {
				// see if we have a hit
				try {
					summary = bundle.getString(messageId);
					detail = bundle.getString(messageId + "_detail");
				} catch (MissingResourceException e) {
					// ignore
				}
			}
		}

		// we couldn't find a summary in the user-provided bundle
		if (null == summary) {
			// see if we have a summary in the app provided bundle
			bundle = ResourceBundle.getBundle(FacesMessage.FACES_MESSAGES, locale,
					Thread.currentThread().getContextClassLoader());
			if (null == bundle) {
				throw new NullPointerException();
			}
			// see if we have a hit
			try {
				summary = bundle.getString(messageId);
				detail = bundle.getString(messageId + "_detail");
			} catch (MissingResourceException e) {
				// ignore
			}
		}

		for (int i = 0; i < params.length; i++) {
			summary = summary.replace("{" + i + "}", params[i]);
			detail = detail.replace("{" + i + "}", params[i]);
		}

		// At this point, we have a summary and a bundle.
		FacesMessage ret = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		ret.setSeverity(FacesMessage.SEVERITY_ERROR);
		return ret;
	}

	/**
	 * <p>
	 * Returns the <code>label</code> property from the specified component.
	 * </p>
	 * Simplified and adapted version of the implementation of Mojarra 2.2.8-b02
	 * (see MessageFactory).
	 *
	 * @param context
	 *            - the <code>FacesContext</code> for the current request
	 *
	 * @return the label, if any, of the component
	 */
	public String getLabel(FacesContext context) {
		Object o = getAttributes().get("label");
		if (o == null || (o instanceof String && ((String) o).length() == 0)) {
			ValueExpression vex = getValueExpression("label");
			if (null != vex)
				return (String) vex.getValue(context.getELContext());
		}
		if (o == null) {
			// Use the "clientId" if there was no label specified.
			o = getClientId(context);
		}
		return (String) o;
	}

	/**
	 * Yields the value of the required and error level CSS class.
	 *
	 * @param input
	 * @param clientId
	 * @return
	 */
	public String getErrorAndRequiredClass(UIInput input, String clientId) {
		String[] levels = { "bf-no-message", "bf-info", "bf-warning", "bf-error", "bf-fatal" };
		int level = 0;
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages(clientId);
		if (null != messages) {
			while (messages.hasNext()) {
				FacesMessage message = messages.next();
				if (message.getSeverity().equals(FacesMessage.SEVERITY_INFO))
					if (level < 1)
						level = 1;
				if (message.getSeverity().equals(FacesMessage.SEVERITY_WARN))
					if (level < 2)
						level = 2;
				if (message.getSeverity().equals(FacesMessage.SEVERITY_ERROR))
					if (level < 3)
						level = 3;
				if (message.getSeverity().equals(FacesMessage.SEVERITY_FATAL))
					if (level < 4)
						level = 4;
			}
		}
		String styleClass = levels[level];
		if (input.isRequired()) {
			styleClass += " bf-required";
		}
		return styleClass;
	}

	protected enum PropertyKeys {
		colLg,
		colMd,
		colSm,
		colXs,
		display,
		hidden,
		largeScreen,
		mediumScreen,
		mode,
		offset,
		offsetLg,
		offsetMd,
		offsetSm,
		offsetXs,
		placeholder,
		smallScreen,
		span,
		tinyScreen,
		visible;
		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}


	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColLg() {
		return (String) getStateHelper().eval(PropertyKeys.colLg, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColLg(String _colLg) {
		getStateHelper().put(PropertyKeys.colLg, _colLg);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColMd() {
		return (String) getStateHelper().eval(PropertyKeys.colMd, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColMd(String _colMd) {
		getStateHelper().put(PropertyKeys.colMd, _colMd);
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColSm() {
		return (String) getStateHelper().eval(PropertyKeys.colSm, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColSm(String _colSm) {
		getStateHelper().put(PropertyKeys.colSm, _colSm);
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColXs() {
		return (String) getStateHelper().eval(PropertyKeys.colXs, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColXs(String _colXs) {
		getStateHelper().put(PropertyKeys.colXs, _colXs);
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDisplay() {
		return (String) getStateHelper().eval(PropertyKeys.display, "block");
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisplay(String _display) {
		getStateHelper().put(PropertyKeys.display, _display);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHidden() {
		return (String) getStateHelper().eval(PropertyKeys.hidden);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHidden(String _hidden) {
		getStateHelper().put(PropertyKeys.hidden, _hidden);
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLargeScreen() {
		return (String) getStateHelper().eval(PropertyKeys.largeScreen, "-1");
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLargeScreen(String _largeScreen) {
		getStateHelper().put(PropertyKeys.largeScreen, _largeScreen);
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getMediumScreen() {
		return (String) getStateHelper().eval(PropertyKeys.mediumScreen, "-1");
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMediumScreen(String _mediumScreen) {
		getStateHelper().put(PropertyKeys.mediumScreen, _mediumScreen);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffset() {
		return (String) getStateHelper().eval(PropertyKeys.offset);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffset(String _offset) {
		getStateHelper().put(PropertyKeys.offset, _offset);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetLg() {
		return (String) getStateHelper().eval(PropertyKeys.offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetLg(String _offsetLg) {
		getStateHelper().put(PropertyKeys.offsetLg, _offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetMd() {
		return (String) getStateHelper().eval(PropertyKeys.offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetMd(String _offsetMd) {
		getStateHelper().put(PropertyKeys.offsetMd, _offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetSm() {
		return (String) getStateHelper().eval(PropertyKeys.offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetSm(String _offsetSm) {
		getStateHelper().put(PropertyKeys.offsetSm, _offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetXs() {
		return (String) getStateHelper().eval(PropertyKeys.offsetXs);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetXs(String _offsetXs) {
		getStateHelper().put(PropertyKeys.offsetXs, _offsetXs);
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSmallScreen() {
		return (String) getStateHelper().eval(PropertyKeys.smallScreen, "-1");
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSmallScreen(String _smallScreen) {
		getStateHelper().put(PropertyKeys.smallScreen, _smallScreen);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSpan() {
		return (String) getStateHelper().eval(PropertyKeys.span);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(String _span) {
		getStateHelper().put(PropertyKeys.span, _span);
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTinyScreen() {
		return (String) getStateHelper().eval(PropertyKeys.tinyScreen, "-1");
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTinyScreen(String _tinyScreen) {
		getStateHelper().put(PropertyKeys.tinyScreen, _tinyScreen);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getVisible() {
		return (String) getStateHelper().eval(PropertyKeys.visible);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVisible(String _visible) {
		getStateHelper().put(PropertyKeys.visible, _visible);
	}

}