/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.navBar;

import java.io.IOException;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.render.FacesRenderer;

import net.bootsfaces.component.navLink.AbstractNavLink;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:navBar /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.navBar.NavBar")
public class NavBarRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:navBar.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:navBar.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		NavBar navBar = (NavBar) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = navBar.getClientId();

		if (!component.isRendered()) {
			return;
		}

		String fixed = navBar.getFixed();
		boolean fluid = navBar.isFluid();
		String ns = navBar.getStyleClass();
		if (ns==null) {
			ns="";
		} else {
			ns += " ";
		}

		if (navBar.isInverse()) {
			ns += "navbar navbar-inverse";
		} else {
			ns += "navbar navbar-default";
		}

		if (navBar.getPosition() != null) {
			// new API
			if (navBar.getPosition().equals("top")) {
				if (navBar.isSticky()) {
					ns += " navbar-fixed-top";
				} else {
					ns += " navbar-static-top";
				}
			} else if (navBar.getPosition().equals("bottom")) {
				if (navBar.isSticky()) {
					ns += " navbar-fixed-bottom";
				} else {
					ns += " navbar-fixed-bottom navbar-nonsticky";
				}
			} else {
				// don't add any class
			}
		} else {
			if (fixed != null) {
				if (fixed.equals("top")) {
					ns += " navbar-fixed-top";
				}
				if (fixed.equals("bottom")) {
					ns += " navbar-fixed-bottom";
				}
				if (fixed.equals("non-sticky")) {
					ns += " navbar-fixed-bottom navbar-nonsticky";
				}
			}
			if (navBar.isStatic()) {
				ns += " navbar-static-top";
			}
		}

		/*
		 * The <nav> tag defines a set of navigation links. The <nav> element is
		 * intended only for major block of navigation links. The <nav> tag is
		 * supported in Internet Explorer 9, Firefox, Opera, Chrome, and Safari.
		 * See http://www.w3schools.com/tags/tag_nav.asp Note: Internet Explorer
		 * 8 and earlier versions, do not support the <nav> tag. When IE8 will
		 * be dropped there will be HTML5 <nav> tag instead of <div>
		 */
		rw.startElement("nav", navBar);
		rw.writeAttribute("id", navBar.getClientId(context), "id");
		Tooltip.generateTooltip(context, navBar, rw);
		rw.writeAttribute("class", ns, "class");
		super.writeAttribute(rw, "style", navBar.getStyle());
		rw.writeAttribute("role", "navigation", null);

		rw.startElement("div", navBar);
		rw.writeAttribute("class", fluid ? "container-fluid" : "container", "class");
		// x Layout Centrato. TODO : layout full width

		rw.startElement("div", navBar);
		rw.writeAttribute("class", "navbar-header", "class"); // navbar-header
		rw.startElement("button", navBar);
		String tabindex = navBar.getTabindex();
		if (!"0".equals(tabindex)) {
			writeAttribute(rw, "tabindex", tabindex, null);
		}
		rw.writeAttribute("type", "button", "type");
		rw.writeAttribute("class", "navbar-toggle", "class");
		rw.writeAttribute("data-toggle", "collapse", "type");
		rw.writeAttribute("data-target", "#" + escapeClientId(clientId) + "_inner", "data-target");

		rw.startElement("span", navBar);
		rw.writeAttribute("class", "sr-only", "class");
		rw.writeText("Toggle navigation", null);
		rw.endElement("span");
		if (navBar.getFacet("kebab") != null) {
			navBar.getFacet("kebab").encodeAll(context);
		} else {
			rw.startElement("span", navBar);
			rw.writeAttribute("class", "icon-bar", "class");
			rw.endElement("span");
			rw.startElement("span", navBar);
			rw.writeAttribute("class", "icon-bar", "class");
			rw.endElement("span");
			rw.startElement("span", navBar);
			rw.writeAttribute("class", "icon-bar", "class");
			rw.endElement("span");
		}
		rw.endElement("button");
		String brand = navBar.getBrand();
		String brandImg = navBar.getBrandImg();
		if (brand != null || brandImg != null) {
			rw.startElement("a", navBar);
			String onClick = navBar.getOnclick();
			if (null != onClick) {
				rw.writeAttribute("onclick", onClick, "onclick");
			}
			String styleClass = navBar.getBrandStyleClass();
			if (null == styleClass) {
				rw.writeAttribute("class", "navbar-brand", "class");
			} else {
				rw.writeAttribute("class", "navbar-brand " + navBar.getBrandStyleClass(), "class");
			}

			writeAttribute(rw, "style", navBar.getBrandStyle());
			String href = navBar.getBrandHref();
			if (href == null) {
				rw.writeAttribute("href", "#", "href");
			} else {
				rw.writeAttribute("href", href, "href");
				writeAttribute(rw, "target", navBar.getBrandTarget());
			}
			rw.startElement("span", navBar);
			if (brandImg != null) {
				String altText = navBar.getAlt();
				if (altText == null)
					altText = "Brand"; // default
				rw.startElement("img", navBar);
				rw.writeAttribute("alt", altText, "alt");
				rw.writeAttribute("src", brandImg, "src");
				writeAttribute(rw, "style", "vertical-align: top; " + navBar.getBrandImgStyle());
				writeAttribute(rw, "class", navBar.getBrandImgStyleClass());
				rw.endElement("img");
			}
			if (brand != null)
				rw.writeText(brand, null);
			rw.endElement("span");
			rw.endElement("a");
		}
		rw.endElement("div"); // navbar-header

		/*
		 * <!-- Collect the nav links, forms, and other content for toggling -->
		 * <div class="collapse navbar-collapse navbar-ex1-collapse">
		 */
		rw.startElement("div", navBar);
		rw.writeAttribute("id", escapeClientId(clientId) + "_inner", "id");
		rw.writeAttribute("class", "collapse navbar-collapse navbar-ex1-collapse", "class");

	}

	/**
	 * This methods generates the HTML code of the current b:navBar.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:navBar.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		ResponseWriter rw = context.getResponseWriter();
		rw.endElement("div"); // collapse
		rw.endElement("div"); // container
		rw.endElement("nav"); // navbar
		Tooltip.activateTooltips(context, component);

	}

}
