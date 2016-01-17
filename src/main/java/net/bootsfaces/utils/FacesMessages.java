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
	public static void fatal(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void error(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void warning(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void info(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void fatal(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void error(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void warning(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void info(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
