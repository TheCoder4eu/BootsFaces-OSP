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
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import net.bootsfaces.C;
import net.bootsfaces.render.RLabel;


@ResourceDependencies({
        @ResourceDependency(library="bsf", name="css/core.css", target="head"),
        @ResourceDependency(library="bsf", name="css/labels.css", target="head")
})
@FacesComponent(C.LABEL_COMPONENT_TYPE)
public class Label extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.LABEL_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public Label() {
        setRendererType(null); // this component renders itself
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        /*
         * <span class="label label-success">Success</span>
         */
        
        RLabel.encBegin(this, context);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
