/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.ut.icon;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import net.bootsfaces.component.icon.Icon;
import net.bootsfaces.component.icon.IconRenderer;
import net.bootsfaces.component.iconAwesome.IconAwesomeRenderer;
import net.bootsfaces.ut.common.JsfMock;

/**
 *
 * @author yersan
 */
public class IconTest {
    
    @Rule
    public JsfMock jsfMock = new JsfMock();
    
    @Test
    public void testStyleClass() throws IOException{
        Icon icon = new Icon();
        icon.setName("prueba");
        String expected="<span><i> id=\"clientId1\" class=\"glyphicon glyphicon-prueba\"</i></span>";
        
        jsfMock.generateAndTest(icon, expected, new IconAwesomeRenderer());
        
        
        this.jsfMock.resetResponseWriter();
                
        icon = new Icon();
        
        icon.getAttributes().put("styleClass", "styleClass1");
        icon.setName("prueba");

        expected="<span><i> id=\"clientId1\" class=\"styleClass1 glyphicon glyphicon-prueba\"</i></span>";
        
        jsfMock.generateAndTest(icon, expected, new IconRenderer());
        
    }
            
}
