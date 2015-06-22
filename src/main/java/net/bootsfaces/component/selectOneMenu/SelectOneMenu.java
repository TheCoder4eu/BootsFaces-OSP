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

package net.bootsfaces.component.selectOneMenu;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.render.Tooltip;

/** This class holds the attributes of &lt;b:selectOneMenu /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head") })
@FacesComponent("net.bootsfaces.component.selectOneMenu.SelectOneMenu")
public class SelectOneMenu extends HtmlInputText implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.selectOneMenu.SelectOneMenu";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.selectOneMenu.SelectOneMenu";

	public SelectOneMenu() {

		Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		accesskey, alt, binding, dir, disabled, fieldSize, id, immediate, label, lang, onblur, onchange, onclick, ondblclick, onfocus, onkeydown, onkeypress, onkeyup, onmousedown, onmousemove, onmouseout, onmouseover, onmouseup, onselect, placeholder, readonly, renderLabel, required, requiredMessage, size, span, style, styleClass, tabindex, title, tooltip, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition;

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
	 * Access key to transfer focus to the input element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getAccesskey() {
		String value = (String) getStateHelper().eval(PropertyKeys.accesskey);
		return value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getAlt() {
		String value = (String) getStateHelper().eval(PropertyKeys.alt);
		return value;
	}

	/**
	 * Alternate textual description of the input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAlt(String _alt) {
		getStateHelper().put(PropertyKeys.alt, _alt);
	}

	/**
	 * An el expression referring to a server side UIComponent instance in a
	 * backing bean. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		javax.faces.component.UIComponent value = (javax.faces.component.UIComponent) getStateHelper()
				.eval(PropertyKeys.binding);
		return value;
	}

	/**
	 * An el expression referring to a server side UIComponent instance in a
	 * backing bean. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
		getStateHelper().put(PropertyKeys.binding, _binding);
	}

	/**
	 * Direction indication for text that does not inherit directionality.
	 * 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getDir() {
		String value = (String) getStateHelper().eval(PropertyKeys.dir);
		return value;
	}

	/**
	 * Direction indication for text that does not inherit directionality.
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
		getStateHelper().put(PropertyKeys.dir, _dir);
	}

	/**
	 * Disables the input element, default is false. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isDisabled() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
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
	 * The size of the input. Possible values are xs (extra small), sm (small),
	 * md (medium) and lg (large) . 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getFieldSize() {
		String value = (String) getStateHelper().eval(PropertyKeys.fieldSize);
		return value;
	}

	/**
	 * The size of the input. Possible values are xs (extra small), sm (small),
	 * md (medium) and lg (large) . 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFieldSize(String _fieldSize) {
		getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
	}

	/**
	 * Unique identifier of the component in a namingContainer. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getId() {
		String value = (String) getStateHelper().eval(PropertyKeys.id);
		return value;
	}

	/**
	 * Unique identifier of the component in a namingContainer. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setId(String _id) {
		getStateHelper().put(PropertyKeys.id, _id);
	}

	/**
	 * Flag indicating that, if this component is activated by the user,
	 * notifications should be delivered to interested listeners and actions
	 * immediately (that is, during Apply Request Values phase) rather than
	 * waiting until Invoke Application phase. Default is false. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isImmediate() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.immediate, false);
		return (boolean) value;
	}

	/**
	 * Flag indicating that, if this component is activated by the user,
	 * notifications should be delivered to interested listeners and actions
	 * immediately (that is, during Apply Request Values phase) rather than
	 * waiting until Invoke Application phase. Default is false. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
		getStateHelper().put(PropertyKeys.immediate, _immediate);
	}

	/**
	 * The label of the field. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getLabel() {
		String value = (String) getStateHelper().eval(PropertyKeys.label);
		return value;
	}

	/**
	 * The label of the field. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
		getStateHelper().put(PropertyKeys.label, _label);
	}

	/**
	 * A localized user presentable name. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getLang() {
		String value = (String) getStateHelper().eval(PropertyKeys.lang);
		return value;
	}

	/**
	 * A localized user presentable name. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLang(String _lang) {
		getStateHelper().put(PropertyKeys.lang, _lang);
	}

	/**
	 * Client side callback to execute when input element loses focus. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnblur() {
		String value = (String) getStateHelper().eval(PropertyKeys.onblur);
		return value;
	}

	/**
	 * Client side callback to execute when input element loses focus. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnblur(String _onblur) {
		getStateHelper().put(PropertyKeys.onblur, _onblur);
	}

	/**
	 * Client side callback to execute when input element loses focus and its
	 * value has been modified since gaining focus. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnchange() {
		String value = (String) getStateHelper().eval(PropertyKeys.onchange);
		return value;
	}

	/**
	 * Client side callback to execute when input element loses focus and its
	 * value has been modified since gaining focus. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnchange(String _onchange) {
		getStateHelper().put(PropertyKeys.onchange, _onchange);
	}

	/**
	 * OnClick DHTML event . 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnclick() {
		String value = (String) getStateHelper().eval(PropertyKeys.onclick);
		return value;
	}

	/**
	 * OnClick DHTML event . 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
		getStateHelper().put(PropertyKeys.onclick, _onclick);
	}

	/**
	 * Client side callback to execute when input element is double clicked.
	 * 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOndblclick() {
		String value = (String) getStateHelper().eval(PropertyKeys.ondblclick);
		return value;
	}

	/**
	 * Client side callback to execute when input element is double clicked.
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndblclick(String _ondblclick) {
		getStateHelper().put(PropertyKeys.ondblclick, _ondblclick);
	}

	/**
	 * Client side callback to execute when input element receives focus. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnfocus() {
		String value = (String) getStateHelper().eval(PropertyKeys.onfocus);
		return value;
	}

	/**
	 * Client side callback to execute when input element receives focus. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnfocus(String _onfocus) {
		getStateHelper().put(PropertyKeys.onfocus, _onfocus);
	}

	/**
	 * Client side callback to execute when a key is pressed down over input
	 * element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnkeydown() {
		String value = (String) getStateHelper().eval(PropertyKeys.onkeydown);
		return value;
	}

	/**
	 * Client side callback to execute when a key is pressed down over input
	 * element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeydown(String _onkeydown) {
		getStateHelper().put(PropertyKeys.onkeydown, _onkeydown);
	}

	/**
	 * Client side callback to execute when a key is pressed and released over
	 * input element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnkeypress() {
		String value = (String) getStateHelper().eval(PropertyKeys.onkeypress);
		return value;
	}

	/**
	 * Client side callback to execute when a key is pressed and released over
	 * input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeypress(String _onkeypress) {
		getStateHelper().put(PropertyKeys.onkeypress, _onkeypress);
	}

	/**
	 * Client side callback to execute when a key is released over input
	 * element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnkeyup() {
		String value = (String) getStateHelper().eval(PropertyKeys.onkeyup);
		return value;
	}

	/**
	 * Client side callback to execute when a key is released over input
	 * element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnkeyup(String _onkeyup) {
		getStateHelper().put(PropertyKeys.onkeyup, _onkeyup);
	}

	/**
	 * Client side callback to execute when a pointer input element is pressed
	 * down over input element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmousedown() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmousedown);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is pressed
	 * down over input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousedown(String _onmousedown) {
		getStateHelper().put(PropertyKeys.onmousedown, _onmousedown);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * within input element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmousemove() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmousemove);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * within input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousemove(String _onmousemove) {
		getStateHelper().put(PropertyKeys.onmousemove, _onmousemove);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * away from input element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmouseout() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmouseout);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * away from input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseout(String _onmouseout) {
		getStateHelper().put(PropertyKeys.onmouseout, _onmouseout);
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * onto input element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmouseover() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmouseover);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * onto input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseover(String _onmouseover) {
		getStateHelper().put(PropertyKeys.onmouseover, _onmouseover);
	}

	/**
	 * Client side callback to execute when a pointer input element is released
	 * over input element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnmouseup() {
		String value = (String) getStateHelper().eval(PropertyKeys.onmouseup);
		return value;
	}

	/**
	 * Client side callback to execute when a pointer input element is released
	 * over input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseup(String _onmouseup) {
		getStateHelper().put(PropertyKeys.onmouseup, _onmouseup);
	}

	/**
	 * Client side callback to execute when text within input element is
	 * selected by user. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnselect() {
		String value = (String) getStateHelper().eval(PropertyKeys.onselect);
		return value;
	}

	/**
	 * Client side callback to execute when text within input element is
	 * selected by user. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnselect(String _onselect) {
		getStateHelper().put(PropertyKeys.onselect, _onselect);
	}

	/**
	 * The placeholder attribute shows text in a field until the field is
	 * focused upon, then hides the text. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getPlaceholder() {
		String value = (String) getStateHelper().eval(PropertyKeys.placeholder);
		return value;
	}

	/**
	 * The placeholder attribute shows text in a field until the field is
	 * focused upon, then hides the text. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPlaceholder(String _placeholder) {
		getStateHelper().put(PropertyKeys.placeholder, _placeholder);
	}

	/**
	 * Flag indicating that this input element will prevent changes by the user.
	 * 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isReadonly() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.readonly, false);
		return (boolean) value;
	}

	/**
	 * Flag indicating that this input element will prevent changes by the user.
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setReadonly(boolean _readonly) {
		getStateHelper().put(PropertyKeys.readonly, _readonly);
	}

	/**
	 * Allows you to suppress automatic rendering of labels. Used by
	 * AngularFaces, too. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isRenderLabel() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.renderLabel, true);
		return (boolean) value;
	}

	/**
	 * Allows you to suppress automatic rendering of labels. Used by
	 * AngularFaces, too. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRenderLabel(boolean _renderLabel) {
		getStateHelper().put(PropertyKeys.renderLabel, _renderLabel);
	}

	/**
	 * Boolean value Require input in the component when the form is submitted.
	 * 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isRequired() {
		Boolean value = (Boolean) getStateHelper().eval(PropertyKeys.required, false);
		return (boolean) value;
	}

	/**
	 * Boolean value Require input in the component when the form is submitted.
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequired(boolean _required) {
		getStateHelper().put(PropertyKeys.required, _required);
	}

	/**
	 * Message to show if the user did not specify a value and the attribute
	 * required is set to true. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getRequiredMessage() {
		String value = (String) getStateHelper().eval(PropertyKeys.requiredMessage);
		return value;
	}

	/**
	 * Message to show if the user did not specify a value and the attribute
	 * required is set to true. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequiredMessage(String _requiredMessage) {
		getStateHelper().put(PropertyKeys.requiredMessage, _requiredMessage);
	}

	/**
	 * Number of characters used to determine the width of the input element.
	 * 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getSize() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.size, 0);
		return (int) value;
	}

	/**
	 * Number of characters used to determine the width of the input element.
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSize(int _size) {
		getStateHelper().put(PropertyKeys.size, _size);
	}

	/**
	 * The size of the input specified as number of grid columns. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getSpan() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.span, 0);
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyle() {
		String value = (String) getStateHelper().eval(PropertyKeys.style);
		return value;
	}

	/**
	 * Inline style of the input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of the input element. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String) getStateHelper().eval(PropertyKeys.styleClass);
		return value;
	}

	/**
	 * Style class of the input element. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Advisory tooltip information. 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTabindex() {
		String value = (String) getStateHelper().eval(PropertyKeys.tabindex);
		return value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTitle() {
		String value = (String) getStateHelper().eval(PropertyKeys.title);
		return value;
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
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String) getStateHelper().eval(PropertyKeys.tooltip);
		return value;
	}

	/**
	 * The text of the tooltip. 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, _tooltip);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelay() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
		return (int) value;
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
		getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
		return (int) value;
	}

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
		getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		Integer value = (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
		return (int) value;
	}

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay). 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
		getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom". 
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTooltipPosition() {
		String value = (String) getStateHelper().eval(PropertyKeys.tooltipPosition);
		return value;
	}

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom". 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
		getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
	}

}
