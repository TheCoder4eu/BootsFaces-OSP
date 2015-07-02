/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.junit.icon;

import java.io.IOException;
import javax.faces.component.html.HtmlGraphicImage;
import net.bootsfaces.component.IconAwesome;
import net.bootsfaces.component.commandButton.CommandButton;
import net.bootsfaces.junit.common.JsfMock;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author yersan
 */
public class CommandButtonTest {
    
    @Rule
    public JsfMock jsfMock = new JsfMock();
    
    @Test
    public void testImageChildren() throws IOException{
        CommandButton commandButton = new CommandButton();
	HtmlGraphicImage img = new HtmlGraphicImage();
	img.setUrl("http://sample/url/image.jpg");
	
	commandButton.getChildren().add(img);
        
        String expected="<button class=\"btn btn-success\" name=\"form:cb2\" id=\"form:cb2\" type=\"submit\">"
		+ "This text"
		+ "<img width=\"14\" height=\"14\" src=\"http://dailydropcap.com/images/H-11.jpg\">"
		+ "surrounds an image."
		+ "</button>";
        
        jsfMock.generateAndTest(commandButton, expected);
    }
            
}
