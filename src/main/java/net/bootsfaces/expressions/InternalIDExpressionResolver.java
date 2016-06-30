package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;

public class InternalIDExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		
		for (UIComponent parent : parentComponents) {
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
					c = component.findComponent(":"+childId);
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

}
