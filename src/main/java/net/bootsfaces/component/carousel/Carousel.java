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

package net.bootsfaces.component.carousel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.component.behavior.ClientBehaviorHolder;

import net.bootsfaces.C;
import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.Tooltip;

/** This class holds the attributes of &lt;b:carousel /&gt;. */
@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
		@ResourceDependency(library = "bsf", name = "css/carousel.css", target = "head"),
		@ResourceDependency(library = "javax.faces", name = "jsf.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/bsf.js", target = "head"),
		@ResourceDependency(library = "bsf", name = "js/carousel.js", target = "head"),
		/* moved to constructor @ResourceDependency(library = "bsf", name = "jq/ui/core.js", target = "body"), */
		@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
@FacesComponent("net.bootsfaces.component.carousel.Carousel")
public class Carousel extends UICommand implements net.bootsfaces.render.IHasTooltip,IAJAXComponent, ClientBehaviorHolder {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.carousel.Carousel";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.carousel.Carousel";

	private Map<String, Object> attributes;

	public Carousel() {
		Tooltip.addResourceFile();
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/ui/core.js");
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

	private static final Collection<String> EVENT_NAMES = Collections.unmodifiableCollection(Arrays.asList("click",
			"dblclick", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "slidestart", "slid"));
	
    /**
     * returns the subset of AJAX requests that are implemented by jQuery callback or other non-standard means
     * (such as the onclick event of b:tabView, which has to be implemented manually).Ø
     * @return
     */
    public Map<String, String> getJQueryEvents() {
    	Map<String, String> result= new HashMap<String, String>();
    	result.put("slidestart", "slide.bs.carousel");
    	result.put("slid", "slid.bs.carousel");
    	return result;
    }


	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	public String getDefaultEventName() {
		return "click";
	}

	protected enum PropertyKeys {
		disabled, activeIndex, ajax, interval, pause, wrap, onclick, oncomplete, ondblclick, onmousedown, onmousemove, onmouseout, onmouseover, onmouseup, onslidestart, onslid, process, slide, startAnimation, showControls, showIndicators, style, styleClass, tooltip, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, update, tooltipContainer;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering error in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipContainer() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
		return  value;
	}
	
	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering error in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
	    getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
    }
	/**
	 * Boolean value to specify if the button is disabled. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.disabled, false);
		return (boolean) value;
	}
	
	/**
	 * Boolean value to specify if the button is disabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
	    getStateHelper().put(PropertyKeys.disabled, _disabled);
    }
	

	/**
	 * Optional attribute to define which tab is active initially. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getActiveIndex() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.activeIndex, 0);
		return (int) value;
	}
	
	/**
	 * Optional attribute to define which tab is active initially. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setActiveIndex(int _activeIndex) {
	    getStateHelper().put(PropertyKeys.activeIndex, _activeIndex);
    }
	

	/**
	 * Whether the Button submits the form with AJAX. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isAjax() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.ajax, false);
		return (boolean) value;
	}
	
	/**
	 * Whether the Button submits the form with AJAX. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAjax(boolean _ajax) {
	    getStateHelper().put(PropertyKeys.ajax, _ajax);
    }
	

	/**
	 * Specifies the delay (in milliseconds) between each slide. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getInterval() {
		String value = (String)getStateHelper().eval(PropertyKeys.interval, "5000");
		return  value;
	}
	
	/**
	 * Specifies the delay (in milliseconds) between each slide. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setInterval(String _interval) {
	    getStateHelper().put(PropertyKeys.interval, _interval);
    }
	

	/**
	 * Pauses the carousel from going through the next slide when the mouse pointer enters the carousel, and resumes the sliding when the mouse pointer leaves the carousel. Set pause to false to stop the ability to pause on hover. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPause() {
		String value = (String)getStateHelper().eval(PropertyKeys.pause, "hover");
		return  value;
	}
	
	/**
	 * Pauses the carousel from going through the next slide when the mouse pointer enters the carousel, and resumes the sliding when the mouse pointer leaves the carousel. Set pause to false to stop the ability to pause on hover. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPause(String _pause) {
	    getStateHelper().put(PropertyKeys.pause, _pause);
    }
	

	/**
	 * Specifies whether the carousel should go through all slides continuously, or stop at the last slide. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isWrap() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.wrap, true);
		return (boolean) value;
	}
	
	/**
	 * Specifies whether the carousel should go through all slides continuously, or stop at the last slide. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWrap(boolean _wrap) {
	    getStateHelper().put(PropertyKeys.wrap, _wrap);
    }
	

	/**
	 * The onclick attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnclick() {
		String value = (String)getStateHelper().eval(PropertyKeys.onclick);
		return  value;
	}
	
	/**
	 * The onclick attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
	    getStateHelper().put(PropertyKeys.onclick, _onclick);
    }
	

	/**
	 * Javascript to be executed when ajax completes with success. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOncomplete() {
		String value = (String)getStateHelper().eval(PropertyKeys.oncomplete);
		return  value;
	}
	
	/**
	 * Javascript to be executed when ajax completes with success. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncomplete(String _oncomplete) {
	    getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
    }
	

	/**
	 * Client side callback to execute when input element is double clicked. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndblclick() {
		String value = (String)getStateHelper().eval(PropertyKeys.ondblclick);
		return  value;
	}
	
	/**
	 * Client side callback to execute when input element is double clicked. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndblclick(String _ondblclick) {
	    getStateHelper().put(PropertyKeys.ondblclick, _ondblclick);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousedown() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmousedown);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is pressed down over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousedown(String _onmousedown) {
	    getStateHelper().put(PropertyKeys.onmousedown, _onmousedown);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is moved within input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmousemove() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmousemove);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is moved within input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmousemove(String _onmousemove) {
	    getStateHelper().put(PropertyKeys.onmousemove, _onmousemove);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is moved away from input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseout() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmouseout);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is moved away from input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseout(String _onmouseout) {
	    getStateHelper().put(PropertyKeys.onmouseout, _onmouseout);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is moved onto input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseover() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmouseover);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is moved onto input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseover(String _onmouseover) {
	    getStateHelper().put(PropertyKeys.onmouseover, _onmouseover);
    }
	

	/**
	 * Client side callback to execute when a pointer input element is released over input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnmouseup() {
		String value = (String)getStateHelper().eval(PropertyKeys.onmouseup);
		return  value;
	}
	
	/**
	 * Client side callback to execute when a pointer input element is released over input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnmouseup(String _onmouseup) {
	    getStateHelper().put(PropertyKeys.onmouseup, _onmouseup);
    }
	

	/**
	 * Client side callback to execute when the carousel start to slide to another item. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnslidestart() {
		String value = (String)getStateHelper().eval(PropertyKeys.onslidestart);
		return  value;
	}
	
	/**
	 * Client side callback to execute when the carousel start to slide to another item. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnslidestart(String _onslidestart) {
	    getStateHelper().put(PropertyKeys.onslidestart, _onslidestart);
    }
	

	/**
	 * Client side callback to execute when the carousel just finished sliding to another item. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnslid() {
		String value = (String)getStateHelper().eval(PropertyKeys.onslid);
		return  value;
	}
	
	/**
	 * Client side callback to execute when the carousel just finished sliding to another item. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnslid(String _onslid) {
	    getStateHelper().put(PropertyKeys.onslid, _onslid);
    }
	
	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getProcess() {
		String value = (String)getStateHelper().eval(PropertyKeys.process);
		return  value;
	}
	
	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setProcess(String _process) {
	    getStateHelper().put(PropertyKeys.process, _process);
    }

	/**
	 * Tells Bootstrap to show new items by sliding them into view <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isSlide() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.slide, true);
		return (boolean) value;
	}
	
	/**
	 * Tells Bootstrap to show new items by sliding them into view <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSlide(boolean _slide) {
	    getStateHelper().put(PropertyKeys.slide, _slide);
    }

	/**
	 * tells Bootstrap to begin animating the carousel immediately when the page loads. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isStartAnimation() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.startAnimation, true);
		return (boolean) value;
	}
	
	/**
	 * tells Bootstrap to begin animating the carousel immediately when the page loads. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStartAnimation(boolean _startAnimation) {
	    getStateHelper().put(PropertyKeys.startAnimation, _startAnimation);
    }
	

	/**
	 * adds 'left' and 'right' buttons that allows the user to go back and forth between the slides manually <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowControls() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.showControls, true);
		return (boolean) value;
	}
	
	/**
	 * adds 'left' and 'right' buttons that allows the user to go back and forth between the slides manually <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowControls(boolean _showControls) {
	    getStateHelper().put(PropertyKeys.showControls, _showControls);
    }
	

	/**
	 * The indicators are the little dots at the bottom of each slide (which indicates how many slides there are in the carousel, and which slide the user are currently viewing). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isShowIndicators() {
		Boolean value = (Boolean)getStateHelper().eval(PropertyKeys.showIndicators, true);
		return (boolean) value;
	}
	
	/**
	 * The indicators are the little dots at the bottom of each slide (which indicates how many slides there are in the carousel, and which slide the user are currently viewing). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setShowIndicators(boolean _showIndicators) {
	    getStateHelper().put(PropertyKeys.showIndicators, _showIndicators);
    }
	

	/**
	 * Inline style of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.style);
		return  value;
	}
	
	/**
	 * Inline style of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
	    getStateHelper().put(PropertyKeys.style, _style);
    }
	

	/**
	 * Style class of this element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.styleClass);
		return  value;
	}
	
	/**
	 * Style class of this element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
	    getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }
	

	/**
	 * The text of the tooltip. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltip);
		return  value;
	}
	
	/**
	 * The text of the tooltip. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
	    getStateHelper().put(PropertyKeys.tooltip, _tooltip);
    }
	

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelay() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
	    getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
    }
	

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
	    getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
    }
	

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
	    getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
    }
	

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipPosition() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
		return  value;
	}
	
	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
	    getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
    }
	

	/**
	 * Component(s) to be updated with ajax. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getUpdate() {
		String value = (String)getStateHelper().eval(PropertyKeys.update);
		return  value;
	}
	
	/**
	 * Component(s) to be updated with ajax. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
	    getStateHelper().put(PropertyKeys.update, _update);
    }
	
}

