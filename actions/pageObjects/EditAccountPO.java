package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class EditAccountPO extends AbstractPO {
	WebDriver driver;

	public EditAccountPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
