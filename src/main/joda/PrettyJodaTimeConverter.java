package com.anem.green.web.convert;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.ocpsoft.prettytime.jsf.PrettyTimeConverter;

@Named
public class PrettyJodaTimeConverter extends PrettyTimeConverter {

	private static final long serialVersionUID = -7092244860259925929L;

	@Override
	public String getAsString(final FacesContext context, final UIComponent comp, final Object value) {

		if (null == value) {
			return StringUtils.EMPTY;
		}
		else if (value instanceof DateTime) {
			Date date = ((DateTime) value).toDate();
			return super.getAsString(context, comp, date);
		}
		else {
			return super.getAsString(context, comp, value);
		}
	}
}