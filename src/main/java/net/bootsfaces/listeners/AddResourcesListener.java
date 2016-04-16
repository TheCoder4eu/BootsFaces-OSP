/**
 *  Copyright 2015-2016 Stephan Rauh, http://www.beyondjava.net
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
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
import javax.faces.component.html.HtmlBody;
import javax.faces.component.html.HtmlHead;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import net.bootsfaces.C;
import net.bootsfaces.beans.ELTools;

/**
 * This class adds the resource needed by BootsFaces and ensures that they are
 * loaded in the correct order. It replaces the former HeadListener.
 * 
 * @author Stephan Rauh
 */
public class AddResourcesListener implements SystemEventListener {

	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.listeners.AddResourcesListener");

	/**
	 * Are the jsf.js and the bsf.js file required?
	 */
	private static final String BASIC_JS_RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.BasicJSResourceFiles";

	/**
	 * Components can request JS resources by registering them in the ViewMap,
	 * using the RESOURCE_KEY.
	 */
	private static final String RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.ResourceFiles";

	/**
	 * Components can request themed CSS resources by registering them in the ViewMap,
	 * using the THEME_RESOURCE_KEY.
	 */
	private static final String THEME_RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.ThemedResourceFiles";

	static {
		LOGGER.info("This application is running on BootsFaces 0.8.2 (developer preview).");
		LOGGER.info("net.bootsfaces.listeners.AddResourcesListener ready for use.");
	}

