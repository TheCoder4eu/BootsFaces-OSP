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

package net.bootsfaces.component.selectMultiMenu;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:selectMultiMenu /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/bootstrap-multiselect.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/dropdown.js", target = "body"), })

@FacesComponent("net.bootsfaces.component.selectMultiMenu.SelectMultiMenu")
public class SelectMultiMenu extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {
	private String renderLabel = null;

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.selectMultiMenu.SelectMultiMenu";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.selectMultiMenu.SelectMultiMenu";

	public SelectMultiMenu() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("dropdowns.css");
		AddResourcesListener.addThemedCSSResource("bootstrap-multiselect.css");
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
		accesskey,
		allSelectedText,
		alt,
		binding,
		buttonContainer,
		buttonWidth,
		buttonClass,
		dir,
		disableIfEmpty,
		disabled,
		dropRight,
		enableCaseInsensitiveFiltering,
		enableFiltering,
		fieldSize,
		filterPlaceholder,
		immediate,
		includeSelectAllOption,
		inline,
		label,
		lang,
		maxHeight,
		nSelectedText,
		nonSelectedText,
		numberDisplayed,
		onchange,
		ondropdownhide,
		ondropdownshow,
		placeholder,
		radiobuttons,
		readonly,
		renderLabel,
		required,
		requiredMessage,
		selectAllText,
		size,
		span,
		style,
		styleClass,
		tabindex,
		title,
		tooltip,
		tooltipContainer,
		tooltipDelay,
		tooltipDelayHide,
		tooltipDelayShow,
		tooltipPosition;
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

