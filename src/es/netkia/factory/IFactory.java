package es.netkia.factory;

import java.util.HashMap;
import java.util.List;

public interface IFactory {
	
	public static <T> T build(List<String> lines, HashMap<String,String> properties) {
		return null;
	}

}
