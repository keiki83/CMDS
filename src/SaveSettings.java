/*
 * Author: Michael R. Callan III
 * Version: 1.06
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SaveSettings {

	public SaveSettings(String path, ServerSettings serverSettings) {
		Properties properties = new Properties();
		try {
			FileOutputStream out= new FileOutputStream(path + serverSettings.getName() + ".properties");
			properties.setProperty("name", serverSettings.getName());
			properties.setProperty("path", serverSettings.getPath());
			properties.setProperty("exe", serverSettings.getExe());
			properties.setProperty("arg", serverSettings.getArg());
			properties.setProperty("relative", serverSettings.isRelativePath().toString());
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
