package net.bootsfaces.component;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.bootsfaces.component.inputText.InputText;

/**
 * Backed bean for InputTextITCase.
 * 
 * @author yersan
 */
@ManagedBean
@SessionScoped
public class InputTextSessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

    private String txtValue1 = "";
    private String txtValue2 = "DUMMY";
    
    private String txtValue4;
    private String txtValue5;
      
    private int valueChangeCount = 0;
    private InputText inputText;

    public String getTxtValue1() {
        return txtValue1;
    }

    public void setTxtValue1(String txtValue1) {
        this.txtValue1 = txtValue1;
    }

    public String getTxtValue2() {
        return txtValue2;
    }

    public void setTxtValue2(String txtValue2) {
        this.txtValue2 = txtValue2;
    }

  
    public void ajaxAction(){
    }

    public void valueChangeMethod(ValueChangeEvent e){
       valueChangeCount++;
    }

    public int getValueChangeCount() {
        return valueChangeCount;
    }

    public void setValueChangeCount(int valueChangeCount) {
        this.valueChangeCount = valueChangeCount;
    }

    public String getTxtValue4() {
        return txtValue4;
    }

    public void setTxtValue4(String txtValue4) {
        this.txtValue4 = txtValue4;
    }

    public String getTxtValue5() {
        return txtValue5;
    }

    public void setTxtValue5(String txtValue5) {
        this.txtValue5 = txtValue5;
    }


    public InputText getInputText() {
         if ( inputText == null ) {
             inputText = new InputText();
             inputText.setValue("VALUE BINDING");
         }
        return inputText;
    }

    public void setInputText(InputText inputText) {
        this.inputText = inputText;
    }
}
