package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class NewAccountPO extends AbstractPO {
	WebDriver driver;

	public NewAccountPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
