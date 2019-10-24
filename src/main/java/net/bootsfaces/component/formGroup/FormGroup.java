/**
 * Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu)
 *
 * This file is part of BootsFaces.
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
package net.bootsfaces.component.formGroup;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.component.row.Row;

/**
 *
 * @author Guillermo González de Agüero
 */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(FormGroup.COMPONENT_TYPE)
public class FormGroup extends Row {

    public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".formGroup.FormGroup";
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    public static final String DEFAULT_RENDERER = FormGroupRenderer.RENDERER_TYPE;

    public FormGroup() {
        setRendererType(DEFAULT_RENDERER);
    }


	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		if (isAutoUpdate()) {
			if (FacesContext.getCurrentInstance().isPostback()) {
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(getClientId());
			}
 	 		super.processEvent(event);
 	 	}
	}

}
