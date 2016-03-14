/**
 *  Copyright 2014-2016 Dario D'Urzo
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */
package net.bootsfaces.component.tree;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import net.bootsfaces.beans.BsfBeanInfo;

/**
 * BeanInfo class to provide mapping
 * of snake-case attributes to camelCase ones
 * @author durzod
 *
 */
public class TreeBeanInfo extends BsfBeanInfo {

	public PropertyDescriptor[] getCustomPropertyDescriptor() 
	throws IntrospectionException {
		PropertyDescriptor[] items = new PropertyDescriptor[] {
			new PropertyDescriptor("render-root", Tree.class, "isRenderRoot", "setRenderRoot") {{ setBound(true); }},
			new PropertyDescriptor("node-selection-listener", Tree.class, "getNodeSelectionListener", "setNodeSelectionListener") {{ setBound(true); }},
			new PropertyDescriptor("show-tags", Tree.class, "isShowTags", "setShowTags") {{ setBound(true); }},
			new PropertyDescriptor("show-icon", Tree.class, "isShowIcon", "setShowIcon") {{ setBound(true); }},
			new PropertyDescriptor("show-checkbox", Tree.class, "isShowCheckbox", "setShowCheckbox") {{ setBound(true); }},
			new PropertyDescriptor("enable-links", Tree.class, "isEnableLinks", "setEnableLinks") {{ setBound(true); }},
			new PropertyDescriptor("collapse-icon", Tree.class, "getCollapseIcon", "setCollapseIcon") {{ setBound(true); }},
			new PropertyDescriptor("expand-icon", Tree.class, "getExpandIcon", "setExpandIcon") {{ setBound(true); }}
		};
		
		return items;
	}
}
