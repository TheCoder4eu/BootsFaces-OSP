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
        final String ESC = "\\\\";

        CoreRenderer renderer = new CoreRenderer();
        //no escaping
        Assert.assertEquals(null, renderer.escapeClientId(null));
        Assert.assertEquals("", renderer.escapeClientId(""));
        Assert.assertEquals("  ", renderer.escapeClientId("  "));
        Assert.assertEquals("abc def ghi", renderer.escapeClientId("abc def ghi"));
        Assert.assertEquals("  abc def ghi  ", renderer.escapeClientId("  abc def ghi  "));
        //escaping
        Assert.assertEquals(ESC + ".", renderer.escapeClientId("."));
        Assert.assertEquals(" " + ESC + "." + ESC + ". ", renderer.escapeClientId(" .. "));
        Assert.assertEquals(" " + ESC + ":" + ESC + ": ", renderer.escapeClientId(" :: "));
        Assert.assertEquals(" " + ESC + ":" + ESC + "." + ESC + "." + ESC + ": ", renderer.escapeClientId(" :..: "));
        Assert.assertEquals("some" + ESC + ".client" + ESC + ".id", renderer.escapeClientId("some.client.id"));
        Assert.assertEquals("some" + ESC + ":client" + ESC + ":id", renderer.escapeClientId("some:client:id"));
    }
}