package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;

public class FormExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, String parentId, String currentId, String originalExpression) {
		
		UIComponent c = component;
		
		while (c != null && c.getClass() != UIViewRoot.class) {
			System.out.println(c.getClass().getName());
			if (UIForm.class.isAssignableFrom(c.getClass())) {
				List<UIComponent> result = new ArrayList<UIComponent>();
				result.add(c);
				return result;
			}
			c = c.getParent();
		}
		throw new FacesException("Invalid search expression - the component isn't inside a form " + originalExpression);
		
	}

}
