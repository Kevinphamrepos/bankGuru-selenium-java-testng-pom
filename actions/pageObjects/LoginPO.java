package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.LoginPageUI;

public class LoginPO extends AbstractPO {
	WebDriver driver;

	public LoginPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public String getLoginPageUrl() {
		return getPageCurrentUrl();
	}

	public RegisterPO clickOnHereLink() {
		waitForElementVisibleByXpath(LoginPageUI.HERE_LINK);
		clickOnElement(LoginPageUI.HERE_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputToUserIDTextbox(String userID) {
		waitForElementVisibleByXpath(LoginPageUI.USERID_TEXTBOX);
		sendKeysToElement(LoginPageUI.USERID_TEXTBOX, userID);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisibleByXpath(LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public ManagerHomePO clickOnSubmitButton() {
		waitForElementVisibleByXpath(LoginPageUI.LOGIN_BUTTON);
		clickOnElement(LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getManagerHomePage(driver);
	}

}
