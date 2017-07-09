/**
 * 
 */
package com.semenova.tinkoff_test.webpages.utility_providers;

import org.openqa.selenium.WebDriver;

/**
 * @author semenova
 *
 */
public class ZHKUMoscowPageFactory extends UtilityProviderPageFactory {

	public ZHKUMoscowPageFactory() {
	}

	/* (non-Javadoc)
	 * @see com.semenova.tinkoff_test.webpages.utility_providers.UtilityProviderPageFactory#createPage(org.openqa.selenium.WebDriver)
	 */
	@Override
	public <T extends AbstractUtilityProviderPage> T createPage(WebDriver driver) {
		@SuppressWarnings("unchecked")
		T page = (T)new ZHKUMoscowPage(driver);
		return page;
	}

}
