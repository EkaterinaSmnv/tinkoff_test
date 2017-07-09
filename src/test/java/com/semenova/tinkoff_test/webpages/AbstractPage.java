package com.semenova.tinkoff_test.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	protected WebDriver driver;
	
	public AbstractPage(WebDriver d) {
		driver = d;
	}
	
	public String getHTMLSource() {
		return driver.getPageSource();
	}
	
	public String getURL() {
		return driver.getCurrentUrl();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	protected WebElement findElement(By by) {
		return driver.findElement(by);
	}
	
	protected WebElement findElementWithWait(By by) {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
	
	protected WebElement findDivByText(String text) {
    	String xpathRequest = String.format("//div[text()='%s']", text);
    	return findElementWithWait(By.xpath(xpathRequest));
    }
	
	protected WebElement findDivByClass(String name) {
    	String xpathRequest = String.format("//div[@class='%s']", name);
    	return findElementWithWait(By.xpath(xpathRequest));
    }
	
	protected WebElement findLinkByTitle(String title) {
    	String xpathRequest = String.format("//a[@title='%s']", title);
    	return findElementWithWait(By.xpath(xpathRequest));
    }
	
	protected WebElement findLinkByText(String text) {
		return findElementWithWait(By.linkText(text));
	}
	
	protected WebElement findElementByName(String name) {
		return findElementWithWait(By.name(name));
	}
	
	protected void clickElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	protected void waitVisibleOf(WebElement element ) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
