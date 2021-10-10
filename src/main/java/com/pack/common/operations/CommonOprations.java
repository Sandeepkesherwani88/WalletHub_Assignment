package com.pack.common.operations;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonOprations {

	private WebDriver driver;
	WebDriverWait wait;
	boolean elementStatus;
	Logger logger=LogManager.getLogger(CommonOprations.class);
	
	public CommonOprations(WebDriver driver) {
		this.driver = driver;
		wait=new WebDriverWait(this.driver, 30);
	}

	public void clickOnElement(By locator) {
		checkVisibilityOfElement(locator);
		logger.info("**********************Clicking on element for "+ locator +"*************************");
		this.waitTillElementIsClickable(locator);
		this.driver.findElement(locator).click();		
	}

	public String getText(By locator) {
		String text=null;
		checkVisibilityOfElement(locator);
		logger.info("**********************Getting the text for "+ locator +"*************************");
		text=this.driver.findElement(locator).getText();
		logger.info("**********************The text for "+ locator +" is "+text+"*************************");
		return text;
	}

	public void enterValueInTextBox(By locator,String text) {
		checkVisibilityOfElement(locator);
		logger.info("**********************Sending keys for "+ locator +"*************************");
		this.driver.findElement(locator).sendKeys(text);		
	}

	public void takeSnapShot(String fileName) throws Exception{
		try {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileName+".png");
		FileUtils.copyFile(SrcFile, DestFile);
		}catch(Exception ex) {
			logger.error("**********************Exception occured while capturing the screenshots *************************");
		}
	}

	public void scrollIntoView(By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", this.driver.findElement(locator));
		logger.info("**********************Scrolling for "+ locator +"*************************");		
	}

	public void refreshPage() {
		this.driver.navigate().refresh();
		logger.info("**********************Page Refreshed Successfully*************************");		
	}

	public boolean checkVisibilityOfElement_toBeDeleted(By locator) {
		WebElement element;
		element=this.driver.findElement(locator);
		if (element.isDisplayed()) {
			logger.info("**********************Element is visible for "+ locator +"*************************");
			return true;
		}else {
			logger.error("**********************Element is not visible for "+ locator +"*************************");
			return false;
		}
	}

	public boolean checkVisibilityOfElement(By locator) {
		try {
			logger.info("**********************Waiting for element "+ locator +"*************************");
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}catch(Exception exception) {
			logger.error("**********************Element is not visible for "+ locator +"*************************");
			logger.trace(exception.getMessage());
			return false;
		}
		return true;
	}

	public boolean checkElementAbsence(By locator) {
		try {
			logger.info("**********************Waiting for element "+ locator +"*************************");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}catch(Exception exception) {
			logger.error("**********************Element is not visible for "+ locator +"*************************");
			logger.trace(exception.getMessage());
			return false;
		}
		return true;
	}

	public boolean isElementDisplayed(By locator) {
		elementStatus=driver.findElement(locator).isDisplayed();
		if(elementStatus) {
			return true;
		}else {
			return false;
		}
	}

	public void waitTillElementIsClickable(By locator) {
		try {
			logger.info("**********************Waiting for element "+ locator +"*************************");
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}catch(Exception exception) {
			logger.error("**********************Element is not clickable for "+ locator +"*************************");
			logger.trace(exception.getMessage());
		}
	}
	
	public Properties readFromPropertiesFile()throws Exception{  
	    FileReader reader=new FileReader("C:\\Users\\sande\\eclipse-workspace\\Wallethub\\src\\main\\resources\\testDataProperties.properties");  	      
	    Properties propLoad=new Properties();  
	    propLoad.load(reader);
	    return propLoad;
	}  
	
	public boolean checkIfCheckboxCheckedorNot(By locator) {
		logger.info("**********************Checking Element is checked or not for "+ locator +"*************************");
		if(this.driver.findElement(locator).isSelected()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getAttributValue(By locator,String attribute) {
		logger.info("**********************Get Attribute "+attribute+" for "+ locator +"*************************");
		String attributValue=this.driver.findElement(locator).getAttribute(attribute);
		return attributValue;
	}
	
	public void performMouseHover(By locator) {
		logger.info("**********************Performing mouse hover on "+ locator +"*************************");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).perform();
	}
	
	public void performMouseHoverAndClick(By locator) {
		logger.info("**********************Performing mouse hover on "+ locator +"*************************");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).click().build().perform();
	}
	
	public List<WebElement> findElements(By locator) {
		logger.info("**********************Finding List of Elements for "+ locator +"*************************");
		List<WebElement> elements=driver.findElements(locator);
		return elements;
	}
	
	public int getSizeOfFindElements(By locator) {
		int numberOfErlements=findElements(locator).size();
		return numberOfErlements;
	}
	

	

}
