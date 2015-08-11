/**
 *  Copyright 2014 Riccardo Massera (TheCoder4.Eu)
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

import net.bootsfaces.C;

/**
 * HTML Tags
 * @author thecoder4.eu
 */
public final class H {
    public static final String HASH=C.HASH;
    //hyphen
    public static final String HYP="-";
    
    public static final String ARIA="aria";
    public static final String ARIALBLBY=ARIA+HYP+"labelledby";
    /* TAGS */
    public static final String A ="a";
    public static final String ALT ="alt";
    public static final String B ="b";
    public static final String BR ="br";
    /*HTML tag button */
    public static final String BUTTON ="button";
    
    public static final String I ="i";
    public static final String IMG="img";
    public static final String H1 ="h1";
    public static final String H2 ="h2";
    public static final String H3 ="h3";
    public static final String H4 ="h4";
    public static final String H5 ="h5";
    
    public static final String LI="li";
    public static final String SELECT="select";
    
    public static final String STRONG="strong";
    
    public static final String UL="ul";
    
    /* HTML5 */
    public static final String PHOLDER="placeholder";
    
    /* WAI-ARIA */
    public static final String ROLE="role";
    public static final String MENU="menu";
    
    public static final String STYLECLASS ="styleClass";
    public static final String DISABLED=C.DISABLED;
    public static final String HREF="href";
    public static final String SRC="src";
    public static final String READONLY=C.READONLY;
    public static final String STYLE="style";
    public static final String TITLE="title";
    /** Types */
	public static final String PASSWORD = "password"; // used by inputSecret

    /* WAI-ARIA Attribute Values */
    
    /* HTML5 custom DATA attributes */
    public static final String DISMISS="data-dismiss"; //alert,modal
    public static final String TOGGLE="data-toggle"; //modal
    public static final String TARGET="data-target";
    public static final String ORIGTITLE="data-original-title";
    
    public static final String TOOLTIP="tooltip";
    public static final String POPOVER="popover";
    //public static final String ="";
    
    // h:inputText HTML 4.0 pass-through attributes
    // accesskey, alt, dir, disabled, lang, maxlength, readonly, size, style, styleClass, tabindex, title 
    // NOTE: disabled, readonly, styleClass are handled by component
    public static final String[] INPUT_TEXT = {
		"accesskey",
		"alt",
		"dir",
                //DISABLED,
		"lang",
		"maxlength",
                //READONLY,
		"size",
                STYLE,
		"tabindex",
		TITLE
    };
    
    public static final String[] CHECKBOX = {
		"accesskey",
		"alt",
		"lang",
        STYLE,
		"tabindex",
		TITLE
    };
    
    public static final String[] SELECT_ONE_MENU = {
		"accesskey",
		"alt",
		"lang",
        STYLE,
		"tabindex",
		TITLE
    };


    public static final String[] TAB_VIEW = {
        STYLE
    };
    public static final String[] TAB = {
        STYLE,
		"tabindex"
    };

    
    // NOTE: disabled, styleClass are handled by component
    public static final String[] ALLBUTTON = {
		"accesskey",
		"dir",
                //DISABLED,
		"lang",
                STYLE,
		"tabindex",
		TITLE
    };
    
    // Suppress default constructor for noninstantiability
    private H() {
        throw new AssertionError();
    }
}
