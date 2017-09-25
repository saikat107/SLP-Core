package slp.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Util {
	public static List<File> getFiles(File root) {
		if (root.isFile()) return Collections.singletonList(root);
		List<File> files = new ArrayList<>();
		for (String child : root.list()) {
			File file = new File(root, child);
			if (file.isFile()) {
				files.add(file);
			}
			else if (file.isDirectory()) files.addAll(getFiles(file));
		}
		return files;
	}
	
	public static void println(Object message){
		Util.print(message);
		System.out.println();
	}
	
	public static void print(Object message){
		if(message instanceof Collection){
			Collection c = (Collection) message;
			Util.print("[");
			for(Object obj: c){
				Util.print(obj);
				Util.print(", ");
			}
			Util.print("]");
		}
		else if (message instanceof Map){
			Map m = (Map) message;
			for(Object key: m.keySet()){
				Util.print(key);
				Util.print(" -> ");
				Util.print(m.get(key));
			}
		}
		else {
			System.out.print(message.toString());
		}
	}
	
	public static void logln(Object message){
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		StackTraceElement currentTrace = traces[2];
		System.out.print(currentTrace + " ");
		Util.println(message);
	}
}
