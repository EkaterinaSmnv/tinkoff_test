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
public class IncorrectAmountValidator implements Validator {
	private static final String ERR_MSG_MIN_AMOUNT = "Минимальная сумма перевода - 10 ₽";
	private static final String ERR_MSG_MAX_AMOUNT = "Максимальная сумма перевода - 15 000 ₽";
	/**
	 * Constructor
	 */
	public IncorrectAmountValidator() {
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
		page.setAmountField("1");
		page.clickPayButton();
		
		assertTrue("Incorrect error message for amount field", ERR_MSG_MIN_AMOUNT.equals(page.getAmountFieldErrorMsg()));
		page.clearAmountField();
		page.setAmountField("20000");
		page.clickPayButton();
		assertTrue("Incorrect error message for amount field", ERR_MSG_MAX_AMOUNT.equals(page.getAmountFieldErrorMsg()));
	}

}
