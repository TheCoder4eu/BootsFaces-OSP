/**
 *  Copyright 2022 The BootsFaces developers
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
package net.bootsfaces.test;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

import org.apache.commons.io.IOUtils;

/**
 * Utility methods for testing.
 * 
 * @author flange
 */
public class TestUtils {
	private TestUtils() {
	};

	public static void encodeRenderer(Renderer renderer, FacesContext context, UIComponent component)
			throws IOException {
		renderer.encodeBegin(context, component);
		if (renderer.getRendersChildren()) {
			renderer.encodeChildren(context, component);
		}
		renderer.encodeEnd(context, component);
	}

	public static void addFacet(UIComponent target, String facetName, UIComponent facet) {
		target.getFacets().put(facetName, facet);
	}

	/**
	 * @return a component that encodes only its value without the need of a
	 *         renderer
	 */
	public static UIOutput createSimpleTextComponent() {
		return new UIOutput() {
			@Override
			public String getRendererType() {
				return null;
			}

			@Override
			public void encodeBegin(FacesContext context) throws IOException {
				context.getResponseWriter().write((String) getValue());
			};
		};
	}

	public static <T> String readResourceFile(Class<T> clazz, String fileName) throws IOException {
		InputStream in = clazz.getResourceAsStream(fileName);
		if (in == null) {
			throw new IOException("File not found: " + fileName);
		}
		return IOUtils.toString(in, "UTF-8");
	}

	public static <T> String readResourceFileIgnoringNewlinesAndTabs(Class<T> clazz, String fileName)
			throws IOException {
		return readResourceFile(clazz, fileName).replace("\r", "").replace("\n", "").replace("\t", "");
	}
}
