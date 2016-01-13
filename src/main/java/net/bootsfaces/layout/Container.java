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

package net.bootsfaces.layout;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */
@ResourceDependencies({ 
    @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })

@FacesComponent(C.CONTAINER_COMPONENT_TYPE)
public class Container extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.CONTAINER_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFLAYOUT;
	private Map<String, Object> attributes;
    

    public Container() {
        setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
        Tooltip.addResourceFile();
    }
    
	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(super.getAttributes());
		return attributes;
	}

	@Override
    public void processDecodes(FacesContext context) {
    	super.processDecodes(context);
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
         * OLD Only Fluid: as of BS3
         * Now BS3 has again container-fluid
         * <div class="container">...</div>
         * or
         * <div class="container-fluid">...</div>
         */
        
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = getAttributes();
        boolean fluid=A.toBool(attrs.get("fluid"), false);
        String style=A.asString(attrs.get("style")); 
        String sc=A.asString(attrs.get("styleClass"));
        
        String c=(fluid ? "container-fluid" : "container");
        if(sc!=null) { c+=" "+sc; }
        
        rw.startElement("div", this);
        rw.writeAttribute("id",getClientId(fc),"id");
//		if (null != container.getDir()) {
//			rw.writeAttribute("dir", container.getDir(), "dir");
//		}
        String dir = A.asString(attrs.get("dir"));
        if (null != dir) rw.writeAttribute("dir", dir, "dir");

        Tooltip.generateTooltip(fc, attrs, rw);
        if(style!=null) { rw.writeAttribute("style",style,"style"); }
        rw.writeAttribute("class", c, "class");
        //rw.writeAttribute(H.CLASS, (fluid ? "container-fluid" : "container"),H.CLASS);
    }
    
    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        if (!isRendered()) {
            return;
        }
        fc.getResponseWriter().endElement("div");
        Tooltip.activateTooltips(fc, getAttributes(), this);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
