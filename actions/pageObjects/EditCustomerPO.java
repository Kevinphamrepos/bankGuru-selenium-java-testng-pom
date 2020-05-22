package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class EditCustomerPO extends AbstractPO {
	WebDriver driver;

	public EditCustomerPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
