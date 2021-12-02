package com.mertyazilim.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

	  public static String getScreenshot(WebDriver driver, String screenshotName, int jobNo, String date) throws IOException{

		
		String adate = new SimpleDateFormat("MM-dd-yyyy-HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination ="TestsScreenshots/" + screenshotName + adate + ".png";
		File finalDestination = new File("Test-Report/"+date+"/job"+jobNo+"/"+destination);
		FileUtils.copyFile(source, finalDestination);
		System.out.println("Screenshot is taken");
		return destination;
	}
	  

	}
