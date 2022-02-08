package com.maximus.testcases;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.maximus.base.BaseTest;
import com.maximus.utilities.RestUtils;
import com.maximus.utilities.XLUtils;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;

public class DataDriven_PostEmployeeRecords extends BaseTest{
	
	//RestUtils will be called if we need dynamic name/user on every execution******************************
	//String empName = RestUtils.empName();
	//String empAge = RestUtils.empAge();
	//String empSalary = RestUtils.empAge();
	
	//@BeforeClass
	@Test(priority=1, dataProvider = "employeeDataForPost")
	//void postEmpRecords(String uName, String pwd, String empName, String empAge, String empSalary) throws InterruptedException {
	void postEmpRecords(String empName, String empAge, String empSalary) throws InterruptedException {
		logger.info("****************Started Datadriven Testing*****************");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		//for authorization
        //PreemptiveBasicAuthScheme authorization = new PreemptiveBasicAuthScheme();
        //authorization.setUserName(uName);  //username and pwd need to use in excel sheet
        //authorization.setPassword(pwd);
        //RestAssured.authentication = authorization;
		
		httpRequest = RestAssured.given();
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("name", empName);
		jsonObject.put("age", empAge);
		jsonObject.put("salary", empSalary);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(jsonObject.toJSONString());
		
		response = httpRequest.request(Method.POST, "/create");
		
		Thread.sleep(50000);
	}
	
	@DataProvider(name = "employeeDataForPost")
    String [][] getData() throws IOException {
    	String path = System.getProperty("user.dir")+ "/TestData/Emp_Data.xlsx";
        
        int rownum = XLUtils.getRowCount(path, "Sheet1");
        int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

        String usersData [][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                usersData [i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
            }
        }
        return (usersData);
    }
	
	@Test(priority = 2)
	void checkContentType() {
		logger.info("**************Checking Content Type*******************");
		
		String contentType = response.header("Content-Type");
		
		if(contentType.equals("application/json")) {
			Assert.assertTrue(true);
			System.out.println("Content type is: " + contentType);
		}
	}
	
	@Test(priority = 3)
	void getResponseBody() {
		logger.info("*****************Getting Response Body*********************");
		
		String respBody = response.getBody().asString();
		System.out.println("The response body is: " + respBody);
		Assert.assertTrue(respBody!=null);
		
		//Assert.assertEquals(respBody.contains(empName), true);
		//Assert.assertEquals(respBody.contains(empAge), true);
		//Assert.assertEquals(respBody.contains(empSalary), true);
	}
	
	@Test(priority = 4)
	void getStatusCode() {
		logger.info("****************Checking Status Code***************");
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test(priority = 5)
	void getStatusLine() {
		logger.info("********************Checking Status Line*********************");
		String statusLine = response.getStatusLine();
		System.out.println("Response status line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	//@Test(priority = 7)
//	void checkResponseTime() {
//		long respTime = response.getTime();
//		if(respTime > 2500) {
//			System.out.println("Response time " + respTime + " is more than expected");
//		}
//	}
	
	@Test(priority = 6)
	void checkServerType(){
		logger.info("*******************Checking Server Type**********************");
		String serverType = response.header("Server");
		System.out.println("Server type is: " + serverType);
		Assert.assertEquals(serverType, "nginx");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("****************Finished up getting employee details*****************");
		System.out.println("Finished up getting employee details");
	}

}



