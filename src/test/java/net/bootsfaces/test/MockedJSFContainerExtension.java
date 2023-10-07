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

import java.io.StringWriter;

import javax.faces.context.ResponseWriter;

import org.apache.myfaces.shared.renderkit.html.HtmlResponseWriterImpl;
import org.apache.myfaces.test.mock.MockedJsfTestContainer;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * JUnit5 extension that brings up a mocked JSF environment before the test run
 * and tears it down after the test.
 * 
 * @author flange
 */
public class MockedJSFContainerExtension implements BeforeEachCallback, AfterEachCallback {
	private MockedJsfTestContainer container;
	private StringWriter writer;

	/**
	 * Creates a new JSF mock container and a new response writer.
	 */
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		container = new MockedJsfTestContainer();
		container.setUpAll();

		writer = new StringWriter();
		/*
		 * We use the implementation of MyFaces here, because MockResponseWriter
		 * HTML-escapes JavaScript code in <script> tags.
		 */
		ResponseWriter responseWriter = new HtmlResponseWriterImpl(writer, "text/html", "UTF-8", false);
		container.getFacesContext().setResponseWriter(responseWriter);
	}

	/**
	 * Tears down the JSF mock container.
	 */
	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		container.tearDownAll();
	}

	/**
	 * @return the mocked JSF container instance
	 */
	public MockedJsfTestContainer getContainer() {
		return container;
	}

	/**
	 * @return the writer, in which components/renderers encode their response
	 */
	public StringWriter getWriter() {
		return writer;
	}
}
