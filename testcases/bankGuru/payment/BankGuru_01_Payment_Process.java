package bankGuru.payment;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.BalanceEnquiryPO;
import pageObjects.DeleteAccountPO;
import pageObjects.DeleteCustomerPO;
import pageObjects.DepositPO;
import pageObjects.EditAccountPO;
import pageObjects.EditCustomerPO;
import pageObjects.FundTransferPO;
import pageObjects.LoginPO;
import pageObjects.ManagerHomePO;
import pageObjects.NewAccountPO;
import pageObjects.NewCustomerPO;
import pageObjects.RegisterPO;
import pageObjects.WithdrawalPO;
import reportScreenshot.ExtentTestManager;

public class BankGuru_01_Payment_Process extends AbstractTest {
	private WebDriver driver;

	private ManagerHomePO managerHomePage;
	private RegisterPO registerPage;
	private LoginPO loginPage;
	private NewCustomerPO newCustomerPage;
	private EditCustomerPO editCustomerPage;
	private NewAccountPO newAccountPage;
	private EditAccountPO editAccountPage;
	private DepositPO depositPage;
	private WithdrawalPO withdrawalPage;
	private FundTransferPO fundTransferPage;
	private BalanceEnquiryPO balanceEnquiryPage;
	private DeleteAccountPO deleteAccountPage;
	private DeleteCustomerPO deleteCustomerPage;
	
	String userID, password, loginPageUrl, customerID, firstAccountID, secondAccountID;
	String randomEmail = "Kevinpham_" + randomNumber() + "@hotmail.com";
	String accountTypeSavings = "Savings";
	String accountTypeCurrent = "Current";
	
	String newCustomerName = "Kevin Pham";
	String newGender = "male";
	String newDateofBirth = "1989-02-15";
	String newAddress = "123 ABC Street";
	String newCity = "Phoenix";
	String newState = "Arizona";
	String newPin = "850058";
	String newMobileNumber = "0123888999";
	String newRandomEmail = "Kevinpham_" + randomNumber() + "@gmail.com";
	String newPassword = "abcxyz123";

