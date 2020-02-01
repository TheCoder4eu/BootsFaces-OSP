/**
 *  Copyright 2015-2016 Stephan Rauh, http://www.beyondjava.net,
 *                      and Riccardo Massera (TheCoder4.Eu)
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

import static net.bootsfaces.C.P_BLOCK_UI;
import static net.bootsfaces.C.P_GET_BOOTSTRAP_FROM_CDN;
import static net.bootsfaces.C.P_GET_DATATABLE_FROM_CDN;
import static net.bootsfaces.C.P_GET_FONTAWESOME_FROM_CDN;
import static net.bootsfaces.C.P_GET_JQUERYUI_FROM_CDN;
import static net.bootsfaces.C.P_GET_JQUERY_FROM_CDN;
import static net.bootsfaces.C.THEME_NAME_DEFAULT;
import static net.bootsfaces.C.THEME_NAME_OTHER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
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
import net.bootsfaces.component.internalCssScriptResource.InternalCssScriptResource;
import net.bootsfaces.component.internalJavaScriptResource.InternalJavaScriptResource;
import net.bootsfaces.utils.BsfUtils;

/**
 * This class adds the resource needed by BootsFaces and ensures that they are loaded in the correct order. It replaces
 * the former HeadListener.
 *
 * @author Stephan Rauh
 */
public class AddResourcesListener implements SystemEventListener {

	private static final Logger LOGGER = Logger.getLogger("net.bootsfaces.listeners.AddResourcesListener");

	/**
	 * List of JS files that have to be loaded earlier than most JS files.
	 */
	private static final String BASIC_JS_RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.BasicJSResourceFiles";

	/**
	 * Version of FontAwesome. By default, version 4.7.0 is used. If you specify the subfont ("solid", "regular",
	 * "light" or "brand"), the version number is automatically set to 5.2.0.
	 */
	private static final String FONTAWESOME_VERSION = "net.bootsfaces.listeners.AddResourcesListener.FontAwesomeVersion";

	/**
	 * List of components using a non-default version of FontAwesome. Needed to detect whether FontAwesome 4 (the
	 * default version) needs to be imported or not.
	 */
	private static final String FONTAWESOME_NEW_VERSION = "net.bootsfaces.listeners.AddResourcesListener.FontAwesomeNewVersion";

	/**
	 * This entry of the view map is set to true if there's at least one Fontawesome icon
	 */
	private static final String FONTAWESOME_USED = "net.bootsfaces.listeners.AddResourcesListener.FontAwesomeIsUsed";

	/**
	 * Components can request JS resources by registering them in the ViewMap, using the RESOURCE_KEY.
	 */
	private static final String RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.ResourceFiles";

	/**
	 * Components can request themed CSS resources by registering them in the ViewMap, using the THEME_RESOURCE_KEY.
	 */
	private static final String THEME_RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.ThemedResourceFiles";

	/**
	 * Components can request extended CSS resources by registering them in the ViewMap, using the EXT_RESOURCE_KEY.
	 */
	private static final String EXT_RESOURCE_KEY = "net.bootsfaces.listeners.AddResourcesListener.ExtResourceFiles";

	private static final String SCRIPT_RENDERER = "javax.faces.resource.Script",
		CSS_RENDERER = "javax.faces.resource.Stylesheet";

	static {
		LOGGER.info("This application is running on BootsFaces " + C.BSFVERSION + "-" + C.BSFRELEASE_STATUS);
	}

	/**
	 * Returns true if the source is an instance of {@link UIViewRoot}.
	 *
	 * @param source
	 * @return
	 */
	@Override
	public boolean isListenerForSource(Object source) {
		return (source instanceof UIViewRoot);
	}

	/**
	 * Trigger adding the resources if and only if the event has been fired by UIViewRoot.
	 *
	 * @param event
	 */
	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();

