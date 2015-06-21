package net.bootsfaces.junit;
import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import net.bootsfaces.component.inputText.InputText;
import net.bootsfaces.component.inputText.InputTextRenderer;

public class InputTextTest {
 
//    private PaymentView paymentView;
 
    @Rule
    public JsfMock jsfMock = new JsfMock();
 
    @Before
    public void initialize() {
//        paymentView = mock(PaymentView.class);
//        ...
    }
 
    @Test
    public void toJson() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // Mock URL and context path
        StringBuffer requestURI = new StringBuffer("http://localhost:8080/webshop");
        Mockito.when(jsfMock.mockHttpServletRequest.getRequestURL()).thenReturn(requestURI);
        Mockito.when(jsfMock.mockHttpServletRequest.getContextPath()).thenReturn("/webshop");
        
        InputText input = new InputText();
        input.setId("clientId1");
        Field declaredField = input.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("clientId");
        declaredField.setAccessible(true);
        declaredField.set(input, "clientId1");
        InputTextRenderer inputTextRenderer = new InputTextRenderer();
        
        try {
			inputTextRenderer.encodeBegin(jsfMock.mockFacesContext, input);
			inputTextRenderer.encodeEnd(jsfMock.mockFacesContext, input);
			System.out.println(((MyResponseWriter)jsfMock.mockResponseWriter).getResponse());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // Invoke toJson method
//        String json = PaymentRequestForm.toJson(jsfMock.mockFacesContext, paymentView);
 
        // Verify
//        ...
    }
}