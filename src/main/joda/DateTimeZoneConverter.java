package com.anem.green.web.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

import com.google.common.base.Preconditions;

public class DateTimeZoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		try {
			return DateTimeZone.forID(value);
		}
		catch (IllegalArgumentException e) {
			throw new ConverterException("Invalid timezone id: " + value, e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		Preconditions.checkArgument(value instanceof DateTimeZone, "%s is not a DateTimeZone", value);
		return ((DateTimeZone) value).getID();
	}
}