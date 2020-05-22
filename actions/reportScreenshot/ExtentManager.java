package reportScreenshot;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			String rootFolder = System.getProperty("user.dir");
			extent = new ExtentReports(rootFolder + "\\test-output\\ExtentReportV2\\ExtentReportResultsV2WithScreenshot.html", true);
		}
		return extent;
	}
}
