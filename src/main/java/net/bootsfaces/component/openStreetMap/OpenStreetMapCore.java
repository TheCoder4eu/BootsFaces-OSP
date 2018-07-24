/**
 *  Copyright 2014-2017 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.openStreetMap;

import javax.faces.component.UIData;

/** This class holds the attributes of &lt;b:openStreetMap /&gt;. */
public abstract class OpenStreetMapCore extends UIData {

	protected enum PropertyKeys {
		attribution, center, dragging, height, marker, maxZoom, minZoom, miniMap, miniMapHeight, miniMapPosition, miniMapWidth, popupMsg, urlTemplate, width, zoom, zoomControl, zoomGlobal;
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
	 * attribution <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAttribution() {
		return (String) getStateHelper().eval(PropertyKeys.attribution);
	}

	/**
	 * attribution <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAttribution(String _attribution) {
		getStateHelper().put(PropertyKeys.attribution, _attribution);
	}

	/**
	 * center <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getCenter() {
		return (String) getStateHelper().eval(PropertyKeys.center);
	}

	/**
	 * center <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCenter(String _center) {
		getStateHelper().put(PropertyKeys.center, _center);
	}

	/**
	 * dragging <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isDragging() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.dragging, false);
	}

	/**
	 * dragging <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDragging(boolean _dragging) {
		getStateHelper().put(PropertyKeys.dragging, _dragging);
	}

	/**
	 * height <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHeight() {
		return (String) getStateHelper().eval(PropertyKeys.height);
	}

	/**
	 * height <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHeight(String _height) {
		getStateHelper().put(PropertyKeys.height, _height);
	}

	/**
	 * marker <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getMarker() {
		return (String) getStateHelper().eval(PropertyKeys.marker);
	}

	/**
	 * marker <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMarker(String _marker) {
		getStateHelper().put(PropertyKeys.marker, _marker);
	}

	/**
	 * maxZoom <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getMaxZoom() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.maxZoom, 0);
	}

	/**
	 * maxZoom <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxZoom(int _maxZoom) {
		getStateHelper().put(PropertyKeys.maxZoom, _maxZoom);
	}

	/**
	 * minZoom <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getMinZoom() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.minZoom, 0);
	}

	/**
	 * minZoom <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMinZoom(int _minZoom) {
		getStateHelper().put(PropertyKeys.minZoom, _minZoom);
	}

	/**
	 * miniMap <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isMiniMap() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.miniMap, false);
	}

	/**
	 * miniMap <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMap(boolean _miniMap) {
		getStateHelper().put(PropertyKeys.miniMap, _miniMap);
	}

	/**
	 * miniMapHeight <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getMiniMapHeight() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.miniMapHeight, 0);
	}

	/**
	 * miniMapHeight <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapHeight(int _miniMapHeight) {
		getStateHelper().put(PropertyKeys.miniMapHeight, _miniMapHeight);
	}

	/**
	 * miniMapPosition <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getMiniMapPosition() {
		return (String) getStateHelper().eval(PropertyKeys.miniMapPosition);
	}

	/**
	 * miniMapPosition <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapPosition(String _miniMapPosition) {
		getStateHelper().put(PropertyKeys.miniMapPosition, _miniMapPosition);
	}

	/**
	 * miniMapWidth <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getMiniMapWidth() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.miniMapWidth, 0);
	}

	/**
	 * miniMapWidth <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapWidth(int _miniMapWidth) {
		getStateHelper().put(PropertyKeys.miniMapWidth, _miniMapWidth);
	}

	/**
	 * popupMsg <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPopupMsg() {
		return (String) getStateHelper().eval(PropertyKeys.popupMsg);
	}

	/**
	 * popupMsg <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPopupMsg(String _popupMsg) {
		getStateHelper().put(PropertyKeys.popupMsg, _popupMsg);
	}

	/**
	 * urlTemplate <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getUrlTemplate() {
		return (String) getStateHelper().eval(PropertyKeys.urlTemplate);
	}

	/**
	 * urlTemplate <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUrlTemplate(String _urlTemplate) {
		getStateHelper().put(PropertyKeys.urlTemplate, _urlTemplate);
	}

	/**
	 * width <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWidth() {
		return (String) getStateHelper().eval(PropertyKeys.width);
	}

	/**
	 * width <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidth(String _width) {
		getStateHelper().put(PropertyKeys.width, _width);
	}

	/**
	 * zoom <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getZoom() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.zoom, 0);
	}

	/**
	 * zoom <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setZoom(int _zoom) {
		getStateHelper().put(PropertyKeys.zoom, _zoom);
	}

	/**
	 * zoomControl <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isZoomControl() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.zoomControl, false);
	}

	/**
	 * zoomControl <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setZoomControl(boolean _zoomControl) {
		getStateHelper().put(PropertyKeys.zoomControl, _zoomControl);
	}

	/**
	 * zoomGlobal <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isZoomGlobal() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.zoomGlobal, false);
	}

	/**
	 * zoomGlobal <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setZoomGlobal(boolean _zoomGlobal) {
		getStateHelper().put(PropertyKeys.zoomGlobal, _zoomGlobal);
	}

}
