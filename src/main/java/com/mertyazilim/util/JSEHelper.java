
/*
 * @author Ramazan Sivri 
 */

package com.mertyazilim.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class JSEHelper {
	
	public void drawBorder1(WebElement element, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
	
	public void sendESN(String esnno, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("angular.element(document.getElementById(\"tfvalesn-esn\")).scope().data.esn=esnno");
    }
	
	 public static BigDecimal readBigDecimal(String obj)
	    {
	        if (StringUtils.isEmpty(obj))
	            return null;

	        try
	        {
	            return new BigDecimal(obj.trim());
	        }
	        catch (java.lang.NumberFormatException e)
	        {
	            return null;
	        }
	    }				
	
	public void highlight(WebElement element, WebDriver driver) {
        String bgcolor  = element.getCssValue("backgroundColor");
        for (int i = 0; i <  30; i++) {  //put another number instead of 30 to change flash time 
            changeColor("rgb(0,200,0)", element,driver);//1
            changeColor(bgcolor, element,driver);//2
        }
    }
    public void changeColor(String color, WebElement element, WebDriver driver) {
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '"+color+"'",  element);

        try {
            Thread.sleep(20);
        }  catch (InterruptedException e) {
        }
     }
	
    public static String getCurrentDateSO() throws ParseException{

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate); 
	}
    
    public static String[] generateRandomWords(int numberOfWords)
   	{
   	    Random random = new Random();

   	    String[] randomStrings = new String[numberOfWords];
   	    for(int i = 0; i < numberOfWords; i++)
   	    {
   	        char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
   	        for(int j = 0; j < word.length; j++)
   	        {
   	            word[j] = (char)('a' + random.nextInt(26));
   	        }
   	        randomStrings[i] = new String(word);
   	    }
   	    return randomStrings;
   	}
    public static int genraterRandomNumber()
    {
	    Random rand = new Random(); 
	    
	    int randomNumber=rand.nextInt(1000);
	    return randomNumber;

    }
    
    public Boolean waitForElement(WebDriver driver, final WebElement elements) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,60);
        try {
            wait.until(ExpectedConditions.visibilityOf(elements));
            wait.until(ExpectedConditions.elementToBeClickable(elements));
        } catch (StaleElementReferenceException e) {
            
            wait.until(ExpectedConditions.stalenessOf(elements));
            wait.until(ExpectedConditions.visibilityOf(elements));
            wait.until(ExpectedConditions.elementToBeClickable(elements));
        }
        
        WebElement ex = null;
        Boolean found = false;
        if ((ex = elements) != null) {
            for (int j = 0; j < 400; j++) {
                if (ex.isDisplayed() && ex.isEnabled()) {
                    found = true;
                    break;
                } else {
                    Thread.sleep(100);
                }
            }
        }
        if (found == false) {
            return null;
        }
        return found;       
    }
    
    public void waitForPageToload(WebDriver driver) {
        
        //driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String command = "return document.readyState";
        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("System error -> " + e);
            }
            if (js.executeScript(command).toString().equals("complete")) {
                break;
            }
        }
        
       }
 
	
    public void drawBorder(WebElement element, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
    
    public void generateAlert(WebDriver driver, String message){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("alert('"+message+"')");

    }
    
    public void clickElementByJS(WebElement element, WebDriver driver){  // very important!!
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].click();", element);
    	
    }
	
    public void refreshBrowserByJS(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("history.go(0)");
    }
    
    public String getTitleByJS(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	String title = js.executeScript("return document.title;").toString();
    	return title;
    }
    
    public String getPageInnerText(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	String pageText = js.executeScript("return document.documentElement.innerText;").toString();
    	return pageText;
    }
    
    public void scrollPageDown(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    
    public void scrollIntoView(WebElement element, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void scrollVertPix(int pix, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	  js.executeScript("window.scrollBy(0,pix)");
    }

    public void scrollDown(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	  js.executeScript("window.scrollBy(0,150)");
    }
    
    public void scrollDown200(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	  js.executeScript("window.scrollBy(0,200)");
    }

    public void scrollVert500PixUp(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	  js.executeScript("window.scrollBy(0,-500)");
    }
    
    public void scrollBottom(WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    public void scrollHorizontalElement(WebElement element, WebDriver driver){
    	JavascriptExecutor js = ((JavascriptExecutor) driver);
    	js.executeScript("arguments[0].scrollIntoView();", element);
    }
    
    public static XMLGregorianCalendar convertDateFromDateToGregorian() {
        
	    Date input = DateUtils.addDays(new Date(),-1);
		GregorianCalendar date = new GregorianCalendar();
        date.setTime(input);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.HOUR_OF_DAY, 0);
        try {
        	XMLGregorianCalendar caldate= DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
        	caldate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        	caldate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        	return caldate;
        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }
    
    public static XMLGregorianCalendar convertDateFromDateToGregorian_20() {
        
   	 Date input = DateUtils.addDays(new Date(),-20);
		GregorianCalendar date = new GregorianCalendar();
        date.setTime(input);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.HOUR_OF_DAY, 0);
        try {
        	XMLGregorianCalendar caldate= DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
        	caldate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        	caldate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        	return caldate;
        } catch (DatatypeConfigurationException e) {
            return null;
        }
}
    
    public static XMLGregorianCalendar convertDateFromDateToGregorian(int min) {
        
      	 Date input = DateUtils.addDays(new Date(),min);
   		GregorianCalendar date = new GregorianCalendar();
           date.setTime(input);
           date.set(Calendar.SECOND, 0);
           date.set(Calendar.MINUTE, 0);
           date.set(Calendar.HOUR_OF_DAY, 0);
           try {
           	XMLGregorianCalendar caldate= DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
           	caldate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
           	caldate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
           	return caldate;
           } catch (DatatypeConfigurationException e) {
               return null;
           }
   }
}

