/**
 *  (C) 2013-2016 Stephan Rauh http://www.beyondjava.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bootsfaces.beans;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.PropertyNotFoundException;
import javax.el.ValueExpression;
import javax.el.ValueReference;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

/** Collection of helper methods dealing with the JSF Expression language. */
public class ELTools {
	private final static Pattern EL_EXPRESSION = Pattern.compile("#\\{\\{([A-Z_$€]|[a-z_0-9$€]|\\.)+\\}");

	private static Map<String, NGBeanAttributeInfo> beanAttributeInfos = new HashMap<String, NGBeanAttributeInfo>();

	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.beans.ELTools");

	/**
	 * Utility method to create a JSF Value expression from the p_expression string
	 * 
	 * @param p_expression
	 * @return
	 */
	public static ValueExpression createValueExpression(String p_expression) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, Object.class);
		return vex;
	}

	/**
	 * Utility method to create a JSF Value expression from p_expression with
	 * exprectedType class as return
	 * 
	 * @param p_expression
	 * @param expectedType
	 * @return
	 */
	public static ValueExpression createValueExpression(String p_expression, Class<?> expectedType) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		if (null == expectedType) {
			LOGGER.severe("The expected type of " + p_expression + " is null. Defaulting to String.");
			expectedType = String.class;
		}
		ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, expectedType);
		return vex;
	}

	/**
	 * Utility method to create a JSF Method expression
	 * 
	 * @param p_expression
	 * @param returnType
	 * @param parameterTypes
	 * @return
	 */
	public static MethodExpression createMethodExpression(String p_expression, Class<?> returnType,
			Class<?>... parameterTypes) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();

		MethodExpression mex = expressionFactory.createMethodExpression(elContext, p_expression, returnType,
				parameterTypes);
		return mex;
	}

	/**
	 * Evaluates an EL expression into an object.
	 *
	 * @param p_expression
	 *            the expression
	 * @throws PropertyNotFoundException
	 *             if the attribute doesn't exist at all (as opposed to being null)
	 * @return the object
	 */
	public static Object evalAsObject(String p_expression) throws PropertyNotFoundException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, Object.class);

		Object result = vex.getValue(elContext);
		return result;
	}

	/**
	 * Evaluates an EL expression into a string.
	 *
	 * @param p_expression
	 *            the el expression
	 * @return the value
	 */
	public static String evalAsString(String p_expression) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, String.class);
		String result = (String) vex.getValue(elContext);
		return result;
	}

	/**
	 * Get the bean attributes info
	 * 
	 * @param c
	 * @return
	 */
	public static NGBeanAttributeInfo getBeanAttributeInfos(UIComponent c) {
		String core = getCoreValueExpression(c);
		synchronized (beanAttributeInfos) {
			if (beanAttributeInfos.containsKey(c)) {
				return beanAttributeInfos.get(c);
			}
		}
		NGBeanAttributeInfo info = new NGBeanAttributeInfo(c);
		synchronized (beanAttributeInfos) {
			beanAttributeInfos.put(core, info);
		}
		return info;
	}

	/**
	 * Return the core value expression of a specified component
	 * 
	 * @param component
	 * @return
	 */
	public static String getCoreValueExpression(UIComponent component) {
		ValueExpression valueExpression = component.getValueExpression("value");
		if (null != valueExpression) {
			String v = valueExpression.getExpressionString();
			if (null != v) {
				Matcher matcher = EL_EXPRESSION.matcher(v);
				if (matcher.find()) {
					String exp = matcher.group();
					return exp.substring(2, exp.length() - 1);
				}
				return v;
			}
		}
		return null;
	}

	private static Field getField(Object container, String p_expression) {
		if (container == null) {
			return null;
		}
		if (p_expression.startsWith("#{") && p_expression.endsWith("}")) {
			// the following code covers these use cases:
			// #{someBean.someField}
			// #{someBean.someMap['someField']}
			// #{someBean.someMap[someBean.fieldName]}
			// #{someBean.someMap[someBean.fieldName].someAttribute}
			int delimiterPos = p_expression.lastIndexOf('.');
			int mapDelimiterPos = p_expression.lastIndexOf('[');
			if (mapDelimiterPos >= 0) {
				int mapEndDelimiterPos = p_expression.lastIndexOf(']');
				if (delimiterPos < mapEndDelimiterPos) {
					delimiterPos = mapDelimiterPos; // treat the [...] as field
				}
			}
			if (delimiterPos < 0) {
				LOGGER.log(Level.WARNING, "There's no field to access: #{" + p_expression + "}");
				return null;
			}
			String fieldName = p_expression.substring(delimiterPos + 1, p_expression.length() - 1);
			Class<? extends Object> c = container.getClass();
			while (c != null) {
				Field declaredField;
				try {
					declaredField = c.getDeclaredField(fieldName);
					// synchronized (fields) {
					// fields.put(p_expression, declaredField);
					// }
					return declaredField;
				} catch (NoSuchFieldException e) {
					// let\"s try with the super class
					c = c.getSuperclass();
				} catch (SecurityException e) {
					LOGGER.log(Level.SEVERE, "Unable to access a field", e);
					return null;
				}
			}
		}
		return null;
	}

	private static Method getGetter(Object container, String p_expression) {
		if (container == null) {
			return null;
		}
		if (p_expression.startsWith("#{") && p_expression.endsWith("}")) {
			// the following code covers these use cases:
			// #{someBean.someField}
			// #{someBean.someMap['someField']}
			// #{someBean.someMap[someBean.fieldName]}
			// #{someBean.someMap[someBean.fieldName].someAttribute}
			int delimiterPos = p_expression.lastIndexOf('.');
			int mapDelimiterPos = p_expression.lastIndexOf('[');
			if (mapDelimiterPos >= 0) {
				int mapEndDelimiterPos = p_expression.lastIndexOf(']');
				if (delimiterPos < mapEndDelimiterPos) {
					// the last part of the expression is a map access, so there's no getter
					return null;
				}
			}
			if (delimiterPos < 0) {
				LOGGER.log(Level.WARNING, "There's no getter to access: #{" + p_expression + "}");
				return null;
			}
			String fieldName = p_expression.substring(delimiterPos + 1, p_expression.length() - 1);
			String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method declaredMethod = findMethod(container, getterName);
			String booleanGetterName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			if (null == declaredMethod)
				declaredMethod = findMethod(container, booleanGetterName);
			return declaredMethod;

		}
		return null;
	}

	private static Method findMethod(Object container, String getterName) {
		Class<? extends Object> c = container.getClass();
		Method declaredField;
		try {
			declaredField = c.getMethod(getterName);
			return declaredField;
		} catch (NoSuchMethodException e) {
			// let\"s try with the super class
			c = c.getSuperclass();
		} catch (SecurityException e) {
			LOGGER.log(Level.SEVERE, "Unable to access a getter for security reasons", e);
		}
		return null;

	}

	/**
	 * Yields the type of the variable given by an expression.
	 *
	 * @param p_expression
	 *            the expression
	 * @return the type (as class)
	 */
	public static Class<?> getType(Object base, String p_expression) {
		Method declaredField = getGetter(base, p_expression);
		if (null != declaredField) {
			return declaredField.getReturnType();
		}
		return null;
	}

	/**
	 * Yields the type of the variable displayed by a component.
	 *
	 * @param p_component
	 *            the UIComponent
	 * @return the type (as class)
	 */
	public static Class<?> getType(UIComponent p_component) {
		ValueExpression valueExpression = p_component.getValueExpression("value");
		if (valueExpression != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ELContext elContext = context.getELContext();
			return valueExpression.getType(elContext);
		}
		return null;
	}

	public static boolean hasValueExpression(UIComponent component) {
		ValueExpression valueExpression = component.getValueExpression("value");
		return null != valueExpression;
	}

	/**
	 * Is the parameter passed a primitive type (such as int, long, etc) or a type
	 * considered primitive by most programmers (such as String)?
	 *
	 * @param c
	 *            the object
	 * @return true if c is a de-facto-primitive
	 */
	@SuppressWarnings("unused")
	private static boolean isPrimitive(Class<? extends Object> c) {
		return (null == c) || (Class.class == c) || (String.class == c) || c.isPrimitive() || (Integer.class == c)
				|| (Long.class == c) || (Short.class == c) || (Byte.class == c) || (Character.class == c)
				|| (Float.class == c) || (Double.class == c) || (Void.class == c) || (Boolean.class == c);
	}

	/**
	 * Which annotations are given to an object described by an EL expression?
	 *
	 * @param p_expression
	 *            EL expression of the JSF bean attribute
	 * @return null if there are no annotations, or if they cannot be accessed
	 */
	public static Annotation[] readAnnotations(ValueExpression p_expression, UIComponent p_component) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		try {
			ValueReference valueReference = p_expression.getValueReference(elContext);
			Object base;
			if (null == valueReference) {
				base = evaluteBaseForMojarra(elContext, p_expression);
			} else {
				base = valueReference.getBase();
			}
			Field declaredField = getField(base, p_expression.getExpressionString());
			if (null != declaredField) {
				return declaredField.getAnnotations();
			}
			Method getter = getGetter(base, p_expression.getExpressionString());
			if (null != getter) {
				return getter.getAnnotations();
			}
		} catch (PropertyNotFoundException ex) {
			// this happens if a bean is null. That's a legal state, so suffice it to return no annotation.
		}
		return null;
	}

	private static Object evaluteBaseForMojarra(ELContext elContext, ValueExpression p_expression) {
		String exp = p_expression.getExpressionString();
		int endOfBaseName = exp.lastIndexOf('.');
		int mapDelimiterPos = exp.lastIndexOf('[');
		if (mapDelimiterPos >= 0) {
			int mapEndDelimiterPos = exp.lastIndexOf(']');
			if (endOfBaseName < mapEndDelimiterPos) {
				endOfBaseName = mapDelimiterPos; // treat the [...] as field
			}
		}
		if (endOfBaseName == -1) {
			endOfBaseName = exp.lastIndexOf('}'); 
		}

		String basename = exp.substring(2, endOfBaseName);

		Object result = evalAsObject("#{" + basename + "}");
		if (null != result) {
			return result;
		}

		int start = 0;
		int end = basename.indexOf('.', start);
		int end2 = basename.indexOf('[', start);
		if (end2 >= 0 && end2 < end) {
			end = end2;
		}
		if (end < 0) {
			end = basename.length();
		}
		String variableName = basename.substring(start, end);
		FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes()
				.get(FaceletContext.FACELET_CONTEXT_KEY);
		Object resolvedBase = faceletContext.getAttribute(variableName);
		if (resolvedBase != null) {
			if (endOfBaseName == end + 2) {
				result = resolvedBase;
			} else {
				basename = basename.substring(end + 1, endOfBaseName - 2);
				result = elContext.getELResolver().getValue(elContext, resolvedBase, basename);
			}
		}

		return result;
	}

	/**
	 * Which annotations are given to an object displayed by a JSF component?
	 *
	 * @param p_component
	 *            the component
	 * @return null if there are no annotations, or if they cannot be accessed
	 */
	public static Annotation[] readAnnotations(UIComponent p_component) {
		ValueExpression valueExpression = p_component.getValueExpression("value");
		if (valueExpression != null && valueExpression.getExpressionString() != null && valueExpression.getExpressionString().length()>0) {
			return readAnnotations(valueExpression, p_component);
		}
		return null;
	}
}
