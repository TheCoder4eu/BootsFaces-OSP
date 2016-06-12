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

package net.bootsfaces.component.modal;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:modal /&gt;. */
@ResourceDependencies({
		@ResourceDependency(library = "bsf", name = "js/modal.js", target = "body"), })
@FacesComponent("net.bootsfaces.component.modal.Modal")
public class Modal extends UIComponentBase {

	protected enum PropertyKeys {
		backdrop, closable, closeOnEscape, contentClass, contentStyle, headerClass, headerStyle, size, style, styleClass, title;

		String toString;

		PropertyKeys() {
		}

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		@Override
		public String toString() {
			return toString != null ? toString : super.toString();
		}
	}

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.modal.Modal";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.modal.Modal";

	public Modal() {
		setRendererType(DEFAULT_RENDERER);
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addThemedCSSResource("modals.css");
		// AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY,
		// "jq/jquery.js");
	}

	/**
	 * content-class is optional: if specified, the content will be displayed
	 * with this specific class
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getContentClass() {
		return (String) getStateHelper().eval(PropertyKeys.contentClass);
	}

	/**
	 * Inline style of the content area.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getContentStyle() {
		return (String) getStateHelper().eval(PropertyKeys.contentStyle);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * The style of the header is optional. If specified, it will add the style
	 * to the header.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getHeaderStyle() {
		return (String) getStateHelper().eval(PropertyKeys.headerStyle);
	}

	/**
	 * The style class of the header is optional. If specified, it will add a
	 * style class to the header.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getHeaderStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.headerClass);
	}

	/**
	 * Modal's size. Possible values modal-sm, modal-lg
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getSize() {
		return (String) getStateHelper().eval(PropertyKeys.size);
	}

	/**
	 * Inline style
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Style class of this element.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Bold title displayed in the modal's header.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getTitle() {
		return (String) getStateHelper().eval(PropertyKeys.title);
	}

	/**
	 * By default, you can close a modal dialog by clicking somewhere outside
	 * the modal. Set backdrop="false" to disable this feature.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isBackdrop() {
		return (Boolean) getStateHelper().eval(PropertyKeys.backdrop, true);
	}

	/**
	 * If true, the modal dialog can be closed by clicking the small cross in
	 * the upper right corner
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isClosable() {
		return (Boolean) getStateHelper().eval(PropertyKeys.closable, true);
	}

	/**
	 * By default, users can close modal dialogs by hitting the ESC key. Set
	 * close-on-escape="false" to disable this feature.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isCloseOnEscape() {
		return (Boolean) getStateHelper().eval(PropertyKeys.closeOnEscape,
				true);
	}

	/**
	 * By default, you can close a modal dialog by clicking somewhere outside
	 * the modal. Set backdrop="false" to disable this feature.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBackdrop(boolean _backdrop) {
		getStateHelper().put(PropertyKeys.backdrop, _backdrop);
	}

	/**
	 * If true, the modal dialog can be closed by clicking the small cross in
	 * the upper right corner
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setClosable(boolean _closable) {
		getStateHelper().put(PropertyKeys.closable, _closable);
	}

	/**
	 * By default, users can close modal dialogs by hitting the ESC key. Set
	 * close-on-escape="false" to disable this feature.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCloseOnEscape(boolean _closeOnEscape) {
		getStateHelper().put(PropertyKeys.closeOnEscape, _closeOnEscape);
	}

	/**
	 * content-class is optional: if specified, the content will be displayed
	 * with this specific class
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentClass(String _contentClass) {
		getStateHelper().put(PropertyKeys.contentClass, _contentClass);
	}

	/**
	 * Inline style of the content area.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentStyle(String _contentStyle) {
		getStateHelper().put(PropertyKeys.contentStyle, _contentStyle);
	}

	/**
	 * Modal's size. Possible values modal-sm, modal-lg
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSize(String _size) {
		getStateHelper().put(PropertyKeys.size, _size);
	}

	/**
	 * Inline style
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of this element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Bold title displayed in the modal's header.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitle(String _title) {
		getStateHelper().put(PropertyKeys.title, _title);
	}

	@Override
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

}
