package net.bootsfaces;

import java.net.URL;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yeray Santana Borges
 */
public class IntegrationTestsBase {
     @ArquillianResource
    protected URL context;

    protected final static String WEBAPP_SRC = "target/test-classes/webapp/";

    @Drone
    protected WebDriver browser;
    
    
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
