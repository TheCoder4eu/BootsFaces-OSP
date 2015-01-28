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

import java.io.IOException;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public enum RIcon {
    icon;
    

    /**
     * Begin Encoding
     * @param c the UIComponent to encode
     * @param fc the FacesContext
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        /*
         * <span class="badge badge-important">6</span>
         */
        
        Map<String, Object> attrs = c.getAttributes();
        String icon = A.asString(attrs.get("name"));
        //!//String set = A.asString(attrs.get("icon-set"));
        String size = A.asString(attrs.get("size"));
        String rotate = A.asString(attrs.get("rotate"));
        String flip = A.asString(attrs.get("flip"));
        boolean spin = A.toBool(attrs.get("spin"));
        boolean addon = A.toBool(attrs.get("addon"));
        R.encodeIcon(fc.getResponseWriter(), c, icon, false, size, rotate, flip, spin, addon);
    }
    
    /**
    * End encoding
    * @param c the UIComponent to encode
    * @param fc the FacesContext
    * @throws IOException
    */
    public static final void encEnd(UIComponent c, FacesContext fc) throws IOException {
        
    }
}
