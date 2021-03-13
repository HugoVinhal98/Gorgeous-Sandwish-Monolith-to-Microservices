package utils;

import java.io.IOException;
import java.util.Properties;

import springboot.SpringbootCrudHibernateExampleApplication;


public class PropertiesObtain {

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Properties getProperties()  {
		Properties props = new Properties();
		/*
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream stream = loader.getResourceAsStream("../config.properties");
		*/
		try {
			props.load(SpringbootCrudHibernateExampleApplication.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
	}
	
	public static String getPropertiesValue(String key)  {
		Properties props = PropertiesObtain.getProperties();
		return props.getProperty(key);
	}
}
