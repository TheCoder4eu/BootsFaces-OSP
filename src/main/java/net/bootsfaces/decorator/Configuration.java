package net.bootsfaces.decorator;


public class Configuration {
    public static boolean myFaces = false;

    static {
        StackTraceElement[] stackTrace = new NullPointerException().getStackTrace();
        for (StackTraceElement line : stackTrace) {
            if (line.getClassName().contains("org.apache.myfaces")) {
                myFaces = true;
                break;
            }
            if (line.getClassName().contains("com.sun.faces")) {
                myFaces = false;
                break;
            }
        }
    }
}
