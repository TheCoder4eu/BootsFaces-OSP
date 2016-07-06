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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
	 * Debug method to print tree node structure
	 * @param rootNode
	 * @param tab
	 */
	public static void printNodeData(Node rootNode, String tab) {
		if(tab == null) tab = "";
		else tab = tab + "  ";

//		System.out.println(tab + "NODE: " + rootNode.getNodeId() + " CHECKED: " + rootNode.isChecked());
		for(Node n: rootNode.getChilds()) {
			printNodeData(n, tab);
		}
	}

	/**
	 * Update the node with the new state, if node is found
	 *
	 * @param rootNode
	 * @param nodeId
	 * @param nodeState
	 */
	public static void updateNodeById(Node rootNode, int nodeId, Node nodeState) {
		Node refNode = searchNodeById(rootNode, nodeId);
		if(refNode != null) {
			refNode.setChecked(nodeState.isChecked());
			refNode.setDisabled(nodeState.isDisabled());
			refNode.setSelected(nodeState.isSelected());
			refNode.setSelectable(nodeState.isSelectable());
			refNode.setExpanded(nodeState.isExpanded());
		}
	}

	/**
	 * Basic implementation of recursive node search by id
	 * It works only on a DefaultNodeImpl
	 * @param nodeId
	 * @return
	 */
	private static Node searchNodeById(Node rootNode, int nodeId) {
		if(rootNode.getNodeId() == nodeId) return rootNode;
		Node foundNode = null;
		for(Node n: rootNode.getChilds()) {
			foundNode = searchNodeById(n, nodeId);
			if(foundNode != null) break;
		}
		return foundNode;
	}

	/**
	 * dataString structure is the following
	 *
	 * (0) nodeId
	 * (1) text
	 * (2) checked
	 * (3) disabled
	 * (4) expanded
	 * (5) selected
	 *
	 * the separator is |#*#|
	 *
	 * @param dataString see above
	 * @return
	 */
	public static Node mapDataToModel(String dataString) {
		String[] dataMap = dataString.split(Pattern.quote("|#*#|"));

		DefaultNodeImpl n = new DefaultNodeImpl();
		try {
			n.setNodeId(Integer.parseInt(dataMap[0]));
			n.setText(dataMap[1]);
			n.setData(dataMap[2]);
			n.setChecked(("true".equalsIgnoreCase(dataMap[3]) ? true: false));
			n.setDisabled(("true".equalsIgnoreCase(dataMap[4]) ? true: false));
			n.setExpanded(("true".equalsIgnoreCase(dataMap[5]) ? true: false));
			n.setSelected(("true".equalsIgnoreCase(dataMap[6]) ? true: false));
		} catch(Exception e) {
			e.printStackTrace();
		}

		return n;
	}

	/**
	 * Render the node model as JSON
	 * @return
	 */
	public static String renderModelAsJson (Node rootNode, boolean renderRoot) {
		if(renderRoot)
			return renderSubnodes(new ArrayList<Node>(Arrays.asList(rootNode)));
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
			// i have to map the generated node id to an internal value that is not related to the original structure
			sb.append("\"nodeInternalId\": " + node.getNodeId() + ", ");
		}
		// TEXT
		if(BsfUtils.isStringValued(node.getText())) {
			sb.append("\"text\": \"" + node.getText() + "\", ");
		}
		// ICON
		if(BsfUtils.isStringValued(node.getIcon())) {
			sb.append("\"icon\": \"" + node.getIcon() + "\", ");
		}
		// SELECTED ICON
		if(BsfUtils.isStringValued(node.getSelectedIcon())) {
			sb.append("\"selectedIcon\": \"" + node.getSelectedIcon() + "\", ");
		}
		// COLOR
		if(BsfUtils.isStringValued(node.getColor())) {
			sb.append("\"color\": \"" + node.getColor() + "\", ");
		}
		// BACK COLOR
		if(BsfUtils.isStringValued(node.getBackColor())) {
			sb.append("\"backColor\": \"" + node.getBackColor() + "\", ");
		}
		// HREF
		if(BsfUtils.isStringValued(node.getHRef())) {
			sb.append("\"href\": \"" + node.getHRef() + "\", ");
		}
		// DATA
		if(BsfUtils.isStringValued(node.getData())) {
			sb.append("\"data\": \"" + node.getData() + "\", ");
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
		// SERIALIZE
		sb.append("\"serialize\": \"" + BsfUtils.toString(node) + "\", ");

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