		// render the resources only if there is at least one bsf component
		if (ensureExistBootsfacesComponent(root)) {
			addCSS(root, context);
			addJavascript(root, context);
			addMetaTags(root, context);
		}
	}

	/**
	 *
	 * @param root the UIViewRoot
	 * @return
	 */
	private static Map<String, Object> getMapForView(UIViewRoot rootView) {
		// if the view is transient, we use only the attribute-store
		if (rootView.isTransient()) {
			LOGGER.log(Level.FINEST, "use attribute-store");
			return rootView.getAttributes();
		} else {
			// if a session exists, we can use the viewMap-store
			// check if a session exists
			boolean sessionExists = FacesContext.getCurrentInstance().getExternalContext().getSession(false) != null;
			if (sessionExists) {
				LOGGER.log(Level.FINEST, "use viewMap-store");
				return rootView.getViewMap();
			} else {
				// as far as I know, a session is started if there is a UIForm
				// if a form exists, we can use or create the viewMap-store
				// check if a form exists
				boolean formExists = getClosestForm(rootView) != null;
				Map<String, Object> viewMap = rootView.getViewMap(formExists);
				if (viewMap != null) {
					LOGGER.log(Level.FINEST, "use viewMap-store");
					return viewMap;
				} else {
					LOGGER.log(Level.FINEST, "use attribute-store");
					return rootView.getAttributes();
				}
			}
		}
	}

	/**
	 * searches recursively for the next form
	 * @param component
	 * @return 
	 */
	private static UIForm getClosestForm(UIComponent component) {
		for (UIComponent c : component.getChildren()) {
			if (c instanceof UIForm) {
				return (UIForm) c;
			} else {
				UIForm found = getClosestForm(c);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
	}

	/**
	 * Check if there is almost one bootsfaces component in page. If yes, load the correct items.
	 *
	 * To ensure that, it checks first the viewMap to find specific bootsfaces key. If it found nothing, it check
	 * for components that has as a resource lib, the "bsf" lib.
	 *
	 * @param root the UIViewRoot
	 * @param context the faces context
	 * @return
	 */
	private boolean ensureExistBootsfacesComponent(UIViewRoot root) {
		Map<String, Object> viewMap = getMapForView(root);

		// check explicit js request
		if (viewMap.get(RESOURCE_KEY) != null) {
			return true;
		}
		// check explicit css request
		if (viewMap.get(THEME_RESOURCE_KEY) != null) {
			return true;
		}
		// check explicit css request
		if (viewMap.get(EXT_RESOURCE_KEY) != null) {
			return true;
		}
		// check referenced bsf request
		return findBsfComponent(root, C.BSF_LIBRARY) != null;
	}

	/**
	 * Check all components in page to find one that has as resource library the target library. I use this method
	 * to check existence of a BsF component because, at this level, the getComponentResource returns always null
	 *
	 * @param parent the parent component
	 * @param targetLib the lib to search
	 * @return
	 */
	public static UIComponent findBsfComponent(UIComponent parent, String targetLib) {
		if (targetLib.equalsIgnoreCase((String) parent.getAttributes().get("library"))) {
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
		if (!viewportParam.isEmpty() && isFalseOrNo(viewportParam)) {
			return;
		}
		if (!viewportParam.isEmpty() && !isTrueOrYes(viewportParam)) {
			content = viewportParam;
		}

		// Otherwise
		String viewportMeta = "<meta name=\"viewport\" content=\"" + content + "\"/>";
		UIOutput viewport = new UIOutput();
		viewport.setRendererType("javax.faces.Text");
		viewport.getAttributes().put("escape", false);
		viewport.setValue(viewportMeta);

		UIComponent header = findHeader(root);
		if (header != null) {
			header.getChildren().add(0, viewport);
		}
	}

	/**
	 * Add the required CSS files and the FontAwesome CDN link.
	 *
	 * @param root The UIViewRoot of the JSF tree.
	 * @param context The current FacesContext
	 */
	private void addCSS(UIViewRoot root, FacesContext context) {
		// The following code is needed to diagnose the warning "Unable to save dynamic
		// action with clientId 'j_id...'"
		// List<UIComponent> r = root.getComponentResources(context, "head");
		// System.out.println("**************");
		// for (UIComponent ava : r) {
		// String name = (String) ava.getAttributes().get("name");
		// System.out.println(ava.getClientId(context) +":" + name + " " +
		// ava.getClass().getSimpleName());
		// }
		// System.out.println("**************");
		// end of the diagnostic code

		// 1) First load Theme files (core.css + theme.css)
		String theme = loadTheme(root, context);

		// deactivate FontAwesome support if the no-fa facet is found in the h:head tag
		UIComponent header = findHeader(root);
		boolean useCDNImportForFontAwesome = (null == header) || (null == header.getFacet("no-fa"));
		if (useCDNImportForFontAwesome) {
			String useCDN = BsfUtils.getInitParam(P_GET_FONTAWESOME_FROM_CDN);
			if (null != useCDN) {
				useCDN = ELTools.evalAsString(useCDN);
			}
			if (null != useCDN) {
				useCDNImportForFontAwesome = !isFalseOrNo(useCDN);
			}
		}

		// Do we have to add font-awesome, or are the resources already there?
		List<UIComponent> availableResources = root.getComponentResources(context, "head");
		for (UIComponent ava : availableResources) {
			String name = (String) ava.getAttributes().get("name");
			if (null != name) {
				name = name.toLowerCase();
				if ((name.contains("font-awesome") || name.contains("fontawesome")) && name.endsWith("css")) {
					useCDNImportForFontAwesome = false;
				}
			}
		}

		LOGGER.log(Level.FINER, "by addCSS - useCDNImportForFontAwesome is {0}", useCDNImportForFontAwesome);

		// 2) Font Awesome
		if (useCDNImportForFontAwesome) {
			InternalFALink output = new InternalFALink();
			Map<String, Object> viewMap = getMapForView(root);
			if (viewMap.containsKey(FONTAWESOME_USED)) {
				String version = (String) viewMap.get(FONTAWESOME_VERSION);
				if (version != null) {
					output.setVersion(version);
				} else {
					output.setVersion("4");
				}
				boolean needsFontAwesome4 = needsFontAwesome4();
				output.setNeedsVersion4(needsFontAwesome4);
				LOGGER.log(Level.FINER, "by addCSS - needsFontAwesome4 is {0}", needsFontAwesome4);
				addResourceIfNecessary(root, context, output);
			}
		}

		@SuppressWarnings("unchecked")
		List<String> extCSSMap = (List<String>) getMapForView(root).get(EXT_RESOURCE_KEY);
		if (extCSSMap != null) {
			for (String file : extCSSMap) {
				String name = "css/" + file;
				createAndAddComponent(root, context, CSS_RENDERER, name, C.BSF_LIBRARY);
			}
		}

		@SuppressWarnings("unchecked")
		List<String> themedCSSMap = (List<String>) getMapForView(root).get(THEME_RESOURCE_KEY);
		if (themedCSSMap != null) {
			for (String file : themedCSSMap) {
				// Glyphicons icons now are in core.css if(file.equals("icons.css")) //the
				// icons.css file isn't found in a theme folder
				// Glyphicons icons now are in core.css name = "css/icons.css"; //look for it
				// under the css root instead
				String name = "css/" + theme + "/" + file;
				createAndAddComponent(root, context, CSS_RENDERER, name, C.BSF_LIBRARY);
			}
		}

		if (theme.equals("patternfly")) {
			createAndAddComponent(root, context, CSS_RENDERER, "css/patternfly/bootstrap-switch.css", C.BSF_LIBRARY);
		}

		// Add mandatory CSS bsf.css
		createAndAddComponent(root, context, CSS_RENDERER, "css/bsf.css", C.BSF_LIBRARY);

		// 3) Bootstrap from CDN (TODO: check removeBootstrapResources)
		boolean loadBootstrap = shouldLibraryBeLoaded(P_GET_BOOTSTRAP_FROM_CDN, true);
		if (!loadBootstrap) {
			removeBootstrapResources(root, context);
		}
	}

	private String loadTheme(UIViewRoot root, FacesContext context) {
		/*
		 * As of v0.8.0 we have two Context Parameters: BootsFaces_USETHEME - as in
		 * previous versions controls if the current theme is to be rendered in the Flat
		 * variant (default) or in its Enhanced variant, with shadows and decorations
		 * turned on. BootsFaces_THEME - controls the Theme to use: the value "default"
		 * is plain Bootstrap, the other options are a Bootswach Theme name (lowercase)
		 * or "custom". If custom is chosen, you will have to provide your custom CSS in
		 * the "other" folder.
		 */

		ResourceHandler rh = context.getApplication().getResourceHandler();

		String theme = getTheme(context);

		// why this line of code???
		boolean hasRemoveFontAwesome = isFontAwesomeComponentUsedAndRemoveIt();

		// Theme loading
		if (hasRemoveFontAwesome || (!theme.equalsIgnoreCase(THEME_NAME_OTHER))) {
			// String filename = "bsf.css";
			String filename = "core.css";
			Resource themeResource = rh.createResource("css/" + theme + "/" + filename, C.BSF_LIBRARY);

			if (themeResource == null) {
				throw new FacesException("Error loading theme, cannot find \"" + "css/" + theme + "/" + filename
					+ "\" resource of \"" + C.BSF_LIBRARY + "\" library");
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

	private String getTheme(FacesContext context) {
		String theme = evalELIfPossible(BsfUtils.getInitParam(C.P_THEME, context));
		if (!theme.isEmpty()) {
			if (theme.equalsIgnoreCase("custom")) {
				theme = THEME_NAME_OTHER;
			}
		} else {
			theme = THEME_NAME_DEFAULT;
		}
		return theme;
	}

	/**
	 * Add the required Javascript files and the FontAwesome CDN link.
	 *
	 * @param root The UIViewRoot of the JSF tree.
	 * @param context The current FacesContext
	 */
	private void addJavascript(UIViewRoot root, FacesContext context) {
		// The following code is needed to diagnose the warning "Unable to save dynamic
		// action with clientId 'j_id...'"
		// List<UIComponent> r = root.getComponentResources(context, "head");
		// System.out.println("**************");
		// for (UIComponent ava : r) {
		// String name = (String) ava.getAttributes().get("name");
		// System.out.println(ava.getClientId(context) +":" + name + " " +
		// ava.getClass().getSimpleName());
		// }
		// System.out.println("**************");
		// end of the diagnostic code

		boolean loadJQuery = shouldLibraryBeLoaded(P_GET_JQUERY_FROM_CDN, true);
		boolean loadJQueryUI = shouldLibraryBeLoaded(P_GET_JQUERYUI_FROM_CDN, true);
		// Do we have to add jQuery, or are the resources already there?
		List<UIComponent> availableResources = root.getComponentResources(context, "head");
		for (UIComponent ava : availableResources) {
			String name = (String) ava.getAttributes().get("name");
			if (null != name) {
				name = name.toLowerCase();
				if ((name.contains("/jquery-ui") || name.startsWith("jquery-ui")) && name.endsWith(".js")) {
					loadJQueryUI = false;
				} else if ((name.contains("/jquery") || name.startsWith("jquery")) && name.endsWith(".js")) {
					loadJQuery = false;
				}
			}
		}

		addMandatoryLibraries(root, context, loadJQuery, loadJQueryUI);

		String blockUI = BsfUtils.getInitParam(P_BLOCK_UI, context);
		if (null != blockUI) {
			blockUI = ELTools.evalAsString(blockUI);
		}
		if (null != blockUI && isTrueOrYes(blockUI)) {
			createAndAddComponent(root, context, SCRIPT_RENDERER, "js/jquery.blockUI.js", C.BSF_LIBRARY);
		}

		removeDuplicateResources(root, context);

		addResourceIfNecessary(root, context, new InternalIE8CompatiblityLinks());

		enforceCorrectLoadOrder(root, context);
	}

	private Map<String, Object> addMandatoryLibraries(UIViewRoot root, FacesContext context, boolean loadJQuery,
		boolean loadJQueryUI) {

		/*
		 * We used to check if a single component needs the bsf.js, jsf or jquery
		 * library. This can be an error prone approach so we add all of them (if not
		 * different specified)
		 */
		createAndAddComponent(root, context, SCRIPT_RENDERER, "jsf.js", "javax.faces");
		createAndAddComponent(root, context, SCRIPT_RENDERER, "js/bsf.js", C.BSF_LIBRARY, "last");

		if (loadJQuery) {
			createAndAddComponent(root, context, SCRIPT_RENDERER, "jq/jquery.js", C.BSF_LIBRARY);
		}

		Map<String, Object> viewMap = getMapForView(root);
		@SuppressWarnings("unchecked")
		Map<String, String> basicResourceMap = (Map<String, String>) viewMap.get(BASIC_JS_RESOURCE_KEY);

		if (null != basicResourceMap) {
			for (Entry<String, String> entry : basicResourceMap.entrySet()) {
				String file = entry.getValue();
				String library = entry.getKey().substring(0, entry.getKey().length() - file.length() - 1);
				if (!"jq/jquery.js".equals(file) || !C.BSF_LIBRARY.equals(library) || !file.startsWith("jq/ui")
					|| loadJQueryUI) {
					createAndAddComponent(root, context, SCRIPT_RENDERER, file, library);
				}
			}
			viewMap.remove(BASIC_JS_RESOURCE_KEY);
		}
		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(RESOURCE_KEY);

		if (null != resourceMap) {
			for (Entry<String, String> entry : resourceMap.entrySet()) {
				String file = entry.getValue();
				String library = entry.getKey().substring(0, entry.getKey().length() - file.length() - 1);
				if (!"jq/jquery.js".equals(file) || !C.BSF_LIBRARY.equals(library) || !file.startsWith("jq/ui")
					|| loadJQueryUI) {
					createAndAddComponent(root, context, SCRIPT_RENDERER, file, library);
				}
			}
			viewMap.remove(RESOURCE_KEY);
		}
		return viewMap;
	}

	private static boolean shouldLibraryBeLoaded(String initParameter, boolean defaultValue) {
		String suppressLibrary = BsfUtils.getInitParam(initParameter);
		if (suppressLibrary == null) {
			return defaultValue;
		}
		return !isTrueOrYes(ELTools.evalAsString(suppressLibrary));
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, InternalIE8CompatiblityLinks output) {
		addResourceIfNecessary(root, context, output, output.getClass());
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, InternalFALink output) {
		addResourceIfNecessary(root, context, output, output.getClass());
	}

	private void addResourceIfNecessary(UIViewRoot root, FacesContext context, UIComponent output, Class<?> clazz) {
		for (UIComponent c : root.getComponentResources(context, "head")) {
			if (c.getClass() == clazz) {
				LOGGER.log(Level.FINER, "by addResourceIfNecessary - find existing class {0}", clazz);

				// remove old
				root.removeComponentResource(context, c, "head");

				// add new
				root.addComponentResource(context, output, "head");
				return;
			}
		}
		// resource not found yet, so add it now
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
					LOGGER.log(Level.FINEST, "by addResourceIfNecessary - find existing {0} - {1}", new Object[]{name, library});
					return;
				}
			}
		}
		root.addComponentResource(context, output, "head");
	}

	public static void addResourceIfNecessary(String url) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();
		if (url.endsWith(".css")) {
			for (UIComponent c : root.getComponentResources(context, "head")) {
				if (c instanceof InternalCssScriptResource) {
					if (((InternalCssScriptResource) c).getUrl().equals(url)) {
						return;
					}
				}
			}
			InternalCssScriptResource urlComponent = new InternalCssScriptResource();
			urlComponent.setUrl(url);
			root.addComponentResource(context, urlComponent, "head");
		} else {
			for (UIComponent c : root.getComponentResources(context, "head")) {
				if (c instanceof InternalJavaScriptResource) {
					if (((InternalJavaScriptResource) c).getUrl().equals(url)) {
						return;
					}
				}
			}
			InternalJavaScriptResource urlComponent = new InternalJavaScriptResource();
			urlComponent.setUrl(url);
			root.addComponentResource(context, urlComponent, "head");
		}
	}

	public static void setFontAwesomeVersion(int version, Object uiComponent) {
		Map<String, Object> viewMap = getMapForView(FacesContext.getCurrentInstance().getViewRoot());

		if (version != 4) {
			@SuppressWarnings("unchecked")
			Map<Object, Boolean> v5 = (Map<Object, Boolean>) viewMap.get(FONTAWESOME_NEW_VERSION);
			if (null == v5) {
				v5 = new HashMap<>();
				viewMap.put(FONTAWESOME_NEW_VERSION, v5);
			}
			v5.put(uiComponent, true);
		}

		String _version = (String) viewMap.get(FONTAWESOME_VERSION);
		if (_version != null) {
			if (!_version.contains(String.valueOf(version))) {
				viewMap.put(FONTAWESOME_VERSION, String.valueOf(_version + "," + version));
			}
		} else {
			if (viewMap.containsKey(FONTAWESOME_USED)) {
				viewMap.put(FONTAWESOME_VERSION, "4," + String.valueOf(version));
			} else {
				viewMap.put(FONTAWESOME_VERSION, String.valueOf(version));
			}
		}

		LOGGER.log(Level.FINER, "by setFontAwesomeVersion - viewMap(FONTAWESOME_VERSION) is {0}",
			viewMap.get(FONTAWESOME_VERSION));
	}

	@SuppressWarnings("unchecked")
	public static void setNeedsFontsAwesome(Object uiComponent) {
		Map<String, Object> viewMap = getMapForView(FacesContext.getCurrentInstance().getViewRoot());
		ArrayList<Object> list = (ArrayList<Object>) viewMap.get(FONTAWESOME_USED);
		if (list == null) {
			list = new ArrayList<>();
			viewMap.put(FONTAWESOME_USED, list);
		}
		list.add(uiComponent);
	}

	private boolean needsFontAwesome4() {
		Map<String, Object> viewMap = getMapForView(FacesContext.getCurrentInstance().getViewRoot());
		@SuppressWarnings("unchecked")
		Map<Object, Boolean> v5 = (Map<Object, Boolean>) viewMap.get(FONTAWESOME_NEW_VERSION);
		@SuppressWarnings("unchecked")
		ArrayList<Object> all = (ArrayList<Object>) viewMap.get(FONTAWESOME_USED);
		viewMap.remove(FONTAWESOME_NEW_VERSION);
		viewMap.remove(FONTAWESOME_USED);
		if (all == null) {
			return false;
		}
		if (v5 == null) {
			all.clear();
			return true;
		}
		boolean v4 = false;
		for (Object o : all) {
			if (!v5.containsKey(o)) {
				v4 = true;
				break;
			}
		}
		all.clear();
		v5.clear();
		return v4;
	}

	/**
	 * Remove duplicate resource files. For some reason, many resource files are added more than once, especially
	 * when AJAX is used. The method removes the duplicate files.
	 *
	 * TODO: Verify if the duplicate resource files are a bug of BootsFaces or of the Mojarra library itself.
	 *
	 * @param root The current UIViewRoot
	 * @param context The current FacesContext
	 */
	private void removeDuplicateResources(UIViewRoot root, FacesContext context) {
		List<UIComponent> resourcesToRemove = new ArrayList<>();
		Map<String, UIComponent> alreadyThere = new HashMap<>();
		List<UIComponent> components = new ArrayList<>(root.getComponentResources(context, "head"));
		Collections.sort(components, (UIComponent o1, UIComponent o2) -> o1.getClientId().compareTo(o2.getClientId()));
		for (UIComponent resource : components) {
			String name = (String) resource.getAttributes().get("name");
			String library = (String) resource.getAttributes().get("library");
			String url = (String) resource.getAttributes().get("url");
			String key;
			if (null != url) {
				key = url;
			} else {
				key = library + "/" + name + "/" + resource.getClass().getName();
			}
			if (alreadyThere.containsKey(key)) {
				resourcesToRemove.add(resource);
				continue;
			}
			alreadyThere.put(key, resource);
		}
		for (UIComponent c : resourcesToRemove) {
			c.setInView(false);
			root.removeComponentResource(context, c);

			String name = (String) c.getAttributes().get("name");
			String library = (String) c.getAttributes().get("library");
			String url = (String) c.getAttributes().get("url");
			LOGGER.log(Level.FINER, "-1 " + c.getClientId() + " " + name + " " + library + " " + url + " "
				+ c.getClass().getSimpleName());
		}
	}

	/**
	 * Remove Bootstrap CSS files (called if the context param "net.bootsfaces.get_bootstrap_from_cdn" is set). Only
	 * needed for Default Theme (Bootstrap) and custom themes, other themes would get default theme look and feel
	 * otherwise.
	 *
	 * @param root The current UIViewRoot
	 * @param context The current FacesContext
	 */
	private void removeBootstrapResources(UIViewRoot root, FacesContext context) {
		String theme = getTheme(context);

		if (theme.equals(THEME_NAME_DEFAULT) || theme.equals(THEME_NAME_OTHER)) {
			root.getComponentResources(context, "head").forEach((resource) -> {
				String name = (String) resource.getAttributes().get("name");
				String library = (String) resource.getAttributes().get("library");
				if (C.BSF_LIBRARY.equals(library) && name != null) {
					if (name.endsWith("bootstrap.min.css") || name.endsWith("bootstrap.css")
						|| name.endsWith("core.css") || name.endsWith("theme.css")) {
						resource.setInView(false);
						root.removeComponentResource(context, resource);
					}
				}
			});
		}
		/*
		 * List<UIComponent> resourcesToRemove = new ArrayList<UIComponent>(); for
		 * (UIComponent resource : root.getComponentResources(context, "head")) { String
		 * name = (String) resource.getAttributes().get("name"); String library =
		 * (String) resource.getAttributes().get("library"); if ("bsf".equals(library)
		 * && name != null && name.endsWith(".css")) { resourcesToRemove.add(resource);
		 * } } for (UIComponent c : resourcesToRemove) { c.setInView(false);
		 * root.removeComponentResource(context, c); //System.out.println("-2" +
		 * c.getClientId()); }
		 */
	}

	/**
	 * Make sure jQuery is loaded before jQueryUI, and that every other Javascript is loaded later. Also make sure
	 * that the BootsFaces resource files are loaded prior to other resource files, giving the developer the
	 * opportunity to overwrite a CSS or JS file.
	 *
	 * @param root The current UIViewRoot
	 * @param context The current FacesContext
	 */
	private void enforceCorrectLoadOrder(UIViewRoot root, FacesContext context) {
		// // first, handle the CSS files.
		// // Put BootsFaces.css or BootsFaces.min.css first,
		// // theme.css second
		// // and everything else behind them.
		List<UIComponent> resources = new ArrayList<>();
		List<UIComponent> first = new ArrayList<>();
		List<UIComponent> middle = new ArrayList<>();
		List<UIComponent> last = new ArrayList<>();
		List<UIComponent> datatable = new ArrayList<>();

		root.getComponentResources(context, "head").forEach((UIComponent resource) -> {
			String name = (String) resource.getAttributes().get("name");

			String position = (String) resource.getAttributes().get("position");
			if ("first".equals(position)) {
				first.add(resource);
			} else if ("last".equals(position)) {
				last.add(resource);
			} else if ("middle".equals(position)) {
				middle.add(resource);
			} else {
				if (resource instanceof InternalJavaScriptResource) {
					datatable.add(resource);
				} else if (name != null && (name.endsWith(".js"))) {
					if (name.contains("dataTables")) {
						datatable.add(resource);
					} else {
						resources.add(resource);
					}
				}
			}
		});

		// add the JavaScript files in correct order.
		Collections.sort(resources, new ResourceFileComparator());

		first.forEach((c) -> {
			root.removeComponentResource(context, c);
		});
		resources.forEach((c) -> {
			root.removeComponentResource(context, c);
		});
		last.forEach((c) -> {
			root.removeComponentResource(context, c);
		});
		datatable.forEach((c) -> {
			root.removeComponentResource(context, c);
		});

		// for (UIComponent resource : root.getComponentResources(context, "head")) {
		// System.out.println(resource.getClass().getName());
		// }
		root.getComponentResources(context, "head").forEach((c) -> {
			middle.add(c);
		});
		middle.forEach((c) -> {
			root.removeComponentResource(context, c);
		});

		first.forEach((c) -> {
			root.addComponentResource(context, c, "head");
		});
		middle.forEach((c) -> {
			root.addComponentResource(context, c, "head");
		});
		resources.forEach((c) -> {
			root.addComponentResource(context, c, "head");
		});
		last.forEach((c) -> {
			root.addComponentResource(context, c, "head");
		});
		datatable.forEach((c) -> {
			root.addComponentResource(context, c, "head");
		});
	}

	/**
	 * Look whether a b:iconAwesome component is used. If so, the font-awesome.css is removed from the resource list
	 * because it's loaded from the CDN.
	 *
	 * @return true, if the font-awesome.css is found in the resource list. Note the side effect of this method!
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
				if (name.endsWith("font-awesome.css")) {
					fontAwesomeResource = resource;
				}
			}
		}
		if (null != fontAwesomeResource) {
			fontAwesomeResource.setInView(false);
			viewRoot.removeComponentResource(fc, fontAwesomeResource);
			return true;
		}
		return false;
	}

	/**
	 * Looks for the header in the JSF tree.
	 *
	 * @param root The root of the JSF tree.
	 * @return null, if the head couldn't be found.
	 */
	private UIComponent findHeader(UIViewRoot root) {
		for (UIComponent c : root.getChildren()) {
			if (c instanceof HtmlHead) {
				return c;
			}
		}
		for (UIComponent c : root.getChildren()) {
			if (c instanceof HtmlBody) {
				return null;
			}
			if (c instanceof UIOutput) {
				if (c.getFacets() != null) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * Registers a JS file that needs to be included in the header of the HTML file, but after jQuery and AngularJS.
	 *
	 * @param library The name of the sub-folder of the resources folder.
	 * @param resource The name of the resource file within the library folder.
	 */
	public static void addResourceToHeadButAfterJQuery(String library, String resource) {
		addResource(resource, library, library + "#" + resource, RESOURCE_KEY);
	}

	/**
	 * Registers a core JS file that needs to be included in the header of the HTML file, but after jQuery and
	 * AngularJS.
	 *
	 * @param library The name of the sub-folder of the resources folder.
	 * @param resource The name of the resource file within the library folder.
	 */
	public static void addBasicJSResource(String library, String resource) {
		addResource(resource, library, resource, BASIC_JS_RESOURCE_KEY);
	}

	/**
	 * Registers a themed CSS file that needs to be included in the header of the HTML file.
	 *
	 * @param resource The name of the resource file within the library folder.
	 */
	public static void addThemedCSSResource(String resource) {
		Map<String, Object> viewMap = getMapForView(FacesContext.getCurrentInstance().getViewRoot());
		@SuppressWarnings("unchecked")
		List<String> resourceList = (List<String>) viewMap.get(THEME_RESOURCE_KEY);
		if (null == resourceList) {
			resourceList = new ArrayList<>();
			viewMap.put(THEME_RESOURCE_KEY, resourceList);
		}

		if (!resourceList.contains(resource)) {
			resourceList.add(resource);
		}
	}

	/**
	 * Registers a Extension CSS file that needs to be included in the header of the HTML file.
	 *
	 * @param resource The name of the resource file within the library folder.
	 */
	public static void addExtCSSResource(String resource) {
		Map<String, Object> viewMap = getMapForView(FacesContext.getCurrentInstance().getViewRoot());
		@SuppressWarnings("unchecked")
		List<String> resourceList = (List<String>) viewMap.get(EXT_RESOURCE_KEY);
		if (null == resourceList) {
			resourceList = new ArrayList<>();
			viewMap.put(EXT_RESOURCE_KEY, resourceList);
		}

		if (!resourceList.contains(resource)) {
			resourceList.add(resource);
		}
	}

	private static void addResource(String resource, String library, String resourceKey, String resourceTypeKey) {
		Map<String, Object> viewMap = getMapForView(FacesContext.getCurrentInstance().getViewRoot());
		@SuppressWarnings("unchecked")
		Map<String, String> resourceMap = (Map<String, String>) viewMap.get(resourceTypeKey);
		if (null == resourceMap) {
			resourceMap = new HashMap<>();
			viewMap.put(resourceTypeKey, resourceMap);
		}

		if (!resourceMap.containsKey(resourceKey)) {
			resourceMap.put(library + "#" + resource, resource);
		}
	}

	private String evalELIfPossible(String expression) {
		if (expression != null) {
			expression = ELTools.evalAsString(expression).trim();
		} else {
			expression = "";
		}
		return expression;
	}

	private void createAndAddComponent(UIViewRoot root, FacesContext context, String rendererType, String name,
		String library) {
		createAndAddComponent(root, context, rendererType, name, library, null);
	}

	private void createAndAddComponent(UIViewRoot root, FacesContext context, String rendererType, String name,
		String library, String position) {

		// if (library != null && BSF_LIBRARY.equals(library)) {
		// boolean loadBsfResource =
		// shouldLibraryBeLoaded(P_GET_BOOTSTRAP_COMPONENTS_FROM_CDN, true);
		//
		// if (! loadBsfResource) {
		// return;
		// }
		// }
		UIOutput output = new UIOutput();
		output.setRendererType(rendererType);
		output.getAttributes().put("name", name);
		output.getAttributes().put("library", library);
		output.getAttributes().put("target", "head");
		if (position != null) {
			output.getAttributes().put("position", position);
		}
		addResourceIfNecessary(root, context, output);
	}

	private static boolean isTrueOrYes(String param) {
		return param.equalsIgnoreCase("true") || param.equalsIgnoreCase("yes");
	}

	private boolean isFalseOrNo(String param) {
		return param.equalsIgnoreCase("false") || param.equalsIgnoreCase("no");
	}

	/**
	 * Add the default datatables.net resource if and only if the user doesn't bring their own copy, and if they
	 * didn't disallow it in the web.xml by setting the context paramter net.bootsfaces.get_datatable_from_cdn to
	 * true.
	 *
	 * @param defaultFilename The URL of the file to be loaded
	 * @param type either "js" or "css"
	 */
	public static void addDatatablesResourceIfNecessary(String defaultFilename, String type) {
		boolean loadDatatables = shouldLibraryBeLoaded(P_GET_DATATABLE_FROM_CDN, true);
		// Do we have to add datatables.min.{css|js}, or are the resources already
		// there?
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();

		String[] positions = {"head", "body", "form"};
		for (String position : positions) {
			if (loadDatatables) {
				List<UIComponent> availableResources = root.getComponentResources(context, position);
				for (UIComponent ava : availableResources) {
					if (ava.isRendered()) {
						String name = (String) ava.getAttributes().get("name");
						if (null != name) {
							name = name.toLowerCase();
							if (name.contains("datatables") && name.endsWith("." + type)) {
								loadDatatables = false;
								break;
							}
						}
					}
				}
			}
		}
		if (loadDatatables) {
			addResourceIfNecessary(defaultFilename);
		}
	}
}
