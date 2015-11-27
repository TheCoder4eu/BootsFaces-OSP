/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.component;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.bootsfaces.component.selectBooleanCheckbox.SelectBooleanCheckbox;

/**
 * Backed bean for SelectBooleanCheckBoxITCase.
 * 
 * @author yersan
 */
@ManagedBean
@SessionScoped
public class SelectBooleanCheckBoxSessionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean boolValue1;
    private boolean boolValue2 = true;
    private int valueChangeCount = 0;
    private SelectBooleanCheckbox booleanCheckbox;

  
    public void ajaxAction(){
    }

    public void valueChangeMethod(ValueChangeEvent e){
       valueChangeCount++;
    }

    public boolean isBoolValue1() {
        return boolValue1;
    }

    public void setBoolValue1(boolean boolValue1) {
        this.boolValue1 = boolValue1;
    }

    public boolean isBoolValue2() {
        return boolValue2;
    }

    public void setBoolValue2(boolean boolValue2) {
        this.boolValue2 = boolValue2;
    }

    public int getValueChangeCount() {
        return valueChangeCount;
    }

    public void setValueChangeCount(int valueChangeCount) {
        this.valueChangeCount = valueChangeCount;
    }
    

    public SelectBooleanCheckbox getBooleanCheckbox() {
        if ( booleanCheckbox == null ) {
            booleanCheckbox = new SelectBooleanCheckbox();
            booleanCheckbox.setValue(Boolean.TRUE);
        }
        return booleanCheckbox;
    }

    public void setBooleanCheckbox(SelectBooleanCheckbox booleanCheckbox) {
        this.booleanCheckbox = booleanCheckbox;
    }
}
