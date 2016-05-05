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

package net.bootsfaces.component.message;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIMessage;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:message /&gt;. */
@FacesComponent("net.bootsfaces.component.message.Message")
public class Message extends UIMessage {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.message.Message";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.message.Message";

	public Message() {
		setRendererType(DEFAULT_RENDERER);
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("alerts.css");
		AddResourcesListener.addThemedCSSResource("bsf.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/alert.js");
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	protected enum PropertyKeys {
		dir,
		errorClass,
		errorStyle,
		escape,
		fatalClass,
		fatalStyle,
		infoClass,
		infoStyle,
		lineBreak,
		lineBreakTag,
		redisplay,
		showDetail,
		showIcon,
		showSummary,
		style,
		styleClass,
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
	 * Flag indicating whether previously handled messages are redisplayed or not <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRedisplay() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.redisplay, true);
	}

	/**
	 * Flag indicating whether previously handled messages are redisplayed or not <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRedisplay(boolean _redisplay) {
		getStateHelper().put(PropertyKeys.redisplay, _redisplay);
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
