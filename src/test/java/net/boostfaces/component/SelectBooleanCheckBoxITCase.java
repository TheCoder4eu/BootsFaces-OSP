/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boostfaces.component;

import net.bootsfaces.layout.*;
import java.io.IOException;
import javax.inject.Inject;
import net.bootsfaces.IntegrationTestsBase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Simple test which validates the rendered information for b:selectbooleancheckbox tag. 
 * It opens the SelectBooleanCheckBox.xhtml file and assert that the rendered information is
 * correct.
 *
 * @author yersan
 */
@RunWith(Arquillian.class)
public class SelectBooleanCheckBoxITCase extends IntegrationTestsBase {

    
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive deployment = createBaseDeployment()
                .addClass(SelectBooleanCheckBoxSessionBean.class)
                .addClass(RequiredCheckboxValidator.class)
                .addAsWebResource("SelectBooleanCheckBox.xhtml");

        
        System.out.println(deployment.toString(true));

        return deployment;
    }

    @Before
    public void setup() throws IOException {
        browser.get(context + "/faces/SelectBooleanCheckBox.xhtml");
    }

    @Inject
    SelectBooleanCheckBoxSessionBean selectBooleanCheckBoxSessionBean;

    @Test
    public void renderSelectBooleanCheckBox() {
        String pageTitle = browser.getTitle();
        
        assertEquals("SelectBooleanCheckBox IT", pageTitle);
    }

}
