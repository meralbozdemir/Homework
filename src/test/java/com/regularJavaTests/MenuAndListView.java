package com.regularJavaTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MenuAndListView {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "Util\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get("http://automationpractice.com/index.php");

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.linkText("WOMEN"))).build().perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='Summer Dresses']")).click();

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,300)");

		Thread.sleep(3000);

		driver.findElement(By.id("list")).click();
		Thread.sleep(3000);

		for (int i = 0; i < 3; i++) {
			js.executeScript("window.scrollBy(0,300)");
			Thread.sleep(3000);
		}
		
		WebElement followUs=driver.findElement(By.xpath("//*[@id='social_block']/h4"));
		
		js.executeScript("arguments[0].scrollIntoView(true);", followUs);
		Thread.sleep(5000);

		System.out.println("Automation is completed...");
		driver.close();

	}

}
