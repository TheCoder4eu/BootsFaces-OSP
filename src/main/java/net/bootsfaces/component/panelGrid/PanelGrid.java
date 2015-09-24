/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
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

package net.bootsfaces.component.panelGrid;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

import net.bootsfaces.render.Tooltip;

/**
 * This component puts child components in a grid, very much like
 * &lt;h:panelGrid&gt;.
 *
 * <p>
 * Usage:
 * </p>
 * <p>
 * &lt;b:panelGrid id="grid-three-nine" colSpans="3,9" columnClasses="red,green"
 * styleClass="gridStyle" rowClasses="light,dark"&gt;<br>
 * &nbsp;&nbsp;&nbsp;(child components)<br>
 * &lt;/b:panelGrid&gt;
 * </p>
 * <ul>
 * <li>id: the id of the component. It's added to the surrounding &lt;div
 * class="container" &gt;.</li>
 * <li>styleClass: The CSS class assigned to the surrounding &lt;div
 * class="container" &gt;.</li>
 * <li>colSpans: comma-separated list of the Bootstrap columns each components
 * spans. The number are translated to col-lg-%colspan%. In the example, the
 * first component is assigned col-lg-3, the second col-lg-9. The next row start
 * with col-lg-3 again. The column spans must sum up to 12.</li>
 * <li>columnClasses: An optional attribute that allows to assign different CSS
 * classes to each Bootstrap column. If "colSpans" defines more columns than
 * "columnClasses", the columnClasses are repeated cyclically.</li>
 * <li>rowClasses: An optional attribute that allows to assign CSS classes to
 * each Bootstrap row. If there are more rows than row classes, the row classes
 * are repeated cyclically.</li>
 * </ul>
 * 
 * @author Stephan Rauh, http://www.beyondjava.net
 * @since 22.11.2014
 */

@ResourceDependencies({ @ResourceDependency(library = "bsf", name = "css/bsf.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head")})
@FacesComponent("net.bootsfaces.component.panelGrid.PanelGrid")
public class PanelGrid extends UIOutput implements net.bootsfaces.render.IHasTooltip {

	public static final String COMPONENT_TYPE = "net.bootsfaces.component.panelGrid.PanelGrid";

	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";

	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.panelGrid.PanelGrid";

	public PanelGrid() {
		Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

    protected enum PropertyKeys {
colSpans,
columnClasses,
columns,
rowClasses,
size,
style,
styleClass,
tooltip,
tooltipDelay,
tooltipDelayHide,
tooltipDelayShow,
tooltipPosition, tooltipContainer
;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {}

        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }
	

	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering error in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipContainer() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipContainer, "body");
		return  value;
	}
	
	/**
	 * Where is the tooltip div generated? That's primarily a technical value that can be used to fix rendering error in special cases. Also see data-container in the documentation of Bootstrap. The default value is body. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipContainer(String _tooltipContainer) {
	    getStateHelper().put(PropertyKeys.tooltipContainer, _tooltipContainer);
    }
	/**
	 * Comma-separated list of the column spans <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColSpans() {
		String value = (String)getStateHelper().eval(PropertyKeys.colSpans);
		return  value;
	}
	
	/**
	 * Comma-separated list of the column spans <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColSpans(String _colSpans) {
	    getStateHelper().put(PropertyKeys.colSpans, _colSpans);
    }
	

	/**
	 * Comma-separated list of the CSS classes assigned to each column. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColumnClasses() {
		String value = (String)getStateHelper().eval(PropertyKeys.columnClasses);
		return  value;
	}
	
	/**
	 * Comma-separated list of the CSS classes assigned to each column. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColumnClasses(String _columnClasses) {
	    getStateHelper().put(PropertyKeys.columnClasses, _columnClasses);
    }
	

	/**
	 * Synonym to colSpans: comma-separated list of the column spans <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColumns() {
		String value = (String)getStateHelper().eval(PropertyKeys.columns);
		return  value;
	}
	
	/**
	 * Synonym to colSpans: comma-separated list of the column spans <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColumns(String _columns) {
	    getStateHelper().put(PropertyKeys.columns, _columns);
    }
	

	/**
	 * Comma-separated list of the CSS classes assigned to each row. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getRowClasses() {
		String value = (String)getStateHelper().eval(PropertyKeys.rowClasses);
		return  value;
	}
	
	/**
	 * Comma-separated list of the CSS classes assigned to each row. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setRowClasses(String _rowClasses) {
	    getStateHelper().put(PropertyKeys.rowClasses, _rowClasses);
    }
	

	/**
	 * Optional. Legal values are lg, md, sm and xs. Default is lg. Influences the grid size. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSize() {
		String value = (String)getStateHelper().eval(PropertyKeys.size);
		return  value;
	}
	
	/**
	 * Optional. Legal values are lg, md, sm and xs. Default is lg. Influences the grid size. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSize(String _size) {
	    getStateHelper().put(PropertyKeys.size, _size);
    }
	

	/**
	 * CSS inline style of the div surrounding the panel grid. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.style);
		return  value;
	}
	
	/**
	 * CSS inline style of the div surrounding the panel grid. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
	    getStateHelper().put(PropertyKeys.style, _style);
    }
	

	/**
	 * Style class of the div surrounding the panel grid. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.styleClass);
		return  value;
	}
	
	/**
	 * Style class of the div surrounding the panel grid. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyleClass(String _styleClass) {
	    getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }
	

	/**
	 * The text of the tooltip. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltip() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltip);
		return  value;
	}
	
	/**
	 * The text of the tooltip. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltip(String _tooltip) {
	    getStateHelper().put(PropertyKeys.tooltip, _tooltip);
    }
	

	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelay() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelay, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is shown and hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelay(int _tooltipDelay) {
	    getStateHelper().put(PropertyKeys.tooltipDelay, _tooltipDelay);
    }
	

	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayHide() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayHide, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is hidden with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayHide(int _tooltipDelayHide) {
	    getStateHelper().put(PropertyKeys.tooltipDelayHide, _tooltipDelayHide);
    }
	

	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getTooltipDelayShow() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.tooltipDelayShow, 0);
		return (int) value;
	}
	
	/**
	 * The tooltip is shown with a delay. This value is the delay in milliseconds. Defaults to 0 (no delay). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipDelayShow(int _tooltipDelayShow) {
	    getStateHelper().put(PropertyKeys.tooltipDelayShow, _tooltipDelayShow);
    }
	

	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTooltipPosition() {
		String value = (String)getStateHelper().eval(PropertyKeys.tooltipPosition);
		return  value;
	}
	
	/**
	 * Where is the tooltip to be displayed? Possible values: "top", "bottom", "right", "left", "auto", "auto top", "auto bottom", "auto right" and "auto left". Default to "bottom". <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTooltipPosition(String _tooltipPosition) {
	    getStateHelper().put(PropertyKeys.tooltipPosition, _tooltipPosition);
    }
	
}

