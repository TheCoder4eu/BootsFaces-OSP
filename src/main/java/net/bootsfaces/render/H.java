/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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
