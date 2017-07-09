/**
 * 
 */
package com.semenova.tinkoff_test.webpages;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.semenova.tinkoff_test.webpages.utility_providers.AbstractUtilityProviderPage;
import com.semenova.tinkoff_test.webpages.utility_providers.UtilityProviderPageFactory;
import com.semenova.tinkoff_test.webpages.utility_providers.UtilityProviderPageFactory.FactoryNotImplementedException;

/**
 * @author semenova
 *
 */
public class UtilityPaymentsPage extends AbstractTopMenuPage {

	/**
	 * @param driver
	 */
	public UtilityPaymentsPage(WebDriver driver) {
		super(driver);
	}
	
	public String getCurrentRegion() {
		return getRegionElement().getText();
	}
	
	public UtilityPaymentsPage setCurrentRegion(String regionName) {
		getRegionElement().click();
		WebElement regionsDiv = findDivByClass("ui-regions");
		String xpathRequest = String.format("//div[@class='ui-regions__item']/span[text()='%s']", regionName);
		WebElement region = regionsDiv.findElement(By.xpath(xpathRequest));
		region.click();
		return this;
	}
	
	public List<Provider> getProviders() {
		WebElement providersList = findElementWithWait(By.xpath("//div[@class='ui-page-frame__content']/span/section/ul"));
    	List<WebElement> providersElements = providersList.findElements(By.xpath("//li/span[@class='ui-link ui-menu__logo-link']/a"));
    	List<Provider> providers = new ArrayList<Provider>(providersElements.size());
    	for(WebElement elem : providersElements) {
    		providers.add(new Provider(driver, elem));
    	}
    	return providers;
	}

	private WebElement getRegionElement() {
		WebElement utilityPaymentsDiv = findDivByText("Коммунальные платежи");
		WebElement currentRegion = utilityPaymentsDiv.findElement(By.xpath("//span[@class='ui-link payment-page__title_inner']"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(currentRegion));
		return currentRegion;
	}
	
	public class Provider {
		private WebDriver driver;
		private WebElement providerElem;
		
		Provider(WebDriver driver, WebElement provider) {
			this.driver = driver;
			providerElem = provider;
		}
		
		public String getTitle() {
			return providerElem.getAttribute("Title");
		}
		
		public <T extends AbstractUtilityProviderPage> T select() {
			T page = null;
			String providerName = this.getTitle();
			providerElem.click();
			try {
				page = UtilityProviderPageFactory.getFactory(providerName).createPage(driver);
			} catch (FactoryNotImplementedException e) {
				e.printStackTrace();
				assertTrue(false);
			}
			
			return page;
		}
	}
	
}
