/**
 * Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 *
 * This file is part of BootsFaces.
 *
 * BootsFaces is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * BootsFaces is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */
package net.bootsfaces.component.inputText;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

import net.bootsfaces.C;
import net.bootsfaces.render.Tooltip;

/**
 *
 * @author thecoder4.eu
 */
@ResourceDependencies({
    @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
    @ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head")
})
@FacesComponent(C.INPUTTEXT_COMPONENT_TYPE)
public class InputText extends HtmlInputText {

    /**
     * <p>
     * The standard component type for this component.</p>
     */
    public static final String COMPONENT_TYPE = C.INPUTTEXT_COMPONENT_TYPE;
    /**
     * <p>
     * The component family for this component.</p>
     */
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public static final String ADDON = "input-group-addon";

    protected enum PropertyKeys {

        placeholder, 
        fieldSize, 
        type;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
        }

        @Override
        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }

    public InputText() {
        setRendererType("net.bootsfaces.component.InputTextRenderer");
        Tooltip.addResourceFile();
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public java.lang.String getPlaceholder() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.placeholder, null);
    }

    public void setPlaceholder(java.lang.String _placeholder) {
        getStateHelper().put(PropertyKeys.placeholder, _placeholder);
    }

    public java.lang.String getFieldSize() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.fieldSize, null);
    }

    public void setFieldSize(java.lang.String _fieldSize) {
        getStateHelper().put(PropertyKeys.fieldSize, _fieldSize);
    }

    public java.lang.String getType() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.type, "text");
    }

    public void setType(java.lang.String _type) {
        getStateHelper().put(PropertyKeys.type, _type);
    }

}
