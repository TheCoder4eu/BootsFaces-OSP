package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import jakarta.faces.FacesException;
import jakarta.faces.component.UIComponent;

import net.bootsfaces.component.column.Column;

public class NextExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent grandparent = parent.getParent();
			UIComponent successor = findSuccessor(parent);
			if (successor == null) {
				if (grandparent instanceof Column) {
					// check the column after that column. Intuitively, that's the successor.
					UIComponent nextColumn = findSuccessor(grandparent);
					if (null != nextColumn) {
						if (nextColumn.getChildCount()>0) {
							successor=nextColumn.getChildren().get(nextColumn.getChildCount()-1);
						}
					}
				}
			}
			if (successor != null) {
				result.add(successor);
			}

		}
		if (result.size() > 0) {
			return result;
		}
		String componentList = " component(s): ";
		for (UIComponent parent : parentComponents) {
			componentList += parent.getClass().getSimpleName() + " id= " + parent.getId() + ", ";
		}

		if (componentList.endsWith(", ")) {
			throw new FacesException("Invalid search expression - there's no successor to "
					+ componentList.substring(0, componentList.length() - 2) + ". Complete search expression: "
					+ originalExpression);
		}

		throw new FacesException(
				"Invalid search expression - there's no successor to the component. Complete search expression: "
						+ originalExpression);
	}

	private UIComponent findSuccessor(UIComponent parent) {
		UIComponent grandparent = parent.getParent();
		for (int i = 0; i < grandparent.getChildCount(); i++) {
			if (grandparent.getChildren().get(i) == parent) {
				i++;
				if (i < grandparent.getChildCount()) {
					return grandparent.getChildren().get(i);

				}
			}
		}
		return null;
	}

}
