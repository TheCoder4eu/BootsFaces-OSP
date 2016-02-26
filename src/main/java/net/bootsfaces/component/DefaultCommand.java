package net.bootsfaces.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

@FacesComponent(C.DEFAULT_COMMAND_COMPONENT_TYPE)
public class DefaultCommand extends UIComponentBase {
	public static final String COMPONENT_TYPE = C.DEFAULT_COMMAND_COMPONENT_TYPE;

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	private Map<String, Object> attributes;

	public DefaultCommand() {
		setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		//		super.encodeBegin(context);
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		Map<String, Object> attrs = getAttributes();

		final UIForm form = getForm(this);
		if(form == null) {
			throw new FacesException("The default command component must be inside a form", null);
		} else {
			String target = (String)attrs.get("target");
			if(BsfUtils.StringIsValued(target)) {
				ResponseWriter rw = context.getResponseWriter();
				String formId = form.getClientId();
				String actionCommandId = getComponentClientId(target);
			
				rw.startElement("script", this);
				
				rw.writeText("" +
							"$(function() { " +
							"    $('form#" + BsfUtils.EscapeJQuerySpecialCharsInSelector(formId) + " :input').keypress(function (e) { " +
							"    if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) { " +
							"        document.getElementById('" + actionCommandId + "').click();return false; " + 
							"    } else { " +
							"        console.log('keycode not 13'); " + 
							"        return true; " +
							"    } " +
							"    }); " +
							"});", null);
				rw.endElement("script");
			} else {
				throw new FacesException("The default command component needs a defined target ID", null);
			}
		}
	}

	/**
	 * Get the related form
	 * @param component
	 * @return
	 */
	private UIForm getForm(UIComponent component) {
		while (component != null) {
			if (component instanceof UIForm) {
				break;
			}
			component = component.getParent();
		}
		return (UIForm) component;
	}

	/**
	 * Returns the clientId for a component with id="foo"
	 */
	public String getComponentClientId(final String componentId) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();

		UIComponent c = findComponent(root, componentId);
		return c.getClientId(context);
	}

	/**
	 * Finds component with the given id
	 */
	private UIComponent findComponent(UIComponent c, String id) {
		if (id.equals(c.getId())) {
			return c;
		}
		Iterator<UIComponent> kids = c.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent found = findComponent(kids.next(), id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}
}
