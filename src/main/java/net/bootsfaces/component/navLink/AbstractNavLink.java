package net.bootsfaces.component.navLink;

import java.util.Collection;
import java.util.Map;

import net.bootsfaces.component.ajax.IAJAXComponent;
import net.bootsfaces.render.IHasTooltip;
import net.bootsfaces.render.IResponsive;

public interface AbstractNavLink extends IHasTooltip, IAJAXComponent, IResponsive {

	String getFamily();

	boolean getRendersChildren();

	/**
	 * returns the subset of AJAX requests that are implemented by jQuery
	 * callback or other non-standard means (such as the onclick event of
	 * b:tabView, which has to be implemented manually).
	 *
	 * @return
	 */
	Map<String, String> getJQueryEvents();

	Collection<String> getEventNames();

	String getDefaultEventName();

	/**
	 * Adds the active state to the link.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	boolean isActive();

	/**
	 * Adds the active state to the link.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setActive(boolean _active);

	/**
	 * Whether the Button submits the form with AJAX.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	boolean isAjax();

	/**
	 * Whether the Button submits the form with AJAX.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setAjax(boolean _ajax);

	/**
	 * An el expression referring to a server side UIComponent instance in a
	 * backing bean.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	javax.faces.component.UIComponent getBinding();

	/**
	 * An el expression referring to a server side UIComponent instance in a
	 * backing bean.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setBinding(javax.faces.component.UIComponent _binding);

	/**
	 * contentClass is optional: if specified, the content (i.e. the anchor tag)
	 * will be displayed with this specific class
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getContentClass();

	/**
	 * contentClass is optional: if specified, the content (i.e. the anchor tag)
	 * will be displayed with this specific class
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setContentClass(String _contentClass);

	/**
	 * Inline style of the content area (i.e the anchor tag).
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getContentStyle();

	/**
	 * Inline style of the content area (i.e the anchor tag).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setContentStyle(String _contentStyle);

	/**
	 * The fragment that is to be appended to the target URL. The # separator is
	 * applied automatically and needs not be included.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getFragment();

	/**
	 * The fragment that is to be appended to the target URL. The # separator is
	 * applied automatically and needs not be included.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setFragment(String _fragment);

	/**
	 * If present, this element is rendered as Header in a menu with the text
	 * specifide by this attribute value: all other attributes will be ignored.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getHeader();

	/**
	 * If present, this element is rendered as Header in a menu with the text
	 * specifide by this attribute value: all other attributes will be ignored.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setHeader(String _header);

	/**
	 * URL to link directly to implement anchor behavior.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getHref();

	/**
	 * URL to link directly to implement anchor behavior.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setHref(String _href);

	/**
	 * Navigation Link Icon, can be one of the Bootstrap's Glyphicons icon
	 * names. Alignment can be specified with the iconAlign attribute.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getIcon();

	/**
	 * Navigation Link Icon, can be one of the Bootstrap's Glyphicons icon
	 * names. Alignment can be specified with the iconAlign attribute.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setIcon(String _icon);

	/**
	 * Alignment can right or left.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getIconAlign();

	/**
	 * Alignment can right or left.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setIconAlign(String _iconAlign);

	/**
	 * Navigation Link Font Awesome Icon, can be one of the Font Awesome icon
	 * names. Alignment can be specified with the iconAlign attribute.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getIconAwesome();

	/**
	 * Navigation Link Font Awesome Icon, can be one of the Font Awesome icon
	 * names. Alignment can be specified with the iconAlign attribute.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setIconAwesome(String _iconAwesome);

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
	boolean isImmediate();

	/**
	 * Flag indicating that, if this component is activated by the user,
	 * notifications should be delivered to interested listeners and actions
	 * immediately (that is, during Apply Request Values phase) rather than
	 * waiting until Invoke Application phase. Default is false.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setImmediate(boolean _immediate);

	/**
	 * <p class="changed_added_2_0">
	 * Return whether or not the view parameters should be encoded into the
	 * target url.
	 * </p>
	 *
	 * @since 2.0
	 */
	boolean isIncludeViewParams();

	/**
	 * <p class="changed_added_2_0">
	 * Set whether or not the page parameters should be encoded into the target
	 * url.
	 * </p>
	 *
	 * @param includeViewParams
	 *            The state of the switch for encoding page parameters
	 *
	 * @since 2.0
	 */
	void setIncludeViewParams(boolean includeViewParams);

