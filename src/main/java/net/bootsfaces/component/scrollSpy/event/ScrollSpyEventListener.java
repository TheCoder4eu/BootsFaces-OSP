package net.bootsfaces.component.scrollSpy.event;

public interface ScrollSpyEventListener {
	/**
	 * Executed when the spy came to a specific section
	 * @param itemID
	 */
	void itemSelected(final String itemID);
}
