/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.radiobutton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.beans.ELTools;
import net.bootsfaces.component.SelectItemAndComponent;
import net.bootsfaces.component.SelectItemUtils;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.inputText.InputTextRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:radiobutton /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.radiobutton.Radiobutton")
public class RadiobuttonRenderer extends InputTextRenderer {
	
	private UIComponent findComponentByName(UIComponent c, String name) {
		Iterator<UIComponent> children = c.getFacetsAndChildren();
		while(children.hasNext()) {
			UIComponent comp = children.next();
			if (comp instanceof Radiobutton) {
				if (name.equals(((Radiobutton)comp).getName())) return comp;
			}
			UIComponent r = findComponentByName(comp, name);
			if (r != null) return r;
		}
		return null;
	}

	private List<UIComponent> findComponentsByName(UIComponent c, String name) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		Iterator<UIComponent> children = c.getFacetsAndChildren();
		while(children.hasNext()) {
			UIComponent comp = children.next();
			if (comp instanceof Radiobutton) {
				if (name.equals(((Radiobutton)comp).getName())) {
					result.add(comp);
				}
			}
			List<UIComponent> r = findComponentsByName(comp, name);
			if (!r.isEmpty()) {
				result.addAll(r);
			}
		}
		return result;
	}
	
	@Override
	public void decode(FacesContext context, UIComponent component) {
		Radiobutton radioButton = (Radiobutton) component;

		if (radioButton.isDisabled() || radioButton.isReadonly()) {
			return;
		}

		decodeBehaviors(context, radioButton);

		String clientId = radioButton.getClientId(context);
		String name = radioButton.getName();
		if (null == name) {
			name = "input_" + clientId;
		}

		UIForm form = findSurroundingForm(component);
		if (null == form) {
			throw new FacesException("Radiobuttons must be enclosed in a form. Client-ID of the radio button: " + clientId);
		}
		
		// AJAX fires decode to all radio buttons. Change value only for the first element
		List<UIComponent> radioButtonGroup = findComponentsByName(form, radioButton.getName());
		if (radioButtonGroup.get(0) == component) {
			List<String> legalValues = collectLegalValues(context, radioButtonGroup);
			super.decode(context, component, legalValues, "input_" + clientId);
		}
	}

	private UIForm findSurroundingForm(UIComponent component) {
		UIForm form = null;
		UIComponent c = component;
		while(c != null) {
			if (c instanceof UIForm) {
				form = (UIForm)c;
				break;
			}
			c = c.getParent();
		}
		return form;
	}

	private List<String> collectLegalValues(FacesContext context, List<UIComponent> radioButtonGroup) {
		List<String> legalValues = new ArrayList<String>();
		for (UIComponent b: radioButtonGroup) {
			Radiobutton r = (Radiobutton)b;
			Converter converter = r.getConverter();
			List<SelectItemAndComponent> options = SelectItemUtils.collectOptions(context, r, converter);
			if (options.size()>0) {
				// traditional JSF approach using f:selectItem[s]
				for (SelectItemAndComponent option:options) {
					String o = null;
					if (null != option.getSelectItem().getValue()) {
						o = String.valueOf(option.getSelectItem().getValue());
					}
					legalValues.add(o);
				}

			} else {
				// BootsFaces approach using b:radioButtons for each radiobutton item
				legalValues.add(r.getItemValue());
			}
		}
		return legalValues;
	}
	
	/**
	 * This methods generates the HTML code of the current b:radiobutton.
	* <code>encodeBegin</code> generates the start of the component. After the, the JSF framework calls <code>encodeChildren()</code>
	* to generate the HTML code between the beginning and the end of the component. For instance, in the case of a panel component
	* the content of the panel is generated by <code>encodeChildren()</code>. After that, <code>encodeEnd()</code> is called
	* to generate the rest of the HTML code.
	 * @param context the FacesContext.
	 * @param component the current b:radiobutton.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		Radiobutton radiobutton = (Radiobutton) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = radiobutton.getClientId();

		ValueExpression valueExpression = radiobutton.getValueExpression("value");
		if (null == valueExpression) {
			throw new FacesException("Radiobuttons always need a value. More precisely, the value attribute must be an EL expression pointing to an attribute of a JSF bean.");
		}
		String propertyName = valueExpression.getExpressionString();
		Object beanValue = ELTools.evalAsObject(propertyName);
		if (propertyName.startsWith("#{") && propertyName.endsWith("}")) {
			propertyName=propertyName.substring(2, propertyName.length()-1).trim();
		} else {
			throw new FacesException("The value attribute of a radiobutton must be an EL expression.");
		}
		UIForm form = findSurroundingForm(component);
		if (null == form) {
			throw new FacesException("Radio buttons must be inside a form.");
		}
		
		String name = radiobutton.getName();
		if (null == name) {
			throw new FacesException("Please specify the 'name' attribute of the radio button.");
		}
		UIComponent radioButtonGroup = findComponentByName(form, name);
		String radiobuttonGroupId = radioButtonGroup.getClientId(context);

		RadioButtonInternalStateBean state = (RadioButtonInternalStateBean) ELTools.evalAsObject("#{radioButtonInternalStateBean}");
		
		String key = "BF_generated_radiobuttonfield_"+radiobuttonGroupId;
		if (!state.inputHasAlreadyBeenRendered(key)) {
			rw.startElement("input", null);
			rw.writeAttribute("id", "input_" + clientId, null);
			rw.writeAttribute("name", name, null);
			rw.writeAttribute("type",  "hidden", null);
			rw.writeAttribute("value", String.valueOf(beanValue), null);
			AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), radiobutton, rw, false);

			rw.endElement("input");

		}

		Converter converter = radiobutton.getConverter();
		List<SelectItemAndComponent> options = SelectItemUtils.collectOptions(context, component, converter);
		if (options.size()>0) {
			// traditional JSF approach using f:selectItem[s]
			int counter=0;
			for (SelectItemAndComponent option:options) {
				generateASingleRadioButton(context, component, radiobutton, rw, propertyName, beanValue,
						option.getSelectItem().getValue(),
						option.getSelectItem().getLabel(), clientId+(counter++), option.getSelectItem().isDisabled());

			}

		} else {
			// BootsFaces approach using b:radioButtons for each radiobutton item
			String itemValue = radiobutton.getItemValue();
			String itemLabel = radiobutton.getItemLabel();
			if (itemLabel == null) {
				itemLabel = radiobutton.getItemValue();
			}
			String itemId = clientId;

			generateASingleRadioButton(context, component, radiobutton, rw, propertyName, beanValue, itemValue,
					itemLabel, itemId, false);
		}
	}

	private void generateASingleRadioButton(FacesContext context, UIComponent component, Radiobutton radiobutton,
			ResponseWriter rw, String propertyName, Object beanValue, Object itemValue, String itemLabel, String itemId, boolean disabled)
			throws IOException {
		rw.startElement("div", radiobutton);
		rw.writeAttribute("id", itemId, null);

		String styleClass=Responsive.getResponsiveStyleClass(radiobutton, false);
		String styleClass2 = radiobutton.getStyleClass();
		if (styleClass2!=null) {
			styleClass = styleClass2 + styleClass;
		}
		styleClass=styleClass.trim() + " radio";
		
		UIForm form = findSurroundingForm(component);
		if (null == form) {
			throw new FacesException("Radiobuttons must be enclosed in a form. Client-ID of the radio button: " + radiobutton.getClientId());
		}
		Radiobutton firstRadioButton = (Radiobutton)findComponentByName(form, radiobutton.getName());
		String errorClass = getErrorAndRequiredClass(firstRadioButton, firstRadioButton.getClientId(context));
		styleClass += " " + errorClass;
		
		rw.writeAttribute("class", styleClass, null);
		writeAttribute(rw, "style", radiobutton.getStyle());
		Tooltip.generateTooltip(context, radiobutton, rw);
		rw.startElement("label", component);
		if (radiobutton.isDisabled()) {
			rw.writeAttribute("class", "disabled", null);
		} 
		rw.startElement("input", component);
		if (!radiobutton.isDisabled()) {
			boolean ajax = radiobutton.isAjax();
			ajax |= null != ((IAJAXComponent) component).getUpdate();
			String trigger = ajax ? ".trigger('click')" : "";
			// Add onclick to input element to avoid event bubbling, if event is added on a label
			rw.writeAttribute("onclick", "$('#input_" + firstRadioButton.getClientId(context).replace(":", "\\\\:") + "').val('" + itemValue + "')" + trigger, null);
		}
		rw.writeAttribute("type", "radio", null);
		rw.writeAttribute("name", propertyName.replace('.','_'), null);
		if (beanValue!=null) {
			if (beanValue.toString().equals(itemValue)) {
				rw.writeAttribute("checked", "checked", null);
			}
		} else if (itemValue==null){
			rw.writeAttribute("checked", "checked", null);
		}
		if (radiobutton.isDisabled() || disabled) {
			rw.writeAttribute("disabled", "true", null);
		}
		rw.endElement("input");
		if (itemLabel!=null) {
			rw.writeText(itemLabel, null);
		}
		encodeChildren(context, component);
		rw.endElement("label");
		rw.endElement("div");
		Tooltip.activateTooltips(context, component, itemId);
	}

	/**
	 * This methods generates the HTML code of the current b:radiobutton.
	* <code>encodeBegin</code> generates the start of the component. After the, the JSF framework calls <code>encodeChildren()</code>
	* to generate the HTML code between the beginning and the end of the component. For instance, in the case of a panel component
	* the content of the panel is generated by <code>encodeChildren()</code>. After that, <code>encodeEnd()</code> is called
	* to generate the rest of the HTML code.
	 * @param context the FacesContext.
	 * @param component the current b:radiobutton.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		// already rendered in encodeBegin
	}

}
