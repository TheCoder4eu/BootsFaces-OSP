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

package net.bootsfaces.component.column;

import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

import net.bootsfaces.component.AttributeMapWrapper;
import net.bootsfaces.render.Tooltip;


/** This class holds the attributes of &lt;b:column /&gt;. */
@ResourceDependencies({ 
    @ResourceDependency(library = "bsf", name = "css/core.css", target = "head"),
	@ResourceDependency(library = "bsf", name = "css/tooltip.css", target = "head") })
@FacesComponent("net.bootsfaces.component.column.Column")
public class Column extends UIOutput  implements net.bootsfaces.render.IHasTooltip  {
	
	public static final String COMPONENT_TYPE = "net.bootsfaces.component.column.Column";
	
	public static final String COMPONENT_FAMILY = "net.bootsfaces.component";
	
	public static final String DEFAULT_RENDERER = "net.bootsfaces.component.column.Column";
	
	private Map<String, Object> attributes = null;
	
	public Column() {
		
		
	Tooltip.addResourceFile();
		setRendererType(DEFAULT_RENDERER);
	}
	
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		if (attributes == null)
			attributes = new AttributeMapWrapper(super.getAttributes());
		return attributes;
	}
	
	
    protected enum PropertyKeys {
colLg,
colMd,
colSm,
colXs,
largeScreen,
mediumScreen,
smallScreen,
tinyScreen,
dir,
display,
hidden,
offset,
offsetLg,
offsetMd,
offsetSm,
offsetXs,
span,
style,
styleClass,
tooltip,
tooltipContainer,
tooltipDelay,
tooltipDelayHide,
tooltipDelayShow,
tooltipPosition,
visible
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
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColLg() {
		String value = (String)getStateHelper().eval(PropertyKeys.colLg, "-1");
		return  value;
	}
	
	/**
	 * Integer value to specify how many columns to span on large screens (≥1200 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColLg(String _colLg) {
	    getStateHelper().put(PropertyKeys.colLg, _colLg);
    }
	

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColMd() {
		String value = (String)getStateHelper().eval(PropertyKeys.colMd, "-1");
		return  value;
	}
	
	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColMd(String _colMd) {
	    getStateHelper().put(PropertyKeys.colMd, _colMd);
    }
	

	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColSm() {
		String value = (String)getStateHelper().eval(PropertyKeys.colSm, "-1");
		return  value;
	}
	
	/**
	 * Integer value to specify how many columns to span on small screens (≥768p pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColSm(String _colSm) {
	    getStateHelper().put(PropertyKeys.colSm, _colSm);
    }
	

	/**
	 * Integer value to specify how many columns to span on tiny screens (< 768 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getColXs() {
		String value = (String)getStateHelper().eval(PropertyKeys.colXs, "-1");
		return  value;
	}
	
	/**
	 * Integer value to specify how many columns to span on tiny screens (< 768 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setColXs(String _colXs) {
	    getStateHelper().put(PropertyKeys.colXs, _colXs);
    }
	

	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getLargeScreen() {
		String value = (String)getStateHelper().eval(PropertyKeys.largeScreen, "-1");
		return  value;
	}
	
	/**
	 * Alternative spelling to col-lg. Integer value to specify how many columns to span on large screens (≥1200 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setLargeScreen(String _largeScreen) {
	    getStateHelper().put(PropertyKeys.largeScreen, _largeScreen);
    }
	

	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getMediumScreen() {
		String value = (String)getStateHelper().eval(PropertyKeys.mediumScreen, "-1");
		return  value;
	}
	
	/**
	 * Alternative spelling to col-md. Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setMediumScreen(String _mediumScreen) {
	    getStateHelper().put(PropertyKeys.mediumScreen, _mediumScreen);
    }
	

	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSmallScreen() {
		String value = (String)getStateHelper().eval(PropertyKeys.smallScreen, "-1");
		return  value;
	}
	
	/**
	 * Alternative spelling to col-sm. Integer value to specify how many columns to span on small screens (≥768p pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSmallScreen(String _smallScreen) {
	    getStateHelper().put(PropertyKeys.smallScreen, _smallScreen);
    }
	

	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (< 768 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getTinyScreen() {
		String value = (String)getStateHelper().eval(PropertyKeys.tinyScreen, "-1");
		return  value;
	}
	
	/**
	 * Alternative spelling to col-xs. Integer value to specify how many columns to span on tiny screens (< 768 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setTinyScreen(String _tinyScreen) {
	    getStateHelper().put(PropertyKeys.tinyScreen, _tinyScreen);
    }
	

	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphebet, based on the page content). <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDir() {
		String value = (String)getStateHelper().eval(PropertyKeys.dir);
		return  value;
	}
	
	/**
	 * Direction indication for text that does not inherit directionality. Legal values: ltr (Default. Left-to-right text direction), rtl (Right-to-left text direction) and auto (let the browser figure out the direction of your alphebet, based on the page content). <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDir(String _dir) {
	    getStateHelper().put(PropertyKeys.dir, _dir);
    }
	

	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getDisplay() {
		String value = (String)getStateHelper().eval(PropertyKeys.display, "block");
		return  value;
	}
	
	/**
	 * If you use the "visible" attribute, the value of this attribute is added. Legal values: block, inline, inline-block. Default: block. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setDisplay(String _display) {
	    getStateHelper().put(PropertyKeys.display, _display);
    }
	

	/**
	 * This row is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getHidden() {
		String value = (String)getStateHelper().eval(PropertyKeys.hidden);
		return  value;
	}
	
	/**
	 * This row is hidden on a certain screen size and below. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setHidden(String _hidden) {
	    getStateHelper().put(PropertyKeys.hidden, _hidden);
    }
	

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getOffset() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.offset, 0);
		return (int) value;
	}
	
	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffset(int _offset) {
	    getStateHelper().put(PropertyKeys.offset, _offset);
    }
	

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getOffsetLg() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.offsetLg, 0);
		return (int) value;
	}
	
	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetLg(int _offsetLg) {
	    getStateHelper().put(PropertyKeys.offsetLg, _offsetLg);
    }
	

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getOffsetMd() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.offsetMd, 0);
		return (int) value;
	}
	
	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetMd(int _offsetMd) {
	    getStateHelper().put(PropertyKeys.offsetMd, _offsetMd);
    }
	

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getOffsetSm() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.offsetSm, 0);
		return (int) value;
	}
	
	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetSm(int _offsetSm) {
	    getStateHelper().put(PropertyKeys.offsetSm, _offsetSm);
    }
	

	/**
	 * Integer value to specify how many columns to offset. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public int getOffsetXs() {
		Integer value = (Integer)getStateHelper().eval(PropertyKeys.offsetXs, 0);
		return (int) value;
	}
	
	/**
	 * Integer value to specify how many columns to offset. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setOffsetXs(int _offsetXs) {
	    getStateHelper().put(PropertyKeys.offsetXs, _offsetXs);
    }
	

	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getSpan() {
		String value = (String)getStateHelper().eval(PropertyKeys.span, "-1");
		return  value;
	}
	
	/**
	 * Integer value to specify how many columns to span on medium screens (≥992 pixels). The number may optionally by followed by "column" or "columns". Alternative legal values: half, one-third, two-thirds, one-fourth, three-forth. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setSpan(String _span) {
	    getStateHelper().put(PropertyKeys.span, _span);
    }
	

	/**
	 * Inline style of the input element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyle() {
		String value = (String)getStateHelper().eval(PropertyKeys.style);
		return  value;
	}
	
	/**
	 * Inline style of the input element. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setStyle(String _style) {
	    getStateHelper().put(PropertyKeys.style, _style);
    }
	

	/**
	 * Style class of this element. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getStyleClass() {
		String value = (String)getStateHelper().eval(PropertyKeys.styleClass);
		return  value;
	}
	
	/**
	 * Style class of this element. <P>
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
	

	/**
	 * This row is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * @return Returns the value of the attribute, or null, if it hasn't been set by the JSF file.
	 */
	public String getVisible() {
		String value = (String)getStateHelper().eval(PropertyKeys.visible);
		return  value;
	}
	
	/**
	 * This row is shown on a certain screen size and above. Legal values: lg, md, sm, xs. <P>
	 * Usually this method is called internally by the JSF engine.
	 */
	public void setVisible(String _visible) {
	    getStateHelper().put(PropertyKeys.visible, _visible);
    }
	
}

