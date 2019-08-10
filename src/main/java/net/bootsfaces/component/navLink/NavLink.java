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

package net.bootsfaces.component.navLink;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:navLink /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(NavLink.COMPONENT_TYPE)
public class NavLink extends NavLinkCore implements ClientBehaviorHolder, net.bootsfaces.render.IHasTooltip,
		IAJAXComponent, IAJAXComponent2, AbstractNavLink, IResponsive {

	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".navLink.NavLink";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.navLink.NavLink";

	public NavLink() {
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		setRendererType(DEFAULT_RENDERER);
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	private static final Collection<String> EVENT_NAMES = Collections
			.unmodifiableCollection(Arrays.asList("blur", "change", "click", "dblclick", "focus", "keydown", "keypress",
					"keyup", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).
	 *
	 * @return
	 */
	@Override
	public Map<String, String> getJQueryEvents() {
		return null;
	}

	/**
	 * Returns the subset of the parameter list of jQuery and other non-standard JS callbacks which is sent to the server via AJAX.
	 * If there's no parameter list for a certain event, the default is simply null.
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterListsForAjax() {
		return null;
	}

	/**
	 * Returns the parameter list of jQuery and other non-standard JS callbacks.
	 * If there's no parameter list for a certain event, the default is simply "event".
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterLists() {
		return null;
	}

	@Override
	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	@Override
	public String getDefaultEventName() {
		return "click";
	}

	@Override
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

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	/**
	 * Boolean value to specify if the widget is disabled.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or false, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isDisabled() {
		if (super.isDisabled()) 
			return true;
		UIComponent ancestor = getParent();
		while (ancestor!=null) {
			if (ancestor instanceof IContentDisabled) {
				if (((IContentDisabled)ancestor).isContentDisabled()) {
					return true;
				}
			}
			ancestor=ancestor.getParent();
		}
		return false;
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
