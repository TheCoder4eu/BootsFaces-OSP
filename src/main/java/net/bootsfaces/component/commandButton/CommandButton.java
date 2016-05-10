/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
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

package net.bootsfaces.component.commandButton;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;

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
@FacesComponent(CommandButton.COMPONENT_TYPE)
public class CommandButton extends CommandButtonCore 
implements ClientBehaviorHolder, IHasTooltip, IAJAXComponent, IAJAXComponent2, IResponsive {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.commandButton.CommandButton";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

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
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).
	 *
	 * @return
	 */
	public Map<String, String> getJQueryEvents() {
		return null;
	}

	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
}

