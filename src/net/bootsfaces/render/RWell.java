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
import javax.faces.context.ResponseWriter;
import net.bootsfaces.C;

/**
 *
 * @author droidcoder
 */
public enum RWell {
    well,
    size;
    

    /**
     * Begin Encoding
     * @param c the UIComponent to encode
     * @param fc the FacesContext
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        
        /*
         * <div class="well"> || <div class="well well-large">
         * ...
         * </div>
         * Size: large/small
         */
        ResponseWriter rw = fc.getResponseWriter();
        Map<String, Object> attrs = c.getAttributes();
        String sz = A.asString(attrs, size);
        
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.ID,c.getClientId(fc),H.ID);
        
        if(sz!=null) { rw.writeAttribute(H.CLASS, well+C.SP+well+C.HYP+sz,H.CLASS); }
        else           { rw.writeAttribute(H.CLASS, well, H.CLASS); }
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
