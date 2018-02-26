package es.netkia.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import es.netkia.script.Script;

public class Converter {
	private String schema;
	private HashMap<String,String> conversions;
	private HashSet<String> cache;

	public Converter(String schema) {
		this.schema = schema;
		this.cache = new HashSet<String>();
		this.conversions = new HashMap<String,String>();

		populateConversions();
	}

	public String convert(ArrayList<String> columns) {
		String convertedColumns = "";

		for(int i=0; i<columns.size(); i++) {
			String col = columns.get(i);
			String[] parts = col.split("\\t");

			if( (this.schema.equalsIgnoreCase("stg") && parts.length!=2) || ((this.schema.equalsIgnoreCase("dwh") && parts.length!=3)) ) {
				convertedColumns += "\t"+ Script.BAD_COLUMN_SYNTAX +"\n";
			}
			else if(!cache.contains(parts[0])) {
				if(i!=0) convertedColumns += "\t";
				if(!parts[0].isEmpty()) {
					convertedColumns += "\""+ parts[0] +"\" ";
					if(this.schema.equalsIgnoreCase("stg"))
						convertedColumns += this.convertColumn(parts[1]);
					else if(this.schema.equalsIgnoreCase("dwh"))
						convertedColumns += this.convertColumn(parts[2]);
				}
				else {
					if(this.schema.equalsIgnoreCase("stg"))
						convertedColumns += Script.EMPTY +" "+ this.convertColumn(parts[1]);
					else if(this.schema.equalsIgnoreCase("dwh"))
						convertedColumns += Script.EMPTY +" "+ this.convertColumn(parts[2]);
				}

				if(this.schema.equalsIgnoreCase("dwh")) {
					if(parts[1].equalsIgnoreCase("pk")) convertedColumns += " "+ Script.AUTO_INCREMENT +",\n";
					else {
						convertedColumns += ",";
						if(i!=columns.size()-1) convertedColumns += "\n";
					}
				}
				else if(i!=columns.size()-1) convertedColumns += ",\n";

				cache.add(parts[0]);
			}
		}

		return convertedColumns;
	}

	private String convertColumn(String type) {
		type = type.trim();
		String[] typeParts = type.split("\\(");
		String conversion = "";

		conversion = conversions.get(typeParts[0].toLowerCase());

		if(type.isEmpty()) {
			conversion = Script.EMPTY;
		}
		else if(conversion == null) {
			conversion = Script.TYPE_NOT_FOUND;
		}
		else if(typeParts.length > 1) {
			conversion = conversion + "(" + typeParts[1].substring(0, typeParts[1].indexOf(")")) +")";
		}

		return conversion.toUpperCase();
	}

	private void populateConversions() {
		conversions.put("bigint", 		"number(19,0)");
		conversions.put("bit", 			"raw");
		conversions.put("blob", 		"blob");
		conversions.put("char", 		"char");
		conversions.put("date", 		"date");
		conversions.put("datetime", 	"date");
		conversions.put("decimal", 		"number");
		conversions.put("double", 		"number");
		conversions.put("enum", 		"varchar2");
		conversions.put("float", 		"number(14,2)");
		conversions.put("int", 			"number");
		conversions.put("integer", 		"number");
		conversions.put("longblob", 	"blob");
		conversions.put("longtext", 	"varchar2(4000)");
		conversions.put("mediumblob", 	"blob");
		conversions.put("mediumint", 	"number(7,0)");
		conversions.put("mediumtext", 	"varchar2(4000)");
		conversions.put("numeric", 		"number");
		conversions.put("real", 		"number");
		conversions.put("set", 			"varchar2");
		conversions.put("smallint", 	"number(5,0)");
		conversions.put("text", 		"varchar2(4000)");
		conversions.put("time", 		"date");
		conversions.put("timestamp", 	"date");
		conversions.put("tinyblob", 	"raw");
		conversions.put("tinyint", 		"number");
		conversions.put("tinytext", 	"varchar2(255)");
		conversions.put("varchar", 		"varchar2");
		conversions.put("year", 		"number");

		conversions.put("varchar2",		"varchar2");
		conversions.put("number",		"number");
	}
}