	/**
	 * Client side callback to execute when input element loses focus.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnblur();

	/**
	 * Client side callback to execute when input element loses focus.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnblur(String _onblur);

	/**
	 * Client side callback to execute when input element loses focus and its
	 * value has been modified since gaining focus.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnchange();

	/**
	 * Client side callback to execute when input element loses focus and its
	 * value has been modified since gaining focus.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnchange(String _onchange);

	/**
	 * The onclick attribute.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnclick();

	/**
	 * The onclick attribute.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnclick(String _onclick);

	/**
	 * Javascript to be executed when ajax completes with success.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOncomplete();

	/**
	 * Javascript to be executed when ajax completes with success.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOncomplete(String _oncomplete);

	/**
	 * Client side callback to execute when input element is double clicked.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOndblclick();

	/**
	 * Client side callback to execute when input element is double clicked.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOndblclick(String _ondblclick);

	/**
	 * Client side callback to execute when input element receives focus.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnfocus();

	/**
	 * Client side callback to execute when input element receives focus.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnfocus(String _onfocus);

	/**
	 * Client side callback to execute when a key is pressed down over input
	 * element.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnkeydown();

	/**
	 * Client side callback to execute when a key is pressed down over input
	 * element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnkeydown(String _onkeydown);

	/**
	 * Client side callback to execute when a key is pressed and released over
	 * input element.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnkeypress();

	/**
	 * Client side callback to execute when a key is pressed and released over
	 * input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnkeypress(String _onkeypress);

	/**
	 * Client side callback to execute when a key is released over input
	 * element.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnkeyup();

	/**
	 * Client side callback to execute when a key is released over input
	 * element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnkeyup(String _onkeyup);

	/**
	 * Client side callback to execute when a pointer input element is pressed
	 * down over input element.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnmousedown();

	/**
	 * Client side callback to execute when a pointer input element is pressed
	 * down over input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnmousedown(String _onmousedown);

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * within input element.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnmousemove();

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * within input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnmousemove(String _onmousemove);

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * away from input element.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnmouseout();

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * away from input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnmouseout(String _onmouseout);

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * onto input element.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnmouseover();

	/**
	 * Client side callback to execute when a pointer input element is moved
	 * onto input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnmouseover(String _onmouseover);

	/**
	 * Client side callback to execute when a pointer input element is released
	 * over input element.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnmouseup();

	/**
	 * Client side callback to execute when a pointer input element is released
	 * over input element.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnmouseup(String _onmouseup);

	/**
	 * Client side callback to execute when text within input element is
	 * selected by user.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOnselect();

	/**
	 * Client side callback to execute when text within input element is
	 * selected by user.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOnselect(String _onselect);

	/**
	 * The outcome to navigate to.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getOutcome();

	/**
	 * The outcome to navigate to.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setOutcome(String _outcome);

	/**
	 * Comma or space separated list of ids or search expressions denoting which
	 * values are to be sent to the server.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getProcess();

	/**
	 * Comma or space separated list of ids or search expressions denoting which
	 * values are to be sent to the server.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setProcess(String _process);

	/**
	 * Inline style
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getStyle();

	/**
	 * Inline style
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setStyle(String _style);

	/**
	 * CSS style class
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getStyleClass();

	/**
	 * CSS style class
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setStyleClass(String _styleClass);

	/**
	 * The text of the tooltip.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getTooltip();

	/**
	 * The text of the tooltip.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setTooltip(String _tooltip);

	/**
	 * Where is the tooltip div generated? That's primarily a technical value
	 * that can be used to fix rendering error in special cases. Also see
	 * data-container in the documentation of Bootstrap. The default value is
	 * body.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getTooltipContainer();

	/**
	 * Where is the tooltip div generated? That's primarily a technical value
	 * that can be used to fix rendering error in special cases. Also see
	 * data-container in the documentation of Bootstrap. The default value is
	 * body.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setTooltipContainer(String _tooltipContainer);

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	int getTooltipDelay();

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setTooltipDelay(int _tooltipDelay);

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	int getTooltipDelayHide();

	/**
	 * The tooltip is hidden with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setTooltipDelayHide(int _tooltipDelayHide);

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	int getTooltipDelayShow();

	/**
	 * The tooltip is shown with a delay. This value is the delay in
	 * milliseconds. Defaults to 0 (no delay).
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setTooltipDelayShow(int _tooltipDelayShow);

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom".
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getTooltipPosition();

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom",
	 * "right", "left", "auto", "auto top", "auto bottom", "auto right" and
	 * "auto left". Default to "bottom".
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setTooltipPosition(String _tooltipPosition);

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	String getUpdate();

	/**
	 * Component(s) to be updated with ajax.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setUpdate(String _update);

	/**
	 * Boolean value to specify if the button is disabled.
	 * <P>
	 *
	 * @return Returns the value of the attribute, or null, if it hasn't been
	 *         set by the JSF file.
	 */
	boolean isDisabled();

	/**
	 * Boolean value to specify if the button is disabled.
	 * <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	void setDisabled(boolean _disabled);

	public Object getValue();

	public boolean isRendered();

	/**
	 * Boolean value: if true the icon will spin. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public boolean isIconSpin();

	/**
	 * Flip the icon: can be H (horizontal) or V (vertical). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconFlip();

	/**
	 * Rotate 90 degrees the icon: Can be L,R. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconRotate();

	/**
	 * Icon Size: legal values are lg, 2x, 3x, 4x, 5x. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getIconSize();


}