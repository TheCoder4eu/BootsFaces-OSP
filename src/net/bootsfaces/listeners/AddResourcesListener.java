/**
 *  Copyright 2015 Stephan Rauh, http://www.beyondjava.net
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
package net.bootsfaces.listeners;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.ProjectStage;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlHead;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import net.bootsfaces.C;

public class AddResourcesListener implements SystemEventListener {
	
	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.listeners.AddResourcesListener");

	private static final String RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.ResourceFiles";

	static {
		LOGGER.info("net.bootsfaces.listeners.AddResourcesListener ready for use.");
	}

	public void processEvent(SystemEvent event) throws AbortProcessingException {
		Object source = event.getSource();
		if (source instanceof UIViewRoot) {
			final FacesContext context = FacesContext.getCurrentInstance();
			boolean isProduction = context.isProjectStage(ProjectStage.Production);

			addJavascript((UIViewRoot) source, context, isProduction);
		}
	}

	private void addJavascript(UIViewRoot root, FacesContext context, boolean isProduction) {
        Application app = context.getApplication();
        ResourceHandler rh = app.getResourceHandler();
        
        //If the BootsFaces_USETHEME parameter is true, render Theme CSS link
        String theme = null;
        theme = context.getExternalContext().getInitParameter(C.P_USETHEME);
        if (theme!=null && theme.equals(C.TRUE)) {
            Resource themeResource = rh.createResource(C.BSF_CSS_TBSTHEME, C.BSF_LIBRARY);
            
            if (themeResource == null) {
                throw new FacesException("Error loading theme, cannot find \"" + C.BSF_CSS_TBSTHEME + "\" resource of \"" + C.BSF_LIBRARY + "\" library");
            } else {
    			UIOutput output = new UIOutput();
    			output.setRendererType("javax.faces.resource.Stylesheet");
  				output.getAttributes().put("name", C.BSF_CSS_TBSTHEME);
    			output.getAttributes().put("library", C.BSF_LIBRARY);
    			root.addComponentResource(context, output, "head");
            }
        }

        
   
        // deactive FontAwesome support if the no-fa facet is found in the h:head tag
        UIComponent header=findHeader(root);
        boolean usefa = (null==header) || (null == header.getFacet("no-fa"));
        //Font Awesome
        if (usefa) { //!=null && usefa.equals(C.TRUE)) {
   			InternalFALink output = new InternalFALink();
			output.getAttributes().put("src", C.FONTAWESOME_CDN_URL);
			root.addComponentResource(context, output, "head");
        }

        

		boolean loadJQuery = true;
		boolean loadJQueryUI = true;
		List<UIComponent> availableResources = root.getComponentResources(context, "head");
		for (UIComponent ava : availableResources) {
			String name = (String) ava.getAttributes().get("name");
//			System.out.println(name);
			if (null != name)
				if (name.toLowerCase().contains("jquery-ui") && name.toLowerCase().endsWith(".js")) {
					loadJQueryUI = false;
				} else if (name.toLowerCase().contains("jquery") && name.toLowerCase().endsWith(".js")) {
					loadJQuery = false;
				}
		}

		Map<String, Object> viewMap=root.getViewMap();
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(RESOURCE_KEY);
		
		if (loadJQuery) {
			boolean needsJQuery=false;
			for (Entry<String, String> entry: resourceMap.entrySet()) {
				String file = entry.getValue();
				if ("jq/jquery.js".equals(file)) {
					needsJQuery=true;
				}
			}
			if (needsJQuery) {
    			UIOutput output = new UIOutput();
    			output.setRendererType("javax.faces.resource.Script");
    			output.getAttributes().put("name", "jq/jquery.js");
    			output.getAttributes().put("library", C.BSF_LIBRARY);
    			root.addComponentResource(context, output, "head");
			}
		}
		
		if (null != resourceMap) {
			for (Entry<String, String> entry: resourceMap.entrySet()) {
				String file = entry.getValue();
				String library = entry.getKey().substring(0, entry.getKey().length()-file.length()-1);
				if (!"jq/jquery.js".equals(file)) {
    				UIOutput output = new UIOutput();
    				output.setRendererType("javax.faces.resource.Script");
    				output.getAttributes().put("name", file);
    				output.getAttributes().put("library", library);
    				root.addComponentResource(context, output, "head");
				}
				
			}
		}


		{
//			System.out.println("ToDo: add the IE8 compatibility code");
			InternalIE8CompatiblityLinks output = new InternalIE8CompatiblityLinks();
			root.addComponentResource(context, output, "head");

            //        Resource h5s = rh.createResource("js/html5shiv.js", C.BSF_LIBRARY);
            //        Resource rjs = rh.createResource("js/respond.js", C.BSF_LIBRARY);
            //        
            //        rw.write("<!--[if lt IE 9]>");
            //        rw.startElement("script", null); rw.writeAttribute("src", h5s.getRequestPath(), null); rw.endElement("script");
            //        rw.startElement("script", null); rw.writeAttribute("src", rjs.getRequestPath(), null); rw.endElement("script");
            //        rw.write("<![endif]-->");
		}

	}

	private UIComponent findHeader(UIViewRoot root) {
		for (UIComponent c:root.getChildren()) {
			if (c instanceof HtmlHead) 
				return c;
		}
		return null;
	}

	@Override
	public boolean isListenerForSource(Object source) {
		if (source instanceof UIComponent) {
			return true;
		}
		return false;
	}

	/** 
	 * Registers a JS file that needs to be include in the header of the HTML file, but after jQuery and AngularJS.
	 * @param library The name of the sub-folder of the resources folder.
	 * @param resource The name of the resource file within the library folder.
	 */
	public static void addResourceToHeadButAfterJQuery(String library, String resource) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UIViewRoot v = ctx.getViewRoot();
		Map<String, Object> viewMap=v.getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(RESOURCE_KEY);
		if (null==resourceMap) {
			resourceMap = new HashMap<String, String>();
			viewMap.put(RESOURCE_KEY, resourceMap);
		}
		String key = library + "#" + resource;
		if (!resourceMap.containsKey(key)) {
			resourceMap.put(key, resource);
		}
	}


}
