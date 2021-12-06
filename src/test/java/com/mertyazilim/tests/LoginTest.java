package com.mertyazilim.tests;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.IOException;
import org.testng.Assert;

public class LoginTest extends TestBase {

	@Test(dataProvider = "dataProvider")
	public void testSuccessfulLogin(String username, String password) throws IOException {

		ExtentReports extent = this.extent;
		extentTest = extent.startTest("IT Career Center Login : "+username);
		extentTest.setDescription("Running Environment : " + testConfig.getProperty("environment"));

	testResult = homePage.clickMyAccountLink(extentTest, jobNo, date).loginAs(jobNo,date, username, password, extentTest)
				.isLoginSuccesful(jobNo,date,username, extentTest);

		Assert.assertTrue(testResult, "Error:Login is not Succesful for user " + username);
	}

}
