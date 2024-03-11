package rest.notesapi.listeners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener implements ITestListener {

	private static ExtentReports extent;
	private String dir = System.getProperty("user.dir");
	private String testOutput = dir + "\\test-output";
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		String suiteName = context.getSuite().getName();
		LocalDateTime testExecutionDateTime = LocalDateTime.now();
		String reportPath = testOutput + "\\Extent-Report" + "_" + suiteName + "_"
				+ DateTimeFormatter.ofPattern("ddMMM", Locale.ENGLISH).format(testExecutionDateTime) + "_"
				+ DateTimeFormatter.ofPattern("HHmmss", Locale.ENGLISH).format(testExecutionDateTime) + ".html";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();

		sparkReporter.config().setReportName(
				"API Automation Framework developed using Rest Assured and TestNG, written by Harshit Bhardwaj");
		sparkReporter.config().setDocumentTitle("Rest Notes API Test Automation Results Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extent.attachReporter(sparkReporter);
		System.out.println("Suite started: " + suiteName);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// Create a new ExtentTest for each test case
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail("Test failed: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test skipped: " + result.getThrowable());
	}

	// Additional method to get the current ExtentTest
	public static ExtentTest getTest() {
		return test.get();
	}
}
