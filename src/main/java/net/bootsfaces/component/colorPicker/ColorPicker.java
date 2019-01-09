package net.bootsfaces.component.colorPicker;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;

import net.bootsfaces.C;
import net.bootsfaces.beans.ELTools;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.IResponsiveLabel;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(ColorPicker.COMPONENT_TYPE)
public class ColorPicker extends ColorPickerCore implements IHasTooltip, IAJAXComponent, IAJAXComponent2, IResponsive, IResponsiveLabel {

	private String renderLabel = null;

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".colorPicker.ColorPicker";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			Arrays.asList("blur", "change", "click", "dblclick", "focus", "keydown", "keypress", "keyup",
					"mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

	public ColorPicker() {
		setRendererType("net.bootsfaces.component.colorPicker.ColorPicker");
		Tooltip.addResourceFiles();
		//AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addExtCSSResource("jquery.minicolors.css");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/jquery.minicolors.min.js");

		renderLabel = BsfUtils.getInitParam("net.bootsfaces.defaults.renderLabel");
		if (null != renderLabel && renderLabel.contains("#{")) {
			renderLabel = ELTools.evalAsString(renderLabel);
		}
	}

	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
 	 		super.processEvent(event);
 	 	}
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Returns the subset of the parameter list of jQuery and other non-standard JS callbacks which is sent to the server via AJAX.
	 * If there's no parameter list for a certain event, the default is simply null.
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterListsForAjax() {
		return null;
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
		return "change";
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

	/**
	 * Returns the parameter list of jQuery and other non-standard JS callbacks.
	 * If there's no parameter list for a certain event, the default is simply "event".
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterLists() {
		return null;
	}
	
	/**
	 * Boolean value to specify if the widget is disabled.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or false, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isDisabled() {
		if (super.isDisabled()) 
			return true;
		UIComponent ancestor = getParent();
		while (ancestor!=null) {
			if (ancestor instanceof IContentDisabled) {
				if (((IContentDisabled)ancestor).isContentDisabled()) {
					return true;
				}
			}
			ancestor=ancestor.getParent();
		}
		return false;
	}
	
	/**
	 * Allows you to suppress automatic rendering of labels. Used internally by AngularFaces, too. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isRenderLabel() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.renderLabel, net.bootsfaces.component.ComponentUtils.isRenderLabelDefault());
	}


}
