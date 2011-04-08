package edu.fiu.cis.feedClient;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Some common utilities
 * @author omvaldiv
 *
 */
public class Utils {
	/**
	 * Checks if a string is null or blank
	 * @param string
	 * @return true if string is null or blank
	 */
	public static boolean empty(String string){
		return string == null || "".equals(string);
	}
	
	
	public static String getString(String source){
		if (source!=null)
			return source;
		else
			return "";
		
	}
	
	public static String setString(String s){
		if (s==null)
			return "null";
		
		return "\'" + s + "\'";
	}
	
	public static Properties loadProperties(String path){
		
		Properties properties = new Properties();
		try {
			
			properties.load(new FileInputStream(new File(path)));
		} catch (Exception e) {
			properties = null;
		}
		
		return properties;
		
	}
	
	public static void logDebug(String msg, Class cl){
		Logger.getLogger(cl).debug(msg);
	}
	
	public static void logError(String msg, Class cl){
		Logger.getLogger(cl).error(msg);
	}
	
	public static void logInfo(String msg, Class cl){
		Logger.getLogger(cl).info(msg);
	}
}
