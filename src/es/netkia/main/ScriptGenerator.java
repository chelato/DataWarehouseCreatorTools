package es.netkia.main;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import es.netkia.factory.ScriptFactory;
import es.netkia.io.FileManager;
import es.netkia.script.IScript;

public class ScriptGenerator {
	
	/**
	 * Load specific file to generate the script
	 * 
	 * @param file
	 */
	public static boolean load(File file, String destination, HashMap<String,String> properties) {
		boolean generated = false;
		
		try {
			List<String> lines = FileManager.readAllLines(file);

			IScript script = ScriptFactory.build(lines,properties);
			if(script != null) {
				script.generate(destination);
				generated = true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return generated;
	}
}
