/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component.fetchBeanInfos;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import net.bootsfaces.C;
import net.bootsfaces.render.A;
import net.bootsfaces.utils.BsfUtils;

/**
 * The &lt;alert&gt; tag generates a colored box that can be used to display
 * error messages, warnings, informations or simply success messages.
 * 
 * @author thecoder4eu
 */

@FacesComponent("net.bootsfaces.component.fetchBeanInfos.FetchBeanInfos")
public class FetchBeanInfos extends UIComponentBase {

	/**
	 * <p>
	 * The standard component type for this component.
	 * </p>
	 */
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.fetchBeanInfos.FetchBeanInfos";
	/**
	 * <p>
	 * The component family for this component.
	 * </p>
	 */
	public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

	public FetchBeanInfos() {
		setRendererType(null); // this component renders itself
	}


	/**
	 * Provide support to snake-case attribute in EL-expression items
	 */
	@Override
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	protected enum PropertyKeys {
		validationSeverity,
		severityLevel;
		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	/**
	 * validation facesContext.maximumSeverity. Default to "false" <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isValidationSeverity() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.validationSeverity, false);
	}

	/**
	 * validation facesContext.maximumSeverity. Default to "false" <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setValidationSeverity(boolean _validationSeverity) {
		getStateHelper().put(PropertyKeys.validationSeverity, _validationSeverity);
	}

	/**
	 * What Severity level to be Failed? Possible values: "info", "warn", "error" and "fatal" . Default to "error". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSeverityLevel() {
		return (String) getStateHelper().eval(PropertyKeys.severityLevel,"error");
	}

	/**
	 * What Severity level to be Failed? Possible values: "info", "warn", "error" and "fatal" . Default to "error". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSeverityLevel(String _severityLevel) {
		getStateHelper().put(PropertyKeys.severityLevel, _severityLevel);
	}



	@Override
	public void encodeBegin(FacesContext fc) throws IOException {
		ResponseWriter rw = fc.getResponseWriter();
		Map<String, Object> attrs = this.getAttributes();
		boolean validationSeverity = A.toBool(attrs.get("validationSeverity"));
		String severityLevel = A.asString(attrs.get("severityLevel"));

		boolean validationFailed;
		if (fc.isValidationFailed()) {
			validationFailed = true;
		} else if (validationSeverity && fc.getMaximumSeverity() != null && severityLevel != null){

			int severityOrdinal;
			if  ("info".equalsIgnoreCase(severityLevel)) {
				severityOrdinal = FacesMessage.SEVERITY_INFO.getOrdinal();
			} else if ("warn".equalsIgnoreCase(severityLevel)){
				severityOrdinal = FacesMessage.SEVERITY_WARN.getOrdinal();
			} else if ("error".equalsIgnoreCase(severityLevel)){
				severityOrdinal = FacesMessage.SEVERITY_ERROR.getOrdinal();
			} else if ("fatal".equalsIgnoreCase(severityLevel)){
				severityOrdinal = FacesMessage.SEVERITY_FATAL.getOrdinal();
			}else{
				throw new FacesException("severityLevel not define:" + severityLevel);
			}
			validationFailed = fc.getMaximumSeverity().getOrdinal() >= severityOrdinal;
		}else{
			validationFailed = false;
		}
		rw.startElement("script", this);
		rw.writeText("validationFailed=" + validationFailed + ";", null);
		rw.endElement("script");

	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
