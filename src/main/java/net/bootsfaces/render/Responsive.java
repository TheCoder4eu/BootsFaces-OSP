package net.bootsfaces.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.FacesException;


public class Responsive {
	private static String[] delimiters = new String[] {",", "..." };
	private static String[] validValues = new String[] { "xs", "sm", "md", "lg", "tiny-screen", "small-screen", "medium-screen", "large-screen" };

	public enum Sizes {
		xs, sm, md, lg
	}

	/**
	 * Create the responsive class combination
	 * @param r
	 * @return
	 */
	public static String getResponsiveStyleClass(IResponsive r) { return getResponsiveStyleClass(r, true); }
	public static String getResponsiveStyleClass(IResponsive r, boolean forceColMd) {
		int colxs = sizeToInt(getSize(r, Sizes.xs));
		int colsm = sizeToInt(getSize(r, Sizes.sm));
		int collg = sizeToInt(getSize(r, Sizes.lg));

		int span = sizeToInt(r.getSpan());

		int colmd = (span > 0) ? span : sizeToInt(getSize(r, Sizes.md));
		if ((colxs > 0) || (colsm > 0) || (collg > 0)) {
			colmd = (colmd > 0) ? colmd : -1;
		} else {
			colmd = (colmd > 0) ? colmd : (forceColMd ? 12 : -1);
		}

		int offs = sizeToInt(r.getOffset());
		int offsmd = (offs > 0) ? offs : sizeToInt(r.getOffsetMd());
		int oxs = sizeToInt(r.getOffsetXs());
		int osm = sizeToInt(r.getOffsetSm());
		int olg = sizeToInt(r.getOffsetLg());

		StringBuilder sb = new StringBuilder();
		if (colmd > 0 || offsmd > 0) {
			if (colmd > 0) {
				sb.append("col-md-").append(colmd);
			}
			if (offsmd > 0) {
				if (colmd > 0) {
					sb.append(" ");
				}
				sb.append("col-md-offset-" + offsmd);
			}
		}
		if(colmd == 0) {
			sb.append(" hidden-md");
		}

		if (colxs > 0) {
			sb.append(" col-xs-").append(colxs);
		}
		if (colxs == 0) {
			sb.append(" hidden-xs");
		}

		if (colsm > 0) {
			sb.append(" col-sm-").append(colsm);
		}
		if (colsm == 0) {
			sb.append(" hidden-sm");
		}

		if (collg > 0) {
			sb.append(" col-lg-").append(collg);
		}
		if (collg == 0) {
			sb.append(" hidden-lg");
		}

		sb.append(encodeVisibility(r, r.getVisible(), "visible"));
		sb.append(encodeVisibility(r, r.getHidden(), "hidden"));

		if (oxs > 0) {
			sb.append(" col-xs-offset-").append(oxs);
		}
		if (osm > 0) {
			sb.append(" col-sm-offset-").append(osm);
		}
		if (olg > 0) {
			sb.append(" col-lg-offset-").append(olg);
		}

		return " " + sb.toString().trim() + " ";
	}

	/**
	 * Encode the visible field
	 * @param r
	 * @return
	 */
	private static String encodeVisibility(IResponsive r, String value, String prefix) {
		if(value == null) return "";

		List<String> str = wonderfulTokenizer(value, delimiters);
		return evaluateExpression(str, delimiters, validValues, prefix, r.getDisplay());
	}

	/**
	 * Decode col sizes between two way of definition
	 * @param col
	 * @param size
	 * @return
	 */
	private static String getSize(IResponsiveLabel r, Sizes size) {
		String colSize = "-1";
		switch(size) {
		case xs:
			colSize = r.getLabelColXs();
			if(colSize.equals("-1")) colSize = r.getLabelTinyScreen();
			break;
		case sm:
			colSize = r.getLabelColSm();
			if(colSize.equals("-1")) colSize = r.getLabelSmallScreen();
			break;
		case md:
			colSize = r.getLabelColMd();
			if(colSize.equals("-1")) colSize = r.getLabelMediumScreen();
			break;
		case lg:
			colSize = r.getLabelColLg();
			if(colSize.equals("-1")) colSize = r.getLabelLargeScreen();
			break;
		}
		return colSize;
	}
	
	private static String getSize(IResponsive r, Sizes size) {
		String colSize = "-1";
		switch(size) {
		case xs:
			colSize = r.getColXs();
			if(colSize.equals("-1")) colSize = r.getTinyScreen();
			break;
		case sm:
			colSize = r.getColSm();
			if(colSize.equals("-1")) colSize = r.getSmallScreen();
			break;
		case md:
			colSize = r.getColMd();
			if(colSize.equals("-1")) colSize = r.getMediumScreen();
			break;
		case lg:
			colSize = r.getColLg();
			if(colSize.equals("-1")) colSize = r.getLargeScreen();
			break;
		}
		return colSize;
	}

