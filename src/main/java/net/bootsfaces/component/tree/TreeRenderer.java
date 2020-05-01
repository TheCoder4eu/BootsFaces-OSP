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
import net.bootsfaces.component.tree.event.TreeNodeExpandedEvent;
import net.bootsfaces.component.tree.event.TreeNodeSelectionEvent;
import net.bootsfaces.component.tree.model.Node;
import net.bootsfaces.component.tree.model.TreeModelUtils;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.utils.BsfUtils;

/**
 * This class provide the rendering logic of the &lt;b:tree /&gt;.
 */
@FacesRenderer(componentFamily = C.BSFCOMPONENT, rendererType = "net.bootsfaces.component.tree.Tree")
public class TreeRenderer extends CoreRenderer {

    /**
     * Decode ajax behaviours specific to the components
     *
     * @param context
     * @param component
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        Tree tree = (Tree) component;
        super.decode(context, tree);

        final TreeNodeEventListener nodeSelectionListener = tree.getNodeSelectionListener();
        String params = context.getExternalContext().getRequestParameterMap().get("BsFEvent");

        if (params != null) {
            String[] pair = params.split(":", 2);
            String key = pair[0];
            Integer value = null;
            if (pair.length == 2)
                value = Integer.valueOf(pair[1]);
            
            if (value != null) {
                Node n = TreeModelUtils.searchNodeById(tree.getValue(), value);

                if (nodeSelectionListener != null && n != null) {
                    // execute listener only for listened events
                    if ("nodeSelected".equals(key)) {
                        n.setSelected(true);
                        nodeSelectionListener.processValueSelected(new TreeNodeSelectionEvent(n, true));
                    } else if ("nodeUnselected".equals(key)) {
                        n.setSelected(false);
                        nodeSelectionListener.processValueSelected(new TreeNodeSelectionEvent(n, false));
                    } else if ("nodeChecked".equals(key)) {
                        n.setChecked(true);
                        nodeSelectionListener.processValueChecked(new TreeNodeCheckedEvent(n, true));
                    } else if ("nodeUnchecked".equals(key)) {
                        n.setChecked(false);
                        nodeSelectionListener.processValueChecked(new TreeNodeCheckedEvent(n, false));
                    } else if ("nodeExpanded".equals(key)) {
                        n.setExpanded(true);
                        nodeSelectionListener.processValueExpanded(new TreeNodeExpandedEvent(n, true));
                    } else if ("nodeCollapsed".equals(key)) {
                        n.setExpanded(false);
                        nodeSelectionListener.processValueExpanded(new TreeNodeExpandedEvent(n, false));
                    }
                }
            }
        }
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
        if (form == null) {
            throw new FacesException("The tree component must be inside a form", null);
        }

        rw.startElement("div", tree);
        rw.writeAttribute("id", "tree_" + clientId, "id");
        renderPassThruAttributes(context, component, new String[] { "placeholder", "tabindex", "lang", "accesskey"}, true);
        String clazz = Responsive.getResponsiveStyleClass(tree, false);
        String styleClass = tree.getStyleClass();
        if (null != styleClass) {
            clazz = styleClass + clazz;
        }
        clazz = clazz.trim();
        if (clazz.length() > 0) {
            rw.writeAttribute("class", clazz, "class");
        }
        writeAttribute(rw, "style", tree.getStyle());
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
        if (form == null) {
            throw new FacesException("The tree component must be inside a form", null);
        }
        // String updateItems = BsfUtils.GetOrDefault("'" + tree.getUpdate() + "'", "null");
        String updateItems = tree.getUpdate();
        if (updateItems != null) {
            updateItems = ExpressionResolver.getComponentIDs(context, component, updateItems);
        }

        rw.startElement("script", tree);
        //# Start enclosure
        rw.writeText("$(document).ready(function() {", null);
        // build tree management javascript
        rw.writeText("function getTreeData() { "
                + "   return '" + TreeModelUtils.renderModelAsJson(tree.getValue(), tree.isRenderRoot()) + "'; "
                + "} "
                + // build tree structure
                "$('#tree_" + jqClientId + "').treeview({ "
                + (tree.isShowTags() ? "showTags: true," : "")
                + (tree.isShowIcon() ? "showIcon: true," : "")
                + (tree.isShowCheckbox() ? "showCheckbox: true," : "")
                + (tree.isEnableLinks() ? "enableLinks: true," : "")
                + (tree.isShowBorder() ? "showBorder: true," : "showBorder: false,")
                + (BsfUtils.isStringValued(tree.getCollapseIcon()) ? "collapseIcon: '" + tree.getCollapseIcon() + "'," : "")
                + (BsfUtils.isStringValued(tree.getExpandIcon()) ? "expandIcon: '" + tree.getExpandIcon() + "'," : "")
                + (BsfUtils.isStringValued(tree.getColor()) ? "color: '" + tree.getColor() + "'," : "")
                + (BsfUtils.isStringValued(tree.getHoverColor()) ? "onhoverColor: '" + tree.getHoverColor() + "'," : "")
                + (BsfUtils.isStringValued(tree.getSelectedColor()) ? "selectedColor: '" + tree.getSelectedColor() + "'," : "")
                + (BsfUtils.isStringValued(tree.getBorderColor()) ? "borderColor: '" + tree.getBorderColor() + "'," : "")
                + "   data: getTreeData(),   "
                + // enable nodeSelected event callback
                "	onNodeSelected: function(event, data) { "
                + "   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, null, null, 'nodeSelected:' + data.nodeInternalId);"
                + "	},"
                + // enable nodeUnselected event callback
                "	onNodeUnselected: function(event, data) { "
                + "   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, null, null, 'nodeUnselected:' + data.nodeInternalId);"
                + "	},"
                + //enable nodeChecked event callback
                "	onNodeChecked: function(event, data) { "
                + "   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, null, null, 'nodeChecked:' + data.nodeInternalId);"
                + "	},"
                + //enable nodeUnchecked event callback
                "	onNodeUnchecked: function(event, data) { "
                + "   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, null, null, 'nodeUnchecked:' + data.nodeInternalId);"
                + "	},"
                + // enable nodeCollapsed event callback
                "	onNodeCollapsed: function(event, data) { "
                + "   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, null, null, 'nodeCollapsed:' + data.nodeInternalId);"
                + "	},"
                + // enable nodeExpanded event callback
                "	onNodeExpanded: function(event, data) { "
                + "   	BsF.ajax.callAjax(this, event, '" + updateItems + "', '" + clientId + "', null, null, null, 'nodeExpanded:' + data.nodeInternalId);"
                + // @all
                "	}"
                + "}); ", null);
        rw.writeText("});", null);
        rw.endElement("script");
    }
}
