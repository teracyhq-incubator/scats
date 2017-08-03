package org.teracy.scats.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class LocatorMap {
	static Properties properties = new Properties();
	static String fseparator = File.separator;
	public static final String config_path = "/serenity.properties";
	
	/**
	 * load config properties file
	 * @return
	 */
	public static Properties loadConfigSys(){
		String fs = File.separator;
		FileInputStream is = null;
		try {
			is = new FileInputStream((System.getProperty("user.dir") + config_path).replace("/", fs).replace("\\", fs));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties pros = new Properties();
		try {
			pros.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pros;
	}
	/**
	 * Get locator string from element properties
	 * @param ElementName
	 * @return
	 * @throws Exception
	 */
	public static String getLocatorString(String ElementName) throws Exception {
		//Read value using the logical name as Key
		String locator = properties.getProperty(ElementName);
		return locator.substring(locator.indexOf("=")+1);
	}

	/**
	 * Get locator string from element properties
	 * @param ElementName
	 * @return
	 * @throws Exception
	 */
	public static String getActualValueFromElementList(String elementName) {
		//Read value using the logical name as Key
		String locator = properties.getProperty(elementName);
		if(locator!=null && !locator.isEmpty()){
			return locator;
		}
		else
			return elementName;
	}

	/**
	 * loading locator element from package
	 * @param objectRepos
	 * @return
	 * @throws IOException
	 */
	public static Properties loadingObjectPropertiesFromPackage(String objectRepos){
		BufferedReader fs = null;
		properties = new Properties();
		String workingDir=System.getProperty("user.dir");
		try {
			File folder = new File((workingDir +fseparator+"src/test/resources/"+objectRepos).replace("\\", fseparator).replace("/", fseparator));
			TestLogger.info(folder.getAbsolutePath());
			if (folder.isDirectory()) {
				File[] files = folder.listFiles();
				for (File file : files) {
					TestLogger.info(file.getAbsolutePath());
					fs = new BufferedReader(
				            new InputStreamReader(
				                       new FileInputStream(file), "UTF8"));
					properties.load(fs);					
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
}
