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

import static net.bootsfaces.test.TestUtils.addFacet;
import static net.bootsfaces.test.TestUtils.createSimpleTextComponent;
import static net.bootsfaces.test.TestUtils.encodeRenderer;
import static net.bootsfaces.test.TestUtils.readResourceFileIgnoringNewlinesAndTabs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.io.StringWriter;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

import org.apache.myfaces.test.mock.MockHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import net.bootsfaces.test.MockedJSFContainerExtension;

/**
 * @author flange
 */
class InputTextareaRendererTest {
	private FacesContext context;
	private MockHttpServletRequest servletRequest;
	private StringWriter writer;
	private InputTextarea comp;
	private InputTextareaRenderer renderer = new InputTextareaRenderer();

	@RegisterExtension
	private MockedJSFContainerExtension ext = new MockedJSFContainerExtension();

	@BeforeEach
	public void setUp() {
		context = ext.getContainer().getFacesContext();
		servletRequest = ext.getContainer().getRequest();
		writer = ext.getWriter();
		comp = new InputTextarea();
		comp.setId("myId");
	}

	/*
	 * Tests for decode
	 */
	@Test
	void test_decode_withDisabledComponent() {
		comp.setDisabled(true);
		assertNull(comp.getSubmittedValue());

		servletRequest.addParameter("input_myId", "the value");
		renderer.decode(context, comp);
		assertNull(comp.getSubmittedValue());
	}

	@Test
	void test_decode_withReadonlyComponent() {
		comp.setDisabled(true);
		assertNull(comp.getSubmittedValue());

		servletRequest.addParameter("input_myId", "the value");
		renderer.decode(context, comp);
		assertNull(comp.getSubmittedValue());
	}

	@Test
	void test_decode_withWritableComponent() {
		assertNull(comp.getSubmittedValue());

		servletRequest.addParameter("input_wrongId", "the value");
		renderer.decode(context, comp);
		assertNull(comp.getSubmittedValue());

		servletRequest.addParameter("input_myId", "the value");
		renderer.decode(context, comp);
		assertEquals("the value", comp.getSubmittedValue());
	}

	/*
	 * Tests for encoding methods
	 */
	@Test
	public void test_encode_withNotRenderedComponent() throws IOException {
		comp.setRendered(false);

		encodeRenderer(renderer, context, comp);

		assertEquals("", writer.toString());
	}

	@Test
	public void test_encode_withDefaultComponent() throws IOException {
		comp.setValue("the value");

		encodeRenderer(renderer, context, comp);

		String expected = readResourceFileIgnoringNewlinesAndTabs(this.getClass(), "encode_withDefaultComponent.txt");
		assertEquals(expected, writer.toString());
	}

	@Test
	public void test_encode_withLabel() throws IOException {
		comp.setValue("the value");
		comp.setLabel("the label");

		encodeRenderer(renderer, context, comp);

		String expected = readResourceFileIgnoringNewlinesAndTabs(this.getClass(), "encode_withLabel.txt");
		assertEquals(expected, writer.toString());
	}

	@Test
	public void test_encode_withPrependFacet() throws IOException {
		comp.setValue("the value");
		UIOutput text = createSimpleTextComponent();
		text.setValue("prepend value");
		addFacet(comp, "prepend", text);

		encodeRenderer(renderer, context, comp);

		String expected = readResourceFileIgnoringNewlinesAndTabs(this.getClass(), "encode_withPrependFacet.txt");
		assertEquals(expected, writer.toString());
	}

	@Test
	public void test_encode_withAppendFacet() throws IOException {
		comp.setValue("the value");
		UIOutput text = createSimpleTextComponent();
		text.setValue("append value");
		addFacet(comp, "append", text);

		encodeRenderer(renderer, context, comp);

		String expected = readResourceFileIgnoringNewlinesAndTabs(this.getClass(), "encode_withAppendFacet.txt");
		assertEquals(expected, writer.toString());
	}
}
