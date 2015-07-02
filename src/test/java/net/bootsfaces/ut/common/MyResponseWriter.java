package net.bootsfaces.ut.common;
import java.io.IOException;
import java.io.Writer;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

public class MyResponseWriter extends ResponseWriter{
	private StringBuffer response = new StringBuffer(); 

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDocument() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startElement(String name, UIComponent component) throws IOException {
		response.append('<');
		response.append(name);
		response.append('>');
		
	}

	@Override
	public void endElement(String name) throws IOException {
		response.append("</");
		response.append(name);
		response.append('>');
		
	}

	@Override
	public void writeAttribute(String name, Object value, String property) throws IOException {
		response.append(' ');
		response.append(name);
		response.append("=\"");
		response.append(value);
		response.append('\"');
		
	}

	@Override
	public void writeURIAttribute(String name, Object value, String property) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeComment(Object comment) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeText(Object text, String property) throws IOException {
		response.append(text);
		
	}

	@Override
	public void writeText(char[] text, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseWriter cloneWithWriter(Writer writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	public String getResponse() {
		return response.toString();
	}
        
        public void reset(){
            this.response.delete(0, response.length());
        }
	
}
