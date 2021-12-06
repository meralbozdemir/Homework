package com.mertyazilim.tests;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.IOException;


public class AddToCart extends TestBase {

	@Test 
	public void testAltMenu() throws IOException, InterruptedException {

		ExtentReports extent = this.extent;
		extentTest = extent.startTest("Add To Cart Test");
		extentTest.setDescription("Running Environment : " + testConfig.getProperty("environment"));

				homePage.goMertPage().altMenu(jobNo, date, extentTest)
				       	.addToCart(jobNo, date, extentTest)
				        ;

	}

}
