package net.bootsfaces.ut.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import net.bootsfaces.ut.common.FacesContextMocker;

public class JsfMock implements TestRule {

	public FacesContext mockFacesContext;
	public UIViewRoot mockViewRoot;
	public Application mockApplication;
	public ExternalContext mockExternalContext;
	public HttpSession mockHttpSession;
	public HttpServletRequest mockHttpServletRequest;
	public HttpServletResponse mockHttpServletResponse;
	public PrintWriter mockWriter;

	public ResponseWriter mockResponseWriter = new MyResponseWriter();

	@Override
	public Statement apply(final Statement base, final Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				init();
				try {
					base.evaluate();
				} finally {
					mockFacesContext.release();
				}
			}
		};
	}

	private List<Locale> createLocales() {
		List<Locale> locales = new ArrayList<Locale>();
		locales.add(new Locale("en"));
		locales.add(new Locale("de"));
		return locales;
	}

	public void generateAndTest(UIComponent component, String expected) throws IOException {
		provideMockClientId(component);

		component.encodeBegin(mockFacesContext);
		component.encodeChildren(mockFacesContext);
		component.encodeEnd(mockFacesContext);

		String response = ((MyResponseWriter) mockResponseWriter).getResponse();

		Assert.assertEquals(response, expected);
	}

	public void generateAndTest(UIComponent component, String expected, Renderer renderer) throws IOException {

		provideMockClientId(component);

		renderer.encodeBegin(mockFacesContext, component);
		renderer.encodeChildren(mockFacesContext, component);
		renderer.encodeEnd(mockFacesContext, component);

		String response = ((MyResponseWriter) mockResponseWriter).getResponse();

		Assert.assertEquals(response, expected);
	}

	protected void init() throws IOException {
		mockFacesContext = FacesContextMocker.mockFacesContext();
		mockApplication = Mockito.mock(Application.class);
		mockViewRoot = Mockito.mock(UIViewRoot.class);
		mockExternalContext = Mockito.mock(ExternalContext.class);
		mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		mockHttpServletResponse = Mockito.mock(HttpServletResponse.class);
		mockHttpSession = Mockito.mock(HttpSession.class);
		mockWriter = Mockito.mock(PrintWriter.class);

		Mockito.when(mockHttpServletResponse.getWriter()).thenReturn(mockWriter);

		Mockito.when(mockFacesContext.getApplication()).thenReturn(mockApplication);
		Mockito.when(mockApplication.getSupportedLocales()).thenReturn(createLocales().iterator());

		Mockito.when(mockFacesContext.getViewRoot()).thenReturn(mockViewRoot);
		Mockito.when(mockViewRoot.getLocale()).thenReturn(new Locale("en"));

		Mockito.when(mockFacesContext.getExternalContext()).thenReturn(mockExternalContext);
		Mockito.when(mockExternalContext.getRequest()).thenReturn(mockHttpServletRequest);
		Mockito.when(mockHttpServletRequest.getSession()).thenReturn(mockHttpSession);

		Mockito.when(mockFacesContext.getResponseWriter()).thenReturn(mockResponseWriter);

		Map<String, String> requestMap = new HashMap<String, String>();
		Mockito.when(mockExternalContext.getRequestParameterMap()).thenReturn(requestMap);
	}

	private void provideMockClientId(UIComponent component) {
		Class<?> c = component.getClass();
		while (c != null && c != Object.class) {
			try {
				Field declaredField = c.getDeclaredField("clientId");
				declaredField.setAccessible(true);
				declaredField.set(component, "clientId1");
				break;
			} catch (ReflectiveOperationException tryAgain) {
				c = c.getSuperclass();
			}
		}
	}
        
        public void resetResponseWriter(){
            ((MyResponseWriter) mockResponseWriter).reset();
        }

}