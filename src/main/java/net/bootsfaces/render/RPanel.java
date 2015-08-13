/**
 *  Copyright 2014 Riccardo Massera (TheCoder4.Eu)
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.bootsfaces.render;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.layout.Panel;

/**
 *
 * @author droidcoder
 */
public enum RPanel {
    panel,
    look,
    title,
    heading,
    body,
    footer,
    titleClass,
    styleClass,
    contentClass,
    style,
    titleStyle,
    contentStyle;
    
    private static final String PP=panel+" "+panel+"-"; //"panel panel-"
    private static final String PPD=PP+"default"; //"panel panel-default"
    private static final String PH=panel+"-"+heading; //"panel-heading"
    private static final String PB=panel+"-"+body; //"panel-body"
    private static final String PF=panel+"-"+footer; //"panel-footer"

    /**
     * Begin Encoding
     * @param c
     * @param fc
     * @throws IOException
     */
    public static final void encBegin(UIComponent c, FacesContext fc) throws IOException {
        if (!c.isRendered()) {
            return;
        }
        
        /*
         * <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Panel title</h3>
            </div>
            <div class="panel-body">
              Panel content
            </div>
            <div class="panel-footer">Panel footer</div>
           </div>
         */
        ResponseWriter rw = fc.getResponseWriter();
        Map<String, Object> attrs = c.getAttributes();
        
        String clientId = c.getClientId(fc);
        String jQueryClientID = c.getClientId().replace(":", "_");

        boolean isCollapsible = attrs.get("collapsible") == null || attrs.get("collapsible").equals("true");
        
        if (isCollapsible) {
	        rw.startElement("div", c);
	        rw.writeAttribute("class", "panel-group", null);
        }

        String _look = A.asString(attrs,look);
        String _title = A.asString(attrs,title);
        String _titleClass = A.asString(attrs,titleClass);
        String _styleClass = A.asString(attrs, styleClass);
        if (null == _styleClass) {
        	_styleClass="";
        } else {
        	_styleClass+=" ";
        }

        
        rw.startElement("div", c);
		rw.writeAttribute("id", clientId, "id");
        Tooltip.generateTooltip(fc, attrs, rw);
        String _style = A.asString(attrs,style);
        if (null != _style && _style.length()>0) {
          rw.writeAttribute("style", _style, "style");
        }

        if (_look != null) {
            rw.writeAttribute("class", _styleClass + PP + _look, "class"); //"panel panel-" + Look
        } else {
            rw.writeAttribute("class", _styleClass + PPD, "class"); //"panel panel-default"
        }
        
        UIComponent head = c.getFacet(heading.name());
        if (head != null || _title != null) {
            rw.startElement("div", c);
            rw.writeAttribute("class", PH, "class"); //"panel-heading"
            String _titleStyle = A.asString(attrs,titleStyle);
            if (null != _titleStyle) {
            	rw.writeAttribute("style", _titleStyle, "style");
            }
            if (_title != null) {
                rw.startElement(H.H4, c);
                if (_titleClass != null){
                	rw.writeAttribute("class", _titleClass, "class");
                } else {
                	rw.writeAttribute("class", "panel-title", "class");
                }
                if (isCollapsible) {
	                rw.startElement("a", c);
	                rw.writeAttribute("data-toggle", "collapse", "null");
	                rw.writeAttribute("data-target", "#"+ jQueryClientID+"content", "null");
                }

                rw.writeText(_title, null);
                rw.endElement("a");
                rw.endElement(H.H4);
            } else {
                if (isCollapsible) {
	                rw.startElement("a", c);
	                rw.writeAttribute("data-toggle", "collapse", "null");
	                rw.writeAttribute("data-target", "#"+ jQueryClientID+"content", "null");
                }
                head.encodeAll(fc);
                rw.endElement("a");
            }
            rw.endElement("div");
        }

        rw.startElement("div", c);
        rw.writeAttribute("id", jQueryClientID+"content", null);
        String _contentClass = A.asString(attrs,contentClass);
        if (null == _contentClass) _contentClass="";
        if (isCollapsible) {
        	_contentClass += " panel-collapse collapse"; // in
        	if (!((Panel)c).isCollapsed()) _contentClass += " in";
        }
        _contentClass = PB + " " + _contentClass;
        _contentClass=_contentClass.trim();
        if (_contentClass.length()>0)
            rw.writeAttribute("class", _contentClass, "class"); //"panel-body"
        String _contentStyle = A.asString(attrs,contentStyle);
        if (null != _contentStyle && _contentStyle.length()>0) {
          rw.writeAttribute("style", _contentStyle, "style");
        }

    }
    
    /**
     * End encoding
     * @param c
     * @param fc
     * @throws IOException
     */
    public static final void encEnd(UIComponent c, FacesContext fc) throws IOException {
        if (!c.isRendered()) {
            return;
        }
        ResponseWriter rw = fc.getResponseWriter();
        rw.endElement("div"); //panel-body
        UIComponent foot = c.getFacet(footer.name());
        if (foot != null) {
            rw.startElement("div", c); //modal-footer
            rw.writeAttribute("class", PF, "class"); //"panel-footer"
            foot.encodeAll(fc);
            
            rw.endElement("div"); //panel-footer
        }
        
        rw.endElement("div");
        Map<String, Object> attrs = c.getAttributes();
        boolean isCollapsible = attrs.get("collapsible") == null || attrs.get("collapsible").equals("true");
        if (isCollapsible) {
        	String jQueryClientID = c.getClientId().replace(":", "_");
        	rw.endElement("div");
            rw.startElement("input", c);
            rw.writeAttribute("type", "hidden", null);
			String hiddenInputFieldID = jQueryClientID + "_collapsed";
            rw.writeAttribute("name", hiddenInputFieldID, "name");
            rw.writeAttribute("id", hiddenInputFieldID, "id");
            rw.writeAttribute("value", String.valueOf(((Panel)c).isCollapsed()), "value");
            rw.endElement("input");
        	rw.startElement("script", c);
        	rw.writeText("\r\n", null);
        	rw.writeText(" $('"+ "#"+ jQueryClientID+"content" + "').on('show.bs.collapse', function(){ document.getElementById('" + hiddenInputFieldID + "').value='false'; });", null);
        	rw.writeText("\r\n", null);
        	rw.writeText(" $('"+ "#"+ jQueryClientID+"content" + "').on('hide.bs.collapse', function(){ document.getElementById('" + hiddenInputFieldID + "').value='true'; });", null);
        	rw.writeText("\r\n", null);
        	rw.endElement("script");
        }
        
        Tooltip.activateTooltips(fc, c.getAttributes(), c);
        

    }
}
