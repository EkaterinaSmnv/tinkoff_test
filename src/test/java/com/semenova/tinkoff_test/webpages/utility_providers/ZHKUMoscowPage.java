/**
 * 
 */
package com.semenova.tinkoff_test.webpages.utility_providers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author semenova
 *
 */
public class ZHKUMoscowPage extends AbstractUtilityProviderPage {
	private static final String PAYMENTS_TAB_NAME = "Оплатить ЖКУ в Москве";

	WebElement paymentsTab;
	/**
	 * @param driver
	 */
	public ZHKUMoscowPage(WebDriver driver) {
		super(driver);
		paymentsTab = findLinkByTitle(PAYMENTS_TAB_NAME);
	}
	
	public ZHKUMoscowPagePayment selectPaymentsTab() {
		clickElement(paymentsTab);
		return new ZHKUMoscowPagePayment(driver);
	}

}
