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

package net.bootsfaces;


/**
 * String Constants valid for the whole Framework
 * 
 * @author thecoder4.eu
 */
public final class C {
    /**
     * BootsFaces Library Constants
     */
    public static final String BSFCOMPONENT= "net.bootsfaces.component";
    public static final String BSFLAYOUT   = "net.bootsfaces.layout";
    //public static final String BSFRENDER   = "net.bootsfaces.render";
    
    //Theme
    public static final String BSF_LIBRARY="bsf";
    public static final String P_USETHEME ="BootsFaces_USETHEME";
    public static final String P_THEME ="BootsFaces_THEME";
    
    //Font Awesome
    //public static final String P_USEFONTAWESOME ="BootsFaces_USE_FA";
    public static final String FA_VERSION="4.5.0";
    public static final String FONTAWESOME_CDN_URL="//maxcdn.bootstrapcdn.com/font-awesome/"+FA_VERSION+"/css/font-awesome.min.css";
    
    /**
     * Component Types
     */
    //Components
    public static final String W_NONAVCASE_LINK="This link is disabled because a navigation case could not be matched.";
    public static final String W_NONAVCASE_BUTTON="This link is disabled because a navigation case could not be matched.";
    
    
    // Suppress default constructor for noninstantiability
    private C() {
        throw new AssertionError();
    }
}
