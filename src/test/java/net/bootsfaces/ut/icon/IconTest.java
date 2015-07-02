/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.ut.icon;

import java.io.IOException;
import net.bootsfaces.component.Icon;
import net.bootsfaces.ut.common.JsfMock;
import org.junit.Rule;
import org.junit.Test;

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
        
        String expected="<span><i> id=\"clientId1_icon\" class=\"glyphicon glyphicon-null\"</i></span>";
        
        jsfMock.generateAndTest(icon, expected);
        
        
        this.jsfMock.resetResponseWriter();
                
        icon = new Icon();
        
        icon.getAttributes().put("styleClass", "styleClass1");
        
        expected="<span><i> id=\"clientId1_icon\" class=\"styleClass1 glyphicon glyphicon-null\"</i></span>";
        
        jsfMock.generateAndTest(icon, expected);
        
    }
            
}
