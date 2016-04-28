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

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.component.tree.event.TreeNodeCheckedEvent;
import net.bootsfaces.component.tree.event.TreeNodeEventListener;
import net.bootsfaces.component.tree.event.TreeNodeSelectionEvent;
import net.bootsfaces.component.tree.model.Node;
import net.bootsfaces.component.tree.model.TreeModelUtils;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.utils.BsfUtils;

/** This class provide the rendering logic of the &lt;b:tree /&gt;. */
@FacesRenderer(componentFamily = C.BSFCOMPONENT, rendererType = "net.bootsfaces.component.tree.Tree")
public class TreeRenderer extends CoreRenderer {
	
	/**
	 * Decode ajax behaviours specific to the components
	 */
	@Override
	public void decode(FacesContext context, UIComponent component) {
		Tree tree = (Tree) component;
		super.decode(context, tree);

		final TreeNodeEventListener nodeSelectionListener = tree.getNodeSelectionListener();
		String params = context.getExternalContext().getRequestParameterMap().get("params");
		if (params != null && nodeSelectionListener != null) {
			if (params != null) {
				params = params.replace("BsFEvent=", "");
				String[] pair = params.split(":", 2);
				String key = pair[0];
				String value = null;
				if (pair.length == 2) {
					value = pair[1];
				}
				if (value != null && !"".equals(value.trim())) {
					Node n = TreeModelUtils.mapDataToModel(value);
					TreeModelUtils.updateNodeById(tree.getValue(), n.getNodeId(), n);
					// tree.setSubmittedValue(tree.getValue());
					
					// execute listener only for listened events
					if ("nodeSelected".equals(key)) {
						Node n2 = checkNodeIsSelected(tree.getValue(), tree);
						nodeSelectionListener.processValueChange(new TreeNodeSelectionEvent(n2, n));
					} else if ("nodeChecked".equals(key)) {
						nodeSelectionListener.processValueChecked(new TreeNodeCheckedEvent(n, true));
					} else if ("nodeUnchecked".equals(key)) {
						nodeSelectionListener.processValueUnchecked(new TreeNodeCheckedEvent(n, false));
					}
					/*
					else {
						throw new FacesException("Unexpected event when trying to decode the tree event: " + key);
					}
					*/
				}
			}
		}
	}

	/**
	 * Check if node tree is selected using the listener callback
	 * @param node
	 * @param tree
	 * @return
	 */
	private Node checkNodeIsSelected(Node node, Tree tree) {
		if(tree.getNodeSelectionListener().isValueSelected(node) == true) return node;
		for (Node n : node.getChilds()) {
			Node rt = checkNodeIsSelected(n, tree);
			if(rt != null) return rt;
		}

		return null;
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}

		Tree tree = (Tree) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = tree.getClientId();
		
		// check is inside form (MyFaces requires to be)
		final UIForm form = BsfUtils.getClosestForm(tree);
		if(form == null) {
			throw new FacesException("The tree component must be inside a form", null);
		}

		rw.startElement("div", tree);
		rw.writeAttribute("id", "tree_" + clientId, "id");
		renderPassThruAttributes(context, component, H.HTML5_DATA_ATTRIBUTES, true);
		rw.endElement("div");
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Tree tree = (Tree) component;
		String clientId = tree.getClientId();
		String jqClientId = BsfUtils.escapeJQuerySpecialCharsInSelector(clientId);
		ResponseWriter rw = context.getResponseWriter();
		
		final UIForm form = BsfUtils.getClosestForm(tree);
		if(form == null) {
			throw new FacesException("The tree component must be inside a form", null);
		}
		// String updateItems = BsfUtils.GetOrDefault("'" + tree.getUpdate() + "'", "null");
		String updateItems = tree.getUpdate();
		if(updateItems != null)
			updateItems = ExpressionResolver.getComponentIDs(context, component, updateItems);

		rw.startElement("script", tree);
		//# Start enclosure
		rw.writeText("$(document).ready(function() {", null);
		// build tree management javascript
		rw.writeText("function getTreeData() { " +
					 "   return '" + TreeModelUtils.renderModelAsJson(tree.getValue(), tree.isRenderRoot())  + "'; " +
					 "} " +
					 
					 // build tree structure
					 "$('#tree_" + jqClientId + "').treeview({ " +
					 	(tree.isShowTags()  ? "showTags: true," : "") +
					 	(tree.isShowIcon()  ? "showIcon: true," : "") +
					 	(tree.isShowCheckbox() ? "showCheckbox: true," : "") +
					 	(tree.isEnableLinks() ? "enableLinks: true," : "") +
					 	(BsfUtils.isStringValued(tree.getCollapseIcon()) ? "collapseIcon: '" + tree.getCollapseIcon() + "'," : "") +
					 	(BsfUtils.isStringValued(tree.getExpandIcon())  ? "expandIcon: '" + tree.getExpandIcon() + "'," : "") +
					 	(BsfUtils.isStringValued(tree.getColor())  ? "color: '" + tree.getColor() + "'," : "") +
						"   data: getTreeData(),   " + 
						// enable nodeSelected event callback
						"	onNodeSelected: function(event, data) { " +
						"   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, 'nodeSelected:' + treeDataMapper(data));" + // @all
						"	}," +
						// enable nodeUnselected event callback
						"	onNodeUnselected: function(event, data) { " +
						"   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, 'nodeUnselected:' + treeDataMapper(data));" + // @all
						"	}," +
						//enable nodeChecked event callback
						"	onNodeChecked: function(event, data) { " +
						"   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, 'nodeChecked:' + treeDataMapper(data));" + // @all
						"	}," +
						//enable nodeUnchecked event callback
						"	onNodeUnchecked: function(event, data) { " +
						"   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, 'nodeUnchecked:' + treeDataMapper(data));" + // @all
						"	}," +
						// enable nodeCollapsed event callback
						"	onNodeCollapsed: function(event, data) { " +
						"   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, 'nodeCollapsed:' + treeDataMapper(data));" + // @all
						"	}," +
						// enable nodeExpanded event callback
						"	onNodeExpanded: function(event, data) { " +
						"   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, 'nodeExpanded:' + treeDataMapper(data));" + // @all
						"	}" +
					 "}); ", null);
		rw.writeText("});", null);
		rw.endElement("script");
	}
}
