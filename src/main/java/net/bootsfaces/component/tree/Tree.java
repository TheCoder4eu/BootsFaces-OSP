/**
 *  Copyright 2014-2019 Dario D'Urzo
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
package net.bootsfaces.component.tree;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.IResponsive;
import net.bootsfaces.utils.BsfUtils;

/**
 * This class holds the attributes of &lt;b:tree /&gt;.
 */
@ResourceDependencies({
    @ResourceDependency(library = "bsf", name = "js/bootstrap-treeview.min.js", target = "body"),})
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent("net.bootsfaces.component.tree.Tree")
public class Tree extends TreeCore implements ClientBehaviorHolder, IResponsive {

    // Static internal references
    public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".tree.Tree";
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    public static final String DEFAULT_RENDERER = "net.bootsfaces.component.tree.Tree";

    // Supported event list
    private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click"));

    /**
     * Constructor. It is used to configure the renderer
     */
    public Tree() {
        setRendererType(DEFAULT_RENDERER);

        AddResourcesListener.addThemedCSSResource("core.css");
        AddResourcesListener.addExtCSSResource("bootstrap-treeview.min.css");
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
     * Provide support to snake-case attribute in EL-expression items
     *
     * @param name
     * @param binding
     */
    @Override
    public void setValueExpression(String name, ValueExpression binding) {
        name = BsfUtils.snakeCaseToCamelCase(name);
        super.setValueExpression(name, binding);
    }

    /**
     * Management of events
     *
     * @return
     */
    @Override
    public Collection<String> getEventNames() {
        return EVENT_NAMES;
    }

    @Override
    public String getDefaultEventName() {
        return "click";
    }
}
