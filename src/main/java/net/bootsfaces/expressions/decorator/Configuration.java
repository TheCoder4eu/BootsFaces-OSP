package net.bootsfaces.expressions.decorator;

public class Configuration {
	public static final boolean myFaces = determineJSFImplementation();

	static boolean determineJSFImplementation() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (StackTraceElement line : stackTrace) {
			if (line.getClassName().contains("org.apache.myfaces")) {
				return true;
			}
			if (line.getClassName().contains("com.sun.faces")) {
				return false;
			}
		}
		return false;
	}
}
