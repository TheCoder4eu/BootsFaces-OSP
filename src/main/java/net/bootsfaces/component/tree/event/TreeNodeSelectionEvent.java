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

/**
 * {@link TreeNodeEventListener} corresponding event. 
 * Holding old value (is present) and new selected value.
 */
public class TreeNodeSelectionEvent {

    private final Node oldSelectedNode;
    private final Node newSelectedNode;

    public TreeNodeSelectionEvent(final Node oldSelectedNode, final Node newSelectedNode) {
        this.oldSelectedNode = oldSelectedNode;
        this.newSelectedNode = newSelectedNode;
    }

	public Node getOldSelectedNode() {
		return oldSelectedNode;
	}

	public Node getNewSelectedNode() {
		return newSelectedNode;
	}
}