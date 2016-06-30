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

package net.bootsfaces.component.tree.model;

import java.io.Serializable;
import java.util.List;

public interface Node 
extends Serializable {
	/**
	 * @return the node id
	 */
	int getNodeId();
    
	/**
     * @return the node title.
     */
    String getText();
	  
    /**
	 * @return the node icon
	 */
	String getIcon();
	
	/**
	 * @return the node expanded icon
	 */
	String getSelectedIcon();
	
	/**
	 * @return the node text color
	 */
	String getColor();
	
	/**
	 * @return the node back color
	 */
	String getBackColor();
	
	/**
	 * @return the node href
	 */
	String getHRef();
	
	/**
	 * @return the node is selectable
	 */
	boolean getUseFaIcons();
	void setUseFaIcons(final boolean useFaIcons);
	
	/**
	 * @return the node is selectable
	 */
	boolean isSelectable();
	void setSelectable(final boolean selectable);
	
	/**
	 * @return the node is checked
	 */
	boolean isChecked();
	void setChecked(final boolean checked);
	
	/**
	 * @return the node is disabled
	 */
	boolean isDisabled();
	void setDisabled(final boolean disabled);
	
	/**
	 * @return the node is expanded
	 */
	boolean isExpanded();
	void setExpanded(final boolean expanded);
	
	/**
	 * @return the node is selected
	 */
	boolean isSelected();
	void setSelected(final boolean selected);
	
	/**
	 * @return the node tags
	 */
	List<String> getTags();
	
	/**
	 * @return the node custom data
	 */
	String getData();
	
	/**
     * @return a list of child nodes. An empty list if no sub nodes exits.
     */
    List<Node> getChilds();
    
    /**
     * @return true if the node has child
     */
    boolean hasChild();
}
