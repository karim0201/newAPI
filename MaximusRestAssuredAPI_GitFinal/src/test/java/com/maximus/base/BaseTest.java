package com.maximus.base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseTest {
	public static RequestSpecification httpRequest;
	public static Response response;
	
	public Logger logger; 
	
@BeforeClass	
public void setUp() {
	//String url = "http://dummy.restapiexample.com/api/v1";
	
	logger=Logger.getLogger("BaseTest");
	PropertyConfigurator.configure("log4j.properties");
	logger.setLevel(Level.DEBUG);
}

}
