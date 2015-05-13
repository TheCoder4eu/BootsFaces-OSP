package net.bootsfaces.render;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;

/**
 * Renders a tooltip.
 * @author Stephan Rauh
 */
public class Tooltip {
	public static void generateTooltip(FacesContext context, Map<String, Object> attrs, ResponseWriter rw)
			throws IOException {
		String tooltip = (String)attrs.get("tooltip");
		if (null != tooltip) {
			String position = (String)attrs.get("tooltipPosition");
			if (null == position) position=(String)attrs.get("tooltip-position");
			if (null == position) position="auto";
			boolean ok = "top".equals(position);
			ok |= "bottom".equals(position);
			ok |= "right".equals(position);
			ok |= "left".equals(position);
			ok |= "auto".equals(position);
			ok |= "auto top".equals(position);
			ok |= "auto bottom".equals(position);
			ok |= "auto right".equals(position);
			ok |= "auto left".equals(position);
			if (!ok) {
				position="bottom";
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong JSF markup", "Tooltip position must either be 'auto', 'top', 'bottom', 'left' or 'right'."));
			}
			rw.writeAttribute("data-toggle", "tooltip", null);
			rw.writeAttribute("data-placement", position, "data-placement");
			rw.writeAttribute("title", tooltip, null);
		}
	}

	public static void addResourceFile() {
//		if (null != getAttributes().get("tooltip")) {
        AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/tooltip.js");
//		}
	}

	public static void activateTooltips(FacesContext context, Map<String, Object> attributes) throws IOException {
		if (attributes.get("tooltip") != null) {
			String js = "$(function () {\n" + 
				  "$('[data-toggle=\"tooltip\"]').tooltip()\n" + 
			"});\n";
			context.getResponseWriter().write("<script type='text/javascript'>" + js + "</script>");
		}
		
	}
}
