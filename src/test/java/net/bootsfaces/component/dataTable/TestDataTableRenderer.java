package net.bootsfaces.component.dataTable;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.faces.component.UIComponent;
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
public class TestDataTableRenderer {

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
		DataTable component = new DataTable();
		component.setId("a_b");
		component.setPropertyBean(new ADataTablePropertyBean(10, 0, "''", "[]") {
		});

		new DataTableRenderer().encodeEnd(context, component);

		assertEquals(
				"</table><script>$(document).ready(function() {a_bWidget = $(&apos;.a_bTable&apos;);var wrapper = $(&apos;#a_b_wrapper&apos;);wrapper.replaceWith(a_bWidget);"
				+ "var table = a_bWidget.DataTable({	fixedHeader: false,	responsive: false, 	paging: true, 	pageLength: 10, 	lengthMenu: [ 10, 25, 50, 100 ], 	searching: true, 	order: [], });"
				+ "var workInProgressErrorMessage = &apos;Multiple DataTables on the same page are not yet supported when using dataTableProperties attribute; Could not save state&apos;;table.page(0);"
				+ "table.search(&apos;&apos;);table.page.len(10).draw(&apos;page&apos;);"
				+ "table.on(&apos;drawCallback&apos;, function(settings){  updateDatatableProperties(table, settings);});} );</script>",
				stringWriter.getBuffer().toString());
	}

	@Test
	public void testEncodeBegin() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		UIComponent component = new DataTable();
		component.setId("a_b");

		new DataTableRenderer().encodeBegin(context, component);

		assertEquals("<table id=\"a_b\" class=\"table table-striped table-bordered table-hover    a_bTable\" cellspacing=\"0\"><thead><tr/></thead><tbody/>", stringWriter.getBuffer().toString());
	}

}