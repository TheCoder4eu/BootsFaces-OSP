/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.component.formGroup;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import net.bootsfaces.C;
import net.bootsfaces.render.CoreRenderer;
import static net.bootsfaces.render.CoreRenderer.beginDisabledFieldset;
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
        String clientId = formGroup.getClientId();

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
        beginDisabledFieldset((IContentDisabled) component, rw);
        rw.endElement("div");
    }
}
