package net.bootsfaces.expressions;

import java.util.List;

import javax.faces.component.UIComponent;

public interface AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, String parentId, String currentId, String originalExpression, String[] parameters);

}
