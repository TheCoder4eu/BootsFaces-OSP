package net.bootsfaces.component.ajax;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

/**
 * Decorator for ResponseWriter, which caches all calls to
 * writeAttribute. See https://github.com/TheCoder4eu/BootsFaces-OSP/issues/750
 * @author Thomas Oster <mail@thomas-oster.de>
 */
public class AttributeOverwritingResponseWriter extends ResponseWriter {

    private final ResponseWriter wrapped;

    private static class AttributeToWrite {

        Object o;
        String string1;
    }

    public void flushCachedAttributes() throws IOException {
        for (Entry<String, AttributeToWrite> e : attributeCache.entrySet()) {
            wrapped.writeAttribute(e.getKey(), e.getValue().o, e.getValue().string1);
        }
        attributeCache.clear();
    }

    public static Map<String, AttributeToWrite> attributeCache = new LinkedHashMap<>();

    public AttributeOverwritingResponseWriter(ResponseWriter w) {
        this.wrapped = w;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        wrapped.write(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        wrapped.close();
    }

    @Override
    public String getContentType() {
        return wrapped.getContentType();
    }

    @Override
    public String getCharacterEncoding() {
        return wrapped.getCharacterEncoding();
    }

    @Override
    public void flush() throws IOException {
        wrapped.flush();
    }

    @Override
    public void startDocument() throws IOException {
        wrapped.startDocument();
    }

    @Override
    public void endDocument() throws IOException {
        wrapped.endDocument();
    }

    @Override
    public void startElement(String string, UIComponent uic) throws IOException {
        wrapped.startElement(string, uic);
    }

    @Override
    public void endElement(String string) throws IOException {
        wrapped.endElement(string);
    }

    @Override
    public void writeAttribute(String string, Object o, String string1) throws IOException {
        AttributeToWrite value = new AttributeToWrite();
        value.o = o;
        value.string1 = string1;
        attributeCache.put(string, value);
    }

    @Override
    public void writeURIAttribute(String string, Object o, String string1) throws IOException {
        wrapped.writeURIAttribute(string, o, string1);
    }

    @Override
    public void startCDATA() throws IOException {
        wrapped.startCDATA();
    }

    @Override
    public void endCDATA() throws IOException {
        wrapped.endCDATA();
    }

    @Override
    public void writeComment(Object o) throws IOException {
        wrapped.writeComment(o);
    }

    @Override
    public void writePreamble(String preamble) throws IOException {
        wrapped.writePreamble(preamble);
    }

    @Override
    public void writeDoctype(String doctype) throws IOException {
        wrapped.writeDoctype(doctype);
    }

    @Override
    public void writeText(Object o, String string) throws IOException {
        wrapped.writeText(o, string);
    }

    @Override
    public void writeText(Object text, UIComponent component, String property) throws IOException {
        wrapped.writeText(text, component, property);
    }

    @Override
    public void writeText(char[] chars, int i, int i1) throws IOException {
        wrapped.writeText(chars, i, i1);
    }

    @Override
    public ResponseWriter cloneWithWriter(Writer writer) {
        return wrapped.cloneWithWriter(writer);
    }

}
