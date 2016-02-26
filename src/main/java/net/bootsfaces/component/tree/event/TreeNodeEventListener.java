package net.bootsfaces.component.tree.event;

import net.bootsfaces.component.tree.model.Node;

/**
 * Handle single tree node selection on {@link de.larmic.butterfaces.component.html.tree.HtmlTree} component if registered.
 */
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