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

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;

import net.bootsfaces.C;

/**
 *
 * @author thecoder4eu
 */

@ResourceDependencies({ 
    @ResourceDependency(library = "bsf", name = "css/core.css"),
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
public class PillLinks extends LinksContainer {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.BSFCOMPONENT+".PillLinks";
    
    private static final String STYLE="nav nav-pills";
    
    public PillLinks() {
        setRendererType(null); // this component renders itself
    }
    
    /*
     * <ul class="nav nav-pills">
     * ...
     * </ul>
     */
    @Override
    protected String getContainerStyles() {
        return STYLE;
    }

    

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
