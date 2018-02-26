package es.netkia.languages;

import java.util.HashMap;

public class Language implements ILanguage {

	protected HashMap<String,String> index;

	public Language() {
		this.index = new HashMap<String,String>();
	}

	@Override
	public String get(String property) {
		if(this.index.containsKey(property)) {
			return this.index.get(property);
		}
		else {
			return "[error]";
		}
	}

	public static String getCode(String name) {
		switch(name.toLowerCase()) {
			case "english":
				return "en";
			case "spanish":
				return "es";
			default:
				return "x";
		}
	}

}
