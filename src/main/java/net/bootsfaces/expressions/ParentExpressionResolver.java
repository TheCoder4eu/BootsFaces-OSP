package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;

public class ParentExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, String parentId, String currentId,
			String originalExpression) {
		if (component.getParent() != null) {
			List<UIComponent> result = new ArrayList<UIComponent>();
			result.add(component.getParent());
			return result;
		}
		throw new FacesException("Invalid search expression - the component isn't inside a form " + originalExpression);
	}

}
