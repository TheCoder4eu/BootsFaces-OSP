package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;

public class IDExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, String parentId, String currentId, String originalExpression) {
		String childId;
		if (parentId.endsWith(":"))
			childId = parentId + ":" + currentId;
		else if (parentId.length() > 0)
			childId = parentId + ":" + currentId;
		else
			childId = currentId;
		UIComponent c = component.findComponent(childId);
		List<UIComponent> result = new ArrayList<UIComponent>();
		result.add(c);
		return result;
	}

}
