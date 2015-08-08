package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

public class FindIdExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, String parentId, String currentId,
			String originalExpression, String[] parameters) {

		UIComponent searchRoot = component;
		if (parentId != null && parentId.length() > 0) {
			searchRoot = component.findComponent(parentId);
		}
			
		UIComponent c = findIdRecursively(searchRoot, parameters[0]);
		if (null != c) {
			List<UIComponent> result = new ArrayList<UIComponent>();
			result.add(c);
			return result;
		}

		throw new FacesException("Invalid search expression - couldn't find id " + currentId + ". Complete search expression: " + originalExpression);
	}

	public UIComponent findIdRecursively(UIComponent parent, String id) {
		if (id.equals(parent.getId())) {
			return parent;
		}
		Iterator<UIComponent> facetsAndChildren = parent.getFacetsAndChildren();
		while (facetsAndChildren.hasNext()) {
			UIComponent child = facetsAndChildren.next();
			UIComponent result = findIdRecursively(child, id);
			if (null != result) return result;
		}
		return null;
	}

}
