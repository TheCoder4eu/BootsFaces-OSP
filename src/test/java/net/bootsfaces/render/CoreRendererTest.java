package net.bootsfaces.render;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jbumbala
 *         Date: 27.7.2015
 *         Time: 9:37
 */
public class CoreRendererTest {

    @Test
    public void testEscapeClientId() throws Exception {
        //double backslash escape sequence
        CoreRenderer renderer = new CoreRenderer();
        //no escaping
        Assert.assertEquals(null, renderer.escapeClientId(null));
        Assert.assertEquals("", renderer.escapeClientId(""));
        Assert.assertEquals("  ", renderer.escapeClientId("  "));
        Assert.assertEquals("abc def ghi", renderer.escapeClientId("abc def ghi"));
        Assert.assertEquals("  abc def ghi  ", renderer.escapeClientId("  abc def ghi  "));
        //escaping
        Assert.assertEquals("form_text", renderer.escapeClientId("form:text"));
        Assert.assertEquals("some_client_id", renderer.escapeClientId("some:client:id"));
    }
}