	/**
	 * Convert the specified size to int value
	 * @param size
	 * @return
	 */
	private static int sizeToInt(String size) {
		if (size==null) return -1;
		if ("full".equals(size)) return 12;
		if ("full-size".equals(size)) return 12;
		if ("fullSize".equals(size)) return 12;
		if ("full-width".equals(size)) return 12;
		if ("fullWidth".equals(size)) return 12;
		if ("half".equals(size)) return 6;
		if ("one-third".equals(size)) return 4;
		if ("oneThird".equals(size)) return 4;
		if ("two-thirds".equals(size)) return 8;
		if ("twoThirds".equals(size)) return 8;
		if ("one-fourth".equals(size)) return 3;
		if ("oneFourth".equals(size)) return 3;
		if ("three-fourths".equals(size)) return 9;
		if ("threeFourths".equals(size)) return 9;
		if (size.length()>2) {
			size=size.replace("columns", "");
			size=size.replace("column", "");
			size=size.trim();
		}
		return new Integer(size).intValue();
	}

	/**
	 * Parse the expression token
	 *
	 * @param expressionToken
	 * @param delimiters
	 * @param validValues
	 * @param visibilityLevel (visible or hidden)
	 * @param display (block, inline or inline-block. default block)
	 * @return
	 */
	public static String evaluateExpression(
				List<String> expressionToken, String[] delimiters,
				String[] validValues, String visibilityLevel, String display)
	{
		if ("visible".equals(visibilityLevel)) {
		display = (display == null || display.trim().isEmpty()) ? "" : "-" + display;
		}
		else {
			// hidden is not suffixed by -block, -inline or -inline-block
			display="";
		}
		String finalExpr = "";
		List<String> _valid = Arrays.asList(validValues);

		// Validate expression
		//
		// expression can be:
		// ONE SIZE: [size]
		// SIZE LIST: [size],[size],...,[size]
		// RANGE FROM: ...[size] or [size]... to get range (only inclusive)
		// RANGE BETWEEN: [size]...[size]

		// 1. Only one size:
		if(expressionToken.size() == 1)
		{
			// validation:
			if(!_valid.contains(expressionToken.get(0)))
				throw new FacesException("Expression not valid. Valid sizes are [ xs, sm, md, lg ]");
			finalExpr = " " + visibilityLevel + "-" + translateSize(expressionToken.get(0)) + display;
		}
		// 2. Expression contains comma, so is a list of sizes
		else if (expressionToken.contains(","))
		{
			for(String ex: expressionToken) {
				if(",".equals(ex)) continue;
				if(!_valid.contains(ex))
					throw new FacesException("Expression contains a non valid size. Valid sizes are [ xs, sm, md, lg ]. ");

				finalExpr += " " + visibilityLevel + "-" + translateSize(ex) + display + " ";
			}
		}
		// 3. Expression is a range from
		else if (expressionToken.size() == 2) {
			if(expressionToken.get(0).equals("...")) {
				if(!_valid.contains(expressionToken.get(1)))
					throw new FacesException("Expression not valid. Valid syntax is ...[size] eg. ...sm . Valid sizes are [ xs, sm, md, lg ].");

				List<String> sR = getSizeRange("<=", translateSize(expressionToken.get(1)));
				for(String s: sR) {
					finalExpr += " " + visibilityLevel + "-" + s + display + " ";
				}
			} else if(expressionToken.get(1).equals("...")) {
				if(!_valid.contains(expressionToken.get(0)))
					throw new FacesException("Expression not valid. Valid syntax is [size]... eg. sm... . Valid sizes are [ xs, sm, md, lg ].");

				List<String> sR = getSizeRange(">=", translateSize(expressionToken.get(0)));
				for(String s: sR) {
					finalExpr += " " + visibilityLevel + "-" + s + display + " ";
				}
			} else {
				throw new FacesException("Expression not valid. Valid syntax is ...[size] or [size]... . Valid sizes are [ xs, sm, md, lg ].");
			}
		}
		// 4. Expression is in range
		else if(expressionToken.size() == 3) {
			// validation:
			if(!_valid.contains(expressionToken.get(0)) && !"...".equals(expressionToken.get(1)) && !_valid.contains(expressionToken.get(2)))
				throw new FacesException("Expression not valid. Valid syntax is [size]...[size] eg. xs...md . Valid sizes are [ xs, sm, md, lg ].");

			List<String> sR2 = getSizeRange(expressionToken.get(1), translateSize(expressionToken.get(0)), translateSize(expressionToken.get(2)));
			for(String s: sR2) {
				finalExpr += " " + visibilityLevel + "-" + s + display + " ";
			}
		}
		// 5. Otherwise
		else {
			throw new FacesException("Expression not valid. See the docs for a list of possibile rules.");
		}

		return finalExpr;
	}

	/**
	 * Translate Sizes
	 * @param size
	 * @return
	 */
	private static String translateSize(String size) {
		if(size.equalsIgnoreCase("xs") || size.equalsIgnoreCase("tiny-screen")) return "xs";
		if(size.equalsIgnoreCase("sm") || size.equalsIgnoreCase("small-screen")) return "sm";
		if(size.equalsIgnoreCase("md") || size.equalsIgnoreCase("medium-screen")) return "md";
		if(size.equalsIgnoreCase("lg") || size.equalsIgnoreCase("large-screen")) return "lg";
		return size;
	}

