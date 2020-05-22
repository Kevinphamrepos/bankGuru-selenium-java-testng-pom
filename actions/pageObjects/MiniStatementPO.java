package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class MiniStatementPO extends AbstractPO {
	WebDriver driver;

	public MiniStatementPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
