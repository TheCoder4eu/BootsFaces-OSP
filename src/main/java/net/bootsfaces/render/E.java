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

/**
 * DHTML EVENTS Constants
 * 
 * @author thecoder4.eu
 */
public final class E {
    public static final String ONDBLCLICK="ondblclick";
    public static String[] FOCUS = {
		"onblur",
		"onfocus"
	};
	
	public static String[] CHANGE_SELECT = {
		"onchange",
		"onselect"
	};
	
	public static String[] CLICK = {
		"onclick",
		"ondblclick"
        };
	
    public static String[] DBLCLICK = {
		ONDBLCLICK
        };
    public static String[] KEY = {
		"onkeydown",
		"onkeypress",
		"onkeyup"
        };
    public static String[] MOUSE = {
		"onmousedown",
		"onmousemove",
		"onmouseout",
		"onmouseover",
		"onmouseup"
	};
    
    // Suppress default constructor for noninstantiability
    private E() {
        throw new AssertionError();
    }
}
