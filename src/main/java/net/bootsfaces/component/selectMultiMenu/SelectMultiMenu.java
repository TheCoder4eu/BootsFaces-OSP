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
accesskey,
alt,
binding,
dir,
disabled,
fieldSize,
id,
immediate,
label,
lang,
maxHeight,
nonSelectedText,
nSelectedText,
allSelectedText,
numberDisplayed,
includeSelectAllOption,
selectAllText,
enableFiltering,
filterPlaceholder,
radiobuttons,
disableIfEmpty,
dropRight,
onchange,
ondropdownshow,
ondropdownhide,
buttonClass,
styleClass,
buttonWidth,
enableCaseInsensitiveFiltering,
placeholder,
readonly,
renderLabel,
required,
requiredMessage,
size,
span,
style,
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
	 * Access key to transfer focus to the input element. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAccesskey() {
		String value = (String)getStateHelper().eval(PropertyKeys.accesskey);
		return  value;
	}
	
	/**
	 * Access key to transfer focus to the input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAccesskey(String _accesskey) {
	    getStateHelper().put(PropertyKeys.accesskey, _accesskey);
    }
	

	/**
	 * Alternate textual description of the input element. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAlt() {
		String value = (String)getStateHelper().eval(PropertyKeys.alt);
		return  value;
	}
	
	/**
	 * Alternate textual description of the input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAlt(String _alt) {
	    getStateHelper().put(PropertyKeys.alt, _alt);
    }
	

	/**
	 * An el expression referring to a server side UIComponent instance in a backing bean. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		javax.faces.component.UIComponent value = (javax.faces.component.UIComponent)getStateHelper().eval(PropertyKeys.binding);
		return  value;
	}
	
	/**
	 * An el expression referring to a server side UIComponent instance in a backing bean. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
	    getStateHelper().put(PropertyKeys.binding, _binding);
    }
	

	/**
	 * Direction indication for text that does not inherit directionality. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDir() {
		String value = (String)getStateHelper().eval(PropertyKeys.dir);
		return  value;
	}
	
	/**
	 * Direction indication for text that does not inherit directionality. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
	    getStateHelper().put(PropertyKeys.dir, _dir);
    }
	

	/**
	 * Disables the input element, default is false. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.disabled, false);
		return (boolean) value;
	}
	
	/**
	 * Disables the input element, default is false. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
	    getStateHelper().put(PropertyKeys.disabled, _disabled);
    }
	

	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFieldSize() {
		String value = (String)getStateHelper().eval(PropertyKeys.fieldSize);
		return  value;
	}
	
	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFieldSize(String _fieldSize) {
	    getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
    }
	

	/**
	 * Unique identifier of the component in a namingContainer. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getId() {
		String value = (String)getStateHelper().eval(PropertyKeys.id);
		return  value;
	}
	
	/**
	 * Unique identifier of the component in a namingContainer. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setId(String _id) {
	    getStateHelper().put(PropertyKeys.id, _id);
    }
	

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isImmediate() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.immediate, false);
		return (boolean) value;
	}
	
	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
	    getStateHelper().put(PropertyKeys.immediate, _immediate);
    }
	

	/**
	 * The Label of the field . 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		String value = (String)getStateHelper().eval(PropertyKeys.label);
		return  value;
	}
	
	/**
	 * The Label of the field . 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
	    getStateHelper().put(PropertyKeys.label, _label);
    }
	

	/**
	 * A localized user presentable name. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLang() {
		String value = (String)getStateHelper().eval(PropertyKeys.lang);
		return  value;
	}
	
	/**
	 * A localized user presentable name. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLang(String _lang) {
	    getStateHelper().put(PropertyKeys.lang, _lang);
    }
	

	/**
	 * Maximum height of the options panel. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getMaxHeight() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.maxHeight, 0);
		return (int) value;
	}
	
	/**
	 * Maximum height of the options panel. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxHeight(int _maxHeight) {
	    getStateHelper().put(PropertyKeys.maxHeight, _maxHeight);
    }
	

	/**
	 * Text which is displayed if no option has been selected. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNonSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.nonSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if no option has been selected. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNonSelectedText(String _nonSelectedText) {
	    getStateHelper().put(PropertyKeys.nonSelectedText, _nonSelectedText);
    }
	

	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.nSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNSelectedText(String _nSelectedText) {
	    getStateHelper().put(PropertyKeys.nSelectedText, _nSelectedText);
    }
	

	/**
	 * Text which is displayed if every option has been selected. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAllSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.allSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if every option has been selected. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllSelectedText(String _allSelectedText) {
	    getStateHelper().put(PropertyKeys.allSelectedText, _allSelectedText);
    }
	

	/**
	 * Maximum number of options displayed in the button. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getNumberDisplayed() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.numberDisplayed, 0);
		return (int) value;
	}
	
	/**
	 * Maximum number of options displayed in the button. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNumberDisplayed(int _numberDisplayed) {
	    getStateHelper().put(PropertyKeys.numberDisplayed, _numberDisplayed);
    }
	

	/**
	 * If true, you can select every option with a single click. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isIncludeSelectAllOption() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.includeSelectAllOption, false);
		return (boolean) value;
	}
	
	/**
	 * If true, you can select every option with a single click. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIncludeSelectAllOption(boolean _includeSelectAllOption) {
	    getStateHelper().put(PropertyKeys.includeSelectAllOption, _includeSelectAllOption);
    }
	

	/**
	 * The text displayed for the select all option. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSelectAllText() {
		String value = (String)getStateHelper().eval(PropertyKeys.selectAllText);
		return  value;
	}
	
	/**
	 * The text displayed for the select all option. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectAllText(String _selectAllText) {
	    getStateHelper().put(PropertyKeys.selectAllText, _selectAllText);
    }
	

	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableFiltering() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.enableFiltering, false);
		return (boolean) value;
	}
	
	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableFiltering(boolean _enableFiltering) {
	    getStateHelper().put(PropertyKeys.enableFiltering, _enableFiltering);
    }
	

	/**
	 * The placeholder used for the filter input. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFilterPlaceholder() {
		String value = (String)getStateHelper().eval(PropertyKeys.filterPlaceholder);
		return  value;
	}
	
	/**
	 * The placeholder used for the filter input. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFilterPlaceholder(String _filterPlaceholder) {
	    getStateHelper().put(PropertyKeys.filterPlaceholder, _filterPlaceholder);
    }
	

	/**
	 * Set to true to display radiobuttons instead of checkboxes. Of course, in this case you can only select one option, so the widget's name is sort of a misnomer. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRadiobuttons() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.radiobuttons, false);
		return (boolean) value;
	}
	
	/**
	 * Set to true to display radiobuttons instead of checkboxes. Of course, in this case you can only select one option, so the widget's name is sort of a misnomer. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRadiobuttons(boolean _radiobuttons) {
	    getStateHelper().put(PropertyKeys.radiobuttons, _radiobuttons);
    }
	

	/**
	 * If true, the button is disabled if no options are given. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisableIfEmpty() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.disableIfEmpty, false);
		return (boolean) value;
	}
	
	/**
	 * If true, the button is disabled if no options are given. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisableIfEmpty(boolean _disableIfEmpty) {
	    getStateHelper().put(PropertyKeys.disableIfEmpty, _disableIfEmpty);
    }
	

	/**
	 * Moves the drop-down-area from the left hand side to the right hand side. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDropRight() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.dropRight, false);
		return (boolean) value;
	}
	
	/**
	 * Moves the drop-down-area from the left hand side to the right hand side. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDropRight(boolean _dropRight) {
	    getStateHelper().put(PropertyKeys.dropRight, _dropRight);
    }
	

	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnchange() {
		String value = (String)getStateHelper().eval(PropertyKeys.onchange);
		return  value;
	}
	
	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnchange(String _onchange) {
	    getStateHelper().put(PropertyKeys.onchange, _onchange);
    }
	

	/**
	 * Client side callback called when the drop-down area is shown. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownshow() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondropdownshow);
		return  value;
	}
	
	/**
	 * Client side callback called when the drop-down area is shown. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownshow(String _ondropdownshow) {
	    getStateHelper().put(PropertyKeys.ondropdownshow, _ondropdownshow);
    }
	

	/**
	 * Client side callback called when the drop-down area is hidden. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownhide() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondropdownhide);
		return  value;
	}
	
	/**
	 * Client side callback called when the drop-down area is hidden. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownhide(String _ondropdownhide) {
	    getStateHelper().put(PropertyKeys.ondropdownhide, _ondropdownhide);
    }
	

	/**
	 * The CSS class of the button. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getButtonClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.buttonClass);
		return  value;
	}
	
	/**
	 * The CSS class of the button. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonClass(String _buttonClass) {
	    getStateHelper().put(PropertyKeys.buttonClass, _buttonClass);
    }
	

	/**
	 * Style class of the input element. Is translated to the buttonContainer attribute. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.styleClass);
		return  value;
	}
	
	/**
	 * Style class of the input element. Is translated to the buttonContainer attribute. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
	    getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }
	

	/**
	 * The width of the multiselect button may be fixed using this option. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getButtonWidth() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.buttonWidth, 0);
		return (int) value;
	}
	
	/**
	 * The width of the multiselect button may be fixed using this option. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonWidth(int _buttonWidth) {
	    getStateHelper().put(PropertyKeys.buttonWidth, _buttonWidth);
    }
	

	/**
	 * If set to true, the filter is case-insensitive. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableCaseInsensitiveFiltering() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.enableCaseInsensitiveFiltering, false);
		return (boolean) value;
	}
	
	/**
	 * If set to true, the filter is case-insensitive. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableCaseInsensitiveFiltering(boolean _enableCaseInsensitiveFiltering) {
	    getStateHelper().put(PropertyKeys.enableCaseInsensitiveFiltering, _enableCaseInsensitiveFiltering);
    }
	

	/**
	 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPlaceholder() {
		String value = (String)getStateHelper().eval(PropertyKeys.placeholder);
		return  value;
	}
	
	/**
	 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlaceholder(String _placeholder) {
	    getStateHelper().put(PropertyKeys.placeholder, _placeholder);
    }
	

	/**
	 * Flag indicating that this input element will prevent changes by the user. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isReadonly() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.readonly, false);
		return (boolean) value;
	}
	
	/**
	 * Flag indicating that this input element will prevent changes by the user. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setReadonly(boolean _readonly) {
	    getStateHelper().put(PropertyKeys.readonly, _readonly);
    }
	

	/**
	 * Allows you to suppress automatic rendering of labels. Used by AngularFaces, too. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRenderLabel() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.renderLabel, true);
		return (boolean) value;
	}
	
	/**
	 * Allows you to suppress automatic rendering of labels. Used by AngularFaces, too. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRenderLabel(boolean _renderLabel) {
	    getStateHelper().put(PropertyKeys.renderLabel, _renderLabel);
    }
	

	/**
	 * Boolean value Require input in the component when the form is submitted. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRequired() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.required, false);
		return (boolean) value;
	}
	
	/**
	 * Boolean value Require input in the component when the form is submitted. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequired(boolean _required) {
	    getStateHelper().put(PropertyKeys.required, _required);
    }
	

	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRequiredMessage() {
		String value = (String)getStateHelper().eval(PropertyKeys.requiredMessage);
		return  value;
	}
	
	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequiredMessage(String _requiredMessage) {
	    getStateHelper().put(PropertyKeys.requiredMessage, _requiredMessage);
    }
	

	/**
	 * Number of characters used to determine the width of the input element. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getSize() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.size, 0);
		return (int) value;
	}
	
	/**
	 * Number of characters used to determine the width of the input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSize(int _size) {
	    getStateHelper().put(PropertyKeys.size, _size);
    }
	

	/**
	 * The size of the input specified as number of grid columns. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getSpan() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.span, 0);
		return (int) value;
	}
	
	/**
	 * The size of the input specified as number of grid columns. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(int _span) {
	    getStateHelper().put(PropertyKeys.span, _span);
    }
	

	/**
	 * Inline style of the input element. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.style);
		return  value;
	}
	
	/**
	 * Inline style of the input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
	    getStateHelper().put(PropertyKeys.style, _style);
    }
	

	/**
	 * Advisory tooltip information. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTabindex() {
		String value = (String)getStateHelper().eval(PropertyKeys.tabindex);
		return  value;
	}
	
	/**
	 * Advisory tooltip information. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTabindex(String _tabindex) {
	    getStateHelper().put(PropertyKeys.tabindex, _tabindex);
    }
	

	/**
	 * Advisory tooltip information. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTitle() {
		String value = (String)getStateHelper().eval(PropertyKeys.title);
		return  value;
	}
	
	/**
	 * Advisory tooltip information. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitle(String _title) {
	    getStateHelper().put(PropertyKeys.title, _title);
    }
	

	/**
	 * The text of the tooltip. 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltip);
		return  value;
	}
	
	/**
	 * The text of the tooltip. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
	    getStateHelper().put(PropertyKeys.tooltip, _tooltip);
    }
	

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelay() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
	    getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
    }
	

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
	    getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
    }
	

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
	    getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
    }
	

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipPosition() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
		return  value;
	}
	
	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
	    getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
    }
}
