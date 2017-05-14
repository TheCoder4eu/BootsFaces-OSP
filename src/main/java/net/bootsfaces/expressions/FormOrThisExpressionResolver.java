package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;

import net.bootsfaces.component.commandButton.CommandButton;
import net.bootsfaces.component.navCommandLink.NavCommandLink;
import net.bootsfaces.component.poll.Poll;

public class FormOrThisExpressionResolver implements AbstractExpressionResolver {
	public List<UIComponent> resolve(UIComponent component, List<UIComponent> parentComponents, String currentId,
			String originalExpression, String[] parameters) {
		
		
		List<UIComponent> result = new ArrayList<UIComponent>();
		boolean isButton = component instanceof CommandButton || component instanceof NavCommandLink;
		isButton |= component instanceof Poll;
		if (!isButton) {
			result.add(component);
			return result;
		}

		UIComponent c = component;

		while (c != null && c.getClass() != UIViewRoot.class) {
			if (UIForm.class.isAssignableFrom(c.getClass())) {
				result.add(c);
				break;
			}
			c = c.getParent();
		}
		if (result.size() > 0) {
			return result;
		}

		c = component;

		while (c != null && c.getClass() != UIViewRoot.class) {
			if (NamingContainer.class.isAssignableFrom(c.getClass())) {
				result.add(c);
				break;
			}
			c = c.getParent();
		}
		if (result.size() > 0) {
			return result;
		}
		else {
			result.add(component);
			return result;
		}
	}

}
