/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.touchSpin;

import net.bootsfaces.beans.BsfBeanInfo;

/** 
 * BeanInfo class to provide mapping
 * of snake-case attributes to camelCase ones 
 * 
 * @author Dario D'Urzo
 */
public class TouchSpinBeanInfo extends BsfBeanInfo {
	
	/**
	 * Get the reference decorated class
	 */
	@Override
	public Class<?> getDecoratedClass() {
		return TouchSpin.class;
	}
}

