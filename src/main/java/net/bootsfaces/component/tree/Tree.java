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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.C;
import net.bootsfaces.component.tree.event.TreeNodeEventListener;
import net.bootsfaces.component.tree.model.Node;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:tree /&gt;. */
@ResourceDependencies({ 
		@ResourceDependency(library = "bsf", name = "js/bootstrap-treeview.min.js", target = "body"),
		})
@FacesComponent("net.bootsfaces.component.tree.Tree")
public class Tree extends UIComponentBase implements ClientBehaviorHolder {
	
	// Static internal references
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.tree.Tree";
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.tree.Tree";

	// Supported event list
	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click"));

	// Object properties
	protected enum PropertyKeys {
		value, 
		nodeSelectionListener, 
		showTags, 
		showIcon, 
		showCheckbox, 
		enableLinks, 
		collapseIcon, 
		expandIcon, 
		color, 
		update, 
		renderRoot
		;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	/**
	 * Constructor. It is used to configure the renderer
	 */
	public Tree() {
		setRendererType(DEFAULT_RENDERER);
		AddResourcesListener.addBasicJSResource("javax.faces", "jsf.js");
		AddResourcesListener.addThemedCSSResource("bootstrap-treeview.min.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("tooltip.css");
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Provide support to snake-case attribute in EL-expression items
	 */
	@Override
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}
	
	/**
	 * Management of events
	 */
	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}

	/**
	 * Get the configured NodeSelectionListener 
	 * @return
	 */
	public TreeNodeEventListener getNodeSelectionListener() {
		return (TreeNodeEventListener) this.getStateHelper().eval(PropertyKeys.nodeSelectionListener);
	}
	public void setNodeSelectionListener(TreeNodeEventListener nodeSelectionListener) {
		this.updateStateHelper(PropertyKeys.nodeSelectionListener.toString(), nodeSelectionListener);
	}

	/**
	 * Get the binding value. Here we have to set the Root Node 
	 * of the tree structure. The renderer provide a JSON Transformation
	 * to support the javascript library
	 * @return
	 */
	public Node getValue() {
		return (Node) this.getStateHelper().eval(PropertyKeys.value);
	}
	public void setValue(final Node _value) {
		this.updateStateHelper(PropertyKeys.value.toString(), _value);
	}
	
	/**
	 * Check if root node have to be displayed or not
	 * @return
	 */
	public boolean isRenderRoot() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.renderRoot, true);
		return (boolean) value;
	}
	public void setRenderRoot(boolean _renderRoot) {
		getStateHelper().put(PropertyKeys.renderRoot, _renderRoot);
	}

	/**
	 * Check if the icons have to be displayed or not
	 * @return
	 */
	public boolean isShowIcon() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.showIcon, true);
		return (boolean) value;
	}
	public void setShowIcon(boolean _showIcon) {
		getStateHelper().put(PropertyKeys.showIcon, _showIcon);
	}

	/**
	 * Check if the checkbox have to be displayed or not
	 * @return
	 */
	public boolean isShowCheckbox() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.showCheckbox, false);
		return (boolean) value;
	}
	public void setShowCheckbox(boolean _showCheckbox) {
		getStateHelper().put(PropertyKeys.showCheckbox, _showCheckbox);
	}

	/**
	 * Check if the node tags have to be displayed or not
	 * @return
	 */
	public boolean isShowTags() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.showTags, false);
		return (boolean) value;
	}
	public void setShowTags(boolean _showTags) {
		getStateHelper().put(PropertyKeys.showTags, _showTags);
	}

	/**
	 * Check if the href links are enabled or not
	 * @return
	 */
	public boolean isEnableLinks() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.enableLinks, false);
		return (boolean) value;
	}
	public void setEnableLinks(boolean _enableLinks) {
		getStateHelper().put(PropertyKeys.enableLinks, _enableLinks);
	}

	/**
	 * Get/Set the collapse icon for the tree
	 * @return
	 */
	public String getCollapseIcon() {
		String value = (String) getStateHelper().eval(PropertyKeys.collapseIcon);
		return value;
	}
	public void setCollapseIcon(String _collapseIcon) {
		getStateHelper().put(PropertyKeys.collapseIcon, _collapseIcon);
	}

	/**
	 * Get/Set the expand icon for the tree
	 * @return
	 */
	public String getExpandIcon() {
		String value = (String) getStateHelper().eval(PropertyKeys.expandIcon);
		return value;
	}
	public void setExpandIcon(String _expandIcon) {
		getStateHelper().put(PropertyKeys.expandIcon, _expandIcon);
	}

	/**
	 * Get the tree item text color
	 * @return
	 */
	public String getColor() {
		String value = (String) getStateHelper().eval(PropertyKeys.color);
		return value;
	}
	public void setColor(String _color) {
		getStateHelper().put(PropertyKeys.color, _color);
	}

	/**
	 * Get the item to update after an ajax request
	 * @return
	 */
	public String getUpdate() {
		String value = (String) getStateHelper().eval(PropertyKeys.update);
		return value;
	}
	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	/**
	 * Utility method to update the state helper
	 * @param propertyName
	 * @param value
	 */
	private void updateStateHelper(final String propertyName, final Object value) {
		this.getStateHelper().put(propertyName, value);

		final ValueExpression ve = this.getValueExpression(propertyName);

		if (ve != null) {
			ve.setValue(this.getFacesContext().getELContext(), value);
		}
	}
}
