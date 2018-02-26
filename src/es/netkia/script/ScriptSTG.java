package es.netkia.script;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import es.netkia.io.Converter;
import es.netkia.io.FileManager;

public class ScriptSTG extends Script {	
	
	public ScriptSTG(List<String> lines, HashMap<String,String> properties) {
		super(lines,properties);
	}
	
	public void generate(String destination) {
		Converter c = new Converter(this.schema);
		String dirPath = destination+File.separator+this.schema;
		
		String script = String.join("\n"
			, "CREATE TABLE \""+ this.schema +"\".\""+ this.name +"\" "
			, "( "+ c.convert(this.columns)
			, ") SEGMENT CREATION IMMEDIATE"
			, "PCTFREE "+ properties.get("pctfree") +" PCTUSED "+ properties.get("pctused") +" INITRANS "+ properties.get("initrans") +" MAXTRANS "+ properties.get("maxtrans")
			, "NOCOMPRESS LOGGING"
			, "  STORAGE(INITIAL "+ properties.get("initial") +" NEXT "+ properties.get("next") +" MINEXTENTS "+ properties.get("minextents") +" MAXEXTENTS "+ properties.get("maxextents")
			, "  PCTINCREASE "+ properties.get("pctincrease") +" FREELISTS "+ properties.get("freelists") +" FREELIST GROUPS "+ properties.get("groups")
			, "  BUFFER_POOL "+ properties.get("bufferpool") +" FLASH_CACHE "+ properties.get("flashcache") +" CELL_FLASH_CACHE "+ properties.get("cellflashcache") +")"
			, "  TABLESPACE \""+ this.tablespace +"\";\n"
		);
		
		FileManager.createDirectoryIfNotExists(dirPath);
		FileManager.writeSQL(dirPath+File.separator+this.name, script);
	}
	
	public String getDivision() {
		return this.schema;
	}

	public String getName() {
		return this.name;
	}

	public String getTablespace() {
		return this.tablespace;
	}
}
