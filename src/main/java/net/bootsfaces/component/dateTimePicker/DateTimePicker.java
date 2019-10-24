/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.IResponsiveLabel;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;
import net.bootsfaces.utils.LocaleUtils;

/** This class holds the attributes of &lt;b:dateTimePicker /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(DateTimePicker.COMPONENT_TYPE)
public class DateTimePicker extends DateTimePickerCore
		implements net.bootsfaces.render.IHasTooltip, IResponsive, IAJAXComponent, IAJAXComponent2, IResponsiveLabel {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".dateTimePicker.DateTimePicker";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dateTimePicker.DateTimePicker";

	private static final Collection<String> EVENT_NAMES = Collections
			.unmodifiableCollection(Arrays.asList("blur", "change", "click", "dblclick", "focus", "keydown", "keypress",
					"keyup", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

	public DateTimePicker() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addExtCSSResource("bootstrap-datetimepicker.min.css");
		AddResourcesListener.addBasicJSResource(C.BSF_LIBRARY, "js/moment-with-locales.min.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/bootstrap-datetimepicker.min.js");
		setRendererType(DEFAULT_RENDERER);
	}

	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
			super.processEvent(event);
		}
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

	/**
	 * Returns the subset of the parameter list of jQuery and other non-standard JS
	 * callbacks which is sent to the server via AJAX. If there's no parameter list
	 * for a certain event, the default is simply null.
	 *
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterListsForAjax() {
		return null;
	}

	/**
	 * Returns the parameter list of jQuery and other non-standard JS callbacks. If
	 * there's no parameter list for a certain event, the default is simply "event".
	 *
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterLists() {
		return null;
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

	/**
	 * Converts the date from the moment.js format to a java.util.Date.
	 */
	@Override
	public Object getConvertedValue(FacesContext context, Object submittedValue) throws ConverterException {
		if (submittedValue == null) {
			return null;
		}

		String val = (String) submittedValue;
		// If the Trimmed submitted value is empty, return null
		if (val.trim().length() == 0) {
			return null;
		}

		Converter converter = this.getConverter();

		// If the user supplied a converter, use it
		if (converter != null) {
			return converter.getAsObject(context, this, val);
		}
		// Else we use our own converter
		Locale sloc = BsfUtils.selectLocale(context.getViewRoot().getLocale(), this.getLocale(), this);
		String momentJSFormat = BsfUtils.selectMomentJSDateTimeFormat(sloc, this.getFormat(), this.isShowDate(),
				this.isShowTime());
		String javaFormat = LocaleUtils.momentToJavaFormat(momentJSFormat);

		Calendar cal = Calendar.getInstance(sloc);
		SimpleDateFormat format = new SimpleDateFormat(javaFormat, sloc);
		format.setTimeZone(cal.getTimeZone());

		try {
			cal.setTime(format.parse(val));

			return cal.getTime();
		} catch (ParseException e) {

			// FIRST STEP GONE: TRY THE AUTO-PARSER
			try {
				cal.setTime(LocaleUtils.autoParseDateFormat(val));
				return cal.getTime();
			} catch (Exception pe) {
				this.setValid(false);
				throw new ConverterException(BsfUtils.getMessage("javax.faces.converter.DateTimeConverter.DATE", val,
						javaFormat, BsfUtils.getLabel(context, this)));
			}
		}
	}

	/**
	 * Boolean value to specify if the widget is disabled.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or false, if it hasn't been set
	 *         by the JSF file.
	 */
	public boolean isDisabled() {
		if (super.isDisabled())
			return true;
		UIComponent ancestor = getParent();
		while (ancestor != null) {
			if (ancestor instanceof IContentDisabled) {
				if (((IContentDisabled) ancestor).isContentDisabled()) {
					return true;
				}
			}
			ancestor = ancestor.getParent();
		}
		return false;
	}

	public void setIconAwesome(String _iconAwesome) {
		AddResourcesListener.setNeedsFontsAwesome(this);
		super.setIconAwesome(_iconAwesome);
	}
	
	@Override
	public void setIconBrand(boolean _iconBrand) {
		if (_iconBrand) {
			AddResourcesListener.setFontAwesomeVersion(5, this);
		}
		super.setIconBrand(_iconBrand);
	}

	@Override
	public void setIconRegular(boolean _iconRegular) {
		if (_iconRegular) {
			AddResourcesListener.setFontAwesomeVersion(5, this);
		}
		super.setIconRegular(_iconRegular);
	}

	@Override
	public void setIconLight(boolean _iconLight) {
		if (_iconLight) {
			AddResourcesListener.setFontAwesomeVersion(5, this);
		}
		super.setIconLight(_iconLight);
	}

	@Override
	public void setIconSolid(boolean _iconSolid) {
		if (_iconSolid) {
			AddResourcesListener.setFontAwesomeVersion(5, this);
		}
		super.setIconSolid(_iconSolid);
	}

}
