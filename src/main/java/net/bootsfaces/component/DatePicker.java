/**
 *  Copyright 2014 Riccardo Massera (TheCoder4.Eu)
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

package net.bootsfaces.component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.application.ResourceHandler;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.render.H;
import net.bootsfaces.render.JQ;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/jq.ui.core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/jq.ui.theme.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/jq.ui.datepicker.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
		/* moved to constructor @ResourceDependency(library = "bsf", name = "jq/ui/datepicker.js", target = "head") */
		/* moved to constructor @ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head"), */
		@ResourceDependency(library = "bsf", name = "jq/ui/core.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")

})
@FacesComponent(C.DATEPICKER_COMPONENT_TYPE)
public class DatePicker extends HtmlInputText {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.DATEPICKER_COMPONENT_TYPE;
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
	// private TimeZone stz;
	/**
	 * selected Date Format
	 */
	private String sdf;
	private String mode;

	public DatePicker() {
		setRendererType(null); // this component renders itself

        AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
        AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/core.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/datepicker.js");
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		ResourceHandler rh = app.getResourceHandler();
		Resource rdp;
		Iterator<Locale> preferredLanguages = context.getExternalContext().getRequestLocales();
		while (preferredLanguages.hasNext()) {
			final String jsl = "jq/ui/i18n/datepicker-" + preferredLanguages.next().getLanguage() + ".js";
			rdp = rh.createResource(jsl, C.BSF_LIBRARY);
			if (rdp != null) { //rdp is null if the language .js is not present in jar
				AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, jsl);
				break;
			}

		}
		Tooltip.addResourceFile();
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
		sloc = selectLocale(fc.getViewRoot().getLocale(), A.asString(getAttributes().get(A.LOCALE)));
		sdf = selectDateFormat(sloc, A.asString(getAttributes().get(A.DTFORMAT)));
		SimpleDateFormat format = null;
		Object date = null;
		try {
			format = new SimpleDateFormat(sdf, sloc);

			format.setTimeZone(java.util.TimeZone.getDefault());

			date = format.parse(val);
			((Date) date).setHours(12);

		} catch (ParseException e) {
			this.setValid(false);
			throw new ConverterException(getMessage("javax.faces.converter.DateTimeConverter.DATE", val, sdf, getLabel(fc)));
		}

