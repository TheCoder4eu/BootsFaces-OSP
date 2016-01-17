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

package net.bootsfaces.component.message;

import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIMessage;

import net.bootsfaces.component.AttributeMapWrapper;


/** This class holds the attributes of &lt;b:message /&gt;. */
@ResourceDependencies({
	@ResourceDependency(library="bsf", name="css/core.css", target="head"),
        @ResourceDependency(library="bsf", name="css/alerts.css", target="head"),
        @ResourceDependency(library="bsf", name="js/alert.js", target="body"),
        @ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head"),
        @ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head")
})
@FacesComponent("net.bootsfaces.component.message.Message")
public class Message extends UIMessage  {
	
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.message.Message";
	
	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";
	
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.message.Message";

	private Map<String, Object> attributes;
	
	public Message() {
		
		
		setRendererType(DEFAULT_RENDERER);
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(this, super.getAttributes());
		return attributes;
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	
    protected enum PropertyKeys {
dir,
errorClass,
errorStyle,
fatalClass,
fatalStyle,
infoClass,
infoStyle,
showDetail,
showIcon,
showSummary,
redisplay,
style,
styleClass,
warnClass,
warnStyle
;

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
	 * HTML: The direction of text display, either 'ltr' (left-to-right) or 'rtl' (right-to-left). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDir() {
		String value = (String)getStateHelper().eval(PropertyKeys.dir);
		return  value;
	}
	
	/**
	 * HTML: The direction of text display, either 'ltr' (left-to-right) or 'rtl' (right-to-left). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
	    getStateHelper().put(PropertyKeys.dir, _dir);
    }
	

	/**
	 * CSS class to be used for messages with severity "ERROR". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getErrorClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.errorClass);
		return  value;
	}
	
	/**
	 * CSS class to be used for messages with severity "ERROR". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setErrorClass(String _errorClass) {
	    getStateHelper().put(PropertyKeys.errorClass, _errorClass);
    }
	

	/**
	 * CSS style to be used for messages with severity "ERROR". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getErrorStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.errorStyle);
		return  value;
	}
	
	/**
	 * CSS style to be used for messages with severity "ERROR". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setErrorStyle(String _errorStyle) {
	    getStateHelper().put(PropertyKeys.errorStyle, _errorStyle);
    }
	

	/**
	 * CSS class to be used for messages with severity "FATAL". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFatalClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.fatalClass);
		return  value;
	}
	
	/**
	 * CSS class to be used for messages with severity "FATAL". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFatalClass(String _fatalClass) {
	    getStateHelper().put(PropertyKeys.fatalClass, _fatalClass);
    }
	

	/**
	 * CSS style to be used for messages with severity "FATAL". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFatalStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.fatalStyle);
		return  value;
	}
	
	/**
	 * CSS style to be used for messages with severity "FATAL". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFatalStyle(String _fatalStyle) {
	    getStateHelper().put(PropertyKeys.fatalStyle, _fatalStyle);
    }
	

	/**
	 * CSS class to be used for messages with severity "INFO". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getInfoClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.infoClass);
		return  value;
	}
	
	/**
	 * CSS class to be used for messages with severity "INFO". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInfoClass(String _infoClass) {
	    getStateHelper().put(PropertyKeys.infoClass, _infoClass);
    }
	

	/**
	 * CSS style to be used for messages with severity "INFO". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getInfoStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.infoStyle);
		return  value;
	}
	
	/**
	 * CSS style to be used for messages with severity "INFO". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInfoStyle(String _infoStyle) {
	    getStateHelper().put(PropertyKeys.infoStyle, _infoStyle);
    }
	

	/**
	 * Specifies whether the detailed information from the message should be shown. Default to false. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowDetail() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.showDetail, true);
		return (boolean) value;
	}
	
	/**
	 * Specifies whether the detailed information from the message should be shown. Default to false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowDetail(boolean _showDetail) {
	    getStateHelper().put(PropertyKeys.showDetail, _showDetail);
    }
	

	/**
	 * If false, the icon of the message is not show. Default = true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowIcon() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.showIcon, true);
		return (boolean) value;
	}
	
	/**
	 * If false, the icon of the message is not show. Default = true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowIcon(boolean _showIcon) {
	    getStateHelper().put(PropertyKeys.showIcon, _showIcon);
    }
	

	/**
	 * Specifies whether the summary information from the message should be shown. Defaults to true. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowSummary() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.showSummary, true);
		return (boolean) value;
	}
	
	/**
	 * Specifies whether the summary information from the message should be shown. Defaults to true. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowSummary(boolean _showSummary) {
	    getStateHelper().put(PropertyKeys.showSummary, _showSummary);
    }
	

	/**
	 * Flag indicating whether previously handled messages are redisplayed or not <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isRedisplay() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.redisplay, true);
		return (boolean) value;
	}
	
	/**
	 * Flag indicating whether previously handled messages are redisplayed or not <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRedisplay(boolean _redisplay) {
	    getStateHelper().put(PropertyKeys.redisplay, _redisplay);
    }
	

	/**
	 * HTML: CSS styling instructions. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.style);
		return  value;
	}
	
	/**
	 * HTML: CSS styling instructions. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
	    getStateHelper().put(PropertyKeys.style, _style);
    }
	

	/**
	 * The CSS class for this element.  Corresponds to the HTML 'class' attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.styleClass);
		return  value;
	}
	
	/**
	 * The CSS class for this element.  Corresponds to the HTML 'class' attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
	    getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }
	

	/**
	 * CSS class to be used for messages with severity "WARN". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWarnClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.warnClass);
		return  value;
	}
	
	/**
	 * CSS class to be used for messages with severity "WARN". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWarnClass(String _warnClass) {
	    getStateHelper().put(PropertyKeys.warnClass, _warnClass);
    }
	

	/**
	 * CSS style to be used for messages with severity "WARN". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWarnStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.warnStyle);
		return  value;
	}
	
	/**
	 * CSS style to be used for messages with severity "WARN". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWarnStyle(String _warnStyle) {
	    getStateHelper().put(PropertyKeys.warnStyle, _warnStyle);
    }
	
}

