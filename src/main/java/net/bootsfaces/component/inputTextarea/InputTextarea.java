/**
 * Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
 *
 * This file is part of BootsFaces.
 *
 * BootsFaces is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * BootsFaces is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */
package net.bootsfaces.component.inputTextarea;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author thecoder4.eu
 */
@FacesComponent("net.bootsfaces.component.inputTextarea.InputTextarea")
public class InputTextarea extends HtmlInputText implements IHasTooltip, IAJAXComponent {
	private String renderLabel = null;

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.inputTextarea.InputTextarea";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(
			Arrays.asList("blur", "change", "valueChange", "click", "dblclick", "focus", "keydown", "keypress", "keyup",
					"mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

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

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public InputTextarea() {
		setRendererType("net.bootsfaces.component.InputTextareaRenderer");
		AddResourcesListener.addBasicJSResource("javax.faces", "jsf.js");
		AddResourcesListener.addBasicJSResource("bsf", "js/bsf.js");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("tooltip.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		Tooltip.addResourceFile();
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	protected enum PropertyKeys {
		ajax,
		fieldSize,
		inline,
		oncomplete,
		placeholder,
		process,
		renderLabel,
		rows,
		span,
		tooltip,
		tooltipContainer,
		tooltipDelay,
		tooltipDelayHide,
		tooltipDelayShow,
		tooltipPosition,
		type,
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
 * Activates AJAX. The default value is false (no AJAX). <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public boolean isAjax() {
	return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.ajax, false);
}

/**
 * Activates AJAX. The default value is false (no AJAX). <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setAjax(boolean _ajax) {
    getStateHelper().put(PropertyKeys.ajax, _ajax);
}


/**
 * The size of the input. Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public String getFieldSize() {
	return  (String)getStateHelper().eval(PropertyKeys.fieldSize);
}

/**
 * The size of the input. Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setFieldSize(String _fieldSize) {
    getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
}


/**
 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public boolean isInline() {
	return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.inline, false);
}

/**
 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setInline(boolean _inline) {
    getStateHelper().put(PropertyKeys.inline, _inline);
}


/**
 * JavaScript to be executed when ajax completes with success. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public String getOncomplete() {
	return  (String)getStateHelper().eval(PropertyKeys.oncomplete);
}

/**
 * JavaScript to be executed when ajax completes with success. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setOncomplete(String _oncomplete) {
    getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
}


/**
 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public String getPlaceholder() {
	return  (String)getStateHelper().eval(PropertyKeys.placeholder);
}

/**
 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setPlaceholder(String _placeholder) {
    getStateHelper().put(PropertyKeys.placeholder, _placeholder);
}


/**
 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public String getProcess() {
	return  (String)getStateHelper().eval(PropertyKeys.process);
}

/**
 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setProcess(String _process) {
    getStateHelper().put(PropertyKeys.process, _process);
}


/**
 * Allows you to suppress automatic rendering of labels. Used by AngularFaces, too. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public boolean isRenderLabel() {
	return (boolean) (Boolean)getStateHelper().eval(PropertyKeys.renderLabel, false);
}

/**
 * Allows you to suppress automatic rendering of labels. Used by AngularFaces, too. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setRenderLabel(boolean _renderLabel) {
    getStateHelper().put(PropertyKeys.renderLabel, _renderLabel);
}


/**
 * Number of rows <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public int getRows() {
	return (int) (Integer)getStateHelper().eval(PropertyKeys.rows, 5);
}

/**
 * Number of rows <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setRows(int _rows) {
    getStateHelper().put(PropertyKeys.rows, _rows);
}


/**
 * The size of the input specified as number of grid columns. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public int getSpan() {
	return (int) (Integer)getStateHelper().eval(PropertyKeys.span, 0);
}

/**
 * The size of the input specified as number of grid columns. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setSpan(int _span) {
    getStateHelper().put(PropertyKeys.span, _span);
}


/**
 * The text of the tooltip. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public String getTooltip() {
	return  (String)getStateHelper().eval(PropertyKeys.tooltip);
}

/**
 * The text of the tooltip. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setTooltip(String _tooltip) {
    getStateHelper().put(PropertyKeys.tooltip, _tooltip);
}


/**
 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering errors in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public String getTooltipContainer() {
	return  (String)getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
}

/**
 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering errors in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setTooltipContainer(String _tooltipContainer) {
    getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
}


/**
 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public int getTooltipDelay() {
	return (int) (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
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
	return (int) (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
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
	return (int) (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
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
	return  (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
}

/**
 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setTooltipPosition(String _tooltipPosition) {
    getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
}


/**
 * Type of the input. The default is text. <P>
 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
 */
public String getType() {
	return  (String)getStateHelper().eval(PropertyKeys.type);
}

/**
 * Type of the input. The default is text. <P>
 * Usually this method is called internally by the JSF engine.
 */
public void setType(String _type) {
    getStateHelper().put(PropertyKeys.type, _type);
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

