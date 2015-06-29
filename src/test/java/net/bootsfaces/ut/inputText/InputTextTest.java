package net.bootsfaces.ut.inputText;
import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import net.bootsfaces.component.inputText.InputText;
import net.bootsfaces.component.inputText.InputTextRenderer;
import net.bootsfaces.ut.common.JsfMock;
import net.bootsfaces.ut.common.MyResponseWriter;

public class InputTextTest {
 
    @Rule
    public JsfMock jsfMock = new JsfMock();
 
    @Before
    public void initialize() {
    }
 
    @Test
    public void testStyleClassAndRequired() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
        InputText input = new InputText();
        
        input.getAttributes().put("styleClass", "styleClass1"); 
        input.setRequired(true);
        String expected="<div> class=\"form-group\"<input> id=\"clientId1\" name=\"clientId1\" type=\"text\" class=\"form-control styleClass1 bf-required\" value=\"null\"</input></div>";
        
        InputTextRenderer inputTextRenderer = new InputTextRenderer();

        jsfMock.generateAndTest(input, expected, inputTextRenderer);
    }
}