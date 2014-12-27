package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	public static String getProperty(String key){
		try {
			InputStream is = PropertyLoader.class.getResourceAsStream("/props/pathconfig.properties");
			Properties pro = new Properties();
			pro.load(is);
			return pro.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
