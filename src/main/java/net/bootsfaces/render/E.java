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
