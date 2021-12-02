package com.mertyazilim.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.mertyazilim.util.Screenshots;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ShopPage extends PageBase {

	public ShopPage(WebDriver driver) {
		super(driver);
	}

	public ShopPage setSortOrder(int jobNo, String date,String sortOrder, ExtentTest extentTest) throws IOException {
		Select sortList = new Select(selSort);
		sortList.selectByVisibleText(sortOrder);
		js.drawBorder(selSort, driver);
		sortList = new Select(selSort);
		extentTest.log(LogStatus.INFO, "Sorted by"+sortList.getFirstSelectedOption().getText());
		String screenshotPath = Screenshots.getScreenshot(driver, "Shop Page",jobNo,date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		return new ShopPage(driver);
	}

	public String getSortOrder() {
		Select sortList = new Select(selSort);
		return sortList.getFirstSelectedOption().getText();
	}
}
