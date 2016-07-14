package net.bootsfaces.component.defaultCommand;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.utils.BsfUtils;

@FacesComponent("net.bootsfaces.component.defaultCommand.DefaultCommand")
public class DefaultCommand extends UIComponentBase {
	public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".DefaultCommand";

	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public DefaultCommand() {
		setRendererType(null); // this component renders itself
		// AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY,
		// "jq/jquery.js");
	}

	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		// super.encodeBegin(context);
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		Map<String, Object> attrs = getAttributes();

		final UIForm form = BsfUtils.getClosestForm(this);
		if (form == null) {
			throw new FacesException("The default command component must be inside a form", null);
		} else {
			String target = (String)attrs.get("target");
			if(BsfUtils.isStringValued(target)) {
				ResponseWriter rw = context.getResponseWriter();
				String formId = form.getClientId();
				String actionCommandId = ExpressionResolver.getComponentIDs(context, this, target);

				rw.startElement("script", this);

				rw.writeText("" + "$(function() { " + "    $('form#"
						+ BsfUtils.escapeJQuerySpecialCharsInSelector(formId) + " :input').keypress(function (e) { "
						+ "    if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) { "
						+ "        document.getElementById('" + actionCommandId + "').click();return false; "
						+ "    } else { " + "        console.log('keycode not 13'); " + "        return true; "
						+ "    } " + "    }); " + "});", null);
				rw.endElement("script");
			} else {
				throw new FacesException("The default command component needs a defined target ID", null);
			}
		}
	}

}
