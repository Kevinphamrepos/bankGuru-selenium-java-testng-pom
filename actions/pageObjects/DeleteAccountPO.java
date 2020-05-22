package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class DeleteAccountPO extends AbstractPO {
	WebDriver driver;

	public DeleteAccountPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
