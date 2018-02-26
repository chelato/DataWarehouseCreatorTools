package es.netkia.script;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.netkia.io.Converter;
import es.netkia.io.FileManager;

public class ScriptDWH extends Script {
	private ArrayList<String> foreignKeyList;
	private String tablespaceIndex;
	
	public ScriptDWH(List<String> lines, HashMap<String,String> properties) {
		super(lines,properties);
		this.foreignKeyList = new ArrayList<String>();
		this.tablespaceIndex = lines.get(3);
	}

	public void generate(String destination) {
		Converter c = new Converter(this.schema);
		String dirPath = destination+File.separator+this.schema;
		
		String script = String.join("\n"
			, "CREATE TABLE \""+ this.schema +"\".\""+ this.name +"\" "
			, "( "+ c.convert(this.columns)
			, generateConstraints()
			, ") SEGMENT CREATION IMMEDIATE"
			, "PCTFREE "+ properties.get("pctfree") +" PCTUSED "+ properties.get("pctused") +" INITRANS "+ properties.get("initrans") +" MAXTRANS "+ properties.get("maxtrans")
			, "NOCOMPRESS LOGGING"
			, "  STORAGE(INITIAL "+ properties.get("initial") +" NEXT "+ properties.get("next") +" MINEXTENTS "+ properties.get("minextents") +" MAXEXTENTS "+ properties.get("maxextents")
			, "  PCTINCREASE "+ properties.get("pctincrease") +" FREELISTS "+ properties.get("freelists") +" FREELIST GROUPS "+ properties.get("groups")
			, "  BUFFER_POOL "+ properties.get("bufferpool") +" FLASH_CACHE "+ properties.get("flashcache") +" CELL_FLASH_CACHE "+ properties.get("cellflashcache") +")"
			, "  TABLESPACE \""+ this.tablespace +"\";"
			, ""
			, generateIndexes()
		);
		
		FileManager.createDirectoryIfNotExists(dirPath);
		FileManager.writeSQL(dirPath+File.separator+this.name, script);
	}
	
	private String generateConstraints() {
		String[] nameParts = this.name.split("\\_");
		String newTableName = "";
		String primaryKey = "_PK_NOT_FOUND_";
		String primaryKeyName = "_PK_NOT_FOUND_";
		String foreignKeys = "";
		boolean pkFound = false;
		
		// Classify PK and FK
		for(String column : this.columns) {
			String[] colParts = column.split("\\t");
			
			if(colParts.length == 3) {
				if(colParts[1].equalsIgnoreCase("pk") && !pkFound) {
					pkFound = true;
					primaryKey = colParts[0];
					primaryKeyName = nameParts[0] +"_"+ nameParts[1] +"_"+ colParts[0] +"_PK";
				}
				if(colParts[1].equalsIgnoreCase("fk")) {
					this.foreignKeyList.add(colParts[0]);
				}
			}
		}
		
		// Build new table name without schema
		for(int i=0; i<nameParts.length; i++) {
			if(!nameParts[i].equalsIgnoreCase("dm") && !nameParts[i].equalsIgnoreCase("hc") && !nameParts[i].isEmpty())
				newTableName += nameParts[i]+"_";
		}
		
		// Build foreign keys
		for(int i=0; i<this.foreignKeyList.size(); i++) {
			foreignKeys += "\tCONSTRAINT \"FK_"+ newTableName + this.foreignKeyList.get(i) +"\" FOREIGN KEY (\""+ this.foreignKeyList.get(i) +"\")\n";
			foreignKeys += "\t  REFERENCES \"_SCHEMA_\".\"_TABLE_\" (\"_COLUMN_\") RELY DISABLE";
			if(i != this.foreignKeyList.size()-1) foreignKeys += ",\n";
		}
		
		// Build constraints
		String constraints = String.join("\n"
			, "\tCONSTRAINT \""+ primaryKeyName +"\" PRIMARY KEY (\""+ primaryKey +"\") RELY"
			, "\tUSING INDEX PCTFREE "+ properties.get("pctfree") +" INITRANS 2 MAXTRANS "+ properties.get("maxtrans") +" COMPUTE STATISTICS NOLOGGING"
			, "\t  STORAGE(INITIAL "+ properties.get("initial") +" NEXT "+ properties.get("next") +" MINEXTENTS "+ properties.get("minextents") +" MAXEXTENTS "+ properties.get("maxextents")
			, "\t  PCTINCREASE "+ properties.get("pctincrease") +" FREELISTS "+ properties.get("freelists") +" FREELIST GROUPS "+ properties.get("groups")
			, "\t  BUFFER_POOL "+ properties.get("bufferpool") +" FLASH_CACHE "+ properties.get("flashcache") +" CELL_FLASH_CACHE "+ properties.get("cellflashcache") +")"
			, "\t  TABLESPACE \""+ this.tablespaceIndex +"\" ENABLE,"
			, foreignKeys
		);
		
		return constraints;
	}
	
	private String generateIndexes() {
		String indexes = "";
		
		for(String foreignKey : this.foreignKeyList) {
			String index = String.join("\n"
				, "CREATE INDEX \"DWH\".\"IDX_"+ this.name +"_"+ foreignKey +"\" ON \"DWH\".\""+ this.name +"\" (\""+ foreignKey +"\")"
				, "PCTFREE "+ properties.get("pctfree") +" INITRANS 2 MAXTRANS "+ properties.get("maxtrans") +" COMPUTE STATISTICS NOLOGGING"
				, "  STORAGE(INITIAL "+ properties.get("initial") +" NEXT "+ properties.get("next") +" MINEXTENTS "+ properties.get("minextents") +" MAXEXTENTS "+ properties.get("maxextents")
				, "  PCTINCREASE "+ properties.get("pctincrease") +" FREELISTS "+ properties.get("freelists") +" FREELIST GROUPS "+ properties.get("groups")
				, "  BUFFER_POOL "+ properties.get("bufferpool") +" FLASH_CACHE "+ properties.get("flashcache") +" CELL_FLASH_CACHE "+ properties.get("cellflashcache") +")"
				, "  TABLESPACE \""+ this.tablespaceIndex +"\";\n\n"
			);
			
			indexes += index;
		}
		
		return indexes;
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