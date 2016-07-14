package net.bootsfaces.component.focus;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.expressions.ExpressionResolver;
import net.bootsfaces.utils.BsfUtils;

@FacesComponent("net.bootsfaces.component.focus.Focus")
public class Focus extends UIComponentBase {
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.focus.Focus";
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public Focus() {
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

		String target = (String) attrs.get("target");
		if(!BsfUtils.isStringValued(target) && this.getParent() != null)
			target = this.getParent().getId();

		if(BsfUtils.isStringValued(target)) {
			ResponseWriter rw = context.getResponseWriter();
			String itemToFocusID = ExpressionResolver.getComponentIDs(context, this, target);

			rw.startElement("script", this);
			rw.writeText("" + "$(document).ready(function() { " + "   var item = $(jq('" + itemToFocusID + "')); "
					+ "   if (item.is('div')) { " + "   	$(jq('input_" + itemToFocusID + "')).focus(); "
					+ "   } else { item.focus(); }" + "});", null);
			rw.endElement("script");
		} else {
			throw new FacesException("The focus component needs a defined target ID", null);
		}
	}
}
