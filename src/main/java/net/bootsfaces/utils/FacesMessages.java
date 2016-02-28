/**
 *  Copyright 2014-2016 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.utils;

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
	 * @param message The message.
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
	 * @param message The message.
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
	 * @param message The message.
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
	 * @param message The message.
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
	
}
