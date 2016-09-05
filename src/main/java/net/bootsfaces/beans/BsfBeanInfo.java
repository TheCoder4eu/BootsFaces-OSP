/**
 *  Copyright 2014-2016 by Riccardo Massera (TheCoder4.Eu), Stephan Rauh (http://www.beyondjava.net) and Dario D'Urzo.
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
package net.bootsfaces.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.component.UIComponent;

import net.bootsfaces.utils.BsfUtils;

/**
 * Utility class that extends SimpleBeanInfo to manage automatic mapping of all
 * properties and additional custom ones
 * 
 * @author durzod
 *
 */
public abstract class BsfBeanInfo extends java.beans.SimpleBeanInfo {

	/**
	 * Optional method to add custom property descriptors
	 * 
	 * @return null, if there's nothing to add
	 * @throws IntrospectionException
	 *             thrown if something goes wrong
	 */
	public PropertyDescriptor[] getCustomPropertyDescriptor() throws IntrospectionException {
		return null;
	};

	/**
	 * Methods that provide the class to decorate. It is an utility method to
	 * provide direct mapping inside this superclass
	 * 
	 * @return
	 */
	public abstract Class<?> getDecoratedClass();

	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		// get properties from default property descriptors
		List<PropertyDescriptor> pdl = new ArrayList<PropertyDescriptor>();
		PropertyDescriptor rv[] = super.getPropertyDescriptors();
		if (rv != null) {
			pdl.addAll(Arrays.asList(rv));
		}

		try {
			// if i have found nothing, i'm trying to get manually
			if (pdl.size() <= 0) {
				BeanInfo bi = Introspector.getBeanInfo(getDecoratedClass(), Introspector.IGNORE_ALL_BEANINFO);
				PropertyDescriptor birv[] = bi.getPropertyDescriptors();
				if (birv != null) {
					pdl.addAll(Arrays.asList(birv));
				}
			}

			add_snake_case_properties(pdl);

			// then add custom properties
			PropertyDescriptor[] customPd = getCustomPropertyDescriptor();
			if (customPd != null)
				pdl.addAll(Arrays.asList(customPd));
		} catch (IntrospectionException e) {
			e.printStackTrace();
			throw new Error(e.toString());
		}

		PropertyDescriptor[] array = new PropertyDescriptor[pdl.size()];
		return pdl.toArray(array);
	}

	/**
	 * Method that generates dynamically the snake-case methods from
	 * the available camelcase properties found inside the bean list.
	 * @param pdl
	 * @throws IntrospectionException
	 */
	private void add_snake_case_properties(List<PropertyDescriptor> pdl) throws IntrospectionException {
		List<PropertyDescriptor> alternatives = new ArrayList<PropertyDescriptor>();
		for (PropertyDescriptor descriptor : pdl) {
			String camelCase = descriptor.getName();
			if (camelCase.equals("rendererType")) {
				continue;
			}
			if (camelCase.equals("localValueSet")) {
				continue;
			}
			if (camelCase.equals("submittedValue")) {
				continue;
			}
			String snake_case = BsfUtils.camelCaseToSnakeCase(camelCase);
			if (snake_case != camelCase) { // in this particular case, we don't
											// need to use equals!
				if (descriptor.getReadMethod() != null && descriptor.getWriteMethod() != null) {
					String getter = descriptor.getReadMethod().getName();
					String setter = descriptor.getWriteMethod().getName();
					if (UIComponent.class != descriptor.getReadMethod().getDeclaringClass()) {
						PropertyDescriptor alternative = new PropertyDescriptor(snake_case, getDecoratedClass(), getter,
								setter);
						alternative.setBound(true);
						alternatives.add(alternative);

						// more alternatives
						if (camelCase.equals("styleClass")) {
							alternative = new PropertyDescriptor("class", getDecoratedClass(), getter, setter);
							alternative.setBound(true);
							alternatives.add(alternative);
						}
						// addAlternativeForScreenLayout(alternatives, camelCase, getter, setter);
					}
				}

			}
		}
		if (alternatives.size() > 0) {
			pdl.addAll(alternatives);
		}
	}

	/**
	 * Utility method to add alternative getter and setter 
	 * for grid layout settings. 
	 * @param alternatives
	 * @param camelCase
	 * @param getter
	 * @param setter
	 * @throws IntrospectionException
	 
	private void addAlternativeForScreenLayout(List<PropertyDescriptor> alternatives, String camelCase, String getter,
			String setter) throws IntrospectionException {
		PropertyDescriptor alternative;
		if (camelCase.equals("colXs")) {
			alternative = new PropertyDescriptor("tiny-screen", getDecoratedClass(), getter, setter);
			alternative.setBound(true);
			alternatives.add(alternative);
			alternative = new PropertyDescriptor("tinyScreen", getDecoratedClass(), getter, setter);
			alternative.setBound(true);
			alternatives.add(alternative);
		}
		if (camelCase.equals("colSm")) {
			alternative = new PropertyDescriptor("small-screen", getDecoratedClass(), getter, setter);
			alternative.setBound(true);
			alternatives.add(alternative);
			alternative = new PropertyDescriptor("smallScreen", getDecoratedClass(), getter, setter);
			alternative.setBound(true);
			alternatives.add(alternative);
		}
		if (camelCase.equals("colMd")) {
			alternative = new PropertyDescriptor("medium-screen", getDecoratedClass(), getter, setter);
			alternative.setBound(true);
			alternatives.add(alternative);
			alternative = new PropertyDescriptor("mediumScreen", getDecoratedClass(), getter, setter);
			alternative.setBound(true);
			alternatives.add(alternative);
		}
		if (camelCase.equals("colLg")) {
			alternative = new PropertyDescriptor("huge-screen", getDecoratedClass(), getter, setter);
			alternative.setBound(true);
			alternatives.add(alternative);
			alternative = new PropertyDescriptor("hugeScreen", getDecoratedClass(), getter, setter);
			alternative.setBound(true);
			alternatives.add(alternative);
		}
	}*/
}
