package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

public class NextExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent grandparent = component.getParent();
			for (int i = 0; i < grandparent.getChildCount(); i++) {
				if (grandparent.getChildren().get(i) == parent) {
					i++;
					if (i < grandparent.getChildCount()) {
						result.add(grandparent.getChildren().get(i));
						break;
					}
				}
			}
		}
		if (result.size() > 0) {
			return result;
		}
		String componentList = " component(s): ";
		for (UIComponent parent : parentComponents) {
			componentList += parent.getClass().getSimpleName() + " id= " + parent.getId() + ", ";
		}

		if (componentList.endsWith(", ")) {
			throw new FacesException("Invalid search expression - there's no successor to the"
					+ componentList.substring(0, componentList.length() - 2) + ". Complete search expression: "
					+ originalExpression);
		}

		throw new FacesException(
				"Invalid search expression - there's no successor to the component. Complete search expression: "
						+ originalExpression);
	}

}
