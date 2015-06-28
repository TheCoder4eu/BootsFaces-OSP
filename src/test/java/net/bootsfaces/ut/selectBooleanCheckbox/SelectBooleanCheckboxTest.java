package net.bootsfaces.ut.selectBooleanCheckbox;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.render.Renderer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import net.bootsfaces.component.SelectBooleanCheckbox;
import net.bootsfaces.component.inputText.InputTextRenderer;
import net.bootsfaces.ut.common.JsfMock;
import net.bootsfaces.ut.common.MyResponseWriter;

public class SelectBooleanCheckboxTest {

	@Rule
	public JsfMock jsfMock = new JsfMock();

	@Before
	public void initialize() {
	}

	@Test
	public void testStyleClassAndRequired() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, IOException {
		SelectBooleanCheckbox input = new SelectBooleanCheckbox();

		input.getAttributes().put("styleClass", "styleClass1");
		input.setRequired(true);

		String expected = "<div> class=\"checkbox\"<label><input> id=\"clientId1\" name=\"clientId1\" type=\"checkbox\" class=\"styleClass1\"</input></label></div>"; 

		jsfMock.generateAndTest(input, expected);
	}

}