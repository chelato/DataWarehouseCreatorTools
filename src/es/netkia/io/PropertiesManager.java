package es.netkia.io;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PropertiesManager {
	
	private HashMap<String,String> properties;
	private String defaultLoadPath;
	private String defaultScriptsPath;

	public PropertiesManager() {
		this.properties = null;
		this.defaultLoadPath = "";
		this.defaultScriptsPath = "";
	}

	public void initialize() {
		this.properties = this.getDefaultAppProperties();
		String[] loadPathParts = this.properties.get("defaultloadpath").split("[.]");
		String[] scriptsPathParts = this.properties.get("defaultscriptspath").split("[.]");
		
		if(loadPathParts[0].equalsIgnoreCase("APP")) {
			this.defaultLoadPath = PathManager.APP_HOME + File.separator;
			loadPathParts = Arrays.copyOfRange(loadPathParts, 1, loadPathParts.length);
		}
		if(scriptsPathParts[0].equalsIgnoreCase("APP")) {
			this.defaultScriptsPath = PathManager.APP_HOME + File.separator;
			scriptsPathParts = Arrays.copyOfRange(scriptsPathParts, 1, scriptsPathParts.length);
		}
		
		int count = 0;
		for(String part : loadPathParts) {
			if(!part.isEmpty()) {
				this.defaultLoadPath += part;
				if(count < loadPathParts.length-1) this.defaultLoadPath += File.separator;
			}
			count++;
		}
		
		count = 0;
		for(String part : scriptsPathParts) {
			if(!part.isEmpty()) {
				this.defaultScriptsPath += part;
				if(count < scriptsPathParts.length-1) this.defaultScriptsPath += File.separator;
			}
			count++;
		}
		
		this.properties.put("loadpath", this.defaultLoadPath);
		this.properties.put("scriptspath", this.defaultScriptsPath);
	}

	public HashMap<String,String> getDefaultAppProperties() {
		HashMap<String,String> properties = new HashMap<String,String>();

		try {
			File xmlFile = new File(PathManager.APP_PROPERTIES);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			NodeList nodelist = doc.getDocumentElement().getChildNodes();
			for(int i=0; i<nodelist.getLength(); i++) {
				Node n = nodelist.item(i);
				
				if(n.getNodeType() == 1) {
					Node nodeAttribute = n.getAttributes().getNamedItem("value");
					String nodeName = n.getNodeName().toLowerCase();
					
					if( !nodeName.equalsIgnoreCase("DefaultLanguage") &&
						!nodeName.equalsIgnoreCase("DefaultLoadPath") &&
						!nodeName.equalsIgnoreCase("DefaultScriptsPath")) {
						properties.put(nodeName, nodeAttribute.getNodeValue().toUpperCase());
					}
					else properties.put(nodeName, nodeAttribute.getNodeValue());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return properties;
	}
	
	public void writeDefaultAppProperties(HashMap<String,String> newProperties) {
		try {
			File xmlFile = new File(PathManager.APP_PROPERTIES);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			NodeList nodelist = doc.getDocumentElement().getChildNodes();
			for(int i=0; i<nodelist.getLength(); i++) {
				Node n = nodelist.item(i);
				
				if(n.getNodeType() == 1) {
					Node nodeAttribute = n.getAttributes().getNamedItem("value");
					String nodeName = n.getNodeName().toLowerCase();
					
					if(newProperties.containsKey(nodeName)) {
						nodeAttribute.setNodeValue(newProperties.get(nodeName));
					}
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(PathManager.APP_PROPERTIES));
			transformer.transform(source, result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String,String> getProperties() {
		return this.properties;
	}
	
	public String getDefaultLoadPath() {
		return this.defaultLoadPath;
	}
	
	public String getDefaultScriptsPath() {
		return this.defaultScriptsPath;
	}
	
	public String getDefaultLanguage() {
		return this.properties.get("defaultlanguage");
	}
}
