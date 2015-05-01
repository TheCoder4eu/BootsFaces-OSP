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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.component;

import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.H;
import net.bootsfaces.render.RAlert;
import net.bootsfaces.render.Tooltip;

/**
 * The &lt;alert&gt; tag generates a colored box that can be used to display error messages, warnings, informations or simply success messages.
 * 
 * @author thecoder4eu
 */

@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css", target="head"),
        @ResourceDependency(library="bsf", name="css/alerts.css", target="head"),
        @ResourceDependency(library="bsf", name="js/alert.js", target="body")
})
@FacesComponent(C.ALERT_COMPONENT_TYPE)
public class Alert extends UIComponentBase {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE =C.ALERT_COMPONENT_TYPE;
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public Alert() {
        setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		Tooltip.addResourceFile();
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        encBegin(fc);
        
    }

    public void encBegin(FacesContext fc) throws IOException {
        /*
        * <div class="alert fade in">
        <button class="close" data-dismiss="alert" type="button">×</button>
        <strong>Holy guacamole!</strong>
        Best check yo self, you're not looking too good.
        </div>
        */
        
        RAlert.encBegin(this, fc);
    }

    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        fc.getResponseWriter().endElement(H.DIV);
		Tooltip.activateTooltips(fc, getAttributes());
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
}
