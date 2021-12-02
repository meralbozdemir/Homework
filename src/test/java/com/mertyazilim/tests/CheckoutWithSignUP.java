package com.mertyazilim.tests;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.IOException;
import org.testng.Assert;

public class CheckoutWithSignUP extends TestBase {

	@Test 
	public void testCheckout() throws IOException, InterruptedException {

		ExtentReports extent = this.extent;
		extentTest = extent.startTest("Alt Menu Test");
		extentTest.setDescription("Running Environment : " + testConfig.getProperty("environment"));

		testResult=homePage.goMertPage().altMenu(jobNo, date, extentTest)
				       	.addToCart(jobNo, date, extentTest)
				       	.signUp(jobNo, date, extentTest)
				       	.checkout(jobNo, date, extentTest)
				       	.assertCheckout()
				        ;
		Assert.assertTrue(testResult, "Error:Login is not Succesful for user " );
	}

}
