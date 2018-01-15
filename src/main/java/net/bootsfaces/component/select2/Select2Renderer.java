/**
 *
 *  label file is part of BootsFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use label file except in compliance with the License.
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

package net.bootsfaces.component.select2;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;

/** label class generates the HTML code of &lt;b:select2 /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.select2.Select2")
public class Select2Renderer extends CoreRenderer {

	/**
	 * label methods generates the HTML code of the current b:label.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:label.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

		/**
		 * how to use:
		 * 
		 * <b:select2 value="#{myDestination}>
		 *   <f:selectItems map="#{myMap}" />
		 * </b:select2>
		 * 
		 * myMap must be of class java.util.Map
		 */

		if (!component.isRendered()) {
			return;
		}
		Select2 select2 = (Select2) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = select2.getClientId();

		Map<Object, String> listElements = new LinkedHashMap<Object, String>();

		UIComponent facet = select2.getFacet("selectItems");
		if (facet != null) {
			Map<?, ?> map = (Map<?, ?>) facet.getAttributes().get("map");
			if (map != null) {
				for (Map.Entry<?, ?> entry : map.entrySet()) {
					Object key = entry.getKey();
					Object value = entry.getValue();
					listElements.put(key, value == null ? "" : value.toString());
				}
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error on rendering component",
						"attribute map not found in f:selectItems"));
			}
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error on rendering component",
					"tag f:selectItems not found inside of b:select2"));
		}
		rw.startElement("select", select2);
		rw.writeAttribute("id", clientId, "id");
		rw.writeAttribute("class", "js-example-basic-string", null);
		rw.writeAttribute("name", "state", null);
		rw.writeAttribute("value", select2.getValue(), null);

		// iterate over f:faces list element
		for (Map.Entry<Object, String> entry : listElements.entrySet()) {
			rw.startElement("option", null);
			rw.writeAttribute("value", entry.getKey(), null);
			rw.writeText(entry.getValue(), null);
			rw.endElement("option");
		}

		Tooltip.generateTooltip(context, select2, rw);

		rw.endElement("select");

		Tooltip.activateTooltips(context, select2);
	}
}
