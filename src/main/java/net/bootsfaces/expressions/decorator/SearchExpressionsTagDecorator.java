/**
 *  (C) 2013-2015 Stephan Rauh http://www.beyondjava.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bootsfaces.expressions.decorator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.facelets.Tag;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributes;
import javax.faces.view.facelets.TagDecorator;

/**
 * This is one of the most important classes of AngularFaces. It converts attributes to pass-through parameters, adds
 * them to the list of JSF bean to be synchronized with the client and implements a couple of pseudo JSF tags.
 */
public class SearchExpressionsTagDecorator implements TagDecorator {
	private static boolean active = false;
	private boolean activeByDefault = true;

	private static final String JSF_NAMESPACE = "http://xmlns.jcp.org/jsf/html";
	private static final String JSF_NAMESPACE_OLD = "http://java.sun.com/jsf/html";
	private static final String JSF_CORE_NAMESPACE = "http://xmlns.jcp.org/jsf/core";
	private static final String JSF_CORE_NAMESPACE_OLD = "http://java.sun.com/jsf/core";

	private static final Map<String, Boolean> activePages = new HashMap<String, Boolean>();

	static {
		System.out.println(
				"The BootsFaces Search Expressions decorator is active. You can switch it off globally using the context parameter net.bootsfaces.defaults.decorator in the web.xml or on a per-page basis by adding the attribute bootsFacesDecorator='false'.");
	}

	public SearchExpressionsTagDecorator() {
		String isActive = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.defaults.decorator");

		if ("false".equalsIgnoreCase(isActive)) {
			activeByDefault = false;
		} else {
			activeByDefault = true;
		}
	}

	public static boolean isActive() {
		return active;
	}


	@Override
	public Tag decorate(Tag tag) {
		TagAttribute decorator = tag.getAttributes().get("bootsFacesDecorator");
		String page = tag.getLocation().getPath();
		if (decorator != null) {
			boolean decoratorActive = "true".equalsIgnoreCase(decorator.getValue());
			Boolean isActive = activePages.get(page);
			if (isActive == null) {
				activePages.put(page, decoratorActive);
			} else if (isActive.booleanValue() != decoratorActive) {
				activePages.remove(page);
				activePages.put(page, decoratorActive);
			}
		}
		Boolean isActive = activePages.get(page);
		if (isActive == null) {
			isActive = activeByDefault;
		}
		if (isActive == Boolean.TRUE) {
			Tag newTag = addSearchExpressionResolver(tag);
			return newTag;
		} else {
			return tag;
		}

	}

	private Tag addSearchExpressionResolver(Tag tag) {
		if (tag.getNamespace().equals(JSF_CORE_NAMESPACE) || tag.getNamespace().equals(JSF_CORE_NAMESPACE_OLD)
				|| tag.getNamespace().equals(JSF_NAMESPACE_OLD) || tag.getNamespace().equals(JSF_NAMESPACE)) {
			boolean changeFor = containsAdvancesSearchExpression(tag, "for");
			// boolean changeRender = containsAdvancesSearchExpression(tag, "render");
			// boolean changeExecute = containsAdvancesSearchExpression(tag, "execute");

			if (changeFor /* || changeRender || changeExecute */) {
				TagAttribute[] attributes = tag.getAttributes().getAll();
				AFTagAttributes more = new AFTagAttributes(attributes);
				if (changeFor) {
					String old = tag.getAttributes().get("for").getValue();
					more.replaceAttributeValue("for",
							"#{searchExpressionResolverBean.resolve(component, '" + old + "')}");
				}
				// if (changeRender) {
				// String old = tag.getAttributes().get("render").getValue();
				// more.replaceAttributeValue("render", "#{searchExpressionResolverBean.resolve(component, '" + old +
				// "')}");
				// }
				// if (changeExecute) {
				// String old = tag.getAttributes().get("execute").getValue();
				// more.replaceAttributeValue("execute", "#{searchExpressionResolverBean.resolve(component, '" + old +
				// "')}");
				// }
				tag = new Tag(tag.getLocation(), tag.getNamespace(), tag.getLocalName(), tag.getQName(), more);
			}
		}
		return tag;
	}

	private boolean containsAdvancesSearchExpression(Tag tag, String attribute) {
		boolean changeIt = false;
		TagAttribute forAttribute = tag.getAttributes().get(attribute);
		if (null != forAttribute) {
			String value = forAttribute.getValue();
			if (value.contains("*"))
				changeIt = true;
			if (value.contains("@"))
				changeIt = true;
			if (value.equals("@form") || value.equals("@none") || value.equals("@this") || value.equals("@all"))
				changeIt = false;
			if (value.startsWith("#{"))
				changeIt = false;
		}
		return changeIt;
	}
}
