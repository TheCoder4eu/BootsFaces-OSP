/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.layout;

import java.io.File;
import java.io.IOException;
import net.bootsfaces.IntegrationTestsBase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author yersan
 */
@RunWith(Arquillian.class)
public class ColumnITCase extends IntegrationTestsBase {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive deployment = ShrinkWrap.create(WebArchive.class, "test.war");

        deployment.merge(ShrinkWrap.create(GenericArchive.class)
                .as(ExplodedImporter.class)
                .importDirectory(WEBAPP_SRC)
                .as(GenericArchive.class), "/", Filters.includeAll())
                .addAsLibraries(new File("target/bootsfaces.jar"));

        System.out.println(deployment.toString(true));

        return deployment;
    }
    
    
    @Before
    public void setup() throws IOException {
        browser.get(context + "/faces/columnIT.xhtml");
    }

    @Test
    public void renderColumn() {
        String pageTitle = browser.getTitle();
        Assert.assertEquals("Column IT", pageTitle);
    }
    
}
