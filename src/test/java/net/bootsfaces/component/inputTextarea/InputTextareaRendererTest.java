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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
	private InputTextarea comp;
	private InputTextareaRenderer renderer = new InputTextareaRenderer();

	@RegisterExtension
	private MockedJSFContainerExtension ext = new MockedJSFContainerExtension();

	@BeforeEach
	public void setUp() {
		context = ext.getContainer().getFacesContext();
		servletRequest = ext.getContainer().getRequest();
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
}
