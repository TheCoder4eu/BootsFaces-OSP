/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.scrollUp;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:scrollUp /&gt;. */
@FacesComponent("net.bootsfaces.component.scrollUp.ScrollUp")
public class ScrollUp extends UIComponentBase {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.scrollUp.ScrollUp";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.scrollUp.ScrollUp";

	public ScrollUp() {
		setRendererType(DEFAULT_RENDERER);
		AddResourcesListener.addThemedCSSResource("scrollup.css");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/jquery.scrollUp.min.js");
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * Manage EL-expression for snake-case attributes
	 */
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	protected enum PropertyKeys {
		animation,
		animationSpeed,
		distance,
		easing,
		from,
		image,
		name,
		overlay,
		speed,
		target,
		text,
		title,
		trigger;
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
	 * Animation type: Fade, slide, none. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAnimation() {
		return (String) getStateHelper().eval(PropertyKeys.animation);
	}

	/**
	 * Animation type: Fade, slide, none. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAnimation(String _animation) {
		getStateHelper().put(PropertyKeys.animation, _animation);
	}

	/**
	 * Animation speed (ms). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getAnimationSpeed() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.animationSpeed, 0);
	}

	/**
	 * Animation speed (ms). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAnimationSpeed(int _animationSpeed) {
		getStateHelper().put(PropertyKeys.animationSpeed, _animationSpeed);
	}

	/**
	 * Distance from top/bottom before showing element (px) <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getDistance() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.distance, 0);
	}

	/**
	 * Distance from top/bottom before showing element (px) <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDistance(int _distance) {
		getStateHelper().put(PropertyKeys.distance, _distance);
	}

	/**
	 * Scroll easing (see http://easings.net/). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getEasing() {
		return (String) getStateHelper().eval(PropertyKeys.easing);
	}

	/**
	 * Scroll easing (see http://easings.net/). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setEasing(String _easing) {
		getStateHelper().put(PropertyKeys.easing, _easing);
	}

	/**
	 * scroll direction: 'top' or 'bottom' <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getFrom() {
		return (String) getStateHelper().eval(PropertyKeys.from);
	}

	/**
	 * scroll direction: 'top' or 'bottom' <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setFrom(String _from) {
		getStateHelper().put(PropertyKeys.from, _from);
	}

	/**
	 * Set true to use image. (Must be set in related css). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getImage() {
		return (String) getStateHelper().eval(PropertyKeys.image);
	}

	/**
	 * Set true to use image. (Must be set in related css). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImage(String _image) {
		getStateHelper().put(PropertyKeys.image, _image);
	}

	/**
	 * Element ID <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getName() {
		return (String) getStateHelper().eval(PropertyKeys.name);
	}

	/**
	 * Element ID <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setName(String _name) {
		getStateHelper().put(PropertyKeys.name, _name);
	}

	/**
	 * Set CSS color to display scrollUp active point, e.g '#00FFFF' <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOverlay() {
		return (String) getStateHelper().eval(PropertyKeys.overlay);
	}

	/**
	 * Set CSS color to display scrollUp active point, e.g '#00FFFF' <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOverlay(String _overlay) {
		getStateHelper().put(PropertyKeys.overlay, _overlay);
	}

	/**
	 * Speed back to top (ms). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getSpeed() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.speed, 0);
	}

	/**
	 * Speed back to top (ms). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpeed(int _speed) {
		getStateHelper().put(PropertyKeys.speed, _speed);
	}

	/**
	 * Set a custom target element for scrolling to. Can be element or number. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTarget() {
		return (String) getStateHelper().eval(PropertyKeys.target);
	}

	/**
	 * Set a custom target element for scrolling to. Can be element or number. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTarget(String _target) {
		getStateHelper().put(PropertyKeys.target, _target);
	}

	/**
	 * Text for element, can contain HTML. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getText() {
		return (String) getStateHelper().eval(PropertyKeys.text);
	}

	/**
	 * Text for element, can contain HTML. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setText(String _text) {
		getStateHelper().put(PropertyKeys.text, _text);
	}

	/**
	 * Set a custom &lt;a&gt; title if required. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTitle() {
		return (String) getStateHelper().eval(PropertyKeys.title);
	}

	/**
	 * Set a custom &lt;a&gt; title if required. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTitle(String _title) {
		getStateHelper().put(PropertyKeys.title, _title);
	}

	/**
	 * Set a custom triggering element. Can be an HTML string or jQuery object <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTrigger() {
		return (String) getStateHelper().eval(PropertyKeys.trigger);
	}

	/**
	 * Set a custom triggering element. Can be an HTML string or jQuery object <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTrigger(String _trigger) {
		getStateHelper().put(PropertyKeys.trigger, _trigger);
	}

}
