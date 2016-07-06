/**
 *  Copyright 2014-2016 Dario D'Urzo
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
