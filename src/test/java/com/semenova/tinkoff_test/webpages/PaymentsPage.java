/**
 * 
 */
package com.semenova.tinkoff_test.webpages;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
public class PaymentsPage extends AbstractTopMenuPage {
	private static final String UTILITY_PAYMENTS_LINK_TEXT = "Коммунальные платежи";

	/**
	 * @param driver
	 */
	public PaymentsPage(WebDriver driver) {
		super(driver);
	}

	public UtilityPaymentsPage goToUtilityPaymentsPage() {
		WebElement utilityPayment = findElementWithWait(By.linkText(UTILITY_PAYMENTS_LINK_TEXT));
    	clickElement(utilityPayment);
    	return new UtilityPaymentsPage(driver);
	}
	
	public List<SearchResult> searchFor(String searchTerm) {
		WebElement searchField = findElementWithWait(By.xpath("//input[@type='text' and @class='_2XFoD _1phV_']"));
		searchField.sendKeys(searchTerm);
		// need to wait while results are available
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_2zhCT _2b7Gu']")));
		List<WebElement> searchResultElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_3WKsv _3KH0m HhZgX _2wQkF']")));
		
		List<SearchResult> results = new ArrayList<SearchResult>(searchResultElements.size());
		
		for(WebElement element : searchResultElements) {
			results.add(new SearchResult(element));
		}
		
		return results;
	}
	
	public class SearchResult {
		private WebElement element;
		
		public SearchResult(WebElement element) {
			this.element = element;
		}
		
		public String getTitle() {
			return element.getText();
		}
		
		public <T extends AbstractUtilityProviderPage> T select() {
			T page = null;
			String title = this.getTitle();
			element.click();
			try {
				page = UtilityProviderPageFactory.getFactory(title).createPage(driver);
			} catch (FactoryNotImplementedException e) {
				e.printStackTrace();
				assertTrue(false);
			}
			
			
			
			return page;
		}
	}
}
