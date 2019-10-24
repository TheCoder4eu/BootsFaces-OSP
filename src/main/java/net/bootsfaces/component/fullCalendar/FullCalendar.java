package net.bootsfaces.component.fullCalendar;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.PostAddToViewEvent;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 *
 * @author jottyfan
 *
 */
@ListenersFor({ @ListenerFor(systemEventClass = PostAddToViewEvent.class) })
@FacesComponent(FullCalendar.COMPONENT_TYPE)
public class FullCalendar extends FullCalendarCore implements net.bootsfaces.render.IHasTooltip, net.bootsfaces.render.IResponsive {

    public static final String COMPONENT_TYPE = C.BSFCOMPONENT +".fullCalendar.FullCalendar";

    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public static final String DEFAULT_RENDERER = "net.bootsfaces.component.fullCalendar.FullCalendar";

    public FullCalendar() {
        Tooltip.addResourceFiles();
		AddResourcesListener.addBasicJSResource(C.BSF_LIBRARY, "js/moment-with-locales.min.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/fullcalendar.min.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/fullcalendar-lang-all.js");

        AddResourcesListener.addThemedCSSResource("core.css");
        AddResourcesListener.addExtCSSResource("fullcalendar.min.css");
        setRendererType(DEFAULT_RENDERER);
    }

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
     * Manage EL-expression for snake-case attributes
     */
    public void setValueExpression(String name, ValueExpression binding) {
        name = BsfUtils.snakeCaseToCamelCase(name);
        super.setValueExpression(name, binding);
    }

	public void setWeekMode(String weekMode) {
		if ("liquid".equals(weekMode) || "fixed".equals(weekMode) || "variable".equals(weekMode)) {
			getStateHelper().put(PropertyKeys.weekMode, weekMode);
		} else {
			getStateHelper().put(PropertyKeys.weekMode, "liquid");
		}
	}


}
