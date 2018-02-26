package es.netkia.io;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

	public static final String SQL = ".sql";
	public static final String DAT = ".dat";

	/*
	 * READ OPERATIONS
	 */

	/**
	 * 
	 * @param directory
	 * @return
	 */
	public static File[] getAllFiles(String directory) {
		File dir = new File(directory);
		File[] files = dir.listFiles(file -> !file.isDirectory());

		return files;
	}

	public static File[] getAllFiles(String directory, String ext) {
		File dir = new File(directory);
		File[] files = dir.listFiles(file -> file.getName().endsWith(ext));

		return files;
	}

	public static List<String> readAllLines(File file) {
		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
			list = stream
					.map(String::toUpperCase)
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static File getFile(String path) {
		File file = new File(path);

		if(!file.exists()) return null;
		else return file;
	}

	public static HashMap<String,String> getFileProperties(File file) {
		HashMap<String,String> properties = new HashMap<String,String>();

		properties.put("name", file.getName());
		properties.put("type", file.getName().substring(file.getName().lastIndexOf(".") + 1).toUpperCase());
		properties.put("size", file.length()+"");
		properties.put("lastModified", new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(file.lastModified()));

		return properties;
	}

	public static void openFile(String path) {
		try {
			Desktop.getDesktop().open(FileManager.getFile(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createNewFile(String path) {
		File file = new File(path);

		if(!file.exists()) {
			try {
				file.createNewFile();
				Desktop.getDesktop().open(file);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteFile(String path) {
		try {
			getFile(path).delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * WRITE OPERATIONS
	 */
	public static void writeSQL(String path, String script) {
		String filePath = path;

		try {
			int duplicateCount = 0;

			while(new File(filePath+FileManager.SQL).exists()) {
				filePath = path;
				filePath += " [copy "+ (++duplicateCount) +"]";
			}

			Files.write(Paths.get(filePath+FileManager.SQL), script.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createDirectoryIfNotExists(String path) {
		File directory = new File(path);

		if(!directory.exists()) {
			directory.mkdirs();
		}
	}

	/*
	 * VALIDATION
	 */
	public static boolean validateFileName(String name) {
		boolean isValid = false;

		if(name != null) {
			name = name.trim();
			isValid = !name.isEmpty() && !name.matches("[\\\\/:\\*\\¿\\?\\\"<>\\| ]");
		}

		return isValid;
	}

	public static boolean validateLoadFileContents(String path) {
		List<String> lines = FileManager.readAllLines(FileManager.getFile(path));
		boolean validationStatus = false;

		if(lines.size() >= 4) {
			boolean linesAreNotEmpty = 
					!lines.get(0).trim().isEmpty() &&
					!lines.get(1).trim().isEmpty() &&
					!lines.get(2).trim().isEmpty() &&
					!lines.get(3).trim().isEmpty();

			if(linesAreNotEmpty) {
				if(lines.get(0).trim().equalsIgnoreCase("stg")) {
					if(lines.get(3).trim().equalsIgnoreCase("#")) validationStatus = true;
				}
				else if(lines.get(0).trim().equalsIgnoreCase("dwh")) {
					if(lines.size() >= 5) {
						if(lines.get(4).trim().equalsIgnoreCase("#")) validationStatus = true;
					}
				}
			}
		}

		return validationStatus;
	}
}
