package net.bootsfaces.component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AttributeMapWrapper implements Map<String, Object> {

	private Map<String, Object> realMap;

	private String componentName;

	private static Map<String, Object> types = new HashMap<String, Object>();

	private static Map<String, String> attributeNames = new HashMap<String, String>();

	static {
		attributeNames.put("large-screen", "colLg");
		attributeNames.put("largeScreen", "colLg");
		attributeNames.put("medium-screen", "colMd");
		attributeNames.put("mediumScreen", "colMd");
		attributeNames.put("small-screen", "colSm");
		attributeNames.put("smallScreen", "colSm");
		attributeNames.put("tinyScreen", "colXs");
		attributeNames.put("tiny-screen", "colXs");
	}

	public AttributeMapWrapper(Object component, Map<String, Object> realMap) {
		this.realMap = realMap;
		componentName = component.getClass().getName();
		if (!types.containsKey(componentName)) {
			synchronized (types) {
				if (!types.containsKey(componentName)) {
					Method[] methods = component.getClass().getMethods();
					for (Method m : methods) {
						if (m.getName().startsWith("get") && m.getParameterCount() == 0) {
							String attribute = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4);
							String type = m.getReturnType().getTypeName();
							if (null != type && (!"java.lang.Class".equals(type)) && (!"java.lang.Object".equals(type))
									&& (!"java.lang.String".equals(type)) && (!type.startsWith("java.util"))
									&& (!type.startsWith("javax."))

							) {
								types.put(componentName + "." + attribute, type);
							}
						} else if (m.getName().startsWith("is") && m.getParameterCount() == 0) {
							String attribute = m.getName().substring(2, 3).toLowerCase() + m.getName().substring(3);
							String type = m.getReturnType().getTypeName();
							types.put(componentName + "." + attribute, type);
						}
					}
					types.put(componentName, true);
				}
			}
		}
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

			if (key != null && key.indexOf('-') > 0) {

				String realAttributeName = findRealAttributeName(key);
				Object typedValue = convertValueToType(realAttributeName, value);
				realMap.put(key, value); // for the sake of compatibility
				return realMap.put(realAttributeName, typedValue);
			}
			return realMap.put(key, value);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	private String findRealAttributeName(String key) {
		if (attributeNames.containsKey(key)) {
			return attributeNames.get(key);
		}
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
		String realAttributeName = newKey.toString();
		synchronized (attributeNames) {
			if (!attributeNames.containsKey(key))
				attributeNames.put(key, realAttributeName);
		}
		return realAttributeName;
	}

	/**
	 * This converter works only for basic type mapper
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Object convertValueToType(String key, Object value) {
		if (null == value)
			return value;
		Object type = types.get(componentName + "." + key);
		if (type != null && type != Object.class && type != String.class) {
			if ("boolean".equals(type)) {
				if (value instanceof String) {
					return Boolean.valueOf((String) value);
				}
			} else if ("int".equals(type)) {
				if (value instanceof String) {
					return Integer.valueOf((String) value);
				}
			} else {
				System.err.println(
						"This type doesn't support type conversion yet. Please use the camelcase notation for the attribute "
								+ key + " Type to be converted to: " + type);
			}
		}
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
