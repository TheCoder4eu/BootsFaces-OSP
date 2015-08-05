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

package net.bootsfaces.component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.render.JQ;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/badges.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/jq.ui.core.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/jq.ui.theme.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/jq.ui.slider.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "jq/ui/core.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "jq/ui/widget.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "jq/ui/mouse.js", target = "body"),
		@ResourceDependency(library = "bsf", name = "jq/ui/slider.js", target = "body") })
@FacesComponent(C.SLIDER_COMPONENT_TYPE)
public class Slider extends HtmlInputText {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = C.SLIDER_COMPONENT_TYPE;
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	private int min;
	private int max;

	public Slider() {
		setRendererType(null); // this component renders itself
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		Tooltip.addResourceFile();
	}

	/** Method added to prevent AngularFaces from setting the type */
	public String getType() {
		String mode = A.asString(getAttributes().get("mode"), "badge");
		return mode.equals("edit") ? "text" : "hidden";
	}

	/**
	 * Method added to prevent AngularFaces from setting the type
	 * 
	 * @param type
	 *            this parameter is ignored
	 */
	public void setType(String type) {
		// ignore the type - it's defined by the mode attribute

	}

	@Override
	public void decode(FacesContext context) {
		String subVal = context.getExternalContext().getRequestParameterMap().get(getClientId(context));

		if (subVal != null) {
			this.setSubmittedValue(subVal);
			this.setValid(true);
		}
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		/*
		 * <div id="slider"></div>
		 */

		ResponseWriter rw = context.getResponseWriter();
		encodeHTML(context, rw);

	}

	private void encodeHTML(FacesContext context, ResponseWriter rw) throws IOException {
		Map<String, Object> attrs = getAttributes();
		String clientId = getClientId(context);

		String mode = A.asString(attrs.get("mode"), "badge");
		String label = A.asString(attrs.get("label"));

		min = A.toInt(attrs.get("min"), 0);
		max = A.toInt(attrs.get("max"), 100);
		Object v = getSubmittedValue();
		if (v == null) {
			v = this.getValue();
		}
		if (v == null) {
			v = A.asString(attrs.get("value"));
			this.setValue(v);
		}
		if (v == null) {
			v = max / 2;
			this.setValue(v);
		}
		int val = A.toInt(v);
		if (val > max) {
			val = max;
		}
		if (val < min) {
			val = min;
		}
		String o;
		if (attrs.get("orientation") != null) {
			o = A.asString(attrs.get("orientation"));
		} else {
			o = C.H;
		}
		boolean vo = o.startsWith("vertical");
		boolean bottom = o.endsWith("bottom");

		rw.startElement("div", null);// form-group
		Tooltip.generateTooltip(context, attrs, rw);
		rw.writeAttribute("class", "form-group", "class");
		R.encodeRow(rw, null, null, (vo ? "slider-vertical" : "slider")); 
		// -------------------------------------------------------------->
		// <<-- Vertical -->>
		if (vo) {
			if (label != null && !bottom) {
				R.encodeRow(rw, null, null, null);
				encodeVLabel(rw, label);
				rw.endElement("div");/* Row */
			}
			R.encodeRow(rw, null, null, null);
			if (bottom) {
				encodeSliderDiv(rw, vo, clientId);
				rw.endElement("div");/* Row */
				R.encodeRow(rw, null, null, null);
			}
			encodeInput(rw, mode, context, val, clientId, vo, min, max);
			if (!bottom) {
				rw.endElement("div"); /* Row */

				R.encodeRow(rw, null, null, null);
				encodeSliderDiv(rw, vo, clientId);
			}
			rw.endElement("div"); /* Row */
			if (label != null && bottom) {
				R.encodeRow(rw, null, null, null);
				encodeVLabel(rw, label);
				rw.endElement("div"); /* Row */
			}

		} else {
			// <<-- Horizontal -->>

			if (label != null) {
				R.encodeRow(rw, null, null, null);

				R.encodeColumn(rw, null, 6, 6, 6, 6, 0, 0, 0, 0, null, null);
				rw.startElement("label", this);
				rw.writeAttribute("for", clientId, null);
				rw.write(label);
				rw.endElement("label"); // Label

				rw.endElement("div");// Column

				rw.endElement("div");/* Row */
			}
			R.encodeRow(rw, null, null, null);

			encodeInput(rw, mode, context, val, clientId, vo, min, max);

			encodeSliderDiv(rw, vo, clientId);
			rw.endElement("div");/* Row */

		} // if vo

		// <<---------------------------------------
		rw.endElement("div"); // rw.write("<!-- Slider Widget Row
								// -->\n");//Slider Widget Row

		rw.endElement("div"); // rw.write("<!-- form-group -->\n");//form-group

		encodeJS(rw, clientId);
		Tooltip.activateTooltips(context, attrs, this);
	}

