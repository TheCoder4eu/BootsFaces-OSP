package net.bootsfaces.component.buttonGroup;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;

import org.apache.myfaces.test.mock.MockResponseWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 
 * @author jottyfan
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ FacesContext.class })
public class TestButtonGroupRenderer {

	private StringWriter stringWriter;

	@Before
	public void setUp() {
		stringWriter = new StringWriter();
		PowerMockito.mockStatic(FacesContext.class);
		FacesContext context = Mockito.mock(FacesContext.class);
		UIViewRoot viewRoot = Mockito.mock(UIViewRoot.class);
		Map<String, Object> viewMap = viewRoot.getViewMap(true);
		RenderKit renderKit = Mockito.mock(RenderKit.class);
		ResponseWriter rw = new MockResponseWriter(stringWriter);
		ExternalContext externalContext = Mockito.mock(ExternalContext.class);
		Mockito.when(FacesContext.getCurrentInstance()).thenReturn(context);
		Mockito.when(context.getViewRoot()).thenReturn(viewRoot);
		Mockito.when(viewRoot.getViewMap()).thenReturn(viewMap);
		Mockito.when(context.getRenderKit()).thenReturn(renderKit);
		Mockito.when(context.getExternalContext()).thenReturn(externalContext);
		Mockito.when(context.getResponseWriter()).thenReturn(rw);
	}

	@Test
	public void testEncodeEnd() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ButtonGroup component = new ButtonGroup();
		component.setId("a_b");

		new ButtonGroupRenderer().encodeEnd(context, component);

		assertEquals("</div>", stringWriter.getBuffer().toString());
	}

	@Test
	public void testEncodeBegin() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ButtonGroup component = new ButtonGroup();
		component.setId("a_b");

		new ButtonGroupRenderer().encodeBegin(context, component);

		assertEquals("<div id=\"a_b\" class=\"btn-group\"", stringWriter.getBuffer().toString());
		
		component.setOrientation("vertical");
		stringWriter.getBuffer().setLength(0);
		
		new ButtonGroupRenderer().encodeBegin(context, component);

		assertEquals("><div id=\"a_b\" class=\"btn-group-vertical\"", stringWriter.getBuffer().toString());
	}

}
