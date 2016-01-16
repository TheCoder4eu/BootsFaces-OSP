import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MergeTaglib {
	public static String mergedContent = "";

	public static void main(String[] args) throws FileNotFoundException {
		File folder = new File("src-gen/net/bootsfaces/component");
		File[] folders = folder.listFiles();
		for (File f : folders) {
			File[] files = f.listFiles();
			for (File ff : files) {
				if (ff.getName().endsWith(".taglib.xml"))
					addFile(ff);
			}
		}

		mergedContent = header + mergedContent + footer;
		mergedContent = mergedContent.replace("\t", "  ");

		System.out.println(mergedContent);
		PrintWriter out = new PrintWriter("src/main/meta/META-INF/bootsfaces-b.taglib.xml");
		out.println(mergedContent);
		out.close();

		// String content = new Scanner(new
		// File("taglib.xml")).useDelimiter("\\Z").next();

	}

	public static void addFile(File f) throws FileNotFoundException {
		String content = new Scanner(f).useDelimiter("\\Z").next();
		int start = content.indexOf("<tag>");
		int end = content.indexOf("</tag>");
		mergedContent += "\n\n\n<!-- *********** " + f.getName().replace("xhtml", "")
				+ " ************************* --> \n\n  ";
		mergedContent += content.substring(start, end + 6);
	}

	private static String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<facelet-taglib xmlns=\"http://java.sun.com/xml/ns/javaee\"\n"
			+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "	xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facelettaglibrary_2_0.xsd\"\n"
			+ "	version=\"2.0\">\n" + "	<namespace>http://bootsfaces.net/ui</namespace>\n";

	private static String footer = "\n</facelet-taglib>";

}