	/**
	 * Trigger adding the resources if and only if the event has been fired by
	 * UIViewRoot.
	 */
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		Object source = event.getSource();
		if (source instanceof UIViewRoot) {
			final FacesContext context = FacesContext.getCurrentInstance();
			boolean isProduction = context.isProjectStage(ProjectStage.Production);
			
			UIViewRoot root = (UIViewRoot) source;
			addJavascript(root, context, isProduction);
			addMetaTags(root, context);
		}
	}

	/**
	 * Add the viewport meta tag if not disabled from context-param
	 * 
	 * @param root
	 * @param context
	 * @param isProduction
	 */
	private void addMetaTags(UIViewRoot root, FacesContext context) {
		// Check context-param
		String viewportParam = null;
		viewportParam = context.getExternalContext().getInitParameter(C.P_VIEWPORT);
		if (viewportParam != null) {
			viewportParam = ELTools.evalAsString(viewportParam);
		} else {
			viewportParam = "";
		}
		viewportParam = viewportParam.trim();
		String content = "width=device-width, initial-scale=1";
		if (viewportParam.length() > 0) {
			if (viewportParam.equalsIgnoreCase("no") || viewportParam.equalsIgnoreCase("false")) {
				return;
			}
			if (!viewportParam.equalsIgnoreCase("yes") && !viewportParam.equalsIgnoreCase("true")) {
				content = viewportParam;
			}
		}

		// Otherwise
		String viewportMeta = "<meta name=\"viewport\" content=\"" + content + "\"/>";
		UIOutput viewport = new UIOutput();
		viewport.setRendererType("javax.faces.Text");
		viewport.getAttributes().put("escape", false);
		viewport.setValue(viewportMeta);

		UIComponent header = findHeader(root);
		if(header != null) {
			header.getChildren().add(0, viewport);
		}
	}

	/**
	 * Add the required Javascript files and the FontAwesome CDN link.
	 * 
	 * @param root
	 *            The UIViewRoot of the JSF tree.
	 * @param context
	 *            The current FacesContext
	 * @param isProduction
	 *            This flag can be used to deliver different version of the JS
	 *            library, optimized for debugging or production.
	 */
	private void addJavascript(UIViewRoot root, FacesContext context, boolean isProduction) {
		Application app = context.getApplication();
		ResourceHandler rh = app.getResourceHandler();

// The following code is needed to diagnose the warning "Unable to save dynamic action with clientId 'j_id...'"
//				List<UIComponent> r = root.getComponentResources(context, "head");
//				System.out.println("**************");
//				for (UIComponent ava : r) {
//					String name = (String) ava.getAttributes().get("name");
//					System.out.println(ava.getClientId(context) +":" + name + " " + ava.getClass().getSimpleName());
//				}
//				System.out.println("**************");
// end of the diagnostic code

		// If the BootsFaces_USETHEME parameter is true, render Theme CSS link

		/*
		 * As of v0.8.0 we have two Context Parameters: BootsFaces_USETHEME - as
		 * in previous versions controls if the current theme is to be rendered
		 * in the Flat variant (default) or in its Enhanced variant, with
		 * shadows and decorations turned on. BootsFaces_THEME - controls the
		 * Theme to use: the value "default" is plain Bootstrap, the other
		 * options are a Bootswach Theme name (lowercase) or "custom". If custom
		 * is chosen, you will have to provide your custom CSS in the "other"
		 * folder.
		 */
		String theme = null;
		String usetheme = null;
		theme = context.getExternalContext().getInitParameter(C.P_THEME);
		usetheme = context.getExternalContext().getInitParameter(C.P_USETHEME);
		if (theme != null) {
			theme = ELTools.evalAsString(theme);
		} else {
			theme = "";
		}
		theme = theme.trim();
		if (theme.length() > 0) {
			if (theme.equalsIgnoreCase("custom")) {
				theme = "other";
			}
		} else
			theme = "default";

		// Theme loading
		if (isFontAwesomeComponentUsedAndRemoveIt() || (!theme.equalsIgnoreCase("other"))) {
			String filename = "bsf.css";
			Resource themeResource = rh.createResource("css/" + theme + "/" + filename, C.BSF_LIBRARY);

			if (themeResource == null) {
				throw new FacesException("Error loading theme, cannot find \"" + "css/" + theme + "/" + filename + "\" resource of \""
						+ C.BSF_LIBRARY + "\" library");
			} else {
				UIOutput output = new UIOutput();
				output.setRendererType("javax.faces.resource.Stylesheet");
				output.getAttributes().put("name", "css/" + theme + "/" + filename);
				output.getAttributes().put("library", C.BSF_LIBRARY);
				output.getAttributes().put("target", "head");
				addResourceIfNecessary(root, context, output);
			}
		}

		if (usetheme != null) {
			usetheme = ELTools.evalAsString(usetheme);
		} else {
			usetheme = "";
		}
		usetheme = usetheme.trim();
		if (usetheme.length() > 0) {
			if (usetheme.equalsIgnoreCase("true") || usetheme.equalsIgnoreCase("yes")) {
				UIOutput output = new UIOutput();
				output.setRendererType("javax.faces.resource.Stylesheet");
				output.getAttributes().put("name", "css/" + theme + "/theme.css");
				output.getAttributes().put("library", C.BSF_LIBRARY);
				output.getAttributes().put("target", "head");
				addResourceIfNecessary(root, context, output);
			}
		}

		// deactivate FontAwesome support if the no-fa facet is found in the
		// h:head tag
		UIComponent header = findHeader(root);
		boolean useCDNImportForFontAwesome = (null == header) || (null == header.getFacet("no-fa"));
		if (useCDNImportForFontAwesome) {
			String useCDN = FacesContext.getCurrentInstance().getExternalContext()
					.getInitParameter("net.bootsfaces.get_fontawesome_from_cdn");
			if (null != useCDN) {
				useCDN = ELTools.evalAsString(useCDN);
			}
			if (null != useCDN) {
				if (useCDN.equalsIgnoreCase("false") || useCDN.equals("no"))
					useCDNImportForFontAwesome = false;
				else
					useCDNImportForFontAwesome = true;
			}
		}

		// Do we have to add font-awesome and jQuery, or are the resources
		// already there?
		boolean loadJQuery = true;
		String suppressJQuery = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.get_jquery_from_cdn");
		if (null != suppressJQuery)
			suppressJQuery = ELTools.evalAsString(suppressJQuery);
		if (null != suppressJQuery)
			if (suppressJQuery.equalsIgnoreCase("true") || suppressJQuery.equals("yes"))
				loadJQuery = false;

		boolean loadJQueryUI = true;
		String suppressJQueryUI = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.get_jqueryui_from_cdn");
		if (null != suppressJQueryUI)
			suppressJQueryUI = ELTools.evalAsString(suppressJQueryUI);
		if (null != suppressJQueryUI)
			if (suppressJQueryUI.equalsIgnoreCase("true") || suppressJQueryUI.equals("yes"))
				loadJQueryUI = false;

		boolean loadBootstrapFromCDN = false;
		String loadBootstrapFromCDNParam = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.get_bootstrap_from_cdn");
		if (null != loadBootstrapFromCDNParam)
			loadBootstrapFromCDNParam = ELTools.evalAsString(loadBootstrapFromCDNParam);
		if (null != loadBootstrapFromCDNParam)
			if (loadBootstrapFromCDNParam.equalsIgnoreCase("true") || loadBootstrapFromCDNParam.equals("yes"))
				loadBootstrapFromCDN = true;

		List<UIComponent> availableResources = root.getComponentResources(context, "head");
		for (UIComponent ava : availableResources) {
			String name = (String) ava.getAttributes().get("name");
			if (null != name) {
				name = name.toLowerCase();
				if ((name.contains("font-awesome") || name.contains("fontawesome")) && name.endsWith("css"))
					useCDNImportForFontAwesome = false;
				if (name.startsWith("jquery-ui") && name.endsWith(".js")) {
					// do nothing - the if is needed to avoid confusion between
					// jQuery and jQueryUI
					loadJQueryUI = false;
				} else if (name.startsWith("jquery") && name.endsWith(".js")) {
					loadJQuery = false;
				}
			}
		}

		// Font Awesome
		if (useCDNImportForFontAwesome) { // !=null && usefa.equals(C.TRUE)) {
			InternalFALink output = new InternalFALink();
			output.getAttributes().put("src", C.FONTAWESOME_CDN_URL);
			addResourceIfNecessary(root, context, output);
		}

		Map<String, Object> viewMap = root.getViewMap();

		/* In the old way, we check if the single component needs the bsf.js
		 * library. This can be an error prone approach so we start to force
		 * the bsf addition (if not different specified) 
		 * 
		@SuppressWarnings("unchecked")
		Map<String, String> basicResourceMap = (Map<String, String>) viewMap.get(BASIC_JS_RESOURCE_KEY);
		if(basicResourceMap != null) {
			if (basicResourceMap.containsValue("jsf.js")) {
				UIOutput output = new UIOutput();
				output.setRendererType("javax.faces.resource.Script");
				output.getAttributes().put("name", "jsf.js");
				output.getAttributes().put("library", "javax.faces");
				output.getAttributes().put("target", "head");
				addResourceIfNecessary(root, context, output);
			}
			
			if (basicResourceMap.containsValue("bsf.js") || basicResourceMap.containsValue("js/bsf.js")) {
				UIOutput output = new UIOutput();
				output.setRendererType("javax.faces.resource.Script");
				output.getAttributes().put("name", "js/bsf.js");
				output.getAttributes().put("library", "bsf");
				output.getAttributes().put("target", "head");
				addResourceIfNecessary(root, context, output);
			}
		}
		 */
		
		// add JSF by default
		UIOutput jsfOutput = new UIOutput();
		jsfOutput.setRendererType("javax.faces.resource.Script");
		jsfOutput.getAttributes().put("name", "jsf.js");
		jsfOutput.getAttributes().put("library", "javax.faces");
		jsfOutput.getAttributes().put("target", "head");
		addResourceIfNecessary(root, context, jsfOutput);
		
		// add BSF by default
		UIOutput bsfOutput = new UIOutput();
		bsfOutput.setRendererType("javax.faces.resource.Script");
		bsfOutput.getAttributes().put("name", "js/bsf.js");
		bsfOutput.getAttributes().put("library", "bsf");
		bsfOutput.getAttributes().put("target", "head");
		addResourceIfNecessary(root, context, bsfOutput);

		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(RESOURCE_KEY);

		if (null != resourceMap) {
			if (loadJQuery) {
				/* In the old way, we check if the single component needs the jquery
				 * library. This can be an error prone approach so we start to force
				 * the jquery addition (if not different specified)
				boolean needsJQuery = false;
				for (Entry<String, String> entry : resourceMap.entrySet()) {
					String file = entry.getValue();
					if ("jq/jquery.js".equals(file)) {
						needsJQuery = true;
					}
				}
				if (needsJQuery) {
					UIOutput output = new UIOutput();
					output.setRendererType("javax.faces.resource.Script");
					output.getAttributes().put("name", "jq/jquery.js");
					output.getAttributes().put("library", C.BSF_LIBRARY);
					output.getAttributes().put("target", "head");
					addResourceIfNecessary(root, context, output);
				}*/
				UIOutput output = new UIOutput();
				output.setRendererType("javax.faces.resource.Script");
				output.getAttributes().put("name", "jq/jquery.js");
				output.getAttributes().put("library", C.BSF_LIBRARY);
				output.getAttributes().put("target", "head");
				addResourceIfNecessary(root, context, output);
			}

			for (Entry<String, String> entry : resourceMap.entrySet()) {
				String file = entry.getValue();
				String library = entry.getKey().substring(0, entry.getKey().length() - file.length() - 1);
				if (!"jq/jquery.js".equals(file) || (!"bsf".equals(library))) {
					if ((!file.startsWith("jq/ui")) || (!"bsf".equals(library)) || loadJQueryUI) {
						UIOutput output = new UIOutput();
						output.setRendererType("javax.faces.resource.Script");
						output.getAttributes().put("name", file);
						output.getAttributes().put("library", library);
						output.getAttributes().put("target", "head");
						addResourceIfNecessary(root, context, output);
					}
				}
			}
			viewMap.remove(RESOURCE_KEY);
		}
		String blockUI = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("net.bootsfaces.blockUI");
		if (null != blockUI)
			blockUI = ELTools.evalAsString(blockUI);
		if (null != blockUI && (blockUI.equalsIgnoreCase("yes") || blockUI.equalsIgnoreCase("true")))
		{
			// addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
			UIOutput output = new UIOutput();
			output.setRendererType("javax.faces.resource.Script");
			output.getAttributes().put("name", "js/jquery.blockUI.js");
			output.getAttributes().put("library", C.BSF_LIBRARY);
			output.getAttributes().put("target", "head");
			addResourceIfNecessary(root, context, output);
		}

		// replaceCSSResourcesByMinifiedResources(root, context);
		removeDuplicateResources(root, context);
		if (loadBootstrapFromCDN) {
			removeBootstrapResources(root, context);
		}
		enforceCorrectLoadOrder(root, context);

		{
			InternalIE8CompatiblityLinks output = new InternalIE8CompatiblityLinks();
			addResourceIfNecessary(root, context, output);
		}

		List<UIComponent> res = root.getComponentResources(context, "head");
		for (UIComponent ava : res) {
			String library = (String) ava.getAttributes().get("library");
			if (library != null && library.equals("bsf")) {
				String name = (String) ava.getAttributes().get("name");
				if (null != name) {
					if (name.endsWith(".css") && name.startsWith("css/")) {
						if (!name.equals("css/icons.css")) {
							ava.getAttributes().remove("name");
							int pos = name.lastIndexOf('/');
							ava.getAttributes().put("name", "css/" + theme + "/" + name.substring(pos + 1));
						}
					}
				}
			}
		}

		List<String> themedCSSMap = (List<String>) viewMap.get(THEME_RESOURCE_KEY);
		if(themedCSSMap != null) {
			for (String file: themedCSSMap) {
				UIOutput themedCSS = new UIOutput();
				themedCSS.setRendererType("javax.faces.resource.Stylesheet");
				themedCSS.getAttributes().put("name", "css/" + theme + "/" + file);
				themedCSS.getAttributes().put("library", C.BSF_LIBRARY);
				themedCSS.getAttributes().put("target", "head");
				addResourceIfNecessary(root, context, themedCSS);
			}
		}

		// Glyphicons
		UIOutput goutput = new UIOutput();
		goutput.setRendererType("javax.faces.resource.Stylesheet");
		goutput.getAttributes().put("name", "css/icons.css");
		//goutput.getAttributes().put("name", "css/" + theme + "/icons.css");
		goutput.getAttributes().put("library", C.BSF_LIBRARY);
		goutput.getAttributes().put("target", "head");
		addResourceIfNecessary(root, context, goutput);
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, InternalIE8CompatiblityLinks output) {
		for (UIComponent c : root.getComponentResources(context, "head")) {
			if (c instanceof InternalIE8CompatiblityLinks)
				return;
		}
		root.addComponentResource(context, output, "head");
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, InternalFALink output) {
		for (UIComponent c : root.getComponentResources(context, "head")) {
			if (c instanceof InternalFALink)
				return;
		}
		root.addComponentResource(context, output, "head");
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, UIOutput output) {
		Object libToAdd = output.getAttributes().get("library");
		Object nameToAdd = output.getAttributes().get("name");
		for (UIComponent c : root.getComponentResources(context, "head")) {
			String library = (String) c.getAttributes().get("library");
			String name = (String) c.getAttributes().get("name");
			if (library != null && library.equals(libToAdd)) {
				if (name != null && name.equals(nameToAdd)) {
					return;
				}
			}
		}
		root.addComponentResource(context, output, "head");
		//        System.out.println("++" + output.getClientId() + " " + nameToAdd + " " + libToAdd);
	}

	/**
	 * Remove duplicate resource files. For some reason, many resource files are
	 * added more than once, especially when AJAX is used. The method removes
	 * the duplicate files.
	 * 
	 * TODO: Verify if the duplicate resource files are a bug of BootsFaces or
	 * of the Mojarra library itself.
	 * 
	 * @param root
	 *            The current UIViewRoot
	 * @param context
	 *            The current FacesContext
	 */
	private void removeDuplicateResources(UIViewRoot root, FacesContext context) {
		List<UIComponent> resourcesToRemove = new ArrayList<UIComponent>();
		Map<String, UIComponent> alreadyThere = new HashMap<String, UIComponent>();
		for (UIComponent resource : root.getComponentResources(context, "head")) {
			String name = (String) resource.getAttributes().get("name");
			String library = (String) resource.getAttributes().get("library");
			String key = library + "/" + name + "/" + resource.getClass().getName();
			if (alreadyThere.containsKey(key)) {
				resourcesToRemove.add(resource);
				continue;
			}
			alreadyThere.put(key, resource);
		}
		for (UIComponent c : resourcesToRemove) {
			//			c.setInView(false);
			root.removeComponentResource(context, c);
			//String name = (String) c.getAttributes().get("name");
			//String library = (String) c.getAttributes().get("library");
			//System.out.println("-1" + c.getClientId() + " " + name + " " + library + " " + c.getClass().getSimpleName() );
		}
	}

	/**
	 * Remove Bootstrap CSS files (called if the context param
	 * "net.bootsfaces.get_bootstrap_from_cdn" is set).
	 * 
	 * @param root
	 *            The current UIViewRoot
	 * @param context
	 *            The current FacesContext
	 */
	private void removeBootstrapResources(UIViewRoot root, FacesContext context) {
		List<UIComponent> resourcesToRemove = new ArrayList<UIComponent>();
		for (UIComponent resource : root.getComponentResources(context, "head")) {
			String name = (String) resource.getAttributes().get("name");
			String library = (String) resource.getAttributes().get("library");
			if ("bsf".equals(library) && name != null && name.endsWith(".css")) {
				resourcesToRemove.add(resource);
			}
		}
		for (UIComponent c : resourcesToRemove) {
			c.setInView(false);
			root.removeComponentResource(context, c);
			//System.out.println("-2" + c.getClientId());
		}
	}

	/**
	 * Make sure jQuery is loaded before jQueryUI, and that every other
	 * Javascript is loaded later. Also make sure that the BootsFaces resource
	 * files are loaded prior to other resource files, giving the developer the
	 * opportunity to overwrite a CSS or JS file.
	 * 
	 * @param root
	 *            The current UIViewRoot
	 * @param context
	 *            The current FacesContext
	 */
	private void enforceCorrectLoadOrder(UIViewRoot root, FacesContext context) {
		// // first, handle the CSS files.
		// // Put BootsFaces.css or BootsFaces.min.css first,
		// // theme.css second
		// // and everything else behind them.
		List<UIComponent> resources = new ArrayList<UIComponent>();
		List<UIComponent> first = new ArrayList<UIComponent>();
		List<UIComponent> middle = new ArrayList<UIComponent>();
		List<UIComponent> last = new ArrayList<UIComponent>();

		for (UIComponent resource : root.getComponentResources(context, "head")) {
			String name = (String) resource.getAttributes().get("name");

			String position = (String) resource.getAttributes().get("position");
			if ("first".equals(position)) {
				first.add(resource);
			} else if ("last".equals(position)) {
				last.add(resource);
			} else if ("middle".equals(position)) {
				middle.add(resource);
			} else {
				if (name != null && (name.endsWith(".js"))) {
					resources.add(resource);
				}
			}
		}

		// add the JavaScript files in correct order.
		Collections.sort(resources, new Comparator<UIComponent>() {

			@Override
			public int compare(UIComponent o1, UIComponent o2) {
				String name1 = (String) o1.getAttributes().get("name");
				// String lib1 = (String) o1.getAttributes().get("name");
				String name2 = (String) o2.getAttributes().get("name");
				// String lib2 = (String) o2.getAttributes().get("name");
				if (name1 == null)
					return 1;
				if (name2 == null)
					return -1;
				if (name1.endsWith(".js") && (!(name2.endsWith(".js"))))
					return 1;
				if (name2.endsWith(".js") && (!(name1.endsWith(".js"))))
					return -1;
				if (name1.endsWith(".js")) {
					if (name1.toLowerCase().contains("jquery-ui"))
						name1 = "2.js"; // make it the second JS file
					else if (name1.toLowerCase().contains("jquery"))
						name1 = "1.js"; // make it the first JS file
					else if (name1.toLowerCase().contains("ui/core.js"))
						name1 = "3.js"; // make it the first JS file
					else if (name1.toLowerCase().contains("ui/widget.js"))
						name1 = "4.js"; // make it the last JS file
					else if (name1.toLowerCase().contains("bsf.js"))
						name1 = "zzz.js"; // make it the last JS file
					else
						name1 = "keep.js"; // don't move it
				}
				if (name2.endsWith(".js")) {
					if (name2.toLowerCase().contains("jquery-ui"))
						name2 = "2.js"; // make it the second JS file
					else if (name2.toLowerCase().contains("jquery"))
						name2 = "1.js"; // make it the first JS file
					else if (name2.toLowerCase().contains("ui/core.js"))
						name2 = "3.js"; // make it the first JS file
					else if (name2.toLowerCase().contains("ui/widget.js"))
						name2 = "4.js"; // make it the last JS file
					else if (name2.toLowerCase().contains("bsf.js"))
						name2 = "zzz.js"; // make it the last JS file
					else
						name2 = "keep.js"; // don't move it
				}
				int result = name1.compareTo(name2);

				return result;
			}
		});

		for (UIComponent c : first) {
			root.removeComponentResource(context, c);
		}
		for (UIComponent c : resources) {
			root.removeComponentResource(context, c);
		}
		for (UIComponent c : last) {
			root.removeComponentResource(context, c);
		}

		for (UIComponent c : root.getComponentResources(context, "head")) {
			middle.add(c);
		}
		for (UIComponent c : middle) {
			root.removeComponentResource(context, c);
		}
		for (UIComponent c : first) {
			root.addComponentResource(context, c, "head");
			//			root.getComponentResources(context, "head").add(c);
		}
		for (UIComponent c : middle) {
			root.addComponentResource(context, c, "head");
			//			root.addComponentResource(context, c, "head");
		}
		for (UIComponent c : resources) {
			root.addComponentResource(context, c, "head");
		}
		for (UIComponent c : last) {
			root.addComponentResource(context, c, "head");
		}
	}

	/**
	 * Look whether a b:iconAwesome component is used. If so, the
	 * font-awesome.css is removed from the resource list because it's loaded
	 * from the CDN.
	 * 
	 * @return true, if the font-awesome.css is found in the resource list. Note
	 *         the side effect of this method!
	 */
	private boolean isFontAwesomeComponentUsedAndRemoveIt() {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = fc.getViewRoot();
		ListIterator<UIComponent> resourceIterator = (viewRoot.getComponentResources(fc, "head")).listIterator();
		UIComponent fontAwesomeResource = null;
		while (resourceIterator.hasNext()) {
			UIComponent resource = (UIComponent) resourceIterator.next();
			String name = (String) resource.getAttributes().get("name");
			// rw.write("\n<!-- res: '"+name+"' -->" );
			if (name != null) {
				if (name.endsWith("font-awesome.css"))
					fontAwesomeResource = resource;
			}
		}
		if (null != fontAwesomeResource) {
			fontAwesomeResource.setInView(false);
			viewRoot.removeComponentResource(fc, fontAwesomeResource);
			//System.out.println("-3" + fontAwesomeResource.getClientId());
			return true;
		}
		return false;

	}

	/**
	 * Looks for the header in the JSF tree.
	 * 
	 * @param root
	 *            The root of the JSF tree.
	 * @return null, if the head couldn't be found.
	 */
	private UIComponent findHeader(UIViewRoot root) {
		for (UIComponent c : root.getChildren()) {
			if (c instanceof HtmlHead)
				return c;
		}
		for (UIComponent c : root.getChildren()) {
			if (c instanceof HtmlBody)
				return null;
			if (c instanceof UIOutput)
				if (c.getFacets() != null)
					return c;
		}
		return null;
	}

	/**
	 * Which JSF elements do we listen to?
	 */
	@Override
	public boolean isListenerForSource(Object source) {
		if (source instanceof UIComponent) {
			return true;
		}
		return false;
	}

	/**
	 * Registers a JS file that needs to be include in the header of the HTML
	 * file, but after jQuery and AngularJS.
	 * 
	 * @param library
	 *            The name of the sub-folder of the resources folder.
	 * @param resource
	 *            The name of the resource file within the library folder.
	 */
	public static void addResourceToHeadButAfterJQuery(String library, String resource) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UIViewRoot v = ctx.getViewRoot();
		Map<String, Object> viewMap = v.getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(RESOURCE_KEY);
		if (null == resourceMap) {
			resourceMap = new HashMap<String, String>();
			viewMap.put(RESOURCE_KEY, resourceMap);
		}
		String key = library + "#" + resource;
		if (!resourceMap.containsKey(key)) {
			resourceMap.put(key, resource);
		}
	}

	/**
	 * Registers a core JS file that needs to be include in the header of the HTML
	 * file, but after jQuery and AngularJS.
	 * 
	 * @param library
	 *            The name of the sub-folder of the resources folder.
	 * @param resource
	 *            The name of the resource file within the library folder.
	 */
	public static void addBasicJSResource(String library, String resource) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UIViewRoot v = ctx.getViewRoot();
		Map<String, Object> viewMap = v.getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(BASIC_JS_RESOURCE_KEY);
		if (null == resourceMap) {
			resourceMap = new HashMap<String, String>();
			viewMap.put(BASIC_JS_RESOURCE_KEY, resourceMap);
		}
		String key = library + "#" + resource;
		if (!resourceMap.containsKey(resource)) {
			resourceMap.put(key, resource);
		}
	}

	/**
	 * Registers a themed CSS file that needs to be includes in the header of the HTML
	 * file.
	 * 
	 * @param resource
	 *            The name of the resource file within the library folder.
	 */
	public static void addThemedCSSResource(String resource) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UIViewRoot v = ctx.getViewRoot();
		Map<String, Object> viewMap = v.getViewMap();
		@SuppressWarnings("unchecked")
		List<String> resourceMap = (List<String>) viewMap.get(THEME_RESOURCE_KEY);
		if (null == resourceMap) {
			resourceMap = new ArrayList<String>();
			viewMap.put(THEME_RESOURCE_KEY, resourceMap);
		}
		String key = resource;
		if (!resourceMap.contains(key)) {
			resourceMap.add(key);
		}
	}
}
