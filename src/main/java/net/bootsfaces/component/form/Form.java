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

package net.bootsfaces.component.form;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;
import javax.faces.event.PreRenderComponentEvent;

import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:form /&gt;. */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class),
		@ListenerFor(systemEventClass = PreRenderComponentEvent.class) })
@FacesComponent(Form.COMPONENT_TYPE)
public class Form extends FormCore implements ClientBehaviorHolder {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.form.Form";

	public static final String COMPONENT_FAMILY = "javax.faces.Form";

	public static final String DEFAULT_RENDERER = "javax.faces.Form";

	public Form() {
		setRendererType(DEFAULT_RENDERER);
	}

	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (event instanceof PostAddToViewEvent) {
			if (isAutoUpdate()) {
				if (FacesContext.getCurrentInstance().isPostback()) {
					FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
				}
				super.processEvent(event);
			}
		} else if (event instanceof PreRenderComponentEvent) {

			String clazz = super.getStyleClass();
			if (isHorizontal()) {
				if (clazz == null) {
					clazz = "form-horizontal";
				} else {
					clazz += " form-horizontal";
				}
				setStyleClass(clazz);
			}
			if (isInline()) {
				if (clazz == null) {
					clazz = "form-inline";
				} else {
					clazz += " form-inline";
				}
				setStyleClass(clazz);
			}
		}
	}

	/**
	 * Manage EL-expression for snake-case attributes
	 */
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public void setStyle(String _style) {
		// TODO Auto-generated method stub
		super.setStyle(_style);
	}

	public void encodeBegin(FacesContext context) throws IOException {
		if (isHorizontal() && isInline()) {
			throw new FacesException(
					"A b:form can't be form both inline and horizontal. Please set only one of them for form \""
							+ getClientId() + "\".");
		}
		super.encodeBegin(context);
	}
}
