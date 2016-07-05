package net.bootsfaces.component.fullCalendar;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;
import net.bootsfaces.utils.BsfUtils;

/**
 * 
 * @author jottyfan
 *
 */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "js/moment.min.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/fullcalendar.min.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/fullcalendar.min.css", target = "head") })
@FacesComponent("net.bootsfaces.component.fullCalendar.FullCalendar")
public class FullCalendar extends FullCalendarCore implements net.bootsfaces.render.IHasTooltip, net.bootsfaces.render.IResponsive {

    public static final String COMPONENT_TYPE = "net.bootsfaces.component.fullCalendar.FullCalendar";

    public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

    public static final String DEFAULT_RENDERER = "net.bootsfaces.component.fullCalendar.FullCalendar";

    public FullCalendar() {
        Tooltip.addResourceFiles();
        AddResourcesListener.addThemedCSSResource("core.css");
        AddResourcesListener.addThemedCSSResource("bsf.css");
        setRendererType(DEFAULT_RENDERER);
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
}
