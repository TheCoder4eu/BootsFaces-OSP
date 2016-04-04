package net.bootsfaces.expressions;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;

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
