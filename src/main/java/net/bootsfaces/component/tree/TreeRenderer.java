package net.bootsfaces.component.tree;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.component.tree.model.Node;
import net.bootsfaces.component.tree.model.TreeModelUtils;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.utils.BsfUtils;

@FacesRenderer(componentFamily = C.BSFCOMPONENT, rendererType = "net.bootsfaces.component.tree.Tree")
public class TreeRenderer extends CoreRenderer {
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}

		Tree tree = (Tree) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = tree.getClientId();

		// put custom code here
		// Simple demo widget that simply renders every attribute value
		rw.startElement("div", tree);
		rw.writeAttribute("id", "tree_" + clientId, "id");
		rw.endElement("div");
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Tree tree = (Tree) component;
		String clientId = tree.getClientId();
		ResponseWriter rw = context.getResponseWriter();
		String updateItems = BsfUtils.GetOrDefault("'" + tree.getUpdate() + "'", "null");
		
		rw.startElement("script", tree);
		//# Start enclosure
		rw.writeText("$(document).ready(function() {", null);
		// build tree management javascript
		rw.writeText("function getTreeData() { " +
					 "   return '" + TreeModelUtils.renderModelAsJson((List<Node>) tree.getValue())  + "'; " +
					 "} " +
					 
					 // build tree structure
					 "$('#tree_" + clientId + "').treeview({ " +
					 
					 	(tree.getAttributes().get("showTag") != null ? "showTag: " + tree.getAttributes().get("showTag") + "," : "") +
					 	(tree.getAttributes().get("showIcon") != null ? "showIcon: " + tree.getAttributes().get("showIcon") + "," : "") +
					 	(tree.getAttributes().get("showCheckbox") != null ? "showCheckbox: " + tree.getAttributes().get("showCheckbox") + "," : "") +
					 	(tree.getAttributes().get("enableLinks") != null ? "enableLinks: " + tree.getAttributes().get("enableLinks") + "," : "") +
					 	(tree.getAttributes().get("collapseIcon") != null ? "collapseIcon: '" + tree.getAttributes().get("collapseIcon") + "'," : "") +
					 	(tree.getAttributes().get("expandIcon") != null ? "expandIcon: '" + tree.getAttributes().get("expandIcon") + "'," : "") +
					 	(tree.getAttributes().get("color") != null ? "color: '" + tree.getAttributes().get("color") + "'," : "") +
					 
						"     data: getTreeData()   " + 
					 "}); " +
					
					 // enable nodeSelected event callback
					 "$('#tree_" + clientId + "').on('nodeSelected', function(event, data) { " +
					 "   BsF.ajax.callAjax(this, event, " + updateItems + ", '@form', null, 'nodeSelected:' + data.text);" + // @all
					 "});" +
					 
					 //enable nodeChecked event callback
					 "$('#tree_" + clientId + "').on('nodeChecked', function(event, data) { " +
					 "   BsF.ajax.callAjax(this, event, " + updateItems + ", '@form', null, 'nodeChecked:' + data.text);" + // @all
					 "});" +
					 
					 //enable nodeUnchecked event callback
					 "$('#tree_" + clientId + "').on('nodeUnchecked', function(event, data) { " +
					 "   BsF.ajax.callAjax(this, event, " + updateItems + ", '@form', null, 'nodeUnchecked:' + data.text);" + // @all
					 "});", null);
		rw.writeText("});", null);
		rw.endElement("script");
	}
}
