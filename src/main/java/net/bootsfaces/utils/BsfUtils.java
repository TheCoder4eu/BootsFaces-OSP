package net.bootsfaces.utils;

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
}
