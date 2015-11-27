package net.bootsfaces.it.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

import net.bootsfaces.it.IntegrationTestsBase;

/**
 * Simple test which validates the rendered information for b:column tag. 
 * It opens the columnIT.xhtml file and assert that the rendered information is
 * correct.
 *
 * @author yersan
 */
//@RunWith(Arquillian.class)
public class ColumnITCase extends IntegrationTestsBase {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive deployment = createBaseDeployment()
                .addAsWebResource("columnIT.xhtml");
        
        
        System.out.println(deployment.toString(true));

        return deployment;
    }

    @Before
    public void setup() throws IOException {
        browser.get(context + "/faces/columnIT.xhtml");
    }

    @FindBy(id = "col_1")
    private GrapheneElement col1;

    @FindBy(id = "col_2")
    private GrapheneElement col2;

    @FindBy(id = "col_3")
    private GrapheneElement col3;

    @FindBy(id = "col_4")
    private GrapheneElement col4;

    @FindBy(id = "col_5")
    private GrapheneElement col5;
    
     @FindBy(id = "col_6")
    private GrapheneElement col6;

//    @Test
    public void renderColumn() {
        String pageTitle = browser.getTitle();
        assertEquals("Column IT", pageTitle);
        
        assertTrue("col_1 rendered failed", col1.getAttribute("class").contains("hidden-xs")
                && col1.getAttribute("class").contains("hidden-sm")
                && col1.getAttribute("class").contains("hidden-lg"));
        
        assertTrue("col_2 rendered failed",(!col2.getAttribute("class").contains("hidden-xs")
                && !col2.getAttribute("class").contains("hidden-sm")
                && !col2.getAttribute("class").contains("hidden-lg")
                ) && (
                        col2.getAttribute("class").contains("col-xs-1")
                                && col2.getAttribute("class").contains("col-sm-1")
                                && col2.getAttribute("class").contains("col-lg-1") )
        );
        
        assertTrue("col_3 rendered failed", col3.getAttribute("class").contains("hidden-lg"));
        
        assertTrue("col_4 rendered failed", col4.getAttribute("class").contains("hidden-sm"));
        
        assertTrue("col_5 rendered failed", col5.getAttribute("class").contains("hidden-xs"));
        
        assertTrue("col_6 rendered failed", !col6.getAttribute("class").contains("hidden-xs")
                && !col6.getAttribute("class").contains("hidden-sm")
                && !col6.getAttribute("class").contains("hidden-lg"));
    }

}
