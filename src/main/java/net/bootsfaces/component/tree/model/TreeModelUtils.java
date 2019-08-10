/**
 *  Copyright 2014-2019 Dario D'Urzo
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

import net.bootsfaces.utils.BsfUtils;

/**
 * Utility class that transform a Tree Model to relative JSON structure
 *
 * @author durzod
 *
 */
public class TreeModelUtils {

    /**
     * Debug method to print tree node structure
     *
     * @param rootNode
     * @param tab
     */
    public static void printNodeData(Node rootNode, String tab) {
        
        tab = tab == null ? "" : tab + "  ";

        for (Node n : rootNode.getChilds()) {
            printNodeData(n, tab);
        }
    }

    /**
     * Basic implementation of recursive node search by id It works only on a
     * DefaultNodeImpl
     *
     * @param rootNode
     * @param nodeId
     * @return Node instance or null if there is no node with specific id
     */
    public static Node searchNodeById(Node rootNode, int nodeId) {
        if (rootNode.getNodeId() == nodeId) {
            return rootNode;
        }
        Node foundNode = null;
        for (Node n : rootNode.getChilds()) {
            foundNode = searchNodeById(n, nodeId);
            if (foundNode != null) {
                break;
            }
        }
        return foundNode;
    }

    /**
     * Render the node model as JSON
     *
     * @param rootNode
     * @param renderRoot
     * @return
     */
    public static String renderModelAsJson(Node rootNode, boolean renderRoot) {
        if (renderRoot) {
            return renderSubnodes(rootNode == null ? new ArrayList<Node>() : new ArrayList<Node>(Arrays.asList(rootNode)));
        } else if (rootNode != null && rootNode.hasChild()) {
            return renderSubnodes(rootNode.getChilds());
        }

        return "";
    }

    private static String renderNode(Node node) {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        // NODE ID
        if (node.getNodeId() != -1) {
            // i have to map the generated node id to an internal value that is not related to the original structure
            sb.append("\"nodeInternalId\": ");
            sb.append(node.getNodeId());
            sb.append(", ");
        }
        // TEXT
        if (BsfUtils.isStringValued(node.getText())) {
            sb.append("\"text\": \"");
            sb.append(node.getText());
            sb.append("\", ");
        }
        // ICON
        if (BsfUtils.isStringValued(node.getIcon())) {
            sb.append("\"icon\": \"");
            sb.append(node.getIcon());
            sb.append("\", ");
        }
        // SELECTED ICON
        if (BsfUtils.isStringValued(node.getSelectedIcon())) {
            sb.append("\"selectedIcon\": \"");
            sb.append(node.getSelectedIcon());
            sb.append("\", ");
        }
        // COLOR
        if (BsfUtils.isStringValued(node.getColor())) {
            sb.append("\"color\": \"");
            sb.append(node.getColor());
            sb.append("\", ");
        }
        // BACK COLOR
        if (BsfUtils.isStringValued(node.getBackColor())) {
            sb.append("\"backColor\": \"");
            sb.append(node.getBackColor());
            sb.append("\", ");
        }
        // HREF
        if (BsfUtils.isStringValued(node.getHRef())) {
            sb.append("\"href\": \"");
            sb.append(node.getHRef());
            sb.append("\", ");
        }
        // DATA
        if (BsfUtils.isStringValued(node.getData())) {
            sb.append("\"data\": \"");
            sb.append(node.getData());
            sb.append("\", ");
        }
        // TAGS
        if (node.getTags() != null && node.getTags().size() > 0) {
            sb.append("\"tags\": [");
            for (String tag : node.getTags()) {
                sb.append("\"");
                sb.append(tag);
                sb.append("\",");
            }
            sb.append("\"\"],");
        }
        // NODES:
        if (node.getChilds() != null && node.getChilds().size() > 0) {
            sb.append("\"nodes\": ");
            sb.append(renderSubnodes(node.getChilds()));
            sb.append(",");
        }

        sb.append("\"selectable\": ");
        sb.append(node.isSelectable());
        sb.append(", ");
        sb.append("\"state\": {");
        sb.append("\"checked\": ");
        sb.append(node.isChecked());
        sb.append(", ");
        sb.append("\"disabled\": ");
        sb.append(node.isDisabled());
        sb.append(", ");
        sb.append("\"expanded\": ");
        sb.append(node.isExpanded());
        sb.append(", ");
        sb.append("\"selected\": ");
        sb.append(node.isSelected());
        sb.append(" }}");

        return sb.toString();
    }

    private static String renderSubnodes(List<Node> nodeList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int idx = 0;
        for (Node n : nodeList) {
            if (idx > 0) {
                sb.append(",");
            }
            idx++;
            sb.append(renderNode(n));
        }
        sb.append("]");

        return sb.toString();
    }
}
