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

import java.sql.Date;

import javax.faces.component.UIComponent;

/**
 * Stores server side validation and layout informations.
 */
public class NGBeanAttributeInfo {
	private Class<?> clazz;
	private String coreExpression;

	private boolean hasMax = false;

	private boolean hasMaxSize = false;
	private boolean hasMin = false;
	private boolean hasMinSize = false;
	/** Is the attribute a date? */
	private boolean isDate = false;

	/** Is the attribute a boolean? */
	private boolean isBoolean = false;

	/** Is the attribute a float or a double? */
	private boolean isFloat = false;
	/** Is the attribute one of the integer types (int, long, short, byte)? */
	private boolean isInteger = false;

	/** Numeric values include integers and doubles. */
	private boolean isNumeric = false;
	private boolean isRequired = false;
	private long max = 0;
	private long maxSize = Integer.MIN_VALUE;
	private long min = 0;
	private long minSize = Integer.MIN_VALUE;

	/**
	 * Extract the server side validation and layout informations.
	 * @param component the component
	 */
	public NGBeanAttributeInfo(UIComponent component) {
		readJSR303Annotations(component);
	}

	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @return the coreExpression
	 */
	public String getCoreExpression() {
		return this.coreExpression;
	}

	public long getMax() {
		return max;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public long getMin() {
		return min;
	}

	public long getMinSize() {
		return minSize;
	}
	
	/** Is the attribute a boolean? 
	 * @return true if it's a boolean
	 * */
	public boolean isBoolean() {
		return isBoolean;
	}

	/** Is the attribute a date? 
	 * @return true if its a java.util.Date 
	*/
	public boolean isDate() {
		return isDate;
	}

	/**
	 * Is the attribute a float or a double?
	 * @return true if it's a float of a double
	 */
	public boolean isFloat() {
		return this.isFloat;
	}

	public boolean isHasMax() {
		return hasMax;
	}

	public boolean isHasMaxSize() {
		return hasMaxSize;
	}

	public boolean isHasMin() {
		return hasMin;
	}

	public boolean isHasMinSize() {
		return hasMinSize;
	}

	/**
	 * Is the attribute one of the integer types (int, long, short, byte)?
	 * @return true if it is
	 */
	public boolean isInteger() {
		return isInteger;
	}

	public boolean isNumeric() {
		return isNumeric;
	}

	public boolean isRequired() {
		return isRequired;
	}
	
	public String toString() {
		return "CLASS: " + clazz + " - CORE ESPRESSION: " + coreExpression;
	}

	/**
	 * Read the JSR 303 annotations from a bean\"s attribute.
	 *
	 * @param component
	 */
	private void readJSR303Annotations(UIComponent component) {
		coreExpression = ELTools.getCoreValueExpression(component);
//		Annotation[] annotations = ELTools.readAnnotations(component);
//		if (null != annotations) {
//			for (Annotation a : annotations) {
//				if (a instanceof Max) {
//					long maximum = ((Max) a).value();
//					max = maximum;
//					hasMax = true;
//				} else if (a instanceof Min) {
//					long minimum = ((Min) a).value();
//					hasMin = true;
//					min = minimum;
//				} else if (a instanceof Size) {
//					maxSize = ((Size) a).max();
//					hasMaxSize = maxSize > 0;
//					minSize = ((Size) a).min();
//					hasMinSize = minSize > 0;
//				} else if (a instanceof NotNull) {
//					isRequired = true;
//				} else if (a.annotationType().getSimpleName().equals("NotEmpty")) {
//					isRequired = true;
//				}
//			}
//		}

		clazz = ELTools.getType(component);
		if ((clazz == Integer.class) || (clazz == int.class)
				|| (clazz == Byte.class) || (clazz == byte.class)
				|| (clazz == Short.class) || (clazz == short.class)
				|| (clazz == Long.class) || (clazz == long.class)) {
			isInteger = true;
			isNumeric = true;
		} else if ((clazz == Double.class) || (clazz == double.class)
				|| (clazz == Float.class) || (clazz == float.class)) {
			isFloat = true;
			isNumeric = true;
		} else if ((clazz==Date.class) || (clazz==java.util.Date.class)) {
			isDate=true;
		}
		else if ((clazz==Boolean.class) || (clazz==boolean.class)) {
			isBoolean=true;
		}
	}
}
