package net.bootsfaces.component.dataTable;

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
		component.setPropertyBean(new ADataTablePropertyBean(10, 0, "", "[]") {
		});

		new DataTableRenderer().encodeEnd(context, component);

		assertEquals("</table><script>"
				+ "$(document).ready(function() {"
				+ "a_bWidget = $(&apos;"
				+ ".a_bTable&apos;"
				+ ");"
				+ "var wrapper = $(&apos;#a_b_wrapper&apos;);"
				+ "wrapper.replaceWith(a_bWidget);"
				+ "var table = a_bWidget.DataTable({	fixedHeader: false,	responsive: false, 	paging: true, 	pageLength: 10, 	lengthMenu: [ 10, 25, 50, 100 ], 	searching: true, 	order: [], });"
				+ "var workInProgressErrorMessage = &apos;Multiple DataTables on the same page are not yet supported when using dataTableProperties attribute; Could not save state&apos;;"
				+ "table.page(0);"
				+ "table.search(&apos;&apos;);"
				+ "table.page.len(10).draw(&apos;page&apos;);"
				+ "a_bWidget.on('draw.dt', function(e, settings){"
				+ "  var oldUserProperties = $(\"[id='a_b.userProperties']\").val();"
				+ "  var oSearchTerm = settings.oPreviousSearch.sSearch;"
				+ "  var oOrderString = settings.aaSorting;"
				+ "  var oPageLength = parseInt(settings._iDisplayLength);"
				+ "  var oCurrentPage = parseInt((settings._iDisplayStart + 1) / oPageLength);"
				+ "  var newUserProperties = JSON.stringify({\"searchTerm\": oSearchTerm, \"orderString\": oOrderString, \"currentPage\": oCurrentPage, \"pageLength\": oPageLength});"
				+ "  if (oldUserProperties == newUserProperties) return;"
				+ "  $(\"[id='a_b.userProperties']\").val(newUserProperties);"
				+ "  $(\"[id='a_b.userProperties']\").trigger(\"change\");"
				+ "});"
				+ "} );</script>",
				stringWriter.getBuffer().toString());
	}

	@Test
	public void testEncodeBegin() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		DataTable component = new DataTable();
		component.setId("a_b");
		component.setPropertyBean(new ADataTablePropertyBean(10, 0, "this is crap", null) {
		});

		new DataTableRenderer().encodeBegin(context, component);

		assertEquals(
				"<input id=\"a_b.userProperties\" name=\"a_b.userProperties\" "
				+ "value=\"{&quot;searchTerm&quot;:&quot;this is crap&quot;,&quot;pageLength&quot;:10,&quot;currentPage&quot;:0,&quot;orderString&quot;:null}\" "
				+ "type=\"hidden\" "
				+ "onchange=\"BsF.ajax.callAjax(this, event, null, &apos;a_b.userProperties&apos;, null, null, null);;\"/>"
						+ "<table id=\"a_b\" class=\"table table-striped table-bordered table-hover    a_bTable\" cellspacing=\"0\">"
						+ "<thead><tr/></thead><tbody/>",
				stringWriter.getBuffer().toString());
	}

}