	private void encodeVLabel(ResponseWriter rw, String label) throws IOException {
		R.encodeColumn(rw, null, 12, 12, 12, 12, 0, 0, 0, 0, null, null);
		rw.startElement("p", this);
		rw.write(label);
		rw.endElement("p"); // Label
		rw.endElement("div"); // Column
	}

	private void encodeInput(ResponseWriter rw, String mode, FacesContext context, int val, String clientId, boolean vo,
			int min, int max) throws IOException {
		int cols = (vo ? 12 : 1);
		if (!mode.equals("basic")) {
			/*
			 * int span, int offset, int cxs, int csm, int clg, int oxs, int
			 * osm, int olg
			 */
			R.encodeColumn(rw, null, cols, cols, cols, cols, 0, 0, 0, 0, null, null);
			if (mode.equals("badge")) {
				R.encodeBadge(context, this, "_badge", Integer.toString(val));
			}
		}
		removeMisleadingType();
		// Input
		rw.startElement("input", this);
		rw.writeAttribute("id", clientId, null);
		rw.writeAttribute("name", clientId, null);
		rw.writeAttribute("type", (mode.equals("edit") ? "text" : "hidden"), null);
		rw.writeAttribute("size", String.valueOf(max).length() - 1, null);
		rw.writeAttribute("min", min, null);
		rw.writeAttribute("max", max, null);
		rw.writeAttribute("maxlength", String.valueOf(max).length(), null);

		rw.writeAttribute("class", "form-control input-sm" + (vo ? " text-center" : ""), "class");

		rw.writeAttribute("value", val, null);

		// if (rdonly) { rw.writeAttribute(H.READONLY, H.READONLY, null); }
		rw.endElement("input");

		if (!mode.equals("basic")) {
			rw.endElement("div");
		} // Column
	}

	/**
	 * remove wrong type information that may have been added by AngularFaces
	 */
	@SuppressWarnings("rawtypes")
	private void removeMisleadingType() {
		try {
			Method method = getClass().getMethod("getPassThroughAttributes", (Class[])null);
			if (null != method) {
				Object map = method.invoke(this, (Object[])null);
				if (null != map) {
					Map attributes = (Map) map;
					if (attributes.containsKey("type"))
						attributes.remove("type");
				}
			}
		} catch (ReflectiveOperationException ignoreMe) {
			// we don't really have to care about this error
		}

	}

	private void encodeSliderDiv(ResponseWriter rw, boolean vo, String clientId) throws IOException {
		/*
		 * int span, int offset, int cxs, int csm, int clg, int oxs, int osm,
		 * int olg
		 */
		R.encodeColumn(rw, null, (vo ? 12 : 4), (vo ? 12 : 4), (vo ? 12 : 4), (vo ? 12 : 4), 0, 0, 0, 0, null, null);
		// Slider <div>
		rw.startElement("div", null);
		rw.writeAttribute("id", clientId + "_slider", null);// concat
																		// controproducente
		rw.endElement("div");
		rw.endElement("div"); // Column
	}

	private void encodeJS(ResponseWriter rw, String cId) throws IOException {
		Map<String, Object> attrs = getAttributes();
		StringBuilder sb = new StringBuilder(100);
		sb.append("value").append(":").append(this.getValue()).append(",");
		if (attrs.get("max") != null) {
			sb.append("max").append(":").append(A.toInt(attrs.get("max"))).append(",");
		}
		if (attrs.get("min") != null) {
			sb.append("min").append(":").append(A.toInt(attrs.get("min"))).append(",");
		}
		if (attrs.get("orientation") != null) {
			String o = A.asString(attrs.get("orientation"));
			if (o.endsWith("bottom")) {
				o = "vertical";
			}
			sb.append("orientation").append(":").append("'".concat(o).concat("'")).append(",");
		}
		if (attrs.get("step") != null) {
			sb.append("step").append(":").append(A.toInt(attrs.get("step"))).append(",");
		}
		sb.append("range").append(":").append("\"min\"").append(",");

		String hsize = A.asString(attrs.get("handle-size"));
		String hshape = A.asString(attrs.get("handle-shape"));
		boolean hround = ((hshape != null) && (hshape.equals("round")));

		JQ.simpleSlider(rw, cId, sb.toString(), A.asString(attrs.get("mode"), "badge").equals("badge"), hsize, hround);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
	}

	@Override
	public void encodeChildren(FacesContext context) throws IOException {
	}

}
