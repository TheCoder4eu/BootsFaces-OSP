package net.bootsfaces.expressions;

import java.util.List;

import javax.faces.component.UIComponent;

public interface AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId, String originalExpression, String[] parameters);

}
