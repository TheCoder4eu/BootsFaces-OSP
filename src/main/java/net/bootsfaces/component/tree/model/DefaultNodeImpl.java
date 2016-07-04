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
implements Node, Serializable {
	private static final long serialVersionUID = -6742025921003033215L;
	
	private final transient List<Node> childs = new ArrayList<Node>();
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
		if(nodeId <= 0) {
			int rand = (int)(1 + (Math.random() * 9));
			int txtHash = (text != null ? text.hashCode() : 1);
			int icnHash = (icon != null ? icon.hashCode() : 2);
			
			nodeId = rand + txtHash + icnHash;
			if(nodeId < 0) nodeId = nodeId * -1;
		}
		// if(nodeId <= 0) nodeId = new BigInteger(text.getBytes()).intValue();
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
		if(obj == null) return false;
		if(obj instanceof DefaultNodeImpl)
			return this.getText().equals(((Node)obj).getText());
		return false;
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
}