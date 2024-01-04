package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.faces.FacesException;
import jakarta.faces.component.NamingContainer;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;

public class FindIdRecursiveExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {

		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent searchRoot = parent;
			while ((!(searchRoot instanceof UIViewRoot)) && (!(searchRoot instanceof NamingContainer))) {
				searchRoot = searchRoot.getParent();
			}
				
			List<UIComponent> c = findIdsRecursively(searchRoot, parameters[0]);
			if (null != c) {
				result.addAll(c);
			}
		}
		if (result.size() > 0) {
			return result;
		}

		throw new FacesException("Invalid search expression - couldn't find id " + currentId + ". Complete search expression: " + originalExpression);
	}

	public List<UIComponent> findIdsRecursively(UIComponent parent, String id) {
		if (null==parent)
			return null;
		List<UIComponent> result = null;
		if (id.equals(parent.getId())) {
			result = new ArrayList<UIComponent>();
			result.add(parent);
		}
		Iterator<UIComponent> facetsAndChildren = parent.getFacetsAndChildren();
		while (facetsAndChildren.hasNext()) {
			UIComponent child = facetsAndChildren.next();
			List<UIComponent> childresult = findIdsRecursively(child, id);
			if (null != childresult && (!childresult.isEmpty())) {
				if (null == result) {
					result = new ArrayList<UIComponent>();
				}
				result.addAll(childresult);
			}
		}
		return result;
	}
}
