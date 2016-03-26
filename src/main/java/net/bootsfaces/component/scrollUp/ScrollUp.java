/**
 *  Copyright 2014-2016 Dario D'Urzo
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
package net.bootsfaces.component.scrollUp;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PreRenderComponentEvent;

import net.bootsfaces.C;
import net.bootsfaces.listeners.AddResourcesListener;
import net.bootsfaces.render.A;
import net.bootsfaces.utils.BsfUtils;

/** This class holds the attributes of &lt;b:scrollup /&gt;. */
@ResourceDependencies({@ResourceDependency(library = "bsf", name = "css/scrollup.css", target = "head")})
@FacesComponent(C.SCROLL_COMPONENT_TYPE)
public class ScrollUp extends UIComponentBase {
	// Static internal references
    public static final String COMPONENT_TYPE =C.SCROLL_COMPONENT_TYPE;
    public static final String COMPONENT_FAMILY = C.BSFCOMPONENT;

    public ScrollUp() {
        setRendererType(null); // this component renders itself
        AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "jq/jquery.js");
		AddResourcesListener.addResourceToHeadButAfterJQuery(C.BSF_LIBRARY, "js/jquery.scrollUp.min.js");
    }
    
	public void setValueExpression(String name, ValueExpression binding) {
		name = BsfUtils.snakeCaseToCamelCase(name);
		super.setValueExpression(name, binding);
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
//		super.encodeBegin(context);
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		if (!isRendered()) {
			return;
		}
		Map<String, Object> attrs = getAttributes();
		ResponseWriter rw = context.getResponseWriter();


        context.getApplication().publishEvent(context,
                                              PreRenderComponentEvent.class,
                                              this);

		String id = getClientId();
		String scrollName = (String)attrs.get("name");
		String scrollDistance = A.asString(attrs.get("distance"));
		String scrollFrom = A.asString(attrs.get("from"));
		String scrollSpeed = A.asString(attrs.get("speed"));
		String easingType = A.asString(attrs.get("easing"));
		String animation = A.asString(attrs.get("animation"));
		String animationSpeed = A.asString(attrs.get("animationSpeed"));
		String scrollText = A.asString(attrs.get("text"));
		String scrollTitle = A.asString(attrs.get("title"));
		if(scrollTitle != null && !"false".equalsIgnoreCase(scrollTitle)) scrollTitle = "'" + scrollTitle + "'";
		String scrollImg = A.asString(attrs.get("image"));
		String scrollTrigger = A.asString(attrs.get("trigger"));
		if(scrollTrigger != null && !"false".equalsIgnoreCase(scrollTrigger)) scrollTrigger = "'" + scrollTrigger + "'";
		String scrollTarget =  A.asString(attrs.get("target"));
		if(scrollTarget != null && !"false".equalsIgnoreCase(scrollTarget)) scrollTarget = "'" + scrollTarget + "'";
		String scrollOverlay = A.asString(attrs.get("overlay"));
		if(scrollOverlay != null && !"false".equalsIgnoreCase(scrollOverlay)) scrollOverlay = "'" + scrollOverlay + "'";
		

		rw.append("<script id='" + id + "' type='text/javascript'>");
		rw.append("\r\n");
		rw.append("$(function() {");
		rw.append("\r\n");
		rw.append("		$.scrollUp({");
		rw.append("\r\n");
		if(scrollName != null) rw.append("			scrollName: '" + scrollName + "', \r\n");
		if(scrollDistance != null) rw.append("		scrollDistance: " + scrollDistance + ", \r\n");
		if(scrollFrom != null) rw.append("			scrollFrom: '" + scrollFrom + "', \r\n");
		if(scrollSpeed != null) rw.append("			scrollSpeed: " + scrollSpeed + ", \r\n");
		if(easingType != null) rw.append("			easingType: '" + easingType + "', \r\n");
		if(animation != null) rw.append("			animation: '" + animation + "', \r\n");
		if(animationSpeed != null) rw.append("		animationSpeed: " + animationSpeed + ", \r\n");
		if(scrollText != null) rw.append("			scrollText: '" + scrollText + "', \r\n");
		if(scrollTitle != null) rw.append("			scrollTitle: " + scrollTitle.toString() + ", \r\n");
		if(scrollImg != null) rw.append("			scrollImg: " + scrollImg.toString() + ", \r\n");
		if(scrollTrigger != null) rw.append("		scrollTrigger: " + scrollTrigger.toString() + ", \r\n");
		if(scrollTarget != null) rw.append("		scrollTarget: " + scrollTarget.toString() + ", \r\n");
		if(scrollOverlay != null) rw.append("		activeOverlay: " + scrollOverlay.toString() + ", \r\n");
		rw.append("			zIndex: 2147483647");
		rw.append("\r\n");
		rw.append("		});");
		rw.append("\r\n");
		rw.append("});");
		rw.append("\r\n");
		rw.append("</script>");
	}

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
}