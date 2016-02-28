package net.bootsfaces.component.tree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.component.tree.event.TreeNodeSelectionEvent;
import net.bootsfaces.component.tree.event.TreeNodeCheckedEvent;
import net.bootsfaces.component.tree.event.TreeNodeEventListener;
import net.bootsfaces.component.tree.model.DefaultNodeImpl;
import net.bootsfaces.component.tree.model.Node;

/** This class holds the attributes of &lt;b:dataTable /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
	@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
	@ResourceDependency(library = "bsf", name = "js/bootstrap-treeview.min.js", target = "body"),
	@ResourceDependency(library = "bsf", name = "css/bootstrap-treeview.min.css", target = "head")})
@FacesComponent("net.bootsfaces.component.tree.Tree")
public class Tree extends UIComponentBase implements ClientBehaviorHolder {
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.tree.Tree";
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.tree.Tree";

	private Map<String, Object> attributes;
	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click"));

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

	public Tree() {
		setRendererType(DEFAULT_RENDERER);
	}

	@Override
	public void decode(FacesContext context)
	{
		super.decode(context);

		final TreeNodeEventListener nodeSelectionListener = this.getNodeSelectionListener();
		String params = context.getExternalContext().getRequestParameterMap().get("params");
		if ( params != null )
		{
			if ( params != null )
			{
				params = params.replace( "BsFEvent=", "" );
				String[] pair = params.split( ":", 2 );
				String key = pair[ 0 ];
				String value = null;
				if ( pair.length == 2 )
				{
					value = pair[1];
				}
				if ( value != null )
				{
					Node n = new DefaultNodeImpl(value);
					
					switch(key) {
					case "nodeSelected":
						Node n2 = checkNodeIsSelected((List<Node>)this.getValue());
						nodeSelectionListener.processValueChange(new TreeNodeSelectionEvent(n2, n));
						break;
					case "nodeChecked": 
						nodeSelectionListener.processValueChecked(new TreeNodeCheckedEvent(n, true));
						break;
					case "nodeUnchecked": 
						nodeSelectionListener.processValueUnchecked(new TreeNodeCheckedEvent(n, false));
						break;
					}
				}
			}
		}
	}
	
	private Node checkNodeIsSelected(List<Node> nodeList) {
		for(Node n: nodeList) {
			if(this.getNodeSelectionListener().isValueSelected(n) == true) return n;
			else if(n.getSubNodes() != null && n.getSubNodes().size() > 0) {
				Node rt = checkNodeIsSelected(n.getSubNodes());
				if(rt != null) return rt;
			} 
		}

		return null;
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public TreeNodeEventListener getNodeSelectionListener() {
		return (TreeNodeEventListener) this.getStateHelper().eval(PropertyKeys.nodeSelectionListener);
	}

	public void setNodeSelectionListener(final TreeNodeEventListener nodeSelectionListener) {
		this.updateStateHelper(PropertyKeys.nodeSelectionListener.toString(), nodeSelectionListener);
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}


	@SuppressWarnings("unchecked")
	public List<Node> getValue() {
		return (List<Node>) this.getStateHelper().eval(PropertyKeys.value);
	}

	public void setValue(final List<Node> _value) {
		this.updateStateHelper(PropertyKeys.value.toString(), _value);
	}
	
	public boolean isShowIcon() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.showIcon, true);
		return (boolean) value;
	}
	
	public void setShowIcon(boolean _showIcon) {
	    getStateHelper().put(PropertyKeys.showIcon, _showIcon);
    }
	
	public boolean isShowCheckbox() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.showCheckbox, false);
		return (boolean) value;
	}
	
	public void setShowCheckbox(boolean _showCheckbox) {
	    getStateHelper().put(PropertyKeys.showCheckbox, _showCheckbox);
    }
	
	public boolean isShowTags() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.showTags, false);
		return (boolean) value;
	}
	
	public void setShowTags(boolean _showTags) {
	    getStateHelper().put(PropertyKeys.showTags, _showTags);
    }
	
	public boolean isEnableLinks() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.enableLinks, false);
		return (boolean) value;
	}
	
	public void setEnableLinks(boolean _enableLinks) {
	    getStateHelper().put(PropertyKeys.enableLinks, _enableLinks);
    }
	
	public String getCollapseIcon() {
		String value = (String)getStateHelper().eval(PropertyKeys.collapseIcon);
		return  value;
	}
	
	public void setCollapseIcon(String _collapseIcon) {
	    getStateHelper().put(PropertyKeys.collapseIcon, _collapseIcon);
    }
	
	public String getExpandIcon() {
		String value = (String)getStateHelper().eval(PropertyKeys.expandIcon);
		return  value;
	}
	
	public void setExpandIcon(String _expandIcon) {
	    getStateHelper().put(PropertyKeys.expandIcon, _expandIcon);
    }
	
	public String getColor() {
		String value = (String)getStateHelper().eval(PropertyKeys.color);
		return  value;
	}
	
	public void setColor(String _color) {
	    getStateHelper().put(PropertyKeys.color, _color);
    }
	
	public String getUpdate() {
		String value = (String)getStateHelper().eval(PropertyKeys.update);
		return  value;
	}

	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	private void updateStateHelper(final String propertyName, final Object value) {
		this.getStateHelper().put(propertyName, value);

		final ValueExpression ve = this.getValueExpression(propertyName);

		if (ve != null) {
			ve.setValue(this.getFacesContext().getELContext(), value);
		}
	}
}
