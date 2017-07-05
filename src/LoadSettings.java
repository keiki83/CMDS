/*
 * Author: Michael R. Callan III
 * Version: 1.07
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class LoadSettings {
	private LinkedList<ServerSettings> serverSettings;

	public LoadSettings(String savePath) {
		serverSettings = new LinkedList<ServerSettings>();
		
		// Get list of server files
		File serversFolder = new File(savePath);
		File[] serverFiles = serversFolder.listFiles();

		// Load settings of each file
		for(int i = 0; i < serverFiles.length; i++) {
			if(serverFiles[i].isFile() && serverFiles[i].getName().contains(".properties")) {
				serverSettings.add(new ServerSettings(savePath, serverFiles[i].getName()));
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
