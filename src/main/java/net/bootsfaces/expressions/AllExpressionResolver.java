package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

public class AllExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId, String originalExpression, String[] parameters) {
		
		UIComponent c = component;
		
		while (c.getParent() != null) {
			c = c.getParent();
		}
		List<UIComponent> result = new ArrayList<UIComponent>();
		result.add(c);
		return result;
		
	}

}
