package com.regularJavaTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUpload {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "Util\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get("http://the-internet.herokuapp.com/upload");

		Thread.sleep(2000);

		WebElement fileSelect = driver.findElement(By.id("file-upload"));

		fileSelect.sendKeys("C:\\Users\\rsivr\\Desktop\\WorkspaceEmir\\Homework\\Util\\UploadedFile.txt");
		System.out.println("1- File selected");

		Thread.sleep(5000);

		driver.findElement(By.id("file-submit")).click();
		System.out.println("2- Upload completed");

		Thread.sleep(5000);

		driver.close();
	}
}
