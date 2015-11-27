package net.bootsfaces.component;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
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
