package org.sen4ik.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesFileUtil {

	private Properties properties;
	private String propertiesFilePath;

	public PropertiesFileUtil(String propertiesFilePath) throws IOException {
		this.propertiesFilePath = propertiesFilePath;
		properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(propertiesFilePath);
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			String errorMessage = "Wasn't able to load properties file " + propertiesFilePath + ".\n" + e.getMessage() + "\n" + e.getStackTrace();
			log.error(errorMessage);
			throw new IOException(errorMessage);
		}
		log.info("Properties file " + propertiesFilePath + " loaded OK.");
	}

	public String getProperty(String propertyName) {
		log.info("CALLED: getProperty()");
		String propertyValue = properties.getProperty(propertyName);
		if (propertyValue != null) {
				log.info("Property [" + propertyName + "] found with value '" + propertyValue + "'");
			return propertyValue;
		} else {
			log.error("Property [" + propertyName + "] was not found!");
			return null;
		}
	}

}
