/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.layout;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author yersan
 */
@RunWith(Arquillian.class)
public class ColumnITCase {

    @ArquillianResource
    private URL context;

    public final static String WEBAPP_SRC = "target/test-classes/webapp/";

    @Drone
    private WebDriver browser;

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

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    
    
    @Before
    public void setup() throws IOException {
        browser.get(context + "/faces/columnIT.xhtml");
    }

    @Test
    public void renderColumn() {
        String pageTitle = browser.getTitle();
        Assert.assertEquals("Column IT", pageTitle);
    }
    
    @After
    public void after() {
        System.out.println(browser.getPageSource());
        browser.manage().deleteAllCookies();
    }
}
