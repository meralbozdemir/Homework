package com.mertyazilim.pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import com.mertyazilim.pages.HomePage;
import com.mertyazilim.pages.LoginPage;
import com.mertyazilim.pages.MyAccountPage;
import com.mertyazilim.pages.ShopPage;
import com.mertyazilim.util.Screenshots;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class HomePage extends PageBase {

	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	public MyAccountPage clickMyAccountLink(ExtentTest extentTest, int jobNo, String date) throws IOException
	{
		String screenshotPath = Screenshots.getScreenshot(driver, "Login Page", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		myAccountButton.click();
		return new MyAccountPage(driver);
	}
	
	public ShopPage clickShopLink()
	{
		shopButton.click();
		return new ShopPage(driver);
	}
	
	public MertPage goMertPage()
	{
		return new MertPage(driver);
	}
	
	public LoginPage login()
	{
		
		return new LoginPage(driver);
	}
		
}
