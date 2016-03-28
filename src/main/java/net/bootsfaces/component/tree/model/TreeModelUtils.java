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

import java.util.List;

import net.bootsfaces.utils.BsfUtils;

/**
 * Utility class that transform a Tree Model to 
 * relative JSON structure
 * 
 * @author durzod
 *
 */
public class TreeModelUtils {
	
	/**
	 * Render the node model as JSON
	 * @return
	 */
	public static String renderModelAsJson (Node rootNode, boolean renderRoot) {
		if(renderRoot) 
			return renderSubnodes(BsfUtils.AsList(rootNode));
		else {
			if(rootNode.hasChild()) return renderSubnodes(rootNode.getChilds());
		}
		
		return "";
	}
	
	private static String renderNode(Node node) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		// NODE ID
		if(node.getNodeId() != -1) {
			sb.append("\"nodeId\": " + node.getNodeId() + ", ");
		}
		// TEXT
		if(BsfUtils.StringIsValued(node.getText())) {
			sb.append("\"text\": \"" + node.getText() + "\", ");
		}
		// ICON
		if(BsfUtils.StringIsValued(node.getIcon())) {
			sb.append("\"icon\": \"" + node.getIcon() + "\", ");
		}
		// SELECTED ICON
		if(BsfUtils.StringIsValued(node.getSelectedIcon())) {
			sb.append("\"selectedIcon\": \"" + node.getSelectedIcon() + "\", ");
		}
		// COLOR
		if(BsfUtils.StringIsValued(node.getColor())) {
			sb.append("\"color\": \"" + node.getColor() + "\", ");
		}
		// BACK COLOR
		if(BsfUtils.StringIsValued(node.getBackColor())) {
			sb.append("\"backColor\": \"" + node.getBackColor() + "\", ");
		}
		// HREF
		if(BsfUtils.StringIsValued(node.getHRef())) {
			sb.append("\"href\": \"" + node.getHRef() + "\", ");
		}	
		// TAGS
		if(node.getTags() != null && node.getTags().size() > 0) {
			sb.append("\"tags\": [");
			for(String tag: node.getTags()) {
				sb.append("\"" + tag + "\",");
			}
			sb.append("\"\"],");
		}
		// NODES:
		if(node.getChilds() != null && node.getChilds().size() > 0) {
			sb.append("\"nodes\": ");
			sb.append(renderSubnodes(node.getChilds()));
			sb.append(",");
		}
		
		sb.append("\"selectable\": " + node.isSelectable() + ", ");
		sb.append("\"state\": {");
		sb.append("\"checked\": " + node.isChecked() + ", ");
		sb.append("\"disabled\": " + node.isDisabled() + ", ");
		sb.append("\"expanded\": " + node.isExpanded() + ", ");
		sb.append("\"selected\": " + node.isSelected() + " ");
		sb.append("}");
		sb.append("}");
		
		return sb.toString();
	}
	
	private static String renderSubnodes(List<Node> nodeList) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int idx = 0;
		for(Node n: nodeList) {
			if(idx > 0) {
				sb.append(",");
			}
			idx++;
			sb.append(renderNode(n));
		}
		sb.append("]");
		
		return sb.toString();
	}
}
