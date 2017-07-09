/**
 * 
 */
package com.semenova.tinkoff_test.validators.zhku_moscow_payment_page;

import static org.junit.Assert.assertTrue;

import com.semenova.tinkoff_test.webpages.utility_providers.ZHKUMoscowPagePayment;

/**
 * @author semenova
 *
 */
public class IncorrectPeriodValidator implements Validator {
	private static final String ERR_INCORRECT_PERIOD_MSG = "Поле заполнено некорректно";
	
	/**
	 * 
	 */
	public IncorrectPeriodValidator() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.semenova.tinkoff_test.validators.zhku_moscow_payment_page.Validator#validate(com.semenova.tinkoff_test.webpages.utility_providers.ZHKUMoscowPagePayment)
	 */
	@Override
	public void validate(ZHKUMoscowPagePayment page) {
		page.clearCodeField();
		page.clearPeriodField();
		page.clearInsuranceField();
		page.clearAmountField();
		
		page.setPeriodField("32.32");
		
		page.clickPayButton();
		
		assertTrue("Incorrect error message for code field", ERR_INCORRECT_PERIOD_MSG.equals(page.getPeriodFieldErrorMsg()));
	}

}
