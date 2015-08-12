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
import net.bootsfaces.render.RThumbnail;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4eu
 */
@ResourceDependencies({
        @ResourceDependency(library="bsf", name="css/core.css", target="head"),
        @ResourceDependency(library="bsf", name="css/thumbnails.css", target="head"),
        @ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")
})
@FacesComponent(C.THUMBNAIL_COMPONENT_TYPE)
public class Thumbnail extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.THUMBNAIL_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    public Thumbnail() {
        setRendererType(null); // this component renders itself
        Tooltip.addResourceFile();
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        /*
        *     <div class="thumbnail">
        <img data-src="holder.js/300x200" alt="...">
        
        <div class="caption">
        <h3>Thumbnail label</h3>
        <p>...</p>
        <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
        </div>
        
        </div>
        */
        RThumbnail.encBegin(this,fc);
    }
    
    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        //ResponseWriter rw = fc.getResponseWriter();
        //rw.endElement(H.DIV);
        RThumbnail.encEnd(this, fc);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
