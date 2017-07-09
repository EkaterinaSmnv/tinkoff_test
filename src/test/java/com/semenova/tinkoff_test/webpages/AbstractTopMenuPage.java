/**
 * 
 */
package com.semenova.tinkoff_test.webpages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author semenova
 *
 */
public abstract class AbstractTopMenuPage extends AbstractPage {

	/**
	 * @param driver
	 */
	public AbstractTopMenuPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public PaymentsPage goToPayments() {
		WebElement paymentMenuItem = findElementWithWait(By.xpath("//ul[@class='_2CDK1']/li/div/a[.//span[text()='Платежи']]"));
		if (paymentMenuItem.isDisplayed()) {
			clickElement(paymentMenuItem);
			return new PaymentsPage(driver);
		} else { // top menu collapsed in left corner 
			WebElement menuToggle = findElementWithWait(By.xpath("//label[@role='button' and @class='_1WFzI']"));
			if (menuToggle.isDisplayed()) {
				clickElement(menuToggle);
				paymentMenuItem = findElementWithWait(By.xpath("//ul[@class='_1cmv-']/li/div/a[.//span[text()='Платежи']]"));
				clickElement(paymentMenuItem);
				return new PaymentsPage(driver);
			}
		}
		assertTrue("Cannot go to payments page", false);
		return null;
	}

}
