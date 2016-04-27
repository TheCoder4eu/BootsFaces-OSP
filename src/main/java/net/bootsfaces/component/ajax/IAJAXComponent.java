package net.bootsfaces.component.ajax;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.component.behavior.ClientBehavior;

public interface IAJAXComponent {
	/**
	 * Whether the Button submits the form with AJAX.
	 * <P>
	 * 
	 * @return true, if AJAX is to be activated.
	 */
	public boolean isAjax();

	/**
	 * Boolean value to specify if the button is disabled.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isDisabled();

	/**
	 * Flag indicating that, if this component is activated by the user,
	 * notifications should be delivered to interested listeners and actions
	 * immediately (that is, during Apply Request Values phase) rather than
	 * waiting until Invoke Application phase. Default is false.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public boolean isImmediate();

	/**
	 * <p class="changed_added_2_0">
	 * This is a default implementation of
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder#getClientBehaviors}
	 * . <code>UIComponent</code> does not implement the
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} interface,
	 * but provides default implementations for the methods defined by
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} to simplify
	 * subclass implementations. Subclasses that wish to support the
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} contract must
	 * declare that the subclass implements
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder}, and must add
	 * an implementation of
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder#getEventNames}
	 * .
	 * </p>
	 *
	 * @since 2.0
	 */
	public Map<String, List<ClientBehavior>> getClientBehaviors();

	/**
	 * <p class="changed_added_2_0">
	 * This is a default implementation of
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder#getDefaultEventName}
	 * . <code>UIComponent</code> does not implement the
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} interface,
	 * but provides default implementations for the methods defined by
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} to simplify
	 * subclass implementations. Subclasses that wish to support the
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} contract must
	 * declare that the subclass implements
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder}, and must
	 * provide an implementation of
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder#getEventNames}
	 * .
	 * </p>
	 */
	public String getDefaultEventName();

	/**
	 * <p class="changed_added_2_0">
	 * This is a default implementation of
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder#getEventNames}
	 * . <code>UIComponent</code> does not implement the
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} interface,
	 * but provides default implementations for the methods defined by
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} to simplify
	 * subclass implementations. Subclasses that wish to support the
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder} contract must
	 * declare that the subclass implements
	 * {@link javax.faces.component.behavior.ClientBehaviorHolder}, and must
	 * override this method to return a non-Empty <code>Collection</code> of the
	 * client event names that the component supports.
	 * </p>
	 *
	 * @since 2.0
	 */
	public Collection<String> getEventNames();

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).Ø
	 * 
	 * @return
	 */
	public Map<String, String> getJQueryEvents();

	/**
	 * Comma or space separated list of ids or search expressions denoting which
	 * values are to be sent to the server.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getProcess();

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getUpdate();

	/**
	 * The onclick attribute.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOnclick();

	/**
	 * Javascript to be executed when ajax completes with success.
	 * <P>
	 * 
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	public String getOncomplete();
}
