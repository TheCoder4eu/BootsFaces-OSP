package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class RootExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>(1);
		result.add(FacesContext.getCurrentInstance().getViewRoot());
		return result;
	}
}
