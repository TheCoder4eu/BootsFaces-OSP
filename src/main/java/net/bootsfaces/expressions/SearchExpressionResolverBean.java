package net.bootsfaces.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import net.bootsfaces.expressions.ExpressionResolver;

@ManagedBean
@RequestScoped
public class SearchExpressionResolverBean {
	public String resolve(UIComponent component, String expression) {
		String componentIDs = ExpressionResolver.getComponentIDs(FacesContext.getCurrentInstance(), component, expression);
		return ":"+componentIDs;
	}

}
