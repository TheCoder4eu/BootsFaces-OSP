package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;

public class AllExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, String parentId, String currentId, String originalExpression) {
		
		UIComponent c = component;
		
		while (c.getParent() != null) {
			c = c.getParent();
		}
		List<UIComponent> result = new ArrayList<UIComponent>();
		result.add(c);
		return result;
		
	}

}
