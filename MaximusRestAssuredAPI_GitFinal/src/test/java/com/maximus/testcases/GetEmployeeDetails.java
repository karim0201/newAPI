package com.maximus.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.maximus.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetEmployeeDetails extends BaseTest{
	
	@BeforeClass
	public void getEmployeeDetails() throws InterruptedException {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		//for authorization
        //PreemptiveBasicAuthScheme authorization = new PreemptiveBasicAuthScheme();
        //authorization.setUserName("username");
        //authorization.setPassword("password");
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(9000);
		
	}
	
	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		
		if(contentType.equals("application/json")) {
			Assert.assertTrue(true);
			System.out.println("Content type is: " + contentType);
		}
	}
	
	@Test
	void getResponseBody() {
		String respBody = response.getBody().asString();
		System.out.println("The response body is: " + respBody);
		Assert.assertTrue(respBody!=null);
	}
	
	@Test
	void getStatusCode() {
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void getStatusLine() {
		String statusLine = response.getStatusLine();
		System.out.println("Response status line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	//@Test
	//void checkResponseTime() {
		//long respTime = response.getTime();
		//if(respTime > 2000) {
		//	System.out.println("Response time is: " + respTime);
		//}
	//}
	
	@Test
	void checkServerType(){
		String serverType = response.header("Server");
		System.out.println("Server type is: " + serverType);
		Assert.assertEquals(serverType, "nginx");
	}
	
	@AfterClass
	void tearDown() {
		System.out.println("Finished up getting employee details");
	}

}
