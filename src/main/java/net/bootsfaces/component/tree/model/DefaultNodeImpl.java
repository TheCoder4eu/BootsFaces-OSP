package net.bootsfaces.component.tree.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a basic default implementation of Node interface.
 * So, here we represent a single tree node with all it's attributes and a series of
 * utility behaviour:
 * - implementation of equals for searching
 * - Fluent interface (with* methods) to provide fast node definition
 * - A basic implementation of searchById on nodes and subnodes 
 * 
 * @author durzod
 *
 */
public class DefaultNodeImpl 
implements Node {

    private final List<Node> childs = new ArrayList<Node>();
    private String data;
    private int nodeId;
    private String text;
    private String icon;
    private String selectedIcon;
    private String color;
    private String backColor;
    private String HRef;
    private List<String> tags;
    private boolean selectable = true;
    private boolean checked = false;
    private boolean disabled = false;
    private boolean expanded = true;
    private boolean selected = false;
    private boolean useFaIcons = false;

    public DefaultNodeImpl() { }
    
    public DefaultNodeImpl(String text) {
		super();
		this.text = text;
	}
    
	public DefaultNodeImpl(String text, String icon) {
		super();
		this.text = text;
		this.icon = icon;
	}
	
	public DefaultNodeImpl(String text, String icon, boolean useFaIcons) {
		super();
		this.text = text;
		this.icon = icon;
		this.useFaIcons = useFaIcons;
	}

	public DefaultNodeImpl(String text, String icon, String data) {
		super();
		this.data = data;
		this.text = text;
		this.icon = icon;
	}
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		if(icon != null) {
			if(icon.contains("glyphicon-") || icon.contains("fa-")) return icon;
			else if(useFaIcons) return "fa fa-" + icon; 
			else return "glyphicon glyphicon-" + icon; 
		}
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSelectedIcon() {
		if(selectedIcon != null) {
			if(selectedIcon.contains("glyphicon-") || selectedIcon.contains("fa-")) return selectedIcon;
			else if(useFaIcons) return "fa fa-" + selectedIcon; 
			else return "glyphicon glyphicon-" + selectedIcon; 
		}
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public List<Node> getChilds() {
		return childs;
	}
	
	public boolean hasChild() {
		return (childs != null && childs.size() > 0);
	}

	public String getHRef() {
		return HRef;
	}

	public void setHRef(String hRef) {
		HRef = hRef;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getText().equals(((Node)obj).getText());
	}

	@Override
	public boolean getUseFaIcons() {
		return useFaIcons;
	}

	@Override
	public void setUseFaIcons(boolean useFaIcons) {
		this.useFaIcons = useFaIcons;
	}
	
	/** 
	 * Fluent API implementation
	 * These methods provide a different way to set property
	 * in a fluent-style manner. 
	 */
	public DefaultNodeImpl withData(String data) {
		this.data = data;
		return this;
	}

	public DefaultNodeImpl withNodeId(int nodeId) {
		this.nodeId = nodeId;
		return this;
	}

	public DefaultNodeImpl withText(String text) {
		this.text = text;
		return this;
	}

	public DefaultNodeImpl withIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public DefaultNodeImpl withSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
		return this;
	}

	public DefaultNodeImpl withColor(String color) {
		this.color = color;
		return this;
	}

	public DefaultNodeImpl withBackColor(String backColor) {
		this.backColor = backColor;
		return this;
	}

	public DefaultNodeImpl withSelectable(boolean selectable) {
		this.selectable = selectable;
		return this;
	}

	public DefaultNodeImpl witchChecked(boolean checked) {
		this.checked = checked;
		return this;
	}

	public DefaultNodeImpl withDisabled(boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	public DefaultNodeImpl withExpanded(boolean expanded) {
		this.expanded = expanded;
		return this;
	}

	public DefaultNodeImpl withSelected(boolean selected) {
		this.selected = selected;
		return this;
	}

	public DefaultNodeImpl withHRef(String hRef) {
		this.HRef = hRef;
		return this;
	}

	public DefaultNodeImpl withTags(List<String> tags) {
		this.tags = tags;
		return this;
	}
	
	public DefaultNodeImpl withUseFaIcons(boolean useFaIcons) {
		this.useFaIcons = useFaIcons;
		return this;
	}
	
	public DefaultNodeImpl withSubnode(Node subNode) {
		this.getChilds().add(subNode);
		return this;
	}
	
	/**
	 * Basic implementation of recursive node search by id
	 * It works only on a DefaultNodeImpl
	 * @param nodeId
	 * @return
	 */
	public Node searchNodeById(int nodeId) {
		if(this.nodeId == nodeId) return this;
		for(Node n: this.getChilds()) {
			if(n instanceof DefaultNodeImpl) {
				Node foundNode = ((DefaultNodeImpl)n).searchNodeById(nodeId);
				if(foundNode != null) return foundNode;
			}
		}
		return null;
		
	}
}