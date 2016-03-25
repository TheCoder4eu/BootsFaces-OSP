package net.bootsfaces.component.image;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;


/** This class holds the attributes of &lt;b:image /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
        @ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
        @ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
        @ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head"),
        @ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
@FacesComponent("net.bootsfaces.component.image.Image")
public class Image extends UICommand implements net.bootsfaces.render.IHasTooltip, IAJAXComponent, ClientBehaviorHolder {

    public static final String COMPONENT_TYPE = "net.bootsfaces.component.image.Image";

    public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

    public static final String DEFAULT_RENDERER = "net.bootsfaces.component.image.Image";

	private Map<String, Object> attributes;

    public Image() {
        Tooltip.addResourceFile();
        setRendererType(DEFAULT_RENDERER);
    }

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList(
            "click", "dblclick", "dragstart", "dragover", "drop", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup"));

    /**
     * returns the subset of AJAX requests that are implemented by jQuery callback or other non-standard means
     * (such as the onclick event of b:tabView, which has to be implemented manually).
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

    protected enum PropertyKeys {
        ajax,
        alt,
        disabled,
        height,
        lang,
        name,
        onclick,
        oncomplete,
        ondblclick,
        ondragstart,
        ondragover,
        ondrop,
        onmousedown,
        onmousemove,
        onmouseout,
        onmouseover,
        onmouseup,
        process,
        readonly,
        style,
        styleClass,
        title,
        tooltip,
        tooltipDelay,
        tooltipDelayHide,
        tooltipDelayShow,
        tooltipPosition,
        update,
        width, tooltipContainer
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
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering error in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipContainer() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
		return  value;
	}
	
	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering error in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
	    getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
    }
	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getProcess() {
		String value = (String)getStateHelper().eval(PropertyKeys.process);
		return  value;
	}
	
	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setProcess(String _process) {
	    getStateHelper().put(PropertyKeys.process, _process);
    }

	/**
     * Alternate textual description of the element rendered by this component. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getAlt() {
        String value = (String)getStateHelper().eval(PropertyKeys.alt);
        return  value;
    }

    /**
     * Alternate textual description of the element rendered by this component. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setAlt(String _alt) {
        getStateHelper().put(PropertyKeys.alt, _alt);
    }


    /**
     * Activates AJAX. The default value is false (no AJAX). <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public boolean isAjax() {
        Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.ajax, false);
        return (boolean) value;
    }

    /**
     * Activates AJAX. The default value is false (no AJAX). <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setAjax(boolean _ajax) {
        getStateHelper().put(PropertyKeys.ajax, _ajax);
    }

    /**
     * Boolean value to specify if the button is disabled. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public boolean isDisabled() {
        Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.disabled, false);
        return (boolean) value;
    }

    /**
     * Boolean value to specify if the button is disabled. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setDisabled(boolean _disabled) {
        getStateHelper().put(PropertyKeys.disabled, _disabled);
    }


    /**
     * Override for the height of this image. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getHeight() {
        String value = (String)getStateHelper().eval(PropertyKeys.height);
        return  value;
    }

    /**
     * Override for the height of this image. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setHeight(String _height) {
        getStateHelper().put(PropertyKeys.height, _height);
    }


    /**
     * Code describing the language used in the generated markup for this component. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getLang() {
        String value = (String)getStateHelper().eval(PropertyKeys.lang);
        return  value;
    }

    /**
     * Code describing the language used in the generated markup for this component. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setLang(String _lang) {
        getStateHelper().put(PropertyKeys.lang, _lang);
    }


    /**
     * Icon name, mandatory. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getName() {
        String value = (String)getStateHelper().eval(PropertyKeys.name);
        return  value;
    }

    /**
     * Icon name, mandatory. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setName(String _name) {
        getStateHelper().put(PropertyKeys.name, _name);
    }


    /**
     * The onclick attribute. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getOnclick() {
        String value = (String)getStateHelper().eval(PropertyKeys.onclick);
        return  value;
    }

    /**
     * The onclick attribute. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setOnclick(String _onclick) {
        getStateHelper().put(PropertyKeys.onclick, _onclick);
    }


    /**
     * Javascript to be executed when ajax completes with success. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getOncomplete() {
        String value = (String)getStateHelper().eval(PropertyKeys.oncomplete);
        return  value;
    }

    /**
     * Javascript to be executed when ajax completes with success. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setOncomplete(String _oncomplete) {
        getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
    }


    /**
     * Client side callback to execute when input element is double clicked. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getOndblclick() {
        String value = (String)getStateHelper().eval(PropertyKeys.ondblclick);
        return  value;
    }

    /**
     * Client side callback to execute when input element is double clicked. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setOndblclick(String _ondblclick) {
        getStateHelper().put(PropertyKeys.ondblclick, _ondblclick);
    }


	/**
	 * Client side callback when a drag-and-drop action starts. May also call an AJAX function. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndragstart() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondragstart);
		return  value;
	}
	
	/**
	 * Client side callback when a drag-and-drop action starts. May also call an AJAX function. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndragstart(String _ondragstart) {
	    getStateHelper().put(PropertyKeys.ondragstart, _ondragstart);
    }
	

	/**
	 * Client side callback when an element is dragged over another element. Call event.preventDefault() to allow the dragged element to drop. May also call an AJAX function. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndragover() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondragover);
		return  value;
	}
	
	/**
	 * Client side callback when an element is dragged over another element. Call event.preventDefault() to allow the dragged element to drop. May also call an AJAX function. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndragover(String _ondragover) {
	    getStateHelper().put(PropertyKeys.ondragover, _ondragover);
    }
	

	/**
	 * Client side callback when a drag-and-drop action ends. May also call an AJAX function. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndrop() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondrop);
		return  value;
	}
	
	/**
	 * Client side callback when a drag-and-drop action ends. May also call an AJAX function. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndrop(String _ondrop) {
	    getStateHelper().put(PropertyKeys.ondrop, _ondrop);
    }

	/**
     * Client side callback to execute when a pointer input element is pressed down over input element. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getOnmousedown() {
        String value = (String)getStateHelper().eval(PropertyKeys.onmousedown);
        return  value;
    }

    /**
     * Client side callback to execute when a pointer input element is pressed down over input element. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setOnmousedown(String _onmousedown) {
        getStateHelper().put(PropertyKeys.onmousedown, _onmousedown);
    }


    /**
     * Client side callback to execute when a pointer input element is moved within input element. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getOnmousemove() {
        String value = (String)getStateHelper().eval(PropertyKeys.onmousemove);
        return  value;
    }

    /**
     * Client side callback to execute when a pointer input element is moved within input element. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setOnmousemove(String _onmousemove) {
        getStateHelper().put(PropertyKeys.onmousemove, _onmousemove);
    }


    /**
     * Client side callback to execute when a pointer input element is moved away from input element. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getOnmouseout() {
        String value = (String)getStateHelper().eval(PropertyKeys.onmouseout);
        return  value;
    }

    /**
     * Client side callback to execute when a pointer input element is moved away from input element. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setOnmouseout(String _onmouseout) {
        getStateHelper().put(PropertyKeys.onmouseout, _onmouseout);
    }


    /**
     * Client side callback to execute when a pointer input element is moved onto input element. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getOnmouseover() {
        String value = (String)getStateHelper().eval(PropertyKeys.onmouseover);
        return  value;
    }

    /**
     * Client side callback to execute when a pointer input element is moved onto input element. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setOnmouseover(String _onmouseover) {
        getStateHelper().put(PropertyKeys.onmouseover, _onmouseover);
    }


    /**
     * Client side callback to execute when a pointer input element is released over input element. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getOnmouseup() {
        String value = (String)getStateHelper().eval(PropertyKeys.onmouseup);
        return  value;
    }

    /**
     * Client side callback to execute when a pointer input element is released over input element. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setOnmouseup(String _onmouseup) {
        getStateHelper().put(PropertyKeys.onmouseup, _onmouseup);
    }


    /**
     * Flag indicating that this input element will prevent changes by the user. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public boolean isReadonly() {
        Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.readonly, false);
        return (boolean) value;
    }

    /**
     * Flag indicating that this input element will prevent changes by the user. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setReadonly(boolean _readonly) {
        getStateHelper().put(PropertyKeys.readonly, _readonly);
    }


    /**
     * Inline style <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getStyle() {
        String value = (String)getStateHelper().eval(PropertyKeys.style);
        return  value;
    }

    /**
     * Inline style <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setStyle(String _style) {
        getStateHelper().put(PropertyKeys.style, _style);
    }


    /**
     * CSS style class <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getStyleClass() {
        String value = (String)getStateHelper().eval(PropertyKeys.styleClass);
        return  value;
    }

    /**
     * CSS style class <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setStyleClass(String _styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }


    /**
     * Advisory title information about markup elements generated for this component. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getTitle() {
        String value = (String)getStateHelper().eval(PropertyKeys.title);
        return  value;
    }

    /**
     * Advisory title information about markup elements generated for this component. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setTitle(String _title) {
        getStateHelper().put(PropertyKeys.title, _title);
    }


    /**
     * The text of the tooltip. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getTooltip() {
        String value = (String)getStateHelper().eval(PropertyKeys.tooltip);
        return  value;
    }

    /**
     * The text of the tooltip. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltip(String _tooltip) {
        getStateHelper().put(PropertyKeys.tooltip, _tooltip);
    }


    /**
     * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public int getTooltipDelay() {
        Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
        return (int) value;
    }

    /**
     * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltipDelay(int _tooltipDelay) {
        getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
    }


    /**
     * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public int getTooltipDelayHide() {
        Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
        return (int) value;
    }

    /**
     * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltipDelayHide(int _tooltipDelayHide) {
        getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
    }


    /**
     * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public int getTooltipDelayShow() {
        Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
        return (int) value;
    }

    /**
     * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltipDelayShow(int _tooltipDelayShow) {
        getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
    }


    /**
     * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getTooltipPosition() {
        String value = (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
        return  value;
    }

    /**
     * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setTooltipPosition(String _tooltipPosition) {
        getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
    }


    /**
     * Component(s) to be updated with ajax. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getUpdate() {
        String value = (String)getStateHelper().eval(PropertyKeys.update);
        return  value;
    }

    /**
     * Component(s) to be updated with ajax. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setUpdate(String _update) {
        getStateHelper().put(PropertyKeys.update, _update);
    }


    /**
     * Override for the width of this image. <P>
     * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
     */
    public String getWidth() {
        String value = (String)getStateHelper().eval(PropertyKeys.width);
        return  value;
    }

    /**
     * Override for the width of this image. <P>
     * Usually this method is called internally by the JSF engine.
     */
    public void setWidth(String _width) {
        getStateHelper().put(PropertyKeys.width, _width);
    }
}
