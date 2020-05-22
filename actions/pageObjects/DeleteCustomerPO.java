package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.DeleteCustomerPageUI;

public class DeleteCustomerPO extends AbstractPO {
	WebDriver driver;

	public DeleteCustomerPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public LoginPO clickOnLogoutLink() {
		waitForElementVisibleByXpath(DeleteCustomerPageUI.LOG_OUT_LINK);
		clickOnElement(DeleteCustomerPageUI.LOG_OUT_LINK);
		acceptAlert();
		return PageGeneratorManager.getLoginPage(driver);
	}

}
