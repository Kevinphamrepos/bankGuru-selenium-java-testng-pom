package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class WithdrawalPO extends AbstractPO {
	WebDriver driver;

	public WithdrawalPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
