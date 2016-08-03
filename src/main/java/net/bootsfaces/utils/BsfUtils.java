package net.bootsfaces.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.bind.DatatypeConverter;

import net.bootsfaces.expressions.ExpressionResolver;

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

	/**
	 * <p>
	 * Creates and returns a FacesMessage for the specified Locale.
	 * </p>
	 * Simplified and streamlined version of the implementation of Mojarra
	 * 2.2.8-b02 (see MessageFactory).
	 *
	 * @param messageId
	 *            - the key of the message in the resource bundle
	 * @param params
	 *            - substitution parameters
	 *
	 * @return a localized <code>FacesMessage</code> with the severity of
	 *         FacesMessage.SEVERITY_ERROR
	 */
	public static FacesMessage getMessage(String messageId, String... params) {
		String summary = null;
		String detail = null;
		ResourceBundle bundle;
		String bundleName;
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = context.getViewRoot().getLocale();

		// see if we have a user-provided bundle
		Application app = (FacesContext.getCurrentInstance().getApplication());
		if (null != (bundleName = app.getMessageBundle())) {
			if (null != (bundle = ResourceBundle.getBundle(bundleName, locale,
					Thread.currentThread().getContextClassLoader()))) {
				// see if we have a hit
				try {
					summary = bundle.getString(messageId);
					detail = bundle.getString(messageId + "_detail");
				} catch (MissingResourceException e) {
					// ignore
				}
			}
		}

		// we couldn't find a summary in the user-provided bundle
		if (null == summary) {
			// see if we have a summary in the app provided bundle
			bundle = ResourceBundle.getBundle(FacesMessage.FACES_MESSAGES, locale,
					Thread.currentThread().getContextClassLoader());
			if (null == bundle) {
				throw new NullPointerException();
			}
			// see if we have a hit
			try {
				summary = bundle.getString(messageId);
				detail = bundle.getString(messageId + "_detail");
			} catch (MissingResourceException e) {
				// ignore
			}
		}

		for (int i = 0; i < params.length; i++) {
			summary = summary.replace("{" + i + "}", params[i]);
			detail = detail.replace("{" + i + "}", params[i]);
		}

		// At this point, we have a summary and a bundle.
		FacesMessage ret = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		ret.setSeverity(FacesMessage.SEVERITY_ERROR);
		return ret;
	}


	/**
	 * Implementation from Apache Commons Lang
	 *
	 * @param str
	 * @return
	 */
	public static Locale toLocale(String str) {
		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len != 2 && len != 5 && len < 7) {
			throw new IllegalArgumentException("Invalid locale format: " + str);
		}
		char ch0 = str.charAt(0);
		char ch1 = str.charAt(1);
		if (ch0 < 'a' || ch0 > 'z' || ch1 < 'a' || ch1 > 'z') {
			throw new IllegalArgumentException("Invalid locale format: " + str);
		}
		if (len == 2) {
			return new Locale(str, "");
		} else {
			if (str.charAt(2) != '_') {
				throw new IllegalArgumentException("Invalid locale format: " + str);
			}
			char ch3 = str.charAt(3);
			if (ch3 == '_') {
				return new Locale(str.substring(0, 2), "", str.substring(4));
			}
			char ch4 = str.charAt(4);
			if (ch3 < 'A' || ch3 > 'Z' || ch4 < 'A' || ch4 > 'Z') {
				throw new IllegalArgumentException("Invalid locale format: " + str);
			}
			if (len == 5) {
				return new Locale(str.substring(0, 2), str.substring(3, 5));
			} else {
				if (str.charAt(5) != '_') {
					throw new IllegalArgumentException("Invalid locale format: " + str);
				}
				return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
			}
		}
	}

	// Pass the attrs timezone value
	// Pass facesContext.getViewRoot().getLocale() and attrs locale value
	public static Locale selectLocale(Locale vrloc, Object loc, UIComponent comp) {
		java.util.Locale selLocale = vrloc;

		if (loc != null) {
			if (loc instanceof String) {
				selLocale = BsfUtils.toLocale((String) loc);
			} else if (loc instanceof java.util.Locale) {
				selLocale = (java.util.Locale) loc;
			} else {
				throw new IllegalArgumentException(
						"Type:" + loc.getClass() + " is not a valid locale type for " + comp.getFamily() + ":" + comp.getClientId());
			}
		}

		return selLocale;
	}

	/**
	 * Selects the Date Pattern to use based on the given Locale if the input
	 * format is null
	 *
	 * @param locale
	 *            Locale (may be the result of a call to selectLocale)
	 * @param format
	 *            Input format String
	 * @return Date Pattern eg. dd/MM/yyyy
	 */
	public static String selectDateFormat(Locale locale, String format) {
		String selFormat;
		if (format == null) {
			selFormat = ((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale)).toPattern();
			// Since DateFormat.SHORT is silly, return a smart format
			if (selFormat.equals("M/d/yy")) {
				return "MM/dd/yyyy";
			}
			if (selFormat.equals("d/M/yy")) {
				return "dd/MM/yyyy";
			}
		} else {
			selFormat = format;
		}

		return selFormat;
	}

	/**
	 * Selects the Date Pattern to use based on the given Locale if the input
	 * format is null
	 *
	 * @param locale
	 *            Locale (may be the result of a call to selectLocale)
	 * @param format
	 *            Input format String
	 * @return Date Pattern eg. dd/MM/yyyy
	 */
	public static String selectDateTimeFormat(Locale locale, String format, boolean withDate, boolean withTime) {
		if (format == null) {
			String dateFormat = "";
			if (withDate) {
				dateFormat = ((SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale)).toPattern();
			}
			String timeFormat = "";
			if (withTime) {
				timeFormat = ((SimpleDateFormat)DateFormat.getTimeInstance(DateFormat.MEDIUM, locale)).toPattern();
			}
			// Since DateFormat.SHORT is silly, return a smart format
			if (dateFormat.equals("M/d/yy")) {
				dateFormat = "MM/dd/yyyy";
			}
			else if (dateFormat.equals("d/M/yy")) {
				dateFormat = "dd/MM/yyyy";
			}
			return (dateFormat + " " + timeFormat).trim();
		} else {
			return format;
		}
	}


	/**
	 * <p>
	 * Returns the <code>label</code> property from the specified component.
	 * </p>
	 * Simplified and adapted version of the implementation of Mojarra 2.2.8-b02
	 * (see MessageFactory).
	 *
	 * @param context
	 *            - the <code>FacesContext</code> for the current request
	 *
	 * @return the label, if any, of the component
	 */
	public static String getLabel(FacesContext context, UIComponent comp) {
		Object o = comp.getAttributes().get("label");
		if (o == null || (o instanceof String && ((String) o).length() == 0)) {
			ValueExpression vex = comp.getValueExpression("label");
			if (null != vex)
				return (String) vex.getValue(context.getELContext());
		}
		if (o == null) {
			// Use the "clientId" if there was no label specified.
			o = comp.getClientId(context);
		}
		return (String) o;
	}

	/**
	 * Read an object from a base64 string
	 * @param s
	 * @return
	 */
	public static Object fromString(String s) {
		try {
			byte[] data = DatatypeConverter.parseBase64Binary(s);
//			byte [] data = Base64.getDecoder().decode( s );
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
			Object o  = ois.readObject();
			ois.close();
			return o;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Write an object to base64 string
	 * @param o
	 * @return
	 */
	public static String toString(Serializable o) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
//			return Base64.getEncoder().encodeToString(baos.toByteArray());

			return DatatypeConverter.printBase64Binary(baos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Resolve the search expression 
	 * @param refItem
	 * @return
	 */
	public static String resolveSearchExpressions(String refItem) {
		if (refItem != null) {
			if (refItem.contains("@") || refItem.contains("*")) {
				refItem = ExpressionResolver.getComponentIDs(FacesContext.getCurrentInstance(),
						FacesContext.getCurrentInstance().getViewRoot(), refItem);
			}
		}
		return refItem;
	}
}