	/**
	 * Get the size ranges
	 * @param operation
	 * @param size
	 * @return
	 */
	private static List<String> getSizeRange(String operation, String size)
	{
		return getSizeRange(operation, size, null);
	}
	private static List<String> getSizeRange(String operation, String size1, String size2)
	 {
		String[] orderSizes = { "xs", "sm", "md", "lg" };
		List<String> sizeRange = new ArrayList<String>();

		int itemIdx = Arrays.asList(orderSizes).indexOf(size1);

		if(operation.equals(">")) {
			for(int i = itemIdx + 1; i < orderSizes.length; i++) {
				sizeRange.add(orderSizes[i]);
			}
		} else if(operation.equals(">=")) {
			for(int i = itemIdx; i < orderSizes.length; i++) {
				sizeRange.add(orderSizes[i]);
			}
		} else if(operation.equals("<")) {
			for(int i = 0; i < itemIdx; i++) {
				sizeRange.add(orderSizes[i]);
			}
		} else if(operation.equals("<=")) {
			for(int i = 0; i <= itemIdx; i++) {
				sizeRange.add(orderSizes[i]);
			}
		} else if(operation.equals("...") && size2 != null) {
			int secondIdx = Arrays.asList(orderSizes).indexOf(size2);
			if(secondIdx < itemIdx) {
				int temp = secondIdx;
				secondIdx = itemIdx;
				itemIdx = temp;
			}
			for(int i = itemIdx; i <= secondIdx; i++) {
				sizeRange.add(orderSizes[i]);
			}
		} else {
			throw new FacesException("Operation not valid");
		}

		return sizeRange;
	}

	/**
	 * Tokenize string based on rules
	 *
	 * @param tokenString
	 * @param delimiters
	 * @return
	 */
	public static List<String> wonderfulTokenizer(String tokenString, String[] delimiters) {
		List<String> tokens = new ArrayList<String>();
		String currentToken = "";
		for(int i = 0; i < tokenString.length(); i++) {
			String _currItem = String.valueOf(tokenString.charAt(i));
			if(_currItem.trim().isEmpty()) continue;

			String delimiterFound = "";
			for(String d: delimiters) {
				if(d.startsWith(_currItem)) {
					if(d.length() > 1) {
						if(d.equals(tokenString.substring(i, i + d.length()))) {
							delimiterFound = d;
							i = i + (d.length() - 1);
						}
					} else delimiterFound = d;
				}
				if(!delimiterFound.isEmpty()) break;
			}

			if(!delimiterFound.isEmpty()) {
				if(!currentToken.isEmpty()) {
					tokens.add(currentToken);
					currentToken = "";
				}
				tokens.add(delimiterFound);
			} else currentToken += _currItem;
		}
		if(!currentToken.isEmpty()) tokens.add(currentToken);

		return tokens;
	}
	
	/**
	 * Create the responsive class combination
	 * @param r the component bearing the responsiveness attributes
	 * @return null, if there's no label-col-xx attribute
	 */
	public static String getResponsiveLabelClass(IResponsiveLabel r) {
		int colxs = sizeToInt(getSize(r, Sizes.xs));
		int colsm = sizeToInt(getSize(r, Sizes.sm));
		int colmd = sizeToInt(getSize(r, Sizes.md));
		int collg = sizeToInt(getSize(r, Sizes.lg));

		if (colmd > 0) {
			return "col-md-"+ colmd;
		}

		if (colxs > 0) {
			return "col-xs-"+ colxs;
		}
		if (colsm > 0) {
			return "col-sm-"+ colsm;
		}
		if (collg > 0) {
			return "col-lg-"+ collg;
		}

		return null;
	}


	// TEST METHOD
	public static void main(String[] args)
	{
		List<String> str = wonderfulTokenizer("sm...md", delimiters);
		String finalExpr = evaluateExpression(str, delimiters, validValues, "hidden", "block");
		for(String s: str) System.out.println(s);
		System.out.println(finalExpr);

		System.out.println("*******");
		str = wonderfulTokenizer("sm...medium-screen", delimiters);
		finalExpr = evaluateExpression(str, delimiters, validValues, "hidden", "block");
		for(String s: str) System.out.println(s);
		System.out.println(finalExpr);

		System.out.println("*******");
		str = wonderfulTokenizer("...md", delimiters);
		finalExpr = evaluateExpression(str, delimiters, validValues, "hidden", "block");
		for(String s: str) System.out.println(s);
		System.out.println(finalExpr);

		System.out.println("*******");
		str = wonderfulTokenizer("xs,lg", delimiters);
		finalExpr = evaluateExpression(str, delimiters, validValues, "hidden", "block");
		for(String s: str) System.out.println(s);
		System.out.println(finalExpr);

		System.out.println("*******");
		str = wonderfulTokenizer("md...", delimiters);
		finalExpr = evaluateExpression(str, delimiters, validValues, "hidden", "block");
		for(String s: str) System.out.println(s);
		System.out.println(finalExpr);

	}
}
