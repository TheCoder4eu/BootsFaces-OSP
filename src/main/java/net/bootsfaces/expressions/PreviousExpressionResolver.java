package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import jakarta.faces.FacesException;
import jakarta.faces.component.UIComponent;

import net.bootsfaces.component.column.Column;

public class PreviousExpressionResolver implements AbstractExpressionResolver {
	private static final String ERROR_MESSAGE = "Invalid search expression - there's no predecessor of the current widget.";

	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		for (UIComponent parent : parentComponents) {
			UIComponent predecessor = findPredecessor(parent);
			if (predecessor == null) {
				UIComponent grandparent = parent.getParent();
				if (grandparent instanceof Column) {
					// check the column before that column. Intuitively, that's the predecessor.
					UIComponent previousColumn = findPredecessor(grandparent);
					if (null != previousColumn) {
						if (previousColumn.getChildCount()>0) {
							predecessor=previousColumn.getChildren().get(previousColumn.getChildCount()-1);
						}
					}
				}
			}
			if (predecessor == null) {
				throw new FacesException(ERROR_MESSAGE + originalExpression);
			} else {
			  result.add(predecessor);
			}
		}
		
		if (result.size() > 0) {
			return result;
		}

		String componentList=" component(s): ";
		for (UIComponent parent : parentComponents) {
			componentList += parent.getClass().getSimpleName() + " id= " + parent.getId()+ ", ";
		}

		if (componentList.endsWith(", ")) {
			throw new FacesException(ERROR_MESSAGE + componentList.substring(0, componentList.length()-2) + " complete search expression: " + originalExpression);
		}

		throw new FacesException(ERROR_MESSAGE + "component " + " complete search expression: " + originalExpression);
	}

	private UIComponent findPredecessor(UIComponent parent) {
		UIComponent grandparent = parent.getParent();
		for (int i = 0; i < grandparent.getChildCount(); i++) {
			if (grandparent.getChildren().get(i) == parent) {
					if(i == 0) //if this is the first element of this component tree level there is no previous
						return null;
					//otherwise take the component before this one
					return grandparent.getChildren().get(i-1);
			}
		}
		return null; // unreachable code - but the compiler doesn't know that
	}

}
