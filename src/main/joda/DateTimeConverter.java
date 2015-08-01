package com.anem.green.web.convert;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.anem.green.web.SessionPreferences;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 */
public class DateTimeConverter implements Converter {

	private ValueExpression timeZoneExpression;

	/**
	 * used to temporarily store the facesContext during the duration of a getAsString or getAsObject
	 */
	transient FacesContext facesContext;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		this.facesContext = facesContext;
		Class<?> type = ValueExpressionHelper.getValueType(facesContext, uiComponent,
				Lists.<Class<?>> newArrayList(DateTime.class, LocalDate.class, LocalTime.class));
		Preconditions.checkArgument(type != null, "DateTimeConverter is not attached to a component bound to either a"
				+ " DateTime, LocalDate, or LocalTime.");
		Object valueAsObject;
		if (type.isAssignableFrom(DateTime.class)) {
			valueAsObject = (DateTime) getAsObjectInstance(facesContext, uiComponent, value);
		}
		else if (type.isAssignableFrom(LocalDate.class)) {
			Object dateTime = getAsObjectInstance(facesContext, uiComponent, value);
			valueAsObject = dateTime == null ? null : new LocalDate(dateTime);
		}
		else if (type.isAssignableFrom(LocalTime.class)) {
			Object dateTime = getAsObjectInstance(facesContext, uiComponent, value);
			valueAsObject = dateTime == null ? null : new LocalTime(dateTime);
		}
		else {
			throw new AssertionError("ValueExpressionHelper.getValueType() broke its contract");
		}
		this.facesContext = null;
		return valueAsObject;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		this.facesContext = facesContext;
		Class<?> type = ValueExpressionHelper.getValueType(facesContext, uiComponent,
				Lists.<Class<?>> newArrayList(DateTime.class, LocalDate.class, LocalTime.class));
		Preconditions.checkArgument(type != null, "DateTimeConverter is not attached to a component bound to either a"
				+ " DateTime, LocalDate, or LocalTime.");
		if (value instanceof LocalDate) {
			LocalDate localDate = (LocalDate) value;
			return getAsStringValue(facesContext, uiComponent, localDate.toDateTimeAtStartOfDay(getTimeZone()));
		}
		if (value instanceof LocalTime) {
			LocalTime localTime = (LocalTime) value;
			return getAsStringValue(facesContext, uiComponent, localTime.toDateTimeToday(getTimeZone()));
		}
		if (value instanceof ReadablePartial) {
			ReadablePartial readablePartial = (ReadablePartial) value;
			return getAsStringValue(facesContext, uiComponent, readablePartial.toDateTime(new DateTime()));
		}
		this.facesContext = null;
		return getAsStringValue(facesContext, uiComponent, value);
	}

	private Object getAsObjectInstance(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (facesContext == null) {
			throw new NullPointerException("facesContext");
		}
		if (uiComponent == null) {
			throw new NullPointerException("uiComponent");
		}

		if (value != null) {
			value = value.trim();
			if (value.length() > 0) {
				DateTimeFormatter format = getDateFormat(uiComponent);

				try {
					return format.parseDateTime(value);
				}
				catch (IllegalArgumentException e) {
					throw new ConverterException(e);
				}
			}
		}
		return null;
	}

	private String getAsStringValue(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (facesContext == null) {
			throw new NullPointerException("facesContext");
		}
		if (uiComponent == null) {
			throw new NullPointerException("uiComponent");
		}

		if (value == null) {
			return "";
		}
		if (value instanceof String) {
			return (String) value;
		}

		DateTimeFormatter format = getDateFormat(uiComponent);

		try {
			return format.print((ReadableInstant) value);
		}
		catch (Exception e) {
			throw new ConverterException("Cannot convert value '" + value + "'");
		}
	}

	private DateTimeFormatter getDateFormat(UIComponent uiComponent) {
		DateTimeFormatter format = DateTimeFormat.forPattern(getPattern(uiComponent));

		format = format.withLocale(SessionPreferences.getCurrentLocale());
		format = format.withZone(getTimeZone());

		return format;
	}

	public DateTimeZone getTimeZone() {
		if (timeZoneExpression == null) {
			return DateTimeZone.getDefault();
		}
		else {
			ELContext context;// TODO: Need to know how to test this.
			if (facesContext == null) {
				context = FacesContext.getCurrentInstance().getELContext();
			}
			else {
				context = facesContext.getELContext();
			}
			return (DateTimeZone) timeZoneExpression.getValue(context);
		}
	}

	public static String getPattern(UIComponent component) {
		Map<String, Object> properties = component.getAttributes();
		String pattern = (String) properties.get("dateFormat");
		if (null == pattern) {
			pattern = (String) properties.get("pattern");
		}
		if (null == pattern) {
			pattern = "MM/dd/yyyy";
		}
		return pattern;
	}
}
