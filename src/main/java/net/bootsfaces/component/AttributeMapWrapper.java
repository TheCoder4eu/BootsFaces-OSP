package net.bootsfaces.component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AttributeMapWrapper implements Map<String, Object> {

	private Map<String, Object> realMap;

	public AttributeMapWrapper(Map<String, Object> realMap) {
		this.realMap = realMap;
	}

	@Override
	public int size() {
		return realMap.size();
	}

	@Override
	public boolean isEmpty() {
		return realMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return realMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return realMap.containsKey(value);
	}

	@Override
	public Object get(Object key) {
		return realMap.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		if ("class".equals(key))
			realMap.put("styleClass", value);
		if (key != null && key.indexOf('-') > 0) {
			StringBuilder newKey = new StringBuilder(key.length());
			boolean toUpperCase = false;
			for (char c : key.toCharArray()) {
				if (c == '-')
					toUpperCase = true;
				else {
					if (toUpperCase) {
						toUpperCase = false;
						c = Character.toUpperCase(c);
					}
					newKey.append(c);
				}
			}
			Object newValue=value;
			if (key.startsWith("col-") || key.startsWith("tooltip-delay")) {
				newValue = new Integer((String) value);
			}
			realMap.put(key, value); // for the sake of compatibility
			return realMap.put(newKey.toString(), newValue);
		}
		return realMap.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return realMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		realMap.putAll(m);

	}

	@Override
	public void clear() {
		realMap.clear();

	}

	@Override
	public Set<String> keySet() {
		return realMap.keySet();
	}

	@Override
	public Collection<Object> values() {
		return realMap.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return realMap.entrySet();
	}

}
