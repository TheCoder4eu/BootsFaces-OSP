package net.bootsfaces.expressions;

import java.util.List;

import jakarta.faces.component.UIComponent;

public interface AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId, String originalExpression, String[] parameters);

}
