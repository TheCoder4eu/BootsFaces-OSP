package net.bootsfaces.utils;

import java.util.HashMap;
import java.util.Map;

public class LocaleUtils {
	// moment-java format mapping
	@SuppressWarnings("serial")
	private static HashMap<String, String> momentFormatMapping = new HashMap<String, String>() {{
		put("D", "d");
		put("DD", "dd");
		put("YY", "yy");
		put("YYY", "yyyy");
		put("YYYY", "yyyy");
		put("a", "a");
		put("A", "A");
		put("M", "M");
		put("MM", "MM");
		put("MMM", "MMM");
		put("MMMM", "MMMM");
		put("h", "h");
		put("hh", "hh");
		put("H", "H");
		put("HH", "HH");
		put("m", "m");
		put("mm", "mm");
		put("s", "s");
		put("ss", "ss");
		put("S", "S");
		put("SS", "S");
		put("SSS", "S");
		put("ddd", "E");
		put("dddd", "EEEE");
		put("DDD", "D");
		put("W", "w");
		put("WW", "ww");
		put("ZZ", "z");
		put("Z", "XXX");
		put("E", "u");
	}};
	
	// java-moment format mapping
	@SuppressWarnings("serial")
	private static HashMap<String, String> javaFormatMapping = new HashMap<String, String>() {{
		put("d", "D");
		put("dd", "DD");
		put("y", "YYYY");
		put("yy", "YY");
		put("yyy", "YYYY");
		put("yyyy", "YYYY");
		put("a", "a");
		put("A", "A");
		put("M", "M");
		put("MM", "MM");
		put("MMM", "MMM");
		put("MMMM", "MMMM");
		put("h", "h");
		put("hh", "hh");
		put("H", "H");
		put("HH", "HH");
		put("m", "m");
		put("mm", "mm");
		put("s", "s");
		put("ss", "ss");
		put("S", "SSS");
		put("SS", "SSS");
		put("SSS", "SSS");
		put("E", "ddd");
		put("EE", "ddd");
		put("EEE", "ddd");
		put("EEEE", "dddd");
		put("EEEEE", "dddd");
		put("EEEEEE", "dddd");
		put("D", "DDD");
		put("w", "W");
		put("ww", "WW");
		put("z", "ZZ");
		put("zzzz", "Z");
		put("Z", "ZZ");
		put("X", "ZZ");
		put("XX", "ZZ");
		put("XXX", "Z");
		put("u", "E");
	}};
	
	/**
	 * Translate java format to moment.js format
	 * @param formatString
	 * @return
	 */
	public static String javaToMomentFormat(String formatString) {
		return translateFormat(formatString, javaFormatMapping);
	}
	
	/**
	 * Translate moment.js format to java format
	 * @param formatString
	 * @return
	 */
	public static String momentToJavaFormat(String formatString) {
		return translateFormat(formatString, momentFormatMapping);
	}
 	
	/**
	 * Internal method to do translations
	 * 
	 * @param formatString
	 * @param mapping
	 * @return
	 */
	private static String translateFormat(String formatString, Map<String, String> mapping) {
		int beginIndex = -1;
		int i = 0;
		String lastChar = null;
		String currentChar = "";
		String resultString = "";
		
		for(; i < formatString.length(); i++) {
			currentChar = String.valueOf(formatString.charAt(i));
			if(lastChar == null || !lastChar.equals(currentChar)) {
				resultString = appendMappedString(formatString, mapping, beginIndex, i, resultString);
				beginIndex = i;
			}
			lastChar = currentChar;
		}
		
		return appendMappedString(formatString, mapping, beginIndex, i, resultString);
	}
	
	/**
	 * Append the new mapping
	 * @param formatString
	 * @param mapping
	 * @param beginIndex
	 * @param currentIndex
	 * @param resultString
	 * @return
	 */
	private static String appendMappedString(String formatString, Map<String, String> mapping, int beginIndex, int currentIndex, String resultString) {
		String tempString;
		
		if(beginIndex != -1) {
			tempString = formatString.substring(beginIndex, currentIndex);
			if(mapping.containsKey(tempString)) {
				tempString = mapping.get(tempString);
			}
			resultString += tempString;
		}
		return resultString;
	}
	
	// TEST METHOD
	public static void main(String[] args) {
		System.out.println(javaToMomentFormat("dd/MM/yyyy"));
		System.out.println(momentToJavaFormat("DD/MM/YYYY"));
	}
}
