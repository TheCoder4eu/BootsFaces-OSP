package net.bootsfaces;

import java.io.File;
import java.net.URL;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;


/**
 * This is a base class for Arquillian integration tests.
 * It creates the base deployment include the boostFaces compiled library. 
 * 
 * @author yersan
 */
public class IntegrationTestsBase {
    
    @ArquillianResource
    protected URL context;

    protected final static String WEBAPP_SRC = "target/test-classes/webapp/";

    @Drone
    protected WebDriver browser;
    
    public static WebArchive createBaseDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsLibraries(new File("target/bootsfaces-test.jar"));

    }
    
    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };
    
     @After
    public void after() {
        System.out.println(browser.getPageSource());
        browser.manage().deleteAllCookies();
    }
}
