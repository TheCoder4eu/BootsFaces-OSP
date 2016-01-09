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

import java.util.Map;

/**
 * Attributes Constants
 * @author thecoder4.eu
 */
public final class A {
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
        
    public static boolean toBool(Object val) {
        if (val == null) { return false; }
        if (val instanceof Boolean) {
            return ((Boolean) val).booleanValue();
        }
        if (val instanceof String) {
            return ((String) val).equals("true");
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
    
    
    /**
     * This is a utility class which is not intended to be instantiated.
     * Suppress default constructor for noninstantiability.
     */
    private A() {
        throw new AssertionError();
    }

}
