/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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

package net.bootsfaces.render;

/**
 * HTML Tags
 * 
 * @author thecoder4.eu
 */
public final class H {
	// h:inputText HTML 4.0 pass-through attributes
	// accesskey, alt, dir, disabled, lang, maxlength, readonly, size, style,
	// styleClass, tabindex, title
	// NOTE: disabled, readonly, styleClass are handled by component
	public static final String[] INPUT_TEXT = { "accesskey", "alt", "dir", "lang", "maxlength", "size", "style",
			"tabindex", "title" };

	public static final String[] CHECKBOX = { "accesskey", "alt", "lang", "style", "tabindex", "title" };

	public static final String[] SELECT_ONE_MENU = { "accesskey", "alt", "lang", "style", "tabindex", "title" };

	public static final String[] TAB_VIEW = { "style" };
	public static final String[] TAB = { "style", "tabindex" };

	// NOTE: disabled, styleClass are handled by component
	public static final String[] ALLBUTTON = { "accesskey", "dir", "lang", "style", "tabindex", "title" };

	public static final String[] HTML5_DATA_ATTRIBUTES = { "placeholder", "tabindex", "lang", "accesskey"};

	// Suppress default constructor for noninstantiability
	private H() {
		throw new AssertionError();
	}
}
