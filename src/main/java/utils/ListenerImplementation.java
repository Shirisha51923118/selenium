package utils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ListenerImplementation implements ITestListener{
	ExtentReports reports;
	ExtentTest test;
	public static ExtentTest stest;
	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReports/report.html");
		spark.config().setDocumentTitle("Extent Reports");
		spark.config().setReportName("Skillrary");
		spark.config().setTheme(Theme.DARK);
		
		reports = new ExtentReports();
		reports.attachReporter(spark);
		
		reports.setSystemInfo("Application", "Skillrary");
		reports.setSystemInfo("OS", "Windows 11");
		reports.setSystemInfo("Browser", "Chrome");
		reports.setSystemInfo("JDK", "21");
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName());
		stest = test;
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getMethod().getMethodName() + " Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+ " Fail");
		test.fail(result.getThrowable());
		String path = new DriverUtility().getScreenshot(BaseClass.sdriver, result.getMethod().getMethodName());
		test.addScreenCaptureFromPath(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName()+ " Skip");
		test.skip(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
