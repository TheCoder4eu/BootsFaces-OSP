package net.bootsfaces.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class converts a date format string from the momement.js format to the Java SimpleDateFormater
 * format string, and vice versa. It has been inspired by 
 * https://github.com/MadMG/moment-jdateformatparser/blob/master/moment-jdateformatparser.js. 
 * @author chongma
 *
 */
public class LocaleUtils {
	
	static long time = 0l;
	
	@SuppressWarnings("serial")
	private static final HashMap<String, String> momentToJava = new HashMap<String, String>() {
		{
			put("d", ""); // (day in week - 0=Sunday, 6=Saturday)");
			put("D", "d"); // (day in month - one or two digits)");
			put("DD", "dd"); // (day in month - two digits)");
			put("Do", null); // (day of month with ordinal)");
			put("Y", null); // (year - any number of digits and sign");
			put("YY", "yy"); // (year - two digits)");
			put("YYYY", "yyyy"); // (year - all digits)");
			put("a", null); // (am or pm)");
			put("A", "a"); // (AM or PM)");
			put("M", "M"); // (month in year - two digits, 01..12)");
			put("MM", "MM"); // (month in year - two digits, 01..12)");
			put("MMM", "MMM"); // (month in year - short text)");
			put("MMMM", "MMMM"); // (month in year - full text)");
			put("h", "h"); // (hour - one or two digits, 12 hours, 1..12)");
			put("hh", "hh"); // (hour - two or two digits, 12 hours, 01..12)");
			put("H", "H"); // (hour - one or two digits, 24 hours, 0..23)");
			put("HH", "HH"); // (hour - two digits, 24 hours, 00..23)");
			put("k", "k"); // (hour - one or two digits, 24 hours, 1..24)");
			put("kk", "kk"); // (hour - two digits, 24 hours, 01..24)");
			put("m", "m"); // (minutes - one or two digits)");
			put("mm", "mm"); // (minutes - two digits)");
			put("s", "s"); // (seconds - one or two digits)");
			put("ss", "ss"); // (second - two digits)");
			put("S", "S"); // (fractional seconds - 0...999");
			put("SS", "SS"); // (fractional seconds - 00...999");
			put("SSS", "SSS"); // (fractional seconds - 000...999");
			put("ddd", "E"); // (day name - short)");
			put("dddd", "EEEE"); // (day name - full)");
			put("DDD", "D"); // (day in year)");
			put("DDDD", "DDD"); // (day in year)");
			put("W", "w"); // (week in year - one or two digits, 1..53)");
			put("WW", "ww"); // (week in year - two digits, 01..53)");
			put("w", null); // (locale week in year - one or two digits, 1..53)");
			put("ww", null); // (locale week in year - two digits, 01..53)");
			put("ZZ", "ZZ"); // (offset from UTC)");
			put("Z", "XXX"); // (offset from UTC)");
			put("E", "u"); // (ISO day of week - 1..7)");
			put("e", null); // (Locale day of week - 0..6)");
			put("Q", null); // (quarter in year - 1..4");
			put("X", null); // (UNIX timestamp)");
			put("x", null); // (UNIX timestamp - milliseconds)");
			put("[", "'"); // (escape character)");
			put("]", "'"); // (escape character)");
		}
	};

