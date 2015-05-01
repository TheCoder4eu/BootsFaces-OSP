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

package net.bootsfaces.render;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import net.bootsfaces.C;

/**
 * This class renders an &lt;alert&gt;.
 * The &lt;alert&gt; tag generates a colored box that can be used to display error messages, warnings, informations or simply success messages.
 *
 */

public enum RAlert {
    alert,
    //attrs
    severity,
    title,
    closable,
    //Style
    close,
    fadein,
    value;
    

    /**
     * Begin Encoding
     * @param c
     * @param fc
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        if (!c.isRendered()) {
            return;
        }
       /*
        * <div class="alert fade in">
        <button class="close" data-dismiss="alert" type="button">×</button>
        <strong>Holy guacamole!</strong>
        Best check yo self, you're not looking too good.
        </div>
        */
        Map<String, Object> attrs = c.getAttributes();
        
        
        ResponseWriter rw = fc.getResponseWriter();
        
        String sev = A.asString(attrs,severity);
        
        String t = A.asString(attrs,title);
        boolean closbl= A.toBool(attrs,closable);
        
        rw.startElement(H.DIV, c);
        rw.writeAttribute(H.ID,c.getClientId(fc),H.ID);
		Tooltip.generateTooltip(fc, attrs, rw);

        
        if(sev!=null) { rw.writeAttribute(H.CLASS,alert+C.SP+alert+C.HYP+sev+C.SP+fadein,H.CLASS); }
        else               { rw.writeAttribute(H.CLASS, alert+C.SP+fadein,H.CLASS); }
        if(closbl) {
            rw.startElement(H.BUTTON, c);
            rw.writeAttribute(H.TYPE, H.BUTTON,H.TYPE);
            rw.writeAttribute(H.CLASS, close,H.CLASS);
            rw.writeAttribute(H.DISMISS, alert,H.DISMISS);
            rw.write(MessageFormat.format("{0}times{1}","&",";"));
            rw.endElement(H.BUTTON);
        }
        if(t!=null) {
            rw.startElement(H.STRONG, c);
            rw.writeText(t, null);
            rw.endElement(H.STRONG);
            rw.startElement(H.BR, c);
            rw.endElement(H.BR);
        }
    }
    
    /**
     * End encoding
     * @param c
     * @param fc
     * @throws IOException
     */
    public static final void encEnd(UIComponent c, FacesContext fc) throws IOException {
        
    }
    
}
