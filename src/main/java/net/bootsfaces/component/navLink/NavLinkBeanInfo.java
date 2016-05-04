package net.bootsfaces.component.navLink;

import net.bootsfaces.beans.BsfBeanInfo;

/**
 * BeanInfo class to provide mapping of snake-case attributes to camelCase ones
 * 
 * @author durzod
 */
public class NavLinkBeanInfo extends BsfBeanInfo {
	/**
	 * Get the reference decorated class
	 */
	@Override
	public Class<?> getDecoratedClass() {
		return NavLink.class;
	}
}