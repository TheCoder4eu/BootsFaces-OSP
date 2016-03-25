package net.bootsfaces.component.inputTextarea;

import net.bootsfaces.beans.BsfBeanInfo;

/**
 * BeanInfo class to provide mapping
 * of snake-case attributes to camelCase ones
 * 
 * @author durzod
 */
public class InputTextareaBeanInfo extends BsfBeanInfo {
	/**
	 * Get the reference decorated class
	 */
	@Override
	public Class<?> getDecoratedClass() {
		return InputTextarea.class;
	}
}