package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;

/**
 * Collects everything following the current JSF node within the same branch of the tree.
 * It's like "@next @next:@next @next:@next:@next ...".
 */
public class AfterExpressionResolver implements AbstractExpressionResolver {
	/**
	 * Collects every JSF node following the current JSF node within the same branch of the tree.
	 * It's like "@next @next:@next @next:@next:@next ...".
	 */
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
		UIComponent grandparent = component.getParent();
		for (int i = 0; i < grandparent.getChildCount(); i++) {
			if (grandparent.getChildren().get(i) == parent) {
				i++;
				while (i<grandparent.getChildCount()) {
					result.add(grandparent.getChildren().get(i));
					i++;
				}
			}
		}
		}
		if (result.size() > 0) {
			return result;
		}
		throw new FacesException("Invalid search expression - there's no successor to the component " + originalExpression);
	}

}
