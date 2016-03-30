package net.bootsfaces.component.accordion;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.component.panel.Panel;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:panel /&gt;. */
@ResourceDependencies({ 
		@ResourceDependency(library = "bsf", name = "js/transition.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "js/collapse.js", target = "body"),
})
@FacesComponent("net.bootsfaces.component.accordion.Accordion")
public class Accordion extends UIComponentBase {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.accordion.Accordion";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public Accordion() {
		Tooltip.addResourceFile();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("panels.css");
		setRendererType(null);
	}
	
	protected enum PropertyKeys {
		collapsedPanels, expandedPanels;
		
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
	
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		// Children are already rendered in encodeBegin()
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		
		ResponseWriter rw = context.getResponseWriter();
		String accordionClientId = this.getClientId().replace(":", "_");
		
		List<String> expandedIds = (null != this.getExpandedPanels()) ? Arrays.asList(this.getExpandedPanels().split(",")) : null;
		
		rw.startElement("div", this);
		rw.writeAttribute("class", "panel-group", null);
		rw.writeAttribute("id", accordionClientId, "id");
		
		if(this.getChildren() != null && this.getChildren().size() > 0) {
			for(UIComponent _child: this.getChildren()) {
				if(_child instanceof Panel && ((Panel) _child).isCollapsible()) {
					Panel _childPane = (Panel) _child;
					_childPane.setAccordionParent(accordionClientId);
					if(null != expandedIds && expandedIds.contains(_childPane.getClientId()))
						_childPane.setCollapsed(false);
					else _childPane.setCollapsed(true);
					_childPane.encodeAll(context);
				} else {
					throw new FacesException("Accordion must contains only collapsible panel components", null);
				}
			}
		}
	}
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		ResponseWriter rw = context.getResponseWriter();
		rw.endElement("div");
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
	/**
	 * This attribute specify a list of comma separated child panels id to display 
	 * initially expanded. 
	 * Collapsed is the default state for child panel.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getExpandedPanels() {
		String value = (String) getStateHelper().eval(PropertyKeys.expandedPanels, null);
		return value;
	}

	/**
	 * This attribute specify a list of comma separated child panels id to display 
	 * initially expanded. 
	 */
	public void setExpandedPanels(String _expandedPanels) {
		getStateHelper().put(PropertyKeys.expandedPanels, _expandedPanels);
	}
}
