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
			throw new FacesException("The @styleClass search expression requires parameter! " + originalExpression);
		}
		List<UIComponent> result = new ArrayList<UIComponent>(1);
		UIComponent r = findStyleClassRecursively(component, parameters[0]);
		if (null != r)
			result.add(r);
		return result;


	}

	public UIComponent findStyleClassRecursively(UIComponent parent, String styleClass) {
		if (null==parent)
			return null;
		String sc = getStyleClass(parent);
		if (sc != null && sc.contains(styleClass)) {
			return parent;
		}
		Iterator<UIComponent> facetsAndChildren = parent.getFacetsAndChildren();
		while (facetsAndChildren.hasNext()) {
			UIComponent child = facetsAndChildren.next();
			UIComponent result = findStyleClassRecursively(child, styleClass);
			if (null != result) return result;
		}
		return null;
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
