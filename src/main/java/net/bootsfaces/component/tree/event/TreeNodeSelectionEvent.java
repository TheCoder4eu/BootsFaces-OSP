package net.bootsfaces.component.tree.event;

import net.bootsfaces.component.tree.model.Node;

/**
 * {@link TreeNodeEventListener} corresponding event. Holding old value (is present) and new selected value.
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