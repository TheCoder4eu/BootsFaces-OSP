/**
 *  Copyright 2014-2016 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.radiobutton;

import net.bootsfaces.beans.BsfBeanInfo;

/**
 * BeanInfo class to provide mapping
 * of snake-case attributes to camelCase ones
 *
 * @author Dario D'Urzo
 */
public class RadiobuttonBeanInfo extends BsfBeanInfo {

	/**
	 * Get the reference decorated class
	 */
	@Override
	public Class<?> getDecoratedClass() {
		return Radiobutton.class;
	}
}

