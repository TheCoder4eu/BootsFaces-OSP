/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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

package net.bootsfaces.layout;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import net.bootsfaces.C;
import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.render.RJumbotron;
import net.bootsfaces.render.Tooltip;


@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css", target = "head"),
        @ResourceDependency(library="bsf", name="css/jumbotron.css"),
        @ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")
})
@FacesComponent(C.JUMBOTRON_COMPONENT_TYPE)
public class Jumbotron extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.JUMBOTRON_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFLAYOUT;
	private Map<String, Object> attributes;
    

    public Jumbotron() {
        setRendererType(null); // this component renders itself
        Tooltip.addResourceFile();
    }
    
	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}


    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
         * <div class="jumbotron">
         * ...
         * </div>
         */
        
        RJumbotron.encBegin(this, context);
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
        context.getResponseWriter().endElement("div");
        Tooltip.activateTooltips(context, getAttributes(), this);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
