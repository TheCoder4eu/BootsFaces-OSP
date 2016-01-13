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

package net.bootsfaces.component.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.component.AttributeMapWrapper;

/**
 * 
 * <p>
 * By default, the <code>rendererType</code> property must be set to
 * "<code>javax.faces.Messages</code>". This value can be changed by calling the
 * <code>setRendererType()</code> method.
 * </p>
 */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/alerts.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/alert.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
@FacesComponent("net.bootsfaces.component.Messages")
public class Messages extends javax.faces.component.UIMessages {

	private static final String OPTIMIZED_PACKAGE = "javax.faces.component.";
	private Map<String, Object> attributes;

	public Messages() {
		super();
		setRendererType("net.bootsfaces.component.MessagesRenderer");
	}

	protected enum PropertyKeys {
		dir, errorClass, errorStyle, fatalClass, fatalStyle, infoClass, infoStyle, lang, layout, role, showDetail, style, styleClass, title, tooltip, warnClass, warnStyle,;
		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((toString != null) ? toString : super.toString());
		}
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(super.getAttributes());
		return attributes;
	}

	/**
	 * <p>
	 * Return the value of the <code>dir</code> property.
	 * </p>
	 * <p>
	 * Contents: Direction indication for text that does not inherit
	 * directionality. Valid values are "LTR" (left-to-right) and "RTL"
	 * (right-to-left). These attributes are case sensitive when rendering to
	 * XHTML, so care must be taken to have the correct case.
	 */
	public java.lang.String getDir() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.dir);

	}

	/**
	 * <p>
	 * Set the value of the <code>dir</code> property.
	 * </p>
	 */
	public void setDir(java.lang.String dir) {
		getStateHelper().put(PropertyKeys.dir, dir);
		handleAttribute("dir", dir);
	}

	/**
	 * <p>
	 * Return the value of the <code>errorClass</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style class to apply to any message with a severity class
	 * of "ERROR".
	 */
	public java.lang.String getErrorClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.errorClass);

	}

	/**
	 * <p>
	 * Set the value of the <code>errorClass</code> property.
	 * </p>
	 */
	public void setErrorClass(java.lang.String errorClass) {
		getStateHelper().put(PropertyKeys.errorClass, errorClass);
	}

	/**
	 * <p>
	 * Return the value of the <code>errorStyle</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style(s) to apply to any message with a severity class of
	 * "ERROR".
	 */
	public java.lang.String getErrorStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.errorStyle);

	}

	/**
	 * <p>
	 * Set the value of the <code>errorStyle</code> property.
	 * </p>
	 */
	public void setErrorStyle(java.lang.String errorStyle) {
		getStateHelper().put(PropertyKeys.errorStyle, errorStyle);
	}

	/**
	 * <p>
	 * Return the value of the <code>fatalClass</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style class to apply to any message with a severity class
	 * of "FATAL".
	 */
	public java.lang.String getFatalClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.fatalClass);

	}

	/**
	 * <p>
	 * Set the value of the <code>fatalClass</code> property.
	 * </p>
	 */
	public void setFatalClass(java.lang.String fatalClass) {
		getStateHelper().put(PropertyKeys.fatalClass, fatalClass);
	}

	/**
	 * <p>
	 * Return the value of the <code>fatalStyle</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style(s) to apply to any message with a severity class of
	 * "FATAL".
	 */
	public java.lang.String getFatalStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.fatalStyle);

	}

	/**
	 * <p>
	 * Set the value of the <code>fatalStyle</code> property.
	 * </p>
	 */
	public void setFatalStyle(java.lang.String fatalStyle) {
		getStateHelper().put(PropertyKeys.fatalStyle, fatalStyle);
	}

	/**
	 * <p>
	 * Return the value of the <code>infoClass</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style class to apply to any message with a severity class
	 * of "INFO".
	 */
	public java.lang.String getInfoClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.infoClass);

	}

	/**
	 * <p>
	 * Set the value of the <code>infoClass</code> property.
	 * </p>
	 */
	public void setInfoClass(java.lang.String infoClass) {
		getStateHelper().put(PropertyKeys.infoClass, infoClass);
	}

	/**
	 * <p>
	 * Return the value of the <code>infoStyle</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style(s) to apply to any message with a severity class of
	 * "INFO".
	 */
	public java.lang.String getInfoStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.infoStyle);

	}

	/**
	 * <p>
	 * Set the value of the <code>infoStyle</code> property.
	 * </p>
	 */
	public void setInfoStyle(java.lang.String infoStyle) {
		getStateHelper().put(PropertyKeys.infoStyle, infoStyle);
	}

	/**
	 * <p>
	 * Return the value of the <code>lang</code> property.
	 * </p>
	 * <p>
	 * Contents: Code describing the language used in the generated markup for
	 * this component.
	 */
	public java.lang.String getLang() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.lang);

	}

	/**
	 * <p>
	 * Set the value of the <code>lang</code> property.
	 * </p>
	 */
	public void setLang(java.lang.String lang) {
		getStateHelper().put(PropertyKeys.lang, lang);
		handleAttribute("lang", lang);
	}

	/**
	 * <p>
	 * Return the value of the <code>layout</code> property.
	 * </p>
	 * <p>
	 * Contents: The type of layout markup to use when rendering error messages.
	 * Valid values are "table" (an HTML table) and "list" (an HTML list). If
	 * not specified, the default value is "list".
	 */
	public java.lang.String getLayout() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.layout, "list");

	}

	/**
	 * <p>
	 * Set the value of the <code>layout</code> property.
	 * </p>
	 */
	public void setLayout(java.lang.String layout) {
		getStateHelper().put(PropertyKeys.layout, layout);
	}

	/**
	 * <p>
	 * Return the value of the <code>role</code> property.
	 * </p>
	 * <p>
	 * Contents:
	 * <p class="changed_added_2_2">
	 * Per the WAI-ARIA spec and its relationship to HTML5 (Section title ARIA
	 * Role Attriubute), every HTML element may have a "role" attribute whose
	 * value must be passed through unmodified on the element on which it is
	 * declared in the final rendered markup. The attribute, if specified, must
	 * have a value that is a string literal that is, or an EL Expression that
	 * evaluates to, a set of space-separated tokens representing the various
	 * WAI-ARIA roles that the element belongs to.
	 * </p>
	 * 
	 * <p class="changed_added_2_2">
	 * It is the page author's responsibility to ensure that the user agent is
	 * capable of correctly interpreting the value of this attribute.
	 * </p>
	 */
	public java.lang.String getRole() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.role);

	}

	/**
	 * <p>
	 * Set the value of the <code>role</code> property.
	 * </p>
	 */
	public void setRole(java.lang.String role) {
		getStateHelper().put(PropertyKeys.role, role);
		handleAttribute("role", role);
	}

	/**
	 * <p>
	 * Return the value of the <code>style</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style(s) to be applied when this component is rendered.
	 */
	public java.lang.String getStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.style);

	}

	/**
	 * <p>
	 * Set the value of the <code>style</code> property.
	 * </p>
	 */
	public void setStyle(java.lang.String style) {
		getStateHelper().put(PropertyKeys.style, style);
		handleAttribute("style", style);
	}

	/**
	 * <p>
	 * Return the value of the <code>styleClass</code> property.
	 * </p>
	 * <p>
	 * Contents: Space-separated list of CSS style class(es) to be applied when
	 * this element is rendered. This value must be passed through as the
	 * "class" attribute on generated markup.
	 */
	public java.lang.String getStyleClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass);

	}

	/**
	 * <p>
	 * Set the value of the <code>styleClass</code> property.
	 * </p>
	 */
	public void setStyleClass(java.lang.String styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, styleClass);
	}

	/**
	 * <p>
	 * Return the value of the <code>title</code> property.
	 * </p>
	 * <p>
	 * Contents: Advisory title information about markup elements generated for
	 * this component.
	 */
	public java.lang.String getTitle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.title);

	}

	/**
	 * <p>
	 * Set the value of the <code>title</code> property.
	 * </p>
	 */
	public void setTitle(java.lang.String title) {
		getStateHelper().put(PropertyKeys.title, title);
		handleAttribute("title", title);
	}

	/**
	 * <p>
	 * Return the value of the <code>tooltip</code> property.
	 * </p>
	 * <p>
	 * Contents: Flag indicating whether the detail portion of the message
	 * should be displayed as a tooltip.
	 */
	public boolean isTooltip() {
		return (java.lang.Boolean) getStateHelper().eval(PropertyKeys.tooltip, false);
	}

	/**
	 * <p>
	 * Return the flag indicating whether the <code>detail</code> property of
	 * the associated message(s) should be displayed. Defaults to false.
	 * </p>
	 */
	public boolean isShowDetail() {
		return (Boolean) getStateHelper().eval(PropertyKeys.showDetail, true);
	}

	/**
	 * <p>
	 * Set the value of the <code>tooltip</code> property.
	 * </p>
	 */
	public void setTooltip(boolean tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, tooltip);
	}

	/**
	 * <p>
	 * Return the value of the <code>warnClass</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style class to apply to any message with a severity class
	 * of "WARN".
	 */
	public java.lang.String getWarnClass() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.warnClass);

	}

	/**
	 * <p>
	 * Set the value of the <code>warnClass</code> property.
	 * </p>
	 */
	public void setWarnClass(java.lang.String warnClass) {
		getStateHelper().put(PropertyKeys.warnClass, warnClass);
	}

	/**
	 * <p>
	 * Return the value of the <code>warnStyle</code> property.
	 * </p>
	 * <p>
	 * Contents: CSS style(s) to apply to any message with a severity class of
	 * "WARN".
	 */
	public java.lang.String getWarnStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.warnStyle);

	}

	/**
	 * <p>
	 * Set the value of the <code>warnStyle</code> property.
	 * </p>
	 */
	public void setWarnStyle(java.lang.String warnStyle) {
		getStateHelper().put(PropertyKeys.warnStyle, warnStyle);
	}

	private void handleAttribute(String name, Object value) {
		@SuppressWarnings("unchecked")
		List<String> setAttributes = (List<String>) this.getAttributes()
				.get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		if (setAttributes == null) {
			String cname = this.getClass().getName();
			if (cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
				setAttributes = new ArrayList<String>(6);
				this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
			}
		}
		if (setAttributes != null) {
			if (value == null) {
				ValueExpression ve = getValueExpression(name);
				if (ve == null) {
					setAttributes.remove(name);
				}
			} else if (!setAttributes.contains(name)) {
				setAttributes.add(name);
			}
		}
	}

}
