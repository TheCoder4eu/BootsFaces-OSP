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
	 * Components can request resources by registering them in the ViewMap,
	 * using the RESOURCE_KEY.
	 */
	private static final String RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.ResourceFiles";

	static {
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

			addJavascript((UIViewRoot) source, context, isProduction);
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

		// If the BootsFaces_USETHEME parameter is true, render Theme CSS link
		UIOutput bootsfaces = new UIOutput();
		bootsfaces.setRendererType("javax.faces.resource.Stylesheet");
		bootsfaces.getAttributes().put("name", "css/bootsfaces.css");
		bootsfaces.getAttributes().put("library", C.BSF_LIBRARY);
		bootsfaces.getAttributes().put("target", "head");
		addResourceIfNecessary(root, context, bootsfaces);
		String theme = null;
		theme = context.getExternalContext().getInitParameter(C.P_USETHEME);
		theme = ELTools.evalAsString(theme);
		if (theme != null) {
			if (theme.equalsIgnoreCase("true") || theme.equalsIgnoreCase("yes")) {
				theme = "default";
			} else if (theme.equalsIgnoreCase("false") || theme.equalsIgnoreCase("no")
					|| theme.equalsIgnoreCase("custom")) {
				theme = "other";
			}
		} else
			theme = "other";
		if (isFontAwesomeComponentUsedAndRemoveIt() || (!theme.equalsIgnoreCase("other"))) {
			String filename = "bsf.css";
			Resource themeResource = rh.createResource("css/" + theme + "/" + filename, C.BSF_LIBRARY);

			if (themeResource == null) {
				throw new FacesException("Error loading theme, cannot find \"" + "css/" + filename + "\" resource of \""
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

		// deactivate FontAwesome support if the no-fa facet is found in the
		// h:head tag
		UIComponent header = findHeader(root);
		boolean useCDNImportForFontAwesome = (null == header) || (null == header.getFacet("no-fa"));
		if (useCDNImportForFontAwesome) {
			String useCDN = FacesContext.getCurrentInstance().getExternalContext()
					.getInitParameter("net.bootsfaces.get_fontawesome_from_cdn");
			if (null != useCDN)
				if (useCDN.equalsIgnoreCase("false") || useCDN.equals("no"))
					useCDNImportForFontAwesome = false;
				else
					useCDNImportForFontAwesome = true;
		}

		// Do we have to add font-awesome and jQuery, or are the resources
		// already there?
		boolean loadJQuery = true;
		String suppressJQuery = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.get_jquery_from_cdn");
		if (null != suppressJQuery)
			if (suppressJQuery.equalsIgnoreCase("true") || suppressJQuery.equals("yes"))
				loadJQuery = false;

		boolean loadJQueryUI = true;
		String suppressJQueryUI = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.get_jqueryui_from_cdn");
		if (null != suppressJQueryUI)
			if (suppressJQueryUI.equalsIgnoreCase("true") || suppressJQueryUI.equals("yes"))
				loadJQueryUI = false;

		boolean loadBootstrapFromCDN = false;
		String loadBootstrapFromCDNParam = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("net.bootsfaces.get_bootstrap_from_cdn");
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
				if (name.contains("jquery-ui") && name.endsWith(".js")) {
					// do nothing - the if is needed to avoid confusion between
					// jQuery and jQueryUI
					loadJQueryUI = false;
				} else if (name.contains("jquery") && name.endsWith(".js")) {
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
		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(RESOURCE_KEY);

		if (null != resourceMap) {
			if (loadJQuery) {
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
				}

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
						ava.getAttributes().remove("name");
						int pos = name.lastIndexOf('/');
						ava.getAttributes().put("name", "css/" + theme + "/" + name.substring(pos + 1));
					}
				}
			}
		}

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
	private void replaceCSSResourcesByMinifiedResources(UIViewRoot root, FacesContext context) {
		Application app = context.getApplication();
		ResourceHandler rh = app.getResourceHandler();
		Resource cssmin = rh.createResource("css/BootsFaces.min.css", C.BSF_LIBRARY);
		// Resource jsmin = rh.createResource("js/BootsFaces.min.js",
		// C.BSF_LIBRARY);

		if (cssmin != null) {
			List<UIComponent> resourcesToRemove = new ArrayList<UIComponent>();
			for (UIComponent resource : root.getComponentResources(context, "head")) {
				String name = (String) resource.getAttributes().get("name");
				String library = (String) resource.getAttributes().get("library");
				if ((library != null) && library.equals("bsf"))
					if ((name != null) && (!name.startsWith("jq/"))) {
						if (name.endsWith(".css")) {
							if (!name.equals("css/theme.css"))
								resourcesToRemove.add(resource);
						}
					}
			}
			for (UIComponent c : resourcesToRemove) {
				root.removeComponentResource(context, c);
			}
			UIOutput output = new UIOutput();
			output.setRendererType("javax.faces.resource.Stylesheet");
			output.getAttributes().put("name", "css/BootsFaces.min.css");
			output.getAttributes().put("library", C.BSF_LIBRARY);
			output.getAttributes().put("target", "head");
			addResourceIfNecessary(root, context, output);

			// output = new UIOutput();
			// output.setRendererType("javax.faces.resource.Script");
			// output.getAttributes().put("name", "js/BootsFaces.min.js");
			// output.getAttributes().put("library", C.BSF_LIBRARY);
			// output.getAttributes().put("target", "head");
			// addResourceIfNecessary(root, context, output);

		}
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
			String key = library + "/" + name;
			if (alreadyThere.containsKey(key)) {
				resourcesToRemove.add(resource);
				continue;
			}
			alreadyThere.put(key, resource);
		}
		for (UIComponent c : resourcesToRemove) {
			root.removeComponentResource(context, c);
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
			root.removeComponentResource(context, c);
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
						name1 = "1.js"; // make it the second JS file
					else if (name1.toLowerCase().contains("bsf.js"))
						name1 = "zzz.js"; // make it the last JS file
					else
						name1 = "keep.js"; // don't move it
				}
				if (name2.endsWith(".js")) {
					if (name2.toLowerCase().contains("jquery-ui"))
						name2 = "2.js"; // make it the second JS file
					else if (name2.toLowerCase().contains("jquery"))
						name2 = "1.js"; // make it the second JS file
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
			root.getComponentResources(context, "head").add(c);
		}
		for (UIComponent c : middle) {
			root.addComponentResource(context, c, "head");
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
			viewRoot.removeComponentResource(fc, fontAwesomeResource);
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
}
