/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
 *  
 *  This file is part of BootsFaces.
 *  
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */

package net.bootsfaces.render;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author droidcoder
 */
public enum RWell {
    well,
    size;
    

    /**
     * Begin Encoding
     * @param c the UIComponent to encode
     * @param fc the FacesContext
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        
        /*
         * <div class="well"> || <div class="well well-large">
         * ...
         * </div>
         * Size: large/small
         */
        ResponseWriter rw = fc.getResponseWriter();
        Map<String, Object> attrs = c.getAttributes();
        String sz = A.asString(attrs, size);
        
        rw.startElement("div", c);
        rw.writeAttribute("id",c.getClientId(fc),"id");
        String style=(String) attrs.get("style");
        if (null!=style) {
        	rw.writeAttribute("style", style, null);
        }
        String styleClass=(String) attrs.get("styleClass");
        if (null ==styleClass) styleClass=""; else styleClass=" "+styleClass;
        Tooltip.generateTooltip(fc, c, rw);
        
        if(sz!=null) { rw.writeAttribute("class", well+" "+well+"-"+sz+styleClass,"class"); }
        else           { rw.writeAttribute("class", well+styleClass, "class"); }
    }
    
    /**
     * End encoding
     * @param c the UIComponent to encode
     * @param fc the FacesContext
     * @throws IOException
     */
    public static final void encEnd(UIComponent c, FacesContext fc) throws IOException {
        
    }
}
