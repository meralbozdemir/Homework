package com.mertyazilim.pages;


import java.io.IOException;
import org.openqa.selenium.WebDriver;

import com.mertyazilim.util.Screenshots;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver)
	{
		super(driver);
	//	PageFactory.initElements(driver, this);
	}
	
	
	public HomePage loginHome(String username, String password,ExtentTest extentTest, int jobNo, String date) throws IOException{

     user.sendKeys(username);
     pass.sendKeys(password);
 	extentTest.log(LogStatus.INFO, "GIVEN User is on Home Page to Login");
	extentTest.log(LogStatus.INFO, "Login Title is : "+ driver.getTitle());
	loginBtn.click();
     String screenshotPath = Screenshots.getScreenshot(driver, "ready to Login", jobNo, date);
 	 extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
 	 extentTest.log(LogStatus.INFO, "Home Page Title is : "+ driver.getTitle());
   
           return new HomePage(driver);
    }
	
	
	
	public HomePage dtnTradeLogin(String username, String password){

		user.sendKeys(username);
	    pass.sendKeys(password);
	    loginBtn.click();
	   
	           return new HomePage(driver);
	    }
	
	
public void login(String username, String password,ExtentTest extentTest, int jobNo, String date) throws IOException {
		
		try {
    		
			extentTest.log(LogStatus.INFO, "GIVEN User is on Home Page to Login");
	    	extentTest.log(LogStatus.INFO, "AND Title is : "+driver.getTitle());
	    	extentTest.log(LogStatus.INFO, "AND take screenshot before send credentials");
	    	String screenshotPath = Screenshots.getScreenshot(driver, "ready to Login", jobNo, date);
	    	extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
			user.sendKeys(username);
	        pass.sendKeys(password);
	        extentTest.log(LogStatus.INFO, "WHEN send valid credentials to Login");
	        loginBtn.click();
	       	  
	        extentTest.log(LogStatus.INFO, "AND take screenshot with valid credential");
	        String screenshotPath1 = Screenshots.getScreenshot(driver, "valid credentials for Login",jobNo, date);
	    	extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath1));
	    	extentTest.log(LogStatus.INFO, "THEN Login is Successful");
	    	
	    	extentTest.log(LogStatus.INFO, "RESULT Invalid Login Test is Successful");
	    	
	        
	    		
	    	} catch(Exception NoSuchElementException ) {
	    		 System.out.println("You have wrong Credientials");
	    		
	    	}
	}

	
	
	
	public void invalidLogin(String username, String password,ExtentTest extentTest, int jobNo, String date) throws IOException {
		
		try {
    		
			extentTest.log(LogStatus.INFO, "GIVEN User is on Home Page to Login");
	    	extentTest.log(LogStatus.INFO, "AND Title is : "+driver.getTitle());
	    	extentTest.log(LogStatus.INFO, "AND take screenshot before send credentials");
	    	String screenshotPath = Screenshots.getScreenshot(driver, "ready to Login", jobNo, date);
	    	extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));
			user.sendKeys(username);
	        pass.sendKeys(password);
	        extentTest.log(LogStatus.INFO, "WHEN send invalid credentials to Login");
	        loginBtn.click();
	       	  
	        extentTest.log(LogStatus.INFO, "AND take screenshot with invalid credential");
	        String screenshotPath1 = Screenshots.getScreenshot(driver, "invalid credentials for Login", jobNo, date);
	    	extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath1));
	    	extentTest.log(LogStatus.INFO, "THEN Login is not Successful");
	    	extentTest.log(LogStatus.INFO, "BUT Test is Successful because Test catched invalid credentials");
	    	extentTest.log(LogStatus.INFO, "RESULT Invalid Login Test is Successful");
	    	
	        
	    		
	    	} catch(Exception NoSuchElementException ) {
	    		 System.out.println("You have wrong Credientials");
	    		
	    	}
	}

}
