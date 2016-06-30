package net.bootsfaces.component.image;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.application.ProjectStage;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.ajax.AJAXRenderer;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Responsive;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.FacesMessages;

@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.image.Image")
public class ImageRenderer extends CoreRenderer {

	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.component.image.ImageRenderer");

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
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:image.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
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
		rw.writeURIAttribute("src", getImageSource(context, component), "value");

		renderPassThruAttributes(context, image, new String[] { "alt", "height", "lang", "style", "title", "width" });

		String styleClass = image.getStyleClass();
		if (null == styleClass)
			styleClass = Responsive.getResponsiveStyleClass(image, false);
		else
			styleClass += Responsive.getResponsiveStyleClass(image, false);
		if (styleClass != null && styleClass.trim().length() > 0) {
			writeAttribute(rw, "class", styleClass, "styleClass");
		}

		AJAXRenderer.generateBootsFacesAJAXAndJavaScript(FacesContext.getCurrentInstance(), image, rw);

		rw.endElement("img");
		Tooltip.activateTooltips(context, image);
	}

	/**
	 * <p>
	 * Determine the path value of an image value.
	 * </p>
	 *
	 * @param context
	 *            the {@link FacesContext} for the current request.
	 * @param component
	 *            the component to obtain the image information from
	 * @return the encoded path to the image source
	 */
	public static String getImageSource(FacesContext context, UIComponent component) {
		Image image = (Image) component;
		ResourceHandler handler = context.getApplication().getResourceHandler();
		String resourceName = image.getName();
		String value = image.getValue();
		if (value != null && value.length() > 0) {
			if (FacesContext.getCurrentInstance().isProjectStage(ProjectStage.Development)) {
				LOGGER.warning(
						"Please use either the 'value' attribute of b:image, or the 'name' and 'library' attribute pair. If all three attributes are provided, BootsFaces uses the 'value' attributes, ignoring both 'name' and 'library'.");
			}
			if (resourceName != null || image.getLibrary() != null) {

			}
			if (handler.isResourceURL(value)) {
				return value;
			} else {
				value = context.getApplication().getViewHandler().getResourceURL(context, value);
				return (context.getExternalContext().encodeResourceURL(value));
			}
		}

		String library = image.getLibrary();
		Resource res = handler.createResource(resourceName, library);
		if (res == null) {
			if (context.isProjectStage(ProjectStage.Development)) {
				String msg = "Unable to find resource " + resourceName;
				FacesMessages.error(component.getClientId(context), msg, msg);
			}
			return "RES_NOT_FOUND";
		} else {
			return (context.getExternalContext().encodeResourceURL(res.getRequestPath()));
		}
	}
}
