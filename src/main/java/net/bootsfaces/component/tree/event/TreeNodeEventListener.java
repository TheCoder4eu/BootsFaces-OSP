/**
 *  Copyright 2014-2016 Dario D'Urzo
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