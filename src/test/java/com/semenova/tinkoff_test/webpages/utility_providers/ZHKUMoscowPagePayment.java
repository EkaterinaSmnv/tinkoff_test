/**
 * 
 */
package com.semenova.tinkoff_test.webpages.utility_providers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author semenova
 *
 */
public class ZHKUMoscowPagePayment extends AbstractUtilityProviderPage {
	private static final String PAY_BUTTON_XPATH = "//button[@class='ui-button ui-button_failure ui-button_mobile-wide ui-button_provider-pay ui-button_size_xxl ui-form__button ui-button_inline']";
	private static final String CODE_INPUT_FIELD_NAME = "provider-payerCode";
	private static final String PERIOD_INPUT_FIELD_NAME = "provider-period";
	
	private static final String FORMAT_STRING_INPUT_BY_SPAN_TEXT = "//div[.//span[text()='%s']]/input";
	private static final String INSURANCE_INPUT_SPAN_TEXT = "Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве, ₽";
	private static final String AMOUNT_INPUT_SPAN_TEXT = "от 10 до 15 000 ₽";
	
	private static final String ERROR_DIV_CLASS = "ui-form-field-error-message ui-form-field-error-message_ui-form";
	
	
	private WebElement codeInput;
	private WebElement periodInput;
	private WebElement insuranceInput;
	private WebElement amountInput;
	private WebElement payButton;
	
	/**
	 * @param driver
	 */
	public ZHKUMoscowPagePayment(WebDriver driver) {
		super(driver);
		
		codeInput = findElementByName(CODE_INPUT_FIELD_NAME);
		periodInput = findElementByName(PERIOD_INPUT_FIELD_NAME);
		insuranceInput = findElementWithWait(By.xpath(String.format(FORMAT_STRING_INPUT_BY_SPAN_TEXT, INSURANCE_INPUT_SPAN_TEXT)));
		amountInput = findElementWithWait(By.xpath(String.format(FORMAT_STRING_INPUT_BY_SPAN_TEXT, AMOUNT_INPUT_SPAN_TEXT)));
		payButton = findElementWithWait(By.xpath(PAY_BUTTON_XPATH));
	}
	
	public ZHKUMoscowPagePayment setCodeField(String code) {
		codeInput.sendKeys(code);
		return this;
	}
	
	public ZHKUMoscowPagePayment clearCodeField() {
		codeInput.clear();
		return this;
	}
	
	public ZHKUMoscowPagePayment setPeriodField(String period) {
		periodInput.sendKeys(period);
		return this;
	}
	
	public ZHKUMoscowPagePayment clearPeriodField() {
		periodInput.clear();
		return this;
	}
	
	public ZHKUMoscowPagePayment setInsuranceField(String amount) {
		insuranceInput.sendKeys(amount); 
		return this;
	}
	
	public ZHKUMoscowPagePayment clearInsuranceField() {
		insuranceInput.clear();
		return this;
	}
	
	public ZHKUMoscowPagePayment setAmountField(String amount) {
		amountInput.sendKeys(amount); 
		return this;
	}
	
	public ZHKUMoscowPagePayment clearAmountField() {
		amountInput.clear();
		return this;
	}
	
	public ZHKUMoscowPagePayment clickPayButton() {
		clickElement(payButton);
		waitVisibleOf(codeInput);
		waitVisibleOf(periodInput);
		waitVisibleOf(insuranceInput);
		waitVisibleOf(amountInput);
		waitVisibleOf(payButton);
		return this;
	}
	
	public String getCodeFieldErrorMsg() {
		String errMsg = "";
		String xpathExpression = String.format("//div[.//input[@name='%s']]/div[@class='%s']", CODE_INPUT_FIELD_NAME, ERROR_DIV_CLASS);
		WebElement codeFieldError = findElement(By.xpath(xpathExpression));
		if(codeFieldError != null) {
			errMsg = codeFieldError.getText();
		}
		return errMsg;
	}
	
	public String getPeriodFieldErrorMsg() {
		String errMsg = "";
		String xpathExpression = String.format("//div[.//input[@name='%s']]/div[@class='%s']", PERIOD_INPUT_FIELD_NAME, ERROR_DIV_CLASS);
		WebElement periodFieldError = findElement(By.xpath(xpathExpression));
		if(periodFieldError != null) {
			errMsg = periodFieldError.getText();
		}
		return errMsg;
	}
	
	public String getAmountFieldErrorMsg() {
		String errMsg = "";
		String xpathExpression = String.format("//div[.//b[starts-with(text(),'Сумма платежа')]]/div[@class='%s']", ERROR_DIV_CLASS);
		WebElement amountFieldError = findElement(By.xpath(xpathExpression));
		if(amountFieldError != null) {
			errMsg = amountFieldError.getText();
		}
		return errMsg;
	}

}
