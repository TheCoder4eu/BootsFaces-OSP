package net.bootsfaces.component;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * Backed bean for InputTextITCase.
 * 
 * @author yersan
 */
@Named
@SessionScoped
public class InputTextSessionBean implements Serializable {

    private String txtValue1 = "";
    private String txtValue2 = "DUMMY";
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
