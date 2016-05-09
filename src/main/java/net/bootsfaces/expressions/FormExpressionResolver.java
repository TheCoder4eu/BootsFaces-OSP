package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;

public class FormExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {

		List<UIComponent> result = new ArrayList<UIComponent>();
//		for (UIComponent parent : parentComponents) {
			UIComponent c = component;

			while (c != null && c.getClass() != UIViewRoot.class) {
				if (UIForm.class.isAssignableFrom(c.getClass())) {
					result.add(c);
					break;
				}
				c = c.getParent();
			}
//		}
		if (result.size() > 0) {
			return result;
		}
		throw new FacesException("Invalid search expression - the component isn't inside a form " + originalExpression);

	}

}
