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
    public static final String BSF_CSS_TBSTHEME="css/theme.css";
    
    /**
     * Component Types
     */
    //Components
    public static final String ALERT_COMPONENT_TYPE=BSFCOMPONENT+".Alert";
    public static final String BADGE_COMPONENT_TYPE=BSFCOMPONENT+".Badge";
    public static final String BUTTON_COMPONENT_TYPE=BSFCOMPONENT+".Button";
    public static final String BUTTONGROUP_COMPONENT_TYPE=BSFCOMPONENT+".ButtonGroup";
    public static final String BUTTONTOOLBAR_COMPONENT_TYPE=BSFCOMPONENT+".ButtonToolbar";
    public static final String COMMANDBUTTON_COMPONENT_TYPE=BSFCOMPONENT+".CommandButton";
    public static final String DROPBUTTON_COMPONENT_TYPE=BSFCOMPONENT+".DropButton";
    public static final String DROPMENU_COMPONENT_TYPE=BSFCOMPONENT+".DropMenu";
    public static final String INPUTSECRET_COMPONENT_TYPE=BSFCOMPONENT+".InputSecret";
    public static final String INPUTTEXT_COMPONENT_TYPE=BSFCOMPONENT+".InputText";
    public static final String LABEL_COMPONENT_TYPE=BSFCOMPONENT+".Label";
    public static final String LISTLINKS_COMPONENT_TYPE=BSFCOMPONENT+".ListLinks";
    public static final String MODAL_COMPONENT_TYPE=BSFCOMPONENT+".Modal";
    public static final String NAVBAR_COMPONENT_TYPE=BSFCOMPONENT+".NavBar";
    public static final String NAVBARLINKS_COMPONENT_TYPE=BSFCOMPONENT+".NavBarLinks";
    public static final String NAVLINK_COMPONENT_TYPE=BSFCOMPONENT+".NavLink";
    public static final String PANELGRID_COMPONENT_TYPE=BSFCOMPONENT+".PanelGrid";
    public static final String SELECT_BOOLEAN_CHECKBOX_COMPONENT_TYPE=BSFCOMPONENT+".SelectBooleanCheckbox";
    public static final String TAB_COMPONENT_TYPE=BSFCOMPONENT+".Tab";
    public static final String TAB_VIEW_COMPONENT_TYPE=BSFCOMPONENT+".TabView";
    public static final String THUMBNAIL_COMPONENT_TYPE=BSFCOMPONENT+".Thumbnail";
    //jQuery Components
    public static final String DATEPICKER_COMPONENT_TYPE=BSFCOMPONENT+".DatePicker";
    public static final String SLIDER_COMPONENT_TYPE=BSFCOMPONENT+".Slider";
    //Layout Components
    public static final String CONTAINER_COMPONENT_TYPE=BSFLAYOUT+".Container";
    public static final String COLUMN_COMPONENT_TYPE=BSFLAYOUT+".Column";
    public static final String ROW_COMPONENT_TYPE=BSFLAYOUT+".Row";
    public static final String JUMBOTRON_COMPONENT_TYPE=BSFLAYOUT+".Jumbotron";
    public static final String PANEL_COMPONENT_TYPE=BSFLAYOUT+".Panel";
    public static final String WELL_COMPONENT_TYPE=BSFLAYOUT+".Well";
    
    /*
    * Common constants
    */
    //Hash
    public static final String HASH="#";
    // Hyphen
    public static final String HYP="-";
    // Empty
    public static final String EMPTY="";
    // Space
    public static final String SP=" ";
    // Dot
    public static final String DOT=".";
    // Colon
    public static final String COLON=":";
    // SemiColon
    public static final String SCOLON=";";
    // Comma
    public static final String COMMA=",";
    // Underline
    public static final String USCORE="_";
    // Quote
    public static final String QUOTE="'";
    
    public static final String DISABLED="disabled";
    public static final String READONLY="readonly";
    public static final String INPUT="input";
    public static final String VALUE="value";
    
    // Bottom, Left, Right, Top, Down, Up
    public static final String BOTTOM="bottom";
    public static final String LEFT="left";
    public static final String RIGHT="right";
    public static final String TOP="top";
    public static final String DOWN="down";
    public static final String UP="up";
    public static final String H="horizontal";
    public static final String V="vertical";
    
    public static final String APPEND="append";
    public static final String PREPEND="prepend";
    
    public static final String TRUE="true";
    //public static final String ="";
    
    /*
     * Messages
     */
    //Errors
    //Warnings
    public static final String W_NONAVCASE_LINK="This link is disabled because a navigation case could not be matched.";
    public static final String W_NONAVCASE_BUTTON="This link is disabled because a navigation case could not be matched.";
    
    
    //Info
    
    // Suppress default constructor for noninstantiability
    private C() {
        throw new AssertionError();
    }
}
