package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

/**
 * Collects everything preceding the current JSF node within the same branch of the tree.
 * It's like "@previous previous:@previous previous:@previous:@previous ...".
 */
public class BeforeExpressionResolver implements AbstractExpressionResolver {
	private static final String ERROR_MESSAGE = "Invalid search expression - there's no predecessor to the component ";

	/**
	 * Collects everything preceding the current JSF node within the same branch of the tree.
	 * It's like "@previous previous:@previous previous:@previous:@previous ...".
	 */
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent grandparent = component.getParent();
			for (int i = 0; i < grandparent.getChildCount(); i++) {
				if (grandparent.getChildren().get(i) == parent) {
						if(i == 0) //if this is the first element of this component tree level there is no previous
							throw new FacesException(ERROR_MESSAGE + originalExpression);
						//otherwise take the components before this one
						while ((--i)>=0) {
							result.add(grandparent.getChildren().get(i));
						}
						return result;
				}
			}
		}

		throw new FacesException(ERROR_MESSAGE + originalExpression);
	}

}
