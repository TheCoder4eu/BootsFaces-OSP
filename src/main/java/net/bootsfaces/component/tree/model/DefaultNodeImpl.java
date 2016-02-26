package net.bootsfaces.component.tree.model;

import java.util.ArrayList;
import java.util.List;

public class DefaultNodeImpl 
implements Node {

    private final List<Node> subNodes = new ArrayList<Node>();
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

    public DefaultNodeImpl() {
    }
    
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

	public List<Node> getSubNodes() {
		return subNodes;
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
}