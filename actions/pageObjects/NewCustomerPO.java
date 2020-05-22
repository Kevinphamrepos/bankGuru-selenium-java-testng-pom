package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class NewCustomerPO extends AbstractPO {
	WebDriver driver;

	public NewCustomerPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
