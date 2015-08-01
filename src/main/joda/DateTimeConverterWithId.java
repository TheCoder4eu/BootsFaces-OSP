package com.anem.green.web.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 */
@FacesConverter("jodaDateTime")
public class DateTimeConverterWithId implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return new DateTimeConverter().getAsObject(context, component, value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return new DateTimeConverter().getAsString(context, component, value);
	}
}
