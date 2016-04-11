package net.bootsfaces.component.image;

import java.io.IOException;

import javax.faces.application.ProjectStage;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.H;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.FacesMessages;

@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.image.Image")
public class ImageRenderer extends CoreRenderer {

    @Override
    public void decode(FacesContext context, UIComponent component) {
        Image image = (Image) component;

        if (componentIsDisabledOrReadonly(image)) {
            return;
        }

        decodeBehaviors(context, image); // moved to AJAXRenderer
        new AJAXRenderer().decode(context, component);
    }

    /**
     * This methods generates the HTML code of the current b:image.
     *
     * @param context   the FacesContext.
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


        rw.startElement("img", image);
        Tooltip.generateTooltip(context, image, rw);
        rw.writeAttribute("id", clientId, "id");
        rw.writeURIAttribute("src", getImageSource(context, component, "value"), "value");

        renderPassThruAttributes(context, image, H.IMAGE);

        writeAttribute(rw, "class", image.getStyleClass(), "styleClass");

        AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), image, rw);

        rw.endElement("img");
        Tooltip.activateTooltips(context, image);
    }

    /**
     * <p>
     * Determine the path value of an image value.
     * </p>
     *
     * @param context the {@link FacesContext} for the current request.
     * @param component the component to obtain the image information from
     * @param attrName the attribute name that needs to be queried if the
     *  name and library attributes are not specified
     *
     * @return the encoded path to the image source
     */
    public static String getImageSource(FacesContext context, UIComponent component, String attrName) {
    	String resourceName = (String) component.getAttributes().get("name");
    	ResourceHandler handler = context.getApplication().getResourceHandler();
    	if(resourceName != null) {
    		String library = (String) component.getAttributes().get("library");
    		Resource res = handler.createResource(resourceName, library);
    		if(res == null) {
    			if (context.isProjectStage(ProjectStage.Development)) {
                    String msg = "Unable to find resource " + resourceName;
                    FacesMessages.error(component.getClientId(context), msg, msg);
                }
                return "RES_NOT_FOUND";
    		} else {
    			return (context.getExternalContext().encodeResourceURL(res.getRequestPath()));
    		}
    	} else {
	        String value = (String) component.getAttributes().get(attrName);
	        if (value == null || value.length() == 0) {
	            return "";
	        }
	        
	        if (handler.isResourceURL(value)) {
	            return value;
	        } else {
	            value = context.getApplication().getViewHandler().
	                    getResourceURL(context, value);
	            return (context.getExternalContext().encodeResourceURL(value));
	        }
    	}
    }
}
