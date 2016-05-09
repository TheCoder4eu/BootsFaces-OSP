package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;

public class FindPartialIdRecursiveExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {

		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent searchRoot = parent;
			while ((!(searchRoot instanceof UIViewRoot)) && (!(searchRoot instanceof NamingContainer))) {
				searchRoot = searchRoot.getParent();
			}
				
			List<UIComponent> c = findIdRecursively(searchRoot, parameters[0]);
			if (null != c) {
				result.addAll(c);
			}
		}
		if (result.size() > 0) {
			return result;
		}

		throw new FacesException("Invalid search expression - couldn't find id " + currentId + ". Complete search expression: " + originalExpression);
	}

	public List<UIComponent> findIdRecursively(UIComponent parent, String id) {
		if (null==parent)
			return null;
		List<UIComponent> result = new ArrayList<UIComponent>();
		boolean found = matches(id, parent);
		if (found) {
			result.add(parent);
		}
		Iterator<UIComponent> facetsAndChildren = parent.getFacetsAndChildren();
		while (facetsAndChildren.hasNext()) {
			UIComponent child = facetsAndChildren.next();
			List<UIComponent> subresult = findIdRecursively(child, id);
			if (null != subresult) result.addAll(subresult);
		}
		return result;
	}

	private boolean matches(String id, UIComponent child) {
		boolean found=false;
		if (id.startsWith("*") && id.endsWith("*")) {
			String search=id.substring(1, id.length()-1);
			if (child.getId() != null && child.getId().contains(search)) {
				found=true;
			}
		} else if (id.endsWith("*")) {
			String search=id.substring(0, id.length()-1);
			if (child.getId() != null && child.getId().startsWith(search)) {
				found=true;
			}
		} else if (id.startsWith("*")) {
			String search=id.substring(1);
			if (child.getId() != null && child.getId().endsWith(search)) {
				found=true;
			}
		}
		else if (id.equals(child.getId())) {
			found=true;
		}
		return found;
	}
}
