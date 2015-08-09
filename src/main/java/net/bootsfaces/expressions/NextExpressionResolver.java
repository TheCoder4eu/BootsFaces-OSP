package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;

public class NextExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, String parentId, String currentId,
			String originalExpression, String[] parameters) {
		UIComponent parent = component.getParent();
		for (int i = 0; i < parent.getChildCount(); i++) {
			if (parent.getChildren().get(i) == component) {
				i++;
				if (i<parent.getChildCount()) {
					List<UIComponent> result = new ArrayList<UIComponent>();
					result.add(parent.getChildren().get(i));
					return result;
				}
			}
		}
		throw new FacesException("Invalid search expression - there's no successor to the component " + originalExpression);
	}

}
