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
		center, marker, popupMsg, zoom, width, height, minZoom, maxZoom, dragging, zoomControl, zoomGlobal, attribution,
		miniMap, miniMapWidth, miniMapHeight, miniMapPosition, urlTemplate;
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
	 * String specifying the center of the map.
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getCenter() {
		return (String) getStateHelper().eval(PropertyKeys.center);
	}

	/**
	 * String specifying the center of the map.
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setCenter(String _value) {
		getStateHelper().put(PropertyKeys.center, _value);
	}

	/**
	 * marker
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getMarker() {
		return (String) getStateHelper().eval(PropertyKeys.marker);
	}

	/**
	 * marker
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMarker(String _value) {
		getStateHelper().put(PropertyKeys.marker, _value);
	}

	/**
	 * popupMsg
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getPopupMsg() {
		return (String) getStateHelper().eval(PropertyKeys.popupMsg);
	}

	/**
	 * popupMsg
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setPopupMsg(String _value) {
		getStateHelper().put(PropertyKeys.popupMsg, _value);
	}

	/**
	 * zoom
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public Integer getZoom() {
		return (Integer) getStateHelper().eval(PropertyKeys.zoom, 10);
	}

	/**
	 * zoom
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setZoom(Integer _value) {
		getStateHelper().put(PropertyKeys.zoom, _value);
	}

	/**
	 * width
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getWidth() {
		return (String) getStateHelper().eval(PropertyKeys.width, "300px");
	}

	/**
	 * width
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setWidth(String _value) {
		getStateHelper().put(PropertyKeys.width, _value);
	}

	/**
	 * height
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getHeight() {
		return (String) getStateHelper().eval(PropertyKeys.height, "200px");
	}

	/**
	 * height
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHeight(String _value) {
		getStateHelper().put(PropertyKeys.height, _value);
	}

	/**
	 * minZoom
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public Integer getMinZoom() {
		return (Integer) getStateHelper().eval(PropertyKeys.minZoom, 1);
	}

	/**
	 * minZoom
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMinZoom(Integer _value) {
		getStateHelper().put(PropertyKeys.minZoom, _value);
	}

	/**
	 * maxZoom
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public Integer getMaxZoom() {
		return (Integer) getStateHelper().eval(PropertyKeys.maxZoom, 19);
	}

	/**
	 * maxZoom
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMaxZoom(Integer _value) {
		getStateHelper().put(PropertyKeys.maxZoom, _value);
	}

	/**
	 * Boolean to set whether dragging is enabled.
	 * 
	 * @return Returns the value of the attribute, or , false, if it hasn't been set
	 *         by the JSF file.
	 */
	public Boolean getDragging() {
		return (Boolean) getStateHelper().eval(PropertyKeys.dragging, true);
	}

	/**
	 * Boolean to set whether dragging is enabled.
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDragging(boolean _value) {
		getStateHelper().put(PropertyKeys.dragging, _value);
	}

	/**
	 * zoomControl
	 * 
	 * @return Returns the value of the attribute, or , false, if it hasn't been set
	 *         by the JSF file.
	 */
	public Boolean getZoomControl() {
		return (Boolean) getStateHelper().eval(PropertyKeys.zoomControl, true);
	}

	/**
	 * zoomControl
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setZoomControl(boolean _value) {
		getStateHelper().put(PropertyKeys.zoomControl, _value);
	}

	/**
	 * zoomGlobal
	 * 
	 * @return Returns the value of the attribute, or , false, if it hasn't been set
	 *         by the JSF file.
	 */
	public Boolean getZoomGlobal() {
		return (Boolean) getStateHelper().eval(PropertyKeys.zoomGlobal, true);
	}

	/**
	 * zoomGlobal
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setZoomGlobal(boolean _value) {
		getStateHelper().put(PropertyKeys.zoomGlobal, _value);
	}

	/**
	 * attribution
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getAttribution() {
		return (String) getStateHelper().eval(PropertyKeys.attribution,
				"Map data &copy; <a href=\"http://openstreetmap.org\">OpenStreetMap</a> contributors,<a href=\"http://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>");
//		return (String) getStateHelper().eval(PropertyKeys.attribution,
//				"Map data &copy; &lt;a href=&quot;http://openstreetmap.org&quot;&gt;OpenStreetMap&lt;/a&gt; contributors,&lt;a href=&quot;http://creativecommons.org/licenses/by-sa/2.0/&quot;&gt;CC-BY-SA&lt;/a&gt;");}
	}

	/**
	 * attribution
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setAttribution(String _value) {
		getStateHelper().put(PropertyKeys.attribution, _value);
	}

	/**
	 * miniMap
	 * 
	 * @return Returns the value of the attribute, or , false, if it hasn't been set
	 *         by the JSF file.
	 */
	public Boolean getMiniMap() {
		return (Boolean) getStateHelper().eval(PropertyKeys.miniMap, false);
	}

	/**
	 * miniMap
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMap(boolean _value) {
		getStateHelper().put(PropertyKeys.miniMap, _value);
	}

	/**
	 * miniMapWidth
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public Integer getMiniMapWidth() {
		return (Integer) getStateHelper().eval(PropertyKeys.miniMapWidth, 100);
	}

	/**
	 * miniMapWidth
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapWidth(Integer _value) {
		getStateHelper().put(PropertyKeys.miniMapWidth, _value);
	}

	/**
	 * miniMapHeight
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public Integer getMiniMapHeight() {
		return (Integer) getStateHelper().eval(PropertyKeys.miniMapHeight, 100);
	}

	/**
	 * miniMapHeight
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapHeight(Integer _value) {
		getStateHelper().put(PropertyKeys.miniMapHeight, _value);
	}

	/**
	 * miniMapPosition
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getMiniMapPosition() {
		return (String) getStateHelper().eval(PropertyKeys.miniMapPosition, "bottomright");
	}

	/**
	 * miniMapPosition
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMiniMapPosition(String _value) {
		getStateHelper().put(PropertyKeys.miniMapPosition, _value);
	}

	/**
	 * urlTemplate
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been set by
	 *         the JSF file.
	 */
	public String getUrlTemplate() {
		return (String) getStateHelper().eval(PropertyKeys.urlTemplate, "http://{s}.tile.osm.org/{z}/{x}/{y}.png");
	}

	/**
	 * urlTemplate
	 * 
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setUrlTemplate(String _value) {
		getStateHelper().put(PropertyKeys.urlTemplate, _value);
	}
}
