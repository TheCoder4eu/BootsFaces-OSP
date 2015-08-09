package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

public class NoneExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {

		List<UIComponent> result = new ArrayList<UIComponent>();
		return result;

	}

}
