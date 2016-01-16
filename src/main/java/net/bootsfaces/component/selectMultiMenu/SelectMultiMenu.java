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

import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.render.Tooltip;

/** This class holds the attributes of &lt;b:selectMultiMenu /&gt;. */
@ResourceDependencies({ 
	    @ResourceDependency(library = "bsf", name = "js/bootstrap-multiselect.js", target = "head"),
	    @ResourceDependency(library = "bsf", name = "css/dropdowns.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/bootstrap-multiselect.css", target = "head"),
		@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/dropdown.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")})

@FacesComponent("net.bootsfaces.component.selectMultiMenu.SelectMultiMenu")
public class SelectMultiMenu extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {
	
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.selectMultiMenu.SelectMultiMenu";
	
	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";
	
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.selectMultiMenu.SelectMultiMenu";

	private Map<String, Object> attributes;
	
	public SelectMultiMenu() {
		
		
	Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	
    protected enum PropertyKeys {
accesskey,
alt,
binding,
buttonContainer,
dir,
disabled,
fieldSize,
immediate,
inline,
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
tooltipContainer,
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
	 * Access key to transfer focus to the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAccesskey() {
		String value = (String)getStateHelper().eval(PropertyKeys.accesskey);
		return  value;
	}
	
	/**
	 * Access key to transfer focus to the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAccesskey(String _accesskey) {
	    getStateHelper().put(PropertyKeys.accesskey, _accesskey);
    }
	

	/**
	 * Alternate textual description of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAlt() {
		String value = (String)getStateHelper().eval(PropertyKeys.alt);
		return  value;
	}
	
	/**
	 * Alternate textual description of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAlt(String _alt) {
	    getStateHelper().put(PropertyKeys.alt, _alt);
    }
	

	/**
	 * An el expression referring to a server side UIComponent instance in a backing bean. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		javax.faces.component.UIComponent value = (javax.faces.component.UIComponent)getStateHelper().eval(PropertyKeys.binding);
		return  value;
	}
	
	/**
	 * An el expression referring to a server side UIComponent instance in a backing bean. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
	    getStateHelper().put(PropertyKeys.binding, _binding);
    }
	

	/**
	 * HTML snippet of the container holding both the button as well as the dropdown. Default: <div class='btn-group' style='display:block'></div>. Note that the original definition of the widget doesn't use the style definition. We've added it to fix a rendering bug. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getButtonContainer() {
		String value = (String)getStateHelper().eval(PropertyKeys.buttonContainer);
		return  value;
	}
	
	/**
	 * HTML snippet of the container holding both the button as well as the dropdown. Default: <div class='btn-group' style='display:block'></div>. Note that the original definition of the widget doesn't use the style definition. We've added it to fix a rendering bug. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonContainer(String _buttonContainer) {
	    getStateHelper().put(PropertyKeys.buttonContainer, _buttonContainer);
    }
	

	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphebet, based on the page content). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDir() {
		String value = (String)getStateHelper().eval(PropertyKeys.dir);
		return  value;
	}
	
	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphebet, based on the page content). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
	    getStateHelper().put(PropertyKeys.dir, _dir);
    }
	

	/**
	 * Disables the input element, default is false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.disabled, false);
		return (boolean) value;
	}
	
	/**
	 * Disables the input element, default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
	    getStateHelper().put(PropertyKeys.disabled, _disabled);
    }
	

	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFieldSize() {
		String value = (String)getStateHelper().eval(PropertyKeys.fieldSize);
		return  value;
	}
	
	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFieldSize(String _fieldSize) {
	    getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
    }
	

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isImmediate() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.immediate, false);
		return (boolean) value;
	}
	
	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
	    getStateHelper().put(PropertyKeys.immediate, _immediate);
    }
	

	/**
	 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isInline() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.inline, false);
		return (boolean) value;
	}
	
	/**
	 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInline(boolean _inline) {
	    getStateHelper().put(PropertyKeys.inline, _inline);
    }
	

	/**
	 * The Label of the field . <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		String value = (String)getStateHelper().eval(PropertyKeys.label);
		return  value;
	}
	
	/**
	 * The Label of the field . <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
	    getStateHelper().put(PropertyKeys.label, _label);
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
	 * Maximum height of the options panel. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getMaxHeight() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.maxHeight, 0);
		return (int) value;
	}
	
	/**
	 * Maximum height of the options panel. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxHeight(int _maxHeight) {
	    getStateHelper().put(PropertyKeys.maxHeight, _maxHeight);
    }
	

	/**
	 * Text which is displayed if no option has been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNonSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.nonSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if no option has been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNonSelectedText(String _nonSelectedText) {
	    getStateHelper().put(PropertyKeys.nonSelectedText, _nonSelectedText);
    }
	

	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.nSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNSelectedText(String _nSelectedText) {
	    getStateHelper().put(PropertyKeys.nSelectedText, _nSelectedText);
    }
	

	/**
	 * Text which is displayed if every option has been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAllSelectedText() {
		String value = (String)getStateHelper().eval(PropertyKeys.allSelectedText);
		return  value;
	}
	
	/**
	 * Text which is displayed if every option has been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllSelectedText(String _allSelectedText) {
	    getStateHelper().put(PropertyKeys.allSelectedText, _allSelectedText);
    }
	

	/**
	 * Maximum number of options displayed in the button. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getNumberDisplayed() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.numberDisplayed, 0);
		return (int) value;
	}
	
	/**
	 * Maximum number of options displayed in the button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNumberDisplayed(int _numberDisplayed) {
	    getStateHelper().put(PropertyKeys.numberDisplayed, _numberDisplayed);
    }
	

	/**
	 * If true, you can select every option with a single click. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isIncludeSelectAllOption() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.includeSelectAllOption, false);
		return (boolean) value;
	}
	
	/**
	 * If true, you can select every option with a single click. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIncludeSelectAllOption(boolean _includeSelectAllOption) {
	    getStateHelper().put(PropertyKeys.includeSelectAllOption, _includeSelectAllOption);
    }
	

	/**
	 * The text displayed for the select all option. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSelectAllText() {
		String value = (String)getStateHelper().eval(PropertyKeys.selectAllText);
		return  value;
	}
	
	/**
	 * The text displayed for the select all option. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectAllText(String _selectAllText) {
	    getStateHelper().put(PropertyKeys.selectAllText, _selectAllText);
    }
	

	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableFiltering() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.enableFiltering, false);
		return (boolean) value;
	}
	
	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableFiltering(boolean _enableFiltering) {
	    getStateHelper().put(PropertyKeys.enableFiltering, _enableFiltering);
    }
	

	/**
	 * The placeholder used for the filter input. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFilterPlaceholder() {
		String value = (String)getStateHelper().eval(PropertyKeys.filterPlaceholder);
		return  value;
	}
	
	/**
	 * The placeholder used for the filter input. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFilterPlaceholder(String _filterPlaceholder) {
	    getStateHelper().put(PropertyKeys.filterPlaceholder, _filterPlaceholder);
    }
	

	/**
	 * Set to true to display radiobuttons instead of checkboxes. Of course, in this case you can only select one option, so the widget's name is sort of a misnomer. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRadiobuttons() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.radiobuttons, false);
		return (boolean) value;
	}
	
	/**
	 * Set to true to display radiobuttons instead of checkboxes. Of course, in this case you can only select one option, so the widget's name is sort of a misnomer. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRadiobuttons(boolean _radiobuttons) {
	    getStateHelper().put(PropertyKeys.radiobuttons, _radiobuttons);
    }
	

	/**
	 * If true, the button is disabled if no options are given. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisableIfEmpty() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.disableIfEmpty, false);
		return (boolean) value;
	}
	
	/**
	 * If true, the button is disabled if no options are given. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisableIfEmpty(boolean _disableIfEmpty) {
	    getStateHelper().put(PropertyKeys.disableIfEmpty, _disableIfEmpty);
    }
	

	/**
	 * Moves the drop-down-area from the left hand side to the right hand side. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDropRight() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.dropRight, false);
		return (boolean) value;
	}
	
	/**
	 * Moves the drop-down-area from the left hand side to the right hand side. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDropRight(boolean _dropRight) {
	    getStateHelper().put(PropertyKeys.dropRight, _dropRight);
    }
	

	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnchange() {
		String value = (String)getStateHelper().eval(PropertyKeys.onchange);
		return  value;
	}
	
	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnchange(String _onchange) {
	    getStateHelper().put(PropertyKeys.onchange, _onchange);
    }
	

	/**
	 * Client side callback called when the drop-down area is shown. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownshow() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondropdownshow);
		return  value;
	}
	
	/**
	 * Client side callback called when the drop-down area is shown. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownshow(String _ondropdownshow) {
	    getStateHelper().put(PropertyKeys.ondropdownshow, _ondropdownshow);
    }
	

	/**
	 * Client side callback called when the drop-down area is hidden. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownhide() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondropdownhide);
		return  value;
	}
	
	/**
	 * Client side callback called when the drop-down area is hidden. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownhide(String _ondropdownhide) {
	    getStateHelper().put(PropertyKeys.ondropdownhide, _ondropdownhide);
    }
	

	/**
	 * The CSS class of the button. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getButtonClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.buttonClass);
		return  value;
	}
	
	/**
	 * The CSS class of the button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonClass(String _buttonClass) {
	    getStateHelper().put(PropertyKeys.buttonClass, _buttonClass);
    }
	

	/**
	 * Style class of the input element. Is translated to the buttonContainer attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.styleClass);
		return  value;
	}
	
	/**
	 * Style class of the input element. Is translated to the buttonContainer attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
	    getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }
	

	/**
	 * The width of the multiselect button may be fixed using this option. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getButtonWidth() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.buttonWidth, 0);
		return (int) value;
	}
	
	/**
	 * The width of the multiselect button may be fixed using this option. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonWidth(int _buttonWidth) {
	    getStateHelper().put(PropertyKeys.buttonWidth, _buttonWidth);
    }
	

	/**
	 * If set to true, the filter is case-insensitive. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableCaseInsensitiveFiltering() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.enableCaseInsensitiveFiltering, false);
		return (boolean) value;
	}
	
	/**
	 * If set to true, the filter is case-insensitive. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableCaseInsensitiveFiltering(boolean _enableCaseInsensitiveFiltering) {
	    getStateHelper().put(PropertyKeys.enableCaseInsensitiveFiltering, _enableCaseInsensitiveFiltering);
    }
	

	/**
	 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPlaceholder() {
		String value = (String)getStateHelper().eval(PropertyKeys.placeholder);
		return  value;
	}
	
	/**
	 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlaceholder(String _placeholder) {
	    getStateHelper().put(PropertyKeys.placeholder, _placeholder);
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
	 * Allows you to suppress automatic rendering of labels. Used by AngularFaces, too. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRenderLabel() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.renderLabel, true);
		return (boolean) value;
	}
	
	/**
	 * Allows you to suppress automatic rendering of labels. Used by AngularFaces, too. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRenderLabel(boolean _renderLabel) {
	    getStateHelper().put(PropertyKeys.renderLabel, _renderLabel);
    }
	

	/**
	 * Boolean value Require input in the component when the form is submitted. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRequired() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.required, false);
		return (boolean) value;
	}
	
	/**
	 * Boolean value Require input in the component when the form is submitted. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequired(boolean _required) {
	    getStateHelper().put(PropertyKeys.required, _required);
    }
	

	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRequiredMessage() {
		String value = (String)getStateHelper().eval(PropertyKeys.requiredMessage);
		return  value;
	}
	
	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequiredMessage(String _requiredMessage) {
	    getStateHelper().put(PropertyKeys.requiredMessage, _requiredMessage);
    }
	

	/**
	 * Number of characters used to determine the width of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getSize() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.size, 0);
		return (int) value;
	}
	
	/**
	 * Number of characters used to determine the width of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSize(int _size) {
	    getStateHelper().put(PropertyKeys.size, _size);
    }
	

	/**
	 * The size of the input specified as number of grid columns. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getSpan() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.span, 0);
		return (int) value;
	}
	
	/**
	 * The size of the input specified as number of grid columns. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(int _span) {
	    getStateHelper().put(PropertyKeys.span, _span);
    }
	

	/**
	 * Inline style of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.style);
		return  value;
	}
	
	/**
	 * Inline style of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
	    getStateHelper().put(PropertyKeys.style, _style);
    }
	

	/**
	 * Position of this element in the tabbing order for the current document.  This value must be an integer between 0 and 32767. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTabindex() {
		String value = (String)getStateHelper().eval(PropertyKeys.tabindex);
		return  value;
	}
	
	/**
	 * Position of this element in the tabbing order for the current document.  This value must be an integer between 0 and 32767. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTabindex(String _tabindex) {
	    getStateHelper().put(PropertyKeys.tabindex, _tabindex);
    }
	

	/**
	 * Advisory tooltip information. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTitle() {
		String value = (String)getStateHelper().eval(PropertyKeys.title);
		return  value;
	}
	
	/**
	 * Advisory tooltip information. <P>
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
	
}

