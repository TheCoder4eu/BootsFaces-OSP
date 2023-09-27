package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import jakarta.faces.FacesException;
import jakarta.faces.component.NamingContainer;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;

public class InternalIDExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		
		for (UIComponent parent : parentComponents) {
				UIComponent c = findIdInNamingcontainer(component, currentId, result, parent);
				if (null == c && parent instanceof NamingContainer && parent.getParent()!=null) {
					c = findIdInNamingcontainer(component, currentId, result, parent.getParent());
				}
				if (null != c) {
					result.add(c);
				}
		}
		if (result.size() > 0) {
			return result;
		}
		throw new FacesException("ID not found: " + currentId + " search expression: " + originalExpression);
	}

	private UIComponent findIdInNamingcontainer(UIComponent component, String currentId, List<UIComponent> result,
			UIComponent parent) {
		while ((!(parent instanceof UIViewRoot)) && (!(parent instanceof NamingContainer))) {
			parent = parent.getParent();
		}
		
		String parentId = ExpressionResolverUtilities.determineQualifiedId(parent);
		String childId;
		if (parentId.length()==0)
			childId=currentId;
		else if (parentId.endsWith(":"))
			childId = parentId + currentId;
		else
			childId = parentId + ":" + currentId;
		
		UIComponent c = component.findComponent(childId);
		if (null == c) {
			c = parent.findComponent(childId);
		}
		if (null == c) {
			c = component.findComponent(":"+childId);
		}
		
		return c;
	}
}
