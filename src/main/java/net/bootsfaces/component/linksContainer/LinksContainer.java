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

package net.bootsfaces.component.linksContainer;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 * 
 * @author thecoder4eu
 */
public abstract class LinksContainer extends UIComponentBase implements IContentDisabled {
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public LinksContainer() {
		setRendererType(null); // this component renders itself
		Tooltip.addResourceFiles();
		AddResourcesListener.addThemedCSSResource("core.css");
		//AddResourcesListener.addThemedCSSResource("bsf.css");
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public void encodeBegin(FacesContext fc) throws IOException {
		if (!isRendered()) {
			return;
		}
		/*
		 * <ul class="?"> ... </ul>
		 */

		ResponseWriter rw = fc.getResponseWriter();

		Map<String, Object> attrs = getAttributes();

		String pull = A.asString(attrs.get("pull"));

		rw.startElement("ul", this);
		rw.writeAttribute("id", getClientId(fc), "id");
		String style = (String) attrs.get("style");
		if (null != style && style.length() > 0) {
			rw.writeAttribute("style", style, "style");
		}
		Tooltip.generateTooltip(fc, this, rw);
        String containerStyles=getContainerStyles();
		String styleClass = (String) attrs.get("styleClass");
		if (null == styleClass) {
			styleClass = "";
		}

		if (pull != null && (pull.equals("right") || pull.equals("left"))) {
                        if(containerStyles.contains("navbar")) {
                            containerStyles=containerStyles.concat(" navbar-"+pull);
                        } else {
                            containerStyles=containerStyles.concat(" pull-"+pull);
                        }
		} /*else {
			rw.writeAttribute("class", styleClass.concat(" ").concat(getContainerStyles()), "class");
		}*/
                rw.writeAttribute("class", containerStyles.concat(" ").concat(styleClass), "class");
	}

	/**
	 * every container must override this method returning the specific
	 * class(es) for its rendering
	 *
	 * @return the specific class
	 */
	protected String getContainerStyles() {
		throw new UnsupportedOperationException("Please Extend this class.");
	}

	@Override
	public void encodeEnd(FacesContext fc) throws IOException {
		fc.getResponseWriter().endElement("ul");
		Tooltip.activateTooltips(fc, this);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	/**
	 * Enables or disables every child element of this container. By default, child elements are enabled. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isContentDisabled() {
		return (boolean) (Boolean) getStateHelper().eval("contentDisabled", false);
	}

	/**
	 * Enables or disables every child element of this container. By default, child elements are enabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setContentDisabled(boolean _contentDisabled) {
		getStateHelper().put("contentDisabled", _contentDisabled);
	}

	/**
	 * Setting this flag updates the widget on every AJAX request. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isAutoUpdate() {
		return (boolean) (Boolean) getStateHelper().eval("autoUpdate", false);
	}

	/**
	 * Setting this flag updates the widget on every AJAX request. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAutoUpdate(boolean _autoUpdate) {
		getStateHelper().put("autoUpdate", _autoUpdate);
	}

}
