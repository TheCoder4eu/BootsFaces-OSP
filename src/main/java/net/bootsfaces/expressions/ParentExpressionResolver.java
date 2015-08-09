package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

public class ParentExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			if (parent.getParent() != null) {
				result.add(parent.getParent());
			}
		}
		if (result.size() > 0) {
			return result;
		}
		throw new FacesException("Invalid search expression - the component isn't inside a form " + originalExpression);
	}

}
