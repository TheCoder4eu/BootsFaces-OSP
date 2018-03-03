package net.bootsfaces.expressions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class SearchExpressionResolverBean {
	public String resolve(UIComponent component, String expression) {
		String componentIDs = ExpressionResolver.getComponentIDs(FacesContext.getCurrentInstance(), component, expression);
		if (componentIDs.indexOf(' ')>=0) {
			// multiple component ids
			componentIDs = componentIDs.replace(" ", " :");
		}
		return ":"+componentIDs;
	}
}