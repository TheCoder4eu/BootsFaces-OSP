/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.video;

import javax.faces.component.UIComponentBase;

/** This class holds the attributes of &lt;b:video /&gt;. */
public abstract class VideoCore extends UIComponentBase implements net.bootsfaces.render.IHasTooltip {

	protected enum PropertyKeys {
		ajax, autoplay, binding, colLg, colMd, colSm, colXs, controls, delay, disabled, display, hidden, immediate, largeScreen, loop, mediumScreen, offset, offsetLg, offsetMd, offsetSm, offsetXs, onabort, oncanplay, oncanplaythrough, onclick, oncomplete, ondurationchange, onemptied, onended, onerror, onloadeddata, onloadedmetadata, onloadstart, onpause, onplay, onplaying, onprogress, onratechange, onseeked, onseeking, onstalled, onsuccess, onsuspend, ontimeupdate, onvolumechange, onwaiting, process, required, smallScreen, span, src, style, styleClass, tinyScreen, tooltip, tooltipContainer, tooltipDelay, tooltipDelayHide, tooltipDelayShow, tooltipPosition, update, visible;
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
	 * Whether the Button submits the form with AJAX. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isAjax() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.ajax, false);
	}

	/**
	 * Whether the Button submits the form with AJAX. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAjax(boolean _ajax) {
		getStateHelper().put(PropertyKeys.ajax, _ajax);
	}

	/**
	 * Setting this to true will start the video on page load. By default, autoplay is deactivated. Note that mobile devices never start videos automatically. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isAutoplay() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.autoplay, false);
	}

	/**
	 * Setting this to true will start the video on page load. By default, autoplay is deactivated. Note that mobile devices never start videos automatically. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAutoplay(boolean _autoplay) {
		getStateHelper().put(PropertyKeys.autoplay, _autoplay);
	}

	/**
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public javax.faces.component.UIComponent getBinding() {
		return (javax.faces.component.UIComponent) getStateHelper().eval(PropertyKeys.binding);
	}

	/**
	 * An EL expression referring to a server side UIComponent instance in a backing bean. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setBinding(javax.faces.component.UIComponent _binding) {
		getStateHelper().put(PropertyKeys.binding, _binding);
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColLg() {
		return (String) getStateHelper().eval(PropertyKeys.colLg, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColLg(String _colLg) {
		getStateHelper().put(PropertyKeys.colLg, _colLg);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColMd() {
		return (String) getStateHelper().eval(PropertyKeys.colMd, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColMd(String _colMd) {
		getStateHelper().put(PropertyKeys.colMd, _colMd);
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColSm() {
		return (String) getStateHelper().eval(PropertyKeys.colSm, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColSm(String _colSm) {
		getStateHelper().put(PropertyKeys.colSm, _colSm);
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getColXs() {
		return (String) getStateHelper().eval(PropertyKeys.colXs, "-1");
	}

	/**
	 * Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColXs(String _colXs) {
		getStateHelper().put(PropertyKeys.colXs, _colXs);
	}

	/**
	 * Hide the controls (start, stop, seeking, volume etc.) by setting this property to false. By default, the controls are shown. <P>
	 * @return Returns the value of the attribute, or true, if it hasn't been set by the JSF file.
	 */
	public boolean isControls() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.controls, true);
	}

	/**
	 * Hide the controls (start, stop, seeking, volume etc.) by setting this property to false. By default, the controls are shown. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setControls(boolean _controls) {
		getStateHelper().put(PropertyKeys.controls, _controls);
	}

	/**
	 * Delays the AJAX request. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDelay() {
		return (String) getStateHelper().eval(PropertyKeys.delay);
	}

	/**
	 * Delays the AJAX request. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDelay(String _delay) {
		getStateHelper().put(PropertyKeys.delay, _delay);
	}

	/**
	 * Boolean value to specify if the button is disabled. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isDisabled() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.disabled, false);
	}

	/**
	 * Boolean value to specify if the button is disabled. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisabled(boolean _disabled) {
		getStateHelper().put(PropertyKeys.disabled, _disabled);
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * @return Returns the value of the attribute, or "block", if it hasn't been set by the JSF file.
	 */
	public String getDisplay() {
		return (String) getStateHelper().eval(PropertyKeys.display, "block");
	}

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisplay(String _display) {
		getStateHelper().put(PropertyKeys.display, _display);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHidden() {
		return (String) getStateHelper().eval(PropertyKeys.hidden);
	}

	/**
	 * This column is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHidden(String _hidden) {
		getStateHelper().put(PropertyKeys.hidden, _hidden);
	}

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isImmediate() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.immediate, false);
	}

	/**
	 * Flag indicating that, if this component is activated by the user, notifications should be delivered to interested listeners and actions immediately (that is, during Apply Request Values phase) rather than waiting until Invoke Application phase. Default is false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setImmediate(boolean _immediate) {
		getStateHelper().put(PropertyKeys.immediate, _immediate);
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getLargeScreen() {
		return (String) getStateHelper().eval(PropertyKeys.largeScreen, "-1");
	}

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLargeScreen(String _largeScreen) {
		getStateHelper().put(PropertyKeys.largeScreen, _largeScreen);
	}

	/**
	 * Setting this flag to true causes the video to start over when it's finished. By default, loop is set to false. <P>
	 * @return Returns the value of the attribute, or false, if it hasn't been set by the JSF file.
	 */
	public boolean isLoop() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.loop, false);
	}

	/**
	 * Setting this flag to true causes the video to start over when it's finished. By default, loop is set to false. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLoop(boolean _loop) {
		getStateHelper().put(PropertyKeys.loop, _loop);
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getMediumScreen() {
		return (String) getStateHelper().eval(PropertyKeys.mediumScreen, "-1");
	}

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMediumScreen(String _mediumScreen) {
		getStateHelper().put(PropertyKeys.mediumScreen, _mediumScreen);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffset() {
		return (String) getStateHelper().eval(PropertyKeys.offset);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffset(String _offset) {
		getStateHelper().put(PropertyKeys.offset, _offset);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetLg() {
		return (String) getStateHelper().eval(PropertyKeys.offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetLg(String _offsetLg) {
		getStateHelper().put(PropertyKeys.offsetLg, _offsetLg);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetMd() {
		return (String) getStateHelper().eval(PropertyKeys.offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetMd(String _offsetMd) {
		getStateHelper().put(PropertyKeys.offsetMd, _offsetMd);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetSm() {
		return (String) getStateHelper().eval(PropertyKeys.offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetSm(String _offsetSm) {
		getStateHelper().put(PropertyKeys.offsetSm, _offsetSm);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOffsetXs() {
		return (String) getStateHelper().eval(PropertyKeys.offsetXs);
	}

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetXs(String _offsetXs) {
		getStateHelper().put(PropertyKeys.offsetXs, _offsetXs);
	}

	/**
	 * Fires when the loading of an audio/video is aborted <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnabort() {
		return (String) getStateHelper().eval(PropertyKeys.onabort);
	}

	/**
	 * Fires when the loading of an audio/video is aborted <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnabort(String _onabort) {
		getStateHelper().put(PropertyKeys.onabort, _onabort);
	}

	/**
	 * Fires when the browser can start playing the audio/video <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOncanplay() {
		return (String) getStateHelper().eval(PropertyKeys.oncanplay);
	}

	/**
	 * Fires when the browser can start playing the audio/video <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncanplay(String _oncanplay) {
		getStateHelper().put(PropertyKeys.oncanplay, _oncanplay);
	}

	/**
	 * Fires when the browser can play through the audio/video without stopping for buffering <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOncanplaythrough() {
		return (String) getStateHelper().eval(PropertyKeys.oncanplaythrough);
	}

	/**
	 * Fires when the browser can play through the audio/video without stopping for buffering <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncanplaythrough(String _oncanplaythrough) {
		getStateHelper().put(PropertyKeys.oncanplaythrough, _oncanplaythrough);
	}

	/**
	 * The onclick attribute. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnclick() {
		return (String) getStateHelper().eval(PropertyKeys.onclick);
	}

	/**
	 * The onclick attribute. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnclick(String _onclick) {
		getStateHelper().put(PropertyKeys.onclick, _onclick);
	}

	/**
	 * JavaScript to be executed when ajax completes. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOncomplete() {
		return (String) getStateHelper().eval(PropertyKeys.oncomplete);
	}

	/**
	 * JavaScript to be executed when ajax completes. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOncomplete(String _oncomplete) {
		getStateHelper().put(PropertyKeys.oncomplete, _oncomplete);
	}

	/**
	 * Fires when the duration of the audio/video is changed <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOndurationchange() {
		return (String) getStateHelper().eval(PropertyKeys.ondurationchange);
	}

	/**
	 * Fires when the duration of the audio/video is changed <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOndurationchange(String _ondurationchange) {
		getStateHelper().put(PropertyKeys.ondurationchange, _ondurationchange);
	}

	/**
	 * Fires when the current playlist is empty <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnemptied() {
		return (String) getStateHelper().eval(PropertyKeys.onemptied);
	}

	/**
	 * Fires when the current playlist is empty <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnemptied(String _onemptied) {
		getStateHelper().put(PropertyKeys.onemptied, _onemptied);
	}

	/**
	 * Fires when the current playlist is ended <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnended() {
		return (String) getStateHelper().eval(PropertyKeys.onended);
	}

	/**
	 * Fires when the current playlist is ended <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnended(String _onended) {
		getStateHelper().put(PropertyKeys.onended, _onended);
	}

	/**
	 * JavaScript to be executed when ajax results on an error (including both network errors and Java exceptions). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnerror() {
		return (String) getStateHelper().eval(PropertyKeys.onerror);
	}

	/**
	 * JavaScript to be executed when ajax results on an error (including both network errors and Java exceptions). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnerror(String _onerror) {
		getStateHelper().put(PropertyKeys.onerror, _onerror);
	}

	/**
	 * Fires when the browser has loaded the current frame of the audio/video <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnloadeddata() {
		return (String) getStateHelper().eval(PropertyKeys.onloadeddata);
	}

	/**
	 * Fires when the browser has loaded the current frame of the audio/video <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnloadeddata(String _onloadeddata) {
		getStateHelper().put(PropertyKeys.onloadeddata, _onloadeddata);
	}

	/**
	 * Fires when the browser has loaded meta data for the audio/video <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnloadedmetadata() {
		return (String) getStateHelper().eval(PropertyKeys.onloadedmetadata);
	}

	/**
	 * Fires when the browser has loaded meta data for the audio/video <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnloadedmetadata(String _onloadedmetadata) {
		getStateHelper().put(PropertyKeys.onloadedmetadata, _onloadedmetadata);
	}

	/**
	 * Fires when the browser starts looking for the audio/video <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnloadstart() {
		return (String) getStateHelper().eval(PropertyKeys.onloadstart);
	}

	/**
	 * Fires when the browser starts looking for the audio/video <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnloadstart(String _onloadstart) {
		getStateHelper().put(PropertyKeys.onloadstart, _onloadstart);
	}

	/**
	 * Fires when the audio/video has been paused <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnpause() {
		return (String) getStateHelper().eval(PropertyKeys.onpause);
	}

	/**
	 * Fires when the audio/video has been paused <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnpause(String _onpause) {
		getStateHelper().put(PropertyKeys.onpause, _onpause);
	}

	/**
	 * Fires when the audio/video has been started or is no longer paused <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnplay() {
		return (String) getStateHelper().eval(PropertyKeys.onplay);
	}

	/**
	 * Fires when the audio/video has been started or is no longer paused <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnplay(String _onplay) {
		getStateHelper().put(PropertyKeys.onplay, _onplay);
	}

	/**
	 * Fires when the audio/video is playing after having been paused or stopped for buffering <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnplaying() {
		return (String) getStateHelper().eval(PropertyKeys.onplaying);
	}

	/**
	 * Fires when the audio/video is playing after having been paused or stopped for buffering <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnplaying(String _onplaying) {
		getStateHelper().put(PropertyKeys.onplaying, _onplaying);
	}

	/**
	 * Fires when the browser is downloading the audio/video <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnprogress() {
		return (String) getStateHelper().eval(PropertyKeys.onprogress);
	}

	/**
	 * Fires when the browser is downloading the audio/video <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnprogress(String _onprogress) {
		getStateHelper().put(PropertyKeys.onprogress, _onprogress);
	}

	/**
	 * Fires when the playing speed of the audio/video is changed <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnratechange() {
		return (String) getStateHelper().eval(PropertyKeys.onratechange);
	}

	/**
	 * Fires when the playing speed of the audio/video is changed <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnratechange(String _onratechange) {
		getStateHelper().put(PropertyKeys.onratechange, _onratechange);
	}

	/**
	 * Fires when the user is finished moving/skipping to a new position in the audio/video <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnseeked() {
		return (String) getStateHelper().eval(PropertyKeys.onseeked);
	}

	/**
	 * Fires when the user is finished moving/skipping to a new position in the audio/video <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnseeked(String _onseeked) {
		getStateHelper().put(PropertyKeys.onseeked, _onseeked);
	}

	/**
	 * Fires when the user starts moving/skipping to a new position in the audio/video <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnseeking() {
		return (String) getStateHelper().eval(PropertyKeys.onseeking);
	}

	/**
	 * Fires when the user starts moving/skipping to a new position in the audio/video <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnseeking(String _onseeking) {
		getStateHelper().put(PropertyKeys.onseeking, _onseeking);
	}

	/**
	 * Fires when the browser is trying to get media data, but data is not available <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnstalled() {
		return (String) getStateHelper().eval(PropertyKeys.onstalled);
	}

	/**
	 * Fires when the browser is trying to get media data, but data is not available <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnstalled(String _onstalled) {
		getStateHelper().put(PropertyKeys.onstalled, _onstalled);
	}

	/**
	 * JavaScript to be executed when ajax completes with success (i.e. there's neither a network error nor a Java exception). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnsuccess() {
		return (String) getStateHelper().eval(PropertyKeys.onsuccess);
	}

	/**
	 * JavaScript to be executed when ajax completes with success (i.e. there's neither a network error nor a Java exception). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnsuccess(String _onsuccess) {
		getStateHelper().put(PropertyKeys.onsuccess, _onsuccess);
	}

	/**
	 * Fires when the browser is intentionally not getting media data <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnsuspend() {
		return (String) getStateHelper().eval(PropertyKeys.onsuspend);
	}

	/**
	 * Fires when the browser is intentionally not getting media data <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnsuspend(String _onsuspend) {
		getStateHelper().put(PropertyKeys.onsuspend, _onsuspend);
	}

	/**
	 * Fires when the current playback position has changed <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOntimeupdate() {
		return (String) getStateHelper().eval(PropertyKeys.ontimeupdate);
	}

	/**
	 * Fires when the current playback position has changed <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOntimeupdate(String _ontimeupdate) {
		getStateHelper().put(PropertyKeys.ontimeupdate, _ontimeupdate);
	}

	/**
	 * Fires when the volume has been changed <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnvolumechange() {
		return (String) getStateHelper().eval(PropertyKeys.onvolumechange);
	}

	/**
	 * Fires when the volume has been changed <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnvolumechange(String _onvolumechange) {
		getStateHelper().put(PropertyKeys.onvolumechange, _onvolumechange);
	}

	/**
	 * Fires when the video stops because it needs to buffer the next frame <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getOnwaiting() {
		return (String) getStateHelper().eval(PropertyKeys.onwaiting);
	}

	/**
	 * Fires when the video stops because it needs to buffer the next frame <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOnwaiting(String _onwaiting) {
		getStateHelper().put(PropertyKeys.onwaiting, _onwaiting);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getProcess() {
		return (String) getStateHelper().eval(PropertyKeys.process);
	}

	/**
	 * Comma or space separated list of ids or search expressions denoting which values are to be sent to the server. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setProcess(String _process) {
		getStateHelper().put(PropertyKeys.process, _process);
	}

	/**
	 * URL of the video to be shown. If you use a relative path (i.e. without http:// and without the domain name), the video will be taken from your own server. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRequired() {
		return (String) getStateHelper().eval(PropertyKeys.required);
	}

	/**
	 * URL of the video to be shown. If you use a relative path (i.e. without http:// and without the domain name), the video will be taken from your own server. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRequired(String _required) {
		getStateHelper().put(PropertyKeys.required, _required);
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getSmallScreen() {
		return (String) getStateHelper().eval(PropertyKeys.smallScreen, "-1");
	}

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSmallScreen(String _smallScreen) {
		getStateHelper().put(PropertyKeys.smallScreen, _smallScreen);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSpan() {
		return (String) getStateHelper().eval(PropertyKeys.span);
	}

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(String _span) {
		getStateHelper().put(PropertyKeys.span, _span);
	}

	/**
	 *  <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSrc() {
		return (String) getStateHelper().eval(PropertyKeys.src);
	}

	/**
	 *  <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSrc(String _src) {
		getStateHelper().put(PropertyKeys.src, _src);
	}

	/**
	 * Inline CSS of the tab. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		return (String) getStateHelper().eval(PropertyKeys.style);
	}

	/**
	 * Inline CSS of the tab. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
		getStateHelper().put(PropertyKeys.style, _style);
	}

	/**
	 * Style class of the div surrounding the tab pane. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		return (String) getStateHelper().eval(PropertyKeys.styleClass);
	}

	/**
	 * Style class of the div surrounding the tab pane. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
		getStateHelper().put(PropertyKeys.styleClass, _styleClass);
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * @return Returns the value of the attribute, or "-1", if it hasn't been set by the JSF file.
	 */
	public String getTinyScreen() {
		return (String) getStateHelper().eval(PropertyKeys.tinyScreen, "-1");
	}

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (≤ 767 pixels wide). The number may optionally be followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-fourths. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTinyScreen(String _tinyScreen) {
		getStateHelper().put(PropertyKeys.tinyScreen, _tinyScreen);
	}

	/**
	 * The text of the tooltip. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		return (String) getStateHelper().eval(PropertyKeys.tooltip);
	}

	/**
	 * The text of the tooltip. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
		getStateHelper().put(PropertyKeys.tooltip, _tooltip);
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering errors in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * @return Returns the value of the attribute, or "body", if it hasn't been set by the JSF file.
	 */
	public String getTooltipContainer() {
		return (String) getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
	}

	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering errors in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
		getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
	}

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelay() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
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
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
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
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
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
		return (String) getStateHelper().eval(PropertyKeys.tooltipPosition);
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
		return (String) getStateHelper().eval(PropertyKeys.update);
	}

	/**
	 * Component(s) to be updated with ajax. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUpdate(String _update) {
		getStateHelper().put(PropertyKeys.update, _update);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getVisible() {
		return (String) getStateHelper().eval(PropertyKeys.visible);
	}

	/**
	 * This column is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVisible(String _visible) {
		getStateHelper().put(PropertyKeys.visible, _visible);
	}

}
