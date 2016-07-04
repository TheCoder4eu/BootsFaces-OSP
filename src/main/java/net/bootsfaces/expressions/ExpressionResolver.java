/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu)
 *
 *  This file is part of BootsFaces.
 *
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */
package net.bootsfaces.expressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

/**
 * The ExpressionResolver converts search expressions to component ids.
 *
 * @author Stephan Rauh, http://www.beyondjava.net
 *
 */
public class ExpressionResolver {

	private static InternalIDExpressionResolver idExpressionResolver = new InternalIDExpressionResolver();
	private static Map<String, AbstractExpressionResolver> resolvers = new HashMap<String, AbstractExpressionResolver>();

	public static String getComponentIDs(FacesContext context, UIComponent component, String update) {
		List<String> expressions = getExpressions(update);
		return getComponentIDs(context, component, expressions);
	}

	public static String getComponentIDs(FacesContext context, UIComponent component, Collection<String> expressions) {
		String result = "";
		for (String exp : expressions) {
			result += getComponentId(context, component, exp) + " ";
		}
		return result.trim();
	}

	private static String getComponentId(FacesContext context, UIComponent component, String originalExpression) {
		char separatorChar = UINamingContainer.getSeparatorChar(context);
		String separator = "" + separatorChar;
		UIComponent root;
		if (originalExpression.startsWith(":")) {
			originalExpression = originalExpression.substring(1);
			root = context.getViewRoot();
		} else {
			root = component;
		}
		List<UIComponent> roots = new ArrayList<UIComponent>();
		roots.add(root);
		String result = "";
		String[] subexpressions = originalExpression.split(separator);
		for (int i = 0; i < subexpressions.length; i++) {
			String currentId = subexpressions[i];
			if (currentId != null && currentId.length() > 0) {
				if (currentId.startsWith("@(") && currentId.endsWith(")")) {
					// the jQuery expression is evaluated on the client side
					result += currentId + " ";
					roots.clear();
				} else {
					if (currentId.equals("**")) {
						i++;
						if (subexpressions[i].contains("*"))
							currentId = "@findPartialIdRecursively(" + subexpressions[i] + ")";
						else
							currentId = "@findIdRecursively(" + subexpressions[i] + ")";
					} else if (currentId.equals("*")) {
						i++;
						currentId = "@findId(" + subexpressions[i] + ")";
					} else if (currentId.contains("*")) {
						currentId = "@findPartialId(" + currentId + ")";
					}
					roots = translateSearchExpressionToId(component, roots, currentId, originalExpression);
				}
			} else
				throw new FacesException("Invalid search expression: " + originalExpression);
		}


		for (UIComponent c : roots) {
			result += c.getClientId() + " ";
		}
		return result.trim();
	}

	private static List<UIComponent> translateSearchExpressionToId(UIComponent component, List<UIComponent> roots,
			String currentId, String originalExpression) {
		List<UIComponent> result = new ArrayList<UIComponent>();
		try {
			if (currentId.startsWith("@")) {
				String[] params = null;
				AbstractExpressionResolver resolver;
				String className = currentId.substring(1, 2).toUpperCase();
				if (currentId.length() > 2)
					className += currentId.substring(2);
				int paramStart = className.indexOf('(');
				if (paramStart > 0) {
					int paramEnd = className.indexOf(')');
					String paramsCSV = className.substring(paramStart + 1, paramEnd);
					params = paramsCSV.split(",");
					className = className.substring(0, paramStart).trim();
				}
				if (resolvers.containsKey(className)) {
					resolver = resolvers.get(className);
				} else {
					Class<?> rc = Class.forName("net.bootsfaces.expressions." + className + "ExpressionResolver");
					resolver = (AbstractExpressionResolver) rc.newInstance();
					synchronized (resolvers) {
						if (!resolvers.containsKey(className))
							resolvers.put(className, resolver);
					}
				}
				result.addAll(resolver.resolve(component, roots, currentId, originalExpression, params));
			} else
				result.addAll(idExpressionResolver.resolve(component, roots, currentId, originalExpression, null));

			return result;
		} catch (Exception e) {
			String msg ="";
			if (e.getMessage()!=null) {
				msg += " Additional information: " + e.getMessage();
			}
			throw new FacesException("Invalid search expression: " + originalExpression + " The subexpression "
					+ currentId + " doesn't exist, or it can't be resolved." + msg);
		}
	}

	public static List<String> getExpressions(String commaSeparatedList) {
		if (commaSeparatedList == null)
			commaSeparatedList = "@formOrThis";
		List<String> expressions = new ArrayList<String>();
		int pos = 0;
		int start = 0;
		while (pos < commaSeparatedList.length()) {
			char c = commaSeparatedList.charAt(pos);
			if (c == ' ' || c == ',') {
				if (pos - start > 0) {
					expressions.add(commaSeparatedList.substring(start, pos));
				}
				pos++;
				start = pos;
				continue;
			}
			if (c == '(') {
				pos = readUntilNextParenthesis(commaSeparatedList, pos + 1);
			}
			pos++;
		}
		if (pos - start > 0) {
			expressions.add(commaSeparatedList.substring(start, pos));
		}
		return expressions;
	}

	private static int readUntilNextParenthesis(String commaSeparatedList, int start) {
		int pos = start;
		while (pos < commaSeparatedList.length()) {
			char c = commaSeparatedList.charAt(pos);
			if (c == '\'')
				readUntilEndOfString(commaSeparatedList, pos + 1);
			if (c == ')')
				return pos;
			pos++;
		}
		throw new FacesException("invalid search expression - closing parenthesis expected: " + commaSeparatedList
				+ " starting at position " + start);
	}

	private static int readUntilEndOfString(String commaSeparatedList, int start) {
		int pos = start;
		while (pos < commaSeparatedList.length()) {
			char c = commaSeparatedList.charAt(pos);
			if (c == '\\')
				pos++;
			if (c == '\'')
				return pos;
			pos++;
		}
		throw new FacesException("invalid search expression - end of string expected: " + commaSeparatedList
				+ " starting at position " + start);
	}

}
