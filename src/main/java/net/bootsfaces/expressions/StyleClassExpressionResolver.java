package net.bootsfaces.expressions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

public class StyleClassExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		if (null==parameters || parameters.length!=1) {
			throw new FacesException("The @styleClass search expression requires a parameter! " + originalExpression);
		}
		List<UIComponent> result = null;
		for (UIComponent p:parentComponents) {
			List<UIComponent> r = findStyleClassRecursively(p, parameters[0]);
			if (result==null) {
				result=r;
			} else if (r!=null) {
				result.addAll(r);
			}
			
		}
		return result;
	}

	public List<UIComponent> findStyleClassRecursively(UIComponent parent, String styleClass) {
		if (null==parent)
			return null;
		String sc = getStyleClass(parent);
		if (sc != null && sc.contains(styleClass)) {
			List<UIComponent> result = new ArrayList<UIComponent>(3);
			result.add(parent);
			return result;
		}
		Iterator<UIComponent> facetsAndChildren = parent.getFacetsAndChildren();
		List<UIComponent> result = null;

		while (facetsAndChildren.hasNext()) {
			UIComponent child = facetsAndChildren.next();
			List<UIComponent> hit = findStyleClassRecursively(child, styleClass);
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

	public String getStyleClass(Object component) {
		try {
			Method method = component.getClass().getMethod("getStyleClass");
			return (String)method.invoke(component);
		} catch (NoSuchMethodException e) {
			// deliberately ignored
			return null;
		} catch (SecurityException e) {
			// deliberately ignored
			return null;
		} catch (IllegalAccessException e) {
			// deliberately ignored
			return null;
		} catch (IllegalArgumentException e) {
			// deliberately ignored
			return null;
		} catch (InvocationTargetException e) {
			// deliberately ignored
			return null;
		}
	}
}
