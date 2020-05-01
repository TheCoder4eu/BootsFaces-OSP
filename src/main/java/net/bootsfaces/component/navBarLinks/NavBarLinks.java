/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu)
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.component.navBarLinks;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import net.bootsfaces.C;
import net.bootsfaces.component.linksContainer.LinksContainer;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author thecoder4.eu
 */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(NavBarLinks.COMPONENT_TYPE)
public class NavBarLinks extends LinksContainer {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".navbarLinks.NavbarLinks";
    /**
     * <p>The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    
    public static final String NAV="nav";
    public static final String NAVBAR="navbar";

    public NavBarLinks() {
        setRendererType(null); // this component renders itself
		AddResourcesListener.addThemedCSSResource("core.css");
    }
    
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	/*
     * <ul class="nav navbar-nav">
     * ...
     * </ul>
     */
    @Override
    protected String getContainerStyles() {
        return NAV+" "+NAVBAR+"-"+NAV;
    }

	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
 	 		super.processEvent(event);
 	 	}
	}

	@Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
}
