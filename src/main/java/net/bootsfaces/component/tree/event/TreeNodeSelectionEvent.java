/**
 *  Copyright 2014-2019 Dario D'Urzo
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

/**
 * {@link TreeNodeEventListener} corresponding event. 
 * Holding the node that is selected
 */
public class TreeNodeSelectionEvent {

    private final Node node;
    private final boolean selected;

    public TreeNodeSelectionEvent(final Node node, final boolean selected) {
        this.node = node;
        this.selected = selected;
    }

    public Node getNode() {
        return node;
    }

    public boolean isSelected() {
        return selected;
    }
}
