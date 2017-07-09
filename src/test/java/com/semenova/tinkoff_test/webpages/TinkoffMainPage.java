/**
 * 
 */
package com.semenova.tinkoff_test.webpages;

import org.openqa.selenium.WebDriver;

/**
 * @author semenova
 *
 */
public class TinkoffMainPage extends AbstractTopMenuPage {
	private static String TINKOFF_URL = "https://www.tinkoff.ru";
	
	/**
	 * Fabric method to create instance of TinkoffMainPage class
	 * 
	 * @param driver - WebDriver
	 * @return TinkoffMainPgae object
	 */
	public static TinkoffMainPage openPage(WebDriver driver) {
		driver.get(TINKOFF_URL);
		return new TinkoffMainPage(driver);
	}

	/**
	 * @param driver - WebDriver
	 */
	public TinkoffMainPage(WebDriver driver) {
		super(driver);
	}

}
