package es.netkia.factory;

import java.util.HashMap;
import java.util.List;

import es.netkia.script.IScript;
import es.netkia.script.ScriptDWH;
import es.netkia.script.ScriptSTG;

public class ScriptFactory implements IFactory {
	
	public static IScript build(List<String> lines, HashMap<String,String> properties) {
		IScript script = null;
		
		if(lines.size() > 0) {
			if(lines.get(0).equalsIgnoreCase("STG"))
				script = new ScriptSTG(lines, properties);
			
			else if(lines.get(0).equalsIgnoreCase("DWH"))
				script = new ScriptDWH(lines, properties);
		}
		
		return script;
	}
}
