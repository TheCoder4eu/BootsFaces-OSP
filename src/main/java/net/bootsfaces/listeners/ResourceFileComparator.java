package net.bootsfaces.listeners;

import java.util.Comparator;

import jakarta.faces.component.UIComponent;

public class ResourceFileComparator implements Comparator<UIComponent> {
	@Override
	public int compare(UIComponent o1, UIComponent o2) {
		String name1 = (String) o1.getAttributes().get("name");
		String name2 = (String) o2.getAttributes().get("name");
		if (name1 == null)
			return 1;
		if (name2 == null)
			return -1;
		if (name1.endsWith(".js") && (!(name2.endsWith(".js"))))
			return 1;
		if (name2.endsWith(".js") && (!(name1.endsWith(".js"))))
			return -1;
		if (name1.endsWith(".js")) {
			name1 = renameJSFile(name1);
		}
		if (name2.endsWith(".js")) {
			name2 = renameJSFile(name2);
		}
		int result = name1.compareTo(name2);

		return result;
	}

	private String renameJSFile(String name) {
		name = name.toLowerCase();
		String libname = name;
		int pos = name.lastIndexOf("/");
		if (pos >= 0) {
			libname = name.substring(pos+1);
		}
		if (libname.contains("jquery-ui"))
			name = "2" + libname.replace(".js", ""); // make it the second JS file, while still distinguishing between jquery-ui.js and jquery-ui-plugins.js
		else if (libname.contains("jquery")) {
			name = "1" + libname.replace(".js", ""); // make it the first JS file, while still distinguishing between jquery.js and jquery-plugins.js
		}
		else if (name.contains("ui/core.js"))
			name = "3.js"; // make it the third JS file
		else if (name.contains("ui/widget.js"))
			name = "4.js"; // make it the second last JS file
		else if (name.contains("bsf.js"))
			name = "zzz.js"; // make it the last JS file
		else
			name = "keep.js"; // don't move it
		return name;
	}
}
