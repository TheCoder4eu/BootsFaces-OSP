package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

public class PropertyExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		if (null==parameters || parameters.length!=1) {
			throw new FacesException("The @property search expression requires a parameter! " + originalExpression);
		}
		String propertyName = "#{" + parameters[0] + "}";
		List<UIComponent> result = null;
		for (UIComponent p:parentComponents) {
			List<UIComponent> r = findPropertyRecursively(p, propertyName);
			if (result==null) {
				result=r;
			} else if (r!=null) {
				result.addAll(r);
			}
			
		}
		return result;
	}

	public List<UIComponent> findPropertyRecursively(UIComponent parent, String propertyName) {
		if (null==parent)
			return null;
		ValueExpression vex = parent.getValueExpression("value");
		if (null != vex) {
			String expression = vex.getExpressionString();
			if (expression != null && expression.equals(propertyName)) {
				List<UIComponent> result = new ArrayList<UIComponent>(1);
				result.add(parent);
				return result;
			}
		}
		Iterator<UIComponent> facetsAndChildren = parent.getFacetsAndChildren();
		List<UIComponent> result = null;

		while (facetsAndChildren.hasNext()) {
			UIComponent child = facetsAndChildren.next();
			List<UIComponent> hit = findPropertyRecursively(child, propertyName);
			if (null != hit) {
				if (null==result) {
					result = hit;
				} else {
					result.addAll(hit);
				}
			}
		}
		return result;
	}
}
