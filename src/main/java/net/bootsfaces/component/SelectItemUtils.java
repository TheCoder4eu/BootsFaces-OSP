/**
 *  Copyright 2014-2016 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
 *
 *  This file is part of BootsFaces.
 *
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */
package net.bootsfaces.component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * Collection of helper methods dealing with f:selectItem and f:selectItems.
 *
 * @author Stephan Rauh
 *
 */
public class SelectItemUtils {
    @SuppressWarnings("rawtypes")
    public static List<SelectItem> collectOptions(FacesContext context, UIComponent menu) {
        List<SelectItem> items = new ArrayList<SelectItem>();

        List<UIComponent> selectItems = menu.getChildren();
        for (UIComponent kid : selectItems) {
            if (kid instanceof UISelectItem) {
                UISelectItem option = (UISelectItem) kid;
                items.add(toSelectItem(option));
            } else if (kid instanceof UISelectItems) {

                UISelectItems uiSelectItems = ((UISelectItems) kid);
                Object value = uiSelectItems.getValue();

                if (value != null) {
                    if (value instanceof SelectItem) {
                        items.add((SelectItem)value);

                    } else {
                        if (value.getClass().isArray()) {
                            for (int i = 0; i < Array.getLength(value); i++) {
                                Object item = Array.get(value, i);

                                if (item instanceof SelectItem)
                                    items.add((SelectItem)item);
                                else
                                    items.add(createSelectItem(context, uiSelectItems, item, null));
                            }
                        } else if (value instanceof Map) {
                            Map map = (Map) value;

                            for (Iterator it = map.keySet().iterator(); it.hasNext();) {
                                Object key = it.next();

                                items.add(createSelectItem(context, uiSelectItems, map.get(key), String.valueOf(key)));
                            }
                        } else if (value instanceof Collection) {
                            Collection collection = (Collection) value;

                            for (Iterator it = collection.iterator(); it.hasNext();) {
                                Object item = it.next();
                                if (item instanceof SelectItem)
                                    items.add((SelectItem)item);
                                else
                                    items.add(createSelectItem(context, uiSelectItems, item, null));
                            }
                        }
                    }
                }

            }
        }
        return items;
    }

    private static SelectItem toSelectItem(UISelectItem option) {
		SelectItem item = new SelectItem();
		item.setDescription(option.getItemDescription());
		item.setDisabled(option.isItemDisabled());
		item.setEscape(option.isItemEscaped());
		item.setLabel(option.getItemLabel());
		item.setNoSelectionOption(option.isNoSelectionOption());
		item.setValue(option.getItemValue());
		return item;
	}

	/**
     * Copied from the InputRenderer class of PrimeFaces 5.1.
     *
     * @param context
     * @param uiSelectItems
     * @param value
     * @param label
     * @return
     */
    private static SelectItem createSelectItem(FacesContext context, UISelectItems uiSelectItems, Object value,
            Object label) {
        String var = (String) uiSelectItems.getAttributes().get("var");
        Map<String, Object> attrs = uiSelectItems.getAttributes();
        Map<String, Object> requestMap = context.getExternalContext().getRequestMap();

        if (var != null) {
            requestMap.put(var, value);
        }

        Object itemLabelValue = attrs.get("itemLabel");
        Object itemValue = attrs.get("itemValue");
        String description = (String) attrs.get("itemDescription");
        Object itemDisabled = attrs.get("itemDisabled");
        Object itemEscaped = attrs.get("itemLabelEscaped");
        Object noSelection = attrs.get("noSelectionOption");

        if (itemValue == null) {
            itemValue = value;
        }

        if (itemLabelValue == null) {
            itemLabelValue = label;
        }

        String itemLabel = itemLabelValue == null ? String.valueOf(value) : String.valueOf(itemLabelValue);
        boolean disabled = itemDisabled == null ? false : Boolean.valueOf(itemDisabled.toString());
        boolean escaped = itemEscaped == null ? false : Boolean.valueOf(itemEscaped.toString());
        boolean noSelectionOption = noSelection == null ? false : Boolean.valueOf(noSelection.toString());

        if (var != null) {
            requestMap.remove(var);
        }

        return new SelectItem(itemValue, itemLabel, description, disabled, escaped, noSelectionOption);
    }


}
