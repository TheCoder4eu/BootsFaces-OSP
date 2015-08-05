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

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */

public class GenContainerDiv extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.BSFCOMPONENT+C.DOT+"GenericDivContainer";
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public GenContainerDiv() {
        setRendererType(null); // this component renders itself
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
        * <div class="?">
        * ...
        * </div>
        */
        
        R.genDivContainer(this,fc);

    }

    /** 
     * every container must override this method returning
     * the specific class(es) for its rendering
     * @return the specific class
     */
    public String getContainerStyles() {
        throw new UnsupportedOperationException("Please Extend this class.");
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
        context.getResponseWriter()
               .endElement("div");
		Tooltip.activateTooltips(context, getAttributes(), this);

    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

}
