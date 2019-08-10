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

package net.bootsfaces.component.commandButton;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import net.bootsfaces.C;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.component.ajax.IAJAXComponent2;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author thecoder4.eu
 */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(CommandButton.COMPONENT_TYPE)
public class CommandButton extends CommandButtonCore
		implements ClientBehaviorHolder, IHasTooltip, IAJAXComponent, IAJAXComponent2, IResponsive {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".commandButton.CommandButton";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	/**
	 * <p>
	 * Default renderer.
	 * </p>
	 */
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.commandButton.CommandButton";

	private static final Collection<String> EVENT_NAMES = Collections
			.unmodifiableCollection(Arrays.asList("blur", "change", "click", "dblclick", "focus", "keydown", "keypress",
					"keyup", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "select"));

	public CommandButton() {
		setRendererType(DEFAULT_RENDERER); // this component renders itself
		AddResourcesListener.addThemedCSSResource("core.css");
		Tooltip.addResourceFiles();
	}

	/**
	 * Provide support to snake-case attribute in EL-expression items
	 */
	@Override
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery callback
	 * or other non-standard means (such as the onclick event of b:tabView, which
	 * has to be implemented manually).
	 *
	 * @return
	 */
	public Map<String, String> getJQueryEvents() {
		return null;
	}

	/**
	 * Returns the parameter list of jQuery and other non-standard JS callbacks. If
	 * there's no parameter list for a certain event, the default is simply "event".
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterLists() {
		return null;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

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

	/**
	 * Returns the subset of the parameter list of jQuery and other non-standard JS
	 * callbacks which is sent to the server via AJAX. If there's no parameter list
	 * for a certain event, the default is simply null.
	 * 
	 * @return A hash map containing the events. May be null.
	 */
	@Override
	public Map<String, String> getJQueryEventParameterListsForAjax() {
		return null;
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
