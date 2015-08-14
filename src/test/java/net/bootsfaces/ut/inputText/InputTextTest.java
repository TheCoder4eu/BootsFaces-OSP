package net.bootsfaces.ut.inputText;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import net.bootsfaces.component.inputText.InputText;
import net.bootsfaces.component.inputText.InputTextRenderer;
import net.bootsfaces.ut.common.JsfMock;

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
        String expected="<div> id=\"clientId1\" class=\"form-group\"<input> id=\"input_clientId1\" name=\"input_clientId1\" type=\"text\" class=\"form-control styleClass1 bf-required\" value=\"null\"</input></div>";
        
        InputTextRenderer inputTextRenderer = new InputTextRenderer();

        jsfMock.generateAndTest(input, expected, inputTextRenderer);
    }
}