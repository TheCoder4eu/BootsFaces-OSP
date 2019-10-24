/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.dropMenu;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:dropMenu /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/dropdown.js", target = "body") })
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(DropMenu.COMPONENT_TYPE)
public class DropMenu extends DropMenuCore implements IHasTooltip, IResponsive, IContentDisabled {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".dropMenu.DropMenu";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.dropMenu.DropMenu";

	public DropMenu() {
		Tooltip.addResourceFiles();
		// AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		AddResourcesListener.addThemedCSSResource("core.css");
		AddResourcesListener.addExtCSSResource("dropdown-submenu.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
 	 		super.processEvent(event);
 	 	}
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public void setIconAwesome(String _iconAwesome) {
		AddResourcesListener.setNeedsFontsAwesome(this);
		super.setIconAwesome(_iconAwesome);
	}
	
	@Override
	public void setIconBrand(boolean _iconBrand) {
		if (_iconBrand) {
			AddResourcesListener.setFontAwesomeVersion(5, this);
		}
		super.setIconBrand(_iconBrand);
	}

	@Override
	public void setIconRegular(boolean _iconRegular) {
		if (_iconRegular) {
			AddResourcesListener.setFontAwesomeVersion(5, this);
		}
		super.setIconRegular(_iconRegular);
	}

	@Override
	public void setIconLight(boolean _iconLight) {
		if (_iconLight) {
			AddResourcesListener.setFontAwesomeVersion(5, this);
		}
		super.setIconLight(_iconLight);
	}

	@Override
	public void setIconSolid(boolean _iconSolid) {
		if (_iconSolid) {
			AddResourcesListener.setFontAwesomeVersion(5, this);
		}
		super.setIconSolid(_iconSolid);
	}
}
