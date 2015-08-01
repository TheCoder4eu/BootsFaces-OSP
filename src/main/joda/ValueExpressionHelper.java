package com.anem.green.web.convert;

import java.util.Collection;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class ValueExpressionHelper {

	/**
	 * Source adapted from Seam's enumConverter. The goal is to get the type to which this component's value is bound.
	 * First, check if the valueExpression provides the type. For dropdown-like components, this may not work, so check
	 * for SelectItems children.
	 * 
	 * @param context the current FacesContext
	 * @param uiComponent
	 * @param validTypes a list of types to look for
	 * @return null if a valid type cannot be found
	 */
	public static Class<?> getValueType(FacesContext context, UIComponent uiComponent, Collection<Class<?>> validTypes) {
		Class<?> valueType = getValueType(context, uiComponent);
		if (valueType != null && isValid(validTypes, valueType)) {
			return valueType;
		}
		else {
			for (UIComponent child : uiComponent.getChildren()) {
				UIComponent c = (UIComponent) child;
				ValueExpression expr = c.getValueExpression("value");
				Object val = expr == null ? null : expr.getValue(context.getELContext());
				if (val != null) {

					valueType = val.getClass();
					if (valueType.isArray() && isValid(validTypes, valueType.getComponentType())) {
						return valueType;
					}
					else if (val instanceof Collection<?>) {
						valueType = ((Collection<?>) val).iterator().next().getClass();
						if (isValid(validTypes, valueType)) {
							return valueType;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * return the type for the "value" attribute of the given component, if it exists. Return null otherwise.
	 * @param context
	 * @param comp
	 * @return
	 */
	public static Class<?> getValueType(FacesContext context, UIComponent comp) {
		ValueExpression expr = comp.getValueExpression("value");
		Class<?> valueType = expr == null ? null : expr.getType(context.getELContext());
		return valueType;
	}

	private static boolean isValid(Collection<Class<?>> validTypes, Class<?> valueType) {
		for (Class<?> validType : validTypes) {
			if (validType.isAssignableFrom(valueType)) {
				return true;
			}
		}
		return false;
	}

}