package com.mertyazilim.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.IOException;


public class CheckoutWithSignIN extends TestBase {

	@Test 
	public void testCheckout() throws IOException, InterruptedException {

		ExtentReports extent = this.extent;
		extentTest = extent.startTest("Checkout Test With Sign In");
		extentTest.setDescription("Running Environment : " + testConfig.getProperty("environment"));

		testResult=homePage.goMertPage().altMenu(jobNo, date, extentTest)
				       	.addToCart(jobNo, date, extentTest)
				       	.signIN(jobNo, date, extentTest)
				       	.checkout(jobNo, date, extentTest)
				       	.assertCheckout()
				        ;
				Assert.assertTrue(testResult, "Error:Login is not Succesful for user " );
	}

}
