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
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.render.A;
import net.bootsfaces.render.H;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4eu
 */

@ResourceDependencies({ 
    @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
public class LinksContainer extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.BSFCOMPONENT+C.DOT+"LinksContainer";
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    public LinksContainer() {
        setRendererType(null); // this component renders itself
        Tooltip.addResourceFile();
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
         * <ul class="?">
         * ...
         * </ul>
         */
        
        ResponseWriter rw = fc.getResponseWriter();
        
        Map<String, Object> attrs = getAttributes();
        
        
        String pull = A.asString(attrs.get(A.PULL));
        
        rw.startElement(H.UL, this);
        rw.writeAttribute("id",getClientId(fc),"id");
        String style = (String) attrs.get("style");
        if (null != style && style.length()>0) {
        	rw.writeAttribute("style", style, "style");
        }
        Tooltip.generateTooltip(fc, attrs, rw);
        String styleClass = (String) attrs.get("styleClass");
        if (null == styleClass) {
        	styleClass="";
        }
        if(pull!=null && (pull.equals(A.RIGHT) || pull.equals(A.LEFT)) ) {
            rw.writeAttribute("class", styleClass.concat(" ").concat(getContainerStyles()).concat(" ").concat(A.PULL).concat("-").concat(pull),"class");
        } else {
            rw.writeAttribute("class", styleClass.concat(" ").concat(getContainerStyles()),"class");
        }
        
        
    }
    
    /** 
     * every container must override this method returning
     * the specific class(es) for its rendering
     * @return the specific class
     */
    protected String getContainerStyles() {
        throw new UnsupportedOperationException("Please Extend this class.");
    }
    
    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        fc.getResponseWriter()
               .endElement(H.UL);
        Tooltip.activateTooltips(fc, getAttributes(), this);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

}
