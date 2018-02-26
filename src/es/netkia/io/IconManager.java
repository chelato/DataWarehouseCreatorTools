package es.netkia.io;

import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class IconManager {

	public static HashMap<String,ImageIcon> getAppIcons() {
		/**
		 * ICONS
		 */
		HashMap<String,ImageIcon> icons;
		icons = new HashMap<String,ImageIcon>();
		
		icons.put("logo"		, IconManager.newIcon("logo.png", 24));
		icons.put("refresh"		, IconManager.newIcon("refresh.png", 24));
		icons.put("remove"		, IconManager.newIcon("remove-from-list.png", 24));
		icons.put("newFile"		, IconManager.newIcon("new-file.png", 28));
		icons.put("editFile"	, IconManager.newIcon("edit-file.png", 28));
		icons.put("deleteFile"	, IconManager.newIcon("delete-file.png", 28));
		icons.put("valid"		, IconManager.newIcon("valid.png", 20));
		icons.put("invalid"		, IconManager.newIcon("invalid.png", 20));
		icons.put("idle"		, IconManager.newIcon("idle.png", 20));
		
		return icons;
	}
	
	private static ImageIcon newIcon(String name, int dim) {
		return new ImageIcon(new ImageIcon(PathManager.ICONS + File.separator + name).getImage().getScaledInstance(dim, dim, java.awt.Image.SCALE_SMOOTH));
	}
}
