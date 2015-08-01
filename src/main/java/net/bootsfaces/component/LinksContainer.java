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
        rw.writeAttribute(H.ID,getClientId(fc),H.ID);
        Tooltip.generateTooltip(fc, attrs, rw);
        if(pull!=null && (pull.equals(A.RIGHT) || pull.equals(A.LEFT)) ) {
            rw.writeAttribute(H.CLASS, getContainerStyles().concat(C.SP).concat(A.PULL).concat(C.HYP).concat(pull),H.CLASS);
        } else {
            rw.writeAttribute(H.CLASS, getContainerStyles(),H.CLASS);
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
