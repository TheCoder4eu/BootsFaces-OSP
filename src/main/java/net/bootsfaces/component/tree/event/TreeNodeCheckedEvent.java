package net.bootsfaces.component.tree.event;

import net.bootsfaces.component.tree.model.Node;

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
