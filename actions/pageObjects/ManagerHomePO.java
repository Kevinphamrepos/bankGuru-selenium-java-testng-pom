package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.ManagerHomePageUI;

public class ManagerHomePO extends AbstractPO {
	WebDriver driver;

	public ManagerHomePO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public boolean isWelcomeMgsDisplayed() {
		waitForElementVisibleByXpath(ManagerHomePageUI.WELCOME_MGS);
		return isElementDisplayed(ManagerHomePageUI.WELCOME_MGS);
	}

	public String getManagerID() {
		waitForElementVisibleByXpath(ManagerHomePageUI.MANAGER_ID_TEXT);
		return getTextElement(ManagerHomePageUI.MANAGER_ID_TEXT);
	}

}
