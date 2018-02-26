package es.netkia.factory;

import es.netkia.languages.English;
import es.netkia.languages.ILanguage;
import es.netkia.languages.Spanish;

public class LanguageFactory implements IFactory {

	public static ILanguage build(String languageCode) {
		if(languageCode.equalsIgnoreCase("en"))
			return new English();

		else if(languageCode.equalsIgnoreCase("es"))
			return new Spanish();

		else return null;
	}
}
