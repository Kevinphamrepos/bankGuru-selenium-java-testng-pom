package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class CustomisedStatementPO extends AbstractPO {
	WebDriver driver;

	public CustomisedStatementPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
