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

import java.util.Arrays;
import java.util.Map;

import net.bootsfaces.C;

/**
 * Attributes Constants
 * @author thecoder4.eu
 */
public final class A {
    public static final String POPUP="popup";
    /* Attributes */
    public static final String ACTIVE="active";
    public static final String BACKDROP="backdrop";
    public static final String BRAND_ALIGN="brand"+"Align";
    public static final String CLOSABLE="closable";
    public static final String CLOSE_ON_ESC="close-on-escape";
    public static final String FRAGMENT="fragment";
    
    public static final String DISMISS="dismiss";
    public static final String DATA_DISMISS="data"+"-"+DISMISS;
    public static final String DISABLED=C.DISABLED;
    public static final String DROP="drop";
    public static final String HEADER="header";
    public static final String HREF="href";
    public static final String ICON="icon";
    public static final String ICONAWESOME="iconAwesome";
    public static final String BADGE="badge";
    public static final String ICON_ALIGN=ICON+"Align";
    public static final String ICON_POS=ICON+"Pos";
    public static final String LOOK="look";
    public static final String LIBRARY="library";
    public static final String NAME="name";
    
    public static final String MEDIA="media";
    public static final String MEDIA_URL=MEDIA+"Url";
    public static final String MEDIA_ALIGN=MEDIA+"Align";
    
    public static final String PULL="pull";
    public static final String OFFSET="offset";
    public static final String READONLY=C.READONLY;
    public static final String PHOLDER="placeholder";
    public static final String RENDERLABEL="renderLabel"; // suppresses rendering labels
    public static final String SEVERITY="severity";
    public static final String SIZE="size";
    public static final String MAXLEN="maxlength";
    public static final String FIELDSIZE="fieldSize";
    public static final String SPAN="span";
    public static final String STYLE = H.STYLE;
    public static final String TITLE=H.TITLE;
    public static final String TEXT="text";
    public static final String PANEL="panel";
    public static final String SHOWON="showOn";
    //jQuery DatePicker
    public static final String DTFORMAT = JQ.DTFORMAT;
    public static final String LOCALE = JQ.LOCALE;
    public static final String TZ="timezone";
    
    
    /* Attribute Values */
    
    //DatePicker
    public static final String INLINE="inline";
    public static final String POPICON=POPUP+"-"+ICON;
    public static final String ICONPOP=ICON+"-"+POPUP;
    public static final String TOGGLEICON="toggle"+"-"+ICON;
    public static final String ICONTOGGLE=ICON+"-"+"toggle";
    
    public static final String LEFT=C.LEFT;
    public static final String RIGHT=C.RIGHT;
    public static final String DOWN=C.DOWN;
    public static final String UP=C.UP;
    public static final String BOTH="both";
    
    public static final String APPEND=C.APPEND;
    public static final String PREPEND=C.PREPEND;
    
    public static final String TRUE=C.TRUE;
    public static final String AJAX="ajax";
    public static final String SUBMIT="submit";
    public static final String UPDATE="update";
    public static final String ONCOMPLETE="oncomplete";
    
    
    /** Converts the parameter to an integer value, if possible.
     * Throws an IllegalArgumentException if the parameter cannot be converted to an integer.
     * 
     * @param val the parameter to be converted. May be a String, a number or null. Everything else causes an {@link IllegalArgumentException} to be thrown.
     * @return the integer value. 0 if the parameter is null.
     */
    public static int toInt(Object val) {
        if (val == null) { return 0; }
        if (val instanceof Number) {
            return ((Number) val).intValue();
        }
        if (val instanceof String) {
            return Integer.parseInt((String) val);
        }
        
        throw new IllegalArgumentException("Cannot convert " + val);
    }
    
    /** Converts the parameter to an integer value, if possible.
     * Throws an IllegalArgumentException if the parameter cannot be converted to an integer.
     * 
     * @param val the parameter to be converted. May be a String, a number or null. Everything else causes an {@link IllegalArgumentException} to be thrown.
     * @param d default value (returned if val is null).
     * @return the integer value.
     */
    public static int toInt(Object val, int d) { //if null returns suppled default value
        if (val == null) { return d; }
        else { return toInt(val); }
    }
    
    public static boolean toBool(Object val) {
        if (val == null) { return false; }
        if (val instanceof Boolean) {
            return ((Boolean) val).booleanValue();
        }
        if (val instanceof String) {
            return ((String) val).equals(TRUE);
        }
        if (val instanceof Number) {
            return ((Number) val).intValue() > 0;
        }
        throw new IllegalArgumentException("Cannot convert " + val);
    }
    public static boolean toBool(Object val, boolean d) {
        if (val == null) { return d; }
        return toBool(val);
    }
    public static boolean toBool(Map<String, Object> attrs, Object o) {
        return toBool(attrs.get(o.toString()));
    }
    
    public static String asString(Object val) {
        if (val == null) { return null; }
        return val.toString();
    }
    public static String asString(Object val, String d) { //if null returns suppled default value
        if (val == null) { return d; }
        return val.toString();
    }
    
    public static String asString(Map<String, Object> attrs, Object o) {
        return asString(attrs.get(o.toString()));
    }
    
    public static final String[] ALLBUTTON_ATTRS = A.concatAll( H.ALLBUTTON);
    public static final String[] CHECKBOX_ATTRS = A.concatAll( H.CHECKBOX);
    public static final String[] INPUT_TEXT_ATTRS = A.concatAll( H.INPUT_TEXT);
    public static final String[] TAB_ATTRS = A.concatAll( H.TAB);
    public static final String[] TAB_VIEW_ATTRS = A.concatAll( H.TAB_VIEW);
    public static final String[] SELECT_ONE_MENU_ATTRS = A.concatAll( H.SELECT_ONE_MENU);
    public static final String[] IMAGE = A.concatAll( H.IMAGE);


   
    /**
     * Joins two arrays efficiently.
     * @param first must not be null
     * @param second must not be null
     * @return An array containing the elements of both array. Never null.
     * 
     * This version requires Java 6, as they use Arrays.copyOf()
     * Both versions avoid creating any intermediary List objects and use System.arraycopy()
     * to ensure that copying large arrays is as fast as possible.
     */
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    //For an arbitrary number of arrays (>= 1)

    /**
     * Joins an arbitrary number of arrays efficiently.
     * @param first must not be null
     * @param rest optional, arbitrary-length list of arrays
     * @return An array containing the elements of both array. Never null.
     * 
     * This version requires Java 6, as they use Arrays.copyOf()
     * Both versions avoid creating any intermediary List objects and use System.arraycopy()
     * to ensure that copying large arrays is as fast as possible.
     */
    public static <T> T[] concatAll(T[] first, T[]... rest) {
      int totalLength = first.length;
      for (T[] array : rest) {
        totalLength += array.length;
      }
      T[] result = Arrays.copyOf(first, totalLength);
      int offset = first.length;
      for (T[] array : rest) {
        System.arraycopy(array, 0, result, offset, array.length);
        offset += array.length;
      }
      return result;
    }

    
    /**
     * This is a utility class which is not intended to be instantiated.
     * Suppress default constructor for noninstantiability.
     */
    private A() {
        throw new AssertionError();
    }

}
