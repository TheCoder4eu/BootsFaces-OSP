package net.bootsfaces.component;

import java.io.IOException;
import net.bootsfaces.component.validators.RequiredInputTextValidator;
import net.bootsfaces.IntegrationTestsBase;
import org.jboss.arquillian.container.test.api.Deployment;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

/**
 * Simple test which validates the rendered information for b:inputText tag. 
 * It opens the inputTextIT.xhtml file and assert that the rendered information is
 * correct.
 *
 * @author yersan
 */
@RunWith(Arquillian.class)
public class InputTextITCase extends IntegrationTestsBase {

    
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive deployment = createBaseDeployment()
                .addClass(InputTextSessionBean.class)
                .addClass(RequiredInputTextValidator.class)
                .addAsWebResource("inputText.xhtml");

        
        System.out.println(deployment.toString(true));

        return deployment;
    }

    @Before
    public void setup() throws IOException {
        browser.get(context + "/faces/inputText.xhtml");
    }

    
    @FindBy(id = "txt_1")
    private GrapheneElement txt_1;

    @FindBy(id = "txt_2")
    private GrapheneElement txt_2;

    @FindBy(id = "txt_3")
    private GrapheneElement txt_3;
    
    @FindBy(id = "txt_4")
    private GrapheneElement txt_4;
     
    @FindBy(id = "txt_4_label")
    private GrapheneElement txt_4_label;

    @FindBy(id = "cmd")
    private GrapheneElement cmd;

    @FindBy(id = "msg")
    private GrapheneElement msg;
    
    @FindByJQuery("#msg li")
    private GrapheneElement facesMessage;
    
    @FindBy(id = "txt_1_label")
    private GrapheneElement txt_1_label;
    
    
    

    @Test
    @InSequence(1)
    public void testInputTextRender() {
        String pageTitle = browser.getTitle();
        
        //assert page title
        assertEquals("InputText IT", pageTitle);
       
        assertTrue("txt_1 rendered failed", txt_1.getAttribute("onchange").equals("var dummy=0;"));
        assertTrue("txt_1 rendered failed", txt_1.getAttribute("onselect").equals("var dummy=0;"));
    }
    
    @Test
    @InSequence(10)
    public void testInputTextValidator() {
        
        //assert that there is no message
        assertFalse(facesMessage.isPresent());
        
        
        txt_2.clear();
        
        //submit
        guardAjax(cmd).click();
        
        //assert that there is message
        assertTrue(facesMessage.getText().contains(RequiredInputTextValidator.VALUE_REQUIRED_MSG));
        
        
        txt_2.sendKeys("DUMMY");
        
    }
    
    @Test
    @InSequence(20)
    public void testInputTextBinding() {
        //binding value was set in backed bean, must be checked
        assertTrue("txt_3 binding failed", txt_3.getAttribute("value").contains("VALUE BINDING"));
    }
    
    
    @Test
    @InSequence(30)
    public void testInputTextValuechangeListener() {
        
        txt_1.clear();
        
        //change listener count is 0 ?
        assertTrue(txt_1_label.getText().contains("0"));
        
        txt_1.sendKeys("CHANGED VALUE");
        
         //submit
        guardAjax(cmd).click();
        
       //change listener count is 1 ?
        assertTrue(txt_1_label.getText().contains("1"));
    }
    
    
    @Test
    @InSequence(40)
    public void testImmediate() {
        String txt4Label = txt_4_label.getText();
        String txt4 = txt_4.getAttribute("value");
        
        //values must be the same
        assertTrue(txt4.equals(txt4Label));
        txt_4.sendKeys(txt4Label + " changed");
        
         //submit
        guardAjax(cmd).click();
        
        //after submit form, text label must be the same
        assertTrue("inmediate attribute failed.", txt_4.getAttribute("value").equals(txt4Label));
    }
    
       
    

    
}
