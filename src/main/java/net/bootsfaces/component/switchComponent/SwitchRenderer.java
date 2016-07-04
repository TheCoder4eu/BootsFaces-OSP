/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.switchComponent;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.selectBooleanCheckbox.SelectBooleanCheckbox;
import net.bootsfaces.component.selectBooleanCheckbox.SelectBooleanCheckboxRenderer;

/** This class generates the HTML code of &lt;b:switchWidget /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.switch.Switch")
public class SwitchRenderer extends SelectBooleanCheckboxRenderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter rw = context.getResponseWriter();
		String clientId = component.getClientId();
		super.encodeBegin(context, component);
		clientId = clientId.replace(":", "\\\\:");
		rw.append("<script>");
		rw.append("$('#input_" + clientId + "').bootstrapSwitch();");
		rw.append("</script>");
	}

	/**
	 * The b:switch and the b:selectBooleanCheckbox share most of their code.
	 * This method allows to add extra attributes for the switch.
	 * 
	 * @param rw
	 * @param selectBooleanCheckbox
	 * @throws IOException
	 */
	protected void addAttributesForSwitch(ResponseWriter rw, SelectBooleanCheckbox selectBooleanCheckbox)
			throws IOException {
		Switch switchComponent = (Switch) selectBooleanCheckbox;
		writeAttribute(rw, "data-off-text", switchComponent.getOffText());
		writeAttribute(rw, "data-on-text", switchComponent.getOnText());
		writeAttribute(rw, "data-on-color", switchComponent.getOnColor());
		writeAttribute(rw, "data-off-color", switchComponent.getOffColor());
		if (switchComponent.isIndeterminate())
			writeAttribute(rw, "data-indeterminate", switchComponent.isIndeterminate());
		if (switchComponent.isInverse())
			writeAttribute(rw, "data-inverse", switchComponent.isInverse());
		writeAttribute(rw, "data-size", switchComponent.getSwitchsize());
		if (!switchComponent.isAnimate())
			writeAttribute(rw, "data-animate", switchComponent.isAnimate());
		if (switchComponent.isDisabled())
			writeAttribute(rw, "data-disabled", switchComponent.isDisabled());
		if (switchComponent.isReadonly())
			writeAttribute(rw, "data-readonly", switchComponent.isReadonly());
		writeAttribute(rw, "data-label-text", switchComponent.getLabelText());
		if (switchComponent.getHandleWidth() > 0)
			writeAttribute(rw, "data-handle-width", switchComponent.getHandleWidth());
		if (switchComponent.getLabelWidth() > 0)
			writeAttribute(rw, "data-label-width", switchComponent.getLabelWidth());
	}

}
