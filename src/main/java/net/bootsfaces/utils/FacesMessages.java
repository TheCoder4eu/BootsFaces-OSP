/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Simplified reporting of errors, warning or informations to the user of our
 * JSF application.
 * 
 * @author stephan
 *
 */
public class FacesMessages {
	/**
	 * Adds a FATAL message.
	 *
	 * @param detail The message.
	 */
	public static void fatal(String detail) {
		fatal("", detail);
	}
	public static void fatal(String summary, String detail) {
		fatal(null, summary, detail);
	}
	public static void fatal(String refItem, String summary, String detail) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(refItem, facesMsg);
	}
	
	/**
	 * Adds a ERROR message.
	 *
	 * @param detail The message.
	 */
	public static void error(String detail) {
		error("", detail);
	}
	public static void error(String summary, String detail) {
		error(null, summary, detail);
	}
	public static void error(String refItem, String summary, String detail) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(refItem, facesMsg);
	}
	
	/**
	 * Adds a WARNING message.
	 *
	 * @param detail The message.
	 */
	public static void warning(String detail) {
		warning("", detail);
	}
	public static void warning(String summary, String detail) {
		warning(null, summary, detail);
	}
	public static void warning(String refItem, String summary, String detail) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(refItem, facesMsg);
	}
	
	/**
	 * Adds an INFO message.
	 *
	 * @param detail The message.
	 */
	public static void info(String detail) {
		info("", detail);
	}
	public static void info(String summary, String detail) {
		info(null, summary, detail);
	}
	public static void info(String refItem, String summary, String detail) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(refItem, facesMsg);
	}
	
	/** copied from the JSF API (because the original method is not publicly visible) */
	protected static ClassLoader getCurrentLoader(Class<?> fallbackClass) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = fallbackClass.getClassLoader();
		}
		return loader;
	}
	
	/** slightly simplified version of the corresponding method of the JSF API (because the original method
	 * is not publicly visible)
	 * @param messageId The id of the error message in the message bundle
	 * @param label The label of the input field
	 */
	public static void createErrorMessageFromResourceBundle(String clientId, String messageId, String label) {
		String summary = null;
		String detail = null;
		ResourceBundle bundle;
		String bundleName;

		// see if we have a user-provided bundle
		Application app = FacesContext.getCurrentInstance().getApplication();
		Class<?> appClass = app.getClass();
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		if (null != (bundleName = app.getMessageBundle())) {
			if (null != (bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentLoader(appClass)))) {
				// see if we have a hit
				try {
					summary = bundle.getString(messageId);
					detail = bundle.getString(messageId + "_detail");
				} catch (MissingResourceException e) {
					// ignore
				}
			}
		}

		// we couldn't find a summary in the user-provided bundle
		if (null == summary) {
			// see if we have a summary in the app provided bundle
			bundle = ResourceBundle.getBundle(FacesMessage.FACES_MESSAGES, locale, getCurrentLoader(appClass));
			if (null == bundle) {
				throw new NullPointerException();
			}
			// see if we have a hit
			try {
				summary = bundle.getString(messageId);
				detail = bundle.getString(messageId + "_detail");
			} catch (MissingResourceException e) {
				// ignore
			}
		}

		summary = summary != null ? summary.replace("{0}", label) : "";
		detail = detail != null ? detail.replace("{0}", label) : "";

		// At this point, we have a summary and a bundle.
		FacesMessages.error(clientId, summary, detail);
	}
	
}
