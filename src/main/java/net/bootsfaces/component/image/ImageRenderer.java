package net.bootsfaces.component.image;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;


@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.image.Image")
public class ImageRenderer extends CoreRenderer {

    /**
     * This methods generates the HTML code of the current b:image.
     * @param context the FacesContext.
     * @param component the current b:image.
     * @throws IOException thrown if something goes wrong when writing the HTML code.
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        Image image = (Image) component;
        ResponseWriter rw = context.getResponseWriter();
        String clientId = image.getClientId();

        // put custom code here
        // Simple demo widget that simply renders every attribute value
        rw.startElement("image", image);
        Tooltip.generateTooltip(context, image, rw);




        rw.endElement("image");
        Tooltip.activateTooltips(context, image);
    }
