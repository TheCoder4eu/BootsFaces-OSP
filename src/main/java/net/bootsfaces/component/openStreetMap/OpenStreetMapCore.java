/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu), Dario D'Urzo and Stephan Rauh (http://www.beyondjava.net).
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
		attribution, center, dragging, height, marker, maxZoom, minZoom, miniMap, miniMapHeight, miniMapPosition,
		miniMapWidth, popupMsg, urlTemplate, width, zoom, zoomControl, zoomGlobal;
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
	 * Copyright notice, indicating the template provider (aka tile server). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getAttribution() {
		return (String) getStateHelper().eval(PropertyKeys.attribution);
	}

	/**
	 * Copyright notice, indicating the template provider (aka tile server). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAttribution(String _attribution) {
		getStateHelper().put(PropertyKeys.attribution, _attribution);
	}

	/**
	 * GPS coordinates of the center of the map <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getCenter() {
		return (String) getStateHelper().eval(PropertyKeys.center);
	}

	/**
	 * GPS coordinates of the center of the map <P>
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
	 * Height of the map. Every CSS unit can be use (e.g. 500px or 50vh). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHeight() {
		return (String) getStateHelper().eval(PropertyKeys.height);
	}

	/**
	 * Height of the map. Every CSS unit can be use (e.g. 500px or 50vh). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHeight(String _height) {
		getStateHelper().put(PropertyKeys.height, _height);
	}

	/**
	 * Where to put the marker. Leave this attribute empty if you don't need a marker. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getMarker() {
		return (String) getStateHelper().eval(PropertyKeys.marker);
	}

	/**
	 * Where to put the marker. Leave this attribute empty if you don't need a marker. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMarker(String _marker) {
		getStateHelper().put(PropertyKeys.marker, _marker);
	}

	/**
	 * Maximal zoom level the user can configure. <P>
	 * @return Returns the value of the attribute, or 20, if it hasn't been set by the JSF file.
	 */
	public int getMaxZoom() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.maxZoom, 20);
	}

	/**
	 * Maximal zoom level the user can configure. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxZoom(int _maxZoom) {
		getStateHelper().put(PropertyKeys.maxZoom, _maxZoom);
	}

	/**
	 * Minimal zoom level the user can configure. <P>
	 * @return Returns the value of the attribute, or 1, if it hasn't been set by the JSF file.
	 */
	public int getMinZoom() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.minZoom, 1);
	}

	/**
	 * Minimal zoom level the user can configure. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMinZoom(int _minZoom) {
		getStateHelper().put(PropertyKeys.minZoom, _minZoom);
	}

	/**
	 * Setting this to true activates a miniature map. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isMiniMap() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.miniMap, false);
	}

	/**
	 * Setting this to true activates a miniature map. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMap(boolean _miniMap) {
		getStateHelper().put(PropertyKeys.miniMap, _miniMap);
	}

	/**
	 * Height of the miniature map (in pixels). <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getMiniMapHeight() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.miniMapHeight, 0);
	}

	/**
	 * Height of the miniature map (in pixels). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapHeight(int _miniMapHeight) {
		getStateHelper().put(PropertyKeys.miniMapHeight, _miniMapHeight);
	}

	/**
	 * Position of the miniature map (in pixels). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getMiniMapPosition() {
		return (String) getStateHelper().eval(PropertyKeys.miniMapPosition);
	}

	/**
	 * Position of the miniature map (in pixels). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapPosition(String _miniMapPosition) {
		getStateHelper().put(PropertyKeys.miniMapPosition, _miniMapPosition);
	}

	/**
	 * Width of the miniature map (in pixels). <P>
	 * @return Returns the value of the attribute, or 0, if it hasn't been set by the JSF file.
	 */
	public int getMiniMapWidth() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.miniMapWidth, 0);
	}

	/**
	 * Width of the miniature map (in pixels). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapWidth(int _miniMapWidth) {
		getStateHelper().put(PropertyKeys.miniMapWidth, _miniMapWidth);
	}

	/**
	 * Tooltip shown when the marker is clicked. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getPopupMsg() {
		return (String) getStateHelper().eval(PropertyKeys.popupMsg);
	}

	/**
	 * Tooltip shown when the marker is clicked. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPopupMsg(String _popupMsg) {
		getStateHelper().put(PropertyKeys.popupMsg, _popupMsg);
	}

	/**
	 * URL of the template provider (aka tile server) delivering  the maps. See https://wiki.openstreetmap.org/wiki/Tile_servers for full details. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getUrlTemplate() {
		return (String) getStateHelper().eval(PropertyKeys.urlTemplate);
	}

	/**
	 * URL of the template provider (aka tile server) delivering  the maps. See https://wiki.openstreetmap.org/wiki/Tile_servers for full details. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUrlTemplate(String _urlTemplate) {
		getStateHelper().put(PropertyKeys.urlTemplate, _urlTemplate);
	}

	/**
	 * Width of the map. Every CSS unit can be use (e.g. 500px or 50vh). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getWidth() {
		return (String) getStateHelper().eval(PropertyKeys.width);
	}

	/**
	 * Width of the map. Every CSS unit can be use (e.g. 500px or 50vh). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidth(String _width) {
		getStateHelper().put(PropertyKeys.width, _width);
	}

	/**
	 * Zoom level. Ranges from 1 (world view) to 20 (detailed view). <P>
	 * @return Returns the value of the attribute, or 1, if it hasn't been set by the JSF file.
	 */
	public int getZoom() {
		return (int) (Integer) getStateHelper().eval(PropertyKeys.zoom, 1);
	}

	/**
	 * Zoom level. Ranges from 1 (world view) to 20 (detailed view). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setZoom(int _zoom) {
		getStateHelper().put(PropertyKeys.zoom, _zoom);
	}

	/**
	 * Setting this to false hides the zoom control widget. <P>
	 * @return Returns the value of the attribute, or , false, if it hasn't been set by the JSF file.
	 */
	public boolean isZoomControl() {
		return (boolean) (Boolean) getStateHelper().eval(PropertyKeys.zoomControl, false);
	}

	/**
	 * Setting this to false hides the zoom control widget. <P>
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
