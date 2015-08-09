package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

public class PreviousExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent grandparent = component.getParent();
			for (int i = 0; i < grandparent.getChildCount(); i++) {
				if (grandparent.getChildren().get(i) == parent) {
					i--;
					if (i >= 0) {
						result.add(grandparent.getChildren().get(i));
						break;
					}
				}
			}
		}
		if (result.size() > 0) {
			return result;
		}
		throw new FacesException(
				"Invalid search expression - there's no predecessor to the component " + originalExpression);
	}

}
