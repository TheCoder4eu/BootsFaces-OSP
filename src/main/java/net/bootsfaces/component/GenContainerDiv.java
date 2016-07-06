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

package net.bootsfaces.component;

import java.io.IOException;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */

public class GenContainerDiv extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.BSFCOMPONENT+"."+"GenericDivContainer";
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public GenContainerDiv() {
        setRendererType(null); // this component renders itself
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        if (!isRendered()) {
            return;
        }
        /*
        * <div class="?">
        * ...
        * </div>
        */
        
        R.genDivContainer(this,fc);

    }

    /** 
     * every container must override this method returning
     * the specific class(es) for its rendering
     * @return the specific class
     */
    public String getContainerStyles() {
        throw new UnsupportedOperationException("Please Extend this class.");
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        if (!isRendered()) {
            return;
        }
        context.getResponseWriter()
               .endElement("div");
		Tooltip.activateTooltips(context, this);

    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

}
