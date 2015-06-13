package net.bootsfaces.render;

public interface IHasTooltip  {

	/**
	 * The text of the tooltip. <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip();

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelay();

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayHide();

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayShow();

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <br />
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipPosition();
}
