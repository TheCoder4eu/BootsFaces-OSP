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
package net.bootsfaces.component.inputTextarea;

import static net.bootsfaces.test.TestUtils.readResourceFileIgnoringNewlinesAndTabs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.myfaces.renderkit.html.HtmlTextRenderer;
import org.apache.myfaces.shared.renderkit.html.HtmlResponseWriterImpl;
import org.apache.myfaces.view.facelets.FaceletTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * @author flange
 */
public class InputTextareaFaceletTest extends FaceletTestCase {
	private FacesContext facesContext;
	private UIViewRoot root;

	@Before
	public void before() {
		facesContext = FacesContext.getCurrentInstance();
		root = facesContext.getViewRoot();
	}

	@Override
	protected void setupComponents() {
		application.addComponent(UIViewRoot.COMPONENT_TYPE, UIViewRoot.class.getName());
		application.addComponent(InputTextarea.COMPONENT_TYPE, InputTextarea.class.getName());
		application.addComponent(HtmlOutputText.COMPONENT_TYPE, HtmlOutputText.class.getName());
	}

	@Override
	protected void setupRenderers() {
		renderKit.addRenderer(InputTextarea.COMPONENT_FAMILY, "net.bootsfaces.component.InputTextareaRenderer",
				new InputTextareaRenderer());
		renderKit.addRenderer(HtmlOutputText.COMPONENT_FAMILY, "javax.faces.Text", new HtmlTextRenderer());
	}

	private String buildAndRenderView(String view) throws IOException {
		vdl.buildView(facesContext, root, view);
		StringWriter writer = new StringWriter();
		ResponseWriter responseWriter = new HtmlResponseWriterImpl(writer, "text/html", "UTF-8", false);
		facesContext.setResponseWriter(responseWriter);
		root.encodeAll(facesContext);
		return writer.toString();
	}

	@Test
	public void test_encode_withNotRenderedComponent() throws IOException {
		String expected = readResourceFileIgnoringNewlinesAndTabs(getClass(), "encode_withNotRenderedComponent.html");
		assertEquals(expected, buildAndRenderView("encode_withNotRenderedComponent.xhtml"));
	}

	@Test
	public void test_encode_withDefaultComponent() throws IOException {
		String expected = readResourceFileIgnoringNewlinesAndTabs(getClass(), "encode_withDefaultComponent.html");
		assertEquals(expected, buildAndRenderView("encode_withDefaultComponent.xhtml"));
	}

	@Test
	public void test_encode_withLabel() throws IOException {
		String expected = readResourceFileIgnoringNewlinesAndTabs(getClass(), "encode_withLabel.html");
		assertEquals(expected, buildAndRenderView("encode_withLabel.xhtml"));
	}

	@Test
	public void test_encode_withPrependFacet() throws IOException {
		String expected = readResourceFileIgnoringNewlinesAndTabs(getClass(), "encode_withPrependFacet.html");
		assertEquals(expected, buildAndRenderView("encode_withPrependFacet.xhtml"));
	}

	@Test
	public void test_encode_withAppendFacet() throws IOException {
		String expected = readResourceFileIgnoringNewlinesAndTabs(getClass(), "encode_withAppendFacet.html");
		assertEquals(expected, buildAndRenderView("encode_withAppendFacet.xhtml"));
	}
}
