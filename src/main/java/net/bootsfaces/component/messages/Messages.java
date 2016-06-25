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

package net.bootsfaces.component.messages;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/**
 * 
 * <p>
 * By default, the <code>rendererType</code> property must be set to
 * "<code>javax.faces.Messages</code>". This value can be changed by calling the
 * <code>setRendererType()</code> method.
 * </p>
 */
@FacesComponent("net.bootsfaces.component.messages.Messages")
public class Messages extends javax.faces.component.UIMessages {

	public Messages() {
		super();
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("alerts.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/alert.js");
		setRendererType("net.bootsfaces.component.messages.MessagesRenderer");
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	protected enum PropertyKeys {
		dir,
		errorClass,
		errorStyle,
		escape,
		fatalClass,
		fatalStyle,
		_for,
		globalOnly,
		infoClass,
		infoStyle,
		lineBreak,
		lineBreakTag,
		recursive,
		showDetail,
		showIcon,
		showSummary,
		style,
		styleClass,
		tooltip,
		tooltipContainer,
		tooltipDelay,
		tooltipDelayHide,
		tooltipDelayShow,
		tooltipPosition,
		warnClass,
		warnStyle;
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
	 * HTML: The direction of text display, either 'ltr' (left-to-right) or 'rtl' (right-to-left). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDir() {
		return (String) getStateHelper().eval(PropertyKeys.dir);
	}

	/**
	 * HTML: The direction of text display, either 'ltr' (left-to-right) or 'rtl' (right-to-left). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
		getStateHelper().put(PropertyKeys.dir, _dir);
	}

	/**
	 * CSS class to be used for messages with severity "ERROR". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getErrorClass() {
		return (String) getStateHelper().eval(PropertyKeys.errorClass);
	}

	/**
	 * CSS class to be used for messages with severity "ERROR". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setErrorClass(String _errorClass) {
		getStateHelper().put(PropertyKeys.errorClass, _errorClass);
	}

	/**
	 * CSS style to be used for messages with severity "ERROR". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getErrorStyle() {
		return (String) getStateHelper().eval(PropertyKeys.errorStyle);
	}

	/**
	 * CSS style to be used for messages with severity "ERROR". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setErrorStyle(String _errorStyle) {
		getStateHelper().put(PropertyKeys.errorStyle, _errorStyle);
	}

	/**
	 * By default, error messages encode HTML and JavaScript code. Instead of being executed, the source code is displayed. This protects you against hacker attacks. By setting escape=false, you deactivate the protection, and allow HTML and JavaScript code to be rendered. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isEscape() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.escape, true);
	}

	/**
	 * By default, error messages encode HTML and JavaScript code. Instead of being executed, the source code is displayed. This protects you against hacker attacks. By setting escape=false, you deactivate the protection, and allow HTML and JavaScript code to be rendered. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEscape(boolean _escape) {
		getStateHelper().put(PropertyKeys.escape, _escape);
	}

	/**
	 * CSS class to be used for messages with severity "FATAL". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFatalClass() {
		return (String) getStateHelper().eval(PropertyKeys.fatalClass);
	}

	/**
	 * CSS class to be used for messages with severity "FATAL". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFatalClass(String _fatalClass) {
		getStateHelper().put(PropertyKeys.fatalClass, _fatalClass);
	}

	/**
	 * CSS style to be used for messages with severity "FATAL". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFatalStyle() {
		return (String) getStateHelper().eval(PropertyKeys.fatalStyle);
	}

	/**
	 * CSS style to be used for messages with severity "FATAL". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFatalStyle(String _fatalStyle) {
		getStateHelper().put(PropertyKeys.fatalStyle, _fatalStyle);
	}

	/**
	 * Optional id or search expression. Only the messages of the component defined by the id and its children are shown. By default, the algorithm checks the ids, which means the the 'for' component has to be a NamingContainer. Note that this algorithm fails if you use the prependId='false'. Alternatively, you can activate a slower but more flexible algorithm by setting 'recursive' to true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFor() {
		return (String) getStateHelper().eval(PropertyKeys._for);
	}

	/**
	 * Optional id or search expression. Only the messages of the component defined by the id and its children are shown. By default, the algorithm checks the ids, which means the the 'for' component has to be a NamingContainer. Note that this algorithm fails if you use the prependId='false'. Alternatively, you can activate a slower but more flexible algorithm by setting 'recursive' to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFor(String _for) {
		getStateHelper().put(PropertyKeys._for, _for);
	}

	/**
	 * Specifies whether only messages (FacesMessage objects) not associated with a specific component should be displayed, ie whether messages should be ignored if they are attached to a particular component. Defaults to false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isGlobalOnly() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.globalOnly, false);
	}

	/**
	 * Specifies whether only messages (FacesMessage objects) not associated with a specific component should be displayed, ie whether messages should be ignored if they are attached to a particular component. Defaults to false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setGlobalOnly(boolean _globalOnly) {
		getStateHelper().put(PropertyKeys.globalOnly, _globalOnly);
	}

	/**
	 * CSS class to be used for messages with severity "INFO". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getInfoClass() {
		return (String) getStateHelper().eval(PropertyKeys.infoClass);
	}

	/**
	 * CSS class to be used for messages with severity "INFO". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInfoClass(String _infoClass) {
		getStateHelper().put(PropertyKeys.infoClass, _infoClass);
	}

	/**
	 * CSS style to be used for messages with severity "INFO". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getInfoStyle() {
		return (String) getStateHelper().eval(PropertyKeys.infoStyle);
	}

	/**
	 * CSS style to be used for messages with severity "INFO". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInfoStyle(String _infoStyle) {
		getStateHelper().put(PropertyKeys.infoStyle, _infoStyle);
	}

	/**
	 * If there's more than one message, they can optionally be separated by a line break. By default, the separator is a br tag. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isLineBreak() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.lineBreak, true);
	}

	/**
	 * If there's more than one message, they can optionally be separated by a line break. By default, the separator is a br tag. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLineBreak(boolean _lineBreak) {
		getStateHelper().put(PropertyKeys.lineBreak, _lineBreak);
	}

	/**
	 * If there's more than one message, they can optionally be separated by a line break. By default, the separator is a br tag. You can replace if with an arbitrary HTML snippet. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLineBreakTag() {
		return (String) getStateHelper().eval(PropertyKeys.lineBreakTag, "<br />");
	}

	/**
	 * If there's more than one message, they can optionally be separated by a line break. By default, the separator is a br tag. You can replace if with an arbitrary HTML snippet. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLineBreakTag(String _lineBreakTag) {
		getStateHelper().put(PropertyKeys.lineBreakTag, _lineBreakTag);
	}

	/**
	 * If set to true, b:messages checks the for attributes using a slower algorithm that doesn't require you to put the component into a namingcontainer. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRecursive() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.recursive, false);
	}

	/**
	 * If set to true, b:messages checks the for attributes using a slower algorithm that doesn't require you to put the component into a namingcontainer. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRecursive(boolean _recursive) {
		getStateHelper().put(PropertyKeys.recursive, _recursive);
	}

	/**
	 * Specifies whether the detailed information from the message should be shown. Default to true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowDetail() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showDetail, true);
	}

	/**
	 * Specifies whether the detailed information from the message should be shown. Default to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowDetail(boolean _showDetail) {
		getStateHelper().put(PropertyKeys.showDetail, _showDetail);
	}

	/**
	 * If false, the icon of the message is not show. Default = true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowIcon() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showIcon, true);
	}

	/**
	 * If false, the icon of the message is not show. Default = true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowIcon(boolean _showIcon) {
		getStateHelper().put(PropertyKeys.showIcon, _showIcon);
	}

	/**
	 * Specifies whether the summary information from the message should be shown. Defaults to true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowSummary() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.showSummary, true);
	}

	/**
	 * Specifies whether the summary information from the message should be shown. Defaults to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowSummary(boolean _showSummary) {
		getStateHelper().put(PropertyKeys.showSummary, _showSummary);
	}

	/**
	 * HTML: CSS styling instructions. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * HTML: CSS styling instructions. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * The CSS class for this element.  Corresponds to the HTML 'class' attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * The CSS class for this element.  Corresponds to the HTML 'class' attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * If true, the message summary will be rendered as a tooltip (i.e. HTML title attribute). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		return (String) getStateHelper().eval(PropertyKeys.tooltip);
	}

	/**
	 * If true, the message summary will be rendered as a tooltip (i.e. HTML title attribute). <P>
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

	/**
	 * CSS class to be used for messages with severity "WARN". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWarnClass() {
		return (String) getStateHelper().eval(PropertyKeys.warnClass);
	}

	/**
	 * CSS class to be used for messages with severity "WARN". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWarnClass(String _warnClass) {
		getStateHelper().put(PropertyKeys.warnClass, _warnClass);
	}

	/**
	 * CSS style to be used for messages with severity "WARN". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWarnStyle() {
		return (String) getStateHelper().eval(PropertyKeys.warnStyle);
	}

	/**
	 * CSS style to be used for messages with severity "WARN". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWarnStyle(String _warnStyle) {
		getStateHelper().put(PropertyKeys.warnStyle, _warnStyle);
	}

}
