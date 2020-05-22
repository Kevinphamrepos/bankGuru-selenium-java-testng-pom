package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static ManagerHomePO getManagerHomePage(WebDriver driver) {
		return new ManagerHomePO(driver);
	}
	
	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}
	
	public static RegisterPO getRegisterPage(WebDriver driver) {
		return new RegisterPO(driver);
	}
	
	public static NewCustomerPO getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPO(driver);
	}
	
	public static EditCustomerPO getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPO(driver);
	}
	
	public static DeleteCustomerPO getDeleteCustomerPage(WebDriver driver) {
		return new DeleteCustomerPO(driver);
	}
	
	public static NewAccountPO getNewAccountPage(WebDriver driver) {
		return new NewAccountPO(driver);
	}
	
	public static EditAccountPO getEditAccountPage(WebDriver driver) {
		return new EditAccountPO(driver);
	}
	
	public static DeleteAccountPO getDelelteAccountPage(WebDriver driver) {
		return new DeleteAccountPO(driver);
	}
	
	public static DepositPO getDepositPage(WebDriver driver) {
		return new DepositPO(driver);
	}
	
	public static WithdrawalPO getWithdrawalPage(WebDriver driver) {
		return new WithdrawalPO(driver);
	}
	
	public static FundTransferPO getFundTransferPage(WebDriver driver) {
		return new FundTransferPO(driver);
	}
	
	public static BalanceEnquiryPO getBalanceEnquiryPage(WebDriver driver) {
		return new BalanceEnquiryPO(driver);
	}

	public static ChangePasswordPO getChangePasswordPage(WebDriver driver) {
		return new ChangePasswordPO(driver);
	}

	public static MiniStatementPO getMiniStatementPage(WebDriver driver) {
		return new MiniStatementPO(driver);
	}

	public static CustomisedStatementPO getCustomisedStatementPage(WebDriver driver) {
		return new CustomisedStatementPO(driver);
	}

	
}
