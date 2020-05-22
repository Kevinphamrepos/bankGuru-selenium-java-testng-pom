package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.RegisterPageUI;

public class RegisterPO extends AbstractPO {
	WebDriver driver;

	public RegisterPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public void inputToEmailIDTextbox(String randomEmail) {
		waitForElementVisibleByXpath(RegisterPageUI.EMAILID_TEXTBOX);
		sendKeysToElement(RegisterPageUI.EMAILID_TEXTBOX, randomEmail);
	}

	public void clickOnSubmmitButton() {
		waitForElementVisibleByXpath(RegisterPageUI.SUBMIT_BUTTON);
		clickOnElement(RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDValue() {
		waitForElementVisibleByXpath(RegisterPageUI.USERID_VALUE);
		return getTextElement(RegisterPageUI.USERID_VALUE);
	}

	public String getPasswordValue() {
		waitForElementVisibleByXpath(RegisterPageUI.PASSWORD_VALUE);
		return getTextElement(RegisterPageUI.PASSWORD_VALUE);
	}

	public LoginPO openLoginPage(String loginPageUrl) {
		openUrl(loginPageUrl);
		return PageGeneratorManager.getLoginPage(driver);
	}

}
