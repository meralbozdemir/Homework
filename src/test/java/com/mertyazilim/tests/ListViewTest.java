package com.mertyazilim.tests;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.IOException;

public class ListViewTest extends TestBase {

	@Test
	public void testAltMenu() throws IOException, InterruptedException {

		ExtentReports extent = this.extent;
		extentTest = extent.startTest("List View Test");
		extentTest.setDescription("Running Environment : " + testConfig.getProperty("environment"));

		homePage.goMertPage().altMenu(jobNo, date, extentTest).listView(jobNo, date, extentTest)

		;

	}

}
