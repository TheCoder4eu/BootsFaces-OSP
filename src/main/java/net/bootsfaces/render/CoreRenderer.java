/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu).
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
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.FacesException;
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
import javax.faces.convert.ConverterException;
import javax.faces.render.Renderer;
import net.bootsfaces.beans.ELTools;
import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.component.form.Form;
import net.bootsfaces.utils.BsfUtils;
import net.bootsfaces.utils.FacesMessages;

public class CoreRenderer extends Renderer {

	/**
	 * Method that provide ability to render pass through attributes.
	 * 
	 * @param context
	 * @param component
	 * @param attrs
	 * @throws IOException
	 */
	protected void renderPassThruAttributes(FacesContext context, UIComponent component, String[] attrs,
			boolean shouldRenderDataAttributes) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		if ((attrs == null || attrs.length <= 0) && shouldRenderDataAttributes == false)
			return;

		// pre-defined attributes
		for (String attribute : component.getAttributes().keySet()) {
			boolean attributeToRender = false;
			if (shouldRenderDataAttributes && attribute.startsWith("data-")) {
				attributeToRender = true;
			}
			if (!attributeToRender && attrs != null) {
				for (String ca : attrs) {
					if (attribute.equals(ca)) {
						attributeToRender = true;
						break;
					}
				}
			}
			if (attributeToRender) {
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
			for (String attribute : attrs) {
				Object value = component.getAttributes().get(attribute);

				if (shouldRenderAttribute(value))
					writer.writeAttribute(attribute, value.toString(), attribute);
			}
		}
	}

	/**
	 * @deprecated Use
	 *             {@link CoreRenderer#generateErrorAndRequiredClass(javax.faces.component.UIInput, javax.faces.context.ResponseWriter, java.lang.String, java.lang.String, java.lang.String, java.lang.String) }
	 *             instead
	 * 
	 *             Renders the CSS pseudo classes for required fields and for the
	 *             error levels.
	 *
	 * @param input
	 * @param rw
	 * @param clientId
	 * @throws IOException
	 */
	@Deprecated
	public void generateErrorAndRequiredClassForLabels(UIInput input, ResponseWriter rw, String clientId,
			String additionalClass) throws IOException {
		String styleClass = getErrorAndRequiredClass(input, clientId);
		if (null != additionalClass) {
			additionalClass = additionalClass.trim();
			if (additionalClass.trim().length() > 0) {
				styleClass += " " + additionalClass;
			}
		}
		UIForm currentForm = AJAXRenderer.getSurroundingForm((UIComponent) input, true);
		if (currentForm instanceof Form) {
			if (((Form) currentForm).isHorizontal()) {
				styleClass += " control-label";
			}
		}
		if (input instanceof IResponsiveLabel) {
			String responsiveLabelClass = Responsive.getResponsiveLabelClass((IResponsiveLabel) input);
			if (null != responsiveLabelClass) {
				styleClass += " " + responsiveLabelClass;
			}
		}

		rw.writeAttribute("class", styleClass, "class");
	}

	/**
	 * Renders the CSS pseudo classes for required fields and for the error levels.
	 *
	 * @param input
	 * @param rw
	 * @param clientId
	 * @throws IOException
	 */
	protected void generateErrorAndRequiredClass(UIInput input, ResponseWriter rw, String clientId,
			String additionalClass1, String additionalClass2, String additionalClass3) throws IOException {
		String styleClass = getErrorAndRequiredClass(input, clientId);
		if (null != additionalClass1) {
			additionalClass1 = additionalClass1.trim();
			if (additionalClass1.trim().length() > 0) {
				styleClass += " " + additionalClass1;
			}
		}
		if (null != additionalClass2) {
			additionalClass2 = additionalClass2.trim();
			if (additionalClass2.trim().length() > 0) {
				styleClass += " " + additionalClass2;
			}
		}
		if (null != additionalClass3) {
			additionalClass3 = additionalClass3.trim();
			if (additionalClass3.trim().length() > 0) {
				styleClass += " " + additionalClass3;
			}
		}
		rw.writeAttribute("class", styleClass, "class");
	}

