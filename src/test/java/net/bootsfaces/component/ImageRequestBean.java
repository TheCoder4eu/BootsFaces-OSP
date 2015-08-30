package net.bootsfaces.component;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class ImageRequestBean implements Serializable {
    private Boolean ajaxCalled = Boolean.FALSE;

    public void ajaxAction(){
        ajaxCalled = Boolean.TRUE;
    }

    public Boolean getAjaxCalled() {
        return ajaxCalled;
    }

    public void setAjaxCalled(Boolean ajaxCalled) {
        this.ajaxCalled = ajaxCalled;
    }
}
