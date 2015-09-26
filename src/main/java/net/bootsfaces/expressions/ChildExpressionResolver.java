package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

public class ChildExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		if (null==parameters || parameters.length!=1) {
			throw new FacesException("The @child search expression required a numerical parameter! " + originalExpression);
		}
		try {
		int index = new Integer(parameters[0]).intValue();
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			result.add(parent.getChildren().get(index));
		}
		if (result.size() > 0) {
			return result;
		}
		throw new FacesException("Error processing the @child search expression. " + originalExpression);
		}
		catch (NumberFormatException nfe) {
			throw new FacesException("The @child search expression required a numerical parameter! " + originalExpression);
		}
		catch (ArrayIndexOutOfBoundsException nfe) {
			throw new FacesException("The component doesn't have so many children! " + originalExpression);
		}
	}
}
