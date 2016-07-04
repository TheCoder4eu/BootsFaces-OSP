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

package net.bootsfaces.render;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.component.GenContainerDiv;
import net.bootsfaces.component.icon.Icon;

/**
 * Rendering functions for the core or common to more than one component
 * 
 * @author thecoder4.eu
 */
public final class R {
	// span => col-md-* as of TBS3
	public static final String COLMD = "col-md-"; // Default as of TBS3
	// span => col-md-offset-* as of TBS3
	public static final String OFFSET = "col-md-offset-"; // Default as of TBS3
	// public static final String ="";

	/**
	 * Encodes a Column
	 * 
	 * @param rw
	 * @param c
	 * @param span
	 * @param cxs
	 * @param csm
	 * @param clg
	 * @param offset
	 * @param oxs
	 * @param osm
	 * @param olg
	 * @param style
	 * @param sclass
	 * @throws IOException
	 */
	public static final void encodeColumn(ResponseWriter rw, UIComponent c, int span, int cxs, int csm, int clg,
			int offset, int oxs, int osm, int olg, String style, String sclass) throws IOException {

		rw.startElement("div", c);
		Map<String, Object> componentAttrs = new HashMap<String, Object>();

		if (c != null) {
			rw.writeAttribute("id", c.getClientId(), "id");
			Tooltip.generateTooltip(FacesContext.getCurrentInstance(), c, rw);
			componentAttrs = c.getAttributes();
		}

		StringBuilder sb = new StringBuilder();
		if (span > 0 || offset > 0) {
			if (span > 0) {
				sb.append(COLMD).append(span);
			}
			if (offset > 0) {
				if (span > 0) {
					sb.append(" ");
				}
				sb.append(OFFSET + offset);
			}
		}

		if (cxs > 0) {
			sb.append(" col-xs-").append(cxs);
		}
		if (componentAttrs.get("col-xs") != null && cxs == 0) {
			sb.append(" hidden-xs");
		}

		if (csm > 0) {
			sb.append(" col-sm-").append(csm);
		}
		if (componentAttrs.get("col-sm") != null && csm == 0) {
			sb.append(" hidden-sm");
		}

		if (clg > 0) {
			sb.append(" col-lg-").append(clg);
		}
		if (componentAttrs.get("col-lg") != null && clg == 0) {
			sb.append(" hidden-lg");
		}

		if (oxs > 0) {
			sb.append(" col-xs-offset-").append(oxs);
		}
		if (osm > 0) {
			sb.append(" col-sm-offset-").append(osm);
		}
		if (olg > 0) {
			sb.append(" col-lg-offset-").append(olg);
		}

		if (sclass != null) {
			sb.append(" ").append(sclass);
		}
		rw.writeAttribute("class", sb.toString().trim(), "class");
		if (style != null) {
			rw.writeAttribute("style", style, "style");
		}

		if (null != c) {
			Tooltip.activateTooltips(FacesContext.getCurrentInstance(), c);
		}
	}

	/**
	 * 
	 * @param c
	 * @param fc
	 * @throws IOException
	 */
	public static void genDivContainer(GenContainerDiv c, FacesContext fc) throws IOException {
		/*
		 * <div class="?"> ... </div>
		 */

		ResponseWriter rw = fc.getResponseWriter();

		Map<String, Object> attrs = c.getAttributes();

		String pull = A.asString(attrs.get("pull"));

		rw.startElement("div", c);
		rw.writeAttribute("id", c.getClientId(fc), "id");
		Tooltip.generateTooltip(fc, c, rw);
		if (pull != null && (pull.equals("right") || pull.equals("left"))) {
			rw.writeAttribute("class", c.getContainerStyles().concat(" ").concat("pull").concat("-").concat(pull),
					"class");
		} else {
			rw.writeAttribute("class", c.getContainerStyles(), "class");
		}
	}

	/**
	 * Adds a CSS class to a component within a facet.
	 * 
	 * @param f
	 *            the facet
	 * @param cname
	 *            the class name of the component to be manipulated.
	 * @param aclass
	 *            the CSS class to be added
	 */
	public static void addClass2FacetComponent(UIComponent f, String cname, String aclass) {
		// If the facet contains only one component, getChildCount()=0 and the
		// Facet is the UIComponent
		if (f.getClass().getName().endsWith(cname)) {
			addClass2Component(f, aclass);
		} else {
			if (f.getChildCount() > 0) {
				for (UIComponent c : f.getChildren()) {
					if (c.getClass().getName().endsWith(cname)) {
						addClass2Component(c, aclass);
					}
				}
			}
		}
	}

