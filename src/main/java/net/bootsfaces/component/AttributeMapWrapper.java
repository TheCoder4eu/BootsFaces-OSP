package net.bootsfaces.component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AttributeMapWrapper implements Map<String, Object> {

	private Map<String, Object> realMap;
	
//	private Map<String, Object> types = new HashMap<String, Object>();

	public AttributeMapWrapper(Object component, Map<String, Object> realMap) {
		this.realMap = realMap;
//		Method[] methods = component.getClass().getMethods();
//		for (Method m: methods) {
//			if (m.getName().startsWith("get") && m.getParameterCount()==0) {
//				String attribute = m.getName().substring(3,4).toLowerCase() + m.getName().substring(4);
//				Object type = m.getReturnType();
//				types.put(attribute, type);
//			}
//		}
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
		try {
			if ("class".equals(key))
				realMap.put("styleClass", value);
			if (("large-screen".equals(key)) || ("largeScreen".equals(key)))
				key = "colLg";
			if (("medium-screen".equals(key)) || ("mediumScreen".equals(key)))
				key = "colMd";
			if (("small-screen".equals(key)) || ("smallScreen".equals(key)))
				key = "colSm";
			if (("tiny-screen".equals(key)) || ("tinyScreen".equals(key)))
				key = "colXs";
			if ("class".equals(key))
				key = "styleClass";

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
				Object newValue = value;
				if (key.startsWith("tooltip-delay")) {
					newValue = new Integer((String) value);
				}
				if (newKey.toString().equals("closeOnEscape")) {
					newValue = new Boolean((String) value);
				}
				else newValue = convertValueToType(newKey.toString(), value);
				realMap.put(key, value); // for the sake of compatibility
				return realMap.put(newKey.toString(), newValue);
			}
			return realMap.put(key, convertValueToType(key, value));
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	/**
	 * This converter works only for basic type mapper
	 * @param key
	 * @param value
	 * @return
	 */
	public Object convertValueToType(String key, Object value) {
//		Object type = types.get(key);
//		if (type != null && type != Object.class) 
//			System.out.println(type);
		return value;
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
