/*
 * Author: Michael R. Callan III
 * Version: 1.06
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class LoadSettings {
	private LinkedList<ServerSettings> serverSettings;

	public LoadSettings(String settingsPath) {
		serverSettings = new LinkedList<ServerSettings>();
		
		// Get list of server files
		File serversFolder = new File(settingsPath);
		File[] serverFiles = serversFolder.listFiles();

		// Load settings of each file
		for(int i = 0; i < serverFiles.length; i++) {
			if(!serverFiles[i].isFile() ||  !serverFiles[i].getName().contains(".properties")) {
				continue;
			}

			ServerSettings settings = new ServerSettings();

			try {
				FileInputStream in = new FileInputStream(settingsPath + "/" + serverFiles[i].getName());
				Properties properties = new Properties();
				properties.load(in);
				if(properties.containsKey("name")) {
					settings.setName(properties.getProperty("name"));
				}
				if(properties.containsKey("path")) {
					settings.setPath(properties.getProperty("path"));
				}
				if(properties.containsKey("exe")) {
					settings.setExe(properties.getProperty("exe"));
				}
				if(properties.containsKey("arg")) {
					settings.setArg(properties.getProperty("arg"));
				}
				if(properties.containsKey("relative")) {
					if(properties.getProperty("relative").equals("true")) {
						settings.setRelativePath(true);
					}
					else {
						settings.setRelativePath(false);
					}
				}			
				in.close();
				serverSettings.add(settings);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Boolean hasSettings() {
		return !serverSettings.isEmpty();
	}
	
	public ServerSettings pop() {
		return serverSettings.pop();
	}
	
	public ServerSettings peek() {
		return serverSettings.peek();
	}
}
