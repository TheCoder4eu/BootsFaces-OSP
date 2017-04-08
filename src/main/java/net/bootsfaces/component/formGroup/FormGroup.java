/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bootsfaces.component.formGroup;

import javax.faces.component.FacesComponent;
import net.bootsfaces.C;
import net.bootsfaces.component.row.Row;

/**
 *
 * @author Guillermo González de Agüero
 */
@FacesComponent(FormGroup.COMPONENT_TYPE)
public class FormGroup extends Row {

    public static final String COMPONENT_TYPE = C.BSFCOMPONENT + ".formGroup.FormGroup";
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;
    public static final String DEFAULT_RENDERER = FormGroupRenderer.RENDERER_TYPE;

    public FormGroup() {
        setRendererType(DEFAULT_RENDERER);
    }


}
