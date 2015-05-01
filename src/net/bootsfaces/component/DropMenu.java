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

package net.bootsfaces.component;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;
import net.bootsfaces.render.RdropMenu;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */

@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css", target="head"),
        @ResourceDependency(library="bsf", name="css/dropdowns.css", target="head"),
        @ResourceDependency(library="bsf", name="js/dropdown.js", target="body")
})
@FacesComponent(C.DROPMENU_COMPONENT_TYPE)
public class DropMenu extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.DROPMENU_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
//    public static final String DROP="drop";
//    public static final String DROPDOWN=DROP.concat(C.DOWN);
//    public static final String LBL="dtL"; // data toggle Label

    public DropMenu() {
        setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		Tooltip.addResourceFile();
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
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
        
//        ResponseWriter rw = fc.getResponseWriter();
//        
//        Map<String, Object> attrs = getAttributes();
//        
//        String drop = A.asString(attrs.get(A.DROP));
//        if(drop==null) { drop=A.DOWN; }
//        if(!drop.equals(C.UP) && !drop.equals(C.DOWN)) { drop=A.DOWN; }
//        String s=DROPDOWN;
//        if(!drop.equals(C.UP)) { s+=C.SP+DROP+drop; }
//        
//        R.encodeDropElementStart(this, rw, getClientId(fc), H.LI, s);
//        rw.startElement(H.LI, this);
//	rw.writeAttribute(H.ID, getClientId(fc), H.ID);
//	rw.writeAttribute(H.NAME, getClientId(fc), H.NAME);
//        rw.writeAttribute(H.CLASS, s,H.CLASS);
//        
//        String ts=C.EMPTY;
//        String value = A.asString(attrs.get(A.VALUE));
//        R.encodeDataToggler(this, value, H.A,rw, LBL+getClientId(fc),ts);
//        
//        R.encodeDropMenuStart(this, rw, LBL+getClientId(fc));
        RdropMenu.encBegin(this, fc);
    }

    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        ResponseWriter rw = fc.getResponseWriter();
        rw.endElement(H.UL);
        rw.endElement(H.LI);
		Tooltip.activateTooltips(fc, getAttributes());

    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
