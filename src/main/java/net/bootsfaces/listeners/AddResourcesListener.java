/**
 *  Copyright 2015-2016 Stephan Rauh, http://www.beyondjava.net
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
package net.bootsfaces.listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.faces.FacesException;
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
import net.bootsfaces.utils.BsfUtils;

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

	private static final String SCRIPT_RENDERER = "javax.faces.resource.Script",
								CSS_RENDERER = "javax.faces.resource.Stylesheet";

	static {
		LOGGER.info("This application is running on BootsFaces 0.9.0-SNAPSHOT");
	}

	/**
	 * Returns true if the source is an instance of {@link UIViewRoot}.
	 */
	@Override
	public boolean isListenerForSource(Object source) {
		return (source instanceof UIViewRoot);
	}

	/**
	 * Trigger adding the resources if and only if the event has been fired by
	 * UIViewRoot.
	 */
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();

		// render the resources only if there is at least one bsf component
		if(ensureExistBootsfacesComponent(root, context)) {
			addJavascript(root, context);
			addMetaTags(root, context);
		}
	}

	/**
	 * Check if there is almost one bootsfaces component in page.
	 * If yes, load the correct items.
	 *
	 * To ensure that, it checks first the viewMap to find specific bootsfaces key.
	 * If it found nothing, it check for components that has as a resource lib, the "bsf" lib.
	 *
	 * @param root <- the UIViewRoot
	 * @param context <- the faces context
	 * @return
	 */
	private boolean ensureExistBootsfacesComponent(UIViewRoot root, FacesContext context) {
		Map<String, Object> viewMap = root.getViewMap();

		// check explicit js request
		if(viewMap.get(RESOURCE_KEY) != null) return true;
		// check explicit css request
		if(viewMap.get(THEME_RESOURCE_KEY) != null) return true;
		// check referenced bsf request
		if(findBsfComponent(root, "bsf") != null) return true;

		return false;
	}

	/**
	 * Check all components in page to find one that
	 * has as resource library the target library.
	 * I use this method to check existence of a BsF component
	 * because, at this level, the getComponentResource returns always null
	 *
	 * @param parent the parent component
	 * @param targetLib the lib to search
	 * @return
	 */
	public static UIComponent findBsfComponent(UIComponent parent, String targetLib) {
		if (targetLib.equalsIgnoreCase((String)parent.getAttributes().get("library"))) {
			return parent;
		}
		Iterator<UIComponent> kids = parent.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent found = findBsfComponent(kids.next(), targetLib);
			if (found != null) {
				return found;
			}
		}
		return null;
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
		String viewportParam = BsfUtils.getInitParam(C.P_VIEWPORT, context);

		viewportParam = evalELIfPossible(viewportParam);
		String content = "width=device-width, initial-scale=1";
		if (!viewportParam.isEmpty() && isFalseOrNo(viewportParam))
			return;
		if(!viewportParam.isEmpty() && !isTrueOrYes(viewportParam))
			content = viewportParam;

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
	private void addJavascript(UIViewRoot root, FacesContext context) {
		// The following code is needed to diagnose the warning "Unable to save dynamic action with clientId 'j_id...'"
		//				List<UIComponent> r = root.getComponentResources(context, "head");
		//				System.out.println("**************");
		//				for (UIComponent ava : r) {
		//					String name = (String) ava.getAttributes().get("name");
		//					System.out.println(ava.getClientId(context) +":" + name + " " + ava.getClass().getSimpleName());
		//				}
		//				System.out.println("**************");
		// end of the diagnostic code

		ResourceHandler rh = context.getApplication().getResourceHandler();
		String theme = loadTheme(root, context, rh);

		// deactivate FontAwesome support if the no-fa facet is found in the h:head tag
		UIComponent header = findHeader(root);
		boolean useCDNImportForFontAwesome = (null == header) || (null == header.getFacet("no-fa"));
		if (useCDNImportForFontAwesome) {
			String useCDN = BsfUtils.getInitParam("net.bootsfaces.get_fontawesome_from_cdn");
			if (null != useCDN) {
				useCDN = ELTools.evalAsString(useCDN);
			}
			if (null != useCDN) {
				useCDNImportForFontAwesome = !isFalseOrNo(useCDN);
			}
		}

		boolean loadJQuery = shouldLibraryBeLoaded("net.bootsfaces.get_jquery_from_cdn", true);
		boolean loadJQueryUI = shouldLibraryBeLoaded("net.bootsfaces.get_jqueryui_from_cdn", true);
		boolean loadBootstrapFromCDN = shouldLibraryBeLoaded("net.bootsfaces.get_bootstrap_from_cdn", false);
		// Do we have to add font-awesome and jQuery, or are the resources already there?
		List<UIComponent> availableResources = root.getComponentResources(context, "head");
		for (UIComponent ava : availableResources) {
			String name = (String) ava.getAttributes().get("name");
			if (null != name) {
				name = name.toLowerCase();
				if ((name.contains("font-awesome") || name.contains("fontawesome")) && name.endsWith("css"))
					useCDNImportForFontAwesome = false;
				if (name.startsWith("jquery-ui") && name.endsWith(".js"))
					loadJQueryUI = false;
				else if (name.startsWith("jquery") && name.endsWith(".js"))
					loadJQuery = false;
			}
		}

		// Font Awesome
		if (useCDNImportForFontAwesome) {
			InternalFALink output = new InternalFALink();
			output.getAttributes().put("src", C.FONTAWESOME_CDN_URL);
			addResourceIfNecessary(root, context, output);
		}

		addMandatoryLibraries(root, context, loadJQuery, loadJQueryUI);

		String blockUI = BsfUtils.getInitParam("net.bootsfaces.blockUI", context);
		if (null != blockUI)
			blockUI = ELTools.evalAsString(blockUI);
		if (null != blockUI && isTrueOrYes(blockUI)) {
			createAndAddComponent(root, context, SCRIPT_RENDERER, "js/jquery.blockUI.js", C.BSF_LIBRARY);
		}

		removeDuplicateResources(root, context);
		if (loadBootstrapFromCDN) {
			removeBootstrapResources(root, context);
		}

		addResourceIfNecessary(root, context, new InternalIE8CompatiblityLinks());

		List<UIComponent> res = root.getComponentResources(context, "head");
		for (UIComponent ava : res) {
			String library = (String) ava.getAttributes().get("library");
			if (library != null && library.equals("bsf")) {
				String name = (String) ava.getAttributes().get("name");
				if (null != name && name.endsWith(".css") && name.startsWith("css/")
						&& !name.equals("css/icons.css")) {
					ava.getAttributes().remove("name");
					int pos = name.lastIndexOf('/');
					ava.getAttributes().put("name", "css/" + theme + "/" + name.substring(pos + 1));
				}
			}
		}

		@SuppressWarnings("unchecked")
		List<String> themedCSSMap = (List<String>) root.getViewMap().get(THEME_RESOURCE_KEY);
		if(themedCSSMap != null) {
			for (String file: themedCSSMap) {
				String name = "css/" + theme + "/" + file;
				if(file.equals("icons.css")) //the icons.css file isn't found in a theme folder
					name = "css/icons.css";  //look for it under the css root instead

				createAndAddComponent(root, context, CSS_RENDERER, name, C.BSF_LIBRARY);
			}
		}

		// Glyphicons now icons are in core.css
		//String name = "css/icons.css";
		//createAndAddComponent(root, context, CSS_RENDERER, name, C.BSF_LIBRARY);
		enforceCorrectLoadOrder(root, context);
	}

	private Map<String, Object> addMandatoryLibraries(
			UIViewRoot root, FacesContext context, boolean loadJQuery, boolean loadJQueryUI) {

		/* We used to check if a single component needs the bsf.js, jsf or jquery library.
		 * This can be an error prone approach so we add all of them (if not different specified)
		 */
		createAndAddComponent(root, context, SCRIPT_RENDERER, "jsf.js", "javax.faces");
		createAndAddComponent(root, context, SCRIPT_RENDERER, "js/bsf.js", "bsf");

		if (loadJQuery)
			createAndAddComponent(root, context, SCRIPT_RENDERER, "jq/jquery.js", C.BSF_LIBRARY);

		Map<String, Object> viewMap = root.getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(RESOURCE_KEY);

		if (null != resourceMap) {
			for (Entry<String, String> entry : resourceMap.entrySet()) {
				String file = entry.getValue();
				String library = entry.getKey().substring(0, entry.getKey().length() - file.length() - 1);
				if (!"jq/jquery.js".equals(file) || !"bsf".equals(library) ||
						!file.startsWith("jq/ui") || loadJQueryUI)
					createAndAddComponent(root, context, SCRIPT_RENDERER, file, library);
			}
			viewMap.remove(RESOURCE_KEY);
		}
		return viewMap;
	}

	private String loadTheme(UIViewRoot root, FacesContext context, ResourceHandler rh) {
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
		String theme = evalELIfPossible(BsfUtils.getInitParam(C.P_THEME, context));
		if (!theme.isEmpty()) {
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
				String name = "css/" + theme + "/" + filename;
				createAndAddComponent(root, context, CSS_RENDERER, name, C.BSF_LIBRARY);
			}
		}

		// If the BootsFaces_USETHEME parameter is true, render Theme CSS link
		String usetheme = evalELIfPossible(BsfUtils.getInitParam(C.P_USETHEME, context));
		if (!usetheme.isEmpty() && isTrueOrYes(usetheme)) {
			String name = "css/" + theme + "/theme.css";
			createAndAddComponent(root, context, CSS_RENDERER, name, C.BSF_LIBRARY);
		}

		return theme;
	}

	private boolean shouldLibraryBeLoaded(String initParameter, boolean defaultValue) {
		String suppressLibrary = BsfUtils.getInitParam(initParameter);
		if(suppressLibrary == null)
			return defaultValue;
		return !isTrueOrYes(ELTools.evalAsString(suppressLibrary));
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, InternalIE8CompatiblityLinks output) {
		addResourceIfNecessary(root, context, output, output.getClass());
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, InternalFALink output) {
		addResourceIfNecessary(root, context, output, output.getClass());
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, UIComponent output, Class<?> clazz) {
		for (UIComponent c : root.getComponentResources(context, "head"))
			if (c.getClass() == clazz)
				return;
		//resource not found yet, so add it now
		root.addComponentResource(context, output, "head");
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, UIOutput output) {
		Object libToAdd = output.getAttributes().get("library");
		Object nameToAdd = output.getAttributes().get("name");
		for (UIComponent c : root.getComponentResources(context, "head")) {
			String library = (String) c.getAttributes().get("library");
			String name = (String) c.getAttributes().get("name");
			if (library != null && library.equals(libToAdd) && name != null && name.equals(nameToAdd))
				return;
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
		List<UIComponent> components = new ArrayList<UIComponent>(
				root.getComponentResources(context, "head"));
		Collections.sort(components, new Comparator<UIComponent>() {
			@Override
			public int compare(UIComponent o1, UIComponent o2) {
				return o1.getClientId().compareTo(o2.getClientId());
			}
		});
		for (UIComponent resource : components) {
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
			c.setInView(false);
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
		Collections.sort(resources, new ResourceFileComparator());

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
			UIComponent resource = resourceIterator.next();
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
	 * Registers a JS file that needs to be included in the header of the HTML
	 * file, but after jQuery and AngularJS.
	 *
	 * @param library
	 *            The name of the sub-folder of the resources folder.
	 * @param resource
	 *            The name of the resource file within the library folder.
	 */
	public static void addResourceToHeadButAfterJQuery(String library, String resource) {
		addResource(resource, library, library + "#" + resource, RESOURCE_KEY);
	}

	/**
	 * Registers a core JS file that needs to be included in the header of the HTML
	 * file, but after jQuery and AngularJS.
	 *
	 * @param library
	 *            The name of the sub-folder of the resources folder.
	 * @param resource
	 *            The name of the resource file within the library folder.
	 */
	public static void addBasicJSResource(String library, String resource) {
		addResource(resource, library, resource, BASIC_JS_RESOURCE_KEY);
	}

	/**
	 * Registers a themed CSS file that needs to be included in the header of the HTML
	 * file.
	 *
	 * @param resource
	 *            The name of the resource file within the library folder.
	 */
	public static void addThemedCSSResource(String resource) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		@SuppressWarnings("unchecked")
		List<String> resourceList = (List<String>) viewMap.get(THEME_RESOURCE_KEY);
		if (null == resourceList) {
			resourceList = new ArrayList<String>();
			viewMap.put(THEME_RESOURCE_KEY, resourceList);
		}

		if (!resourceList.contains(resource)) {
			resourceList.add(resource);
		}
	}

	private static void addResource(String resource, String library, String resourceKey, String resourceTypeKey) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(resourceTypeKey);
		if (null == resourceMap) {
			resourceMap = new HashMap<String, String>();
			viewMap.put(resourceTypeKey, resourceMap);
		}

		if (!resourceMap.containsKey(resourceKey))
			resourceMap.put(library + "#" + resource, resource);
	}

	private String evalELIfPossible(String expression) {
		if (expression != null)
			expression = ELTools.evalAsString(expression).trim();
		else
			expression = "";
		return expression;
	}

	private void createAndAddComponent(UIViewRoot root, FacesContext context,
			String rendererType, String name, String library) {
		UIOutput output = new UIOutput();
		output.setRendererType(rendererType);
		output.getAttributes().put("name", name);
		output.getAttributes().put("library", library);
		output.getAttributes().put("target", "head");
		addResourceIfNecessary(root, context, output);
	}

	private boolean isTrueOrYes(String param) {
		return param.equalsIgnoreCase("true") || param.equalsIgnoreCase("yes");
	}

	private boolean isFalseOrNo(String param) {
		return param.equalsIgnoreCase("false") || param.equalsIgnoreCase("no");
	}
}
