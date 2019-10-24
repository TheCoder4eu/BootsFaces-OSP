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

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.C;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.IContentDisabled;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author Guillermo González de Agüero
 */
@FacesRenderer(componentFamily = FormGroupRenderer.COMPONENT_FAMILY, rendererType = FormGroupRenderer.RENDERER_TYPE)
public class FormGroupRenderer extends CoreRenderer {

    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    public static final String RENDERER_TYPE = C.BSFCOMPONENT + ".formGroup.FormGroup";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }

        FormGroup formGroup = (FormGroup) component;
        ResponseWriter rw = context.getResponseWriter();
        String clientId = formGroup.getClientId(context);

        rw.startElement("div", formGroup);
        Tooltip.generateTooltip(context, formGroup, rw);
        String dir = formGroup.getDir();
        if (null != dir) {
            rw.writeAttribute("dir", dir, "dir");
        }
        String s = "form-group";
        String sclass = formGroup.getStyleClass();
        if (sclass != null) {
            s += " " + sclass;
        }
        rw.writeAttribute("id", clientId, "id");
        String style = formGroup.getStyle();
        if (style != null) {
            rw.writeAttribute("style", style, "style");
        }
        rw.writeAttribute("class", s, "class");

        Tooltip.activateTooltips(context, formGroup);
        beginDisabledFieldset(formGroup, rw);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        ResponseWriter rw = context.getResponseWriter();
        endDisabledFieldset((IContentDisabled) component, rw);
        rw.endElement("div");
    }
}
