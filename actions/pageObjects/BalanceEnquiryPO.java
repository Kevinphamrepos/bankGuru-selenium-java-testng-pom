package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class BalanceEnquiryPO extends AbstractPO {
	WebDriver driver;

	public BalanceEnquiryPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
