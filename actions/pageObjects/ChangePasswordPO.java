package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class ChangePasswordPO extends AbstractPO {
	WebDriver driver;

	public ChangePasswordPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
