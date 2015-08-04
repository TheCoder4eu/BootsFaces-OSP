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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.component;

import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;

/**
 *
 * @author thecoder4.eu
 */

@ResourceDependency(library="bsf", name="css/core.css", target="head")
@FacesComponent(C.NAVBARLINKS_COMPONENT_TYPE)
public class NavBarLinks extends LinksContainer {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.NAVBARLINKS_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    public static final String NAV="nav";
    public static final String NAVBAR="navbar";

    public NavBarLinks() {
        setRendererType(null); // this component renders itself
    }
    
    /*
     * <ul class="nav navbar-nav">
     * ...
     * </ul>
     */
    @Override
    protected String getContainerStyles() {
        return NAV+" "+NAVBAR+"-"+NAV;
    }

    

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
