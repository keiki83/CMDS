/*
 * Author: Michael R. Callan III
 * Version: 1.07
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerSettings {
	// variables
	private String name;
	private String path;
	private String exe;
	private String arg;
	private Boolean relativePath;
	private String savePath;


	// constructors
	public ServerSettings(String savePath) {
		name = "";
		path = "";
		exe = "";
		arg = "";
		this.savePath = savePath;
		relativePath = false;
	}
	
	public ServerSettings(String savePath, String fileName) {
		this(savePath);
		loadSettings(fileName);
	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setExe(String exe) {
		this.exe = exe;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public void setRelativePath(Boolean relativePath) {
		this.relativePath = relativePath;
	}

	// getters
	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getExe() {
		return exe;
	}

	public String getArg() {
		return arg;
	}

	public Boolean isRelativePath() {
		return relativePath;
	}

	// Utility
	public void saveSettings() {
		Properties properties = new Properties();
		try {
			FileOutputStream out= new FileOutputStream(savePath + name + ".properties");
			properties.setProperty("name", name);
			properties.setProperty("path", path);
			properties.setProperty("exe", exe);
			properties.setProperty("arg", arg);
			properties.setProperty("relative", relativePath.toString());
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadSettings(String fileName) {
		try {
			FileInputStream in = new FileInputStream(savePath + "/" + fileName);
			Properties properties = new Properties();
			properties.load(in);
			if(properties.containsKey("name")) {
				name = properties.getProperty("name");
			}
			if(properties.containsKey("path")) {
				path = properties.getProperty("path");
			}
			if(properties.containsKey("exe")) {
				exe = properties.getProperty("exe");
			}
			if(properties.containsKey("arg")) {
				arg = properties.getProperty("arg");
			}
			if(properties.containsKey("relative")) {
				if(properties.getProperty("relative").equals("true")) {
					relativePath = true;
				}
				else {
					relativePath = false;
				}
			}			
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
