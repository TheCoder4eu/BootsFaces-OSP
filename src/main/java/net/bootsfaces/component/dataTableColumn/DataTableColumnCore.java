/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.dataTableColumn;

import javax.faces.component.*;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:dataTableColumn /&gt;. */
public abstract class DataTableColumnCore extends UIOutput {

	protected enum PropertyKeys {
		label,
		order,
		style,
		styleClass;
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
	 * Label in the header of the colum. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLabel() {
		return (String) getStateHelper().eval(PropertyKeys.label);
	}

	/**
	 * Label in the header of the colum. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLabel(String _label) {
		getStateHelper().put(PropertyKeys.label, _label);
	}

	/**
	 * Is the table to be sorted by this column? Legal values are 'asc' and 'desc'. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOrder() {
		return (String) getStateHelper().eval(PropertyKeys.order);
	}

	/**
	 * Is the table to be sorted by this column? Legal values are 'asc' and 'desc'. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOrder(String _order) {
		getStateHelper().put(PropertyKeys.order, _order);
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

}
