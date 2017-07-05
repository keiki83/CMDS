/*
 * Author: Michael R. Callan III
 * Version: 1.06
 */

public class ServerSettings {
	// variables
	private String name;
	private String path;
	private String exe;
	private String arg;
	private Boolean relativePath;
	
	
	// constructors
	public ServerSettings() {
		name = "";
		path = "";
		exe = "";
		arg = "";
		relativePath = false;
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
		
}
