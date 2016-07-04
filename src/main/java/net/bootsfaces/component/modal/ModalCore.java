/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
 *
 *  This file is part of BootsFaces.
 *
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */

package net.bootsfaces.component.modal;

import javax.faces.component.*;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:modal /&gt;. */
public abstract class ModalCore extends UIComponentBase {

	protected enum PropertyKeys {
		backdrop,
		closable,
		closeOnEscape,
		contentClass,
		contentStyle,
		headerClass,
		headerStyle,
		size,
		style,
		styleClass,
		title;
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
	 * By default, you can close a modal dialog by clicking somewhere outside the modal. Set backdrop="false" to disable this feature. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isBackdrop() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.backdrop, true);
	}

	/**
	 * By default, you can close a modal dialog by clicking somewhere outside the modal. Set backdrop="false" to disable this feature. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBackdrop(boolean _backdrop) {
		getStateHelper().put(PropertyKeys.backdrop, _backdrop);
	}

	/**
	 * If true, the modal dialog can be closed by clicking the small cross in the upper right corner <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isClosable() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.closable, true);
	}

	/**
	 * If true, the modal dialog can be closed by clicking the small cross in the upper right corner <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setClosable(boolean _closable) {
		getStateHelper().put(PropertyKeys.closable, _closable);
	}

	/**
	 * By default, users can close modal dialogs by hitting the ESC key. Set close-on-escape="false" to disable this feature. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isCloseOnEscape() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.closeOnEscape, true);
	}

	/**
	 * By default, users can close modal dialogs by hitting the ESC key. Set close-on-escape="false" to disable this feature. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCloseOnEscape(boolean _closeOnEscape) {
		getStateHelper().put(PropertyKeys.closeOnEscape, _closeOnEscape);
	}

	/**
	 * content-class is optional: if specified, the content will be displayed with this specific class <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getContentClass() {
		return (String) getStateHelper().eval(PropertyKeys.contentClass);
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
		return (String) getStateHelper().eval(PropertyKeys.contentStyle);
	}

	/**
	 * Inline style of the content area. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentStyle(String _contentStyle) {
		getStateHelper().put(PropertyKeys.contentStyle, _contentStyle);
	}

	/**
	 * The style class of the header is optional. If specified, it will add a CSS style class to the header. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHeaderClass() {
		return (String) getStateHelper().eval(PropertyKeys.headerClass);
	}

	/**
	 * The style class of the header is optional. If specified, it will add a CSS style class to the header. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHeaderClass(String _headerClass) {
		getStateHelper().put(PropertyKeys.headerClass, _headerClass);
	}

	/**
	 * The style of the header is optional. If specified, it will add the CSS style to the header. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHeaderStyle() {
		return (String) getStateHelper().eval(PropertyKeys.headerStyle);
	}

	/**
	 * The style of the header is optional. If specified, it will add the CSS style to the header. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHeaderStyle(String _headerStyle) {
		getStateHelper().put(PropertyKeys.headerStyle, _headerStyle);
	}

	/**
	 * Modal's size. Possible values modal-sm, modal-lg <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSize() {
		return (String) getStateHelper().eval(PropertyKeys.size);
	}

	/**
	 * Modal's size. Possible values modal-sm, modal-lg <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSize(String _size) {
		getStateHelper().put(PropertyKeys.size, _size);
	}

	/**
	 * Inline style <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline style <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of this element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of this element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Bold title displayed in the modal's header. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTitle() {
		return (String) getStateHelper().eval(PropertyKeys.title);
	}

	/**
	 * Bold title displayed in the modal's header. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitle(String _title) {
		getStateHelper().put(PropertyKeys.title, _title);
	}

}
