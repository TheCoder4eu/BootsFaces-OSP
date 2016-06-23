package net.bootsfaces.component.image;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:image /&gt;. */
@FacesComponent("net.bootsfaces.component.image.Image")
public class Image extends ImageCore
		implements net.bootsfaces.render.IHasTooltip, IAJAXComponent, ClientBehaviorHolder, IResponsive {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.image.Image";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.image.Image";

	public Image() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click",
			"dblclick", "dragstart", "dragover", "drop", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup"));

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).
	 *
	 * @return
	 */
	public Map<String, String> getJQueryEvents() {
		return null;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}
}
