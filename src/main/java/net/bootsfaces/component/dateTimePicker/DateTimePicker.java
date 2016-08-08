/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;
import net.bootsfaces.utils.LocaleUtils;

/** This class holds the attributes of &lt;b:dateTimePicker /&gt;. */
@FacesComponent("net.bootsfaces.component.dateTimePicker.DateTimePicker")
public class DateTimePicker extends DateTimePickerCore
implements net.bootsfaces.render.IHasTooltip, IResponsive, IAJAXComponent {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.dateTimePicker.DateTimePicker";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dateTimePicker.DateTimePicker";
	
	private static final Collection<String> EVENT_NAMES = Collections
			.unmodifiableCollection(Arrays.asList("blur", "change", "click", "dblclick", "focus", "keydown",
					"keypress", "keyup", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

	public DateTimePicker() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("bootstrap-datetimepicker.min.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/moment-with-locales.min.js");
		// AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/moment-jdateformatparser.min.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/bootstrap-datetimepicker.min.js");
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	

	@Override
	public Map<String, String> getJQueryEvents() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("dtchange", "dp.change");
		return result;
	}
	
	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}

	/**
	 * Manage EL-expression for snake-case attributes
	 */
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}
	
	@Override
	public Object getConvertedValue(FacesContext context, Object submittedValue)
	throws ConverterException {
		if (submittedValue == null) {
			return null;
		}

		String val = (String) submittedValue;
		// If the Trimmed submitted value is empty, return null
		if (val.trim().length() == 0) {
			return null;
		}
		//System.out.println("CV: " + val);

		Converter converter = this.getConverter();

		// If the user supplied a converter, use it
		if (converter != null) {
			return converter.getAsObject(context, this, val);
		}
		// Else we use our own converter
		Locale sloc = BsfUtils.selectLocale(context.getViewRoot().getLocale(), this.getLocale(), this);
		String componentFormat = BsfUtils.selectDateTimeFormat(sloc, this.getFormat(), this.isShowDate(), this.isShowTime());
		String sdf = BsfUtils.selectDateFormat(sloc, componentFormat);
		sdf = LocaleUtils.momentToJavaFormat(sdf);

		Calendar cal = Calendar.getInstance(sloc);
		SimpleDateFormat format = new SimpleDateFormat(sdf, sloc);
		format.setTimeZone(cal.getTimeZone());

		try {
			cal.setTime(format.parse(val));

			return cal.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			this.setValid(false);
			throw new ConverterException(
					BsfUtils.getMessage("javax.faces.converter.DateTimeConverter.DATE", val, sdf, BsfUtils.getLabel(context, this)));
		}
	}
}
