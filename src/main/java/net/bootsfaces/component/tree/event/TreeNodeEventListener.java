/**
 *  Copyright 2014-2016 Dario D'Urzo
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
package net.bootsfaces.component.tree.event;

import net.bootsfaces.component.tree.model.Node;

public interface TreeNodeEventListener {

    /**
     * Called if a single click is processed by user interaction.
     *
     * @param event corresponding {@link TreeNodeSelectionEvent}
     */
    void processValueChange(final TreeNodeSelectionEvent event);
    
    /**
     * Called if a check event is processed by user interaction.
     *
     * @param event corresponding {@link TreeNodeCheckedEvent}
     */
    void processValueChecked(final TreeNodeCheckedEvent event);
    
    /**
     * Called if a uncheck event is processed by user interaction.
     *
     * @param event corresponding {@link TreeNodeCheckedEvent}
     */
    void processValueUnchecked(final TreeNodeCheckedEvent event);
    
    /**
     * @return true if actual value is selected. if a selected row is found remaining rows will not be checked because
     * only single tree node selection is available.
     *
     * @param data selected row data
     */
    boolean isValueSelected(final Node data);
    
    /**
     * @return true if actual value is checked. 
     *
     * @param data checked row data
     */
    boolean isValueChecked(final Node data);
}