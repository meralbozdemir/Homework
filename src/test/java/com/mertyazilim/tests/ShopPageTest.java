package com.mertyazilim.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;

public class ShopPageTest extends TestBase {

	@Test(dataProvider = "dataProvider")
	public void testApplyingSortOrder(String sortOrder) throws IOException {

		ExtentReports extent = this.extent;
		extentTest = extent.startTest("Order by->" + sortOrder);
		extentTest.setDescription("Running Environment : " + testConfig.getProperty("environment"));

		String actualSortOrder = homePage.clickShopLink().setSortOrder(jobNo, date, sortOrder, extentTest)
				.getSortOrder();

		Assert.assertTrue(actualSortOrder.equals(sortOrder), "Sort order is not applied properly");
	}

}
