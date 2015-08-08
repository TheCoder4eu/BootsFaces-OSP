/**
 *  Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */
package net.bootsfaces.expressions;

import java.util.ArrayList;
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

	private static IDExpressionResolver idExpressionResolver = new IDExpressionResolver();
	private static Map<String, AbstractExpressionResolver> resolvers = new HashMap<String, AbstractExpressionResolver>();

	public static String getComponentIDs(FacesContext context, UIComponent component, String update) {
		List<String> expressions = getExpressions(update);
		String result = "";
		for (String exp : expressions) {
			result += " " + getComponentId(context, component, exp);
		}
		return result.trim();
	}

	private static String getComponentId(FacesContext context, UIComponent component, String originalExpression) {
		char separatorChar = UINamingContainer.getSeparatorChar(context);
		String separator = "" + separatorChar;
		String parentId = "";
		if (originalExpression.startsWith(":")) {
			originalExpression = originalExpression.substring(1);
			parentId = ":";
		}
		String[] subexpressions = originalExpression.split(separator);
		for (int i = 0; i < subexpressions.length; i++) {
			String currentId = subexpressions[i];
			if (currentId != null && currentId.length() > 0) {
				parentId = translateSearchExpressionToId(component, parentId, currentId, originalExpression);
			} else
				throw new FacesException("Invalid search expression: " + originalExpression);
		}

		UIComponent c = component.findComponent(parentId);
		return c.getClientId();
	}

	private static String translateSearchExpressionToId(UIComponent component, String parentId, String currentId,
			String originalExpression) {
		try {
			List<UIComponent> c;
			if (currentId.startsWith("@")) {
				AbstractExpressionResolver resolver;
				if (resolvers.containsKey(currentId)) {
					resolver = resolvers.get(currentId);
				} else {
					String className = currentId.substring(1, 2).toUpperCase();
					if (currentId.length()>2) className += currentId.substring(2);
					Class<?> rc = Class.forName("net.bootsfaces.expressions." + className + "ExpressionResolver");
					resolver = (AbstractExpressionResolver) rc.newInstance();
					synchronized (resolvers) {
						if (!resolvers.containsKey(currentId)) resolvers.put(currentId, resolver);
					}
				}
				c = resolver.resolve(component, parentId, currentId, originalExpression);
			} else
				c = idExpressionResolver.resolve(component, parentId, currentId, originalExpression);
			if (null != c && c.size() == 1) {
				parentId += ":" + c.get(0).getId();
			} else
				throw new FacesException("Invalid search expression: " + originalExpression + " resolved to " + parentId
						+ ":" + currentId);
			return parentId;
		} catch (ReflectiveOperationException e) {
			throw new FacesException(
					"Invalid search expression: " + originalExpression + " The subexpression " + currentId + " doesn't exist");
		}
	}

	public static List<String> getExpressions(String commaSeparatedList) {
		List<String> expressions = new ArrayList<String>();
		int pos = 0;
		int start = 0;
		while (pos < commaSeparatedList.length()) {
			char c = commaSeparatedList.charAt(pos);
			if (c == ' ' | c == ',') {
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
