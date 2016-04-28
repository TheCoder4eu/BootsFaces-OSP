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
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/bootstrap-treeview.min.js", target = "body"), })
@FacesComponent("net.bootsfaces.component.tree.Tree")
public class Tree extends UIComponentBase implements ClientBehaviorHolder {

	// Static internal references
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.tree.Tree";
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.tree.Tree";

	// Supported event list
	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click"));

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

	// Object properties
	protected enum PropertyKeys {
		value,
		renderRoot,
		nodeSelectionListener,
		showTags,
		showIcon,
		showCheckbox,
		enableLinks,
		collapseIcon,
		expandIcon,
		color,
		update
;
        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {}

        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }
	

	/**
	 * Tree model value. Requires a java.util.List&lt;net.bootsfaces.component.tree.model.Node&gt; to work. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public Node getValue() {
		return  (Node)getStateHelper().eval(PropertyKeys.value);
	}
	
	/**
	 * Tree model value. Requires a java.util.List&lt;net.bootsfaces.component.tree.model.Node&gt; to work. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setValue(Node _value) {
	    getStateHelper().put(PropertyKeys.value, _value);
    }
	

	/**
	 * Boolean value to specify if the root node must be rendered. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRenderRoot() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.renderRoot, true);
	}
	
	/**
	 * Boolean value to specify if the root node must be rendered. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRenderRoot(boolean _renderRoot) {
	    getStateHelper().put(PropertyKeys.renderRoot, _renderRoot);
    }
	

	/**
	 * Selection listener called on selection changed. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public TreeNodeEventListener getNodeSelectionListener() {
		return  (TreeNodeEventListener)getStateHelper().eval(PropertyKeys.nodeSelectionListener);
	}
	
	/**
	 * Selection listener called on selection changed. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNodeSelectionListener(TreeNodeEventListener _nodeSelectionListener) {
	    getStateHelper().put(PropertyKeys.nodeSelectionListener, _nodeSelectionListener);
    }
	

	/**
	 * Boolean value to specify if tags was show or not. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowTags() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.showTags, false);
	}
	
	/**
	 * Boolean value to specify if tags was show or not. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowTags(boolean _showTags) {
	    getStateHelper().put(PropertyKeys.showTags, _showTags);
    }
	

	/**
	 * Boolean value to specify if icons are rendered or not. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowIcon() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.showIcon, false);
	}
	
	/**
	 * Boolean value to specify if icons are rendered or not. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowIcon(boolean _showIcon) {
	    getStateHelper().put(PropertyKeys.showIcon, _showIcon);
    }
	

	/**
	 * Boolean value to specify if checkbox is rendered or not. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowCheckbox() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.showCheckbox, false);
	}
	
	/**
	 * Boolean value to specify if checkbox is rendered or not. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowCheckbox(boolean _showCheckbox) {
	    getStateHelper().put(PropertyKeys.showCheckbox, _showCheckbox);
    }
	

	/**
	 * Boolean value to specify if enable href link. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableLinks() {
		return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.enableLinks, false);
	}
	
	/**
	 * Boolean value to specify if enable href link. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableLinks(boolean _enableLinks) {
	    getStateHelper().put(PropertyKeys.enableLinks, _enableLinks);
    }
	

	/**
	 * Glyphicons of collapse. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getCollapseIcon() {
		return  (String)getStateHelper().eval(PropertyKeys.collapseIcon);
	}
	
	/**
	 * Glyphicons of collapse. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCollapseIcon(String _collapseIcon) {
	    getStateHelper().put(PropertyKeys.collapseIcon, _collapseIcon);
    }
	

	/**
	 * Glyphicons of expand. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getExpandIcon() {
		return  (String)getStateHelper().eval(PropertyKeys.expandIcon);
	}
	
	/**
	 * Glyphicons of expand. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setExpandIcon(String _expandIcon) {
	    getStateHelper().put(PropertyKeys.expandIcon, _expandIcon);
    }
	

	/**
	 * Text color. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColor() {
		return  (String)getStateHelper().eval(PropertyKeys.color);
	}
	
	/**
	 * Text color. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColor(String _color) {
	    getStateHelper().put(PropertyKeys.color, _color);
    }
	

	/**
	 * Component(s) to be updated with ajax. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getUpdate() {
		return  (String)getStateHelper().eval(PropertyKeys.update);
	}
	
	/**
	 * Component(s) to be updated with ajax. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
	    getStateHelper().put(PropertyKeys.update, _update);
    }
	
}

