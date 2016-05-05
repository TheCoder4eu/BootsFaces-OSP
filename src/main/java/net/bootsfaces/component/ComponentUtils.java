package net.bootsfaces.component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import net.bootsfaces.beans.ELTools;

@ManagedBean
@RequestScoped
public class ComponentUtils {
	private boolean renderLabelDefault = true;

	public ComponentUtils() {
		String renderLabel = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.defaults.renderLabel");
		if (null != renderLabel && renderLabel.contains("#{")) {
			renderLabelDefault = (Boolean) ELTools.evalAsObject(renderLabel);
		}
		else if (null != renderLabel) {
			renderLabelDefault = Boolean.valueOf(renderLabel);
		}
	}

	public static boolean isRenderLabelDefault() {
		ComponentUtils cu = (ComponentUtils) ELTools.evalAsObject("#{componentUtils}");
		return cu.renderLabelDefault;
	}
}