	String editAddress = "789 XYZ Street";
	String editCity = "Los Angeles";
	String editState = "California";
	String editPin = "980089";
	String editMobileNumber = "0123123123";
	String editRandomEmail = "Kevinpham_" + randomNumber() + "@yahoo.com";

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
	}

	@Test
	public void Payment_01_RegisterAndLogin(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_01_RegisterAndLogin");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "RegisterAndLogin - Step 01: Register New User ID and Password By Random Email");
		loginPageUrl = loginPage.getLoginPageUrl();
		registerPage = loginPage.clickOnHereLink();
		registerPage.inputToEmailIDTextbox(randomEmail);
		registerPage.clickOnSubmmitButton();
		userID = registerPage.getUserIDValue();
		password = registerPage.getPasswordValue();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "RegisterAndLogin - Step 02: Log in By Provided User ID and Password");
		loginPage = registerPage.openLoginPage(loginPageUrl);
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		managerHomePage = loginPage.clickOnSubmitButton();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "RegisterAndLogin - Step 03: Verify Welcome message and Manager ID displayed");
		verifyTrue(managerHomePage.isWelcomeMgsDisplayed());
		verifyEquals(managerHomePage.getManagerID(), "Manger Id : " + userID);
	}
	
	@Test
	public void Payment_02_AddNewCustomer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_02_AddNewCustomer");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "AddNewCustomer - Step 01: Create New Customer");
		newCustomerPage = (NewCustomerPO) managerHomePage.openMultiplePages("New Customer");
		verifyTrue(newCustomerPage.isDynamicHeaderTextDisplayed("Add New Customer"));
		newCustomerPage.inputToDynamicTextboxTextarea("name", newCustomerName);
		newCustomerPage.inputToDynamicTextboxTextarea("dob", newDateofBirth);
		newCustomerPage.inputToDynamicTextboxTextarea("addr", newAddress);
		newCustomerPage.inputToDynamicTextboxTextarea("city", newCity);
		newCustomerPage.inputToDynamicTextboxTextarea("state", newState);
		newCustomerPage.inputToDynamicTextboxTextarea("pinno", newPin);
		newCustomerPage.inputToDynamicTextboxTextarea("telephoneno", newMobileNumber);
		newCustomerPage.inputToDynamicTextboxTextarea("emailid", newRandomEmail);
		newCustomerPage.inputToDynamicTextboxTextarea("password", newPassword);
		newCustomerPage.clickOnDynamicButton("Submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "AddNewCustomer - Step 02: Verify Customer's Info are as input");
		verifyTrue(newCustomerPage.isDynamicHeaderTextDisplayed("Customer Registered Successfully!!!"));
		verifyEquals(newCustomerPage.getDynamicCellValue("Customer Name"), newCustomerName);
		verifyEquals(newCustomerPage.getDynamicCellValue("Gender"), newGender);
		verifyEquals(newCustomerPage.getDynamicCellValue("Birthdate"), newDateofBirth);
		verifyEquals(newCustomerPage.getDynamicCellValue("Address"), newAddress);
		verifyEquals(newCustomerPage.getDynamicCellValue("City"), newCity);
		verifyEquals(newCustomerPage.getDynamicCellValue("State"), newState);
		verifyEquals(newCustomerPage.getDynamicCellValue("Pin"), newPin);
		verifyEquals(newCustomerPage.getDynamicCellValue("Mobile No."), newMobileNumber);
		verifyEquals(newCustomerPage.getDynamicCellValue("Email"), newRandomEmail);
	
		customerID = newCustomerPage.getDynamicCellValue("Customer ID");
	}
	
	@Test
	public void Payment_03_EditCustomer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_03_EditCustomer");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "EditCustomer - Step 01: Edit Customer");
		editCustomerPage = (EditCustomerPO) newCustomerPage.openMultiplePages("Edit Customer");
		verifyTrue(editCustomerPage.isDynamicHeaderTextDisplayed("Edit Customer Form"));
		editCustomerPage.inputToDynamicTextboxTextarea("cusid", customerID);
		editCustomerPage.clickOnDynamicButton("Submit");
		verifyTrue(editCustomerPage.isDynamicHeaderTextDisplayed("Edit Customer"));
		editCustomerPage.inputToDynamicTextboxTextarea("addr", editAddress);
		editCustomerPage.inputToDynamicTextboxTextarea("city", editCity);
		editCustomerPage.inputToDynamicTextboxTextarea("state", editState);
		editCustomerPage.inputToDynamicTextboxTextarea("pinno", editPin);
		editCustomerPage.inputToDynamicTextboxTextarea("telephoneno", editMobileNumber);
		editCustomerPage.inputToDynamicTextboxTextarea("emailid", editRandomEmail);
		editCustomerPage.clickOnDynamicButton("Submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "EditCustomer - Step 02: Verify Customer's Info are updated");
		verifyTrue(editCustomerPage.isDynamicHeaderTextDisplayed("Customer details updated Successfully!!!"));
		verifyEquals(editCustomerPage.getDynamicCellValue("Address"), editAddress);
		verifyEquals(editCustomerPage.getDynamicCellValue("City"), editCity);
		verifyEquals(editCustomerPage.getDynamicCellValue("State"), editState);
		verifyEquals(editCustomerPage.getDynamicCellValue("Pin"), editPin);
		verifyEquals(editCustomerPage.getDynamicCellValue("Mobile No."), editMobileNumber);
		verifyEquals(editCustomerPage.getDynamicCellValue("Email"), editRandomEmail);
		
	}
	
	@Test
	public void Payment_04_NewAccount(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_04_NewAccount");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "NewAccount - Step 01: Creat a Payer's Account");
		newAccountPage = (NewAccountPO) editCustomerPage.openMultiplePages("New Account");
		verifyTrue(newAccountPage.isDynamicHeaderTextDisplayed("Add new account form"));
		newAccountPage.inputToDynamicTextboxTextarea("cusid", customerID);
		newAccountPage.selectItemInDynamicDropdown("selaccount", accountTypeSavings);
		newAccountPage.inputToDynamicTextboxTextarea("inideposit", "5000");
		newAccountPage.clickOnDynamicButton("submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "NewAccount - Step 02: Verify Payer's Account Info");
		verifyTrue(newAccountPage.isDynamicHeaderTextDisplayed("Account Generated Successfully!!!"));
		verifyEquals(newAccountPage.getDynamicCellValue("Customer ID"), customerID);
		verifyEquals(newAccountPage.getDynamicCellValue("Customer Name"), newCustomerName);
		verifyEquals(newAccountPage.getDynamicCellValue("Email"), editRandomEmail);
		verifyEquals(newAccountPage.getDynamicCellValue("Account Type"), accountTypeSavings);
		verifyEquals(newAccountPage.getDynamicCellValue("Date of Opening"), getToday());
		verifyEquals(newAccountPage.getDynamicCellValue("Current Amount"), "5000");
		firstAccountID = newAccountPage.getDynamicCellValue("Account ID");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "NewAccount - Step 03: Creat a Payees's Account");
		newAccountPage = (NewAccountPO) newAccountPage.openMultiplePages("New Account");
		verifyTrue(newAccountPage.isDynamicHeaderTextDisplayed("Add new account form"));
		newAccountPage.inputToDynamicTextboxTextarea("cusid", customerID);
		newAccountPage.selectItemInDynamicDropdown("selaccount", accountTypeSavings);
		newAccountPage.inputToDynamicTextboxTextarea("inideposit", "1000");
		newAccountPage.clickOnDynamicButton("submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "NewAccount - Step 04: Verify Payees's Account Info");
		verifyTrue(newAccountPage.isDynamicHeaderTextDisplayed("Account Generated Successfully!!!"));
		verifyEquals(newAccountPage.getDynamicCellValue("Customer ID"), customerID);
		verifyEquals(newAccountPage.getDynamicCellValue("Customer Name"), newCustomerName);
		verifyEquals(newAccountPage.getDynamicCellValue("Email"), editRandomEmail);
		verifyEquals(newAccountPage.getDynamicCellValue("Account Type"), accountTypeSavings);
		verifyEquals(newAccountPage.getDynamicCellValue("Date of Opening"), getToday());
		verifyEquals(newAccountPage.getDynamicCellValue("Current Amount"), "1000");
		secondAccountID = newAccountPage.getDynamicCellValue("Account ID");
	}
	
	@Test
	public void Payment_05_EditAccount(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_05_EditAccount");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "EditAccount - Step 01: Edit Account Type");
		editAccountPage = (EditAccountPO) newAccountPage.openMultiplePages("Edit Account");
		verifyTrue(editAccountPage.isDynamicHeaderTextDisplayed("Edit Account Form"));
		editAccountPage.inputToDynamicTextboxTextarea("accountno", firstAccountID);
		editAccountPage.clickOnDynamicButton("Submit");
		verifyTrue(editAccountPage.isDynamicHeaderTextDisplayed("Edit Account Entry Form"));
		editAccountPage.selectItemInDynamicDropdown("a_type", accountTypeCurrent);
		editAccountPage.clickOnDynamicButton("Submit");
	
		ExtentTestManager.getTest().log(LogStatus.INFO, "EditAccount - Step 02: Verify Account Type are updated");
		verifyTrue(editAccountPage.isDynamicHeaderTextDisplayed("Account details updated Successfully!!!"));
		verifyEquals(editAccountPage.getDynamicCellValue("Account ID"), firstAccountID);
		verifyEquals(editAccountPage.getDynamicCellValue("Customer ID"), customerID);
		verifyEquals(editAccountPage.getDynamicCellValue("Customer Name"), newCustomerName);
		verifyEquals(editAccountPage.getDynamicCellValue("Email"), editRandomEmail);
		verifyEquals(editAccountPage.getDynamicCellValue("Account Type"), accountTypeCurrent);
		verifyEquals(editAccountPage.getDynamicCellValue("Date of Opening"), getToday());
		verifyEquals(editAccountPage.getDynamicCellValue("Current Amount"), "5000");
		
	}
	
	@Test
	public void Payment_06_DepositToAccount(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_06_DepositToAccount");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "DepositToAccount - Step 01: Deposit to Account");
		depositPage = (DepositPO) editAccountPage.openMultiplePages("Deposit");
		verifyTrue(depositPage.isDynamicHeaderTextDisplayed("Amount Deposit Form"));
		depositPage.inputToDynamicTextboxTextarea("accountno", firstAccountID);
		depositPage.inputToDynamicTextboxTextarea("ammount", "2000");
		depositPage.inputToDynamicTextboxTextarea("desc", "Deposit to First Account");
		depositPage.clickOnDynamicButton("Submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "DepositToAccount - Step 02: Verify Account Balance are updated");
		verifyTrue(depositPage.isDynamicHeaderTextDisplayed("Transaction details of Deposit for Account " + firstAccountID));
		verifyEquals(depositPage.getDynamicCellValue("Account No"), firstAccountID);
		verifyEquals(depositPage.getDynamicCellValue("Amount Credited"), "2000");
		verifyEquals(depositPage.getDynamicCellValue("Type of Transaction"), "Deposit");
		verifyEquals(depositPage.getDynamicCellValue("Description"), "Deposit to");
		verifyEquals(depositPage.getDynamicCellValue("Current Balance"), "7000");
	}
	
	@Test
	public void Payment_07_WithdrawalFromAccount(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_07_WithdrawalFromAccount");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "WithdrawalFromAccount - Step 01: Withdrawal From Account");
		withdrawalPage = (WithdrawalPO) depositPage.openMultiplePages("Withdrawal");
		verifyTrue(withdrawalPage.isDynamicHeaderTextDisplayed("Amount Withdrawal Form"));
		withdrawalPage.inputToDynamicTextboxTextarea("accountno", firstAccountID);
		withdrawalPage.inputToDynamicTextboxTextarea("ammount", "3000");
		withdrawalPage.inputToDynamicTextboxTextarea("desc", "Withdrawal from First Account");
		withdrawalPage.clickOnDynamicButton("Submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "WithdrawalFromAccount - Step 02: Verify Account Balance are updated");
		verifyTrue(withdrawalPage.isDynamicHeaderTextDisplayed("Transaction details of Withdrawal for Account " + firstAccountID));
		verifyEquals(withdrawalPage.getDynamicCellValue("Account No"), firstAccountID);
		verifyEquals(withdrawalPage.getDynamicCellValue("Amount Debited"), "3000");
		verifyEquals(withdrawalPage.getDynamicCellValue("Type of Transaction"), "Withdrawal");
		verifyEquals(withdrawalPage.getDynamicCellValue("Description"), "Withdrawal");
		verifyEquals(withdrawalPage.getDynamicCellValue("Current Balance"), "4000");
	}
	
	@Test
	public void Payment_08_TransferToOtherAccount(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_08_TransferToOtherAccount");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "TransferToOtherAccount - Step 01: Transfer To Other Account");
		fundTransferPage = (FundTransferPO) withdrawalPage.openMultiplePages("Fund Transfer");
		verifyTrue(fundTransferPage.isDynamicHeaderTextDisplayed("Fund transfer"));
		fundTransferPage.inputToDynamicTextboxTextarea("payersaccount", firstAccountID);
		fundTransferPage.inputToDynamicTextboxTextarea("payeeaccount", secondAccountID);
		fundTransferPage.inputToDynamicTextboxTextarea("ammount", "2000");
		fundTransferPage.inputToDynamicTextboxTextarea("desc", "Transfer");
		fundTransferPage.clickOnDynamicButton("Submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "TransferToOtherAccount - Step 02: Verify Account Balance are updated");
		verifyTrue(fundTransferPage.isDynamicHeaderTextDisplayed("Fund Transfer Details"));
		verifyEquals(fundTransferPage.getDynamicCellValue("From Account Number"), firstAccountID);
		verifyEquals(fundTransferPage.getDynamicCellValue("To Account Number"), secondAccountID);
		verifyEquals(fundTransferPage.getDynamicCellValue("Amount"), "2000");
		verifyEquals(fundTransferPage.getDynamicCellValue("Description"), "Transfer");
	}
	
	@Test
	public void Payment_09_CheckCurrentBalance(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_09_CheckCurrentBalance");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "CheckCurrentBalance - Step 01: Check 1st Account's Balance");
		balanceEnquiryPage = (BalanceEnquiryPO) fundTransferPage.openMultiplePages("Balance Enquiry");
		verifyTrue(balanceEnquiryPage.isDynamicHeaderTextDisplayed("Balance Enquiry Form"));
		balanceEnquiryPage.inputToDynamicTextboxTextarea("accountno", firstAccountID);
		balanceEnquiryPage.clickOnDynamicButton("Submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "CheckCurrentBalance - Step 02: Verify 1st Account's Balance is displayed correctly");
		verifyTrue(balanceEnquiryPage.isDynamicHeaderTextDisplayed("Balance Details for Account " + firstAccountID));
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Account No"), firstAccountID);
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Type of Account"), accountTypeCurrent);
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Balance"), "2000");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "CheckCurrentBalance - Step 03: Check 2nd Account's Balance");
		balanceEnquiryPage = (BalanceEnquiryPO) balanceEnquiryPage.openMultiplePages("Balance Enquiry");
		verifyTrue(balanceEnquiryPage.isDynamicHeaderTextDisplayed("Balance Enquiry Form"));
		balanceEnquiryPage.inputToDynamicTextboxTextarea("accountno", secondAccountID);
		balanceEnquiryPage.clickOnDynamicButton("Submit");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "CheckCurrentBalance - Step 04: Verify 2nd Account's Balance is displayed correctly");
		verifyTrue(balanceEnquiryPage.isDynamicHeaderTextDisplayed("Balance Details for Account " + secondAccountID));
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Account No"), secondAccountID);
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Type of Account"), accountTypeSavings);
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Balance"), "3000"); // Must be 3000 --> This is Bug
	}

	@Test
	public void Payment_10_DeleteAllAccount(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_10_DeleteAllAccount");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "DeleteAllAccount - Step 01: Delete 1st Account");
		deleteAccountPage = (DeleteAccountPO) balanceEnquiryPage.openMultiplePages("Delete Account");
		verifyTrue(deleteAccountPage.isDynamicHeaderTextDisplayed("Delete Account Form"));
		deleteAccountPage.inputToDynamicTextboxTextarea("accountno", firstAccountID);
		deleteAccountPage.clickOnDynamicButton("Submit");
		deleteAccountPage.acceptAlert();
		managerHomePage = (ManagerHomePO) deleteAccountPage.clickAcceptAlert();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "DeleteAllAccount - Step 02: Verify 1st Account is deleted");
		deleteAccountPage = (DeleteAccountPO) managerHomePage.openMultiplePages("Delete Account");
		verifyTrue(deleteAccountPage.isDynamicHeaderTextDisplayed("Delete Account Form"));
		deleteAccountPage.inputToDynamicTextboxTextarea("accountno", firstAccountID);
		deleteAccountPage.clickOnDynamicButton("Submit");
		deleteAccountPage.acceptAlert();
		verifyEquals(deleteAccountPage.getTextAlert(), "Account does not exist");
		deleteAccountPage.acceptAlert();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "DeleteAllAccount - Step 03: Delete 2nd Account");
		verifyTrue(deleteAccountPage.isDynamicHeaderTextDisplayed("Delete Account Form"));
		deleteAccountPage.inputToDynamicTextboxTextarea("accountno", secondAccountID);
		deleteAccountPage.clickOnDynamicButton("Submit");
		deleteAccountPage.acceptAlert();
		managerHomePage = (ManagerHomePO) deleteAccountPage.clickAcceptAlert();

		ExtentTestManager.getTest().log(LogStatus.INFO, "DeleteAllAccount - Step 04: Verify 2nd Account is deleted");
		deleteAccountPage = (DeleteAccountPO) managerHomePage.openMultiplePages("Delete Account");
		verifyTrue(deleteAccountPage.isDynamicHeaderTextDisplayed("Delete Account Form"));
		deleteAccountPage.inputToDynamicTextboxTextarea("accountno", secondAccountID);
		deleteAccountPage.clickOnDynamicButton("Submit");
		deleteAccountPage.acceptAlert();
		verifyEquals(deleteAccountPage.getTextAlert(), "Account does not exist");
		managerHomePage = (ManagerHomePO) deleteAccountPage.clickAcceptAlert();
		
	}
	
	@Test
	public void Payment_11_DeleteCustomer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_11_DeleteCustomer");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "DeleteCustomer - Step 01: Delete Customer");
		deleteCustomerPage = (DeleteCustomerPO) managerHomePage.openMultiplePages("Delete Customer");
		verifyTrue(deleteCustomerPage.isDynamicHeaderTextDisplayed("Delete Customer Form"));
		deleteCustomerPage.inputToDynamicTextboxTextarea("cusid", customerID);
		deleteCustomerPage.clickOnDynamicButton("Submit");
		deleteCustomerPage.acceptAlert();
		managerHomePage = (ManagerHomePO) deleteCustomerPage.clickAcceptAlert();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "DeleteCustomer - Step 02: Verify Customer is deleted");
		deleteCustomerPage = (DeleteCustomerPO) managerHomePage.openMultiplePages("Delete Customer");
		verifyTrue(deleteCustomerPage.isDynamicHeaderTextDisplayed("Delete Customer Form"));
		deleteCustomerPage.inputToDynamicTextboxTextarea("cusid", customerID);
		deleteCustomerPage.clickOnDynamicButton("Submit");
		deleteCustomerPage.acceptAlert();
		verifyEquals(deleteAccountPage.getTextAlert(), "Customer does not exist!!");
		deleteCustomerPage.acceptAlert();
	}
	
	@Test
	public void Payment_12_LogoutSystem(Method method) {
		ExtentTestManager.startTest(method.getName(), "Payment_12_LogoutSystem");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "LogoutSystem - Step 01: Log out");
		loginPage = (LoginPO) deleteCustomerPage.clickOnLogoutLink();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "LogoutSystem - Step 02: Verify the Login Page is displayed");
		verifyEquals(loginPage.getPageTitle(), "Guru99 Bank Home Page");
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
	}
}