	/**
	 * Adds a CSS class to a component in the view tree. The class is appended
	 * to the styleClass value.
	 * 
	 * @param c
	 *            the component
	 * @param aclass
	 *            the CSS class to be added
	 */
	protected static void addClass2Component(UIComponent c, String aclass) {
		Map<String, Object> a = c.getAttributes();
		if (a.containsKey("styleClass")) {
			a.put("styleClass", a.get("styleClass") + " " + aclass);
		} else {
			a.put("styleClass", aclass);
		}
	}
	
	/**
	 * Decorate the facet children with a class to render
	 * bootstrap like "prepend" and "append" sections
	 * @param parent
	 * @param comp
	 * @param ctx
	 * @param rw
	 * @throws IOException
	 */
	public static void decorateFacetComponent(UIComponent parent, UIComponent comp, FacesContext ctx, ResponseWriter rw) 
	throws IOException {
		/*
		System.out.println("COMPONENT CLASS = " + comp.getClass().getName());
		System.out.println("FAMILY = " + comp.getFamily());
		System.out.println("CHILD COUNT = " + comp.getChildCount());
		System.out.println("PARENT CLASS = " + comp.getParent().getClass());
		
			
			if (app.getClass().getName().endsWith("Button")
					|| (app.getChildCount() > 0 && app.getChildren().get(0).getClass().getName().endsWith("Button"))) {
				rw.startElement("div", inputText);
				rw.writeAttribute("class", "input-group-btn", "class");
				app.encodeAll(context);
				rw.endElement("div");
			} else {
				if (app instanceof Icon)
					((Icon) app).setAddon(true);
				rw.startElement("span", inputText);
				rw.writeAttribute("class", "input-group-addon", "class");
				app.encodeAll(context);
				rw.endElement("span");
			}
		*/
		if(comp.getChildCount() >= 1 && comp.getClass().getName().endsWith("Panel")) {
			for(UIComponent child: comp.getChildren()) {
				decorateComponent(parent, child, ctx, rw);
			}
		} else {
			decorateComponent(parent, comp, ctx, rw);
		}
	}
	
	/**
	 * Add the correct class
	 * @param parent
	 * @param comp
	 * @param ctx
	 * @param rw
	 * @throws IOException
	 */
	private static void decorateComponent(UIComponent parent, UIComponent comp, FacesContext ctx, ResponseWriter rw) 
	throws IOException {
		if (comp instanceof Icon)
			((Icon) comp).setAddon(true); // modifies the id of the icon
		
		String classToApply = "input-group-addon";
		if(comp.getClass().getName().endsWith("Button") || comp.getChildCount() > 0)
			classToApply = "input-group-btn";
		
		rw.startElement("span", parent);
		rw.writeAttribute("class", classToApply, "class");
		comp.encodeAll(ctx);
		rw.endElement("span");
	}

	/**
	 * Encodes component attributes (HTML 4 + DHTML) TODO: replace this method
	 * with CoreRenderer.renderPassThruAttributes()
	 * 
	 * @param rw
	 *            ResponseWriter instance
	 * @param attrs
	 * @param alist
	 * @throws IOException
	 */
	public static void encodeHTML4DHTMLAttrs(ResponseWriter rw, Map<String, Object> attrs, String[] alist)
			throws IOException {
		// Encode attributes (HTML 4 + DHTML)

		for (String a : alist) {
			if (attrs.get(a) != null) {
				String val = A.asString(attrs.get(a));
				if (val != null && val.length() > 0) {
					rw.writeAttribute(a, val, a);
				}
			}
		}
	}

	/**
	 * Finds the Form Id of a component inside a form.
	 * 
	 * @param fc
	 *            FacesContext instance
	 * @param c
	 *            UIComponent instance
	 * @return
	 */
	public static String findComponentFormId(FacesContext fc, UIComponent c) {
		UIComponent parent = c.getParent();

		while (parent != null) {
			if (parent instanceof UIForm) {
				return parent.getClientId(fc);
			}
			parent = parent.getParent();
		}
		return null;
	}

	/**
	 * Renders the Childrens of a Component
	 * 
	 * @param fc
	 * @param component
	 * @throws IOException
	 */
	public static void renderChildren(FacesContext fc, UIComponent component) throws IOException {
		for (Iterator<UIComponent> iterator = component.getChildren().iterator(); iterator.hasNext();) {
			UIComponent child = (UIComponent) iterator.next();
			renderChild(fc, child);
		}
	}

	/**
	 * Renders the Child of a Component
	 * 
	 * @param fc
	 * @param child
	 * @throws IOException
	 */
	public static void renderChild(FacesContext fc, UIComponent child) throws IOException {
		if (!child.isRendered()) {
			return;
		}

		child.encodeBegin(fc);

		if (child.getRendersChildren()) {
			child.encodeChildren(fc);
		} else {
			renderChildren(fc, child);
		}
		child.encodeEnd(fc);
	}

	// Suppress default constructor for noninstantiability
	private R() {
		throw new AssertionError();
	}
}
