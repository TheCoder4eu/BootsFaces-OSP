/**
 *  Copyright 2014-2019 Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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
package net.bootsfaces.component;

import javax.faces.component.UIComponent;
import javax.faces.model.SelectItem;

/**
 * This class is used to return both the SelectItem and the underlying UIComponent from
 * the collectSelectItems() method. The component is needed to generate pass-through attributes.
 * @author Stephan Rauh
 *
 */
public class SelectItemAndComponent {
	private UIComponent component;
	
	private SelectItem selectItem;
	
	public SelectItemAndComponent(UIComponent component, SelectItem selectItem) {
		this.component = component;
		this.selectItem = selectItem;
	}

	public UIComponent getComponent() {
		return component;
	}

	public SelectItem getSelectItem() {
		return selectItem;
	}
}