	/**
	 * Yields the value of the required and error level CSS class.
	 *
	 * @param input
	 *            must not be null
	 * @param clientId
	 *            must not be null
	 * @return can never be null
	 */
	public String getErrorAndRequiredClass(UIInput input, String clientId) {
		String styleClass = "";
		if (BsfUtils.isLegacyFeedbackClassesEnabled()) {
			styleClass = FacesMessages.getErrorSeverityClass(clientId);
		}
		if (input.isRequired()) {
			styleClass += " bf-required";
		} else {
			Annotation[] readAnnotations = ELTools.readAnnotations(input);
			if (null != readAnnotations && readAnnotations.length > 0) {
				for (Annotation a : readAnnotations) {
					if ((a.annotationType().getName().endsWith("NotNull"))
							|| (a.annotationType().getName().endsWith("NotEmpty"))
							|| (a.annotationType().getName().endsWith("NotBlank"))) {
						styleClass += " bf-required";
						break;
					}
				}
			}
		}
		return styleClass;
	}

	protected boolean shouldRenderAttribute(Object value) {
		if (value == null)
			return false;

		if (value instanceof Boolean) {
			return ((Boolean) value);
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
				String clientId = component.getClientId(context);
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
	 *             if this method is called when there is no currently open element
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
	 *             if this method is called when there is no currently open element
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
	 *            The {@link UIComponent} (if any) to which this element corresponds
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

	protected static void assertComponentIsInsideForm(UIComponent component, String msg) {
		assertComponentIsInsideForm(component, msg, false);

	}

	public static void assertComponentIsInsideForm(UIComponent component, String msg, boolean throwException) {
		if (!FacesContext.getCurrentInstance().isProjectStage(ProjectStage.Production)) {
			UIComponent c = component;
			while ((c != null) && (!(c instanceof UIForm))) {
				c = c.getParent();
			}
			if (!(c instanceof UIForm)) {
				System.out.println("Warning: The BootsFaces component " + component.getClass()
						+ " works better if put inside a form. These capabilities get lost if not put in a form:");
				if (throwException) {
					throw new FacesException(msg);
				} else {
					System.out.println(msg);
				}
			}
		}
	}

	protected static UIForm getSurroundingForm(UIComponent component, boolean lenient) {
		UIComponent c = component;
		while ((c != null) && (!(c instanceof UIForm)) && (!(c instanceof Form))) {
			c = c.getParent();
		}
		if (!(c instanceof UIForm || c instanceof Form)) {
			if (lenient) {
				return null;
			} else {
				throw new FacesException(
						"The component with the id " + component.getClientId() + " must be inside a form");
			}
		}
		return (UIForm) c;
	}

	public static boolean isHorizontalForm(UIComponent component) {
		UIForm c = getSurroundingForm(component, true);
		if (null != c && c instanceof Form) {
			return ((Form) c).isHorizontal();
		}
		return false;
	}

	/**
	 * Algorithm works as follows; - If it's an input component, submitted value is
	 * checked first since it'd be the value to be used in case validation errors
	 * terminates jsf lifecycle - Finally the value of the component is retrieved
	 * from backing bean and if there's a converter, converted value is returned
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
				/*
				 * OLD Converter converter = getConverter(fc, vh);
				 */

				/* NEW */
				Converter converter = vh.getConverter();
				if (converter == null) {
					Class<?> valueType = val.getClass();
					if (valueType == String.class && (null == fc.getApplication().createConverter(String.class))) {
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

	/**
	 * This method is called by the JSF framework to get the type-safe value of the
	 * attribute. Do not delete this method.
	 */
	@Override
	public Object getConvertedValue(FacesContext fc, UIComponent c, Object sval) throws ConverterException {
		Converter cnv = resolveConverter(fc, c, sval);

		if (cnv != null) {
			if (sval == null || sval instanceof String) {
				return cnv.getAsObject(fc, c, (String) sval);
			} else {
				return cnv.getAsObject(fc, c, String.valueOf(sval));
			}
		} else {
			return sval;
		}
	}

	protected Converter resolveConverter(FacesContext context, UIComponent c, Object value) {
		if (!(c instanceof ValueHolder)) {
			return null;
		}

		Converter cnv = ((ValueHolder) c).getConverter();

		if (cnv != null) {
			return cnv;
		} else {
			ValueExpression ve = c.getValueExpression("value");

			if (ve != null) {
				Class<?> valType = ve.getType(context.getELContext());
				

				if (valType != null && (!valType.isPrimitive())) { // workaround for a Mojarra bug (#966)
					return context.getApplication().createConverter(valType);
				} else if (valType != null && (value instanceof String)) { // workaround for the workaround of the Mojarra bug (#977)
					return context.getApplication().createConverter(valType);
				}
			}

			return null;
		}
	}
	
	/**
	 * Returns request parameter value for the provided parameter name.
	 *
	 * @param context Faces context.
	 * @param name    Parameter name to get value for.
	 *
	 * @return Request parameter value for the provided parameter name.
	 */
	public static String getRequestParameter(FacesContext context, String name) {
		return context.getExternalContext().getRequestParameterMap().get(name);
	}

	/**
	 * Returns type of the value attribute's {@link ValueExpression} of the provided component.
	 *
	 * @param component Component to get attribute type for.
	 *
	 * @return Type of the value attribute's {@link ValueExpression} of the provided component.
	 */
	public static Class<?> getValueType(UIComponent component) {
		return getAttributeType(component, "value");
	}

	/**
	 * Returns type of the provided attribute name's {@link ValueExpression} of the provided component.
	 *
	 * @param component Component to get attribute type for.
	 * @param attribute Attribute to get type for.
	 *
	 * @return Type of the provided attribute name's {@link ValueExpression} of the provided component.
	 */
	public static Class<?> getAttributeType(UIComponent component, String attribute) {
		ValueExpression valueExpression = component.getValueExpression(attribute);
		return valueExpression == null
			? null
			: valueExpression.getType(FacesContext.getCurrentInstance().getELContext());
	}
	
	public static void endDisabledFieldset(IContentDisabled component, ResponseWriter rw) throws IOException {
		if (component.isContentDisabled()) {
			rw.endElement("fieldset");
		}
	}

	/**
	 * Renders the code disabling every input field and every button within a
	 * container.
	 * 
	 * @param component
	 * @param rw
	 * @return true if an element has been rendered
	 * @throws IOException
	 */
	public static boolean beginDisabledFieldset(IContentDisabled component, ResponseWriter rw) throws IOException {
		if (component.isContentDisabled()) {
			rw.startElement("fieldset", (UIComponent) component);
			rw.writeAttribute("disabled", "disabled", "null");
			return true;
		}
		return false;
	}

	/**
	 * Get the main field container
	 * 
	 * @deprecated Use
	 *             {@link CoreInputRenderer#getWithFeedback(net.bootsfaces.render.CoreInputRenderer.InputMode, javax.faces.component.UIComponent)}
	 *             instead
	 * 
	 * @param additionalClass
	 * @param clientId
	 * @return
	 */
	@Deprecated
	protected String getFormGroupWithFeedback(String additionalClass, String clientId) {
		if (BsfUtils.isLegacyFeedbackClassesEnabled()) {
			return additionalClass;
		}
		return additionalClass + " " + FacesMessages.getErrorSeverityClass(clientId);
	}

	protected void beginResponsiveWrapper(UIComponent component, ResponseWriter responseWriter) throws IOException {
		if (!(component instanceof IResponsive)) {
			return;
		}

		String responsiveStyleClass = Responsive.getResponsiveStyleClass((IResponsive) component, false);
		if (!"".equals(responsiveStyleClass)) {
			responseWriter.startElement("div", component);
			responseWriter.writeAttribute("class", responsiveStyleClass, null);
		}
	}

	protected void endResponsiveWrapper(UIComponent component, ResponseWriter responseWriter) throws IOException {
		if (!(component instanceof IResponsive)) {
			return;
		}

		String responsiveStyleClass = Responsive.getResponsiveStyleClass((IResponsive) component, false);
		if (!"".equals(responsiveStyleClass)) {
			responseWriter.endElement("div");
		}
	}
}
