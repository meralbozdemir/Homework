package com.regularJavaTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Filtreleme_SelectBox {

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

		Select filtreBox = new Select(driver.findElement(By.id("selectProductSort")));//Bu test aslinda FAIL cunku filtreleme gerceklesmiyor.
		filtreBox.selectByVisibleText("Price: Lowest first");
		System.out.println("Secili Filtre : " + filtreBox.getFirstSelectedOption().getText());
		Thread.sleep(5000);
		
		filtreBox.selectByVisibleText("Price: Highest first");
		System.out.println("Secili Filtre : " + filtreBox.getFirstSelectedOption().getText());
		Thread.sleep(5000);
		
		filtreBox.selectByVisibleText("Product Name: A to Z");
		System.out.println("Secili Filtre : " + filtreBox.getFirstSelectedOption().getText());
		Thread.sleep(5000);
		
		filtreBox.selectByVisibleText("Product Name: Z to A");
		System.out.println("Secili Filtre : " + filtreBox.getFirstSelectedOption().getText());
		Thread.sleep(5000);
		
		filtreBox.selectByVisibleText("In stock");
		System.out.println("Secili Filtre : " + filtreBox.getFirstSelectedOption().getText());
		Thread.sleep(5000);
		
		filtreBox.selectByVisibleText("Reference: Lowest first");
		System.out.println("Secili Filtre : " + filtreBox.getFirstSelectedOption().getText());
		Thread.sleep(5000);
		
		filtreBox.selectByVisibleText("Reference: Highest first");
		System.out.println("Secili Filtre : " + filtreBox.getFirstSelectedOption().getText());
		Thread.sleep(5000);


		System.out.println("Otomasyon Tamamlandi...");
		driver.close();

	}

}
