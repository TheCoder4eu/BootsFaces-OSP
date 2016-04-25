package net.bootsfaces.listeners;

import java.util.Comparator;

import javax.faces.component.UIComponent;

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
		if (name.contains("jquery-ui"))
			name = "2.js"; // make it the second JS file
		else if (name.contains("jquery"))
			name = "1.js"; // make it the first JS file
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
