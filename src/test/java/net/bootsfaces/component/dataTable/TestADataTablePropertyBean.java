package net.bootsfaces.component.dataTable;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author jottyfan
 *
 */
public class TestADataTablePropertyBean {
	@Test
	public void testGetJson() {
		assertEquals("{\"searchTerm\":null,\"pageLength\":null,\"currentPage\":null,\"orderString\":null}",
				new ADataTablePropertyBean(null, null, null, null) {
				}.getJson());
		assertEquals("{\"searchTerm\":\"this is crap\",\"pageLength\":10,\"currentPage\":1,\"orderString\":\"[]\"}",
				new ADataTablePropertyBean(10, 1, "this is crap", "[]") {
				}.getJson());
	}

	@Test
	public void testSetJson() throws ParseException {
		ADataTablePropertyBean bean = new ADataTablePropertyBean(null, null, null, null) {
		};
		bean.setJson("{}");
		assertNull(bean.getPageLength());
		assertNull(bean.getCurrentPage());
		assertNull(bean.getSearchTerm());
		assertNull(bean.getOrderString());
		
		bean.setJson("{\"searchTerm\":\"this is crap\",\"pageLength\":10,\"currentPage\":1,\"orderString\":\"[]\"}");
		assertEquals(new Integer(10), bean.getPageLength());
		assertEquals(new Integer(1), bean.getCurrentPage());
		assertEquals("this is crap", bean.getSearchTerm());
		assertEquals("[]", bean.getOrderString());		
	}
}
