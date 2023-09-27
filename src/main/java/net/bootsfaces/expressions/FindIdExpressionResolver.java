package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.faces.FacesException;
import jakarta.faces.component.NamingContainer;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;

public class FindIdExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {

		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent searchRoot = parent;
			while ((!(searchRoot instanceof UIViewRoot)) && (!(searchRoot instanceof NamingContainer))) {
				searchRoot = searchRoot.getParent();
			}
				
			UIComponent c = findId(searchRoot, parameters[0]);
			if (null != c) {
				result.add(c);
			}
		}
		if (result.size() > 0) {
			return result;
		}

		throw new FacesException("Invalid search expression - couldn't find id " + currentId + ". Complete search expression: " + originalExpression);
	}

	public UIComponent findId(UIComponent parent, String id) {
		if (null==parent)
			return null;
		if (id.equals(parent.getId())) {
			return parent;
		}
		Iterator<UIComponent> facetsAndChildren = parent.getFacetsAndChildren();
		while (facetsAndChildren.hasNext()) {
			UIComponent child = facetsAndChildren.next();
			if (id.equals(child.getId())) {
				return child;
			}
		}
		return null;
	}
}
