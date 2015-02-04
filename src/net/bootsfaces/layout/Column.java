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
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import net.bootsfaces.render.A;
import net.bootsfaces.C;
import net.bootsfaces.render.H;
import net.bootsfaces.render.R;

@ResourceDependency(library="bsf", name="css/core.css")
@FacesComponent(C.COLUMN_COMPONENT_TYPE)
public class Column extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.COLUMN_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFLAYOUT;

    public Column() {
        setRendererType(null); // this component renders itself
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        /*
         * ...
         * </div>
         */
    	
        if (isRendered())
        {
            ResponseWriter rw = fc.getResponseWriter();
            
            Map<String, Object> attrs = getAttributes();
            
            int colxs = A.toInt(attrs.get("col-xs"));
            int colsm = A.toInt(attrs.get("col-sm"));
            int collg = A.toInt(attrs.get("col-lg"));
            
            int span = A.toInt(attrs.get(A.SPAN));
            
            int colmd = (span > 0) ? span : A.toInt(attrs.get("col-md"));
            if((colxs>0)||(colsm>0)||(collg>0)) {
                colmd = (colmd > 0) ? colmd : 0;
            } else {
                colmd = (colmd > 0) ? colmd : 12;
            }
            
            int offs = A.toInt(attrs.get(A.OFFSET));
            int offsmd = (offs > 0) ? offs : A.toInt(attrs.get("offset-md"));
            
            R.encodeColumn(rw, this, colmd,
                                     colxs,
                                     colsm,
                                     collg,
                                     offsmd,
                                     A.toInt(attrs.get("offset-xs")),
                                     A.toInt(attrs.get("offset-sm")),
                                     A.toInt(attrs.get("offset-lg")),
                                     A.asString(attrs.get(H.STYLECLASS)));
        }
        
    }
    
    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        fc.getResponseWriter().endElement(H.DIV);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
