package net.bootsfaces.expressions;

import jakarta.faces.component.NamingContainer;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;

public class ExpressionResolverUtilities {
	public static String determineQualifiedId(UIComponent component) {
		String qualifiedId = "";
		if (component instanceof NamingContainer) 
			return "";
		
		while (component != null && (!(component instanceof UIViewRoot)) && (!(component instanceof NamingContainer))) {
			component = component.getParent();
			if (component instanceof NamingContainer)
				qualifiedId = component.getId() + ":" + qualifiedId;
		}
		return ":" + qualifiedId;
	}

}
