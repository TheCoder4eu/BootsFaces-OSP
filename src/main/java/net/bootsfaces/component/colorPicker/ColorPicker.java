package net.bootsfaces.component.colorPicker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.beans.ELTools;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;

@FacesComponent("net.bootsfaces.component.colorPicker.ColorPicker")
public class ColorPicker extends ColorPickerCore implements IHasTooltip, IAJAXComponent, IResponsive {

	private String renderLabel = null;

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".ColorPicker";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			Arrays.asList("blur", "change", "valueChange", "click", "dblclick", "focus", "keydown", "keypress", "keyup",
					"mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

	public ColorPicker() {
		setRendererType("net.bootsfaces.component.colorPicker.ColorPicker");
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addThemedCSSResource("jquery.minicolors.css");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/jquery.minicolors.min.js");

		renderLabel = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.defaults.renderLabel");
		if (null != renderLabel && renderLabel.contains("#{")) {
			renderLabel = ELTools.evalAsString(renderLabel);
		}
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

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
		return "valueChange";
	}

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getSwatches() {
		String value = (String) getStateHelper().eval("swatches");
		return value;
	}

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSwatches(String _swatches) {
		getStateHelper().put("swatches", _swatches);
	}
}
