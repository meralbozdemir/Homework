package com.mertyazilim.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.mertyazilim.util.Screenshots;

public class MyAccountPage extends PageBase {

	
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	public MyAccountPage loginAs(int jobNo, String date,String username, String password, ExtentTest extentTest) throws IOException
	{
		js.scrollDown200(driver);
		extentTest.log(LogStatus.INFO, "Title is"+driver.getTitle());
		String screenshotPath = Screenshots.getScreenshot(driver, "Login Page", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		js.drawBorder(user, driver);
		user.sendKeys(username);
		js.drawBorder(pass, driver);
		pass.sendKeys(password);
		js.drawBorder(loginBtn, driver);
		screenshotPath = Screenshots.getScreenshot(driver, "Login Page1", jobNo, date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		loginBtn.click();
		extentTest.log(LogStatus.INFO, "Login successful");
		
		return new MyAccountPage(driver);
	}
	
	public boolean isLoginSuccesful(int jobNo, String date,String username, ExtentTest extentTest) throws IOException
	{	js.scrollDown(driver);
	    extentTest.log(LogStatus.INFO, "User Entered Users private page");
	    js.drawBorder(isLogin, driver);
		String screenshotPath = Screenshots.getScreenshot(driver, "Login Page",jobNo,date);
		extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
		return isLogin.getText().contains(username);
	}

	public MyAccountPage myAccountButton(int jobNo, String date,String username, String password, ExtentTest extentTest) throws IOException
	{
		System.out.println(driver.findElement(By.linkText("MY ACCOUNT")).isDisplayed());
		
		
		return new MyAccountPage(driver);
	}
	

	
	}


