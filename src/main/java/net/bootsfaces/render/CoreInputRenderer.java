/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu).
 *
 *  This file is part of BootsFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bootsfaces.render;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;

import net.bootsfaces.component.formGroup.FormGroup;
import net.bootsfaces.utils.BsfUtils;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Guillermo González de Agüero
 */
public class CoreInputRenderer extends CoreRenderer {

    private static final Logger LOGGER = Logger.getLogger(CoreInputRenderer.class.getName());

    public enum InputMode {
        @Deprecated
        INLINE,
        DEFAULT
    }

    /**
     * <p>
     * Check whether the input needs to be wrapped on a div containing the
     * "form-group" class.</p>
     *
     * This method return true in the following cases:
     * <ul>
     * <li>The direct parent of the component passed as argument is an instance
     * of {@link FormGroup}</li>
     * </ul>
     *
     * In any other case, this method will return false.
     *
     * @param component
     * @return
     */
    protected boolean hasToRenderFormGroup(UIComponent component) {
        return !(component.getParent() instanceof FormGroup);
    }

    protected String getWithFeedback(InputMode inputMode, UIComponent component) {
        String additionalClass;
        switch (inputMode) {
            case INLINE:
                additionalClass = "form-inline";
                LOGGER.log(Level.WARNING, "The inline attribute of {0} class (clienId: {1}) is deprecated and generates faulty HTML code. Please use <b:form inline=\"true\"> instead.", new Object[]{component.getClass().getName(), component.getClientId()});
                break;
            case DEFAULT:
            default:
                additionalClass = hasToRenderFormGroup(component) ? "form-group" : "";
                break;
        }

        if (BsfUtils.isLegacyFeedbackClassesEnabled()) {
            return additionalClass;
        }

        return additionalClass + " " + FacesMessages.getErrorSeverityClass(component.getClientId());
    }

    protected InputMode getInputMode(boolean inline) {
        return inline ? InputMode.INLINE : InputMode.DEFAULT;
    }
}
