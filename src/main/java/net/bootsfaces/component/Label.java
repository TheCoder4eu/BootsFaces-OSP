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

package net.bootsfaces.component;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.render.A;
import net.bootsfaces.render.Tooltip;

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/labels.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")})
@FacesComponent(C.LABEL_COMPONENT_TYPE)
public class Label extends UIComponentBase {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.LABEL_COMPONENT_TYPE;
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public Label() {
		setRendererType(null); // this component renders itself
		Tooltip.addResourceFile();
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		if (!this.isRendered()) {
			return;
		}
		/*
		 * <span class="badge badge-important">6</span>
		 */

		Map<String, Object> attrs = this.getAttributes();

		String sev = A.asString(attrs.get("severity"));
		String txt = A.asString(attrs.get("text"));
		ResponseWriter rw = context.getResponseWriter();

		rw.startElement("span", this);
		rw.writeAttribute("id", this.getClientId(), "id");
		Tooltip.generateTooltip(context, this.getAttributes(), rw);
		String sclass = "label" + " " + "label";
		if (sev != null) {
			sclass += "-" + sev;
		} else {
			sclass += "-default";
		}
		rw.writeAttribute("class", sclass, "class");
		rw.writeText(txt, null);
		rw.endElement("span");
		Tooltip.activateTooltips(context, this.getAttributes(), this);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
