package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class FundTransferPO extends AbstractPO {
	WebDriver driver;

	public FundTransferPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
