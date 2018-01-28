package com.xo.mail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @author Amit Sahu
 * 
 */

public class GetPropertyValues {
public static void main(String[] args) throws Exception {
	getPropValues();
}
	public static void getPropValues() throws IOException {
		String company1 = null;
		String company2 = null;
		String company3 = null;
		Date time =null;
		try {
			Properties prop = new Properties();
			String propFileName = "D:/config.properties";
			InputStream fio = new FileInputStream(propFileName);
			prop.load(fio);

			 time = new Date(System.currentTimeMillis());

			// get the property value and print it out
			 company1 = prop.getProperty("company1");
			 company2 = prop.getProperty("company2");
			 company3 = prop.getProperty("company3");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		
		System.out.println("Company List = " + company1 + ", " + company2 + ", " + company3 +" Date is: " ); 
	}
}