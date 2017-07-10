package com.semenova.tinkoff_test;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.semenova.tinkoff_test.validators.zhku_moscow_payment_page.EmptyFieldsValidator;
import com.semenova.tinkoff_test.validators.zhku_moscow_payment_page.IncorrectAmountValidator;
import com.semenova.tinkoff_test.validators.zhku_moscow_payment_page.IncorrectCodeValidator;
import com.semenova.tinkoff_test.validators.zhku_moscow_payment_page.IncorrectPeriodValidator;
import com.semenova.tinkoff_test.validators.zhku_moscow_payment_page.Validator;
import com.semenova.tinkoff_test.webpages.PaymentsPage;
import com.semenova.tinkoff_test.webpages.TinkoffMainPage;
import com.semenova.tinkoff_test.webpages.UtilityPaymentsPage;
import com.semenova.tinkoff_test.webpages.PaymentsPage.SearchResult;
import com.semenova.tinkoff_test.webpages.UtilityPaymentsPage.Provider;
import com.semenova.tinkoff_test.webpages.utility_providers.ZHKUMoscowPage;
import com.semenova.tinkoff_test.webpages.utility_providers.ZHKUMoscowPagePayment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Selenium test for tinkoff.com.
 */
public class TinkoffTest 
{
	private static final String EXPECTED_UTILITY_PROVIDER = "ЖКУ-Москва";
	private WebDriver driver;
	
	private List<Validator> zhkuMoscowPagePaymentValidators = new ArrayList<Validator>()
	{
		private static final long serialVersionUID = 7882005727954867022L;

		{
			add(new EmptyFieldsValidator());
			add(new IncorrectCodeValidator());
			add(new IncorrectPeriodValidator());
			add(new IncorrectAmountValidator());
		}
	};
    
    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver","C:\\Development\\JAVA\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @After
    public void closeBrowser() throws IOException {
        driver.quit();
    }

    /**
     * Test
     */
    @Test
    public void testTinkoff()
    {
    	// 1
    	TinkoffMainPage mainPage = TinkoffMainPage.openPage(driver);
    	// 2
    	PaymentsPage paymentsPage = mainPage.goToPayments();
    	// 3
    	UtilityPaymentsPage utilityPaymentsPage = paymentsPage.goToUtilityPaymentsPage();
    	// 4
    	if (!utilityPaymentsPage.getCurrentRegion().startsWith("Москв")) {
    		utilityPaymentsPage = utilityPaymentsPage.setCurrentRegion("г. Москва");
    	}
    	//5
    	List<Provider> providers = utilityPaymentsPage.getProviders();
    	Provider zhkuMoscowProvider  = providers.get(0);
    	String firstProviderTitle = zhkuMoscowProvider.getTitle();
    	assertTrue("First utility provider not equals to expected", EXPECTED_UTILITY_PROVIDER.equals(firstProviderTitle));
    	
    	ZHKUMoscowPage zhkuMoscowPage = zhkuMoscowProvider.select();
    	String zhkuMoscowPageURL1 = zhkuMoscowPage.getURL();
    	//6
    	ZHKUMoscowPagePayment zhkuMoscowPagePayment = zhkuMoscowPage.selectPaymentsTab();
    	// 7
    	for(Validator validator : zhkuMoscowPagePaymentValidators) {
    		validator.validate(zhkuMoscowPagePayment);
    	}    	
    	// 8
    	paymentsPage = zhkuMoscowPagePayment.goToPayments();
    	// 9
    	List<SearchResult> searchResults = paymentsPage.searchFor(firstProviderTitle);
    	assertTrue("Cannot find utility provider", !searchResults.isEmpty());
    	// 10
    	SearchResult firstSearchResult = searchResults.get(0);
    	assertTrue("First result in search is not equal to requested utility provider", firstProviderTitle.equals(firstSearchResult.getTitle()));
    	// 11
    	zhkuMoscowPage = firstSearchResult.select();
    	String zhkuMoscowPageURL2 = zhkuMoscowPage.getURL();
    	assertTrue("zhkuMoscowPage differs to the previous load", zhkuMoscowPageURL1.equals(zhkuMoscowPageURL2));
    	// 12
    	paymentsPage = zhkuMoscowPage.goToPayments();
    	utilityPaymentsPage = paymentsPage.goToUtilityPaymentsPage();
    	// 13
    	utilityPaymentsPage = utilityPaymentsPage.setCurrentRegion("г. Санкт-Петербург");
    	// 14
    	providers = utilityPaymentsPage.getProviders();
    	for (Provider provider : providers) {
    		assertTrue("Saint-Petersburg utility providers list contains unexpected provider", !firstProviderTitle.equals(provider.getTitle()));
    	}
    }
}
