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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


public enum RJumbotron {
    jumbotron;
    

    /**
     * Begin Encoding
     * @param c the UIComponent to encode
     * @param fc the FacesContext
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        
        /*
         * <div class="jumbotron">
         * ...
         * </div>
         */
        ResponseWriter rw = fc.getResponseWriter();
        
        rw.startElement("div", c);
        rw.writeAttribute("id",c.getClientId(fc),"id");
        Tooltip.generateTooltip(fc, c.getAttributes(), rw);
        rw.writeAttribute("class", jumbotron, "class");
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
