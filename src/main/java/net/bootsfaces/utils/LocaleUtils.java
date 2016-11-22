package net.bootsfaces.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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

	private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("^\\d{8}$", "yyyyMMdd");
			put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
			put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
			put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
			put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
			put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
			put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
			put("^\\d{12}$", "yyyyMMddHHmm");
			put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
			put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
			put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
			put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
			put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
			put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
			put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
			put("^\\d{14}$", "yyyyMMddHHmmss");
			put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
			put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
			put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
			put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
			put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
			put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
			put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
		}};

		/**
		 * Determine SimpleDateFormat pattern matching with the given date string. Returns null if
		 * format is unknown. You can simply extend DateUtil with more formats if needed.
		 * @param dateString The date string to determine the SimpleDateFormat pattern for.
		 * @return The matching SimpleDateFormat pattern, or null if format is unknown.
		 * @see SimpleDateFormat
		 */
		public static String determineDateFormat(String dateString) {
			for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
				if (dateString.toLowerCase().matches(regexp)) {
					return DATE_FORMAT_REGEXPS.get(regexp);
				}
			}
			return null; // Unknown format.
		}

		/**
		 * Try to auto-parse the date format
		 * @param dateString
		 * @return
		 */
		public static Date autoParseDateFormat(String dateString) {
			// STEP 1: try to detect standard locale based java date format
			for (Locale locale : DateFormat.getAvailableLocales()) {
				for (int style =  DateFormat.FULL; style <= DateFormat.SHORT; style ++) {
					DateFormat df = DateFormat.getDateInstance(style, locale);
					try {
						return df.parse(dateString);
						// either return "true", or return the Date obtained Date object
					} catch (ParseException ex) {
						continue; // unperasable, try the next one
					}
				}
			}
			
			// STEP 2: if the date format was not found, check with predefined dates
			String format = determineDateFormat(dateString);
			if(format != null) {
				DateFormat df = new SimpleDateFormat(format);
				try {
					return df.parse(dateString);
				} catch (ParseException e) {
					return null;
				}
			}
			return null;
		}

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
