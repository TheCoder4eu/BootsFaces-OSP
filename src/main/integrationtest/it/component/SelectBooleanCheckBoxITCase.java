package net.bootsfaces.it.component;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

import net.bootsfaces.component.SelectBooleanCheckBoxSessionBean;
import net.bootsfaces.component.validators.RequiredCheckBoxValidator;
import net.bootsfaces.it.IntegrationTestsBase;

/**
 * Simple test which validates the rendered information for b:selectbooleancheckbox tag. 
 * It opens the SelectBooleanCheckBox.xhtml file and assert that the rendered information is
 * correct.
 *
 * @author yersan
 */
//@RunWith(Arquillian.class)
public class SelectBooleanCheckBoxITCase extends IntegrationTestsBase {

    
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive deployment = createBaseDeployment()
                .addClass(SelectBooleanCheckBoxSessionBean.class)
                .addClass(RequiredCheckBoxValidator.class)
                .addAsWebResource("selectBooleanCheckBox.xhtml");

        
        System.out.println(deployment.toString(true));

        return deployment;
    }

    @Before
    public void setup() throws IOException {
        browser.get(context + "/faces/selectBooleanCheckBox.xhtml");
    }

    
    @FindBy(id = "chk_1")
    private GrapheneElement chk_1;

    @FindBy(id = "chk_2")
    private GrapheneElement chk_2;

    @FindBy(id = "chk_3")
    private GrapheneElement chk_3;

    @FindBy(id = "cmd")
    private GrapheneElement cmd;

    @FindBy(id = "msg")
    private GrapheneElement msg;
    
    @FindByJQuery("#msg li")
    private GrapheneElement facesMessage;
    
    @FindBy(id = "chk_1_label")
    private GrapheneElement chk_1_label;
    
    
    

//    @Test
    @InSequence(1)
    public void testSelectBooleanCheckBoxRender() {
        String pageTitle = browser.getTitle();
        
        //assert page title
        assertEquals("SelectBooleanCheckBox IT", pageTitle);
       
        assertTrue("chk_1 rendered failed", chk_1.getAttribute("onchange").equals("var dummy=0;"));
        assertTrue("chk_1 rendered failed", chk_1.getAttribute("onselect").equals("var dummy=0;"));
    }
    
//    @Test
    @InSequence(10)
    public void testSelectBooleanCheckBoxValidator() {
        
        //assert that there is no message
        assertFalse(facesMessage.isPresent());
        
        //unselect the checkBox
        chk_2.click();
        
        //submit
        guardAjax(cmd).click();
        
        //assert that there is message
        assertTrue(facesMessage.getText().contains(RequiredCheckBoxValidator.VALUE_REQUIRED_MSG));
        
    }
    
//    @Test
    @InSequence(20)
    public void testSelectBooleanCheckBoxBinding() {
        
        //binding value was set in backed bean, must be checked
        assertTrue("chk_3 binding failed", chk_3.getAttribute("checked").equals("true"));
    }
    
    
//    @Test
    @InSequence(30)
    public void testSelectBooleanCheckBoxValuechangeListener() {
        //change listener count is 0 ?
        assertTrue(chk_1_label.getText().contains("0"));
        
        chk_1.click();
        
         //submit
        guardAjax(cmd).click();
        
       //change listener count is 1 ?
        assertTrue(chk_1_label.getText().contains("1"));
    }
    
       
    

    
}