	/**
	 * Access key to transfer focus to the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAccesskey() {
		return (String) getStateHelper().eval(PropertyKeys.accesskey);
	}

	/**
	 * Access key to transfer focus to the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAccesskey(String _accesskey) {
		getStateHelper().put(PropertyKeys.accesskey, _accesskey);
	}

	/**
	 * Text which is displayed if every option has been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAllSelectedText() {
		return (String) getStateHelper().eval(PropertyKeys.allSelectedText);
	}

	/**
	 * Text which is displayed if every option has been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAllSelectedText(String _allSelectedText) {
		getStateHelper().put(PropertyKeys.allSelectedText, _allSelectedText);
	}

	/**
	 * Alternate textual description of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAlt() {
		return (String) getStateHelper().eval(PropertyKeys.alt);
	}

	/**
	 * Alternate textual description of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAlt(String _alt) {
		getStateHelper().put(PropertyKeys.alt, _alt);
	}

	/**
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		return (javax.faces.component.UIComponent) getStateHelper().eval(PropertyKeys.binding);
	}

	/**
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
		getStateHelper().put(PropertyKeys.binding, _binding);
	}

	/**
	 * HTML snippet of the container holding both the button as well as the dropdown. Default: &lt;div class='btn-group' style='display:block' /&gt;. Note that the original definition of the widget doesn't use the style definition. We've added it to fix a rendering bug. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getButtonContainer() {
		return (String) getStateHelper().eval(PropertyKeys.buttonContainer);
	}

	/**
	 * HTML snippet of the container holding both the button as well as the dropdown. Default: &lt;div class='btn-group' style='display:block' /&gt;. Note that the original definition of the widget doesn't use the style definition. We've added it to fix a rendering bug. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonContainer(String _buttonContainer) {
		getStateHelper().put(PropertyKeys.buttonContainer, _buttonContainer);
	}

	/**
	 * The width of the multiselect button may be fixed using this option. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getButtonWidth() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.buttonWidth, 0);
	}

	/**
	 * The width of the multiselect button may be fixed using this option. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonWidth(int _buttonWidth) {
		getStateHelper().put(PropertyKeys.buttonWidth, _buttonWidth);
	}

	/**
	 * The CSS class of the button. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getButtonClass() {
		return (String) getStateHelper().eval(PropertyKeys.buttonClass);
	}

	/**
	 * The CSS class of the button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setButtonClass(String _buttonClass) {
		getStateHelper().put(PropertyKeys.buttonClass, _buttonClass);
	}

	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphabet, based on the page content). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDir() {
		return (String) getStateHelper().eval(PropertyKeys.dir);
	}

	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphabet, based on the page content). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
		getStateHelper().put(PropertyKeys.dir, _dir);
	}

	/**
	 * If true, the button is disabled if no options are given. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisableIfEmpty() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disableIfEmpty, false);
	}

	/**
	 * If true, the button is disabled if no options are given. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisableIfEmpty(boolean _disableIfEmpty) {
		getStateHelper().put(PropertyKeys.disableIfEmpty, _disableIfEmpty);
	}

	/**
	 * Disables the input element, default is false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
	}

	/**
	 * Disables the input element, default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * Moves the drop-down-area from the left hand side to the right hand side. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDropRight() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.dropRight, false);
	}

	/**
	 * Moves the drop-down-area from the left hand side to the right hand side. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDropRight(boolean _dropRight) {
		getStateHelper().put(PropertyKeys.dropRight, _dropRight);
	}

	/**
	 * If set to true, the filter is case-insensitive. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableCaseInsensitiveFiltering() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.enableCaseInsensitiveFiltering, false);
	}

	/**
	 * If set to true, the filter is case-insensitive. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableCaseInsensitiveFiltering(boolean _enableCaseInsensitiveFiltering) {
		getStateHelper().put(PropertyKeys.enableCaseInsensitiveFiltering, _enableCaseInsensitiveFiltering);
	}

	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEnableFiltering() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.enableFiltering, false);
	}

	/**
	 * Set to true or false to enable or disable the filter. A filter input will be added to dynamically filter all options. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEnableFiltering(boolean _enableFiltering) {
		getStateHelper().put(PropertyKeys.enableFiltering, _enableFiltering);
	}

	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFieldSize() {
		return (String) getStateHelper().eval(PropertyKeys.fieldSize);
	}

	/**
	 * The size of the input.Possible values are xs (extra small), sm (small), md (medium) and lg (large) . <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFieldSize(String _fieldSize) {
		getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
	}

	/**
	 * The placeholder used for the filter input. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFilterPlaceholder() {
		return (String) getStateHelper().eval(PropertyKeys.filterPlaceholder);
	}

	/**
	 * The placeholder used for the filter input. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFilterPlaceholder(String _filterPlaceholder) {
		getStateHelper().put(PropertyKeys.filterPlaceholder, _filterPlaceholder);
	}

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isImmediate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.immediate, false);
	}

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
		getStateHelper().put(PropertyKeys.immediate, _immediate);
	}

	/**
	 * If true, you can select every option with a single click. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isIncludeSelectAllOption() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.includeSelectAllOption, false);
	}

	/**
	 * If true, you can select every option with a single click. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setIncludeSelectAllOption(boolean _includeSelectAllOption) {
		getStateHelper().put(PropertyKeys.includeSelectAllOption, _includeSelectAllOption);
	}

	/**
	 * Inline forms are more compact and put the label to the left hand side of the input field instead of putting it above the input field. Inline applies only to screens that are at least 768 pixels wide. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isInline() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.inline, false);
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
		return (String) getStateHelper().eval(PropertyKeys.label);
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
		return (String) getStateHelper().eval(PropertyKeys.lang);
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
		return (int) (Integer) getStateHelper().eval(PropertyKeys.maxHeight, 0);
	}

	/**
	 * Maximum height of the options panel. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxHeight(int _maxHeight) {
		getStateHelper().put(PropertyKeys.maxHeight, _maxHeight);
	}

	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNSelectedText() {
		return (String) getStateHelper().eval(PropertyKeys.nSelectedText);
	}

	/**
	 * Text which is displayed if more than numberDisplayed options have been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNSelectedText(String _nSelectedText) {
		getStateHelper().put(PropertyKeys.nSelectedText, _nSelectedText);
	}

	/**
	 * Text which is displayed if no option has been selected. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getNonSelectedText() {
		return (String) getStateHelper().eval(PropertyKeys.nonSelectedText);
	}

	/**
	 * Text which is displayed if no option has been selected. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNonSelectedText(String _nonSelectedText) {
		getStateHelper().put(PropertyKeys.nonSelectedText, _nonSelectedText);
	}

	/**
	 * Maximum number of options displayed in the button. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getNumberDisplayed() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.numberDisplayed, 0);
	}

	/**
	 * Maximum number of options displayed in the button. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setNumberDisplayed(int _numberDisplayed) {
		getStateHelper().put(PropertyKeys.numberDisplayed, _numberDisplayed);
	}

	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnchange() {
		return (String) getStateHelper().eval(PropertyKeys.onchange);
	}

	/**
	 * Client side callback to execute when input element loses focus and its value has been modified since gaining focus. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnchange(String _onchange) {
		getStateHelper().put(PropertyKeys.onchange, _onchange);
	}

	/**
	 * Client side callback called when the drop-down area is hidden. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownhide() {
		return (String) getStateHelper().eval(PropertyKeys.ondropdownhide);
	}

	/**
	 * Client side callback called when the drop-down area is hidden. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownhide(String _ondropdownhide) {
		getStateHelper().put(PropertyKeys.ondropdownhide, _ondropdownhide);
	}

	/**
	 * Client side callback called when the drop-down area is shown. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndropdownshow() {
		return (String) getStateHelper().eval(PropertyKeys.ondropdownshow);
	}

	/**
	 * Client side callback called when the drop-down area is shown. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndropdownshow(String _ondropdownshow) {
		getStateHelper().put(PropertyKeys.ondropdownshow, _ondropdownshow);
	}

	/**
	 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPlaceholder() {
		return (String) getStateHelper().eval(PropertyKeys.placeholder);
	}

	/**
	 * The placeholder attribute shows text in a field until the field is focused upon, then hides the text. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlaceholder(String _placeholder) {
		getStateHelper().put(PropertyKeys.placeholder, _placeholder);
	}

	/**
	 * Set to true to display radiobuttons instead of checkboxes. Of course, in this case you can only select one option, so the widget's name is sort of a misnomer. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRadiobuttons() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.radiobuttons, false);
	}

	/**
	 * Set to true to display radiobuttons instead of checkboxes. Of course, in this case you can only select one option, so the widget's name is sort of a misnomer. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRadiobuttons(boolean _radiobuttons) {
		getStateHelper().put(PropertyKeys.radiobuttons, _radiobuttons);
	}

	/**
	 * Flag indicating that this input element will prevent changes by the user. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isReadonly() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.readonly, false);
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
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.renderLabel, true);
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
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.required, false);
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
		return (String) getStateHelper().eval(PropertyKeys.requiredMessage);
	}

	/**
	 * Message to show if the user did not specify a value and the attribute required is set to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequiredMessage(String _requiredMessage) {
		getStateHelper().put(PropertyKeys.requiredMessage, _requiredMessage);
	}

	/**
	 * The text displayed for the select all option. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSelectAllText() {
		return (String) getStateHelper().eval(PropertyKeys.selectAllText);
	}

	/**
	 * The text displayed for the select all option. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSelectAllText(String _selectAllText) {
		getStateHelper().put(PropertyKeys.selectAllText, _selectAllText);
	}

	/**
	 * Number of characters used to determine the width of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getSize() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.size, 0);
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
		return (int) (Integer) getStateHelper().eval(PropertyKeys.span, 0);
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
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline style of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of the input element. Is translated to the buttonContainer attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of the input element. Is translated to the buttonContainer attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Position of this element in the tabbing order for the current document.  This value must be an integer between 0 and 32767. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTabindex() {
		return (String) getStateHelper().eval(PropertyKeys.tabindex);
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
		return (String) getStateHelper().eval(PropertyKeys.title);
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
		return (String) getStateHelper().eval(PropertyKeys.tooltip);
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
		return (String) getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
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
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
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
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
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
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
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
		return (String) getStateHelper().eval(PropertyKeys.tooltipPosition);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
		getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
	}

}
