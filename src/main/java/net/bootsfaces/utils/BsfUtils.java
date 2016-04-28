package net.bootsfaces.utils;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class BsfUtils {

	/**
	 * This is a trick method to provide navigation from an ajax request (credit by Ryan Lubke)
	 * In fact, you can map an action to an UICommand that calls this method and return null.
	 * Once the bean sends the redirect, the ajax client receives a message from the server telling the client to redirect to a new page.
	 * 
	 * 
	 * @param outcome
	 * @throws FacesException
	 */
	// Example:
	// <h:form id="form">
	// <!-- with basic jsf components -->
	// <h:commandButton id="goToPageBtn" value="Go"
	// action="#{exampleBean.goToNewPage}">
	// <f:ajax execute="@this" render="@none"/>
	// </h:commandButton>
	//
	// <!-- with bootsfaces components -->
	// <b:commandButton id="goToPageBtn" value="Go"
	// onclick="ajax:exampleBean.goToNewPage();" />
	// </h:form>
	//
	// @ManagedBean
	// @RequestScoped
	// public class ExampleBean {
	// public String goToNewPage() {
	// BsfUtils.navigateInAjax("/pages/newPage.xhtml"); return null; }
	// }

	public static String navigateInAjax(String outcome) 
			throws FacesException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extContext = ctx.getExternalContext();

		String url = extContext.encodeActionURL(
				ctx.getApplication().getViewHandler().getActionURL(
						ctx, outcome));
		try {
			extContext.redirect(url);
		} catch (IOException ioe) {
			throw new FacesException(ioe);
		}
		return null;
	}

	/**
	 * Check if a string is valued
	 * @param str
	 * @return
	 */
	public static boolean isStringValued(String str) {
		return str != null && !"".equals(str.trim());
	}

	/**
	 *  Get the string if is not null or empty,
	 *  otherwise return the default value
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String stringOrDefault(String str, String defaultValue) {
		if(isStringValued(str)) return str;
		return defaultValue;
	}

	/**
	 * Transform a snake-case string to a camel-case one.
	 * @param snakeCase
	 * @return
	 */
	public static String snakeCaseToCamelCase(String snakeCase) {
		if(snakeCase.contains("-")) {
			StringBuilder camelCaseStr = new StringBuilder(snakeCase.length());
			boolean toUpperCase = false;
			for (char c : snakeCase.toCharArray()) {
				if (c == '-')
					toUpperCase = true;
				else {
					if (toUpperCase) {
						toUpperCase = false;
						c = Character.toUpperCase(c);
					}
					camelCaseStr.append(c);
				}
			}
			snakeCase = camelCaseStr.toString();
		}
		return snakeCase;
	}

	/**
	 * Transform a snake-case string to a camelCase one.
	 * @param camelCase
	 * @return the original string if there is no camelCase character. Otherwise, a snake-case representation of the camelCase version.
	 */
	public static String camelCaseToSnakeCase(String camelCase) {
		if (null == camelCase || camelCase.length()==0)
			return camelCase;
		StringBuilder snakeCase = new StringBuilder(camelCase.length()+3);
		snakeCase.append(camelCase.charAt(0));
		boolean hasCamelCase=false;
		for (int i = 1; i < camelCase.length(); i++) {
			char c = camelCase.charAt(i);
			if (Character.isUpperCase(c)) {
				snakeCase.append("-");
				c = Character.toLowerCase(c);
				hasCamelCase=true;
			}
			snakeCase.append(c);
		}
		if (!hasCamelCase)
			return camelCase;

		return snakeCase.toString();
	}


	/**
	 * Escape html special chars from string
	 * @param htmlString
	 * @return
	 */
	public static String escapeHtml(String htmlString) {
		StringBuffer sb = new StringBuffer(htmlString.length());
		// true if last char was blank
		boolean lastWasBlankChar = false;
		int len = htmlString.length();
		char c;

		for (int i = 0; i < len; i++)
		{
			c = htmlString.charAt(i);
			if (c == ' ') {
				// blank gets extra work,
				// this solves the problem you get if you replace all
				// blanks with &nbsp;, if you do that you loss 
				// word breaking
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
					sb.append("&nbsp;");
				}
				else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			}
			else {
				lastWasBlankChar = false;
				//
				// HTML Special Chars
				if (c == '"')
					sb.append("&quot;");
				else if (c == '&')
					sb.append("&amp;");
				else if (c == '<')
					sb.append("&lt;");
				else if (c == '>')
					sb.append("&gt;");
				else if (c == '/') 
					sb.append("-");
				else if (c == '\\')
					sb.append("-");
				else if (c == '\n')
					// Handle Newline
					sb.append("&lt;br/&gt;");
				else {
					int ci = 0xffff & c;
					if (ci < 160 )
						// nothing special only 7 Bit
						sb.append(c);
					else {
						// Not 7 Bit use the unicode system
						sb.append("&#");
						sb.append(new Integer(ci).toString());
						sb.append(';');
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Escape special jQuery chars in selector query
	 * @param selector
	 * @return
	 */
	public static String escapeJQuerySpecialCharsInSelector(String selector) {
		String jQuerySpecialChars = "!\"#$%&'()*+,./:;<=>?@[]^`{|}~;";
		String[] jsc = jQuerySpecialChars.split("(?!^)");
		for(String c: jsc) {
			selector = selector.replace(c, "\\\\" + c);
		}
		return selector;
	}

	/**
	 * Check if a class is primitive, wrapper or String type.
	 * So, it returns true if is a basic java type class
	 * @param obj
	 * @return
	 */
	public static boolean isPrimitiveOrPrimitiveWrapperOrString(Object obj) {
		if(obj == null) return false;
		return isPrimitiveOrPrimitiveWrapperOrString(obj.getClass());
	}
	public static boolean isPrimitiveOrPrimitiveWrapperOrString(Class<?> type) {
		return (type.isPrimitive() && type != void.class) ||
				type == Double.class || type == Float.class || type == Long.class ||
				type == Integer.class || type == Short.class || type == Character.class ||
				type == Byte.class || type == Boolean.class || type == String.class;
	}

	/**
	 * Get the related form
	 * @param component
	 * @return
	 */
	public static UIForm getClosestForm(UIComponent component) {
		while (component != null) {
			if (component instanceof UIForm) {
				return (UIForm) component;
			}
			component = component.getParent();
		}
		return null;
	}

	/**
	 * Returns the clientId for a component with id="foo"
	 */
	public static String getComponentClientId(final String componentId) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();

		UIComponent c = findComponent(root, componentId);
		return c.getClientId(context);
	}

	/**
	 * Finds component with the given id
	 */
	public static UIComponent findComponent(UIComponent c, String id) {
		if (id.equals(c.getId())) {
			return c;
		}
		Iterator<UIComponent> kids = c.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent found = findComponent(kids.next(), id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}
	
	/**
	 * Shortcut for getting context parameters.
	 * @param param context parameter name
	 * @return value of context parameter, may be null or empty
	 */
	public static String getInitParam(String param) {
		return getInitParam(param, FacesContext.getCurrentInstance());
	}
	
	/**
	 * Shortcut for getting context parameters using an already obtained FacesContext.
	 * @param param context parameter name
	 * @return value of context parameter, may be null or empty
	 */
	public static String getInitParam(String param, FacesContext context) {
		return context.getExternalContext().getInitParameter(param);
	}
}
