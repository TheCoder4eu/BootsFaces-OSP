package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

public class PreviousExpressionResolver implements AbstractExpressionResolver {
	private static final String ERROR_MESSAGE = "Invalid search expression - there's no predecessor to the ";

	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent grandparent = component.getParent();
			for (int i = 0; i < grandparent.getChildCount(); i++) {
				if (grandparent.getChildren().get(i) == parent) {
						if(i == 0) //if this is the first element of this component tree level there is no previous
							throw new FacesException(ERROR_MESSAGE + originalExpression);
						//otherwise take the component before this one
						result.add(grandparent.getChildren().get(i-1));
						return result;
				}
			}
		}

		String componentList=" component(s): ";
		for (UIComponent parent : parentComponents) {
			componentList += parent.getClass().getSimpleName() + " id= " + parent.getId()+ ", ";
		}

		if (componentList.endsWith(", ")) {
			throw new FacesException(ERROR_MESSAGE + componentList.substring(0, componentList.length()-2) + " complete search expression: " + originalExpression);
		}

		throw new FacesException(ERROR_MESSAGE + "component " + " complete search expression: " + originalExpression);
	}

}
