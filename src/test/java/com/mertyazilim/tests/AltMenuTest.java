package com.mertyazilim.tests;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.IOException;
import org.testng.Assert;

public class AltMenuTest extends TestBase {

	@Test 
	public void testAltMenu() throws IOException, InterruptedException {

		ExtentReports extent = this.extent;
		extentTest = extent.startTest("Alt Menu Test");
		extentTest.setDescription("Running Environment : " + testConfig.getProperty("environment"));

				homePage.goMertPage().altMenu(jobNo, date, extentTest);

	}

}
