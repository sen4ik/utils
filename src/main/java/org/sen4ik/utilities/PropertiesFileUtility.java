package org.sen4ik.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {

	private Properties properties;
	private String propertiesFilePath;

	public PropertiesFileUtility(String propertiesFilePath) throws IOException {
		this.propertiesFilePath = propertiesFilePath;
		properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(propertiesFilePath);
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			String errorMessage = "Wasn't able to load properties file " + propertiesFilePath + ".\n" + e.getMessage() + "\n" + e.getStackTrace();
			System.err.println(errorMessage);
			throw new IOException(errorMessage);
		}
	}

	public String getProperty(String propertyName) {
		String propertyValue = properties.getProperty(propertyName);
		if (propertyValue != null) {
			return propertyValue;
		} else {
			System.err.println("Property [" + propertyName + "] was not found!");
			return null;
		}
	}

}