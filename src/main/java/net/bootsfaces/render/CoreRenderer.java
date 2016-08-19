/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu).
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
package net.bootsfaces.render;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.ProjectStage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.ValueHolder;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.render.Renderer;

public class CoreRenderer extends Renderer {

	/**
	 * Method that provide ability to render pass through attributes.
	 * @param context
	 * @param component
	 * @param attrs
	 * @throws IOException
	 */
	protected void renderPassThruAttributes(FacesContext context, UIComponent component, String[] attrs, boolean shouldRenderDataAttributes)
	throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		if((attrs == null || attrs.length <= 0) && shouldRenderDataAttributes == false) return;

		// pre-defined attributes
		for(String attribute: component.getAttributes().keySet()) {
			boolean attributeToRender = false;
			if(shouldRenderDataAttributes && attribute.startsWith("data-")) {
				attributeToRender = true;
			}
			if(!attributeToRender && attrs != null) {
				for(String ca: attrs) {
					if(attribute.equals(ca)) {
						attributeToRender = true;
						break;
					}
				}
			}
			if(attributeToRender) {
				Object value = component.getAttributes().get(attribute);

				if (shouldRenderAttribute(value))
					writer.writeAttribute(attribute, value.toString(), attribute);
			}
		}
	}
	protected void renderPassThruAttributes(FacesContext context, UIComponent component, String[] attrs)
	throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		// pre-defined attributes
		if (attrs != null && attrs.length > 0) {
			for (String attribute: attrs) {
				Object value = component.getAttributes().get(attribute);

				if (shouldRenderAttribute(value))
					writer.writeAttribute(attribute, value.toString(), attribute);
			}
		}
	}

	/**
	 * Renders the CSS pseudo classes for required fields and for the error
	 * levels.
	 *
	 * @param input
	 * @param rw
	 * @param clientId
	 * @throws IOException
	 */
	protected void generateErrorAndRequiredClass(UIInput input, ResponseWriter rw, String clientId) throws IOException {
		getErrorAndRequiredClass(input, clientId);
		generateErrorAndRequiredClass(input, rw, clientId, null);
	}

	/**
	 * Renders the CSS pseudo classes for required fields and for the error
	 * levels.
	 *
	 * @param input
	 * @param rw
	 * @param clientId
	 * @throws IOException
	 */
	protected void generateErrorAndRequiredClass(UIInput input, ResponseWriter rw, String clientId, String additionalClass) throws IOException {
		String styleClass = getErrorAndRequiredClass(input, clientId);
		if (null != additionalClass) {
			additionalClass = additionalClass.trim();
			if (additionalClass.trim().length()>0) {
				styleClass += " " + additionalClass;
			}
		}
		rw.writeAttribute("class", styleClass, "class");
	}
	
	/**
	 * Renders the CSS pseudo classes for required fields and for the error
	 * levels.
	 *
	 * @param input
	 * @param rw
	 * @param clientId
	 * @throws IOException
	 */
	protected void generateErrorAndRequiredClass(UIInput input, ResponseWriter rw, String clientId, String additionalClass1, String additionalClass2, String additionalClass3) throws IOException {
		String styleClass = getErrorAndRequiredClass(input, clientId);
		if (null != additionalClass1) {
			additionalClass1 = additionalClass1.trim();
			if (additionalClass1.trim().length()>0) {
				styleClass += " " + additionalClass1;
			}
		}
		if (null != additionalClass2) {
			additionalClass2 = additionalClass2.trim();
			if (additionalClass2.trim().length()>0) {
				styleClass += " " + additionalClass2;
			}
		}
		if (null != additionalClass3) {
			additionalClass3 = additionalClass3.trim();
			if (additionalClass3.trim().length()>0) {
				styleClass += " " + additionalClass3;
			}
		}
		rw.writeAttribute("class", styleClass, "class");
	}


	/**
	 * Yields the value of the required and error level CSS class.
	 *
	 * @param input
	 * @param clientId
	 * @return
	 */
	public String getErrorAndRequiredClass(UIInput input, String clientId) {
		String[] levels = { "bf-no-message has-success", "bf-info", "bf-warning has-warning", "bf-error has-error", "bf-fatal has-error" };
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

	protected boolean shouldRenderAttribute(Object value) {
		if (value == null)
			return false;

		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue();
		} else if (value instanceof Number) {
			Number number = (Number) value;

			if (value instanceof Integer)
				return number.intValue() != Integer.MIN_VALUE;
			else if (value instanceof Double)
				return number.doubleValue() != Double.MIN_VALUE;
			else if (value instanceof Long)
				return number.longValue() != Long.MIN_VALUE;
			else if (value instanceof Byte)
				return number.byteValue() != Byte.MIN_VALUE;
			else if (value instanceof Float)
				return number.floatValue() != Float.MIN_VALUE;
			else if (value instanceof Short)
				return number.shortValue() != Short.MIN_VALUE;
		}

		return true;
	}

	protected void decodeBehaviors(FacesContext context, UIComponent component) {
		if (!(component instanceof ClientBehaviorHolder)) {
			return;
		}
		
		Map<String, List<ClientBehavior>> behaviors = ((ClientBehaviorHolder) component).getClientBehaviors();
		if (behaviors.isEmpty()) {
			return;
		}

		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String behaviorEvent = params.get("javax.faces.behavior.event");
		if (null != behaviorEvent) {
			List<ClientBehavior> behaviorsForEvent = behaviors.get(behaviorEvent);

			if (behaviorsForEvent != null && !behaviorsForEvent.isEmpty()) {
				String behaviorSource = params.get("javax.faces.source");
				String clientId = component.getClientId();
				if (behaviorSource != null && clientId.equals(behaviorSource)) {
					for (ClientBehavior behavior : behaviorsForEvent) {
						behavior.decode(context, component);
					}
				}
			}
		}
	}

	public boolean componentIsDisabledOrReadonly(UIComponent component) {
		return Boolean.valueOf(String.valueOf(component.getAttributes().get("disabled")))
				|| Boolean.valueOf(String.valueOf(component.getAttributes().get("readonly")));
	}

	protected String escapeClientId(String clientId) {
		if (clientId == null) {
			return null;
		}
		// replace colons by underscores to avoid problems with jQuery
		return clientId.replace(':', '_');
	}

	/**
	 * @param rw
	 *            ResponseWriter to be used
	 * @param name
	 *            Attribute name to be added
	 * @param value
	 *            Attribute value to be added
	 * @param property
	 *            Name of the property or attribute (if any) of the
	 *            {@link UIComponent} associated with the containing element, to
	 *            which this generated attribute corresponds
	 * @throws IllegalStateException
	 *             if this method is called when there is no currently open
	 *             element
	 * @throws IOException
	 *             if an input/output error occurs
	 * @throws NullPointerException
	 *             if <code>name</code> is <code>null</code>
	 */
	protected void writeAttribute(ResponseWriter rw, String name, Object value, String property) throws IOException {
		if (value == null) {
			return;
		}
		if (value instanceof String)
			if (((String) value).length() == 0)
				return;
		rw.writeAttribute(name, value, property);
	}

	/**
	 * @param rw
	 *            ResponseWriter to be used
	 * @param name
	 *            Attribute name to be added
	 * @param value
	 *            Attribute value to be added
	 * @throws IllegalStateException
	 *             if this method is called when there is no currently open
	 *             element
	 * @throws IOException
	 *             if an input/output error occurs
	 * @throws NullPointerException
	 *             if <code>name</code> is <code>null</code>
	 */
	protected void writeAttribute(ResponseWriter rw, String name, Object value) throws IOException {
		if (value == null) {
			return;
		}
		if (value instanceof String)
			if (((String) value).length() == 0)
				return;
		rw.writeAttribute(name, value, name);
	}

	/**
	 * @param rw
	 *            ResponseWriter to be used
	 * @param text
	 *            Text to be written
	 * @param property
	 *            Name of the property or attribute (if any) of the
	 *            {@link UIComponent} associated with the containing element, to
	 *            which this generated text corresponds
	 * @throws IOException
	 *             if an input/output error occurs
	 * @throws NullPointerException
	 *             if <code>text</code> is <code>null</code>
	 */
	public void writeText(ResponseWriter rw, Object text, String property) throws IOException {
		if (text == null || text.equals("")) {
			return;
		}
		rw.writeText(text, property);
	}

	/**
	 * @param rw
	 *            ResponseWriter to be used
	 * @param text
	 *            Text to be written
	 * @param component
	 *            The {@link UIComponent} (if any) to which this element
	 *            corresponds
	 * @param property
	 *            Name of the property or attribute (if any) of the
	 *            {@link UIComponent} associated with the containing element, to
	 *            which this generated text corresponds
	 * @throws IOException
	 *             if an input/output error occurs
	 * @throws NullPointerException
	 *             if <code>text</code> is <code>null</code>
	 */
	public void writeText(ResponseWriter rw, Object text, UIComponent component, String property) throws IOException {
		if (text == null || text.equals("")) {
			return;
		}
		rw.writeText(text, property);
	}

	/**
	 * @param rw
	 *            ResponseWriter to be used
	 * @param text
	 *            Text to be written
	 * @param off
	 *            Starting offset (zero-relative)
	 * @param len
	 *            Number of characters to be written
	 * @throws IndexOutOfBoundsException
	 *             if the calculated starting or ending position is outside the
	 *             bounds of the character array
	 * @throws IOException
	 *             if an input/output error occurs
	 * @throws NullPointerException
	 *             if <code>text</code> is <code>null</code>
	 */
	public void writeText(ResponseWriter rw, char text[], int off, int len) throws IOException {
		if (text == null || text.length <= 0 || "".equals(String.valueOf(text))) {
			return;
		}
		rw.writeText(text, off, len);
	}

	protected void assertComponentIsInsideForm(UIComponent component, String msg) {
		if (!FacesContext.getCurrentInstance().isProjectStage(ProjectStage.Production)) {
			UIComponent c = component;
			while ((c != null) && (!(c instanceof UIForm))) {
				c = c.getParent();
			}
			if (!(c instanceof UIForm)) {
				System.out.println("Warning: The BootsFaces component " + component.getClass()
						+ " works better if put inside a form. These capabilities get lost if not put in a form:");
				System.out.println(msg);
			}
		}

	}

	/**
	 * Algorithm works as follows; - If it's an input component, submitted value
	 * is checked first since it'd be the value to be used in case validation
	 * errors terminates jsf lifecycle - Finally the value of the component is
	 * retrieved from backing bean and if there's a converter, converted value
	 * is returned
	 *
	 * @param fc
	 *            FacesContext instance
	 * @param c
	 *            UIComponent instance whose value will be returned
	 * @return End text
	 */
	public String getValue2Render(FacesContext fc, UIComponent c) {
		if (c instanceof ValueHolder) {

			if (c instanceof EditableValueHolder) {
				Object sv = ((EditableValueHolder) c).getSubmittedValue();
				if (sv != null) {
					return sv.toString();
				}
			}

			ValueHolder vh = (ValueHolder) c;
			Object val = vh.getValue();

			// format the value as string
			if (val != null) {
				/* OLD
				Converter converter = getConverter(fc, vh);
				*/

				/* NEW */
                Converter converter = vh.getConverter();
                if (converter == null) {
                    Class<?> valueType = val.getClass();
                    if(valueType == String.class) {
                        return (String) val;
                    }

                    converter = fc.getApplication().createConverter(valueType);
                }
                /* END NEW */

                if (converter != null)
					return converter.getAsString(fc, c, val);
				else
					return val.toString(); // Use toString as a fallback if
											// there is no explicit or implicit
											// converter
			} else {
				// component is a value holder but has no value
				return null;
			}
		}

		// component it not a value holder
		return null;
	}

	/**
	 * Finds the appropriate converter for a given value holder
	 *
	 * @param fc
	 *            FacesContext instance
	 * @param vh
	 *            ValueHolder instance to look converter for
	 * @return Converter
	 */
	public static Converter getConverter(FacesContext fc, ValueHolder vh) {
		// explicit converter
		Converter converter = vh.getConverter();

		// try to find implicit converter
		if (converter == null) {
			ValueExpression expr = ((UIComponent) vh).getValueExpression("value");
			if (expr != null) {
				Class<?> valueType = expr.getType(fc.getELContext());
				if (valueType != null) {
					converter = fc.getApplication().createConverter(valueType);
				}
			}
		}

		return converter;
	}

}
