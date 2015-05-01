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
 * @author thecoder4.eu
 */
public enum RdropButton {
    dropbutton;
    
    public static final String LBL="dtL"; // data toggle Label
    
    /**
     * Begin Encoding
     * @param c the UIComponent to encode
     * @param fc the FacesContext
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        if (!c.isRendered()) {
            return;
        }
        /*<div class="btn-group">
            <button id="dLabel" class="dropdown-toggle" role="button" data-toggle="dropdown" href="#">
            Dropdown
            <b class="caret"></b>
            </button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
        * ...
        * </ul>
        * </div>
        */
        
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = c.getAttributes();
        
        String drop = A.asString(attrs.get(A.DROP));
        String size = A.asString(attrs.get(A.SIZE));
        if(drop==null) { drop=A.DOWN; }
        if(!drop.equals(C.UP) && !drop.equals(C.DOWN)) { drop=A.DOWN; }
        String s="btn-group";
        if(drop.equals(C.UP)) { s+=" drop"+drop; }
        R.encodeDropElementStart(c, rw, c.getClientId(fc), H.DIV, s);
		Tooltip.generateTooltip(fc, attrs, rw);

        String ts="btn btn-";
        String look = A.asString(attrs.get(A.LOOK));
        if(look!=null) { ts+=look+C.SP; } else { ts+="default "; }
        if(size!=null) { ts+="btn-"+size+C.SP; }
        String value = A.asString(attrs.get(A.VALUE)).concat(C.SP);
        R.encodeDataToggler(c, value, H.BUTTON,rw, LBL+c.getClientId(fc),ts);
        
        R.encodeDropMenuStart(c, rw, LBL+c.getClientId(fc));
    }
    
    /**
    * End encoding
    * @param c the UIComponent to encode
    * @param fc the FacesContext
    * @throws IOException
    */
    public static final void encEnd(UIComponent c, FacesContext fc) throws IOException {
        ResponseWriter rw = fc.getResponseWriter();
        rw.endElement(H.UL);
        rw.endElement(H.LI);
        
        rw.endElement(H.DIV); //btn-group
        rw.writeText("\n", null);
		Tooltip.activateTooltips(fc, c.getAttributes());
    }
}
