package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class DepositPO extends AbstractPO {
	WebDriver driver;

	public DepositPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
