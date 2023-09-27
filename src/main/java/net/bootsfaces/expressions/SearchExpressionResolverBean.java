package net.bootsfaces.expressions;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;

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