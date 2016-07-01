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

package net.bootsfaces.decorator;

import java.util.HashMap;
import java.util.Map;

import javax.faces.render.Renderer;
import javax.faces.view.Location;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.Tag;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributes;
import javax.faces.view.facelets.TagDecorator;

/**
 * A simple tag decorator to enable jsf: syntax
 */
public class RelaxedTagDecorator implements TagDecorator {

	private static enum Mapper {
		body("h:body"),
		head("h:head"),
		label("h:outputLabel"),
		form("h:form"),
		button(new ElementConverter("b:commandButton", "jsf:outcome"), new ElementConverter("b:navLink"));

		private ElementConverter elementConverter;

		private Mapper(final ElementConverter... elementConverters) {
			if (elementConverters.length == 1) {
				this.elementConverter = elementConverters[0];
			} else {
				this.elementConverter = new ElementConverter() {
					@Override
					public Tag decorate(Tag tag) {
						for (ElementConverter converter : elementConverters) {
							Tag decorated = converter.decorate(tag);
							if (decorated != null) {
								return decorated;
							}
						}
						return null;
					}
				};
			}
		}

		private Mapper(String faceletTag) {
			elementConverter = new ElementConverter(faceletTag);
		}
	}

	private static enum Namespace {
		p("http://xmlns.jcp.org/jsf/passthrough"), jsf("http://xmlns.jcp.org/jsf"), h("http://java.sun.com/jsf/html"),
		b("http://bootsfaces.net/ui");

		private String uri;

		Namespace(String uri) {
			this.uri = uri;
		}
	}

	public Tag decorate(Tag tag) {
		String ns = tag.getNamespace();
		// we only handle html tags!
		if (!("".equals(ns) || "http://www.w3.org/1999/xhtml".equals(ns))) {
			throw new FaceletException("Elements with namespace " + ns + " may not have attributes in namespace "
					+ Namespace.jsf.uri + "." + " Namespace " + Namespace.jsf.uri
					+ " is intended for otherwise non-JSF-aware markup, such as <input type=\"text\" jsf:id >"
					+ " It is not valid to have <h:commandButton jsf:id=\"button\" />.");
		}
		for (Mapper mapper : Mapper.values()) {
			if (tag.getLocalName().equals(mapper.name())) {
				return mapper.elementConverter.decorate(tag);
			}
		}

		return null;
	}

	private static class ElementConverter implements TagDecorator {
		private String localName;
		private Namespace namespace;
		private String arbiterAttributeName;
		private String arbiterAttributeNamespace = "";
		private Map<String, String> additionalMappings = new HashMap<String, String>();
		private String otherHtmlIdAttribute;

		private ElementConverter() {
			super();
		}

		private ElementConverter(String faceletsTag) {
			this(faceletsTag, null);
		}

		private ElementConverter(String faceletsTag, String arbiterAttributeName) {
			String[] strings = faceletsTag.split(":");
			this.namespace = Namespace.valueOf(strings[0]);
			this.localName = strings[1];
			this.arbiterAttributeName = arbiterAttributeName;

			if (arbiterAttributeName != null && arbiterAttributeName.indexOf(':') > 0) {
				strings = arbiterAttributeName.split(":");
				this.arbiterAttributeNamespace = Namespace.valueOf(strings[0]).uri;
				this.arbiterAttributeName = strings[1];
			}
		}

		public Tag decorate(Tag tag) {
			if (arbiterAttributeName == null) {
				// no arbiter
				return convertTag(tag, namespace, localName);
			}

			TagAttribute arbiterAttribute = tag.getAttributes().get(arbiterAttributeNamespace, arbiterAttributeName);

			String myLocalName=null;
			if (arbiterAttribute == null) {
				// no arbiter
//				return null;
			} else {

			myLocalName = additionalMappings.get(arbiterAttribute.getValue());
			}

			if (myLocalName == null) {
				myLocalName = this.localName;
			}

			return convertTag(tag, namespace, myLocalName);
		}

		protected Tag convertTag(Tag tag, Namespace namespace, String localName) {
			Location location = tag.getLocation();
			String ns = namespace.uri;
			String qName = namespace.name() + ":" + localName;

			TagAttributes attributes = convertAttributes(tag.getAttributes(), tag);

			Tag converted = new Tag(location, ns, localName, qName, attributes);

			for (TagAttribute tagAttribute : attributes.getAll()) {
				// set the correct tag
				tagAttribute.setTag(converted);
			}

			return converted;
		}

		protected TagAttributes convertAttributes(TagAttributes original, Tag element) {
			Map<String, TagAttribute> attributes = new HashMap<String, TagAttribute>();
			TagAttribute elementName = createElementName(element);
			attributes.put(elementName.getQName(), elementName);

			for (TagAttribute attribute : original.getAll()) {
				TagAttribute converted = convertTagAttribute(attribute);
				// avoid duplicates
				attributes.put(converted.getQName(), converted);
			}

			return new AFTagAttributes(attributes.values().toArray(new TagAttribute[attributes.size()]));
		}

		private TagAttribute createElementName(Tag tag) {
			Location location = tag.getLocation();
			String ns = Namespace.p.uri;
			String myLocalName = Renderer.PASSTHROUGH_RENDERER_LOCALNAME_KEY;
			String qName = "p:" + myLocalName;
			String value = tag.getLocalName();

			return TagAttributeUtilities.createTagAttribute(location, ns, myLocalName, qName, value);
		}

		protected TagAttribute convertTagAttribute(TagAttribute attribute) {
			Location location = attribute.getLocation();
			String ns = attribute.getNamespace();
			String myLocalName = attribute.getLocalName();
			String qName;
			String value = attribute.getValue();

			if (Namespace.jsf.uri.equals(attribute.getNamespace())) {
				// make this a component attribute
				qName = myLocalName;
				ns = "";
			} else {
				if (ns.length() != 0 && !ns.equals(attribute.getTag().getNamespace())) {
					// the attribute has a different namespace than the tag.
					// preserve it.
					return attribute;
				}
				if (attribute.getLocalName().equals(otherHtmlIdAttribute)) {
					// special case for input name
					qName = "id";
					myLocalName = "id";
				} else {
					// make this a pass through attribute
					qName = "h:" + myLocalName;
					ns = Namespace.h.uri;
				}
			}
			return TagAttributeUtilities.createTagAttribute(location, ns, myLocalName, qName, value);
		}
	}

}