		return date;
	}

	@Override
	public void encodeBegin(FacesContext fc) throws IOException {
        if (!isRendered()) {
            return;
        }
		/*
		 * Popup <input id="form:popCal" name="form:popCal" type="text" /> <script id="form:popCal_js" type="text/javascript"> $(function(){
		 * $('form:popCal').datepicker({id:'form:popupCal',popup:true,locale:'en_US',dateFormat:'m/d/y'}); }); </script>
		 * 
		 * Inline Adds a Div and Uses a Hidden Input
		 */

		encodeHTML(fc);
		encodeDefaultLanguageJS(fc);
		Tooltip.activateTooltips(fc, getAttributes(), this);
	}

	/**
	 * Generates the default language for the date picker. Originally implemented in the HeadRenderer, this code has been moved here to
	 * provide better compatibility to PrimeFaces. If multiple date pickers are on the page, the script is generated redundantly, but this
	 * shouldn't do no harm.
	 * 
	 * @param fc The current FacesContext
	 * @throws IOException
	 */
	private void encodeDefaultLanguageJS(FacesContext fc) throws IOException {
		ResponseWriter rw = fc.getResponseWriter();
		rw.startElement("script", null);
		rw.write("$.datepicker.setDefaults($.datepicker.regional['" + fc.getViewRoot().getLocale().getLanguage() + "']);");
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
		// stz = selectTimeZone(attrs.get(A.TZ));

		sloc = selectLocale(fc.getViewRoot().getLocale(), A.asString(attrs.get(A.LOCALE)));
		sdf = selectDateFormat(sloc, A.asString(attrs.get(A.DTFORMAT)));

		// Debugging Locale and dateformat
		// rw.write("<span>DEBUG sloc='"+sloc+"', sdf='"+sdf+"' </span>");

		String dpId;

		Object v = getSubmittedValue();
		if (v == null) {
			v = this.getValue();
		}

		/*
		 * 6 modes: 1) inline 2) popup (no icons) 3) popup-icon 4) icon-popup 5) toggle-icon (Default) 6) icon-toggle
		 */
		mode = A.asString(attrs.get("mode"), A.TOGGLEICON);
		boolean inline = mode.equals(A.INLINE);

		if (inline) { // inline => div with ID
			dpId = clientId + "_" + "div";
			rw.startElement("div", this);
			rw.writeAttribute("id", dpId, null);
			rw.endElement("div");
		} else { // popup
			dpId = clientId;

			if (!mode.equals(A.POPUP)) { // with icon => div with prepend/append style
				rw.startElement("div", this);
				rw.writeAttribute("class", "input-group", "class");
				if (mode.equals(A.ICONPOP) || mode.equals(A.ICONTOGGLE)) {
					R.addonIcon(rw, this, CALENDAR, false);
				}
			}
		}
		String type = inline ? "hidden" : "text";

		rw.startElement("input", null);
		rw.writeAttribute("id", clientId, null);
		rw.writeAttribute("name", clientId, null);
		Tooltip.generateTooltip(fc, attrs, rw);
		rw.writeAttribute("type", type, null);
		rw.writeAttribute("class", "form-control", "class");
		if (v != null) {
			rw.writeAttribute("value", getDateAsString(v, sdf, sloc), null);
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
		rw.endElement("input");

		encodeJS(fc, rw, clientId, dpId);
		if (mode.equals(A.POPICON) || mode.equals(A.TOGGLEICON)) {
			R.addonIcon(rw, this, CALENDAR, false);
		}

		if (!inline && !mode.equals(A.POPUP)) {
			rw.endElement("div");
			JQ.datePickerToggler(rw, clientId, clientId + "_" + ADDON);

		} // Closes the popup prepend/append style div
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
			sb.append(JQ.SHOWBUTS).append(":").append(C.TRUE).append(",");
		}
		if (A.toBool(attrs.get(JQ.CHNGMONTH))) {
			sb.append(JQ.CHNGMONTH).append(":").append(C.TRUE).append(",");
		}
		if (A.toBool(attrs.get(JQ.CHNGYEAR))) {
			sb.append(JQ.CHNGYEAR).append(":").append(C.TRUE).append(",");
		}
		if (A.toBool(attrs.get(JQ.SHOWWK))) {
			sb.append(JQ.SHOWWK).append(":").append(C.TRUE).append(",");
		}

		if (mode.equals(A.TOGGLEICON) || mode.equals(A.ICONTOGGLE)) {
			sb.append(JQ.SHOWON).append(":").append("'" + H.BUTTON + "'").append(",");
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

		// If user specifies a specific language to use then we render the datepicker using this language
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
			// dateFormat.setTimeZone(tz);

			return dtFormat.format((Date) dt);
		} else {
			throw new IllegalArgumentException("Value could be either String or java.util.Date");
		}
	}

	// Pass the attrs timezone value
	/*
	 * public static TimeZone selectTimeZone(Object utz) { java.util.TimeZone selTimeZone; if (utz != null) { if (utz instanceof String) {
	 * selTimeZone = java.util.TimeZone.getTimeZone((String) utz); } else if (utz instanceof java.util.TimeZone) { selTimeZone =
	 * (java.util.TimeZone) utz; } else { throw new IllegalArgumentException("TimeZone should be either String or java.util.TimeZone"); } }
	 * else { selTimeZone = java.util.TimeZone.getDefault(); } return selTimeZone; }
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
				throw new IllegalArgumentException("Type:" + loc.getClass() + " is not a valid locale type for DatePicker:"
						+ this.getClientId());
			}
		}

		return selLocale;
	}

	/**
	 * Selects the Date Pattern to use based on the given Locale if the input format is null
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
	 * Simplified and streamlined version of the implementation of Mojarra 2.2.8-b02 (see MessageFactory).
	 *
	 * @param messageId
	 *            - the key of the message in the resource bundle
	 * @param params
	 *            - substitution parameters
	 *
	 * @return a localized <code>FacesMessage</code> with the severity of FacesMessage.SEVERITY_ERROR
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
			if (null != (bundle = ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()))) {
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
			bundle = ResourceBundle.getBundle(FacesMessage.FACES_MESSAGES, locale, Thread.currentThread().getContextClassLoader());
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
	 * Simplified and adapted version of the implementation of Mojarra 2.2.8-b02 (see MessageFactory).
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
		// Use the "clientId" if there was no label specified.
		return (String) getClientId(context);
	}
}