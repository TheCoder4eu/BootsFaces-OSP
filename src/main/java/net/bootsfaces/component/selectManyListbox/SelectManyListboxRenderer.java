/**
 *  Copyright 2014 - 17 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.selectManyListbox;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:selectManyListbox /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.selectManyListbox.SelectManyListbox")
public class SelectManyListboxRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:selectManyListbox.
	 * @param context the FacesContext.
	 * @param component the current b:selectManyListbox.
	 * @throws IOException thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	    if (!component.isRendered()) {
	        return;
	    }
		SelectManyListbox selectManyListbox = (SelectManyListbox) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = selectManyListbox.getClientId();
	
		// put custom code here
		// Simple demo widget that simply renders every attribute value
		rw.startElement("selectManyListbox", selectManyListbox);
		Tooltip.generateTooltip(context, selectManyListbox, rw);
	
	    rw.writeAttribute("accesskey", selectManyListbox.getAccesskey(), "accesskey");
	    rw.writeAttribute("ajax", String.valueOf(selectManyListbox.isAjax()), "ajax");
	    rw.writeAttribute("allSelectedText", selectManyListbox.getAllSelectedText(), "allSelectedText");
	    rw.writeAttribute("alt", selectManyListbox.getAlt(), "alt");
	    rw.writeAttribute("auto-update", String.valueOf(selectManyListbox.isAutoUpdate()), "auto-update");
	    rw.writeAttribute("binding", selectManyListbox.getBinding(), "binding");
	    rw.writeAttribute("colLg", selectManyListbox.getColLg(), "colLg");
	    rw.writeAttribute("colMd", selectManyListbox.getColMd(), "colMd");
	    rw.writeAttribute("colSm", selectManyListbox.getColSm(), "colSm");
	    rw.writeAttribute("col-xs", selectManyListbox.getColXs(), "col-xs");
	    rw.writeAttribute("delay", selectManyListbox.getDelay(), "delay");
	    rw.writeAttribute("dir", selectManyListbox.getDir(), "dir");
	    rw.writeAttribute("disabled", String.valueOf(selectManyListbox.isDisabled()), "disabled");
	    rw.writeAttribute("display", selectManyListbox.getDisplay(), "display");
	    rw.writeAttribute("hidden", selectManyListbox.getHidden(), "hidden");
	    rw.writeAttribute("id", selectManyListbox.getId(), "id");
	    rw.writeAttribute("immediate", String.valueOf(selectManyListbox.isImmediate()), "immediate");
	    rw.writeAttribute("inline", String.valueOf(selectManyListbox.isInline()), "inline");
	    rw.writeAttribute("label", selectManyListbox.getLabel(), "label");
	    rw.writeAttribute("labelColLg", selectManyListbox.getLabelColLg(), "labelColLg");
	    rw.writeAttribute("labelColMd", selectManyListbox.getLabelColMd(), "labelColMd");
	    rw.writeAttribute("labelColSm", selectManyListbox.getLabelColSm(), "labelColSm");
	    rw.writeAttribute("labelCol-xs", selectManyListbox.getLabelColXs(), "labelCol-xs");
	    rw.writeAttribute("labelLargeScreen", selectManyListbox.getLabelLargeScreen(), "labelLargeScreen");
	    rw.writeAttribute("labelMediumScreen", selectManyListbox.getLabelMediumScreen(), "labelMediumScreen");
	    rw.writeAttribute("labelSmallScreen", selectManyListbox.getLabelSmallScreen(), "labelSmallScreen");
	    rw.writeAttribute("labelStyle", selectManyListbox.getLabelStyle(), "labelStyle");
	    rw.writeAttribute("labelStyleClass", selectManyListbox.getLabelStyleClass(), "labelStyleClass");
	    rw.writeAttribute("labelTinyScreen", selectManyListbox.getLabelTinyScreen(), "labelTinyScreen");
	    rw.writeAttribute("lang", selectManyListbox.getLang(), "lang");
	    rw.writeAttribute("largeScreen", selectManyListbox.getLargeScreen(), "largeScreen");
	    rw.writeAttribute("mediumScreen", selectManyListbox.getMediumScreen(), "mediumScreen");
	    rw.writeAttribute("nSelectedText", selectManyListbox.getNSelectedText(), "nSelectedText");
	    rw.writeAttribute("nonSelectedText", selectManyListbox.getNonSelectedText(), "nonSelectedText");
	    rw.writeAttribute("numberDisplayed", selectManyListbox.getNumberDisplayed(), "numberDisplayed");
	    rw.writeAttribute("offset", selectManyListbox.getOffset(), "offset");
	    rw.writeAttribute("offsetLg", selectManyListbox.getOffsetLg(), "offsetLg");
	    rw.writeAttribute("offsetMd", selectManyListbox.getOffsetMd(), "offsetMd");
	    rw.writeAttribute("offsetSm", selectManyListbox.getOffsetSm(), "offsetSm");
	    rw.writeAttribute("offset-xs", selectManyListbox.getOffsetXs(), "offset-xs");
	    rw.writeAttribute("onchange", selectManyListbox.getOnchange(), "onchange");
	    rw.writeAttribute("onclick", selectManyListbox.getOnclick(), "onclick");
	    rw.writeAttribute("oncomplete", selectManyListbox.getOncomplete(), "oncomplete");
	    rw.writeAttribute("ondeselectall", selectManyListbox.getOndeselectall(), "ondeselectall");
	    rw.writeAttribute("ondropdownhidden", selectManyListbox.getOndropdownhidden(), "ondropdownhidden");
	    rw.writeAttribute("ondropdownhide", selectManyListbox.getOndropdownhide(), "ondropdownhide");
	    rw.writeAttribute("ondropdownshow", selectManyListbox.getOndropdownshow(), "ondropdownshow");
	    rw.writeAttribute("ondropdownshown", selectManyListbox.getOndropdownshown(), "ondropdownshown");
	    rw.writeAttribute("onerror", selectManyListbox.getOnerror(), "onerror");
	    rw.writeAttribute("oninitialized", selectManyListbox.getOninitialized(), "oninitialized");
	    rw.writeAttribute("onselectall", selectManyListbox.getOnselectall(), "onselectall");
	    rw.writeAttribute("onsuccess", selectManyListbox.getOnsuccess(), "onsuccess");
	    rw.writeAttribute("process", selectManyListbox.getProcess(), "process");
	    rw.writeAttribute("readonly", String.valueOf(selectManyListbox.isReadonly()), "readonly");
	    rw.writeAttribute("renderLabel", String.valueOf(selectManyListbox.isRenderLabel()), "renderLabel");
	    rw.writeAttribute("rendered", String.valueOf(selectManyListbox.isRendered()), "rendered");
	    rw.writeAttribute("required", String.valueOf(selectManyListbox.isRequired()), "required");
	    rw.writeAttribute("requiredMessage", selectManyListbox.getRequiredMessage(), "requiredMessage");
	    rw.writeAttribute("size", selectManyListbox.getSize(), "size");
	    rw.writeAttribute("smallScreen", selectManyListbox.getSmallScreen(), "smallScreen");
	    rw.writeAttribute("span", selectManyListbox.getSpan(), "span");
	    rw.writeAttribute("style", selectManyListbox.getStyle(), "style");
	    rw.writeAttribute("styleClass", selectManyListbox.getStyleClass(), "styleClass");
	    rw.writeAttribute("tabindex", selectManyListbox.getTabindex(), "tabindex");
	    rw.writeAttribute("tinyScreen", selectManyListbox.getTinyScreen(), "tinyScreen");
	    rw.writeAttribute("title", selectManyListbox.getTitle(), "title");
	    rw.writeAttribute("tooltip", selectManyListbox.getTooltip(), "tooltip");
	    rw.writeAttribute("tooltipContainer", selectManyListbox.getTooltipContainer(), "tooltipContainer");
	    rw.writeAttribute("tooltipDelay", selectManyListbox.getTooltipDelay(), "tooltipDelay");
	    rw.writeAttribute("tooltipDelay-hide", selectManyListbox.getTooltipDelayHide(), "tooltipDelay-hide");
	    rw.writeAttribute("tooltipDelayShow", selectManyListbox.getTooltipDelayShow(), "tooltipDelayShow");
	    rw.writeAttribute("tooltip-position", selectManyListbox.getTooltipPosition(), "tooltip-position");
	    rw.writeAttribute("update", selectManyListbox.getUpdate(), "update");
	    rw.writeAttribute("value", selectManyListbox.getValue(), "value");
	    rw.writeAttribute("visible", selectManyListbox.getVisible(), "visible");
		rw.writeText("Dummy content of b:selectManyListbox", null);
		rw.endElement("selectManyListbox");
		Tooltip.activateTooltips(context, selectManyListbox);
	
	}

}
