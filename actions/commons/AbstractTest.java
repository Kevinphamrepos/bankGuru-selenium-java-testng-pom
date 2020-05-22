package commons;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import reportConfig.VerificationFailures;

public class AbstractTest {
	private WebDriver driver;
	private String rootFolder = System.getProperty("user.dir");
	private String downloadPath = rootFolder + "\\downloadedFiles";

	protected final Log log;
	
	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	protected synchronized WebDriver getBrowserDriverAutoUrl(String browserName, String url) {
		if (browserName.equalsIgnoreCase("firefox_ui")) {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, rootFolder + "\\test-output\\Firefox Logs.txt");
			FirefoxOptions firefoxOptions = new FirefoxOptions(); 
			firefoxOptions.addPreference("browser.download.folderList", 2); 
			firefoxOptions.addPreference("browser.download.dir", downloadPath);
			firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false); 
			firefoxOptions.addPreference("browser.download.useDownloadDir", true); 
			firefoxOptions.addPreference("browser.helperApps.alwaysAsk.force", false);
			firefoxOptions.addPreference("browser.helperApps.neverAsk.openFile", "application/txt"); 
			firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/txt,text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
			firefoxOptions.addPreference("pdfjs.disabled", true); 
			firefoxOptions.setCapability("moz:useNonSpecCompliantPointerOrigin", true);
			driver = new FirefoxDriver(firefoxOptions);
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("firefox_headless")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions headlessFirefoxOptions = new FirefoxOptions();
			headlessFirefoxOptions.setHeadless(true);
			driver = new FirefoxDriver(headlessFirefoxOptions);
		} else if (browserName.equalsIgnoreCase("chrome_ui")) {
			WebDriverManager.chromedriver().setup();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome_headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions headlessChromeOptions = new ChromeOptions();
			headlessChromeOptions.addArguments("headless");
			driver = new ChromeDriver(headlessChromeOptions);
		} else if (browserName.equalsIgnoreCase("brave")) {
			System.setProperty("webdriver.chrome.driver", rootFolder + "\\resources\\chromedriver_v80_brave.exe");
			ChromeOptions braveOptions = new ChromeOptions();
			braveOptions.setBinary("C:\\Program Files (x86)\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			braveOptions.setExperimentalOption("useAutomationExtension", false);
			braveOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));    
			braveOptions.addArguments("--disable-notifications");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			braveOptions.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(braveOptions);
			driver.manage().window().maximize();
		} else {
			System.out.println("Please input your browser name!");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
	
	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);
			String cmd = "";
			if (driver != null) {
				driver.quit();
			}
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			}
			
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESSFULLY ----------");
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	protected int randomNumber() {
		Random random = new Random();
		return random.nextInt(99999);
	}
	
	protected void sleepInSecondAbstractTest(long numberInSecond) {
		try {
			Thread.sleep(numberInSecond * 1000);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return day + "";
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return month + "";
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		return now.getYear() + "";
	}
	
	protected String getCurrentWeekday() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		return now.getDayOfWeek() + "";
	}

	protected String getToday() {
		return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
	}
	
	protected String getTodayWithWeekday() { 
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"); 
		String weekday = formatter.format(date);
		System.out.println(weekday);
		return weekday;
	}
	
	protected String getTodayWithWeekdayByTimeZone(String zoneID) {
		LocalDate date = LocalDate.now(ZoneId.of(zoneID));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"); 
		String weekday = formatter.format(date);
		System.out.println(weekday);
		return weekday;
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public boolean isSortedStringAscending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortingArray = new ArrayList<String>();
		for (String child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		return sortingArray.equals(arrayList);

	}

	public boolean isSortedStringDescending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortingArray = new ArrayList<String>();
		for (String child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		Collections.reverse(sortingArray);
		return sortingArray.equals(arrayList);
	}

	public boolean isSortedFloatAscending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}
		ArrayList<Float> sortingArray = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		return sortingArray.equals(arrayList);

	}

	public boolean isSortedStringAscendingJDK8(WebDriver driver, String locator) {
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		List<String> items = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedList = items;
		Collections.sort(sortedList);
		return items.equals(sortedList);
	}	
	
	public boolean isSortedStringDescendingJDK8(WebDriver driver, String locator) {
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		List<String> itemList = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortingList = itemList;
		Collections.sort(sortingList);
		Collections.reverse(sortingList);
		return sortingList.equals(itemList);
	}	
	
	public void selectItemInHTMLDropdown(WebDriver driver, String dropdownXpath, String expectedItem) {
		WebElement element = driver.findElement(By.xpath(dropdownXpath));
		Select select;
		select = new Select(element);
		select.selectByVisibleText(expectedItem);
	}

	@BeforeSuite
	public void deleteAllFilesInReportNGScreenshotFolder() {
		System.out.println("---------- START deleting Screenshot Files ----------");
		deleteAllFilesInFolder();
		System.out.println("---------- FINISH deleting Screenshot Files ----------");
	}
	
	public void deleteAllFilesInFolder() {
		try {
			String rootFolder = System.getProperty("user.dir");
			String screenshotFolderPath = rootFolder + "\\test-output\\screenshotReportNG";
			File file = new File(screenshotFolderPath);
			File[] listFiles = file.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				if (listFiles[i].isFile()) {
					System.out.println(listFiles[i].getName());
					new File(listFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}

