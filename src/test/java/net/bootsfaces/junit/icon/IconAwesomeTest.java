/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.junit.icon;

import java.io.IOException;
import net.bootsfaces.component.IconAwesome;
import net.bootsfaces.junit.common.JsfMock;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author yersan
 */
public class IconAwesomeTest {
    
    @Rule
    public JsfMock jsfMock = new JsfMock();
    
    @Test
    public void testStyleClass() throws IOException{
        IconAwesome iconAwesome = new IconAwesome();
        
        String expected="<span><i> id=\"clientId1_icon\" class=\"fa fa-null\"</i></span>";
        
        jsfMock.generateAndTest(iconAwesome, expected);
        
        
        this.jsfMock.resetResponseWriter();
                
        iconAwesome = new IconAwesome();
        
        iconAwesome.getAttributes().put("styleClass", "styleClass1");
        
        expected="<span><i> id=\"clientId1_icon\" class=\"styleClass1 fa fa-null\"</i></span>";
        
        jsfMock.generateAndTest(iconAwesome, expected);
        
    }
            
}
