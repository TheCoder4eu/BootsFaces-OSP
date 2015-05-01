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
public enum RdropMenu {
    dropmenu;
    
    public static final String DROP="drop";
    public static final String DROPDOWN=DROP.concat(C.DOWN);
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
        /*<li class="dropdown">
            <a id="dLabel" class="dropdown-toggle" role="button" data-toggle="dropdown" href="#">
            Dropdown
            <b class="caret"></b>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
        * ...
        * </ul>
        * </li>
        */
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = c.getAttributes();
        
        String drop = A.asString(attrs.get(A.DROP));
        if(drop==null) { drop=A.DOWN; }
        if(!drop.equals(C.UP) && !drop.equals(C.DOWN)) { drop=A.DOWN; }
        String s=DROPDOWN;
        if(!drop.equals(C.UP)) { s+=C.SP+DROP+drop; }
        
        R.encodeDropElementStart(c, rw, c.getClientId(fc), H.LI, s);
		Tooltip.generateTooltip(fc, attrs, rw);
        rw.startElement(H.LI, c);
	rw.writeAttribute(H.ID, c.getClientId(fc), H.ID);
	rw.writeAttribute(H.NAME, c.getClientId(fc), H.NAME);
        rw.writeAttribute(H.CLASS, s,H.CLASS);
        
        String ts=C.EMPTY;
        String value = A.asString(attrs.get(A.VALUE));
        R.encodeDataToggler(c, value, H.A,rw, LBL+c.getClientId(fc),ts);
        
        R.encodeDropMenuStart(c, rw, LBL+c.getClientId(fc));
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
