package eleks.com.util;

import java.net.URL;
import java.util.Properties;

public class ConfigProperties {
	
	private static Properties properties;
	
	static{
		properties = new Properties();
		URL systemResource = ClassLoader.getSystemResource("config.properties");
		try {
			properties.load(systemResource.openStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperties(String key) {
		return properties.getProperty(key);
	}
}
