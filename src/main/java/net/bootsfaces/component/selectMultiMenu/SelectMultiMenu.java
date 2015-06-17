/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.selectMultiMenu;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.render.Tooltip;

/** This class holds the attributes of &lt;b:selectMultiMenu /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/bootstrap-multiselect.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/bootstrap-multiselect.css", target = "head") })

@FacesComponent("net.bootsfaces.component.selectMultiMenu.SelectMultiMenu")
public class SelectMultiMenu extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {
	
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.selectMultiMenu.SelectMultiMenu";
	
	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";
	
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.selectMultiMenu.SelectMultiMenu";
	
	public SelectMultiMenu() {
		
		
	Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}
	
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	
    protected enum PropertyKeys {
maxHeight,
nonSelectedText,
nSelectedText,
allSelectedText,
numberDisplayed,
includeSelectAllOption,
selectAllText,
enableFiltering,
filterPlaceholder,
disabled,
fieldSize,
id,
immediate,
label,
lang,
onblur,
onchange,
onclick,
ondblclick,
onfocus,
onkeydown,
onkeypress,
onkeyup,
onmousedown,
onmousemove,
onmouseout,
onmouseover,
onmouseup,
onselect,
readonly,
renderLabel,
required,
requiredMessage,
span,
style,
styleClass,
tabindex,
title,
tooltip,
tooltipDelay,
tooltipDelayHide,
tooltipDelayShow,
tooltipPosition
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
	 * Maximum height of the options panel. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getMaxHeight() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.maxHeight, 0);
		return (int) value;
	}
	
	/**
	 * Maximum height of the options panel. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxHeight(int _maxHeight) {
	    getStateHelper().put(PropertyKeys.maxHeight, _maxHeight);
    }
	

	/**
	 * Text which is displayed if no option has been selected. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNonSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.nonSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if no option has been selected. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNonSelectedText(String _nonSelectedText) {
	    getStateHelper().put(PropertyKeys.nonSelectedText, _nonSelectedText);
    }
	

	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.nSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNSelectedText(String _nSelectedText) {
	    getStateHelper().put(PropertyKeys.nSelectedText, _nSelectedText);
    }
	

	/**
	 * Text which is displayed if every option has been selected. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAllSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.allSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if every option has been selected. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllSelectedText(String _allSelectedText) {
	    getStateHelper().put(PropertyKeys.allSelectedText, _allSelectedText);
    }
	

	/**
	 * Maximum number of options displayed in the button. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getNumberDisplayed() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.numberDisplayed, 0);
		return (int) value;
	}
	
	/**
	 * Maximum number of options displayed in the button. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNumberDisplayed(int _numberDisplayed) {
	    getStateHelper().put(PropertyKeys.numberDisplayed, _numberDisplayed);
    }
	

	/**
	 * If true, you can select every option with a single click. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isIncludeSelectAllOption() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.includeSelectAllOption, false);
		return (boolean) value;
	}
	
	/**
	 * If true, you can select every option with a single click. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIncludeSelectAllOption(boolean _includeSelectAllOption) {
	    getStateHelper().put(PropertyKeys.includeSelectAllOption, _includeSelectAllOption);
    }
	

	/**
	 * The text displayed for the select all option. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSelectAllText() {
		String value = (String)getStateHelper().eval(PropertyKeys.selectAllText);
		return  value;
	}
	
	/**
	 * The text displayed for the select all option. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectAllText(String _selectAllText) {
	    getStateHelper().put(PropertyKeys.selectAllText, _selectAllText);
    }
	

	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableFiltering() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.enableFiltering, false);
		return (boolean) value;
	}
	
	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableFiltering(boolean _enableFiltering) {
	    getStateHelper().put(PropertyKeys.enableFiltering, _enableFiltering);
    }
	

	/**
	 * The placeholder used for the filter input. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFilterPlaceholder() {
		String value = (String)getStateHelper().eval(PropertyKeys.filterPlaceholder);
		return  value;
	}
	
	/**
	 * The placeholder used for the filter input. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFilterPlaceholder(String _filterPlaceholder) {
	    getStateHelper().put(PropertyKeys.filterPlaceholder, _filterPlaceholder);
    }
	

	/**
	 * Disables the input element, default is false. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.disabled, false);
		return (boolean) value;
	}
	
	/**
	 * Disables the input element, default is false. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
	    getStateHelper().put(PropertyKeys.disabled, _disabled);
    }
	

	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFieldSize() {
		String value = (String)getStateHelper().eval(PropertyKeys.fieldSize);
		return  value;
	}
	
	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFieldSize(String _fieldSize) {
	    getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
    }
	

	/**
	 * Unique identifier of the component in a namingContainer. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getId() {
		String value = (String)getStateHelper().eval(PropertyKeys.id);
		return  value;
	}
	
	/**
	 * Unique identifier of the component in a namingContainer. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setId(String _id) {
	    getStateHelper().put(PropertyKeys.id, _id);
    }
	

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isImmediate() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.immediate, false);
		return (boolean) value;
	}
	
	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
	    getStateHelper().put(PropertyKeys.immediate, _immediate);
    }
	

	/**
	 * The Label of the field . <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		String value = (String)getStateHelper().eval(PropertyKeys.label);
		return  value;
	}
	
	/**
	 * The Label of the field . <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
	    getStateHelper().put(PropertyKeys.label, _label);
    }
	

	/**
	 * A localized user presentable name. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLang() {
		String value = (String)getStateHelper().eval(PropertyKeys.lang);
		return  value;
	}
	
	/**
	 * A localized user presentable name. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLang(String _lang) {
	    getStateHelper().put(PropertyKeys.lang, _lang);
    }
	

	/**
	 * Client side callback to execute when input element loses focus. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnblur() {
		String value = (String)getStateHelper().eval(PropertyKeys.onblur);
		return  value;
	}
	
	/**
	 * Client side callback to execute when input element loses focus. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnblur(String _onblur) {
	    getStateHelper().put(PropertyKeys.onblur, _onblur);
    }
	

	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnchange() {
		String value = (String)getStateHelper().eval(PropertyKeys.onchange);
		return  value;
	}
	
	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnchange(String _onchange) {
	    getStateHelper().put(PropertyKeys.onchange, _onchange);
    }
	

	/**
	 * OnClick DHTML event . <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnclick() {
		String value = (String)getStateHelper().eval(PropertyKeys.onclick);
		return  value;
	}
	
	/**
	 * OnClick DHTML event . <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
	    getStateHelper().put(PropertyKeys.onclick, _onclick);
    }
	

	/**
	 * Client side callback to execute when input element is double clicked. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndblclick() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondblclick);
		return  value;
	}
	
	/**
	 * Client side callback to execute when input element is double clicked. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndblclick(String _ondblclick) {
	    getStateHelper().put(PropertyKeys.ondblclick, _ondblclick);
    }
	

	/**
	 * Client side callback to execute when input element receives focus. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnfocus() {
		String value = (String)getStateHelper().eval(PropertyKeys.onfocus);
		return  value;
	}
	
	/**
	 * Client side callback to execute when input element receives focus. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnfocus(String _onfocus) {
	    getStateHelper().put(PropertyKeys.onfocus, _onfocus);
    }
	

	/**
	 * Client side callback to execute when a key is pressed down over input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnkeydown() {
		String value = (String)getStateHelper().eval(PropertyKeys.onkeydown);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a key is pressed down over input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeydown(String _onkeydown) {
	    getStateHelper().put(PropertyKeys.onkeydown, _onkeydown);
    }
	

	/**
	 * Client side callback to execute when a key is pressed and released over input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnkeypress() {
		String value = (String)getStateHelper().eval(PropertyKeys.onkeypress);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a key is pressed and released over input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeypress(String _onkeypress) {
	    getStateHelper().put(PropertyKeys.onkeypress, _onkeypress);
    }
	

	/**
	 * Client side callback to execute when a key is released over input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnkeyup() {
		String value = (String)getStateHelper().eval(PropertyKeys.onkeyup);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a key is released over input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeyup(String _onkeyup) {
	    getStateHelper().put(PropertyKeys.onkeyup, _onkeyup);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousedown() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmousedown);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousedown(String _onmousedown) {
	    getStateHelper().put(PropertyKeys.onmousedown, _onmousedown);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is moved within input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousemove() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmousemove);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is moved within input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousemove(String _onmousemove) {
	    getStateHelper().put(PropertyKeys.onmousemove, _onmousemove);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is moved away from input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseout() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmouseout);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is moved away from input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseout(String _onmouseout) {
	    getStateHelper().put(PropertyKeys.onmouseout, _onmouseout);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is moved onto input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseover() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmouseover);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is moved onto input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseover(String _onmouseover) {
	    getStateHelper().put(PropertyKeys.onmouseover, _onmouseover);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is released over input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseup() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmouseup);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is released over input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseup(String _onmouseup) {
	    getStateHelper().put(PropertyKeys.onmouseup, _onmouseup);
    }
	

	/**
	 * Client side callback to execute when text within input element is selected by user. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnselect() {
		String value = (String)getStateHelper().eval(PropertyKeys.onselect);
		return  value;
	}
	
	/**
	 * Client side callback to execute when text within input element is selected by user. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnselect(String _onselect) {
	    getStateHelper().put(PropertyKeys.onselect, _onselect);
    }
	

	/**
	 * Flag indicating that this input element will prevent changes by the user. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isReadonly() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.readonly, false);
		return (boolean) value;
	}
	
	/**
	 * Flag indicating that this input element will prevent changes by the user. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setReadonly(boolean _readonly) {
	    getStateHelper().put(PropertyKeys.readonly, _readonly);
    }
	

	/**
	 * Allows you to suppress automatic rendering of labels. Used by AngularFaces, too. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRenderLabel() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.renderLabel, true);
		return (boolean) value;
	}
	
	/**
	 * Allows you to suppress automatic rendering of labels. Used by AngularFaces, too. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRenderLabel(boolean _renderLabel) {
	    getStateHelper().put(PropertyKeys.renderLabel, _renderLabel);
    }
	

	/**
	 * Boolean value Require input in the component when the form is submitted. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRequired() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.required, false);
		return (boolean) value;
	}
	
	/**
	 * Boolean value Require input in the component when the form is submitted. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequired(boolean _required) {
	    getStateHelper().put(PropertyKeys.required, _required);
    }
	

	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRequiredMessage() {
		String value = (String)getStateHelper().eval(PropertyKeys.requiredMessage);
		return  value;
	}
	
	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequiredMessage(String _requiredMessage) {
	    getStateHelper().put(PropertyKeys.requiredMessage, _requiredMessage);
    }
	

	/**
	 * The size of the input specified as number of grid columns. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getSpan() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.span, 0);
		return (int) value;
	}
	
	/**
	 * The size of the input specified as number of grid columns. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(int _span) {
	    getStateHelper().put(PropertyKeys.span, _span);
    }
	

	/**
	 * Inline style of the input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.style);
		return  value;
	}
	
	/**
	 * Inline style of the input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
	    getStateHelper().put(PropertyKeys.style, _style);
    }
	

	/**
	 * Style class of the input element. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.styleClass);
		return  value;
	}
	
	/**
	 * Style class of the input element. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
	    getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }
	

	/**
	 * Advisory tooltip information. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTabindex() {
		String value = (String)getStateHelper().eval(PropertyKeys.tabindex);
		return  value;
	}
	
	/**
	 * Advisory tooltip information. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTabindex(String _tabindex) {
	    getStateHelper().put(PropertyKeys.tabindex, _tabindex);
    }
	

	/**
	 * Advisory tooltip information. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTitle() {
		String value = (String)getStateHelper().eval(PropertyKeys.title);
		return  value;
	}
	
	/**
	 * Advisory tooltip information. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitle(String _title) {
	    getStateHelper().put(PropertyKeys.title, _title);
    }
	

	/**
	 * The text of the tooltip. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltip);
		return  value;
	}
	
	/**
	 * The text of the tooltip. <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
	    getStateHelper().put(PropertyKeys.tooltip, _tooltip);
    }
	

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelay() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
	    getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
    }
	

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
	    getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
    }
	

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
	    getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
    }
	

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipPosition() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
		return  value;
	}
	
	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <br />
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
	    getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
    }
	
}
