/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.ut.icon;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import net.bootsfaces.component.iconAwesome.IconAwesome;
import net.bootsfaces.component.iconAwesome.IconAwesomeRenderer;
import net.bootsfaces.ut.common.JsfMock;

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
        iconAwesome.setName("prueba");
       
        String expected="<span><i> id=\"clientId1\" class=\"fa fa-prueba\"</i></span>";
        
        jsfMock.generateAndTest(iconAwesome, expected, new IconAwesomeRenderer());
        
        
        this.jsfMock.resetResponseWriter();
                
        iconAwesome = new IconAwesome();
        
        iconAwesome.getAttributes().put("styleClass", "styleClass1");
        iconAwesome.setName("prueba");
        
        expected="<span><i> id=\"clientId1\" class=\"styleClass1 fa fa-prueba\"</i></span>";
        
        jsfMock.generateAndTest(iconAwesome, expected, new IconAwesomeRenderer());
        
    }
            
}
