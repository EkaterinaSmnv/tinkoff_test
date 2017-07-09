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
public class EmptyFieldsValidator implements Validator {
	private static final String ERR_MSG = "Поле обязательное";
	
	/**
	 * 
	 */
	public EmptyFieldsValidator() {
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
		page.clickPayButton();
    	assertTrue("Incorect error message for code field", ERR_MSG.equals(page.getCodeFieldErrorMsg()));
    	assertTrue("Incorect error message for period field", ERR_MSG.equals(page.getPeriodFieldErrorMsg()));
    	assertTrue("Incorrect error message for amount field", ERR_MSG.equals(page.getAmountFieldErrorMsg()));
	}
}
