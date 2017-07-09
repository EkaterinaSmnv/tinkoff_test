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
public class IncorrectCodeValidator implements Validator {
	private static final String ERR_INCORRECT_CODE_MSG = "Поле неправильно заполнено";
	/**
	 * 
	 */
	public IncorrectCodeValidator() {
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
		
		page.setCodeField("0");
		
		page.clickPayButton();
		
		assertTrue("Incorrect error message for code field", ERR_INCORRECT_CODE_MSG.equals(page.getCodeFieldErrorMsg()));
	}

}
