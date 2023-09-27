package net.bootsfaces.component;


import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import net.bootsfaces.beans.ELTools;

@ManagedBean
@RequestScoped
public class ComponentUtils {
	private boolean renderLabelDefault = true;

	public ComponentUtils() {
		try {
		String renderLabel = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.defaults.renderLabel");
		if (null != renderLabel && renderLabel.contains("#{")) {
			renderLabelDefault = (Boolean) ELTools.evalAsObject(renderLabel);
		}
		else if (null != renderLabel) {
			renderLabelDefault = Boolean.valueOf(renderLabel);
		}
		} catch (Exception e) {
			System.out.println("Exception when reading net.bootsfaces.defaults.renderLabel");
		}
	}

	public static boolean isRenderLabelDefault() {
		ComponentUtils cu = (ComponentUtils) ELTools.evalAsObject("#{componentUtils}");
		if (null == cu) {
			return true;
		}
		return cu.renderLabelDefault;
	}
}
