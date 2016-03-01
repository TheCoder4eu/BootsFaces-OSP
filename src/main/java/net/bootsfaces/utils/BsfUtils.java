package net.bootsfaces.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class BsfUtils {

	/**
	 * Check if a string is valued
	 * @param str
	 * @return
	 */
	public static boolean StringIsValued(String str) {
		if(str != null && !"".equals(str.trim())) return true;
		return false;
	}
	
	/**
	 * Get the string if is valued, otherwise return default Value
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String StringOrDefault(String str, String defaultValue) {
		if(StringIsValued(str)) return str;
		return defaultValue;
	}
	
	/**
	 * Return a var-args list of items as List
	 * @param objects
	 * @return
	 */
	@SafeVarargs
	public static <T> List<T> AsList (T ... objects) {
		List<T> listOfObjects = new ArrayList<T>();
		for(T obj: objects)
			listOfObjects.add(obj);
		
		return listOfObjects;
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
	 * Get the string if is not null or empty,
	 * otherwise return the default value
	 * 
	 * @param inputValue
	 * @param defaultReturnValue
	 * @return
	 */
	public static String GetOrDefault (String inputValue, String defaultReturnValue) {
		if(StringIsValued(inputValue)) return inputValue;
		else return defaultReturnValue;
	}
	
	/**
	 * Escape special jQuery chars in selector query
	 * @param selector
	 * @return
	 */
	public static String EscapeJQuerySpecialCharsInSelector(String selector) {
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
	 * @param type
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
	public static UIForm getForm(UIComponent component) {
		while (component != null) {
			if (component instanceof UIForm) {
				break;
			}
			component = component.getParent();
		}
		return (UIForm) component;
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
}
