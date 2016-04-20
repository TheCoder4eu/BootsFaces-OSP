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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/** Collection of helper methods dealing with the JSF Expression language. */
public class ELTools {
	private final static Pattern EL_EXPRESSION = Pattern.compile("#\\{\\{([A-Z_$€]|[a-z_0-9$€]|\\.)+\\}");

	private static Map<String, NGBeanAttributeInfo> beanAttributeInfos = new HashMap<String, NGBeanAttributeInfo>();

	/** Caching */
	// private static Map<String, Field> fields = new HashMap<String, Field>();

	/** Caching */
	// private static Map<String, Method> getters = new HashMap<String, Method>();

	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.beans.ELTools");

	/** Caching */
	// private static Map<String, List<String>> propertyLists = new HashMap<String, List<String>>();

	/**
	 * Utility method to create a JSF Value expression from the p_expression string
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
	 * Utility method to create a JSF Value expression from p_expression with exprectedType class as return
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
	 *             if the attribute doesn't exist at all (as opposed to being
	 *             null)
	 * @return the object
	 */
	public static Object evalAsObject(String p_expression) throws PropertyNotFoundException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, Object.class);
		Object result = vex.getValue(elContext);
		if (null == result) {
			// check whether the JSF attributes exists
			vex.getValueReference(elContext);
		}
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

	private static Field getField(String p_expression) {
		if (p_expression.startsWith("#{") && p_expression.endsWith("}")) {
			int delimiterPos = p_expression.lastIndexOf('.');
			if (delimiterPos < 0) {
				LOGGER.log(Level.WARNING, "There's no field to access: #{" + p_expression + "}");
				return null;
			}
			String beanExp = p_expression.substring(0, delimiterPos) + "}";
			String fieldName = p_expression.substring(delimiterPos + 1, p_expression.length() - 1);
			Object container = evalAsObject(beanExp);
			if (null == container) {
				LOGGER.severe("Can't read the bean '" + beanExp
						+ "'. Thus JSR 303 annotations can't be read, let alone used by the AngularJS / AngularDart client.");
				return null;
			}

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
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	private static Method getGetter(String p_expression) {
		// synchronized (getters) {
		// if (getters.containsKey(p_expression)) {
		// return getters.get(p_expression);
		// }
		// }

		if (p_expression.startsWith("#{") && p_expression.endsWith("}")) {
			int delimiterPos = p_expression.lastIndexOf('.');
			if (delimiterPos < 0) {
				LOGGER.log(Level.WARNING, "There's no getter to access: #{" + p_expression + "}");
				return null;
			}
			String beanExp = p_expression.substring(0, delimiterPos) + "}";
			String fieldName = p_expression.substring(delimiterPos + 1, p_expression.length() - 1);
			String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			String booleanGetterName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Object container = evalAsObject(beanExp);
			if (null == container) {
				LOGGER.severe("Can't read the bean '" + beanExp
						+ "'. Thus JSR 303 annotations can't be read, let alone used by the AngularJS / AngularDart client.");
				return null;
			}
			Method declaredMethod = findMethod(container, getterName);
			if (null == declaredMethod)
				declaredMethod = findMethod(container, booleanGetterName);
			// synchronized (getters) {
			// getters.put(p_expression, declaredMethod);
			// }
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
			e.printStackTrace();
		}
		return null;

	}

	public static String getNGModel(UIComponent p_component) {
		String id = ELTools.getCoreValueExpression(p_component);
		if (id.contains(".")) {
			int index = id.lastIndexOf(".");
			id = id.substring(index + 1);
		}
		return id;
	}

	/**
	 * Yields the type of the variable given by an expression.
	 *
	 * @param p_expression
	 *            the expression
	 * @return the type (as class)
	 */
	public static Class<?> getType(String p_expression) {
		Method declaredField = getGetter(p_expression);
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
			return getType(valueExpression.getExpressionString());
		}
		return null;
	}

	public static boolean hasValueExpression(UIComponent component) {
		ValueExpression valueExpression = component.getValueExpression("value");
		return null != valueExpression;
	}

	/**
	 * Is the parameter passed a primitive type (such as int, long, etc) or a
	 * type considered primitive by most programmers (such as String)?
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
	public static Annotation[] readAnnotations(String p_expression) {
		Field declaredField = getField(p_expression);
		if (null != declaredField) {
			if (declaredField.getAnnotations() != null)
				return declaredField.getAnnotations();
		}
		Method getter = getGetter(p_expression);
		if (null != getter) {
			return getter.getAnnotations();
		}
		return null;
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
		if (valueExpression != null) {
			return readAnnotations(valueExpression.getExpressionString());
		}
		return null;
	}
}
