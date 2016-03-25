package net.bootsfaces.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class that extends SimpleBeanInfo
 * to manage automatic mapping of all properties and 
 * additional custom ones
 * 
 * @author durzod
 *
 */
public abstract class BsfBeanInfo 
extends java.beans.SimpleBeanInfo {
	
	/**
	 * Abstract method to manage custom property descriptor
	 * @return
	 */
	public abstract PropertyDescriptor[] getCustomPropertyDescriptor() throws IntrospectionException;
	
	/**
	 * Methods that provide the class to decorate.
	 * It is an utility method to provide direct mapping inside this superclass
	 * @return
	 */
	public abstract Class<?> getDecoratedClass();
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		// get properties from default property descriptors
		List<PropertyDescriptor> pdl = new ArrayList<PropertyDescriptor>();
		PropertyDescriptor rv[] = super.getPropertyDescriptors();
		if(rv != null) {
			pdl.addAll(Arrays.asList(rv));
		}

		try {
			// if i have found nothing, i'm trying to get manually
			if(pdl.size() <= 0) {
				BeanInfo bi = Introspector.getBeanInfo(getDecoratedClass(), Introspector.IGNORE_ALL_BEANINFO);
				PropertyDescriptor birv[] = bi.getPropertyDescriptors();
				if(birv != null) {
					pdl.addAll(Arrays.asList(birv));
				}
			}
			
			// then add custom properties
			PropertyDescriptor[] customPd = getCustomPropertyDescriptor();
			if(customPd != null) 
				pdl.addAll(Arrays.asList(customPd));
		} catch (IntrospectionException e) {
			e.printStackTrace();
			throw new Error(e.toString());
		}
		
		PropertyDescriptor[] array = new PropertyDescriptor[pdl.size()];
		return pdl.toArray(array);
	} 

}
