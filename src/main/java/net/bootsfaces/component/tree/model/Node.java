package net.bootsfaces.component.tree.model;

import java.util.List;

public interface Node {
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
    List<Node> getSubNodes();
}