	@SuppressWarnings("serial")
	private static final HashMap<String, String> javaToMoment = new HashMap<String, String>() {
		{
			put("d", "D"); // (day in month - one or two digits)");
			put("dd", "DD"); // (day in month - two digits)");
			put("D", "DDD"); // (day in year - one to three digits)");
			put("DD", null); // (day in year- two or three digits)");
			put("DDD", "DDDD"); // (day in year- three digits)");
			put("F", null); // (day of week in month)");
			put("y", "YYYY"); // (year - every digit)");
			put("yy", "YY"); // (year - two digits)");
			put("yyy", "YYYY"); // (year - three digits)");
			put("yyyy", "YYYY"); // (year - every digit)");
			put("Y", null); // (week year - two digits)");
			put("YY", "gg"); // (week year - two digits)");
			put("YYY", null); // (week year - three digits)");
			put("YYYY", "gggg"); // (week year - all digits)");
			put("a", "A"); // (AM or PM)");
			put("G", null); // (era - AD or BC)");
			put("M", "M"); // (month in year - two digits, 1..12)");
			put("MM", "MM"); // (month in year - two digits, 01..12)");
			put("MMM", "MMM"); // (month in year - short text)");
			put("MMMM", "MMMM "); // (month in year - full text)");
			put("h", "h"); // (hour - one or two digits, 12 hours, 1..12)");
			put("hh", "hh"); // (hour - two digits, 12 hours, 01..12)");
			put("H", "H"); // (hour - one or two digits, 24 hours, 0..23)");
			put("HH", "HH"); // (hour - two digits, 24 hours, 00..23)");
			put("k", "k"); // (hour - one or two digits, 12 hours, 1..24)");
			put("kk", "kk"); // (hour - two digits, 24 hours, 01..24)");
			put("K", null); // (hour - one or two digits, 12 hours, 0..11)");
			put("KK", null); // (hour - two digits, 12 hours, 00..11)");
			put("m", "m"); // (minutes - one or two digits)");
			put("mm", "mm"); // (minutes - two digits)");
			put("s", "s"); // (seconds- one or two digits)");
			put("ss", "ss"); // (seconds - two digits)");
			put("S", "S"); // (millisecond)");
			put("SS", "SS "); // (millisecond)");
			put("SSS", "SSS "); // (millisecond)");
			put("E", "ddd"); // (day name in week - short)");
			put("EE", "ddd "); // (day name in week - short)");
			put("EEE", "ddd "); // (day name in week - short)");
			put("EEEE", "dddd "); // (day name in week - full)");
			put("w", "W"); // (week in year - one or two digits)");
			put("ww", "WW"); // (week in year - two digits, zero-padded)");
			put("W", null); // (week in month - one or two digits)");
			put("WW", null); // (week in month - two digits, zero-padded)");
			put("z", null); // (General time zone)");
			put("zz", null); // (General time zone)");
			put("zzz", null); // (General time zone)");
			put("zzzz", null); // (General time zone)");
			put("Z", "ZZ"); // (RFC 822 time zone)");
			put("X", null); // (ISO 8601 time zone -  - hours only)");
			put("XX", "ZZ"); // (ISO 8601 time zone - short)");
			put("XXX", "Z"); // (ISO 8601 time zone - long)");
			put("u", "E"); // (day number of week - 1=Monday, 7=Sunday)");
			put("'", "[]"); // (escape character);
		}
	};

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
		}
	};

	/**
	 * Determine SimpleDateFormat pattern matching with the given date string.
	 * Returns null if format is unknown. You can simply extend DateUtil with
	 * more formats if needed.
	 * 
	 * @param dateString
	 *            The date string to determine the SimpleDateFormat pattern for.
	 * @return The matching SimpleDateFormat pattern, or null if format is
	 *         unknown.
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
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date autoParseDateFormat(String dateString) {
		long start = System.nanoTime();
		try {
		// STEP 1: try to detect standard locale based java date format
		for (Locale locale : DateFormat.getAvailableLocales()) {
			for (int style = DateFormat.FULL; style <= DateFormat.SHORT; style++) {
				DateFormat df = DateFormat.getDateInstance(style, locale);
				try {
					return df.parse(dateString);
					// either return "true", or return the Date obtained Date
					// object
				} catch (ParseException ex) {
					continue; // unparsable, try the next one
				}
			}
		}

		// STEP 2: if the date format was not found, check with predefined dates
		String format = determineDateFormat(dateString);
		if (format != null) {
			DateFormat df = new SimpleDateFormat(format);
			try {
				return df.parse(dateString);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
		} finally {
			long end = System.nanoTime() - start;
			time += end;
			System.out.println("Time: " + (time / 1000)/1000.0 + " ms");
		}
	}

	/**
	 * Translate java format to moment.js format
	 * 
	 * @param formatString
	 * @return
	 */
	public static String javaToMomentFormat(String formatString) {
		return translateFormat(formatString, javaToMoment, "'", "'", "[", "]");
	}

	/**
	 * Translate moment.js format to java format
	 * 
	 * @param formatString
	 * @return
	 */
	public static String momentToJavaFormat(String formatString) {
		return translateFormat(formatString, momentToJava, "[", "]", "'", "'");
	}

	/**
	 * Internal method to do translations
	 * 
	 * @param formatString
	 * @param mapping
	 * @return
	 */
	private static String translateFormat(String formatString, Map<String, String> mapping, String escapeStart, String escapeEnd, String targetEscapeStart, String targetEscapeEnd) {
		int beginIndex = 0;
		int i = 0;
		char lastChar = 0;
		char currentChar = 0;
		String resultString = "";
		char esc1 = escapeStart.charAt(0);
		char esc2 = escapeEnd.charAt(0);

		for (; i < formatString.length(); i++) {
			currentChar = formatString.charAt(i);
			if (i > 0 && lastChar != currentChar) {
				resultString += mapSubformat(formatString, mapping, beginIndex, i, escapeStart, escapeEnd, targetEscapeStart, targetEscapeEnd);
				beginIndex = i;
			}
			lastChar = currentChar;
			if (currentChar == esc1) {
				i++;
				while (i< formatString.length() && formatString.charAt(i) != esc2) {
					i++;
				}
				resultString += targetEscapeStart;
				resultString += formatString.substring(beginIndex+1, i);
				resultString += targetEscapeEnd;
				i++;
				if (i < formatString.length()) {
					lastChar = formatString.charAt(i);
				}
				beginIndex = i;
			}
		}
		
		if (beginIndex < formatString.length() && i <= formatString.length()) {
			return resultString + mapSubformat(formatString, mapping, beginIndex, i, escapeStart, escapeEnd, targetEscapeStart, targetEscapeEnd);
		} else {
			return resultString;
		}

	}

	/**
	 * Append the new mapping
	 * 
	 * @param formatString
	 * @param mapping
	 * @param beginIndex
	 * @param currentIndex
	 * @param resultString
	 * @return
	 */
	private static String mapSubformat(String formatString, Map<String, String> mapping, int beginIndex,
			int currentIndex, String escapeStart, String escapeEnd, String targetEscapeStart, String targetEscapeEnd) {
		String subformat = formatString.substring(beginIndex, currentIndex);
		if (subformat.equals(escapeStart) || subformat.equals(escapeEnd)) {
			return targetEscapeStart + "(error: cannot ecape escape characters)" + targetEscapeEnd;
		}
		if (mapping.containsKey(subformat)) {
			String result = mapping.get(subformat);
			if (result==null || result.length()==0) {
				return targetEscapeStart + "(error: " + subformat + " cannot be converted)" + targetEscapeEnd;
			}
			return result;
		}
		return subformat;
	}

	// TEST METHOD
	public static void main(String[] args) {
		isCorrect(momentToJavaFormat("DD/MM/YYYY"), "dd/MM/yyyy");
		isCorrect(momentToJavaFormat("DD[/]MM[/]YYYY"), "dd'/'MM'/'yyyy");
		isCorrect(javaToMomentFormat("DD/MM/YYYY"), "[(error: DD can't be converted)]/MM/gggg");
		isCorrect(javaToMomentFormat("DD[/]MM"), "[(error: DD cannot be converted)][(error: cannot ecape escape characters)]/[(error: cannot ecape escape characters)]MM");
		isCorrect(javaToMomentFormat("dd/MM/yyyy"), "DD/MM/YYYY");
		//		System.out.println(javaToMomentFormat("dd/MM/yyyy"));
//		System.out.println(momentToJavaFormat("DD/MM/YYYY"));
	}
	
	// TEST METHOD
	private static void isCorrect(String one, String two) {
		if (one != null && one.equals(two)) {
			System.out.println(one + " correct");
		} else {
			System.out.println(one + " wrong - should be " + two);
		}
		
	}
}
