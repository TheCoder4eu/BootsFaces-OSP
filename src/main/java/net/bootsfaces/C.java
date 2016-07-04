/**
 *  Copyright 2013-2016 Riccardo Massera (TheCoder4.Eu)
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
    public static final String FA_VERSION="4.6.1";
    public static final String FONTAWESOME_CDN_URL="//maxcdn.bootstrapcdn.com/font-awesome/"+FA_VERSION+"/css/font-awesome.min.css";
    
    //Meta tags
    // default yes. to disable set to 'no' or 'false'. if you want to change, set to the value you want to use 
    public static final String P_VIEWPORT = "BootsFaces_USE_VIEWPORT"; 
    
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
