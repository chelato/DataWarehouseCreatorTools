package es.netkia.io;

import java.io.File;

public class PathManager {
	
	public final static String APP_HOME 		= System.getProperty("user.dir");
	
	public final static String APP_PROPERTIES 	= APP_HOME + File.separator + "properties.xml";
	
	public final static String ICONS 			= APP_HOME + File.separator + "resources" + File.separator + "icons";
}
