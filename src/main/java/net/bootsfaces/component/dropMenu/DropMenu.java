/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.dropMenu;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:dropMenu /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/dropdown.js", target = "body"), })
@FacesComponent("net.bootsfaces.component.dropMenu.DropMenu")
public class DropMenu extends UIComponentBase implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.dropMenu.DropMenu";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dropMenu.DropMenu";

	public DropMenu() {
		Tooltip.addResourceFiles();
		// AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		AddResourcesListener.addThemedCSSResource("dropdowns.css");
		AddResourcesListener.addThemedCSSResource("dropdown-submenu.css");
		AddResourcesListener.addThemedCSSResource("core.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		binding,
		contentClass,
		contentStyle,
		display,
		drop,
		hidden,
		icon,
		iconAlign,
		iconAwesome,
		style,
		styleClass,
		tooltip,
		tooltipContainer,
		tooltipDelay,
		tooltipDelayHide,
		tooltipDelayShow,
		tooltipPosition,
		visible
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
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		return  (javax.faces.component.UIComponent)getStateHelper().eval(PropertyKeys.binding);
	}
	
	/**
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
	    getStateHelper().put(PropertyKeys.binding, _binding);
    }
	

	/**
	 * content-class is optional: if specified, the content will be displayed with this specific class <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentClass() {
		return  (String)getStateHelper().eval(PropertyKeys.contentClass);
	}
	
	/**
	 * content-class is optional: if specified, the content will be displayed with this specific class <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentClass(String _contentClass) {
	    getStateHelper().put(PropertyKeys.contentClass, _contentClass);
    }
	

	/**
	 * Inline style of the content area. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentStyle() {
		return  (String)getStateHelper().eval(PropertyKeys.contentStyle);
	}
	
	/**
	 * Inline style of the content area. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentStyle(String _contentStyle) {
	    getStateHelper().put(PropertyKeys.contentStyle, _contentStyle);
    }
	

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDisplay() {
		return  (String)getStateHelper().eval(PropertyKeys.display, "block");
	}
	
	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisplay(String _display) {
	    getStateHelper().put(PropertyKeys.display, _display);
    }
	

	/**
	 * Use up For Dropup and down for Dropdown, default is down. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDrop() {
		return  (String)getStateHelper().eval(PropertyKeys.drop);
	}
	
	/**
	 * Use up For Dropup and down for Dropdown, default is down. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDrop(String _drop) {
	    getStateHelper().put(PropertyKeys.drop, _drop);
    }
	

	/**
	 * This row is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHidden() {
		return  (String)getStateHelper().eval(PropertyKeys.hidden);
	}
	
	/**
	 * This row is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHidden(String _hidden) {
	    getStateHelper().put(PropertyKeys.hidden, _hidden);
    }
	

	/**
	 * Navigation Link Icon, can be one of the Bootstrap's Glyphicons icon names. Alignment can be specified with the icon-align attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIcon() {
		return  (String)getStateHelper().eval(PropertyKeys.icon);
	}
	
	/**
	 * Navigation Link Icon, can be one of the Bootstrap's Glyphicons icon names. Alignment can be specified with the icon-align attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIcon(String _icon) {
	    getStateHelper().put(PropertyKeys.icon, _icon);
    }
	

	/**
	 * Alignment can be right or left. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconAlign() {
		return  (String)getStateHelper().eval(PropertyKeys.iconAlign);
	}
	
	/**
	 * Alignment can be right or left. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconAlign(String _iconAlign) {
	    getStateHelper().put(PropertyKeys.iconAlign, _iconAlign);
    }
	

	/**
	 * Navigation Link Font Awesome Icon, can be one of the Font Awesome icon names. Alignment can be specified with the icon-align attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconAwesome() {
		return  (String)getStateHelper().eval(PropertyKeys.iconAwesome);
	}
	
	/**
	 * Navigation Link Font Awesome Icon, can be one of the Font Awesome icon names. Alignment can be specified with the icon-align attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIconAwesome(String _iconAwesome) {
	    getStateHelper().put(PropertyKeys.iconAwesome, _iconAwesome);
    }
	

	/**
	 * Inline style <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return  (String)getStateHelper().eval(PropertyKeys.style);
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
		return  (String)getStateHelper().eval(PropertyKeys.styleClass);
	}
	
	/**
	 * CSS style class <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
	    getStateHelper().put(PropertyKeys.styleClass, _styleClass);
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
	 * This row is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getVisible() {
		return  (String)getStateHelper().eval(PropertyKeys.visible);
	}
	
	/**
	 * This row is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVisible(String _visible) {
	    getStateHelper().put(PropertyKeys.visible, _visible);
    }
	
}

