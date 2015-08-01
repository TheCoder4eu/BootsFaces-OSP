package net.bootsfaces.it;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.beans10.BeansDescriptor;
import org.jboss.shrinkwrap.descriptor.api.webapp30.WebAppDescriptor;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

/**
 * This is a base class for Arquillian integration tests. It creates the base
 * deployment include the boostFaces compiled library.
 *
 * @author yersan
 */
public class IntegrationTestsBase {
    
    protected final static String WEBAPP_SRC = "src/test/resources/webapp/";

    @ArquillianResource
    protected URL context;

    @Drone
    protected WebDriver browser;

    
    public static WebArchive createBaseDeployment() {

        WebArchive deployment = ShrinkWrap.create(WebArchive.class, "test.war");
        
        BeansDescriptor beans = Descriptors.importAs(BeansDescriptor.class)
                .fromFile(WEBAPP_SRC+"/WEB-INF/beans.xml");
        
        WebAppDescriptor webDescriptor = Descriptors.importAs(WebAppDescriptor.class)
                .fromFile(WEBAPP_SRC+"/WEB-INF/web.xml");
        

        deployment.addAsWebInfResource(new StringAsset(beans.exportAsString()), "beans.xml")
                    .addAsWebInfResource(new StringAsset(webDescriptor.exportAsString()), "web.xml")
                    .addAsLibraries(new File("target/bootsfaces-test.jar"));
        
        return deployment;
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
