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

package net.bootsfaces.render;

import net.bootsfaces.C;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

/**
 * Renders head content based on the following order
 * A - first facet if defined
 * B - Registered Resources in HEAD
 * C - Theme CSS
 * D - CSS resources
 * E - JS resources
 * F - HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries
 * G - h:head content (encoded by super class at encodeChildren)
 * H - last facet if defined
 * 
 * @author thecoder4.eu
 */
public class HeadRenderer extends Renderer {

    @Override
    public void encodeBegin(FacesContext fc, UIComponent component) throws IOException {
        ResponseWriter rw = fc.getResponseWriter();
        Application app = fc.getApplication();
        ResourceHandler rh = app.getResourceHandler();
        
        rw.startElement("head", component);

        //First facet
        UIComponent first = component.getFacet("first");
        if(first != null) {
            first.encodeAll(fc);
        }

        //Registered Resources
        UIViewRoot viewRoot = fc.getViewRoot();
        ListIterator<UIComponent> iter = (viewRoot.getComponentResources(fc, "head")).listIterator();
        List<UIComponent> styles = new ArrayList<UIComponent>();
        List<UIComponent> scripts = new ArrayList<UIComponent>();
        boolean usefa = false;
        
        while(iter.hasNext()) {
            UIComponent resource = (UIComponent) iter.next();
            String name = (String) resource.getAttributes().get("name");
            //rw.write("\n<!-- res: '"+name+"' -->" );
            if(name != null) {
                if(name.endsWith("font-awesome.css")) { usefa = true; }
                else if(name.endsWith(".css")) { styles.add(resource); }
                else if(name.endsWith(".js"));
                    scripts.add(resource);
            }
        }
        //If the BootsFaces_USETHEME parameter is true, render Theme CSS link
        String theme = null;
        theme = fc.getExternalContext().getInitParameter(C.P_USETHEME);
        if (theme!=null && theme.equals(C.TRUE)) {
            Resource themeResource = rh.createResource(C.BSF_CSS_TBSTHEME, C.BSF_LIBRARY);
            
            if (themeResource == null) {
                throw new FacesException("Error loading theme, cannot find \"" + C.BSF_CSS_TBSTHEME + "\" resource of \"" + C.BSF_LIBRARY + "\" library");
            } else {
                rw.startElement("link", null);
                rw.writeAttribute("type", "text/css", null);
                rw.writeAttribute("rel", "stylesheet", null);
                rw.writeAttribute("href", themeResource.getRequestPath(), null);
                rw.endElement("link"); 
            }
        }
        
        //Styles
        for(UIComponent style : styles) {
            style.encodeAll(fc);
        }
        //Font Awesome
        if (usefa) { //!=null && usefa.equals(C.TRUE)) {
            rw.startElement("link", null);
            rw.writeAttribute("type", "text/css", null);
            rw.writeAttribute("rel", "stylesheet", null);
            rw.writeAttribute("href", C.FONTAWESOME_CDN_URL, null);
            rw.endElement("link"); 
        }
        
        //Scripts
        for (UIComponent script : scripts) {
            script.encodeAll(fc);
            String n = (String) script.getAttributes().get("name");
            //rw.write("\n<!-- JS: '" + n + "' -->");
            
            //If it's a DatePicker, we add the scripts for supported locales
            if (n.endsWith("datepicker.js")) {
                Resource rdp;
                Iterator<Locale> i = app.getSupportedLocales();
                while (i.hasNext()) {
                    final String jsl = "jq/ui/i18n/datepicker-" + i.next().getLanguage() + ".js";
                    
                    rdp = rh.createResource(jsl, C.BSF_LIBRARY);
                    //rw.write("\n<!-- lang: '" + jsl + "' -->");
                    if (rdp != null) { //rdp is null if the language .js is not present in jar
                        rw.startElement("script", null);
                        rw.writeAttribute("src", rdp.getRequestPath(), null);
                        rw.endElement("script");
                    }
                }
                rw.startElement("script", null);
                rw.write("$.datepicker.setDefaults($.datepicker.regional['"+fc.getViewRoot().getLocale().getLanguage()+"']);");
                rw.endElement("script");
            }
        }
        /*<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
          <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
          <![endif]-->*/
        Resource h5s = rh.createResource("js/html5shiv.js", C.BSF_LIBRARY);
        Resource rjs = rh.createResource("js/respond.js", C.BSF_LIBRARY);
        
        rw.write("<!--[if lt IE 9]>");
        rw.startElement("script", null); rw.writeAttribute("src", h5s.getRequestPath(), null); rw.endElement("script");
        rw.startElement("script", null); rw.writeAttribute("src", rjs.getRequestPath(), null); rw.endElement("script");
        rw.write("<![endif]-->");
    }

    @Override
    public void encodeEnd(FacesContext fc, UIComponent c) throws IOException {
        ResponseWriter rw = fc.getResponseWriter();
        
        //Last facet
        UIComponent last = c.getFacet("last");
        if(last != null) {
            last.encodeAll(fc);
        }
        
        rw.endElement("head");
    }

}
