/**
 * Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
 *
 * This file is part of BootsFaces.
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
package net.bootsfaces.component.inputText;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.beans.ELTools;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.IResponsiveLabel;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author thecoder4.eu
 */
@FacesComponent("net.bootsfaces.component.inputText.InputText")
public class InputText extends InputTextCore implements IHasTooltip, IAJAXComponent, IResponsive, IResponsiveLabel {

	private String renderLabel = null;

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.inputText.InputText";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	private static final Collection<String> EVENT_NAMES = Collections
			.unmodifiableCollection(Arrays.asList("blur", "change", "click", "dblclick", "focus", "input", "keydown",
					"keypress", "keyup", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).
	 *
	 * @return
	 */
	public Map<String, String> getJQueryEvents() {
		return null;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "input";
	}

	public InputText() {
		setRendererType("net.bootsfaces.component.inputText.InputText");
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		renderLabel = BsfUtils.getInitParam("net.bootsfaces.defaults.renderLabel");
		if (null != renderLabel && renderLabel.contains("#{")) {
			renderLabel = ELTools.evalAsString(renderLabel);
		}
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Show the words of the input text as tags (similar to price tags in the supermarket). You can select one or more tags. The list is sent to the backend bean as a comma-separated list. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTags(boolean _tags) {
		if (_tags) {
			AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/bootstrap-tagsinput.min.js");
			//!bs-less//AddResourcesListener.addThemedCSSResource("labels.css");
			AddResourcesListener.addThemedCSSResource("bootstrap-tagsinput.css");

		}
		super.setTags(_tags);
	}

	/**
	 * Activates the type-ahead aka autocomplete function. The list of values has to be defined in typeahead-values. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	@Override
	public void setTypeahead(boolean _typeahead) {
		if (_typeahead) {
			AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/typeahead.js");
			AddResourcesListener.addThemedCSSResource("typeahead.css");
		}
		super.setTypeahead(_typeahead);
	}

	@Override
	public void setTypeaheadValues(String _typeaheadValues) {
		setTypeahead(true);
		super.setTypeaheadValues(_typeaheadValues);
	}


}
