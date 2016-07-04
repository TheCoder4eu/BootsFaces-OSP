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

/**
 * {@link TreeNodeEventListener} corresponding event. 
 * Hold the node that is checked
 */
public class TreeNodeCheckedEvent {
	private final Node node;
	private final boolean checked;
	
	public TreeNodeCheckedEvent(Node node, boolean checked) {
		super();
		this.node = node;
		this.checked = checked;
	}
	
	public Node getNode() {
		return node;
	}
	public boolean isChecked() {
		return checked;
	}
}
