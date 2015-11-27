package net.bootsfaces.it.component;



import net.bootsfaces.component.ImageRequestBean;
import net.bootsfaces.it.IntegrationTestsBase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.arquillian.warp.jsf.AfterPhase;
import org.jboss.arquillian.warp.jsf.BeforePhase;
import org.jboss.arquillian.warp.jsf.Phase;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

import javax.inject.Inject;
import java.io.IOException;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


//@RunWith(Arquillian.class)
@WarpTest
public class ImageITCase extends IntegrationTestsBase {


    @Deployment
    public static WebArchive createDeployment() {
        WebArchive deployment = createBaseDeployment()
                .addClass(IntegrationTestsBase.class)
                .addClass(ImageRequestBean.class)
                .addAsWebResource("imageIT.xhtml");


        System.out.println(deployment.toString(true));

        return deployment;
    }

    @Before
    public void setup() throws IOException {
        browser.get(context + "/faces/imageIT.xhtml");
    }


    @FindBy(id = "image_id_1")
    private GrapheneElement image_id_1;

    @FindBy(id = "image_id_2")
    private GrapheneElement image_id_2;

    @FindBy(id = "image_id_3")
    private GrapheneElement image_id_3;




 //   @Test
    @InSequence(1)
    @RunAsClient
    public void testImageItRender() {
        String pageTitle = browser.getTitle();

        //assert page title
        assertEquals("Wrong page title found", "Image IT", pageTitle);


        assertTrue("image_id_1 rendered failed. src does not exist.", image_id_1.getAttribute("src").endsWith("resources/images/bsf_logo.png"));
        assertTrue("image_id_1 rendered failed. lang does not exist.", image_id_1.getAttribute("lang").equals("lang"));
        assertTrue("image_id_1 rendered failed. alt does not exist.", image_id_1.getAttribute("alt").equals("this is an alternative text"));
        assertTrue("image_id_1 rendered failed. width does not exist.", image_id_1.getAttribute("width").equals("100"));
        assertTrue("image_id_1 rendered failed. height does not exist.", image_id_1.getAttribute("height").equals("100"));
        assertTrue("image_id_1 rendered failed. style does not exist.", image_id_1.getAttribute("style").equals("style"));
        assertTrue("image_id_1 rendered failed. class does not exist.", image_id_1.getAttribute("class").equals("styleClass"));
        assertTrue("image_id_1 rendered failed. onclick does not exist.", image_id_1.getAttribute("onclick").equals("var dummy = 0;"));
        assertTrue("image_id_1 rendered failed. ondblclick does not exist.", image_id_1.getAttribute("ondblclick").equals("var dummy = 0;"));
        assertTrue("image_id_1 rendered failed. onmousedown does not exist.", image_id_1.getAttribute("onmousedown").equals("var dummy = 0;"));
        assertTrue("image_id_1 rendered failed. onmousemove does not exist.", image_id_1.getAttribute("onmousemove").equals("var dummy = 0;"));
        assertTrue("image_id_1 rendered failed. onmouseout does not exist.", image_id_1.getAttribute("onmouseout").equals("var dummy = 0;"));
        assertTrue("image_id_1 rendered failed. onmouseover does not exist.", image_id_1.getAttribute("onmouseover").equals("var dummy = 0;"));
        assertTrue("image_id_1 rendered failed. onmouseup does not exist.", image_id_1.getAttribute("onmouseup").equals("var dummy = 0;"));

    }

//    @Test
    @InSequence(10)
    @RunAsClient
    public void testBootsfacesAjaxClick() {

        //Navigate to page
        browser.navigate().to(context + "/faces/imageIT.xhtml");

        Warp.initiate(new Activity() {

            public void perform() {
                guardAjax(image_id_2).click();
            }

        })

        .inspect(new Inspection() {
            private static final long serialVersionUID = 1L;

            @Inject
            ImageRequestBean imageRequestBean;

            @BeforePhase(Phase.INVOKE_APPLICATION)
            public void testBeforeAjax() {
                assertEquals(Boolean.FALSE, imageRequestBean.getAjaxCalled());
            }

            @AfterPhase(Phase.INVOKE_APPLICATION)
            public void testAfterAjax() {
                assertEquals(Boolean.TRUE, imageRequestBean.getAjaxCalled());
            }
        });
    }

//    @Test
    @InSequence(10)
    @RunAsClient
    public void testJsfAjaxClick() {

        //Navigate to page
        browser.navigate().to(context + "/faces/imageIT.xhtml");

        Warp.initiate(new Activity() {

            public void perform() {
                guardAjax(image_id_3).click();
            }

        })

                .inspect(new Inspection() {
                    private static final long serialVersionUID = 1L;

                    @Inject
                    ImageRequestBean imageRequestBean;

                    @BeforePhase(Phase.INVOKE_APPLICATION)
                    public void testBeforeAjax() {
                        assertEquals(Boolean.FALSE, imageRequestBean.getAjaxCalled());
                    }

                    @AfterPhase(Phase.INVOKE_APPLICATION)
                    public void testAfterAjax() {
                        assertEquals(Boolean.TRUE, imageRequestBean.getAjaxCalled());
                    }
                });
    }
}
