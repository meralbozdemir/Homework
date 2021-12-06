package com.mertyazilim.tests;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.IOException;
import org.testng.Assert;

public class FiltrelemeTest extends TestBase {

	@Test(dataProvider = "dataProvider")
	public void testFiltreleme(String sortOrder) throws IOException, InterruptedException {

		ExtentReports extent = this.extent;
		extentTest = extent.startTest("Filtreleme->" + sortOrder);
		extentTest.setDescription("Running Environment : " + testConfig.getProperty("environment"));

		String actualSortOrder = homePage.goMertPage().altMenu(jobNo, date, extentTest)
				.setSortOrder(jobNo, date, extentTest, sortOrder).getSortOrder();

		Assert.assertTrue(actualSortOrder.equals(sortOrder), "Filtreleme uygun sekilde yapilamadi");
	}

}
