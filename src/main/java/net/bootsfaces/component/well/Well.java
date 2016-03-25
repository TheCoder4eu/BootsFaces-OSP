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

package net.bootsfaces.component.well;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.render.RWell;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head"),
    @ResourceDependency(library="bsf", name="css/wells.css")
})
@FacesComponent(C.WELL_COMPONENT_TYPE)
public class Well extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.WELL_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFLAYOUT;
	private Map<String, Object> attributes;

    public Well() {
        setRendererType(null); // this component renders itself
        Tooltip.addResourceFile();
    }
    
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}


    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
         * <div class="well"> || <div class="well well-large">
         * ...
         * </div>
         * Size: large/small
         */
        
        RWell.encBegin(this, context);
        
//        ResponseWriter rw = context.getResponseWriter();
//        
//        Map<String, Object> attrs = getAttributes();
//        
//        String size = A.asString(attrs.get(A.SIZE));
//        
//        rw.startElement(H.DIV, this);
//        rw.writeAttribute(H.ID,getClientId(context),H.ID);
//        if(size!=null) { rw.writeAttribute(H.CLASS,S.WELL+S.SP+S.WELL+S.HYP+size,H.CLASS); }
//        rw.writeAttribute(H.CLASS, S.WELL,H.CLASS);
        
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
