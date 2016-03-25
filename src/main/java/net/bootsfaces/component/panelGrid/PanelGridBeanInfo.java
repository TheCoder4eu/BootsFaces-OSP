package net.bootsfaces.component.panelGrid;

import net.bootsfaces.beans.BsfBeanInfo;

/**
 * BeanInfo class to provide mapping
 * of snake-case attributes to camelCase ones
 * 
 * @author durzod
 */
public class PanelGridBeanInfo extends BsfBeanInfo {
	/**
	 * Get the reference decorated class
	 */
	@Override
	public Class<?> getDecoratedClass() {
		return PanelGrid.class;
	}
}