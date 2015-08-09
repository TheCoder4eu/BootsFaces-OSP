package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;

public class PreviousExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, String parentId, String currentId,
			String originalExpression, String[] parameters) {
		UIComponent parent = component.getParent();
		for (int i = 0; i < parent.getChildCount(); i++) {
			if (parent.getChildren().get(i) == component) {
				i--;
				if (i>=0) {
					List<UIComponent> result = new ArrayList<UIComponent>();
					result.add(parent.getChildren().get(i));
					return result;
				}
			}
		}
		throw new FacesException("Invalid search expression - there's no predecessor to the component " + originalExpression);
	}

}
